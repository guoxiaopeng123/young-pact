package com.young.pact.manager.rentcustomer;

import com.young.pact.domain.rentcustomer.CustomerOwnerDO;
import com.young.pact.domain.rentcustomer.CustomerOwnerVO;
import com.young.pact.domain.rentcustomer.RentCustomerDO;
import com.young.pact.domain.rentcustomer.RentCustomerVO;

/**
 * @描述 : 托出租客
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月1日 下午5:05:41
 */
public interface RentCustomerManager {

    /**
     * 
    * @Title: saveRentCustomer
    * @Description: TODO( 保存租客+共同居住人)
    * @author GuoXiaoPeng
    * @param rentCustomerDO 租客
    * @param list 共同居住人集合
    * @return 成功 true 失败false
     */
    void saveRentCustomerRedis(CustomerOwnerDO  customerOwnerDO);
    /**
     * 
    * @Title: getRentCustomer
    * @Description: TODO(根据缓存key获取托出租客 )
    * @author GuoXiaoPeng
    * @param key 缓存key
    * @return 返回结果
    */
    CustomerOwnerVO getRentCustomerRedis(String key);
    /**
    * @Title: saveRentCustomer
    * @Description: TODO( 保存租客)
    * @author GuoXiaoPeng
    * @param rentCustomerDO 租客信息
    * @return 记录标识
    * @throws
    */
    Long saveRentCustomer(RentCustomerDO rentCustomerDO);
    /**
     * @Title: getRentCustomerByPactCode
     * @Description: TODO(根据合同编码查询托出租客 )
     * @author GuoXiaoPeng 
     * @param pactCode 合同编码
     * @return 托出租客信息
     * @throws
      */
     RentCustomerVO getRentCustomerByPactCode(String pactCode);
}
