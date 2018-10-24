package com.young.pact.rpc.room;

import com.young.product.api.domain.param.rpc.room.RoomParam;
import com.young.product.api.domain.result.rpc.room.RoomResult;

/**
 * @描述 : 房间RPC
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月21日 下午6:19:21
 */
public interface RoomRpc {
    /**
    * @Title: countRoomStateByCode
    * @Description: TODO( 根据编码和状态获取房间数量 )
    * @author GuoXiaoPeng
    * @param roomParam 编码和状态
    * @return 房间数量
    * @throws
     */
    Integer countRoomStateByCode(RoomParam roomParam);
    /**
     * @Title: getRoomBase
     * @Description: TODO( 根据房间编码获取房间基本信息 )
     * @author GuoXiaoPeng
     * @param roomCode 房间编码
     * @return 房间基本信息
     * @throws
      */
    RoomResult getRoomBase(String roomCode);
    /**
     * 
    * @Title: getRoomByCode
    * @Description: TODO( 根据房间编码查询房间信息，包括房屋)
    * @author GuoXiaoPeng
    * @param roomCode 房间编码
    * @return 房间信息，包括房屋
    * @throws 异常
     */
    RoomResult getRoomByCode(String roomCode);
}
