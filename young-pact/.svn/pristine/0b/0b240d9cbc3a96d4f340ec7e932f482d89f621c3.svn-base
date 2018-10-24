package com.young.pact.interceptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.tools.common.util.json.JsonUtil;
import com.young.common.annotation.PermissionsCode;
import com.young.common.dict.CommonEnum;
import com.young.common.domain.ApiResult;
import com.young.pact.commom.CommonConsts;
import com.young.pact.commom.LoginConsts;
import com.young.system.api.domain.result.rpc.actioin.ActionPinResult;
import com.young.system.api.service.rpc.action.ActionRpcService;
/**
 * 
* @ClassName: LoginInterceptor
* @Description: TODO 权限拦截器
* @author HeZeMin
* @date 2018年1月26日 上午10:19:35
*
 */
public class PermissionsInterceptor extends HandlerInterceptorAdapter {
    /****************声明区*******************/
    private static final Log LOG = LogFactory.getLog("----------- PermissionsInterceptor -----------");
    /** 暂无权限页 **/
    private String errorPage;
    /** 管理员pin **/
    private String adminPin;
    /** 查询用户权限接口 **/
    private ActionRpcService actionRpcService;
    /****************方法区*******************/
    /*
     * (非 Javadoc)
    * <p>Title: preHandle</p>
    * <p>Description: 预处理回调方法，实现处理器的预处理</p>
    * @param handler
    * @return true表示继续流程,false表示流程中断
    * @throws Exception
    * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String pin = (String) request.getAttribute(LoginConsts.PIN);
        LOG.info("登录人pin----------------" + pin);
        // 如果是管理员账号直接放行
        if(null != pin && pin.equals(adminPin)){
            request.setAttribute(LoginConsts.SCOPE, CommonConsts.SCOPE_ALL);
            return true;
        }
        if(handler instanceof HandlerMethod){
            HandlerMethod method = (HandlerMethod)handler;
            PermissionsCode annotation = method.getMethod().getAnnotation(PermissionsCode.class);
            if(null != annotation && StringUtils.isNotBlank(annotation.code())){
                // 根据pin获取该用户的所有权限码
                ApiResult<List<ActionPinResult>> apiResult = actionRpcService.listActionByPin(pin);
                if(apiResult.getCode().equals(CommonEnum.REQUEST_SUCCESS.getCode())){
                    String code = annotation.code();
                    List<ActionPinResult> actionList = apiResult.getData();
                    if(null != actionList && actionList.size() > 0){
                        for(ActionPinResult action : actionList){
                            if(code.equals(action.getActionCode())){
                                // 如果有权限，直接放行
                                request.setAttribute(LoginConsts.SCOPE, action.getScope());
                                return true;
                            }
                        }
                    }
                }
                // 没有权限，驳回请求
                this.redirectAuth(request, response);
                return false;
            }else{// 该请求没有权限码，直接放行
                return true;
            }
        }else{// 当请求静态文件时,该类型不做任何处理，直接放行
            return true;
        }
    }
    /**
     * 
    * @Title: redirectAuth
    * @Description: TODO ajax请求如没权限则返回No Auth. ajax json 请求返回{"800","No Auth"}.普通请求返回暂无权限页
    * @author HeZeMin
     */
    private void redirectAuth(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put(CommonEnum.NO_AUTH.getCode(), CommonEnum.NO_AUTH.getMessage());
        map.put(CommonConsts.DATA, errorPage);
        response.getWriter().write(JsonUtil.toJson(map));
    }
    /****************get/set*******************/
    public String getErrorPage() {
        return errorPage;
    }
    public void setErrorPage(String errorPage) {
        this.errorPage = errorPage;
    }
    public String getAdminPin() {
        return adminPin;
    }
    public void setAdminPin(String adminPin) {
        this.adminPin = adminPin;
    }
    public ActionRpcService getActionRpcService() {
        return actionRpcService;
    }
    public void setActionRpcService(ActionRpcService actionRpcService) {
        this.actionRpcService = actionRpcService;
    }
}
