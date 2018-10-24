package com.young.pact.service.purchasebase.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.tools.common.util.convert.BeanUtils;
import com.young.common.dict.CommonEnum;
import com.young.common.domain.ApiResult;
import com.young.common.page.PageBean;
import com.young.common.page.PageTableBean;
import com.young.pact.api.domain.param.rest.purchasebase.PurchaseBaseParam;
import com.young.pact.api.domain.param.rest.purchasebase.PurchaseBaseQueryParam;
import com.young.pact.api.domain.result.rest.commonbelonger.CommonBelongerResult;
import com.young.pact.api.domain.result.rest.commoncostrule.CommonCostRuleResult;
import com.young.pact.api.domain.result.rest.commonpic.CommonPicResult;
import com.young.pact.api.domain.result.rest.financereceiptpay.FinanceReceiptPayResult;
import com.young.pact.api.domain.result.rest.purchasebase.PurchaseBaseListResult;
import com.young.pact.api.domain.result.rest.purchasebase.PurchaseBaseResult;
import com.young.pact.api.domain.result.rest.purchasehouse.PurchaseHouseOwnerResult;
import com.young.pact.api.domain.result.rest.purchasehouse.PurchaseHouseResult;
import com.young.pact.api.domain.result.rest.purchaserentfree.PurchaseRentfreeResult;
import com.young.pact.api.service.rest.purchasebase.PurchaseBaseRestService;
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
import com.young.pact.domain.commonpic.CommonPicDO;
import com.young.pact.domain.declare.DeclareVO;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayDO;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayQuery;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayVO;
import com.young.pact.domain.purchasebase.PurchaseBaseDO;
import com.young.pact.domain.purchasebase.PurchaseBaseQuery;
import com.young.pact.domain.purchasebase.PurchaseBaseVO;
import com.young.pact.domain.purchasehouse.PurchaseHouseDO;
import com.young.pact.domain.purchasehouse.PurchaseHouseOwnerDO;
import com.young.pact.domain.purchasehouse.PurchaseHouseOwnerVO;
import com.young.pact.domain.purchasehouse.PurchaseHouseQuery;
import com.young.pact.domain.purchasehouse.PurchaseHouseVO;
import com.young.pact.domain.purchaserentfree.PurchaseRentfreeDO;
import com.young.pact.domain.statistics.AgendaDO;
import com.young.pact.manager.commonbelonger.CommonBelongerManager;
import com.young.pact.manager.declare.DeclareManager;
import com.young.pact.manager.financereceiptpay.FinanceReceiptPayManager;
import com.young.pact.manager.propertytransfer.PropertyTransferManager;
import com.young.pact.manager.purchasebase.PurchaseBaseManager;
import com.young.pact.manager.purchasehouse.PurchaseHouseManager;
import com.young.pact.manager.purchasehouse.PurchaseHouseOwnerManager;
import com.young.pact.manager.statistics.StatisticsManager;
import com.young.pact.rpc.dept.DeptRpc;
import com.young.pact.rpc.houseroom.HouseRoomRpc;
import com.young.pact.rpc.operatelog.OperateLogRpc;
import com.young.pact.rpc.personal.PersonalRpc;
import com.young.pact.rpc.remind.RemindRpc;
import com.young.pact.rpc.serialize.SerializeRpc;
import com.young.sso.api.domain.result.rpc.personal.PersonalResult;
/**
 * 
* @ClassName: PurchaseBaseRestServiceImpl
* @Description: TODO( 托进合同)
* @author HeZeMin
* @date 2018年8月2日 上午10:55:23
*
 */
