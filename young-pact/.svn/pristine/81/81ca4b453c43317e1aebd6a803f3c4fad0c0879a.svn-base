package com.young.pact.controller.common;

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
import com.young.pact.api.domain.param.rest.resourcetransfer.ResourceTransferParam;
import com.young.pact.api.service.rest.common.ResourceTransferService;
import com.young.pact.commom.LoginConsts;

/**
 * 
* @ClassName: ResourceTransferController
* @Description: TODO( 资源转移 )
* @author GuoXiaoPeng
* @date 2018年10月9日 上午9:46:47
*
 */
@RestController
@RequestMapping(value = "/resource", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class ResourceTransferController extends BaseController{

    private static final Log LOG = LogFactory.getLog(BankAccountController.class);
    private ResourceTransferService resourceTransferService;
    @PermissionsCode(code = "rest_resource_transfer")
    @RequestMapping(value = "/resourceTransfer", method = RequestMethod.POST)
    public ApiResult<Boolean> resourceTransfer(HttpServletRequest request,@RequestBody ResourceTransferParam param){
        ApiResult<Boolean> result = new ApiResult<>();
        try {
            if(null!=param){
              String pin = (String) request.getAttribute(LoginConsts.PIN);
              String ip = super.getRemoteIPs(request);
              param.setModifiedName(pin);
              param.setIp(ip);
            }
            result = resourceTransferService.resourceTransfer(param);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
    public ResourceTransferService getResourceTransferService() {
        return resourceTransferService;
    }
    public void setResourceTransferService(ResourceTransferService resourceTransferService) {
        this.resourceTransferService = resourceTransferService;
    }
}
