package com.young.pact.service.statistics;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.LogFactoryImpl;
import org.springframework.stereotype.Service;

import com.young.common.dict.CommonEnum;
import com.young.common.domain.ApiResult;
import com.young.pact.api.domain.param.rpc.statistics.StatisticsParam;
import com.young.pact.api.service.rpc.statistics.StatisticsRpcService;
import com.young.pact.common.constant.StringConsts;
import com.young.pact.common.dict.PactCommonEnum;
import com.young.pact.domain.commonbelonger.CommonBelongerQuery;
import com.young.pact.domain.commonbelonger.CommonBelongerVO;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayQuery;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayVO;
import com.young.pact.domain.statistics.AgendaDO;
import com.young.pact.domain.statistics.StatisticDO;
import com.young.pact.manager.commonbelonger.CommonBelongerManager;
import com.young.pact.manager.financereceiptpay.FinanceReceiptPayManager;
import com.young.pact.manager.statistics.StatisticsManager;

/**
 * @描述 : 托进合同创建房间和租后管家,同步首页统计记录
 * @创建者 : GuoXiaoPeng
 * @创建时间 : 2018年9月26日 上午10:12:02
 */
@Service("statisticsRpcService")
public class StatisticsRpcServiceImpl implements StatisticsRpcService{

    private static Log LOG = LogFactoryImpl.getLog(StatisticsRpcServiceImpl.class);
    private CommonBelongerManager commonBelongerManager;
    private StatisticsManager statisticsManager;
    private FinanceReceiptPayManager financeReceiptPayManager;
    
    @Override
    public ApiResult<Boolean> saveStatistics(StatisticsParam param) {
        ApiResult<Boolean> result = new ApiResult<>();
        try {
            boolean purRoomStatisFlag = savePurRoomStatis(param);
            if(!purRoomStatisFlag){
                result.setCode(PactCommonEnum.SAVE_PUR_ROOMSTATIS_ERROR.getCode());
                result.setMessage(PactCommonEnum.SAVE_PUR_ROOMSTATIS_ERROR.getMessage());
                result.setData(false);
                return result;
            }
            boolean receivablesStatisticsFlag = saveReceivablesStatistics(param);
            if(!receivablesStatisticsFlag){
                result.setCode(PactCommonEnum.SAVE_RECEIVABLES_STATISTICS_ERROR.getCode());
                result.setMessage(PactCommonEnum.SAVE_RECEIVABLES_STATISTICS_ERROR.getMessage());
                result.setData(false);
                return result;
            }
            boolean obligationStatisticsFlag = saveObligationStatistics(param);
            if(!obligationStatisticsFlag){
                result.setCode(PactCommonEnum.SAVE_OBLIGATION_STATISTICS_ERROR.getCode());
                result.setMessage(PactCommonEnum.SAVE_OBLIGATION_STATISTICS_ERROR.getMessage());
                result.setData(false);
                return result;
            }
            result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
            result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
            result.setData(false);
        } catch (Exception e) {
            // TODO: handle exception
            LOG.error(e);
            result.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            result.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        }
       
        return result;
    }
    /**
    * @Title: savePurRoomStatis
    * @Description: TODO( 保存首页托进房间--托进量 )
    * @author GuoXiaoPeng
    * @param param 托进合同编码+房间总数
    * @return 成功返回true，失败false
    * @throws
     */
    private boolean savePurRoomStatis(StatisticsParam param){
        /****************************保存交易统计   start**********************************/
        Integer roomCount = 1; 
        roomCount = param.getRoomCount();
        CommonBelongerQuery commonBelongerQuery = new CommonBelongerQuery();
        commonBelongerQuery.setPactCode(param.getPurchaseCode());
        commonBelongerQuery.setUserRole(StringConsts.BELONGER_DEAL);
        CommonBelongerVO commonBelongerVO = commonBelongerManager.getBelongersByParam(commonBelongerQuery);
        for(int i = 0; i < roomCount;i ++){
            StatisticDO statisticDO = new StatisticDO();
            statisticDO.setServiceCode(param.getPurchaseCode());
            statisticDO.setPactType(StringConsts.NEW_INTO_COUNT);
            statisticDO.setPin(commonBelongerVO.getUserPin());
            statisticDO.setName(commonBelongerVO.getUserName());
            statisticDO.setDeptCode(commonBelongerVO.getDeptCode());
            statisticDO.setDeptName(commonBelongerVO.getDeptName());
            statisticsManager.savePactRoomStatistics(statisticDO);
        }
        /****************************保存交易统计   end**********************************/
        return true;
    }
    /**
     * 
    * @Title: saveReceivablesStatistics
    * @Description: TODO( 添加待办事务统计待收款统计记录 )
    * @author GuoXiaoPeng
    * @param param  托进合同编码
     * @param afterRentBelongerVO 租后管家
    * @throws 异常
     */
    private boolean saveReceivablesStatistics(StatisticsParam param) {
        FinanceReceiptPayQuery financeReceiptPayQuery = new FinanceReceiptPayQuery();
        financeReceiptPayQuery.setIsValid(1);
        financeReceiptPayQuery.setPactCode(param.getPurchaseCode());
        financeReceiptPayQuery.setType(0);
        List<Integer> costList = new ArrayList<>();
        costList.add(1);
        costList.add(2);
        financeReceiptPayQuery.setCostList(costList);
        List<FinanceReceiptPayVO> financeReceiptPayVOs = financeReceiptPayManager.listFinanceReceiptPayByPactCode(financeReceiptPayQuery);
        if(null != financeReceiptPayVOs && financeReceiptPayVOs.size() > 0){
            for(FinanceReceiptPayVO recPay:financeReceiptPayVOs){
                AgendaDO pendingAgendaDO = new AgendaDO();
                pendingAgendaDO.setServiceCode(String.valueOf(recPay.getId()));
                pendingAgendaDO.setType(StringConsts.STATISTIC_PENDING_RECEIVABLES);
                pendingAgendaDO.setIsDo(0);
                pendingAgendaDO.setPin(param.getAfterBelogerPin());
                pendingAgendaDO.setName(param.getAfterBelogerUserName());
                pendingAgendaDO.setDeptCode(param.getAfterBelogerDeptCode());
                pendingAgendaDO.setDeptName(param.getAfterBelogerDeptName());
                pendingAgendaDO.setDate(new Date());
                statisticsManager.saveBacklogStatistics(pendingAgendaDO);
            }
        }
        return true;
    }
    
