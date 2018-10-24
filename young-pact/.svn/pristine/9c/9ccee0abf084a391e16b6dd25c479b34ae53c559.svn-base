package com.young.pact.manager.rentroom.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.LogFactoryImpl;
import org.springframework.stereotype.Component;

import com.tools.common.redis.client.RedisClient;
import com.tools.common.redis.exception.RedisAccessException;
import com.tools.common.util.convert.BeanUtils;
import com.young.common.dict.CommonEnum;
import com.young.common.exception.DaoException;
import com.young.pact.common.constant.ErrorPactConsts;
import com.young.pact.common.constant.RedisConsts;
import com.young.pact.common.exception.PactManagerExcepion;
import com.young.pact.dao.rentroom.RentRoomDao;
import com.young.pact.domain.rentroom.RentRoomDO;
import com.young.pact.domain.rentroom.RentRoomVO;
import com.young.pact.manager.rentroom.RentRoomManager;

/**
 * @描述 :  托出房间
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月1日 下午4:44:44
 */
@Component("rentRoomManager")
public class RentRoomManagerImpl implements RentRoomManager{
    private static Log LOG = LogFactoryImpl.getLog(RentRoomManagerImpl.class);
    private RedisClient redisClient;
    private RentRoomDao rentRoomDao;
    @Override
    public void saveRoomRedis(RentRoomDO rentRoomDO) {
        try {
            redisClient.setObject(rentRoomDO.getKey(), RedisConsts.PURCHASE_TIME,rentRoomDO);
        } catch (RedisAccessException e) {
            LOG.error(ErrorPactConsts.REDIS_SET_EROR + rentRoomDO.getKey(), e);
            throw new PactManagerExcepion(ErrorPactConsts.REDIS_SET_EROR + rentRoomDO.getKey(), e);
        }
    }

    @Override
    public RentRoomVO getRoomRedis(String key) {
        try {
            RentRoomVO rentRoomVO = new RentRoomVO();
            if(redisClient.exists(key)){
                BeanUtils.copyProperties(redisClient.getObject(key), rentRoomVO);
                return rentRoomVO;
            }else{
                return null;
            }
        } catch (RedisAccessException e) {
            LOG.error(ErrorPactConsts.REDIS_SET_EROR + key, e);
            throw new PactManagerExcepion(ErrorPactConsts.REDIS_SET_EROR + key, e);
        }catch (Exception e) {
            LOG.error(CommonEnum.SYSTEM_EXCEPTION + key, e);
            throw new PactManagerExcepion(CommonEnum.SYSTEM_EXCEPTION.getMessage(), e);
        }
    }

    @Override
    public Long saveRentRoomVO(RentRoomDO rentRoomDO) {
        try {
            Long flag = rentRoomDao.saveRentRoom(rentRoomDO);
            return flag;
        } catch (DaoException e) {
            // TODO: handle exception
            LOG.error(ErrorPactConsts.INSERT_RENTROOM_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.INSERT_RENTROOM_ERROR, e);
        }
    }
    
    @Override
    public RentRoomVO getRommByRentCode(String pactCode) {
        try {
            RentRoomVO rentRoomVO = rentRoomDao.getRommByRentCode(pactCode);
            return rentRoomVO;
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.QUERY_RENT_ROOM_BY_PACTCODE_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.QUERY_RENT_ROOM_BY_PACTCODE_ERROR, e);
        }catch (Exception e) {
            LOG.error(ErrorPactConsts.QUERY_RENT_ROOM_BY_PACTCODE_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.QUERY_RENT_ROOM_BY_PACTCODE_ERROR, e);
        }
    }
    
  
    public RedisClient getRedisClient() {
        return redisClient;
    }

    public void setRedisClient(RedisClient redisClient) {
        this.redisClient = redisClient;
    }

    public RentRoomDao getRentRoomDao() {
        return rentRoomDao;
    }

    public void setRentRoomDao(RentRoomDao rentRoomDao) {
        this.rentRoomDao = rentRoomDao;
    }
}
