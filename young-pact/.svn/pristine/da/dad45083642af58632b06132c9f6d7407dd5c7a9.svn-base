package com.young.pact.dao.rentcontinued;

import java.util.List;

import com.young.common.exception.DaoException;
import com.young.pact.domain.rentcontinued.RentContinuedDO;
import com.young.pact.domain.rentcontinued.RentContinuedQuery;
import com.young.pact.domain.rentcontinued.RentContinuedVO;

/**
 * @描述 :  续签协议
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月10日 下午8:44:29
 */
public interface RentContinuedDao {

    /**
     * @Title: countAllProtocolByPactCode
     * @Description: TODO( 查询某个托出合同除了审核通过的转租，续租，调房，托出解约协议的总数)
     * @author GuoXiaoPeng
     * @param rentPactCode 托出合同编码
     * @return 总数
     * @throws DaoException
     * @throws 异常
      */
    Integer countAllProtocolByPactCode(String rentPactCode) throws DaoException;
    /**
    * @Title: saveRentContinued
    * @Description: TODO( 保存续签协议信息 )
    * @author GuoXiaoPeng
    * @param rentContinuedDO 续签协议信息
    * @return 记录标识
    * @throws 异常
     */
    Long saveRentContinued(RentContinuedDO rentContinuedDO)throws DaoException;
    /**
    * @Title: count
    * @Description: TODO( 查询续签列表总数 )
    * @author GuoXiaoPeng
    * @param query 查询条件
    * @return 列表总数
    * @throws DaoException 
    * @throws 异常
     */
    Integer count(RentContinuedQuery query)throws DaoException;
    /**
    * @Title: listParam
    * @Description: TODO( 查询续签列表)
    * @author GuoXiaoPeng
    * @param query 查询条件
    * @return 续签列表
    * @throws DaoException 
    * @throws 异常
     */
    List<RentContinuedVO> listParam(RentContinuedQuery query)throws DaoException;
    /**
    * @Title: getRentContinuedByRenewCode
    * @Description: TODO(根据续签协议编码查询详情 )
    * @author GuoXiaoPeng
    * @param renewCode 续签协议编码
    * @return 续签协议详情
    * @throws DaoException
    * @throws 异常
     */
    RentContinuedVO getRentContinuedByRenewCode(String renewCode)throws DaoException;
    /**
     * @Title: updateRentContinuedStateByCode
     * @Description: TODO( 修改续签协议审核状态 )
     * @author GuoXiaoPeng
     * @param rentContinuedDO 续签协议信息
     * @return 记录数
     * @throws 异常
      */
    Long updateRentContinuedStateByCode(RentContinuedDO rentContinuedDO) throws DaoException;
    /**
    * @Title: updateRentContinuedByRenewCode
    * @Description: TODO( 修改续签协议 )
    * @author GuoXiaoPeng
    * @param rentContinuedDO 续签协议编码
    * @return 记录数
    * @throws DaoException
    * @throws 异常
     */
    Integer updateRentContinuedByRenewCode(RentContinuedDO rentContinuedDO)throws DaoException;
    /**
    * @Title: removeRentContinuedByReletCode
    * @Description: TODO( 删除续签协议 )
    * @author GuoXiaoPeng
    * @param rentContinuedDO 续签协议 信息
    * @return 记录数
    * @throws DaoException
    * @throws 异常
     */
    Integer removeRentContinuedByReletCode(RentContinuedDO rentContinuedDO)throws DaoException;
    /**
     * @Title: listUnsubmitted
     * @Description: TODO( 查询录入托出续租协议超过24小时未提交审核的托出续租协议集合 )
     * @author GuoXiaoPeng 
     * @param rentContinuedQuery  合同状态草稿
     * @return 托出续租协议集合 
     * @throws DaoException
     * @throws 异常
      */
    List<RentContinuedVO> listUnsubmitted(RentContinuedQuery rentContinuedQuery)throws DaoException;
    /**
     * 
    * @Title: listUnReview
    * @Description: TODO( 查询托出续租协议提交审核后超过24小时没有审核通过或驳回的托出续租协议 )
    * @author GuoXiaoPeng
    * @param rentContinuedQuery 协议状态待审核
    * @return 托出续租协议 
    * @throws DaoException
    * @throws 异常
     */
    List<RentContinuedVO> listUnReview(RentContinuedQuery rentContinuedQuery)throws DaoException;
    /**
     * 
    * @Title: listRentcontinuedByParam
    * @Description: TODO( 条件查询托出续租协议集合 )
    * @author GuoXiaoPeng
    * @param rentContinuedQuery 协议审核状态
    * @return  托出续租协议集合
    * @throws DaoException
    * @throws 异常
     */
    List<RentContinuedVO> listRentcontinuedByParam(RentContinuedQuery rentContinuedQuery)throws DaoException;
    /**
     * 
    * @Title: getPermissions
    * @Description: TODO( 查询登录人有没有权限查看详情 )
    * @author GuoXiaoPeng 
    * @param query 续租协议编码和权限作用域
    * @return 续租协议
    * @throws DaoException
    * @throws 异常
     */
    RentContinuedVO getPermissions(RentContinuedQuery rentContinuedQuery)throws DaoException;

}
