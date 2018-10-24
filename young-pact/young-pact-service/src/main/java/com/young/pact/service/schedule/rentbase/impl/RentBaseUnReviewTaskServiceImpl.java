package com.young.pact.service.schedule.rentbase.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.taobao.pamirs.schedule.IScheduleTaskDealSingle;
import com.taobao.pamirs.schedule.TaskItemDefine;
import com.young.pact.common.constant.PactTypeConsts;
import com.young.pact.common.constant.SerializeTypeConts;
import com.young.pact.common.constant.StringConsts;
import com.young.pact.domain.remind.RemindDO;
import com.young.pact.domain.rentbase.RentBaseQuery;
import com.young.pact.domain.rentbase.RentBaseVO;
import com.young.pact.manager.rentbase.RentBaseManager;
import com.young.pact.rpc.operatelog.OperateLogRpc;
import com.young.pact.rpc.remind.RemindRpc;

/**
 * @描述 : 托出提交审核后超过24小时未审核定时任务定时器(申请审核后没有审核通过或者驳回的记录)
 * @创建者 : guoxiaopeng
 * @创建时间 : 2018年9月7日 下午4:19:17
 */
 @Service("rentBaseUnReviewTaskService")
public class RentBaseUnReviewTaskServiceImpl implements IScheduleTaskDealSingle<RentBaseVO>{
     private static final Log LOG = LogFactory.getLog(RentBaseUnReviewTaskServiceImpl.class);
     private RentBaseManager rentBaseManager;
     private RemindRpc remindRpc;
     private OperateLogRpc operateLogRpc;
     private int count;
     @Override
     public List<RentBaseVO> selectTasks(String paramString1, String paramString2, int paramInt1, List<TaskItemDefine> paramList, int paramInt2) throws Exception {
        try {
            RentBaseQuery rentBaseQuery = new RentBaseQuery();
            rentBaseQuery.setState(2);
            List<RentBaseVO> list= rentBaseManager.listUnReview(rentBaseQuery);
            if(list != null && list.size()>0){
                if(count == list.size()){
                    count = 0;
                    return null;
                }else{
                    count = list.size();
                    return list;
                }
            }else{
                return null;
            }
        } catch (Exception e) {
            LOG.error(e);
        }
       return null;
    }

    @Override
    public Comparator<RentBaseVO> getComparator() {
        return null;
    }

    @Override
    public boolean execute(RentBaseVO rentBaseVO, String paramString) throws Exception {
        LOG.info("----------- 托出提交审核后超过24小时未审核定时任务执行结果   开始--------");
        RemindDO remindDO = new RemindDO();
        List<String> variableList = new ArrayList<>();
        variableList.add(PactTypeConsts.RENT);
        variableList.add(rentBaseVO.getRentPactCode());
        remindDO.setKey(StringConsts.REMIND_TEMPLATE_UNREVIEW_KEY);
        remindDO.setPin(rentBaseVO.getDealPin());
        remindDO.setServicecode(rentBaseVO.getRentPactCode());
        remindDO.setServicetype(SerializeTypeConts.RENT);
        remindDO.setTitle(StringConsts.REMIND_TEMPLATE_UNREVIEW_TITLE);
        remindDO.setVariableList(variableList);
        boolean flag = remindRpc.saveRemind(remindDO);
        if(flag){
            LOG.info("托出合同提交审核后超过24小时未审核提醒----成功编码--"+rentBaseVO.getRentPactCode());
        }else{
            LOG.error("托出合同提交审核后超过24小时未审核提醒----失败编码--"+rentBaseVO.getRentPactCode());
        }
        LOG.info("----------- 托出合同提交审核后超过24小时未审核定时任务执行结果  结束--------");
        return true;
    }

    public RentBaseManager getRentBaseManager() {
        return rentBaseManager;
    }

    public void setRentBaseManager(RentBaseManager rentBaseManager) {
        this.rentBaseManager = rentBaseManager;
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
