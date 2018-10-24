package com.young.pact.dao.pactrenttransfer;

import java.util.List;

import com.young.common.exception.DaoException;
import com.young.pact.domain.pactrenttransfer.PactRentTransferDO;
import com.young.pact.domain.pactrenttransfer.PactRentTransferQuery;
import com.young.pact.domain.pactrenttransfer.PactRentTransferVO;

/**
 * 
* @ClassName: PactRentTransferDao
* @Description: 调房协议dao接口
* @author LiuYuChi
* @date 2018年8月8日 下午1:47:51
*
 */
public interface PactRentTransferDao {

	/**
	 * 
	* @Title: saveTransfer
	* @Description: 保存调房协议基本信息
	* @author LiuYuChi
	* @param pactRentTransferDO
	* @return
	* @throws DaoException
	* @throws
	 */
	Long saveTransfer(PactRentTransferDO pactRentTransferDO) throws DaoException;
	
	/**
	 * 
	* @Title: getTransfer
	* @Description: 查询调房协议详情
	* @author LiuYuChi
	* @param transferCode
	* @return
	* @throws
	 */
	PactRentTransferVO getTransfer(String transferCode)throws DaoException;
	
	/**
	 * 
	* @Title: updateTransfer
	* @Description: 修改调房协议
	* @author LiuYuChi
	* @param pactRentTransferDO
	* @return
	* @throws
	 */
	int updateTransfer (PactRentTransferDO pactRentTransferDO) throws DaoException;
	
	/**
	 * 
	* @Title: listPactRentTransfer
	* @Description: 查询调房协议列表
	* @author LiuYuChi
	* @param query
	* @return
	* @throws DaoException
	* @throws
	 */
	List<PactRentTransferVO> listPactRentTransfer(PactRentTransferQuery query)throws DaoException ;
	
	/**
	 * 
	* @Title: countTransfer
	* @Description: 查询数量
	* @author LiuYuChi
	* @param query
	* @return
	* @throws DaoException
	* @throws
	 */
	int countTransfer(PactRentTransferQuery query) throws DaoException;
	/**
    * @Title: listTransferByParam
    * @Description: TODO( 条件查询托出调房协议集合 )
    * @author GuoXiaoPeng
    * @param rentTransferQuery 协议审核状态
    * @return 托出调房协议集合
    * @throws DaoException
    * @throws 异常
     */
    List<PactRentTransferVO> listTransferByParam(PactRentTransferQuery rentTransferQuery)throws DaoException ;
    /**
     * @Title: listUnReview
     * @Description: TODO( 查询托出调房协议提交审核后超过24小时没有审核通过或驳回的托出调房协议集合 )
     * @author GuoXiaoPeng
     * @param rentTransferQuery 协议状态待审核
     * @return 托出调房协议集合
     * @throws DaoException
     * @throws 异常
      */
    List<PactRentTransferVO> listUnReview(PactRentTransferQuery rentTransferQuery)throws DaoException ;
    /**
     * @Title: listUnsubmitted
     * @Description: TODO( 查询录入托出调房协议超过24小时未提交审核的托出续调房议集合 )
     * @author GuoXiaoPeng
     * @param rentTransferQuery 合同状态草稿
     * @return 托出调房协议集合 
     * @throws DaoException
     * @throws 异常
      */
    List<PactRentTransferVO> listUnsubmitted(PactRentTransferQuery rentTransferQuery)throws DaoException ;
    /**
     * 
    * @Title: getPermissions
    * @Description: TODO( 查询登录人有没有权限查看详情 )
    * @author GuoXiaoPeng
    * @param query 协议编码
    * @return 托出调房协议
    * @throws DaoException
    * @throws 异常
     */
    PactRentTransferVO getPermissions(PactRentTransferQuery query)throws DaoException ;
}
