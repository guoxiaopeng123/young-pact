package com.young.pact.service.listener;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.rocketmq.client.producer.SendCallback;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.young.common.util.DateUtil;
/**
 * 
* @ClassName: SendListenter
* @Description: mq消息生产监听
* @author LiuYuchi
* @date 2018年3月16日 上午9:52:52
*
 */
public class SendListenter implements SendCallback{
    /***********************声明区*********************************/
    private static final Log LOG = LogFactory.getLog(SendListenter.class);
    /***********************方法区*********************************/
    /**
     * @描述 : mq消息生产异常
     * @创建者 : HeZeMin
     * @创建时间 : 2017-6-15 下午3:39:05
     */
    public void onException(Throwable throwable) {
        LOG.info(throwable + ":" + DateUtil.dateToString(new Date()));
    }
    /**
     * @描述 : mq消息生产成功
     * @创建者 : HeZeMin
     * @创建时间 : 2017-6-15 下午3:39:05
     */
    public void onSuccess(SendResult sendResult) {
        LOG.info(sendResult + ":" + DateUtil.dateToString(new Date()));
    }
}