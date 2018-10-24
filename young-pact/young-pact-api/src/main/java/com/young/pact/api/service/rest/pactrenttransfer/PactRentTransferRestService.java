package com.young.pact.api.service.rest.pactrenttransfer;

import com.young.common.domain.ApiResult;
import com.young.common.page.PageBean;
import com.young.pact.api.domain.param.rest.pactrenttransfer.PactRentTransferParam;
import com.young.pact.api.domain.param.rest.rentbase.RentPactParam;
import com.young.pact.api.domain.result.rest.pactrenttransfer.PactRentTransferResult;
import com.young.pact.api.domain.result.rest.rentbase.RentPactResult;

/**
 * 
* @ClassName: PactRentTransferService
* @Description: 调房协议service接口
* @author LiuYuChi
* @date 2018年8月8日 下午1:52:43
*
 */
public interface PactRentTransferRestService {
	
	/**
	 * 
	* @Title: insertFirst
	* @Description: 第一步录入调房协议
	* @author LiuYuChi
	* @param param
	* @return
	* @throws
	 */
	ApiResult<String> insertFirst(PactRentTransferParam param);
	
	/**
	 * 
	* @Title: getFirst
	* @Description: 查询调房协议第一步信息
	* @author LiuYuChi
	* @param key
	* @return
	* @throws
	 */
	ApiResult<PactRentTransferResult> getFirst(String key);
	
	/**
	 * 
	* @Title: insertTransfer
	* @Description: 添加调房协议和托出合同
	* @author LiuYuChi
	* @param param
	* @return
	* @throws
	 */
	ApiResult<String> insertTransfer(PactRentTransferParam param);
	
	/**
	 * 
	* @Title: insertPact
	* @Description: 调房协议-录入新合同信息
	* @author LiuYuChi
	* @param parm
	* @return
	* @throws
	 */
	ApiResult<String> insertPact(RentPactParam parm);
	
	/**
	 * 
	* @Title: getPact
	* @Description: 跳转到录合同信息页面(如果有缓存获取缓存,没有缓存查询原合同截止日期的转天作为合同开始时间)
	* @author LiuYuChi
	* @param key
	* @return 缓存的合同信息或原合同截止日期的转天
	* @throws
	 */
	ApiResult<RentPactResult> getPact(String key,String pin);
	
	/**
	 * 
	* @Title: getTransferInfo
	* @Description: 根据调房协议编码获取调房协议详情
	* @author LiuYuChi
	* @param param
	* @return
	* @throws
	 */
	ApiResult<PactRentTransferResult> getTransferInfo(PactRentTransferParam param);
	
	/**
	 * 
	* @Title: delTransfer
	* @Description: 删除调房协议
	* @author LiuYuChi
	* @param transferCode
	* @return
	* @throws
	 */
	ApiResult<Boolean> delTransfer (PactRentTransferParam param);
	
	/**
	 * 
	* @Title: updateTransfer
	* @Description: 修改调房协议
	* @author LiuYuChi
	* @param param
	* @return
	* @throws
	 */
	ApiResult <Boolean> updateTransfer(PactRentTransferParam param);
	
	/**
	 * 
	* @Title: listTransfer
	* @Description: 分页查询调房协议列表
	* @author LiuYuChi
	* @param param
	* @return
	* @throws
	 */
	ApiResult<PageBean<PactRentTransferResult>> listTransfer(PactRentTransferParam param);
	
	/**
	 * 
	* @Title: updateTransferInfo
	* @Description: 修改调房协议主表信息
	* @author LiuYuChi
	* @param transferCode
	* @return
	* @throws
	 */
	ApiResult<Boolean> aplyCheck(PactRentTransferParam param);
	
	/**
	 * 
	* @Title: reject
	* @Description: 驳回
	* @author LiuYuChi
	* @param param
	* @return
	* @throws
	 */
	ApiResult<Boolean> reject(PactRentTransferParam param);
	
	/**
	 * 
	* @Title: check
	* @Description: 审核调房协议
	* @author LiuYuChi
	* @param transferCode
	* @return
	* @throws
	 */
	ApiResult<Boolean> check (PactRentTransferParam param);

	
}
