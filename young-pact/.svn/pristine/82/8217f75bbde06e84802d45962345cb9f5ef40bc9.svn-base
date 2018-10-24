package com.young.pact.controller.pactrenttransfer;

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
import com.young.pact.api.domain.param.rest.pactrenttransfer.PactRentTransferParam;
import com.young.pact.api.domain.param.rest.rentbase.RentPactParam;
import com.young.pact.api.domain.result.rest.pactrenttransfer.PactRentTransferResult;
import com.young.pact.api.domain.result.rest.rentbase.RentPactResult;
import com.young.pact.api.service.rest.pactrenttransfer.PactRentTransferRestService;
import com.young.pact.common.constant.LoginConsts;

/**
 * 
* @ClassName: PactRentTransferController
* @Description: 调房协议controller
* @author LiuYuChi
* @date 2018年8月8日 下午5:26:45
*
 */
@RestController
@RequestMapping(value="/pactRentTransferController" ,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class PactRentTransferController extends BaseController{
	
	private static Log LOG = LogFactory.getLog(PactRentTransferController.class);
	private PactRentTransferRestService pactRentTransferService;
	
	/**
	 * 
	* @Title: insertFirst
	* @Description: 录入调房协议
	* @author LiuYuChi
	* @param request
	* @param param
	* @return
	* @throws
	 */
	@RequestMapping(value = "/insertFirst", method = RequestMethod.POST)
    public ApiResult<String> insertFirst(HttpServletRequest request, @RequestBody PactRentTransferParam param){
        ApiResult<String> result = new ApiResult<>();
        try {
            if(null!=param){
            	String pin = (String) request.getAttribute(LoginConsts.PIN);
            	String ip =super.getRemoteIPs(request);
                param.setCreateName(pin);
                param.setIp(ip);
                param.setModifiedName(pin);
            }
            result = pactRentTransferService.insertFirst(param);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
	
	/**
	 * 
	* @Title: getFirst
	* @Description: 读取调房协议
	* @author LiuYuChi
	* @param request
	* @param key
	* @return
	* @throws
	 */
	@RequestMapping(value = "/getFirst/{key}", method = RequestMethod.GET)
    public ApiResult<PactRentTransferResult> getFirst(HttpServletRequest request, @PathVariable String key){
        ApiResult<PactRentTransferResult> result = new ApiResult<PactRentTransferResult>();
        try {
            result = pactRentTransferService.getFirst(key);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
	
	/**
	 * 
	* @Title: insertPact
	* @Description:新增合同信息(redis保存)
	* @author LiuYuChi
	* @param request
	* @param param
	* @return
	* @throws
	 */
	@RequestMapping(value = "/insertPact", method = RequestMethod.POST)
    public ApiResult<String> insertPact(HttpServletRequest request, @RequestBody RentPactParam param){
        ApiResult<String> result = new ApiResult<>();
        try {
            if(null!=param){
            	String pin = (String) request.getAttribute(LoginConsts.PIN);
            	String ip =super.getRemoteIPs(request);
                param.setCreateName(pin);
                param.setIp(ip);
                param.setModifiedName(pin);
            }
            result = pactRentTransferService.insertPact(param);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
	
	/**
	 * 
	* @Title: getPact
	* @Description:读取合同信息
	* @author LiuYuChi
	* @param request
	* @param key
	* @return
	* @throws
	 */
	@SuppressWarnings("unused")
    @RequestMapping(value = "/getPact/{key}", method = RequestMethod.GET)
    public ApiResult<RentPactResult> getPact(HttpServletRequest request, @PathVariable String key){
        ApiResult<RentPactResult> result = new ApiResult<RentPactResult>();
        try {
            if(null!=key){
            	String pin = (String) request.getAttribute(LoginConsts.PIN);
                String ip =super.getRemoteIPs(request);
            	result = pactRentTransferService.getPact(key, pin);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
	
	/**
	 * 
	* @Title: insertTransfer
	* @Description: 保存调房协议和合同信息
	* @author LiuYuChi
	* @param request
	* @param param
	* @return
	* @throws
	 */
	@PermissionsCode(code = "rest_rent_transfer_save")
	@RequestMapping(value = "/insertTransfer", method = RequestMethod.POST)
    public ApiResult<String> insertTransfer(HttpServletRequest request, @RequestBody PactRentTransferParam param){
        ApiResult<String> result = new ApiResult<String>();
        try {
            if(null!=param){
            	String pin = (String) request.getAttribute(LoginConsts.PIN);
            	String ip =super.getRemoteIPs(request);
                param.setCreateName(pin);
                param.setIp(ip);
                param.setModifiedName(pin);
            }
            result = pactRentTransferService.insertTransfer(param);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
	
	/**
	 * 
	* @Title: getTransfer
	* @Description: 获取调房详情
	* @author LiuYuChi
	* @param request
	* @param transferCode
	* @return
	* @throws
	 */
	@SuppressWarnings("unused")
    @PermissionsCode(code = "rest_rent_transfer_detail")
	@RequestMapping(value = "/getTransfer/{transferCode}", method = RequestMethod.GET)
    public ApiResult<PactRentTransferResult> getTransfer(HttpServletRequest request, @PathVariable String transferCode){
        ApiResult<PactRentTransferResult> result = new ApiResult<PactRentTransferResult>();
        try {
            if(null!=transferCode){
                String pin = (String) request.getAttribute(LoginConsts.PIN);
                String ip =super.getRemoteIPs(request);
                PactRentTransferParam param = new PactRentTransferParam();
                Integer scope = (Integer) request.getAttribute(LoginConsts.SCOPE);
                param.setCreateName(pin);
                param.setModifiedName(pin);
                param.setTransferCode(transferCode);
                param.setScope(scope);
            	result = pactRentTransferService.getTransferInfo(param);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
	
	/**
	 * 
	* @Title: delTransfer
	* @Description: 删除调房协议
	* @author LiuYuChi
	* @param request
	* @param transferCode
	* @return
	* @throws
	 */
	@PermissionsCode(code = "rest_rent_transfer_remove")
	@RequestMapping(value = "/delTransfer", method = RequestMethod.POST)
    public ApiResult<Boolean> delTransfer(HttpServletRequest request, @RequestBody PactRentTransferParam param){
        ApiResult<Boolean> result = new ApiResult<Boolean>();
        try {
            String pin = (String) request.getAttribute(LoginConsts.PIN);
            String ip =super.getRemoteIPs(request);
            param.setCreateName(pin);
            param.setIp(ip);
            param.setModifiedName(pin);
            result = pactRentTransferService.delTransfer(param);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
	
	
	/**
	 * 
	* @Title: listPurchaseBase
	* @Description: 查询调房协议列表
	* @author LiuYuChi
	* @param request
	* @param param
	* @return
	* @throws
	 */
	@PermissionsCode(code = "rest_rent_transfer_page")
	@RequestMapping(value = "/listTransfer", method = RequestMethod.POST)
    public ApiResult<PageBean<PactRentTransferResult>> listPurchaseBase(HttpServletRequest request, @RequestBody PactRentTransferParam param){
        try {
            if(null != param){
                String pin = (String) request.getAttribute(LoginConsts.PIN);
                Integer scope = (Integer) request.getAttribute(LoginConsts.SCOPE);
                String ip =super.getRemoteIPs(request);
                param.setCreateName(pin);
                param.setCreateName(pin);
                param.setIp(ip);
                param.setModifiedName(pin);
                param.setScope(scope);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return pactRentTransferService.listTransfer(param);
    }
	 
	 /**
	  * 
	 * @Title: updateTransfer
	 * @Description: 保存修改协议
	 * @author LiuYuChi
	 * @param request
	 * @param param
	 * @return
	 * @throws
	  */
	@PermissionsCode(code = "rest_rent_transfer_update")
	@RequestMapping(value = "/updateTransfer", method = RequestMethod.POST)
    public ApiResult<Boolean> updateTransfer(HttpServletRequest request, @RequestBody PactRentTransferParam param){
        ApiResult<Boolean> result = new ApiResult<Boolean>();
        try {
            if(null!=param){
            	String pin = (String) request.getAttribute(LoginConsts.PIN);
            	String ip =super.getRemoteIPs(request);
                param.setIp(ip);
                param.setModifiedName(pin);
            	result = pactRentTransferService.updateTransfer(param);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

	/**
	 * 
	* @Title: aplyCheck
	* @Description: 申请审核
	* @author LiuYuChi
	* @param request
	* @param transferCode
	* @return
	* @throws
	 */
	@PermissionsCode(code = "rest_rent_transfer_apply")
	@RequestMapping(value = "/applyCheck", method = RequestMethod.POST)
    public ApiResult<Boolean> aplyCheck(HttpServletRequest request, @RequestBody PactRentTransferParam param){
        ApiResult<Boolean> result = new ApiResult<Boolean>();
        try {
            if(null!=param){
            	String pin = (String) request.getAttribute(LoginConsts.PIN);
            	String ip =super.getRemoteIPs(request);
                param.setIp(ip);
                param.setModifiedName(pin);
            	param.setState(2);
            	result = pactRentTransferService.aplyCheck(param);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
	
	/**
	 * 
	* @Title: rejectTransfer
	* @Description: 驳回
	* @author LiuYuChi
	* @param request
	* @param param
	* @return
	* @throws
	 */
	@PermissionsCode(code = "rest_rent_transfer_reject")
	@RequestMapping(value = "/rejectTransfer", method = RequestMethod.POST)
    public ApiResult<Boolean> rejectTransfer(HttpServletRequest request, @RequestBody PactRentTransferParam param){
        ApiResult<Boolean> result = new ApiResult<Boolean>();
        try {
            if(null!=param){
            	String pin = (String) request.getAttribute(LoginConsts.PIN);
            	String ip =super.getRemoteIPs(request);
                param.setCreateName(pin);
                param.setIp(ip);
                param.setModifiedName(pin);
            	param.setState(2);
            	result = pactRentTransferService.reject(param);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
	
	/**
	 * 
	* @Title: check
	* @Description: 审核通过
	* @author LiuYuChi
	* @param request
	* @param transferCode
	* @return
	* @throws
	 */
	@PermissionsCode(code = "rest_rent_transfer_passed")
	@RequestMapping(value = "/check", method = RequestMethod.POST)
    public ApiResult<Boolean> check(HttpServletRequest request, @RequestBody PactRentTransferParam param){
        ApiResult<Boolean> result = new ApiResult<Boolean>();
        try {
            if(null!=param){
            	String pin = (String) request.getAttribute(LoginConsts.PIN);
            	String ip =super.getRemoteIPs(request);
                param.setIp(ip);
                param.setModifiedName(pin);
                result = pactRentTransferService.check(param);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
	
	
	public PactRentTransferRestService getPactRentTransferService() {
		return pactRentTransferService;
	}
	public void setPactRentTransferService(
			PactRentTransferRestService pactRentTransferService) {
		this.pactRentTransferService = pactRentTransferService;
	}
}
