package com.young.pact.manager.rentroom;

import com.young.pact.domain.rentroom.RentRoomDO;
import com.young.pact.domain.rentroom.RentRoomVO;

/**
 * @描述 : 托出房间
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月1日 下午4:43:49
 */
public interface RentRoomManager {

    /**
     * 
    * @Title: saveRoom
    * @Description: TODO(保存房间+客源 )
    * @author GuoXiaoPeng
    * @param rentRoomDO 房间信息
    * @return 成功 true 失败false
    * @throws
    */
    void saveRoomRedis(RentRoomDO rentRoomDO);
    /**
    * @Title: getRoom
    * @Description: TODO( 根据缓存的key查询已选择的房间信息)
    * @author GuoXiaoPeng
    * @param key 缓存的key
    * @return 房间编码
    * @throws
    */
    RentRoomVO getRoomRedis(String key);
    /**
    * @Title: saveRentRoomVO
    * @Description: TODO(保存托出房间 )
    * @author GuoXiaoPeng
    * @param rentRoomDO 托出房间信息
    * @return 记录标识
    * @throws
    */
    Long saveRentRoomVO(RentRoomDO rentRoomDO);
    /**
    * @Title: getRommByRentCode
    * @Description: TODO( 根据托出合同编码查询房间信息)
    * @author GuoXiaoPeng 
    * @param pactCode 托出合同编码
    * @return 房间信息
    * @throws
     */
    RentRoomVO getRommByRentCode(String pactCode);
}
