package com.young.pact.manager.purchasebase;

import java.util.List;
import java.util.Map;

import com.young.common.page.PageBean;
import com.young.pact.domain.commonbelonger.CommonBelongerDO;
import com.young.pact.domain.commoncostrule.CommonCostRuleDO;
import com.young.pact.domain.commongoods.CommonGoodsDO;
import com.young.pact.domain.commonmeterread.CommonMeterReadDO;
import com.young.pact.domain.commonpic.CommonPicDO;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayDO;
import com.young.pact.domain.purchasebase.PurchaseBaseDO;
import com.young.pact.domain.purchasebase.PurchaseBaseQuery;
import com.young.pact.domain.purchasebase.PurchaseBaseVO;
import com.young.pact.domain.purchasehouse.PurchaseHouseDO;
import com.young.pact.domain.purchasehouse.PurchaseHouseOwnerDO;
import com.young.pact.domain.purchaserentfree.PurchaseRentfreeDO;

/**
 * 
* @ClassName: PurchaseBaseManager
* @Description: TODO( 托进合同)
* @author HeZeMin
* @date 2018年8月2日 上午10:59:32
*
 */
public interface PurchaseBaseManager {
    /**
     * 
    * @Title: savePurchaseBase
    * @Description: TODO( 托进合同第三步 把合同信息保存到redis中)
    * @author HeZeMin
    * @param map    合同基本信息、收支规则、免租期、收支、图片
    * @param redisKey   redis key
     */
    void savePurchaseBaseRedis(Map<String, Object> map, String redisKey);
    /**
     * 
    * @Title: getPurchaseBase
    * @Description: TODO( 根据redis key查询缓存中托进合同第三步合同信息)
    * @author HeZeMin
    * @param redisKey   redis key
    * @return   返回 合同基本信息、收支规则、免租期、收支、图片
     */
    Map<String, Object> getPurchaseBaseRedis(String redisKey);
    /**
     * 
    * @Title: savePurchaseBase
    * @Description: TODO( 保存托进合同)
    * @author HeZeMin
    * @param purchaseBaseDO 托进合同基本信息
    * @param commonCostRuleDOs  托进合同收支规则
    * @param purchaseRentfreeDOList 托进合同免租期
    * @param financeReceiptPayDOList    收支
    * @param commonPicDOList    合同图片
    * @param purchaseHouseDO    托进合同房源
    * @param purchaseHouseOwnerDO   托进合同业主
    * @param commonGoodsDOList  物品
    * @param commonMeterReadDOList  抄表
    * @param commonBelongerDOList   合同责任人
    * @return   返回保存是否成功
     */
    boolean savePurchaseBase(PurchaseBaseDO purchaseBaseDO, List<CommonCostRuleDO> commonCostRuleDOs, List<PurchaseRentfreeDO> purchaseRentfreeDOList,
            List<FinanceReceiptPayDO> financeReceiptPayDOList, List<CommonPicDO> commonPicDOList, PurchaseHouseDO purchaseHouseDO,
            PurchaseHouseOwnerDO purchaseHouseOwnerDO, List<CommonGoodsDO> commonGoodsDOList, List<CommonMeterReadDO> commonMeterReadDOList,
            List<CommonBelongerDO> commonBelongerDOList);
    /**
     * 
    * @Title: removePurchaseBaseRedis
    * @Description: TODO( 清除托进合同redis缓存)
    * @author HeZeMin
    * @param redisKeys  四步缓存redis key
     */
    void removePurchaseBaseRedis(List<String> redisKeys);
    /**
     * 
    * @Title: countByDeclareCode
    * @Description: TODO( 根据申报编码查询是否有未删除的托进合同)
    * @author HeZeMin
    * @param declareCode    申报编码
    * @return   返回托进合同个数
     */
    int countByDeclareCode(String declareCode);
    /**
     * 
    * @Title: listPurchaseBase
    * @Description: TODO( 查询托进合同列表)
    * @author HeZeMin
    * @param query  查询条件
    * @return   返回托进合同列表
     */
    PageBean<PurchaseBaseVO> listPurchaseBase(PurchaseBaseQuery query);
    /**
     * 
    * @Title: getPurchaseBase
    * @Description: TODO( 根据托进合同编码查询托进合同详情，房源，业主，收支规则，图片)
    * @author HeZeMin
    * @param purchaseCode   托进合同编码
    * @return   返回托进合同详情，房源，业主，收支规则，图片
     */
    PurchaseBaseVO getPurchaseBaseDetail(String purchaseCode);
    /**
     * 
     * @Title: getPurchaseBase
     * @Description: TODO( 根据托进合同编码查询托进合同基本信息)
     * @author HeZeMin
     * @param purchaseCode   托进合同编码
     * @return   返回托进合同基本信息
     */
    PurchaseBaseVO getPurchaseBase(String purchaseCode);
    /**
     * 
    * @Title: removePurchaseBase
    * @Description: TODO( 根据托进合同编码删除托进合同及相关信息)
    * @author HeZeMin
    * @param purchaseBaseDO 托进合同编码
    * @return   删除是否成功
     */
    boolean removePurchaseBase(PurchaseBaseDO purchaseBaseDO);
    /**
     * 
    * @Title: updateState
    * @Description: TODO( 修改托进合同审核状态)
    * @author HeZeMin
    * @param purchaseBaseDO 审核信息
    * @return   返回修改结果
     */
    boolean updateState(PurchaseBaseDO purchaseBaseDO);
    /**
     * 
     * @Title: getPurchaseBase
     * @Description: TODO( 根据托进合同编码查询托进合同基本信息--修改托进合同信息需要)
     * @author HeZeMin
     * @param purchaseCode   托进合同编码
     * @return   返回托进合同基本信息
     */
    PurchaseBaseVO getUpdatePurchaseBase(String purchaseCode);
    /**
     * 
    * @Title: updatePurchaseBase
    * @Description: TODO( 修改托进合同)
    * @author HeZeMin
    * @param purchaseBaseDO 托进合同基本信息
    * @param commonCostRuleDOList  托进合同收支规则
    * @param purchaseRentfreeDOList 托进合同免租期
    * @param financeReceiptPayDOList    收支
    * @param commonPicDOList    合同图片
    * @param commonBelongerDO   合同责任人
    * @return   返回保存是否成功
     */
    boolean updatePurchaseBase(PurchaseBaseDO purchaseBaseDO, List<CommonCostRuleDO> commonCostRuleDOList, List<PurchaseRentfreeDO> purchaseRentfreeDOList,
            List<FinanceReceiptPayDO> financeReceiptPayDOList, List<CommonPicDO> commonPicDOList, CommonBelongerDO commonBelongerDO);
    /**
    * @Title: listPurchaseBaseByParam
    * @Description: TODO( 根据申报编码查询拖进合同集合 )
    * @author GuoXiaoPeng
    * @param purchaseBaseQuery 申报编码
    * @return 拖进合同集合
    * @throws 异常
     */
    List<PurchaseBaseVO> listPurchaseBaseByParam(PurchaseBaseQuery purchaseBaseQuery);
    /**
     * 
    * @Title: updateStandard
    * @Description: TODO( 修改托进合同标准化状态)
    * @author HeZeMin
    * @param purchaseBaseDO 标准化信息
    * @return   返回修改结果
     */
    boolean updateStandard(PurchaseBaseDO purchaseBaseDO);
    /**
    * @Title: getPermissions
    * @Description: TODO( 查看登录人有没有某个拖进合同的查看权限 )
    * @author GuoXiaoPeng
    * @param query 数据作用域和拖进合同编码
    * @return 有权限返回true，否则false
    * @throws 异常
     */
    boolean getPermissions(PurchaseBaseQuery query);
    /**
    * @Title: listUnsubmitted
    * @Description: TODO( 查询录入合同超过24小时未提交审核的托进合同 )
    * @author GuoXiaoPeng
    * @param purchaseBaseQuery 合同状态草稿
    * @return 合同状态为草稿并且创建时间超过24小时的托进合同
    * @throws 异常
     */
    List<PurchaseBaseVO> listUnsubmitted(PurchaseBaseQuery purchaseBaseQuery);
    /**
    * @Title: listUnReview
    * @Description: TODO( 查询托进合同提交审核后超过24小时没有审核通过或驳回的托进合同集合 )
    * @author GuoXiaoPeng
    * @param purchaseBaseQuery 合同状态待审核
    * @return  托进合同集合
    * @throws 异常
     */
    List<PurchaseBaseVO> listUnReview(PurchaseBaseQuery purchaseBaseQuery);
    /**
    * @Title: listUnCreateRoom
    * @Description: TODO(  查询托进合同审核通过后超过24小时未创建房间的托进合同集合)
    * @author GuoXiaoPeng
    * @return 托进合同集合
    * @throws 异常
     */
    List<PurchaseBaseVO> listUnCreateRoom();
    /**
     * @Title: listUnCreateRoom
     * @Description: TODO(  查询合同驳回后超过24小时未重新提交审核托进合同集合)
     * @author GuoXiaoPeng
     * @return 托进合同集合
     * @throws 异常
      */
    List<PurchaseBaseVO> listRejectUnApply(PurchaseBaseQuery purchaseBaseQuery);
    /**
    * @Title: listPurchaseExpir
    * @Description: TODO( 查询托进到期时间距离今日还有60天的的托进合同 )
    * @author GuoXiaoPeng
    * @return 托进合同 集合
    * @throws 异常
     */
    List<PurchaseBaseVO> listPurchaseExpir();
}
