package com.young.pact.controller.purchasetermination;

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
import com.young.pact.api.domain.param.rest.purchasetermination.PurchaseTerminationParam;
import com.young.pact.api.domain.param.rest.purchasetermination.PurchaseTerminationQueryParam;
import com.young.pact.api.domain.result.rest.purchasetermination.PurchaseTerminationBaseResult;
import com.young.pact.api.domain.result.rest.purchasetermination.PurchaseTerminationEditResult;
import com.young.pact.api.domain.result.rest.purchasetermination.PurchaseTerminationListResult;
import com.young.pact.api.service.rest.purchasetermination.PurchaseTerminationRestService;
import com.young.pact.common.constant.LoginConsts;
import com.young.pact.controller.purchasebase.PurchaseBaseController;

/**
 * 
* @ClassName: PurchaseTermination
* @Description: 托进解约
* @author SunYiXuan
* @date 2018年8月9日 下午1:58:59
*
 */
@RestController
@RequestMapping(value = "/purchaseTermination", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class PurchaseTerminationController extends BaseController{

    private static final Log LOG = LogFactory.getLog(PurchaseBaseController.class);
    private PurchaseTerminationRestService purchaseTerminationRestService;
    
    
    
    
    /**
     * 
    * @Title: savePurchaseTermination
    * @Description: 添加托进解约协议
    * @author SunYiXuan
    * @param param
    * @return
    * @throws
     */
    @PermissionsCode(code = "rest_purchase_termination_save")
    @RequestMapping(value = "/savePurchaseTermination", method = RequestMethod.POST)
    public ApiResult<Boolean> savePurchaseTermination(HttpServletRequest request, @RequestBody PurchaseTerminationParam param){
        try {
            if(null!=param){
                String pin = (String) request.getAttribute(LoginConsts.PIN);
                String ip = super.getRemoteIPs(request);
                param.setCreateName(pin);
                param.setIp(ip);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return purchaseTerminationRestService.savePurchaseTermination(param);
    }

    /**
     * 
    * @Title: listPurchaseTermination
    * @Description: 查询解约协议列表
    * @author SunYiXuan
    * @param param
    * @return
    * @throws
     */
    @PermissionsCode(code = "rest_purchase_termination_page")
    @RequestMapping(value = "/listPurchaseTermination", method = RequestMethod.POST)
    public ApiResult<PageBean<PurchaseTerminationListResult>> listPurchaseTermination(HttpServletRequest request, @RequestBody PurchaseTerminationQueryParam param){
      try {
          if(null!=param){
              String pin = (String) request.getAttribute(LoginConsts.PIN);
              @SuppressWarnings("unused")
            String ip = super.getRemoteIPs(request);
              Integer scope = (Integer) request.getAttribute(LoginConsts.SCOPE);
              param.setCreateName(pin);
              param.setScope(scope);
          }
      } catch (Exception e) {
          LOG.error(e.getMessage(), e);
      }
        return purchaseTerminationRestService.listPurchaseTermination(param);
    }

    
    /**
     * 
    * @Title: getPurchaseTerminationBase
    * @Description: 获取托进合同解约协议基本信息
    * @author SunYiXuan
    * @param terminationCode
    * @return
    * @throws
     */
    @SuppressWarnings("unused")
    @PermissionsCode(code = "rest_purchase_termination_detail")
    @RequestMapping(value = "/getPurchaseTerminationBase/{terminationCode}", method = RequestMethod.GET)
    public ApiResult<PurchaseTerminationBaseResult> getPurchaseTerminationBase(HttpServletRequest request,@PathVariable String terminationCode){
        PurchaseTerminationQueryParam param = new PurchaseTerminationQueryParam();
        String pin = (String) request.getAttribute(LoginConsts.PIN);
        String ip = super.getRemoteIPs(request);
        Integer scope = (Integer) request.getAttribute(LoginConsts.SCOPE);
        param.setCreateName(pin);
        param.setScope(scope);
        param.setTerminationCode(terminationCode);
        return purchaseTerminationRestService.getPurchaseTerminationBase(param );
    }
    
    
    /**
     * 
    * @Title: getPurchaseTerminationEdit
    * @Description: 获取拖进合同解约协议编辑页信息
    * @author SunYiXuan
    * @param terminationCode
    * @return
    * @throws
     */
    @RequestMapping(value = "/getPurchaseTerminationEdit/{terminationCode}", method = RequestMethod.GET)
    public ApiResult<PurchaseTerminationEditResult> getPurchaseTerminationEdit(@PathVariable String terminationCode){
        return purchaseTerminationRestService.getPurchaseTerminationEdit(terminationCode);
    }
    
    
    
    /**
     * 
    * @Title: updatePurchaseTermination
    * @Description: 修改拖进合同解约协议
    * @author SunYiXuan
    * @param param
    * @return
    * @throws
     */
    @PermissionsCode(code = "rest_purchase_termination_update")
    @RequestMapping(value = "/updatePurchaseTermination", method = RequestMethod.POST)
    public ApiResult<Boolean> updatePurchaseTermination(HttpServletRequest request, @RequestBody PurchaseTerminationParam param){
        try {
            if(null != param){
              String pin = (String) request.getAttribute(LoginConsts.PIN);
              String ip = super.getRemoteIPs(request);
              param.setModifiedName(pin);
              param.setIp(ip);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return purchaseTerminationRestService.updatePurchaseTermination(param);
    }
    
    
    
    
    /**
     * 
    * @Title: removePurchaseTermination
    * @Description: 删除托进合同解约协议
    * @author SunYiXuan
    * @param terminationCode
    * @return
    * @throws
     */
    @PermissionsCode(code = "rest_purchase_termination_remove")
    @RequestMapping(value = "/removePurchaseTermination", method = RequestMethod.POST)
    public ApiResult<Boolean> removePurchaseTermination(HttpServletRequest request, @RequestBody PurchaseTerminationParam param){
        try {
            if(null != param){
            String pin = (String) request.getAttribute(LoginConsts.PIN);
            String ip = super.getRemoteIPs(request);
            param.setModifiedName(pin);
            param.setIp(ip);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return purchaseTerminationRestService.removePurchaseTermination(param);
    }
    
    
    /**
     * 
    * @Title: purchaseTerminationApply
    * @Description: 申请审核
    * @author SunYiXuan
    * @param request
    * @param param
    * @return
    * @throws
     */
    @PermissionsCode(code = "rest_purchase_termination_apply")
    @RequestMapping(value = "/purchaseTerminationApply", method = RequestMethod.POST)
    public ApiResult<Boolean> purchaseTerminationApply(HttpServletRequest request, @RequestBody PurchaseTerminationParam param){
        try {
            if(null != param){
                
              String pin = (String) request.getAttribute(LoginConsts.PIN);
              String ip = super.getRemoteIPs(request);
              param.setModifiedName(pin);
              param.setIp(ip);
              }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return purchaseTerminationRestService.purchaseTerminationApply(param);
    }
    
    
    /**
     * 
    * @Title: purchaseTerminationReject
    * @Description: 审核驳回
    * @author SunYiXuan
    * @param request
    * @param param
    * @return
    * @throws
     */
    @PermissionsCode(code = "rest_purchase_termination_reject")
    @RequestMapping(value = "/purchaseTerminationReject", method = RequestMethod.POST)
    public ApiResult<Boolean> purchaseTerminationReject(HttpServletRequest request, @RequestBody PurchaseTerminationParam param){
        try {
            if(null != param){
              String pin = (String) request.getAttribute(LoginConsts.PIN);
              String ip = super.getRemoteIPs(request);
              param.setModifiedName(pin);
              param.setIp(ip);
              }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return purchaseTerminationRestService.purchaseTerminationReject(param);
    }
    
    /**
     * 
    * @Title: purchaseTerminationReview
    * @Description: TODO( 托进解约审核通过)
    * @author HeZeMin
    * @param param  审核信息
    * @return   返回审核结果
     */
    @PermissionsCode(code = "rest_purchase_termination_passed")
    @RequestMapping(value = "/purchaseTerminationReview", method = RequestMethod.POST)
    public ApiResult<Boolean> purchaseTerminationReview(HttpServletRequest request, @RequestBody PurchaseTerminationParam param){
        try {
            if(null != param){
              String pin = (String) request.getAttribute(LoginConsts.PIN);
              String ip = super.getRemoteIPs(request);
              param.setModifiedName(pin);
              param.setIp(ip);
              }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return purchaseTerminationRestService.purchaseTerminationReview(param);
    }
    
    
    
    public PurchaseTerminationRestService getPurchaseTerminationRestService() {
        return purchaseTerminationRestService;
    }

    public void setPurchaseTerminationRestService(PurchaseTerminationRestService purchaseTerminationRestService) {
        this.purchaseTerminationRestService = purchaseTerminationRestService;
    }
    
    
    
    
    
}
