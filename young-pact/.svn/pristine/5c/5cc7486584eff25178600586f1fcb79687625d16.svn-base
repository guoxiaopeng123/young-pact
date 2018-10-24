package com.young.pact.api.service.rest.commongoods;

import com.young.common.domain.ApiResult;
import com.young.common.page.PageBean;
import com.young.pact.api.domain.param.rest.commongoods.CommonGoodsParam;
import com.young.pact.api.domain.result.rest.commongoods.CommonGoodsResult;

public interface CommonGoodsService {

	/**
	 * 
	* @Title: insertGoods
	* @Description: 新增物品
	* @author LiuYuChi
	* @param param
	* @return
	* @throws
	 */
	ApiResult<Boolean> insertGoods(CommonGoodsParam param);
	
	/**
	 * 
	* @Title: getGoods
	* @Description: 物品详情
	* @author LiuYuChi
	* @param id
	* @return
	* @throws
	 */
	ApiResult<CommonGoodsResult> getGoods(Long id);
	
	/**
	 * 
	* @Title: listGoods
	* @Description: 物品列表
	* @author LiuYuChi
	* @param param
	* @return
	* @throws
	 */
	ApiResult<PageBean<CommonGoodsResult>> listGoods(CommonGoodsParam param);
	
	/**
	 * 
	* @Title: updateGoods
	* @Description: 修改物品信息
	* @author LiuYuChi
	* @param param
	* @return
	* @throws
	 */
	ApiResult<Boolean> updateGoods(CommonGoodsParam param);
	
	/**
	 * @描述 : 删除物品信息
	 * @创建者 : guoxiaopeng
	 * @创建时间 : 2018年8月22日 上午10:59:51
	 * @param param 合同编码和物品主键
	 * @return 返回结果
	 */
    ApiResult<Boolean> delGoods(CommonGoodsParam param);
}
