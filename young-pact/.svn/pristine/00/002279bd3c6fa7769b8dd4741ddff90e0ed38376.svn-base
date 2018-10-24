package com.young.pact.controller.customer;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tools.common.springmvc.base.BaseController;
import com.tools.common.util.string.StringUtil;
import com.young.common.domain.ApiResult;
import com.young.pact.api.domain.result.rest.customer.CustomerResult;
import com.young.pact.api.service.rest.customer.CustomerService;

/**
 * 
* @ClassName: CustomerController
* @Description: TODO( 客源 )
* @author GuoXiaoPeng
* @date 2018年10月9日 上午9:47:18
*
 */
@RestController
@RequestMapping(value = "/customer", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class CustomerController extends BaseController{
    private static final Log LOG = LogFactory.getLog(CustomerController.class);
    private  CustomerService customerService;
    /**
    * @Title: getCustomerByDemandCode
    * @Description: TODO( 根据需求编码查询客源详情 )
    * @author GuoXiaoPeng
    * @param request
    * @param demandCode 需求编码 
    * @return 客源详情
    * @throws 异常
     */
    @RequestMapping(value = "/getCustomerByDemandCode/{demandCode}", method = RequestMethod.GET)
    public ApiResult<CustomerResult>  getCustomerByDemandCode(HttpServletRequest request,@PathVariable String demandCode){
        ApiResult<CustomerResult> result = new ApiResult<>();
        try {
            if(StringUtil.isNotBlank(demandCode)){
                result = customerService.getCustomerByDemandCode(demandCode);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
    public CustomerService getCustomerService() {
        return customerService;
    }
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }
}
