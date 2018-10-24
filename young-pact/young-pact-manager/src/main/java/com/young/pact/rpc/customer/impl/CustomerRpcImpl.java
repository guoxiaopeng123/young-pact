package com.young.pact.rpc.customer.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.young.common.dict.CommonEnum;
import com.young.common.domain.ApiResult;
import com.young.customer.api.domain.result.rpc.CustomerRpcResult;
import com.young.customer.api.service.rpc.CustomerRpcService;
import com.young.pact.common.constant.ErrorPactConsts;
import com.young.pact.common.exception.PactManagerExcepion;
import com.young.pact.rpc.customer.CustomerRpc;

/**
 * @描述 : 客源rpc
 * @创建者 : guoxiaopeng
 * @创建时间 : 2018年8月23日 下午8:28:56
 */
@Component("customerRpc")
public class CustomerRpcImpl implements CustomerRpc{

    private static final Log LOG = LogFactory.getLog(CustomerRpcImpl.class);
    private CustomerRpcService customerRpcService;
    @Override
    public CustomerRpcResult getCustomerByDemandCode(String demandCode) {
        CustomerRpcResult customerRpcResult = new CustomerRpcResult(); 
        ApiResult<CustomerRpcResult> result = customerRpcService.getCustomer(demandCode);
        if(CommonEnum.REQUEST_SUCCESS.getCode().equals(result.getCode())){
            customerRpcResult = result.getData();
        }else{
            LOG.error(ErrorPactConsts.QUERY_CUSTOMER_BYDEMANDCODE_ERROR + ",需求编码：" + demandCode);
            throw new PactManagerExcepion(ErrorPactConsts.QUERY_CUSTOMER_BYDEMANDCODE_ERROR);
        }
        return customerRpcResult;
    }
    public CustomerRpcService getCustomerRpcService() {
        return customerRpcService;
    }
    public void setCustomerRpcService(CustomerRpcService customerRpcService) {
        this.customerRpcService = customerRpcService;
    }
   
    
}
