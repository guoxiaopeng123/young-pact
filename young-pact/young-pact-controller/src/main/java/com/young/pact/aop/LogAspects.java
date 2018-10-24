package com.young.pact.aop;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.tools.common.util.json.JsonUtil;

/**
 * @描述 : 切面类
 * @创建者 : guoxiaopeng
 * @创建时间 : 2018年10月23日 下午3:23:41
 */
@EnableAspectJAutoProxy
@Aspect
public class LogAspects {

    
    @Pointcut("execution(* com.young.pact.controller..*.*(..))")
    public void pointCut() {

    }
    /**
     * 
    * @Title: logStart
    * @Description: TODO( 前置通知 )
    * @author GuoXiaoPeng
    * @param joinPoint 切入点
    * @throws
     */
    @SuppressWarnings("unused")
    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint){
        Map<String,List<Map<String, Object>>> dataMap = new HashMap<>();
        Object[] args = joinPoint.getArgs();
        String methodName = joinPoint.getSignature().getName();
        List<Object> asList = Arrays.asList(args);
        for (int i = 0; i < asList.size(); i++) {
           Object obj = asList.get(i);
           List<Map<String, Object>> dataList = processData(obj);
           dataMap.put(obj.getClass().getSimpleName(), dataList);
        }
        Iterator<Entry<String, List<Map<String, Object>>>> iterator = dataMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, List<Map<String, Object>>> next = iterator.next();
            String key = next.getKey();
            List<Map<String, Object>> value = next.getValue();
            if("RequestFacade".equals(key)){
                iterator.remove();
            }
        }
        String json = JsonUtil.toJson(dataMap);
        System.out.println(json);
    }
    @After("com.young.pact.aop.LogAspects.pointCut()")
    public void logEnd(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        System.out.println(methodName + "结束.........@After");
    }
    @AfterReturning(value = "pointCut()",returning = "result")
    public void logReturn(JoinPoint joinPoint,Object result){
        Map<String,List<Map<String, Object>>> dataMap = new HashMap<>();
        String methodName = joinPoint.getSignature().getName();
        List<Map<String, Object>> processData = processData(result);
        dataMap.put("data", processData);
        String json = JsonUtil.toJson(dataMap);
        System.out.println(methodName + "结束.........@AfterReturning");
        System.out.println(json);
    }
    @AfterThrowing(value = "pointCut()",throwing = "exception")
    public Map<String,String> logException(JoinPoint joinPoint,Exception exception){
        String methodName = joinPoint.getSignature().getName();
        String exceptionAllinformation = getExceptionAllinformation(exception);
        String errorMessage = exception.getMessage();
        Map<String,String> map = new HashMap<>();
        map.put("errorMessage", errorMessage);
        map.put("exceptionAllinformation", exceptionAllinformation);
        System.out.println(methodName + "异常....异常信息：" + JsonUtil.toJson(map));
        return map;
    }
    /**
     * 
    * @Title: processData
    * @Description: TODO( 请求参数对象处理 )
    * @author GuoXiaoPeng
    * @param obj 请求参数对象
    * @throws
     */
    private List<Map<String, Object>> processData(Object obj){
        Field[] fields = obj.getClass().getDeclaredFields();
        List<Map<String, Object>> list = new ArrayList<>();
        for (int j = 0, len = fields.length; j < len; j++) {
            // 对于每个属性，获取属性名
            String varName = fields[j].getName();
            Map<String, Object> map = new HashMap<>();
            try {
                // 获取原来的访问控制权限
                boolean accessFlag = fields[j].isAccessible();
                // 修改访问控制权限
                fields[j].setAccessible(true);
                // 获取在对象f中属性fields[i]对应的对象中的变量
                Object value;
                try {
                    value = fields[j].get(obj);
                    map.put(varName, value);
                    list.add(map);
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                // 恢复访问控制权限
                fields[j].setAccessible(accessFlag);
            } catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            }
        }
        return list;
    }
    /**
     * 
    * @Title: getExceptionAllinformation
    * @Description: TODO( 获取异常详细信息 )
    * @author GuoXiaoPeng
    * @param ex 异常
    * @return 异常详细信息
    * @throws
     */
    public static String getExceptionAllinformation(Exception ex){
        String sOut = "";
        StackTraceElement[] trace = ex.getStackTrace();
        for (StackTraceElement s : trace) {
            sOut += "\tat " + s + "\r\n";
        }
        return sOut;
    }
}
