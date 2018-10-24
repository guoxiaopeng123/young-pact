package com.young.pact.controller.rentroom;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tools.common.springmvc.base.BaseController;
import com.young.common.domain.ApiResult;
import com.young.pact.api.domain.param.rest.rentroom.RoomParam;
import com.young.pact.api.domain.result.rest.rentroom.RoomResult;
import com.young.pact.api.service.rest.rentroom.RentRoomService;
import com.young.pact.common.constant.LoginConsts;

/**
 * @ClassName: PurchaseHouseController
 * @Description: TODO( 托出房间信息)
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月1日 下午6:23:19
 */
@RestController
@RequestMapping(value = "/rentroom", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class RentRoomController extends BaseController{
    private static Log LOG = LogFactory.getLog(RentRoomController.class);
    private  RentRoomService rentRoomService;
    /**
     * 
    * @Title: saveRentCustomer
    * @Description: TODO(保存房间redis )
    * @author GuoXiaoPeng
    * @param rentCustomerParam
    * @return 返回结果
     */
    @RequestMapping(value = "/saveRoom", method = RequestMethod.POST)
    public ApiResult<String> saveRoom(HttpServletRequest request, @RequestBody RoomParam roomParam){
        ApiResult<String> result = new ApiResult<>();
        if(null!=roomParam){
            String pin = (String) request.getAttribute(LoginConsts.PIN);
            String ip = super.getRemoteIPs(request);
            roomParam.setCreateName(pin);
            roomParam.setIp(ip);
        }
        try {
            result = rentRoomService.saveRoomRedis(roomParam);
        } catch (Exception e) {
            // TODO: handle exception
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
    /**
     * 
    * @Title: getRentCustomer
    * @Description: TODO(根据缓存key房间详情 )
    * @author GuoXiaoPeng
    * @param key 缓存key
    * @return 房间详情
     */
    @RequestMapping(value = "/getRoom/{redisKey}", method = RequestMethod.GET)
    public  ApiResult<RoomResult> getRoom(HttpServletRequest request,@PathVariable String redisKey){
        ApiResult<RoomResult> result = new ApiResult<RoomResult>();
        try {
            result = rentRoomService.getRoomRedis(redisKey);
        } catch (Exception e) {
            // TODO: handle exception
            LOG.error(e.getMessage(), e);
        }
        return result;
       
    }
    public RentRoomService getRentRoomService() {
        return rentRoomService;
    }
    public void setRentRoomService(RentRoomService rentRoomService) {
        this.rentRoomService = rentRoomService;
    }
   
}
