package com.young.pact.manager.pactrenttransfer;

import java.util.List;
import java.util.Map;

import com.young.common.page.PageBean;
import com.young.pact.domain.commonbelonger.CommonBelongerDO;
import com.young.pact.domain.commoncostrule.CommonCostRuleDO;
import com.young.pact.domain.commongoods.CommonGoodsDO;
import com.young.pact.domain.commonmeterread.CommonMeterReadDO;
import com.young.pact.domain.commonpic.CommonPicDO;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayDO;
import com.young.pact.domain.pactrenttransfer.PactRentTransferDO;
import com.young.pact.domain.pactrenttransfer.PactRentTransferQuery;
import com.young.pact.domain.pactrenttransfer.PactRentTransferVO;
import com.young.pact.domain.pactrenttransfer.PactTransferAllVO;
import com.young.pact.domain.rentbase.RentBaseDO;
import com.young.pact.domain.rentcommonowner.RentCommonOwnerDO;
import com.young.pact.domain.rentcustomer.RentCustomerDO;
import com.young.pact.domain.rentroom.RentRoomDO;

/**
 * 
* @ClassName: PactRentTransferManager
* @Description: 调房manager接口
* @author LiuYuChi
* @date 2018年8月8日 下午1:50:22
*
 */
public interface PactRentTransferManager {

	/**
	 * 
	* @Title: saveRedis
	* @Description: 保存到redis公用方法
	* @author LiuYuChi
	* @param map 保存的内容
	* @param time 保存的时间
	* @param redisKey 关键字
	* @throws
	 */
	void saveRedis(Map<String, Object> map,int time, String redisKey);
	
	/**
	 * 
	* @Title: getRedis
	* @Description: 获取redis缓存公用方法
	* @author LiuYuChi
	* @param key 关键字
	* @return 缓存内容
	* @throws
	 */
	Map<String, Object> getRedis (String key);
	
	/**
	 * 
	* @Title: saveRentBaseAndTransfer
	* @Description: 保存调房协议和托出合同信息
	* @author LiuYuChi
	* @param pactRentTransferDO
	* @param financeReceiptPayList
	* @param commonPicList
	* @param rentRoomDO
	* @param rentCustomerDO
	* @param cohabitantList
	* @param rentBaseDO
	* @param pactReceiptPayList
	* @param receiptPayList
	* @param commonPactPicList
	* @param commonMeterReadList
	* @param commonGoodsList
	* @param commonBelongerDOList
	* @return
	* @throws
	 */
	boolean saveRentBaseAndTransfer(PactRentTransferDO pactRentTransferDO,
			List<CommonMeterReadDO> meterReadList,
			List<CommonGoodsDO> goodsList,RentRoomDO rentRoomDO, RentCustomerDO rentCustomerDO, 
			List<RentCommonOwnerDO> cohabitantList, RentBaseDO rentBaseDO, List<CommonCostRuleDO> pactReceiptPayList, 
			List<FinanceReceiptPayDO> receiptPayList, List<CommonPicDO> commonPactPicList, 
			List<CommonMeterReadDO> commonMeterReadList, List<CommonGoodsDO> commonGoodsList, 
			List<CommonBelongerDO> commonBelongerDOList);
	
	/**
	 * 
	* @Title: getTransfer
	* @Description: 查询调房协议详情
	* @author LiuYuChi
	* @param transferCode
	* @return
	* @throws
	 */
	PactTransferAllVO getTransfer(String transferCode);
	
	/**
	 * 
	* @Title: updateTransfer
	* @Description: 删除调房协议
	* @author LiuYuChi
	* @param pactRentTransferDO
	* @return
	* @throws
	 */
	boolean updateTransferInfo (PactRentTransferDO pactRentTransferDO);
	
	/**
	 * 
	* @Title: updarteTransfer
	* @Description: 修改调房协议
	* @author LiuYuChi
	* @param pactRentTransferDO 调房信息
	* @param financeReceiptPayDOs 收支信息
	* @param commonPicDOs 调房协议照片
	* @param commonBelongerDO 签约管家信息
	* @return
	* @throws
	 */
	boolean updateTransfer(PactRentTransferDO pactRentTransferDO, List<FinanceReceiptPayDO> financeReceiptPayDOs,
			List<CommonPicDO> commonPicDOs, List<CommonBelongerDO> commonBelongerDO,List<FinanceReceiptPayDO> financeReceiptPayList);
	
	
	/**
	 * 
	* @Title: listParam
	* @Description: 查询调房列表
	* @author LiuYuChi
	* @param query
	* @return
	* @throws
	 */
	PageBean<PactRentTransferVO> listParam(PactRentTransferQuery  query);
	
	/**
	 * 
	* @Title: countTransfer
	* @Description: 查询数量
	* @author LiuYuChi
	* @param query
	* @return
	* @throws
	 */
	//int countTransfer(PactRentTransferQuery  query);
	
	/**
	 * 
	* @Title: rejectTransferInfo
	* @Description: 驳回
	* @author LiuYuChi
	* @param pactRentTransferDO
	* @return
	* @throws
	 */
	boolean rejectTransferInfo (PactRentTransferDO pactRentTransferDO);
	
	/**
	 * 
	* @Title: checkTransfer
	* @Description: 审核通过
	* @author LiuYuChi
	* @param pactRentTransferDO
	* @return
	* @throws
	 */
	boolean checkTransfer (PactRentTransferDO pactRentTransferDO,FinanceReceiptPayDO oldFinanceReceiptPayDO,
			FinanceReceiptPayDO newFinanceReceiptPayDO,FinanceReceiptPayDO transferReceiptPayDO,
			RentBaseDO newRentBaseDO,RentBaseDO oldRentBaseDO);
	/**
	* @Title: listTransferByParam
	* @Description: TODO( 条件查询托出调房协议集合 )
	* @author GuoXiaoPeng
	* @param rentTransferQuery 协议审核状态
	* @return 托出调房协议集合
	* @throws 异常
	 */
    List<PactRentTransferVO> listTransferByParam(PactRentTransferQuery rentTransferQuery);
    /**
    * @Title: listUnReview
    * @Description: TODO( 查询托出调房协议提交审核后超过24小时没有审核通过或驳回的托出调房协议集合 )
    * @author GuoXiaoPeng
    * @param rentTransferQuery 协议状态待审核
    * @return 托出调房协议集合
    * @throws 异常
     */
    List<PactRentTransferVO> listUnReview(PactRentTransferQuery rentTransferQuery);
    /**
    * @Title: listUnsubmitted
    * @Description: TODO( 查询录入托出调房协议超过24小时未提交审核的托出续调房议集合 )
    * @author GuoXiaoPeng
    * @param rentTransferQuery 合同状态草稿
    * @return 托出调房协议集合 
    * @throws 异常
     */
    List<PactRentTransferVO> listUnsubmitted(PactRentTransferQuery rentTransferQuery);
    /**
     * 
    * @Title: aplyCheck
    * @Description: TODO( 申请审核  )
    * @author GuoXiaoPeng
    * @param pactRentTransferDO 
    * @return 成功true失败false
    * @throws 异常
     */
    Boolean aplyCheck(PactRentTransferDO pactRentTransferDO);
    /**
    * @Title: getPermissions
    * @Description: TODO( 查询登录人有没有权限查看详情 )
    * @author GuoXiaoPeng
    * @param query 协议编码
    * @return 有权限返回true，没有权限返回false
    * @throws 异常
     */
    boolean getPermissions(PactRentTransferQuery query);
}

	
	
