package com.young.pact.manager.financereceiptpay;

import java.util.List;

import com.young.common.page.PageBean;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayDO;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayQuery;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayVO;

/**
 * @描述 : 收支
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月6日 下午9:49:05
 */
public interface FinanceReceiptPayManager {
    
    /**
    * @Title: getPayeeByRentPactCode
    * @Description: TODO( 根据托出合同编码获取收款人信息)
    * @author GuoXiaoPeng
    * @param rentPactCode 托出合同编码
    * @return 收款人信息
    * @throws
     */
    FinanceReceiptPayVO getPayeeByRentPactCode(FinanceReceiptPayQuery query);
    /**
     * 
    * @Title: listAllFinanceReceiptPay
    * @Description: TODO( 收支管理列表-全部)
    * @author HeZeMin
    * @param query  查询条件
    * @return   返回收支列表
     */
    PageBean<FinanceReceiptPayVO> listAllFinanceReceiptPay(FinanceReceiptPayQuery query);
    /**
     * 
    * @Title: listReceivFinanceReceiptPay
    * @Description: TODO( 收支管理列表-应收、应支)
    * @author HeZeMin
    * @param query  查询条件
    * @return   返回收支列表
     */
    PageBean<FinanceReceiptPayVO> listReceivFinanceReceiptPay(FinanceReceiptPayQuery query);
    /**
     * 
    * @Title: listNetFinanceReceiptPay
    * @Description: TODO( 收支管理列表-实收、应支)
    * @author HeZeMin
    * @param query  查询条件
    * @return   返回收支列表
     */
    PageBean<FinanceReceiptPayVO> listNetFinanceReceiptPay(FinanceReceiptPayQuery query);
    /**
     * 
    * @Title: getAnswerCollectDetail
    * @Description: TODO( 根据应收id查询应收详情)
    * @author HeZeMin
    * @param id 应收id
    * @return   返回应收详情
     */
    FinanceReceiptPayVO getAnswerCollectDetail(Long id);
    /**
     * 
    * @Title: getRealCollectDetail
    * @Description: TODO( 根据实收id查询实收详情)
    * @author HeZeMin
    * @param id 实收id
    * @return   返回实收详情
     */
    FinanceReceiptPayVO getRealCollectDetail(Long id);
    /**
     * 
    * @Title: listRealCollectByPid
    * @Description: TODO( 根据应收id查询实收集合)
    * @author HeZeMin
    * @param pid    应收id
    * @return   返回实收集合
     */
    List<FinanceReceiptPayVO> listRealCollectByPid(Long pid);
    /**
     * 
    * @Title: getAnswerExpendDetail
    * @Description: TODO( 根据应支id查询应支详情)
    * @author HeZeMin
    * @param id 应支id
    * @return   返回应支详情
     */
    FinanceReceiptPayVO getAnswerExpendDetail(Long id);
    /**
     * 
    * @Title: getRealExpendDetail
    * @Description: TODO( 根据实支id查询实支详情)
    * @author HeZeMin
    * @param id 实支id
    * @return   返回实支详情
     */
    FinanceReceiptPayVO getRealExpendDetail(Long id);
    /**
     * 
    * @Title: listRealExpendByPid
    * @Description: TODO( 根据应支id查询实支集合)
    * @author HeZeMin
    * @param pid 应支id
    * @return   返回实支集合
     */
    List<FinanceReceiptPayVO> listRealExpendByPid(Long pid);
    /**
     * 
    * @Title: listReceivFinanceReceiptPay
    * @Description: TODO( 合同详情-应收、应支)
    * @author HeZeMin
    * @param query  查询条件
    * @return   返回收支列表
     */
    PageBean<FinanceReceiptPayVO> listReceivReceiptPay(FinanceReceiptPayQuery query);
    /**
     * 
    * @Title: listNetFinanceReceiptPay
    * @Description: TODO( 合同详情-全部、实收、应支)
    * @author HeZeMin
    * @param query  查询条件
    * @return   返回收支列表
     */
    PageBean<FinanceReceiptPayVO> listNetReceiptPay(FinanceReceiptPayQuery query);
    /**
     * 
    * @Title: getAnswerCollectDetail
    * @Description: TODO( 合同详情-根据应收id查询应收详情)
    * @author HeZeMin
    * @param id 应收id
    * @return   返回应收详情
     */
    FinanceReceiptPayVO getPactAnswerCollectDetail(Long id);
    /**
     * 
    * @Title: getRealCollectDetail
    * @Description: TODO( 合同详情-根据实收id查询实收详情)
    * @author HeZeMin
    * @param id 实收id
    * @return   返回实收详情
     */
    FinanceReceiptPayVO getPactRealCollectDetail(Long id);
    /**
     * 
    * @Title: getAnswerExpendDetail
    * @Description: TODO( 合同详情-根据应支id查询应支详情)
    * @author HeZeMin
    * @param id 应支id
    * @return   返回应支详情
     */
    FinanceReceiptPayVO getPactAnswerExpendDetail(Long id);
    /**
     * 
    * @Title: getRealExpendDetail
    * @Description: TODO( 合同详情-根据实支id查询实支详情)
    * @author HeZeMin
    * @param id 实支id
    * @return   返回实支详情
     */
    FinanceReceiptPayVO getPactRealExpendDetail(Long id);
    /**
     * 
    * @Title: answerCollectReceipt
    * @Description: TODO( 应收收款)
    * @author HeZeMin
    * @param financeReceiptPayDO    收款信息
    * @return   返回收款结果
     */
    boolean answerCollectReceipt(FinanceReceiptPayDO financeReceiptPayDO);
    /**
     * 
    * @Title: realCollectConfirm
    * @Description: TODO( 确认收款)
    * @author HeZeMin
    * @param financeReceiptPayDO    确认信息
    * @return   返回确认结果
     */
    boolean realCollectConfirm(FinanceReceiptPayDO financeReceiptPayDO);
    /**
     * 
    * @Title: realCollectReject
    * @Description: TODO( 驳回收款)
    * @author HeZeMin
    * @param financeReceiptPayDO    驳回信息
    * @return   返回驳回结果
     */
    boolean realCollectReject(FinanceReceiptPayDO financeReceiptPayDO);
    /**
     * 
    * @Title: getRealAmountByPid
    * @Description: TODO( 根据应收、应支id 查询 实收、实支总和)
    * @author HeZeMin
    * @param pid    应收、应支id
    * @return   返回实收、实支总和
     */
    double getRealAmountByPid(Long pid);
    /**
     * 
    * @Title: answerExpendApply
    * @Description: TODO( 应支付款申请)
    * @author HeZeMin
    * @param financeReceiptPayDO    付款申请信息
    * @return   返回申请结果
     */
    boolean answerExpendApply(FinanceReceiptPayDO financeReceiptPayDO);
    /**
     * 
    * @Title: answerExpendReview
    * @Description: TODO( 实支审核通过)
    * @author HeZeMin
    * @param financeReceiptPayDO    审核信息
    * @return   返回审核结果
     */
    boolean answerExpendReview(FinanceReceiptPayDO financeReceiptPayDO);
    /**
     * 
    * @Title: answerExpendReject
    * @Description: TODO( 实支驳回)
    * @author HeZeMin
    * @param financeReceiptPayDO    驳回信息
    * @return   返回驳回结果
     */
    boolean answerExpendReject(FinanceReceiptPayDO financeReceiptPayDO);
    /**
     * 
    * @Title: answerExpendReceipt
    * @Description: TODO( 实支付款)
    * @author HeZeMin
    * @param financeReceiptPayDO    付款信息
    * @return   付款结果
     */
    boolean answerExpendReceipt(FinanceReceiptPayDO financeReceiptPayDO);
    /**
     * 
    * @Title: saveReceiptPay
    * @Description: TODO( 添加应收、应支)
    * @author HeZeMin
    * @param financeReceiptPayDO    收支信息
    * @return   返回添加结果
     */
    boolean saveReceiptPay(FinanceReceiptPayDO financeReceiptPayDO);
    
