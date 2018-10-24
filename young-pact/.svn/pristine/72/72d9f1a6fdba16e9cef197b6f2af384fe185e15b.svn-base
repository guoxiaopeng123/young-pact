package com.young.pact.controller.commonmeterread;

import java.util.List;

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
import com.young.pact.api.domain.param.rest.commonmeterread.CommonMeterReadParam;
import com.young.pact.api.domain.param.rest.commonmeterread.MeterGoodsParam;
import com.young.pact.api.domain.result.rest.commonmeterread.CommonMeterReadResult;
import com.young.pact.api.domain.result.rest.commonmeterread.MeterGoodsResult;
import com.young.pact.api.service.rest.commonmeterread.MeterGoodsService;
import com.young.pact.commom.LoginConsts;

/**
 * @描述 : 抄表信息
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月2日 下午6:32:28
 */
@RestController
@RequestMapping("/commonmeterread")
public class CommonMeterReadController extends BaseController{
    private static Log LOG = LogFactory.getLog(CommonMeterReadController.class);
    private MeterGoodsService meterGoodsService;
    /**
    * @Title: saveMeterReadRedis
    * @Description: TODO( 保存抄表信息和物品信息 )
    * @author GuoXiaoPeng
    * @param param 抄表信息和物品信息
    * @return 返回结果
    */
    @RequestMapping(value = "/saveMeterReadRedis", method = RequestMethod.POST)
    public ApiResult<String> saveMeterReadRedis(HttpServletRequest request, @RequestBody MeterGoodsParam param){
        ApiResult<String> result = new ApiResult<>();
        try {
            if(null!=param){
                String pin = (String) request.getAttribute(LoginConsts.PIN);
                String ip =super.getRemoteIPs(request);
                param.setCreateName(pin);
                param.setIp(ip);
            }
            result = meterGoodsService.saveMeterGoodsRedis(param);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
    /**
    * @Title: getMeterReadRedis
    * @Description: TODO(根据缓存key获取抄表信息和物品信息)
    * @author GuoXiaoPeng 
    * @param redisKey 缓存key
    * @return 抄表信息和物品信息
    */
    @RequestMapping(value = "/getMeterReadRedis/{redisKey}" , method = RequestMethod.GET)
    public ApiResult<MeterGoodsResult> getMeterReadRedis(HttpServletRequest request,@PathVariable String redisKey){
        ApiResult<MeterGoodsResult> result = new ApiResult<>();
        try {
             result = meterGoodsService.getMeterGoodsRedis(redisKey);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
    
    /**
     * 
    * @Title: insertMeterRead
    * @Description: 新增抄表
    * @author GuoXiaoPeng
    * @param param 抄表信息
    * @return 返回结果
    * @throws 异常
     */
    @PermissionsCode(code = "rest_meterread_save")
    @RequestMapping(value = "/insertMeterRead" , method = RequestMethod.POST)
    ApiResult<Boolean> insertMeterRead(HttpServletRequest request,@RequestBody CommonMeterReadParam param){
        ApiResult<Boolean> result =new ApiResult<>();
        try {
            if(null!=param){
                String pin = (String) request.getAttribute(LoginConsts.PIN);
                String ip =super.getRemoteIPs(request);
                param.setCreateName(pin);
                param.setIp(ip);
            }
            result = meterGoodsService.insertMeterRead(param);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
    
    /**
     * 
    * @Title: getGoods
    * @Description: 抄表详情
    * @author GuoXiaoPeng
    * @param id
    * @return 抄表详情信息
    * @throws 异常
     */
    @PermissionsCode(code = "rest_meterread_detail")
    @RequestMapping(value = "/getMeterRead/{id}" , method = RequestMethod.GET)
    ApiResult<CommonMeterReadResult> getMeterRead(HttpServletRequest request,@PathVariable Long id){
        ApiResult<CommonMeterReadResult> result =new ApiResult<>();
        try {
            result = meterGoodsService.getMeterRead(id);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
    
    /**
     * 
    * @Title: listMeterRead
    * @Description: 抄表列表
    * @author GuoXiaoPeng
    * @param param 查询条件
    * @return 返回结果
    * @throws 异常
     */
    @PermissionsCode(code = "rest_meterread_page")
    @RequestMapping(value = "/listMeterRead" , method = RequestMethod.POST)
    ApiResult <List<CommonMeterReadResult>> listMeterRead(HttpServletRequest request,@RequestBody CommonMeterReadParam param){
        ApiResult<List<CommonMeterReadResult>> result =new ApiResult<>();
        try {
            result = meterGoodsService.listMeterRead(param);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
    
    /**
     * 
    * @Title: updateMeterRead
    * @Description: 修改抄表信息
    * @author GuoXiaoPeng
    * @param param 抄表信息
    * @return 返回结果
    * @throws 异常
     */
    @PermissionsCode(code = "rest_meterread_update")
    @RequestMapping(value = "/updateMeterRead" , method = RequestMethod.POST)
    ApiResult<Boolean> updateMeterRead(HttpServletRequest request,@RequestBody CommonMeterReadParam param){
        ApiResult<Boolean> result =new ApiResult<>();
        try {
            if(null!=param){
                String pin = (String) request.getAttribute(LoginConsts.PIN);
                String ip =super.getRemoteIPs(request);
                param.setModifiedName(pin);
                param.setIp(ip);
            }
            result = meterGoodsService.updateMeterRead(param);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
    
    /**
     * 
    * @Title: removeMeterRead
    * @Description: 删除抄表信息
    * @author GuoXiaoPeng
    * @param id 记录标识
    * @return 返回结果
    * @throws 异常
     */
    @PermissionsCode(code = "rest_meterread_remove")
    @RequestMapping(value = "/removeMeterRead" , method = RequestMethod.POST)
    ApiResult<Boolean> removeMeterRead(HttpServletRequest request,@RequestBody CommonMeterReadParam commonMeterReadParam){
        ApiResult<Boolean> result =new ApiResult<>();
        try {
            String pin = (String) request.getAttribute(LoginConsts.PIN);
            String ip =super.getRemoteIPs(request);
            commonMeterReadParam.setCreateName(pin);
            commonMeterReadParam.setIp(ip);
            result = meterGoodsService.removeMeterRead(commonMeterReadParam);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
    public MeterGoodsService getMeterGoodsService() {
        return meterGoodsService;
    }
    public void setMeterGoodsService(MeterGoodsService meterGoodsService) {
        this.meterGoodsService = meterGoodsService;
    }
}
