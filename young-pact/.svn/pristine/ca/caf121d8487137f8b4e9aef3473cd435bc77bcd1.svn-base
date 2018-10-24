package com.young.pact.service.rentcontinued.impl;

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
import com.young.pact.api.domain.param.rest.financereceiptpay.FinanceReceiptPayParam;
import com.young.pact.api.domain.param.rest.rentbase.RentPactParam;
import com.young.pact.api.domain.param.rest.rentcontinued.RentContinuedParam;
import com.young.pact.api.domain.result.rest.commoncostrule.CommonCostRuleResult;
import com.young.pact.api.domain.result.rest.commonpic.CommonPicResult;
import com.young.pact.api.domain.result.rest.financereceiptpay.FinanceReceiptPayResult;
import com.young.pact.api.domain.result.rest.rentbase.RentBaseResult;
import com.young.pact.api.domain.result.rest.rentbase.RentPactResult;
import com.young.pact.api.domain.result.rest.rentcontinued.RentContinuedDetailResult;
import com.young.pact.api.domain.result.rest.rentcontinued.RentContinuedResult;
import com.young.pact.api.domain.result.rest.rentroom.RoomResult;
import com.young.pact.api.service.rest.rentcontinued.RentContinuedService;
import com.young.pact.common.constant.NormalConstant;
import com.young.pact.common.constant.PactTypeConsts;
import com.young.pact.common.constant.SerializeTypeConts;
import com.young.pact.common.constant.StringConsts;
import com.young.pact.common.dict.PactCommonEnum;
import com.young.pact.common.exception.PactManagerExcepion;
import com.young.pact.common.util.RegexUtil;
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
import com.young.pact.domain.rentcontinued.RentContinuedDO;
import com.young.pact.domain.rentcontinued.RentContinuedQuery;
import com.young.pact.domain.rentcontinued.RentContinuedVO;
import com.young.pact.domain.rentcustomer.RentCustomerDO;
import com.young.pact.domain.rentcustomer.RentCustomerVO;
import com.young.pact.domain.rentroom.RentRoomDO;
import com.young.pact.domain.rentroom.RentRoomVO;
import com.young.pact.domain.statistics.AgendaDO;
import com.young.pact.domain.statistics.StatisticDO;
import com.young.pact.manager.common.CommonManager;
import com.young.pact.manager.commonbelonger.CommonBelongerManager;
import com.young.pact.manager.commonpic.CommonPicManager;
import com.young.pact.manager.financereceiptpay.FinanceReceiptPayManager;
import com.young.pact.manager.pactrenttransfer.PactRentTransferManager;
import com.young.pact.manager.propertytransfer.PropertyTransferManager;
import com.young.pact.manager.rentbase.RentBaseManager;
import com.young.pact.manager.rentcommonowner.CommonOwnerManager;
import com.young.pact.manager.rentcontinued.RentContinuedManager;
import com.young.pact.manager.rentcustomer.RentCustomerManager;
import com.young.pact.manager.rentroom.RentRoomManager;
import com.young.pact.manager.rentturn.RentTurnManager;
import com.young.pact.manager.statistics.StatisticsManager;
import com.young.pact.rpc.dept.DeptRpc;
import com.young.pact.rpc.dictionary.DictionaryRpc;
import com.young.pact.rpc.operatelog.OperateLogRpc;
import com.young.pact.rpc.personal.PersonalRpc;
import com.young.pact.rpc.remind.RemindRpc;
import com.young.pact.rpc.room.RoomRpc;
import com.young.pact.rpc.serialize.SerializeRpc;
import com.young.sso.api.domain.result.rpc.personal.PersonalResult;

/**
 * @描述 : 续签协议
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月10日 下午8:49:31
 */
@Service("rentContinuedService")
public class RentContinuedServiceImpl implements RentContinuedService {

    private static Log LOG = LogFactoryImpl.getLog(RentContinuedServiceImpl.class);