    /**
     * 
    * @Title: listFinanceReceiptPayDO
    * @Description: 根据条件查询收支集合
    * @author LiuYuChi
    * @param query
    * @return
    * @throws
     */
    List<FinanceReceiptPayVO> listFinanceReceiptPayDO (FinanceReceiptPayQuery query);
    /**
    * @Title: getPermissions
    * @Description: TODO( 查看登录人有没有某个收支 的权限)
    * @author GuoXiaoPeng
    * @param query 收支id
    * @return 有权限返回true否则false
    * @throws 异常
     */
    boolean getPermissions(FinanceReceiptPayQuery query);
    /**
    * @Title: listFinanceReceiptPayByPactCode
    * @Description: TODO( 根据合同编码查询收支集合 )
    * @author GuoXiaoPeng
    * @param financeReceiptPayQuery 查询条件
    * @return  收支集合
    * @throws 异常
     */
    List<FinanceReceiptPayVO> listFinanceReceiptPayByPactCode(FinanceReceiptPayQuery financeReceiptPayQuery);
    /**
     * @Title: listReceiptPayByDate
     * @Description: TODO( 查询某个托出合同的收支，并且收支时间大于转租时间的收支集合)
     * @author GuoXiaoPeng
     * @param financeReceiptPayQuery 合同编码+收支时间
     * @return 收支集合
     * @throws 异常
      */
    List<FinanceReceiptPayVO> listReceiptPayByDate(FinanceReceiptPayQuery oldPactReceiptPayQuery);
    /**
     * 
    * @Title: countRecPayAmount
    * @Description: TODO( 根据条件查询收支费用 )
    * @author GuoXiaoPeng
    * @param query 查询条件
    * @return 费用
    * @throws
     */
    Double countRecPayAmount(FinanceReceiptPayQuery query);
}
