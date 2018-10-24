package com.young.pact.service.schedule.businessincome.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.taobao.pamirs.schedule.IScheduleTaskDealSingle;
import com.taobao.pamirs.schedule.TaskItemDefine;
import com.young.pact.common.constant.StringConsts;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayQuery;
import com.young.pact.domain.statistics.SituationDO;
import com.young.pact.manager.financereceiptpay.FinanceReceiptPayManager;
import com.young.pact.manager.statistics.StatisticsManager;

/**
 * @描述 : 本月营业收入定时任务
 * @创建者 : guoxiaopeng
 * @创建时间 : 2018年9月17日 下午5:23:40
 */
@Service("businesIncomeTaskService")
public class BusinesIncomeTaskServiceImpl implements IScheduleTaskDealSingle<Map<Integer, BigDecimal>> {

    private static final Log LOG = LogFactory.getLog(BusinesIncomeTaskServiceImpl.class);
    private int count;
    private StatisticsManager statisticsManager;
    private FinanceReceiptPayManager financeReceiptPayManager;

    @Override
    public Comparator<Map<Integer, BigDecimal>> getComparator() {
        return null;
    }

    @Override
    public List<Map<Integer, BigDecimal>> selectTasks(String arg0, String arg1, int arg2, List<TaskItemDefine> arg3, int arg4) throws Exception {
        try {
            List<Map<Integer, BigDecimal>> list = new ArrayList<>();
            Map<Integer, BigDecimal> map = new HashMap<>();
            // 服务费
            FinanceReceiptPayQuery serviceChargeQuery = new FinanceReceiptPayQuery();
            serviceChargeQuery.setCostType(String.valueOf(6));
            serviceChargeQuery.setCostState(8);
            serviceChargeQuery.setType(1);
            Double serviceCharge = financeReceiptPayManager.countRecPayAmount(serviceChargeQuery);
            // 托进违约金
            FinanceReceiptPayQuery purPenaltyQuery = new FinanceReceiptPayQuery();
            purPenaltyQuery.setCostType(String.valueOf(8));
            purPenaltyQuery.setCostState(8);
            purPenaltyQuery.setType(1);
            Double purPenalty = financeReceiptPayManager.countRecPayAmount(purPenaltyQuery);
            // 托出违约金
            FinanceReceiptPayQuery rentPenaltyQuery = new FinanceReceiptPayQuery();
            rentPenaltyQuery.setCostType(String.valueOf(24));
            rentPenaltyQuery.setCostState(8);
            rentPenaltyQuery.setType(1);
            Double rentPenalty = financeReceiptPayManager.countRecPayAmount(rentPenaltyQuery);
            BigDecimal penalty = new BigDecimal(purPenalty).add(new BigDecimal(rentPenalty));
            // 管理费
            FinanceReceiptPayQuery managementExpenseQuery = new FinanceReceiptPayQuery();
            managementExpenseQuery.setCostType(String.valueOf(25));
            managementExpenseQuery.setCostState(8);
            managementExpenseQuery.setType(1);
            Double managementExpense = financeReceiptPayManager.countRecPayAmount(managementExpenseQuery);
            map.put(1, new BigDecimal(serviceCharge).setScale(2, BigDecimal.ROUND_DOWN));
            map.put(2, penalty.setScale(2, BigDecimal.ROUND_DOWN));
            map.put(3, new BigDecimal(managementExpense).setScale(2, BigDecimal.ROUND_DOWN));
            list.add(map);
            if (list != null && list.size() > 0) {
                if (count == list.size()) {
                    count = 0;
                    return null;
                } else {
                    count = list.size();
                    return list;
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            LOG.error(e);
        }
        return null;
    }

    @Override
    public boolean execute(Map<Integer, BigDecimal> map, String arg1) throws Exception {
        BigDecimal serviceChargeAmount;
        BigDecimal penaltyAmount;
        BigDecimal managementExpenseAmount;
        // 服务费
        serviceChargeAmount = map.get(1);
        SituationDO serviceChargeSituationDO = new SituationDO();
        serviceChargeSituationDO.setModule(StringConsts.BUSINESS_INCOME_OF_THE_MONTH);
        serviceChargeSituationDO.setType(StringConsts.SERVICECHARGE);
        serviceChargeSituationDO.setValue(serviceChargeAmount.toString());
        serviceChargeSituationDO.setDate(new Date());
        statisticsManager.saveSituationDO(serviceChargeSituationDO);
        // 违约金
        penaltyAmount = map.get(2);
        SituationDO penaltySituationDO = new SituationDO();
        penaltySituationDO.setModule(StringConsts.BUSINESS_INCOME_OF_THE_MONTH);
        penaltySituationDO.setType(StringConsts.PENALTY);
        penaltySituationDO.setValue(penaltyAmount.toString());
        penaltySituationDO.setDate(new Date());
        statisticsManager.saveSituationDO(penaltySituationDO);
        // 管理费
        managementExpenseAmount = map.get(3);
        SituationDO managementExpenseSituationDO = new SituationDO();
        managementExpenseSituationDO.setModule(StringConsts.BUSINESS_INCOME_OF_THE_MONTH);
        managementExpenseSituationDO.setType(StringConsts.MANAGEMENTEXPENSE);
        managementExpenseSituationDO.setValue(managementExpenseAmount.toString());
        managementExpenseSituationDO.setDate(new Date());
        statisticsManager.saveSituationDO(managementExpenseSituationDO);
        return true; 
    }

    public StatisticsManager getStatisticsManager() {
        return statisticsManager;
    }

    public void setStatisticsManager(StatisticsManager statisticsManager) {
        this.statisticsManager = statisticsManager;
    }

    public FinanceReceiptPayManager getFinanceReceiptPayManager() {
        return financeReceiptPayManager;
    }

    public void setFinanceReceiptPayManager(FinanceReceiptPayManager financeReceiptPayManager) {
        this.financeReceiptPayManager = financeReceiptPayManager;
    }

}
