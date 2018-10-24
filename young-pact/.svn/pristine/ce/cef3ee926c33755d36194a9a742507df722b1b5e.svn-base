package com.young.pact.api.service.rest.pactrenttermination;


import com.young.common.domain.ApiResult;
import com.young.common.page.PageBean;
import com.young.pact.api.domain.param.rest.pactrenttermination.PactRentTerminationParam;
import com.young.pact.api.domain.result.rest.pactrenttermination.PactRentTerminationResult;

/**
 * 
* @ClassName: PactRentTerminationService
* @Description: 脱出解约协议接口
* @author LiuYuChi
* @date 2018年8月12日 上午10:56:38
*
 */
public interface PactRentTerminationService {
	
	/**
	 * 
	* @Title: insertTermination
	* @Description: 新增脱出解约协议
	* @author LiuYuChi
	* @param param 参数
	* @return true成功  false失败
	* @throws
	 */
	ApiResult<Boolean> insertTermination (PactRentTerminationParam param);
	
	/**
	 * 
	* @Title: listTermination
	* @Description: 分页查询解约协议列表
	* @author LiuYuChi
	* @param param
	* @return
	* @throws
	 */
	ApiResult<PageBean<PactRentTerminationResult>> listTermination(PactRentTerminationParam param);
	
	/**
	 * 
	* @Title: getTermination
	* @Description: 获取解约协议详情
	* @author LiuYuChi
	* @param param
	* @return
	* @throws
	 */
	ApiResult<PactRentTerminationResult> getTermination(PactRentTerminationParam param);
	
	/**
	 * 
	* @Title: toUpdatePage
	* @Description: 跳转到修改协议页面
	* @author LiuYuChi
	* @param dissolutionCode
	* @return
	* @throws
	 */
	ApiResult<PactRentTerminationResult> toUpdatePage(String dissolutionCode);
	
	/**
	 * 
	* @Title: updateTerminatioin
	* @Description: 修改拖出解约协议
	* @author LiuYuChi
	* @param param
	* @return
	* @throws
	 */
	ApiResult<Boolean> updateTerminatioin(PactRentTerminationParam param);
	
	/**
	 * 
	* @Title: applyCheck
	* @Description: 申请审核
	* @author LiuYuChi
	* @param dissolutionCode
	* @return
	* @throws
	 */
	ApiResult<Boolean> applyCheck(PactRentTerminationParam param);
	
	/**
	 * 
	* @Title: reject
	* @Description: 驳回解约协议
	* @author LiuYuChi
	* @param pactRentTerminationDO
	* @return
	* @throws
	 */
	ApiResult<Boolean> reject(PactRentTerminationParam param);
	
	/**
	 * 
	* @Title: delTermination
	* @Description: 删除解约协议
	* @author LiuYuChi
	* @param dissolutionCode
	* @return
	* @throws
	 */
	ApiResult<Boolean> delTermination(PactRentTerminationParam param);
	
	/**
	 * 
	* @Title: check
	* @Description: 审核通过
	* @author LiuYuChi
	* @param dissolutionCode
	* @param pin
	* @param ip
	* @return
	* @throws
	 */
	ApiResult<Boolean> check(PactRentTerminationParam param);
}
