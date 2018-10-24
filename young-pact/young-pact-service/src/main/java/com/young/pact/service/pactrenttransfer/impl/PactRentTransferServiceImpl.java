package com.young.pact.service.pactrenttransfer.impl;

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
import com.tools.common.util.string.StringUtil;
import com.young.common.dict.CommonEnum;
import com.young.common.domain.ApiResult;
import com.young.common.page.PageBean;
import com.young.common.page.PageTableBean;
import com.young.house.api.domain.result.rpc.house.GetHouseRpcResult;
import com.young.pact.api.domain.param.rest.financereceiptpay.FinanceReceiptPayParam;
import com.young.pact.api.domain.param.rest.pactrenttransfer.PactRentTransferParam;
import com.young.pact.api.domain.param.rest.rentbase.RentPactParam;
import com.young.pact.api.domain.result.rest.commoncostrule.CommonCostRuleResult;
import com.young.pact.api.domain.result.rest.commongoods.CommonGoodsResult;
import com.young.pact.api.domain.result.rest.commonmeterread.CommonMeterReadResult;
import com.young.pact.api.domain.result.rest.commonpic.CommonPicResult;
import com.young.pact.api.domain.result.rest.financereceiptpay.FinanceReceiptPayResult;
import com.young.pact.api.domain.result.rest.pactrenttransfer.PactRentTransferResult;
import com.young.pact.api.domain.result.rest.rentbase.RentBaseResult;
import com.young.pact.api.domain.result.rest.rentbase.RentPactResult;
import com.young.pact.api.service.rest.pactrenttransfer.PactRentTransferRestService;
import com.young.pact.common.constant.NormalConstant;
import com.young.pact.common.constant.PactTypeConsts;
import com.young.pact.common.constant.RedisConsts;
import com.young.pact.common.constant.SerializeTypeConts;
import com.young.pact.common.constant.StringConsts;
import com.young.pact.common.dict.PactCommonEnum;
import com.young.pact.common.exception.PactManagerExcepion;
import com.young.pact.common.util.DateTools;
import com.young.pact.common.util.DateUtil;
import com.young.pact.common.util.RegexUtil;
import com.young.pact.common.util.StrTools;
import com.young.pact.domain.commonbelonger.CommonBelongerDO;
import com.young.pact.domain.commonbelonger.CommonBelongerQuery;
import com.young.pact.domain.commonbelonger.CommonBelongerVO;
import com.young.pact.domain.commoncostrule.CommonCostRuleDO;
import com.young.pact.domain.commoncostrule.CommonCostRuleVO;
import com.young.pact.domain.commongoods.CommonGoodsDO;
import com.young.pact.domain.commongoods.CommonGoodsPicDO;
import com.young.pact.domain.commonmeterread.CommonMeterReadDO;
import com.young.pact.domain.commonmeterread.CommonMeterReadPicDO;
import com.young.pact.domain.commonmeterread.MeterReadGoodsVO;
import com.young.pact.domain.commonpic.CommonPicDO;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayDO;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayQuery;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayVO;
import com.young.pact.domain.pactrenttransfer.PactRentTransferDO;
import com.young.pact.domain.pactrenttransfer.PactRentTransferQuery;
import com.young.pact.domain.pactrenttransfer.PactRentTransferVO;
import com.young.pact.domain.pactrenttransfer.PactTransferAllVO;
import com.young.pact.domain.rentbase.RentBaseDO;
import com.young.pact.domain.rentbase.RentBaseVO;
import com.young.pact.domain.rentbase.RentPactDO;
import com.young.pact.domain.rentbase.RentPactVO;
import com.young.pact.domain.rentcommonowner.RentCommonOwnerDO;
import com.young.pact.domain.rentcommonowner.RentCommonOwnerVO;
import com.young.pact.domain.rentcustomer.CustomerOwnerDO;
import com.young.pact.domain.rentcustomer.RentCustomerDO;
import com.young.pact.domain.rentcustomer.RentCustomerVO;
import com.young.pact.domain.rentroom.RentRoomDO;
import com.young.pact.domain.rentroom.RentRoomVO;
import com.young.pact.domain.statistics.AgendaDO;
import com.young.pact.manager.commonbelonger.CommonBelongerManager;
import com.young.pact.manager.commongoods.CommonGoodsManager;
import com.young.pact.manager.commonmeterread.CommonMeterReadManager;
import com.young.pact.manager.costrule.CommonCostRuleManager;
import com.young.pact.manager.financereceiptpay.FinanceReceiptPayManager;
import com.young.pact.manager.pactrenttransfer.PactRentTransferManager;
import com.young.pact.manager.rentbase.RentBaseManager;
import com.young.pact.manager.rentcommonowner.CommonOwnerManager;
import com.young.pact.manager.rentcustomer.RentCustomerManager;
import com.young.pact.manager.rentroom.RentRoomManager;
import com.young.pact.manager.statistics.StatisticsManager;
import com.young.pact.rpc.dept.DeptRpc;
import com.young.pact.rpc.house.HouseRpc;
import com.young.pact.rpc.operatelog.OperateLogRpc;
import com.young.pact.rpc.personal.PersonalRpc;
import com.young.pact.rpc.remind.RemindRpc;
import com.young.pact.rpc.room.RoomRpc;
import com.young.pact.rpc.serialize.SerializeRpc;
import com.young.product.api.domain.result.rpc.room.RoomResult;
import com.young.sso.api.domain.result.rpc.personal.PersonalResult;

/**
 * 
* @ClassName: PactRentTransferServiceImpl
* @Description: 调房协议service实现类
* @author LiuYuChi
* @date 2018年8月8日 下午1:51:50
*
 */
@Service("pactRentTransferService")
public class PactRentTransferServiceImpl implements PactRentTransferRestService{

	private PactRentTransferManager pactRentTransferManager;
	private static final Log LOG = LogFactory.getLog(PactRentTransferServiceImpl.class);
	private RentBaseManager rentBaseManager;
	private RentCustomerManager rentCustomerManager;
	private CommonMeterReadManager commonMeterReadManager;
	private RentRoomManager rentRoomManager;
	private CommonOwnerManager commonOwnerManager;
	private CommonBelongerManager commonBelongerManager;
	private FinanceReceiptPayManager financeReceiptPayManager;
	private CommonGoodsManager commonGoodsManager;

