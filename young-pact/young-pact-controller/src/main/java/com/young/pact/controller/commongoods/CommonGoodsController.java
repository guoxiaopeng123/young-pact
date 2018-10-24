package com.young.pact.controller.commongoods;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tools.common.springmvc.base.BaseController;
import com.young.common.annotation.PermissionsCode;
import com.young.common.domain.ApiResult;
import com.young.common.page.PageBean;
import com.young.pact.api.domain.param.rest.commongoods.CommonGoodsParam;
import com.young.pact.api.domain.result.rest.commongoods.CommonGoodsResult;
import com.young.pact.api.service.rest.commongoods.CommonGoodsService;
import com.young.pact.commom.LoginConsts;

/**
 * 
* @ClassName: CommonGoodsController
* @Description:公共物品
* @author LiuYuChi
* @date 2018年8月14日 下午1:39:03
*
 */
@RestController
@RequestMapping("/commonGoodsController")
public class CommonGoodsController extends BaseController{
	
	private static Log LOG = LogFactory.getLog(CommonGoodsController.class);
	private CommonGoodsService commonGoodsService;

	/**
	 * 
	* @Title: insertGoods
	* @Description: 新增物品信息
	* @author LiuYuChi
	* @param request
	* @param param
	* @return
	* @throws
	 */
	@PermissionsCode(code = "rest_goods_save")
	@RequestMapping(value = "/insertGoods", method = RequestMethod.POST)
    public ApiResult<Boolean> insertGoods(HttpServletRequest request, @RequestBody CommonGoodsParam param){
        ApiResult<Boolean> result = new ApiResult<>();
        try {
            if(null!=param){
            	String pin = (String) request.getAttribute(LoginConsts.PIN);
            	String ip =super.getRemoteIPs(request);
                param.setCreateName(pin);
                param.setIp(ip);
            }
            result = commonGoodsService.insertGoods(param);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
	
	/**
	 * 
	* @Title: delGoods
	* @Description: 删除物品信息
	* @author LiuYuChi
	* @param request
	* @param id
	* @return
	* @throws
	 */
	@PermissionsCode(code = "rest_goods_remove")
	@RequestMapping(value = "/delGoods", method = RequestMethod.POST)
    public ApiResult<Boolean> delGoods(HttpServletRequest request, @RequestBody CommonGoodsParam param){
        ApiResult<Boolean> result = new ApiResult<>();
        try {
            String pin = (String) request.getAttribute(LoginConsts.PIN);
            String ip =super.getRemoteIPs(request);
            param.setIp(ip);
            param.setCreateName(pin);
            result = commonGoodsService.delGoods(param);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
	
	/**
	 * 
	* @Title: getGoods
	* @Description: 获取物品详情
	* @author LiuYuChi
	* @param request
	* @param id
	* @return
	* @throws
	 */
	@PermissionsCode(code = "rest_goods_detail")
	@RequestMapping(value = "/getGoods/{id}", method = RequestMethod.GET)
    public ApiResult<CommonGoodsResult> getGoods(HttpServletRequest request, @PathVariable Long id){
        ApiResult<CommonGoodsResult> result = new ApiResult<>();
        try {
            result = commonGoodsService.getGoods(id);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
	
	
	/**
	 * 
	* @Title: updateGoods
	* @Description: 修改物品
	* @author LiuYuChi
	* @param request
	* @param param
	* @return
	* @throws
	 */
	@PermissionsCode(code = "rest_goods_update")
	@RequestMapping(value = "/updateGoods", method = RequestMethod.POST)
    public ApiResult<Boolean> updateGoods(HttpServletRequest request, @RequestBody CommonGoodsParam param){
        ApiResult<Boolean> result = new ApiResult<>();
        try {
        	if (param!=null) {
        		String pin = (String) request.getAttribute(LoginConsts.PIN);
            	String ip =super.getRemoteIPs(request);
                param.setModifiedName(pin);
                param.setIp(ip);
			}
            result = commonGoodsService.updateGoods(param);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
	
	/**
	 * 
	* @Title: listGoods
	* @Description: 物品列表
	* @author LiuYuChi
	* @param request
	* @param param
	* @return
	* @throws
	 */
	@PermissionsCode(code = "rest_goods_page")
	@RequestMapping(value = "/listGoods", method = RequestMethod.POST)
    public ApiResult<PageBean<CommonGoodsResult>> listGoods(HttpServletRequest request, @RequestBody CommonGoodsParam param){
		ApiResult<PageBean<CommonGoodsResult>> result = new ApiResult<>();
		try {
            if(null != param){
            	result = commonGoodsService.listGoods(param);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

	public CommonGoodsService getCommonGoodsService() {
		return commonGoodsService;
	}
	public void setCommonGoodsService(CommonGoodsService commonGoodsService) {
		this.commonGoodsService = commonGoodsService;
	}
	
}
