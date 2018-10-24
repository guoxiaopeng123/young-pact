package com.young.pact.service.schedule.transferroom.impl;

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
import com.young.pact.domain.pactrenttransfer.PactRentTransferQuery;
import com.young.pact.domain.pactrenttransfer.PactRentTransferVO;
import com.young.pact.domain.remind.RemindDO;
import com.young.pact.manager.pactrenttransfer.PactRentTransferManager;
import com.young.pact.rpc.operatelog.OperateLogRpc;
import com.young.pact.rpc.remind.RemindRpc;

/**
 * @描述 : 托出合同调房协议提交审核后超过24小时未审核定时器(申请审核后没有审核通过或者驳回的记录)
 * @创建者 : guoxiaopeng
 * @创建时间 : 2018年9月7日 下午6:21:18
 */
@Service("rentTransferUnReviewTaskService")
public class RentTransferUnReviewTaskServiceImpl implements IScheduleTaskDealSingle<PactRentTransferVO>{
    private static final Log LOG = LogFactory.getLog(RentTransferUnReviewTaskServiceImpl.class);
    private PactRentTransferManager pactRentTransferManager;
    private RemindRpc remindRpc;
    private OperateLogRpc operateLogRpc;
    private int count;
    @Override
    public List<PactRentTransferVO> selectTasks(String paramString1, String paramString2, int paramInt1, List<TaskItemDefine> paramList, int paramInt2) throws Exception {
        try {
            LOG.info("----------- 托出合同调房协议提交审核后超过24小时未审核定时任务   开始--------");
            PactRentTransferQuery rentTransferQuery = new PactRentTransferQuery();
            rentTransferQuery.setState(2);
            List<PactRentTransferVO> list= pactRentTransferManager.listUnReview(rentTransferQuery);
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
    public Comparator<PactRentTransferVO> getComparator() {
        return null;
    }

    @Override
    public boolean execute(PactRentTransferVO pactRentTransferVO, String paramString) throws Exception {
        LOG.info("----------- 托出调房协议提交审核后超过24小时未审核定时任务执行结果   开始--------");
        RemindDO remindDO = new RemindDO();
        List<String> variableList = new ArrayList<>();
        variableList.add(PactTypeConsts.RENT_ROOM);
        variableList.add(pactRentTransferVO.getTransferCode());
        remindDO.setKey(StringConsts.REMIND_TEMPLATE_UNREVIEW_KEY);
        remindDO.setPin(pactRentTransferVO.getGuardianPin());
        remindDO.setServicecode(pactRentTransferVO.getTransferCode());
        remindDO.setServicetype(SerializeTypeConts.TRANSFER);
        remindDO.setTitle(StringConsts.REMIND_TEMPLATE_UNREVIEW_TITLE);
        remindDO.setVariableList(variableList);
        boolean flag = remindRpc.saveRemind(remindDO);
        if(flag){
            LOG.info("托出合同调房协议提交审核后超过24小时未审核提醒----成功编码--"+pactRentTransferVO.getTransferCode());
        }else{
            LOG.error("托出合同调房协议提交审核后超过24小时未审核提醒----失败编码--"+pactRentTransferVO.getTransferCode());
        }
        LOG.info("----------- 托出合同调房协议提交审核后超过24小时未审核定时任务执行结果  结束--------");
        return true;
    }



    public PactRentTransferManager getPactRentTransferManager() {
        return pactRentTransferManager;
    }

    public void setPactRentTransferManager(PactRentTransferManager pactRentTransferManager) {
        this.pactRentTransferManager = pactRentTransferManager;
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