@Service("purchaseBaseRestService")
public class PurchaseBaseRestServiceImpl implements PurchaseBaseRestService {
    /*******************声明区**************************/
    private static final Log LOG = LogFactory.getLog(PurchaseBaseRestServiceImpl.class);
    private PurchaseHouseManager purchaseHouseManager;
    private PurchaseHouseOwnerManager purchaseHouseOwnerManager;
    private PurchaseBaseManager purchaseBaseManager;
    private PropertyTransferManager propertyTransferManager;
    private CommonBelongerManager commonBelongerManager;
    private DeclareManager declareManager;
    private SerializeRpc serializeRpc;
    private PersonalRpc personalRpc;
    private OperateLogRpc operateLogRpc;
    private DeptRpc deptRpc;
    private RemindRpc remindRpc;
    private Map<String,String> remindMap;
    private StatisticsManager statisticsManager;
    private HouseRoomRpc houseRoomRpc;
    private FinanceReceiptPayManager financeReceiptPayManager;
    /*******************方法区**************************/
    @Override
    public ApiResult<String> savePurchaseBaseRedis(PurchaseBaseParam param) {
        ApiResult<String> result = new ApiResult<>();
        if(null == param){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        if(StringUtils.isBlank(param.getDeclareCode())&&StringUtils.isBlank(param.getHouseCode())){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        try {
            Map<String, Object> map = new HashMap<>();
            // copy 托进合同基本信息
            PurchaseBaseDO purchaseBaseDO = new PurchaseBaseDO();
            BeanUtils.copyProperties(param, purchaseBaseDO);
            map.put("purchaseBaseDO", purchaseBaseDO);
            // copy 托进合同收支规则
            List<CommonCostRuleDO> commonCostRuleList = new ArrayList<>();
            BeanUtils.copyListProperties(param.getCommonCostRuleList(), commonCostRuleList, CommonCostRuleDO.class);
            map.put("commonCostRuleList", commonCostRuleList);
            // copy 托进合同免租期
            List<PurchaseRentfreeDO> purchaseRentfreeList = new ArrayList<>();
            BeanUtils.copyListProperties(param.getPurchaseRentfreeList(), purchaseRentfreeList, PurchaseRentfreeDO.class);
            map.put("purchaseRentfreeList", purchaseRentfreeList);
            // copy 托进合同收支
            List<FinanceReceiptPayDO> financeReceiptPayList = new ArrayList<>();
            if(null != param.getFinanceReceiptPayList()){
                BeanUtils.copyListProperties(param.getFinanceReceiptPayList(), financeReceiptPayList, FinanceReceiptPayDO.class);
            }
            map.put("financeReceiptPayList", financeReceiptPayList);
            // copy 托进合同照片
            List<CommonPicDO> commonPicList = new ArrayList<>();
            BeanUtils.copyListProperties(param.getCommonPicList(), commonPicList, CommonPicDO.class);
            map.put("commonPicList", commonPicList);
            // redis key
            StringBuilder sb = new StringBuilder();
            if(StringUtils.isNotBlank(param.getDeclareCode())){
                sb.append(purchaseBaseDO.getDeclareCode()).append(StringConsts.UNDERLINE)
                .append(StringConsts.PURCHASE).append(StringConsts.UNDERLINE).append(purchaseBaseDO.getCreateName());
            }else if(StringUtils.isNotBlank(param.getHouseCode())){
                sb.append(param.getHouseCode()).append(StringConsts.UNDERLINE)
                .append(StringConsts.PURCHASE).append(StringConsts.UNDERLINE).append(purchaseBaseDO.getCreateName());
            }
            purchaseBaseManager.savePurchaseBaseRedis(map, sb.toString());
            result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
            result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
            result.setData(sb.toString());
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

    @SuppressWarnings("unchecked")
    @Override
    public ApiResult<PurchaseBaseResult> getPurchaseBaseRedis(String redisKey) {
        ApiResult<PurchaseBaseResult> result = new ApiResult<>();
        if(StringUtils.isBlank(redisKey)){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        try {
            Map<String, Object> map = purchaseBaseManager.getPurchaseBaseRedis(redisKey);
            if(null != map && map.size() > 0){
                PurchaseBaseResult purchaseBaseResult = new PurchaseBaseResult();
                // copy 托进合同基本信息
                PurchaseBaseDO purchaseBaseDO = (PurchaseBaseDO) map.get("purchaseBaseDO");
                if(null != purchaseBaseDO){
                    BeanUtils.copyProperties(purchaseBaseDO, purchaseBaseResult);
                }
                // copy 托进合同收支规则
                List<CommonCostRuleDO> commonCostRuleList = (List<CommonCostRuleDO>) map.get("commonCostRuleList");
                if(null != commonCostRuleList){
                    List<CommonCostRuleResult> commonCostRuleResults = new ArrayList<>();
                    BeanUtils.copyListProperties(commonCostRuleList, commonCostRuleResults, CommonCostRuleResult.class);
                    purchaseBaseResult.setCommonCostRuleList(commonCostRuleResults);
                }
                // copy 托进合同免租期
                List<PurchaseRentfreeDO> purchaseRentfreeList = (List<PurchaseRentfreeDO>) map.get("purchaseRentfreeList");
                if(null != purchaseRentfreeList){
                    List<PurchaseRentfreeResult> purchaseRentfreeResults = new ArrayList<>();
                    BeanUtils.copyListProperties(purchaseRentfreeList, purchaseRentfreeResults, PurchaseRentfreeResult.class);
                    purchaseBaseResult.setPurchaseRentfreeList(purchaseRentfreeResults);
                }
                // copy 托进合同收支
                List<FinanceReceiptPayDO> financeReceiptPayList = (List<FinanceReceiptPayDO>) map.get("financeReceiptPayList");
                if(null != financeReceiptPayList){
                    List<FinanceReceiptPayResult> financeReceiptPayResults = new ArrayList<>();
                    BeanUtils.copyListProperties(financeReceiptPayList, financeReceiptPayResults, FinanceReceiptPayResult.class);
                    purchaseBaseResult.setFinanceReceiptPayList(financeReceiptPayResults);
                }
                // copy 托进合同照片
                List<CommonPicDO> commonPicList = (List<CommonPicDO>) map.get("commonPicList");
                if(null != commonPicList){
                    List<CommonPicResult> commonPicResults = new ArrayList<>();
                    BeanUtils.copyListProperties(commonPicList, commonPicResults, CommonPicResult.class);
                    purchaseBaseResult.setCommonPicList(commonPicResults);
                }
                result.setData(purchaseBaseResult);
            }
            result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
            result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
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
    @SuppressWarnings("unchecked")
    @Override
    public ApiResult<String> savePurchaseBase(PurchaseBaseParam param) {
        ApiResult<String> result = new ApiResult<>();
        // 房源信息 redis key
        StringBuilder houseRedisKey = new StringBuilder();
        // 业主信息 redis key
        StringBuilder houseOwnerRedisKey = new StringBuilder();
        // 合同信息 redis key
        StringBuilder purchaseBaseRedisKey = new StringBuilder();
        // 物业交接 redis key
        StringBuilder propertyTransferRedisKey = new StringBuilder();
        boolean declareStateFlag = false;
        if(null == param ){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        try {
            if(StringUtils.isNotBlank(param.getDeclareCode())){
                houseRedisKey.append(param.getDeclareCode()).append(StringConsts.UNDERLINE)
                .append(StringConsts.HOUSE).append(StringConsts.UNDERLINE).append(param.getCreateName());
                houseOwnerRedisKey.append(param.getDeclareCode()).append(StringConsts.UNDERLINE)
                .append(StringConsts.HOUSE_OWNER).append(StringConsts.UNDERLINE).append(param.getCreateName());
                purchaseBaseRedisKey.append(param.getDeclareCode()).append(StringConsts.UNDERLINE)
                .append(StringConsts.PURCHASE).append(StringConsts.UNDERLINE).append(param.getCreateName());
                propertyTransferRedisKey.append(param.getDeclareCode()).append(StringConsts.UNDERLINE)
                .append(StringConsts.PROPERTY_TRANSFER).append(StringConsts.UNDERLINE).append(param.getCreateName());
                // 根据申报编码查询申报审核状态是否为 已审核
                DeclareVO declareVO = declareManager.getDeclareByDeclareCode(param.getDeclareCode());
                if(2 == declareVO.getState()){
                    declareStateFlag = true;
                }
                // 申报不是已审核
                if(!declareStateFlag){
                    result.setCode(PactCommonEnum.DECLARE_STATE_NOTIS_2.getCode());
                    result.setMessage(PactCommonEnum.DECLARE_STATE_NOTIS_2.getMessage());
                    return result;
                }
                int purchaseBaseCount = purchaseBaseManager.countByDeclareCode(param.getDeclareCode());
                if(purchaseBaseCount > 0){
                    result.setCode(PactCommonEnum.PURCHASE_BASE_IS_EXIST.getCode());
                    result.setMessage(PactCommonEnum.PURCHASE_BASE_IS_EXIST.getMessage());
                    return result;
                }
            }else if(StringUtils.isNotBlank(param.getHouseCode())){
                houseRedisKey.append(param.getHouseCode()).append(StringConsts.UNDERLINE)
                .append(StringConsts.HOUSE).append(StringConsts.UNDERLINE).append(param.getCreateName());
                houseOwnerRedisKey.append(param.getHouseCode()).append(StringConsts.UNDERLINE)
                .append(StringConsts.HOUSE_OWNER).append(StringConsts.UNDERLINE).append(param.getCreateName());
                purchaseBaseRedisKey.append(param.getHouseCode()).append(StringConsts.UNDERLINE)
                .append(StringConsts.PURCHASE).append(StringConsts.UNDERLINE).append(param.getCreateName());
                propertyTransferRedisKey.append(param.getHouseCode()).append(StringConsts.UNDERLINE)
                .append(StringConsts.PROPERTY_TRANSFER).append(StringConsts.UNDERLINE).append(param.getCreateName());
            }
            PurchaseHouseDO purchaseHouseDO = purchaseHouseManager.getHouseRedis(houseRedisKey.toString());
            PurchaseHouseQuery purchaseHouseQuery = new PurchaseHouseQuery();
            purchaseHouseQuery.setHouseCode(param.getHouseCode());
            boolean purchaseHouseFlag = purchaseHouseManager.exitPurchaseHouseByParam(purchaseHouseQuery);
            if(purchaseHouseFlag){
                result.setCode(PactCommonEnum.PURCHASEBASE_HOUSE_EXITS.getCode());
                result.setMessage(PactCommonEnum.PURCHASEBASE_HOUSE_EXITS.getMessage());
                return result;
            }
            if(null != purchaseHouseDO){// 第一步 房源信息 redis存在
                PurchaseHouseOwnerDO purchaseHouseOwnerDO = purchaseHouseOwnerManager.getHouseOwnerRedis(houseOwnerRedisKey.toString());
                if(null != purchaseHouseOwnerDO){// 第二步 业主信息 redis 存在
                    Map<String, Object> purchaseBaseMap = purchaseBaseManager.getPurchaseBaseRedis(purchaseBaseRedisKey.toString());
                    if(null != purchaseBaseMap && purchaseBaseMap.size() > 0){// 第三步 合同信息 redis 存在
                        Map<String, Object> propertyTransferMap = propertyTransferManager.getPropertyTransferRedis(propertyTransferRedisKey.toString());
                        if(null != propertyTransferMap && propertyTransferMap.size() > 0){// 第三步 物业交接 redis 存在
                            // 托进合同编码 序列化生成
                            String purchaseCode = serializeRpc.queryCodeBySerializeType(SerializeTypeConts.PURCHASE);
                            // 托进合同业主编码
                            String ownerCode = serializeRpc.queryCodeBySerializeType(SerializeTypeConts.PUROWER);
                            String pin = param.getCreateName();
                            String ip = param.getIp();
                            if(StringUtils.isNotBlank(purchaseCode) && StringUtils.isNotBlank(ownerCode)){// 序列化成功
                                // 托进合同基本信息
                                PurchaseBaseDO purchaseBaseDO = (PurchaseBaseDO) purchaseBaseMap.get("purchaseBaseDO");
                                purchaseBaseDO.setPurchaseCode(purchaseCode);
                                purchaseBaseDO.setCreateName(pin);
                                purchaseBaseDO.setIp(ip);
                                
                                // 托进合同房源
                                purchaseHouseDO.setIp(ip);
                                purchaseHouseDO.setCreateName(pin);
                                purchaseHouseDO.setPurchaseCode(purchaseCode);
                                
                                // 托进合同业主
                                purchaseHouseOwnerDO.setPurchaseCode(purchaseCode);
                                purchaseHouseOwnerDO.setIp(ip);
                                purchaseHouseOwnerDO.setCreateName(pin);
                                purchaseHouseOwnerDO.setOwnerCode(ownerCode);
                                
                                // 托进收支规则
                                List<CommonCostRuleDO> commonCostRuleList = (List<CommonCostRuleDO>) purchaseBaseMap.get("commonCostRuleList");
                                if(null != commonCostRuleList){
                                    for (CommonCostRuleDO commonCostRuleDO : commonCostRuleList) {
                                        commonCostRuleDO.setIp(ip);
                                        commonCostRuleDO.setCreateName(pin);
                                        commonCostRuleDO.setPactCode(purchaseCode);
                                        commonCostRuleDO.setPactType(PactTypeConsts.PURCHASE);
                                    }
                                }
                                
                                // 托进合同免租期
                                List<PurchaseRentfreeDO> purchaseRentfreeList = (List<PurchaseRentfreeDO>) purchaseBaseMap.get("purchaseRentfreeList");
                                if(null != purchaseRentfreeList){
                                    for (PurchaseRentfreeDO purchaseRentfreeDO : purchaseRentfreeList) {
                                        purchaseRentfreeDO.setPurchaseCode(purchaseCode);
                                        purchaseRentfreeDO.setCreateName(pin);
                                        purchaseRentfreeDO.setIp(ip);
                                    }
                                }
                                // 收支
                                List<FinanceReceiptPayDO> financeReceiptPayList = (List<FinanceReceiptPayDO>) purchaseBaseMap.get("financeReceiptPayList");
                                if(null != financeReceiptPayList){
                                    for (FinanceReceiptPayDO financeReceiptPayDO : financeReceiptPayList) {
                                        financeReceiptPayDO.setPactCode(purchaseCode);
                                        financeReceiptPayDO.setPactType(PactTypeConsts.PURCHASE);
                                        financeReceiptPayDO.setCreateName(pin);
                                        financeReceiptPayDO.setIp(ip);
                                        if(null != financeReceiptPayDO.getType() && 0 == financeReceiptPayDO.getType()){// 应收
                                            financeReceiptPayDO.setCostState(1);// 待收款
                                        }else if(null != financeReceiptPayDO.getType() && 2 == financeReceiptPayDO.getType()){// 应支
                                            financeReceiptPayDO.setCostState(4);// 待付款
                                            // 收款人是业主
                                            if(StringConsts.PAYEE_OBJECT_OWNER.equals(financeReceiptPayDO.getPayeeObject())){
                                                financeReceiptPayDO.setName(purchaseHouseOwnerDO.getPayeeName());
                                                financeReceiptPayDO.setTel(purchaseHouseOwnerDO.getPayeeTel());
                                                financeReceiptPayDO.setAccount(purchaseHouseOwnerDO.getPayeeAccount());
                                                financeReceiptPayDO.setBank(purchaseHouseOwnerDO.getPayeeBank());
                                                financeReceiptPayDO.setOpenBank(purchaseHouseOwnerDO.getPayeeOpeningBank());
                                            }
                                        }
                                        financeReceiptPayDO.setHouseCode(purchaseHouseDO.getHouseCode());
                                        financeReceiptPayDO.setAddress(purchaseHouseDO.getAddress());
                                    }
                                }
                                // 合同图片
                                List<CommonPicDO> commonPicList = (List<CommonPicDO>) purchaseBaseMap.get("commonPicList");
                                if(null != commonPicList){
                                    for (CommonPicDO commonPicDO : commonPicList) {
                                        commonPicDO.setIp(ip);
                                        commonPicDO.setCreateName(pin);
                                        commonPicDO.setPactCode(purchaseCode);
                                        commonPicDO.setPactType(PactTypeConsts.PURCHASE);
                                    }
                                }
                                
                                // 合同抄表
                                List<CommonMeterReadDO> commonMeterReadList = (List<CommonMeterReadDO>) propertyTransferMap.get("commonMeterReadList");
                                if(null != commonMeterReadList){
                                    for (CommonMeterReadDO commonMeterReadDO : commonMeterReadList) {
                                        commonMeterReadDO.setIp(ip);
                                        commonMeterReadDO.setCreateName(pin);
                                        commonMeterReadDO.setPactCode(purchaseCode);
                                        commonMeterReadDO.setPactType(PactTypeConsts.PURCHASE);
                                        if(null != commonMeterReadDO.getCommonMeterReadPicList()){
                                            List<CommonMeterReadPicDO> commonMeterReadPicList = new ArrayList<>();
                                            BeanUtils.copyListProperties(commonMeterReadDO.getCommonMeterReadPicList(), commonMeterReadPicList, CommonMeterReadPicDO.class);
                                            for (CommonMeterReadPicDO commonMeterReadPicDO : commonMeterReadPicList) {
                                                commonMeterReadPicDO.setIp(ip);
                                                commonMeterReadPicDO.setCreateName(pin);
                                            }
                                            commonMeterReadDO.setCommonMeterReadPicList(commonMeterReadPicList);
                                        }
                                    }
                                }
                                
                                // 合同物品
                                List<CommonGoodsDO> commonGoodsList = (List<CommonGoodsDO>) propertyTransferMap.get("commonGoodsList");
                                if(null != commonGoodsList){
                                    for (CommonGoodsDO commonGoodsDO : commonGoodsList) {
                                        commonGoodsDO.setIp(ip);
                                        commonGoodsDO.setCreateName(pin);
                                        commonGoodsDO.setPactCode(purchaseCode);
                                        commonGoodsDO.setPactType(PactTypeConsts.PURCHASE);
                                        if(null != commonGoodsDO.getCommonGoodsPicList()){
                                            List<CommonGoodsPicDO> commonGoodsPicList = new ArrayList<>();
                                            BeanUtils.copyListProperties(commonGoodsDO.getCommonGoodsPicList(), commonGoodsPicList, CommonGoodsPicDO.class);
                                            for (CommonGoodsPicDO commonGoodsPicDO : commonGoodsPicList) {
                                                commonGoodsPicDO.setIp(ip);
                                                commonGoodsPicDO.setCreateName(pin);
                                            }
                                            commonGoodsDO.setCommonGoodsPicList(commonGoodsPicList); 
                                        }
                                    }
                                }
                                
                                // 合同责任人
                                List<CommonBelongerDO> commonBelongerDOList = new ArrayList<>();
                                // 需要根据签约管家pin通过rpc查询用户信息
                                PersonalResult personalResult = personalRpc.getPersonalResultBypin(purchaseBaseDO.getDealPin()).getData();
                                CommonBelongerDO commonBelongerDO = new CommonBelongerDO();
                                commonBelongerDO.setPactCode(purchaseCode);
                                commonBelongerDO.setPactType(PactTypeConsts.PURCHASE);
                                commonBelongerDO.setUserPin(purchaseBaseDO.getDealPin());
                                commonBelongerDO.setUserName(personalResult.getEmployeeName());
                                commonBelongerDO.setUserTel(personalResult.getEmployeeTel());
                                commonBelongerDO.setUserRole(StringConsts.BELONGER_DEAL);
                                commonBelongerDO.setDeptCode(personalResult.getDeptCode());
                                commonBelongerDO.setDeptName(personalResult.getDeptName());
                                commonBelongerDO.setIp(ip);
                                commonBelongerDO.setCreateName(pin);
                                commonBelongerDOList.add(commonBelongerDO);
                                // 运营管家 默认是签约管家
                                commonBelongerDO = new CommonBelongerDO();
                                commonBelongerDO.setPactCode(purchaseCode);
                                commonBelongerDO.setPactType(PactTypeConsts.PURCHASE);
                                commonBelongerDO.setUserPin(purchaseBaseDO.getDealPin());
                                commonBelongerDO.setUserName(personalResult.getEmployeeName());
                                commonBelongerDO.setUserTel(personalResult.getEmployeeTel());
                                commonBelongerDO.setUserRole(StringConsts.BELONGER_OPERATE);
                                commonBelongerDO.setDeptCode(personalResult.getDeptCode());
                                commonBelongerDO.setDeptName(personalResult.getDeptName());
                                commonBelongerDO.setIp(ip);
                                commonBelongerDO.setCreateName(pin);
                                commonBelongerDOList.add(commonBelongerDO);
                                
                                boolean flag = purchaseBaseManager.savePurchaseBase(purchaseBaseDO, commonCostRuleList, purchaseRentfreeList, financeReceiptPayList, 
                                        commonPicList, purchaseHouseDO, purchaseHouseOwnerDO, commonGoodsList, commonMeterReadList, commonBelongerDOList);
                                if(flag){// 保存成功
                                    Map<String, Object> map = new HashMap<String, Object>();
                                    map.put(NormalConstant.SERVICETYPE, SerializeTypeConts.PURCHASE);
                                    map.put(NormalConstant.SERVICECODE, purchaseBaseDO.getPurchaseCode());
                                    map.put(NormalConstant.OPERATETYPE, StringConsts.ADD);
                                    map.put(NormalConstant.OPERATEPIN, purchaseBaseDO.getCreateName());
                                    map.put(NormalConstant.OPERATECONTENT, StringConsts.ADD_DATA);
                                    operateLogRpc.saveOperateLog(map);
                                    // 清除缓存
                                    List<String> redisKeys = new ArrayList<>();
                                    redisKeys.add(houseRedisKey.toString());
                                    redisKeys.add(houseOwnerRedisKey.toString());
                                    redisKeys.add(purchaseBaseRedisKey.toString());
                                    redisKeys.add(propertyTransferRedisKey.toString());
                                    purchaseBaseManager.removePurchaseBaseRedis(redisKeys);
                                    result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                                    result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                                    result.setData(purchaseCode);
                                }else{// 保存失败
                                    result.setCode(PactCommonEnum.PURCHASE_SAVE_FAIL.getCode());
                                    result.setMessage(PactCommonEnum.PURCHASE_SAVE_FAIL.getMessage());
                                }
                            }else{// 序列化失败
                                result.setCode(PactCommonEnum.SYSTEM_SERIALIZE_FAIL.getCode());
                                result.setMessage(PactCommonEnum.SYSTEM_SERIALIZE_FAIL.getMessage());
                            }
                        }else{// 第三步 物业交接 redis 失效
                            result.setCode(PactCommonEnum.PURCHASE_PROPERTY_REDIS_INVALID.getCode());
                            result.setMessage(PactCommonEnum.PURCHASE_PROPERTY_REDIS_INVALID.getMessage());
                        }
                    }else{// 第三步 合同信息 redis 失效
                        result.setCode(PactCommonEnum.PURCHASE_BASE_REDIS_INVALID.getCode());
                        result.setMessage(PactCommonEnum.PURCHASE_BASE_REDIS_INVALID.getMessage());
                    }
                }else{// 第二步 业主信息 redis 失效
                    result.setCode(PactCommonEnum.PURCHASE_HOUSEOWNER_REDIS_INVALID.getCode());
                    result.setMessage(PactCommonEnum.PURCHASE_HOUSEOWNER_REDIS_INVALID.getMessage());
                }
            }else{// 第一步 房源信息 redis 失效
                result.setCode(PactCommonEnum.PURCHASE_HOUSE_REDIS_INVALID.getCode());
                result.setMessage(PactCommonEnum.PURCHASE_HOUSE_REDIS_INVALID.getMessage());
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
    public ApiResult<PageBean<PurchaseBaseListResult>> listPurchaseBase(PurchaseBaseQueryParam param) {
        ApiResult<PageBean<PurchaseBaseListResult>> result = new ApiResult<>();
        if(null == param){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        try {
            ApiResult<PersonalResult> personalApiResult = personalRpc.getPersonalResultBypin(param.getCreateName());
            PersonalResult personalResult = personalApiResult.getData();
            String position = personalResult.getPosition();
            PurchaseBaseQuery query = new PurchaseBaseQuery();
            BeanUtils.copyProperties(param, query);
            Integer scope = param.getScope();
            if(StringConsts.BELONGER_OPERATE.equals(position)){
                query.setPosition(1);
            }else if(StringConsts.BELONGER_SERVICE.equals(position)){
                query.setPosition(2);
            }else{
                query.setPosition(3);
            }
            if(1 == scope){// 1=本人
                query.setUserPin(param.getCreateName());
            }else if(2 == scope){// 2=本部门
                query.setDeptCode(personalResult.getDeptCode());
            }else if(3 == scope){//3=本人及下属
                List<String> pinList = personalRpc.listLowerByPin(param.getCreateName());
                query.setPinList(pinList);
            }else if(4 == scope){//4=本部门及下属部门
                List<String> deptList = deptRpc.listCodeByDeptCode(personalResult.getDeptCode());
                query.setDeptList(deptList);
            }
            // 查询托进合同分页数据
            PageBean<PurchaseBaseVO> purchaseBaseList = purchaseBaseManager.listPurchaseBase(query);
            if(purchaseBaseList.getTotalItem() > 0){
                PageBean<PurchaseBaseListResult> resultList = new PageTableBean<PurchaseBaseListResult>(query.getPageIndex(),query.getPageSize());
                resultList.setTotalItem(purchaseBaseList.getTotalItem());
                // copy分页数据
                BeanUtils.copyListProperties(purchaseBaseList.getData(), resultList.getData(), PurchaseBaseListResult.class);
                result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                result.setData(resultList);
            }else{// 暂无数据
                result.setCode(CommonEnum.NO_CONTENT.getCode());
                result.setMessage(CommonEnum.NO_CONTENT.getMessage());
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
    public ApiResult<PurchaseBaseResult> getPurchaseBase(PurchaseBaseQueryParam param) {
        ApiResult<PurchaseBaseResult> result = new ApiResult<>();
        String purchaseCode = param.getPurchaseCode();
        if(StringUtils.isBlank(purchaseCode)){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        try {
            boolean permissionFlag = getPermission(param);
            if(!permissionFlag){
                result.setCode(PactCommonEnum.PURCHASEBASE_NO_PERMISSIONS.getCode());
                result.setMessage(PactCommonEnum.PURCHASEBASE_NO_PERMISSIONS.getMessage());
                return result;
            }
            PurchaseBaseVO purchaseBaseVO = purchaseBaseManager.getPurchaseBaseDetail(purchaseCode);
            if(null != purchaseBaseVO){
                PurchaseBaseResult purchaseBaseResult = new PurchaseBaseResult();
                BeanUtils.copyProperties(purchaseBaseVO, purchaseBaseResult);
                List<CommonBelongerResult> commonBelongerResults = new ArrayList<>();
                if(null!=purchaseBaseVO&&null!=purchaseBaseVO.getCommonBelongerList()){
                    BeanUtils.copyListProperties(purchaseBaseVO.getCommonBelongerList(), commonBelongerResults, CommonBelongerResult.class);
                }
                purchaseBaseResult.setCommonBelongerList(commonBelongerResults);
                List<CommonCostRuleResult> commonCostRuleResults = new ArrayList<>();
                if(null!=purchaseBaseVO&&null!=purchaseBaseVO.getCommonCostRuleList()){
                    BeanUtils.copyListProperties(purchaseBaseVO.getCommonCostRuleList(), commonCostRuleResults, CommonCostRuleResult.class);
                }
                purchaseBaseResult.setCommonCostRuleList(commonCostRuleResults);
                List<CommonPicResult> commonPicResults = new ArrayList<>();
                if(null!=purchaseBaseVO&&null!=purchaseBaseVO.getCommonPicList()){
                    BeanUtils.copyListProperties(purchaseBaseVO.getCommonPicList(), commonPicResults, CommonPicResult.class);
                }
                purchaseBaseResult.setCommonPicList(commonPicResults);
                PurchaseHouseResult purchaseHouseResult = new PurchaseHouseResult();
                BeanUtils.copyProperties(purchaseBaseVO.getPurchaseHouseVO(), purchaseHouseResult);
                purchaseBaseResult.setPurchaseHouseResult(purchaseHouseResult);
                PurchaseHouseOwnerResult purchaseHouseOwnerResult = new PurchaseHouseOwnerResult();
                BeanUtils.copyProperties(purchaseBaseVO.getPurchaseHouseOwnerVO(), purchaseHouseOwnerResult);
                purchaseBaseResult.setPurchaseHouseOwnerResult(purchaseHouseOwnerResult);
                result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                result.setData(purchaseBaseResult);
            }else{// 托进合同编码无效
                result.setCode(PactCommonEnum.PURCHASE_CODE_INVALID.getCode());
                result.setMessage(PactCommonEnum.PURCHASE_CODE_INVALID.getMessage());
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
    /**
    * @Title: getPermission
    * @Description: TODO( 查看登录人有没有某个拖进合同的查看权限)
    * @author GuoXiaoPeng
    * @param param 数据作用域和拖进合同编码
    * @return 有权限返回true，否则false
     */
    private boolean getPermission(PurchaseBaseQueryParam param) {
        boolean flag;
        PurchaseBaseQuery query = new PurchaseBaseQuery();
        query.setPurchaseCode(param.getPurchaseCode());
        ApiResult<PersonalResult> personalApiResult = personalRpc.getPersonalResultBypin(param.getCreateName());
        PersonalResult loginPersonal = personalApiResult.getData();
        String position = loginPersonal.getPosition();
        Integer scope = param.getScope();
        query.setScope(scope);
        if(StringConsts.BELONGER_OPERATE.equals(position)){
            query.setPosition(1);
        }else if(StringConsts.BELONGER_SERVICE.equals(position)){
            query.setPosition(2);
        }else{
            query.setPosition(3);
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
        flag = purchaseBaseManager.getPermissions(query);
        return flag;
    }

    @Override
    public ApiResult<Boolean> removePurchaseBase(PurchaseBaseParam param) {
        ApiResult<Boolean> result = new ApiResult<>();
        if(null == param || StringUtils.isBlank(param.getPurchaseCode())){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        try {
            PurchaseBaseDO purchaseBaseDO = new PurchaseBaseDO();
            BeanUtils.copyProperties(param, purchaseBaseDO);
            purchaseBaseDO.setIsDelete(1);// 逻辑删除
            PurchaseBaseVO purchaseBaseVO = purchaseBaseManager.getPurchaseBase(purchaseBaseDO.getPurchaseCode());
            if(null != purchaseBaseVO){
                // 状态 草稿和驳回 才可以删除
                if(1 == purchaseBaseVO.getState() || 4 == purchaseBaseVO.getState()){
                    boolean flag = purchaseBaseManager.removePurchaseBase(purchaseBaseDO);
                    if(flag){
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put(NormalConstant.SERVICETYPE, SerializeTypeConts.DECLARETYPE);
                        map.put(NormalConstant.SERVICECODE, purchaseBaseDO.getPurchaseCode());
                        map.put(NormalConstant.OPERATETYPE, StringConsts.DELETE);
                        map.put(NormalConstant.OPERATEPIN, purchaseBaseDO.getModifiedName());
                        map.put(NormalConstant.OPERATECONTENT, StringConsts.DELETE_DATA);
                        operateLogRpc.saveOperateLog(map);
                        result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                        result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                        result.setData(flag);
                    }else{
                        result.setCode(CommonEnum.REQUEST_FAIL.getCode());
                        result.setMessage(CommonEnum.REQUEST_FAIL.getMessage());
                    }
                }else{// 不是 草稿和驳回 不可以删除
                    result.setCode(PactCommonEnum.PURCHASE_BASE_STATE_ERROR.getCode());
                    result.setMessage(PactCommonEnum.PURCHASE_BASE_STATE_ERROR.getMessage());
                }
            }else{// 托进合同编码无效
                result.setCode(PactCommonEnum.PURCHASE_CODE_INVALID.getCode());
                result.setMessage(PactCommonEnum.PURCHASE_CODE_INVALID.getMessage());
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
    public ApiResult<Boolean> purchaseBaseApply(PurchaseBaseParam param) {
        ApiResult<Boolean> result = new ApiResult<>();
        if(null == param || StringUtils.isBlank(param.getPurchaseCode())){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        try {
            PurchaseBaseDO purchaseBaseDO = new PurchaseBaseDO();
            BeanUtils.copyProperties(param, purchaseBaseDO);
            purchaseBaseDO.setState(2);// 待审核
            PurchaseBaseVO purchaseBaseVO = purchaseBaseManager.getPurchaseBase(purchaseBaseDO.getPurchaseCode());
            if(null != purchaseBaseVO){
                // 状态 草稿和驳回 才可以申请审核
                if(1 == purchaseBaseVO.getState() || 4 == purchaseBaseVO.getState()){
                    boolean flag = purchaseBaseManager.updateState(purchaseBaseDO);
                    result.setData(flag);
                    if(flag){
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put(NormalConstant.SERVICETYPE, SerializeTypeConts.PURCHASE);
                        map.put(NormalConstant.SERVICECODE, purchaseBaseDO.getPurchaseCode());
                        map.put(NormalConstant.OPERATEPIN, purchaseBaseDO.getModifiedName());
                        map.put(NormalConstant.OPERATECONTENT, StringConsts.AUDIT_DATA);
                        map.put(NormalConstant.OPERATETYPE, StringConsts.OPERATETYPE_APPLICATION_AUDIT);
                        operateLogRpc.saveOperateLog(map);
                        /*************************首页待办数据统计  start*****************************/
                        CommonBelongerQuery commonBelongerQuery = new CommonBelongerQuery();
                        commonBelongerQuery.setPactCode(param.getPurchaseCode());
                        commonBelongerQuery.setUserRole(StringConsts.BELONGER_DEAL);
                        CommonBelongerVO commonBelongerVO = commonBelongerManager.getBelongersByParam(commonBelongerQuery);
                        AgendaDO agendaDO = new AgendaDO();
                        agendaDO.setServiceCode(param.getPurchaseCode());
                        agendaDO.setType(StringConsts.STATISTIC_PENDING_PURCHBASE);
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
                        result.setCode(CommonEnum.REQUEST_FAIL.getCode());
                        result.setMessage(CommonEnum.REQUEST_FAIL.getMessage());
                    }
                }else{// 不是 草稿和驳回 不可以申请审核
                    result.setCode(PactCommonEnum.PURCHASE_BASE_APPLY_ERROR.getCode());
                    result.setMessage(PactCommonEnum.PURCHASE_BASE_APPLY_ERROR.getMessage());
                }
            }else{// 托进合同编码无效
                result.setCode(PactCommonEnum.PURCHASE_CODE_INVALID.getCode());
                result.setMessage(PactCommonEnum.PURCHASE_CODE_INVALID.getMessage());
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
    public ApiResult<Boolean> purchaseBasePassed(PurchaseBaseParam param) {
        ApiResult<Boolean> result = new ApiResult<>();
        if(null == param || StringUtils.isBlank(param.getPurchaseCode())){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        try {
            PurchaseBaseDO purchaseBaseDO = new PurchaseBaseDO();
            BeanUtils.copyProperties(param, purchaseBaseDO);
            purchaseBaseDO.setState(3);// 审核通过
            PurchaseBaseVO purchaseBaseVO = purchaseBaseManager.getPurchaseBase(purchaseBaseDO.getPurchaseCode());
            if(null != purchaseBaseVO){
                // 状态 待审核 才可以审核
                if(2 == purchaseBaseVO.getState()){
                    boolean flag = purchaseBaseManager.updateState(purchaseBaseDO);
                    result.setData(flag);
                    if(flag){
                        List<String> variableList = new ArrayList<>();
                        variableList.add(param.getPurchaseCode());
                        saveRemind(param,StringConsts.PURCHASEBASE_PASSED,variableList);
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put(NormalConstant.SERVICETYPE, SerializeTypeConts.PURCHASE);
                        map.put(NormalConstant.SERVICECODE, purchaseBaseDO.getPurchaseCode());
                        map.put(NormalConstant.OPERATEPIN, purchaseBaseDO.getModifiedName());
                        map.put(NormalConstant.OPERATECONTENT, StringConsts.REVIEWPASS_DATA);
                        map.put(NormalConstant.OPERATETYPE, StringConsts.OPERATETYPE_REVIEW_PASS);
                        operateLogRpc.saveOperateLog(map);
  
                        /******************** 修改待办事务统计表托进合同审核的状态  start *******************************/
                        AgendaDO agendaDO = new AgendaDO();
                        agendaDO.setServiceCode(param.getPurchaseCode());
                        agendaDO.setIsDo(1);
                        agendaDO.setType(StringConsts.STATISTIC_PENDING_PURCHBASE);
                        agendaDO.setModifiedName(param.getModifiedName());
                        agendaDO.setIp(param.getIp());
                        statisticsManager.updatBacklogStatisticsIsDo(agendaDO);
                        /******************** 修改待办事务统计表托进合同审核的状态  end *******************************/
                        result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                        result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                    }else{
                        result.setCode(CommonEnum.REQUEST_FAIL.getCode());
                        result.setMessage(CommonEnum.REQUEST_FAIL.getMessage());
                    }
                }else{// 不是 待审核 不可以审核
                    result.setCode(PactCommonEnum.PURCHASE_BASE_AUDIT_ERROR.getCode());
                    result.setMessage(PactCommonEnum.PURCHASE_BASE_AUDIT_ERROR.getMessage());
                }
            }else{// 托进合同编码无效
                result.setCode(PactCommonEnum.PURCHASE_CODE_INVALID.getCode());
                result.setMessage(PactCommonEnum.PURCHASE_CODE_INVALID.getMessage());
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
    /**
     * 
    * @Title: saveReceivablesStatistics
    * @Description: TODO( 添加待办事务统计待收款统计记录 )
    * @author GuoXiaoPeng
    * @param param  托进合同编码
     * @param afterRentBelongerVO 租后管家
    * @throws 异常
     */
    @SuppressWarnings("unused")
    private void saveReceivablesStatistics(PurchaseBaseParam param, CommonBelongerVO afterRentBelongerVO) {
        FinanceReceiptPayQuery financeReceiptPayQuery = new FinanceReceiptPayQuery();
        financeReceiptPayQuery.setIsValid(1);
        financeReceiptPayQuery.setPactCode(param.getPurchaseCode());
        financeReceiptPayQuery.setType(0);
        List<Integer> costList = new ArrayList<>();
        costList.add(1);
        costList.add(2);
        financeReceiptPayQuery.setCostList(costList);
        List<FinanceReceiptPayVO> financeReceiptPayVOs = financeReceiptPayManager.listFinanceReceiptPayByPactCode(financeReceiptPayQuery);
        if(null != financeReceiptPayVOs && financeReceiptPayVOs.size() > 0){
            for(FinanceReceiptPayVO recPay:financeReceiptPayVOs){
                AgendaDO pendingAgendaDO = new AgendaDO();
                pendingAgendaDO.setServiceCode(String.valueOf(recPay.getId()));
                pendingAgendaDO.setType(StringConsts.STATISTIC_PENDING_RECEIVABLES);
                pendingAgendaDO.setIsDo(0);
                pendingAgendaDO.setPin(afterRentBelongerVO.getUserPin());
                pendingAgendaDO.setName(afterRentBelongerVO.getUserName());
                pendingAgendaDO.setDeptCode(afterRentBelongerVO.getDeptCode());
                pendingAgendaDO.setDeptName(afterRentBelongerVO.getDeptName());
                pendingAgendaDO.setDate(new Date());
                statisticsManager.saveBacklogStatistics(pendingAgendaDO);
            }
        }
    }

    /**
     * 
    * @Title: saveObligationStatistics
    * @Description: TODO( 添加待办事务统计待付款统计记录 )
    * @author GuoXiaoPeng
    * @param param 托进合同编码
     * @param afterRentBelongerVO 租后管家
    * @throws 异常
     */
    @SuppressWarnings("unused")
    private void saveObligationStatistics(PurchaseBaseParam param, CommonBelongerVO afterRentBelongerVO) {
        FinanceReceiptPayQuery financeReceiptPayQuery = new FinanceReceiptPayQuery();
        financeReceiptPayQuery.setIsValid(1);
        financeReceiptPayQuery.setPactCode(param.getPurchaseCode());
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

    @Override
    public ApiResult<Boolean> purchaseBaseReject(PurchaseBaseParam param) {
        ApiResult<Boolean> result = new ApiResult<>();
        if(null == param || StringUtils.isBlank(param.getPurchaseCode())){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        try {
            PurchaseBaseDO purchaseBaseDO = new PurchaseBaseDO();
            BeanUtils.copyProperties(param, purchaseBaseDO);
            purchaseBaseDO.setState(4);// 已驳回
            PurchaseBaseVO purchaseBaseVO = purchaseBaseManager.getPurchaseBase(purchaseBaseDO.getPurchaseCode());
            if(null != purchaseBaseVO){
                // 状态 待审核 才可以审核
                if(2 == purchaseBaseVO.getState()){
                    boolean flag = purchaseBaseManager.updateState(purchaseBaseDO);
                    result.setData(flag);
                    if(flag){
                        List<String> variableList = new ArrayList<>();
                        variableList.add(param.getPurchaseCode());
                        saveRemind(param,StringConsts.PURCHASEBASE_REJECT,variableList);
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put(NormalConstant.SERVICETYPE, SerializeTypeConts.PURCHASE);
                        map.put(NormalConstant.SERVICECODE, purchaseBaseDO.getPurchaseCode());
                        map.put(NormalConstant.OPERATEPIN, purchaseBaseDO.getModifiedName());
                        map.put(NormalConstant.OPERATECONTENT, param.getReason());
                        map.put(NormalConstant.OPERATETYPE, StringConsts.OPERATETYPE_REVIEW_DISMISSAL);
                        operateLogRpc.saveOperateLog(map);
                        /******************** 修改待办事务统计表的状态  start *******************************/
                        AgendaDO agendaDO = new AgendaDO();
                        agendaDO.setServiceCode(param.getPurchaseCode());
                        agendaDO.setIsDo(1);
                        agendaDO.setType(StringConsts.STATISTIC_PENDING_PURCHBASE);
                        agendaDO.setModifiedName(param.getModifiedName());
                        agendaDO.setIp(param.getIp());
                        statisticsManager.updatBacklogStatisticsIsDo(agendaDO);
                        /******************** 修改待办事务统计表的状态  end *******************************/
                        result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                        result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                    }else{
                        result.setCode(CommonEnum.REQUEST_FAIL.getCode());
                        result.setMessage(CommonEnum.REQUEST_FAIL.getMessage());
                    }
                }else{// 不是 待审核 不可以审核
                    result.setCode(PactCommonEnum.PURCHASE_BASE_AUDIT_ERROR.getCode());
                    result.setMessage(PactCommonEnum.PURCHASE_BASE_AUDIT_ERROR.getMessage());
                }
            }else{// 托进合同编码无效
                result.setCode(PactCommonEnum.PURCHASE_CODE_INVALID.getCode());
                result.setMessage(PactCommonEnum.PURCHASE_CODE_INVALID.getMessage());
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
    public ApiResult<PurchaseBaseResult> getPurchaseByPurchaseCode(String purchaseCode) {
        ApiResult<PurchaseBaseResult> result = new ApiResult<>();
        if(StringUtils.isBlank(purchaseCode)){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        try {
            PurchaseBaseVO purchaseBaseVO = purchaseBaseManager.getUpdatePurchaseBase(purchaseCode);
            if(null != purchaseBaseVO){
                PurchaseBaseResult purchaseBaseResult = new PurchaseBaseResult();
                BeanUtils.copyProperties(purchaseBaseVO, purchaseBaseResult);
                
                result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                result.setData(purchaseBaseResult);
            }else{// 托进合同编码无效
                result.setCode(PactCommonEnum.PURCHASE_CODE_INVALID.getCode());
                result.setMessage(PactCommonEnum.PURCHASE_CODE_INVALID.getMessage());
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
    public ApiResult<Boolean> updatePurchaseBase(PurchaseBaseParam param) {
        ApiResult<Boolean> result = new ApiResult<>();
        if(null == param || StringUtils.isBlank(param.getPurchaseCode())){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        try {
            PurchaseBaseVO purchaseBaseVO = purchaseBaseManager.getPurchaseBase(param.getPurchaseCode());
            if(null != purchaseBaseVO){
                // 状态 草稿和驳回 才可以修改
                if(1 == purchaseBaseVO.getState() || 4 == purchaseBaseVO.getState()){
                    String ip = param.getIp();
                    String pin = param.getModifiedName();
                    String purchaseCode = param.getPurchaseCode();
                    // 托进合同
                    PurchaseBaseDO purchaseBaseDO = new PurchaseBaseDO();
                    BeanUtils.copyProperties(param, purchaseBaseDO);
                    
                    // 收支规则
                    List<CommonCostRuleDO> commonCostRuleDOList = new ArrayList<>();
                    BeanUtils.copyListProperties(param.getCommonCostRuleList(), commonCostRuleDOList, CommonCostRuleDO.class);
                    for (CommonCostRuleDO commonCostRuleDO : commonCostRuleDOList) {
                        commonCostRuleDO.setIp(ip);
                        commonCostRuleDO.setCreateName(pin);
                        commonCostRuleDO.setModifiedName(pin);
                        commonCostRuleDO.setPactCode(purchaseCode);
                        commonCostRuleDO.setPactType(PactTypeConsts.PURCHASE);
                    }
                    
                    // 免租期
                    List<PurchaseRentfreeDO> purchaseRentfreeDOList = new ArrayList<>();
                    BeanUtils.copyListProperties(param.getPurchaseRentfreeList(), purchaseRentfreeDOList, PurchaseRentfreeDO.class);
                    for (PurchaseRentfreeDO purchaseRentfreeDO : purchaseRentfreeDOList) {
                        purchaseRentfreeDO.setPurchaseCode(purchaseCode);
                        purchaseRentfreeDO.setCreateName(pin);
                        purchaseRentfreeDO.setModifiedName(pin);
                        purchaseRentfreeDO.setIp(ip);
                    }
                    
                    // 收支
                    List<FinanceReceiptPayDO> financeReceiptPayDOList = new ArrayList<>();
                    BeanUtils.copyListProperties(param.getFinanceReceiptPayList(), financeReceiptPayDOList, FinanceReceiptPayDO.class);
                    for (FinanceReceiptPayDO financeReceiptPayDO : financeReceiptPayDOList) {
                        financeReceiptPayDO.setPactCode(purchaseCode);
                        financeReceiptPayDO.setPactType(PactTypeConsts.PURCHASE);
                        financeReceiptPayDO.setCreateName(pin);
                        financeReceiptPayDO.setModifiedName(pin);
                        financeReceiptPayDO.setIp(ip);
                        if(null != financeReceiptPayDO.getType() && 0 == financeReceiptPayDO.getType()){// 应收
                            financeReceiptPayDO.setCostState(1);// 待收款
                        }else if(null != financeReceiptPayDO.getType() && 2 == financeReceiptPayDO.getType()){// 应支
                            financeReceiptPayDO.setCostState(4);// 待付款
                            // 收款人是业主
                            if(StringConsts.PAYEE_OBJECT_OWNER.equals(financeReceiptPayDO.getPayeeObject())){
                                PurchaseHouseOwnerVO purchaseHouseOwner = purchaseHouseOwnerManager.getPurchaseHouseOwner(purchaseCode);
                                if(null != purchaseHouseOwner){
                                    financeReceiptPayDO.setName(purchaseHouseOwner.getPayeeName());
                                    financeReceiptPayDO.setTel(purchaseHouseOwner.getPayeeTel());
                                    financeReceiptPayDO.setAccount(purchaseHouseOwner.getPayeeAccount());
                                    financeReceiptPayDO.setBank(purchaseHouseOwner.getPayeeBank());
                                    financeReceiptPayDO.setOpenBank(purchaseHouseOwner.getPayeeOpeningBank());
                                }
                            }
                        }
                        PurchaseHouseVO purchaseHouse = purchaseHouseManager.getPurchaseHouse(purchaseCode);
                        if(null != purchaseHouse){
                            financeReceiptPayDO.setHouseCode(purchaseHouse.getHouseCode());
                            financeReceiptPayDO.setAddress(purchaseHouse.getAddress());
                        }
                    }
                    // 照片
                    List<CommonPicDO> commonPicDOList = new ArrayList<>();
                    BeanUtils.copyListProperties(param.getCommonPicList(), commonPicDOList, CommonPicDO.class);
                    for (CommonPicDO commonPicDO : commonPicDOList) {
                        commonPicDO.setIp(ip);
                        commonPicDO.setCreateName(pin);
                        commonPicDO.setModifiedName(pin);
                        commonPicDO.setPactCode(purchaseCode);
                        commonPicDO.setPactType(PactTypeConsts.PURCHASE);
                    }
                    CommonBelongerDO commonBelongerDO = new CommonBelongerDO();
                    List<CommonBelongerVO> commonBelongerList = commonBelongerManager.listCommonBelonger(purchaseCode);
                    if(null != commonBelongerList){
                        for (CommonBelongerVO commonBelongerVO : commonBelongerList) {
                            if(StringConsts.BELONGER_DEAL.equals(commonBelongerVO.getUserRole())){
                                commonBelongerDO.setId(commonBelongerVO.getId());
                            }
                        }
                    }
                    // 根据签约管家pin通过rpc查询用户信息
                    PersonalResult personalResult = personalRpc.getPersonalResultBypin(purchaseBaseDO.getDealPin()).getData();
                    commonBelongerDO.setPactCode(purchaseCode);
                    commonBelongerDO.setUserPin(purchaseBaseDO.getDealPin());
                    commonBelongerDO.setUserName(personalResult.getEmployeeName());
                    commonBelongerDO.setUserTel(personalResult.getEmployeeTel());
                    commonBelongerDO.setUserRole(StringConsts.BELONGER_DEAL);
                    commonBelongerDO.setDeptCode(personalResult.getDeptCode());
                    commonBelongerDO.setDeptName(personalResult.getDeptName());
                    commonBelongerDO.setIp(ip);
                    commonBelongerDO.setCreateName(pin);
                    commonBelongerDO.setModifiedName(pin);
                    
                    boolean flag = purchaseBaseManager.updatePurchaseBase(purchaseBaseDO, commonCostRuleDOList, purchaseRentfreeDOList, 
                            financeReceiptPayDOList, commonPicDOList, commonBelongerDO);
                    if(flag){
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put(NormalConstant.SERVICETYPE, SerializeTypeConts.PURCHASE);
                        map.put(NormalConstant.SERVICECODE, purchaseBaseDO.getPurchaseCode());
                        map.put(NormalConstant.OPERATETYPE, StringConsts.UPDATE);
                        map.put(NormalConstant.OPERATEPIN, purchaseBaseDO.getModifiedName());
                        map.put(NormalConstant.OPERATECONTENT, StringConsts.UPDATE_DATA);
                        operateLogRpc.saveOperateLog(map);
                        result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                        result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                    }else{
                        result.setCode(CommonEnum.REQUEST_FAIL.getCode());
                        result.setMessage(CommonEnum.REQUEST_FAIL.getMessage());
                    }
                    result.setData(flag);
                }else{// 不是 待审核 不可以修改
                    result.setCode(PactCommonEnum.PURCHASE_BASE_STATE_UPDATE_ERROR.getCode());
                    result.setMessage(PactCommonEnum.PURCHASE_BASE_STATE_UPDATE_ERROR.getMessage());
                }
            }else{// 托进合同编码无效
                result.setCode(PactCommonEnum.PURCHASE_CODE_INVALID.getCode());
                result.setMessage(PactCommonEnum.PURCHASE_CODE_INVALID.getMessage());
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
    public ApiResult<Integer> getRoomNumberByPurchaseCode(String purchaseCode) {
        ApiResult<Integer> result = new ApiResult<>();
        try {
            if(StringUtils.isBlank(purchaseCode)){
                result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
                result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
                return result;
            }
            PurchaseBaseVO purchaseBaseVO = purchaseBaseManager.getPurchaseBase(purchaseCode);
            if(null!=purchaseBaseVO){
                if(StringUtils.isBlank(purchaseBaseVO.getDeclareCode())){
                    result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                    result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                    result.setData(0);
                }else{
                    DeclareVO declareVO = declareManager.getDeclareByDeclareCode(purchaseBaseVO.getDeclareCode());
                    result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                    result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                    result.setData(declareVO.getRoomNumber());
                }
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
    /**
    * @Title: saveRemind
    * @Description: TODO( 发送指定内容的提醒消息 )
    * @author GuoXiaoPeng
    * @param param 资源编码
    * @param key 模板key
    * @param variableList 替换的变量集合
    * @throws 异常
     */
    private void saveRemind(PurchaseBaseParam param, String key, List<String> variableList) {
        String content = remindMap.get(key);
        for (int i = 0; i < variableList.size(); i++) {
            content = content.replace(StringConsts.VARIABLE + i, variableList.get(i));
        }
        CommonBelongerQuery commonBelongerQuery = new CommonBelongerQuery();
        commonBelongerQuery.setPactCode(param.getPurchaseCode());
        commonBelongerQuery.setUserRole(StringConsts.BELONGER_OPERATE);
        CommonBelongerVO commonBelongerVO = commonBelongerManager.getBelongersByParam(commonBelongerQuery);
        Map<String, Object> map = new HashMap<>();
        map.put(NormalConstant.REMIND_TITLE, StringConsts.REMIND_AUDITING_TITLE);
        map.put(NormalConstant.REMIND_PIN, commonBelongerVO.getUserPin());
        map.put(NormalConstant.REMIND_SERVICETYPE, SerializeTypeConts.PURCHASE);
        map.put(NormalConstant.REMIND_SERVICECODE, param.getPurchaseCode());
        map.put(NormalConstant.REMIND_REMINDCONTENT,content);
        remindRpc.saveRemind(map);
    }
    /*******************get/set**************************/

    public PurchaseBaseManager getPurchaseBaseManager() {
        return purchaseBaseManager;
    }
    public void setPurchaseBaseManager(PurchaseBaseManager purchaseBaseManager) {
        this.purchaseBaseManager = purchaseBaseManager;
    }
    public PurchaseHouseManager getPurchaseHouseManager() {
        return purchaseHouseManager;
    }
    public void setPurchaseHouseManager(PurchaseHouseManager purchaseHouseManager) {
        this.purchaseHouseManager = purchaseHouseManager;
    }
    public PurchaseHouseOwnerManager getPurchaseHouseOwnerManager() {
        return purchaseHouseOwnerManager;
    }
    public void setPurchaseHouseOwnerManager(PurchaseHouseOwnerManager purchaseHouseOwnerManager) {
        this.purchaseHouseOwnerManager = purchaseHouseOwnerManager;
    }
    public PropertyTransferManager getPropertyTransferManager() {
        return propertyTransferManager;
    }
    public void setPropertyTransferManager(PropertyTransferManager propertyTransferManager) {
        this.propertyTransferManager = propertyTransferManager;
    }
    public CommonBelongerManager getCommonBelongerManager() {
        return commonBelongerManager;
    }
    public void setCommonBelongerManager(CommonBelongerManager commonBelongerManager) {
        this.commonBelongerManager = commonBelongerManager;
    }
    public DeclareManager getDeclareManager() {
        return declareManager;
    }

    public void setDeclareManager(DeclareManager declareManager) {
        this.declareManager = declareManager;
    }

    public SerializeRpc getSerializeRpc() {
        return serializeRpc;
    }

    public void setSerializeRpc(SerializeRpc serializeRpc) {
        this.serializeRpc = serializeRpc;
    }

    public PersonalRpc getPersonalRpc() {
        return personalRpc;
    }

    public void setPersonalRpc(PersonalRpc personalRpc) {
        this.personalRpc = personalRpc;
    }

    public OperateLogRpc getOperateLogRpc() {
        return operateLogRpc;
    }

    public void setOperateLogRpc(OperateLogRpc operateLogRpc) {
        this.operateLogRpc = operateLogRpc;
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

    public HouseRoomRpc getHouseRoomRpc() {
        return houseRoomRpc;
    }

    public void setHouseRoomRpc(HouseRoomRpc houseRoomRpc) {
        this.houseRoomRpc = houseRoomRpc;
    }

    public FinanceReceiptPayManager getFinanceReceiptPayManager() {
        return financeReceiptPayManager;
    }

    public void setFinanceReceiptPayManager(FinanceReceiptPayManager financeReceiptPayManager) {
        this.financeReceiptPayManager = financeReceiptPayManager;
    }

    
}
