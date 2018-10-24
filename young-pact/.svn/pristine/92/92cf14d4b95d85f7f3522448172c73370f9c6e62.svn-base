package com.young.pact.dao.pactrenttermination;

import java.util.List;

import com.young.common.exception.DaoException;
import com.young.pact.domain.pactrenttermination.PactRentTerminationDO;
import com.young.pact.domain.pactrenttermination.PactRentTerminationQuery;
import com.young.pact.domain.pactrenttermination.PactRentTerminationVO;

/**
 * 
* @ClassName: PactRentTerminationDao
* @Description: 解约协议dao接口
* @author LiuYuChi
* @date 2018年8月12日 上午11:41:24
*
 */
public interface PactRentTerminationDao {
	
	/**
	 * 
	* @Title: insertTermination
	* @Description: 新增解约协议信息
	* @author LiuYuChi
	* @param termination
	* @return
	* @throws
	 */
	Long insertTermination(PactRentTerminationDO termination) throws DaoException;
	
	/**
	 * 
	* @Title: listTermination
	* @Description:解约协议列表
	* @author LiuYuChi
	* @param query
	* @return
	* @throws
	 */
	List<PactRentTerminationVO> listTermination(PactRentTerminationQuery query) throws DaoException;
	
	/**
	 * 
	* @Title: countTermination
	* @Description: 查询解约协议数量
	* @author LiuYuChi
	* @param query
	* @return
	* @throws DaoException
	* @throws
	 */
	int countTermination(PactRentTerminationQuery query) throws DaoException;
	
	/**
	 * 
	* @Title: getTermination
	* @Description: 获取解约详情
	* @author LiuYuChi
	* @param query
	* @return
	* @throws DaoException
	* @throws
	 */
	PactRentTerminationVO getTermination(PactRentTerminationQuery query) throws DaoException;
	
	/**
	 * 
	* @Title: updateTermination
	* @Description: 修改解约协议
	* @author LiuYuChi
	* @param termination
	* @return
	* @throws DaoException
	* @throws
	 */
	int updateTermination (PactRentTerminationDO termination)throws DaoException;
	/**
    * @Title: listRentTerminationByParam
    * @Description: TODO( 条件查询托出解约协议集合 )
    * @author GuoXiaoPeng
    * @param pactRentTerminationQuery 协议审核状态
    * @return  托出解约协议集合
    * @throws DaoException
    * @throws 异常
     */

    List<PactRentTerminationVO> listRentTerminationByParam(PactRentTerminationQuery pactRentTerminationQuery)throws DaoException;
    /**
     * @Title: listUnsubmitted
     * @Description: TODO( 查询录入托出解约协议超过24小时未提交审核的托出解约协议 )
     * @author GuoXiaoPeng
     * @param pactRentTerminationQuery 合同状态草稿
     * @return 协议状态为草稿并且创建时间超过24小时的托出解约协议
     * @throws DaoException
     * @throws 异常
      */
    List<PactRentTerminationVO> listUnsubmitted(PactRentTerminationQuery pactRentTerminationQuery)throws DaoException;
    /**
     * @Title: listUnReview
     * @Description: TODO( 查询托出解约协议提交审核后超过24小时没有审核通过或驳回的托出解约协议)
     * @author GuoXiaoPeng
     * @param pactRentTerminationQuery 协议状态待审核
     * @return  托进合同集合
     * @throws DaoException
     * @throws 异常
      */
    List<PactRentTerminationVO> listUnReview(PactRentTerminationQuery pactRentTerminationQuery)throws DaoException;
    /**
     * 
    * @Title: getPermissions
    * @Description: TODO( 查询登录人有没有权限查看详情 )
    * @author GuoXiaoPeng
    * @param query 托出解约协议编码和权限作用域
    * @return 托出解约协议
    * @throws DaoException
    * @throws 异常
    */
    PactRentTerminationVO getPermissions(PactRentTerminationQuery query)throws DaoException;
}
