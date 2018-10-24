package com.young.pact.rpc.serialize;
/**
 * @描述 : 序列化
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月20日 下午8:49:30
 */
public interface SerializeRpc {

    /**
    * @Title: queryCodeBySerializeType
    * @Description: TODO( 获取序列化 )
    * @author GuoXiaoPeng
    * @param serviceType 类型
    * @return 序列化编码
    * @throws 异常
     */
    String queryCodeBySerializeType(String serviceType);
}
