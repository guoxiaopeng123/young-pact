package com.young.pact.manager.financereceiptpay.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.tools.common.rocketmq.client.RocketmqClient;
import com.tools.common.util.json.JsonUtil;
import com.young.common.domain.ApiResult;
import com.young.common.exception.DaoException;
import com.young.common.page.PageBean;
import com.young.common.page.PageTableBean;
import com.young.pact.common.constant.ErrorPactConsts;
import com.young.pact.common.constant.NormalConstant;
import com.young.pact.common.constant.StringConsts;
import com.young.pact.common.exception.PactManagerExcepion;
import com.young.pact.common.util.DateUtil;
import com.young.pact.dao.commonbelonger.CommonBelongerDao;
import com.young.pact.dao.financereceiptpay.FinanceReceiptPayDao;
import com.young.pact.dao.financereceiptpay.FinanceReceiptPayVoucherDao;
import com.young.pact.domain.commonbelonger.CommonBelongerQuery;
import com.young.pact.domain.commonbelonger.CommonBelongerVO;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayDO;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayQuery;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayVO;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayVoucherDO;
import com.young.pact.domain.statistics.AgendaDO;
import com.young.pact.manager.financereceiptpay.FinanceReceiptPayManager;
import com.young.sso.api.domain.result.rpc.personal.PersonalResult;
import com.young.sso.api.service.rpc.personal.PersonalRpcService;

/**
 * @描述 : 收支
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月6日 下午9:49:38
 */
@Component("financeReceiptPayManager")
public class FinanceReceiptPayManagerImpl implements FinanceReceiptPayManager {
    /******************声明区*********************/
    private static final Log LOG = LogFactory.getLog(FinanceReceiptPayManagerImpl.class); 
    private FinanceReceiptPayDao financeReceiptPayDao;
    private FinanceReceiptPayVoucherDao financeReceiptPayVoucherDao;
    private PlatformTransactionManager transactionManager;
    private PersonalRpcService personalRpcService;
    private RocketmqClient rocketmqClient;
    private CommonBelongerDao commonBelongerDao;
    /******************方法区*********************/