	private SerializeRpc serializeRpc;
	private PersonalRpc personalRpc;
	private OperateLogRpc operateLogRpc;
	private RoomRpc roomRpc;
	private HouseRpc houseRpc;
    private DeptRpc deptRpc;
    private Map<String,String> remindMap;
    private RemindRpc remindRpc;
    private StatisticsManager statisticsManager;
    private CommonCostRuleManager commonCostRuleManager;
	@Override
	public ApiResult<String> insertFirst(PactRentTransferParam param) {
		ApiResult<String> apiResult = new ApiResult<String>();
		try {
			//判断必填项
			if (param==null) {
				apiResult.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
				apiResult.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
	            return apiResult;
			}
			//手机格式
			if(StringUtil.isNotBlank(param.getTel())){
			    if(!RegexUtil.is_tel(param.getTel())){
	                apiResult.setCode(PactCommonEnum.TEL_NOT_MATCH.getCode());
	                apiResult.setMessage(PactCommonEnum.TEL_NOT_MATCH.getMessage());
	                return apiResult;
	            }
			}
			Map<String, Object> map = new HashMap<>();
            // copy 调房协议基本信息
            PactRentTransferDO pactRentTransferDO = new PactRentTransferDO();
            BeanUtils.copyProperties(param, pactRentTransferDO);
            map.put("pactRentTransferDO", pactRentTransferDO);
            // copy 收支
            List<FinanceReceiptPayDO> financeReceiptPayList = new ArrayList<>();
            BeanUtils.copyListProperties(param.getReceiptPayList(), financeReceiptPayList, FinanceReceiptPayDO.class);
            map.put("financeReceiptPayList", financeReceiptPayList);
            // copy 照片
            List<CommonPicDO> commonPicList = new ArrayList<>();
            BeanUtils.copyListProperties(param.getPicList(), commonPicList, CommonPicDO.class);
            map.put("commonPicList", commonPicList);
            // copy 抄表
            List<CommonMeterReadDO> meterReadList = new ArrayList<>();
            BeanUtils.copyListProperties(param.getMeterReadList(), meterReadList, CommonMeterReadDO.class);
            for(CommonMeterReadDO commonMeterReadDO: meterReadList){
                List<CommonMeterReadPicDO> commonMeterReadPicDOs = new ArrayList<>();
                BeanUtils.copyListProperties(commonMeterReadDO.getCommonMeterReadPicList(), commonMeterReadPicDOs, CommonMeterReadPicDO.class);
                commonMeterReadDO.setCommonMeterReadPicList(commonMeterReadPicDOs);
            }
            map.put("meterReadList", meterReadList);
            // copy 物品
            List<CommonGoodsDO> goodsList = new ArrayList<>();
            BeanUtils.copyListProperties(param.getGoodsList(), goodsList, CommonGoodsDO.class);
            for(CommonGoodsDO commonGoodsDO:goodsList){
                List<CommonGoodsPicDO> commonGoodsPicList = new ArrayList<>();
                BeanUtils.copyListProperties(commonGoodsDO.getCommonGoodsPicList(), commonGoodsPicList, CommonGoodsPicDO.class);
                commonGoodsDO.setCommonGoodsPicList(commonGoodsPicList);
            }
            map.put("goodsList", goodsList);
            // copy 责任人
            CommonBelongerDO commonBelongDO = new CommonBelongerDO();
            commonBelongDO.setUserPin(param.getUserPin());
            map.put("commonBelongDO", commonBelongDO);
            // redis key
            StringBuilder sb = new StringBuilder();
            //key=原合同编码+业务类型编码+pin
            sb.append(pactRentTransferDO.getPirmaryPactCode()).append(StringConsts.UNDERLINE)
            .append(StringConsts.REDIS_TRANSFER).append(StringConsts.UNDERLINE).append(pactRentTransferDO.getCreateName());
            pactRentTransferManager.saveRedis(map, RedisConsts.PURCHASE_TIME, sb.toString());
            apiResult.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
            apiResult.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
            apiResult.setData(sb.toString());
		} catch (PactManagerExcepion e) {
            LOG.error(CommonEnum.SYSTEM_EXCEPTION.getMessage(), e);
            apiResult.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            apiResult.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        } catch (Exception e) {
            LOG.error(CommonEnum.SYSTEM_EXCEPTION.getMessage(), e);
            apiResult.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            apiResult.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        }
		return apiResult;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ApiResult<PactRentTransferResult> getFirst(String key) {
		ApiResult<PactRentTransferResult> apiResult = new ApiResult<PactRentTransferResult>();
		if(StringUtils.isBlank(key)){
			apiResult.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
			apiResult.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return apiResult;
        }
		try {
			Map<String, Object> map = pactRentTransferManager.getRedis(key);
			if (map!=null&&map.size()>0) {
				PactRentTransferResult pactRentTransfer = new PactRentTransferResult();
	            // copy 托进合同基本信息
				PactRentTransferDO pactRentTransferDO = (PactRentTransferDO) map.get("pactRentTransferDO");
	            if(null != pactRentTransferDO){
	                BeanUtils.copyProperties(pactRentTransferDO, pactRentTransfer);
	            }
	            // copy 托进合同收支
	            List<FinanceReceiptPayDO> financeReceiptPayList = (List<FinanceReceiptPayDO>) map.get("financeReceiptPayList");
	            if(null != financeReceiptPayList){
	                List<FinanceReceiptPayResult> financeReceiptPayResults = new ArrayList<>();
	                BeanUtils.copyListProperties(financeReceiptPayList, financeReceiptPayResults, FinanceReceiptPayResult.class);
	                pactRentTransfer.setReceiptPayList(financeReceiptPayResults);
	            }
	            // copy 托进合同照片
	            List<CommonPicDO> commonPicList = (List<CommonPicDO>) map.get("commonPicList");
	            if(null != commonPicList){
	                List<CommonPicResult> commonPicResults = new ArrayList<>();
	                BeanUtils.copyListProperties(commonPicList, commonPicResults, CommonPicResult.class);
	                pactRentTransfer.setPicList(commonPicResults);
	            }
	            // copy 抄表
	            List<CommonMeterReadDO> meterReadList = (List<CommonMeterReadDO>) map.get("meterReadList");
	            if (null !=meterReadList) {
					List<CommonMeterReadResult> commonMeterReadResults = new ArrayList<>();
					BeanUtils.copyListProperties(meterReadList, commonMeterReadResults, CommonMeterReadResult.class);
					pactRentTransfer.setMeterReadList(commonMeterReadResults);
				}
	            // copy 物品
	            List<CommonGoodsDO> goodsList = (List<CommonGoodsDO>) map.get("goodsList");
	            if (null != goodsList) {
					List<CommonGoodsResult> commonGoodsResults = new ArrayList<>();
					BeanUtils.copyListProperties(goodsList, commonGoodsResults, CommonGoodsResult.class);
					pactRentTransfer.setGoodsList(commonGoodsResults);
				}
	            // copy 责任人
	            CommonBelongerDO commonBelongDO = (CommonBelongerDO) map.get("commonBelongDO");
	            pactRentTransfer.setUserName(commonBelongDO.getUserName());
	            pactRentTransfer.setUserPin(commonBelongDO.getUserPin());
	            
	            apiResult.setData(pactRentTransfer);
	            apiResult.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
	            apiResult.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
			}else{
				apiResult.setData(null);
	            apiResult.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
	            apiResult.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
			}
		} catch (PactManagerExcepion e) {
            LOG.error(CommonEnum.SYSTEM_EXCEPTION.getMessage(), e);
            apiResult.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            apiResult.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        } catch (Exception e) {
            LOG.error(CommonEnum.SYSTEM_EXCEPTION.getMessage(), e);
            apiResult.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            apiResult.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        }
		return apiResult;
	}
	

	@SuppressWarnings({ "unchecked" })
	@Override
	public ApiResult<String> insertTransfer(PactRentTransferParam param) {
		ApiResult<String> apiResult = new ApiResult<String>();
		ApiResult<Boolean> result = new ApiResult<Boolean>();
		try {
			//调房协议信息
			StringBuilder sb = new StringBuilder();
            //key=原合同编码+业务类型编码+pin
            sb.append(param.getPirmaryPactCode()).append(StringConsts.UNDERLINE)
            .append(StringConsts.REDIS_TRANSFER).append(StringConsts.UNDERLINE).append(param.getCreateName());
            String key= sb.toString();
            String pin=param.getCreateName();
            RentRoomVO rentRoomVO = new RentRoomVO();
            Map<String, Object> map = pactRentTransferManager.getRedis(key);
            String transferCode = serializeRpc.queryCodeBySerializeType(SerializeTypeConts.TRANSFER);
            /***********************************  获取房间缓存  start *************************************/
            StringBuilder roomRedisKey = new StringBuilder("");
            roomRedisKey.append(param.getPirmaryPactCode()).append(StringConsts.UNDERLINE)
                .append(StringConsts.REDIS_ROOM_KEY).append(StringConsts.UNDERLINE)
                .append(param.getCreateName());
            rentRoomVO = rentRoomManager.getRoomRedis(roomRedisKey.toString());  
            if(null == rentRoomVO){
                apiResult.setCode(PactCommonEnum.RENT_ROOM_REDIS_INVALID.getCode());
                apiResult.setMessage(PactCommonEnum.RENT_ROOM_REDIS_INVALID.getMessage());
                return apiResult;
            }
            /*********************************** 获取房间缓存  end *************************************/
            /*********************************** 获取新合同物业交接缓存  start *************************************/
            StringBuilder propertyRedisKey = new StringBuilder("");
            propertyRedisKey.append(param.getPirmaryPactCode()).append(StringConsts.UNDERLINE)
                .append(StringConsts.REDIS_PROPERTYTRANSFER_KEY).append(StringConsts.UNDERLINE)
                .append(param.getCreateName());
            MeterReadGoodsVO pactMeterReadGoodsVO = new MeterReadGoodsVO();
            pactMeterReadGoodsVO = commonMeterReadManager.getMeterGoodsRedis(propertyRedisKey.toString());
            if(null == pactMeterReadGoodsVO){
                apiResult.setCode(PactCommonEnum.RENT_PROPERTY_REDIS_INVALID.getCode());
                apiResult.setMessage(PactCommonEnum.RENT_PROPERTY_REDIS_INVALID.getMessage());
                return apiResult;
            }
            /*********************************** 获取新合同物业交接缓存  end *************************************/
            //协议信息
            if (map!=null) {
				//调房信息
				PactRentTransferDO pactRentTransferDO = (PactRentTransferDO) map.get("pactRentTransferDO");
				pactRentTransferDO.setTransferCode(transferCode);
				//协议收支信息
				List<FinanceReceiptPayDO> transferRecPayList = (List<FinanceReceiptPayDO>) map.get("financeReceiptPayList");
				//调房协议照片
				List<CommonPicDO> transferPicList = (List<CommonPicDO>) map.get("commonPicList");
				//协议抄表信息
				List<CommonMeterReadDO> transferMeterReadList = (List<CommonMeterReadDO>) map.get("meterReadList");
				//协议物品信息
				List<CommonGoodsDO> transferGoodsList = (List<CommonGoodsDO>) map.get("goodsList");
				// 协议签约管家
                CommonBelongerDO transferBelongDO = (CommonBelongerDO) map.get("commonBelongDO");
                // 签约管家
                param.setUserPin(transferBelongDO.getUserPin());
				if (StringConsts.VARIABL_CONDITION.equals(pactRentTransferDO.getNatives())) {
	                result = initDataVariabl(pactRentTransferDO,transferRecPayList,transferPicList,
                            transferMeterReadList,transferGoodsList,
                            pactMeterReadGoodsVO,rentRoomVO,param);
	            }else if(StringConsts.ORIGINAL_CONDITION.equals(pactRentTransferDO.getNatives())){
				    result = initDataByOriginal(pactRentTransferDO,transferRecPayList,transferPicList,
	                        transferMeterReadList,transferGoodsList,
	                        pactMeterReadGoodsVO,rentRoomVO,param);
				    
				}
	            if(result.getData()){
	                Map<String, Object> operateMap = new HashMap<String, Object>();
	                operateMap.put(NormalConstant.SERVICETYPE, SerializeTypeConts.TRANSFER);
	                operateMap.put(NormalConstant.SERVICECODE, pactRentTransferDO.getTransferCode());
	                operateMap.put(NormalConstant.OPERATETYPE, StringConsts.ADD);
	                operateMap.put(NormalConstant.OPERATEPIN, pin);
	                operateMap.put(NormalConstant.OPERATECONTENT, StringConsts.ADD_DATA);
	                operateLogRpc.saveOperateLog(operateMap);
	                apiResult.setData(pactRentTransferDO.getTransferCode());
	                apiResult.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
	                apiResult.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
	            }else{
                    apiResult.setCode(result.getCode());
                    apiResult.setMessage(result.getMessage());
	            }
			}else{
				apiResult.setCode(PactCommonEnum.PACT_RENT_TRANSFER_REDIS_FAIL.getCode());
	            apiResult.setMessage(PactCommonEnum.PACT_RENT_TRANSFER_REDIS_FAIL.getMessage());
	            return apiResult;
			}
		} catch (PactManagerExcepion e) {
            LOG.error(CommonEnum.SYSTEM_EXCEPTION.getMessage(), e);
            apiResult.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            apiResult.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        } catch (Exception e) {
            LOG.error(CommonEnum.SYSTEM_EXCEPTION.getMessage(), e);
            apiResult.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            apiResult.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        }
		return apiResult;
	}
	/**
	 * 
	* @Title: initDataByOriginal
	* @Description: TODO( 初始化数据-原条件  )
	* @author GuoXiaoPeng
	* @param pactRentTransferDO 调房协议信息
	* @param transferRecPayList 调房协议收支
	* @param transferPicList 调房协议图片
	* @param transferMeterReadList 调房协议抄表
	* @param transferGoodsList 调房协议物品
	* @param pactMeterReadGoodsVO 合同的物业交接
	* @param rentRoomVO 选择的房间信息
	* @param param 登录人信息
	* @return 返回结果
	* @throws 异常
	 */
	private ApiResult<Boolean> initDataByOriginal(PactRentTransferDO pactRentTransferDO, List<FinanceReceiptPayDO> transferRecPayList,
	        List<CommonPicDO> transferPicList, List<CommonMeterReadDO> transferMeterReadList,
	        List<CommonGoodsDO> transferGoodsList,
	        MeterReadGoodsVO pactMeterReadGoodsVO,RentRoomVO rentRoomVO, PactRentTransferParam param
	        ) {
	    ApiResult<Boolean> apiResult = new ApiResult<Boolean>();
	    String pin = param.getCreateName();
        String rentPactCode = "";
        String renterCode = "";
        String transferCode = "";
        if(null != pactRentTransferDO && StringUtils.isNotBlank(pactRentTransferDO.getTransferCode())){
            transferCode = pactRentTransferDO.getTransferCode();
        }
        //协议图片+合同图片
        List<CommonPicDO> allPicList = new ArrayList<>();
        //协议签约人+协议维护人+合同签约人
        List<CommonBelongerDO> allBelongerDOList =  new ArrayList<>();
        //协议收支+合同收支
        List<FinanceReceiptPayDO> allRecPayDOs = new ArrayList<>();
        try {
            String ip = param.getIp();
            transferCode = pactRentTransferDO.getTransferCode();
            RentBaseDO rentBaseDO = new RentBaseDO();
            rentPactCode = serializeRpc.queryCodeBySerializeType(SerializeTypeConts.RENT);
            renterCode = serializeRpc.queryCodeBySerializeType(SerializeTypeConts.RENTER);
            /***********************房间信息   start************************************************/
            //房间
            RentRoomDO rentRoomDO = new RentRoomDO();
            //根据房间编码查询销控房间信息 
            RoomResult roomResult = roomRpc.getRoomBase(rentRoomVO.getRoomCode());
            //房间
            rentRoomDO.setRoomCode(rentRoomVO.getRoomCode());
            rentRoomDO.setRoomNo(roomResult.getRoomCode());
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
            rentRoomDO.setIsPurchase(2);
            rentRoomDO.setRentTime(new Date());
            rentRoomDO.setVacantDays(roomResult.getVacantDays());
            List<GetHouseRpcResult> houseList = houseRpc.getHouseByCodeList(roomResult.getHouseCode());
            if(null!=houseList&&houseList.size()>0){
                GetHouseRpcResult getHouseRpcResult = houseList.get(0);
                rentRoomDO.setFloor(getHouseRpcResult.getCurrentFloor());
                rentRoomDO.setAllFloor(getHouseRpcResult.getAllFloor());
            }
            rentRoomDO.setAddress(roomResult.getAddress());
            rentRoomDO.setInternetFee(roomResult.getInternetFee());
            rentRoomDO.setIp(ip);
            rentRoomDO.setCreateName(pin);
            pactRentTransferDO.setAddress(roomResult.getAddress());
            /*************房间信息      end******************/
            /*********************** 调房协议信息 start****************************/
            pactRentTransferDO.setTransferCode(transferCode);
            pactRentTransferDO.setNatives(param.getNatives());
            pactRentTransferDO.setPirmaryPactCode(param.getPirmaryPactCode());
            pactRentTransferDO.setState(1);
            pactRentTransferDO.setIsDelete(0);
            pactRentTransferDO.setNewPactCode(rentPactCode);
            pactRentTransferDO.setAddress(roomResult.getAddress());
            /*********************** 调房协议信息 end****************************/
            /*********************** 调房协议签约人 start****************************/
            CommonBelongerDO transferDealBelongDO = new CommonBelongerDO();
            ApiResult<PersonalResult> personalResult = personalRpc.getPersonalResultBypin(param.getUserPin());
            if(null!=personalResult&&StringConsts.REQUEST_SUCCESS_CODE.equals(personalResult.getCode())){
                PersonalResult personalResultData = personalResult.getData();
                transferDealBelongDO.setDeptCode(personalResultData.getDeptCode());
                transferDealBelongDO.setDeptName(personalResultData.getDeptName());
                transferDealBelongDO.setUserName(personalResultData.getEmployeeName());
                transferDealBelongDO.setUserTel(personalResultData.getEmployeeTel());
                transferDealBelongDO.setUserPin(param.getUserPin());
                transferDealBelongDO.setPactType(StringConsts.RENT_TRANSFER);
                transferDealBelongDO.setUserRole(StringConsts.BELONGER_DEAL);
                transferDealBelongDO.setPactCode(transferCode);
                transferDealBelongDO.setIp(ip);
                transferDealBelongDO.setCreateName(pin);
            }
            /*********************** 调房协议签约人end****************************/
         
            /*********************** 调房协议维护人 start****************************/
            CommonBelongerDO transferGuardianBelongDO = new CommonBelongerDO();
            BeanUtils.copyProperties(transferDealBelongDO, transferGuardianBelongDO);
            transferGuardianBelongDO.setUserRole(StringConsts.BELONGER_PERSON);
            transferGuardianBelongDO.setPactType(StringConsts.RENT_TRANSFER);
            transferGuardianBelongDO.setPactCode(transferCode);
            /*********************** 调房协议维护人 end****************************/
            
            /*********************** 调房协议收支 start****************************/
            if (transferRecPayList != null) {
                for (FinanceReceiptPayDO financeReceiptPayDO : transferRecPayList) {
                    financeReceiptPayDO.setPactCode(transferCode);
                    financeReceiptPayDO.setPactType(PactTypeConsts.RENT_ROOM);
                    financeReceiptPayDO.setCreateName(pin);
                    financeReceiptPayDO.setIp(ip);
                    if(null != financeReceiptPayDO.getType() && 0 == financeReceiptPayDO.getType()){// 应收
                        financeReceiptPayDO.setCostState(1);// 待收款
                    }else if(null != financeReceiptPayDO.getType() && 2 == financeReceiptPayDO.getType()){// 应支
                        financeReceiptPayDO.setCostState(4);// 待付款
                    }
                    financeReceiptPayDO.setHouseCode(rentRoomDO.getHouseCode());
                    financeReceiptPayDO.setRoomCode(rentRoomDO.getRoomCode());
                    financeReceiptPayDO.setAddress(rentRoomDO.getAddress());
                }
            }
            /*********************** 调房协议收支 end****************************/
            
            /********************** 从账单里获取收款人账号   start ***************************************/
            FinanceReceiptPayDO payeeFormRecPay = getPayeeFormRecPay(transferRecPayList);
            pactRentTransferDO.setName(payeeFormRecPay.getName());
            pactRentTransferDO.setAccountNum(payeeFormRecPay.getAccount());
            pactRentTransferDO.setCaseBank(payeeFormRecPay.getBank());
            pactRentTransferDO.setOpenBank(payeeFormRecPay.getOpenBank());
            pactRentTransferDO.setTel(payeeFormRecPay.getTel());
            /********************** 从账单里获取收款人账号   end ***************************************/
          
            /*********************** 调房协议抄表  start****************************/
            if(null != transferMeterReadList ){
                for (CommonMeterReadDO commonMeterReadDO : transferMeterReadList) {
                    commonMeterReadDO.setIp(ip);
                    commonMeterReadDO.setCreateName(pin);
                    commonMeterReadDO.setPactCode(transferCode);
                    commonMeterReadDO.setPactType(PactTypeConsts.RENT_ROOM);
                    
                    if(null != commonMeterReadDO.getCommonMeterReadPicList()&&commonMeterReadDO.getCommonMeterReadPicList().size()>0){
                        for (CommonMeterReadPicDO commonMeterReadPicDO : commonMeterReadDO.getCommonMeterReadPicList()) {
                            commonMeterReadPicDO.setIp(ip);
                            commonMeterReadPicDO.setCreateName(pin);
                        }
                    }
                }
            }
            /*********************** 调房协议抄表 end****************************/
            
            /*********************** 调房协议物品 start****************************/
            if(null != transferGoodsList){
                for (CommonGoodsDO commonGoodsDO : transferGoodsList) {
                    commonGoodsDO.setIp(ip);
                    commonGoodsDO.setCreateName(pin);
                    commonGoodsDO.setPactCode(transferCode);
                    commonGoodsDO.setPactType(PactTypeConsts.RENT_ROOM);
                    
                    if(null != commonGoodsDO.getCommonGoodsPicList()&&commonGoodsDO.getCommonGoodsPicList().size()>0){
                        for (CommonGoodsPicDO commonGoodsPicDO : commonGoodsDO.getCommonGoodsPicList()) {
                            commonGoodsPicDO.setIp(ip);
                            commonGoodsPicDO.setCreateName(pin);
                        }
                    }
                }
            }
            /*********************** 调房协议物品 end****************************/
            
            /*********************** 调房协议图片 start****************************/
            for(CommonPicDO commonPicDO :transferPicList){
                commonPicDO.setIp(ip);
                commonPicDO.setCreateName(pin);
                commonPicDO.setPactCode(transferCode);
                commonPicDO.setPactType(PactTypeConsts.RENT_ROOM);
            }
           
            /*********************** 调房协议图片 end****************************/
            /*********************** 新合同 start****************************/
            //原条件调房合同复制老合同
            String oldPactCode = pactRentTransferDO.getPirmaryPactCode();
            RentBaseVO rentBaseVO = rentBaseManager.getRentBaseByCode(oldPactCode);
            rentBaseDO = initNewPact(rentBaseVO,pactRentTransferDO,pin,ip,rentPactCode);
            /*********************** 新合同 end****************************/
            
            
            /*********************** 新合同签约管家 start****************************/
            CommonBelongerDO pactDealBelongDO = new CommonBelongerDO();
            if(null!=personalResult&&StringConsts.REQUEST_SUCCESS_CODE.equals(personalResult.getCode())){
                PersonalResult personalResultData = personalResult.getData();
                pactDealBelongDO.setDeptCode(personalResultData.getDeptCode());
                pactDealBelongDO.setDeptName(personalResultData.getDeptName());
                pactDealBelongDO.setUserName(personalResultData.getEmployeeName());
                pactDealBelongDO.setUserTel(personalResultData.getEmployeeTel());
                pactDealBelongDO.setUserPin(param.getUserPin());
                pactDealBelongDO.setPactType(PactTypeConsts.RENT);
                pactDealBelongDO.setUserRole(StringConsts.RENT_USER_ROLE);
                pactDealBelongDO.setPactCode(rentPactCode);
                pactDealBelongDO.setIp(ip);
                pactDealBelongDO.setCreateName(pin);
            }
            /*********************** 新合同签约管家 end****************************/
            
            /*********************** 新合同收支规则  start****************************/
            List<CommonCostRuleDO> pactCostRuleDOs = new ArrayList<>();
            List<CommonCostRuleVO> pactCostRuleList = commonCostRuleManager.listCommonCostRule(oldPactCode);
            if(null != pactCostRuleList){
                BeanUtils.copyListProperties(pactCostRuleList, pactCostRuleDOs, CommonCostRuleDO.class);
            }
            if(null != pactCostRuleDOs){
                for(CommonCostRuleDO commonCostRuleDO:pactCostRuleDOs){
                    commonCostRuleDO.setPactCode(rentPactCode);
                    commonCostRuleDO.setIp(ip);
                    commonCostRuleDO.setCreateName(pin);
                }
            }
            /*********************** 新合同收支规则  end****************************/
            
            /*********************** 新合同收支 start****************************/
            List<FinanceReceiptPayDO> pactRecPayDOs = new ArrayList<>();
            FinanceReceiptPayQuery query = new FinanceReceiptPayQuery();
            query.setPactCode(oldPactCode);
            Date times =DateTools.getLastDay(pactRentTransferDO.getTransferTime(), 1);
            Date time=DateTools.weeHours(times, 0);
            query.setCostTimeStr(DateTools.parseDay(time));
            List<FinanceReceiptPayVO> pactRecPayList = financeReceiptPayManager.listFinanceReceiptPayDO(query);
            if(null != pactRecPayList){
                for(FinanceReceiptPayVO financeReceiptPayVO:pactRecPayList){
                    FinanceReceiptPayDO pactRecPayDO = new FinanceReceiptPayDO();
                    BeanUtils.copyProperties(financeReceiptPayVO, pactRecPayDO);
                    pactRecPayDO.setPactCode(rentPactCode);
                    pactRecPayDO.setPactType(PactTypeConsts.RENT);
                    pactRecPayDO.setIp(ip);
                    pactRecPayDO.setCreateName(pin);
                    pactRecPayDOs.add(pactRecPayDO);
                }
            }
            /*********************** 新合同收支  end****************************/
            
            /*********************** 新合同抄表  start****************************/
            List<CommonMeterReadDO> pactMeterReadDOs = new ArrayList<>();
            List<CommonMeterReadDO> pactMeterReadList = pactMeterReadGoodsVO.getMeterReadList();
            if(null != pactMeterReadList){
                for (CommonMeterReadDO commonMeterReadDO : pactMeterReadList) {
                    commonMeterReadDO.setIp(ip);
                    commonMeterReadDO.setCreateName(pin);
                    commonMeterReadDO.setPactCode(rentPactCode);
                    commonMeterReadDO.setPactType(PactTypeConsts.RENT);
                    if(null != commonMeterReadDO.getCommonMeterReadPicList()&&commonMeterReadDO.getCommonMeterReadPicList().size()>0){
                        List<CommonMeterReadPicDO> readPicList = new ArrayList<>();
                        BeanUtils.copyListProperties(commonMeterReadDO.getCommonMeterReadPicList(), readPicList, CommonMeterReadPicDO.class);
                        commonMeterReadDO.setCommonMeterReadPicList(readPicList);
                        for (CommonMeterReadPicDO commonMeterReadPicDO : commonMeterReadDO.getCommonMeterReadPicList()) {
                            commonMeterReadPicDO.setIp(ip);
                            commonMeterReadPicDO.setCreateName(pin);
                        }
                    }
                    pactMeterReadDOs.add(commonMeterReadDO);
                }
            }
            /*********************** 新合同抄表 end****************************/
            
            /*********************** 新合同物品 start****************************/
            List<CommonGoodsDO> pactGoodsDOs = new ArrayList<>();
            List<CommonGoodsDO> pactGoodsList = pactMeterReadGoodsVO.getGoodsList();
            if(null != pactGoodsList){
                for (CommonGoodsDO commonGoodsDO : pactGoodsList) {
                    commonGoodsDO.setIp(ip);
                    commonGoodsDO.setCreateName(pin);
                    commonGoodsDO.setPactCode(rentPactCode);
                    commonGoodsDO.setPactType(PactTypeConsts.RENT);
                    
                    if(null != commonGoodsDO.getCommonGoodsPicList()&&commonGoodsDO.getCommonGoodsPicList().size()>0){
                        List<CommonGoodsPicDO> picList = new ArrayList<>();
                        BeanUtils.copyListProperties(commonGoodsDO.getCommonGoodsPicList(), picList, CommonGoodsPicDO.class);
                        commonGoodsDO.setCommonGoodsPicList(picList);
                        for (CommonGoodsPicDO commonGoodsPicDO : commonGoodsDO.getCommonGoodsPicList()) {
                            commonGoodsPicDO.setIp(ip);
                            commonGoodsPicDO.setCreateName(pin);
                        }
                    }
                    pactGoodsDOs.add(commonGoodsDO);
                }
            }
            /*********************** 新合同物品 end****************************/
            
            /*********************** 新合同图片 start****************************/
            List<CommonPicDO> pactPicDOs = new ArrayList<>();
            for(CommonPicDO commonPicDO :transferPicList){
                CommonPicDO pactPic = new CommonPicDO();
                BeanUtils.copyProperties(commonPicDO, pactPic);
                pactPic.setPactCode(rentPactCode);
                pactPic.setPactType(PactTypeConsts.RENT);
                pactPicDOs.add(pactPic);
            }
            /*********************** 新合同图片 end****************************/
            
     
            /*************托出租客 +托出租客共同居住人     start******************/
            //托出租客
            CustomerOwnerDO customerOwnerDO = new CustomerOwnerDO();
            RentCustomerVO customer = rentCustomerManager.getRentCustomerByPactCode(oldPactCode);
            RentCustomerDO rentCustomerDO = new RentCustomerDO();
            BeanUtils.copyProperties(customer, rentCustomerDO);
            rentCustomerDO.setRentPactCode(rentPactCode);
            rentCustomerDO.setCreateName(pin);
            rentCustomerDO.setRenterCode(renterCode);
            rentCustomerDO.setIp(ip);
            customerOwnerDO.setRenter(rentCustomerDO);
            //托出租客共同居住人 
            List<RentCommonOwnerVO> cohabitantList = commonOwnerManager.listCommonOwnerByRenterCode(customer.getRenterCode());
            List<RentCommonOwnerDO> rentCommonOwnerList = new ArrayList<>();
            BeanUtils.copyListProperties(cohabitantList, rentCommonOwnerList, RentCommonOwnerDO.class);
            customerOwnerDO.setCohabitantList(rentCommonOwnerList);
            if(null!=rentCommonOwnerList){
                for(RentCommonOwnerDO rentCommonOwnerDO:rentCommonOwnerList){
                    rentCommonOwnerDO.setRenterCode(renterCode);
                    rentCommonOwnerDO.setCreateName(pin);
                    rentCommonOwnerDO.setIp(ip);
                }
            }
            /*************托出租客 +托出租客共同居住人      end******************/
            allPicList.addAll(transferPicList);
            allPicList.addAll(pactPicDOs);
            allBelongerDOList.add(transferDealBelongDO);
            allBelongerDOList.add(transferGuardianBelongDO);
            allBelongerDOList.add(pactDealBelongDO);
            allRecPayDOs.addAll(transferRecPayList);
            allRecPayDOs.addAll(pactRecPayDOs);
            boolean flag = pactRentTransferManager.saveRentBaseAndTransfer(pactRentTransferDO, 
                    transferMeterReadList, transferGoodsList, rentRoomDO, rentCustomerDO, rentCommonOwnerList, 
                    rentBaseDO, pactCostRuleDOs, allRecPayDOs, allPicList , pactMeterReadDOs, 
                    pactGoodsDOs, allBelongerDOList);
            apiResult.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            apiResult.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
            apiResult.setData(flag);
        } catch (PactManagerExcepion e) {
            LOG.error(CommonEnum.SYSTEM_EXCEPTION.getMessage(), e);
            apiResult.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            apiResult.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
            apiResult.setData(false);
        } catch (Exception e) {
            LOG.error(CommonEnum.SYSTEM_EXCEPTION.getMessage(), e);
            apiResult.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            apiResult.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
            apiResult.setData(false);
        }
        return apiResult;
	}
	
    /**
     * 
    * @Title: initDataVariabl
    * @Description: TODO( 初始化数据-变条件  )
    * @author GuoXiaoPeng
    * @param pactRentTransferDO 调房协议信息
    * @param transferRecPayList 调房协议收支
    * @param transferPicList 调房协议图片
    * @param transferMeterReadList 调房协议抄表
    * @param transferGoodsList 调房协议物品
    * @param pactMeterReadGoodsVO 合同的物业交接
    * @param rentRoomVO 选择的房间信息
    * @param param 登录人信息
    * @return 返回结果
    * @throws 异常
     */
    private ApiResult<Boolean> initDataVariabl(PactRentTransferDO pactRentTransferDO, List<FinanceReceiptPayDO> transferRecPayList,
            List<CommonPicDO> transferPicList, List<CommonMeterReadDO> transferMeterReadList,
            List<CommonGoodsDO> transferGoodsList,
            MeterReadGoodsVO pactMeterReadGoodsVO,RentRoomVO rentRoomVO, PactRentTransferParam param
            ) {
        ApiResult<Boolean> apiResult = new ApiResult<Boolean>();
        String pin = param.getCreateName();
        String rentPactCode = "" ;
        String renterCode ="";
        String transferCode = ""; 
        String oldPactCode = param.getPirmaryPactCode();
        //协议图片+合同图片
        List<CommonPicDO> allPicList = new ArrayList<>();
        //协议签约人+协议维护人+合同签约人
        List<CommonBelongerDO> allBelongerDOList =  new ArrayList<>();
        //协议收支+合同收支
        List<FinanceReceiptPayDO> allRecPayDOs = new ArrayList<>();
        try {
            String ip = param.getIp();
            transferCode = pactRentTransferDO.getTransferCode();
            RentBaseDO rentBaseDO = new RentBaseDO();
            rentPactCode = serializeRpc.queryCodeBySerializeType(SerializeTypeConts.RENT);
            renterCode = serializeRpc.queryCodeBySerializeType(SerializeTypeConts.RENTER);
            /***********************房间信息   start************************************************/
            //房间
            RentRoomDO rentRoomDO = new RentRoomDO();
            //根据房间编码查询销控房间信息 
            RoomResult roomResult = roomRpc.getRoomBase(rentRoomVO.getRoomCode());
            //房间
            rentRoomDO.setRoomCode(rentRoomVO.getRoomCode());
            rentRoomDO.setRoomNo(roomResult.getRoomCode());
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
            rentRoomDO.setIsPurchase(2);
            rentRoomDO.setRentTime(new Date());
            rentRoomDO.setVacantDays(roomResult.getVacantDays());
            List<GetHouseRpcResult> houseList = houseRpc.getHouseByCodeList(roomResult.getHouseCode());
            if(null!=houseList&&houseList.size()>0){
                GetHouseRpcResult getHouseRpcResult = houseList.get(0);
                rentRoomDO.setFloor(getHouseRpcResult.getCurrentFloor());
                rentRoomDO.setAllFloor(getHouseRpcResult.getAllFloor());
            }
            rentRoomDO.setAddress(roomResult.getAddress());
            rentRoomDO.setInternetFee(roomResult.getInternetFee());
            rentRoomDO.setIp(ip);
            rentRoomDO.setCreateName(pin);
            pactRentTransferDO.setAddress(roomResult.getAddress());
            /*************房间信息      end******************/
            /*********************** 调房协议信息 start****************************/
            pactRentTransferDO.setTransferCode(transferCode);
            pactRentTransferDO.setNatives(param.getNatives());
            pactRentTransferDO.setPirmaryPactCode(param.getPirmaryPactCode());
            pactRentTransferDO.setState(1);
            pactRentTransferDO.setIsDelete(0);
            pactRentTransferDO.setNewPactCode(rentPactCode);
            pactRentTransferDO.setAddress(roomResult.getAddress());
            /*********************** 调房协议信息 end****************************/
            /*********************** 调房协议签约人 start****************************/
            CommonBelongerDO transferDealBelongDO = new CommonBelongerDO();
            ApiResult<PersonalResult> personalResult = personalRpc.getPersonalResultBypin(param.getUserPin());
            if(null!=personalResult&&StringConsts.REQUEST_SUCCESS_CODE.equals(personalResult.getCode())){
                PersonalResult personalResultData = personalResult.getData();
                transferDealBelongDO.setDeptCode(personalResultData.getDeptCode());
                transferDealBelongDO.setDeptName(personalResultData.getDeptName());
                transferDealBelongDO.setUserName(personalResultData.getEmployeeName());
                transferDealBelongDO.setUserTel(personalResultData.getEmployeeTel());
                transferDealBelongDO.setUserPin(param.getUserPin());
                transferDealBelongDO.setPactType(StringConsts.RENT_TRANSFER);
                transferDealBelongDO.setUserRole(StringConsts.BELONGER_DEAL);
                transferDealBelongDO.setPactCode(transferCode);
                transferDealBelongDO.setIp(ip);
                transferDealBelongDO.setCreateName(pin);
            }
            /*********************** 调房协议签约人end****************************/
         
            /*********************** 调房协议维护人 start****************************/
            CommonBelongerDO transferGuardianBelongDO = new CommonBelongerDO();
            BeanUtils.copyProperties(transferDealBelongDO, transferGuardianBelongDO);
            transferGuardianBelongDO.setUserRole(StringConsts.BELONGER_PERSON);
            transferGuardianBelongDO.setPactType(StringConsts.RENT_TRANSFER);
            transferGuardianBelongDO.setPactCode(transferCode);
            /*********************** 调房协议维护人 end****************************/
            
            /*********************** 调房协议收支 start****************************/
            if (transferRecPayList != null) {
                for (FinanceReceiptPayDO financeReceiptPayDO : transferRecPayList) {
                    financeReceiptPayDO.setPactCode(transferCode);
                    financeReceiptPayDO.setPactType(PactTypeConsts.RENT_ROOM);
                    financeReceiptPayDO.setCreateName(pin);
                    financeReceiptPayDO.setIp(ip);
                    if(null != financeReceiptPayDO.getType() && 0 == financeReceiptPayDO.getType()){// 应收
                        financeReceiptPayDO.setCostState(1);// 待收款
                    }else if(null != financeReceiptPayDO.getType() && 2 == financeReceiptPayDO.getType()){// 应支
                        financeReceiptPayDO.setCostState(4);// 待付款
                    }
                    financeReceiptPayDO.setHouseCode(rentRoomDO.getHouseCode());
                    financeReceiptPayDO.setRoomCode(rentRoomDO.getRoomCode());
                    financeReceiptPayDO.setAddress(rentRoomDO.getAddress());
                }
            }
            /*********************** 调房协议收支 end****************************/
            
            /********************** 从账单里获取收款人账号   start ***************************************/
            FinanceReceiptPayDO payeeFormRecPay = getPayeeFormRecPay(transferRecPayList);
            pactRentTransferDO.setName(payeeFormRecPay.getName());
            pactRentTransferDO.setAccountNum(payeeFormRecPay.getAccount());
            pactRentTransferDO.setCaseBank(payeeFormRecPay.getBank());
            pactRentTransferDO.setOpenBank(payeeFormRecPay.getOpenBank());
            pactRentTransferDO.setTel(payeeFormRecPay.getTel());
            /********************** 从账单里获取收款人账号   end ***************************************/
          
            /*********************** 调房协议抄表  start****************************/
            if(null != transferMeterReadList ){
                for (CommonMeterReadDO commonMeterReadDO : transferMeterReadList) {
                    commonMeterReadDO.setIp(ip);
                    commonMeterReadDO.setCreateName(pin);
                    commonMeterReadDO.setPactCode(transferCode);
                    commonMeterReadDO.setPactType(PactTypeConsts.RENT_ROOM);
                    
                    if(null != commonMeterReadDO.getCommonMeterReadPicList()&&commonMeterReadDO.getCommonMeterReadPicList().size()>0){
                        for (CommonMeterReadPicDO commonMeterReadPicDO : commonMeterReadDO.getCommonMeterReadPicList()) {
                            commonMeterReadPicDO.setIp(ip);
                            commonMeterReadPicDO.setCreateName(pin);
                        }
                    }
                }
            }
            /*********************** 调房协议抄表 end****************************/
            
            /*********************** 调房协议物品 start****************************/
            if(null != transferGoodsList){
                for (CommonGoodsDO commonGoodsDO : transferGoodsList) {
                    commonGoodsDO.setIp(ip);
                    commonGoodsDO.setCreateName(pin);
                    commonGoodsDO.setPactCode(transferCode);
                    commonGoodsDO.setPactType(PactTypeConsts.RENT_ROOM);
                    
                    if(null != commonGoodsDO.getCommonGoodsPicList()&&commonGoodsDO.getCommonGoodsPicList().size()>0){
                        for (CommonGoodsPicDO commonGoodsPicDO : commonGoodsDO.getCommonGoodsPicList()) {
                            commonGoodsPicDO.setIp(ip);
                            commonGoodsPicDO.setCreateName(pin);
                        }
                    }
                }
            }
            /*********************** 调房协议物品 end****************************/
            
            /*********************** 调房协议图片 start****************************/
            for(CommonPicDO commonPicDO :transferPicList){
                commonPicDO.setIp(ip);
                commonPicDO.setCreateName(pin);
                commonPicDO.setPactCode(transferCode);
                commonPicDO.setPactType(PactTypeConsts.RENT_ROOM);
            }
           
            /*********************** 调房协议图片 end****************************/
            /*********************** 新合同 start****************************/
            StringBuilder pactRedisKey = new StringBuilder("");
            pactRedisKey.append(param.getPirmaryPactCode()).append(StringConsts.UNDERLINE)
            .append(StringConsts.REDIS_TRANSFER_PACT).append(StringConsts.UNDERLINE).append(param.getCreateName());
            RentPactVO rentPactVO = rentBaseManager.getPactRedis(pactRedisKey.toString());
            if(null == rentPactVO){
                apiResult.setCode(PactCommonEnum.RENT_BASE_REDIS_INVALID.getCode());
                apiResult.setMessage(PactCommonEnum.RENT_BASE_REDIS_INVALID.getMessage());
                return apiResult;
            }
            BeanUtils.copyProperties(rentPactVO.getPact(), rentBaseDO);
            rentBaseDO.setState(1);
            rentBaseDO.setDueState(1);
            rentBaseDO.setDealState(3);
            rentBaseDO.setCancelState(1);
            rentBaseDO.setIsDelete(0);
            rentBaseDO.setIsEffective(1);
            rentBaseDO.setIp(ip);
            rentBaseDO.setCreateName(pin);
            rentBaseDO.setRentPactCode(rentPactCode);
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
            /*********************** 新合同 end****************************/
            
            
            /*********************** 新合同签约管家 start****************************/
            CommonBelongerDO pactDealBelongDO = new CommonBelongerDO();
            ApiResult<PersonalResult> personalPactResult = personalRpc.getPersonalResultBypin(param.getUserPin());
            if(null!=personalPactResult&&StringConsts.REQUEST_SUCCESS_CODE.equals(personalPactResult.getCode())){
                PersonalResult personalPactResultData = personalPactResult.getData();
                pactDealBelongDO.setDeptCode(personalPactResultData.getDeptCode());
                pactDealBelongDO.setDeptName(personalPactResultData.getDeptName());
                pactDealBelongDO.setUserName(personalPactResultData.getEmployeeName());
                pactDealBelongDO.setUserTel(personalPactResultData.getEmployeeTel());
                pactDealBelongDO.setUserPin(personalPactResultData.getPin());
                pactDealBelongDO.setPactType(PactTypeConsts.RENT);
                pactDealBelongDO.setUserRole(StringConsts.RENT_USER_ROLE);
                pactDealBelongDO.setPactCode(rentPactCode);
                pactDealBelongDO.setIp(ip);
                pactDealBelongDO.setCreateName(pin);
            }
            /*********************** 新合同签约管家 end****************************/
            
            /*********************** 新合同收支规则  start****************************/
            List<CommonCostRuleDO> pactCostRuleDOs = new ArrayList<>();
            if(null!=rentPactVO.getPactReceiptPayList()){
                BeanUtils.copyListProperties(rentPactVO.getPactReceiptPayList(), pactCostRuleDOs, CommonCostRuleDO.class);
            }
            if(null != pactCostRuleDOs){
                for(CommonCostRuleDO commonCostRuleDO:pactCostRuleDOs){
                    commonCostRuleDO.setPactCode(rentPactCode);
                    commonCostRuleDO.setPactType(PactTypeConsts.RENT);
                    commonCostRuleDO.setIp(ip);
                    commonCostRuleDO.setCreateName(pin);
                }
            }
            /*********************** 新合同收支规则  end****************************/
            
            /*********************** 新合同收支 start****************************/
            List<FinanceReceiptPayDO> pactRecPayDOs = new ArrayList<>();
            BeanUtils.copyListProperties(rentPactVO.getReceiptPayList(), pactRecPayDOs, FinanceReceiptPayDO.class);
            if(null!= pactRecPayDOs){
                for (FinanceReceiptPayDO financeReceiptPayDO : pactRecPayDOs) {
                    financeReceiptPayDO.setPactCode(rentPactCode);
                    financeReceiptPayDO.setPactType(PactTypeConsts.RENT);
                    financeReceiptPayDO.setCreateName(pin);
                    financeReceiptPayDO.setIp(ip);
                    if(null != financeReceiptPayDO.getType() && 0 == financeReceiptPayDO.getType()){// 应收
                        financeReceiptPayDO.setCostState(1);// 待收款
                    }else if(null != financeReceiptPayDO.getType() && 2 == financeReceiptPayDO.getType()){// 应支
                        financeReceiptPayDO.setCostState(4);// 待付款
                    }
                    financeReceiptPayDO.setHouseCode(rentRoomDO.getHouseCode());
                    financeReceiptPayDO.setRoomCode(rentRoomDO.getRoomCode());
                    financeReceiptPayDO.setAddress(rentRoomDO.getAddress());
                }
            }
            /*********************** 新合同收支  end****************************/
            /*********************** 新合同抄表  start****************************/
            List<CommonMeterReadDO> pactMeterReadDOs = new ArrayList<>();
            List<CommonMeterReadDO> pactMeterReadList = pactMeterReadGoodsVO.getMeterReadList();
            if(null != pactMeterReadList){
                for (CommonMeterReadDO commonMeterReadDO : pactMeterReadList) {
                    commonMeterReadDO.setIp(ip);
                    commonMeterReadDO.setCreateName(pin);
                    commonMeterReadDO.setPactCode(rentPactCode);
                    commonMeterReadDO.setPactType(PactTypeConsts.RENT);
                    if(null != commonMeterReadDO.getCommonMeterReadPicList()&&commonMeterReadDO.getCommonMeterReadPicList().size()>0){
                        List<CommonMeterReadPicDO> readPicList = new ArrayList<>();
                        BeanUtils.copyListProperties(commonMeterReadDO.getCommonMeterReadPicList(), readPicList, CommonMeterReadPicDO.class);
                        commonMeterReadDO.setCommonMeterReadPicList(readPicList);
                        for (CommonMeterReadPicDO commonMeterReadPicDO : commonMeterReadDO.getCommonMeterReadPicList()) {
                            commonMeterReadPicDO.setIp(ip);
                            commonMeterReadPicDO.setCreateName(pin);
                        }
                    }
                    pactMeterReadDOs.add(commonMeterReadDO);
                }
            }
            /*********************** 新合同抄表 end****************************/
            
            /*********************** 新合同物品 start****************************/
            List<CommonGoodsDO> pactGoodsDOs = new ArrayList<>();
            List<CommonGoodsDO> pactGoodsList = pactMeterReadGoodsVO.getGoodsList();
            if(null != pactGoodsList){
                for (CommonGoodsDO commonGoodsDO : pactGoodsList) {
                    commonGoodsDO.setIp(ip);
                    commonGoodsDO.setCreateName(pin);
                    commonGoodsDO.setPactCode(rentPactCode);
                    commonGoodsDO.setPactType(PactTypeConsts.RENT);
                    
                    if(null != commonGoodsDO.getCommonGoodsPicList()&&commonGoodsDO.getCommonGoodsPicList().size()>0){
                        List<CommonGoodsPicDO> picList = new ArrayList<>();
                        BeanUtils.copyListProperties(commonGoodsDO.getCommonGoodsPicList(), picList, CommonGoodsPicDO.class);
                        commonGoodsDO.setCommonGoodsPicList(picList);
                        for (CommonGoodsPicDO commonGoodsPicDO : commonGoodsDO.getCommonGoodsPicList()) {
                            commonGoodsPicDO.setIp(ip);
                            commonGoodsPicDO.setCreateName(pin);
                        }
                    }
                    pactGoodsDOs.add(commonGoodsDO);
                }
            }
            /*********************** 新合同物品 end****************************/
            
            /*********************** 新合同图片 start****************************/
            List<CommonPicDO> pactPicDOs = new ArrayList<>();
            for(CommonPicDO commonPicDO :transferPicList){
                CommonPicDO pactPic = new CommonPicDO();
                BeanUtils.copyProperties(commonPicDO, pactPic);
                pactPic.setPactCode(rentPactCode);
                pactPic.setPactType(PactTypeConsts.RENT);
                pactPicDOs.add(pactPic);
            }
            /*********************** 新合同图片 end****************************/
            
     
            /*************托出租客 +托出租客共同居住人     start******************/
            //托出租客
            CustomerOwnerDO customerOwnerDO = new CustomerOwnerDO();
            RentCustomerVO customer = rentCustomerManager.getRentCustomerByPactCode(oldPactCode);
            RentCustomerDO rentCustomerDO = new RentCustomerDO();
            BeanUtils.copyProperties(customer, rentCustomerDO);
            rentCustomerDO.setRentPactCode(rentPactCode);
            rentCustomerDO.setCreateName(pin);
            rentCustomerDO.setRenterCode(renterCode);
            rentCustomerDO.setIp(ip);
            customerOwnerDO.setRenter(rentCustomerDO);
            //托出租客共同居住人 
            List<RentCommonOwnerVO> cohabitantList = commonOwnerManager.listCommonOwnerByRenterCode(customer.getRenterCode());
            List<RentCommonOwnerDO> rentCommonOwnerList = new ArrayList<>();
            BeanUtils.copyListProperties(cohabitantList, rentCommonOwnerList, RentCommonOwnerDO.class);
            customerOwnerDO.setCohabitantList(rentCommonOwnerList);
            if(null!=rentCommonOwnerList){
                for(RentCommonOwnerDO rentCommonOwnerDO:rentCommonOwnerList){
                    rentCommonOwnerDO.setRenterCode(renterCode);
                    rentCommonOwnerDO.setCreateName(pin);
                    rentCommonOwnerDO.setIp(ip);
                }
            }
            /*************托出租客 +托出租客共同居住人      end******************/
            allPicList.addAll(transferPicList);
            allPicList.addAll(pactPicDOs);
            allBelongerDOList.add(transferDealBelongDO);
            allBelongerDOList.add(transferGuardianBelongDO);
            allBelongerDOList.add(pactDealBelongDO);
            allRecPayDOs.addAll(transferRecPayList);
            allRecPayDOs.addAll(pactRecPayDOs);
            boolean flag = pactRentTransferManager.saveRentBaseAndTransfer(pactRentTransferDO, 
                    transferMeterReadList, transferGoodsList, rentRoomDO, rentCustomerDO, rentCommonOwnerList, 
                    rentBaseDO, pactCostRuleDOs, allRecPayDOs, allPicList , pactMeterReadDOs, 
                    pactGoodsDOs, allBelongerDOList);
            apiResult.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            apiResult.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
            apiResult.setData(flag);
        } catch (PactManagerExcepion e) {
            LOG.error(CommonEnum.SYSTEM_EXCEPTION.getMessage(), e);
            apiResult.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            apiResult.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
            apiResult.setData(false);
        } catch (Exception e) {
            LOG.error(CommonEnum.SYSTEM_EXCEPTION.getMessage(), e);
            apiResult.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            apiResult.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
            apiResult.setData(false);
        }
        return apiResult;
    }

    /**
     * 
    * @Title: initNewPact
    * @Description: 保存调房协议和合同信息
    * @author LiuYuChi
    * @param rentPactVO 合同信息
    * @param pactRentTransferDO 协议信息
    * @param pin 登录人 
    * @param ip 登录人ip
    * @param rentPactCode 
    * @return  新合同
    * @throws 异常
     */
    private RentBaseDO initNewPact(RentBaseVO rentBaseVO, PactRentTransferDO pactRentTransferDO, String pin, String ip,String rentPactCode) {
        RentBaseDO rentBaseDO = new RentBaseDO(); 
        BeanUtils.copyProperties(rentBaseVO, rentBaseDO);
        rentBaseDO.setIsEffective(1);
        rentBaseDO.setIsDelete(0);
        rentBaseDO.setStartTime(DateTools.getLastDay(pactRentTransferDO.getTransferTime(), 1));//新合同开始时间是调房时间转天
        rentBaseDO.setCancelState(1);
        rentBaseDO.setDealState(3);
        rentBaseDO.setState(1);
        rentBaseDO.setDueState(1);
        rentBaseDO.setRentPactCode(rentPactCode);
        rentBaseDO.setCreateName(pin);
        rentBaseDO.setIp(ip);
        rentBaseDO.setPay(rentBaseVO.getPay());
        rentBaseDO.setCustody(rentBaseVO.getCustody());
        rentBaseDO.setAccount(rentBaseVO.getAccount());
        rentBaseDO.setAccountName(rentBaseVO.getAccountName());
        rentBaseDO.setBank(rentBaseVO.getBank());
        rentBaseDO.setOpenBank(rentBaseVO.getOpenBank());
        rentBaseDO.setTel(rentBaseVO.getTel());
        return rentBaseDO;
    }

    @Override
	public ApiResult<String> insertPact(RentPactParam parm) {
        ApiResult<String> result = new ApiResult<>();
        if(null == parm){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getCode());
            return result;
        }
        StringBuilder key = new StringBuilder();
        String pin = ""; 
        try {
            pin = parm.getCreateName() == null?"":parm.getCreateName();
            key.append(parm.getServiceCode()).append(StringConsts.UNDERLINE)
            .append(StringConsts.REDIS_TRANSFER_PACT).append(StringConsts.UNDERLINE).append(pin);
            List<FinanceReceiptPayParam> financeReceiptPayParams = parm.getReceiptPayList();
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
            BeanUtils.copyProperties(parm, rentPactDO);
            RentBaseDO pact = new RentBaseDO();
            BeanUtils.copyProperties(parm.getPact(), pact);
            rentPactDO.setPact(pact);
            
            List<CommonCostRuleDO> commonCostRuleDOs =new ArrayList<>();
            List<FinanceReceiptPayDO> financeReceiptPayDOs = new ArrayList<>();
            List<CommonPicDO> commonPicDOs = new ArrayList<>();
            BeanUtils.copyListProperties(parm.getPactReceiptPayList(), commonCostRuleDOs, CommonCostRuleDO.class);
            BeanUtils.copyListProperties(parm.getReceiptPayList(), financeReceiptPayDOs, FinanceReceiptPayDO.class);
            if(null!=parm.getUrlList()){
                BeanUtils.copyListProperties(parm.getUrlList(), commonPicDOs, CommonPicDO.class);
            }
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
	

	@Override
	public ApiResult<RentPactResult> getPact(String key,String pin) {
        ApiResult<RentPactResult> result = new ApiResult<>();
        try {
            RentPactVO rentPactVO = rentBaseManager.getPactRedis(key);
            RentPactResult rentPactResult = new RentPactResult();
            RentBaseResult pact = new RentBaseResult();
            if(null!=rentPactVO){
            	//有缓存
                BeanUtils.copyProperties(rentPactVO, rentPactResult);
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
            }else{
            	//没有缓存,查询原合同截止日期的转天
            	//获取原合同编码
            	String pirmaryPactCode = StrTools.subString(key, StringConsts.UNDERLINE);
                // 原合同截止时间
                //调房协议信息
     			StringBuilder sb = new StringBuilder();
                //key=原合同编码+业务类型编码+pin
                sb.append(pirmaryPactCode).append(StringConsts.UNDERLINE)
                .append(StringConsts.REDIS_TRANSFER).append(StringConsts.UNDERLINE).append(pin);
                RentBaseVO rentBaseVO = rentBaseManager.getRentBaseByCode(pirmaryPactCode);
                pact.setStartTime(DateTools.getLastDay(rentBaseVO.getEndTime(),1));
                rentPactResult.setPact(pact);
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
	public ApiResult<PactRentTransferResult> getTransferInfo(PactRentTransferParam param) {
		ApiResult<PactRentTransferResult> result = new ApiResult<PactRentTransferResult>();
		try {
		    String transferCode = param.getTransferCode();
		    boolean permissionsFlag = getPermissions(param);
		    if(!permissionsFlag){
                result.setCode(PactCommonEnum.TRANSFERROOM_NO_PERMISSIONS.getCode());
                result.setMessage(PactCommonEnum.TRANSFERROOM_NO_PERMISSIONS.getMessage());
                return result;
            }
			PactRentTransferResult pactRentTransfer = new PactRentTransferResult();
			//协议信息+协议图片信息
			PactTransferAllVO pactTransferAllVO = pactRentTransferManager.getTransfer(transferCode);
			BeanUtils.copyProperties(pactTransferAllVO.getPactRentTransferVO(), pactRentTransfer);
			List<CommonPicResult> pList = new ArrayList<>();
			BeanUtils.copyListProperties(pactTransferAllVO.getCommonPicList(), pList, CommonPicResult.class);
			pactRentTransfer.setPicList(pList);
			//老合同信息
			String oldPactCode = pactTransferAllVO.getPactRentTransferVO().getPirmaryPactCode();
			RentPactResult oldPact = new RentPactResult();
			RentBaseResult oldPactBase = new RentBaseResult();
			RentBaseVO rentBaseOld = rentBaseManager.getRentBaseByCode(oldPactCode);
			//老合同客源信息
			RentCustomerVO rentCustomerOld = rentCustomerManager.getRentCustomerByPactCode(oldPactCode);
			BeanUtils.copyProperties(rentBaseOld, oldPactBase);
			oldPactBase.setCustomerName(rentCustomerOld.getName());
			oldPactBase.setCustomerTel(rentCustomerOld.getTel());
			oldPact.setPact(oldPactBase);
			pactRentTransfer.setOldPact(oldPact);
			//老合同房间
			RentRoomVO oldPactRoomVO = rentRoomManager.getRommByRentCode(oldPactCode);
			//新合同信息
			RentPactResult newPact = new RentPactResult();
			RentBaseResult newPactBase = new RentBaseResult();
			String newPactCode = pactTransferAllVO.getPactRentTransferVO().getNewPactCode();
			RentBaseVO rentBaseNew = rentBaseManager.getRentBaseByCode(newPactCode);
			//新合同客源信息
			RentCustomerVO rentCustomerNew = rentCustomerManager.getRentCustomerByPactCode(newPactCode);
			BeanUtils.copyProperties(rentBaseNew, newPactBase);
			newPactBase.setCustomerName(rentCustomerNew.getName());
			newPactBase.setCustomerTel(rentCustomerNew.getTel());
			newPact.setPact(newPactBase);
			pactRentTransfer.setNewPact(newPact);
			//新合同房间
            RentRoomVO newPactRoomVO = rentRoomManager.getRommByRentCode(newPactCode);
			//责任人
			List<CommonBelongerVO> commonBelongList = commonBelongerManager.listCommonBelonger(oldPactCode);
			if (commonBelongList!=null) {
				for (CommonBelongerVO commonBelongerVO : commonBelongList) {
					if (StringConsts.RENT_USER_ROLE_RES.equals(commonBelongerVO.getUserRole())) {
						pactRentTransfer.setUserName(commonBelongerVO.getUserName());
					}
				}
			}
			
			FinanceReceiptPayQuery financeReceiptPayQuery = new FinanceReceiptPayQuery();
			financeReceiptPayQuery.setPactCode(transferCode);
            List<FinanceReceiptPayVO> recPayList = financeReceiptPayManager.listFinanceReceiptPayByPactCode(financeReceiptPayQuery);
            List<FinanceReceiptPayResult> receiptPayList = new ArrayList<>();
            BeanUtils.copyListProperties(recPayList, receiptPayList, FinanceReceiptPayResult.class);
            pactRentTransfer.setReceiptPayList(receiptPayList);
            pactRentTransfer.setAddressOld(oldPactRoomVO.getAddress());
            pactRentTransfer.setAddressNew(newPactRoomVO.getAddress());
            result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
            result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
            result.setData(pactRentTransfer);
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
	public ApiResult<Boolean> delTransfer(PactRentTransferParam param) {
		ApiResult<Boolean> apiResult = new ApiResult<Boolean>();
		try {
			String transferCode = param.getTransferCode();
			String ip = param.getIp();
			String pin = param.getModifiedName();
			//协议信息+协议图片信息
			PactTransferAllVO pactTransferAllVO = pactRentTransferManager.getTransfer(transferCode);
			int state = pactTransferAllVO.getPactRentTransferVO().getState();
			if (state==2||state==3) {
				//待审核和审核通过不可以删除和修改
				apiResult.setCode(PactCommonEnum.PACT_RENT_TRANSFER_STATE_ERROR.getCode());
            	apiResult.setMessage(PactCommonEnum.PACT_RENT_TRANSFER_STATE_ERROR.getMessage());
			}else if (pactTransferAllVO.getPactRentTransferVO().getIsDelete()==1) {
				//已经是删除状态
				apiResult.setCode(PactCommonEnum.PACT_RENT_TRANSFER_ALREADY_DEL.getCode());
            	apiResult.setMessage(PactCommonEnum.PACT_RENT_TRANSFER_ALREADY_DEL.getMessage());
			}else {
				PactRentTransferDO pactRentTransferDO = new PactRentTransferDO();
				pactRentTransferDO.setTransferCode(transferCode);
				pactRentTransferDO.setIsDelete(1);
				pactRentTransferDO.setModifiedName(pin);
				pactRentTransferDO.setIp(ip);
				pactRentTransferDO.setNewPactCode(pactTransferAllVO.getPactRentTransferVO().getNewPactCode());
				boolean flag = pactRentTransferManager.updateTransferInfo(pactRentTransferDO);
				if (flag) {
				    Map<String, Object> map = new HashMap<String, Object>();
                    map.put(NormalConstant.SERVICETYPE, SerializeTypeConts.TRANSFER);
                    map.put(NormalConstant.SERVICECODE, pactRentTransferDO.getTransferCode());
                    map.put(NormalConstant.OPERATETYPE, StringConsts.DELETE);
                    map.put(NormalConstant.OPERATEPIN, param.getCreateName());
                    map.put(NormalConstant.OPERATECONTENT, StringConsts.DELETE_DATA);
                    operateLogRpc.saveOperateLog(map);
					apiResult.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
					apiResult.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
					apiResult.setData(true);
				}else{
					apiResult.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
					apiResult.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
				}
			}
		} catch (PactManagerExcepion e) {
            LOG.error(CommonEnum.SYSTEM_EXCEPTION.getMessage(), e);
            apiResult.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            apiResult.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        } catch (Exception e) {
            LOG.error(CommonEnum.SYSTEM_EXCEPTION.getMessage(), e);
            apiResult.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            apiResult.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        }
		return apiResult;
	}

	@Override
	public ApiResult<Boolean> updateTransfer(PactRentTransferParam param) {
		ApiResult<Boolean> apiResult = new ApiResult<Boolean>();
		try {
			//协议信息
			PactTransferAllVO pactTransferAllVO = pactRentTransferManager.getTransfer(param.getTransferCode());
			int state = pactTransferAllVO.getPactRentTransferVO().getState();
			if (state==2||state==3) {
				//待审核和审核通过不可以删除和修改
				apiResult.setCode(PactCommonEnum.PACT_RENT_TRANSFER_STATE_ERROR.getCode());
            	apiResult.setMessage(PactCommonEnum.PACT_RENT_TRANSFER_STATE_ERROR.getMessage());
			}else {
				String transferCode = param.getTransferCode();
				String pin = param.getModifiedName();
				String ip = param.getIp();
				String pirPactCode= pactTransferAllVO.getPactRentTransferVO().getPirmaryPactCode();
				String newPactCode = pactTransferAllVO.getPactRentTransferVO().getNewPactCode();
				Date transferTimeOld = pactTransferAllVO.getPactRentTransferVO().getTransferTime();
				String natives = pactTransferAllVO.getPactRentTransferVO().getNatives();
				//协议基本信息
				PactRentTransferDO pactRentTransferDO = new PactRentTransferDO();
				BeanUtils.copyProperties(param, pactRentTransferDO);
				pactRentTransferDO.setPirmaryPactCode(pirPactCode);
				pactRentTransferDO.setNewPactCode(newPactCode);
				//收支(集合)**/
                List<FinanceReceiptPayDO> financeReceiptPayDOs = new ArrayList<>();
                BeanUtils.copyListProperties(param.getReceiptPayList(), financeReceiptPayDOs, FinanceReceiptPayDO.class);
                for (FinanceReceiptPayDO financeReceiptPayDO : financeReceiptPayDOs) {
                    financeReceiptPayDO.setPactCode(transferCode);
                    financeReceiptPayDO.setPactType(PactTypeConsts.RENT_ROOM);
                    financeReceiptPayDO.setCreateName(pin);
                    financeReceiptPayDO.setModifiedName(pin);
                    financeReceiptPayDO.setIp(ip);
                    if(null != financeReceiptPayDO.getType() && 0 == financeReceiptPayDO.getType()){// 应收
                        financeReceiptPayDO.setCostState(1);// 待收款
                    }else if(null != financeReceiptPayDO.getType() && 2 == financeReceiptPayDO.getType()){// 应支
                        financeReceiptPayDO.setCostState(4);// 待付款
                    }
                }
                
                FinanceReceiptPayDO payeeFormRecPay = getPayeeFormRecPay(financeReceiptPayDOs);
                pactRentTransferDO.setName(payeeFormRecPay.getName());
                pactRentTransferDO.setAccountNum(payeeFormRecPay.getAccount());
                pactRentTransferDO.setCaseBank(payeeFormRecPay.getBank());
                pactRentTransferDO.setOpenBank(payeeFormRecPay.getOpenBank());
                pactRentTransferDO.setTel(payeeFormRecPay.getTel());
                //合同照片(集合)**/
                List<CommonPicDO> commonPicDOs = new ArrayList<>();
                
                BeanUtils.copyListProperties(param.getPicList(), commonPicDOs, CommonPicDO.class);
                for (CommonPicDO commonPicDO : commonPicDOs) {
                    commonPicDO.setIp(ip);
                    commonPicDO.setCreateName(pin);
                    commonPicDO.setModifiedName(pin);
                    commonPicDO.setPactCode(transferCode);
                    commonPicDO.setPactType(PactTypeConsts.RENT_ROOM);
                }
                CommonBelongerDO commonBelongerDO = new CommonBelongerDO();
                CommonBelongerDO commonBelongDO = new CommonBelongerDO();
                List<CommonBelongerVO> commonBelongerList = commonBelongerManager.listCommonBelonger(transferCode);
                if(null != commonBelongerList){
                    for (CommonBelongerVO commonBelongerVO : commonBelongerList) {
                        if(StringConsts.BELONGER_DEAL.equals(commonBelongerVO.getUserRole())){
                            commonBelongerDO.setId(commonBelongerVO.getId());
                        }else if(StringConsts.BELONGER_PERSON.equals(commonBelongerVO.getUserRole())){
                        	commonBelongDO.setId(commonBelongerVO.getId());
                        }
                    }
                }
                List<CommonBelongerDO> commonBelongList = new ArrayList<>();
                //签约管家
                // rpc接口根据pin查询管家信息
                ApiResult<PersonalResult> personalResult = personalRpc.getPersonalResultBypin(param.getUserPin());
                PersonalResult personalResultData = personalResult.getData();
                commonBelongerDO.setPactCode(transferCode);
                commonBelongerDO.setUserPin(param.getUserPin());
                commonBelongerDO.setUserName(personalResultData.getEmployeeName());
                commonBelongerDO.setUserTel(personalResultData.getEmployeeTel());
                commonBelongerDO.setUserRole(StringConsts.BELONGER_DEAL);
                commonBelongerDO.setDeptCode(personalResultData.getDeptCode());
                commonBelongerDO.setDeptName(personalResultData.getDeptName());
                commonBelongerDO.setIp(ip);
                commonBelongerDO.setCreateName(pin);
                commonBelongerDO.setModifiedName(pin);
                //责任人
                
                commonBelongDO.setPactCode(transferCode);
                commonBelongDO.setUserPin(param.getUserPin());
                commonBelongDO.setUserName(personalResultData.getEmployeeName());
                commonBelongDO.setUserTel(personalResultData.getEmployeeTel());
                commonBelongDO.setUserRole(StringConsts.BELONGER_PERSON);
                commonBelongDO.setDeptCode(personalResultData.getDeptCode());
                commonBelongDO.setDeptName(personalResultData.getDeptName());
                commonBelongDO.setIp(ip);
                commonBelongDO.setCreateName(pin);
                commonBelongDO.setModifiedName(pin);
                commonBelongList.add(commonBelongDO);
                commonBelongList.add(commonBelongerDO);
                
                //原条件调房, 如果修改了原合同截止时间,需要修改新合同开始时间,以及收支(需要重新生成收支,按照新的原合同截止时间截取往后的所有收支)
                List<FinanceReceiptPayDO> receiptPayList = new ArrayList<>();
                if (StringConsts.ORIGINAL_CONDITION.equals(natives) ) {
                	if (transferTimeOld.getTime() != pactRentTransferDO.getTransferTime().getTime()) {
                    	// 收支    	           
                        List<FinanceReceiptPayVO> listFinanceReceiptPayVO = new ArrayList<>();
        	            FinanceReceiptPayQuery query = new FinanceReceiptPayQuery();
        	            query.setPactCode(pirPactCode);
        	            Date times =DateTools.getLastDay(pactRentTransferDO.getTransferTime(), 1);
        	            Date time=DateTools.weeHours(times, 0);
        	            query.setCostTimeStr(DateTools.parseDay(time));
        	            listFinanceReceiptPayVO = financeReceiptPayManager.listFinanceReceiptPayDO(query);
        	            RentRoomVO rentRoom = rentRoomManager.getRommByRentCode(newPactCode);
        	            
        	            BeanUtils.copyListProperties(listFinanceReceiptPayVO, receiptPayList, FinanceReceiptPayDO.class);
        	            if(null!= receiptPayList){
        	                for (FinanceReceiptPayDO financeReceiptPayDO : receiptPayList) {
        	                    financeReceiptPayDO.setPactCode(pirPactCode);
        	                    financeReceiptPayDO.setPactType(PactTypeConsts.RENT);
        	                    financeReceiptPayDO.setCreateName(pin);
        	                    financeReceiptPayDO.setIp(ip);
        	                    if(null != financeReceiptPayDO.getType() && 0 == financeReceiptPayDO.getType()){// 应收
        	                        financeReceiptPayDO.setCostState(1);// 待收款
        	                    }else if(null != financeReceiptPayDO.getType() && 2 == financeReceiptPayDO.getType()){// 应支
        	                        financeReceiptPayDO.setCostState(4);// 待付款
        	                    }
        	                    financeReceiptPayDO.setHouseCode(rentRoom.getHouseCode());
        	                    financeReceiptPayDO.setRoomCode(rentRoom.getRoomCode());
        	                    financeReceiptPayDO.setAddress(rentRoom.getAddress());
        	                }
        	            }
    				}
				}
                boolean flag = pactRentTransferManager.updateTransfer(pactRentTransferDO, financeReceiptPayDOs, commonPicDOs, commonBelongList,receiptPayList);
				if (flag) {
				    Map<String, Object> map = new HashMap<String, Object>();
                    map.put(NormalConstant.SERVICETYPE, SerializeTypeConts.TRANSFER);
                    map.put(NormalConstant.SERVICECODE, pactRentTransferDO.getTransferCode());
                    map.put(NormalConstant.OPERATETYPE, StringConsts.UPDATE);
                    map.put(NormalConstant.OPERATEPIN, param.getModifiedName());
                    map.put(NormalConstant.OPERATECONTENT, StringConsts.UPDATE_DATA);
                    operateLogRpc.saveOperateLog(map);
					apiResult.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
					apiResult.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
					apiResult.setData(true);
				}else{
					apiResult.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
					apiResult.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
				}
			}
		} catch (PactManagerExcepion e) {
            LOG.error(CommonEnum.SYSTEM_EXCEPTION.getMessage(), e);
            apiResult.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            apiResult.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        } catch (Exception e) {
            LOG.error(CommonEnum.SYSTEM_EXCEPTION.getMessage(), e);
            apiResult.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            apiResult.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        }
		return apiResult;
	}
	

	@Override
	public ApiResult<PageBean<PactRentTransferResult>> listTransfer(
			PactRentTransferParam param) {
		ApiResult<PageBean<PactRentTransferResult>> apiResult = new ApiResult<PageBean<PactRentTransferResult>>();
		
		try {
			if (param!=null) {
				if(StringUtil.isNotBlank(param.getTransferTimeStart())
	                    && StringUtil.isNotBlank(param.getTransferTimeEnd())){
	                param.setTransferTimeStart(param.getTransferTimeStart() + " 00:00:00");
	                param.setTransferTimeEnd(param.getTransferTimeEnd() + " 23:59:59");
	            }
				if(StringUtil.isNotBlank(param.getSigningTimeStart())
	                    && StringUtil.isNotBlank(param.getSigningTimeEnd())){
	                param.setSigningTimeStart(param.getSigningTimeStart() + " 00:00:00");
	                param.setSigningTimeEnd(param.getSigningTimeEnd() + " 23:59:59");
	            }
				PactRentTransferQuery query = new PactRentTransferQuery();
				BeanUtils.copyProperties(param, query);
			    ApiResult<PersonalResult> personalApiResult = personalRpc.getPersonalResultBypin(param.getCreateName());
	            PersonalResult personalResult = personalApiResult.getData();
	            Integer scope = param.getScope();
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
				PageBean<PactRentTransferVO> pageBean = pactRentTransferManager.listParam(query);
	            PageBean<PactRentTransferResult> page = new PageTableBean<>(query.getPageIndex(), query.getPageSize());
	            page.setTotalItem(pageBean.getTotalItem());
	            BeanUtils.copyListProperties(pageBean.getData(), page.getData(), PactRentTransferResult.class);
	            apiResult.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
	            apiResult.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
	            apiResult.setData(page);
			}else{
				apiResult.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
				apiResult.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
                return apiResult;
			}
		} catch (PactManagerExcepion e) {
            LOG.error(CommonEnum.SYSTEM_EXCEPTION.getMessage(), e);
            apiResult.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            apiResult.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        } catch (Exception e) {
            LOG.error(CommonEnum.SYSTEM_EXCEPTION.getMessage(), e);
            apiResult.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            apiResult.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        }
		return apiResult;
	}


	@Override
	public ApiResult<Boolean> aplyCheck(PactRentTransferParam param) {
		ApiResult<Boolean> apiResult = new ApiResult<Boolean>();
		try {
			PactTransferAllVO transferAllVO = pactRentTransferManager.getTransfer(param.getTransferCode());
			if (transferAllVO !=null && transferAllVO.getPactRentTransferVO()!=null) {
				int state = transferAllVO.getPactRentTransferVO().getState();
				if (state== 2 ||state==3) {
					//待审核和已通过状态不能申请审核
					apiResult.setCode(PactCommonEnum.PACT_RENT_TRANSFER_APPLY_CHECK_STATE_ERROR.getCode());
					apiResult.setMessage(PactCommonEnum.PACT_RENT_TRANSFER_APPLY_CHECK_STATE_ERROR.getMessage());
					return apiResult;
				}
				PactRentTransferDO transfer = new PactRentTransferDO();
				BeanUtils.copyProperties(param, transfer);
				Boolean flag = pactRentTransferManager.aplyCheck(transfer);
				if (flag) {
				    Map<String, Object> map = new HashMap<String, Object>();
                    map.put(NormalConstant.SERVICETYPE, SerializeTypeConts.TRANSFER);
                    map.put(NormalConstant.SERVICECODE, transfer.getTransferCode());
                    map.put(NormalConstant.OPERATETYPE, StringConsts.OPERATETYPE_APPLICATION_AUDIT);
                    map.put(NormalConstant.OPERATEPIN, param.getModifiedName());
                    map.put(NormalConstant.OPERATECONTENT, StringConsts.AUDIT_DATA);
                    operateLogRpc.saveOperateLog(map);
                    /*************************首页待办数据统计  start*****************************/
                    CommonBelongerQuery commonBelongerQuery = new CommonBelongerQuery();
                    commonBelongerQuery.setPactCode(param.getTransferCode());
                    commonBelongerQuery.setUserRole(StringConsts.BELONGER_DEAL);
                    CommonBelongerVO commonBelongerVO = commonBelongerManager.getBelongersByParam(commonBelongerQuery);
                    AgendaDO agendaDO = new AgendaDO();
                    agendaDO.setServiceCode(param.getTransferCode());
                    agendaDO.setType(StringConsts.STATISTIC_PENDING_RENT_TRANSFER);
                    agendaDO.setIsDo(0);
                    agendaDO.setPin(commonBelongerVO.getUserPin());
                    agendaDO.setName(commonBelongerVO.getUserName());
                    agendaDO.setDeptCode(commonBelongerVO.getDeptCode());
                    agendaDO.setDeptName(commonBelongerVO.getDeptName());
                    agendaDO.setDate(new Date());
                    statisticsManager.saveBacklogStatistics(agendaDO);
                    /*************************首页待办数据统计  end*****************************/
					apiResult.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
		            apiResult.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
		            apiResult.setData(flag);
				}
			}else{
				apiResult.setCode(PactCommonEnum.PACT_RENT_TRANSFER_NO_INFO.getCode());
				apiResult.setMessage(PactCommonEnum.PACT_RENT_TRANSFER_NO_INFO.getMessage());
			}
		} catch (PactManagerExcepion e) {
            LOG.error(CommonEnum.SYSTEM_EXCEPTION.getMessage(), e);
            apiResult.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            apiResult.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        } catch (Exception e) {
            LOG.error(CommonEnum.SYSTEM_EXCEPTION.getMessage(), e);
            apiResult.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            apiResult.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        }
		return apiResult;
	}
	
    @Override
	public ApiResult<Boolean> reject(PactRentTransferParam param) {
		ApiResult<Boolean> apiResult =new ApiResult<Boolean>();
		try {
			PactTransferAllVO transferAllVO = pactRentTransferManager.getTransfer(param.getTransferCode());
			if (transferAllVO !=null && transferAllVO.getPactRentTransferVO()!=null) {
				int state = transferAllVO.getPactRentTransferVO().getState();
				if (state==2) {
					//只有待审核可以驳回
					PactRentTransferDO transfer = new PactRentTransferDO();
					BeanUtils.copyProperties(param, transfer);
					transfer.setState(4);
					Boolean flag = pactRentTransferManager.rejectTransferInfo(transfer);
					if (flag) {
					    List<String> variableList = new ArrayList<>();
	                    variableList.add(param.getTransferCode());
	                    saveRemind(param,StringConsts.REVIEW_DISMISSAL_TRANSFER,variableList);
					    Map<String, Object> map = new HashMap<String, Object>();
	                    map.put(NormalConstant.SERVICETYPE, SerializeTypeConts.TRANSFER);
	                    map.put(NormalConstant.SERVICECODE, transfer.getTransferCode());
	                    map.put(NormalConstant.OPERATETYPE, StringConsts.OPERATETYPE_REVIEW_DISMISSAL);
	                    map.put(NormalConstant.OPERATEPIN, param.getModifiedName());
	                    map.put(NormalConstant.OPERATECONTENT, param.getTransferReason());
	                    operateLogRpc.saveOperateLog(map);
	                    /******************** 修改待办事务统计表的状态  start *******************************/
                        AgendaDO agendaDO = new AgendaDO();
                        agendaDO.setServiceCode(param.getTransferCode());
                        agendaDO.setIsDo(1);
                        agendaDO.setType(StringConsts.STATISTIC_PENDING_RENT_TRANSFER);
                        agendaDO.setModifiedName(param.getModifiedName());
                        agendaDO.setIp(param.getIp());
                        statisticsManager.updatBacklogStatisticsIsDo(agendaDO);
                        /******************** 修改待办事务统计表的状态  end *******************************/
						apiResult.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
			            apiResult.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
			            apiResult.setData(flag);
					}
				}else{
					apiResult.setCode(PactCommonEnum.PACT_RENT_TRANSFER_REJECT_STATE_ERROR.getCode());
					apiResult.setMessage(PactCommonEnum.PACT_RENT_TRANSFER_REJECT_STATE_ERROR.getMessage());
					return apiResult;
				}
				
			}else{
				apiResult.setCode(PactCommonEnum.PACT_RENT_TRANSFER_NO_INFO.getCode());
				apiResult.setMessage(PactCommonEnum.PACT_RENT_TRANSFER_NO_INFO.getMessage());
			}
		} catch (PactManagerExcepion e) {
            LOG.error(CommonEnum.SYSTEM_EXCEPTION.getMessage(), e);
            apiResult.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            apiResult.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        } catch (Exception e) {
            LOG.error(CommonEnum.SYSTEM_EXCEPTION.getMessage(), e);
            apiResult.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            apiResult.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        }
		return apiResult;
	}

	@Override
	public ApiResult<Boolean> check(PactRentTransferParam param) {
		ApiResult<Boolean> apiResult = new ApiResult<Boolean>();
		try {
			//查询协议详情
			String transferCode = param.getTransferCode();
			String ip= param.getIp();
			String pin = param.getModifiedName();
			PactTransferAllVO transferAllVO = pactRentTransferManager.getTransfer(transferCode);
			PactRentTransferDO pactRentTransferDO = new PactRentTransferDO();
			FinanceReceiptPayDO oldFinanceReceiptPayDO = new FinanceReceiptPayDO();
			FinanceReceiptPayDO newFinanceReceiptPayDO = new FinanceReceiptPayDO();
			FinanceReceiptPayDO transferReceiptPayDO = new FinanceReceiptPayDO();
			RentBaseDO newRentBaseDO = new RentBaseDO();
			RentBaseDO oldRentBaseDO = new RentBaseDO();
			if (transferAllVO !=null && transferAllVO.getPactRentTransferVO()!=null) {
			    String oldPactCode = transferAllVO.getPactRentTransferVO().getPirmaryPactCode();
                String newPactCode = transferAllVO.getPactRentTransferVO().getNewPactCode();
				int state = transferAllVO.getPactRentTransferVO().getState();
				RentBaseVO rentBaseVO = rentBaseManager.getRentBaseByCode(oldPactCode);
				apiResult =  checkData(rentBaseVO);
                if(!apiResult.getData()){
                    return apiResult;
                }
				if (state==2) {
					pactRentTransferDO.setState(3);
					pactRentTransferDO.setTransferCode(transferCode);
					pactRentTransferDO.setIp(ip);
					pactRentTransferDO.setModifiedName(pin);
					pactRentTransferDO.setPirmaryPactCode(oldPactCode);
					pactRentTransferDO.setNewPactCode(newPactCode);
					pactRentTransferDO.setTransferTime(transferAllVO.getPactRentTransferVO().getTransferTime());
					//老合同收支  解约当天以后的全删除
					oldFinanceReceiptPayDO.setPactCode(oldPactCode);
					oldFinanceReceiptPayDO.setCostTime(DateTools.getLastDay(transferAllVO.getPactRentTransferVO().getTransferTime(),1));
					oldFinanceReceiptPayDO.setIp(ip);
					oldFinanceReceiptPayDO.setIsDelete(1);
					oldFinanceReceiptPayDO.setModifiedName(pin);
					//新合同收支
					newFinanceReceiptPayDO.setPactCode(newPactCode);
					newFinanceReceiptPayDO.setIp(ip);
					newFinanceReceiptPayDO.setModifiedName(pin);
					newFinanceReceiptPayDO.setIsValid(1);
					//协议收支
					transferReceiptPayDO.setPactCode(transferCode);
					transferReceiptPayDO.setIsValid(1);
					transferReceiptPayDO.setModifiedName(pin);
					transferReceiptPayDO.setIp(ip);
					//新合同审核通过
	                newRentBaseDO.setRentPactCode(newPactCode);
	                newRentBaseDO.setIsEffective(0);
	                newRentBaseDO.setState(3);
	                newRentBaseDO.setModifiedName(param.getModifiedName());
	                newRentBaseDO.setIp(param.getIp());
	                //老合同变状态
	                oldRentBaseDO.setRentPactCode(oldPactCode);
	                oldRentBaseDO.setDueState(1);
	                oldRentBaseDO.setCancelState(4);
	                oldRentBaseDO.setIsEffective(0);
	                oldRentBaseDO.setModifiedName(param.getModifiedName());
	                oldRentBaseDO.setIp(param.getIp());
	                apiResult.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                    apiResult.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                    apiResult.setData(true);
				}else{
				    apiResult.setCode(PactCommonEnum.PACT_AUDIT_STATE_ERROR.getCode());
                    apiResult.setMessage(PactCommonEnum.PACT_AUDIT_STATE_ERROR.getMessage());
				}
				boolean flag = pactRentTransferManager.checkTransfer(pactRentTransferDO, oldFinanceReceiptPayDO,newFinanceReceiptPayDO,transferReceiptPayDO,newRentBaseDO,oldRentBaseDO);
				if (flag) {
				    List<String> variableList = new ArrayList<>();
                    variableList.add(param.getTransferCode());
                    saveRemind(param,StringConsts.REVIEW_PASS_TRANSFER,variableList);
				    Map<String, Object> map = new HashMap<String, Object>();
                    map.put(NormalConstant.SERVICETYPE, SerializeTypeConts.TRANSFER);
                    map.put(NormalConstant.SERVICECODE, pactRentTransferDO.getTransferCode());
                    map.put(NormalConstant.OPERATETYPE, StringConsts.OPERATETYPE_REVIEW_PASS);
                    map.put(NormalConstant.OPERATEPIN, param.getModifiedName());
                    map.put(NormalConstant.OPERATECONTENT, StringConsts.REVIEWPASS_DATA);
                    operateLogRpc.saveOperateLog(map);
                    /******************** 修改待办事务统计表的状态  start *******************************/
                    AgendaDO agendaDO = new AgendaDO();
                    agendaDO.setServiceCode(param.getTransferCode());
                    agendaDO.setIsDo(1);
                    agendaDO.setType(StringConsts.STATISTIC_PENDING_RENT_TRANSFER);
                    agendaDO.setModifiedName(param.getModifiedName());
                    agendaDO.setIp(param.getIp());
                    statisticsManager.updatBacklogStatisticsIsDo(agendaDO);
                    /******************** 修改待办事务统计表的状态  end *******************************/
                    saveReceivablesStatistics(transferAllVO);
                    saveObligationStatistics(transferAllVO);
                    removeStatistics(transferAllVO);
					apiResult.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
		            apiResult.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
		            apiResult.setData(flag);
				}else{
				    apiResult.setCode(CommonEnum.REQUEST_FAIL.getCode());
                    apiResult.setMessage(CommonEnum.REQUEST_FAIL.getMessage());
				}
			}else{
				apiResult.setCode(PactCommonEnum.PACT_RENT_TRANSFER_NO_INFO.getCode());
				apiResult.setMessage(PactCommonEnum.PACT_RENT_TRANSFER_NO_INFO.getMessage());
			}
			
		} catch (PactManagerExcepion e) {
            LOG.error(CommonEnum.SYSTEM_EXCEPTION.getMessage(), e);
            apiResult.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            apiResult.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        } catch (Exception e) {
            LOG.error(CommonEnum.SYSTEM_EXCEPTION.getMessage(), e);
            apiResult.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            apiResult.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        }
		return apiResult;
	}

    private void removeStatistics(PactTransferAllVO transferAllVO) {
        PactRentTransferVO pactRentTransferVO = transferAllVO.getPactRentTransferVO();
        /******************** 待办事务统计待收款（老合同的应收删除）      start *******************************/
        List<FinanceReceiptPayVO> delReceiptPayVOS = null;
        List<FinanceReceiptPayVO> delPendingPaymentList  = null;
        List<AgendaDO> deleteList = new ArrayList<>();
        FinanceReceiptPayQuery oldPactReceiptPayQuery = new FinanceReceiptPayQuery();
        oldPactReceiptPayQuery.setPactCode(pactRentTransferVO.getPirmaryPactCode());
        Date date = DateUtil.getDayByDay(pactRentTransferVO.getTransferTime(), 1);
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
    * @Title: saveObligationStatistics
    * @Description: TODO(  待办事务统计待付款,协议+新合同中所有收支的待付款   )
    * @author GuoXiaoPeng
    * @param rentContinuedVO 调房协议信息
    * @throws 异常
     */
	private void saveObligationStatistics(PactTransferAllVO transferAllVO) {
	    PactRentTransferVO pactRentTransferVO = transferAllVO.getPactRentTransferVO();
        /******************** 待办事务统计待付款（协议中的待付款）      start *******************************/
        FinanceReceiptPayQuery financeReceiptPayQuery = new FinanceReceiptPayQuery();
        financeReceiptPayQuery.setIsValid(1);
        financeReceiptPayQuery.setPactCode(pactRentTransferVO.getTransferCode());
        financeReceiptPayQuery.setType(2);
        List<Integer> costList = new ArrayList<>();
        costList.add(4);
        costList.add(5);
        financeReceiptPayQuery.setCostList(costList);
        List<FinanceReceiptPayVO> financeReceiptPayVOs = financeReceiptPayManager.listFinanceReceiptPayByPactCode(financeReceiptPayQuery);
        if(null != financeReceiptPayVOs && financeReceiptPayVOs.size() > 0){
            CommonBelongerQuery afterRentBelongerQuery = new CommonBelongerQuery();
            afterRentBelongerQuery.setPactCode(pactRentTransferVO.getTransferCode());
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
        /******************** 待办事务统计待付款（协议中的待付款）      end *******************************/
        /******************** 待办事务统计待付款（新合同中的待付款）       start *******************************/
        FinanceReceiptPayQuery newPactReceiptPayQuery = new FinanceReceiptPayQuery();
        newPactReceiptPayQuery.setIsValid(1);
        newPactReceiptPayQuery.setPactCode(pactRentTransferVO.getNewPactCode());
        newPactReceiptPayQuery.setType(0);
        newPactReceiptPayQuery.setCostList(costList);
        List<FinanceReceiptPayVO> newPactReceiptPayVOs = financeReceiptPayManager.listFinanceReceiptPayByPactCode(newPactReceiptPayQuery);
        if(null != newPactReceiptPayVOs && newPactReceiptPayVOs.size() > 0){
            CommonBelongerVO afterBelonger = rentBaseManager.getRentAfterBelonger(pactRentTransferVO.getNewPactCode());
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
        /******************** 待办事务统计待付款（新合同中的待付款）       start *******************************/
    }
	/**
    * 
    * @Title: saveReceivablesStatistics
    * @Description: TODO( 待办事务统计待收款,协议+新合同中所有收支的待收款   )
    * @author GuoXiaoPeng
    * @param rentContinuedVO  调房协议信息
    * @throws 异常
    */
    private void saveReceivablesStatistics(PactTransferAllVO transferAllVO) {
        PactRentTransferVO pactRentTransferVO = transferAllVO.getPactRentTransferVO();
        /******************** 待办事务统计待收款（协议中的应收）      start *******************************/
        FinanceReceiptPayQuery financeReceiptPayQuery = new FinanceReceiptPayQuery();
        financeReceiptPayQuery.setIsValid(1);
        financeReceiptPayQuery.setPactCode(pactRentTransferVO.getTransferCode());
        financeReceiptPayQuery.setType(0);
        List<Integer> costList = new ArrayList<>();
        costList.add(1);
        costList.add(2);
        financeReceiptPayQuery.setCostList(costList);
        List<FinanceReceiptPayVO> financeReceiptPayVOs = financeReceiptPayManager.listFinanceReceiptPayByPactCode(financeReceiptPayQuery);
        if(null != financeReceiptPayVOs && financeReceiptPayVOs.size() > 0){
            CommonBelongerQuery afterRentBelongerQuery = new CommonBelongerQuery();
            afterRentBelongerQuery.setPactCode(pactRentTransferVO.getTransferCode());
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
        newPactReceiptPayQuery.setPactCode(pactRentTransferVO.getNewPactCode());
        newPactReceiptPayQuery.setType(0);
        newPactReceiptPayQuery.setCostList(costList);
        List<FinanceReceiptPayVO> newPactReceiptPayVOs = financeReceiptPayManager.listFinanceReceiptPayByPactCode(newPactReceiptPayQuery);
        if(null != newPactReceiptPayVOs && newPactReceiptPayVOs.size() > 0){
            CommonBelongerVO afterBelonger = rentBaseManager.getRentAfterBelonger(pactRentTransferVO.getNewPactCode());
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
	private void saveRemind(PactRentTransferParam param, String key, List<String> variableList) {
        String content = remindMap.get(key);
        for (int i = 0; i < variableList.size(); i++) {
            content = content.replace(StringConsts.VARIABLE + i, variableList.get(i));
        }
        CommonBelongerQuery commonBelongerQuery = new CommonBelongerQuery();
        commonBelongerQuery.setPactCode(param.getTransferCode());
        commonBelongerQuery.setUserRole(StringConsts.BELONGER_PERSON);
        CommonBelongerVO commonBelongerVO = commonBelongerManager.getBelongersByParam(commonBelongerQuery);
        Map<String, Object> map = new HashMap<>();
        map.put(NormalConstant.REMIND_TITLE, StringConsts.REMIND_AUDITING_TITLE);
        map.put(NormalConstant.REMIND_PIN, commonBelongerVO.getUserPin());
        map.put(NormalConstant.REMIND_SERVICETYPE, SerializeTypeConts.TRANSFER);
        map.put(NormalConstant.REMIND_SERVICECODE, param.getTransferCode());
        map.put(NormalConstant.REMIND_REMINDCONTENT,content);
        remindRpc.saveRemind(map);
    }
	  /**
     * 
    * @Title: getPermissions
    * @Description: TODO( 查看有没有权限查看调房协议 )
    * @author GuoXiaoPeng
    * @param param 调房协议编码
    * @return 有权限返回true没有false
    * @throws 异常
     */
    private boolean getPermissions(PactRentTransferParam param) {
        ApiResult<PersonalResult> personalApiResult = personalRpc.getPersonalResultBypin(param.getCreateName());
        PersonalResult personalResult = personalApiResult.getData();
        Integer scope = param.getScope();
        PactRentTransferQuery query = new PactRentTransferQuery();
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
        query.setTransferCode(param.getTransferCode());
        boolean flag = pactRentTransferManager.getPermissions(query);
        return flag;
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
	public PactRentTransferManager getPactRentTransferManager() {
		return pactRentTransferManager;
	}

	public void setPactRentTransferManager(PactRentTransferManager pactRentTransferManager) {
		this.pactRentTransferManager = pactRentTransferManager;
	}

	public RentBaseManager getRentBaseManager() {
		return rentBaseManager;
	}

	public void setRentBaseManager(RentBaseManager rentBaseManager) {
		this.rentBaseManager = rentBaseManager;
	}

	public RentCustomerManager getRentCustomerManager() {
		return rentCustomerManager;
	}

	public void setRentCustomerManager(RentCustomerManager rentCustomerManager) {
		this.rentCustomerManager = rentCustomerManager;
	}

	public CommonMeterReadManager getCommonMeterReadManager() {
		return commonMeterReadManager;
	}

	public void setCommonMeterReadManager(
			CommonMeterReadManager commonMeterReadManager) {
		this.commonMeterReadManager = commonMeterReadManager;
	}

	public RentRoomManager getRentRoomManager() {
		return rentRoomManager;
	}

	public void setRentRoomManager(RentRoomManager rentRoomManager) {
		this.rentRoomManager = rentRoomManager;
	}
	
	public CommonOwnerManager getCommonOwnerManager() {
		return commonOwnerManager;
	}

	public void setCommonOwnerManager(CommonOwnerManager commonOwnerManager) {
		this.commonOwnerManager = commonOwnerManager;
	}

	public CommonBelongerManager getCommonBelongerManager() {
		return commonBelongerManager;
	}

	public void setCommonBelongerManager(CommonBelongerManager commonBelongerManager) {
		this.commonBelongerManager = commonBelongerManager;
	}

	public FinanceReceiptPayManager getFinanceReceiptPayManager() {
		return financeReceiptPayManager;
	}

	public void setFinanceReceiptPayManager(
			FinanceReceiptPayManager financeReceiptPayManager) {
		this.financeReceiptPayManager = financeReceiptPayManager;
	}

	public CommonGoodsManager getCommonGoodsManager() {
		return commonGoodsManager;
	}

	public void setCommonGoodsManager(CommonGoodsManager commonGoodsManager) {
		this.commonGoodsManager = commonGoodsManager;
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

    public CommonCostRuleManager getCommonCostRuleManager() {
        return commonCostRuleManager;
    }

    public void setCommonCostRuleManager(CommonCostRuleManager commonCostRuleManager) {
        this.commonCostRuleManager = commonCostRuleManager;
    }
    
}
