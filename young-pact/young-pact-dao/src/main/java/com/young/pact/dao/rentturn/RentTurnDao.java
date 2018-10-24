package com.young.pact.dao.rentturn;

import java.util.List;

import com.young.common.exception.DaoException;
import com.young.pact.domain.rentturn.RentTurnDO;
import com.young.pact.domain.rentturn.RentTurnQuery;
import com.young.pact.domain.rentturn.RentTurnVO;

/**
 * @描述 : 转租协议
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月6日 下午8:55:39
 */
public interface RentTurnDao {

    /**
    * @Title: saveRentTurn
    * @Description: TODO( 保存转租协议)
    * @author GuoXiaoPeng
    * @param rentTurnDO 转租协议信息
    * @return 记录标识
    * @DaoException dao异常
    * @throws 异常
     */
    Long saveRentTurn(RentTurnDO rentTurnDO) throws DaoException;
    /**
    * @Title: count
    * @Description: TODO( 条件查询记录总数)
    * @author GuoXiaoPeng
    * @param query 查询条件
    * @return 转租协议总记录数
    * @throws DaoException
    * @throws 异常
     */
    Integer count(RentTurnQuery query)throws DaoException;
    /**
    * @Title: listParam
    * @Description: TODO(条件查询转租协议列表)
    * @author GuoXiaoPeng 
    * @param query 查询条件
    * @return  转租协议记录
    * @throws 异常
     */
    List<RentTurnVO> listParam(RentTurnQuery query)throws DaoException;
    /**
    * @Title: getRentTurnByReletCode
    * @Description: TODO( 根据协议编码查询协议详情)
    * @author GuoXiaoPeng
    * @param reletCode 协议编码
    * @return 协议详情
    * @throws DaoException
    * @throws 异常
     */
    RentTurnVO getRentTurnByReletCode(String reletCode)throws DaoException;
    /**
    * @Title: updateRentBaseStateByCode
    * @Description: TODO( 修改协议审核状态 )
    * @author GuoXiaoPeng 
    * @param rentTurnDO 转租协议信息
    * @return 记录标识
    * @throws DaoException
    * @throws 异常
     */
    Long updateRentTurnStateByCode(RentTurnDO rentTurnDO)throws DaoException;
    /**
    * @Title: updateRentTurn
    * @Description: TODO(修改协议 )
    * @author GuoXiaoPeng
    * @param rentTurnDO
    * @return 记录标识
    * @throws DaoException
    * @throws 异常
     */
    Long updateRentTurn(RentTurnDO rentTurnDO)throws DaoException;
    /**
    * @Title: removeRentTurnByReletCode
    * @Description: TODO( 逻辑删除转租协议)
    * @author GuoXiaoPeng
    * @param rentTurnDO 转租
    * @return 记录标识
    * @throws DaoException 
    * @throws 异常
     */
    Long removeRentTurnByReletCode(RentTurnDO rentTurnDO)throws DaoException;
    /**
     * @Title: listRentTurnByParam
     * @Description: TODO( 条件查询托出合同转租协议集合 )
     * @author GuoXiaoPeng 
     * @param rentTurnQuery 协议审核状态
     * @return 托出合同转租协议集合
     * @throws DaoException 
     * @throws 异常
      */
    List<RentTurnVO> listRentTurnByParam(RentTurnQuery rentTurnQuery)throws DaoException;
    /**
     * @Title: listUnsubmitted
     * @Description: TODO( 查询录入托出合同转租协议超过24小时未提交审核的托出合同转租协议 )
     * @author GuoXiaoPeng
     * @param rentTurnQuery 协议状态为草稿并且创建时间超过24小时的托出解约协议
     * @return 协议状态为草稿并且创建时间超过24小时的托出合同转租协议
     * @throws DaoException 
     * @throws 异常
      */
    List<RentTurnVO> listUnsubmitted(RentTurnQuery rentTurnQuery)throws DaoException;
    /**
     * @Title: listUnReview
     * @Description: TODO( 查询托出合同转租协议提交审核后超过24小时没有审核通过或驳回的托出合同转租协议 )
     * @author GuoXiaoPeng
     * @param rentTurnQuery 协议状态待审核
     * @return 托出合同转租协议
     * @throws DaoException 
     * @throws 异常
      */
    List<RentTurnVO> listUnReview(RentTurnQuery rentTurnQuery)throws DaoException;
    /**
     * 
    * @Title: getPermissions
    * @Description: TODO( 查询登录人有没有权限查看详情 )
    * @author GuoXiaoPeng 
    * @param query 转租协议编码和权限作用域
    * @return 有权限返回true，没有权限返回false
    * @throws DaoException 
    * @throws 异常
     */
    RentTurnVO getPermissions(RentTurnQuery query)throws DaoException;
}
