package com.young.pact.controller.purchasebase;

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
import com.young.pact.api.domain.param.rest.purchasebase.PurchaseBaseParam;
import com.young.pact.api.domain.param.rest.purchasebase.PurchaseBaseQueryParam;
import com.young.pact.api.domain.result.rest.purchasebase.PurchaseBaseListResult;
import com.young.pact.api.domain.result.rest.purchasebase.PurchaseBaseResult;
import com.young.pact.api.service.rest.purchasebase.PurchaseBaseRestService;
import com.young.pact.common.constant.LoginConsts;
/**
 * 
* @ClassName: PurchaseBaseController
* @Description: TODO( 托进合同)
* @author HeZeMin
* @date 2018年8月2日 上午11:52:27
*
 */
@RestController
@RequestMapping(value = "/purchaseBase", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class PurchaseBaseController extends BaseController {
    /*********************声明区**********************/
    private static final Log LOG = LogFactory.getLog(PurchaseBaseController.class);
    private PurchaseBaseRestService purchaseBaseRestService;
    /*********************方法区**********************/
    /**
     * 
    * @Title: savePurchaseBaseRedis
    * @Description: TODO( 托进合同第三步 保存合同信息到redis中)
    * @author HeZeMin
    * @param param  合同信息
    * @return   返回redis key
     */
    @RequestMapping(value = "/savePurchaseBaseRedis", method = RequestMethod.POST)
    public ApiResult<String> savePurchaseBaseRedis(HttpServletRequest request, @RequestBody PurchaseBaseParam param){
        try {
            if(null != param){
                String pin = (String) request.getAttribute(LoginConsts.PIN);
                String ip = super.getRemoteIPs(request);
                param.setCreateName(pin);
                param.setIp(ip);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return purchaseBaseRestService.savePurchaseBaseRedis(param);
        
    }
    /**
     * 
    * @Title: getPurchaseBaseRedis
    * @Description: TODO( 根据redis key查询缓存合同信息)
    * @author HeZeMin
    * @param redisKey redis key
    * @return   返回合同信息
     */
    @RequestMapping(value = "/getPurchaseBaseRedis/{redisKey}", method = RequestMethod.GET)
    public ApiResult<PurchaseBaseResult> getPurchaseBaseRedis(HttpServletRequest request, @PathVariable String redisKey){
        return purchaseBaseRestService.getPurchaseBaseRedis(redisKey);
    }
    /**
     * 
    * @Title: savePurchaseBase
    * @Description: TODO( 添加托进合同)
    * @author HeZeMin
    * @param param  申报编码
    * @return   返回添加结果
     */
    @PermissionsCode(code = "rest_purbase_save")
    @RequestMapping(value = "/savePurchaseBase", method = RequestMethod.POST)
    public ApiResult<String> savePurchaseBase(HttpServletRequest request, @RequestBody PurchaseBaseParam param){
        try {
            if(null != param){
                String pin = (String) request.getAttribute(LoginConsts.PIN);
                String ip = super.getRemoteIPs(request);
                param.setCreateName(pin);
                param.setIp(ip);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return purchaseBaseRestService.savePurchaseBase(param);
    }
    /**
     * 
    * @Title: listPurchaseBase
    * @Description: TODO( 托进合同列表)
    * @author HeZeMin
    * @param param  查询条件
    * @return   返回托进合同列表数据
     */
    @PermissionsCode(code = "rest_purbase_page")
    @RequestMapping(value = "/listPurchaseBase", method = RequestMethod.POST)
    public ApiResult<PageBean<PurchaseBaseListResult>> listPurchaseBase(HttpServletRequest request, @RequestBody PurchaseBaseQueryParam param){
        try {
            if(null != param){
                
              String pin = (String) request.getAttribute(LoginConsts.PIN);
              String ip = super.getRemoteIPs(request);
              Integer scope = (Integer) request.getAttribute(LoginConsts.SCOPE);
              param.setCreateName(pin);
              param.setIp(ip);
              param.setScope(scope);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return purchaseBaseRestService.listPurchaseBase(param);
    }
    /**
     * 
    * @Title: getPurchaseBase
    * @Description: TODO( 根据托进合同编码查询托进合同详情)
    * @author HeZeMin
    * @param purchaseCode   托进合同编码
    * @return   返回托进合同详情
     */
    @PermissionsCode(code = "rest_purbase_detail")
    @RequestMapping(value = "/getPurchaseBase/{purchaseCode}", method = RequestMethod.GET)
    public ApiResult<PurchaseBaseResult> getPurchaseBase(HttpServletRequest request,@PathVariable String purchaseCode){
        PurchaseBaseQueryParam param = new PurchaseBaseQueryParam();
        String pin = (String) request.getAttribute(LoginConsts.PIN);
        String ip = super.getRemoteIPs(request);
        Integer scope = (Integer) request.getAttribute(LoginConsts.SCOPE);
        param.setCreateName(pin);
        param.setIp(ip);
        param.setScope(scope);
        param.setPurchaseCode(purchaseCode);
        return purchaseBaseRestService.getPurchaseBase(param);
    }
    /**
     * 
    * @Title: removePurchaseBase
    * @Description: TODO( 根据托进合同编码删除托进合同)
    * @author HeZeMin
    * @param param  托进合同编码
    * @return   返回删除结果
     */
    @PermissionsCode(code = "rest_purbase_remove")
    @RequestMapping(value = "/removePurchaseBase", method = RequestMethod.POST)
    public ApiResult<Boolean> removePurchaseBase(HttpServletRequest request, @RequestBody PurchaseBaseParam param){
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
        return purchaseBaseRestService.removePurchaseBase(param);
    }
    /**
     * 
    * @Title: purchaseBaseApply
    * @Description: TODO( 申请审核)
    * @author HeZeMin
    * @param param  托进合同编码
    * @return   返回审核结果
     */
    @PermissionsCode(code = "rest_purbase_apply")
    @RequestMapping(value = "/purchaseBaseApply", method = RequestMethod.POST)
    public ApiResult<Boolean> purchaseBaseApply(HttpServletRequest request, @RequestBody PurchaseBaseParam param){
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
        return purchaseBaseRestService.purchaseBaseApply(param);
    }
    /**
     * 
    * @Title: purchaseBasePassed
    * @Description: TODO( 审核通过)
    * @author HeZeMin
    * @param param  托进合同编码
    * @return   返回审核结果
     */
    @PermissionsCode(code = "rest_purbase_passed")
    @RequestMapping(value = "/purchaseBasePassed", method = RequestMethod.POST)
    public ApiResult<Boolean> purchaseBasePassed(HttpServletRequest request, @RequestBody PurchaseBaseParam param){
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
        return purchaseBaseRestService.purchaseBasePassed(param);
    }
    /**
     * 
    * @Title: purchaseBaseReject
    * @Description: TODO( 审核驳回)
    * @author HeZeMin
    * @param param  托进合同编码，原因
    * @return   返回审核结果
     */
    @PermissionsCode(code = "rest_purbase_reject")
    @RequestMapping(value = "/purchaseBaseReject", method = RequestMethod.POST)
    public ApiResult<Boolean> purchaseBaseReject(HttpServletRequest request, @RequestBody PurchaseBaseParam param){
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
        return purchaseBaseRestService.purchaseBaseReject(param);
    }
    
    /**
     * 
    * @Title: getPurchaseByPurchaseCode
    * @Description: TODO( 根据托进合同编码查询托进合同详情--修改托进合同需要)
    * @author HeZeMin
    * @param purchaseCode 托进合同编码
    * @return   返回托进合同详情
     */
    @RequestMapping(value = "/getPurchaseByPurchaseCode/{purchaseCode}", method = RequestMethod.GET)
    public ApiResult<PurchaseBaseResult> getPurchaseByPurchaseCode(@PathVariable String purchaseCode){
        return purchaseBaseRestService.getPurchaseByPurchaseCode(purchaseCode);
    }
    
    /**
     * 
    * @Title: updatePurchaseBase
    * @Description: TODO( 修改托进合同信息)
    * @author HeZeMin
    * @param param  托进合同信息
    * @return   返回修改结果
     */
    @PermissionsCode(code = "rest_purbase_update")
    @RequestMapping(value = "/updatePurchaseBase", method = RequestMethod.POST)
    public ApiResult<Boolean> updatePurchaseBase(HttpServletRequest request, @RequestBody PurchaseBaseParam param){
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
        return purchaseBaseRestService.updatePurchaseBase(param);
    }
    /**
     * @Title: getRoomNumberByPurchaseCode
     * @Description: TODO( 根据拖进合同编码查询申报的拖进间数 )
     * @author GuoXiaoPeng
     * @param purchaseCode 拖进合同编码
     * @return 拖进间数 
     * @throws 异常
      */
    @RequestMapping(value = "/getRoomNumberByPurchaseCode/{purchaseCode}", method = RequestMethod.GET)
    ApiResult<Integer> getRoomNumberByPurchaseCode(@PathVariable String purchaseCode){
        ApiResult<Integer> result = new ApiResult<>();
        try {
            result = purchaseBaseRestService.getRoomNumberByPurchaseCode(purchaseCode);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
    /*********************get/set**********************/
    public PurchaseBaseRestService getPurchaseBaseRestService() {
        return purchaseBaseRestService;
    }
    public void setPurchaseBaseRestService(PurchaseBaseRestService purchaseBaseRestService) {
        this.purchaseBaseRestService = purchaseBaseRestService;
    }
}
