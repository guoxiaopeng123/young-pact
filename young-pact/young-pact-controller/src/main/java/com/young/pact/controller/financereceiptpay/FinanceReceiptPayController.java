package com.young.pact.controller.financereceiptpay;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tools.common.springmvc.base.BaseController;
import com.young.common.annotation.PermissionsCode;
import com.young.common.domain.ApiResult;
import com.young.common.page.PageBean;
import com.young.pact.api.domain.param.rest.financereceiptpay.FinanceReceiptPayParam;
import com.young.pact.api.domain.param.rest.financereceiptpay.FinanceReceiptPayQueryParam;
import com.young.pact.api.domain.result.rest.financereceiptpay.FinanceReceiptPayListResult;
import com.young.pact.api.domain.result.rest.financereceiptpay.FinanceReceiptPayResult;
import com.young.pact.api.domain.result.rest.financereceiptpay.RealReceiptPayResult;
import com.young.pact.api.service.rest.financereceiptpay.FinanceReceiptPayRestService;
import com.young.pact.common.constant.LoginConsts;
/**
 * 
* @ClassName: FinanceReceiptPayController
* @Description: TODO( 收支)
* @author HeZeMin
* @date 2018年8月7日 下午6:08:10
*
 */
@RestController
@RequestMapping(value = "/financereceiptpay", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class FinanceReceiptPayController extends BaseController {
    /*********************声明区**********************/
    private static final Log LOG = LogFactory.getLog(FinanceReceiptPayController.class);
    private FinanceReceiptPayRestService financeReceiptPayRestService;
    /*********************方法区**********************/
    /**
     * 
    * @Title: listAllFinanceReceiptPay
    * @Description: TODO(收支管理-全部 )
    * @author HeZeMin
    * @param param  查询条件
    * @return   返回收支列表
     */
    @PermissionsCode(code = "rest_receiptpay_page")
    @RequestMapping(value = "/listAllFinanceReceiptPay", method = RequestMethod.POST)
    public ApiResult<PageBean<FinanceReceiptPayListResult>> listAllFinanceReceiptPay(HttpServletRequest request, @RequestBody FinanceReceiptPayQueryParam param){
        try {
            if(null != param){
                String pin = (String) request.getAttribute(LoginConsts.PIN);
                String ip =super.getRemoteIPs(request);
                Integer scope = (Integer) request.getAttribute(LoginConsts.SCOPE);
                param.setCreateName(pin);
                param.setIp(ip);
                param.setModifiedName(pin);
                param.setScope(scope);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return financeReceiptPayRestService.listAllFinanceReceiptPay(param);
    }
    /**
     * 
     * @Title: listReceivFinanceReceiptPay
     * @Description: TODO( 收支管理-应收、应支)
     * @author HeZeMin
     * @param param  查询条件
     * @return   返回收支列表
     */
    @PermissionsCode(code = "rest_receiptpay_page")
    @RequestMapping(value = "/listReceivFinanceReceiptPay", method = RequestMethod.POST)
    public ApiResult<PageBean<FinanceReceiptPayListResult>> listReceivFinanceReceiptPay(HttpServletRequest request, @RequestBody FinanceReceiptPayQueryParam param){
        try {
            if(null != param){
                
                String pin = (String) request.getAttribute(LoginConsts.PIN);
                String ip =super.getRemoteIPs(request);
                Integer scope = (Integer) request.getAttribute(LoginConsts.SCOPE);
                param.setCreateName(pin);
                param.setIp(ip);
                param.setScope(scope);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return financeReceiptPayRestService.listReceivFinanceReceiptPay(param);
    }
    /**
     * 
    * @Title: listNetFinanceReceiptPay
    * @Description: TODO( 收支管理-实收、实支)
    * @author HeZeMin
    * @param param   查询条件
    * @return   返回收支列表
     */
    @PermissionsCode(code = "rest_receiptpay_page")
    @RequestMapping(value = "/listNetFinanceReceiptPay", method = RequestMethod.POST)
    public ApiResult<PageBean<FinanceReceiptPayListResult>> listNetFinanceReceiptPay(HttpServletRequest request, @RequestBody FinanceReceiptPayQueryParam param){
        try {
            if(null != param){
                String pin = (String) request.getAttribute(LoginConsts.PIN);
                String ip =super.getRemoteIPs(request);
                Integer scope = (Integer) request.getAttribute(LoginConsts.SCOPE);
                param.setCreateName(pin);
                param.setIp(ip);
                param.setScope(scope);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return financeReceiptPayRestService.listNetFinanceReceiptPay(param);
    }
    /**
     * 
    * @Title: getAnswerCollectDetail
    * @Description: TODO( 根据应收id查询应收详情)
    * @author HeZeMin
    * @param id 应收id
    * @return   返回应收详情
     */
    @PermissionsCode(code = "rest_receiptpay_receivable_detail")
    @RequestMapping(value = "/getAnswerCollectDetail/{id}", method = RequestMethod.GET)
    public ApiResult<FinanceReceiptPayResult> getAnswerCollectDetail(HttpServletRequest request, @PathVariable Long id){
        String pin = (String) request.getAttribute(LoginConsts.PIN);
        String ip = super.getRemoteIPs(request);
        Integer scope = (Integer) request.getAttribute(LoginConsts.SCOPE);
        FinanceReceiptPayQueryParam param = new FinanceReceiptPayQueryParam();
        param.setCreateName(pin);
        param.setIp(ip);
        param.setScope(scope);
        param.setId(id);
        return financeReceiptPayRestService.getAnswerCollectDetail(param);
    }
    /**
     * 
    * @Title: getRealCollectDetail
    * @Description: TODO( 根据实收id查询实收详情)
    * @author HeZeMin
    * @param id 实收id
    * @return   返回实收详情
     */
    @PermissionsCode(code = "rest_receiptpay_netreceipts_detail")
    @RequestMapping(value = "/getRealCollectDetail/{id}", method = RequestMethod.GET)
    public ApiResult<RealReceiptPayResult> getRealCollectDetail(HttpServletRequest request, @PathVariable Long id){
        Integer scope = (Integer) request.getAttribute(LoginConsts.SCOPE);
        String pin = (String) request.getAttribute(LoginConsts.PIN);
        String ip = super.getRemoteIPs(request);
        FinanceReceiptPayQueryParam param = new FinanceReceiptPayQueryParam();
        param.setCreateName(pin);
        param.setIp(ip);
        param.setScope(scope);
        param.setId(id);
        return financeReceiptPayRestService.getRealCollectDetail(param);
    }
    /**
     * 
    * @Title: getAnswerExpendDetail
    * @Description: TODO( 根据应支id查询应支详情)
    * @author HeZeMin
    * @param id 应支id
    * @return   返回应支详情
     */
    @PermissionsCode(code = "rest_receiptpay_branch_detail")
    @RequestMapping(value = "/getAnswerExpendDetail/{id}", method = RequestMethod.GET)
    public ApiResult<FinanceReceiptPayResult> getAnswerExpendDetail(HttpServletRequest request, @PathVariable Long id){
        Integer scope = (Integer) request.getAttribute(LoginConsts.SCOPE);
        String pin = (String) request.getAttribute(LoginConsts.PIN);
        String ip = super.getRemoteIPs(request);
        FinanceReceiptPayQueryParam param = new FinanceReceiptPayQueryParam();
        param.setCreateName(pin);
        param.setIp(ip);
        param.setScope(scope);
        param.setId(id);
        return financeReceiptPayRestService.getAnswerExpendDetail(param);
    }
    /**
     * 
    * @Title: getRealExpendDetail
    * @Description: TODO( 根据实支id查询实支详情)
    * @author HeZeMin
    * @param id 实支id
    * @return   返回实支详情
     */
    @PermissionsCode(code = "rest_receiptpay_real_branch_detail")
    @RequestMapping(value = "/getRealExpendDetail/{id}", method = RequestMethod.GET)
    public ApiResult<RealReceiptPayResult> getRealExpendDetail(HttpServletRequest request, @PathVariable Long id){
        Integer scope = (Integer) request.getAttribute(LoginConsts.SCOPE);
        String pin = (String) request.getAttribute(LoginConsts.PIN);
        String ip = super.getRemoteIPs(request);
        FinanceReceiptPayQueryParam param = new FinanceReceiptPayQueryParam();
        param.setCreateName(pin);
        param.setIp(ip);
        param.setScope(scope);
        param.setId(id);
        return financeReceiptPayRestService.getRealExpendDetail(param);
    }
    
    /**
     * 
     * @Title: listReceivFinanceReceiptPay
     * @Description: TODO( 合同详情-应收、应支)
     * @author HeZeMin
     * @param param  查询条件
     * @return   返回收支列表
     */
    @PermissionsCode(code = "rest_pact_receiptpay_page")
    @RequestMapping(value = "/listReceivReceiptPay", method = RequestMethod.POST)
    public ApiResult<PageBean<FinanceReceiptPayListResult>> listReceivReceiptPay(HttpServletRequest request, @RequestBody FinanceReceiptPayQueryParam param){
        try {
            if(null != param){
                String pin = (String) request.getAttribute(LoginConsts.PIN);
                String ip =super.getRemoteIPs(request);
                Integer scope = (Integer) request.getAttribute(LoginConsts.SCOPE);
                param.setCreateName(pin);
                param.setIp(ip);
                param.setScope(scope);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return financeReceiptPayRestService.listReceivReceiptPay(param);
    }
    /**
     * 
    * @Title: listNetFinanceReceiptPay
    * @Description: TODO( 合同详情-全部、实收、实支)
    * @author HeZeMin
    * @param param   查询条件
    * @return   返回收支列表
     */
    @PermissionsCode(code = "rest_pact_receiptpay_page")
    @RequestMapping(value = "/listNetReceiptPay", method = RequestMethod.POST)
    public ApiResult<PageBean<FinanceReceiptPayListResult>> listNetReceiptPay(HttpServletRequest request, @RequestBody FinanceReceiptPayQueryParam param){
        try {
            if(null != param){
              String pin = (String) request.getAttribute(LoginConsts.PIN);
              String ip =super.getRemoteIPs(request);
              Integer scope = (Integer) request.getAttribute(LoginConsts.SCOPE);
              scope = 0;
              param.setCreateName(pin);
              param.setIp(ip);
              param.setScope(scope);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return financeReceiptPayRestService.listNetReceiptPay(param);
    }
    /**
     * 
    * @Title: getAnswerCollectDetail
    * @Description: TODO( 合同详情-根据应收id查询应收详情)
    * @author HeZeMin
    * @param id 应收id
    * @return   返回应收详情
     */
    @PermissionsCode(code = "rest_pact_receiptpay_receivable_detail")
    @RequestMapping(value = "/getPactAnswerCollectDetail/{id}", method = RequestMethod.GET)
    public ApiResult<FinanceReceiptPayResult> getPactAnswerCollectDetail(HttpServletRequest request, @PathVariable Long id){
        Integer scope = (Integer) request.getAttribute(LoginConsts.SCOPE);
        String pin = (String) request.getAttribute(LoginConsts.PIN);
        String ip =super.getRemoteIPs(request);
        FinanceReceiptPayQueryParam param = new FinanceReceiptPayQueryParam();
        param.setCreateName(pin);
        param.setIp(ip);
        param.setScope(scope);
        param.setId(id);
        return financeReceiptPayRestService.getPactAnswerCollectDetail(param);
    }
    /**
     * 
    * @Title: getRealCollectDetail
    * @Description: TODO( 合同详情-根据实收id查询实收详情)
    * @author HeZeMin
    * @param id 实收id
    * @return   返回实收详情
     */
    @PermissionsCode(code = "rest_pact_receiptpay_real_collect_detail")
    @RequestMapping(value = "/getPactRealCollectDetail/{id}", method = RequestMethod.GET)
    public ApiResult<RealReceiptPayResult> getPactRealCollectDetail(HttpServletRequest request, @PathVariable Long id){
        String pin = (String) request.getAttribute(LoginConsts.PIN);
        String ip =super.getRemoteIPs(request);
        Integer scope = (Integer) request.getAttribute(LoginConsts.SCOPE);
        FinanceReceiptPayQueryParam param = new FinanceReceiptPayQueryParam();
        param.setCreateName(pin);
        param.setIp(ip);
        param.setScope(scope);
        param.setId(id);
        return financeReceiptPayRestService.getPactRealCollectDetail(param);
    }
    /**
     * 
    * @Title: getAnswerExpendDetail
    * @Description: TODO( 合同详情-根据应支id查询应支详情)
    * @author HeZeMin
    * @param id 应支id
    * @return   返回应支详情
     */
    @PermissionsCode(code = "rest_pact_receiptpay_branch_detail")
    @RequestMapping(value = "/getPactAnswerExpendDetail/{id}", method = RequestMethod.GET)
    public ApiResult<FinanceReceiptPayResult> getPactAnswerExpendDetail(HttpServletRequest request, @PathVariable Long id){
        Integer scope = (Integer) request.getAttribute(LoginConsts.SCOPE);
        String pin = (String) request.getAttribute(LoginConsts.PIN);
        String ip =super.getRemoteIPs(request);
        FinanceReceiptPayQueryParam param = new FinanceReceiptPayQueryParam();
        param.setCreateName(pin);
        param.setIp(ip);
        param.setScope(scope);
        param.setId(id);
        return financeReceiptPayRestService.getPactAnswerExpendDetail(param);
    }
    /**
     * 
    * @Title: getRealExpendDetail
    * @Description: TODO( 合同详情-根据实支id查询实支详情)
    * @author HeZeMin
    * @param id 实支id
    * @return   返回实支详情
     */
    @PermissionsCode(code = "rest_pact_receiptpay_real_expend_detail")
    @RequestMapping(value = "/getPactRealExpendDetail/{id}", method = RequestMethod.GET)
    public ApiResult<RealReceiptPayResult> getPactRealExpendDetail(HttpServletRequest request, @PathVariable Long id){
        String pin = (String) request.getAttribute(LoginConsts.PIN);
        String ip =super.getRemoteIPs(request);
        Integer scope = (Integer) request.getAttribute(LoginConsts.SCOPE);
        FinanceReceiptPayQueryParam param = new FinanceReceiptPayQueryParam();
        param.setCreateName(pin);
        param.setIp(ip);
        param.setScope(scope);
        param.setId(id);
        return financeReceiptPayRestService.getPactRealExpendDetail(param);
    }
    
    /**
     * 
    * @Title: answerCollectReceipt
    * @Description: TODO( 应收收款)
    * @author HeZeMin
    * @param param  收款信息
    * @return   返回结果
     */
    @PermissionsCode(code = "rest_receiptpay_receivable_receivables")
    @RequestMapping(value = "/answerCollectReceipt", method = RequestMethod.POST)
    public ApiResult<Boolean> answerCollectReceipt(HttpServletRequest request, @RequestBody FinanceReceiptPayParam param){
        try {
            if(null != param){
              String pin = (String) request.getAttribute(LoginConsts.PIN);
              String ip =super.getRemoteIPs(request);
              param.setCreateName(pin);
              param.setIp(ip);
              param.setModifiedName(pin);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return financeReceiptPayRestService.answerCollectReceipt(param);
    }
    /**
     * 
    * @Title: realCollectConfirm
    * @Description: TODO( 实收确认收款)
    * @author HeZeMin
    * @param param 实收id
    * @return   返回确认结果
     */
    @PermissionsCode(code = "rest_receiptpay_confirmed_receipt")
    @RequestMapping(value = "/realCollectConfirm", method = RequestMethod.POST)
    public ApiResult<Boolean> realCollectConfirm(HttpServletRequest request, @RequestBody FinanceReceiptPayParam param){
        try {
            if(null != param){
              String pin = (String) request.getAttribute(LoginConsts.PIN);
              String ip =super.getRemoteIPs(request);
              param.setCreateName(pin);
              param.setIp(ip);
              param.setModifiedName(pin);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return financeReceiptPayRestService.realCollectConfirm(param);
    }
    /**
     * 
    * @Title: realCollectReject
    * @Description: TODO( 实收驳回收款)
    * @author HeZeMin
    * @param param  驳回原因
    * @return   返回驳回结果
     */
    @PermissionsCode(code = "rest_receiptpay_rejected_receipt")
    @RequestMapping(value = "/realCollectReject", method = RequestMethod.POST)
    public ApiResult<Boolean> realCollectReject(HttpServletRequest request, @RequestBody FinanceReceiptPayParam param){
        try {
            if(null != param){
              String pin = (String) request.getAttribute(LoginConsts.PIN);
              String ip =super.getRemoteIPs(request);
              param.setCreateName(pin);
              param.setIp(ip);
              param.setModifiedName(pin);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return financeReceiptPayRestService.realCollectReject(param);
    }
    
    /**
     * 
    * @Title: answerExpendApply
    * @Description: TODO( 应支申请付款)
    * @author HeZeMin
    * @param param  实支信息
    * @return   返回申请结果
     */
    @PermissionsCode(code = "rest_receiptpay_application_payment")
    @RequestMapping(value = "/answerExpendApply", method = RequestMethod.POST)
    public ApiResult<Boolean> answerExpendApply(HttpServletRequest request, @RequestBody FinanceReceiptPayParam param){
        try {
            if(null != param){
                
              String pin = (String) request.getAttribute(LoginConsts.PIN);
              String ip =super.getRemoteIPs(request);
              param.setCreateName(pin);
              param.setIp(ip);
              param.setModifiedName(pin);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return financeReceiptPayRestService.answerExpendApply(param);
    }
    /**
     * 
    * @Title: answerExpendReview
    * @Description: TODO( 实支审核通过)
    * @author HeZeMin
    * @param param  审核信息
    * @return   返回审核结果
     */
    @PermissionsCode(code = "rest_receiptpay_real_branch_passed")
    @RequestMapping(value = "/answerExpendReview", method = RequestMethod.POST)
    public ApiResult<Boolean> answerExpendReview(HttpServletRequest request, @RequestBody FinanceReceiptPayParam param){
        try {
            if(null != param){
                
              String pin = (String) request.getAttribute(LoginConsts.PIN);
              String ip =super.getRemoteIPs(request);
              param.setCreateName(pin);
              param.setIp(ip);
              param.setModifiedName(pin);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return financeReceiptPayRestService.answerExpendReview(param);
    }
    /**
     * 
    * @Title: answerExpendReject
    * @Description: TODO( 实支驳回)
    * @author HeZeMin
    * @param param  驳回信息
    * @return   驳回结果
     */
    @PermissionsCode(code = "rest_receiptpay_real_branch_reject")
    @RequestMapping(value = "/answerExpendReject", method = RequestMethod.POST)
    public ApiResult<Boolean> answerExpendReject(HttpServletRequest request, @RequestBody FinanceReceiptPayParam param){
        try {
            if(null != param){
                
              String pin = (String) request.getAttribute(LoginConsts.PIN);
              String ip =super.getRemoteIPs(request);
              param.setCreateName(pin);
              param.setIp(ip);
              param.setModifiedName(pin);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return financeReceiptPayRestService.answerExpendReject(param);
    }
    /**
     * 
    * @Title: answerExpendReceipt
    * @Description: TODO( 实支付款)
    * @author HeZeMin
    * @param param  付款信息
    * @return   返回付款结果
     */
    @PermissionsCode(code = "rest_receiptpay_real_branch_payment")
    @RequestMapping(value = "/answerExpendReceipt", method = RequestMethod.POST)
    public ApiResult<Boolean> answerExpendReceipt(HttpServletRequest request, @RequestBody FinanceReceiptPayParam param){
        try {
            if(null != param){
                
              String pin = (String) request.getAttribute(LoginConsts.PIN);
              String ip =super.getRemoteIPs(request);
              param.setCreateName(pin);
              param.setIp(ip);
              param.setModifiedName(pin);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return financeReceiptPayRestService.answerExpendReceipt(param);
    }
    /**
     * 
    * @Title: updateAnswerExpendReceipt
    * @Description: TODO( 修改实支付款信息)
    * @author HeZeMin
    * @param param  实支付款信息
    * @return   返回修改结果
     */
    @PermissionsCode(code = "rest_receiptpay_real_branch_update")
    @RequestMapping(value = "/updateAnswerExpendReceipt", method = RequestMethod.POST)
    public ApiResult<Boolean> updateAnswerExpendReceipt(HttpServletRequest request, @RequestBody FinanceReceiptPayParam param){
        ApiResult<Boolean> result = new ApiResult<>();
        try {
            if(null != param){
              String pin = (String) request.getAttribute(LoginConsts.PIN);
              String ip =super.getRemoteIPs(request);
              param.setCreateName(pin);
              param.setIp(ip);
              param.setModifiedName(pin);
            }
            result = financeReceiptPayRestService.updateAnswerExpendReceipt(param);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
    /**
     * 
    * @Title: saveReceiptPay
    * @Description: TODO( 添加收支)
    * @author HeZeMin
    * @param param  收支信息
    * @return   返回添加结果
     */
    @PermissionsCode(code = "rest_receiptpay_save")
    @RequestMapping(value = "/saveReceiptPay", method = RequestMethod.POST)
    public ApiResult<Boolean> saveReceiptPay(HttpServletRequest request, @RequestBody FinanceReceiptPayParam param) {
        try {
            if(null != param){
              String pin = (String) request.getAttribute(LoginConsts.PIN);
              String ip =super.getRemoteIPs(request);
              param.setCreateName(pin);
              param.setIp(ip);
              param.setModifiedName(pin);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return financeReceiptPayRestService.saveReceiptPay(param);
    }
    /*********************get/set**********************/
    
    public FinanceReceiptPayRestService getFinanceReceiptPayRestService() {
        return financeReceiptPayRestService;
    }
    public void setFinanceReceiptPayRestService(FinanceReceiptPayRestService financeReceiptPayRestService) {
        this.financeReceiptPayRestService = financeReceiptPayRestService;
    }
}
