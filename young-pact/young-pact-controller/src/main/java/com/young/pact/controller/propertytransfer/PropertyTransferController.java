package com.young.pact.controller.propertytransfer;

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
import com.young.common.domain.ApiResult;
import com.young.pact.api.domain.param.rest.propertytransfer.PropertyTransferParam;
import com.young.pact.api.domain.result.rest.propertytransfer.PropertyTransferResult;
import com.young.pact.api.service.rest.propertytransfer.PropertyTransferRestService;
import com.young.pact.common.constant.LoginConsts;
/**
 * 
* @ClassName: PropertyTransferController
* @Description: TODO( 物业交接)
* @author HeZeMin
* @date 2018年8月2日 下午4:26:01
*
 */
@RestController
@RequestMapping(value = "/propertytransfer", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class PropertyTransferController extends BaseController {
    /*********************声明区**********************/
    private static final Log LOG = LogFactory.getLog(PropertyTransferController.class);
    private PropertyTransferRestService propertyTransferRestService;
    /*********************方法区**********************/
    /**
     * 
    * @Title: savePropertyTransferRedis
    * @Description: TODO( 保存托进合同物业交接到redis中)
    * @author HeZeMin
    * @param param  托进合同物业交接
    * @return   返回redis key
     */
    @RequestMapping(value = "/savePropertyTransferRedis", method = RequestMethod.POST)
    public ApiResult<String> savePropertyTransferRedis(HttpServletRequest request, @RequestBody PropertyTransferParam param){
        try {
            if(null != param){
                String pin = (String) request.getAttribute(LoginConsts.PIN);
                String ip =super.getRemoteIPs(request);
                param.setCreateName(pin);
                param.setIp(ip);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return propertyTransferRestService.savePropertyTransferRedis(param);
    }
    /**
     * 
    * @Title: getPropertyTransferRedis
    * @Description: TODO( 根据redis key查询缓存中托进合同物业交接)
    * @author HeZeMin
    * @param redisKey   redis key
    * @return   返回托进合同物业交接
     */
    @RequestMapping(value = "/getPropertyTransferRedis/{redisKey}", method = RequestMethod.GET)
    public ApiResult<PropertyTransferResult> getPropertyTransferRedis(HttpServletRequest request, @PathVariable String redisKey){
        return propertyTransferRestService.getPropertyTransferRedis(redisKey);
    }
    
    
    /*********************get/set**********************/
    public PropertyTransferRestService getPropertyTransferRestService() {
        return propertyTransferRestService;
    }
    public void setPropertyTransferRestService(PropertyTransferRestService propertyTransferRestService) {
        this.propertyTransferRestService = propertyTransferRestService;
    }
}
