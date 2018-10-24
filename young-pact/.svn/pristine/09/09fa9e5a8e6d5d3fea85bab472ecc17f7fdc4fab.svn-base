package com.young.pact.manager.rentcustomer.impl;

import java.util.ArrayList;
import java.util.List;

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
import com.young.pact.dao.rentcustomer.RentCustomerDao;
import com.young.pact.domain.rentcommonowner.RentCommonOwnerVO;
import com.young.pact.domain.rentcustomer.CustomerOwnerDO;
import com.young.pact.domain.rentcustomer.CustomerOwnerVO;
import com.young.pact.domain.rentcustomer.RentCustomerDO;
import com.young.pact.domain.rentcustomer.RentCustomerVO;
import com.young.pact.manager.rentcustomer.RentCustomerManager;

/**
 * @描述 : 租客
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月1日 下午5:07:29
 */
@Component("rentCustomerManager")
public class RentCustomerManagerImpl implements RentCustomerManager{

    private static Log LOG = LogFactoryImpl.getLog(RentCustomerManagerImpl.class);
    private RedisClient redisClient;
    private RentCustomerDao rentCustomerDao;
    @Override
    public void saveRentCustomerRedis(CustomerOwnerDO customerOwnerDO) {
        try {
            redisClient.setObject(customerOwnerDO.getKey(),RedisConsts.PURCHASE_TIME, customerOwnerDO);
        } catch (RedisAccessException e) {
            LOG.error(ErrorPactConsts.REDIS_SET_EROR + customerOwnerDO.getKey(), e);
            throw new PactManagerExcepion(ErrorPactConsts.REDIS_SET_EROR + customerOwnerDO.getKey(), e);
        }
    }

    @Override
    public CustomerOwnerVO getRentCustomerRedis(String key) {
        try {
            CustomerOwnerVO customerOwnerVO = new CustomerOwnerVO();
            if(redisClient.exists(key)){
                CustomerOwnerDO customerOwnerDO = redisClient.getObject(key);
                //租客信息
                RentCustomerVO renter = new RentCustomerVO();
                BeanUtils.copyProperties(customerOwnerDO.getRenter(), renter);
                customerOwnerVO.setRenter(renter);
                //共同居住人（集合）
                List<RentCommonOwnerVO> cohabitantList = new ArrayList<>();
                BeanUtils.copyListProperties(customerOwnerDO.getCohabitantList(), cohabitantList, RentCommonOwnerVO.class);
                customerOwnerVO.setCohabitantList(cohabitantList);
                return customerOwnerVO;
            }else{
                return null;
            }
        } catch (RedisAccessException e) {
            LOG.error(ErrorPactConsts.REDIS_SET_EROR + key, e);
            throw new PactManagerExcepion(ErrorPactConsts.REDIS_SET_EROR + key, e);
        } catch (Exception e) {
            LOG.error(CommonEnum.SYSTEM_EXCEPTION + key, e);
            throw new PactManagerExcepion(CommonEnum.SYSTEM_EXCEPTION.getMessage(), e);
        }
    }
    
    @Override
    public Long saveRentCustomer(RentCustomerDO rentCustomerDO) {
        try {
            Long flag = rentCustomerDao.saveRentCustomer(rentCustomerDO);
            return flag;
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.INSERT_RENTCUSTOMER_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.INSERT_RENTCUSTOMER_ERROR,e);
        }
    }
    
    @Override
    public RentCustomerVO getRentCustomerByPactCode(String pactCode) {
        try {
            RentCustomerVO rentCustomerVO = rentCustomerDao.getRentCustomerByPactCode(pactCode);
            return rentCustomerVO;
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.QUERY_RENT_CUSTOMER_BY_PACTCODE_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.QUERY_RENT_CUSTOMER_BY_PACTCODE_ERROR,e);
        }
    }
    
    public RedisClient getRedisClient() {
        return redisClient;
    }

    public void setRedisClient(RedisClient redisClient) {
        this.redisClient = redisClient;
    }

    public RentCustomerDao getRentCustomerDao() {
        return rentCustomerDao;
    }

    public void setRentCustomerDao(RentCustomerDao rentCustomerDao) {
        this.rentCustomerDao = rentCustomerDao;
    }
}
