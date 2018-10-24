package com.young.pact.domain.remind;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @描述 : 消息提醒
 * @创建者 : guoxiaopeng
 * @创建时间 : 2018年9月6日 下午4:54:00
 */
public class RemindDO implements Serializable{

    /**
    * @Fields serialVersionUID : TODO( )
    */
    private static final long serialVersionUID = 1L;
    /*** @Fields key:消息模板key*/
    private String key;
    /*** @Fields :消息模板变量集合*/
    private List<String> variableList;
    /*** @Fields pin:提醒消息接收人PIN*/
    private String pin;
    /*** @Fields servicetype:消息类型*/
    private String servicetype;
    /*** @Fields servicecode:资源编码*/
    private String servicecode;
    /*** @Fields title:消息标题*/
    private String title;
    /*** @Fields remindContent:消息内容*/
    private String remindContent;
    /*** @Fields isRead:是否已读 0 未读 1已读*/
    private Integer isRead;
    /*** @Fields remindTime:操作时间*/
    private Date remindTime;
    public RemindDO(){
        
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public List<String> getVariableList() {
        return variableList;
    }
    public void setVariableList(List<String> variableList) {
        this.variableList = variableList;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getServicetype() {
        return servicetype;
    }
    public void setServicetype(String servicetype) {
        this.servicetype = servicetype;
    }
    public String getServicecode() {
        return servicecode;
    }
    public void setServicecode(String servicecode) {
        this.servicecode = servicecode;
    }
    public String getRemindContent() {
        return remindContent;
    }
    public void setRemindContent(String remindContent) {
        this.remindContent = remindContent;
    }
    public String getPin() {
        return pin;
    }
    public void setPin(String pin) {
        this.pin = pin;
    }
    public Integer getIsRead() {
        return isRead;
    }
    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }
    public Date getRemindTime() {
        return remindTime;
    }
    public void setRemindTime(Date remindTime) {
        this.remindTime = remindTime;
    }
    
}
