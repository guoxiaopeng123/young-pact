package com.young.pact.manager.rentbase;

import java.util.List;

import com.young.common.page.PageBean;
import com.young.pact.domain.commonbelonger.CommonBelongerDO;
import com.young.pact.domain.commonbelonger.CommonBelongerVO;
import com.young.pact.domain.commoncostrule.CommonCostRuleDO;
import com.young.pact.domain.commongoods.CommonGoodsDO;
import com.young.pact.domain.commonmeterread.CommonMeterReadDO;
import com.young.pact.domain.commonpic.CommonPicDO;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayDO;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayQuery;
import com.young.pact.domain.rentbase.RentBaseDO;
import com.young.pact.domain.rentbase.RentBaseQuery;
import com.young.pact.domain.rentbase.RentBaseVO;
import com.young.pact.domain.rentbase.RentPactDO;
import com.young.pact.domain.rentbase.RentPactVO;
import com.young.pact.domain.rentcommonowner.RentCommonOwnerDO;
import com.young.pact.domain.rentcustomer.CustomerOwnerDO;
import com.young.pact.domain.rentcustomer.RentCustomerDO;
import com.young.pact.domain.rentroom.RentRoomDO;

/**
 * @描述 : 托出合同
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月1日 下午2:56:11
 */
public interface RentBaseManager {


