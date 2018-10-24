package com.young.pact.rpc.operatelog;

import java.util.List;
import java.util.Map;

import com.young.follow.api.domain.param.rpc.OperateLogParam;
import com.young.follow.api.domain.result.rpc.OperateLogResult;


/**
 * @描述 : 日志
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月21日 上午10:02:17
 */
public interface OperateLogRpc {

    /**
    * @Title: saveOperateLog
    * @Description: TODO( 保存操作日志 )
    * @author GuoXiaoPeng
    * @param map 操作日志map 
     */
    public void saveOperateLog( Map<String, Object> map);
    /**
    * @Title: listOperateRecord
    * @Description: TODO( 获取操作日志集合 )
    * @author GuoXiaoPeng
    * @param operateLogParam  操作业务编码+操作业务类型
    * @return 日志信息
    * @throws 异常
     */
    public List<OperateLogResult> listOperateRecord(OperateLogParam operateLogParam);
}
