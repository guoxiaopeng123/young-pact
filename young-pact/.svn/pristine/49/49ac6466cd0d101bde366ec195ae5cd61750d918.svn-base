package com.young.pact.dao.rentbase;

import java.util.List;

import com.young.common.exception.DaoException;
import com.young.pact.domain.commonbelonger.CommonBelongerVO;
import com.young.pact.domain.rentbase.RentBaseDO;
import com.young.pact.domain.rentbase.RentBaseQuery;
import com.young.pact.domain.rentbase.RentBaseVO;

/**
 * @描述 : 
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月3日 上午11:21:16
 */
public interface RentBaseDao{
    /**
    * @Title: saveRentBase
    * @Description: TODO( 保存托出合同)
    * @author GuoXiaoPeng
    * @param rentBaseDO 托出合同基本信息
    * @return 记录标识
    * @throws
    */
    Long saveRentBase(RentBaseDO rentBaseDO)throws DaoException;
    /**
    * @Title: count
    * @Description: TODO( 查询托出合同总数)
    * @author GuoXiaoPeng
    * @param query  条件查询
    * @return 总数
    * @throws
    */
    Integer count(RentBaseQuery query)throws DaoException;
    /**
    * @Title: listParam
    * @Description: TODO( 根据条件分页查询托出合同列表)
    * @author GuoXiaoPeng
    * @param query 条件查询
    * @return 托出合同列表信息
    * @throws
    */
    List<RentBaseVO> listParam(RentBaseQuery query)throws DaoException;
    
    /**
     * @Title: getRentBaseByCode
     * @Description: TODO( 托出合同详情)
     * @author GuoXiaoPeng
     * @param rentPactCode 托出合同编码
     * @return 托出合同详情
     * @throws
     */
     RentBaseVO getRentBaseByCode(String rentPactCode)throws DaoException;
  
     /**
     * @Title: updateRenterByCode
     * @Description: TODO( 修改托出合同)
     * @author GuoXiaoPeng
     * @param rentBaseDO 托出合同信息
     * @return 记录标识
     * @throws
      */
     Long updateRentBaseByCode(RentBaseDO rentBaseDO)throws DaoException;
     /**
     * @Title: updateRenterStateByCode
     * @Description: TODO(修改托出合同审核状态 )
     * @author GuoXiaoPeng
     * @param rentBaseDO 托出合同
     * @return 记录标识
     * @throws
     */
     Long updateRentBaseStateByCode(RentBaseDO rentBaseDO)throws DaoException;
     /**
     * @Title: deleteRenterStateByCode
     * @Description: TODO( 删除托出合同)
     * @author GuoXiaoPeng
     * @param code 托出合同编码
     * @return 记录标识
     * @throws
      */
     Long deleteRentBaseByCode(RentBaseDO rentBaseDO)throws DaoException;
     /**
     * @Title: countRentBaseByCustomer
     * @Description: TODO(根据客源编码查询审核通过的托出合同总数 )
     * @author GuoXiaoPeng
     * @param rentBaseQuery 客源编码
     * @return 托出合同总数
     * @throws DaoException
     * @throws 异常
      */
     int countRentBaseByCustomer(RentBaseQuery rentBaseQuery)throws DaoException;
     /**
      * @Title: getPermissions
      * @Description: TODO( 查看登录人有没有这个托出合同的权限 )
      * @author GuoXiaoPeng
      * @param rentBaseParam 托出合同编码和权限作用域
      * @return 有权限返回true，没有权限返回false
      * @throws DaoException
      * @throws 异常
      */
     RentBaseVO getPermissions(RentBaseQuery query)throws DaoException;
     /**
      * 
     * @Title: getRentAfterBelonger
     * @Description: TODO( 根据托出合同编码查询托进租后管家 )
     * @author GuoXiaoPeng
     * @param pactCode 托出合同编码
     * @return 租后管家 
     * @throws DaoException
     * @throws 异常
      */
     CommonBelongerVO getRentAfterBelonger(String pactCode)throws DaoException;
     /**
      * @Title: listUnsubmitted
      * @Description: TODO( 查询录入托出合同超过24小时未提交审核的托出合同 )
      * @author GuoXiaoPeng
      * @param purchaseBaseQuery 合同状态草稿
      * @return 合同状态为草稿并且创建时间超过24小时的托出合同
      * @throws DaoException
      * @throws 异常
       */
     List<RentBaseVO> listUnsubmitted(RentBaseQuery rentBaseQuery)throws DaoException;
     /**
      * @Title: listUnReview
      * @Description: TODO( 查询托出合同提交审核后超过24小时没有审核通过或驳回的托出合同 )
      * @author GuoXiaoPeng
      * @param purchaseBaseQuery 合同状态待审核
      * @return  托出合同集合
      * @throws DaoException
      * @throws 异常
       */
     List<RentBaseVO> listUnReview(RentBaseQuery rentBaseQuery)throws DaoException;
     /**
      * @Title: listRentBaseByParam
      * @Description: TODO( 根据条件查询 托出合同集合)
      * @author GuoXiaoPeng
      * @param rentBaseQuery 合同状态
      * @return 托出合同集合
      * @throws DaoException
      * @throws 异常
       */
     List<RentBaseVO> listRentBaseByParam(RentBaseQuery rentBaseQuery)throws DaoException;
     /**
     * @Title: listRentExpir
     * @Description: TODO( 查询托出合同到期时间距离今日还有60天的的托出合同  )
     * @author GuoXiaoPeng
     * @return 托出合同集合
     * @throws DaoException
     * @throws 异常
      */
     List<RentBaseVO> listRentExpir()throws DaoException;
}