    @Override
    public FinanceReceiptPayVO getPayeeByRentPactCode(FinanceReceiptPayQuery query) {
        try {
            FinanceReceiptPayVO financeReceiptPayVO = financeReceiptPayDao.getPayeeReceiptPayByPactCode(query);
            return financeReceiptPayVO;
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.QUERY_RENTER_PAYEE_BY_PACTCODE_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.QUERY_RENTER_PAYEE_BY_PACTCODE_ERROR,e);
        }
    }
    
    @Override
    public PageBean<FinanceReceiptPayVO> listAllFinanceReceiptPay(FinanceReceiptPayQuery query) {
        try {
            int count = financeReceiptPayDao.countAllFinanceReceiptPay(query);
            PageBean<FinanceReceiptPayVO> page = new PageTableBean<FinanceReceiptPayVO>(query.getPageIndex(), query.getPageSize());
            if (count != 0){
                page.setTotalItem(count);
                int startRow = page.getStartRow();
                if (startRow > 0) {
                    startRow -= 1;
                }
                query.setStartRow(startRow);
                List<FinanceReceiptPayVO> financeReceiptPayList = financeReceiptPayDao.listAllFinanceReceiptPay(query);
                if(null != financeReceiptPayList){
                    for (FinanceReceiptPayVO financeReceiptPayVO : financeReceiptPayList) {
                        // 只有应收、应支 逾期天数是算出来的
                        if(0 == financeReceiptPayVO.getType() || 2 == financeReceiptPayVO.getType()){
                            // 不是已收款、已付款 的收支才有逾期天数
                            if(3 != financeReceiptPayVO.getCostState() && 6 != financeReceiptPayVO.getCostState()){
                                // 逾期天数 计算
                                Integer daysBetween = DateUtil.daysBetween(financeReceiptPayVO.getCostTime());
                                financeReceiptPayVO.setOverdueDay("" + (daysBetween > 0 ? daysBetween : 0));
                            }else{
                                if(0 == financeReceiptPayVO.getType()||2 == financeReceiptPayVO.getType()){
                                    //查询这个应收的产生的最后一条实收
                                    FinanceReceiptPayQuery lastRecPayQuery = new FinanceReceiptPayQuery();
                                    lastRecPayQuery.setPid(financeReceiptPayVO.getId());
                                    FinanceReceiptPayVO  lastRecPayVo = financeReceiptPayDao.getLastRecPay(lastRecPayQuery);
                                    if(null != lastRecPayVo){
                                        try {
                                            int overdueDays = DateUtil.daysBetweenTwo(lastRecPayVo.getCostTime(), financeReceiptPayVO.getCostTime());
                                            financeReceiptPayVO.setOverdueDay("" + (overdueDays > 0 ? overdueDays : 0));
                                       } catch (ParseException e) {
                                            LOG.error(e);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                page.addAll(financeReceiptPayList);
            }
            return page;
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.LIST_FINANCE_RECEIPTPAY_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.LIST_FINANCE_RECEIPTPAY_ERROR, e);
        }
    }

    @Override
    public PageBean<FinanceReceiptPayVO> listReceivFinanceReceiptPay(FinanceReceiptPayQuery query) {
        try {
            int count = financeReceiptPayDao.countReceivFinanceReceiptPay(query);
            PageBean<FinanceReceiptPayVO> page = new PageTableBean<FinanceReceiptPayVO>(query.getPageIndex(), query.getPageSize());
            if (count != 0){
                page.setTotalItem(count);
                int startRow = page.getStartRow();
                if (startRow > 0) {
                    startRow -= 1;
                }
                query.setStartRow(startRow);
                List<FinanceReceiptPayVO> financeReceiptPayList = financeReceiptPayDao.listReceivFinanceReceiptPay(query);
                if(null != financeReceiptPayList){
                    List<Long> pids = new ArrayList<>();
                    for (FinanceReceiptPayVO financeReceiptPayVO : financeReceiptPayList) {
                        pids.add(financeReceiptPayVO.getId());
                    }
                    List<FinanceReceiptPayVO> sumReceiptPay = financeReceiptPayDao.listSumReceiptPay(pids);
                    for (FinanceReceiptPayVO financeReceiptPayVO : financeReceiptPayList) {
                        // 应收、应支 金额 默认等于 未收、未支 金额
                        BigDecimal totalAmount = BigDecimal.valueOf(financeReceiptPayVO.getCostAmount());
                        if(null != sumReceiptPay){
                            for (FinanceReceiptPayVO receiptPay : sumReceiptPay) {
                                if(financeReceiptPayVO.getId().equals(receiptPay.getPid())){
                                    // 减掉 实收、实支 金额
                                    totalAmount = totalAmount.subtract(BigDecimal.valueOf(receiptPay.getCostAmount()));
                                }
                            }
                        }
                        // 未收、未支 金额
                        financeReceiptPayVO.setUnsupportedAmount(totalAmount.doubleValue());
                        // 不是已收款、已付款 的收支才有逾期天数
                        if(3 != financeReceiptPayVO.getCostState() && 6 != financeReceiptPayVO.getCostState()){
                            // 逾期天数 计算
                            Integer daysBetween = DateUtil.daysBetween(financeReceiptPayVO.getCostTime());
                            financeReceiptPayVO.setOverdueDay("" + (daysBetween > 0 ? daysBetween : 0));
                        }else{
                            if(0 == financeReceiptPayVO.getType()||2 == financeReceiptPayVO.getType()){
                                //查询这个应收的产生的最后一条实收
                                FinanceReceiptPayQuery lastRecPayQuery = new FinanceReceiptPayQuery();
                                lastRecPayQuery.setPid(financeReceiptPayVO.getId());
                                FinanceReceiptPayVO  lastRecPayVo = financeReceiptPayDao.getLastRecPay(lastRecPayQuery);
                                if(null != lastRecPayVo){
                                    try {
                                        int overdueDays = DateUtil.daysBetweenTwo(lastRecPayVo.getCostTime(), financeReceiptPayVO.getCostTime());
                                        financeReceiptPayVO.setOverdueDay("" + (overdueDays > 0 ? overdueDays : 0));
                                   } catch (ParseException e) {
                                        LOG.error(e);
                                    }
                                }
                            }
                        }
                    }
                }
                page.addAll(financeReceiptPayList);
            }
            return page;
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.LIST_FINANCE_RECEIPTPAY_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.LIST_FINANCE_RECEIPTPAY_ERROR, e);
        }
    }

    @Override
    public PageBean<FinanceReceiptPayVO> listNetFinanceReceiptPay(FinanceReceiptPayQuery query) {
        try {
            int count = financeReceiptPayDao.countNetFinanceReceiptPay(query);
            PageBean<FinanceReceiptPayVO> page = new PageTableBean<FinanceReceiptPayVO>(query.getPageIndex(), query.getPageSize());
            if (count != 0){
                page.setTotalItem(count);
                int startRow = page.getStartRow();
                if (startRow > 0) {
                    startRow -= 1;
                }
                query.setStartRow(startRow);
                page.addAll(financeReceiptPayDao.listNetFinanceReceiptPay(query));
            }
            return page;
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.LIST_FINANCE_RECEIPTPAY_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.LIST_FINANCE_RECEIPTPAY_ERROR, e);
        }
    }

    @Override
    public FinanceReceiptPayVO getAnswerCollectDetail(Long id) {
        try {
            FinanceReceiptPayVO financeReceiptPayVO = financeReceiptPayDao.getAnswerCollectDetail(id);
            if(null != financeReceiptPayVO){
                List<Long> pids = new ArrayList<>();
                pids.add(id);
                List<FinanceReceiptPayVO> sumReceiptPay = financeReceiptPayDao.listSumReceiptPay(pids);
                // 应收 金额 默认等于 未收 金额
                BigDecimal totalAmount = BigDecimal.valueOf(financeReceiptPayVO.getCostAmount());
                if(null != sumReceiptPay){
                    for (FinanceReceiptPayVO receiptPay : sumReceiptPay) {
                        if(id.equals(receiptPay.getPid())){
                            // 减掉 实收 金额
                            totalAmount = totalAmount.subtract(BigDecimal.valueOf(receiptPay.getCostAmount()));
                        }
                    }
                }
                // 未收 金额
                financeReceiptPayVO.setUnsupportedAmount(totalAmount.doubleValue());
                // 不是已收款 的收支才有逾期天数
                if(3 != financeReceiptPayVO.getCostState()){
                    // 逾期天数 计算
                    Integer daysBetween = DateUtil.daysBetween(financeReceiptPayVO.getCostTime());
                    financeReceiptPayVO.setOverdueDay("" + (daysBetween > 0 ? daysBetween : 0));
                }else{
                    if(0 == financeReceiptPayVO.getType()){
                        //查询这个应收的产生的最后一条实收
                        FinanceReceiptPayQuery lastRecPayQuery = new FinanceReceiptPayQuery();
                        lastRecPayQuery.setPid(id);
                        FinanceReceiptPayVO  lastRecPayVo = financeReceiptPayDao.getLastRecPay(lastRecPayQuery);
                        if(null != lastRecPayVo){
                            try {
                                int overdueDays = DateUtil.daysBetweenTwo(lastRecPayVo.getCostTime(), financeReceiptPayVO.getCostTime());
                                financeReceiptPayVO.setOverdueDay("" + (overdueDays > 0 ? overdueDays : 0));
                           } catch (ParseException e) {
                                LOG.error(e);
                            }
                        }
                    }
                }
            }
            return financeReceiptPayVO;
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.GET_FINANCE_RECEIPTPAY_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.GET_FINANCE_RECEIPTPAY_ERROR, e);
        }
    }

    @Override
    public FinanceReceiptPayVO getRealCollectDetail(Long id) {
        try {
            return financeReceiptPayDao.getRealCollectDetail(id);
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.GET_FINANCE_RECEIPTPAY_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.GET_FINANCE_RECEIPTPAY_ERROR, e);
        }
    }

    @Override
    public List<FinanceReceiptPayVO> listRealCollectByPid(Long pid) {
        try {
            return financeReceiptPayDao.listRealCollectByPid(pid);
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.LIST_FINANCE_RECEIPTPAY_PID_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.LIST_FINANCE_RECEIPTPAY_PID_ERROR, e);
        }
    }

    @Override
    public FinanceReceiptPayVO getAnswerExpendDetail(Long id) {
        try {
            FinanceReceiptPayVO financeReceiptPayVO = financeReceiptPayDao.getAnswerExpendDetail(id);
            if(null != financeReceiptPayVO){
                List<Long> pids = new ArrayList<>();
                pids.add(id);
                List<FinanceReceiptPayVO> sumReceiptPay = financeReceiptPayDao.listSumReceiptPay(pids);
                // 应支 金额 默认等于 未支 金额
                BigDecimal totalAmount = BigDecimal.valueOf(financeReceiptPayVO.getCostAmount());
                if(null != sumReceiptPay){
                    for (FinanceReceiptPayVO receiptPay : sumReceiptPay) {
                        if(id.equals(receiptPay.getPid())){
                            // 减掉 实支 金额
                            totalAmount = totalAmount.subtract(BigDecimal.valueOf(receiptPay.getCostAmount()));
                        }
                    }
                }
                // 未支 金额
                financeReceiptPayVO.setUnsupportedAmount(totalAmount.doubleValue());
                // 不是已付款 的收支才有逾期天数
                if(6 != financeReceiptPayVO.getCostState()){
                    // 逾期天数 计算
                    Integer daysBetween = DateUtil.daysBetween(financeReceiptPayVO.getCostTime());
                    financeReceiptPayVO.setOverdueDay("" + (daysBetween > 0 ? daysBetween : 0));
                }else{
                    if(2 == financeReceiptPayVO.getType()){
                        //查询这个应收的产生的最后一条实支
                        FinanceReceiptPayQuery lastRecPayQuery = new FinanceReceiptPayQuery();
                        lastRecPayQuery.setPid(id);
                        FinanceReceiptPayVO  lastRecPayVo = financeReceiptPayDao.getLastRecPay(lastRecPayQuery);
                        if(null != lastRecPayVo){
                            try {
                                int overdueDays = DateUtil.daysBetweenTwo(lastRecPayVo.getCostTime(), financeReceiptPayVO.getCostTime());
                                financeReceiptPayVO.setOverdueDay("" + (overdueDays > 0 ? overdueDays : 0));
                           } catch (ParseException e) {
                                LOG.error(e);
                            }
                        }
                    }
                }
            }
            return financeReceiptPayVO;
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.GET_FINANCE_RECEIPTPAY_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.GET_FINANCE_RECEIPTPAY_ERROR, e);
        }
    }

    @Override
    public FinanceReceiptPayVO getRealExpendDetail(Long id) {
        try {
            return financeReceiptPayDao.getRealExpendDetail(id);
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.GET_FINANCE_RECEIPTPAY_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.GET_FINANCE_RECEIPTPAY_ERROR, e);
        }
    }

    @Override
    public List<FinanceReceiptPayVO> listRealExpendByPid(Long pid) {
        try {
            return financeReceiptPayDao.listRealExpendByPid(pid);
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.LIST_FINANCE_RECEIPTPAY_PID_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.LIST_FINANCE_RECEIPTPAY_PID_ERROR, e);
        }
    }

    @Override
    public PageBean<FinanceReceiptPayVO> listReceivReceiptPay(FinanceReceiptPayQuery query) {
        try {
            int count = financeReceiptPayDao.countReceivReceiptPay(query);
            PageBean<FinanceReceiptPayVO> page = new PageTableBean<FinanceReceiptPayVO>(query.getPageIndex(), query.getPageSize());
            if (count != 0){
                page.setTotalItem(count);
                int startRow = page.getStartRow();
                if (startRow > 0) {
                    startRow -= 1;
                }
                query.setStartRow(startRow);
                List<FinanceReceiptPayVO> financeReceiptPayList = financeReceiptPayDao.listReceivReceiptPay(query);
                if(null != financeReceiptPayList){
                    List<Long> pids = new ArrayList<>();
                    for (FinanceReceiptPayVO financeReceiptPayVO : financeReceiptPayList) {
                        pids.add(financeReceiptPayVO.getId());
                    }
                    List<FinanceReceiptPayVO> sumReceiptPay = financeReceiptPayDao.listSumReceiptPay(pids);
                    for (FinanceReceiptPayVO financeReceiptPayVO : financeReceiptPayList) {
                        // 应收、应支 金额 默认等于 未收、未支 金额
                        BigDecimal totalAmount = BigDecimal.valueOf(financeReceiptPayVO.getCostAmount());
                        if(null != sumReceiptPay){
                            for (FinanceReceiptPayVO receiptPay : sumReceiptPay) {
                                if(financeReceiptPayVO.getId().equals(receiptPay.getPid())){
                                    // 减掉 实收、实支 金额
                                    totalAmount = totalAmount.subtract(BigDecimal.valueOf(receiptPay.getCostAmount()));
                                }
                            }
                        }
                        // 未收、未支 金额
                        financeReceiptPayVO.setUnsupportedAmount(totalAmount.doubleValue());
                        // 不是已收款、已付款 的收支才有逾期天数
                        if(3 != financeReceiptPayVO.getCostState() && 6 != financeReceiptPayVO.getCostState()){
                            // 逾期天数 计算
                            Integer daysBetween = DateUtil.daysBetween(financeReceiptPayVO.getCostTime());
                            financeReceiptPayVO.setOverdueDay("" + (daysBetween > 0 ? daysBetween : 0));
                        }else{
                            if(0 == financeReceiptPayVO.getType()||2 == financeReceiptPayVO.getType()){
                                //查询这个应收的产生的最后一条实收
                                FinanceReceiptPayQuery lastRecPayQuery = new FinanceReceiptPayQuery();
                                lastRecPayQuery.setPid(financeReceiptPayVO.getId());
                                FinanceReceiptPayVO  lastRecPayVo = financeReceiptPayDao.getLastRecPay(lastRecPayQuery);
                                if(null != lastRecPayVo){
                                    try {
                                        int overdueDays = DateUtil.daysBetweenTwo(lastRecPayVo.getCostTime(), financeReceiptPayVO.getCostTime());
                                        financeReceiptPayVO.setOverdueDay("" + (overdueDays > 0 ? overdueDays : 0));
                                   } catch (ParseException e) {
                                        LOG.error(e);
                                    }
                                }
                            }
                        }
                    }
                }
                page.addAll(financeReceiptPayList);
            }
            return page;
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.LIST_FINANCE_RECEIPTPAY_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.LIST_FINANCE_RECEIPTPAY_ERROR, e);
        }
    }

    @Override
    public PageBean<FinanceReceiptPayVO> listNetReceiptPay(FinanceReceiptPayQuery query) {
        try {
            int count = financeReceiptPayDao.countNetReceiptPay(query);
            PageBean<FinanceReceiptPayVO> page = new PageTableBean<FinanceReceiptPayVO>(query.getPageIndex(), query.getPageSize());
            if (count != 0){
                page.setTotalItem(count);
                int startRow = page.getStartRow();
                if (startRow > 0) {
                    startRow -= 1;
                }
                query.setStartRow(startRow);
                List<FinanceReceiptPayVO> netReceiptPayList = financeReceiptPayDao.listNetReceiptPay(query);
                if(null != netReceiptPayList){
                    for (FinanceReceiptPayVO financeReceiptPayVO : netReceiptPayList) {
                        // 只有应收、应支 逾期天数是算出来的
                        if(0 == financeReceiptPayVO.getType() || 2 == financeReceiptPayVO.getType()){
                            // 不是已收款、已付款 的收支才有逾期天数
                            if(3 != financeReceiptPayVO.getCostState() && 6 != financeReceiptPayVO.getCostState()){
                                // 逾期天数 计算
                                Integer daysBetween = DateUtil.daysBetween(financeReceiptPayVO.getCostTime());
                                financeReceiptPayVO.setOverdueDay("" + (daysBetween > 0 ? daysBetween : 0));
                            }else{
                                financeReceiptPayVO.setOverdueDay("0");
                            }
                        }else if(3 == financeReceiptPayVO.getType()){
                            //实支的逾期天数：1 财务付款前，当前时间-应支预计收支时间；2 财务付款后，取实支的逾期天数overdue_day
                            if(StringUtils.isBlank(financeReceiptPayVO.getOverdueDay())){
                                FinanceReceiptPayVO branchRecPay = financeReceiptPayDao.getPactAnswerExpendDetail(financeReceiptPayVO.getPid());
                                Integer daysBetween = DateUtil.daysBetween(branchRecPay.getCostTime());
                                financeReceiptPayVO.setOverdueDay("" + (daysBetween > 0 ? daysBetween : 0));
                            }
                        }
                    }
                }
                page.addAll(netReceiptPayList);
            }
            return page;
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.LIST_FINANCE_RECEIPTPAY_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.LIST_FINANCE_RECEIPTPAY_ERROR, e);
        }
    }

    @Override
    public FinanceReceiptPayVO getPactAnswerCollectDetail(Long id) {
        try {
            FinanceReceiptPayVO financeReceiptPayVO = financeReceiptPayDao.getPactAnswerCollectDetail(id);
            if(null != financeReceiptPayVO){
                List<Long> pids = new ArrayList<>();
                pids.add(id);
                List<FinanceReceiptPayVO> sumReceiptPay = financeReceiptPayDao.listSumReceiptPay(pids);
                // 应收 金额 默认等于 未收 金额
                BigDecimal totalAmount = BigDecimal.valueOf(financeReceiptPayVO.getCostAmount());
                if(null != sumReceiptPay){
                    for (FinanceReceiptPayVO receiptPay : sumReceiptPay) {
                        if(id.equals(receiptPay.getPid())){
                            // 减掉 实收 金额
                            totalAmount = totalAmount.subtract(BigDecimal.valueOf(receiptPay.getCostAmount()));
                        }
                    }
                }
                // 未收 金额
                financeReceiptPayVO.setUnsupportedAmount(totalAmount.doubleValue());
                // 不是已收款 的收支才有逾期天数
                if(3 != financeReceiptPayVO.getCostState()){
                    // 逾期天数 计算
                    Integer daysBetween = DateUtil.daysBetween(financeReceiptPayVO.getCostTime());
                    financeReceiptPayVO.setOverdueDay("" + (daysBetween > 0 ? daysBetween : 0));
                }else{
                    if(0 == financeReceiptPayVO.getType()||2 == financeReceiptPayVO.getType()){
                        //查询这个应收的产生的最后一条实收
                        FinanceReceiptPayQuery lastRecPayQuery = new FinanceReceiptPayQuery();
                        lastRecPayQuery.setPid(financeReceiptPayVO.getId());
                        FinanceReceiptPayVO  lastRecPayVo = financeReceiptPayDao.getLastRecPay(lastRecPayQuery);
                        if(null != lastRecPayVo){
                            try {
                                int overdueDays = DateUtil.daysBetweenTwo(lastRecPayVo.getCostTime(), financeReceiptPayVO.getCostTime());
                                financeReceiptPayVO.setOverdueDay("" + (overdueDays > 0 ? overdueDays : 0));
                           } catch (ParseException e) {
                                LOG.error(e);
                            }
                        }
                    }
                }
            }
            return financeReceiptPayVO;
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.GET_FINANCE_RECEIPTPAY_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.GET_FINANCE_RECEIPTPAY_ERROR, e);
        }
    }

    @Override
    public FinanceReceiptPayVO getPactRealCollectDetail(Long id) {
        try {
            return financeReceiptPayDao.getPactRealCollectDetail(id);
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.GET_FINANCE_RECEIPTPAY_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.GET_FINANCE_RECEIPTPAY_ERROR, e);
        }
    }

    @Override
    public FinanceReceiptPayVO getPactAnswerExpendDetail(Long id) {
        try {
            FinanceReceiptPayVO financeReceiptPayVO = financeReceiptPayDao.getPactAnswerExpendDetail(id);
            if(null != financeReceiptPayVO){
                List<Long> pids = new ArrayList<>();
                pids.add(id);
                List<FinanceReceiptPayVO> sumReceiptPay = financeReceiptPayDao.listSumReceiptPay(pids);
                // 应支 金额 默认等于 未支 金额
                BigDecimal totalAmount = BigDecimal.valueOf(financeReceiptPayVO.getCostAmount());
                if(null != sumReceiptPay){
                    for (FinanceReceiptPayVO receiptPay : sumReceiptPay) {
                        if(id.equals(receiptPay.getPid())){
                            // 减掉 实支 金额
                            totalAmount = totalAmount.subtract(BigDecimal.valueOf(receiptPay.getCostAmount()));
                        }
                    }
                }
                // 未支 金额
                financeReceiptPayVO.setUnsupportedAmount(totalAmount.doubleValue());
                // 不是已付款 的收支才有逾期天数
                if(6 != financeReceiptPayVO.getCostState()){
                    // 逾期天数 计算
                    Integer daysBetween = DateUtil.daysBetween(financeReceiptPayVO.getCostTime());
                    financeReceiptPayVO.setOverdueDay("" + (daysBetween > 0 ? daysBetween : 0));
                }else{
                    if(2 == financeReceiptPayVO.getType()){
                        //查询这个应收的产生的最后一条实支
                        FinanceReceiptPayQuery lastRecPayQuery = new FinanceReceiptPayQuery();
                        lastRecPayQuery.setPid(id);
                        FinanceReceiptPayVO  lastRecPayVo = financeReceiptPayDao.getLastRecPay(lastRecPayQuery);
                        if(null != lastRecPayVo){
                            try {
                                int overdueDays = DateUtil.daysBetweenTwo(lastRecPayVo.getCostTime(), financeReceiptPayVO.getCostTime());
                                financeReceiptPayVO.setOverdueDay("" + (overdueDays > 0 ? overdueDays : 0));
                           } catch (ParseException e) {
                                LOG.error(e);
                            }
                        }
                    }
                }
            }
            return financeReceiptPayVO;
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.GET_FINANCE_RECEIPTPAY_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.GET_FINANCE_RECEIPTPAY_ERROR, e);
        }
    }

    @Override
    public FinanceReceiptPayVO getPactRealExpendDetail(Long id) {
        try {
            return financeReceiptPayDao.getPactRealExpendDetail(id);
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.GET_FINANCE_RECEIPTPAY_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.GET_FINANCE_RECEIPTPAY_ERROR, e);
        }
    }

    @Override
    public boolean answerCollectReceipt(final FinanceReceiptPayDO financeReceiptPayDO) {
        try {
            TransactionTemplate transactionTemplate =  new TransactionTemplate(transactionManager);
            return transactionTemplate.execute(new TransactionCallback<Boolean>() {
                @Override
                public Boolean doInTransaction(TransactionStatus status) {
                    try {
                        Long id = financeReceiptPayDao.saveReceiptPay(financeReceiptPayDO);
                        if(id > 0){
                            saveBacklogStatistics(financeReceiptPayDO,id);
                            List<FinanceReceiptPayVoucherDO> financeReceiptPayVoucherDOs = financeReceiptPayDO.getFinanceReceiptPayVoucherList();
                            for (FinanceReceiptPayVoucherDO financeReceiptPayVoucherDO : financeReceiptPayVoucherDOs) {
                                financeReceiptPayVoucherDO.setReceiptPayId(id);
                                financeReceiptPayVoucherDO.setIp(financeReceiptPayDO.getIp());
                                financeReceiptPayVoucherDO.setCreateName(financeReceiptPayDO.getCreateName());
                            }
                            int flag = financeReceiptPayVoucherDao.saveFinanceReceiptPayVouchers(financeReceiptPayVoucherDOs);
                            if(flag > 0){
                                return true;
                            }else{
                                status.setRollbackOnly();
                                return false;
                            }
                        }else{
                            status.setRollbackOnly();
                            return false;
                        }
                    } catch (DaoException e) {
                        status.setRollbackOnly();
                        LOG.error(ErrorPactConsts.RECEIPT_FINANCE_RECEIPTPAY_ERROR, e);
                        throw new PactManagerExcepion(ErrorPactConsts.RECEIPT_FINANCE_RECEIPTPAY_ERROR, e);
                    }
                }
                /**
                 * @Title: saveBacklogStatistics
                 * @Description: TODO( 待办事务统计收款待确认   )
                 * @author GuoXiaoPeng
                 * @param financeReceiptPayDO 实收信息
                 * @param id 实收id
                 * @throws 异常
                 */
                private void saveBacklogStatistics(FinanceReceiptPayDO financeReceiptPayDO, Long id) throws DaoException {
                    try {
                        CommonBelongerQuery belongerQuery = new CommonBelongerQuery();
                        belongerQuery.setPactCode(financeReceiptPayDO.getPactCode());
                        belongerQuery.setUserRole(StringConsts.BELONGER_DEAL);
                        CommonBelongerVO commonBelongerVO = commonBelongerDao.getBelongerByParam(belongerQuery);
                        AgendaDO agendaDO = new AgendaDO();
                        agendaDO.setServiceCode(String.valueOf(financeReceiptPayDO.getPid()));
                        agendaDO.setType(StringConsts.STATISTIC_PENDING_RECEIPT_CONFIRMED);
                        agendaDO.setIsDo(0);
                        agendaDO.setPin(commonBelongerVO.getUserPin());
                        agendaDO.setName(commonBelongerVO.getUserName());
                        agendaDO.setDeptCode(commonBelongerVO.getDeptCode());
                        agendaDO.setDeptName(commonBelongerVO.getDeptName());
                        agendaDO.setDate(new Date());
                        String json = JsonUtil.toJson(agendaDO);
                        rocketmqClient.sendMessage(NormalConstant.TOPIC_BACKLOGSTATISTICS, NormalConstant.TAG_DATA_AGENDA_SAVE, "", json);
                    } catch (Exception e) {
                        LOG.error("保存待办事务统计收款待确认统计记录错误，操作对象financeReceiptPayDO："+ financeReceiptPayDO + ",收支id：" + id);
                    }
                   
                }
            });
        } catch (Exception e) {
            LOG.error(ErrorPactConsts.RECEIPT_FINANCE_RECEIPTPAY_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.RECEIPT_FINANCE_RECEIPTPAY_ERROR, e);
        }
    }
    @Override
    public boolean realCollectConfirm(final FinanceReceiptPayDO financeReceiptPayDO) {
        try {
            TransactionTemplate transactionTemplate =  new TransactionTemplate(transactionManager);
            return transactionTemplate.execute(new TransactionCallback<Boolean>() {
                @Override
                public Boolean doInTransaction(TransactionStatus status) {
                    try {
                        // 修改实收费用状态
                        int flag = financeReceiptPayDao.updateCostState(financeReceiptPayDO);
                        if(flag > 0){
                            // 实收 信息
                            FinanceReceiptPayVO realCollectDetail = financeReceiptPayDao.getRealCollectDetail(financeReceiptPayDO.getId());
                            if(null != realCollectDetail){
                                // 应收 信息
                                FinanceReceiptPayVO receiptPayVO = financeReceiptPayDao.getAnswerCollectDetail(realCollectDetail.getPid());
                                if(null != receiptPayVO){
                                    // 实收总和
                                    double sumAmount = financeReceiptPayDao.getRealAmountByPid(receiptPayVO.getId());
                                    // 未收 金额
                                    BigDecimal totalAmount = BigDecimal.valueOf(receiptPayVO.getCostAmount());
                                    // 减掉 实收 金额
                                    totalAmount = totalAmount.subtract(BigDecimal.valueOf(sumAmount));
                                    
                                    // 判断未收大小
                                    if(totalAmount.compareTo(new BigDecimal(0)) == 0){// 等于
                                        financeReceiptPayDO.setCostState(3);// 已收款
                                        financeReceiptPayDO.setId(realCollectDetail.getPid());
                                        flag = financeReceiptPayDao.updateCostState(financeReceiptPayDO);
                                        if(!(flag > 0)){
                                            status.setRollbackOnly();
                                            return false;
                                        }
                                        /******************** 修改待办事务统计待收款的状态  start *******************************/
                                        AgendaDO agendaDO = new AgendaDO();
                                        agendaDO.setServiceCode(String.valueOf(realCollectDetail.getPid()));
                                        agendaDO.setIsDo(1);
                                        agendaDO.setType(StringConsts.STATISTIC_PENDING_RECEIVABLES);
                                        agendaDO.setModifiedName(financeReceiptPayDO.getModifiedName());
                                        agendaDO.setIp(financeReceiptPayDO.getIp());
                                        String json = JsonUtil.toJson(agendaDO);
                                        rocketmqClient.sendMessage(NormalConstant.TOPIC_BACKLOGSTATISTICS, NormalConstant.TAG_DATA_AGENDA_UPDATE, "", json);
                                        /******************** 修改待办事务统计待收款的状态  end *******************************/
                                    }else if(totalAmount.compareTo(new BigDecimal(0)) == -1){// 小于
                                        LOG.info(ErrorPactConsts.FINANCE_RECEIPTPAY_COSTAMOUNT_ERROR);
                                        status.setRollbackOnly();
                                        return false;
                                    }else if(totalAmount.compareTo(new BigDecimal(0)) == 1){// 大于
                                        financeReceiptPayDO.setCostState(2);// 部分收款
                                        financeReceiptPayDO.setId(realCollectDetail.getPid());
                                        flag = financeReceiptPayDao.updateCostState(financeReceiptPayDO);
                                        if(!(flag > 0)){
                                            status.setRollbackOnly();
                                            return false;
                                        }
                                    }
                                    return true;
                                }else{
                                    status.setRollbackOnly();
                                    return false;
                                }
                            }else{
                                status.setRollbackOnly();
                                return false;
                            }
                        }else{
                            status.setRollbackOnly();
                            return false;
                        }
                    } catch (DaoException e) {
                        status.setRollbackOnly();
                        LOG.error(ErrorPactConsts.CONFIRM_FINANCE_RECEIPTPAY_ERROR, e);
                        throw new PactManagerExcepion(ErrorPactConsts.CONFIRM_FINANCE_RECEIPTPAY_ERROR, e);
                    }
                }
            });
        } catch (Exception e) {
            LOG.error(ErrorPactConsts.CONFIRM_FINANCE_RECEIPTPAY_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.CONFIRM_FINANCE_RECEIPTPAY_ERROR, e);
        }
    }

    @Override
    public boolean realCollectReject(FinanceReceiptPayDO financeReceiptPayDO) {
        try {
            int flag = financeReceiptPayDao.updateCostState(financeReceiptPayDO);
            if(flag > 0){
                return true;
            }else{
                return false;
            }
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.REJECT_FINANCE_RECEIPTPAY_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.REJECT_FINANCE_RECEIPTPAY_ERROR, e);
        }
    }

    @Override
    public double getRealAmountByPid(Long pid) {
        try {
            return financeReceiptPayDao.getRealAmountByPid(pid);
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.GET_REAL_AMOUNT_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.GET_REAL_AMOUNT_ERROR, e);
        }
    }

    @Override
    public boolean answerExpendApply(FinanceReceiptPayDO financeReceiptPayDO) {
        try {
            Long id = financeReceiptPayDao.saveReceiptPay(financeReceiptPayDO);
            if(id > 0){
                CommonBelongerQuery belongerQuery = new CommonBelongerQuery();
                belongerQuery.setPactCode(financeReceiptPayDO.getPactCode());
                belongerQuery.setUserRole(StringConsts.BELONGER_DEAL);
                CommonBelongerVO commonBelongerVO = commonBelongerDao.getBelongerByParam(belongerQuery);
                /******************** 待办事务统计付款待审核      start *******************************/
                AgendaDO agendaDO = new AgendaDO();
                agendaDO.setServiceCode(String.valueOf(id));
                agendaDO.setType(StringConsts.STATISTIC_PENDING_PAYMENT_AUDITED);
                agendaDO.setIsDo(0);
                agendaDO.setPin(commonBelongerVO.getUserPin());
                agendaDO.setName(commonBelongerVO.getUserName());
                agendaDO.setDeptCode(commonBelongerVO.getDeptCode());
                agendaDO.setDeptName(commonBelongerVO.getDeptName());
                agendaDO.setDate(new Date());
                String json = JsonUtil.toJson(agendaDO);
                rocketmqClient.sendMessage(NormalConstant.TOPIC_BACKLOGSTATISTICS, NormalConstant.TAG_DATA_AGENDA_SAVE, "", json);
                /******************** 待办事务统计付款待审核      end *******************************/
                return true;
            }else{
                return false;
            }
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.FINANCE_PAY_APPLY_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.FINANCE_PAY_APPLY_ERROR, e);
        }
    }

    @Override
    public boolean answerExpendReview(final FinanceReceiptPayDO financeReceiptPayDO) {
        try {
            // 修改实支费用状态为 审核通过
            int flag = financeReceiptPayDao.updateCostState(financeReceiptPayDO);
            if(flag > 0){
                return true;
            }else{
                return false;
            }
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.FINANCE_PAY_REVIEW_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.FINANCE_PAY_REVIEW_ERROR, e);
        }
    }

    @Override
    public boolean answerExpendReject(FinanceReceiptPayDO financeReceiptPayDO) {
        try {
            // 修改实支费用状态为 驳回
            int flag = financeReceiptPayDao.updateCostState(financeReceiptPayDO);
            if(flag > 0){
                return true;
            }else{
                return false;
            }
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.FINANCE_PAY_REJECT_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.FINANCE_PAY_REJECT_ERROR, e);
        }
    }

    @Override
    public boolean answerExpendReceipt(final FinanceReceiptPayDO financeReceiptPayDO) {
        try {
            TransactionTemplate transactionTemplate =  new TransactionTemplate(transactionManager);
            return transactionTemplate.execute(new TransactionCallback<Boolean>() {
                @Override
                public Boolean doInTransaction(TransactionStatus status) {
                    try {
                        // 修改实支付款信息
                        int flag = financeReceiptPayDao.updateFinancePayById(financeReceiptPayDO);
                        if(flag > 0){
                            // 对收支凭证 增删改
                            List<FinanceReceiptPayVoucherDO> voucherList = financeReceiptPayDO.getFinanceReceiptPayVoucherList();
                            Map<String, Object> voucherMap = new HashMap<>();
                            voucherMap.put("receiptPayId", financeReceiptPayDO.getId());
                            voucherMap.put("voucherList", voucherList);
                            financeReceiptPayVoucherDao.removeVouchersByPid(voucherMap);
                            if(null != voucherList && voucherList.size() > 0){
                                financeReceiptPayVoucherDao.updateFinanceReceiptPayVouchers(voucherList);
                                List<FinanceReceiptPayVoucherDO> saveVoucherList = new ArrayList<>();
                                for (FinanceReceiptPayVoucherDO voucherDO : voucherList) {
                                    if(null == voucherDO.getId() || !(voucherDO.getId() > 0)){
                                        saveVoucherList.add(voucherDO);
                                    }
                                }
                                if(saveVoucherList.size() > 0){
                                    financeReceiptPayVoucherDao.saveFinanceReceiptPayVouchers(saveVoucherList);
                                }
                            }
                            // 实支 信息
                            FinanceReceiptPayVO realExpendDetail = financeReceiptPayDao.getRealExpendDetail(financeReceiptPayDO.getId());
                            if(null != realExpendDetail){
                                // 应支 信息
                                FinanceReceiptPayVO expendDetail = financeReceiptPayDao.getAnswerExpendDetail(realExpendDetail.getPid());
                                if(null != expendDetail){
                                    // 实支总和
                                    double sumAmount = financeReceiptPayDao.getRealAmountByPid(expendDetail.getId());
                                    // 未付 金额
                                    BigDecimal totalAmount = BigDecimal.valueOf(expendDetail.getCostAmount());
                                    // 减掉 实支 金额
                                    totalAmount = totalAmount.subtract(BigDecimal.valueOf(sumAmount));
                                    
                                    // 判断未付大小
                                    if(totalAmount.compareTo(new BigDecimal(0)) == 0){// 等于
                                        financeReceiptPayDO.setCostState(6);// 已付款
                                        financeReceiptPayDO.setId(realExpendDetail.getPid());
                                        flag = financeReceiptPayDao.updateCostState(financeReceiptPayDO);
                                        if(!(flag > 0)){
                                            status.setRollbackOnly();
                                            return false;
                                        }
                                    }else if(totalAmount.compareTo(new BigDecimal(0)) == 1){// 大于
                                        financeReceiptPayDO.setCostState(5);// 部分收款
                                        financeReceiptPayDO.setId(realExpendDetail.getPid());
                                        flag = financeReceiptPayDao.updateCostState(financeReceiptPayDO);
                                        if(!(flag > 0)){
                                            status.setRollbackOnly();
                                            return false;
                                        }
                                    }
                                    return true;
                                }else{
                                    status.setRollbackOnly();
                                    return false;
                                }
                            }else{
                                status.setRollbackOnly();
                                return false;
                            }
                        }else{
                            status.setRollbackOnly();
                            return false;
                        }
                    } catch (DaoException e) {
                        status.setRollbackOnly();
                        LOG.error(ErrorPactConsts.FINANCE_PAY_REVIEW_ERROR, e);
                        throw new PactManagerExcepion(ErrorPactConsts.FINANCE_PAY_REVIEW_ERROR, e);
                    }
                }
            });
        } catch (Exception e) {
            LOG.error(ErrorPactConsts.FINANCE_PAY_REVIEW_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.FINANCE_PAY_REVIEW_ERROR, e);
        }
    }

    @Override
    public boolean saveReceiptPay(FinanceReceiptPayDO financeReceiptPayDO) {
        try {
            Long id = financeReceiptPayDao.saveReceiptPay(financeReceiptPayDO);
            if(id > 0){
                /*---------添加应收、应支操作日志----start------*/
                Map<String, Object> map = new HashMap<String, Object>();
                if(0==financeReceiptPayDO.getType()){
                    map.put(NormalConstant.SERVICETYPE, StringConsts.ADD_RECEIVABLE);
                }else if(2==financeReceiptPayDO.getType()){
                    map.put(NormalConstant.SERVICETYPE, StringConsts.ADD_BRANCH);
                }
                map.put(NormalConstant.SERVICECODE, id);
                map.put(NormalConstant.OPERATETYPE, StringConsts.ADD);
                map.put(NormalConstant.OPERATEPIN, financeReceiptPayDO.getCreateName());
                map.put(NormalConstant.OPERATECONTENT, StringConsts.ADD_DATA);
                ApiResult<PersonalResult> result = personalRpcService.getPersonInterface((String)map.get(NormalConstant.OPERATEPIN));
                PersonalResult personalResult = result.getData();
                map.put(NormalConstant.OPERATENAME, personalResult.getEmployeeName());
                map.put(NormalConstant.OPERATEDEPT, personalResult.getDeptName());
                map.put(NormalConstant.OPERATETIME, new Date());
                String json = JsonUtil.toJson(map);
                rocketmqClient.sendMessage(NormalConstant.TOPIC_OPERATE, NormalConstant.TAG_OPERATE_SAVE, NormalConstant.KEY_OPERATE_PACT, json);
                /*---------添加应收、应支操作日志----end------*/
                return true;
            }else{
                return false;
            }
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.SAVE_FINANCE_PAY_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.SAVE_FINANCE_PAY_ERROR, e);
        }
    }
    
    @Override
	public List<FinanceReceiptPayVO> listFinanceReceiptPayDO(FinanceReceiptPayQuery query) {
		try {
			return financeReceiptPayDao.listFinanceReceiptPayDO(query);
		} catch (Exception e) {
			LOG.error(ErrorPactConsts.LIST_FINANCE_RECEIPTPAY_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.LIST_FINANCE_RECEIPTPAY_ERROR, e);
		}
		
	}
    
    @Override
    public boolean getPermissions(FinanceReceiptPayQuery query) {
        try {
            FinanceReceiptPayVO financeReceiptPayVO = financeReceiptPayDao.getPermissions(query);
            if(null == financeReceiptPayVO){
                return false;
            }
            return true;
        } catch (Exception e) {
            LOG.error(ErrorPactConsts.GET_FINANCE_RECEIPTPAY_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.GET_FINANCE_RECEIPTPAY_ERROR, e);
        }
    }
    
    @Override
    public List<FinanceReceiptPayVO> listFinanceReceiptPayByPactCode(FinanceReceiptPayQuery financeReceiptPayQuery) {
        try {
            return financeReceiptPayDao.listFinanceReceiptPayByPactCode(financeReceiptPayQuery);
        } catch (Exception e) {
            LOG.error(ErrorPactConsts.LIST_FINANCE_RECEIPTPAY_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.LIST_FINANCE_RECEIPTPAY_ERROR, e);
        }
    }
    
    @Override
    public List<FinanceReceiptPayVO> listReceiptPayByDate(FinanceReceiptPayQuery oldPactReceiptPayQuery) {
        try {
            return financeReceiptPayDao.listReceiptPayByDate(oldPactReceiptPayQuery);
        } catch (Exception e) {
            LOG.error(ErrorPactConsts.LIST_FINANCE_RECEIPTPAY_BYDATE_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.LIST_FINANCE_RECEIPTPAY_BYDATE_ERROR, e);
        }
    }
    
    @Override
    public Double countRecPayAmount(FinanceReceiptPayQuery query) {
        try {
            return financeReceiptPayDao.countRecPayAmount(query);
        } catch (Exception e) {
            LOG.error(ErrorPactConsts.COUNT_RECPAY_AMOUNT_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.COUNT_RECPAY_AMOUNT_ERROR, e);
        }
    }
    /******************get/set*********************/
    public FinanceReceiptPayDao getFinanceReceiptPayDao() {
        return financeReceiptPayDao;
    }
    public void setFinanceReceiptPayDao(FinanceReceiptPayDao financeReceiptPayDao) {
        this.financeReceiptPayDao = financeReceiptPayDao;
    }
    public PlatformTransactionManager getTransactionManager() {
        return transactionManager;
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public FinanceReceiptPayVoucherDao getFinanceReceiptPayVoucherDao() {
        return financeReceiptPayVoucherDao;
    }

    public void setFinanceReceiptPayVoucherDao(FinanceReceiptPayVoucherDao financeReceiptPayVoucherDao) {
        this.financeReceiptPayVoucherDao = financeReceiptPayVoucherDao;
    }

    public PersonalRpcService getPersonalRpcService() {
        return personalRpcService;
    }

    public void setPersonalRpcService(PersonalRpcService personalRpcService) {
        this.personalRpcService = personalRpcService;
    }

    public RocketmqClient getRocketmqClient() {
        return rocketmqClient;
    }

    public void setRocketmqClient(RocketmqClient rocketmqClient) {
        this.rocketmqClient = rocketmqClient;
    }

    public CommonBelongerDao getCommonBelongerDao() {
        return commonBelongerDao;
    }

    public void setCommonBelongerDao(CommonBelongerDao commonBelongerDao) {
        this.commonBelongerDao = commonBelongerDao;
    }
}
