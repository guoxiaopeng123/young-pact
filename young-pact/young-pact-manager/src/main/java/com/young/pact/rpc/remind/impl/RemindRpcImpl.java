package com.young.pact.rpc.remind.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.tools.common.rocketmq.client.RocketmqClient;
import com.tools.common.util.json.JsonUtil;
import com.young.pact.common.constant.ErrorPactConsts;
import com.young.pact.common.constant.NormalConstant;
import com.young.pact.common.constant.StringConsts;
import com.young.pact.common.exception.PactManagerExcepion;
import com.young.pact.domain.remind.RemindDO;
import com.young.pact.rpc.remind.RemindRpc;

/**
 * @描述 : 提醒消息rpc
 * @创建者 : GuoXiaoPeng
 * @创建时间 : 2018年9月3日 上午10:37:13
 */
@Component("remindRpc")
public class RemindRpcImpl implements RemindRpc{

    private static final Log LOG = LogFactory.getLog(RemindRpcImpl.class);
    private RocketmqClient rocketmqClient;
    private Map<String,String> remindMap;
    @Override
    public void saveRemind(Map<String, Object> map) {
        try {
            map.put(NormalConstant.REMIND_TIME, new Date());
            map.put(NormalConstant.REMIND_ISREAD, 0);
            String json = JsonUtil.toJson(map);
            rocketmqClient.sendMessage(NormalConstant.TOPIC_REMIND, "", "", json);
        } catch (Exception e) {
            LOG.error(ErrorPactConsts.SAVE_REMIND_MESSAGE_ERROR);
            throw new PactManagerExcepion(ErrorPactConsts.SAVE_REMIND_MESSAGE_ERROR);
        }
    }
    @Override
    public boolean saveRemind(RemindDO remindDO) {
        boolean flag = true;
        try {
            String content = remindMap.get(remindDO.getKey());
            for (int i = 0; i < remindDO.getVariableList().size(); i++) {
                content = content.replace(StringConsts.VARIABLE + i, remindDO.getVariableList().get(i));
            }
            Map<String, Object> map = new HashMap<>();
            map.put(NormalConstant.REMIND_TITLE, remindDO.getTitle());
            map.put(NormalConstant.REMIND_PIN, remindDO.getPin());
            map.put(NormalConstant.REMIND_SERVICETYPE, remindDO.getServicetype());
            map.put(NormalConstant.REMIND_SERVICECODE, remindDO.getServicecode());
            map.put(NormalConstant.REMIND_REMINDCONTENT,content);
            saveRemind(map);
            return flag;
        } catch (Exception e) {
            LOG.error(ErrorPactConsts.SAVE_REMIND_MESSAGE_ERROR);
            throw new PactManagerExcepion(ErrorPactConsts.SAVE_REMIND_MESSAGE_ERROR);
        }
    }
    public RocketmqClient getRocketmqClient() {
        return rocketmqClient;
    }

    public void setRocketmqClient(RocketmqClient rocketmqClient) {
        this.rocketmqClient = rocketmqClient;
    }

    public Map<String, String> getRemindMap() {
        return remindMap;
    }

    public void setRemindMap(Map<String, String> remindMap) {
        this.remindMap = remindMap;
    }

   
}
