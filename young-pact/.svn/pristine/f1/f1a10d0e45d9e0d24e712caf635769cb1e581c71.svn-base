package com.young.pact.dao.rentcustomer;

import com.young.common.exception.DaoException;
import com.young.pact.domain.rentcustomer.RentCustomerDO;
import com.young.pact.domain.rentcustomer.RentCustomerVO;

/**
 * @描述 : 托出租客基本操作dao
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月2日 下午9:58:04
 */
public interface RentCustomerDao {
    
    /**
    * @Title: saveRentCustomer
    * @Description: TODO( 保存托出租客)
    * @author GuoXiaoPeng
    * @param rentCustomerDO 托出租客
    * @return 记录标识
    * @throws DaoException
    */
    Long saveRentCustomer(RentCustomerDO rentCustomerDO) throws DaoException;
    /**
    * @Title: getRentCustomerByPactCode
    * @Description: TODO(根据合同编码查询托出租客 )
    * @author GuoXiaoPeng 
    * @param pactCode 合同编码
    * @return 托出租客信息
    * @throws 异常
     */
    RentCustomerVO getRentCustomerByPactCode(String pactCode)throws DaoException;
      /**
       * 
      * @Title: updateRentCustomerByPactCode
      * @Description: TODO( 修改托出合同租客 )
      * @author GuoXiaoPeng
      * @param renter 托出合同租客信息
      * @return 成功true 失败false
      * @throws 异常
      */
    Long updateRentCustomerByPactCode(RentCustomerDO renter)throws DaoException;
    /**
    * @Title: removeRentCustomer
    * @Description: TODO(根据托出合同编码删除托出合同租客 )
    * @author GuoXiaoPeng
    * @param rentPactCode 托出合同编码
    * @throws 异常
    */
    Integer removeRentCustomer(String rentPactCode)throws DaoException ;
    
}
