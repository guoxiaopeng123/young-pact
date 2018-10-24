package com.young.pact.manager.rentcontinued;

import java.util.List;

import com.young.common.page.PageBean;
import com.young.pact.domain.commonbelonger.CommonBelongerDO;
import com.young.pact.domain.commoncostrule.CommonCostRuleDO;
import com.young.pact.domain.commongoods.CommonGoodsDO;
import com.young.pact.domain.commonmeterread.CommonMeterReadDO;
import com.young.pact.domain.commonpic.CommonPicDO;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayDO;
import com.young.pact.domain.rentbase.RentBaseDO;
import com.young.pact.domain.rentbase.RentPactDO;
import com.young.pact.domain.rentbase.RentPactVO;
import com.young.pact.domain.rentcontinued.RentContinuedDO;
import com.young.pact.domain.rentcontinued.RentContinuedQuery;
import com.young.pact.domain.rentcontinued.RentContinuedVO;
import com.young.pact.domain.rentcustomer.RentCustomerDO;
import com.young.pact.domain.rentroom.RentRoomDO;

/**
 * @描述 : 续签协议
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月10日 下午8:46:15
 */
public interface RentContinuedManager {
    /**
    * @Title: saveRentContinuedRedis
    * @Description: TODO( 缓存保存续签协议 )
    * @author GuoXiaoPeng
    * @param rentContinuedDO 续签协议信息
    * @param key 缓存key
    * @throws 异常
     */
    void saveRentContinuedRedis(RentContinuedDO rentContinuedDO, String key);
    /**
    * @Title: getRentContinuedRedis
    * @Description: TODO( 缓存查询续签协议)
    * @author GuoXiaoPeng
    * @param redisKey 缓存key
    * @return 续签协议信息
    * @throws 异常
     */
    RentContinuedVO getRentContinuedRedis(String redisKey);
    /**
    * @Title: saveRentContinuedPactRedis
    * @Description: TODO(缓存保存合同 )
    * @author GuoXiaoPeng
    * @param redisKey 缓存key
    * @param rentPactDO 合同信息
    * @throws 异常
     */
    void saveRentContinuedPactRedis(String redisKey, RentPactDO rentPactDO);
    /**
    * @Title: getRentContinuedPactRedis
    * @Description: TODO(缓存查询合同 )
    * @author GuoXiaoPeng
    * @param redisKey  缓存key
    * @return  合同信息
    * @throws 异常
     */
    RentPactVO getRentContinuedPactRedis(String redisKey);
    /**
    * @Title: countAllProtocolByPactCode
    * @Description: TODO( 查询某个托出合同除了审核通过的转租，续租，调房，托出解约协议的总数)
    * @author GuoXiaoPeng
    * @param rentPactCode 托出合同编码
    * @return 总数
    * @throws 异常
     */
    Integer countAllProtocolByPactCode(String rentPactCode);
    /**
    * @Title: saveRentContinuedPact
    * @Description: TODO( 保存续签协议 )
    * @author GuoXiaoPeng
    * @param rentContinuedDO 续签协议信息
    * @param protocolCommonMeterReadDOs 协议抄表信息
    * @param protocolCommonGoodsDOs 协议物品信息
    * @param rentBaseDO 托出合同
    * @param newPactReceiptPayList 合同收支规则
    * @param newReceiptPayList 合同收支
    * @param newCommonMeterReadDOs 合同抄表信息
    * @param newCommonGoodsDOs 合同物品信息
    * @param rentCustomerDO 合同租客
    * @param commonPicDOs 图片
    * @param commonBelongerDOList 负责人
    * @param rentRoomDO 新合同房间
    * @return 成功返回true 失败返回false
    * @throws 异常
     */
    boolean saveRentContinuedPact(RentContinuedDO rentContinuedDO, 
            List<CommonMeterReadDO> protocolCommonMeterReadDOs, 
            List<CommonGoodsDO> protocolCommonGoodsDOs, 
            RentBaseDO rentBaseDO, List<CommonCostRuleDO> newPactReceiptPayList,
            List<FinanceReceiptPayDO> newReceiptPayList, 
            List<CommonMeterReadDO> newCommonMeterReadDOs, 
            List<CommonGoodsDO> newCommonGoodsDOs, 
            RentCustomerDO rentCustomerDO, 
            List<CommonPicDO> commonPicDOs, 
            List<CommonBelongerDO> commonBelongerDOList,
            RentRoomDO rentRoomDO);
    /**
    * @Title: listParam
    * @Description: TODO( 分页查询续签列表)
    * @author GuoXiaoPeng
    * @param rentContinuedQuery 续签信息
    * @return 续签集合
    * @throws 异常
     */
    PageBean<RentContinuedVO> listParam(RentContinuedQuery rentContinuedQuery);
    /**
    * @Title: getRentContinuedByRenewCode
    * @Description: TODO( 续签协议详情 )
    * @author GuoXiaoPeng
    * @param renewCode 续签协议编码
    * @return 续签协议详情
    * @throws 异常
     */
    RentContinuedVO getRentContinuedByRenewCode(String renewCode);
    /**
    * @Title: updateRentContinuedStateByCode
    * @Description: TODO( 修改续签协议审核状态 )
    * @author GuoXiaoPeng
    * @param rentContinuedDO 续签协议信息
    * @return 成功返回true，失败返回false
    * @throws 异常
     */
    boolean updateRentContinuedStateByCode(RentContinuedDO rentContinuedDO);
    /**
    * @Title: updateRentContinuedByRenewCode
    * @Description: TODO( 修改续租协议)
    * @author GuoXiaoPeng
    * @param rentContinuedDO 续租协议
    * @param commonBelongerDOs 负责人集合
    * @param commonPicDOs 协议图片
    * @return
    * @throws
     */
    boolean updateRentContinuedByRenewCode(RentContinuedDO rentContinuedDO, List<CommonBelongerDO> commonBelongerDOs, List<CommonPicDO> commonPicDOs);
    /**
    * @Title: removeRentContinuedByReletCode
    * @Description: TODO( 删除续签协议 )
    * @author GuoXiaoPeng
    * @param rentContinuedDO 续签协议信息
    * @return 成功返回true，失败返回false
    * @throws 异常
     */
    boolean removeRentContinuedByReletCode(RentContinuedDO rentContinuedDO);
    /**
    * @Title: listUnsubmitted
    * @Description: TODO( 查询录入托出续租协议超过24小时未提交审核的托出续租协议集合 )
    * @author GuoXiaoPeng 
    * @param rentContinuedQuery  合同状态草稿
    * @return 托出续租协议集合 
    * @throws 异常
     */
    List<RentContinuedVO> listUnsubmitted(RentContinuedQuery rentContinuedQuery);
    /**
     * 
    * @Title: listUnReview
    * @Description: TODO( 查询托出续租协议提交审核后超过24小时没有审核通过或驳回的托出续租协议 )
    * @author GuoXiaoPeng
    * @param rentContinuedQuery 协议状态待审核
    * @return 托出续租协议 
    * @throws 异常
     */
    List<RentContinuedVO> listUnReview(RentContinuedQuery rentContinuedQuery);
    /**
     * 
    * @Title: listRentcontinuedByParam
    * @Description: TODO( 条件查询托出续租协议集合 )
    * @author GuoXiaoPeng
    * @param rentContinuedQuery 协议审核状态
    * @return  托出续租协议集合
    * @throws 异常
     */
    List<RentContinuedVO> listRentcontinuedByParam(RentContinuedQuery rentContinuedQuery);
    /**
     * 
    * @Title: getPermissions
    * @Description: TODO( 查询登录人有没有权限查看详情 )
    * @author GuoXiaoPeng 
    * @param query 转租协议编码和权限作用域
    * @return 有权限返回true，没有权限返回false
    * @throws 异常
     */
    boolean getPermissions(RentContinuedQuery rentContinuedQuery);

}
