package com.young.pact.controller.rentcontinued;

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
import com.young.pact.api.domain.param.rest.rentbase.RentPactParam;
import com.young.pact.api.domain.param.rest.rentcontinued.RentContinuedParam;
import com.young.pact.api.domain.result.rest.rentbase.RentPactResult;
import com.young.pact.api.domain.result.rest.rentcontinued.RentContinuedDetailResult;
import com.young.pact.api.domain.result.rest.rentcontinued.RentContinuedResult;
import com.young.pact.api.service.rest.rentcontinued.RentContinuedService;
import com.young.pact.common.constant.LoginConsts;

/**
 * @描述 : 续签协议
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月11日 下午2:06:27
 */
@RestController()
@RequestMapping("/rentccontinued")
public class RentContinuedController extends BaseController{

    private static Log LOG = LogFactory.getLog(RentContinuedController.class);
    
    private RentContinuedService rentContinuedService;

    /**
     * @Title: saveRentContinuedRedis
     * @Description: TODO( 缓存保存续签协议 )
     * @author GuoXiaoPeng
     * @param param 续签协议信息
     * @return 返回结果
     * @throws 异常
      */
    @RequestMapping(value = "/saveRentContinuedRedis", method = RequestMethod.POST)
    ApiResult<String> saveRentContinuedRedis(HttpServletRequest request, @RequestBody RentContinuedParam param){
        ApiResult<String> result = new ApiResult<>();
        try {
            if(null!=param){
                param.setCreateName((String) request.getAttribute(LoginConsts.PIN));
                param.setIp(super.getRemoteIPs(request));
                result= rentContinuedService.saveRentContinuedRedis(param);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
     /**
     * @Title: getRentContinuedRedis
     * @Description: TODO(缓存查询续签协议 )
     * @author GuoXiaoPeng
     * @param redisKey 缓存的key 
     * @return 返回结果
     * @throws 异常
      */
    @RequestMapping(value = "/getRentContinuedRedis/{redisKey}", method = RequestMethod.GET)
    ApiResult<RentContinuedResult> getRentContinuedRedis(@PathVariable String redisKey){
         ApiResult<RentContinuedResult> result= new ApiResult<>();
         try {
             result = rentContinuedService.getRentContinuedRedis(redisKey);
         } catch (Exception e) {
             LOG.error(e.getMessage(), e);
         }
         return result;
    }
     /**
     * @Title: saveRentContinuedPactRedis
     * @Description: TODO( 缓存保存合同)
     * @author GuoXiaoPeng
     * @param param 合同信息
     * @return 返回结果
     * @throws 异常
      */
    @RequestMapping(value = "/saveRentContinuedPactRedis", method = RequestMethod.POST)
    ApiResult<String> saveRentContinuedPactRedis(HttpServletRequest request,@RequestBody RentPactParam param){
        ApiResult<String> result = new  ApiResult<>();
        try {
            if(null!=param){
                param.setCreateName((String) request.getAttribute(LoginConsts.PIN));
                param.setIp(super.getRemoteIPs(request));
                result= rentContinuedService.saveRentContinuedPactRedis(param);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
     /**
     * @Title: getRentContinuedPactRedis
     * @Description: TODO(缓存查询合同 )
     * @author GuoXiaoPeng
     * @param redisKey 缓存的key 
     * @return 返回结果
     * @throws 异常
      */
    @RequestMapping(value = "/getRentContinuedPactRedis/{redisKey}", method = RequestMethod.GET)
    ApiResult<RentPactResult> getRentContinuedPactRedis(@PathVariable String redisKey){
        ApiResult<RentPactResult> result= new ApiResult<>();
        try {
            result = rentContinuedService.getRentContinuedPactRedis(redisKey);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
    /**
     * @Title: saveRentContinuedPact
     * @Description: TODO( 保存续签协议 )
     * @author GuoXiaoPeng
     * @param param 续签协议信息
     * @return 返回结果
     */
    @PermissionsCode(code = "rest_rent_continued_save")
    @RequestMapping(value="/saveRentContinuedPact", method=RequestMethod.POST)
    ApiResult<String> saveRentContinuedPact(HttpServletRequest request,@RequestBody RentContinuedParam param){
        ApiResult<String> result = new ApiResult<>();
        try {
            if(null != param){
                String pin = (String) request.getAttribute(LoginConsts.PIN);
                param.setCreateName(pin);
                param.setIp(super.getRemoteIPs(request));
            }
            result = rentContinuedService.saveRentContinuedPact(param);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
     /**
     * @Title: listRentContinued
     * @Description: TODO(分页查询续签协议管理列表 )
     * @author GuoXiaoPeng
     * @param param 查询条件
     * @return 续签协议列表信息
     * @throws
     */
     @PermissionsCode(code = "rest_rent_continued_page")
     @RequestMapping(value="/listRentContinued", method=RequestMethod.POST)
     ApiResult<PageBean<RentContinuedResult>> listRentContinued(HttpServletRequest request,@RequestBody RentContinuedParam param){
         ApiResult<PageBean<RentContinuedResult>> result = new ApiResult<>();
         try {
             if(null != param){
                 String pin = (String) request.getAttribute(LoginConsts.PIN);
                 String ip = super.getRemoteIPs(request);
                 Integer scope = (Integer) request.getAttribute(LoginConsts.SCOPE);
                 param.setCreateName(pin);
                 param.setIp(ip);
                 param.setScope(scope);
             }
             result = rentContinuedService.listRentContinued(param);
         } catch (Exception e) {
             LOG.error(e.getMessage(), e);
         }
         return result;
     }
     /**
     * @Title: getRentContinued
     * @Description: TODO( 续签协议详情)
     * @author GuoXiaoPeng
     * @param renewCode 续签协议编码
     * @return 续签协议详情信息
     * @throws
     */
     @PermissionsCode(code = "rest_rent_continued_detail")
     @RequestMapping(value="/getRentContinued/{renewCode}", method=RequestMethod.GET)
     ApiResult<RentContinuedDetailResult> getRentContinued(HttpServletRequest request,@PathVariable String renewCode){
         ApiResult<RentContinuedDetailResult> result = new ApiResult<>();
         try {
             String pin = (String) request.getAttribute(LoginConsts.PIN);
             String ip = super.getRemoteIPs(request);
             Integer scope = (Integer) request.getAttribute(LoginConsts.SCOPE);
             RentContinuedParam param = new RentContinuedParam();
             param.setCreateName(pin);
             param.setIp(ip);
             param.setScope(scope);
             param.setRenewCode(renewCode);
             result = rentContinuedService.getRentContinued(param);
         } catch (Exception e) {
             LOG.error(e.getMessage(), e);
         }
         return result;
     }
     /**
     * @Title: auditRentContinued
     * @Description: TODO(申请审核续签协议 )
     * @author GuoXiaoPeng
     * @param param 续签协议编码
     * @return 返回结果
     * @throws
     */
     @SuppressWarnings("unused")
     @RequestMapping(value="/auditRentContinued", method=RequestMethod.POST)
     @PermissionsCode(code = "rest_rent_continued_audit")
     ApiResult<Boolean> auditRentContinued(HttpServletRequest request,@RequestBody RentContinuedParam param){
         ApiResult<Boolean> result = new ApiResult<>();
         try {
             if(null != param){
                 String pin = (String) request.getAttribute(LoginConsts.PIN);
                 String ip = super.getRemoteIPs(request);
                 param.setModifiedName(pin);
                 param.setIp(super.getRemoteIPs(request));
             }
             result = rentContinuedService.auditRentContinued(param);
         } catch (Exception e) {
             LOG.error(e.getMessage(), e);
         }
         return result;
     }
     /**
     * @Title: reviewPassRentContinued
     * @Description: TODO(审核通过续签协议 )
     * @author GuoXiaoPeng
     * @param param 续签协议编码
     * @return 返回结果
     * @throws
     */
     @SuppressWarnings("unused")
     @RequestMapping(value="/reviewPassRentContinued", method=RequestMethod.POST)
     @PermissionsCode(code = "rest_rent_continued_passed")
     ApiResult<Boolean> reviewPassRentContinued(HttpServletRequest request,@RequestBody RentContinuedParam param){
         ApiResult<Boolean> result = new ApiResult<>();
         try {
             if(null != param){
                 String pin = (String) request.getAttribute(LoginConsts.PIN);
                 String ip = super.getRemoteIPs(request);
                 param.setModifiedName(pin);
                 param.setIp(super.getRemoteIPs(request));
             }
             result = rentContinuedService.reviewPassRentContinued(param);
         } catch (Exception e) {
             LOG.error(e.getMessage(), e);
         }
         return result;
     }
     /**
     * @Title: reviewDismissalRentContinued
     * @Description: TODO(审核驳回续签协议 )
     * @author GuoXiaoPeng
     * @param rentPactCode 续签协议编码
     * @return 返回结果
     * @throws
     */
     @SuppressWarnings("unused")
     @RequestMapping(value="/reviewDismissalRentContinued", method=RequestMethod.POST)
     @PermissionsCode(code = "rest_rent_continued_reject")
     ApiResult<Boolean> reviewDismissalRentContinued(HttpServletRequest request,@RequestBody RentContinuedParam param){
         ApiResult<Boolean> result = new ApiResult<>();
         try {
             if(null != param){
                 String pin = (String) request.getAttribute(LoginConsts.PIN);
                 String ip = super.getRemoteIPs(request);
                 param.setModifiedName(pin);
                 param.setIp(super.getRemoteIPs(request));
             }
             result = rentContinuedService.reviewDismissalRentContinued(param);
         } catch (Exception e) {
             LOG.error(e.getMessage(), e);
         }
         return result;
     }
     /**
     * @Title: updateRentContinued
     * @Description: TODO( 修改续签协议)
     * @author GuoXiaoPeng
     * @param param 续签协议信息
     * @return 返回结果
     * @throws
      */
     @PermissionsCode(code = "rest_rent_continued_update")
     @RequestMapping(value="/updateRentContinued", method=RequestMethod.POST)
     ApiResult<Boolean> updateRentContinued(HttpServletRequest request,@RequestBody RentContinuedParam param){
         ApiResult<Boolean> result = new ApiResult<>();
         try {
             if(null != param){
                 String pin = (String) request.getAttribute(LoginConsts.PIN);
                 String ip = super.getRemoteIPs(request);
                 param.setModifiedName(pin);
                 param.setIp(ip);
             }
             result = rentContinuedService.updateRentContinued(param);
         } catch (Exception e) {
             LOG.error(e.getMessage(), e);
         }
         return result;
     }
     
     /**
     * @Title: rempveRentContinued
     * @Description: TODO(删除续签协议 )
     * @author GuoXiaoPeng
     * @param param 续签协议 编码
     * @return 返回结果
     * @throws
     */
    @PermissionsCode(code = "rest_rent_continued_remove")
    @RequestMapping(value="/removeRentContinued", method=RequestMethod.POST)
    ApiResult<Boolean> removeRentContinued(HttpServletRequest request,@RequestBody RentContinuedParam param){
        ApiResult<Boolean> result = new ApiResult<>();
        try {
            if(null != param){
                String pin = (String) request.getAttribute(LoginConsts.PIN);
                String ip = super.getRemoteIPs(request);
                param.setModifiedName(pin);
                param.setIp(ip);
            }
            result = rentContinuedService.removeRentContinued(param);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
    public RentContinuedService getRentContinuedService() {
        return rentContinuedService;
    }

    public void setRentContinuedService(RentContinuedService rentContinuedService) {
        this.rentContinuedService = rentContinuedService;
    }
    
}
