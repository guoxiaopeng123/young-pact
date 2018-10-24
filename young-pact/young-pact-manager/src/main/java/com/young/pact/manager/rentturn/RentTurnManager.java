package com.young.pact.manager.rentturn;

import java.util.List;

import com.young.common.page.PageBean;
import com.young.pact.domain.commonbelonger.CommonBelongerDO;
import com.young.pact.domain.commongoods.CommonGoodsDO;
import com.young.pact.domain.commonmeterread.CommonMeterReadDO;
import com.young.pact.domain.commonpic.CommonPicDO;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayDO;
import com.young.pact.domain.rentbase.RentPactDO;
import com.young.pact.domain.rentbase.RentPactVO;
import com.young.pact.domain.rentcommonowner.RentCommonOwnerDO;
import com.young.pact.domain.rentcustomer.RentCustomerDO;
import com.young.pact.domain.rentroom.RentRoomDO;
import com.young.pact.domain.rentturn.RentTurnDO;
import com.young.pact.domain.rentturn.RentTurnProtocolDO;
import com.young.pact.domain.rentturn.RentTurnProtocolVO;
import com.young.pact.domain.rentturn.RentTurnQuery;
import com.young.pact.domain.rentturn.RentTurnVO;
import com.young.pact.domain.rentturncost.RentTurnCostDO;

/**
 * @描述 : 转租协议
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月6日 下午9:01:04
 */
public interface RentTurnManager {

