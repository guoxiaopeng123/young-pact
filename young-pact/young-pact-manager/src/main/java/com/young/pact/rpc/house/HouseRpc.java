package com.young.pact.rpc.house;

import java.util.List;

import com.young.house.api.domain.result.rpc.house.GetHouseRpcResult;

/**
 * @描述 : 房源rpc
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月21日 下午8:11:23
 */
public interface HouseRpc {
    /**
    * @Title: getHouseByCodeList
    * @Description: TODO( 根据房源编码集合获取房源信息集合 )
    * @author GuoXiaoPeng
    * @param houseCodes 房源编码集合字符串（逗号分隔）
    * @return 房源信息集合
    * @throws 异常
     */
    List<GetHouseRpcResult> getHouseByCodeList(String houseCodes); 
    
}
