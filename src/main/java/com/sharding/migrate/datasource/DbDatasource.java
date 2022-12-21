package com.sharding.migrate.datasource;


import java.sql.Timestamp;
import java.util.Objects;

public class DbDatasource {
    private Long recId;
    private Integer serviceId;
    private String dbsourceType;
    private String innerHost;
    private String frontHost;
    private String hostPort;
    private String salt;
    private String databaseName;
    private String dbUser;
    private String dbPwd;
    private Byte isMaster;
    private String followMaterInnerHost;
    private String slaveNode;
    private String hashCheck;
    private Byte isDefault;
    private String remark;
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
