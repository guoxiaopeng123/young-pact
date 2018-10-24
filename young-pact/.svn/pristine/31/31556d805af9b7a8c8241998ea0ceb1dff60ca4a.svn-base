package com.young.pact.dao.purchasetermination;

import java.util.List;

import com.young.common.exception.DaoException;
import com.young.pact.domain.purchasetermination.PurchaseTerminationBaseVO;
import com.young.pact.domain.purchasetermination.PurchaseTerminationDO;
import com.young.pact.domain.purchasetermination.PurchaseTerminationEditVO;
import com.young.pact.domain.purchasetermination.PurchaseTerminationQuery;
import com.young.pact.domain.purchasetermination.PurchaseTerminationVO;

/**
 * 
* @ClassName: PurchaseTerminationDao
* @Description: 托进解约协议
* @author SunYiXuan
* @date 2018年8月9日 下午4:32:46
*
 */
public interface PurchaseTerminationDao {

    /**
     * 
    * @Title: countByPurchaseCode
    * @Description: 根据合同编码查询解约协议个数
    * @author SunYiXuan
    * @param purchaseCode
    * @return
    * @throws DaoException
    * @throws
     */
    int countByPurchaseCode(String purchaseCode) throws DaoException;

    
    /**
     * 
    * @Title: savePurchaseTermination
    * @Description: 添加托进解约协议
    * @author SunYiXuan
    * @param purchaseTerminationDO
    * @return
    * @throws DaoException
    * @throws
     */
    Long savePurchaseTermination(PurchaseTerminationDO purchaseTerminationDO) throws DaoException;


    /**
     * 
    * @Title: countPurchaseTermination
    * @Description: 查询解约协议总数
    * @author SunYiXuan
    * @param query
    * @return
    * @throws DaoException
    * @throws
     */
    int countPurchaseTermination(PurchaseTerminationQuery query) throws DaoException;


    /**
     * 
    * @Title: listPurchaseTermination
    * @Description: 查询解约协议列表
    * @author SunYiXuan
    * @param query
    * @return
    * @throws
     */
    List<PurchaseTerminationVO> listPurchaseTermination(PurchaseTerminationQuery query) throws DaoException;


    /**
     * 
    * @Title: getPurchaseTerminationBase
    * @Description: 查询解约协议基本信息
    * @author SunYiXuan
    * @param terminationCode
    * @return
    * @throws
     */
    PurchaseTerminationBaseVO getPurchaseTerminationBase(String terminationCode) throws DaoException;


    /**
     * 
    * @Title: getPurchaseTerminationEdit
    * @Description: 查询解约协议编辑页信息
    * @author SunYiXuan
    * @param terminationCode
    * @return
    * @throws
     */
    PurchaseTerminationEditVO getPurchaseTerminationEdit(String terminationCode) throws DaoException;


    /**
     * 
    * @Title: getPurchaseTermination
    * @Description: 查询拖进合同解约协议信息
    * @author SunYiXuan
    * @param terminationCode
    * @return
    * @throws DaoException
    * @throws
     */
    PurchaseTerminationVO getPurchaseTermination(String terminationCode) throws DaoException;


    /**
     * 
    * @Title: updatePurchaseTermination
    * @Description: 修改拖进合同解约协议
    * @author SunYiXuan
    * @param terminationDO
    * @return
    * @throws DaoException
    * @throws
     */
    int updatePurchaseTermination(PurchaseTerminationDO terminationDO) throws DaoException;


    /**
     * 
    * @Title: removePurchaseTermination
    * @Description: 逻辑删除拖进合同解约协议
    * @author SunYiXuan
    * @param terminationDO
    * @return
    * @throws DaoException
    * @throws
     */
    int removePurchaseTermination(PurchaseTerminationDO terminationDO) throws DaoException;


    /**
     * 
    * @Title: updateState
    * @Description: 修改协议状态
    * @author SunYiXuan
    * @param terminationDO
    * @return
    * @throws DaoException
    * @throws
     */
    int updateState(PurchaseTerminationDO terminationDO) throws DaoException;
    /**
     * @Title: getPermission
     * @Description: TODO( 查看登录人有没有这个托进合同解约协议的权限 )
     * @author GuoXiaoPeng
     * @param param 数据权限作用域和托进合同解约协议编码
     * @return 托进合同解约协议
    */

    PurchaseTerminationBaseVO getPermissions(PurchaseTerminationQuery query)throws DaoException;

    /**
     * 
    * @Title: listPurTerminationByParam
    * @Description: TODO( 根据查询条件查询托进合同解约协议集合 )
    * @author GuoXiaoPeng
    * @param purchaseBaseQuery  审核状态
    * @return 托进合同解约协议集合 
    * @throws DaoException
    * @throws 异常
    */
    List<PurchaseTerminationVO> listPurTerminationByParam(PurchaseTerminationQuery purchaseBaseQuery)throws DaoException;

    /**
     * @Title: listUnReview
     * @Description: TODO(  查询托进解约协议提交审核后超过24小时没有审核通过或驳回的托进解约协议)
     * @author GuoXiaoPeng
     * @param purchaseTerminationQuery 协议状态待审核
     * @return 托进解约协议集合
     * @throws DaoException
     * @throws 异常
      */
    List<PurchaseTerminationVO> listUnReview(PurchaseTerminationQuery purchaseTerminationQuery)throws DaoException;

    /**
     * @Title: listUnsubmitted
     * @Description: TODO( 查询录入托进解约协议超过24小时未提交审核的托进解约协议)
     * @author GuoXiaoPeng
     * @param purchaseTerminationQuery 托进解约协议状态草稿
     * @return 托进解约协议状态为草稿并且创建时间超过24小时的托进合同
     * @throws 异常
      */
    List<PurchaseTerminationVO> listUnsubmitted(PurchaseTerminationQuery purchaseTerminationQuery)throws DaoException;

}
