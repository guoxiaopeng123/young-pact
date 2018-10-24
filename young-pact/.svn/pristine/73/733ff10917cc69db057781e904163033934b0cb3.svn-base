package com.young.pact.api.service.rest.rentroom;

import com.young.common.domain.ApiResult;
import com.young.pact.api.domain.param.rest.rentroom.RoomParam;
import com.young.pact.api.domain.result.rest.rentroom.RoomResult;

/**
 * @描述 : 托出房间
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月1日 下午4:39:00
 */
public interface RentRoomService {
    /**
     * 
    * @Title: saveRoom
    * @Description: TODO( redis 保存房间+客源)
    * @author GuoXiaoPeng
    * @return redis 缓存的key
    * @throws
     */
    ApiResult<String> saveRoomRedis(RoomParam roomParam);
    /**
     * 
    * @Title: getRoom
    * @Description: TODO(查询已选择房间 )
    * @author GuoXiaoPeng
    * @return 房间信息
    * @throws
     */
    ApiResult<RoomResult> getRoomRedis(String key);
}
