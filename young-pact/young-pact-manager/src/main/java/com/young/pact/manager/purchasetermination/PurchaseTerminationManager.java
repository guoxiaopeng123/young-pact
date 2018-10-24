package com.young.pact.manager.purchasetermination;

import java.util.List;

import com.young.common.page.PageBean;
import com.young.pact.domain.commonbelonger.CommonBelongerDO;
import com.young.pact.domain.commongoods.CommonGoodsDO;
import com.young.pact.domain.commonmeterread.CommonMeterReadDO;
import com.young.pact.domain.commonpic.CommonPicDO;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayDO;
import com.young.pact.domain.purchasetermination.PurchaseTerminationBaseVO;
import com.young.pact.domain.purchasetermination.PurchaseTerminationDO;
import com.young.pact.domain.purchasetermination.PurchaseTerminationEditVO;
import com.young.pact.domain.purchasetermination.PurchaseTerminationQuery;
import com.young.pact.domain.purchasetermination.PurchaseTerminationVO;

/**
 * 
* @ClassName: PurchaseTerminatiorManager
* @Description: 托进解约协议
* @author SunYiXuan
* @date 2018年8月9日 下午4:20:14
*
 */
public interface PurchaseTerminationManager {

    /**
     * 
    * @Title: countByPurchaseCode
    * @Description: 根据合同编码查询解约协议数量
    * @author SunYiXuan
    * @param purchaseCode
    * @return
    * @throws
     */
    int countByPurchaseCode(String purchaseCode);

    
    /**
     * @param purchaseTerminationDO 
     * 
    * @Title: savePurchaseTermination
    * @Description: 添加托进解约协议
    * @author SunYiXuan
    * @param financeReceiptPayList
    * @param commonPicList
    * @param commonMeterReadList
    * @param commonGoodsList
    * @param commonBelongerDOList
    * @return
    * @throws
     */
    Boolean savePurchaseTermination(PurchaseTerminationDO purchaseTerminationDO, List<FinanceReceiptPayDO> financeReceiptPayList, 
            List<CommonPicDO> commonPicList, List<CommonMeterReadDO> commonMeterReadList,
            List<CommonGoodsDO> commonGoodsList, List<CommonBelongerDO> commonBelongerDOList);


    /**
     * 
    * @Title: listPurchaseTermination
    * @Description: 查询解约协议列表
    * @author SunYiXuan
    * @param query
    * @return
    * @throws
     */
    PageBean<PurchaseTerminationVO> listPurchaseTermination(PurchaseTerminationQuery query);


    /**
     * 
    * @Title: getPurchaseTerminationBase
    * @Description: 查询解约协议基本信息
    * @author SunYiXuan
    * @param terminationCode
    * @return
    * @throws
     */
    PurchaseTerminationBaseVO getPurchaseTerminationBase(String terminationCode);


    /**
     * 
    * @Title: getPurchaseTerminationEdit
    * @Description: 获取拖进合同解约协议编辑页数据
    * @author SunYiXuan
    * @param terminationCode
    * @return
    * @throws
     */
    PurchaseTerminationEditVO getPurchaseTerminationEdit(String terminationCode);


    /**
     * 
    * @Title: getPurchaseTerminarion
    * @Description: 获取拖进合同解约协议信息
    * @author SunYiXuan
    * @param terminationCode
    * @return
    * @throws
     */
    PurchaseTerminationVO getPurchaseTerminarion(String terminationCode);


    /**
     * 
    * @Title: updateTermination
    * @Description: 修改拖进合同解约协议
    * @author SunYiXuan
    * @param terminationDO
    * @param financeReceiptPayDOList
    * @param commonPicDOList
    * @param commonBelongerDO
    * @return
    * @throws
     */
    Boolean updateTermination(PurchaseTerminationDO terminationDO, List<FinanceReceiptPayDO> financeReceiptPayDOList, List<CommonPicDO> commonPicDOList, CommonBelongerDO commonBelongerDO);


    /**
     * 
    * @Title: removePurchaseTermination
    * @Description: 删除拖进合同解约协议
    * @author SunYiXuan
    * @param terminationDO
    * @return
    * @throws
     */
    Boolean removePurchaseTermination(PurchaseTerminationDO terminationDO);


    /**
     * 
    * @Title: updateState
    * @Description: 修改审核状态
    * @author SunYiXuan
    * @param terminationDO
    * @return
    * @throws
     */
    Boolean updateState(PurchaseTerminationDO terminationDO);
    
    /**
     * @Title: getPermission
     * @Description: TODO( 查看登录人有没有这个托进合同解约协议的权限 )
     * @author GuoXiaoPeng
     * @param param 数据权限作用域和托进合同解约协议编码
     * @return 有权限返回true，否则返回false
    */
    boolean getPermissions(PurchaseTerminationQuery query);
    /**
     * 
    * @Title: listPurTerminationByParam
    * @Description: TODO( 根据查询条件查询托进合同解约协议集合 )
    * @author GuoXiaoPeng
    * @param purchaseBaseQuery  审核状态
    * @return 托进合同解约协议集合 
    * @throws 异常
     */

    List<PurchaseTerminationVO> listPurTerminationByParam(PurchaseTerminationQuery purchaseBaseQuery);

    /**
    * @Title: listUnReview
    * @Description: TODO(  查询托进解约协议提交审核后超过24小时没有审核通过或驳回的托进解约协议)
    * @author GuoXiaoPeng
    * @param purchaseTerminationQuery 协议状态待审核
    * @return 托进解约协议集合
    * @throws 异常
     */
    List<PurchaseTerminationVO> listUnReview(PurchaseTerminationQuery purchaseTerminationQuery);

    /**
    * @Title: listUnsubmitted
    * @Description: TODO( 查询录入托进解约协议超过24小时未提交审核的托进解约协议)
    * @author GuoXiaoPeng
    * @param purchaseTerminationQuery 托进解约协议状态草稿
    * @return 托进解约协议状态为草稿并且创建时间超过24小时的托进合同
    * @throws 异常
     */
    List<PurchaseTerminationVO> listUnsubmitted(PurchaseTerminationQuery purchaseTerminationQuery);

}
