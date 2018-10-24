package com.young.pact.controller.purchasehouse;

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
import com.young.pact.api.domain.param.rest.purchasehouse.PurchaseHouseParam;
import com.young.pact.api.domain.result.rest.purchasehouse.PurchaseHouseResult;
import com.young.pact.api.service.rest.purchasehouse.PurchaseHouseRestService;
import com.young.pact.common.constant.LoginConsts;
/**
 * 
* @ClassName: PurchaseHouseController
* @Description: TODO( 托进房源信息)
* @author HeZeMin
* @date 2018年8月1日 下午5:43:12
*
 */
@RestController
@RequestMapping(value = "/purchasehouse", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class PurchaseHouseController extends BaseController {
    /*********************声明区**********************/
    private static final Log LOG = LogFactory.getLog(PurchaseHouseController.class);
    private PurchaseHouseRestService purchaseHouseRestService;
    /*********************方法区**********************/
    /**
     * 
    * @Title: saveHouseRedis
    * @Description: TODO( 保存托进房源信息到redis中)
    * @author HeZeMin
    * @param param  托进房源信息
    * @return   返回结果
     */
    @RequestMapping(value = "/saveHouseRedis", method = RequestMethod.POST)
    public ApiResult<String> saveHouseRedis(HttpServletRequest request, @RequestBody PurchaseHouseParam param){
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
        return purchaseHouseRestService.saveHouseRedis(param);
    }
    /**
     * 
    * @Title: getHouseRedis
    * @Description: TODO( 根据redis key查询托进房源信息)
    * @author HeZeMin
    * @param redisKey redis key
    * @return   返回缓存中托进房源信息
     */
    @RequestMapping(value = "/getHouseRedis/{redisKey}", method = RequestMethod.GET)
    public ApiResult<PurchaseHouseResult> getHouseRedis(HttpServletRequest request, @PathVariable String redisKey){
        return purchaseHouseRestService.getHouseRedis(redisKey);
    }
    
    /**
     * 
    * @Title: getHouseByPurchaseCode
    * @Description: TODO( 根据托进合同编码查询托进合同房源信息)
    * @author HeZeMin
    * @param purchaseCode   托进合同编码
    * @return   返回托进合同房源信息
     */
    @RequestMapping(value = "/getHouseByPurchaseCode/{purchaseCode}", method = RequestMethod.GET)
    public ApiResult<PurchaseHouseResult> getHouseByPurchaseCode(@PathVariable String purchaseCode){
        return purchaseHouseRestService.getHouseByPurchaseCode(purchaseCode);
    }
    /**
     * 
    * @Title: updatePurchaseHouse
    * @Description: TODO( 修改托进合同房源信息)
    * @author HeZeMin
    * @param param  托进合同房源信息
    * @return   返回修改结果
     */
    @PermissionsCode(code = "rest_purbase_house_update")
    @RequestMapping(value = "/updatePurchaseHouse", method = RequestMethod.POST)
    public ApiResult<Boolean> updatePurchaseHouse(HttpServletRequest request, @RequestBody PurchaseHouseParam param){
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
        return purchaseHouseRestService.updatePurchaseHouse(param);
    }
    /*********************get/set**********************/
    public PurchaseHouseRestService getPurchaseHouseRestService() {
        return purchaseHouseRestService;
    }
    public void setPurchaseHouseRestService(PurchaseHouseRestService purchaseHouseRestService) {
        this.purchaseHouseRestService = purchaseHouseRestService;
    }
}
