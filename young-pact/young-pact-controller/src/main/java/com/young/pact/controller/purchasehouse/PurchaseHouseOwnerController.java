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
import com.young.pact.api.domain.param.rest.purchasehouse.PurchaseHouseOwnerParam;
import com.young.pact.api.domain.result.rest.purchasehouse.PurchaseHouseOwnerResult;
import com.young.pact.api.service.rest.purchasehouse.PurchaseHouseOwnerRestService;
import com.young.pact.common.constant.LoginConsts;
/**
 * 
* @ClassName: PurchaseHouseController
* @Description: TODO( 托进业主信息)
* @author HeZeMin
* @date 2018年8月1日 下午5:43:12
*
 */
@RestController
@RequestMapping(value = "/purchasehouseowner", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class PurchaseHouseOwnerController extends BaseController {
    /*********************声明区**********************/
    private static final Log LOG = LogFactory.getLog(PurchaseHouseOwnerController.class);
    private PurchaseHouseOwnerRestService purchaseHouseOwnerRestService;
    /*********************方法区**********************/
    /**
     * 
    * @Title: saveHouseOwnerRedis
    * @Description: TODO( 把托进业主信息保存到redis中)
    * @author HeZeMin
    * @param param  托进业主信息
    * @return   返回redis key
     */
    @RequestMapping(value = "/saveHouseOwnerRedis", method = RequestMethod.POST)
    public ApiResult<String> saveHouseOwnerRedis(HttpServletRequest request, @RequestBody PurchaseHouseOwnerParam param){
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
        return purchaseHouseOwnerRestService.saveHouseOwnerRedis(param);
    }
    /**
     * 
    * @Title: getHouseOwnerRedis
    * @Description: TODO( 根据redis key查询托进业主信息)
    * @author HeZeMin
    * @param redisKey   redis key
    * @return   返回托进业主信息
     */
    @RequestMapping(value = "/getHouseOwnerRedis/{redisKey}", method = RequestMethod.GET)
    public ApiResult<PurchaseHouseOwnerResult> getHouseOwnerRedis(HttpServletRequest request, @PathVariable String redisKey){
        return purchaseHouseOwnerRestService.getHouseOwnerRedis(redisKey);
    }
    
    /**
     * 
    * @Title: getOwnerByPurchaseCode
    * @Description: TODO( 根据托进合同编码查询托进合同业主信息)
    * @author HeZeMin
    * @param purchaseCode   托进合同编码
    * @return   返回托进合同业主信息
     */
    @RequestMapping(value = "/getOwnerByPurchaseCode/{purchaseCode}", method = RequestMethod.GET)
    public ApiResult<PurchaseHouseOwnerResult> getOwnerByPurchaseCode(@PathVariable String purchaseCode){
        return purchaseHouseOwnerRestService.getOwnerByPurchaseCode(purchaseCode);
    }
    
    /**
     * 
    * @Title: updatePurchaseHouseOwner
    * @Description: TODO( 修改托进合同业主信息)
    * @author HeZeMin
    * @param param  托进合同业主信息
    * @return   返回修改结果
     */
    @PermissionsCode(code = "rest_purbase_houseowner_update")
    @RequestMapping(value = "/updatePurchaseHouseOwner", method = RequestMethod.POST)
    public ApiResult<Boolean> updatePurchaseHouseOwner(HttpServletRequest request, @RequestBody PurchaseHouseOwnerParam param){
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
        return purchaseHouseOwnerRestService.updatePurchaseHouseOwner(param);
    }
    /*********************get/set**********************/
    public PurchaseHouseOwnerRestService getPurchaseHouseOwnerRestService() {
        return purchaseHouseOwnerRestService;
    }
    public void setPurchaseHouseOwnerRestService(PurchaseHouseOwnerRestService purchaseHouseOwnerRestService) {
        this.purchaseHouseOwnerRestService = purchaseHouseOwnerRestService;
    }
}
