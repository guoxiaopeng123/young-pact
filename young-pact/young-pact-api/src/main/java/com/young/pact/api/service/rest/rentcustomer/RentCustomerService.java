package com.young.pact.api.service.rest.rentcustomer;

import com.young.common.domain.ApiResult;
import com.young.pact.api.domain.param.rest.rentcustomer.RentCustomerParam;
import com.young.pact.api.domain.result.rest.rentcustomer.RentCustomerResult;

/**
 * @描述 : 托出租客
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月1日 下午5:02:07
 */
public interface RentCustomerService {

    /**
     * 
     * @Title: saveRentCustomer
     * @Description: TODO(保存租客 +共同居住人)
     * @author GuoXiaoPeng
     * @return redis 缓存的key
     * @throws
     */
     ApiResult<String> saveRentCustomerRedis(RentCustomerParam rentCustomerParam);
     /**
      * 
     * @Title: getRentCustomer
     * @Description: TODO(查询已选择登记的租客信息)
     * @author GuoXiaoPeng
     * @param key redis 缓存的key
     * @return 已选择登记的租客信息
     * @throws
      */
     ApiResult<RentCustomerResult> getRentCustomerRedis(String key);
}
