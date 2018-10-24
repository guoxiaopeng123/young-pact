package com.young.pact.service.rentroom.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.LogFactoryImpl;
import org.springframework.stereotype.Service;

import com.tools.common.util.convert.BeanUtils;
import com.tools.common.util.string.StringUtil;
import com.young.common.dict.CommonEnum;
import com.young.common.domain.ApiResult;
import com.young.customer.api.domain.result.rpc.CustomerRpcResult;
import com.young.pact.api.domain.param.rest.rentroom.RoomParam;
import com.young.pact.api.domain.result.rest.rentroom.RoomResult;
import com.young.pact.api.service.rest.rentroom.RentRoomService;
import com.young.pact.common.constant.StringConsts;
import com.young.pact.common.dict.PactCommonEnum;
import com.young.pact.common.exception.PactManagerExcepion;
import com.young.pact.domain.rentroom.RentRoomDO;
import com.young.pact.domain.rentroom.RentRoomVO;
import com.young.pact.manager.rentroom.RentRoomManager;
import com.young.pact.rpc.customer.CustomerRpc;

/**
 * @描述 : 托出房间
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月1日 下午4:40:58
 */
@Service("rentRoomService")
public class RentRoomServiceImpl implements RentRoomService{
    private static Log LOG = LogFactoryImpl.getLog(RentRoomServiceImpl.class);
    private RentRoomManager rentRoomManager;
    private CustomerRpc customerRpc;
    @Override
    public ApiResult<String> saveRoomRedis(RoomParam roomParam) {
        ApiResult<String> result = new ApiResult<>();
        String customerCode = "";
        StringBuilder key = new StringBuilder();
        String pin = ""; 
        try {
            if(null == roomParam || null == roomParam.getType() || StringUtil.isBlank(roomParam.getServiceCode())){
                result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
                result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
                return result;
            }
            pin = roomParam.getCreateName() == null?"":roomParam.getCreateName();
            key.append(roomParam.getServiceCode()).append(StringConsts.UNDERLINE)
            .append(StringConsts.REDIS_ROOM_KEY).append(StringConsts.UNDERLINE).append(pin);
            RentRoomDO rentRoomDO = new RentRoomDO();
            BeanUtils.copyProperties(roomParam, rentRoomDO);
            rentRoomDO.setKey(key.toString());
            if(1 == roomParam.getType()){
                if(StringUtil.isBlank(roomParam.getNeedCode())){
                    result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
                    result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
                    return result;
                }
                //=====================根据需求编码查询客源 调用接口 start  =======================================
                CustomerRpcResult customerRpcResult = customerRpc.getCustomerByDemandCode(roomParam.getNeedCode());
                customerCode = customerRpcResult.getCustomerCode();
                rentRoomDO.setCustomerCode(customerCode);
                //=====================根据需求编码查询客源 调用接口 end=======================================
                rentRoomDO.setRoomCode(roomParam.getServiceCode());
                rentRoomDO.setDemandCode(roomParam.getNeedCode());
            }
            else if(2 == roomParam.getType()){
                if(StringUtils.isBlank(roomParam.getRoomCode())){
                    result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
                    result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
                    return result;
                }
                rentRoomDO.setRentPactCode(roomParam.getServiceCode());
            }
            else if(3 == roomParam.getType()){
                if(StringUtil.isBlank(roomParam.getCustomerCode())){
                    result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
                    result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
                    return result;
                }
                if(StringUtil.isBlank(roomParam.getNeedCode())){
                    result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
                    result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
                    return result;
                }
                rentRoomDO.setDemandCode(roomParam.getNeedCode());
                rentRoomDO.setRentPactCode(roomParam.getServiceCode());
            }
            rentRoomManager.saveRoomRedis(rentRoomDO);
            result.setData(rentRoomDO.getKey());
            result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
            result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
        } catch (PactManagerExcepion e){
            LOG.error(CommonEnum.SYSTEM_EXCEPTION.getMessage(), e);
            result.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            result.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        } catch (Exception e) {
            LOG.error(CommonEnum.SYSTEM_EXCEPTION.getMessage(), e);
            result.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            result.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        }
        return result;
    }

    @Override
    public ApiResult<RoomResult> getRoomRedis(String key) {
        ApiResult<RoomResult> result = new ApiResult<>();
        try {
            if(StringUtils.isBlank(key)){
                result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
                result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
                return result;
            }
            RentRoomVO rentRoomVO = rentRoomManager.getRoomRedis(key);
            if(null != rentRoomVO){
                RoomResult roomResult = new RoomResult();
                BeanUtils.copyProperties(rentRoomVO, roomResult);
                result.setData(roomResult);
                result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
            }
        } catch (PactManagerExcepion e) {
            LOG.error(CommonEnum.SYSTEM_EXCEPTION.getMessage(), e);
            result.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            result.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        } catch (Exception e) {
            LOG.error(CommonEnum.SYSTEM_EXCEPTION.getMessage(), e);
            result.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            result.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        }
        return result;
    }

    public RentRoomManager getRentRoomManager() {
        return rentRoomManager;
    }

    public void setRentRoomManager(RentRoomManager rentRoomManager) {
        this.rentRoomManager = rentRoomManager;
    }

    public CustomerRpc getCustomerRpc() {
        return customerRpc;
    }

    public void setCustomerRpc(CustomerRpc customerRpc) {
        this.customerRpc = customerRpc;
    }
}
