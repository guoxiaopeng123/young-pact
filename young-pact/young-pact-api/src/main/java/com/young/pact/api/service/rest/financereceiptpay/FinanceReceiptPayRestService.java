package com.young.pact.api.service.rest.financereceiptpay;

import com.young.common.domain.ApiResult;
import com.young.common.page.PageBean;
import com.young.pact.api.domain.param.rest.financereceiptpay.FinanceReceiptPayParam;
import com.young.pact.api.domain.param.rest.financereceiptpay.FinanceReceiptPayQueryParam;
import com.young.pact.api.domain.result.rest.financereceiptpay.FinanceReceiptPayListResult;
import com.young.pact.api.domain.result.rest.financereceiptpay.FinanceReceiptPayResult;
import com.young.pact.api.domain.result.rest.financereceiptpay.RealReceiptPayResult;

/**
 * 
* @ClassName: FinanceReceiptPayRestService
* @Description: TODO( 收支)
* @author HeZeMin
* @date 2018年8月7日 下午6:10:09
*
 */
public interface FinanceReceiptPayRestService {
    /**
     * 
    * @Title: listAllFinanceReceiptPay 
    * @Description: TODO(收支管理-全部 )
    * @author HeZeMin
    * @param param  查询条件
    * @return   返回收支列表
     */
    ApiResult<PageBean<FinanceReceiptPayListResult>> listAllFinanceReceiptPay(FinanceReceiptPayQueryParam param);
    /**
     * 
     * @Title: listReceivFinanceReceiptPay
     * @Description: TODO( 收支管理-应收、应支)
     * @author HeZeMin
     * @param param  查询条件
     * @return   返回收支列表
     */
    ApiResult<PageBean<FinanceReceiptPayListResult>> listReceivFinanceReceiptPay(FinanceReceiptPayQueryParam param);
    /**
     * 
    * @Title: listNetFinanceReceiptPay
    * @Description: TODO( 收支管理-实收、实支)
    * @author HeZeMin
    * @param param   查询条件
    * @return   返回收支列表
     */
    ApiResult<PageBean<FinanceReceiptPayListResult>> listNetFinanceReceiptPay(FinanceReceiptPayQueryParam param);
    /**
     * 
    * @Title: getAnswerCollectDetail
    * @Description: TODO( 根据应收id查询应收详情)
    * @author HeZeMin
    * @param param 应收id
    * @return   返回应收详情
     */
    ApiResult<FinanceReceiptPayResult> getAnswerCollectDetail(FinanceReceiptPayQueryParam param);
    /**
     * 
    * @Title: getRealCollectDetail
    * @Description: TODO( 根据实收id查询实收详情)
    * @author HeZeMin
    * @param param 实收id
    * @return   返回实收详情
     */
    ApiResult<RealReceiptPayResult> getRealCollectDetail(FinanceReceiptPayQueryParam param);
    /**
     * 
    * @Title: getAnswerExpendDetail
    * @Description: TODO( 根据应支id查询应支详情)
    * @author HeZeMin
    * @param param 应支id
    * @return   返回应支详情
     */
    ApiResult<FinanceReceiptPayResult> getAnswerExpendDetail(FinanceReceiptPayQueryParam param);
    /**
     * 
    * @Title: getRealExpendDetail
    * @Description: TODO( 根据实支id查询实支详情)
    * @author HeZeMin
    * @param param 实支id
    * @return   返回实支详情
     */
    ApiResult<RealReceiptPayResult> getRealExpendDetail(FinanceReceiptPayQueryParam param);
    /**
     * 
     * @Title: listReceivFinanceReceiptPay
     * @Description: TODO( 合同详情-应收、应支)
     * @author HeZeMin
     * @param param  查询条件
     * @return   返回收支列表
     */
    ApiResult<PageBean<FinanceReceiptPayListResult>> listReceivReceiptPay(FinanceReceiptPayQueryParam param);
    /**
     * 
    * @Title: listNetFinanceReceiptPay
    * @Description: TODO( 合同详情-实收、实支)
    * @author HeZeMin
    * @param param   查询条件
    * @return   返回收支列表
     */
    ApiResult<PageBean<FinanceReceiptPayListResult>> listNetReceiptPay(FinanceReceiptPayQueryParam param);
    /**
     * 
    * @Title: getAnswerCollectDetail
    * @Description: TODO( 合同详情-根据应收id查询应收详情)
    * @author HeZeMin
    * @param param 应收id
    * @return   返回应收详情
     */
    ApiResult<FinanceReceiptPayResult> getPactAnswerCollectDetail(FinanceReceiptPayQueryParam param);
    /**
     * 
    * @Title: getRealCollectDetail
    * @Description: TODO( 合同详情-根据实收id查询实收详情)
    * @author HeZeMin
    * @param param 实收id
    * @return   返回实收详情
     */
    ApiResult<RealReceiptPayResult> getPactRealCollectDetail(FinanceReceiptPayQueryParam param);
    /**
     * 
    * @Title: getAnswerExpendDetail
    * @Description: TODO( 合同详情-根据应支id查询应支详情)
    * @author HeZeMin
    * @param param 应支id
    * @return   返回应支详情
     */
    ApiResult<FinanceReceiptPayResult> getPactAnswerExpendDetail(FinanceReceiptPayQueryParam param);
    /**
     * 
    * @Title: getRealExpendDetail
    * @Description: TODO( 合同详情-根据实支id查询实支详情)
    * @author HeZeMin
    * @param param 实支id
    * @return   返回实支详情
     */
    ApiResult<RealReceiptPayResult> getPactRealExpendDetail(FinanceReceiptPayQueryParam param);
    /**
     * 
    * @Title: answerCollectReceipt
    * @Description: TODO( 应收收款)
    * @author HeZeMin
    * @param param  收款信息
    * @return   返回结果
     */
    ApiResult<Boolean> answerCollectReceipt(FinanceReceiptPayParam param);
    /**
     * 
    * @Title: realCollectConfirm
    * @Description: TODO( 实收确认收款)
    * @author HeZeMin
    * @param param 实收id
    * @return   返回确认结果
     */
    ApiResult<Boolean> realCollectConfirm(FinanceReceiptPayParam param);
    /**
     * 
    * @Title: realCollectReject
    * @Description: TODO( 实收驳回收款)
    * @author HeZeMin
    * @param param  驳回原因
    * @return   返回驳回结果
     */
    ApiResult<Boolean> realCollectReject(FinanceReceiptPayParam param);
    /**
     * 
    * @Title: answerExpendApply
    * @Description: TODO( 应支申请付款)
    * @author HeZeMin
    * @param param  实支信息
    * @return   返回申请结果
     */
    ApiResult<Boolean> answerExpendApply(FinanceReceiptPayParam param);
    /**
     * 
    * @Title: answerExpendReview
    * @Description: TODO( 实支审核通过)
    * @author HeZeMin
    * @param param  审核信息
    * @return   返回审核结果
     */
    ApiResult<Boolean> answerExpendReview(FinanceReceiptPayParam param);
    /**
     * 
    * @Title: answerExpendReject
    * @Description: TODO( 实支驳回)
    * @author HeZeMin
    * @param param  驳回信息
    * @return   驳回结果
     */
    ApiResult<Boolean> answerExpendReject(FinanceReceiptPayParam param);
    /**
     * 
    * @Title: answerExpendReceipt
    * @Description: TODO( 实支付款)
    * @author HeZeMin
    * @param param  付款信息
    * @return   返回付款结果
     */
    ApiResult<Boolean> answerExpendReceipt(FinanceReceiptPayParam param);
    /**
     * 
    * @Title: saveReceiptPay
    * @Description: TODO( 添加应收、应支)
    * @author HeZeMin
    * @param param  收支信息
    * @return   返回添加结果
     */
    ApiResult<Boolean> saveReceiptPay(FinanceReceiptPayParam param);
    /**
     * 
    * @Title: updateAnswerExpendReceipt
    * @Description: TODO( 修改实支付款信息 )
    * @author GuoXiaoPeng
    * @param param  实支付款信息
    * @return 返回修改结果
    * @throws 异常
     */
    ApiResult<Boolean> updateAnswerExpendReceipt(FinanceReceiptPayParam param);
}
