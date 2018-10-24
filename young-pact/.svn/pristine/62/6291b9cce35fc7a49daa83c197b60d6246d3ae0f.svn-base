package com.young.pact.service.schedule.purchasetermination.impl;

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
import com.young.pact.domain.purchasetermination.PurchaseTerminationQuery;
import com.young.pact.domain.purchasetermination.PurchaseTerminationVO;
import com.young.pact.domain.remind.RemindDO;
import com.young.pact.manager.purchasetermination.PurchaseTerminationManager;
import com.young.pact.rpc.remind.RemindRpc;

/**
 * @描述 : 录入托进解约协议超过24小时未提交审核定时器(合同草稿状态没有申请审核)
 * @创建者 : guoxiaopeng
 * @创建时间 : 2018年9月7日 下午3:47:42
 */
@Service("purminationUnSubmittedTaskService")
public class PurminationUnSubmittedTaskServiceImpl implements IScheduleTaskDealSingle<PurchaseTerminationVO>{
    private static final Log LOG = LogFactory.getLog(PurminationUnSubmittedTaskServiceImpl.class);
    private PurchaseTerminationManager purchaseTerminationManager;
    private RemindRpc remindRpc;
    private int count;
    @Override
    public List<PurchaseTerminationVO> selectTasks(String paramString1, String paramString2, int paramInt1, List<TaskItemDefine> paramList, int paramInt2) throws Exception {
        try {
            PurchaseTerminationQuery purchaseTerminationQuery = new PurchaseTerminationQuery();
            purchaseTerminationQuery.setState(1);
            List<PurchaseTerminationVO> list= purchaseTerminationManager.listUnsubmitted(purchaseTerminationQuery);
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
    public Comparator<PurchaseTerminationVO> getComparator() {
        return null;
    }

    @Override
    public boolean execute(PurchaseTerminationVO purchaseTerminationVO, String paramString) throws Exception {
        LOG.info("-----------录入托进解约协议超过24小时未提交审核定时任务执行结果   开始--------");
        RemindDO remindDO = new RemindDO();
        List<String> variableList = new ArrayList<>();
        variableList.add(PactTypeConsts.PURCHASE_TERMINATION);
        variableList.add(purchaseTerminationVO.getTerminationCode());
        remindDO.setKey(StringConsts.REMIND_TEMPLATE_UNSUBMITTED_KEY);
        remindDO.setPin(purchaseTerminationVO.getGuardianPin());
        remindDO.setServicecode(purchaseTerminationVO.getTerminationCode());
        remindDO.setServicetype(SerializeTypeConts.PURCHASE);
        remindDO.setTitle(StringConsts.REMIND_TEMPLATE_UNSUBMITTED_TITLE);
        remindDO.setVariableList(variableList);
        boolean flag = remindRpc.saveRemind(remindDO);
        if(flag){
            LOG.info("录入托进解约协议超过24小时未提交审核提醒----成功编码--"+purchaseTerminationVO.getTerminationCode());
        }else{
            LOG.error("录入托进解约协议超过24小时未提交审核提醒----失败编码--"+purchaseTerminationVO.getTerminationCode());
        }
        LOG.info("-----------录入托进解约协议超过24小时未提交审核定时任务执行结果  结束--------");
        return true;
    }

    public PurchaseTerminationManager getPurchaseTerminationManager() {
        return purchaseTerminationManager;
    }

    public void setPurchaseTerminationManager(PurchaseTerminationManager purchaseTerminationManager) {
        this.purchaseTerminationManager = purchaseTerminationManager;
    }

    public RemindRpc getRemindRpc() {
        return remindRpc;
    }

    public void setRemindRpc(RemindRpc remindRpc) {
        this.remindRpc = remindRpc;
    }

}