    /**
     * 
    * @Title: saveObligationStatistics
    * @Description: TODO( 添加待办事务统计待付款统计记录 )
    * @author GuoXiaoPeng
    * @param param 托进合同编码
     * @param afterRentBelongerVO 租后管家
    * @throws 异常
     */
    private boolean saveObligationStatistics(StatisticsParam param) {
        FinanceReceiptPayQuery financeReceiptPayQuery = new FinanceReceiptPayQuery();
        financeReceiptPayQuery.setIsValid(1);
        financeReceiptPayQuery.setPactCode(param.getPurchaseCode());
        financeReceiptPayQuery.setType(2);
        List<Integer> costList = new ArrayList<>();
        costList.add(4);
        costList.add(5);
        financeReceiptPayQuery.setCostList(costList);
        List<FinanceReceiptPayVO> financeReceiptPayVOs = financeReceiptPayManager.listFinanceReceiptPayByPactCode(financeReceiptPayQuery);
        if(null != financeReceiptPayVOs && financeReceiptPayVOs.size() > 0){
            for(FinanceReceiptPayVO recPay:financeReceiptPayVOs){
                AgendaDO agendaDO = new AgendaDO();
                agendaDO.setServiceCode(String.valueOf(recPay.getId()));
                agendaDO.setType(StringConsts.STATISTIC_PENDING_PENDING_PAYMENT);
                agendaDO.setIsDo(0);
                agendaDO.setPin(param.getAfterBelogerPin());
                agendaDO.setName(param.getAfterBelogerUserName());
                agendaDO.setDeptCode(param.getAfterBelogerDeptCode());
                agendaDO.setDeptName(param.getAfterBelogerDeptName());
                agendaDO.setDate(new Date());
                statisticsManager.saveBacklogStatistics(agendaDO);
            }
        }
        return true;
    }
    public CommonBelongerManager getCommonBelongerManager() {
        return commonBelongerManager;
    }
    public void setCommonBelongerManager(CommonBelongerManager commonBelongerManager) {
        this.commonBelongerManager = commonBelongerManager;
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
