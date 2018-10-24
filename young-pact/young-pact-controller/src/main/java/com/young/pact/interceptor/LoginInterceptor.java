package com.young.pact.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.tools.common.springmvc.cookie.CookieUtil;
import com.tools.common.util.json.JsonUtil;
import com.young.common.dict.CommonEnum;
import com.young.common.domain.ApiResult;
import com.young.pact.commom.CommonConsts;
import com.young.pact.commom.LoginConsts;
import com.young.sso.api.domain.param.rpc.login.CheckLoginParam;
import com.young.sso.api.domain.result.rpc.login.CheckLoginResult;
import com.young.sso.api.service.rpc.login.LoginRpcService;

/**
 * 
* @ClassName: LoginInterceptor
* @Description: TODO 登录拦截器
* @author HeZeMin
* @date 2018年1月26日 上午10:19:35
*
 */
public class LoginInterceptor extends HandlerInterceptorAdapter{

    /****************声明区*******************/
    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog("----------- LoginInterceptor -----------");
    /** cookie key name **/
    private String cookieKeyName;
    /** 操作cookie工具类 **/
    private CookieUtil cookieUtil;
    /** 登录页链接 **/
    private String loginUrl;
    /** url回跳携带参数 **/
    private String returnUrlParameter;
    /** 登录验证接口 **/
    private LoginRpcService loginRpcService;
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
        CheckLoginParam param = new CheckLoginParam();
        String cookieValue = cookieUtil.getCookieValue(request, cookieKeyName);
        //String cookieValue = "%7B%22pin%22%3A%22PIN10130%22%2C%22encryptStr%22%3A%22F15F57AAA51CF4823A3DB435FEB2FB86FF73B4B9DC749B8F%22%2C%22encryptMD5Str%22%3A%22b447b9259e482b4734bf2beeced4af3b%22%2C%22ip%22%3A%228f0bb741ccddf30c8a902c2cd747485c%22%2C%22brower%22%3A%2225b9af59403a1a1bbbc76ffebdf6c67e%22%7D";
        param.setCookieValue(cookieValue);
        ApiResult<CheckLoginResult> apiResult = loginRpcService.checkLogin(param);
        
        // cookie验证成功
        if(CommonEnum.REQUEST_SUCCESS.getCode().equals(apiResult.getCode())){
            CheckLoginResult result = apiResult.getData();
            // 离职、禁用和删除 账号不能登录
            if(null != result && 0 == result.getIsDsable() && 0 == result.getIsDimission()){
//                cookieUtil.createdCookie(response, cookieKeyName, cookieValue, -1);
                request.setAttribute(LoginConsts.PIN, result.getPin());
                request.setAttribute(LoginConsts.EMPLOYEE_NO, result.getEmployeeNo());
                request.setAttribute(LoginConsts.EMPLOYEE_NAME, result.getEmployeeName());
                request.setAttribute(LoginConsts.EMPLOYEE_TEL, result.getEmployeeTel());
                request.setAttribute(LoginConsts.POSITION, result.getPosition());
                request.setAttribute(LoginConsts.DEPT_CODE, result.getDeptCode());
                request.setAttribute(LoginConsts.DEPT_NAME, result.getDeptName());
                return true;
            }else{// 用户信息为null
                this.redirectLogin(request, response);
                return false;
            }
        }else{// cookie验证失败
            this.redirectLogin(request, response);
            return false;
        }
    }
    /**
     * 
    * @Title: redirectLogin
    * @Description: TODO ajax请求如未登录则返回NotLogin. ajax json 请求返回{"error":"NotLogin"}.
     *     普通请求返回登录页面并带参数ReturnUrl 用于登录成功返回路径
    * @author HeZeMin
     */
    private void redirectLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put(CommonEnum.NO_LOGIN.getCode(), CommonEnum.NO_LOGIN.getMessage());
        map.put(CommonConsts.DATA, loginUrl);
        response.getWriter().write(JsonUtil.toJson(map));
    }
    
    /****************get/set*******************/
    public String getLoginUrl() {
        return loginUrl;
    }
    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }
    public String getCookieKeyName() {
        return cookieKeyName;
    }
    public void setCookieKeyName(String cookieKeyName) {
        this.cookieKeyName = cookieKeyName;
    }
    public CookieUtil getCookieUtil() {
        return cookieUtil;
    }
    public void setCookieUtil(CookieUtil cookieUtil) {
        this.cookieUtil = cookieUtil;
    }
    public String getReturnUrlParameter() {
        return returnUrlParameter;
    }
    public void setReturnUrlParameter(String returnUrlParameter) {
        this.returnUrlParameter = returnUrlParameter;
    }
    public LoginRpcService getLoginRpcService() {
        return loginRpcService;
    }
    public void setLoginRpcService(LoginRpcService loginRpcService) {
        this.loginRpcService = loginRpcService;
    }

}
