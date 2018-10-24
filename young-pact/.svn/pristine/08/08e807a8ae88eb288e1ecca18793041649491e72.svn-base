package com.young.pact.service.rentturn.impl;

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
import com.young.pact.api.domain.param.rest.commongoods.CommonGoodsParam;
import com.young.pact.api.domain.param.rest.commonmeterread.CommonMeterReadParam;
import com.young.pact.api.domain.param.rest.commonmeterread.CommonMeterReadPicParam;
import com.young.pact.api.domain.param.rest.financereceiptpay.FinanceReceiptPayParam;
import com.young.pact.api.domain.param.rest.rentbase.RentPactParam;
import com.young.pact.api.domain.param.rest.rentturn.RentTurnParam;
import com.young.pact.api.domain.param.rest.rentturn.RentTurnProtocolParam;
import com.young.pact.api.domain.result.rest.commoncostrule.CommonCostRuleResult;
import com.young.pact.api.domain.result.rest.commongoods.CommonGoodsPicResult;
import com.young.pact.api.domain.result.rest.commongoods.CommonGoodsResult;
import com.young.pact.api.domain.result.rest.commonmeterread.CommonMeterReadPicResult;
import com.young.pact.api.domain.result.rest.commonmeterread.CommonMeterReadResult;
import com.young.pact.api.domain.result.rest.commonpic.CommonPicResult;
import com.young.pact.api.domain.result.rest.financereceiptpay.FinanceReceiptPayResult;
import com.young.pact.api.domain.result.rest.rentbase.RentBaseResult;
import com.young.pact.api.domain.result.rest.rentbase.RentPactResult;
import com.young.pact.api.domain.result.rest.rentroom.RoomResult;
import com.young.pact.api.domain.result.rest.rentturn.RentTurnProtocolResult;
import com.young.pact.api.domain.result.rest.rentturn.RentTurnResult;
import com.young.pact.api.domain.result.rest.rentturncost.RentTurnCostResult;
import com.young.pact.api.service.rest.rentturn.RentTurnService;
import com.young.pact.common.constant.NormalConstant;
import com.young.pact.common.constant.PactTypeConsts;
import com.young.pact.common.constant.SerializeTypeConts;
import com.young.pact.common.constant.StringConsts;
import com.young.pact.common.dict.PactCommonEnum;
import com.young.pact.common.exception.PactManagerExcepion;
import com.young.pact.common.util.RegexUtil;
import com.young.pact.dao.commonpic.CommonPicDao;
import com.young.pact.dao.rentturncost.RentTurnCostDao;
import com.young.pact.domain.commonbelonger.CommonBelongerDO;
import com.young.pact.domain.commonbelonger.CommonBelongerQuery;
import com.young.pact.domain.commonbelonger.CommonBelongerVO;
import com.young.pact.domain.commoncostrule.CommonCostRuleDO;
import com.young.pact.domain.commongoods.CommonGoodsDO;
import com.young.pact.domain.commongoods.CommonGoodsPicDO;
import com.young.pact.domain.commongoods.GoodsVO;
import com.young.pact.domain.commonmeterread.CommonMeterReadDO;
import com.young.pact.domain.commonmeterread.CommonMeterReadPicDO;
import com.young.pact.domain.commonmeterread.CommonMeterReadVO;
import com.young.pact.domain.commonpic.CommonPicDO;
import com.young.pact.domain.commonpic.CommonPicVO;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayDO;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayQuery;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayVO;
import com.young.pact.domain.rentbase.RentBaseDO;
import com.young.pact.domain.rentbase.RentBaseVO;
import com.young.pact.domain.rentbase.RentPactDO;
import com.young.pact.domain.rentbase.RentPactVO;
import com.young.pact.domain.rentcommonowner.RentCommonOwnerDO;
import com.young.pact.domain.rentcustomer.CustomerOwnerVO;
import com.young.pact.domain.rentcustomer.RentCustomerDO;
import com.young.pact.domain.rentcustomer.RentCustomerVO;
import com.young.pact.domain.rentroom.RentRoomDO;
import com.young.pact.domain.rentroom.RentRoomVO;
import com.young.pact.domain.rentturn.RentTurnDO;
import com.young.pact.domain.rentturn.RentTurnProtocolDO;
import com.young.pact.domain.rentturn.RentTurnProtocolVO;
import com.young.pact.domain.rentturn.RentTurnQuery;
import com.young.pact.domain.rentturn.RentTurnVO;
import com.young.pact.domain.rentturncost.RentTurnCostDO;
import com.young.pact.domain.rentturncost.RentTurnCostVO;
import com.young.pact.domain.statistics.AgendaDO;
import com.young.pact.manager.common.CommonManager;
import com.young.pact.manager.commonbelonger.CommonBelongerManager;
import com.young.pact.manager.commonpic.CommonPicManager;
import com.young.pact.manager.financereceiptpay.FinanceReceiptPayManager;
import com.young.pact.manager.rentbase.RentBaseManager;
import com.young.pact.manager.rentcustomer.RentCustomerManager;
import com.young.pact.manager.rentroom.RentRoomManager;
import com.young.pact.manager.rentturn.RentTurnManager;
import com.young.pact.manager.statistics.StatisticsManager;
import com.young.pact.rpc.dept.DeptRpc;
import com.young.pact.rpc.dictionary.DictionaryRpc;
import com.young.pact.rpc.operatelog.OperateLogRpc;
import com.young.pact.rpc.personal.PersonalRpc;
import com.young.pact.rpc.remind.RemindRpc;
import com.young.pact.rpc.serialize.SerializeRpc;
import com.young.sso.api.domain.result.rpc.personal.PersonalResult;

/**
 * @描述 : 转租协议
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月6日 下午9:04:24
 */
@Service("rentTurnService")
public class RentTurnServiceImpl implements RentTurnService {

    private static Log LOG = LogFactoryImpl.getLog(RentTurnServiceImpl.class);
    
