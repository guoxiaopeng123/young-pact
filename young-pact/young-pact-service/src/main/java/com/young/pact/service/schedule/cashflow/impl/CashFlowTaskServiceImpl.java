package com.young.pact.service.schedule.cashflow.impl;

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
 * @描述 : 本月现金流量定时任务
 * @创建者 : guoxiaopeng
 * @创建时间 : 2018年9月17日 下午7:31:20
 */
@Service("cashFlowTaskService")
public class CashFlowTaskServiceImpl implements IScheduleTaskDealSingle<Map<Integer, BigDecimal>> {

    private static final Log LOG = LogFactory.getLog(CashFlowTaskServiceImpl.class);
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
            // 现金收入
            FinanceReceiptPayQuery cashIncomeQuery = new FinanceReceiptPayQuery();
            cashIncomeQuery.setCostState(8);
            cashIncomeQuery.setType(1);
            Double cashIncome = financeReceiptPayManager.countRecPayAmount(cashIncomeQuery);
            // 现金支出
            FinanceReceiptPayQuery cashExpenditureQuery = new FinanceReceiptPayQuery();
            cashExpenditureQuery.setCostState(8);
            cashExpenditureQuery.setType(3);
            Double cashExpenditure = financeReceiptPayManager.countRecPayAmount(cashExpenditureQuery);
            map.put(1, new BigDecimal(cashIncome).setScale(2, BigDecimal.ROUND_DOWN));
            map.put(2, new BigDecimal(cashExpenditure).setScale(2, BigDecimal.ROUND_DOWN));
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
        BigDecimal cashIncomeAmount;
        BigDecimal cashExpenditureAmount;
        // 现金收入
        cashIncomeAmount = map.get(1);
        SituationDO cashIncomeAmountSituationDO = new SituationDO();
        cashIncomeAmountSituationDO.setModule(StringConsts.CASH_FLOW_THIS_MONTH);
        cashIncomeAmountSituationDO.setType(StringConsts.CASH_INCOME);
        cashIncomeAmountSituationDO.setValue(cashIncomeAmount.toString());
        cashIncomeAmountSituationDO.setDate(new Date());
        statisticsManager.saveSituationDO(cashIncomeAmountSituationDO);
        LOG.info("本月现金流量定时任务----现金收入执行结果" + cashIncomeAmountSituationDO);
        // 现金支出
        cashExpenditureAmount = map.get(2);
        SituationDO cashExpenditureSituationDO = new SituationDO();
        cashExpenditureSituationDO.setModule(StringConsts.CASH_FLOW_THIS_MONTH);
        cashExpenditureSituationDO.setType(StringConsts.CASH_EXPENDITURE);
        cashExpenditureSituationDO.setValue(cashExpenditureAmount.toString());
        cashExpenditureSituationDO.setDate(new Date());
        statisticsManager.saveSituationDO(cashExpenditureSituationDO);
        LOG.info("本月现金流量定时任务----现金支出执行结果" + cashExpenditureSituationDO);
        return true;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
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