    private RentBaseManager rentBaseManager;
    private RentContinuedManager rentContinuedManager;
    private RentTurnManager rentTurnManager;
    private PactRentTransferManager pactRentTransferManager;
    private CommonManager commonManager;
    private RentRoomManager rentRoomManager;
    private PropertyTransferManager propertyTransferManager;
    private RentCustomerManager rentCustomerManager;
    private CommonOwnerManager commonOwnerManager;
    private CommonPicManager commonPicManager;
    private CommonBelongerManager commonBelongerManager;
    private OperateLogRpc operateLogRpc;
    private PersonalRpc personalRpc;
    private SerializeRpc serializeRpc;
    private DictionaryRpc dictionaryRpc;
    private DeptRpc deptRpc;
    private Map<String,String> remindMap;
    private RemindRpc remindRpc;
    private StatisticsManager statisticsManager;
    private FinanceReceiptPayManager financeReceiptPayManager;
    private RoomRpc roomRpc;
    @SuppressWarnings("unused")
    @Override
    public ApiResult<String> saveRentContinuedRedis(RentContinuedParam param) {
        ApiResult<String> result = new ApiResult<>();
        StringBuilder key = new StringBuilder();
        String pin = ""; 
        try {
            pin = param.getCreateName() == null?"":param.getCreateName();
            key.append(param.getServiceCode()).append(StringConsts.UNDERLINE)
            .append(StringConsts.REDIS_RENT_CONTINUED_KEY).append(StringConsts.UNDERLINE).append(pin);
            if(null == param){
                result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
                result.setMessage(PactCommonEnum.PARAM_IS_NULL.getCode());
                return result;
            }
            RentContinuedDO rentContinuedDO = new RentContinuedDO();
            BeanUtils.copyProperties(param, rentContinuedDO);
            List<CommonPicDO> commonPicDOs = new ArrayList<>();
            if(null!=param.getUrlList()){
                BeanUtils.copyListProperties(param.getUrlList(), commonPicDOs, CommonPicDO.class);
                rentContinuedDO.setUrlList(commonPicDOs);
                rentContinuedManager.saveRentContinuedRedis(rentContinuedDO,key.toString());
            }
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
    public ApiResult<RentContinuedResult> getRentContinuedRedis(String redisKey) {
        ApiResult<RentContinuedResult> result = new ApiResult<>();
        RentContinuedResult rentContinuedResult = new RentContinuedResult();
        try {
            RentContinuedVO rentTurnProtocolVO = rentContinuedManager.getRentContinuedRedis(redisKey);
            if(null!=rentTurnProtocolVO){
                BeanUtils.copyProperties(rentTurnProtocolVO, rentContinuedResult);
                List<CommonPicResult> commonPicResults = new ArrayList<>();
                BeanUtils.copyListProperties(rentTurnProtocolVO.getUrlList(), commonPicResults, CommonPicResult.class);
                rentContinuedResult.setUrlList(commonPicResults);
            }
            result.setData(rentContinuedResult);
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
    public ApiResult<String> saveRentContinuedPactRedis(RentPactParam param) {
        ApiResult<String> result = new ApiResult<>();
        StringBuilder key = new StringBuilder();
        String pin = ""; 
        try {
            pin = param.getCreateName() == null?"":param.getCreateName();
            key.append(param.getServiceCode()).append(StringConsts.UNDERLINE)
            .append(StringConsts.REDIS_RENT_CONTINUED_PACT_KEY).append(StringConsts.UNDERLINE).append(pin);
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
            BeanUtils.copyListProperties(param.getPactReceiptPayList(), commonCostRuleDOs, CommonCostRuleDO.class);
            BeanUtils.copyListProperties(param.getReceiptPayList(), financeReceiptPayDOs, FinanceReceiptPayDO.class);
            if(null!=param.getUrlList()){
                BeanUtils.copyListProperties(param.getUrlList(), commonPicDOs, CommonPicDO.class);
            }
            rentPactDO.setPactReceiptPayList(commonCostRuleDOs);
            rentPactDO.setReceiptPayList(financeReceiptPayDOs);
            rentPactDO.setUrlList(commonPicDOs);
            rentContinuedManager.saveRentContinuedPactRedis(key.toString(), rentPactDO);
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
    public ApiResult<RentPactResult> getRentContinuedPactRedis(String redisKey) {
        ApiResult<RentPactResult> result = new ApiResult<>();
        try {
            RentPactVO rentPactVO = rentContinuedManager.getRentContinuedPactRedis(redisKey);
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
    public ApiResult<String> saveRentContinuedPact(RentContinuedParam param) {
        ApiResult<String> result = new ApiResult<>();
        StringBuilder rentContinuedRedisKey = new StringBuilder("");
        StringBuilder pactRedisKey = new StringBuilder("");
        List<CommonCostRuleDO> newPactReceiptPayList = new ArrayList<>();
        List<FinanceReceiptPayDO> newReceiptPayList = new ArrayList<>();
        List<CommonBelongerDO> commonBelongerDOList = new ArrayList<>();
        List<CommonPicDO> commonPicDOs = new ArrayList<>();
        List<CommonPicDO> protocolCommonPicDOs = new ArrayList<>();
        List<CommonPicDO> pactCommonPicDOs = new ArrayList<>();
        try {
            //原托出合同编码
            String rentPactCode = param.getServiceCode();
            //续签协议编码
            String renewCode = "";
            //新托出合同编码
            String newPactCode = "";
            renewCode = serializeRpc.queryCodeBySerializeType(SerializeTypeConts.RENEW);
            newPactCode = serializeRpc.queryCodeBySerializeType(SerializeTypeConts.RENT);
            String pin = param.getCreateName()==null?"":param.getCreateName();
            String ip = param.getIp();
            String costCode = "";
            costCode = serializeRpc.queryCodeBySerializeType(SerializeTypeConts.COST);
            if(null == param || StringUtil.isBlank(rentPactCode)){
                result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
                result.setMessage(PactCommonEnum.PARAM_IS_NULL.getCode());
                return result;
            }
            RentBaseVO rentBaseVO = rentBaseManager.getRentBaseByCode(rentPactCode);
            Integer protocolCount = rentContinuedManager.countAllProtocolByPactCode(rentPactCode);
            if(1 ==rentBaseVO.getDueState()|| 1 == rentBaseVO.getCancelState()
                    ||protocolCount > 0){
                rentContinuedRedisKey.append(rentPactCode).append(StringConsts.UNDERLINE)
                .append(StringConsts.REDIS_RENT_CONTINUED_KEY).append(StringConsts.UNDERLINE).append(pin);
                RentContinuedVO rentContinuedVO = rentContinuedManager.getRentContinuedRedis(rentContinuedRedisKey.toString());
                if(null == rentContinuedVO){
                    result.setCode(PactCommonEnum.RENT_CONTINUED_CACHE_INVALID.getCode());
                    result.setMessage(PactCommonEnum.RENT_CONTINUED_CACHE_INVALID.getMessage());
                    return result;
                }
                pactRedisKey.append(rentPactCode).append(StringConsts.UNDERLINE)
                .append(StringConsts.REDIS_RENT_CONTINUED_PACT_KEY).append(StringConsts.UNDERLINE).append(pin);
                RentPactVO rentPactVO = rentContinuedManager.getRentContinuedPactRedis(pactRedisKey.toString());
                if(null == rentPactVO){
                    result.setCode(PactCommonEnum.RENT_CONTINUED_PACT_CACHE_INVALID.getCode());
                    result.setMessage(PactCommonEnum.RENT_CONTINUED_PACT_CACHE_INVALID.getMessage());
                    return result;
                }
                if(null == rentPactVO.getReceiptPayList()){
                    result.setCode(PactCommonEnum.RENT_CONTINUED_PACT_RECPAY_IS_NULL.getCode());
                    result.setMessage(PactCommonEnum.RENT_CONTINUED_PACT_RECPAY_IS_NULL.getMessage());
                    return result;
                }
                //老合同房间
                RentRoomVO oldPactRentRoomVO = rentRoomManager.getRommByRentCode(rentPactCode);
                //新合同房间
                RentRoomDO rentRoomDO = new RentRoomDO();
                if(null!=oldPactRentRoomVO){
                    BeanUtils.copyProperties(oldPactRentRoomVO, rentRoomDO);
                }
                rentRoomDO.setRentPactCode(newPactCode);
                rentRoomDO.setCreateName(pin);
                rentRoomDO.setIp(ip);
                /***************************续签协议   start*****************************/
                //续签协议
                RentContinuedDO rentContinuedDO = new RentContinuedDO();
                BeanUtils.copyProperties(rentContinuedVO, rentContinuedDO);
                rentContinuedDO.setUrlList(commonPicDOs);
                rentContinuedDO.setIp(ip);
                rentContinuedDO.setCreateName(pin);
                rentContinuedDO.setRenewCode(renewCode);
                rentContinuedDO.setAddress(oldPactRentRoomVO.getAddress());
                rentContinuedDO.setNativePlance(StringConsts.ORIGINAL_CONDITION);
                rentContinuedDO.setPirmaryPactCode(rentPactCode);
                rentContinuedDO.setNewPactCode(newPactCode);
                rentContinuedDO.setState(1);
                rentContinuedDO.setIsDelete(0);
                rentContinuedDO.setRenewTime(DateUtil.getDayByDay(rentBaseVO.getEndTime(),1));
                List<CommonPicDO> rentContinuedPicDOs = new ArrayList<>();
                if(null!=rentContinuedVO.getUrlList()&&rentContinuedVO.getUrlList().size()>0){
                    BeanUtils.copyListProperties(rentContinuedVO.getUrlList(), rentContinuedPicDOs, CommonPicDO.class);
                }
                rentContinuedDO.setUrlList(rentContinuedPicDOs);
                if(null != rentContinuedDO.getUrlList()&&rentContinuedDO.getUrlList().size()>0){
                    for (CommonPicDO commonPicDO : rentContinuedDO.getUrlList()) {
                        commonPicDO.setIp(ip);
                        commonPicDO.setCreateName(pin);
                        commonPicDO.setPactCode(renewCode);
                        commonPicDO.setPactType(PactTypeConsts.RENT_RENEW);
                    }
                }
                
                //抄表信息
                List<CommonMeterReadVO> oldCommonMeterReads = propertyTransferManager.listCommonMeterReadByPactCode(rentPactCode);
                List<CommonMeterReadDO> protocolCommonMeterReadDOs = new ArrayList<>();
                if(null!=oldCommonMeterReads&&oldCommonMeterReads.size()>0){
                    for(CommonMeterReadVO commonMeterReadVO:oldCommonMeterReads){
                        List<CommonMeterReadPicDO> commonMeterReadPicDOs = new ArrayList<>();
                        BeanUtils.copyListProperties(commonMeterReadVO.getCommonMeterReadPicList(), commonMeterReadPicDOs, CommonMeterReadPicDO.class);
                        CommonMeterReadDO protocolCommonMeterReadDO = new CommonMeterReadDO();
                        BeanUtils.copyProperties(commonMeterReadVO, protocolCommonMeterReadDO);
                        protocolCommonMeterReadDO.setPactCode(renewCode);
                        protocolCommonMeterReadDO.setPactType(PactTypeConsts.RENT_RENEW);
                        protocolCommonMeterReadDO.setCreateName(pin);
                        protocolCommonMeterReadDO.setIp(ip);
                        protocolCommonMeterReadDO.setCommonMeterReadPicList(commonMeterReadPicDOs);
                        protocolCommonMeterReadDOs.add(protocolCommonMeterReadDO);
                    }
                }
                //物品信息
                List<GoodsVO> oldCommonGoods = propertyTransferManager.listCommonGoodsByPactCode(rentPactCode);
                List<CommonGoodsDO> protocolCommonGoodsDOs = new ArrayList<>();
                if(null!=oldCommonGoods&&oldCommonGoods.size()>0){
                    for(GoodsVO goodsVO:oldCommonGoods){
                        List <CommonGoodsPicDO> commonGoodsPicDOs = new ArrayList<>();
                        BeanUtils.copyListProperties(goodsVO.getCommonGoodsPicList(), commonGoodsPicDOs,CommonGoodsPicDO.class);
                        CommonGoodsDO protocolCommonGoodsDO = new CommonGoodsDO();
                        BeanUtils.copyProperties(goodsVO, protocolCommonGoodsDO);
                        protocolCommonGoodsDO.setPactCode(renewCode);
                        protocolCommonGoodsDO.setPactType(PactTypeConsts.RENT_RENEW);
                        protocolCommonGoodsDO.setCreateName(pin);
                        protocolCommonGoodsDO.setIp(ip);
                        protocolCommonGoodsDO.setCommonGoodsPicList(commonGoodsPicDOs);
                        protocolCommonGoodsDOs.add(protocolCommonGoodsDO);
                    }
                }
                //签约管家
                CommonBelongerDO protocolCommonBelongerDO = getCommonBelongerDO(rentContinuedDO.getDealUserPin(),ip,pin);
                protocolCommonBelongerDO.setPactCode(renewCode);
                protocolCommonBelongerDO.setPactType(PactTypeConsts.RENT_RENEW);
                protocolCommonBelongerDO.setUserRole(StringConsts.BELONGER_DEAL);
                //维护人
                CommonBelongerDO guardianCommonBelongerDO = getCommonBelongerDO(rentContinuedDO.getDealUserPin(),ip,pin);
                guardianCommonBelongerDO.setPactCode(renewCode);
                guardianCommonBelongerDO.setPactType(PactTypeConsts.RENT_RENEW);
                guardianCommonBelongerDO.setUserRole(StringConsts.GUARDIAN);
                //协议照片
                for(CommonPicDO commonPicDO:rentContinuedDO.getUrlList()){
                    CommonPicDO  protocolCommonPic = new CommonPicDO();
                    BeanUtils.copyProperties(commonPicDO, protocolCommonPic);
                    protocolCommonPic.setCreateName(pin);
                    protocolCommonPic.setIp(ip);
                    protocolCommonPic.setPactCode(renewCode);
                    protocolCommonPic.setPactType(PactTypeConsts.RENT_RENEW);
                    protocolCommonPicDOs.add(protocolCommonPic);
                }
                /***************************续签协议   end*****************************/
                /***************************新合同  start*****************************/
                //托出合同
                RentBaseDO rentBaseDO = new RentBaseDO();
                BeanUtils.copyProperties(rentPactVO.getPact(), rentBaseDO);
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
                rentBaseDO.setRentPactCode(newPactCode);
                rentBaseDO.setState(1);
                rentBaseDO.setDueState(1);
                rentBaseDO.setDealState(4);
                rentBaseDO.setCancelState(1);
                rentBaseDO.setIsDelete(0);
                rentBaseDO.setIsEffective(1);
                rentBaseDO.setIp(ip);
                rentBaseDO.setCreateName(pin);
                rentBaseDO.setPrice(rentBaseVO.getPrice());
                rentBaseDO.setDeposit(rentBaseVO.getDeposit());
                rentBaseDO.setPayDays(rentBaseVO.getPayDays());
                rentBaseDO.setFirstPayTime(rentBaseVO.getFirstPayTime());
                rentBaseDO.setMonthlyPayment(rentBaseVO.getMonthlyPayment());
                rentBaseDO.setSigningTime(rentBaseVO.getSigningTime());
                // 新合同收支规则
                BeanUtils.copyListProperties(rentPactVO.getPactReceiptPayList(), newPactReceiptPayList, CommonCostRuleDO.class);
                if(null!=newPactReceiptPayList){
                    for(CommonCostRuleDO commonCostRuleDO:newPactReceiptPayList){
                        commonCostRuleDO.setPactCode(newPactCode);
                        commonCostRuleDO.setPactType(PactTypeConsts.RENT);
                        commonCostRuleDO.setCostCode(costCode);
                        commonCostRuleDO.setCreateName(pin);
                        commonCostRuleDO.setIp(ip);
                    }
                }
                // 新合同收支
                BeanUtils.copyListProperties(rentPactVO.getReceiptPayList(), newReceiptPayList, FinanceReceiptPayDO.class);
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
                //签约管家
                CommonBelongerDO dealCommonBelongerDO = getCommonBelongerDO(rentContinuedDO.getDealUserPin(),ip,pin);
                dealCommonBelongerDO.setPactCode(newPactCode);
                dealCommonBelongerDO.setPactType(PactTypeConsts.RENT);
                dealCommonBelongerDO.setUserRole(StringConsts.BELONGER_DEAL);
                
                //抄表信息
                List<CommonMeterReadDO> newCommonMeterReadDOs = new ArrayList<>();
                if(null!=oldCommonMeterReads&&oldCommonMeterReads.size()>0){
                    for(CommonMeterReadVO commonMeterReadVO:oldCommonMeterReads){
                        List<CommonMeterReadPicDO> commonMeterReadPicDOs = new ArrayList<>();
                        BeanUtils.copyListProperties(commonMeterReadVO.getCommonMeterReadPicList(), commonMeterReadPicDOs, CommonMeterReadPicDO.class);
                        CommonMeterReadDO newCommonMeterReadDO = new CommonMeterReadDO();
                        BeanUtils.copyProperties(commonMeterReadVO, newCommonMeterReadDO);
                        newCommonMeterReadDO.setPactCode(newPactCode);
                        newCommonMeterReadDO.setPactType(PactTypeConsts.RENT);
                        newCommonMeterReadDO.setCreateName(pin);
                        newCommonMeterReadDO.setIp(ip);
                        newCommonMeterReadDO.setCommonMeterReadPicList(commonMeterReadPicDOs);
                        newCommonMeterReadDOs.add(newCommonMeterReadDO);
                    }
                }
                //物品信息
                List<CommonGoodsDO> newCommonGoodsDOs = new ArrayList<>();
                if(null!=oldCommonGoods&&oldCommonGoods.size()>0){
                    for(GoodsVO goodsVO:oldCommonGoods){
                        List <CommonGoodsPicDO> commonGoodsPicDOs = new ArrayList<>();
                        BeanUtils.copyListProperties(goodsVO.getCommonGoodsPicList(), commonGoodsPicDOs,CommonGoodsPicDO.class);
                        CommonGoodsDO newCommonGoodsDO = new CommonGoodsDO();
                        BeanUtils.copyProperties(goodsVO, newCommonGoodsDO);
                        newCommonGoodsDO.setPactCode(newPactCode);
                        newCommonGoodsDO.setPactType(PactTypeConsts.RENT);
                        newCommonGoodsDO.setCreateName(pin);
                        newCommonGoodsDO.setIp(ip);
                        newCommonGoodsDO.setCommonGoodsPicList(commonGoodsPicDOs);
                        newCommonGoodsDOs.add(newCommonGoodsDO);
                    }
                }
                //租客
                RentCustomerVO oldRentCustomer = rentCustomerManager.getRentCustomerByPactCode(rentPactCode);
                RentCustomerDO rentCustomerDO = new RentCustomerDO();
                BeanUtils.copyProperties(oldRentCustomer, rentCustomerDO);
                rentCustomerDO.setRentPactCode(newPactCode);
                rentCustomerDO.setCreateName(pin);
                rentCustomerDO.setIp(ip);
                //合同照片
                for(CommonPicDO commonPicDO:rentContinuedDO.getUrlList()){
                    CommonPicDO newPactPicDO = new CommonPicDO();
                    BeanUtils.copyProperties(commonPicDO, newPactPicDO);
                    newPactPicDO.setCreateName(pin);
                    newPactPicDO.setIp(ip);
                    newPactPicDO.setPactCode(newPactCode);
                    newPactPicDO.setPactType(PactTypeConsts.RENT);
                    pactCommonPicDOs.add(newPactPicDO);
                }
                commonPicDOs.addAll(protocolCommonPicDOs);
                commonPicDOs.addAll(pactCommonPicDOs);
                /***************************新合同  end*****************************/
                //合同负责人
                commonBelongerDOList.add(protocolCommonBelongerDO);
                commonBelongerDOList.add(guardianCommonBelongerDO);
                commonBelongerDOList.add(dealCommonBelongerDO);
                boolean flag = rentContinuedManager.saveRentContinuedPact(rentContinuedDO,protocolCommonMeterReadDOs,protocolCommonGoodsDOs,
                        rentBaseDO,newPactReceiptPayList,newReceiptPayList,newCommonMeterReadDOs,newCommonGoodsDOs,rentCustomerDO,commonPicDOs,commonBelongerDOList,rentRoomDO);
                if(flag){
                    String [] delRedisKey = new String []{rentContinuedRedisKey.toString(),
                            pactRedisKey.toString()}; 
                    commonManager.deleteRedis(delRedisKey);
                    /*---------发一个操作日志  未填充----start------*/
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put(NormalConstant.SERVICETYPE, SerializeTypeConts.RENEW);
                    map.put(NormalConstant.SERVICECODE, rentContinuedDO.getRenewCode());
                    map.put(NormalConstant.OPERATETYPE, StringConsts.ADD);
                    map.put(NormalConstant.OPERATECONTENT, StringConsts.ADD_DATA);
                    map.put(NormalConstant.OPERATEPIN, rentBaseDO.getCreateName());
                    operateLogRpc.saveOperateLog(map);
                    /*---------发一个操作日志  未填充----end------*/
                    result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                    result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                    result.setData(renewCode);
                }else{
                    result.setCode(PactCommonEnum.RENT_CONTINUED_SAVE_FAIL.getCode());
                    result.setMessage(PactCommonEnum.RENT_CONTINUED_SAVE_FAIL.getMessage());
                }
            }else{
                result.setCode(PactCommonEnum.RENT_BASE_STATE_NO_CONTINUED.getCode());
                result.setMessage(PactCommonEnum.RENT_BASE_STATE_NO_CONTINUED.getMessage());
                return result;
            }
        } catch(PactManagerExcepion e){
            LOG.error(e.getMessage(), e);
            result.setCode(PactCommonEnum.RENT_CONTINUED_SAVE_FAIL.getCode());
            result.setMessage(PactCommonEnum.RENT_CONTINUED_SAVE_FAIL.getMessage());
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            result.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            result.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        }
        return result;
    }
    /**
    * @Title: getCommonBelongerDO
    * @Description: TODO( 根据签约管家PIN查询用户)
    * @author GuoXiaoPeng
    * @param dealUserPin 签约管家PIN
    * @param pin 创建人
    * @param ip 登录人ip地址
    * @return 用户信息
    * @throws 异常
     */

    private CommonBelongerDO getCommonBelongerDO(String dealUserPin, String ip, String pin) {
        // 需要根据用户pin通过rpc查询用户信息
        ApiResult<PersonalResult> personalResult = personalRpc.getPersonalResultBypin(dealUserPin);
        PersonalResult personalResultData = personalResult.getData();
        CommonBelongerDO newPactCommonBelongerDO = new CommonBelongerDO();
        newPactCommonBelongerDO.setUserPin(dealUserPin);
        newPactCommonBelongerDO.setUserName(personalResultData.getEmployeeName());
        newPactCommonBelongerDO.setUserTel(personalResultData.getEmployeeTel());
        newPactCommonBelongerDO.setUserRole(StringConsts.RENT_USER_ROLE);
        newPactCommonBelongerDO.setDeptName(personalResultData.getDeptName());
        newPactCommonBelongerDO.setDeptCode(personalResultData.getDeptCode());
        newPactCommonBelongerDO.setIp(ip);
        newPactCommonBelongerDO.setCreateName(pin);
        return newPactCommonBelongerDO;
    }

    @Override
    public ApiResult<PageBean<RentContinuedResult>> listRentContinued(RentContinuedParam param) {
        ApiResult<PageBean<RentContinuedResult>> result = new ApiResult<>();
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
            if(StringUtil.isNotBlank(param.getStartDealTime())
                    && StringUtil.isNotBlank(param.getEndDealTime()) ){
                param.setStartDealTime(param.getStartDealTime() + " 00:00:00");
                param.setEndDealTime(param.getEndDealTime() + " 23:59:59");
            }
            RentContinuedQuery rentContinuedQuery = new RentContinuedQuery();
            BeanUtils.copyProperties(param, rentContinuedQuery);
            ApiResult<PersonalResult> personalApiResult = personalRpc.getPersonalResultBypin(param.getCreateName());
            PersonalResult personalResult = personalApiResult.getData();
            Integer scope = param.getScope();
            if(1 == scope){// 1=本人
                rentContinuedQuery.setUserPin(param.getCreateName());
            }else if(2 == scope){// 2=本部门
                rentContinuedQuery.setDeptCode(personalResult.getDeptCode());
            }else if(3 == scope){//3=本人及下属
                List<String> pinList = personalRpc.listLowerByPin(param.getCreateName());
                rentContinuedQuery.setPinList(pinList);
            }else if(4 == scope){//4=本部门及下属部门
                List<String> deptList = deptRpc.listCodeByDeptCode(personalResult.getDeptCode());
                rentContinuedQuery.setDeptList(deptList);
            }
            PageBean<RentContinuedVO> pageBean = rentContinuedManager.listParam(rentContinuedQuery);
            PageBean<RentContinuedResult> page = new PageTableBean<>(rentContinuedQuery.getPageIndex(), rentContinuedQuery.getPageSize());
            page.setTotalItem(pageBean.getTotalItem());
            BeanUtils.copyListProperties(pageBean.getData(), page.getData(), RentContinuedResult.class);
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
    public ApiResult<RentContinuedDetailResult> getRentContinued(RentContinuedParam param) {
        ApiResult<RentContinuedDetailResult> result = new ApiResult<>();
        RentContinuedDetailResult rentContinuedDetailResult = new RentContinuedDetailResult();
        try {
            String renewCode = param.getRenewCode();
            boolean permissionsFlag = getPermissions(param);
            if(!permissionsFlag){
                result.setCode(PactCommonEnum.RENTCONTINUED_NO_PERMISSIONS.getCode());
                result.setMessage(PactCommonEnum.RENTCONTINUED_NO_PERMISSIONS.getMessage());
                return result;
            }
            RentContinuedVO rentContinuedVO =rentContinuedManager.getRentContinuedByRenewCode(renewCode);
            RentRoomVO rentRoomVO = rentRoomManager.getRommByRentCode(rentContinuedVO.getPirmaryPactCode());
            RentBaseVO oldBaseVO = rentBaseManager.getRentBaseByCode(rentContinuedVO.getPirmaryPactCode());
            RentBaseVO newBaseVO = rentBaseManager.getRentBaseByCode(rentContinuedVO.getNewPactCode());
            List<CommonPicVO> commonPicVOs = commonPicManager.listCommonPicByPactCode(renewCode);
            RentCustomerVO oldRentCustomerVO = rentCustomerManager.getRentCustomerByPactCode(rentContinuedVO.getPirmaryPactCode());
            RentCustomerVO newRentCustomerVO = rentCustomerManager.getRentCustomerByPactCode(rentContinuedVO.getNewPactCode());
            //续签信息
            RentContinuedResult rentContinuedResult = new RentContinuedResult();
            BeanUtils.copyProperties(rentContinuedVO, rentContinuedResult);
            rentContinuedDetailResult.setRentContinuedResult(rentContinuedResult);
            //协议照片(集合)
            List<CommonPicResult> urlList = new ArrayList<>();
            BeanUtils.copyListProperties(commonPicVOs, urlList, CommonPicResult.class);
            rentContinuedDetailResult.setUrlList(urlList);
            //转租房间
            RoomResult roomResult = new RoomResult();
            BeanUtils.copyProperties(rentRoomVO, roomResult);
            rentContinuedDetailResult.setRoomResult(roomResult);
            //老合同信息
            RentBaseResult oldPact = new RentBaseResult();
            BeanUtils.copyProperties(oldBaseVO, oldPact);
            oldPact.setRenterCode(oldRentCustomerVO.getRenterCode());
            oldPact.setRenterName(oldRentCustomerVO.getName());
            oldPact.setRenterTel(oldRentCustomerVO.getTel());
            rentContinuedDetailResult.setOldPact(oldPact);
            //新合同信息
            RentBaseResult newPact = new RentBaseResult();
            BeanUtils.copyProperties(newBaseVO, newPact);
            newPact.setRenterCode(newRentCustomerVO.getRenterCode());
            newPact.setRenterName(newRentCustomerVO.getName());
            newPact.setRenterTel(newRentCustomerVO.getTel());
            rentContinuedDetailResult.setNewPact(newPact);
            result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
            result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
            result.setData(rentContinuedDetailResult);
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
    public ApiResult<Boolean> auditRentContinued(RentContinuedParam param) {
        ApiResult<Boolean> result = new ApiResult<>();
        if(null==param){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        if(StringUtil.isBlank(param.getRenewCode())){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        try{
            RentContinuedVO rentContinuedVO = rentContinuedManager.getRentContinuedByRenewCode(param.getRenewCode());
            if(null!=rentContinuedVO){
                if( rentContinuedVO.getState()==1 || rentContinuedVO.getState()==4){
                    RentContinuedDO rentContinuedDO = new RentContinuedDO();
                    BeanUtils.copyProperties(param, rentContinuedDO);
                    rentContinuedDO.setModifiedName(param.getModifiedName()==null?"":param.getModifiedName());
                    rentContinuedDO.setIp(param.getIp());
                    rentContinuedDO.setState(2);
                    boolean flag = rentContinuedManager.updateRentContinuedStateByCode(rentContinuedDO);
                    if(flag){
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put(NormalConstant.SERVICETYPE, SerializeTypeConts.RENEW);
                        map.put(NormalConstant.SERVICECODE, rentContinuedDO.getRenewCode());
                        map.put(NormalConstant.OPERATETYPE, StringConsts.OPERATETYPE_APPLICATION_AUDIT);
                        map.put(NormalConstant.OPERATECONTENT, StringConsts.AUDIT_DATA);
                        map.put(NormalConstant.OPERATEPIN, rentContinuedDO.getModifiedName());
                        operateLogRpc.saveOperateLog(map);
                        /*************************首页待办数据统计  start*****************************/
                        CommonBelongerQuery commonBelongerQuery = new CommonBelongerQuery();
                        commonBelongerQuery.setPactCode(param.getRenewCode());
                        commonBelongerQuery.setUserRole(StringConsts.BELONGER_DEAL);
                        CommonBelongerVO commonBelongerVO = commonBelongerManager.getBelongersByParam(commonBelongerQuery);
                        AgendaDO agendaDO = new AgendaDO();
                        agendaDO.setServiceCode(param.getRenewCode());
                        agendaDO.setType(StringConsts.STATISTIC_PENDING_RENT_CONTINUED);
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
                        result.setCode(PactCommonEnum.RENT_CONTINUED_AUDIT_ERROR.getCode());
                        result.setMessage(PactCommonEnum.RENT_CONTINUED_AUDIT_ERROR.getMessage());
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
    public ApiResult<Boolean> reviewPassRentContinued(RentContinuedParam param) {
        ApiResult<Boolean> result = new ApiResult<>();
        if(null==param){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        if(StringUtil.isBlank(param.getRenewCode())){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        try{
            RentContinuedVO rentContinuedVO = rentContinuedManager.getRentContinuedByRenewCode(param.getRenewCode());
            if(null!=rentContinuedVO){
                RentBaseVO rentBaseVO = rentBaseManager.getRentBaseByCode(rentContinuedVO.getPirmaryPactCode());
                result =  checkData(rentBaseVO);
                if(!result.getData()){
                    return result;
                }
                if( rentContinuedVO.getState()==2){
                    RentContinuedDO rentContinuedDO = new RentContinuedDO();
                    BeanUtils.copyProperties(param, rentContinuedDO);
                    rentContinuedDO.setPirmaryPactCode(rentContinuedVO.getPirmaryPactCode());
                    rentContinuedDO.setNewPactCode(rentContinuedVO.getNewPactCode());
                    rentContinuedDO.setRenewTime(rentContinuedVO.getRenewTime());
                    rentContinuedDO.setModifiedName(param.getModifiedName()==null?"":param.getModifiedName());
                    rentContinuedDO.setIp(param.getIp());
                    rentContinuedDO.setState(3);
                    boolean flag = rentContinuedManager.updateRentContinuedStateByCode(rentContinuedDO);
                    if(flag){
                        List<String> variableList = new ArrayList<>();
                        variableList.add(param.getRenewCode());
                        saveRemind(param,StringConsts.REVIEW_PASS_RENTCONTINUED,variableList);
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put(NormalConstant.SERVICETYPE, SerializeTypeConts.RENEW);
                        map.put(NormalConstant.SERVICECODE, rentContinuedDO.getRenewCode());
                        map.put(NormalConstant.OPERATETYPE, StringConsts.OPERATETYPE_REVIEW_PASS);
                        map.put(NormalConstant.OPERATECONTENT, StringConsts.REVIEWPASS_DATA);
                        map.put(NormalConstant.OPERATEPIN, rentContinuedDO.getModifiedName());
                        operateLogRpc.saveOperateLog(map);
                        /****************************保存交易统计续租量   start**********************************/
                        CommonBelongerQuery commonBelongerQuery = new CommonBelongerQuery();
                        commonBelongerQuery.setPactCode(param.getRenewCode());
                        commonBelongerQuery.setUserRole(StringConsts.BELONGER_DEAL);
                        CommonBelongerVO commonBelongerVO = commonBelongerManager.getBelongersByParam(commonBelongerQuery);
                        RentRoomVO rentRoomVO = rentRoomManager.getRommByRentCode(rentContinuedVO.getNewPactCode());
                        com.young.product.api.domain.result.rpc.room.RoomResult roomRpcResult = roomRpc.getRoomByCode(rentRoomVO.getRoomCode());
                        if(StringConsts.WHOLE_RENT.equals(roomRpcResult.getRentWay())){
                            for(int i = 0;i < Integer.valueOf(roomRpcResult.getRoom()); i++){
                                StatisticDO statisticDO = new StatisticDO();
                                statisticDO.setServiceCode(param.getRenewCode());
                                statisticDO.setPactType(StringConsts.RENEWAL_OF_RENT_COUNT);
                                statisticDO.setPin(commonBelongerVO.getUserPin());
                                statisticDO.setName(commonBelongerVO.getUserName());
                                statisticDO.setDeptCode(commonBelongerVO.getDeptCode());
                                statisticDO.setDeptName(commonBelongerVO.getDeptName());
                                statisticsManager.savePactRoomStatistics(statisticDO);
                            }
                        }else if(StringConsts.JOINT_RENT.equals(roomRpcResult.getRentWay())){
                            StatisticDO statisticDO = new StatisticDO();
                            statisticDO.setServiceCode(param.getRenewCode());
                            statisticDO.setPactType(StringConsts.RENEWAL_OF_RENT_COUNT);
                            statisticDO.setPin(commonBelongerVO.getUserPin());
                            statisticDO.setName(commonBelongerVO.getUserName());
                            statisticDO.setDeptCode(commonBelongerVO.getDeptCode());
                            statisticDO.setDeptName(commonBelongerVO.getDeptName());
                            statisticsManager.savePactRoomStatistics(statisticDO);
                        }
                        /****************************保存交易统计续租量   end**********************************/
                        /******************** 修改待办事务统计表的状态  start *******************************/
                        AgendaDO agendaDO = new AgendaDO();
                        agendaDO.setServiceCode(param.getRenewCode());
                        agendaDO.setIsDo(1);
                        agendaDO.setType(StringConsts.STATISTIC_PENDING_RENT_CONTINUED);
                        agendaDO.setModifiedName(param.getModifiedName());
                        agendaDO.setIp(param.getIp());
                        statisticsManager.updatBacklogStatisticsIsDo(agendaDO);
                        /******************** 修改待办事务统计表的状态  end *******************************/
                        saveReceivablesStatistics(rentContinuedVO);
                        saveObligationStatistics(rentContinuedVO);
                        removeStatistics(rentContinuedVO);
                        result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                        result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                        result.setData(flag);
                    }else{
                        result.setCode(PactCommonEnum.RENT_CONTINUED_REVIEWPASS_ERROR.getCode());
                        result.setMessage(PactCommonEnum.RENT_CONTINUED_REVIEWPASS_ERROR.getMessage());
                    }
                }else{
                    result.setCode(PactCommonEnum.PACT_AUDIT_STATE_ERROR.getCode());
                    result.setMessage(PactCommonEnum.PACT_AUDIT_STATE_ERROR.getMessage());
                }
            }
        } catch (PactManagerExcepion e){
            LOG.error(e);
            result.setCode(PactCommonEnum.RENT_CONTINUED_REVIEWPASS_ERROR.getCode());
            result.setMessage(PactCommonEnum.RENT_CONTINUED_REVIEWPASS_ERROR.getMessage());
        } catch (Exception e) {
            LOG.error(e);
            result.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            result.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        }
        return result;
    }
    /**
     * 
    * @Title: removeStatistics
    * @Description: TODO( 删除待办事务统计待收款+待付款记录 )
    * @author GuoXiaoPeng
    * @param rentContinuedVO 续签协议信息
    * @throws 异常
     */
    private void removeStatistics(RentContinuedVO rentContinuedVO) {
        /******************** 待办事务统计待收款（老合同的应收删除）      start *******************************/
        List<FinanceReceiptPayVO> delReceiptPayVOS = null;
        List<FinanceReceiptPayVO> delPendingPaymentList  = null;
        List<AgendaDO> deleteList = new ArrayList<>();
        FinanceReceiptPayQuery oldPactReceiptPayQuery = new FinanceReceiptPayQuery();
        oldPactReceiptPayQuery.setPactCode(rentContinuedVO.getPirmaryPactCode());
        Date date = DateUtil.getDayByDay(rentContinuedVO.getRenewTime(), 1);
        oldPactReceiptPayQuery.setCostState(1);
        oldPactReceiptPayQuery.setCostTimeStr(DateUtil.formatDate(date));
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
    * @Title: saveObligationStatistics
    * @Description: TODO(  待办事务统计待付款,协议+新合同中所有收支的待付款   )
    * @author GuoXiaoPeng
    * @param rentContinuedVO 续签协议信息
    * @throws 异常
     */
    private void saveObligationStatistics(RentContinuedVO rentContinuedVO) {
        /******************** 待办事务统计待付款（新合同中的待付款）      start *******************************/
        List<Integer> costList = new ArrayList<>();
        costList.add(4);
        costList.add(5);
        FinanceReceiptPayQuery newPactReceiptPayQuery = new FinanceReceiptPayQuery();
        newPactReceiptPayQuery.setIsValid(1);
        newPactReceiptPayQuery.setPactCode(rentContinuedVO.getNewPactCode());
        newPactReceiptPayQuery.setType(2);
        newPactReceiptPayQuery.setCostList(costList);
        List<FinanceReceiptPayVO> newPactReceiptPayVOs = financeReceiptPayManager.listFinanceReceiptPayByPactCode(newPactReceiptPayQuery);
        if(null != newPactReceiptPayVOs && newPactReceiptPayVOs.size() > 0){
            CommonBelongerVO afterBelonger = rentBaseManager.getRentAfterBelonger(rentContinuedVO.getNewPactCode());
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
        /******************** 待办事务统计待付款（新合同中的待付款）      end *******************************/
    }
    /**
     * 
    * @Title: saveReceivablesStatistics
    * @Description: TODO( 待办事务统计待收款,新合同中所有收支的待收款   )
    * @author GuoXiaoPeng
    * @param rentContinuedVO  续签协议信息
    * @throws 异常
     */
    private void saveReceivablesStatistics(RentContinuedVO rentContinuedVO) {
        /******************** 待办事务统计待收款（新合同中的应收）      start *******************************/
        List<Integer> costList = new ArrayList<>();
        costList.add(1);
        costList.add(2);
        FinanceReceiptPayQuery newPactReceiptPayQuery = new FinanceReceiptPayQuery();
        newPactReceiptPayQuery.setIsValid(1);
        newPactReceiptPayQuery.setPactCode(rentContinuedVO.getNewPactCode());
        newPactReceiptPayQuery.setType(0);
        newPactReceiptPayQuery.setCostList(costList);
        List<FinanceReceiptPayVO> newPactReceiptPayVOs = financeReceiptPayManager.listFinanceReceiptPayByPactCode(newPactReceiptPayQuery);
        if(null != newPactReceiptPayVOs && newPactReceiptPayVOs.size() > 0){
            CommonBelongerVO afterBelonger = rentBaseManager.getRentAfterBelonger(rentContinuedVO.getNewPactCode());
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

    /**
     * @Title: saveRemind
     * @Description: TODO( 发送指定内容的提醒消息 )
     * @author GuoXiaoPeng
     * @param param 资源编码
     * @param key 模板key
     * @param variableList 替换的变量集合
     * @throws 异常
      */
    private void saveRemind(RentContinuedParam param, String key, List<String> variableList) {
        String content = remindMap.get(key);
        for (int i = 0; i < variableList.size(); i++) {
            content = content.replace(StringConsts.VARIABLE + i, variableList.get(i));
        }
        CommonBelongerQuery commonBelongerQuery = new CommonBelongerQuery();
        commonBelongerQuery.setPactCode(param.getRenewCode());
        commonBelongerQuery.setUserRole(StringConsts.BELONGER_PERSON);
        CommonBelongerVO commonBelongerVO = commonBelongerManager.getBelongersByParam(commonBelongerQuery);
        Map<String, Object> map = new HashMap<>();
        map.put(NormalConstant.REMIND_TITLE, StringConsts.REMIND_AUDITING_TITLE);
        map.put(NormalConstant.REMIND_PIN, commonBelongerVO.getUserPin());
        map.put(NormalConstant.REMIND_SERVICETYPE, SerializeTypeConts.RENEW);
        map.put(NormalConstant.REMIND_SERVICECODE, param.getRenewCode());
        map.put(NormalConstant.REMIND_REMINDCONTENT,content);
        remindRpc.saveRemind(map);
    }

    @Override
    public ApiResult<Boolean> reviewDismissalRentContinued(RentContinuedParam param) {
        ApiResult<Boolean> result = new ApiResult<>();
        if(null==param){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        if(StringUtil.isBlank(param.getRenewCode())){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        try{
            RentContinuedVO rentContinuedVO = rentContinuedManager.getRentContinuedByRenewCode(param.getRenewCode());
            if(null!=rentContinuedVO){
                if( rentContinuedVO.getState()==2 ){
                    RentContinuedDO rentContinuedDO = new RentContinuedDO();
                    BeanUtils.copyProperties(param, rentContinuedDO);
                    rentContinuedDO.setModifiedName(param.getModifiedName()==null?"":param.getModifiedName());
                    rentContinuedDO.setIp(param.getIp());
                    rentContinuedDO.setState(4);
                    boolean flag = rentContinuedManager.updateRentContinuedStateByCode(rentContinuedDO);
                    if(flag){
                        List<String> variableList = new ArrayList<>();
                        variableList.add(param.getRenewCode());
                        saveRemind(param,StringConsts.REVIEW_DISMISSAL_RENTCONTINUED,variableList);
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put(NormalConstant.SERVICETYPE, SerializeTypeConts.RENEW);
                        map.put(NormalConstant.SERVICECODE, rentContinuedDO.getRenewCode());
                        map.put(NormalConstant.OPERATETYPE, StringConsts.OPERATETYPE_REVIEW_DISMISSAL);
                        map.put(NormalConstant.OPERATECONTENT, param.getRenewReason());
                        map.put(NormalConstant.OPERATEPIN, rentContinuedDO.getModifiedName());
                        operateLogRpc.saveOperateLog(map);
                        /******************** 修改待办事务统计表的状态  start *******************************/
                        AgendaDO agendaDO = new AgendaDO();
                        agendaDO.setServiceCode(param.getRenewCode());
                        agendaDO.setIsDo(1);
                        agendaDO.setType(StringConsts.STATISTIC_PENDING_RENT_CONTINUED);
                        agendaDO.setModifiedName(param.getModifiedName());
                        agendaDO.setIp(param.getIp());
                        statisticsManager.updatBacklogStatisticsIsDo(agendaDO);
                        /******************** 修改待办事务统计表的状态  end *******************************/
                        result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                        result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                        result.setData(flag);
                    }else{
                        result.setCode(PactCommonEnum.RENT_CONTINUED_REVIEWDISMISSAL_ERROR.getCode());
                        result.setMessage(PactCommonEnum.RENT_CONTINUED_REVIEWDISMISSAL_ERROR.getMessage());
                    }
                }else{
                    result.setCode(PactCommonEnum.PACT_REVIEW_DISMISSAL_STATE_ERROR.getCode());
                    result.setMessage(PactCommonEnum.PACT_REVIEW_DISMISSAL_STATE_ERROR.getMessage());
                }
            }
        } catch (PactManagerExcepion e){
            LOG.error(e);
            result.setCode(PactCommonEnum.RENT_CONTINUED_REVIEWDISMISSAL_ERROR.getCode());
            result.setMessage(PactCommonEnum.RENT_CONTINUED_REVIEWDISMISSAL_ERROR.getMessage());
        } catch (Exception e) {
            LOG.error(e);
            result.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            result.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        }
        return result;
    }

    @Override
    public ApiResult<Boolean> updateRentContinued(RentContinuedParam param) {
        ApiResult<Boolean> result = new ApiResult<>();
        if(null==param ){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        try {
           String ip = param.getIp() ==null ? "" :param.getIp();
           String pin = param.getModifiedName();
           RentContinuedDO rentContinuedDO = new RentContinuedDO();
           BeanUtils.copyProperties(param, rentContinuedDO);
           
           RentContinuedVO rentContinuedVO = rentContinuedManager.getRentContinuedByRenewCode(param.getRenewCode());
           if(rentContinuedVO.getState()==1 ||rentContinuedVO.getState()==4){
             //协议照片
               List<CommonPicDO> commonPicDOs = new ArrayList<>();
               BeanUtils.copyListProperties(param.getUrlList(), commonPicDOs, CommonPicDO.class);
               for (CommonPicDO commonPicDO : commonPicDOs) {
                   commonPicDO.setIp(ip);
                   commonPicDO.setCreateName(pin);
                   commonPicDO.setModifiedName(pin);
                   commonPicDO.setPactCode(rentContinuedDO.getRenewCode());
                   commonPicDO.setPactType(PactTypeConsts.RENT_RENEW);
               }
               //协议的签约管家+维护人
               // 需要根据签约管家pin通过rpc查询用户信息
               ApiResult<PersonalResult> personalResult = personalRpc.getPersonalResultBypin(param.getDealUserPin());
               PersonalResult personalResultData = personalResult.getData();
               List<CommonBelongerDO> commonBelongerDOs = new ArrayList<>();
               List<CommonBelongerVO> commonBelongerList = commonBelongerManager.listCommonBelonger(rentContinuedDO.getRenewCode());
               if(null != commonBelongerList){
                   for (CommonBelongerVO commonBelongerVO : commonBelongerList) {
                       CommonBelongerDO commonBelongerDO = new CommonBelongerDO();
                       if(PactTypeConsts.RENT_RENEW.equals(commonBelongerVO.getPactType())){
                           if(StringConsts.BELONGER_DEAL.equals(commonBelongerVO.getUserRole())){
                               commonBelongerDO.setId(commonBelongerVO.getId());
                           }else if(StringConsts.GUARDIAN.equals(commonBelongerVO.getUserRole())){
                               commonBelongerDO.setId(commonBelongerVO.getId());
                           }
                           commonBelongerDO.setPactCode(rentContinuedDO.getRenewCode());
                           commonBelongerDO.setUserPin(param.getDealUserPin());
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
              boolean flag = rentContinuedManager.updateRentContinuedByRenewCode(rentContinuedDO,commonBelongerDOs,
                      commonPicDOs);
              if(flag){
                  Map<String, Object> map = new HashMap<String, Object>();
                  map.put(NormalConstant.SERVICETYPE, SerializeTypeConts.RENEW);
                  map.put(NormalConstant.SERVICECODE, rentContinuedDO.getRenewCode());
                  map.put(NormalConstant.OPERATETYPE, StringConsts.UPDATE);
                  map.put(NormalConstant.OPERATECONTENT, StringConsts.UPDATE_DATA);
                  map.put(NormalConstant.OPERATEPIN, rentContinuedDO.getModifiedName());
                  operateLogRpc.saveOperateLog(map);
                  result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                  result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
              }else{
                  result.setCode(PactCommonEnum.RENT_CONTINUED_UPDATE_ERROR.getCode());
                  result.setMessage(PactCommonEnum.RENT_CONTINUED_UPDATE_ERROR.getMessage());
              }
           }else{
               result.setCode(PactCommonEnum.PACT_NOT_UPDATE_DELTER.getCode());
               result.setMessage(PactCommonEnum.PACT_NOT_UPDATE_DELTER.getMessage());
           }
           
        } catch (PactManagerExcepion e) {
            LOG.error(e);
            result.setCode(PactCommonEnum.RENT_CONTINUED_UPDATE_ERROR.getCode());
            result.setMessage(PactCommonEnum.RENT_CONTINUED_UPDATE_ERROR.getMessage());
        } catch (Exception e) {
            LOG.error(e);
            result.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            result.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        }
        return result;
    }

    @Override
    public ApiResult<Boolean> removeRentContinued(RentContinuedParam param) {
        ApiResult<Boolean> result = new ApiResult<>();
        RentContinuedDO rentContinuedDO = new RentContinuedDO();
        BeanUtils.copyProperties(param, rentContinuedDO);
        rentContinuedDO.setModifiedName(param.getModifiedName()==null?"":param.getModifiedName());
        rentContinuedDO.setIp(param.getIp());
        try {
            RentContinuedVO rentContinuedVO = rentContinuedManager.getRentContinuedByRenewCode(rentContinuedDO.getRenewCode());
            rentContinuedDO.setNewPactCode(rentContinuedVO.getNewPactCode());
            if( rentContinuedVO.getState()==1 ||  rentContinuedVO.getState()==4){
                boolean flag = rentContinuedManager.removeRentContinuedByReletCode(rentContinuedDO);
                if(flag){
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put(NormalConstant.SERVICETYPE, SerializeTypeConts.RENEW);
                    map.put(NormalConstant.SERVICECODE, rentContinuedDO.getRenewCode());
                    map.put(NormalConstant.OPERATETYPE, StringConsts.DELETE);
                    map.put(NormalConstant.OPERATECONTENT, StringConsts.DELETE_DATA);
                    map.put(NormalConstant.OPERATEPIN, rentContinuedDO.getModifiedName());
                    operateLogRpc.saveOperateLog(map);
                    result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                    result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                }else{
                    result.setCode(PactCommonEnum.RENT_CONTINUED_DELETE_ERROR.getCode());
                    result.setMessage(PactCommonEnum.RENT_CONTINUED_DELETE_ERROR.getMessage());
                }
            }else{
                result.setCode(PactCommonEnum.PACT_NOT_UPDATE_DELTER.getCode());
                result.setMessage(PactCommonEnum.PACT_NOT_UPDATE_DELTER.getMessage());
            }
        } catch (PactManagerExcepion e) {
            LOG.error(e);
            result.setCode(PactCommonEnum.RENT_CONTINUED_DELETE_ERROR.getCode());
            result.setMessage(PactCommonEnum.RENT_CONTINUED_DELETE_ERROR.getMessage());
        } catch (Exception e) {
            LOG.error(e);
            result.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            result.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        }
        return result;
    }
    /**
     * 
    * @Title: getPermissions
    * @Description: TODO( 查看有没有权限查看续租协议 )
    * @author GuoXiaoPeng
    * @param param 续租协议编码
    * @return 有权限返回true没有false
    * @throws 异常
     */
    private boolean getPermissions(RentContinuedParam param) {
        ApiResult<PersonalResult> personalApiResult = personalRpc.getPersonalResultBypin(param.getCreateName());
        PersonalResult personalResult = personalApiResult.getData();
        Integer scope = param.getScope();
        RentContinuedQuery rentContinuedQuery = new RentContinuedQuery();
        if(1 == scope){// 1=本人
            rentContinuedQuery.setUserPin(param.getCreateName());
        }else if(2 == scope){// 2=本部门
            rentContinuedQuery.setDeptCode(personalResult.getDeptCode());
        }else if(3 == scope){//3=本人及下属
            List<String> pinList = personalRpc.listLowerByPin(param.getCreateName());
            rentContinuedQuery.setPinList(pinList);
        }else if(4 == scope){//4=本部门及下属部门
            List<String> deptList = deptRpc.listCodeByDeptCode(personalResult.getDeptCode());
            rentContinuedQuery.setDeptList(deptList);
        }
        rentContinuedQuery.setRenewCode(param.getRenewCode());
        boolean flag = rentContinuedManager.getPermissions(rentContinuedQuery);
        return flag;
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
    public RentContinuedManager getRentContinuedManager() {
        return rentContinuedManager;
    }

    public void setRentContinuedManager(RentContinuedManager rentContinuedManager) {
        this.rentContinuedManager = rentContinuedManager;
    }

    public RentBaseManager getRentBaseManager() {
        return rentBaseManager;
    }

    public void setRentBaseManager(RentBaseManager rentBaseManager) {
        this.rentBaseManager = rentBaseManager;
    }

    public RentTurnManager getRentTurnManager() {
        return rentTurnManager;
    }

    public void setRentTurnManager(RentTurnManager rentTurnManager) {
        this.rentTurnManager = rentTurnManager;
    }

    public PactRentTransferManager getPactRentTransferManager() {
        return pactRentTransferManager;
    }

    public void setPactRentTransferManager(PactRentTransferManager pactRentTransferManager) {
        this.pactRentTransferManager = pactRentTransferManager;
    }

    public CommonManager getCommonManager() {
        return commonManager;
    }

    public void setCommonManager(CommonManager commonManager) {
        this.commonManager = commonManager;
    }

    public RentRoomManager getRentRoomManager() {
        return rentRoomManager;
    }

    public void setRentRoomManager(RentRoomManager rentRoomManager) {
        this.rentRoomManager = rentRoomManager;
    }

    public PropertyTransferManager getPropertyTransferManager() {
        return propertyTransferManager;
    }

    public void setPropertyTransferManager(PropertyTransferManager propertyTransferManager) {
        this.propertyTransferManager = propertyTransferManager;
    }

    public RentCustomerManager getRentCustomerManager() {
        return rentCustomerManager;
    }

    public void setRentCustomerManager(RentCustomerManager rentCustomerManager) {
        this.rentCustomerManager = rentCustomerManager;
    }

    public CommonOwnerManager getCommonOwnerManager() {
        return commonOwnerManager;
    }

    public void setCommonOwnerManager(CommonOwnerManager commonOwnerManager) {
        this.commonOwnerManager = commonOwnerManager;
    }

    public CommonPicManager getCommonPicManager() {
        return commonPicManager;
    }

    public void setCommonPicManager(CommonPicManager commonPicManager) {
        this.commonPicManager = commonPicManager;
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

    public FinanceReceiptPayManager getFinanceReceiptPayManager() {
        return financeReceiptPayManager;
    }

    public void setFinanceReceiptPayManager(FinanceReceiptPayManager financeReceiptPayManager) {
        this.financeReceiptPayManager = financeReceiptPayManager;
    }

    public RoomRpc getRoomRpc() {
        return roomRpc;
    }

    public void setRoomRpc(RoomRpc roomRpc) {
        this.roomRpc = roomRpc;
    }
    
}