    /**
     * 
    * @Title: savePactRedis
    * @Description: TODO( 保存合同)
    * @author GuoXiaoPeng
    * @param key 缓存key
    * @param rentPactDO 合同信息
    */
    void savePactRedis(String key ,RentPactDO rentPactDO);
    /**
    * @Title: getPactRedis
    * @Description: TODO(根据缓存key获取合同信息 )
    * @author GuoXiaoPeng
    * @param key 缓存key
    * @return 合同信息
    */
    RentPactVO getPactRedis(String key);
     /**
     * @Title: saveRentBase
     * @Description: TODO( 保存托出合同信息 )
     * @author GuoXiaoPeng
     * @param rentRoomDO 托出房间信息
     * @param rentCustomerDO 托出租客信息
     * @param cohabitantList 托出租客共同居住人信息
     * @param rentBaseDO 托出合同基本信息
     * @param pactReceiptPayList 合同费用规则集合
     * @param receiptPayList 收支费用集合
     * @param commonPicList 合同照片集合
     * @param commonMeterReadList  抄表
     * @param commonGoodsList  物品
     * @param commonBelongerDOList 合同责任人
     * @return 返回保存是否成功
      */
    boolean saveRentBase(RentRoomDO rentRoomDO, RentCustomerDO rentCustomerDO, List<RentCommonOwnerDO> cohabitantList, RentBaseDO rentBaseDO, List<CommonCostRuleDO> pactReceiptPayList, List<FinanceReceiptPayDO> receiptPayList, List<CommonPicDO> commonPicList, List<CommonMeterReadDO> commonMeterReadList, List<CommonGoodsDO> commonGoodsList, List<CommonBelongerDO> commonBelongerDOList);
    /**
    * @Title: listRentBase
    * @Description: TODO( 根据条件分页查询托出合同列表)
    * @author GuoXiaoPeng
    * @param rentBaseQuery 条件查询
    * @return 托出合同列表信息
    * @throws
    */
    PageBean<RentBaseVO> listParam(RentBaseQuery rentBaseQuery);
    /**
    * @Title: getRentBaseByCode
    * @Description: TODO( 托出合同详情)
    * @author GuoXiaoPeng
    * @param rentPactCode 托出合同编码
    * @param financeReceiptPayQuery 收支信息
    * @return 托出合同详情
    * @throws
    */
    RentPactVO getRentBaseByCode(String rentPactCode,FinanceReceiptPayQuery financeReceiptPayQuery);
    /**
    * @Title: updateRenterByCode
    * @Description: TODO(修改托出合同租客 )
    * @author GuoXiaoPeng
    * @param customerOwnerDO 托出合同租客+共同居住人
    * @return  返回更新是否成功
    * @throws
    */
    boolean updateRenterByCode(CustomerOwnerDO customerOwnerDO);
    /**
    * @Title: updateRenterStateByCode
    * @Description: TODO(修改托出合同审核状态 )
    * @author GuoXiaoPeng
    * @param rentBaseDO 托出合同
    * @param pact 合同基本信息
    * @return 返回更新是否成功
    * @throws
    */
    boolean updateRentBaseStateByCode(RentBaseDO rentBaseDO,RentBaseVO pact);
    /**
    * @Title: deleteRentBaseByCode
    * @Description: TODO(删除托出合同 )
    * @author GuoXiaoPeng
    * @param rentBaseDO 托出合同信息
    * @return 删除是否成功
    * @throws
     */
    boolean deleteRentBaseByCode(RentBaseDO rentBaseDO);
    /**
    * @Title: updateRentBaseByCode
    * @Description: TODO(修改托出合同 )
    * @author GuoXiaoPeng
    * @param rentBaseDO 托出合同机基本信息
    * @param commonCostRuleDOs 合同规则集合
    * @param financeReceiptPayDOs 收支集合
    * @param commonPicDOs 合同图片集合
    * @return 返回更新是否成功
    * @throws 异常
     */
    boolean updateRentBaseByCode(RentBaseDO rentBaseDO, List<CommonCostRuleDO> commonCostRuleDOs,
            List<FinanceReceiptPayDO> financeReceiptPayDOs, List<CommonPicDO> commonPicDOs, CommonBelongerDO commonBelongerDO);
    /**
    * @Title: getRentBaseByCode
    * @Description: TODO( 根据托出合同编码获取托出合同详情)
    * @author GuoXiaoPeng
    * @param rentPactCode 托出合同编码
    * @return  托出合同详情
    * @throws 异常
     */
    RentBaseVO getRentBaseByCode(String rentPactCode);
    /**
    * @Title: setDelayDate
    * @Description: TODO( 设置延期时间 )
    * @author GuoXiaoPeng
    * @param rentBaseDO 托出合同信息
    * @return 成功true或失败false
    * @throws 异常
     */
    boolean setDelayDate(RentBaseDO rentBaseDO);
    /**
     * @Title: getPermissions
     * @Description: TODO( 查看登录人有没有这个托出合同的权限 )
     * @author GuoXiaoPeng
     * @param rentBaseParam 托出合同编码和权限作用域
     * @return 有权限返回true，没有权限返回false
      */
    boolean getPermissions(RentBaseQuery query);
    /**
     * 
    * @Title: getRentAfterBelonger
    * @Description: TODO( 根据托出合同编码查询托出租后管家 )
    * @author GuoXiaoPeng
    * @param pactCode 托出合同编码
    * @return 租后管家 
    * @throws 异常
     */
    CommonBelongerVO getRentAfterBelonger(String pactCode);
    /**
     * @Title: listUnsubmitted
     * @Description: TODO( 查询录入托出合同超过24小时未提交审核的托出合同 )
     * @author GuoXiaoPeng
     * @param purchaseBaseQuery 合同状态草稿
     * @return 合同状态为草稿并且创建时间超过24小时的托出合同
     * @throws 异常
      */
    List<RentBaseVO> listUnsubmitted(RentBaseQuery rentBaseQuery);
    /**
     * @Title: listUnReview
     * @Description: TODO( 查询托出合同提交审核后超过24小时没有审核通过或驳回的托出合同 )
     * @author GuoXiaoPeng
     * @param purchaseBaseQuery 合同状态待审核
     * @return  托出合同集合
     * @throws 异常
      */
    List<RentBaseVO> listUnReview(RentBaseQuery purchaseBaseQuery);
    /**
    * @Title: listRentBaseByParam
    * @Description: TODO( 根据条件查询 托出合同集合)
    * @author GuoXiaoPeng
    * @param rentBaseQuery 合同状态
    * @return 托出合同集合
    * @throws 异常
     */
    List<RentBaseVO> listRentBaseByParam(RentBaseQuery rentBaseQuery);
    /**
    * @Title: listRentExpir
    * @Description: TODO( 查询托出合同到期时间距离今日还有60天的的托出合同 )
    * @author GuoXiaoPeng
    * @return 托出合同集合
    * @throws 异常
     */
    List<RentBaseVO> listRentExpir();
}
