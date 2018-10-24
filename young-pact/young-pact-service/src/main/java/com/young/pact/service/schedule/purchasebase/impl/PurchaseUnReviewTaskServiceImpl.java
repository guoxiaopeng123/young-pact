package com.young.pact.service.schedule.purchasebase.impl;

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
import com.young.pact.domain.purchasebase.PurchaseBaseQuery;
import com.young.pact.domain.purchasebase.PurchaseBaseVO;
import com.young.pact.domain.remind.RemindDO;
import com.young.pact.manager.purchasebase.PurchaseBaseManager;
import com.young.pact.rpc.remind.RemindRpc;

/**
 * @描述 :  托进合同提交审核后超过24小时未审核定时器(申请审核后没有审核通过或者驳回的记录)
 * @创建者 : guoxiaopeng
 * @创建时间 : 2018年9月6日 下午4:13:04
 */
@Service("purchaseUnReviewTaskService")
public class PurchaseUnReviewTaskServiceImpl implements IScheduleTaskDealSingle<PurchaseBaseVO>{
    private static final Log LOG = LogFactory.getLog(PurchaseUnReviewTaskServiceImpl.class);
    private PurchaseBaseManager purchaseBaseManager;
    private RemindRpc remindRpc;
    private int count;
    @Override
    public Comparator<PurchaseBaseVO> getComparator() {
        return null;
    }

    @Override
    public List<PurchaseBaseVO> selectTasks(String arg0, String arg1, int arg2, List<TaskItemDefine> arg3, int arg4) throws Exception {
        try {
            PurchaseBaseQuery purchaseBaseQuery = new PurchaseBaseQuery();
            purchaseBaseQuery.setState(2);
            List<PurchaseBaseVO> list= purchaseBaseManager.listUnReview(purchaseBaseQuery);
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
    public boolean execute(PurchaseBaseVO purchaseBaseVO, String arg1) throws Exception {
        LOG.info("----------- 托进提交审核后超过24小时未审核定时任务执行结果   开始--------");
        RemindDO remindDO = new RemindDO();
        List<String> variableList = new ArrayList<>();
        variableList.add(PactTypeConsts.PURCHASE);
        variableList.add(purchaseBaseVO.getPurchaseCode());
        remindDO.setKey(StringConsts.REMIND_TEMPLATE_UNREVIEW_KEY);
        remindDO.setPin(purchaseBaseVO.getOperatePin());
        remindDO.setServicecode(purchaseBaseVO.getPurchaseCode());
        remindDO.setServicetype(SerializeTypeConts.PURCHASE);
        remindDO.setTitle(StringConsts.REMIND_TEMPLATE_UNREVIEW_TITLE);
        remindDO.setVariableList(variableList);
        boolean flag = remindRpc.saveRemind(remindDO);
        if(flag){
            LOG.info("托进提交审核后超过24小时未审核提醒----成功编码--"+purchaseBaseVO.getPurchaseCode());
        }else{
            LOG.error("托进提交审核后超过24小时未审核提醒----失败编码--"+purchaseBaseVO.getPurchaseCode());
        }
        LOG.info("----------- 托进提交审核后超过24小时未审核定时任务执行结果  结束--------");
        return true;
    }
    
    public PurchaseBaseManager getPurchaseBaseManager() {
        return purchaseBaseManager;
    }

    public void setPurchaseBaseManager(PurchaseBaseManager purchaseBaseManager) {
        this.purchaseBaseManager = purchaseBaseManager;
    }

    public RemindRpc getRemindRpc() {
        return remindRpc;
    }

    public void setRemindRpc(RemindRpc remindRpc) {
        this.remindRpc = remindRpc;
    }
}
