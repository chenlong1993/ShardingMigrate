package com.sharding.migrate.datasource;

import com.huice.middleware.distributor.enums.JsonType;
import com.huice.middleware.distributor.exception.MdAssert;
import com.huice.middleware.distributor.orm.annotation.MdColumn;
import com.huice.middleware.distributor.orm.annotation.MdJsonColumn;
import com.huice.middleware.distributor.orm.result.ResultSetMapper;
import com.huice.middleware.distributor.orm.template.Template;
import com.huice.middleware.distributor.vo.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.ArgumentTypePreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.lang.Nullable;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.CollectionUtils;

import javax.sql.DataSource;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.TreeMap;

import static com.huice.middleware.distributor.util.SymbolUtil.JSON_EMPTY_ARRAY;
import static com.huice.middleware.distributor.util.SymbolUtil.JSON_EMPTY_OBJECT;

public class CenterJdbcTemplate extends JdbcTemplate implements Template {
    private static final Logger log = LoggerFactory.getLogger(CenterJdbcTemplate.class);

    private TransactionTemplate transactionTemplate;

    CenterJdbcTemplate(DataSource dataSource) {
        super(dataSource);
        transactionTemplate = new TransactionTemplate(new DataSourceTransactionManager(dataSource));
    }

    public TransactionTemplate getTransactionTemplate() {
        return transactionTemplate;
    }

    /**
     * 查询列表
     */
    public <T> Collection<T> query(String sql, Class<T> clazz, Object... args) {
        return query(sql, args, (resultSet, i) -> ResultSetMapper.resultSet2Object(resultSet, clazz));
    }

    /**
     * 查询单个对象转换为特定的md—entity对象
     */
    public <T> T queryForMdObject(String sql, Class<T> clazz, Object... args) {
        return queryForObject(sql, args, (resultSet, i) -> ResultSetMapper.resultSet2Object(resultSet, clazz));
    }

    /**
     * 通过主键全量更新
     */
    public <T> int fullUpdateByPrimaryKey(String sql, Class<T> clazz, T t) {
        return update(sql, fullUpdateByPrimaryKey(clazz, t));
    }

    @Override
    @Nullable
    public <T> T queryForObject(String sql, @Nullable Object[] args, RowMapper<T> rowMapper) throws DataAccessException {
        List<T> results = query(sql, args, new RowMapperResultSetExtractor<>(rowMapper, 1));
        if (CollectionUtils.isEmpty(results)) {
            return null;
        }
        if (results.size() > 1) {
            throw new IncorrectResultSizeDataAccessException(1, results.size());
        }
        return results.iterator().next();
    }

    @Override
    public <T> List<T> query(String sql, @Nullable Object[] args, RowMapper<T> rowMapper) throws DataAccessException {
        return Optional.ofNullable(query(sql, args, new RowMapperResultSetExtractor<>(rowMapper)))
                .orElse((List<T>) org.apache.commons.collections4.CollectionUtils.emptyCollection());
    }

    @Override
    @Nullable
    public <T> T queryForObject(String sql, RowMapper<T> rowMapper, @Nullable Object... args) throws DataAccessException {
        List<T> results = query(sql, args, new RowMapperResultSetExtractor<>(rowMapper, 1));
        if (CollectionUtils.isEmpty(results)) {
            return null;
        }
        if (results.size() > 1) {
            throw new IncorrectResultSizeDataAccessException(1, results.size());
        }
        return results.iterator().next();
    }

