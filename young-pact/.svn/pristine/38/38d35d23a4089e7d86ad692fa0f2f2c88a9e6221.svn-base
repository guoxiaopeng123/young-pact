package com.young.pact.service.rentbase.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.LogFactoryImpl;
import org.springframework.stereotype.Service;

import com.tools.common.util.convert.BeanUtils;
import com.tools.common.util.string.StringUtil;
import com.young.common.dict.CommonEnum;
import com.young.common.domain.ApiResult;
import com.young.common.page.PageBean;
import com.young.common.page.PageTableBean;
import com.young.common.util.DateUtil;
import com.young.house.api.domain.result.rpc.house.GetHouseRpcResult;
import com.young.pact.api.domain.param.rest.commoncostrule.CommonCostRuleParam;
import com.young.pact.api.domain.param.rest.financereceiptpay.FinanceReceiptPayParam;
import com.young.pact.api.domain.param.rest.rentbase.RentBaseParam;
import com.young.pact.api.domain.param.rest.rentbase.RentPactParam;
import com.young.pact.api.domain.param.rest.rentcustomer.RentCustomerParam;
import com.young.pact.api.domain.result.rest.commoncostrule.CommonCostRuleResult;
import com.young.pact.api.domain.result.rest.commonowner.CommonOwnerResult;
import com.young.pact.api.domain.result.rest.commonpic.CommonPicResult;
import com.young.pact.api.domain.result.rest.financereceiptpay.FinanceReceiptPayResult;
import com.young.pact.api.domain.result.rest.rentbase.RentBaseResult;
import com.young.pact.api.domain.result.rest.rentbase.RentPactResult;
import com.young.pact.api.domain.result.rest.rentcustomer.RentCustomerResult;
import com.young.pact.api.domain.result.rest.rentcustomer.RenterResult;
import com.young.pact.api.domain.result.rest.rentroom.RoomResult;
import com.young.pact.api.service.rest.rentbase.RentBaseService;
import com.young.pact.common.constant.NormalConstant;
import com.young.pact.common.constant.PactTypeConsts;
import com.young.pact.common.constant.SerializeTypeConts;
import com.young.pact.common.constant.StringConsts;
import com.young.pact.common.dict.PactCommonEnum;
import com.young.pact.common.exception.PactManagerExcepion;
import com.young.pact.domain.commonbelonger.CommonBelongerDO;
import com.young.pact.domain.commonbelonger.CommonBelongerQuery;
import com.young.pact.domain.commonbelonger.CommonBelongerVO;
import com.young.pact.domain.commoncostrule.CommonCostRuleDO;
import com.young.pact.domain.commongoods.CommonGoodsDO;
import com.young.pact.domain.commongoods.CommonGoodsPicDO;
import com.young.pact.domain.commonmeterread.CommonMeterReadDO;
import com.young.pact.domain.commonmeterread.CommonMeterReadPicDO;
import com.young.pact.domain.commonmeterread.MeterReadGoodsDO;
import com.young.pact.domain.commonmeterread.MeterReadGoodsVO;
import com.young.pact.domain.commonpic.CommonPicDO;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayDO;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayQuery;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayVO;
import com.young.pact.domain.rentbase.RentBaseDO;
import com.young.pact.domain.rentbase.RentBaseQuery;
import com.young.pact.domain.rentbase.RentBaseVO;
import com.young.pact.domain.rentbase.RentPactDO;
import com.young.pact.domain.rentbase.RentPactVO;
import com.young.pact.domain.rentcommonowner.RentCommonOwnerDO;
import com.young.pact.domain.rentcommonowner.RentCommonOwnerVO;
import com.young.pact.domain.rentcustomer.CustomerOwnerDO;
import com.young.pact.domain.rentcustomer.CustomerOwnerVO;
import com.young.pact.domain.rentcustomer.RentCustomerDO;
import com.young.pact.domain.rentcustomer.RentCustomerVO;
import com.young.pact.domain.rentroom.RentRoomDO;
import com.young.pact.domain.rentroom.RentRoomVO;
import com.young.pact.domain.statistics.AgendaDO;
import com.young.pact.domain.statistics.StatisticDO;
import com.young.pact.manager.common.CommonManager;
import com.young.pact.manager.commonbelonger.CommonBelongerManager;
import com.young.pact.manager.commonmeterread.CommonMeterReadManager;
import com.young.pact.manager.financereceiptpay.FinanceReceiptPayManager;
import com.young.pact.manager.propertytransfer.PropertyTransferManager;
import com.young.pact.manager.rentbase.RentBaseManager;
import com.young.pact.manager.rentcommonowner.CommonOwnerManager;
import com.young.pact.manager.rentcustomer.RentCustomerManager;
import com.young.pact.manager.rentroom.RentRoomManager;
import com.young.pact.manager.statistics.StatisticsManager;
import com.young.pact.rpc.dept.DeptRpc;
import com.young.pact.rpc.dictionary.DictionaryRpc;
import com.young.pact.rpc.house.HouseRpc;
import com.young.pact.rpc.operatelog.OperateLogRpc;
import com.young.pact.rpc.personal.PersonalRpc;
import com.young.pact.rpc.remind.RemindRpc;
import com.young.pact.rpc.room.RoomRpc;
import com.young.pact.rpc.serialize.SerializeRpc;
import com.young.sso.api.domain.result.rpc.personal.PersonalResult;


/**
 * @description : 托出合同
 * @author : guoxiaopeng
 * @date : 2018年8月1日 上午9:47:02
 */
