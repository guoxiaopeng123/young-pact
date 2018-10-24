package com.young.pact.service.customer.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.tools.common.util.convert.BeanUtils;
import com.young.common.dict.CommonEnum;
import com.young.common.domain.ApiResult;
import com.young.customer.api.domain.result.rpc.CustomerRpcResult;
import com.young.pact.api.domain.result.rest.customer.CustomerResult;
import com.young.pact.api.service.rest.customer.CustomerService;
import com.young.pact.common.exception.PactManagerExcepion;
import com.young.pact.rpc.customer.CustomerRpc;

/**
 * @描述 : 
 * @创建者 : GuoXiaoPeng
 * @创建时间 : 2018年9月13日 上午10:42:32
 */
@Service("customerService")
public class CustomerServiceImpl  implements CustomerService{
    private static final Log LOG = LogFactory.getLog(CustomerServiceImpl.class); 
    private CustomerRpc customerRpc;
    @Override
    public ApiResult<CustomerResult> getCustomerByDemandCode(String demandCode) {
        ApiResult<CustomerResult> result = new ApiResult<>();
        CustomerResult customerResult = new CustomerResult();
        try {
            CustomerRpcResult customerRpcResult = customerRpc.getCustomerByDemandCode(demandCode);
            BeanUtils.copyProperties(customerRpcResult, customerResult);
            result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
            result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
            result.setData(customerResult);
        } catch(PactManagerExcepion e){
            LOG.error(e.getMessage(), e);
            result.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            result.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            result.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            result.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        }
        return result;
    }
    public CustomerRpc getCustomerRpc() {
        return customerRpc;
    }
    public void setCustomerRpc(CustomerRpc customerRpc) {
        this.customerRpc = customerRpc;
    }

}
