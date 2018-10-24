package com.young.pact.service.schedule.rentmination.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.taobao.pamirs.schedule.IScheduleTaskDealSingle;
import com.taobao.pamirs.schedule.TaskItemDefine;
import com.tools.common.util.json.JsonUtil;
import com.young.follow.api.domain.param.rpc.OperateLogParam;
import com.young.follow.api.domain.result.rpc.OperateLogResult;
import com.young.pact.common.constant.PactTypeConsts;
import com.young.pact.common.constant.SerializeTypeConts;
import com.young.pact.common.constant.StringConsts;
import com.young.pact.domain.pactrenttermination.PactRentTerminationQuery;
import com.young.pact.domain.pactrenttermination.PactRentTerminationVO;
import com.young.pact.domain.remind.RemindDO;
import com.young.pact.manager.pactrenttermination.PactRentTerminationManager;
import com.young.pact.rpc.operatelog.OperateLogRpc;
import com.young.pact.rpc.remind.RemindRpc;

/**
 * @描述 : 托出合同解约协议驳回后超过24小时未重新提交审核定时器
 * @创建者 : guoxiaopeng
 * @创建时间 : 2018年9月7日 下午6:20:34
 */
@Service("rentMinationRejectTaskService")
public class RentMinationRejectTaskServiceImpl implements IScheduleTaskDealSingle<PactRentTerminationVO>{
    private static final Log LOG = LogFactory.getLog(RentMinationRejectTaskServiceImpl.class);
    private  PactRentTerminationManager pactRentTerminationManager;
    private RemindRpc remindRpc;
    private OperateLogRpc operateLogRpc;
    private int count;
    @Override
    public List<PactRentTerminationVO> selectTasks(String paramString1, String paramString2, int paramInt1, List<TaskItemDefine> paramList, int paramInt2) throws Exception {
        try {
            LOG.info("-----------托出合同解约协议驳回后超过24小时未重新提交审核定时任务   开始--------");
            List<PactRentTerminationVO> rejectList = new ArrayList<>();
            Map<String, List<OperateLogResult>> groupOperateRecordMap = Maps.newHashMap();
            List<String> serviceCodeList = null;
            PactRentTerminationQuery pactRentTerminationQuery = new PactRentTerminationQuery();
            pactRentTerminationQuery.setState(4);
            List<PactRentTerminationVO> list = pactRentTerminationManager.listRentTerminationByParam(pactRentTerminationQuery);
            if(null!=list&&list.size()>0){
                serviceCodeList = new ArrayList<>();
                for(PactRentTerminationVO pactRentTerminationVO : list){
                    serviceCodeList.add(pactRentTerminationVO.getDissolutionCode());
                }
                OperateLogParam operateLogParam = new OperateLogParam();
                operateLogParam.setServiceCodeList(serviceCodeList);
                List<OperateLogResult> operateRecordList = operateLogRpc.listOperateRecord(operateLogParam);
                groupOperateRecordMap = groupOperateRecordByServiceCode(operateRecordList);
                for(Map.Entry<String, List<OperateLogResult>> entry: groupOperateRecordMap.entrySet()){
                    List<OperateLogResult> operateLogList = entry.getValue();
                    if(null !=operateLogList &&operateLogList.size()>0){
                        //查询操作日志中有没有比最新的驳回日志操作时间大于申请审核的操作时间的日志
                        String dissolutionCode = entry.getKey();
                        boolean flag = checkApplyAuditLog(operateLogList);
                        if(!flag){
                            for(PactRentTerminationVO pactRentTerminationVO : list){
                                if( dissolutionCode.equals(pactRentTerminationVO.getDissolutionCode())){
                                    rejectList.add(pactRentTerminationVO);
                                    break;
                                }
                            }
                        }
                    }
                }
                LOG.info("托出合同解约协议驳回后超过24小时未重新提交：" + JsonUtil.toJson(rejectList));
            }
            LOG.info("-----------托出合同解约协议驳回后超过24小时未重新提交审核定时任务  结束--------");
            if(rejectList != null && rejectList.size()>0){
                if(count == rejectList.size()){
                    count = 0;
                    return null;
                }else{
                    count = rejectList.size();
                    return rejectList;
                }
            }else{
                return null;
            }
        } catch (Exception e) {
            // TODO: handle exception
            LOG.error(e);
        }
        return null;
    }
    @Override
    public Comparator<PactRentTerminationVO> getComparator() {
        return null;
    }
    @Override
    public boolean execute(PactRentTerminationVO pactRentTerminationVO, String paramString) throws Exception {
        LOG.info("-----------托出合同解约协议驳回后超过24小时未重新提交审核定时任务执行结果   开始--------");
        RemindDO remindDO = new RemindDO();
        List<String> variableList = new ArrayList<>();
        variableList.add(PactTypeConsts.RENT_TERMINATION);
        variableList.add(pactRentTerminationVO.getDissolutionCode());
        remindDO.setKey(StringConsts.REMIND_TEMPLATE_REJECT_KEY);
        remindDO.setPin(pactRentTerminationVO.getGuardianPin());
        remindDO.setServicecode(pactRentTerminationVO.getDissolutionCode());
        remindDO.setServicetype(SerializeTypeConts.RTERMINA);
        remindDO.setTitle(StringConsts.REMIND_TEMPLATE_REJECT_TITLE);
        remindDO.setVariableList(variableList);
        boolean flag = remindRpc.saveRemind(remindDO);
        if(flag){
            LOG.info("托出合同解约协议驳回后超过24小时未重新提交审核提醒----成功编码--"+pactRentTerminationVO.getDissolutionCode());
        }else{
            LOG.error("托出合同解约协议驳回后超过24小时未重新提交审核提醒----失败编码--"+pactRentTerminationVO.getDissolutionCode());
        }
        LOG.info("-----------托出合同解约协议驳回后超过24小时未重新提交审核定时任务执行结果  结束--------");
        return false;
    }
    /**
     * @Title: checkApplyAuditLog
     * @Description: TODO( 查询操作日志中有没有比最新的驳回日志操作时间大于申请审核的操作时间的日志 )
     * @author GuoXiaoPeng
     * @param operateLogList 资源编码相同的日志集合
     * @return 有返回true，没有返回false
     * @throws 异常
      */
     private boolean checkApplyAuditLog(List<OperateLogResult> operateLogList) { 
         //最新的驳回日志 
         OperateLogResult newRejectLog = getNewRejectLog(operateLogList);
         for(OperateLogResult operateLogResult: operateLogList){
             int compareFlag = compareDate(operateLogResult.getOperateTime(), newRejectLog.getOperateTime());
             if(compareFlag == 1&& StringConsts.OPERATETYPE_APPLICATION_AUDIT.equals(operateLogResult.getOperateType())){
                return true; 
             }else{
                 long hour = hourDiff(newRejectLog.getOperateTime());
                 if(hour >= 24 && hour < 48){
                     return false;
                 }else{
                     return true;
                 }
             }
         }
         return true;
     }
    /**
    * @Title: hourDiff
    * @Description: TODO( 返回当前日期距离指定日期相差的几个小时,若指定日期>当前日期返回0 )
    * @author GuoXiaoPeng
    * @param operateTime 操作日志驳回时间
    * @return 小时
    * @throws 异常
     */
    private static long hourDiff(Date operateTime) {
        //按照传入的格式生成一个simpledateformate对象
        long nh = 1000*60*60;//一小时的毫秒数
        //获得两个时间的毫秒时间差异
        long diff = System.currentTimeMillis() - operateTime.getTime();
        long hour = diff/nh;//计算差多少小时
        if(hour < 0){
            hour = 0;
        }
        return hour;
    }

