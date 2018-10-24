package com.young.pact.dao.purchasebase;

import java.util.List;

import com.young.common.exception.DaoException;
import com.young.pact.domain.purchasebase.PurchaseBaseDO;
import com.young.pact.domain.purchasebase.PurchaseBaseQuery;
import com.young.pact.domain.purchasebase.PurchaseBaseVO;

/**
 * 
* @ClassName: PurchaseBaseDao
* @Description: TODO( 托进合同)
* @author HeZeMin
* @date 2018年8月2日 下午8:49:34
*
 */
public interface PurchaseBaseDao {
    /**
     * 
    * @Title: savePurchaseBase
    * @Description: TODO( 保存托进合同信息)
    * @author HeZeMin
    * @param purchaseBaseDO 托进合同信息
    * @return   返回id
    * @throws   异常
     */
    Long savePurchaseBase(PurchaseBaseDO purchaseBaseDO) throws DaoException;
    /**
     * 
    * @Title: countByDeclareCode
    * @Description: TODO( 根据申报编码查询是否有未删除的托进合同)
    * @author HeZeMin
    * @param declareCode    申报编码
    * @return   返回托进合同个数
    * @throws   异常
     */
    int countByDeclareCode(String declareCode) throws DaoException;
    /**
     * 
    * @Title: countPurchaseBase
    * @Description: TODO( 托进合同列表总个数)
    * @author HeZeMin
    * @param query  查询条件
    * @return   返回总个数
    * @throws   异常
     */
    int countPurchaseBase(PurchaseBaseQuery query) throws DaoException;
    /**
     * 
    * @Title: listPurchaseBase
    * @Description: TODO( 托进合同列表)
    * @author HeZeMin
    * @param query  查询条件
    * @return   返回托进合同列表
    * @throws   异常
     */
    List<PurchaseBaseVO> listPurchaseBase(PurchaseBaseQuery query) throws DaoException;
    /**
     * 
    * @Title: getPurchaseBase
    * @Description: TODO( 根据托进合同编码查询托进合同详情)
    * @author HeZeMin
    * @param purchaseCode   托进合同编码
    * @return   返回托进合同详情
    * @throws   异常
     */
    PurchaseBaseVO getPurchaseBase(String purchaseCode) throws DaoException;
    /**
     * 
    * @Title: removePurchaseBase
    * @Description: TODO( 根据托进合同编码删除托进合同--逻辑删除)
    * @author HeZeMin
    * @param purchaseBaseDO   托进合同编码
    * @return   返回影响行数
    * @throws   异常
     */
    int removePurchaseBase(PurchaseBaseDO purchaseBaseDO) throws DaoException;
    /**
     * 
    * @Title: updateState
    * @Description: TODO( 修改托进合同审核状态)
    * @author HeZeMin
    * @param purchaseBaseDO 审核信息
    * @return   返回修改结果
    * @throws   异常
     */
    int updateState(PurchaseBaseDO purchaseBaseDO) throws DaoException;
    /**
     * 
    * @Title: updatePurchaseBase
    * @Description: TODO( 修改托进合同)
    * @author HeZeMin
    * @param purchaseBaseDO 托进合同信息
    * @return   返回修改结果
    * @throws   异常
     */
    int updatePurchaseBase(PurchaseBaseDO purchaseBaseDO) throws DaoException;
    /**
    * @Title: listPurchaseBaseByParam
    * @Description: TODO( 根据申报编码查询拖进合同集合 )
    * @author GuoXiaoPeng
    * @param purchaseBaseQuery 申报编码
    * @return 拖进合同集合
    * @throws DaoException
    * @throws 异常
     */
    List<PurchaseBaseVO> listPurchaseBaseByParam(PurchaseBaseQuery purchaseBaseQuery)throws DaoException;
    /**
     * 
    * @Title: updateTerminationState
    * @Description: TODO( 修改托进合同解约状态)
    * @author HeZeMin
    * @param purchaseBaseDO 解约信息
    * @return   返回修改结果
    * @throws   异常
     */
    int updateTerminationState(PurchaseBaseDO purchaseBaseDO) throws DaoException;
    /**
     * 
    * @Title: updateStandard
    * @Description: TODO( 修改托进合同标准化状态)
    * @author HeZeMin
    * @param purchaseBaseDO 标准化信息
    * @return   返回修改结果
    * @throws   异常
     */
    int updateStandard(PurchaseBaseDO purchaseBaseDO) throws DaoException;
    /**
    * @Title: getPermissions
    * @Description: TODO(查看登录人有没有某个拖进合同的查看权限 )
    * @author GuoXiaoPeng
    * @param query 数据作用域和拖进合同编码
    * @return 拖进合同
    * @throws DaoException
    * @throws 异常
     */
    PurchaseBaseVO getPermissions(PurchaseBaseQuery query)throws DaoException;
    /**
     * @Title: listUnsubmitted
     * @Description: TODO( 查询录入合同超过24小时未提交审核的托进合同 )
     * @author GuoXiaoPeng
     * @param purchaseBaseQuery 合同状态草稿
     * @return 合同状态为草稿并且创建时间超过24小时的托进合同
     * @throws DaoException
     * @throws 异常
      */
    List<PurchaseBaseVO> listUnsubmitted(PurchaseBaseQuery purchaseBaseQuery)throws DaoException;
    /**
     * @Title: listUnReview
     * @Description: TODO( 查询托进合同提交审核后超过24小时没有审核通过或驳回的托进合同 )
     * @author GuoXiaoPeng
     * @param purchaseBaseQuery 合同状态待审核
     * @return  托进合同集合
     * @throws DaoException
     * @throws 异常
      */
    List<PurchaseBaseVO> listUnReview(PurchaseBaseQuery purchaseBaseQuery)throws DaoException;
    /**
     * @Title: listUnCreateRoom
     * @Description: TODO(  查询托进合同审核通过后超过24小时未创建房间的托进合同集合)
     * @author GuoXiaoPeng
     * @return 托进合同集合
     * @throws DaoException
     * @throws 异常
      */
    List<PurchaseBaseVO> listUnCreateRoom()throws DaoException;
    /**
     * @Title: listUnCreateRoom
     * @Description: TODO(  查询合同驳回后超过24小时未重新提交审核托进合同集合)
     * @author GuoXiaoPeng
     * @return 托进合同集合
     * @throws DaoException
     * @throws 异常
      */
    List<PurchaseBaseVO> listRejectUnApply()throws DaoException;
    /**
     * 
    * @Title: listPurchaseExpir
    * @Description: TODO( 查询托进到期时间距离今日还有60天的的托进合同 )
    * @author GuoXiaoPeng
    * @return 托进合同 集合
    * @throws DaoException
    * @throws 异常
     */
    List<PurchaseBaseVO> listPurchaseExpir()throws DaoException;
}
