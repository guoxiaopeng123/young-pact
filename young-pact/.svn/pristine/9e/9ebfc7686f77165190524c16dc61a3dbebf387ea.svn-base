package com.young.pact.rpc.operatelog.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.tools.common.rocketmq.client.RocketmqClient;
import com.tools.common.util.json.JsonUtil;
import com.young.common.dict.CommonEnum;
import com.young.common.domain.ApiResult;
import com.young.follow.api.domain.param.rpc.OperateLogParam;
import com.young.follow.api.domain.result.rpc.OperateLogResult;
import com.young.follow.api.service.rpc.OperateLogRpcService;
import com.young.pact.common.constant.ErrorPactConsts;
import com.young.pact.common.constant.NormalConstant;
import com.young.pact.common.exception.PactManagerExcepion;
import com.young.pact.rpc.operatelog.OperateLogRpc;
import com.young.sso.api.domain.result.rpc.personal.PersonalResult;
import com.young.sso.api.service.rpc.personal.PersonalRpcService;

/**
 * @描述 : 操作日志rpc
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月21日 上午11:47:22
 */
@Component("operateLogRpc")
public class OperateLogRpcImpl implements OperateLogRpc {
    private static final Log LOG = LogFactory.getLog(OperateLogRpcImpl.class);
    private RocketmqClient rocketmqClient;
    private PersonalRpcService personalRpcService;
    private OperateLogRpcService operateLogRpcService;
    @Override
    public void saveOperateLog(Map<String, Object> map) {
        try {
            ApiResult<PersonalResult> result = personalRpcService.getPersonInterface((String)map.get(NormalConstant.OPERATEPIN));
            PersonalResult personalResult = result.getData();
            map.put(NormalConstant.OPERATENAME, personalResult.getEmployeeName());
            map.put(NormalConstant.OPERATEDEPT, personalResult.getDeptName());
            map.put(NormalConstant.OPERATETIME, new Date());
            String json = JsonUtil.toJson(map);
            rocketmqClient.sendMessage(NormalConstant.TOPIC_OPERATE, NormalConstant.TAG_OPERATE_SAVE, NormalConstant.KEY_OPERATE_PACT, json);
        } catch (Exception e) {
            LOG.error(ErrorPactConsts.SAVE_OPERATELOG_ERROR,e);
            throw new PactManagerExcepion(ErrorPactConsts.SAVE_OPERATELOG_ERROR,e);
        }
    }
    
    @Override
    public List<OperateLogResult> listOperateRecord(OperateLogParam operateLogParam) {
        List<OperateLogResult> list = new ArrayList<>(); 
        try {
            ApiResult<List<OperateLogResult>> result = operateLogRpcService.listOperateRecord(operateLogParam);
            if(CommonEnum.REQUEST_SUCCESS.getCode().equals(result.getCode())){
                list = result.getData();
            }
        } catch (Exception e) {
            LOG.error(ErrorPactConsts.LIST_OPERATELOG_ERROR,e);
            throw new PactManagerExcepion(ErrorPactConsts.LIST_OPERATELOG_ERROR,e);
        }
        return list;
    }
    
    public RocketmqClient getRocketmqClient() {
        return rocketmqClient;
    }

    public void setRocketmqClient(RocketmqClient rocketmqClient) {
        this.rocketmqClient = rocketmqClient;
    }

    public PersonalRpcService getPersonalRpcService() {
        return personalRpcService;
    }

    public void setPersonalRpcService(PersonalRpcService personalRpcService) {
        this.personalRpcService = personalRpcService;
    }

    public OperateLogRpcService getOperateLogRpcService() {
        return operateLogRpcService;
    }

    public void setOperateLogRpcService(OperateLogRpcService operateLogRpcService) {
        this.operateLogRpcService = operateLogRpcService;
    }

}
