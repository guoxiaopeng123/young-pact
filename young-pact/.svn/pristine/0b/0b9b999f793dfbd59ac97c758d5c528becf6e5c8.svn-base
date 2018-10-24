package com.young.pact.rpc.personal;

import java.util.List;

import com.young.common.domain.ApiResult;
import com.young.sso.api.domain.result.rpc.personal.PersonalResult;

/**
 * @描述 : 用户
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月20日 下午8:39:36
 */
public interface PersonalRpc {
    /**
     * @Title: getPersonalResultBypin
     * @Description: 通过pin查询管家信息
     * @author guoxiaopneg
     * @param pin 用户PIN
     * @return 用户部门信息
     * @throws 异常
     */
    ApiResult<PersonalResult> getPersonalResultBypin(String pin);
    /**
     * 
    * @Title: getPersonalByTel
    * @Description: 根据电话查询用户信息
    * @author guoxiaopneg
    * @param tel 电话
    * @return 用户部门信息
    * @throws 异常
     */
    PersonalResult getPersonalByTel(String tel);
    /**
    * @Title: listLowerByPin
    * @Description: TODO( 根据pin获取直属下级pin集合(包括本人) )
    * @author GuoXiaoPeng
    * @param pin 登录人PIN
    * @return 直属下级pin集合(包括本人)
    * @throws 异常
     */
    List<String> listLowerByPin(String pin);
    /**
    * @Title: getSuperiorByPin
    * @Description: TODO( 根据PIN查询上级人员信息 )
    * @author GuoXiaoPeng
    * @param pin 
    * @return 上级人员信息
    * @throws 异常
     */
    PersonalResult getSuperiorByPin(String pin);
    /**
     * 
    * @Title: listByPosition
    * @Description: TODO( 根据职位查询管家 )
    * @author GuoXiaoPeng
    * @param position 职位
    * @return 管家集合
    * @throws 异常
     */
    List<PersonalResult> listByPosition(String position);
}