    private RentTurnManager rentTurnManager;
    private RentBaseManager rentBaseManager;
    private RentRoomManager rentRoomManager;
    private RentCustomerManager rentCustomerManager;
    private FinanceReceiptPayManager financeReceiptPayManager;
    private CommonManager commonManager;
    private CommonPicDao commonPicDao;
    private RentTurnCostDao rentTurnCostDao;
    private CommonBelongerManager commonBelongerManager;
    private CommonPicManager commonPicManager;
    private SerializeRpc serializeRpc;
    private OperateLogRpc operateLogRpc;
    private DictionaryRpc dictionaryRpc;
    private PersonalRpc personalRpc;
    private DeptRpc deptRpc;
    private Map<String,String> remindMap;
    private RemindRpc remindRpc;
    private StatisticsManager statisticsManager;
    @SuppressWarnings("unused")
    @Override
    public ApiResult<String> saveRentTurnRedis(RentTurnProtocolParam param) {
        ApiResult<String> result = new ApiResult<>();
        StringBuilder key = new StringBuilder();
        String pin = ""; 
        try {
            pin = param.getCreateName() == null?"":param.getCreateName();
            key.append(param.getServiceCode()).append(StringConsts.UNDERLINE)
            .append(StringConsts.REDIS_RENT_TURN_KEY).append(StringConsts.UNDERLINE).append(pin);
            if(null == param){
                result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
                result.setMessage(PactCommonEnum.PARAM_IS_NULL.getCode());
                return result;
            }
            List<FinanceReceiptPayParam> financeReceiptPayParams = param.getReceiptPayList();
            if(null != financeReceiptPayParams && financeReceiptPayParams.size() > 0){
                for(FinanceReceiptPayParam financeReceiptPayParam: financeReceiptPayParams) {
                    if(!RegexUtil.is_tel(financeReceiptPayParam.getTel())){
                        result.setCode(PactCommonEnum.TEL_NOT_MATCH.getCode());
                        result.setMessage(PactCommonEnum.TEL_NOT_MATCH.getMessage());
                        return result;
                    }
                }
            }
            RentTurnProtocolDO rentTurnProtocolDO = new RentTurnProtocolDO();
            //(集合)
            RentTurnDO rentTurnDO = new RentTurnDO();
            BeanUtils.copyProperties(param.getRentTurnParam(), rentTurnDO);
            rentTurnProtocolDO.setRentTurnDO(rentTurnDO);
            //收支(集合)
            List<FinanceReceiptPayDO> financeReceiptPayDOs = new ArrayList<>();
            if(null!=param.getReceiptPayList()&&param.getReceiptPayList().size()>0){
                BeanUtils.copyListProperties(param.getReceiptPayList(), financeReceiptPayDOs, FinanceReceiptPayDO.class);
            }
            rentTurnProtocolDO.setFinanceReceiptPayDOs(financeReceiptPayDOs);
            //转让费用(集合)
            List<RentTurnCostDO> rentTurnCostDOs = new ArrayList<>();
            BeanUtils.copyListProperties(param.getRentTurnCostList(), rentTurnCostDOs,RentTurnCostDO.class );
            rentTurnProtocolDO.setRentTurnCostDOs(rentTurnCostDOs);
            //抄表信息(集合)
            List<CommonMeterReadDO> meterReadList = new ArrayList<>();
            if(null!=param.getCommonMeterReadList()&&param.getCommonMeterReadList().size()>0){
                for(CommonMeterReadParam commonMeterReadParam:param.getCommonMeterReadList()){
                    List<CommonMeterReadPicParam> commonMeterReadPicList = commonMeterReadParam.getCommonMeterReadPicList();
                    List<CommonMeterReadPicDO> commonMeterReadPicDOs = new ArrayList<>();
                    BeanUtils.copyListProperties(commonMeterReadParam.getCommonMeterReadPicList(), commonMeterReadPicDOs, CommonMeterReadPicDO.class);
                    CommonMeterReadDO commonMeterReadDO = new CommonMeterReadDO();
                    BeanUtils.copyProperties(commonMeterReadParam, commonMeterReadDO);
                    commonMeterReadDO.setCommonMeterReadPicList(commonMeterReadPicDOs);
                    meterReadList.add(commonMeterReadDO);
                }
            }
            rentTurnProtocolDO.setMeterReadList(meterReadList);
            //物品信息(集合)
            List<CommonGoodsDO> goodsList = new ArrayList<>();
            List<CommonGoodsParam> commonGoodsList = param.getCommonGoodsList();
            if(null!=param.getCommonGoodsList()&&param.getCommonGoodsList().size()>0){
                for(CommonGoodsParam commonGoodsParam:param.getCommonGoodsList()){
                    List<CommonGoodsPicDO> commonGoodsPicDOs = new ArrayList<>();
                    BeanUtils.copyListProperties( commonGoodsParam.getCommonGoodsPicList(), commonGoodsPicDOs, CommonGoodsPicDO.class);
                    CommonGoodsDO commonGoodsDO = new CommonGoodsDO();
                    BeanUtils.copyProperties(commonGoodsParam, commonGoodsDO);
                    commonGoodsDO.setCommonGoodsPicList(commonGoodsPicDOs);
                    goodsList.add(commonGoodsDO);
                }
            }
            rentTurnProtocolDO.setGoodsList(goodsList);
            //协议照片(集合)
            List<CommonPicDO> urlList = new ArrayList<>();
            BeanUtils.copyListProperties(param.getUrlList(), urlList, CommonPicDO.class);
            rentTurnProtocolDO.setUrlList(urlList);
            rentTurnManager.save(rentTurnProtocolDO,key.toString());
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

    @Override
    public ApiResult<RentTurnProtocolResult> getRentTurnRedis(String redisKey) {
        ApiResult<RentTurnProtocolResult> result = new ApiResult<>();
        RentTurnProtocolResult rentTurnProtocolResult = new RentTurnProtocolResult();
        try {
            RentTurnProtocolVO rentTurnProtocolVO = rentTurnManager.getRentTurnProtocolRedis(redisKey);
            if(null!=rentTurnProtocolVO){
                //转租信息**/
                RentTurnResult rentTurnResult = new RentTurnResult();
                BeanUtils.copyProperties(rentTurnProtocolVO.getRentTurnVO(), rentTurnResult);
                rentTurnProtocolResult.setRentTurnResult(rentTurnResult);
                //收支(集合)**/
                List<FinanceReceiptPayResult> receiptPayList = new ArrayList<>();
                BeanUtils.copyListProperties(rentTurnProtocolVO.getFinanceReceiptPayVOs(), receiptPayList, FinanceReceiptPayResult.class);
                rentTurnProtocolResult.setReceiptPayList(receiptPayList);
                //转让费用(集合)**/
                List<RentTurnCostResult> rentTurnCostResults = new ArrayList<>();
                BeanUtils.copyListProperties(rentTurnProtocolVO.getRentTurnCostVOs(), rentTurnCostResults, RentTurnCostResult.class);
                rentTurnProtocolResult.setRentTurnCostResults(rentTurnCostResults);
                //抄表信息(集合)**/
                List<CommonMeterReadResult> meterReadList = new ArrayList<>();
                for(CommonMeterReadVO commonMeterReadVO:rentTurnProtocolVO.getCommonMeterReadVOs()){
                    CommonMeterReadResult commonMeterReadResult = new CommonMeterReadResult();
                    BeanUtils.copyProperties(commonMeterReadVO, commonMeterReadResult);
                    List<CommonMeterReadPicResult> commonMeterReadPicResults = new ArrayList<>();
                    BeanUtils.copyListProperties(commonMeterReadVO.getCommonMeterReadPicList(), commonMeterReadPicResults, CommonMeterReadPicResult.class);
                    commonMeterReadResult.setCommonMeterReadPicList(commonMeterReadPicResults);
                    meterReadList.add(commonMeterReadResult);
                }
                rentTurnProtocolResult.setMeterReadList(meterReadList);
                //物品信息(集合)**/
                List<CommonGoodsResult> goodsList = new ArrayList<>();
                for(GoodsVO goodsVO:rentTurnProtocolVO.getGoodsVOs()){
                    CommonGoodsResult commonGoodsResult = new CommonGoodsResult();
                    BeanUtils.copyProperties(goodsVO, commonGoodsResult);
                    List<CommonGoodsPicResult> commonGoodsPicResults = new ArrayList<>();
                    BeanUtils.copyListProperties(goodsVO.getCommonGoodsPicList(), commonGoodsPicResults, CommonGoodsPicResult.class);
                    commonGoodsResult.setCommonGoodsPicList(commonGoodsPicResults);
                    goodsList.add(commonGoodsResult);
                }
                rentTurnProtocolResult.setGoodsList(goodsList);
                //协议照片(集合)**/
                List<CommonPicResult> urlList = new ArrayList<>();
                BeanUtils.copyListProperties(rentTurnProtocolVO.getCommonPicVOs(), urlList, CommonPicResult.class);
                rentTurnProtocolResult.setUrlList(urlList);
            }
            result.setData(rentTurnProtocolResult);
            result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
            result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
        } catch (Exception e) {
            LOG.error(CommonEnum.SYSTEM_EXCEPTION.getMessage(), e);
            result.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            result.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        }
        return result;
    }

    @SuppressWarnings("unused")
    @Override
    public ApiResult<String> saveRentTurnPactRedis(RentPactParam param) {
        ApiResult<String> result = new ApiResult<>();
        StringBuilder key = new StringBuilder();
        String pin = ""; 
        try {
            pin = param.getCreateName() == null?"":param.getCreateName();
            key.append(param.getServiceCode()).append(StringConsts.UNDERLINE)
            .append(StringConsts.REDIS_RENT_TURN_PACT_KEY).append(StringConsts.UNDERLINE).append(pin);
            if(null == param){
                result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
                result.setMessage(PactCommonEnum.PARAM_IS_NULL.getCode());
                return result;
            }
            List<FinanceReceiptPayParam> financeReceiptPayParams = param.getReceiptPayList();
            if(null != financeReceiptPayParams && financeReceiptPayParams.size() > 0){
                for(FinanceReceiptPayParam financeReceiptPayParam: financeReceiptPayParams) {
                    if(!RegexUtil.is_tel(financeReceiptPayParam.getTel())){
                        result.setCode(PactCommonEnum.TEL_NOT_MATCH.getCode());
                        result.setMessage(PactCommonEnum.TEL_NOT_MATCH.getMessage());
                        return result;
                    }
                }
            }
            RentPactDO rentPactDO = new RentPactDO();
            BeanUtils.copyProperties(param, rentPactDO);
            RentBaseDO pact = new RentBaseDO();
            BeanUtils.copyProperties(param.getPact(), pact);
            rentPactDO.setPact(pact);
            
            List<CommonCostRuleDO> commonCostRuleDOs =new ArrayList<>();
            List<FinanceReceiptPayDO> financeReceiptPayDOs = new ArrayList<>();
            List<CommonPicDO> commonPicDOs = new ArrayList<>();
            if(null != param.getPactReceiptPayList()){
                BeanUtils.copyListProperties(param.getPactReceiptPayList(), commonCostRuleDOs, CommonCostRuleDO.class);
            }
            if(null != param.getReceiptPayList()){
                BeanUtils.copyListProperties(param.getReceiptPayList(), financeReceiptPayDOs, FinanceReceiptPayDO.class);
            }
            BeanUtils.copyListProperties(param.getUrlList(), commonPicDOs, CommonPicDO.class);
            rentPactDO.setPactReceiptPayList(commonCostRuleDOs);
            rentPactDO.setReceiptPayList(financeReceiptPayDOs);
            rentPactDO.setUrlList(commonPicDOs);
            rentTurnManager.saveRentTurnPactRedis(key.toString(), rentPactDO);
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
    @Override
    public ApiResult<RentPactResult> getRentTurnPactRedis(String redisKey) {
        ApiResult<RentPactResult> result = new ApiResult<>();
        try {
            RentPactVO rentPactVO = rentTurnManager.getPactRedis(redisKey);
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
    
    @Override
    public ApiResult<String> saveRentTurnPact(RentTurnParam param) {
        ApiResult<String> result = new ApiResult<>();
        StringBuilder rentTurnRedisKey = new StringBuilder("");
        StringBuilder roomRedisKey = new StringBuilder("");
        StringBuilder renterRedisKey = new StringBuilder("");
        StringBuilder rentPactRedisKey = new StringBuilder("");
        List<CommonCostRuleDO> newPactReceiptPayList = new ArrayList<>();
        List<FinanceReceiptPayDO> newReceiptPayList = new ArrayList<>();
        try {
            String rentPactCode = param.getPirmaryPactCode();
            String reletCode = "";
            String renterCode = "";
            String newPactCode = "";
            String costCode = "";
            renterCode = serializeRpc.queryCodeBySerializeType(SerializeTypeConts.RENTER); 
            reletCode =  serializeRpc.queryCodeBySerializeType(SerializeTypeConts.RENTTURN);
            costCode = serializeRpc.queryCodeBySerializeType(SerializeTypeConts.COST);
            newPactCode = serializeRpc.queryCodeBySerializeType(SerializeTypeConts.RENT);
            String pin = param.getCreateName()==null?"":param.getCreateName();
            String ip = param.getIp();
            String payWay = "";
            if(null == param || StringUtil.isBlank(rentPactCode)){
                result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
                result.setMessage(PactCommonEnum.PARAM_IS_NULL.getCode());
                return result;
            }
            RentBaseVO rentBaseVO = rentBaseManager.getRentBaseByCode(rentPactCode);
            if(null != rentBaseVO){
                if(1 ==rentBaseVO.getDueState()&& 1 == rentBaseVO.getCancelState()){
                    rentTurnRedisKey.append(rentPactCode).append(StringConsts.UNDERLINE)
                    .append(StringConsts.REDIS_RENT_TURN_KEY).append(StringConsts.UNDERLINE).append(pin);
                    RentTurnProtocolVO rentTurnProtocolVO = rentTurnManager.getRentTurnProtocolRedis(rentTurnRedisKey.toString());
                    if(null == rentTurnProtocolVO){
                        result.setCode(PactCommonEnum.RENT_TURN_CACHE_INVALID.getCode());
                        result.setMessage(PactCommonEnum.RENT_TURN_CACHE_INVALID.getMessage());
                        return result;
                    }
                    roomRedisKey.append(rentPactCode).append(StringConsts.UNDERLINE)
                        .append(StringConsts.REDIS_ROOM_KEY).append(StringConsts.UNDERLINE)
                        .append(pin);
                    RentRoomVO rentRoomVO = rentRoomManager.getRoomRedis(roomRedisKey.toString());  
                    if(null==rentRoomVO||StringUtil.isBlank(rentRoomVO.getCustomerCode())){
                        result.setCode(PactCommonEnum.RENT_TURN_CUSTOMER_CACHE_INVALID.getCode());
                        result.setMessage(PactCommonEnum.RENT_TURN_CUSTOMER_CACHE_INVALID.getMessage());
                        return result;
                    }
                    renterRedisKey.append(rentPactCode).append(StringConsts.UNDERLINE)
                        .append(StringConsts.REDIS_RENTER_KEY).append(StringConsts.UNDERLINE)
                        .append(pin);
                    
                    CustomerOwnerVO customerOwnerVO = rentCustomerManager.getRentCustomerRedis(renterRedisKey.toString());
                    if(null==customerOwnerVO){
                        result.setCode(PactCommonEnum.RENT_TURN_RENTER_CACHE_INVALID.getCode());
                        result.setMessage(PactCommonEnum.RENT_TURN_RENTER_CACHE_INVALID.getMessage());
                        return result;
                    }
                    rentPactRedisKey.append(rentPactCode).append(StringConsts.UNDERLINE)
                    .append(StringConsts.REDIS_RENT_TURN_PACT_KEY).append(StringConsts.UNDERLINE).append(pin);
                    RentPactVO rentPactVO = rentTurnManager.getPactRedis(rentPactRedisKey.toString());
                    if(null==rentPactVO){
                        result.setCode(PactCommonEnum.RENT_TURN_PACT_CACHE_INVALID.getCode());
                        result.setMessage(PactCommonEnum.RENT_TURN_PACT_CACHE_INVALID.getMessage());
                        return result;
                    }
                    RentRoomVO oldPactRentRoomVO = rentRoomManager.getRommByRentCode(rentPactCode);
                    FinanceReceiptPayQuery financeReceiptPayQuery = new FinanceReceiptPayQuery();
                    financeReceiptPayQuery.setPactCode(rentPactCode);
                    financeReceiptPayQuery.setPactType(StringConsts.GIVE_OUT);
                    financeReceiptPayQuery.setPayeeObject(StringConsts.RENTER);
                    /**************************转租协议  start*************************/
                    RentTurnDO rentTurnDO = new RentTurnDO();
                    BeanUtils.copyProperties(rentTurnProtocolVO.getRentTurnVO(),rentTurnDO);
                    rentTurnDO.setPirmaryPactCode(rentPactCode);
                    rentTurnDO.setNewPactCode(newPactCode);
                    rentTurnDO.setReletCode(reletCode);
                    rentTurnDO.setState(1);
                    rentTurnDO.setIp(ip);
                    rentTurnDO.setCreateName(pin);
                    rentTurnDO.setIsDelete(0);
                    rentTurnDO.setAddress(oldPactRentRoomVO.getAddress());
                    
                    //协议 收支
                    List<FinanceReceiptPayDO> protocolReceiptPayList = new ArrayList<>();
                    BeanUtils.copyListProperties(rentTurnProtocolVO.getFinanceReceiptPayVOs(), protocolReceiptPayList, FinanceReceiptPayDO.class);
                    if(null!=protocolReceiptPayList&&protocolReceiptPayList.size()>0){
                        for(FinanceReceiptPayDO financeReceiptPayDO:protocolReceiptPayList){
                            if(StringConsts.RENTER.equals(financeReceiptPayDO.getPayeeObject())&& 2 == financeReceiptPayDO.getType()){
                                rentTurnDO.setPayeeName(financeReceiptPayDO.getName());
                                rentTurnDO.setAccountNum(financeReceiptPayDO.getAccount());
                                rentTurnDO.setCaseBank(financeReceiptPayDO.getBank());
                                rentTurnDO.setOpenBank(financeReceiptPayDO.getOpenBank());
                                rentTurnDO.setTel(financeReceiptPayDO.getTel());
                                financeReceiptPayDO.setPersonnelCode(customerOwnerVO.getRenter().getRenterCode());
                                financeReceiptPayDO.setCustomerRole(StringConsts.RENTER);
                            }
                            financeReceiptPayDO.setAddress(oldPactRentRoomVO.getAddress());
                            financeReceiptPayDO.setHouseCode(oldPactRentRoomVO.getHouseCode());
                            financeReceiptPayDO.setRoomCode(oldPactRentRoomVO.getRoomCode());
                            financeReceiptPayDO.setPactType(PactTypeConsts.RENT_SUBLET);
                            financeReceiptPayDO.setPactCode(rentTurnDO.getReletCode());
                            if(null != financeReceiptPayDO.getType() && 0 == financeReceiptPayDO.getType()){// 应收
                                financeReceiptPayDO.setCostState(1);// 待收款
                            }else if(null != financeReceiptPayDO.getType() && 2 == financeReceiptPayDO.getType()){// 应支
                                financeReceiptPayDO.setCostState(4);// 待付款
                            }
                            financeReceiptPayDO.setIp(ip);
                            financeReceiptPayDO.setCreateName(pin);
                        }
                    }
                    //协议转让费用
                    List<RentTurnCostDO> protocolRentTurnCostDOlList = new ArrayList<>();
                    BeanUtils.copyListProperties(rentTurnProtocolVO.getRentTurnCostVOs(), protocolRentTurnCostDOlList, RentTurnCostDO.class);
                    if(null!=protocolRentTurnCostDOlList&&protocolRentTurnCostDOlList.size()>0){
                        for(RentTurnCostDO rentTurnCostDO:protocolRentTurnCostDOlList){
                            rentTurnCostDO.setReletCode(reletCode);
                            rentTurnCostDO.setIp(ip);
                            rentTurnCostDO.setCreateName(pin);
                        }
                    }
                    //协议抄表
                    List<CommonMeterReadDO> protocolCommonMeterReadList = new ArrayList<>();
                    for(CommonMeterReadVO commonMeterReadVO:rentTurnProtocolVO.getCommonMeterReadVOs()){
                        CommonMeterReadDO commonMeterReadDO = new CommonMeterReadDO();
                        BeanUtils.copyProperties(commonMeterReadVO, commonMeterReadDO);
                        List<CommonMeterReadPicDO> commonMeterReadPicDOs = new ArrayList<>();
                        BeanUtils.copyListProperties(commonMeterReadVO.getCommonMeterReadPicList(), commonMeterReadPicDOs, CommonMeterReadPicDO.class);
                        commonMeterReadDO.setCommonMeterReadPicList(commonMeterReadPicDOs);
                        protocolCommonMeterReadList.add(commonMeterReadDO);
                    }
                    
                    
                    if(null != protocolCommonMeterReadList&&protocolCommonMeterReadList.size()>0){
                        for (CommonMeterReadDO commonMeterReadDO : protocolCommonMeterReadList) {
                            commonMeterReadDO.setIp(ip);
                            commonMeterReadDO.setCreateName(pin);
                            commonMeterReadDO.setPactCode(reletCode);
                            commonMeterReadDO.setPactType(PactTypeConsts.RENT_SUBLET);
                            if(null != commonMeterReadDO.getCommonMeterReadPicList()&& commonMeterReadDO.getCommonMeterReadPicList().size()>0){
                                for (CommonMeterReadPicDO commonMeterReadPicDO : commonMeterReadDO.getCommonMeterReadPicList()) {
                                    commonMeterReadPicDO.setIp(ip);
                                    commonMeterReadPicDO.setCreateName(pin);
                                }
                            }
                        }
                    }
                    //协议物品
                    List<CommonGoodsDO> protocolCommonGoodsDOs = new ArrayList<>();
                    for(GoodsVO goodsVO:rentTurnProtocolVO.getGoodsVOs()){
                        CommonGoodsDO commonGoodsDO = new CommonGoodsDO();
                        BeanUtils.copyProperties(goodsVO, commonGoodsDO);
                        List<CommonGoodsPicDO> commonGoodsPicDOs = new ArrayList<>();
                        BeanUtils.copyListProperties(goodsVO.getCommonGoodsPicList(), commonGoodsPicDOs, CommonGoodsPicDO.class);
                        commonGoodsDO.setCommonGoodsPicList(commonGoodsPicDOs);
                        protocolCommonGoodsDOs.add(commonGoodsDO);
                    }
                    if(null!=protocolCommonGoodsDOs&&protocolCommonGoodsDOs.size()>0){
                        for(CommonGoodsDO commonGoodsDO:protocolCommonGoodsDOs){
                            commonGoodsDO.setIp(ip);
                            commonGoodsDO.setCreateName(pin);
                            commonGoodsDO.setPactCode(reletCode);
                            commonGoodsDO.setPactType(PactTypeConsts.RENT_SUBLET);
                            List<CommonGoodsPicDO> commonGoodsPicList = commonGoodsDO.getCommonGoodsPicList();
                            if(null!=commonGoodsPicList&&commonGoodsPicList.size()>0){
                                for(CommonGoodsPicDO commonGoodsPicDO :commonGoodsPicList){
                                    commonGoodsPicDO.setIp(ip);
                                    commonGoodsPicDO.setCreateName(pin);
                                }
                            }
                        }
                    }
                    // 需要根据用户pin通过rpc查询用户信息
                    ApiResult<PersonalResult> personalResult = personalRpc.getPersonalResultBypin(rentTurnDO.getDealUserPin());
                    PersonalResult personalResultData = personalResult.getData();
                    //协议的签约管家
                    CommonBelongerDO protocolCommonBelongerDO = new CommonBelongerDO();
                    protocolCommonBelongerDO.setPactCode(reletCode);
                    protocolCommonBelongerDO.setPactType(PactTypeConsts.RENT_SUBLET);
                    protocolCommonBelongerDO.setUserPin(rentTurnDO.getDealUserPin());
                    protocolCommonBelongerDO.setUserName(personalResultData.getEmployeeName());
                    protocolCommonBelongerDO.setUserTel(personalResultData.getEmployeeTel());
                    protocolCommonBelongerDO.setUserRole(StringConsts.RENT_USER_ROLE);
                    protocolCommonBelongerDO.setDeptName(personalResultData.getDeptName());
                    protocolCommonBelongerDO.setDeptCode(personalResultData.getDeptCode());
                    protocolCommonBelongerDO.setIp(ip);
                    protocolCommonBelongerDO.setCreateName(pin);
                    //协议的维护人
                    CommonBelongerDO guardianCommonBelongerDO = new CommonBelongerDO();
                    guardianCommonBelongerDO.setPactCode(reletCode);
                    guardianCommonBelongerDO.setPactType(PactTypeConsts.RENT_SUBLET);
                    guardianCommonBelongerDO.setUserPin(rentTurnDO.getDealUserPin());
                    guardianCommonBelongerDO.setUserName(personalResultData.getEmployeeName());
                    guardianCommonBelongerDO.setUserTel(personalResultData.getEmployeeTel());
                    guardianCommonBelongerDO.setUserRole(StringConsts.GUARDIAN);
                    guardianCommonBelongerDO.setDeptName(personalResultData.getDeptName());
                    guardianCommonBelongerDO.setDeptCode(personalResultData.getDeptCode());
                    guardianCommonBelongerDO.setIp(ip);
                    guardianCommonBelongerDO.setCreateName(pin);
                    // 协议图片
                    List<CommonPicDO> protocolCommonPicList = new ArrayList<>();
                    BeanUtils.copyListProperties(rentPactVO.getUrlList(), protocolCommonPicList, CommonPicDO.class);
                    if(null != protocolCommonPicList&&protocolCommonPicList.size()>0){
                        for (CommonPicDO commonPicDO : protocolCommonPicList) {
                            commonPicDO.setIp(ip);
                            commonPicDO.setCreateName(pin);
                            commonPicDO.setPactCode(reletCode);
                            commonPicDO.setPactType(PactTypeConsts.RENT_SUBLET);
                        }
                    }
                    /**************************转租协议  end*************************/
                    /**************************新合同  start*************************/
                    //房间
                    RentRoomDO rentRoomDO = new RentRoomDO();
                    BeanUtils.copyProperties(oldPactRentRoomVO, rentRoomDO);
                    rentRoomDO.setRentPactCode(newPactCode);
                    rentRoomDO.setIp(ip);
                    rentRoomDO.setCreateName(pin);
                    //租客
                    RentCustomerDO rentCustomerDO = new RentCustomerDO();
                    BeanUtils.copyProperties(customerOwnerVO.getRenter(), rentCustomerDO);
                    rentCustomerDO.setIp(ip);
                    rentCustomerDO.setCreateName(pin);
                    rentCustomerDO.setRentPactCode(newPactCode);
                    rentCustomerDO.setRenterCode(renterCode);
                    rentCustomerDO.setCustomerCode(rentRoomVO.getCustomerCode());
                    rentCustomerDO.setDemandCode(rentRoomVO.getDemandCode());
                    //租客共同居住人
                    List<RentCommonOwnerDO> cohabitantList = new ArrayList<>();
                    BeanUtils.copyListProperties(customerOwnerVO.getCohabitantList(), cohabitantList, RentCommonOwnerDO.class);
                    if(null!=cohabitantList&&cohabitantList.size()>0){
                        for(RentCommonOwnerDO rentCommonOwnerDO:cohabitantList){
                            rentCommonOwnerDO.setRenterCode(renterCode);
                            rentCommonOwnerDO.setCreateName(pin);
                            rentCommonOwnerDO.setIp(ip);
                        }
                    }
                    //托出合同
                    RentBaseDO rentBaseDO = new RentBaseDO();
                    BeanUtils.copyProperties(rentPactVO.getPact(), rentBaseDO);
                    if(StringConsts.VARIABL_CONDITION.equals(rentTurnProtocolVO.getRentTurnVO().getNature())){
                         payWay = rentPactVO.getPact().getPayWay();
                    }else if(StringConsts.ORIGINAL_CONDITION.equals(rentTurnProtocolVO.getRentTurnVO().getNature())){
                         payWay = rentBaseVO.getPayWay();
                    }
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
                    rentBaseDO.setRentPactCode(newPactCode);
                    rentBaseDO.setState(1);
                    rentBaseDO.setDueState(1);
                    rentBaseDO.setDealState(2);
                    rentBaseDO.setCancelState(1);
                    rentBaseDO.setIsDelete(0);
                    rentBaseDO.setIsEffective(1);
                    rentBaseDO.setIp(ip);
                    rentBaseDO.setCreateName(pin);
                    if(StringConsts.ORIGINAL_CONDITION.equals(rentTurnDO.getNature()) ){
                        rentBaseDO.setPrice(rentBaseVO.getPrice());
                        rentBaseDO.setDeposit(rentBaseVO.getDeposit());
                        rentBaseDO.setPay(rentBaseVO.getPay());
                        rentBaseDO.setCustody(rentBaseVO.getCustody());
                        rentBaseDO.setPayDays(rentBaseVO.getPayDays());
                        rentBaseDO.setFirstPayTime(rentBaseVO.getFirstPayTime());
                        rentBaseDO.setMonthlyPayment(rentBaseVO.getMonthlyPayment());
                        rentBaseDO.setSigningTime(rentBaseVO.getSigningTime());
                    }else if(StringConsts.VARIABL_CONDITION.equals(rentTurnDO.getNature())){
                        // 新合同收支规则
                        if(null!=rentPactVO.getPactReceiptPayList()){
                            BeanUtils.copyListProperties(rentPactVO.getPactReceiptPayList(), newPactReceiptPayList, CommonCostRuleDO.class);
                        }
                        if(null!=newPactReceiptPayList){
                            for(CommonCostRuleDO commonCostRuleDO:newPactReceiptPayList){
                                commonCostRuleDO.setPactCode(newPactCode);
                                commonCostRuleDO.setPactType(PactTypeConsts.RENT);
                                commonCostRuleDO.setCostCode(costCode);
                                commonCostRuleDO.setCreateName(pin);
                                commonCostRuleDO.setIp(ip);
                            }
                        }
                        if(null!=rentPactVO.getReceiptPayList()){
                            BeanUtils.copyListProperties(rentPactVO.getReceiptPayList(), newReceiptPayList, FinanceReceiptPayDO.class);
                        }
                        if(null!= newReceiptPayList){
                            for (FinanceReceiptPayDO financeReceiptPayDO : newReceiptPayList) {
                                financeReceiptPayDO.setPactCode(newPactCode);
                                financeReceiptPayDO.setPactType(PactTypeConsts.RENT);
                                financeReceiptPayDO.setCreateName(pin);
                                financeReceiptPayDO.setIp(ip);
                                if(null != financeReceiptPayDO.getType() && 0 == financeReceiptPayDO.getType()){// 应收
                                    financeReceiptPayDO.setCostState(1);// 待收款
                                }else if(null != financeReceiptPayDO.getType() && 2 == financeReceiptPayDO.getType()){// 应支
                                    financeReceiptPayDO.setCostState(4);// 待付款
                                }
                                financeReceiptPayDO.setHouseCode(oldPactRentRoomVO.getHouseCode());
                                financeReceiptPayDO.setRoomCode(oldPactRentRoomVO.getRoomCode());
                                financeReceiptPayDO.setAddress(oldPactRentRoomVO.getAddress());
                            }
                        }
                        
                    }
                    //新合同的抄表
                    List<CommonMeterReadDO> newPactCommonMeterReadList = new ArrayList<>();
                    for(CommonMeterReadVO commonMeterReadVO:rentTurnProtocolVO.getCommonMeterReadVOs()){
                        CommonMeterReadDO commonMeterReadDO = new CommonMeterReadDO();
                        BeanUtils.copyProperties(commonMeterReadVO, commonMeterReadDO);
                        List<CommonMeterReadPicDO> commonMeterReadPicDOs = new ArrayList<>();
                        BeanUtils.copyListProperties(commonMeterReadVO.getCommonMeterReadPicList(), commonMeterReadPicDOs, CommonMeterReadPicDO.class);
                        commonMeterReadDO.setCommonMeterReadPicList(commonMeterReadPicDOs);
                        newPactCommonMeterReadList.add(commonMeterReadDO);
                    }
                    if(null!=newPactCommonMeterReadList&&newPactCommonMeterReadList.size()>0){
                        for(CommonMeterReadDO commonMeterReadDO:newPactCommonMeterReadList){
                            commonMeterReadDO.setPactCode(newPactCode);
                            commonMeterReadDO.setPactType(PactTypeConsts.RENT);
                            commonMeterReadDO.setCreateName(pin);
                            commonMeterReadDO.setIp(ip);
                            for(CommonMeterReadPicDO commonMeterReadPicDO:commonMeterReadDO.getCommonMeterReadPicList()){
                                commonMeterReadPicDO.setCreateName(pin);
                                commonMeterReadPicDO.setIp(ip);
                            }
                        }
                    }
                    //新合同的物品信息
                    List<CommonGoodsDO> newPactCommonGoodsDOs = new ArrayList<>();
                    //协议物品
                    for(GoodsVO goodsVO:rentTurnProtocolVO.getGoodsVOs()){
                        CommonGoodsDO commonGoodsDO = new CommonGoodsDO();
                        BeanUtils.copyProperties(goodsVO, commonGoodsDO);
                        List<CommonGoodsPicDO> commonGoodsPicDOs = new ArrayList<>();
                        BeanUtils.copyListProperties(goodsVO.getCommonGoodsPicList(), commonGoodsPicDOs, CommonGoodsPicDO.class);
                        commonGoodsDO.setCommonGoodsPicList(commonGoodsPicDOs);
                        newPactCommonGoodsDOs.add(commonGoodsDO);
                    }
                    if(null!=newPactCommonGoodsDOs&&newPactCommonGoodsDOs.size()>0){
                        for(CommonGoodsDO commonGoodsDO:newPactCommonGoodsDOs){
                            commonGoodsDO.setPactCode(newPactCode);
                            commonGoodsDO.setPactType(PactTypeConsts.RENT);
                            commonGoodsDO.setCreateName(pin);
                            commonGoodsDO.setIp(ip);
                            for(CommonGoodsPicDO commonGoodsPicDO:commonGoodsDO.getCommonGoodsPicList()){
                                commonGoodsPicDO.setCreateName(pin);
                                commonGoodsPicDO.setIp(ip);
                            }
                        }
                    }
                    // 合同责任人
                    List<CommonBelongerDO> commonBelongerDOList = new ArrayList<>();
                    CommonBelongerDO newPactCommonBelongerDO = new CommonBelongerDO();
                    newPactCommonBelongerDO.setPactCode(newPactCode);
                    newPactCommonBelongerDO.setPactType(PactTypeConsts.RENT);
                    newPactCommonBelongerDO.setUserPin(rentTurnDO.getDealUserPin());
                    newPactCommonBelongerDO.setUserName(personalResultData.getEmployeeName());
                    newPactCommonBelongerDO.setUserTel(personalResultData.getEmployeeTel());
                    newPactCommonBelongerDO.setUserRole(StringConsts.RENT_USER_ROLE);
                    newPactCommonBelongerDO.setDeptName(personalResultData.getDeptName());
                    newPactCommonBelongerDO.setDeptCode(personalResultData.getDeptCode());
                    newPactCommonBelongerDO.setIp(ip);
                    newPactCommonBelongerDO.setCreateName(pin);
                    commonBelongerDOList.add(newPactCommonBelongerDO);
                    commonBelongerDOList.add(protocolCommonBelongerDO);
                    commonBelongerDOList.add(guardianCommonBelongerDO);
                    //合同照片
                    /**************************新合同  end*************************/
                    List<CommonPicDO> newPactCommonPicList = new ArrayList<>();
                    BeanUtils.copyListProperties(rentPactVO.getUrlList(), newPactCommonPicList, CommonPicDO.class);
                    if(null != newPactCommonPicList&&newPactCommonPicList.size()>0){
                        for (CommonPicDO commonPicDO : newPactCommonPicList) {
                            commonPicDO.setIp(ip);
                            commonPicDO.setCreateName(pin);
                            commonPicDO.setPactCode(newPactCode);
                            commonPicDO.setPactType(PactTypeConsts.RENT);
                        }
                    }
                    RentTurnProtocolDO rentTurnProtocolDO = new RentTurnProtocolDO();
                    rentTurnProtocolDO.setRentTurnDO(rentTurnDO);
                    rentTurnProtocolDO.setFinanceReceiptPayDOs(protocolReceiptPayList);
                    rentTurnProtocolDO.setRentTurnCostDOs(protocolRentTurnCostDOlList);
                    rentTurnProtocolDO.setMeterReadList(protocolCommonMeterReadList);
                    rentTurnProtocolDO.setGoodsList(protocolCommonGoodsDOs);
                    rentTurnProtocolDO.setUrlList(protocolCommonPicList);
                    RentPactDO rentPactDO = new RentPactDO();
                    rentPactDO.setPact(rentBaseDO);
                    rentPactDO.setPactReceiptPayList(newPactReceiptPayList);
                    rentPactDO.setReceiptPayList(newReceiptPayList);
                    rentPactDO.setUrlList(newPactCommonPicList);
                    boolean flag = rentTurnManager.saveRentTurn(rentTurnProtocolDO,rentPactDO,rentRoomDO,
                            rentCustomerDO,cohabitantList,newPactCommonMeterReadList,newPactCommonGoodsDOs,commonBelongerDOList);
                    if(flag){
                        String [] delRedisKey = new String []{rentTurnRedisKey.toString(),
                                roomRedisKey.toString(),renterRedisKey.toString(),
                                rentPactRedisKey.toString()}; 
                        commonManager.deleteRedis(delRedisKey);
                        /*---------发一个操作日志  未填充----start------*/
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put(NormalConstant.SERVICETYPE, SerializeTypeConts.RENTTURN);
                        map.put(NormalConstant.SERVICECODE, rentTurnDO.getReletCode());
                        map.put(NormalConstant.OPERATETYPE, StringConsts.ADD);
                        map.put(NormalConstant.OPERATECONTENT, StringConsts.ADD_DATA);
                        map.put(NormalConstant.OPERATEPIN, rentTurnDO.getCreateName());
                        operateLogRpc.saveOperateLog(map);
                        /*---------发一个操作日志  未填充----end------*/
                        result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                        result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                        result.setData(reletCode);
                    }else{
                        result.setCode(PactCommonEnum.RENT_TURN_SAVE_FAIL.getCode());
                        result.setMessage(PactCommonEnum.RENT_TURN_SAVE_FAIL.getMessage());
                    }
                }else{
                    result.setCode(PactCommonEnum.RENT_BASE_STATE_NO_TURN.getCode());
                    result.setMessage(PactCommonEnum.RENT_BASE_STATE_NO_TURN.getCode());
                    return result;
                }
            }
        } catch(PactManagerExcepion e){
            LOG.error(e.getMessage(), e);
            result.setCode(PactCommonEnum.RENT_TURN_SAVE_FAIL.getCode());
            result.setMessage(PactCommonEnum.RENT_TURN_SAVE_FAIL.getMessage());
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            result.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            result.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        }
        return result;
    }

    @Override
    public ApiResult<PageBean<RentTurnResult>> listRentTurn(RentTurnParam param) {
        ApiResult<PageBean<RentTurnResult>> result = new ApiResult<>();
        try {
            if(null == param){
                result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
                result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
                return result;
            }
            if(StringUtil.isNotBlank(param.getStartDeadline())
                    && StringUtil.isNotBlank(param.getEndDeadline()) ){
                param.setStartDeadline(param.getStartDeadline() + " 00:00:00");
                param.setEndDeadline(param.getEndDeadline() + " 23:59:59");
            }
            if(StringUtil.isNotBlank(param.getDealStartTime())
                    && StringUtil.isNotBlank(param.getDealEndTime()) ){
                param.setDealStartTime(param.getDealStartTime() + " 00:00:00");
                param.setDealEndTime(param.getDealEndTime() + " 23:59:59");
            }
            RentTurnQuery rentTurnQuery = new RentTurnQuery();
            BeanUtils.copyProperties(param, rentTurnQuery);
            ApiResult<PersonalResult> personalApiResult = personalRpc.getPersonalResultBypin(param.getCreateName());
            PersonalResult personalResult = personalApiResult.getData();
            Integer scope = param.getScope();
            if(1 == scope){// 1=本人
                rentTurnQuery.setUserPin(param.getCreateName());
            }else if(2 == scope){// 2=本部门
                rentTurnQuery.setDeptCode(personalResult.getDeptCode());
            }else if(3 == scope){//3=本人及下属
                List<String> pinList = personalRpc.listLowerByPin(param.getCreateName());
                rentTurnQuery.setPinList(pinList);
            }else if(4 == scope){//4=本部门及下属部门
                List<String> deptList = deptRpc.listCodeByDeptCode(personalResult.getDeptCode());
                rentTurnQuery.setDeptList(deptList);
            }
            PageBean<RentTurnVO> pageBean = rentTurnManager.listParam(rentTurnQuery);
            PageBean<RentTurnResult> page = new PageTableBean<>(rentTurnQuery.getPageIndex(), rentTurnQuery.getPageSize());
            page.setTotalItem(pageBean.getTotalItem());
            BeanUtils.copyListProperties(pageBean.getData(), page.getData(), RentTurnResult.class);
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
    @Override
    public ApiResult<RentTurnProtocolResult> getRentTurn(RentTurnParam param) {
        ApiResult<RentTurnProtocolResult> result = new ApiResult<>();
        RentTurnProtocolResult rentTurnProtocolResult = new RentTurnProtocolResult();
        try {
            boolean permissionsFlag = getPermissions(param);
            if(!permissionsFlag){
                result.setCode(PactCommonEnum.RENTTURN_NO_PERMISSIONS.getCode());
                result.setMessage(PactCommonEnum.RENTTURN_NO_PERMISSIONS.getMessage());
                return result;
            }
            String reletCode = param.getReletCode();
            RentTurnVO rentTurnVO = rentTurnManager.getRentTurnByReletCode(reletCode);
            RentRoomVO rentRoomVO = rentRoomManager.getRommByRentCode(rentTurnVO.getPirmaryPactCode());
            RentBaseVO oldBaseVO = rentBaseManager.getRentBaseByCode(rentTurnVO.getPirmaryPactCode());
            RentBaseVO newBaseVO = rentBaseManager.getRentBaseByCode(rentTurnVO.getNewPactCode());
            List<RentTurnCostVO> rentTurnCostVOs = rentTurnCostDao.listTurnCostByReletCode(reletCode);
            List<CommonPicVO> commonPicVOs = commonPicManager.listCommonPicByPactCode(reletCode);
            RentCustomerVO oldRentCustomerVO = rentCustomerManager.getRentCustomerByPactCode(rentTurnVO.getPirmaryPactCode());
            RentCustomerVO newRentCustomerVO = rentCustomerManager.getRentCustomerByPactCode(rentTurnVO.getNewPactCode());
            //转租信息
            RentTurnResult rentTurnResult = new RentTurnResult();
            BeanUtils.copyProperties(rentTurnVO, rentTurnResult);
            rentTurnProtocolResult.setRentTurnResult(rentTurnResult);
            //转让费用(集合)
            List<RentTurnCostResult> rentTurnCostResults = new ArrayList<>();
            BeanUtils.copyListProperties(rentTurnCostVOs, rentTurnCostResults, RentTurnCostResult.class);
            rentTurnProtocolResult.setRentTurnCostResults(rentTurnCostResults);
            FinanceReceiptPayQuery financeReceiptPayQuery = new FinanceReceiptPayQuery();
            financeReceiptPayQuery.setPactCode(reletCode);
            //协议收支(集合)
            List<FinanceReceiptPayVO> recPayList = financeReceiptPayManager.listFinanceReceiptPayByPactCode(financeReceiptPayQuery);
            List<FinanceReceiptPayResult> receiptPayList = new ArrayList<>();
            BeanUtils.copyListProperties(recPayList, receiptPayList, FinanceReceiptPayResult.class);
            rentTurnProtocolResult.setReceiptPayList(receiptPayList);
            //协议照片(集合)
            List<CommonPicResult> urlList = new ArrayList<>();
            BeanUtils.copyListProperties(commonPicVOs, urlList, CommonPicResult.class);
            rentTurnProtocolResult.setUrlList(urlList);
            //转租房间
            RoomResult roomResult = new RoomResult();
            BeanUtils.copyProperties(rentRoomVO, roomResult);
            rentTurnProtocolResult.setRoomResult(roomResult);
            //老合同信息
            RentBaseResult oldPact = new RentBaseResult();
            BeanUtils.copyProperties(oldBaseVO, oldPact);
            oldPact.setRenterCode(oldRentCustomerVO.getRenterCode());
            oldPact.setRenterName(oldRentCustomerVO.getName());
            oldPact.setRenterTel(oldRentCustomerVO.getTel());
            rentTurnProtocolResult.setOldPact(oldPact);
            //新合同信息
            RentBaseResult newPact = new RentBaseResult();
            BeanUtils.copyProperties(newBaseVO, newPact);
            newPact.setRenterCode(newRentCustomerVO.getRenterCode());
            newPact.setRenterName(newRentCustomerVO.getName());
            newPact.setRenterTel(newRentCustomerVO.getTel());
            rentTurnProtocolResult.setNewPact(newPact);
            result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
            result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
            result.setData(rentTurnProtocolResult);
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

    @Override
    public ApiResult<Boolean> auditRentTurn(RentTurnParam param) {
        ApiResult<Boolean> result = new ApiResult<>();
        if(null==param){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        if(StringUtil.isBlank(param.getReletCode())){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        try{
            RentTurnVO rentTurnVO = rentTurnManager.getRentTurnByReletCode(param.getReletCode());
            if(null!=rentTurnVO){
                if( rentTurnVO.getState()==1 || rentTurnVO.getState()==4){
                    RentTurnDO rentTurnDO = new RentTurnDO();
                    BeanUtils.copyProperties(param, rentTurnDO);
                    rentTurnDO.setState(2);
                    boolean flag = rentTurnManager.updateRentTurnStateByCode(rentTurnDO);
                    if(flag){
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put(NormalConstant.SERVICETYPE, SerializeTypeConts.RENTTURN);
                        map.put(NormalConstant.SERVICECODE, rentTurnDO.getReletCode());
                        map.put(NormalConstant.OPERATETYPE, StringConsts.OPERATETYPE_APPLICATION_AUDIT);
                        map.put(NormalConstant.OPERATECONTENT, StringConsts.AUDIT_DATA);
                        map.put(NormalConstant.OPERATEPIN, rentTurnDO.getModifiedName());
                        operateLogRpc.saveOperateLog(map);
                        /*************************首页待办数据统计  start*****************************/
                        CommonBelongerQuery commonBelongerQuery = new CommonBelongerQuery();
                        commonBelongerQuery.setPactCode(param.getReletCode());
                        commonBelongerQuery.setUserRole(StringConsts.BELONGER_DEAL);
                        CommonBelongerVO commonBelongerVO = commonBelongerManager.getBelongersByParam(commonBelongerQuery);
                        AgendaDO agendaDO = new AgendaDO();
                        agendaDO.setServiceCode(param.getReletCode());
                        agendaDO.setType(StringConsts.STATISTIC_PENDING_RENT_TURN);
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
                        result.setData(flag);
                    }else{
                        result.setCode(PactCommonEnum.RENT_TURN_AUDIT_ERROR.getCode());
                        result.setMessage(PactCommonEnum.RENT_TURN_AUDIT_ERROR.getMessage());
                    }
                }else{
                    result.setCode(PactCommonEnum.PACT_AUDIT_STATE_ERROR.getCode());
                    result.setMessage(PactCommonEnum.PACT_AUDIT_STATE_ERROR.getMessage());
                  
                }
            }
        } catch (PactManagerExcepion e){
            LOG.error(e);
            result.setCode(PactCommonEnum.RENT_TURN_AUDIT_ERROR.getCode());
            result.setMessage(PactCommonEnum.RENT_TURN_AUDIT_ERROR.getMessage());
        } catch (Exception e) {
            LOG.error(e);
            result.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            result.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        }
        return result;
    }

    @Override
    public ApiResult<Boolean> reviewPassRentTurn(RentTurnParam param) {
        ApiResult<Boolean> result = new ApiResult<>();
        if(null==param){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        if(StringUtil.isBlank(param.getReletCode())){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        try{
            RentTurnVO rentTurnVO = rentTurnManager.getRentTurnByReletCode(param.getReletCode());
            if(null!=rentTurnVO){
                RentBaseVO rentBaseVO = rentBaseManager.getRentBaseByCode(rentTurnVO.getPirmaryPactCode());
                result =  checkData(rentBaseVO);
                if(!result.getData()){
                    return result;
                }
                if(rentTurnVO.getState()==2 ){
                    RentTurnDO rentTurnDO = new RentTurnDO();
                    BeanUtils.copyProperties(param, rentTurnDO);
                    rentTurnDO.setIp(param.getIp());
                    rentTurnDO.setState(3);
                    rentTurnDO.setNewPactCode(rentTurnVO.getNewPactCode());
                    rentTurnDO.setPirmaryPactCode(rentTurnVO.getPirmaryPactCode());
                    rentTurnDO.setReletTime(rentTurnVO.getReletTime());
                    boolean flag = rentTurnManager.updateRentTurnStateByCode(rentTurnDO);
                    if(flag){
                        List<String> variableList = new ArrayList<>();
                        variableList.add(param.getReletCode());
                        saveRemind(param,StringConsts.REVIEW_PASS_RENTTURN,variableList);
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put(NormalConstant.SERVICETYPE, SerializeTypeConts.RENTTURN);
                        map.put(NormalConstant.SERVICECODE, rentTurnDO.getReletCode());
                        map.put(NormalConstant.OPERATETYPE, StringConsts.OPERATETYPE_REVIEW_PASS);
                        map.put(NormalConstant.OPERATECONTENT, StringConsts.REVIEWPASS_DATA);
                        map.put(NormalConstant.OPERATEPIN, rentTurnDO.getModifiedName());
                        operateLogRpc.saveOperateLog(map);
                        /******************** 修改待办事务统计表的状态  start *******************************/
                        AgendaDO agendaDO = new AgendaDO();
                        agendaDO.setServiceCode(param.getReletCode());
                        agendaDO.setIsDo(1);
                        agendaDO.setType(StringConsts.STATISTIC_PENDING_RENT_TURN);
                        agendaDO.setModifiedName(param.getModifiedName());
                        agendaDO.setIp(param.getIp());
                        statisticsManager.updatBacklogStatisticsIsDo(agendaDO);
                        /******************** 修改待办事务统计表的状态  end *******************************/
                        saveReceivablesStatistics(rentTurnVO);
                        saveObligationStatistics(rentTurnVO);
                        removeStatistics(rentTurnVO);
                        result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                        result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                        result.setData(flag);
                    }else{
                        result.setCode(PactCommonEnum.RENT_TURN_REVIEWPASS_ERROR.getCode());
                        result.setMessage(PactCommonEnum.RENT_TURN_REVIEWPASS_ERROR.getMessage());
                    }
                }else{
                    result.setCode(PactCommonEnum.PACT_AUDIT_STATE_ERROR.getCode());
                    result.setMessage(PactCommonEnum.PACT_AUDIT_STATE_ERROR.getMessage());
                }
            }
        } catch (PactManagerExcepion e){
            LOG.error(e);
            result.setCode(PactCommonEnum.RENT_TURN_REVIEWPASS_ERROR.getCode());
            result.setMessage(PactCommonEnum.RENT_TURN_REVIEWPASS_ERROR.getMessage());
        } catch (Exception e) {
            LOG.error(e);
            result.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            result.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        }
        return result;
    }
    /**
     * 
    * @Title: checkData
    * @Description: TODO( 校验托出合同 的有效性)
    * @author GuoXiaoPeng
    * @param rentBaseVO 托出合同 
    * @return 合同有效返回true，无效返回false
    * @throws 异常
     */
    private ApiResult<Boolean> checkData(RentBaseVO rentBaseVO) {
        ApiResult<Boolean> apiResult = new ApiResult<>();
        apiResult.setData(true);
        if(1 !=rentBaseVO.getCancelState()){
            apiResult.setCode(PactCommonEnum.OLD_PACT_CANCELSTATE_NOTVALID.getCode());
            apiResult.setMessage(PactCommonEnum.OLD_PACT_CANCELSTATE_NOTVALID.getMessage());
            apiResult.setData(false);
            return apiResult;
        }
        if(1 != rentBaseVO.getDueState()){
            apiResult.setCode(PactCommonEnum.OLD_PACT_DUESTATE_NOTVALID.getCode());
            apiResult.setMessage(PactCommonEnum.OLD_PACT_DUESTATE_NOTVALID.getMessage()); 
            apiResult.setData(false);
            return apiResult;
        }
        return apiResult;
    }

    /**
     * 
    * @Title: removeStatistics
    * @Description: TODO( 删除待办事务统计待收款+待付款记录 )
    * @author GuoXiaoPeng
    * @param rentTurnVO 转租协议信息
    * @throws 异常
     */
    private void removeStatistics(RentTurnVO rentTurnVO) {
        /******************** 待办事务统计待收款（老合同的应收删除）      start *******************************/
        List<FinanceReceiptPayVO> delReceiptPayVOS = null;
        List<FinanceReceiptPayVO> delPendingPaymentList  = null;
        List<AgendaDO> deleteList = new ArrayList<>();
        FinanceReceiptPayQuery oldPactReceiptPayQuery = new FinanceReceiptPayQuery();
        oldPactReceiptPayQuery.setPactCode(rentTurnVO.getPirmaryPactCode());
        Date date = DateUtil.getDayByDay(rentTurnVO.getReletTime(), 1);
        oldPactReceiptPayQuery.setCostTimeStr(DateUtil.formatDate(date));
        oldPactReceiptPayQuery.setCostState(1);
        delReceiptPayVOS = financeReceiptPayManager.listReceiptPayByDate(oldPactReceiptPayQuery);
        oldPactReceiptPayQuery.setCostState(4);
        delPendingPaymentList = financeReceiptPayManager.listReceiptPayByDate(oldPactReceiptPayQuery);
        if(null != delReceiptPayVOS && delReceiptPayVOS.size()>0){
            for(FinanceReceiptPayVO recpay:delReceiptPayVOS){
                AgendaDO agendaDO = new AgendaDO();
                agendaDO.setServiceCode(String.valueOf(recpay.getId()));
                agendaDO.setType(StringConsts.STATISTIC_PENDING_RECEIVABLES);
                deleteList.add(agendaDO);
            }
        }
        if(null != delPendingPaymentList && delPendingPaymentList.size()>0){
            for(FinanceReceiptPayVO recpay:delPendingPaymentList){
                AgendaDO agendaDO = new AgendaDO();
                agendaDO.setServiceCode(String.valueOf(recpay.getId()));
                agendaDO.setType(StringConsts.STATISTIC_PENDING_PENDING_PAYMENT);
                deleteList.add(agendaDO);
            }
        }
        statisticsManager.removeBacklogStatistics(deleteList);
        /******************** 待办事务统计待收款（老合同的应收删除）      end *******************************/
    }

    /**
     * 
    * @Title: saveObligationStatistics
    * @Description: TODO( 待办事务统计待付款,协议+新合同中所有收支的待付款  )
    * @author GuoXiaoPeng
    * @param rentTurnVO 转租协议
    * @throws 异常
     */
    private void saveObligationStatistics(RentTurnVO rentTurnVO) {
        /******************** 待办事务统计待付款（协议中的应支）      start *******************************/
        FinanceReceiptPayQuery financeReceiptPayQuery = new FinanceReceiptPayQuery();
        financeReceiptPayQuery.setIsValid(1);
        financeReceiptPayQuery.setPactCode(rentTurnVO.getReletCode());
        financeReceiptPayQuery.setType(2);
        List<Integer> costList = new ArrayList<>();
        costList.add(4);
        costList.add(5);
        financeReceiptPayQuery.setCostList(costList);
        List<FinanceReceiptPayVO> financeReceiptPayVOs = financeReceiptPayManager.listFinanceReceiptPayByPactCode(financeReceiptPayQuery);
        if(null != financeReceiptPayVOs && financeReceiptPayVOs.size() > 0){
            CommonBelongerQuery afterRentBelongerQuery = new CommonBelongerQuery();
            afterRentBelongerQuery.setPactCode(rentTurnVO.getReletCode());
            afterRentBelongerQuery.setUserRole(StringConsts.BELONGER_PERSON);
            CommonBelongerVO guardianBelongerVO = commonBelongerManager.getBelongersByParam(afterRentBelongerQuery);
            for(FinanceReceiptPayVO recPay:financeReceiptPayVOs){
                AgendaDO pendingAgendaDO = new AgendaDO();
                pendingAgendaDO.setServiceCode(String.valueOf(recPay.getId()));
                pendingAgendaDO.setType(StringConsts.STATISTIC_PENDING_PENDING_PAYMENT);
                pendingAgendaDO.setIsDo(0);
                pendingAgendaDO.setPin(guardianBelongerVO.getUserPin());
                pendingAgendaDO.setName(guardianBelongerVO.getUserName());
                pendingAgendaDO.setDeptCode(guardianBelongerVO.getDeptCode());
                pendingAgendaDO.setDeptName(guardianBelongerVO.getDeptName());
                pendingAgendaDO.setDate(new Date());
                statisticsManager.saveBacklogStatistics(pendingAgendaDO);
            }
        }
        /******************** 待办事务统计待付款（协议中的应支）      end *******************************/
        /******************** 待办事务统计待付款（新合同中的应支）      start *******************************/
        FinanceReceiptPayQuery newPactReceiptPayQuery = new FinanceReceiptPayQuery();
        newPactReceiptPayQuery.setIsValid(1);
        newPactReceiptPayQuery.setPactCode(rentTurnVO.getNewPactCode());
        newPactReceiptPayQuery.setType(2);
        newPactReceiptPayQuery.setCostList(costList);
        List<FinanceReceiptPayVO> newPactReceiptPayVOs = financeReceiptPayManager.listFinanceReceiptPayByPactCode(newPactReceiptPayQuery);
        if(null != newPactReceiptPayVOs && newPactReceiptPayVOs.size() > 0){
            CommonBelongerVO afterBelonger = rentBaseManager.getRentAfterBelonger(rentTurnVO.getNewPactCode());
            for(FinanceReceiptPayVO recPay:newPactReceiptPayVOs){
                AgendaDO pendingAgendaDO = new AgendaDO();
                pendingAgendaDO.setServiceCode(String.valueOf(recPay.getId()));
                pendingAgendaDO.setType(StringConsts.STATISTIC_PENDING_PENDING_PAYMENT);
                pendingAgendaDO.setIsDo(0);
                pendingAgendaDO.setPin(afterBelonger.getUserPin());
                pendingAgendaDO.setName(afterBelonger.getUserName());
                pendingAgendaDO.setDeptCode(afterBelonger.getDeptCode());
                pendingAgendaDO.setDeptName(afterBelonger.getDeptName());
                pendingAgendaDO.setDate(new Date());
                statisticsManager.saveBacklogStatistics(pendingAgendaDO);
            }
        }
        /******************** 待办事务统计待付款（新合同中的应支）      end *******************************/
    }

    /**
    * @Title: saveBacklogStatistics
    * @Description: TODO(  待办事务统计待收款,协议+新合同中所有收支的待收款  )
    * @author GuoXiaoPeng
    * @param param 转租协议
    * @throws 异常
     */
    private void saveReceivablesStatistics(RentTurnVO rentTurnVO) {
        /******************** 待办事务统计待收款（协议中的应收）      start *******************************/
        FinanceReceiptPayQuery financeReceiptPayQuery = new FinanceReceiptPayQuery();
        financeReceiptPayQuery.setIsValid(1);
        financeReceiptPayQuery.setPactCode(rentTurnVO.getReletCode());
        financeReceiptPayQuery.setType(0);
        List<Integer> costList = new ArrayList<>();
        costList.add(1);
        costList.add(2);
        financeReceiptPayQuery.setCostList(costList);
        List<FinanceReceiptPayVO> financeReceiptPayVOs = financeReceiptPayManager.listFinanceReceiptPayByPactCode(financeReceiptPayQuery);
        if(null != financeReceiptPayVOs && financeReceiptPayVOs.size() > 0){
            CommonBelongerQuery afterRentBelongerQuery = new CommonBelongerQuery();
            afterRentBelongerQuery.setPactCode(rentTurnVO.getReletCode());
            afterRentBelongerQuery.setUserRole(StringConsts.BELONGER_PERSON);
            CommonBelongerVO guardianBelongerVO = commonBelongerManager.getBelongersByParam(afterRentBelongerQuery);
            for(FinanceReceiptPayVO recPay:financeReceiptPayVOs){
                AgendaDO pendingAgendaDO = new AgendaDO();
                pendingAgendaDO.setServiceCode(String.valueOf(recPay.getId()));
                pendingAgendaDO.setType(StringConsts.STATISTIC_PENDING_RECEIVABLES);
                pendingAgendaDO.setIsDo(0);
                pendingAgendaDO.setPin(guardianBelongerVO.getUserPin());
                pendingAgendaDO.setName(guardianBelongerVO.getUserName());
                pendingAgendaDO.setDeptCode(guardianBelongerVO.getDeptCode());
                pendingAgendaDO.setDeptName(guardianBelongerVO.getDeptName());
                pendingAgendaDO.setDate(new Date());
                statisticsManager.saveBacklogStatistics(pendingAgendaDO);
            }
        }
        /******************** 待办事务统计待收款 （协议中的应收）     end *******************************/
        /******************** 待办事务统计待收款（新合同中的应收）      start *******************************/
        FinanceReceiptPayQuery newPactReceiptPayQuery = new FinanceReceiptPayQuery();
        newPactReceiptPayQuery.setIsValid(1);
        newPactReceiptPayQuery.setPactCode(rentTurnVO.getNewPactCode());
        newPactReceiptPayQuery.setType(0);
        newPactReceiptPayQuery.setCostList(costList);
        List<FinanceReceiptPayVO> newPactReceiptPayVOs = financeReceiptPayManager.listFinanceReceiptPayByPactCode(newPactReceiptPayQuery);
        if(null != newPactReceiptPayVOs && newPactReceiptPayVOs.size() > 0){
            CommonBelongerVO afterBelonger = rentBaseManager.getRentAfterBelonger(rentTurnVO.getNewPactCode());
            for(FinanceReceiptPayVO recPay:newPactReceiptPayVOs){
                AgendaDO pendingAgendaDO = new AgendaDO();
                pendingAgendaDO.setServiceCode(String.valueOf(recPay.getId()));
                pendingAgendaDO.setType(StringConsts.STATISTIC_PENDING_RECEIVABLES);
                pendingAgendaDO.setIsDo(0);
                pendingAgendaDO.setPin(afterBelonger.getUserPin());
                pendingAgendaDO.setName(afterBelonger.getUserName());
                pendingAgendaDO.setDeptCode(afterBelonger.getDeptCode());
                pendingAgendaDO.setDeptName(afterBelonger.getDeptName());
                pendingAgendaDO.setDate(new Date());
                statisticsManager.saveBacklogStatistics(pendingAgendaDO);
            }
        }
        /******************** 待办事务统计待收款（新合同中的应收）      end *******************************/
    }

    @Override
    public ApiResult<Boolean> reviewDismissalRentTurn(RentTurnParam param) {
        ApiResult<Boolean> result = new ApiResult<>();
        if(null==param){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        if(StringUtil.isBlank(param.getReletCode())){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        try{
            RentTurnVO rentTurnVO = rentTurnManager.getRentTurnByReletCode(param.getReletCode());
            if(null!=rentTurnVO){
                if( rentTurnVO.getState()==2 ){
                    RentTurnDO rentTurnDO = new RentTurnDO();
                    BeanUtils.copyProperties(param, rentTurnDO);
                    rentTurnDO.setModifiedName(param.getModifiedName()==null?"":param.getModifiedName());
                    rentTurnDO.setIp(param.getIp());
                    rentTurnDO.setState(4);
                    boolean flag = rentTurnManager.updateRentTurnStateByCode(rentTurnDO);
                    if(flag){
                        List<String> variableList = new ArrayList<>();
                        variableList.add(param.getReletCode());
                        saveRemind(param,StringConsts.REVIEW_DISMISSAL_RENTTURN,variableList);
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put(NormalConstant.SERVICETYPE, SerializeTypeConts.RENTTURN);
                        map.put(NormalConstant.SERVICECODE, rentTurnDO.getReletCode());
                        map.put(NormalConstant.OPERATETYPE, StringConsts.OPERATETYPE_REVIEW_DISMISSAL);
                        map.put(NormalConstant.OPERATECONTENT, param.getReletReason());
                        map.put(NormalConstant.OPERATEPIN, rentTurnDO.getModifiedName());
                        operateLogRpc.saveOperateLog(map);
                        /******************** 修改待办事务统计表的状态  start *******************************/
                        AgendaDO agendaDO = new AgendaDO();
                        agendaDO.setServiceCode(param.getReletCode());
                        agendaDO.setIsDo(1);
                        agendaDO.setType(StringConsts.STATISTIC_PENDING_RENT_TURN);
                        agendaDO.setModifiedName(param.getModifiedName());
                        agendaDO.setIp(param.getIp());
                        statisticsManager.updatBacklogStatisticsIsDo(agendaDO);
                        /******************** 修改待办事务统计表的状态  end *******************************/
                        result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                        result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                        result.setData(flag);
                    }else{
                        result.setCode(PactCommonEnum.RENT_TURN_REVIEWDISMISSAL_ERROR.getCode());
                        result.setMessage(PactCommonEnum.RENT_TURN_REVIEWDISMISSAL_ERROR.getMessage());
                    }
                }else{
                    result.setCode(PactCommonEnum.PACT_REVIEW_DISMISSAL_STATE_ERROR.getCode());
                    result.setMessage(PactCommonEnum.PACT_REVIEW_DISMISSAL_STATE_ERROR.getMessage());
                }
            }
        } catch (PactManagerExcepion e){
            LOG.error(e);
            result.setCode(PactCommonEnum.RENT_TURN_REVIEWDISMISSAL_ERROR.getCode());
            result.setMessage(PactCommonEnum.RENT_TURN_REVIEWDISMISSAL_ERROR.getMessage());
        } catch (Exception e) {
            LOG.error(e);
            result.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            result.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        }
        return result;
    }

    @Override
    public ApiResult<Boolean> updateRentTurn(RentTurnProtocolParam param) {
        ApiResult<Boolean> result = new ApiResult<>();
        //抄表信息
        List<CommonMeterReadDO> commonMeterReadDOs = new ArrayList<>();
        //物品信息
        List<CommonGoodsDO> commonGoodsDOs = new ArrayList<>();
        if(null==param ){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        try {
            String ip = param.getIp() ==null ? "" :param.getIp();
            String pin = param.getCreateName()== null?"":param.getCreateName();
            RentTurnParam rentTurnParam = param.getRentTurnParam();
            RentTurnVO rentTurnVO = rentTurnManager.getRentTurnByReletCode(rentTurnParam.getReletCode());
            if(rentTurnVO.getState()==1 ||rentTurnVO.getState()==4){
                //协议
                RentTurnDO rentTurnDO = new RentTurnDO();
                BeanUtils.copyProperties(param.getRentTurnParam(), rentTurnDO);
                rentTurnDO.setIp(ip);
                rentTurnDO.setModifiedName(pin);
                rentTurnDO.setPirmaryPactCode(rentTurnVO.getPirmaryPactCode());
                rentTurnDO.setNewPactCode(rentTurnVO.getNewPactCode());
                //收支
                List<FinanceReceiptPayDO> financeReceiptPayDOs = new ArrayList<>();
                BeanUtils.copyListProperties(param.getReceiptPayList(), financeReceiptPayDOs, FinanceReceiptPayDO.class);
                if(null!=financeReceiptPayDOs&&financeReceiptPayDOs.size()>0){
                    for(FinanceReceiptPayDO financeReceiptPayDO:financeReceiptPayDOs){
                        financeReceiptPayDO.setPactCode(rentTurnDO.getReletCode());
                        financeReceiptPayDO.setPactType(PactTypeConsts.RENT_SUBLET);
                        financeReceiptPayDO.setCreateName(pin);
                        financeReceiptPayDO.setModifiedName(pin);
                        financeReceiptPayDO.setIp(ip);
                    }
                }
                FinanceReceiptPayDO payeeFormRecPay = getPayeeFormRecPay(financeReceiptPayDOs);
                rentTurnDO.setPayeeName(payeeFormRecPay.getName());
                rentTurnDO.setAccountNum(payeeFormRecPay.getAccount());
                rentTurnDO.setCaseBank(payeeFormRecPay.getBank());
                rentTurnDO.setOpenBank(payeeFormRecPay.getOpenBank());
                rentTurnDO.setTel(payeeFormRecPay.getTel());
                //协议转让费
                List<RentTurnCostDO> rentTurnCostDOs = new ArrayList<>();
                BeanUtils.copyListProperties(param.getRentTurnCostList(), rentTurnCostDOs, RentTurnCostDO.class);
                if(null!=rentTurnCostDOs&&rentTurnCostDOs.size()>0){
                    for(RentTurnCostDO rentTurnCostDO:rentTurnCostDOs){
                        rentTurnCostDO.setReletCode(rentTurnDO.getReletCode());
                        rentTurnCostDO.setCreateName(pin);
                        rentTurnCostDO.setModifiedName(pin);
                        rentTurnCostDO.setIp(ip);
                    }
                }
                //协议照片
                List<CommonPicDO> commonPicDOs = new ArrayList<>();
                BeanUtils.copyListProperties(param.getUrlList(), commonPicDOs, CommonPicDO.class);
                for (CommonPicDO commonPicDO : commonPicDOs) {
                    commonPicDO.setIp(ip);
                    commonPicDO.setCreateName(pin);
                    commonPicDO.setModifiedName(pin);
                    commonPicDO.setPactCode(rentTurnDO.getReletCode());
                    commonPicDO.setPactType(PactTypeConsts.RENT_SUBLET);
                }
                //协议的签约管家+维护人
                // 需要根据签约管家pin通过rpc查询用户信息
                List<CommonBelongerDO> commonBelongerDOs = new ArrayList<>();
                List<CommonBelongerVO> commonBelongerList = commonBelongerManager.listCommonBelonger(rentTurnDO.getReletCode());
                ApiResult<PersonalResult> personalResult = personalRpc.getPersonalResultBypin(rentTurnParam.getDealUserPin());
                PersonalResult personalResultData = personalResult.getData();
                if(null != commonBelongerList){
                    for (CommonBelongerVO commonBelongerVO : commonBelongerList) {
                        CommonBelongerDO commonBelongerDO = new CommonBelongerDO();
                        if(PactTypeConsts.RENT_SUBLET.equals(commonBelongerVO.getPactType())){
                            if(StringConsts.BELONGER_DEAL.equals(commonBelongerVO.getUserRole())){
                                commonBelongerDO.setId(commonBelongerVO.getId());
                            }else if(StringConsts.GUARDIAN.equals(commonBelongerVO.getUserRole())){
                                commonBelongerDO.setId(commonBelongerVO.getId());
                            }
                            commonBelongerDO.setPactCode(rentTurnDO.getReletCode());
                            commonBelongerDO.setUserPin(rentTurnParam.getDealUserPin());
                            commonBelongerDO.setUserName(personalResultData.getEmployeeName());
                            commonBelongerDO.setUserTel(personalResultData.getEmployeeTel());
                            commonBelongerDO.setUserRole(StringConsts.BELONGER_DEAL);
                            commonBelongerDO.setDeptCode(personalResultData.getDeptCode());
                            commonBelongerDO.setDeptName(personalResultData.getDeptName());
                            commonBelongerDO.setIp(ip);
                            commonBelongerDO.setCreateName(pin);
                            commonBelongerDO.setModifiedName(pin);
                            commonBelongerDOs.add(commonBelongerDO);
                        }
                    }
                }
                /******************修改合同的签约管家     start*****************************/
                CommonBelongerQuery pactBelongerQuery = new CommonBelongerQuery();
                pactBelongerQuery.setPactCode(rentTurnDO.getNewPactCode());
                pactBelongerQuery.setUserRole(StringConsts.BELONGER_DEAL);
                CommonBelongerVO pactBelongerVO = commonBelongerManager.getBelongersByParam(pactBelongerQuery);
                CommonBelongerDO pactBelongerDO = new CommonBelongerDO();
                pactBelongerDO.setId(pactBelongerVO.getId());
                pactBelongerDO.setPactCode(rentTurnDO.getNewPactCode());
                pactBelongerDO.setUserPin(rentTurnParam.getDealUserPin());
                pactBelongerDO.setUserName(personalResultData.getEmployeeName());
                pactBelongerDO.setUserTel(personalResultData.getEmployeeTel());
                pactBelongerDO.setUserRole(StringConsts.BELONGER_DEAL);
                pactBelongerDO.setDeptCode(personalResultData.getDeptCode());
                pactBelongerDO.setDeptName(personalResultData.getDeptName());
                pactBelongerDO.setIp(ip);
                pactBelongerDO.setCreateName(pin);
                pactBelongerDO.setModifiedName(pin);
                commonBelongerDOs.add(pactBelongerDO);
                /******************修改合同的签约管家     end*****************************/
               boolean flag = rentTurnManager.updateRentTurnByReletCode(rentTurnDO,financeReceiptPayDOs,
                        rentTurnCostDOs,commonMeterReadDOs,commonGoodsDOs,commonPicDOs,commonBelongerDOs);
               if(flag){
                   Map<String, Object> map = new HashMap<String, Object>();
                   map.put(NormalConstant.SERVICETYPE, SerializeTypeConts.RENTTURN);
                   map.put(NormalConstant.SERVICECODE, rentTurnDO.getReletCode());
                   map.put(NormalConstant.OPERATETYPE, StringConsts.UPDATE);
                   map.put(NormalConstant.OPERATECONTENT, StringConsts.UPDATE_DATA);
                   map.put(NormalConstant.OPERATEPIN,pin);
                   operateLogRpc.saveOperateLog(map);
                   result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                   result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
               }else{
                   result.setCode(PactCommonEnum.RENT_TURN_UPDATE_ERROR.getCode());
                   result.setMessage(PactCommonEnum.RENT_TURN_UPDATE_ERROR.getMessage());
               }
            }else{
                result.setCode(PactCommonEnum.PACT_NOT_UPDATE_DELTER.getCode());
                result.setMessage(PactCommonEnum.PACT_NOT_UPDATE_DELTER.getMessage());
            }
        } catch (PactManagerExcepion e) {
            LOG.error(e);
            result.setCode(PactCommonEnum.RENT_TURN_UPDATE_ERROR.getCode());
            result.setMessage(PactCommonEnum.RENT_TURN_UPDATE_ERROR.getMessage());
        } catch (Exception e) {
            LOG.error(e);
            result.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            result.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        }
        return result;
    }

    /**
     * 
    * @Title: getPayeeFormRecPay
    * @Description: 从收支集合里获取租客收款人信息
    * @author LiuYuChi
    * @param financeReceiptPayList 收支集合
    * @return 租客收款人信息
    * @throws 异常
     */
    private FinanceReceiptPayDO getPayeeFormRecPay(List<FinanceReceiptPayDO> financeReceiptPayList) {
        if(null!=financeReceiptPayList){
            for(FinanceReceiptPayDO financeReceiptPayDO:financeReceiptPayList){
                if(2 == financeReceiptPayDO.getType()&&StringConsts.PAYEE_OBJECT_CUSTOMER.equals(financeReceiptPayDO.getPayeeObject())){
                    return financeReceiptPayDO;
                }
            }
        }
        return new FinanceReceiptPayDO();
    }

    @Override
    public ApiResult<Boolean> removeRentTurn(RentTurnParam param) {
        ApiResult<Boolean> result = new ApiResult<>();
        RentTurnDO rentTurnDO = new RentTurnDO();
        BeanUtils.copyProperties(param, rentTurnDO);
        try {
            RentTurnVO rentTurnVO = rentTurnManager.getRentTurnByReletCode(rentTurnDO.getReletCode());
            rentTurnDO.setNewPactCode(rentTurnVO.getNewPactCode());
            if( rentTurnVO.getState()==1 ||  rentTurnVO.getState()==4){
                boolean flag = rentTurnManager.removeRentTurnByReletCode(rentTurnDO);
                if(flag){
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put(NormalConstant.SERVICETYPE, SerializeTypeConts.RENTTURN);
                    map.put(NormalConstant.SERVICECODE, rentTurnDO.getReletCode());
                    map.put(NormalConstant.OPERATETYPE, StringConsts.DELETE);
                    map.put(NormalConstant.OPERATECONTENT, StringConsts.DELETE_DATA);
                    map.put(NormalConstant.OPERATEPIN, rentTurnDO.getModifiedName());
                    operateLogRpc.saveOperateLog(map);
                    result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                    result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                }else{
                    result.setCode(PactCommonEnum.RENT_TURN_DELETE_ERROR.getCode());
                    result.setMessage(PactCommonEnum.RENT_TURN_DELETE_ERROR.getMessage());
                }
            }else{
                result.setCode(PactCommonEnum.PACT_NOT_UPDATE_DELTER.getCode());
                result.setMessage(PactCommonEnum.PACT_NOT_UPDATE_DELTER.getMessage());
            }
        } catch (PactManagerExcepion e) {
            LOG.error(e);
            result.setCode(PactCommonEnum.RENT_TURN_DELETE_ERROR.getCode());
            result.setMessage(PactCommonEnum.RENT_TURN_DELETE_ERROR.getMessage());
        } catch (Exception e) {
            LOG.error(e);
            result.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            result.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
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
    private void saveRemind(RentTurnParam param, String key, List<String> variableList) {
        String content = remindMap.get(key);
        for (int i = 0; i < variableList.size(); i++) {
            content = content.replace(StringConsts.VARIABLE + i, variableList.get(i));
        }
        CommonBelongerQuery commonBelongerQuery = new CommonBelongerQuery();
        commonBelongerQuery.setPactCode(param.getReletCode());
        commonBelongerQuery.setUserRole(StringConsts.BELONGER_PERSON);
        CommonBelongerVO commonBelongerVO = commonBelongerManager.getBelongersByParam(commonBelongerQuery);
        Map<String, Object> map = new HashMap<>();
        map.put(NormalConstant.REMIND_TITLE, StringConsts.REMIND_AUDITING_TITLE);
        map.put(NormalConstant.REMIND_PIN, commonBelongerVO.getUserPin());
        map.put(NormalConstant.REMIND_SERVICETYPE, SerializeTypeConts.RENTTURN);
        map.put(NormalConstant.REMIND_SERVICECODE, param.getReletCode());
        map.put(NormalConstant.REMIND_REMINDCONTENT,content);
        remindRpc.saveRemind(map);
    }
    
    /**
     * @Title: getPermissions
     * @Description: TODO( 查看登录人有没有这个转租协议的权限 )
     * @author GuoXiaoPeng
     * @param rentBaseParam 转租协议编码和权限作用域
     * @return 有权限返回true，没有权限返回false
      */
     private boolean getPermissions(RentTurnParam param) {
         boolean flag;
         ApiResult<PersonalResult> personalApiResult = personalRpc.getPersonalResultBypin(param.getCreateName());
         PersonalResult loginPersonal = personalApiResult.getData();
         Integer scope = param.getScope();
         RentTurnQuery query = new RentTurnQuery();
         query.setScope(scope);
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
         query.setReletCode(param.getReletCode());
         flag = rentTurnManager.getPermissions(query);
         return flag;
     }
    public RentTurnManager getRentTurnManager() {
        return rentTurnManager;
    }

    public void setRentTurnManager(RentTurnManager rentTurnManager) {
        this.rentTurnManager = rentTurnManager;
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

    public FinanceReceiptPayManager getFinanceReceiptPayManager() {
        return financeReceiptPayManager;
    }

    public void setFinanceReceiptPayManager(FinanceReceiptPayManager financeReceiptPayManager) {
        this.financeReceiptPayManager = financeReceiptPayManager;
    }

    public CommonManager getCommonManager() {
        return commonManager;
    }

    public void setCommonManager(CommonManager commonManager) {
        this.commonManager = commonManager;
    }

    public CommonPicDao getCommonPicDao() {
        return commonPicDao;
    }

    public void setCommonPicDao(CommonPicDao commonPicDao) {
        this.commonPicDao = commonPicDao;
    }

    public RentTurnCostDao getRentTurnCostDao() {
        return rentTurnCostDao;
    }

    public void setRentTurnCostDao(RentTurnCostDao rentTurnCostDao) {
        this.rentTurnCostDao = rentTurnCostDao;
    }

    public CommonBelongerManager getCommonBelongerManager() {
        return commonBelongerManager;
    }

    public void setCommonBelongerManager(CommonBelongerManager commonBelongerManager) {
        this.commonBelongerManager = commonBelongerManager;
    }

    public CommonPicManager getCommonPicManager() {
        return commonPicManager;
    }

    public void setCommonPicManager(CommonPicManager commonPicManager) {
        this.commonPicManager = commonPicManager;
    }

    public SerializeRpc getSerializeRpc() {
        return serializeRpc;
    }

    public void setSerializeRpc(SerializeRpc serializeRpc) {
        this.serializeRpc = serializeRpc;
    }

    public OperateLogRpc getOperateLogRpc() {
        return operateLogRpc;
    }

    public void setOperateLogRpc(OperateLogRpc operateLogRpc) {
        this.operateLogRpc = operateLogRpc;
    }

    public DictionaryRpc getDictionaryRpc() {
        return dictionaryRpc;
    }

    public void setDictionaryRpc(DictionaryRpc dictionaryRpc) {
        this.dictionaryRpc = dictionaryRpc;
    }

    public PersonalRpc getPersonalRpc() {
        return personalRpc;
    }

    public void setPersonalRpc(PersonalRpc personalRpc) {
        this.personalRpc = personalRpc;
    }

    public DeptRpc getDeptRpc() {
        return deptRpc;
    }

    public void setDeptRpc(DeptRpc deptRpc) {
        this.deptRpc = deptRpc;
    }

    public Map<String, String> getRemindMap() {
        return remindMap;
    }

    public void setRemindMap(Map<String, String> remindMap) {
        this.remindMap = remindMap;
    }

    public RemindRpc getRemindRpc() {
        return remindRpc;
    }

    public void setRemindRpc(RemindRpc remindRpc) {
        this.remindRpc = remindRpc;
    }

    public StatisticsManager getStatisticsManager() {
        return statisticsManager;
    }

    public void setStatisticsManager(StatisticsManager statisticsManager) {
        this.statisticsManager = statisticsManager;
    }
}
