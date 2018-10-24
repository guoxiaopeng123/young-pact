package com.young.pact.controller.purchaserentfree;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tools.common.springmvc.base.BaseController;
import com.young.common.annotation.PermissionsCode;
import com.young.common.domain.ApiResult;
import com.young.pact.api.domain.result.rest.purchaserentfree.PurchaseRentfreeResult;
/**
 * 
* @ClassName: PurchaseRentfreeController
* @Description: TODO( 托进合同免租期)
* @author HeZeMin
* @date 2018年8月6日 下午4:30:55
*
 */
import com.young.pact.api.service.rest.purchaserentfree.PurchaseRentfreeRestService;
@RestController
@RequestMapping(value = "/purchaserentfree", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class PurchaseRentfreeController extends BaseController {
    /*********************声明区**********************/
    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog(PurchaseRentfreeController.class);
    private PurchaseRentfreeRestService purchaseRentfreeRestService;
    /*********************方法区**********************/
    
    /**
     * 
    * @Title: listPurchaseRentfree
    * @Description: TODO( 根据托进合同编码查询托进合同免租期信息集合)
    * @author HeZeMin
    * @param purchaseCode   托进合同编码
    * @return   返回托进合同免租期信息集合
     */
    @PermissionsCode(code = "rest_purbase_rentfree_page")
    @RequestMapping(value = "/listPurchaseRentfree/{purchaseCode}", method = RequestMethod.GET)
    public ApiResult<List<PurchaseRentfreeResult>> listPurchaseRentfree(@PathVariable String purchaseCode){
        return purchaseRentfreeRestService.listPurchaseRentfree(purchaseCode);
    }
    
    /*********************get/set**********************/
    public PurchaseRentfreeRestService getPurchaseRentfreeRestService() {
        return purchaseRentfreeRestService;
    }
    public void setPurchaseRentfreeRestService(PurchaseRentfreeRestService purchaseRentfreeRestService) {
        this.purchaseRentfreeRestService = purchaseRentfreeRestService;
    }
}
