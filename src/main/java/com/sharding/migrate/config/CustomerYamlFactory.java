package com.sharding.migrate.config;

import com.huice.middleware.distributor.exception.MdAssert;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;

import java.util.List;
import java.util.Map;

import static org.springframework.boot.context.config.ConfigFileApplicationListener.CONFIG_LOCATION_PROPERTY;


/**
 * The type Customer yaml factory.
 */
public abstract class CustomerYamlFactory {
    private static final Logger log = LoggerFactory.getLogger(CustomerYamlFactory.class);
    private static final String CUSTOMER_CONFIG_FILE = "center_config.yml";
    private static List<Map<String, Object>> propertiesList;

    static {
        String location = System.getProperty(CONFIG_LOCATION_PROPERTY);
        log.info("Spring Config Properties spring.config.location Value ：{}", location);
        String profile = ActiveProfileConstant.profile;
        MdAssert.checkTrue(StringUtils.isBlank(profile), new RuntimeException("active profile can not be null!"));
        // 本地启动加载classpath路径，服务器启动加载 XXX/config/ 路径
        Resource resource = StringUtils.isBlank(location) ? new ClassPathResource("/" + profile + "/" + CUSTOMER_CONFIG_FILE) : new PathResource(location + "/" + profile + "/" + CUSTOMER_CONFIG_FILE);
        com.huice.middleware.distributor.config.yml.CustomerYamlLoader yamlLoader = new com.huice.middleware.distributor.config.yml.CustomerYamlLoader(resource);
        propertiesList = yamlLoader.load();
        MdAssert.checkTrue(CollectionUtils.isEmpty(propertiesList), new RuntimeException("Customer Config Properties Is Empty, Please Reconfirm The Config File center_config.yml !"));
        log.info("Customer Config Properties Load Completed !");
    }

    /**
     * Gets properties.
     *
     * @param key the key
     * @return the properties
     */
    public static Object getProperties(String key) {
        return propertiesList.get(0).get(key);
    }
}
