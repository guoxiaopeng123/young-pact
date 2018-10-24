package com.young.pact.rpc.houseroom.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.young.common.dict.CommonEnum;
import com.young.common.domain.ApiResult;
import com.young.pact.common.constant.ErrorPactConsts;
import com.young.pact.common.exception.PactManagerExcepion;
import com.young.pact.rpc.houseroom.HouseRoomRpc;
import com.young.product.api.domain.result.rpc.house.HouseResult;
import com.young.product.api.service.rpc.house.HouseRpcService;


/**
 * @描述 : 房屋
 * @创建者 : guoxiaopeng
 * @创建时间 : 2018年9月14日 下午6:04:40
 */
@Component("houseRoomRpc")
public class HouseRoomRpcImpl implements HouseRoomRpc {
    private static final Log LOG = LogFactory.getLog(HouseRoomRpcImpl.class);
    private HouseRpcService houseRoomRpcService;
    @Override
    public HouseResult getHouseBase(String pactCode) {
        ApiResult<HouseResult> result = houseRoomRpcService.getHouseBase(pactCode);
        if(null!=result&&CommonEnum.REQUEST_SUCCESS.getCode().equals(result.getCode())){
            return result.getData();
        }else{
            LOG.error(ErrorPactConsts.GET_HOUSEBASE_ROOM_ERROR);
            throw new PactManagerExcepion(ErrorPactConsts.GET_HOUSEBASE_ROOM_ERROR);
        }
    }
    public HouseRpcService getHouseRoomRpcService() {
        return houseRoomRpcService;
    }
    public void setHouseRoomRpcService(HouseRpcService houseRoomRpcService) {
        this.houseRoomRpcService = houseRoomRpcService;
    }
  
}
