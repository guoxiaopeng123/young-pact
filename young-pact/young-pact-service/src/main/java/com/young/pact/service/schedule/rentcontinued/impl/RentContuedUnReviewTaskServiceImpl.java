package com.young.pact.service.schedule.rentcontinued.impl;

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
import com.young.pact.domain.rentcontinued.RentContinuedQuery;
import com.young.pact.domain.rentcontinued.RentContinuedVO;
import com.young.pact.manager.rentcontinued.RentContinuedManager;
import com.young.pact.rpc.operatelog.OperateLogRpc;
import com.young.pact.rpc.remind.RemindRpc;

/**
 * @描述 : 托出合同续租协议提交审核后超过24小时未审核定时器(申请审核后没有审核通过或者驳回的记录) 
 * @创建者 : guoxiaopeng
 * @创建时间 : 2018年9月7日 下午6:21:18
 */
@Service("rentContuedUnReviewTaskService")
public class RentContuedUnReviewTaskServiceImpl implements IScheduleTaskDealSingle<RentContinuedVO>{
    private static final Log LOG = LogFactory.getLog(RentContuedUnReviewTaskServiceImpl.class);
    private RentContinuedManager rentContinuedManager;
    private RemindRpc remindRpc;
    private OperateLogRpc operateLogRpc;
    private int count;
    @Override
    public List<RentContinuedVO> selectTasks(String paramString1, String paramString2, int paramInt1, List<TaskItemDefine> paramList, int paramInt2) throws Exception {
        try {
            RentContinuedQuery rentContinuedQuery = new RentContinuedQuery();
            rentContinuedQuery.setState(2);
            List<RentContinuedVO> list= rentContinuedManager.listUnReview(rentContinuedQuery);
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
            // TODO: handle exception
            LOG.error(e);
        }
        return null;
    }

    @Override
    public Comparator<RentContinuedVO> getComparator() {
        return null;
    }

    @Override
    public boolean execute(RentContinuedVO rentContinuedVO, String paramString) throws Exception {
        LOG.info("----------- 托出续租协议提交审核后超过24小时未审核定时任务执行结果   开始--------");
        RemindDO remindDO = new RemindDO();
        List<String> variableList = new ArrayList<>();
        variableList.add(PactTypeConsts.RENT_RENEW);
        variableList.add(rentContinuedVO.getRenewCode());
        remindDO.setKey(StringConsts.REMIND_TEMPLATE_UNREVIEW_KEY);
        remindDO.setPin(rentContinuedVO.getGuardianPin());
        remindDO.setServicecode(rentContinuedVO.getRenewCode());
        remindDO.setServicetype(SerializeTypeConts.RENEW);
        remindDO.setTitle(StringConsts.REMIND_TEMPLATE_UNREVIEW_TITLE);
        remindDO.setVariableList(variableList);
        boolean flag = remindRpc.saveRemind(remindDO);
        if(flag){
            LOG.info("托出合同续租协议提交审核后超过24小时未审核提醒----成功编码--"+rentContinuedVO.getRenewCode());
        }else{
            LOG.error("托出合同续租协议提交审核后超过24小时未审核提醒----失败编码--"+rentContinuedVO.getRenewCode());
        }
        LOG.info("----------- 托出合同续租协议提交审核后超过24小时未审核定时任务执行结果  结束--------");
        return true;
    }


    public RentContinuedManager getRentContinuedManager() {
        return rentContinuedManager;
    }

    public void setRentContinuedManager(RentContinuedManager rentContinuedManager) {
        this.rentContinuedManager = rentContinuedManager;
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