    /**
    * @Title: getNewRejectLog
    * @Description: TODO( 查询操作日志中最新的驳回日志 )
    * @author GuoXiaoPeng
    * @param operateLogList 资源编码相同的日志集合
    * @return 驳回日志
    * @throws 异常
     */
    private OperateLogResult getNewRejectLog(List<OperateLogResult> operateLogList) {
        Collections.sort(operateLogList,new Comparator<OperateLogResult>(){
            @Override
            public int compare(OperateLogResult o1, OperateLogResult o2) {
              return compareDate(o1.getOperateTime(), o2.getOperateTime());
            }
        });
        OperateLogResult operateLog = null;
        for(OperateLogResult operateLogResult: operateLogList){
            if(StringConsts.OPERATETYPE_REVIEW_DISMISSAL.equals(operateLogResult.getOperateType())){
                operateLog = operateLogResult;
            }
        }
        return operateLog;
    }
    /**
     * 
     * @description :比较两个日期的大小，如：
     *      date1  2018-03-19 00:00:00
     *      date2  2018-03-19 00:00:00
     *      进行比较返回-1
     * @author : guoxiaopeng
     * @date : 2018年1月3日 下午8:13:14
     * @param date1  如：2018-03-19 00:00:00
     * @param date2  如：2018-03-24 00:00:00
     * @return 若data1大于data2 返回1,若data1小于data2 返回-1,等于等于返回0
     */
    public static int compareDate(Date date1, Date date2) {
        try {
            if (date1.getTime() > date2.getTime()) {
                return 1;
            } else if (date1.getTime() < date2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }
    /**
     * 
    * @Title: groupOperateRecordByServiceCode
    * @Description: TODO( 操作日志按资源编码分组 )
    * @author GuoXiaoPeng
    * @param operateRecordList 日志集合
    * @return 以资源编码为key，资源集合为value的键值对
    * @throws 异常
     */
    private Map<String, List<OperateLogResult>> groupOperateRecordByServiceCode(List<OperateLogResult> operateRecordList) {
        Map<String, List<OperateLogResult>> groupOperateRecordMap = Maps.newHashMap();
        if(null != operateRecordList && operateRecordList.size()>0 ){
            for(OperateLogResult operateLogResult: operateRecordList){
                String serviceCode = operateLogResult.getServiceCode();
                if(groupOperateRecordMap.containsKey(serviceCode)){
                    groupOperateRecordMap.get(serviceCode).add(operateLogResult);
                }else{
                    groupOperateRecordMap.put(serviceCode, Lists.newArrayList(operateLogResult));
                }
            }
        }
        return groupOperateRecordMap;
    }
    public PactRentTerminationManager getPactRentTerminationManager() {
        return pactRentTerminationManager;
    }
    public void setPactRentTerminationManager(PactRentTerminationManager pactRentTerminationManager) {
        this.pactRentTerminationManager = pactRentTerminationManager;
    }
    public RemindRpc getRemindRpc() {
        return remindRpc;
    }
    public void setRemindRpc(RemindRpc remindRpc) {
        this.remindRpc = remindRpc;
    }
    public OperateLogRpc getOperateLogRpc() {
        return operateLogRpc;
    }
    public void setOperateLogRpc(OperateLogRpc operateLogRpc) {
        this.operateLogRpc = operateLogRpc;
    }
}
