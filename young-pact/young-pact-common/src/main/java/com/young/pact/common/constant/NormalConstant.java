package com.young.pact.common.constant;
/**
 * 
* @ClassName: NormalConstant
* @Description: TODO( 正常常量 )
* @author GuoXiaoPeng
* @date 2018年10月9日 上午9:39:52
*
 */
public class NormalConstant {

    /*** 操作日志topic ***/
    public static final String TOPIC_OPERATE = "young_operateRecord";
    /*** mq topic----------首页统计(审核通过托出合同，托进合同，托出续租协议，托出解约协议 )***/
    public static final String TOPIC_PACTROOMSTATISTICS = "data_statistics_save";
    /*** mq topic----------首页待办数据统计 ***/
    public static final String TOPIC_BACKLOGSTATISTICS = "data_agenda";
    /*** mq topic---------- 首页概况数据保存***/
    public static final String TOPIC_DATA_SITUATION_SAVE = "data_situation_save";
    public static final String TAG_DATA_AGENDA_SAVE = "save";
    public static final String TAG_DATA_AGENDA_UPDATE = "update";
    public static final String TAG_DATA_AGENDA_REMOVE = "remove";
    
    
    
    
    /*** 保存操作tag ***/
    public static final String TAG_OPERATE_SAVE = "young_operate_save";
    /*** 修改操作tag ***/
    public static final String TAG_OPERATE_UPDATE = "young_operate_update";
    /*** 移除操作tag ***/
    public static final String TAG_OPERATE_REMOVE = "young_operate_remove";
    /*** 合同操作key ***/
    public static final String KEY_OPERATE_PACT = "young_operateRecord";
    /** 操作日志-资源类型 **/
    public static final String SERVICETYPE = "serviceType";
    /** 操作日志-资源编码 **/
    public static final String SERVICECODE = "serviceCode";
    /** 操作日志-类型（添加，修改，删除） **/
    public static final String OPERATETYPE = "operateType";
    /** 操作日志-操作人PIN **/
    public static final String OPERATEPIN = "operatePin";
    /** 操作日志-操作人姓名 **/
    public static final String OPERATENAME = "operateName";
    /** 操作日志-部门 **/
    public static final String OPERATEDEPT = "operateDept";
    /** 操作日志时间 **/
    public static final String OPERATETIME = "operateTime";
    /** 操作日志内容 **/
    public static final String OPERATECONTENT = "operateContent";
    /*** 提醒消息topic ***/
    public static final String TOPIC_REMIND = "young_remind";
    /*** PIN ***/
    public static final String REMIND_PIN = "pin";
    /*** 提醒消息类型 ***/
    public static final String REMIND_SERVICETYPE = "serviceType";
    /*** 提醒消息-资源编码 ***/
    public static final String REMIND_SERVICECODE = "serviceCode";
    /*** 提醒消息是否已读 ***/
    public static final String REMIND_ISREAD = "isRead";
    /*** 提醒内容 ***/
    public static final String REMIND_REMINDCONTENT = "remindContent";
    /*** 提醒消息时间 ***/
    public static final String REMIND_TIME  = "remindTime";
    /*** 提醒消息标题 ***/
    public static final String REMIND_TITLE  = "title";
    
   
}
