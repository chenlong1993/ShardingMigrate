package com.sharding.migrate.datasource;

import com.huice.middleware.distributor.orm.annotation.MdColumn;
import com.huice.middleware.distributor.orm.annotation.MdEntity;

import java.sql.Timestamp;
import java.util.Objects;

//@MdEntity(name = "db_datasource")
public class DbDatasource {
//    @MdColumn(name = "rec_id", primaryKey = true)
    private Long recId;
//    @MdColumn(name = "service_id", order = 0)
    private Integer serviceId;
//    @MdColumn(name = "dbsource_type", order = 1)
    private String dbsourceType;
    @MdColumn(name = "inner_host", order = 2)
    private String innerHost;
    @MdColumn(name = "front_host", order = 3)
    private String frontHost;
    @MdColumn(name = "host_port", order = 4)
    private String hostPort;
    @MdColumn(name = "salt", order = 5)
    private String salt;
    @MdColumn(name = "database_name", order = 6)
    private String databaseName;
    @MdColumn(name = "db_user", order = 7)
    private String dbUser;
    @MdColumn(name = "db_pwd", order = 8)
    private String dbPwd;
    @MdColumn(name = "is_master", order = 9)
    private Byte isMaster;
    @MdColumn(name = "follow_mater_inner_host", order = 10)
    private String followMaterInnerHost;
    @MdColumn(name = "slave_node", order = 11)
    private String slaveNode;
    @MdColumn(name = "hash_check", order = 12)
    private String hashCheck;
    @MdColumn(name = "is_default", order = 13)
    private Byte isDefault;
    @MdColumn(name = "remark", order = 14)
    private String remark;
    @MdColumn(name = "modified", order = 15)
    private Timestamp modified;

    public Long getRecId() {
        return recId;
    }

    public void setRecId(Long recId) {
        this.recId = recId;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getDbsourceType() {
        return dbsourceType;
    }

    public void setDbsourceType(String dbsourceType) {
        this.dbsourceType = dbsourceType;
    }

    public String getInnerHost() {
        return innerHost;
    }

    public void setInnerHost(String innerHost) {
        this.innerHost = innerHost;
    }

    public String getFrontHost() {
        return frontHost;
    }

    public void setFrontHost(String frontHost) {
        this.frontHost = frontHost;
    }

    public String getHostPort() {
        return hostPort;
    }

    public void setHostPort(String hostPort) {
        this.hostPort = hostPort;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getDbUser() {
        return dbUser;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    public String getDbPwd() {
        return dbPwd;
    }

    public void setDbPwd(String dbPwd) {
        this.dbPwd = dbPwd;
    }

    public Byte getIsMaster() {
        return isMaster;
    }

    public void setIsMaster(Byte isMaster) {
        this.isMaster = isMaster;
    }

    public String getFollowMaterInnerHost() {
        return followMaterInnerHost;
    }

    public void setFollowMaterInnerHost(String followMaterInnerHost) {
        this.followMaterInnerHost = followMaterInnerHost;
    }

    public String getSlaveNode() {
        return slaveNode;
    }

    public void setSlaveNode(String slaveNode) {
        this.slaveNode = slaveNode;
    }

    public String getHashCheck() {
        return hashCheck;
    }

    public void setHashCheck(String hashCheck) {
        this.hashCheck = hashCheck;
    }

    public Byte getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Byte isDefault) {
        this.isDefault = isDefault;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Timestamp getModified() {
        return modified;
    }

    public void setModified(Timestamp modified) {
        this.modified = modified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DbDatasource that = (DbDatasource) o;
        return Objects.equals(recId, that.recId) &&
                Objects.equals(serviceId, that.serviceId) &&
                Objects.equals(dbsourceType, that.dbsourceType) &&
                Objects.equals(innerHost, that.innerHost) &&
                Objects.equals(frontHost, that.frontHost) &&
                Objects.equals(hostPort, that.hostPort) &&
                Objects.equals(salt, that.salt) &&
                Objects.equals(databaseName, that.databaseName) &&
                Objects.equals(dbUser, that.dbUser) &&
                Objects.equals(dbPwd, that.dbPwd) &&
                Objects.equals(isMaster, that.isMaster) &&
                Objects.equals(followMaterInnerHost, that.followMaterInnerHost) &&
                Objects.equals(slaveNode, that.slaveNode) &&
                Objects.equals(hashCheck, that.hashCheck) &&
                Objects.equals(isDefault, that.isDefault) &&
                Objects.equals(remark, that.remark) &&
                Objects.equals(modified, that.modified);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recId, serviceId, dbsourceType, innerHost, frontHost, hostPort, salt, databaseName, dbUser, dbPwd, isMaster, followMaterInnerHost, slaveNode, hashCheck, isDefault, remark, modified);
    }
}