    /**
    * @Title: save
    * @Description: TODO( 保存转租协议redis)
    * @author GuoXiaoPeng
    * @param rentTurnProtocolDO
    * @param string 缓存key
    * @throws 异常
     */
    void save(RentTurnProtocolDO rentTurnProtocolDO, String redisKey);
    /**
    * @Title: getRentTurnProtocolRedis
    * @Description: TODO( 通过缓存key查询 转租协议信息)
    * @author GuoXiaoPeng
    * @param redisKey 缓存key
    * @return 转租协议信息
    * @throws 异常 
     */
    RentTurnProtocolVO getRentTurnProtocolRedis(String redisKey);
    /**
    * @Title: saveRentTurnPactRedis
    * @Description: TODO( 保存合同 )
    * @author GuoXiaoPeng
    * @param key  缓存key
    * @param rentPactDO 合同信息
    * @throws 异常 
     */
    void saveRentTurnPactRedis(String key , RentPactDO rentPactDO);
    /**
    * @Title: getPactRedis
    * @Description: TODO(通过缓存key查询 合同信息 )
    * @author GuoXiaoPeng
    * @param redisKey 缓存key
    * @return 合同信息
    * @throws 异常 
     */
    RentPactVO getPactRedis(String redisKey);
    /**
    * @Title: saveRentTurn
    * @Description: TODO( 保存转租协议+新合同 )
    * @author GuoXiaoPeng
    * @param rentTurnProtocolDO 协议信息
    * @param rentPactDO  托出合同信息
    * @param rentRoomDO  托出合同房间信息
    * @param rentCustomerDO 托出合同租客
    * @param cohabitantList 托出合同租客共同居住人
    * @param newPactCommonMeterReadList  托出合同抄表信息
    * @param newPactCommonGoodsDOs  托出合同物品信息
    * @param newPactCommonBelongerDOList 托出合同责任人
    * @return
    * @throws
     */
    boolean saveRentTurn(RentTurnProtocolDO rentTurnProtocolDO,
            RentPactDO rentPactDO, RentRoomDO rentRoomDO, RentCustomerDO rentCustomerDO,
            List<RentCommonOwnerDO> cohabitantList, List<CommonMeterReadDO> newPactCommonMeterReadList, 
            List<CommonGoodsDO> newPactCommonGoodsDOs, List<CommonBelongerDO> newPactCommonBelongerDOList);
    /**
    * @Title: listParam
    * @Description: TODO(分页查询转租协议列表)
    * @author GuoXiaoPeng
    * @param rentTurnQuery 查询条件
    * @return 转租协议列表
    * @throws 异常
     */
    PageBean<RentTurnVO> listParam(RentTurnQuery rentTurnQuery);
    /**
    * @Title: getRentTurnByReletCode
    * @Description: TODO( 根据协议编码查询转租协议详情)
    * @author GuoXiaoPeng
    * @param reletCode 协议编码
    * @return 转租协议详情
    * @throws 异常
     */
    RentTurnVO getRentTurnByReletCode(String reletCode);
    /**
    * @Title: updateRentTurnStateByCode
    * @Description: TODO(申请审核转租协议 )
    * @author GuoXiaoPeng
    * @param rentTurnDO 转租协议信息
    * @return 成功true 失败false
    * @throws 异常
     */
    boolean updateRentTurnStateByCode(RentTurnDO rentTurnDO);
    /**
    * @Title: updateRentTurnByReletCode
    * @Description: TODO( 修改转租协议)
    * @author GuoXiaoPeng
    * @param rentTurnDO 转租协议信息
    * @param financeReceiptPayDOs 协议收支
    * @param rentTurnCostDOs 协议转让费
    * @param commonMeterReadDOs 协议抄表
    * @param commonGoodsDOs 协议物品
    * @param commonPicDOs 协议图片
    * @param commonBelongerDOs 协议负责人
    * @return 成功true 失败false
    * @throws 异常
     */
    boolean updateRentTurnByReletCode(RentTurnDO rentTurnDO, List<FinanceReceiptPayDO> financeReceiptPayDOs,
            List<RentTurnCostDO> rentTurnCostDOs, List<CommonMeterReadDO> commonMeterReadDOs,
            List<CommonGoodsDO> commonGoodsDOs, List<CommonPicDO> commonPicDOs, 
            List<CommonBelongerDO> commonBelongerDOs);
    /**
    * @Title: removeRentTurnByReletCode
    * @Description: TODO(删除转租协议 )
    * @author GuoXiaoPeng
    * @param rentTurnDO 转租协议信息
    * @return 成功true 失败false
    * @throws 异常
     */
    boolean removeRentTurnByReletCode(RentTurnDO rentTurnDO);
    /**
    * @Title: listRentTurnByParam
    * @Description: TODO( 条件查询托出合同转租协议集合 )
    * @author GuoXiaoPeng 
    * @param rentTurnQuery 协议审核状态
    * @return 托出合同转租协议集合
    * @throws 异常
     */
    List<RentTurnVO> listRentTurnByParam(RentTurnQuery rentTurnQuery);
    /**
    * @Title: listUnsubmitted
    * @Description: TODO( 查询录入托出合同转租协议超过24小时未提交审核的托出合同转租协议 )
    * @author GuoXiaoPeng
    * @param rentTurnQuery 协议状态为草稿并且创建时间超过24小时的托出解约协议
    * @return 协议状态为草稿并且创建时间超过24小时的托出合同转租协议
    * @throws 异常
     */
    List<RentTurnVO> listUnsubmitted(RentTurnQuery rentTurnQuery);
    /**
    * @Title: listUnReview
    * @Description: TODO( 查询托出合同转租协议提交审核后超过24小时没有审核通过或驳回的托出合同转租协议 )
    * @author GuoXiaoPeng
    * @param rentTurnQuery 协议状态待审核
    * @return 托出合同转租协议
    * @throws 异常
     */
    List<RentTurnVO> listUnReview(RentTurnQuery rentTurnQuery);
    /**
     * 
    * @Title: getPermissions
    * @Description: TODO( 查询登录人有没有权限查看详情 )
    * @author GuoXiaoPeng 
    * @param query 转租协议编码和权限作用域
    * @return 有权限返回true，没有权限返回false
    * @throws 异常
     */
    boolean getPermissions(RentTurnQuery query);
}
