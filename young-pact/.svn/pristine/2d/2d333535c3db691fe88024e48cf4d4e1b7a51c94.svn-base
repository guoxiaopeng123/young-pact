package com.young.pact.controller.commonbelonger;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tools.common.springmvc.base.BaseController;
import com.young.common.annotation.PermissionsCode;
import com.young.common.domain.ApiResult;
import com.young.pact.api.domain.param.rest.commonbelonger.CommonBelongerParam;
import com.young.pact.api.service.rest.commonbelonger.CommonBelongerRestService;
import com.young.pact.commom.LoginConsts;
/**
 * 
* @ClassName: CommonBelongerController
* @Description: TODO( 合同责任人)
* @author HeZeMin
* @date 2018年8月5日 下午5:02:03
*
 */
@RestController
@RequestMapping(value = "/commonBelonger", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class CommonBelongerController extends BaseController {
    /*********************声明区**********************/
    private static final Log LOG = LogFactory.getLog(CommonBelongerController.class);
    private CommonBelongerRestService commonBelongerRestService;
    /*********************方法区**********************/
    /**
     * 
    * @Title: updateOperateBelonger
    * @Description: TODO( 分配运营管家)
    * @author HeZeMin
    * @param param  运营管家
    * @return   返回分配结果
     */
    @PermissionsCode(code = "rest_purbase_update_operate_belonger")
    @RequestMapping(value = "/updateOperateBelonger", method = RequestMethod.POST)
    public ApiResult<Boolean> updateOperateBelonger(HttpServletRequest request, @RequestBody CommonBelongerParam param){
        try {
            if(null != param){
                String pin = (String) request.getAttribute(LoginConsts.PIN);
                String ip = super.getRemoteIPs(request);
                param.setCreateName(pin);
                param.setModifiedName(pin);
                param.setIp(ip);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return commonBelongerRestService.updateOperateBelonger(param);
    }
    /**
     * 
    * @Title: updateServiceBelonger
    * @Description: TODO( 分配租后管家)
    * @author HeZeMin
    * @param param  租后管家
    * @return   返回分配结果
     */
    @PermissionsCode(code = "rest_purbase_update_service_belonger")
    @RequestMapping(value = "/updateServiceBelonger", method = RequestMethod.POST)
    public ApiResult<Boolean> updateServiceBelonger(HttpServletRequest request, @RequestBody CommonBelongerParam param){
        try {
            if(null != param){
                String pin = (String) request.getAttribute(LoginConsts.PIN);
                String ip = super.getRemoteIPs(request);
                param.setCreateName(pin);
                param.setModifiedName(pin);
                param.setIp(ip);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return commonBelongerRestService.updateServiceBelonger(param);
    }
    
    
    
    /*********************get/set**********************/
    public CommonBelongerRestService getCommonBelongerRestService() {
        return commonBelongerRestService;
    }
    public void setCommonBelongerRestService(CommonBelongerRestService commonBelongerRestService) {
        this.commonBelongerRestService = commonBelongerRestService;
    }
}