@Service("rentBaseService")
public class RentBaseServiceImpl implements RentBaseService{
    private static Log LOG = LogFactoryImpl.getLog(RentBaseServiceImpl.class);
    private RentBaseManager rentBaseManager;
    private RentRoomManager rentRoomManager;
    private RentCustomerManager rentCustomerManager;
    private PropertyTransferManager propertyTransferManager;
    private CommonMeterReadManager commonMeterReadManager;
    private CommonManager commonManager;
    private CommonOwnerManager commonOwnerManager;
    private FinanceReceiptPayManager financeReceiptPayManager;
    private CommonBelongerManager commonBelongerManager;
    private OperateLogRpc operateLogRpc;
    private PersonalRpc personalRpc;
    private SerializeRpc serializeRpc;
    private RoomRpc roomRpc;
    private HouseRpc houseRpc;
    private DictionaryRpc dictionaryRpc;
    private DeptRpc deptRpc;
    private RemindRpc remindRpc;
    private Map<String,String> remindMap;
    private StatisticsManager statisticsManager;
    @Override
    public ApiResult<String> savePactRedis(RentPactParam parm) {
        ApiResult<String> result = new ApiResult<>();
        StringBuilder key = new StringBuilder();
        String pin = ""; 
        try {
            pin = parm.getCreateName() == null?"":parm.getCreateName();
            key.append(parm.getServiceCode()).append(StringConsts.UNDERLINE)
            .append(StringConsts.REDIS_PACT_KEY).append(StringConsts.UNDERLINE).append(pin);
            result = validNull(parm);
            if(StringUtil.isNotEmpty(result.getCode())){
                return result;
            }
            RentPactDO rentPactDO = new RentPactDO();
            BeanUtils.copyProperties(parm, rentPactDO);
            RentBaseDO pact = new RentBaseDO();
            BeanUtils.copyProperties(parm.getPact(), pact);
            rentPactDO.setPact(pact);
            
            List<CommonCostRuleDO> commonCostRuleDOs =new ArrayList<>();
            List<FinanceReceiptPayDO> financeReceiptPayDOs = new ArrayList<>();
            List<CommonPicDO> commonPicDOs = new ArrayList<>();
            if(null!=parm.getPactReceiptPayList()&&parm.getPactReceiptPayList().size()>0){
                BeanUtils.copyListProperties(parm.getPactReceiptPayList(), commonCostRuleDOs, CommonCostRuleDO.class);
            }
            BeanUtils.copyListProperties(parm.getReceiptPayList(), financeReceiptPayDOs, FinanceReceiptPayDO.class);
            BeanUtils.copyListProperties(parm.getUrlList(), commonPicDOs, CommonPicDO.class);
            rentPactDO.setPactReceiptPayList(commonCostRuleDOs);
            rentPactDO.setReceiptPayList(financeReceiptPayDOs);
            rentPactDO.setUrlList(commonPicDOs);
            rentBaseManager.savePactRedis(key.toString(), rentPactDO);
            result.setData(key.toString());
            result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
            result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
        }  catch (PactManagerExcepion e) {
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
    /**
    * @Title: validNull
    * @Description: TODO( 校验必填字段 )
    * @author GuoXiaoPeng
    * @param parm 合同信息参数
    * @return 返回结果
     */
    @SuppressWarnings("unused")
    private ApiResult<String> validNull(RentPactParam parm) {
        ApiResult<String> result = new ApiResult<>();
        RentBaseParam pact = null; 
        List<FinanceReceiptPayParam> financeReceiptPayParams = null;
        pact = parm.getPact();
        List<CommonCostRuleParam> pactReceiptPayList = null;
        pactReceiptPayList = parm.getPactReceiptPayList();
        financeReceiptPayParams = parm.getReceiptPayList();
        if(null == parm){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        if(null == parm){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        if(StringUtil.isBlank(pact.getPaperPactCode())){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        if(null==pact.getStartTime()){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        if(null==pact.getEndTime()){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        if(null==pact.getPactTerm()){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        if(null==pact.getPrice()){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        if(null==pact.getPayWay()){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        if(null==pact.getDeposit()){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        if(null==pact.getPayDays()){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        if(null==pact.getFirstPayTime()){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        if(StringUtil.isBlank(pact.getOpenBank())){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        if(StringConsts.MONTHLY_PAYMENT.equals( pact.getPayWay())){
            if(StringUtil.isBlank(pact.getMonthlyPayment())){
                result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
                result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
                return result;
            }
        }
        if(StringUtil.isBlank(pact.getDealPin())){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        if(null==pact.getSigningTime()){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        if(null==financeReceiptPayParams||financeReceiptPayParams.size()<=0){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        for(FinanceReceiptPayParam financeReceiptPayParam: financeReceiptPayParams) {
            if(null==financeReceiptPayParam.getType()){
                result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
                result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
                return result;
            }
            if(StringUtil.isBlank(financeReceiptPayParam.getCostType())){
                result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
                result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
                return result;
            }
            if(null==financeReceiptPayParam.getCostAmount()){
                result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
                result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
                return result;
            }
            if(StringUtil.isBlank(financeReceiptPayParam.getPayeeObject())){
                result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
                result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
                return result;
            }
            if(StringUtil.isBlank(financeReceiptPayParam.getAccount())){
                result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
                result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
                return result;
            }
            if(null==financeReceiptPayParam.getStartTime()){
                result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
                result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
                return result;
            }
            if(null==financeReceiptPayParam.getEndTime()){
                result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
                result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
                return result;
            }
            if(null==financeReceiptPayParam.getCostTime()){
                result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
                result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
                return result;
            }
        }
        if(null==parm.getUrlList()||parm.getUrlList().size()<=0){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        if(null!=pactReceiptPayList&&pactReceiptPayList.size()>0){
            for(CommonCostRuleParam commonCostRuleParam:pactReceiptPayList){
                if(null==commonCostRuleParam.getReceiptPayType()){
                    result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
                    result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
                    return result;
                }
                if(null==commonCostRuleParam.getCostType()){
                    result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
                    result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
                    return result;
                }
                if(null==commonCostRuleParam.getReceiptPayWay()){
                    result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
                    result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
                    return result;
                }
                if(null==commonCostRuleParam.getCostPrice()){
                    result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
                    result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
                    return result;
                }
                if(StringUtil.isEmpty(commonCostRuleParam.getPayee())){
                    result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
                    result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
                    return result;
                }
            }
        }
        return result;
    }

    @Override
    public ApiResult<RentPactResult> getPactRedis(String redisKey) {
        ApiResult<RentPactResult> result = new ApiResult<>();
        try {
            RentPactVO rentPactVO = rentBaseManager.getPactRedis(redisKey);
            if(null!=rentPactVO){
                RentPactResult rentPactResult = new RentPactResult();
                BeanUtils.copyProperties(rentPactVO, rentPactResult);
                RentBaseResult pact = new RentBaseResult();
                BeanUtils.copyProperties(rentPactVO.getPact(), pact);
                rentPactResult.setPact(pact);
                List<CommonCostRuleResult> commonCostRuleResults = new ArrayList<>();
                List<FinanceReceiptPayResult> financeReceiptPayResults = new ArrayList<>();
                List<CommonPicResult> commonPicResults = new ArrayList<>();
                BeanUtils.copyListProperties(rentPactVO.getPactReceiptPayList(),commonCostRuleResults , CommonCostRuleResult.class);
                BeanUtils.copyListProperties(rentPactVO.getReceiptPayList(), financeReceiptPayResults,FinanceReceiptPayResult.class );
                BeanUtils.copyListProperties(rentPactVO.getUrlList(), commonPicResults, CommonPicResult.class);
                rentPactResult.setPactReceiptPayList(commonCostRuleResults);
                rentPactResult.setReceiptPayList(financeReceiptPayResults);
                rentPactResult.setUrlList(commonPicResults);
                result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                result.setData(rentPactResult);
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
    
    @SuppressWarnings("all")
    @Override
    public ApiResult<String> saveRentPact(RentBaseParam param) {
        ApiResult<String> result = new ApiResult<>();
        String rentPactCode = "";
        rentPactCode = serializeRpc.queryCodeBySerializeType(SerializeTypeConts.RENT);
        String customerCode = "";
        String roomCode = "";
        String renterCode = "";
        renterCode = serializeRpc.queryCodeBySerializeType(SerializeTypeConts.RENTER);
        String pin = param.getCreateName()==null?"":param.getCreateName();
        pin = param.getCreateName();
        String ip = param.getIp();
        String costCode = "";
        costCode = serializeRpc.queryCodeBySerializeType(SerializeTypeConts.COST);
        String houseCode = "";
        String propertyAddress = "";
        if(null == param || StringUtil.isBlank(param.getRoomCode())){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getCode());
            return result;
        }
        StringBuilder roomRedisKey = new StringBuilder("");
        roomRedisKey.append(param.getRoomCode()).append(StringConsts.UNDERLINE)
            .append(StringConsts.REDIS_ROOM_KEY).append(StringConsts.UNDERLINE)
            .append(pin);
        
        StringBuilder renterRedisKey = new StringBuilder("");
        renterRedisKey.append(param.getRoomCode()).append(StringConsts.UNDERLINE)
            .append(StringConsts.REDIS_RENTER_KEY).append(StringConsts.UNDERLINE)
            .append(pin);
        StringBuilder pactRedisKey = new StringBuilder("");
        pactRedisKey.append(param.getRoomCode()).append(StringConsts.UNDERLINE)
            .append(StringConsts.REDIS_PACT_KEY).append(StringConsts.UNDERLINE)
            .append(pin);
        StringBuilder propertyRedisKey = new StringBuilder("");
        propertyRedisKey.append(param.getRoomCode()).append(StringConsts.UNDERLINE)
            .append(StringConsts.REDIS_PROPERTYTRANSFER_KEY).append(StringConsts.UNDERLINE)
            .append(pin);
        try {
            RentRoomVO rentRoomVO = rentRoomManager.getRoomRedis(roomRedisKey.toString());  
            if(null == rentRoomVO){
                result.setCode(PactCommonEnum.RENT_ROOM_REDIS_INVALID.getCode());
                result.setMessage(PactCommonEnum.RENT_ROOM_REDIS_INVALID.getMessage());
                return result;
            }
            roomCode = rentRoomVO.getRoomCode();
            CustomerOwnerVO customerOwnerVO = rentCustomerManager.getRentCustomerRedis(renterRedisKey.toString());
            RentCustomerVO renter = customerOwnerVO.getRenter();
            if(null == customerOwnerVO){
                result.setCode(PactCommonEnum.RENT_CUSTOMER_REDIS_INVALID.getCode());
                result.setMessage(PactCommonEnum.RENT_CUSTOMER_REDIS_INVALID.getMessage());
                return result;
            }
            RentPactVO rentPactVO = rentBaseManager.getPactRedis(pactRedisKey.toString());
            if(null == rentPactVO){
                result.setCode(PactCommonEnum.RENT_BASE_REDIS_INVALID.getCode());
                result.setMessage(PactCommonEnum.RENT_BASE_REDIS_INVALID.getMessage());
                return result;
            }
            MeterReadGoodsVO meterReadGoodsVO = commonMeterReadManager.getMeterGoodsRedis(propertyRedisKey.toString());
            if(null == meterReadGoodsVO){
                result.setCode(PactCommonEnum.RENT_PROPERTY_REDIS_INVALID.getCode());
                result.setMessage(PactCommonEnum.RENT_PROPERTY_REDIS_INVALID.getMessage());
                return result;
            }
            customerCode = rentRoomVO.getCustomerCode();
            roomCode = param.getRoomCode();
            com.young.product.api.domain.result.rpc.room.RoomResult roomResult = roomRpc.getRoomBase(roomCode);
            //房间状态是否为未租
            if(1!=roomResult.getStockState()){
                result.setCode(PactCommonEnum.ROOM_STATE_NOT_MATCH.getCode());
                result.setMessage(PactCommonEnum.ROOM_STATE_NOT_MATCH.getMessage());
                return result;
            }
            /*************根据房间编码查询销控房间信息        start******************/
            propertyAddress = roomResult.getAddress();
            List<GetHouseRpcResult> houseList = houseRpc.getHouseByCodeList(roomResult.getHouseCode());
            GetHouseRpcResult getHouseRpcResult = new GetHouseRpcResult();
            if(null!=houseList&&houseList.size()>0){
                 getHouseRpcResult = houseList.get(0);
            }
            //房间
            RentRoomDO rentRoomDO = new RentRoomDO();
            rentRoomDO.setRoomCode(roomCode);
            rentRoomDO.setRoomNo(roomResult.getRoomNo());
            rentRoomDO.setShsCode(roomResult.getShsCode());
            rentRoomDO.setRentPactCode(rentPactCode);
            rentRoomDO.setHouseCode(roomResult.getHouseCode());
            rentRoomDO.setStyle(roomResult.getHouseStyle());
            rentRoomDO.setRoomAcreage(roomResult.getRoomAcreage());
            rentRoomDO.setOrientation(roomResult.getOrientation());
            rentRoomDO.setCount(roomResult.getCount());
            rentRoomDO.setFloorPrice(roomResult.getFloorPrice());
            rentRoomDO.setPrice(roomResult.getPrice());
            rentRoomDO.setMonthlyPrice(roomResult.getMonthlyPrice());
            rentRoomDO.setQuarterlyPrice(roomResult.getQuarterlyPrice());
            rentRoomDO.setChannelMonthly(roomResult.getChannelMonthly());
            rentRoomDO.setChannelQuarterly(roomResult.getChannelQuarterly());
            rentRoomDO.setPropertyMoney(roomResult.getPropertyMoney());
            rentRoomDO.setWaterMoney(roomResult.getWaterMoney());
            rentRoomDO.setHeatingMoney(roomResult.getHeatingMoney());
            rentRoomDO.setServiceMoney(roomResult.getServiceMoney());
            rentRoomDO.setCardDeposit(roomResult.getCardDeposit());
            rentRoomDO.setKeyDeposit(roomResult.getKeyDeposit());
            rentRoomDO.setManagementMoney(roomResult.getManagementMoney());
            rentRoomDO.setFeature(roomResult.getFeature());
            rentRoomDO.setRoomDeploy(roomResult.getRoomDeploy());
            rentRoomDO.setPublicDeploy(roomResult.getPublicDeploy());
            rentRoomDO.setRentWay(roomResult.getRentWay());
            rentRoomDO.setStockState(roomResult.getStockState());
            rentRoomDO.setSaleState(roomResult.getSaleState());
            rentRoomDO.setDecorationState(roomResult.getDecorationState());
            rentRoomDO.setIsPurchase(roomResult.getIsPurchase());
            rentRoomDO.setRentTime(roomResult.getRentTime());
            rentRoomDO.setVacantDays(roomResult.getVacantDays());
            rentRoomDO.setFloor(getHouseRpcResult.getCurrentFloor());
            rentRoomDO.setAllFloor(getHouseRpcResult.getAllFloor());
            rentRoomDO.setAddress(propertyAddress);
            rentRoomDO.setInternetFee(roomResult.getInternetFee());
            rentRoomDO.setIp(ip);
            rentRoomDO.setCreateName(pin);
            /*************根据房间编码查询销控房间信息      end******************/
            /*************托出租客 +托出租客共同居住人     start******************/
            CustomerOwnerDO customerOwnerDO = new CustomerOwnerDO();
            BeanUtils.copyProperties(customerOwnerVO, customerOwnerDO);
            RentCustomerDO rentCustomerDO = new RentCustomerDO();
            BeanUtils.copyProperties(customerOwnerVO.getRenter(), rentCustomerDO);
            //托出租客
            rentCustomerDO.setRentPactCode(rentPactCode);
            rentCustomerDO.setRenterCode(renterCode);
            rentCustomerDO.setCustomerCode(customerCode);
            rentCustomerDO.setDemandCode(rentRoomVO.getDemandCode());
            rentCustomerDO.setCreateName(pin);
            rentCustomerDO.setIp(ip);
            customerOwnerDO.setRenter(rentCustomerDO);
           //托出租客共同居住人 
            List<RentCommonOwnerDO> cohabitantList = new ArrayList<>();
            if(null!=customerOwnerVO.getCohabitantList()&&customerOwnerVO.getCohabitantList().size()>0){
                BeanUtils.copyListProperties(customerOwnerVO.getCohabitantList(), cohabitantList, RentCommonOwnerDO.class);
            }
            customerOwnerDO.setCohabitantList(cohabitantList);
            if(null!=cohabitantList){
                for(RentCommonOwnerDO rentCommonOwnerDO:cohabitantList){
                    rentCommonOwnerDO.setRenterCode(renterCode);
                    rentCommonOwnerDO.setCreateName(pin);
                    rentCommonOwnerDO.setIp(ip);
                }
            }
            /*************托出租客 +托出租客共同居住人      end******************/
            /*************托出合同      start******************/
            //托出合同  
            RentPactDO rentPactDO = new RentPactDO();
            BeanUtils.copyProperties(rentPactVO, rentPactDO);
            RentBaseDO rentBaseDO = new RentBaseDO();
            BeanUtils.copyProperties(rentPactVO.getPact(), rentBaseDO);
            rentBaseDO.setIsEffective(0);
            rentBaseDO.setIsDelete(0);
            rentBaseDO.setCancelState(1);
            rentBaseDO.setDealState(1);
            rentBaseDO.setState(1);
            rentBaseDO.setDueState(1);
            rentBaseDO.setRentPactCode(rentPactCode);
            rentBaseDO.setCreateName(pin);
            rentBaseDO.setIp(ip);
            RentBaseVO rentBaseVO = rentPactVO.getPact();
            String payWay = rentBaseVO.getPayWay();
            if(StringUtil.isNotBlank(payWay)){
                if(StringConsts.MONTHLY_PAYMENT.equals(payWay)){
                    rentBaseDO.setPay(payWay);
                }else{
                    int indexOf = payWay.indexOf(StringConsts.PAY);
                    String pay = payWay.substring(0,indexOf);
                    String custody = payWay.substring(indexOf);
                    rentBaseDO.setPay(pay);
                    rentBaseDO.setCustody(custody);
                }
            }
            // 托出收支规则
            List<CommonCostRuleDO> pactReceiptPayList = new ArrayList<>();
            BeanUtils.copyListProperties(rentPactVO.getPactReceiptPayList(), pactReceiptPayList, CommonCostRuleDO.class);
            if(null!=pactReceiptPayList){
                for(CommonCostRuleDO commonCostRuleDO:pactReceiptPayList){
                    commonCostRuleDO.setPactCode(rentPactCode);
                    commonCostRuleDO.setPactType(PactTypeConsts.RENT);
                    commonCostRuleDO.setCostCode(costCode);
                    commonCostRuleDO.setCreateName(pin);
                    commonCostRuleDO.setIp(ip);
                }
            }
            // 收支
            List<FinanceReceiptPayDO> receiptPayList = new ArrayList<>();
            BeanUtils.copyListProperties(rentPactVO.getReceiptPayList(), receiptPayList, FinanceReceiptPayDO.class);
            if(null!= receiptPayList){
                for (FinanceReceiptPayDO financeReceiptPayDO : receiptPayList) {
                    financeReceiptPayDO.setPactCode(rentPactCode);
                    financeReceiptPayDO.setPactType(PactTypeConsts.RENT);
                    financeReceiptPayDO.setCreateName(pin);
                    financeReceiptPayDO.setIp(ip);
                    if(null != financeReceiptPayDO.getType() && 0 == financeReceiptPayDO.getType()){// 应收
                        financeReceiptPayDO.setCostState(1);// 待收款
                    }else if(null != financeReceiptPayDO.getType() && 2 == financeReceiptPayDO.getType()){// 应支
                        financeReceiptPayDO.setCostState(4);// 待付款
                    }
                    financeReceiptPayDO.setHouseCode(houseCode);
                    financeReceiptPayDO.setRoomCode(roomCode);
                    financeReceiptPayDO.setAddress(propertyAddress);
                }
            }
            // 合同图片
            List<CommonPicDO> commonPicList = new ArrayList<>();
            BeanUtils.copyListProperties(rentPactVO.getUrlList(), commonPicList, CommonPicDO.class);
            
            if(null != commonPicList){
                for (CommonPicDO commonPicDO : commonPicList) {
                    commonPicDO.setIp(ip);
                    commonPicDO.setCreateName(pin);
                    commonPicDO.setPactCode(rentPactCode);
                    commonPicDO.setPactType(PactTypeConsts.RENT);
                }
            }
            
            MeterReadGoodsDO meterReadGoodsDO = new MeterReadGoodsDO();
            BeanUtils.copyProperties(meterReadGoodsVO, meterReadGoodsDO);
            // 合同抄表
            List<CommonMeterReadDO> commonMeterReadList = meterReadGoodsDO.getMeterReadList();
            if(null != commonMeterReadList){
                for (CommonMeterReadDO commonMeterReadDO : commonMeterReadList) {
                    commonMeterReadDO.setIp(ip);
                    commonMeterReadDO.setCreateName(pin);
                    commonMeterReadDO.setPactCode(rentPactCode);
                    commonMeterReadDO.setPactType(PactTypeConsts.RENT);
                    if(null != commonMeterReadDO.getCommonMeterReadPicList()){
                        for (CommonMeterReadPicDO commonMeterReadPicDO : commonMeterReadDO.getCommonMeterReadPicList()) {
                            commonMeterReadPicDO.setIp(ip);
                            commonMeterReadPicDO.setCreateName(pin);
                        }
                    }
                }
            }
            // 合同物品
            List<CommonGoodsDO> commonGoodsList = meterReadGoodsDO.getGoodsList();
            if(null != commonGoodsList){
                for (CommonGoodsDO commonGoodsDO : commonGoodsList) {
                    commonGoodsDO.setIp(ip);
                    commonGoodsDO.setCreateName(pin);
                    commonGoodsDO.setPactCode(rentPactCode);
                    commonGoodsDO.setPactType(PactTypeConsts.RENT);
                    if(null != commonGoodsDO.getCommonGoodsPicList()){
                        for (CommonGoodsPicDO commonGoodsPicDO : commonGoodsDO.getCommonGoodsPicList()) {
                            commonGoodsPicDO.setIp(ip);
                            commonGoodsPicDO.setCreateName(pin);
                        }
                    }
                }
            }
            // 合同责任人
            List<CommonBelongerDO> commonBelongerDOList = new ArrayList<>();
            // 需要根据用户pin通过rpc查询用户信息
            ApiResult<PersonalResult> personalResult = personalRpc.getPersonalResultBypin(rentBaseDO.getDealPin());
            PersonalResult personalResultData = personalResult.getData();
            CommonBelongerDO commonBelongerDO = new CommonBelongerDO();
            commonBelongerDO.setPactCode(rentPactCode);
            commonBelongerDO.setPactType(PactTypeConsts.RENT);
            commonBelongerDO.setUserPin(personalResultData.getPin());
            commonBelongerDO.setUserName(personalResultData.getEmployeeName());
            commonBelongerDO.setUserTel(personalResultData.getEmployeeTel());
            commonBelongerDO.setUserRole(StringConsts.RENT_USER_ROLE);
            commonBelongerDO.setDeptName(personalResultData.getDeptName());
            commonBelongerDO.setDeptCode(personalResultData.getDeptCode());
            commonBelongerDO.setIp(ip);
            commonBelongerDO.setCreateName(pin);
            commonBelongerDOList.add(commonBelongerDO);
            /*************托出合同      end******************/
            boolean flag = rentBaseManager.saveRentBase(rentRoomDO, rentCustomerDO,cohabitantList, rentBaseDO, pactReceiptPayList,
                    receiptPayList, commonPicList,commonMeterReadList,commonGoodsList,commonBelongerDOList);
            if(flag){
                String [] delRedisKey = new String []{roomRedisKey.toString(),
                        renterRedisKey.toString(),pactRedisKey.toString(),
                        propertyRedisKey.toString()}; 
                commonManager.deleteRedis(delRedisKey);
                /*---------发一个操作日志  ----start------*/
                Map<String, Object> map = new HashMap<String, Object>();
                map.put(NormalConstant.SERVICETYPE, SerializeTypeConts.RENT);
                map.put(NormalConstant.SERVICECODE, rentBaseDO.getRentPactCode());
                map.put(NormalConstant.OPERATETYPE, StringConsts.ADD);
                map.put(NormalConstant.OPERATECONTENT, StringConsts.ADD_DATA);
                map.put(NormalConstant.OPERATEPIN, rentBaseDO.getCreateName());
                operateLogRpc.saveOperateLog(map);
                /*---------发一个操作日志  ----end------*/
                result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                result.setData(rentPactCode);
            }else{
                result.setCode(PactCommonEnum.RENT_SAVE_FAIL.getCode());
                result.setMessage(PactCommonEnum.RENT_SAVE_FAIL.getMessage());
            }
        } catch (PactManagerExcepion e) {
            LOG.error(CommonEnum.SYSTEM_EXCEPTION.getMessage(), e);
            result.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            result.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        }catch (Exception e) {
            LOG.error(CommonEnum.SYSTEM_EXCEPTION.getMessage(), e);
            result.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            result.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        }
        return result;
    }
    
    @Override
    public ApiResult<PageBean<RentBaseResult>> listRentBase(RentBaseParam param) {
        ApiResult<PageBean<RentBaseResult>> result = new ApiResult<>();
        try {
            if(null == param){
                result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
                result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
                return result;
            }
            if(StringUtil.isNotBlank(param.getExpireStartTime())
                    && StringUtil.isNotBlank(param.getExpireEndTime()) ){
                param.setExpireStartTime(param.getExpireStartTime() + " 00:00:00");
                param.setExpireEndTime(param.getExpireEndTime()+ " 23:59:59");
            }
            RentBaseQuery rentBaseQuery = new RentBaseQuery();
            BeanUtils.copyProperties(param, rentBaseQuery);
            ApiResult<PersonalResult> personalApiResult = personalRpc.getPersonalResultBypin(param.getCreateName());
            PersonalResult personalResult = personalApiResult.getData();
            String position = personalResult.getPosition();
            if(StringConsts.BELONGER_SERVICE.equals(position)){
                rentBaseQuery.setPosition(1);
            }else{
                rentBaseQuery.setPosition(2);
            }
            Integer scope = param.getScope();
            if(1 == scope){// 1=本人
                rentBaseQuery.setUserPin(param.getCreateName());
            }else if(2 == scope){// 2=本部门
                rentBaseQuery.setDeptCode(personalResult.getDeptCode());
            }else if(3 == scope){//3=本人及下属
                List<String> pinList = personalRpc.listLowerByPin(param.getCreateName());
                rentBaseQuery.setPinList(pinList);
            }else if(4 == scope){//4=本部门及下属部门
                List<String> deptList = deptRpc.listCodeByDeptCode(personalResult.getDeptCode());
                rentBaseQuery.setDeptList(deptList);
            }
            PageBean<RentBaseVO> pageBean = rentBaseManager.listParam(rentBaseQuery);
            PageBean<RentBaseResult> page = new PageTableBean<>(rentBaseQuery.getPageIndex(), rentBaseQuery.getPageSize());
            page.setTotalItem(pageBean.getTotalItem());
            BeanUtils.copyListProperties(pageBean.getData(), page.getData(), RentBaseResult.class);
            result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
            result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
            result.setData(page);
        } catch(PactManagerExcepion e){
            LOG.error(e.getMessage(), e);
            result.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            result.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            result.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            result.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        }
        return result;
    }
    @SuppressWarnings("unused")
    @Override
    public ApiResult<RentPactResult> getRentBase(RentBaseParam rentBaseParam) {
        ApiResult<RentPactResult> result = new ApiResult<>(); 
        String rentPactCode = rentBaseParam.getRentPactCode();
        try {
            if(StringUtil.isBlank(rentPactCode)){
                result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
                result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
                return result;
            }
            RentPactResult pactResult = new RentPactResult();
            boolean permissionsFlag = getPermissions(rentBaseParam);
            if(!permissionsFlag){
                result.setCode(PactCommonEnum.RENTBASE_NO_PERMISSIONS.getCode());
                result.setMessage(PactCommonEnum.RENTBASE_NO_PERMISSIONS.getMessage());
                return result;
            }
            FinanceReceiptPayQuery financeReceiptPayQuery = new FinanceReceiptPayQuery();
            financeReceiptPayQuery.setPactCode(rentPactCode);
            RentPactVO rentPactVO = rentBaseManager.getRentBaseByCode(rentPactCode,financeReceiptPayQuery);
            List<FinanceReceiptPayVO> recPayList = financeReceiptPayManager.listFinanceReceiptPayByPactCode(financeReceiptPayQuery);
            BeanUtils.copyProperties(rentPactVO, pactResult);
            RentBaseResult rentBaseResult = new RentBaseResult();
            BeanUtils.copyProperties(rentPactVO.getPact(), rentBaseResult);
            pactResult.setPact(rentBaseResult);
            List<FinanceReceiptPayResult> financeReceiptPayResults = new ArrayList<>();
            BeanUtils.copyListProperties(recPayList, financeReceiptPayResults, FinanceReceiptPayResult.class);
            pactResult.setReceiptPayList(financeReceiptPayResults);
            List<CommonCostRuleResult> pactReceiptPayList = new ArrayList<>();
            if(null!=rentPactVO.getPactReceiptPayList()&&rentPactVO.getPactReceiptPayList().size()>0){
                BeanUtils.copyListProperties(rentPactVO.getPactReceiptPayList(), pactReceiptPayList, CommonCostRuleResult.class);
            }
            pactResult.setPactReceiptPayList(pactReceiptPayList);
            List<CommonPicResult> commonPicResults = new ArrayList<>();
            BeanUtils.copyListProperties(rentPactVO.getUrlList(), commonPicResults, CommonPicResult.class);
            pactResult.setUrlList(commonPicResults);
            RentCustomerVO rentCustomerVO = rentCustomerManager.getRentCustomerByPactCode(rentPactCode);
            CustomerOwnerVO customerOwnerVO = new CustomerOwnerVO();
            RentCustomerResult rentCustomerResult = new RentCustomerResult();
            if(null!= rentCustomerVO){
                List<RentCommonOwnerVO> rentCommonOwnerVOs = commonOwnerManager.listCommonOwnerByRenterCode(rentCustomerVO.getRenterCode());
                customerOwnerVO.setRenter(rentCustomerVO);
                customerOwnerVO.setCohabitantList(rentCommonOwnerVOs);
            } 
            BeanUtils.copyProperties(customerOwnerVO, rentCustomerResult);
            RenterResult renterResult = new RenterResult();
            BeanUtils.copyProperties(customerOwnerVO.getRenter(), renterResult);
            rentCustomerResult.setRenter(renterResult);
            List<CommonOwnerResult> cohabitantList  =new ArrayList<>();
            if(null!=customerOwnerVO.getCohabitantList()&&customerOwnerVO.getCohabitantList().size()>0){
                BeanUtils.copyListProperties(customerOwnerVO.getCohabitantList(), cohabitantList, CommonOwnerResult.class);
            }
            rentCustomerResult.setCohabitantList(cohabitantList);
            pactResult.setRentCustomerResult(rentCustomerResult);

            RentRoomVO rentRoomVO = rentRoomManager.getRommByRentCode(rentPactCode);
            RoomResult roomResult = new RoomResult();
            BeanUtils.copyProperties(rentRoomVO, roomResult);
            pactResult.setRoomResult(roomResult);
            if(null!=pactResult){
                result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                result.setData(pactResult);
            }else{
                result.setCode(PactCommonEnum.RENT_BASE_QUERY_ERROR.getCode());
                result.setMessage(PactCommonEnum.RENT_BASE_QUERY_ERROR.getMessage());
            }
            return result;
        } catch (PactManagerExcepion e){
            LOG.error(e);
            result.setCode(PactCommonEnum.RENT_BASE_QUERY_ERROR.getCode());
            result.setMessage(PactCommonEnum.RENT_BASE_QUERY_ERROR.getMessage());
        } catch (Exception e) {
            LOG.error(e);
            result.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            result.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        }
        return result;
    }
    /**
    * @Title: getPermissions
    * @Description: TODO( 查看登录人有没有这个托出合同的权限 )
    * @author GuoXiaoPeng
    * @param rentBaseParam 托出合同编码和权限作用域
    * @return 有权限返回true，没有权限返回false
     */
    private boolean getPermissions(RentBaseParam param) {
        boolean flag;
        ApiResult<PersonalResult> personalApiResult = personalRpc.getPersonalResultBypin(param.getCreateName());
        PersonalResult loginPersonal = personalApiResult.getData();
        String position = loginPersonal.getPosition();
        Integer scope = param.getScope();
        RentBaseQuery query = new RentBaseQuery();
        query.setScope(scope);
        if(StringConsts.BELONGER_SERVICE.equals(position)){
            query.setPosition(1);
        }else {
            query.setPosition(2);
        }    
        if(1 == scope){// 1=本人
            query.setUserPin(param.getCreateName());
        }else if(2 == scope){// 2=本部门
            query.setDeptCode(loginPersonal.getDeptCode());
        }else if(3 == scope){//3=本人及下属
            List<String> pinList = personalRpc.listLowerByPin(param.getCreateName());
            query.setPinList(pinList);
        }else if(4 == scope){//4=本部门及下属部门
            List<String> deptList = deptRpc.listCodeByDeptCode(loginPersonal.getDeptCode());
            query.setDeptList(deptList);
        }
        query.setRentPactCode(param.getRentPactCode());
        flag = rentBaseManager.getPermissions(query);
        return flag;
    }
    @SuppressWarnings("rawtypes")
    @Override
    public ApiResult auditRentBase(RentBaseParam param) {
        ApiResult result = new ApiResult(); 
        try {
            if(null==param){
                result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
                result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
                return result;
            }
            if(StringUtil.isBlank(param.getRentPactCode())){
                result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
                result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
                return result;
            }
            RentPactVO rentPactVO = rentBaseManager.getRentBaseByCode(param.getRentPactCode(), null);
            if(null!=rentPactVO){
                RentBaseVO pact = rentPactVO.getPact();
                if(null!=pact && null!=pact.getState()){
                    if( pact.getState()==1 ||  pact.getState()==4){
                        RentBaseDO rentBaseDO = new RentBaseDO();
                        BeanUtils.copyProperties(param, rentBaseDO);
                        rentBaseDO.setModifiedName(param.getCreateName());
                        rentBaseDO.setIp(param.getIp());
                        rentBaseDO.setState(2);
                        boolean flag = rentBaseManager.updateRentBaseStateByCode(rentBaseDO, pact);
                        if(flag){
                            Map<String, Object> map = new HashMap<String, Object>();
                            map.put(NormalConstant.SERVICETYPE, SerializeTypeConts.RENT);
                            map.put(NormalConstant.SERVICECODE, rentBaseDO.getRentPactCode());
                            map.put(NormalConstant.OPERATETYPE, StringConsts.OPERATETYPE_APPLICATION_AUDIT);
                            map.put(NormalConstant.OPERATECONTENT, StringConsts.AUDIT_DATA);
                            map.put(NormalConstant.OPERATEPIN, rentBaseDO.getCreateName());
                            operateLogRpc.saveOperateLog(map);
                            /*************************首页待办数据统计  start*****************************/
                            CommonBelongerQuery commonBelongerQuery = new CommonBelongerQuery();
                            commonBelongerQuery.setPactCode(param.getRentPactCode());
                            commonBelongerQuery.setUserRole(StringConsts.BELONGER_DEAL);
                            CommonBelongerVO commonBelongerVO = commonBelongerManager.getBelongersByParam(commonBelongerQuery);
                            AgendaDO agendaDO = new AgendaDO();
                            agendaDO.setServiceCode(param.getRentPactCode());
                            agendaDO.setType(StringConsts.STATISTIC_PENDING_RENT);
                            agendaDO.setIsDo(0);
                            agendaDO.setPin(commonBelongerVO.getUserPin());
                            agendaDO.setName(commonBelongerVO.getUserName());
                            agendaDO.setDeptCode(commonBelongerVO.getDeptCode());
                            agendaDO.setDeptName(commonBelongerVO.getDeptName());
                            agendaDO.setDate(new Date());
                            statisticsManager.saveBacklogStatistics(agendaDO);
                            /*************************首页待办数据统计  end*****************************/
                            result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                            result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                        }else{
                            result.setCode(PactCommonEnum.RENT_BASE_AUDIT_ERROR.getCode());
                            result.setMessage(PactCommonEnum.RENT_BASE_AUDIT_ERROR.getMessage());
                        }
                    }else{
                        result.setCode(PactCommonEnum.PACT_AUDIT_STATE_ERROR.getCode());
                        result.setMessage(PactCommonEnum.PACT_AUDIT_STATE_ERROR.getMessage());
                    }
                }else{
                    result.setCode(PactCommonEnum.RENT_BASE_QUERY_ERROR.getCode());
                    result.setMessage(PactCommonEnum.RENT_BASE_QUERY_ERROR.getMessage());
                }
            }else{
                result.setCode(PactCommonEnum.RENT_BASE_QUERY_ERROR.getCode());
                result.setMessage(PactCommonEnum.RENT_BASE_QUERY_ERROR.getMessage());
            }
            return result;
        } catch (PactManagerExcepion e){
            LOG.error(e);
            result.setCode(PactCommonEnum.RENT_BASE_AUDIT_ERROR.getCode());
            result.setMessage(PactCommonEnum.RENT_BASE_AUDIT_ERROR.getMessage());
        } catch (Exception e) {
            LOG.error(e);
            result.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            result.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        }
        return result;
    }

    @SuppressWarnings({ "rawtypes", "unused" })
    @Override
    public ApiResult reviewPassRentBase(RentBaseParam param) {
        ApiResult result = new ApiResult(); 
        try {
            if(null==param){
                result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
                result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
                return result;
            }
            if(StringUtil.isBlank(param.getRentPactCode())){
                result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
                result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
                return result;
            }
            RentPactVO rentPactVO = rentBaseManager.getRentBaseByCode(param.getRentPactCode(), null);
            RentBaseVO rentBaseVO = rentPactVO.getPact();
            if(null!=rentPactVO){
                RentBaseVO pact = rentPactVO.getPact();
                if(null!=pact && null!=pact.getState()&& pact.getState()==2){
                    RentBaseDO rentBaseDO = new RentBaseDO();
                    BeanUtils.copyProperties(param, rentBaseDO);
                    rentBaseDO.setState(3);
                    rentBaseDO.setModifiedName(param.getCreateName());
                    rentBaseDO.setIp(param.getIp());
                    rentBaseDO.setStartTime(rentBaseVO.getStartTime());
                    boolean flag = rentBaseManager.updateRentBaseStateByCode(rentBaseDO, pact);
                    if(flag){
                        List<String> variableList = new ArrayList<>();
                        variableList.add(param.getRentPactCode());
                        saveRemind(param,StringConsts.REVIEW_PASS_RENTBASE,variableList);
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put(NormalConstant.SERVICETYPE, SerializeTypeConts.RENT);
                        map.put(NormalConstant.SERVICECODE, rentBaseDO.getRentPactCode());
                        map.put(NormalConstant.OPERATETYPE, StringConsts.OPERATETYPE_REVIEW_PASS);
                        map.put(NormalConstant.OPERATECONTENT, StringConsts.REVIEWPASS_DATA);
                        map.put(NormalConstant.OPERATEPIN, rentBaseDO.getCreateName());
                        operateLogRpc.saveOperateLog(map);
                        /****************************保存交易统计   start**********************************/
                        RentRoomVO rentRoomVO = rentRoomManager.getRommByRentCode(param.getRentPactCode());
                        com.young.product.api.domain.result.rpc.room.RoomResult roomRpcBase = roomRpc.getRoomByCode(rentRoomVO.getRoomCode());
                        if(1 == rentBaseVO.getDealState()){
                            if(StringConsts.WHOLE_RENT.equals(roomRpcBase.getRentWay())){
                                for(int i = 0;i < Integer.valueOf(roomRpcBase.getRoom()); i++){
                                    StatisticDO statisticDO = new StatisticDO();
                                    statisticDO.setServiceCode(param.getRentPactCode());
                                    statisticDO.setPactType(StringConsts.NEW_OUT_COUNT);
                                    statisticDO.setPin(rentBaseVO.getDealPin());
                                    statisticDO.setName(rentBaseVO.getDealName());
                                    statisticDO.setDeptCode(rentBaseVO.getDealDeptCode());
                                    statisticDO.setDeptName(rentBaseVO.getDealDeptName());
                                    statisticsManager.savePactRoomStatistics(statisticDO);
                                }
                            }else if(StringConsts.JOINT_RENT.equals(roomRpcBase.getRentWay())){
                                StatisticDO statisticDO = new StatisticDO();
                                statisticDO.setServiceCode(param.getRentPactCode());
                                statisticDO.setPactType(StringConsts.NEW_OUT_COUNT);
                                statisticDO.setPin(rentBaseVO.getDealPin());
                                statisticDO.setName(rentBaseVO.getDealName());
                                statisticDO.setDeptCode(rentBaseVO.getDealDeptCode());
                                statisticDO.setDeptName(rentBaseVO.getDealDeptName());
                                statisticsManager.savePactRoomStatistics(statisticDO);
                            }
                        }
                        /****************************保存交易统计   end**********************************/
                        /******************** 修改待办事务统计表的状态  start *******************************/
                        AgendaDO agendaDO = new AgendaDO();
                        agendaDO.setServiceCode(param.getRentPactCode());
                        agendaDO.setIsDo(1);
                        agendaDO.setType(StringConsts.STATISTIC_PENDING_RENT);
                        agendaDO.setModifiedName(param.getCreateName());
                        agendaDO.setIp(param.getIp());
                        statisticsManager.updatBacklogStatisticsIsDo(agendaDO);
                        /******************** 修改待办事务统计表的状态  end *******************************/
                        CommonBelongerVO afterRentBelongerVO = rentBaseManager.getRentAfterBelonger(param.getRentPactCode());
                        saveReceivablesStatistics(param ,afterRentBelongerVO);
                        saveObligationStatistics(param ,afterRentBelongerVO);
                        result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                        result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                    }else{
                        result.setCode(PactCommonEnum.RENT_BASE_REVIEWPASS_ERROR.getCode());
                        result.setMessage(PactCommonEnum.RENT_BASE_REVIEWPASS_ERROR.getMessage());
                    }
                }else{
                    result.setCode(PactCommonEnum.PACT_REVIEWPASS_STATE_ERROR.getCode());
                    result.setMessage(PactCommonEnum.PACT_REVIEWPASS_STATE_ERROR.getMessage());
                }
            }else{
                result.setCode(PactCommonEnum.RENT_BASE_QUERY_ERROR.getCode());
                result.setMessage(PactCommonEnum.RENT_BASE_QUERY_ERROR.getMessage());
            }
        } catch (PactManagerExcepion e){
            LOG.error(e);
            result.setCode(PactCommonEnum.RENT_BASE_REVIEWPASS_ERROR.getCode());
            result.setMessage(PactCommonEnum.RENT_BASE_REVIEWPASS_ERROR.getMessage());
        } catch (Exception e) {
            LOG.error(e);
            result.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            result.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        }
        return result;
    }
    /**
     * 
    * @Title: saveReceivablesStatistics
    * @Description: TODO( 添加待办事务统计待收款统计记录 )
    * @author GuoXiaoPeng
    * @param param  托出合同编码
    * @param afterRentBelongerVO 租后管家
    * @throws 异常
     */
    private void saveReceivablesStatistics(RentBaseParam param, CommonBelongerVO afterRentBelongerVO) {
        FinanceReceiptPayQuery financeReceiptPayQuery = new FinanceReceiptPayQuery();
        financeReceiptPayQuery.setIsValid(1);
        financeReceiptPayQuery.setPactCode(param.getRentPactCode());
        financeReceiptPayQuery.setType(0);
        List<Integer> costList = new ArrayList<>();
        costList.add(1);
        costList.add(2);
        financeReceiptPayQuery.setCostList(costList);
        List<FinanceReceiptPayVO> financeReceiptPayVOs = financeReceiptPayManager.listFinanceReceiptPayByPactCode(financeReceiptPayQuery);
        if(null != financeReceiptPayVOs && financeReceiptPayVOs.size() > 0){
            for(FinanceReceiptPayVO recPay:financeReceiptPayVOs){
                AgendaDO agendaDO = new AgendaDO();
                agendaDO.setServiceCode(String.valueOf(recPay.getId()));
                agendaDO.setType(StringConsts.STATISTIC_PENDING_RECEIVABLES);
                agendaDO.setIsDo(0);
                agendaDO.setPin(afterRentBelongerVO.getUserPin());
                agendaDO.setName(afterRentBelongerVO.getUserName());
                agendaDO.setDeptCode(afterRentBelongerVO.getDeptCode());
                agendaDO.setDeptName(afterRentBelongerVO.getDeptName());
                agendaDO.setDate(new Date());
                statisticsManager.saveBacklogStatistics(agendaDO);
            }
        }
    }
    /**
     * 
    * @Title: saveObligationStatistics
    * @Description: TODO( 添加待办事务统计待付款统计记录 )
    * @author GuoXiaoPeng
    * @param param 托出合同编码
    * @param afterRentBelongerVO 租后管家
    * @throws 异常
     */
    private void saveObligationStatistics(RentBaseParam param, CommonBelongerVO afterRentBelongerVO) {
        FinanceReceiptPayQuery financeReceiptPayQuery = new FinanceReceiptPayQuery();
        financeReceiptPayQuery.setIsValid(1);
        financeReceiptPayQuery.setPactCode(param.getRentPactCode());
        financeReceiptPayQuery.setType(2);
        List<Integer> costList = new ArrayList<>();
        costList.add(4);
        costList.add(5);
        financeReceiptPayQuery.setCostList(costList);
        List<FinanceReceiptPayVO> financeReceiptPayVOs = financeReceiptPayManager.listFinanceReceiptPayByPactCode(financeReceiptPayQuery);
        if(null != financeReceiptPayVOs && financeReceiptPayVOs.size() > 0){
            for(FinanceReceiptPayVO recPay:financeReceiptPayVOs){
                AgendaDO agendaDO = new AgendaDO();
                agendaDO.setServiceCode(String.valueOf(recPay.getId()));
                agendaDO.setType(StringConsts.STATISTIC_PENDING_PENDING_PAYMENT);
                agendaDO.setIsDo(0);
                agendaDO.setPin(afterRentBelongerVO.getUserPin());
                agendaDO.setName(afterRentBelongerVO.getUserName());
                agendaDO.setDeptCode(afterRentBelongerVO.getDeptCode());
                agendaDO.setDeptName(afterRentBelongerVO.getDeptName());
                agendaDO.setDate(new Date());
                statisticsManager.saveBacklogStatistics(agendaDO);
            }
        }
    }
    @SuppressWarnings("rawtypes")
    @Override
    public ApiResult reviewDismissalRentBase(RentBaseParam param) {
        ApiResult result = new ApiResult(); 
        try {
            if(null==param){
                result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
                result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
                return result;
            }
            if(StringUtil.isBlank(param.getRentPactCode())){
                result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
                result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
                return result;
            }
            RentPactVO rentPactVO = rentBaseManager.getRentBaseByCode(param.getRentPactCode(), null);
            if(null!=rentPactVO){
                RentBaseVO pact = rentPactVO.getPact();
                if(null!=pact && null!=pact.getState()&& pact.getState()==2){
                    RentBaseDO rentBaseDO = new RentBaseDO();
                    BeanUtils.copyProperties(param, rentBaseDO);
                    rentBaseDO.setState(4);
                    rentBaseDO.setModifiedName(param.getCreateName());
                    rentBaseDO.setIp(param.getIp());
                    boolean flag = rentBaseManager.updateRentBaseStateByCode(rentBaseDO,pact);
                    if(flag){
                        List<String> variableList = new ArrayList<>();
                        variableList.add(param.getRentPactCode());
                        saveRemind(param,StringConsts.REVIEW_DISMISSAL_RENTBASE,variableList);
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put(NormalConstant.SERVICETYPE, SerializeTypeConts.RENT);
                        map.put(NormalConstant.SERVICECODE, rentBaseDO.getRentPactCode());
                        map.put(NormalConstant.OPERATETYPE, StringConsts.OPERATETYPE_REVIEW_DISMISSAL);
                        map.put(NormalConstant.OPERATECONTENT, param.getReason());
                        map.put(NormalConstant.OPERATEPIN, rentBaseDO.getCreateName());
                        operateLogRpc.saveOperateLog(map);
                        /******************** 修改待办事务统计表的状态  start *******************************/
                        AgendaDO agendaDO = new AgendaDO();
                        agendaDO.setServiceCode(param.getRentPactCode());
                        agendaDO.setIsDo(1);
                        agendaDO.setType(StringConsts.STATISTIC_PENDING_RENT);
                        agendaDO.setModifiedName(param.getCreateName());
                        agendaDO.setIp(param.getIp());
                        statisticsManager.updatBacklogStatisticsIsDo(agendaDO);
                        /******************** 修改待办事务统计表的状态  end *******************************/
                        result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                        result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                    }else{
                        result.setCode(PactCommonEnum.RENT_BASE_REVIEWDISMISSAL_ERROR.getCode());
                        result.setMessage(PactCommonEnum.RENT_BASE_REVIEWDISMISSAL_ERROR.getMessage());
                    }
                }else{
                    result.setCode(PactCommonEnum.PACT_REVIEW_DISMISSAL_STATE_ERROR.getCode());
                    result.setMessage(PactCommonEnum.PACT_REVIEW_DISMISSAL_STATE_ERROR.getMessage());
                }
            }else{
                result.setCode(PactCommonEnum.RENT_BASE_QUERY_ERROR.getCode());
                result.setMessage(PactCommonEnum.RENT_BASE_QUERY_ERROR.getMessage());
            }
        } catch (PactManagerExcepion e){
            LOG.error(e);
            result.setCode(PactCommonEnum.RENT_BASE_REVIEWDISMISSAL_ERROR.getCode());
            result.setMessage(PactCommonEnum.RENT_BASE_REVIEWDISMISSAL_ERROR.getMessage());
        } catch (Exception e) {
            LOG.error(e);
            result.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            result.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        }
        return result;
    }

    @Override
    public ApiResult<Boolean> updateRentBase(RentPactParam pactParam) {
        ApiResult<Boolean> result = new ApiResult<Boolean>(); 
        try {
            if(null==pactParam ){
                result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
                result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
                return result;
            }
            String ip = pactParam.getIp() ==null ? "" :pactParam.getIp();
            String pin = pactParam.getModifiedName()== null?"":pactParam.getModifiedName();
            //合同信息
            RentBaseDO rentBaseDO = new RentBaseDO();
            BeanUtils.copyProperties(pactParam.getPact(),rentBaseDO);
            rentBaseDO.setModifiedName(pin);
            rentBaseDO.setIp(ip);
            String payWay = rentBaseDO.getPayWay();
            if(StringUtil.isNotBlank(payWay)){
                if(StringConsts.MONTHLY_PAYMENT.equals(payWay)){
                    rentBaseDO.setPay(payWay);
                }else{
                    int indexOf = payWay.indexOf(StringConsts.PAY);
                    String pay = payWay.substring(0,indexOf);
                    String custody = payWay.substring(indexOf);
                    rentBaseDO.setPay(pay);
                    rentBaseDO.setCustody(custody);
                }
            }
            if(null==rentBaseDO||StringUtil.isBlank(rentBaseDO.getRentPactCode())){
                result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
                result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
                return result;
            }
            RentPactVO rentPactVO = rentBaseManager.getRentBaseByCode(rentBaseDO.getRentPactCode(),null);
            if(null!=rentPactVO){
                RentBaseVO pact = rentPactVO.getPact();
                if(null!=pact && null!=pact.getState()){
                    if( pact.getState()==1 ||  pact.getState()==4){
                        // 收支规则
                        List<CommonCostRuleDO> commonCostRuleDOList = new ArrayList<>();
                        if(null!=pactParam.getPactReceiptPayList()){
                            BeanUtils.copyListProperties(pactParam.getPactReceiptPayList(), commonCostRuleDOList, CommonCostRuleDO.class);
                        }
                        for (CommonCostRuleDO commonCostRuleDO : commonCostRuleDOList) {
                            commonCostRuleDO.setIp(ip);
                            commonCostRuleDO.setCreateName(pin);
                            commonCostRuleDO.setModifiedName(pin);
                            commonCostRuleDO.setPactCode(rentBaseDO.getRentPactCode());
                            commonCostRuleDO.setPactType(PactTypeConsts.RENT);
                        }
                        //收支(集合)**/
                        List<FinanceReceiptPayDO> financeReceiptPayDOs = new ArrayList<>();
                        if(null!=pactParam.getReceiptPayList()){
                            BeanUtils.copyListProperties(pactParam.getReceiptPayList(), financeReceiptPayDOs, FinanceReceiptPayDO.class);
                        }
                        for (FinanceReceiptPayDO financeReceiptPayDO : financeReceiptPayDOs) {
                            financeReceiptPayDO.setPactCode(rentBaseDO.getRentPactCode());
                            financeReceiptPayDO.setPactType(PactTypeConsts.RENT);
                            financeReceiptPayDO.setCreateName(pin);
                            financeReceiptPayDO.setModifiedName(pin);
                            financeReceiptPayDO.setIp(ip);
                            if(null != financeReceiptPayDO.getType() && 0 == financeReceiptPayDO.getType()){// 应收
                                financeReceiptPayDO.setCostState(1);// 待收款
                            }else if(null != financeReceiptPayDO.getType() && 2 == financeReceiptPayDO.getType()){// 应支
                                financeReceiptPayDO.setCostState(4);// 待付款
                            }
                        }
                        //合同照片(集合)**/
                        List<CommonPicDO> commonPicDOs = new ArrayList<>();
                        if(null!=pactParam.getUrlList()){
                            BeanUtils.copyListProperties(pactParam.getUrlList(), commonPicDOs, CommonPicDO.class);
                        }
                        for (CommonPicDO commonPicDO : commonPicDOs) {
                            commonPicDO.setIp(ip);
                            commonPicDO.setCreateName(pin);
                            commonPicDO.setModifiedName(pin);
                            commonPicDO.setPactCode(rentBaseDO.getRentPactCode());
                            commonPicDO.setPactType(PactTypeConsts.RENT);
                        }
                        // 需要根据签约管家pin通过rpc查询用户信息
                        ApiResult<PersonalResult> personalResult = personalRpc.getPersonalResultBypin(rentBaseDO.getDealPin());
                        PersonalResult personalResultData = personalResult.getData();
                        CommonBelongerDO commonBelongerDO = new CommonBelongerDO();
                        List<CommonBelongerVO> commonBelongerList = commonBelongerManager.listCommonBelonger(rentBaseDO.getRentPactCode());
                        if(null != commonBelongerList){
                            for (CommonBelongerVO commonBelongerVO : commonBelongerList) {
                                if(StringConsts.BELONGER_DEAL.equals(commonBelongerVO.getUserRole())){
                                    commonBelongerDO.setId(commonBelongerVO.getId());
                                }
                            }
                        }
                        commonBelongerDO.setPactCode(rentBaseDO.getRentPactCode());
                        commonBelongerDO.setUserPin(rentBaseDO.getDealPin());
                        commonBelongerDO.setUserName(personalResultData.getEmployeeName());
                        commonBelongerDO.setUserTel(personalResultData.getEmployeeTel());
                        commonBelongerDO.setUserRole(StringConsts.BELONGER_DEAL);
                        commonBelongerDO.setDeptCode(personalResultData.getDeptCode());
                        commonBelongerDO.setDeptName(personalResultData.getDeptName());
                        commonBelongerDO.setIp(ip);
                        commonBelongerDO.setCreateName(pin);
                        commonBelongerDO.setModifiedName(pin);
                        boolean flag = rentBaseManager.updateRentBaseByCode(rentBaseDO,commonCostRuleDOList,
                                financeReceiptPayDOs,commonPicDOs,commonBelongerDO);
                        if(flag){
                            Map<String, Object> map = new HashMap<String, Object>();
                            map.put(NormalConstant.SERVICETYPE, SerializeTypeConts.RENT);
                            map.put(NormalConstant.SERVICECODE, rentBaseDO.getRentPactCode());
                            map.put(NormalConstant.OPERATETYPE, StringConsts.UPDATE);
                            map.put(NormalConstant.OPERATECONTENT, StringConsts.UPDATE_DATA);
                            map.put(NormalConstant.OPERATEPIN, rentBaseDO.getModifiedName());
                            operateLogRpc.saveOperateLog(map);
                            result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                            result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                        }else{
                            result.setCode(PactCommonEnum.RENT_BASE_UPDATE_ERROR.getCode());
                            result.setMessage(PactCommonEnum.RENT_BASE_UPDATE_ERROR.getMessage());
                        }
                    }else{
                        result.setCode(PactCommonEnum.PACT_NOT_UPDATE_DELTER.getCode());
                        result.setMessage(PactCommonEnum.PACT_NOT_UPDATE_DELTER.getMessage());
                    }
                }else{
                    result.setCode(PactCommonEnum.RENT_BASE_QUERY_ERROR.getCode());
                    result.setMessage(PactCommonEnum.RENT_BASE_QUERY_ERROR.getMessage());
                }
            }else{
                result.setCode(PactCommonEnum.RENT_BASE_QUERY_ERROR.getCode());
                result.setMessage(PactCommonEnum.RENT_BASE_QUERY_ERROR.getMessage());
            }
        } catch (PactManagerExcepion e){
            LOG.error(e);
            result.setCode(PactCommonEnum.RENT_BASE_UPDATE_ERROR.getCode());
            result.setMessage(PactCommonEnum.RENT_BASE_UPDATE_ERROR.getMessage());
        } catch (Exception e) {
            LOG.error(e);
            result.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            result.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        }
        return result;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public ApiResult updateRentCustomer(RentCustomerParam rentCustomerParam) {
        ApiResult result = new ApiResult(); 
        try {
            if(null==rentCustomerParam){
                result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
                result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
                return result;
            }
            RentPactVO rentPactVO = rentBaseManager.getRentBaseByCode(rentCustomerParam.getRenter().getRentPactCode(), null);
            if(null!=rentPactVO){
                RentBaseVO pact = rentPactVO.getPact();
                if(null!=pact && null!=pact.getState()){
                    if( pact.getState()==1 ||  pact.getState()==4){
                        CustomerOwnerDO customerOwnerDO = new CustomerOwnerDO();
                        RentCustomerDO rentCustomerDO = new RentCustomerDO();
                        List<RentCommonOwnerDO> commonOwnerDOs= new ArrayList<>();
                        BeanUtils.copyProperties(rentCustomerParam.getRenter(), rentCustomerDO);
                        rentCustomerDO.setIp(rentCustomerParam.getIp());
                        rentCustomerDO.setModifiedName(rentCustomerParam.getCreateName()==null?"":rentCustomerParam.getCreateName());
                        if(null!=rentCustomerParam.getCohabitantList()&&rentCustomerParam.getCohabitantList().size()>0){
                            BeanUtils.copyListProperties(rentCustomerParam.getCohabitantList(), commonOwnerDOs, RentCommonOwnerDO.class);
                            for(RentCommonOwnerDO commonOwnerDO:commonOwnerDOs){
                                commonOwnerDO.setIp(rentCustomerParam.getIp());
                                commonOwnerDO.setModifiedName(rentCustomerParam.getCreateName()==null?"":rentCustomerParam.getCreateName());
                            }
                        }
                        customerOwnerDO.setRenter(rentCustomerDO);
                        customerOwnerDO.setCohabitantList(commonOwnerDOs);
                        boolean flag = rentBaseManager.updateRenterByCode(customerOwnerDO);
                        if(flag){
                            Map<String, Object> map = new HashMap<String, Object>();
                            map.put(NormalConstant.SERVICETYPE, SerializeTypeConts.RENTER);
                            map.put(NormalConstant.SERVICECODE, rentCustomerDO.getRenterCode());
                            map.put(NormalConstant.OPERATETYPE, StringConsts.OPERATETYPE_UPDATE_RENT_CUSTOMER);
                            map.put(NormalConstant.OPERATECONTENT, StringConsts.UPDATE_DATA);
                            map.put(NormalConstant.OPERATEPIN, rentCustomerDO.getModifiedName());
                            operateLogRpc.saveOperateLog(map);
                            result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                            result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                        }else{
                            result.setCode(PactCommonEnum.RENT_CUSTOMER_UPDATE_ERROR.getCode());
                            result.setMessage(PactCommonEnum.RENT_CUSTOMER_UPDATE_ERROR.getMessage());
                        }
                    }else{
                        result.setCode(PactCommonEnum.PACT_NOT_UPDATE_DELTER.getCode());
                        result.setMessage(PactCommonEnum.PACT_NOT_UPDATE_DELTER.getMessage());
                    }
                }else{
                    result.setCode(PactCommonEnum.RENT_BASE_QUERY_ERROR.getCode());
                    result.setMessage(PactCommonEnum.RENT_BASE_QUERY_ERROR.getMessage());
                }
            }else{
                result.setCode(PactCommonEnum.RENT_BASE_QUERY_ERROR.getCode());
                result.setMessage(PactCommonEnum.RENT_BASE_QUERY_ERROR.getMessage());
            }
        } catch (PactManagerExcepion e){
            LOG.error(e);
            result.setCode(PactCommonEnum.RENT_CUSTOMER_UPDATE_ERROR.getCode());
            result.setMessage(PactCommonEnum.RENT_CUSTOMER_UPDATE_ERROR.getMessage());
        } catch (Exception e) {
            LOG.error(e);
            result.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            result.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        }
        return result;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public ApiResult deleteRentBase(RentBaseParam param) {
        ApiResult result = new ApiResult(); 
        try {
             RentBaseDO rentBaseDO = new RentBaseDO();
             rentBaseDO.setModifiedName(param.getCreateName()==null?"":param.getCreateName());
             rentBaseDO.setIp(param.getIp());
             rentBaseDO.setRentPactCode(param.getRentPactCode());
             RentBaseVO rentBaseVO = rentBaseManager.getRentBaseByCode(param.getRentPactCode());
             if(null!=rentBaseVO){
                 if( rentBaseVO.getState()==1 ||  rentBaseVO.getState()==4){
                     boolean flag = rentBaseManager.deleteRentBaseByCode(rentBaseDO);
                     if(flag){
                         Map<String, Object> map = new HashMap<String, Object>();
                         map.put(NormalConstant.SERVICETYPE, SerializeTypeConts.RENT);
                         map.put(NormalConstant.SERVICECODE, rentBaseDO.getRentPactCode());
                         map.put(NormalConstant.OPERATETYPE, StringConsts.DELETE);
                         map.put(NormalConstant.OPERATECONTENT, StringConsts.DELETE_DATA);
                         map.put(NormalConstant.OPERATEPIN, rentBaseDO.getModifiedName());
                         operateLogRpc.saveOperateLog(map);
                         result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                         result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                     }else{
                         result.setCode(PactCommonEnum.RENT_BASE_DELETE_ERROR.getCode());
                         result.setMessage(PactCommonEnum.RENT_BASE_DELETE_ERROR.getMessage());
                     }
                 }else{
                     result.setCode(PactCommonEnum.PACT_NOT_UPDATE_DELTER.getCode());
                     result.setMessage(PactCommonEnum.PACT_NOT_UPDATE_DELTER.getMessage());
                 }
             }else{
                 result.setCode(PactCommonEnum.RENT_BASE_QUERY_ERROR.getCode());
                 result.setMessage(PactCommonEnum.RENT_BASE_QUERY_ERROR.getMessage());
             }
        } catch (PactManagerExcepion e){
            LOG.error(e);
            result.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            result.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        } catch (Exception e) {
            LOG.error(e);
            result.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            result.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        }
        return result;
    }
    
    @Override
    public ApiResult<RentBaseResult> getPropertyAddress(String rentPactCode) {
        ApiResult<RentBaseResult> result = new ApiResult<>();
        try {
            if(StringUtil.isBlank(rentPactCode)){
                result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
                result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
                return result;
            }
            RentRoomVO  rentRoomVO = rentRoomManager.getRommByRentCode(rentPactCode);
            if(null!=rentRoomVO){
                RentBaseResult rentBaseResult = new RentBaseResult();
                rentBaseResult.setPropertyAddress(rentRoomVO.getAddress());
                result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                result.setData(rentBaseResult);
            }else{
                result.setCode(PactCommonEnum.ROOM_PROPERTY_QUERY_ERROR.getCode());
                result.setMessage(PactCommonEnum.ROOM_PROPERTY_QUERY_ERROR.getMessage());
            }
        } catch (PactManagerExcepion e) {
            LOG.error(e);
            result.setCode(PactCommonEnum.ROOM_PROPERTY_QUERY_ERROR.getCode());
            result.setMessage(PactCommonEnum.ROOM_PROPERTY_QUERY_ERROR.getMessage());
        }catch (Exception e) {
            LOG.error(e);
            result.setCode(PactCommonEnum.ROOM_PROPERTY_QUERY_ERROR.getCode());
            result.setMessage(PactCommonEnum.ROOM_PROPERTY_QUERY_ERROR.getMessage());
        }
        return result;
    }

    @Override
    public ApiResult<FinanceReceiptPayResult> getPayeeByRentPactCode(String rentPactCode) {
        ApiResult<FinanceReceiptPayResult> result = new ApiResult<>();
        FinanceReceiptPayQuery query = new FinanceReceiptPayQuery();
        try {
            query.setPactCode(rentPactCode);
            query.setPayeeObject(StringConsts.RENTER);
            query.setPactType(StringConsts.GIVE_OUT);
            query.setType(2);
            FinanceReceiptPayVO financeReceiptPayVO = financeReceiptPayManager.getPayeeByRentPactCode(query);
            FinanceReceiptPayResult financeReceiptPayResult = new FinanceReceiptPayResult();
            BeanUtils.copyProperties(financeReceiptPayVO, financeReceiptPayResult);
            result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
            result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
            result.setData(financeReceiptPayResult);
        } catch (PactManagerExcepion e) {
            LOG.error(e);
            result.setCode(PactCommonEnum.RENTER_PAYEE_QUERY_ERROR.getCode());
            result.setMessage(PactCommonEnum.RENTER_PAYEE_QUERY_ERROR.getMessage());
        } catch (Exception e) {
            LOG.error(e);
            result.setCode(PactCommonEnum.RENTER_PAYEE_QUERY_ERROR.getCode());
            result.setMessage(PactCommonEnum.RENTER_PAYEE_QUERY_ERROR.getMessage());
        }
        return result;
    }
    
    @Override
    public ApiResult<Boolean> setDelayDate(RentBaseParam param) {
        ApiResult<Boolean> result = new ApiResult<>();
        if(null==param||StringUtil.isBlank(param.getRentPactCode())||null==param.getDelayDay()){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        try {
            
            RentBaseDO rentBaseDO = new RentBaseDO();
            BeanUtils.copyProperties(param, rentBaseDO);
            RentBaseVO rentBaseVO = rentBaseManager.getRentBaseByCode(param.getRentPactCode());
            
            Date endDate = DateUtil.getDayByDay(rentBaseVO.getEndTime(), param.getDelayDay());
            rentBaseDO.setEndTime(endDate);
            boolean flag = rentBaseManager.setDelayDate(rentBaseDO);
            if(flag){
                Map<String, Object> map = new HashMap<String, Object>();
                map.put(NormalConstant.SERVICETYPE, SerializeTypeConts.RENT);
                map.put(NormalConstant.SERVICECODE, rentBaseDO.getRentPactCode());
                map.put(NormalConstant.OPERATETYPE, StringConsts.UPDATE);
                map.put(NormalConstant.OPERATECONTENT, StringConsts.UPDATE_DATA);
                map.put(NormalConstant.OPERATEPIN, rentBaseDO.getModifiedName());
                operateLogRpc.saveOperateLog(map);
                result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                result.setData(flag);
            }else{
                result.setCode(PactCommonEnum.RENTBASE_SET_DELAYDATE_ERROR.getCode());
                result.setMessage(PactCommonEnum.RENTBASE_SET_DELAYDATE_ERROR.getMessage());
            }
            
        } catch (PactManagerExcepion e) {
            LOG.error(e);
            result.setCode(PactCommonEnum.RENTBASE_SET_DELAYDATE_ERROR.getCode());
            result.setMessage(PactCommonEnum.RENTBASE_SET_DELAYDATE_ERROR.getMessage());
        } catch (Exception e) {
            LOG.error(e);
            result.setCode(PactCommonEnum.RENTBASE_SET_DELAYDATE_ERROR.getCode());
            result.setMessage(PactCommonEnum.RENTBASE_SET_DELAYDATE_ERROR.getMessage());
        }
        return result;
    }
    /**
     * @Title: saveRemind
     * @Description: TODO( 发送指定内容的提醒消息 )
     * @author GuoXiaoPeng
     * @param param 资源编码
     * @param key 模板key
     * @param variableList 替换的变量集合
     * @throws 异常
     */
     private void saveRemind(RentBaseParam param, String key, List<String> variableList) {
         String content = remindMap.get(key);
         for (int i = 0; i < variableList.size(); i++) {
             content = content.replace(StringConsts.VARIABLE + i, variableList.get(i));
         }
         CommonBelongerQuery commonBelongerQuery = new CommonBelongerQuery();
         commonBelongerQuery.setPactCode(param.getRentPactCode());
         commonBelongerQuery.setUserRole(StringConsts.BELONGER_DEAL);
         CommonBelongerVO commonBelongerVO = commonBelongerManager.getBelongersByParam(commonBelongerQuery);
         Map<String, Object> map = new HashMap<>();
         map.put(NormalConstant.REMIND_TITLE, StringConsts.REMIND_AUDITING_TITLE);
         map.put(NormalConstant.REMIND_PIN, commonBelongerVO.getUserPin());
         map.put(NormalConstant.REMIND_SERVICETYPE, SerializeTypeConts.RENT);
         map.put(NormalConstant.REMIND_SERVICECODE, param.getRentPactCode());
         map.put(NormalConstant.REMIND_REMINDCONTENT,content);
         remindRpc.saveRemind(map);
     }
    public RentBaseManager getRentBaseManager() {
        return rentBaseManager;
    }

    public void setRentBaseManager(RentBaseManager rentBaseManager) {
        this.rentBaseManager = rentBaseManager;
    }

    public RentRoomManager getRentRoomManager() {
        return rentRoomManager;
    }

    public void setRentRoomManager(RentRoomManager rentRoomManager) {
        this.rentRoomManager = rentRoomManager;
    }

    public RentCustomerManager getRentCustomerManager() {
        return rentCustomerManager;
    }

    public void setRentCustomerManager(RentCustomerManager rentCustomerManager) {
        this.rentCustomerManager = rentCustomerManager;
    }

    public PropertyTransferManager getPropertyTransferManager() {
        return propertyTransferManager;
    }

    public void setPropertyTransferManager(PropertyTransferManager propertyTransferManager) {
        this.propertyTransferManager = propertyTransferManager;
    }

    public CommonMeterReadManager getCommonMeterReadManager() {
        return commonMeterReadManager;
    }

    public void setCommonMeterReadManager(CommonMeterReadManager commonMeterReadManager) {
        this.commonMeterReadManager = commonMeterReadManager;
    }

    public CommonManager getCommonManager() {
        return commonManager;
    }

    public void setCommonManager(CommonManager commonManager) {
        this.commonManager = commonManager;
    }

    public CommonOwnerManager getCommonOwnerManager() {
        return commonOwnerManager;
    }

    public void setCommonOwnerManager(CommonOwnerManager commonOwnerManager) {
        this.commonOwnerManager = commonOwnerManager;
    }

    public FinanceReceiptPayManager getFinanceReceiptPayManager() {
        return financeReceiptPayManager;
    }

    public void setFinanceReceiptPayManager(FinanceReceiptPayManager financeReceiptPayManager) {
        this.financeReceiptPayManager = financeReceiptPayManager;
    }

    public CommonBelongerManager getCommonBelongerManager() {
        return commonBelongerManager;
    }

    public void setCommonBelongerManager(CommonBelongerManager commonBelongerManager) {
        this.commonBelongerManager = commonBelongerManager;
    }
    public OperateLogRpc getOperateLogRpc() {
        return operateLogRpc;
    }
    public void setOperateLogRpc(OperateLogRpc operateLogRpc) {
        this.operateLogRpc = operateLogRpc;
    }
    public PersonalRpc getPersonalRpc() {
        return personalRpc;
    }
    public void setPersonalRpc(PersonalRpc personalRpc) {
        this.personalRpc = personalRpc;
    }
    public SerializeRpc getSerializeRpc() {
        return serializeRpc;
    }
    public void setSerializeRpc(SerializeRpc serializeRpc) {
        this.serializeRpc = serializeRpc;
    }
    public RoomRpc getRoomRpc() {
        return roomRpc;
    }
    public void setRoomRpc(RoomRpc roomRpc) {
        this.roomRpc = roomRpc;
    }
    public HouseRpc getHouseRpc() {
        return houseRpc;
    }
    public void setHouseRpc(HouseRpc houseRpc) {
        this.houseRpc = houseRpc;
    }
    public DictionaryRpc getDictionaryRpc() {
        return dictionaryRpc;
    }
    public void setDictionaryRpc(DictionaryRpc dictionaryRpc) {
        this.dictionaryRpc = dictionaryRpc;
    }
    public DeptRpc getDeptRpc() {
        return deptRpc;
    }
    public void setDeptRpc(DeptRpc deptRpc) {
        this.deptRpc = deptRpc;
    }
    public RemindRpc getRemindRpc() {
        return remindRpc;
    }
    public void setRemindRpc(RemindRpc remindRpc) {
        this.remindRpc = remindRpc;
    }
    public Map<String, String> getRemindMap() {
        return remindMap;
    }
    public void setRemindMap(Map<String, String> remindMap) {
        this.remindMap = remindMap;
    }
    public StatisticsManager getStatisticsManager() {
        return statisticsManager;
    }
    public void setStatisticsManager(StatisticsManager statisticsManager) {
        this.statisticsManager = statisticsManager;
    }
    
}