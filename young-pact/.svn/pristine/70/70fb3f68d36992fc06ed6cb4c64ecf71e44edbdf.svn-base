package com.young.pact.service.schedule.purchasebase.impl;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.taobao.pamirs.schedule.IScheduleTaskDealSingle;
import com.taobao.pamirs.schedule.TaskItemDefine;
import com.young.pact.common.constant.StringConsts;
import com.young.pact.domain.purchasebase.PurchaseBaseVO;
import com.young.pact.domain.statistics.AgendaDO;
import com.young.pact.manager.purchasebase.PurchaseBaseManager;
import com.young.pact.manager.statistics.StatisticsManager;

/**
 * @描述 :查询托进到期时间距离今日还有60天的的托进合同定时任务
 * @创建者 : guoxiaopeng
 * @创建时间 : 2018年9月15日 上午11:53:31
 */
@Service("purchaseExpirTaskService")
public class PurchaseExpirTaskServiceImpl implements IScheduleTaskDealSingle<PurchaseBaseVO>{

    private static final Log LOG = LogFactory.getLog(PurchaseExpirTaskServiceImpl.class);
    private PurchaseBaseManager purchaseBaseManager;
    private StatisticsManager statisticsManager;
    private int count;
    @Override
    public Comparator<PurchaseBaseVO> getComparator() {
        return null;
    }

    @Override
    public List<PurchaseBaseVO> selectTasks(String arg0, String arg1, int arg2, List<TaskItemDefine> arg3, int arg4) throws Exception {
        try {
            List<PurchaseBaseVO> list = purchaseBaseManager.listPurchaseExpir();
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
        LOG.info("-----------查询托进到期时间距离今日还有60天的的托进合同定时任务执行结果   开始--------");
        AgendaDO agendaDO = new AgendaDO();
        agendaDO.setServiceCode(purchaseBaseVO.getPurchaseCode());
        agendaDO.setType(StringConsts.STATISTIC_PENDING_PURCHBASE_EXPIR);
        agendaDO.setIsDo(0);
        agendaDO.setPin(purchaseBaseVO.getAfterRentingPin());
        agendaDO.setName(purchaseBaseVO.getAfterRentingName());
        agendaDO.setDeptCode(purchaseBaseVO.getAfterRentingDeptCode());
        agendaDO.setDeptName(purchaseBaseVO.getAfterRentingDeptName());
        agendaDO.setDate(new Date());
        statisticsManager.saveBacklogStatistics(agendaDO);
        LOG.info("-----------查询托进到期时间距离今日还有60天的的托进合同定时任务执行结果   结束--------");
        return true;
    }

    public PurchaseBaseManager getPurchaseBaseManager() {
        return purchaseBaseManager;
    }

    public void setPurchaseBaseManager(PurchaseBaseManager purchaseBaseManager) {
        this.purchaseBaseManager = purchaseBaseManager;
    }

    public StatisticsManager getStatisticsManager() {
        return statisticsManager;
    }

    public void setStatisticsManager(StatisticsManager statisticsManager) {
        this.statisticsManager = statisticsManager;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
