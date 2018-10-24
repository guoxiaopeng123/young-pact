package com.young.pact.controller.pactrenttermination;

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
import com.young.pact.api.domain.param.rest.pactrenttermination.PactRentTerminationParam;
import com.young.pact.api.domain.result.rest.pactrenttermination.PactRentTerminationResult;
import com.young.pact.api.service.rest.pactrenttermination.PactRentTerminationService;
import com.young.pact.common.constant.LoginConsts;

/**
 * 
* @ClassName: PactRentTerminationController
* @Description: 拖出解约协议
* @author LiuYuChi
* @date 2018年8月20日 下午3:52:16
*
 */
@RestController
@RequestMapping(value="/pactRentTerminationController",produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class PactRentTerminationController extends BaseController{
	
	private PactRentTerminationService pactRentTerminationService;
	private static Log LOG = LogFactory.getLog(PactRentTerminationController.class);
	
	/**
	 * 
	* @Title: insertTermination
	* @Description: 录入拖出解约协议
	* @author LiuYuChi
	* @param request
	* @param param
	* @return
	* @throws
	 */
	@PermissionsCode(code = "rest_rent_termination_add")
	@RequestMapping(value = "/insertTermination", method = RequestMethod.POST)
    public ApiResult<Boolean> insertTermination(HttpServletRequest request, @RequestBody PactRentTerminationParam param){
        ApiResult<Boolean> result = new ApiResult<>();
        try {
            if(null!=param){
              String pin = (String) request.getAttribute(LoginConsts.PIN);
              String ip =super.getRemoteIPs(request);
              param.setCreateName(pin);
              param.setIp(ip);
              param.setModifiedName(pin);
            }
            result = pactRentTerminationService.insertTermination(param);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
	
    /**
     * 
    * @Title: listTermination
    * @Description: 分页查询解约协议列表
    * @author LiuYuChi
    * @param request
    * @param param
    * @return
    * @throws
     */
	@PermissionsCode(code = "rest_rent_termination_page")
	@RequestMapping(value = "/listTermination", method = RequestMethod.POST)
    public ApiResult<PageBean<PactRentTerminationResult>> listTermination(HttpServletRequest request, @RequestBody PactRentTerminationParam param){
		ApiResult<PageBean<PactRentTerminationResult>> result = new ApiResult<>();
		try {
            if(null != param){
                String pin = (String) request.getAttribute(LoginConsts.PIN);
                String ip = super.getRemoteIPs(request);
                Integer scope = (Integer) request.getAttribute(LoginConsts.SCOPE);
                param.setCreateName(pin);
                param.setIp(ip);
                param.setScope(scope);
            	result = pactRentTerminationService.listTermination(param);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
	
	/**
	 * 
	* @Title: getTermination
	* @Description: 获取解约协议详情
	* @author LiuYuChi
	* @param request
	* @param dissolutionCode
	* @return
	* @throws
	 */
	@PermissionsCode(code = "rest_rent_termination_detail")
	@RequestMapping(value = "/getTermination/{dissolutionCode}", method = RequestMethod.GET)
    public ApiResult<PactRentTerminationResult> getTermination(HttpServletRequest request, @PathVariable String dissolutionCode){
		ApiResult<PactRentTerminationResult> result = new ApiResult<>();
		try {
            if(null != dissolutionCode){
                String pin = (String) request.getAttribute(LoginConsts.PIN);
                String ip = super.getRemoteIPs(request);
                Integer scope = (Integer) request.getAttribute(LoginConsts.SCOPE);
                PactRentTerminationParam param = new PactRentTerminationParam();
                param.setCreateName(pin);
                param.setIp(ip);
                param.setScope(scope);
                param.setDissolutionCode(dissolutionCode);
            	result = pactRentTerminationService.getTermination(param);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
	
	/**
	 * 
	* @Title: toUpdatePage
	* @Description: 跳转到修改解约协议页面
	* @author LiuYuChi
	* @param request
	* @param dissolutionCode
	* @return
	* @throws
	 */
	@PermissionsCode(code = "rest_rent_termination_update")
	@RequestMapping(value = "/toUpdatePage/{dissolutionCode}", method = RequestMethod.GET)
    public ApiResult<PactRentTerminationResult> toUpdatePage(HttpServletRequest request, @PathVariable String dissolutionCode){
		ApiResult<PactRentTerminationResult> result = new ApiResult<>();
		try {
            if(null != dissolutionCode){
            	result = pactRentTerminationService.toUpdatePage(dissolutionCode);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
	
	/**
	 * 
	* @Title: updateTermination
	* @Description: 修改解约协议
	* @author LiuYuChi
	* @param request
	* @param param
	* @return
	* @throws
	 */
	@PermissionsCode(code = "rest_rent_termination_update")
	@RequestMapping(value = "/updateTermination", method = RequestMethod.POST)
    public ApiResult<Boolean> updateTermination(HttpServletRequest request, @RequestBody PactRentTerminationParam param){
		ApiResult<Boolean> result = new ApiResult<>();
		try {
            if(null != param){
              String pin = (String) request.getAttribute(LoginConsts.PIN);
              String ip =super.getRemoteIPs(request);
              param.setCreateName(pin);
              param.setIp(ip);
              param.setModifiedName(pin);
              result = pactRentTerminationService.updateTerminatioin(param);
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
	* @param dissolutionCode
	* @return
	* @throws
	 */
	@PermissionsCode(code = "rest_rent_termination_apply")
	@RequestMapping(value = "/applyCheck", method = RequestMethod.POST)
    public ApiResult<Boolean> aplyCheck(HttpServletRequest request, @RequestBody PactRentTerminationParam param){
		ApiResult<Boolean> result = new ApiResult<>();
		try {
            if(null != param){
            	String pin = (String) request.getAttribute(LoginConsts.PIN);
            	String ip =super.getRemoteIPs(request);
                param.setCreateName(pin);
                param.setIp(ip);
                param.setModifiedName(pin);
            	result = pactRentTerminationService.applyCheck(param);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
	
	/**
	 * 
	* @Title: reject
	* @Description: 驳回
	* @author LiuYuChi
	* @param request
	* @param param
	* @return
	* @throws
	 */
	@PermissionsCode(code = "rest_rent_termination_reject")
	@RequestMapping(value = "/reject", method = RequestMethod.POST)
    public ApiResult<Boolean> reject(HttpServletRequest request, @RequestBody PactRentTerminationParam param){
		ApiResult<Boolean> result = new ApiResult<>();
		try {
            if(null != param){
            	String pin = (String) request.getAttribute(LoginConsts.PIN);
            	String ip =super.getRemoteIPs(request);
                param.setCreateName(pin);
                param.setIp(ip);
                param.setModifiedName(pin);
            	result = pactRentTerminationService.reject(param);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
	
	/**
	 * 
	* @Title: delTermination
	* @Description: 删除解约协议
	* @author LiuYuChi
	* @param request
	* @param dissolutionCode
	* @return
	* @throws
	 */
	@PermissionsCode(code = "rest_rent_termination_remove")
	@RequestMapping(value = "/delTermination", method = RequestMethod.POST)
    public ApiResult<Boolean> delTermination(HttpServletRequest request, @RequestBody PactRentTerminationParam param){
		ApiResult<Boolean> result = new ApiResult<>();
		try {
            if(null != param){
            	String pin = (String) request.getAttribute(LoginConsts.PIN);
            	String ip =super.getRemoteIPs(request);
                param.setIp(ip);
                param.setModifiedName(pin);
            	result = pactRentTerminationService.delTermination(param);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
	
	/**
	 * 
	* @Title: check
	* @Description: 解约协议审核通过
	* @author LiuYuChi
	* @param request
	* @param dissolutionCode
	* @return
	* @throws
	 */
	@PermissionsCode(code = "rest_rent_termination_passed")
	@RequestMapping(value = "/check", method = RequestMethod.POST)
    public ApiResult<Boolean> check(HttpServletRequest request, @RequestBody PactRentTerminationParam param){
		ApiResult<Boolean> result = new ApiResult<>();
		try {
            if(null != param){
            	String pin = (String) request.getAttribute(LoginConsts.PIN);
            	String ip =super.getRemoteIPs(request);
                param.setCreateName(pin);
                param.setIp(ip);
                param.setModifiedName(pin);
            	result = pactRentTerminationService.check(param);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
	

	public PactRentTerminationService getPactRentTerminationService() {
		return pactRentTerminationService;
	}

	public void setPactRentTerminationService(
			PactRentTerminationService pactRentTerminationService) {
		this.pactRentTerminationService = pactRentTerminationService;
	}
}
