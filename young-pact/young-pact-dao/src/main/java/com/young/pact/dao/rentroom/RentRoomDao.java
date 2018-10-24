package com.young.pact.dao.rentroom;

import com.young.common.exception.DaoException;
import com.young.pact.domain.rentroom.RentRoomDO;
import com.young.pact.domain.rentroom.RentRoomVO;

/**
 * @描述 : 托出房间信息
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月2日 下午9:55:15
 */
public interface RentRoomDao {
    
    /**
    * @Title: saveRentRoom
    * @Description: TODO( 保存托出房间 )
    * @author GuoXiaoPeng
    * @param rentRoomDO 托出房间信息
    * @return 记录标识
    * @throws
     */
    Long saveRentRoom(RentRoomDO rentRoomDO) throws DaoException;
    /**
     * @Title: getRommByRentCode
     * @Description: TODO( 根据托出合同编码查询房间信息)
     * @author GuoXiaoPeng 
     * @param pactCode 托出合同编码
     * @return 房间信息
     * @throws
      */
    RentRoomVO getRommByRentCode(String pactCode)throws DaoException;
    /**
    * @Title: removeRentRoom
    * @Description: TODO( 根据托出合同编码删除托出房间 )
    * @author GuoXiaoPeng
    * @param rentPactCode 托出合同编码
    * @throws DaoException 异常
    * @throws 异常
     */
    void removeRentRoom(String rentPactCode)throws DaoException;
   /**
   * @Title: updateRoomByRoomCode
   * @Description: TODO( 根据房间编码修改房间信息 )
   * @author GuoXiaoPeng
   * @param rentRoomDO 房间编码
   * @return 影响的记录数
   * @throws DaoException 异常
   * @throws 异常
    */
    Integer updateRoomByRoomCode(RentRoomDO rentRoomDO)throws DaoException;

    /**
     * 
    * @Title: getByDissolutionCode
    * @Description: 根据解约协议编码获取房间信息
    * @author SunYiXuan
    * @param dissolutionCode 解约协议编码
    * @return 房间信息
    * @throws DaoException
    * @throws
     */
    RentRoomVO getByDissolutionCode(String dissolutionCode)throws DaoException;
}
