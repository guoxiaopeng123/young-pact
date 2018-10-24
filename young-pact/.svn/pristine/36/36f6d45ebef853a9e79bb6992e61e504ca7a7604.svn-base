package com.young.pact.dao.rentroom.impl;

import org.springframework.stereotype.Repository;

import com.tools.common.ibatis.base.BaseDao;
import com.young.common.exception.DaoException;
import com.young.pact.dao.rentroom.RentRoomDao;
import com.young.pact.domain.rentroom.RentRoomDO;
import com.young.pact.domain.rentroom.RentRoomVO;

/**
 * @描述 : 托出房间信息
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月2日 下午9:56:07
 */
@Repository("rentRoomDao")
public class RentRoomDaoImpl extends BaseDao implements RentRoomDao{

    @SuppressWarnings("deprecation")
    @Override
    public Long saveRentRoom(RentRoomDO rentRoomDO) throws DaoException {
        try {
            return (Long) this.insert("RentRoom.saveRentRoom", rentRoomDO);
        } catch (Exception e) {
            // TODO: handle exception
            throw new DaoException(e);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public RentRoomVO getRommByRentCode(String rentPactCode) throws DaoException {
        try {
            RentRoomVO rentRoomVO = (RentRoomVO) this.queryForObject("RentRoom.getRommByRentCode", rentPactCode);
            return rentRoomVO;
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void removeRentRoom(String rentPactCode) throws DaoException {
        try {
            this.delete("RentRoom.removeRentRoom", rentPactCode);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public Integer updateRoomByRoomCode(RentRoomDO rentRoomDO) throws DaoException {
        try {
            return this.update("RentRoom.updateRoomByRoomCode", rentRoomDO);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public RentRoomVO getByDissolutionCode(String dissolutionCode) throws DaoException {
        try {
            return (RentRoomVO) this.queryForObject("RentRoom.getByDissolutionCode", dissolutionCode);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}
