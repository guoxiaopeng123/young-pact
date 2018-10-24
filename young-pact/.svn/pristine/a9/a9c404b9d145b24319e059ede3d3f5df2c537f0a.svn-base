package com.young.pact.controller.rentturn;

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
import com.young.pact.api.domain.param.rest.rentturn.RentTurnParam;
import com.young.pact.api.domain.param.rest.rentturn.RentTurnProtocolParam;
import com.young.pact.api.domain.result.rest.rentbase.RentPactResult;
import com.young.pact.api.domain.result.rest.rentturn.RentTurnProtocolResult;
import com.young.pact.api.domain.result.rest.rentturn.RentTurnResult;
import com.young.pact.api.service.rest.rentturn.RentTurnService;
import com.young.pact.common.constant.LoginConsts;

/**
 * @描述 : 转租协议
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月7日 下午2:08:14
 */
@RestController()
@RequestMapping("/rentturn")
public class RentTurnController extends BaseController{

    private static Log LOG = LogFactory.getLog(RentTurnController.class);
    private RentTurnService rentTurnService;
    /**
    * @Title: saveRentTurnRedis
    * @Description: TODO(把转租协议信息保存到缓存   第一步)
    * @author GuoXiaoPeng
    * @param param 转租协议信息
    * @return 缓存key
    * @throws 异常
    */
    @RequestMapping(value = "/saveRentTurnRedis", method = RequestMethod.POST)
    ApiResult<String> saveRentTurnRedis(HttpServletRequest request, @RequestBody RentTurnProtocolParam param){
        ApiResult<String> result = new ApiResult<>();
        try {
            if(null!=param){
                String pin = (String) request.getAttribute(LoginConsts.PIN);
                String ip = super.getRemoteIPs(request);
                param.setCreateName(pin);
                param.setIp(ip);
                result= rentTurnService.saveRentTurnRedis(param);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
    /**
    * @Title: getRentTurnRedis
    * @Description: TODO(根据缓存key从缓存中获取转租协议   第一步)
    * @author GuoXiaoPeng
    * @param redisKey 缓存key
    * @return 转租协议
    * @throws 异常
     */
    @RequestMapping(value = "/getRentTurnRedis/{redisKey}", method = RequestMethod.GET)
    ApiResult<RentTurnProtocolResult> getRentTurnRedis(@PathVariable String redisKey){
        ApiResult<RentTurnProtocolResult> result= new ApiResult<>();
        try {
            result = rentTurnService.getRentTurnRedis(redisKey);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
    /**
     * @Title: saveRentTurnPactRedis
     * @Description: TODO(转租第四步录入合同 )
     * @author GuoXiaoPeng
     * @param rentBaseParam 合同信息
     * @return 返回结果
     * @throws 异常
     */
    @RequestMapping(value = "/saveRentTurnPactRedis", method = RequestMethod.POST)
    ApiResult<String> saveRentTurnPactRedis(HttpServletRequest request,@RequestBody RentPactParam param){
        ApiResult<String> result = new  ApiResult<>();
        try {
            if(null!=param){
                String pin = (String) request.getAttribute(LoginConsts.PIN);
                String ip = super.getRemoteIPs(request);
                param.setCreateName(pin);
                param.setIp(ip);
                result= rentTurnService.saveRentTurnPactRedis(param);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
    /**
    * @Title: getRentTurnPactRedis
    * @Description: TODO( 根据缓存key查询转租协议的合同信息)
    * @author GuoXiaoPeng
    * @param redisKey 缓存key
    * @return 返回结果
    * @throws 异常
     */
    @RequestMapping(value = "/getRentTurnPactRedis/{redisKey}", method = RequestMethod.GET)
    ApiResult<RentPactResult> getRentTurnPactRedis(@PathVariable String redisKey){
        ApiResult<RentPactResult> result= new ApiResult<>();
        try {
            result = rentTurnService.getRentTurnPactRedis(redisKey);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
    /**
    * @Title: saveRentTurnPact
    * @Description: TODO( 保存转租协议+新合同 )
    * @author GuoXiaoPeng
    * @param request 
    * @param param 旧的托出合同编码
    * @return 新的托出合同编码
    * @throws 异常
     */
    @PermissionsCode(code = "rest_rent_turn_save")
    @RequestMapping(value="/saveRentTurnPact", method=RequestMethod.POST)
    public ApiResult<String> saveRentTurnPact(HttpServletRequest request,@RequestBody RentTurnParam param){
        ApiResult<String> result = new ApiResult<>();
        try {
            if(null != param){
                String pin = (String) request.getAttribute(LoginConsts.PIN);
                String ip = super.getRemoteIPs(request);
                param.setCreateName(pin);
                param.setIp(ip);
            }
            result = rentTurnService.saveRentTurnPact(param);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
        
    }
    /**
     * 
    * @Title: listRentTurn
    * @Description: TODO( 转租协议分页列表 )
    * @author GuoXiaoPeng
    * @param request 
    * @param param 查询条件
    * @return 转租协议
    * @throws 异常
     */
    @PermissionsCode(code = "rest_rent_turn_page")
    @RequestMapping(value="/listRentTurn", method=RequestMethod.POST)
    public ApiResult<PageBean<RentTurnResult>> listRentTurn(HttpServletRequest request,@RequestBody RentTurnParam param){
        ApiResult<PageBean<RentTurnResult>> result = new ApiResult<>();
        try {
            String pin = (String) request.getAttribute(LoginConsts.PIN);
            String ip = super.getRemoteIPs(request);
            Integer scope = (Integer) request.getAttribute(LoginConsts.SCOPE);
            param.setCreateName(pin);
            param.setIp(ip);
            param.setScope(scope);
            result = rentTurnService.listRentTurn(param);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
    /**
     * 
    * @Title: getRentTurn
    * @Description: TODO( 根据协议编码查询转租协议详情 )
    * @author GuoXiaoPeng
    * @param reletCode 协议编码
    * @return 转租协议详情
    * @throws 异常
     */
    @PermissionsCode(code = "rest_rent_turn_detail")
    @RequestMapping(value="/getRentTurn/{reletCode}", method=RequestMethod.GET)
    public ApiResult<RentTurnProtocolResult> getRentTurn(HttpServletRequest request,@PathVariable String reletCode) {
        ApiResult<RentTurnProtocolResult> result = new ApiResult<>();
        try {
            String pin = (String) request.getAttribute(LoginConsts.PIN);
            String ip = super.getRemoteIPs(request);
            Integer scope = (Integer) request.getAttribute(LoginConsts.SCOPE);
            RentTurnParam param = new RentTurnParam();
            param.setCreateName(pin);
            param.setIp(ip);
            param.setScope(scope);
            param.setReletCode(reletCode);
            result = rentTurnService.getRentTurn(param);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
    /**
     * @Title: auditRentTurn
     * @Description: TODO(申请审核转租协议 )
     * @author GuoXiaoPeng
     * @param param 转租协议编码
     * @return 返回结果
     * @throws 异常
     */
    @PermissionsCode(code = "rest_rent_turn_audit")
    @RequestMapping(value="/auditRentTurn", method=RequestMethod.POST)
    ApiResult<Boolean> auditRentTurn(HttpServletRequest request,@RequestBody RentTurnParam param){
         ApiResult<Boolean> result = new ApiResult<>();
         try {
             if(null != param){
                 String pin = (String) request.getAttribute(LoginConsts.PIN);
                 String ip = super.getRemoteIPs(request);
                 param.setModifiedName(pin);
                 param.setIp(ip);
             }
             result = rentTurnService.auditRentTurn(param);
         } catch (Exception e) {
             LOG.error(e.getMessage(), e);
         }
         return result;
     }
     /**
     * @Title: reviewPassRentTurn
     * @Description: TODO(审核通过转租协议 )
     * @author GuoXiaoPeng
     * @param param 转租协议编码
     * @return 返回结果
     * @throws 异常
     */
    @PermissionsCode(code = "rest_rent_turn_passed")
    @RequestMapping(value="/reviewPassRentTurn", method=RequestMethod.POST)
     ApiResult<Boolean> reviewPassRentTurn(HttpServletRequest request,@RequestBody RentTurnParam param){
         ApiResult<Boolean> result = new ApiResult<>();
         try {
             if(null != param){
                 String pin = (String) request.getAttribute(LoginConsts.PIN);
                 String ip = super.getRemoteIPs(request);
                 param.setModifiedName(pin);
                 param.setIp(ip);
             }
             result = rentTurnService.reviewPassRentTurn(param);
         } catch (Exception e) {
             LOG.error(e.getMessage(), e);
         }
         return result;
     }
     /**
     * @Title: reviewDismissalRentTurn
     * @Description: TODO(审核驳回转租协议 )
     * @author GuoXiaoPeng
     * @param rentPactCode 转租协议编码
     * @return 返回结果
     * @throws 异常
     */
    @PermissionsCode(code = "rest_rent_turn_reject")
    @RequestMapping(value="/reviewDismissalRentTurn", method=RequestMethod.POST)
     ApiResult<Boolean> reviewDismissalRentTurn(HttpServletRequest request,@RequestBody RentTurnParam param){
         ApiResult<Boolean> result = new ApiResult<>();
         try {
             if(null != param){
                 String pin = (String) request.getAttribute(LoginConsts.PIN);
                 String ip = super.getRemoteIPs(request);
                 param.setModifiedName(pin);
                 param.setIp(ip);
             }
             result = rentTurnService.reviewDismissalRentTurn(param);
         } catch (Exception e) {
             LOG.error(e.getMessage(), e);
         }
         return result;
     }
     /**
     * @Title: updateRentTurn
     * @Description: TODO( 修改转租协议)
     * @author GuoXiaoPeng
     * @param param 转租协议信息
     * @return 返回结果
     * @throws 异常
      */
    @PermissionsCode(code = "rest_rent_turn_update")
    @RequestMapping(value="/updateRentTurn", method=RequestMethod.POST)
     ApiResult<Boolean> updateRentTurn(HttpServletRequest request,@RequestBody RentTurnProtocolParam param){
         ApiResult<Boolean> result = new ApiResult<>();
         try {
             if(null != param){
                 String pin = (String) request.getAttribute(LoginConsts.PIN);
                 String ip = super.getRemoteIPs(request);
                 param.setCreateName(pin);
                 param.setIp(ip);
             }
             result = rentTurnService.updateRentTurn(param);
         } catch (Exception e) {
             LOG.error(e.getMessage(), e);
         }
         return result;
     }
     
     /**
     * @Title: rempveRentTurn
     * @Description: TODO(删除转租协议 )
     * @author GuoXiaoPeng
     * @param param 转租协议 编码
     * @return 返回结果
     * @throws 异常
     */
     @PermissionsCode(code = "rest_rent_turn_remove")
     @RequestMapping(value="/removeRentTurn", method=RequestMethod.POST)
     ApiResult<Boolean> removeRentTurn(HttpServletRequest request,@RequestBody RentTurnParam param){
         ApiResult<Boolean> result = new ApiResult<>();
         try {
             if(null != param){
                 String pin = (String) request.getAttribute(LoginConsts.PIN);
                 String ip = super.getRemoteIPs(request);
                 param.setModifiedName(pin);
                 param.setIp(ip);
             }
             result = rentTurnService.removeRentTurn(param);
         } catch (Exception e) {
             LOG.error(e.getMessage(), e);
         }
         return result;
     }
    public RentTurnService getRentTurnService() {
        return rentTurnService;
    }
    public void setRentTurnService(RentTurnService rentTurnService) {
        this.rentTurnService = rentTurnService;
    }
}
