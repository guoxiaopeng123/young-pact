package com.young.pact.service.schedule.rentturn.impl;

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
import com.young.pact.domain.rentturn.RentTurnQuery;
import com.young.pact.domain.rentturn.RentTurnVO;
import com.young.pact.manager.rentturn.RentTurnManager;
import com.young.pact.rpc.operatelog.OperateLogRpc;
import com.young.pact.rpc.remind.RemindRpc;

/**
 * @描述 : 托出合同转租协议提交审核后超过24小时未审核定时器(申请审核后没有审核通过或者驳回的记录)
 * @创建者 : guoxiaopeng
 * @创建时间 : 2018年9月7日 下午6:21:18
 */
@Service("rentTurnUnReviewTaskService")
public class RentTurnUnReviewTaskServiceImpl implements IScheduleTaskDealSingle<RentTurnVO>{
    private static final Log LOG = LogFactory.getLog(RentTurnUnReviewTaskServiceImpl.class);
    private RentTurnManager rentTurnManager;
    private RemindRpc remindRpc;
    private OperateLogRpc operateLogRpc;
    private int count;
    @Override
    public List<RentTurnVO> selectTasks(String paramString1, String paramString2, int paramInt1, List<TaskItemDefine> paramList, int paramInt2) throws Exception {
        try {
            RentTurnQuery rentTurnQuery = new RentTurnQuery();
            rentTurnQuery.setState(2);
            List<RentTurnVO> list= rentTurnManager.listUnReview(rentTurnQuery);
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
    public Comparator<RentTurnVO> getComparator() {
        return null;
    }

    @Override
    public boolean execute(RentTurnVO rentTurnVO, String paramString) throws Exception {
        LOG.info("----------- 托出转租协议提交审核后超过24小时未审核定时任务执行结果   开始--------");
        RemindDO remindDO = new RemindDO();
        List<String> variableList = new ArrayList<>();
        variableList.add(PactTypeConsts.RENT_SUBLET);
        variableList.add(rentTurnVO.getReletCode());
        remindDO.setKey(StringConsts.REMIND_TEMPLATE_UNREVIEW_KEY);
        remindDO.setPin(rentTurnVO.getGuardianPin());
        remindDO.setServicecode(rentTurnVO.getReletCode());
        remindDO.setServicetype(SerializeTypeConts.RENTTURN);
        remindDO.setTitle(StringConsts.REMIND_TEMPLATE_UNREVIEW_TITLE);
        remindDO.setVariableList(variableList);
        boolean flag = remindRpc.saveRemind(remindDO);
        if(flag){
            LOG.info("托出合同转租协议提交审核后超过24小时未审核提醒----成功编码--"+rentTurnVO.getReletCode());
        }else{
            LOG.error("托出合同转租协议提交审核后超过24小时未审核提醒----失败编码--"+rentTurnVO.getReletCode());
        }
        LOG.info("----------- 托出合同转租协议提交审核后超过24小时未审核定时任务执行结果  结束--------");
        return true;
    }


    public RentTurnManager getRentTurnManager() {
        return rentTurnManager;
    }

    public void setRentTurnManager(RentTurnManager rentTurnManager) {
        this.rentTurnManager = rentTurnManager;
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
