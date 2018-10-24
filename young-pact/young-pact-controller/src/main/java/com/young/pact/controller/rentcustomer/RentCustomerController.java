package com.young.pact.controller.rentcustomer;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tools.common.springmvc.base.BaseController;
import com.young.common.domain.ApiResult;
import com.young.pact.api.domain.param.rest.rentcustomer.RentCustomerParam;
import com.young.pact.api.domain.result.rest.rentcustomer.RentCustomerResult;
import com.young.pact.api.service.rest.rentcustomer.RentCustomerService;
import com.young.pact.common.constant.LoginConsts;

/**
 * @描述 : 
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月1日 下午6:00:16
 */
@RestController
@RequestMapping("/rentcustomer")
public class RentCustomerController extends BaseController{

    private static Log LOG = LogFactory.getLog(RentCustomerController.class);
    private  RentCustomerService rentCustomerService;
    /**
    * @Title: saveRentCustomer
    * @Description: TODO(保存租客 )
    * @author GuoXiaoPeng
    * @param rentCustomerParam 租客+共同居住人
    * @return 返回结果
    */
    @RequestMapping(value = "/saveRentCustomer", method = RequestMethod.POST)
    public ApiResult<String> saveRentCustomer(HttpServletRequest request, @RequestBody RentCustomerParam rentCustomerParam){
        ApiResult<String> result = new ApiResult<>();
        try {
            if(null!=rentCustomerParam){
                String pin = (String) request.getAttribute(LoginConsts.PIN);
                String ip = super.getRemoteIPs(request);
                rentCustomerParam.setCreateName(pin);
                rentCustomerParam.setIp(ip);
            }
            result = rentCustomerService.saveRentCustomerRedis(rentCustomerParam);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
    /**
     * 
    * @Title: getRentCustomer
    * @Description: TODO(根据缓存key租客详情 )
    * @author GuoXiaoPeng
    * @param key 缓存key
    * @return 租客详情
     */
    @RequestMapping(value = "/getRentCustomer/{redisKey}", method = RequestMethod.GET)
    public  ApiResult<RentCustomerResult> getRentCustomer(HttpServletRequest request,@PathVariable String redisKey){
        ApiResult<RentCustomerResult> result = new ApiResult<RentCustomerResult>();
        try {
            result = rentCustomerService.getRentCustomerRedis(redisKey);
        } catch (Exception e) {
            // TODO: handle exception
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
    public RentCustomerService getRentCustomerService() {
        return rentCustomerService;
    }
    public void setRentCustomerService(RentCustomerService rentCustomerService) {
        this.rentCustomerService = rentCustomerService;
    }
    
}
