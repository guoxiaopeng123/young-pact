package com.young.pact.rpc.room.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.young.common.dict.CommonEnum;
import com.young.common.domain.ApiResult;
import com.young.pact.common.constant.ErrorPactConsts;
import com.young.pact.common.exception.PactManagerExcepion;
import com.young.pact.rpc.room.RoomRpc;
import com.young.product.api.domain.param.rpc.room.RoomParam;
import com.young.product.api.domain.result.rpc.room.RoomResult;
import com.young.product.api.service.rpc.room.RoomRpcService;

/**
 * @描述 : 房间RPC
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月21日 下午6:21:04
 */
@Component("roomRpc")
public class RoomRpcImpl implements RoomRpc {
    private static final Log LOG = LogFactory.getLog(RoomRpcImpl.class);
    private RoomRpcService roomRpcService;
    @Override
    public Integer countRoomStateByCode(RoomParam roomParam) {
        ApiResult<Integer> result = roomRpcService.countRoomStateByCode(roomParam);
        if(null!=result&&CommonEnum.REQUEST_SUCCESS.getCode().equals(result.getCode())){
            return result.getData();
        }else{
            LOG.error(ErrorPactConsts.COUNT_ROOM_STATE_ERROR);
            throw new PactManagerExcepion(ErrorPactConsts.COUNT_ROOM_STATE_ERROR);
        }
    }
    @Override
    public RoomResult getRoomBase(String roomCode) {
        ApiResult<RoomResult> roomResult = roomRpcService.getRoomBase(roomCode);
        if(null!=roomResult&&CommonEnum.REQUEST_SUCCESS.getCode().equals(roomResult.getCode())){
            RoomResult data = roomResult.getData();
            return data;
        }else{
            LOG.error(ErrorPactConsts.QUERY_ROOMBASE_ERROR);
            throw new PactManagerExcepion(ErrorPactConsts.QUERY_ROOMBASE_ERROR);
        }
    }
    
    @Override
    public RoomResult getRoomByCode(String roomCode) {
        ApiResult<RoomResult> roomResult = roomRpcService.getRoomByCode(roomCode);
        if(null!=roomResult&&CommonEnum.REQUEST_SUCCESS.getCode().equals(roomResult.getCode())){
            RoomResult data = roomResult.getData();
            return data;
        }else{
            LOG.error(ErrorPactConsts.QUERY_ROOMBASE_ERROR);
            throw new PactManagerExcepion(ErrorPactConsts.QUERY_ROOMBASE_ERROR);
        }
    }
    
    public RoomRpcService getRoomRpcService() {
        return roomRpcService;
    }
    public void setRoomRpcService(RoomRpcService roomRpcService) {
        this.roomRpcService = roomRpcService;
    }
    
}
