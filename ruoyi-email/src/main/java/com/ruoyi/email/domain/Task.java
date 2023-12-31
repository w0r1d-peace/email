package com.ruoyi.email.domain;

import com.ruoyi.common.enums.email.ConnStatusEnum;
import com.ruoyi.common.enums.email.ProtocolTypeEnum;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import javax.validation.constraints.NotBlank;

/**
 * 邮箱任务对象 mailbox_task
 * 
 * @author tangJM
 * @date 2023-07-31
 */
public class Task extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 邮箱账号 */
    @NotBlank(message = "邮箱账号不能为空")
    @Excel(name = "邮箱账号")
    private String account;

    /** 邮箱密码 */
    @NotBlank(message = "邮箱密码不能为空")
    @Excel(name = "邮箱密码")
    private String password;

    /** 邮箱别名 */
    @Excel(name = "邮箱别名")
    private String alias;

    /** 连接状态: 1.正常 2.异常 */
    @Excel(name = "连接状态: 1.正常 2.异常")
    private Integer connStatus;

    /** 连接异常原因 */
    @Excel(name = "连接异常原因")
    private String connExceptionReason;

    @Excel(name = "协议类型 1.IMAP 2.POP3 3.EXCHANGE 4.SMTP")
    private Integer protocolType;

    /** 收件服务器 */
    @Excel(name = "收件服务器")
    private String receivingServer;

    /** 收件端口 */
    @Excel(name = "收件端口")
    private Integer receivingPort;

    /** 收件SSL: 0.否 1.是 */
    @Excel(name = "收件SSL: 0.否 1.是")
    private Boolean receivingSslFlag;

    /** 发件服务器 */
    @Excel(name = "发件服务器")
    private String outgoingServer;

    /** 发件端口 */
    @Excel(name = "发件端口")
    private Integer outgoingPort;

    /** 发件SSL: 0.否 1.是 */
    @Excel(name = "发件SSL: 0.否 1.是")
    private Boolean outgoingSslFlag;

    /** 自定义代理: 0.关闭 1.开启 */
    @Excel(name = "自定义代理: 0.关闭 1.开启")
    private Boolean customProxyFlag;

    /** 代理服务器类型 */
    @Excel(name = "代理服务器类型")
    private Integer proxyServerType;

    /** 代理服务器 */
    @Excel(name = "代理服务器")
    private String proxyServer;

    /** 代理端口 */
    @Excel(name = "代理端口")
    private Integer proxyPort;

    /** 代理用户名 */
    @Excel(name = "代理用户名")
    private String proxyUsername;

    /** 代理密码 */
    @Excel(name = "代理密码")
    private String proxyPassword;

    /** 同步文件夹 */
    @Excel(name = "同步文件夹")
    private Boolean synchronizeFolderFlag;

    /** 删除标志(0代表存在2代表删除) */
    private String delFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Integer getConnStatus() {
        return connStatus;
    }

    public void setConnStatus(Integer connStatus) {
        this.connStatus = connStatus;
    }

    public String getConnExceptionReason() {
        return connExceptionReason;
    }

    public void setConnExceptionReason(String connExceptionReason) {
        this.connExceptionReason = connExceptionReason;
    }

    public Integer getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(Integer protocolType) {
        this.protocolType = protocolType;
    }

    public String getReceivingServer() {
        return receivingServer;
    }

    public void setReceivingServer(String receivingServer) {
        this.receivingServer = receivingServer;
    }

    public Integer getReceivingPort() {
        return receivingPort;
    }

    public void setReceivingPort(Integer receivingPort) {
        this.receivingPort = receivingPort;
    }

    public Boolean getReceivingSslFlag() {
        return receivingSslFlag;
    }

    public void setReceivingSslFlag(Boolean receivingSslFlag) {
        this.receivingSslFlag = receivingSslFlag;
    }

    public String getOutgoingServer() {
        return outgoingServer;
    }

    public void setOutgoingServer(String outgoingServer) {
        this.outgoingServer = outgoingServer;
    }

    public Integer getOutgoingPort() {
        return outgoingPort;
    }

    public void setOutgoingPort(Integer outgoingPort) {
        this.outgoingPort = outgoingPort;
    }

    public Boolean getOutgoingSslFlag() {
        return outgoingSslFlag;
    }

    public void setOutgoingSslFlag(Boolean outgoingSslFlag) {
        this.outgoingSslFlag = outgoingSslFlag;
    }

    public Boolean getCustomProxyFlag() {
        return customProxyFlag;
    }

    public void setCustomProxyFlag(Boolean customProxyFlag) {
        this.customProxyFlag = customProxyFlag;
    }

    public Integer getProxyServerType() {
        return proxyServerType;
    }

    public void setProxyServerType(Integer proxyServerType) {
        this.proxyServerType = proxyServerType;
    }

    public String getProxyServer() {
        return proxyServer;
    }

    public void setProxyServer(String proxyServer) {
        this.proxyServer = proxyServer;
    }

    public Integer getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(Integer proxyPort) {
        this.proxyPort = proxyPort;
    }

    public String getProxyUsername() {
        return proxyUsername;
    }

    public void setProxyUsername(String proxyUsername) {
        this.proxyUsername = proxyUsername;
    }

    public String getProxyPassword() {
        return proxyPassword;
    }

    public void setProxyPassword(String proxyPassword) {
        this.proxyPassword = proxyPassword;
    }

    public Boolean getSynchronizeFolderFlag() {
        return synchronizeFolderFlag;
    }

    public void setSynchronizeFolderFlag(Boolean synchronizeFolderFlag) {
        this.synchronizeFolderFlag = synchronizeFolderFlag;
    }

    public String getConnStatusName() {
        return this.connStatus.intValue() == ConnStatusEnum.NORMAL.getType() ? "正常" : "异常";
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getProtocolTypeName() {
        ProtocolTypeEnum protocolTypeEnum = ProtocolTypeEnum.getByType(this.getProtocolType());
        return protocolTypeEnum == null ? "" : protocolTypeEnum.getName();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("account", getAccount())
            .append("password", getPassword())
            .append("alias", getAlias())
            .append("connStatus", getConnStatus())
            .append("connExceptionReason", getConnExceptionReason())
            .append("receivingServer", getReceivingServer())
            .append("receivingPort", getReceivingPort())
            .append("receivingSslFlag", getReceivingSslFlag())
            .append("outgoingServer", getOutgoingServer())
            .append("outgoingPort", getOutgoingPort())
            .append("outgoingSslFlag", getOutgoingSslFlag())
            .append("customProxyFlag", getCustomProxyFlag())
            .append("proxyServerType", getProxyServerType())
            .append("proxyServer", getProxyServer())
            .append("proxyPort", getProxyPort())
            .append("proxyUsername", getProxyUsername())
            .append("proxyPassword", getProxyPassword())
            .append("synchronizeFolderFlag", getSynchronizeFolderFlag())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
