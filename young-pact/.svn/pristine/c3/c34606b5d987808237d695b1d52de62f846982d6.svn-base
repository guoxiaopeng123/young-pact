package com.young.pact.rpc.personal.impl;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.tools.common.util.json.JsonUtil;
import com.young.common.dict.CommonEnum;
import com.young.common.domain.ApiResult;
import com.young.pact.common.constant.ErrorPactConsts;
import com.young.pact.common.exception.PactManagerExcepion;
import com.young.pact.rpc.personal.PersonalRpc;
import com.young.sso.api.domain.result.rpc.personal.PersonalResult;
import com.young.sso.api.service.rpc.personal.PersonalRpcService;
/**
 * @描述 : 用户
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月20日 下午8:39:36
 */
@Component("personalRpc")
public class PersonalRpcImpl implements PersonalRpc {
    private static final Log LOG = LogFactory.getLog(PersonalRpcImpl.class);
    private PersonalRpcService personalRpcService;

    @Override
    public ApiResult<PersonalResult> getPersonalResultBypin(String pin) {
        ApiResult<PersonalResult> result = personalRpcService.getPersonInterface(pin);
        if (CommonEnum.REQUEST_SUCCESS.getCode().equals(result.getCode())) {
            return result;
        }else{
            LOG.error("操作参数：" + pin);
            LOG.error("操作结果：" + JsonUtil.toJson(result));
            LOG.error(ErrorPactConsts.QUERY_PERSONAL_ERROR);
            throw new PactManagerExcepion(ErrorPactConsts.QUERY_PERSONAL_ERROR);
        }
    }
    
    @Override
    public PersonalResult getPersonalByTel(String tel) {
        PersonalResult personalResult=new PersonalResult();
        ApiResult<PersonalResult> apiResult = personalRpcService.getPersonalByTel(tel);
        if (CommonEnum.REQUEST_SUCCESS.getCode().equals(apiResult.getCode())) {
            personalResult=apiResult.getData();
            return personalResult;
        }else{
            LOG.error("操作参数：" + tel);
            LOG.error("操作结果：" + JsonUtil.toJson(apiResult));
            LOG.error(ErrorPactConsts.QUERY_PERSONAL_TEL_ERROR);
            throw new PactManagerExcepion(ErrorPactConsts.QUERY_PERSONAL_TEL_ERROR);
        }
    }
    
    @Override
    public List<String> listLowerByPin(String pin) {
        ApiResult<List<String>> result = personalRpcService.listLowerByPin(pin);
        List<String> list;
        if(CommonEnum.REQUEST_SUCCESS.getCode().equals(result.getCode())){
            list = result.getData();
        }else{
            LOG.error("操作参数：" + pin);
            LOG.error("操作结果：" + JsonUtil.toJson(result));
            LOG.error(ErrorPactConsts.LIST_LOWER_BYPIN_ERROR);
            throw new PactManagerExcepion(ErrorPactConsts.LIST_LOWER_BYPIN_ERROR);
        }
        return list;
    }
    
    @Override
    public PersonalResult getSuperiorByPin(String pin) {
        ApiResult<PersonalResult> result = personalRpcService.getSuperiorByPin(pin);
        if(CommonEnum.REQUEST_SUCCESS.getCode().equals(result.getCode())){
             PersonalResult data = result.getData();
             return data;
        }else{
            LOG.error("操作参数：" + pin);
            LOG.error("操作结果：" + JsonUtil.toJson(result));
            LOG.error(ErrorPactConsts.GET_SUPERIOR_BYPIN_ERROR);
            throw new PactManagerExcepion(ErrorPactConsts.GET_SUPERIOR_BYPIN_ERROR);
        }
    }
    
    @Override
    public List<PersonalResult> listByPosition(String position) {
        ApiResult<List<PersonalResult>> result = personalRpcService.getByPosition(position);
        if(CommonEnum.REQUEST_SUCCESS.getCode().equals(result.getCode())){
             List<PersonalResult> data = result.getData();
             return data;
        }else{
            LOG.error("操作参数：" + position);
            LOG.error("操作结果：" + JsonUtil.toJson(result));
            LOG.error(ErrorPactConsts.LIST_BY_POSITION_ERROR);
            throw new PactManagerExcepion(ErrorPactConsts.LIST_BY_POSITION_ERROR);
        }
    }
    public PersonalRpcService getPersonalRpcService() {
        return personalRpcService;
    }

    public void setPersonalRpcService(PersonalRpcService personalRpcService) {
        this.personalRpcService = personalRpcService;
    }




}
