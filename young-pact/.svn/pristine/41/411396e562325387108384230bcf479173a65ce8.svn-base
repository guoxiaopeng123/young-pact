package com.young.pact.controller.rentbase;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tools.common.springmvc.base.BaseController;
import com.tools.common.util.string.StringUtil;
import com.young.common.annotation.PermissionsCode;
import com.young.common.domain.ApiResult;
import com.young.common.page.PageBean;
import com.young.pact.api.domain.param.rest.rentbase.RentBaseParam;
import com.young.pact.api.domain.param.rest.rentbase.RentPactParam;
import com.young.pact.api.domain.param.rest.rentcustomer.RentCustomerParam;
import com.young.pact.api.domain.result.rest.financereceiptpay.FinanceReceiptPayResult;
import com.young.pact.api.domain.result.rest.rentbase.RentBaseResult;
import com.young.pact.api.domain.result.rest.rentbase.RentPactResult;
import com.young.pact.api.service.rest.rentbase.RentBaseService;
import com.young.pact.common.constant.LoginConsts;

/**
 * @描述 : 托出合同
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月2日 下午2:46:09
 */
@RestController
@RequestMapping("/rentbase")
public class RentBaseController extends BaseController{

    private RentBaseService rentBaseService;
    private static Log LOG = LogFactory.getLog(RentBaseController.class);
    /**
     * 
    * @Title: savePactRedis
    * @Description: TODO( 往缓存中保存合同)
    * @author GuoXiaoPeng
    * @param param 合同信息
    * @return 返回结果
    */
    @RequestMapping(value = "/savePactRedis", method = RequestMethod.POST)
    public ApiResult<String> savePactRedis(HttpServletRequest request, @RequestBody RentPactParam param){
        ApiResult<String> result = new ApiResult<>();
        try {
            if(null!=param){
              String pin = (String) request.getAttribute(LoginConsts.PIN);
              String ip = super.getRemoteIPs(request);
              param.setCreateName(pin);
              param.setIp(ip);
            }
            result = rentBaseService.savePactRedis(param);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
    /**
    * @Title: getPactRedis
    * @Description: TODO( 根据缓存key获取合同信息)
    * @author GuoXiaoPeng
    * @param redisKey 缓存key
    * @return 合同信息
    */
    @RequestMapping(value = "/getPactRedis/{redisKey}" , method = RequestMethod.GET)
    public ApiResult<RentPactResult> getPactRedis(HttpServletRequest request,@PathVariable String redisKey){
        ApiResult<RentPactResult> result = new ApiResult<>();
        try {
             result = rentBaseService.getPactRedis(redisKey);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
    /**
    * @Title: saveRentPact
    * @Description: TODO( 保存托出合同)
    * @author GuoXiaoPeng
    * @param roomCode 房间编码
    * @return  返回结果
    */
    @PermissionsCode(code = "rest_rentbase_save")
    @RequestMapping(value = "/saveRentPact", method = RequestMethod.POST)
    public ApiResult<String> saveRentPact(HttpServletRequest request, @RequestBody RentBaseParam param){
        ApiResult<String> result = new ApiResult<>();
        try {
            if(null != param && StringUtil.isNotBlank(param.getRoomCode())){
              String pin = (String) request.getAttribute(LoginConsts.PIN);
              String ip = super.getRemoteIPs(request);
              param.setCreateName(pin);
              param.setIp(ip);
            }
            result = rentBaseService.saveRentPact(param);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
    /**
    * @Title: listRentPact
    * @Description: TODO( 托出合同列表 )
    * @author GuoXiaoPeng
    * @param request 
    * @param rentBaseParm 托出合同信息
    * @return 托出合同集合
    * @throws 异常
     */
    @SuppressWarnings("unused")
    @PermissionsCode(code = "rest_rentbase_page")
    @RequestMapping(value = "/listRentPact" , method = RequestMethod.POST)
    public ApiResult<PageBean<RentBaseResult>> listRentPact(HttpServletRequest request,@RequestBody RentBaseParam rentBaseParm){
        ApiResult<PageBean<RentBaseResult>> result = new ApiResult<>();
        try {
          String pin = (String) request.getAttribute(LoginConsts.PIN);
          String ip = super.getRemoteIPs(request);
          Integer scope = (Integer) request.getAttribute(LoginConsts.SCOPE);
          if(null!=rentBaseParm){
              rentBaseParm.setCreateName(pin);
              rentBaseParm.setScope(scope);
          }
          result = rentBaseService.listRentBase(rentBaseParm);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
    /**
     * @Title: getRentPactDetail
     * @Description: TODO(获取托出合同详情 )
     * @author GuoXiaoPeng
     * @param request
     * @param rentPactCode 托出合同编码
     * @return 返回结果
     * @throws 异常
      */
     @SuppressWarnings("unused")
     @PermissionsCode(code = "rest_rentbase_detail")
     @RequestMapping(value = "/getRentPactDetail/{rentPactCode}" , method = RequestMethod.GET)
     public ApiResult<RentPactResult> getRentPactDetail(HttpServletRequest request,@PathVariable String rentPactCode){
         ApiResult<RentPactResult> result = new ApiResult<>();
         try {
           String pin = (String) request.getAttribute(LoginConsts.PIN);
           String ip = super.getRemoteIPs(request);
           Integer scope = (Integer) request.getAttribute(LoginConsts.SCOPE);
           RentBaseParam rentBaseParam = new RentBaseParam();
           rentBaseParam.setCreateName(pin);
           rentBaseParam.setScope(scope);
           rentBaseParam.setRentPactCode(rentPactCode);
           result = rentBaseService.getRentBase(rentBaseParam);
         } catch (Exception e) {
             LOG.error(e.getMessage(), e);
         }
         return result;
     }
    /**
    * @Title: auditRentBase
    * @Description: TODO( 申请审核托出合同)
    * @author GuoXiaoPeng
    * @param request
    * @param rentPactCode 托出合同编码
    * @return 返回结果
    * @throws 异常
     */
    @SuppressWarnings("unchecked")
    @PermissionsCode(code = "rest_rentbase_audit")
    @RequestMapping(value = "/auditRentBase" , method = RequestMethod.POST)
    public ApiResult<RentPactResult> auditRentBase(HttpServletRequest request,@RequestBody RentBaseParam param){
        ApiResult<RentPactResult> result = new ApiResult<>();
        try {
            if(null!=param){
              String pin = (String) request.getAttribute(LoginConsts.PIN);
              String ip = super.getRemoteIPs(request);
              param.setCreateName(pin);
              param.setIp(ip);
            }
            result = rentBaseService.auditRentBase(param);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
   
    /**
     * @Title: reviewPassRentBase
     * @Description: TODO( 审核通过托出合同)
     * @author GuoXiaoPeng
     * @param request
     * @param rentPactCode 托出合同编码
     * @return 返回结果
     * @throws 异常
      */
    @SuppressWarnings("unchecked")
    @PermissionsCode(code = "rest_rentbase_passed")
    @RequestMapping(value = "/reviewPassRentBase" , method = RequestMethod.POST)
     public ApiResult<RentPactResult> reviewPassRentBase(HttpServletRequest request,@RequestBody RentBaseParam param){
         ApiResult<RentPactResult> result = new ApiResult<>();
         try {
             if(null!=param){
                 String pin = (String) request.getAttribute(LoginConsts.PIN);
                 String ip = super.getRemoteIPs(request);
                 param.setCreateName(pin);
                 param.setIp(ip);
             }
             result = rentBaseService.reviewPassRentBase(param);
         } catch (Exception e) {
             LOG.error(e.getMessage(), e);
         }
         return result;
     }
     /**
      * @Title: reviewDismissalRentBase
      * @Description: TODO( 审核驳回托出合同)
      * @author GuoXiaoPeng
      * @param request
      * @param rentPactCode 托出合同编码
      * @return 返回结果
      * @throws 异常 
       */
    @SuppressWarnings("unchecked")
    @PermissionsCode(code = "rest_rentbase_reject")
    @RequestMapping(value = "/reviewDismissalRentBase" , method = RequestMethod.POST)
    public ApiResult<RentPactResult> reviewDismissalRentBase(HttpServletRequest request,@RequestBody RentBaseParam param){
          ApiResult<RentPactResult> result = new ApiResult<>();
          try {
              if(null!=param){
                  String pin = (String) request.getAttribute(LoginConsts.PIN);
                  String ip = super.getRemoteIPs(request);
                  param.setCreateName(pin);
                  param.setIp(ip);
              }
              result = rentBaseService.reviewDismissalRentBase(param);
          } catch (Exception e) {
              LOG.error(e.getMessage(), e);
          }
          return result;
    }
    /**
    * @Title: updateRentCustomer
    * @Description: TODO( 修改托出合同租客+共同居住人 )
    * @author GuoXiaoPeng
    * @param request
    * @param param  租客+共同居住人信息
    * @return 返回结果
    * @throws 异常
     */
    @SuppressWarnings("unchecked")
    @PermissionsCode(code = "rest_rent_customer_update")
    @RequestMapping(value = "/updateRentCustomer" , method = RequestMethod.POST)
    public ApiResult<RentPactResult> updateRentCustomer(HttpServletRequest request,@RequestBody RentCustomerParam param){
          ApiResult<RentPactResult> result = new ApiResult<>();
          try {
              if(null!=param){
                  String pin = (String) request.getAttribute(LoginConsts.PIN);
                  String ip = super.getRemoteIPs(request);
                  param.setCreateName(pin);
                  param.setIp(ip);
              }
              result = rentBaseService.updateRentCustomer(param);
          } catch (Exception e) {
              LOG.error(e.getMessage(), e);
          }
          return result;
    }
    /**
    * @Title: deleteRentPact
    * @Description: TODO( 删除托出合同)
    * @author GuoXiaoPeng
    * @param request
    * @param param 托出合同编码
    * @return 返回结果
    * @throws 异常
     */
    @SuppressWarnings("unchecked")
    @PermissionsCode(code = "rest_rentbase_remove")
    @RequestMapping(value = "/deleteRentPact" , method = RequestMethod.POST)
    public ApiResult<RentPactResult> deleteRentPact(HttpServletRequest request,@RequestBody RentBaseParam param){
          ApiResult<RentPactResult> result = new ApiResult<>();
          try {
              if(null!=param){
                  String pin = (String) request.getAttribute(LoginConsts.PIN);
                  String ip = super.getRemoteIPs(request);
                  param.setCreateName(pin);
                  param.setIp(ip);
              }
              result = rentBaseService.deleteRentBase(param);
          } catch (Exception e) {
              LOG.error(e.getMessage(), e);
          }
          return result;
    }
    /**
    * @Title: getPropertyAddress
    * @Description: TODO( 根据托出合同编码查询物业地址)
    * @author GuoXiaoPeng
    * @param rentPactCode  托出合同编码
    * @return 返回结果
    * @throws 异常
     */
    @RequestMapping(value = "/getPropertyAddress/{rentPactCode}" , method = RequestMethod.GET)
    public  ApiResult<RentBaseResult> getPropertyAddress(@PathVariable String rentPactCode){
        ApiResult<RentBaseResult> result = new ApiResult<>();
        try {
            result = rentBaseService.getPropertyAddress(rentPactCode);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
    /**
    * @Title: getPayeeByRentPactCode
    * @Description: TODO( 根据托出合同编码查询租客姓名，收款账户，收款银行，开户行，电话 )
    * @author GuoXiaoPeng
    * @param rentPactCode 托出合同编码
    * @return 租客收款人信息
    * @throws 异常
     */
    @RequestMapping(value = "/getPayeeByRentPactCode/{rentPactCode}" , method = RequestMethod.GET)
    public  ApiResult<FinanceReceiptPayResult> getPayeeByRentPactCode(@PathVariable String rentPactCode){
        ApiResult<FinanceReceiptPayResult> result = new ApiResult<>();
        try {
            result = rentBaseService.getPayeeByRentPactCode(rentPactCode);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
    /**
    * @Title: updateRentBase
    * @Description: TODO( 修改托出合同)
    * @author GuoXiaoPeng
    * @param request
    * @param param 托出合同信息
    * @return 返回结果
    * @throws 异常
     */
    @PermissionsCode(code = "rest_rentbase_update")
    @RequestMapping(value = "/updateRentBase", method = RequestMethod.POST)
    public ApiResult<Boolean> updateRentBase(HttpServletRequest request, @RequestBody RentPactParam param){
        ApiResult<Boolean> result = new ApiResult<>(); 
        try {
            if(null != param){
              String pin = (String) request.getAttribute(LoginConsts.PIN);
              String ip = super.getRemoteIPs(request);
              param.setModifiedName(pin);
              param.setIp(ip);
            }
            result = rentBaseService.updateRentBase(param);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
    /**
    * @Title: setDelayDate
    * @Description: TODO( 托出合同设置延期时间 )
    * @author GuoXiaoPeng
    * @param request
    * @param param 托出合同信息
    * @return 返回结果
    * @throws 异常
     */
    @PermissionsCode(code = "rest_rentbase_set_delay_date")
    @RequestMapping(value = "/setDelayDate", method = RequestMethod.POST)
    public ApiResult<Boolean> setDelayDate(HttpServletRequest request, @RequestBody RentBaseParam param){
        ApiResult<Boolean> result = new ApiResult<>(); 
        try {
            if(null != param){
                String pin = (String) request.getAttribute(LoginConsts.PIN);
                String ip = super.getRemoteIPs(request);
                param.setModifiedName(pin);
                param.setIp(ip);
            }
            result = rentBaseService.setDelayDate(param);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
    public RentBaseService getRentBaseService() {
        return rentBaseService;
    }
    public void setRentBaseService(RentBaseService rentBaseService) {
        this.rentBaseService = rentBaseService;
    }
}