    /**
     * 插入操作
     */
    public <T> int insert(String sql, Class<T> clazz, T t) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
            PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
            Map<Integer, Object> params = new TreeMap<>(Comparator.comparingInt(a -> a));
            for (PropertyDescriptor d : descriptors) {
                String columnsName = d.getName();
                if (!"class".equals(columnsName)) {
                    Field field = clazz.getDeclaredField(columnsName);
                    MdColumn annotation = field.getAnnotation(MdColumn.class);
                    int index = annotation.order();
                    if (!annotation.primaryKey() && index > -1) {
                        Object value = d.getReadMethod().invoke(t);
                        MdAssert.checkNull(value, ResultCode.CODE_ORM_INSERT_FILED_IS_NULL, columnsName);
                        params.put(index, value);
                    }
                }
            }
            return update(sql, params.values().toArray());
        } catch (Exception e) {
            throw new RuntimeException("insert operate failed", e);
        }
    }

    /**
     * 插入或者更新（仅主键重复时）
     *
     * @param sql   sql
     * @param clazz 类
     * @param t     实体
     * @param <T>   类型
     * @return 影响的行数
     * @throws InvocationTargetException InvocationTargetException
     * @throws IntrospectionException    IntrospectionException
     * @throws IllegalAccessException    IllegalAccessException
     * @throws NoSuchFieldException      NoSuchFieldException
     */
    public <T> int migrationInsertOnDuplicate(String sql, Class<T> clazz, T t)
            throws InvocationTargetException, IntrospectionException,
            IllegalAccessException, NoSuchFieldException {
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
        PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
        Map<Integer, Object> params = new TreeMap<>(Comparator.comparingInt(a -> a));
        for (PropertyDescriptor d : descriptors) {
            String columnsName = d.getName();
            if (!"class".equals(columnsName)) {
                Field field = clazz.getDeclaredField(columnsName);
                MdColumn annotation = field.getAnnotation(MdColumn.class);
                int index = annotation.order();
                if (annotation.primaryKey() || index > -1) {
                    assignment(t, params, d, field, index);
                }
            }
        }
        // 除了主键，其他的复制一份
        Object[] args = params.values().toArray();
        Object[] newArgs = new Object[args.length * 2 - 1];
        int pos = args.length;
        System.arraycopy(args, 0, newArgs, 0, pos);
        for (int i = 1; i < args.length; i++) {
            newArgs[pos++] = args[i];
        }
        return update(sql, newArgs);
    }

    private <T> void assignment(T t, Map<Integer, Object> params, PropertyDescriptor d, Field field, int index) throws IllegalAccessException, InvocationTargetException {
        Object value = d.getReadMethod().invoke(t);
        if (Objects.isNull(value)) {
            // 空值处理
            MdJsonColumn jsonColumn = field.getAnnotation(MdJsonColumn.class);
            if (Objects.nonNull(jsonColumn)) {
                // 如果是空的话，给定默认值
                if (jsonColumn.type().equals(JsonType.ARRAY)) {
                    params.put(index, JSON_EMPTY_ARRAY);
                } else {
                    params.put(index, JSON_EMPTY_OBJECT);
                }
            } else {
                params.put(index, "");
            }
        } else {
            // 赋值
            params.put(index, value);
        }
    }

    /**
     * 批量插入操作
     */
    public <T> int batchInsert(String sql, Class<T> clazz, Collection<T> collection) {
        return update(sql, buildInsertArgs(clazz, collection));
    }

    /**
     * Create a new arg-based PreparedStatementSetter using the args passed in.
     * <p>By default, we'll create an {@link ArgumentPreparedStatementSetter}.
     * This method allows for the creation to be overridden by subclasses.
     *
     * @param args object array with arguments
     * @return the new PreparedStatementSetter to use
     */
    @Override
    protected PreparedStatementSetter newArgPreparedStatementSetter(@Nullable Object[] args) {
        preArgsNullCheck(args);
        return new ArgumentPreparedStatementSetter(args);
    }

    /**
     * Create a new arg-type-based PreparedStatementSetter using the args and types passed in.
     * <p>By default, we'll create an {@link ArgumentTypePreparedStatementSetter}.
     * This method allows for the creation to be overridden by subclasses.
     *
     * @param args     object array with arguments
     * @param argTypes int array of SQLTypes for the associated arguments
     * @return the new PreparedStatementSetter to use
     */
    @Override
    protected PreparedStatementSetter newArgTypePreparedStatementSetter(Object[] args, int[] argTypes) {
        preArgsNullCheck(args);
        return new ArgumentTypePreparedStatementSetter(args, argTypes);
    }

    @Override
    @Nullable
    public <T> T query(String sql, @Nullable Object[] args, ResultSetExtractor<T> rse) throws DataAccessException {
        return query(sql, newArgPreparedStatementSetter(args), rse);
    }

    @Override
    @Nullable
    public <T> T query(String sql, ResultSetExtractor<T> rse, @Nullable Object... args) throws DataAccessException {
        return query(sql, newArgPreparedStatementSetter(args), rse);
    }

    @Override
    public void query(String sql, Object[] args, RowCallbackHandler rch) throws DataAccessException {
        query(sql, newArgPreparedStatementSetter(args), rch);
    }

    @Override
    public void query(String sql, RowCallbackHandler rch, @Nullable Object... args) throws DataAccessException {
        query(sql, newArgPreparedStatementSetter(args), rch);
    }

    @Override
    public int update(String sql, @Nullable Object... args) throws DataAccessException {
        return update(sql, newArgPreparedStatementSetter(args));
    }

    @Override
    @Nullable
    public <T> T query(String sql, Object[] args, int[] argTypes, ResultSetExtractor<T> rse) throws DataAccessException {
        return query(sql, newArgTypePreparedStatementSetter(args, argTypes), rse);
    }

    @Override
    public void query(String sql, Object[] args, int[] argTypes, RowCallbackHandler rch) throws DataAccessException {
        query(sql, newArgTypePreparedStatementSetter(args, argTypes), rch);
    }

    @Override
    public int update(String sql, Object[] args, int[] argTypes) throws DataAccessException {
        return update(sql, newArgTypePreparedStatementSetter(args, argTypes));
    }
}
