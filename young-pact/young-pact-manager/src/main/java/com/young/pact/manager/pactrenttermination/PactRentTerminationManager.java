package com.young.pact.manager.pactrenttermination;

import java.util.List;

import com.young.common.page.PageBean;
import com.young.pact.domain.commonbelonger.CommonBelongerDO;
import com.young.pact.domain.commongoods.CommonGoodsDO;
import com.young.pact.domain.commonmeterread.CommonMeterReadDO;
import com.young.pact.domain.commonpic.CommonPicDO;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayDO;
import com.young.pact.domain.pactrenttermination.PactRentTerminationAllVO;
import com.young.pact.domain.pactrenttermination.PactRentTerminationDO;
import com.young.pact.domain.pactrenttermination.PactRentTerminationQuery;
import com.young.pact.domain.pactrenttermination.PactRentTerminationVO;
import com.young.pact.domain.rentbase.RentBaseDO;

/**
 * 
* @ClassName: PactRentTerminationManager
* @Description: 拖出解约协议
* @author LiuYuChi
* @date 2018年8月12日 上午11:48:27
*
 */
public interface PactRentTerminationManager {

	/**
	 * 
	* @Title: insertTermination
	* @Description: 新增解约协议
	* @author LiuYuChi
	* @param pactRentTerminationDO 解约协议信息
	* @param commonBelongerDOList 责任人信息
	* @param commonPactPicList 合同图片
	* @param goodsList 物品列表
	* @param meterReadList 抄表列表
	* @param receiptPayList  收支列表
	* @return
	* @throws
	 */
	boolean insertTermination(PactRentTerminationDO pactRentTerminationDO,List<CommonBelongerDO> commonBelongerDOList,
			List<CommonPicDO> commonPactPicList,List<CommonGoodsDO> goodsList,List<CommonMeterReadDO> meterReadList,
			List<FinanceReceiptPayDO> receiptPayList);
	
	
	/**
	 * 
	* @Title: listTermination
	* @Description: 分页查询解约协议列表
	* @author LiuYuChi
	* @param query
	* @return
	* @throws
	 */
	PageBean<PactRentTerminationVO> listTermination(PactRentTerminationQuery query);
	
	/**
	 * 
	* @Title: getPactRentTermination
	* @Description: 获取解约协议详情
	* @author LiuYuChi
	* @param query
	* @return
	* @throws
	 */
	PactRentTerminationAllVO getPactRentTermination(PactRentTerminationQuery query);
	
	/**
	 * 
	* @Title: updateTermination
	* @Description: 修改解约协议
	* @author LiuYuChi
	* @param pactRentTerminationDO
	* @param financeReceiptPayDOs
	* @param commonPicDOs
	* @param commonBelongerDO
	* @return
	* @throws
	 */
	boolean updateTermination(PactRentTerminationDO pactRentTerminationDO, List<FinanceReceiptPayDO> financeReceiptPayDOs,
			List<CommonPicDO> commonPicDOs, CommonBelongerDO commonBelongerDO);
	
	/**
	 * 
	* @Title: applyCheck
	* @Description: 申请审核
	* @author LiuYuChi
	* @param pactRentTerminationDO
	* @return
	* @throws
	 */
	boolean applyCheck(PactRentTerminationDO pactRentTerminationDO);
	
	/**
	 * 
	* @Title: reject
	* @Description: 驳回解约协议
	* @author LiuYuChi
	* @param pactRentTerminationDO
	* @return
	* @throws
	 */
	boolean reject(PactRentTerminationDO pactRentTerminationDO);
	
	/**
	 * 
	* @Title: delTermination
	* @Description: 删除解约协议
	* @author LiuYuChi
	* @param pactRentTerminationDO
	* @return
	* @throws
	 */
	boolean delTermination(PactRentTerminationDO pactRentTerminationDO);
	
	/**
	 * @param rentBaseDO 
	 * 
	* @Title: check
	* @Description: 审核通过
	* @author LiuYuChi
	* @param pactRentTerminationDO
	* @param oldFinanceReceiptPayDO
	* @param newFinanceReceiptPayDO
	* @return
	* @throws
	 */
	boolean check(PactRentTerminationDO pactRentTerminationDO ,FinanceReceiptPayDO oldFinanceReceiptPayDO,FinanceReceiptPayDO newFinanceReceiptPayDO, RentBaseDO rentBaseDO);
	/**
	* @Title: listRentBaseByParam
	* @Description: TODO( 条件查询托出解约协议集合 )
	* @author GuoXiaoPeng
	* @param rentBaseQuery 协议审核状态
	* @return  托出解约协议集合
	* @throws 异常
	 */
	List<PactRentTerminationVO> listRentTerminationByParam(PactRentTerminationQuery pactRentTerminationQuery);
	
    /**
    * @Title: listUnsubmitted
    * @Description: TODO( 查询录入托出解约协议超过24小时未提交审核的托出解约协议 )
    * @author GuoXiaoPeng
    * @param purchaseBaseQuery 合同状态草稿
    * @return 协议状态为草稿并且创建时间超过24小时的托出解约协议
    * @throws 异常
     */
    List<PactRentTerminationVO> listUnsubmitted(PactRentTerminationQuery pactRentTerminationQuery);
    /**
     * @Title: listUnReview
     * @Description: TODO( 查询托出解约协议提交审核后超过24小时没有审核通过或驳回的托出解约协议)
     * @author GuoXiaoPeng
     * @param purchaseBaseQuery 协议状态待审核
     * @return  托进合同集合
     * @throws 异常
      */
    List<PactRentTerminationVO> listUnReview(PactRentTerminationQuery pactRentTerminationQuery);

    /**
     * 
    * @Title: getPermissions
    * @Description: TODO( 查询登录人有没有权限查看详情 )
    * @author GuoXiaoPeng 
    * @param query 托出解约协议编码和权限作用域
    * @return 有权限返回true，没有权限返回false
    * @throws 异常
     */
    boolean getPermissions(PactRentTerminationQuery query);
}
