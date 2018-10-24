package com.young.pact.service.pactrenttermination.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.tools.common.util.convert.BeanUtils;
import com.tools.common.util.string.StringUtil;
import com.young.common.dict.CommonEnum;
import com.young.common.domain.ApiResult;
import com.young.common.page.PageBean;
import com.young.common.page.PageTableBean;
import com.young.pact.api.domain.param.rest.pactrenttermination.PactRentTerminationParam;
import com.young.pact.api.domain.result.rest.commonpic.CommonPicResult;
import com.young.pact.api.domain.result.rest.financereceiptpay.FinanceReceiptPayResult;
import com.young.pact.api.domain.result.rest.pactrenttermination.PactRentTerminationResult;
import com.young.pact.api.domain.result.rest.rentbase.RentBaseResult;
import com.young.pact.api.service.rest.pactrenttermination.PactRentTerminationService;
import com.young.pact.common.constant.NormalConstant;
import com.young.pact.common.constant.PactTypeConsts;
import com.young.pact.common.constant.SerializeTypeConts;
import com.young.pact.common.constant.StringConsts;
import com.young.pact.common.dict.PactCommonEnum;
import com.young.pact.common.exception.PactManagerExcepion;
import com.young.pact.common.util.DateTools;
import com.young.pact.domain.commonbelonger.CommonBelongerDO;
import com.young.pact.domain.commonbelonger.CommonBelongerQuery;
import com.young.pact.domain.commonbelonger.CommonBelongerVO;
import com.young.pact.domain.commongoods.CommonGoodsDO;
import com.young.pact.domain.commongoods.CommonGoodsPicDO;
import com.young.pact.domain.commonmeterread.CommonMeterReadDO;
import com.young.pact.domain.commonmeterread.CommonMeterReadPicDO;
import com.young.pact.domain.commonpic.CommonPicDO;
import com.young.pact.domain.commonpic.CommonPicVO;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayDO;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayQuery;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayVO;
import com.young.pact.domain.pactrenttermination.PactRentTerminationAllVO;
import com.young.pact.domain.pactrenttermination.PactRentTerminationDO;
import com.young.pact.domain.pactrenttermination.PactRentTerminationQuery;
import com.young.pact.domain.pactrenttermination.PactRentTerminationVO;
import com.young.pact.domain.rentbase.RentBaseDO;
import com.young.pact.domain.rentbase.RentBaseVO;
import com.young.pact.domain.rentcustomer.RentCustomerVO;
import com.young.pact.domain.rentroom.RentRoomVO;
import com.young.pact.domain.statistics.AgendaDO;
import com.young.pact.domain.statistics.StatisticDO;
import com.young.pact.manager.commonbelonger.CommonBelongerManager;
import com.young.pact.manager.financereceiptpay.FinanceReceiptPayManager;
import com.young.pact.manager.pactrenttermination.PactRentTerminationManager;
import com.young.pact.manager.rentbase.RentBaseManager;
import com.young.pact.manager.rentcustomer.RentCustomerManager;
import com.young.pact.manager.rentroom.RentRoomManager;
import com.young.pact.manager.statistics.StatisticsManager;
import com.young.pact.rpc.dept.DeptRpc;
import com.young.pact.rpc.operatelog.OperateLogRpc;
import com.young.pact.rpc.personal.PersonalRpc;
import com.young.pact.rpc.remind.RemindRpc;
import com.young.pact.rpc.room.RoomRpc;
import com.young.pact.rpc.serialize.SerializeRpc;
import com.young.sso.api.domain.result.rpc.personal.PersonalResult;

/**
 * 
* @ClassName: PactRentTerminationServiceImpl
* @Description: 托出解约协议实现类
* @author LiuYuChi
* @date 2018年8月12日 上午11:00:28
*
 */
@Service("pactRentTerminationService")
public class PactRentTerminationServiceImpl implements PactRentTerminationService{

	private static final Log LOG = LogFactory.getLog(PactRentTerminationServiceImpl.class);
	private PactRentTerminationManager pactRentTerminationManager;
	private RentRoomManager rentRoomManager;
	private CommonBelongerManager commonBelongerManager;
	private RentBaseManager rentBaseManager ;
	private PersonalRpc personalRpc;
	private OperateLogRpc operateLogRpc;
	private SerializeRpc serializeRpc;
	private DeptRpc deptRpc;
	private Map<String,String> remindMap;
	private RemindRpc remindRpc;
	private StatisticsManager statisticsManager;
	private FinanceReceiptPayManager financeReceiptPayManager;
	private RoomRpc roomRpc;
	private RentCustomerManager rentCustomerManager;
	@Override
	public ApiResult<Boolean> insertTermination(PactRentTerminationParam param) {
		ApiResult<Boolean> apiResult = new ApiResult<Boolean>();
		try {
			if (param!=null) {
				//解约协议基本信息
				PactRentTerminationDO pactRentTerminationDO = new PactRentTerminationDO();
				BeanUtils.copyProperties(param, pactRentTerminationDO);
				String pin = param.getCreateName();
				String ip = param.getIp();
				String dissolutionCode = "";
				dissolutionCode = serializeRpc.queryCodeBySerializeType(SerializeTypeConts.RTERMINA);
				pactRentTerminationDO.setDissolutionCode(dissolutionCode);
				pactRentTerminationDO.setState(1);
				pactRentTerminationDO.setIsDelete(0);
				if(StringConsts.EXPIR_TERMINATION_VALUE.equals(pactRentTerminationDO.getDissolutionType())){
				    RentBaseVO rentBaseVO = rentBaseManager.getRentBaseByCode(pactRentTerminationDO.getPirmaryPactCode());
				    pactRentTerminationDO.setPirmaryEndTime(rentBaseVO.getEndTime());
				}
				//责任人信息
				List<CommonBelongerDO> commonBelongerDOList = new ArrayList<>();
	            // 需要根据用户pin通过rpc查询用户信息
				ApiResult<PersonalResult> personalResult = personalRpc.getPersonalResultBypin(param.getUserPin());
				CommonBelongerDO commonBelongerDO = new CommonBelongerDO();
	            commonBelongerDO.setPactCode(dissolutionCode);
	            commonBelongerDO.setPactType(PactTypeConsts.RENT_TERMINATION);
	            commonBelongerDO.setUserPin(param.getUserPin());
	            if(null!=personalResult&&StringConsts.REQUEST_SUCCESS_CODE.equals(personalResult.getCode())){
	                PersonalResult personalResultData = personalResult.getData();
	                commonBelongerDO.setUserName(personalResultData.getEmployeeName());
	                commonBelongerDO.setUserTel(personalResultData.getEmployeeTel());
	                commonBelongerDO.setDeptName(personalResultData.getDeptName());
	                commonBelongerDO.setDeptCode(personalResultData.getDeptCode());
	            }
	            commonBelongerDO.setUserRole(StringConsts.RENT_USER_ROLE);
	            commonBelongerDO.setIp(ip);
	            commonBelongerDO.setCreateName(pin);
	            commonBelongerDOList.add(commonBelongerDO);

	            CommonBelongerDO commonBelonger = new CommonBelongerDO();
	            commonBelonger.setPactCode(dissolutionCode);
	            commonBelonger.setPactType(PactTypeConsts.RENT_TERMINATION);
	            commonBelonger.setUserPin(param.getUserPin());
	            if(null!=personalResult&&StringConsts.REQUEST_SUCCESS_CODE.equals(personalResult.getCode())){
                    PersonalResult personalResultData = personalResult.getData();
                    commonBelonger.setUserName(personalResultData.getEmployeeName());
                    commonBelonger.setUserTel(personalResultData.getEmployeeTel());
                    commonBelonger.setDeptName(personalResultData.getDeptName());
                    commonBelonger.setDeptCode(personalResultData.getDeptCode());
                }
	            commonBelonger.setUserRole(StringConsts.BELONGER_PERSON);
	            commonBelonger.setIp(ip);
	            commonBelonger.setCreateName(pin);
	            commonBelongerDOList.add(commonBelonger);
	            
	            // 收支
	            RentRoomVO room = rentRoomManager.getRommByRentCode(param.getPirmaryPactCode());
	            pactRentTerminationDO.setAddress(room.getAddress());
	            List<FinanceReceiptPayDO> receiptPayList = new ArrayList<>();
	            BeanUtils.copyListProperties(param.getReceiptPayList(), receiptPayList, FinanceReceiptPayDO.class);
	            if(null!= receiptPayList){
	                for (FinanceReceiptPayDO financeReceiptPayDO : receiptPayList) {
	                    financeReceiptPayDO.setPactCode(dissolutionCode);
	                    financeReceiptPayDO.setPactType(PactTypeConsts.RENT_TERMINATION);
	                    financeReceiptPayDO.setCreateName(pin);
	                    financeReceiptPayDO.setIp(ip);
	                    if(null != financeReceiptPayDO.getType() && 0 == financeReceiptPayDO.getType()){// 应收
	                        financeReceiptPayDO.setCostState(1);// 待收款
	                    }else if(null != financeReceiptPayDO.getType() && 2 == financeReceiptPayDO.getType()){// 应支
	                        financeReceiptPayDO.setCostState(4);// 待付款
	                    }
	                    financeReceiptPayDO.setHouseCode(room.getHouseCode());
	                    financeReceiptPayDO.setRoomCode(room.getRoomCode());
	                    financeReceiptPayDO.setAddress(room.getAddress());
	                }
	            }
	            // 合同图片
	            List<CommonPicDO> commonPactPicList = new ArrayList<>();
	            if(null!=param.getPicList()){
	                BeanUtils.copyListProperties(param.getPicList(), commonPactPicList, CommonPicDO.class);
	            }
	            
	            if(null != commonPactPicList){
	                for (CommonPicDO commonPicDO : commonPactPicList) {
	                    commonPicDO.setIp(ip);
	                    commonPicDO.setCreateName(pin);
	                    commonPicDO.setPactCode(dissolutionCode);
	                    commonPicDO.setPactType(PactTypeConsts.RENT_TERMINATION);
	                }
	            }
	            //抄表
	            List<CommonMeterReadDO> meterReadList = new ArrayList<>();
	            BeanUtils.copyListProperties(param.getMeterReadList(), meterReadList, CommonMeterReadDO.class);
	            if(null != meterReadList){
	                for (CommonMeterReadDO commonMeterReadDO : meterReadList) {
	                    commonMeterReadDO.setIp(ip);
	                    commonMeterReadDO.setCreateName(pin);
	                    commonMeterReadDO.setPactCode(dissolutionCode);
	                    commonMeterReadDO.setPactType(PactTypeConsts.RENT_TERMINATION);
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
	            //物品
	            List<CommonGoodsDO> goodsList= new ArrayList<>();
	            BeanUtils.copyListProperties(param.getGoodsList(), goodsList, CommonGoodsDO.class);
	            if (null != goodsList) {
					for (CommonGoodsDO commonGoodsDO : goodsList) {
						commonGoodsDO.setIp(ip);
						commonGoodsDO.setCreateName(pin);
						commonGoodsDO.setPactCode(dissolutionCode);
						commonGoodsDO.setPactType(PactTypeConsts.RENT_TERMINATION);
						if (null!=commonGoodsDO.getCommonGoodsPicList()) {
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
	            
	            boolean flag = pactRentTerminationManager.insertTermination(pactRentTerminationDO, commonBelongerDOList, 
	            		commonPactPicList, goodsList, meterReadList, receiptPayList);
	            if (flag) {
	                Map<String, Object> map = new HashMap<String, Object>();
	                map.put(NormalConstant.SERVICETYPE, SerializeTypeConts.RTERMINA);
	                map.put(NormalConstant.SERVICECODE, dissolutionCode);
	                map.put(NormalConstant.OPERATETYPE, StringConsts.ADD);
	                map.put(NormalConstant.OPERATEPIN, param.getCreateName());
	                map.put(NormalConstant.OPERATECONTENT, StringConsts.ADD_DATA);
	                operateLogRpc.saveOperateLog(map);
	            	apiResult.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
	            	apiResult.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
	            	apiResult.setData(true);
				}else{
				    apiResult.setCode(PactCommonEnum.RENT_TERMINATION_SAVE_ERROR.getCode());
	                apiResult.setMessage(PactCommonEnum.RENT_TERMINATION_SAVE_ERROR.getMessage());
	                return apiResult;
				}
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
	public ApiResult<PageBean<PactRentTerminationResult>> listTermination(
			PactRentTerminationParam param) {
		ApiResult<PageBean<PactRentTerminationResult>> apiResult = new ApiResult<>();
		try {
			if (param!=null) {
				if(StringUtil.isNotBlank(param.getPirmaryEndTimeStart())
	                    && StringUtil.isNotBlank(param.getPirmaryEndTimeEnd())){
	                param.setPirmaryEndTimeStart(param.getPirmaryEndTimeStart() + " 00:00:00");
	                param.setPirmaryEndTimeEnd(param.getPirmaryEndTimeEnd() + " 23:59:59");
	            }
				if(StringUtil.isNotBlank(param.getDissolutionTimeStart())
	                    && StringUtil.isNotBlank(param.getDissolutionTimeEnd())){
	                param.setDissolutionTimeStart(param.getDissolutionTimeStart() + " 00:00:00");
	                param.setDissolutionTimeEnd(param.getDissolutionTimeEnd() + " 23:59:59");
	            }
				PactRentTerminationQuery query = new PactRentTerminationQuery();
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
				PageBean<PactRentTerminationVO> pageBean = pactRentTerminationManager.listTermination(query);
	            PageBean<PactRentTerminationResult> page = new PageTableBean<>(query.getPageIndex(), query.getPageSize());
	            page.setTotalItem(pageBean.getTotalItem());
	            BeanUtils.copyListProperties(pageBean.getData(), page.getData(), PactRentTerminationResult.class);
	            apiResult.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
	            apiResult.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
	            apiResult.setData(page);
			}else{
				apiResult.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
				apiResult.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
                return apiResult;
			}
		}  catch (PactManagerExcepion e) {
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
	public ApiResult<PactRentTerminationResult> getTermination(PactRentTerminationParam param) {
		ApiResult<PactRentTerminationResult> apiResult = new ApiResult<PactRentTerminationResult>();
		try {
		    String dissolutionCode = param.getDissolutionCode();
		    boolean permissionsFlag = getPermissions(param);
            if(!permissionsFlag){
                apiResult.setCode(PactCommonEnum.RENTTERMINATION_NO_PERMISSIONS.getCode());
                apiResult.setMessage(PactCommonEnum.RENTTERMINATION_NO_PERMISSIONS.getMessage());
                return apiResult;
            }
			//协议信息+图片
			PactRentTerminationResult result = new PactRentTerminationResult();
			PactRentTerminationQuery query = new PactRentTerminationQuery();
			query.setDissolutionCode(dissolutionCode);
			PactRentTerminationAllVO pactRentTerminationAllVO = pactRentTerminationManager.getPactRentTermination(query);
			PactRentTerminationVO pactRentTerminationVO = null;
			List<CommonPicVO> commonPicList = null;
			if (pactRentTerminationAllVO!=null) {
				pactRentTerminationVO = pactRentTerminationAllVO.getPactRentTerminationVO();
				commonPicList = pactRentTerminationAllVO.getCommonPicVO();
			}
			BeanUtils.copyProperties(pactRentTerminationVO, result);
			List<CommonPicResult> picResultList = new ArrayList<>();
			BeanUtils.copyListProperties(commonPicList, picResultList, CommonPicResult.class);
			result.setPicList(picResultList);
			//责任人
			
			List<CommonBelongerVO> commonBelongList = commonBelongerManager.listCommonBelonger(dissolutionCode);
			if (commonBelongList!=null) {
				for (CommonBelongerVO commonBelongerVO : commonBelongList) {
					if (StringConsts.RENT_USER_ROLE.equals(commonBelongerVO.getUserRole())) {
						result.setUserName(commonBelongerVO.getUserName());
					}
				}
			}
			
			//合同信息
			RentBaseResult oldPact = new RentBaseResult();
			String pactCode = pactRentTerminationVO.getPirmaryPactCode();
			RentBaseVO rentBaseOld = rentBaseManager.getRentBaseByCode(pactCode);
			RentRoomVO rentRoomVO = rentRoomManager.getRommByRentCode(pactCode);
			RentCustomerVO rentCustomerVO = rentCustomerManager.getRentCustomerByPactCode(pactCode);
			BeanUtils.copyProperties(rentBaseOld, oldPact);
			result.setPact(oldPact);
			oldPact.setRenterName(rentCustomerVO.getName());
			oldPact.setRenterTel(rentCustomerVO.getTel());
			oldPact.setRoomCode(rentRoomVO.getRoomCode());
			oldPact.setPropertyAddress(rentRoomVO.getAddress());
			apiResult.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
			apiResult.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
			apiResult.setData(result);
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
	public ApiResult<PactRentTerminationResult> toUpdatePage(String dissolutionCode) {
		ApiResult<PactRentTerminationResult> apiResult = new ApiResult<PactRentTerminationResult>();
		try {
			//协议信息+图片
			PactRentTerminationResult result = new PactRentTerminationResult();
			PactRentTerminationQuery query = new PactRentTerminationQuery();
			query.setDissolutionCode(dissolutionCode);
			PactRentTerminationAllVO pactRentTerminationAllVO = pactRentTerminationManager.getPactRentTermination(query);
			Integer state = pactRentTerminationAllVO.getPactRentTerminationVO().getState();
			//判断当前状态是否可以进行修改
			if (state==1||state==4) {
				PactRentTerminationVO pactRentTerminationVO = new PactRentTerminationVO();
				List<CommonPicVO> commonPicList = new ArrayList<>();
				List<FinanceReceiptPayVO> financeReceiptList = new ArrayList<>();
				if (pactRentTerminationAllVO!=null) {
					pactRentTerminationVO = pactRentTerminationAllVO.getPactRentTerminationVO();
					commonPicList = pactRentTerminationAllVO.getCommonPicVO();
					financeReceiptList = pactRentTerminationAllVO.getFinanceReceiptPayList();
				}
				//协议信息
				BeanUtils.copyProperties(pactRentTerminationVO, result);
				List<CommonPicResult> picResultList = new ArrayList<>();
				//协议图片
				BeanUtils.copyListProperties(commonPicList, picResultList, CommonPicResult.class);
				result.setPicList(picResultList);
				//收支列表
				List<FinanceReceiptPayResult> receiptPayList = new ArrayList<>();
				BeanUtils.copyListProperties(financeReceiptList, receiptPayList, FinanceReceiptPayResult.class);
				result.setReceiptPayList(receiptPayList);
				//责任人
				List<CommonBelongerVO> commonBelongList = commonBelongerManager.listCommonBelonger(dissolutionCode);
				if (commonBelongList!=null) {
					for (CommonBelongerVO commonBelongerVO : commonBelongList) {
						if (StringConsts.RENT_USER_ROLE.equals(commonBelongerVO.getUserRole())) {
							result.setUserName(commonBelongerVO.getUserName());
							result.setUserPin(commonBelongerVO.getUserPin());
						}
					}
				}
				apiResult.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
	            apiResult.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
	            apiResult.setData(result);
			}else{
				apiResult.setCode(PactCommonEnum.PACT_RENT_TRANSFER_STATE_ERROR.getCode());
				apiResult.setMessage(PactCommonEnum.PACT_RENT_TRANSFER_STATE_ERROR.getMessage());
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
	public ApiResult<Boolean> updateTerminatioin(PactRentTerminationParam param) {
		ApiResult<Boolean> apiResult= new ApiResult<Boolean> ();
		try {
			PactRentTerminationQuery query = new PactRentTerminationQuery();
			query.setDissolutionCode(param.getDissolutionCode());
			PactRentTerminationAllVO pactRentTerminationAllVO = pactRentTerminationManager.getPactRentTermination(query);
			Integer state = pactRentTerminationAllVO.getPactRentTerminationVO().getState();
			if (state==1||state==4) {
				String ip = param.getIp();
				String pin = param.getCreateName();
				//协议信息
				PactRentTerminationDO pactRentTerminationDO = new PactRentTerminationDO();
				BeanUtils.copyProperties(param, pactRentTerminationDO);
				//签约管家
                CommonBelongerDO commonBelongerDO = new CommonBelongerDO();
                //根据pin查询管家信息
                ApiResult<PersonalResult> personalResult = personalRpc.getPersonalResultBypin(param.getUserPin());
                if(null!=personalResult&&StringConsts.REQUEST_SUCCESS_CODE.equals(personalResult.getCode())){
                    PersonalResult personalResultDate = personalResult.getData();
                    commonBelongerDO.setUserName(personalResultDate.getEmployeeName());
                    commonBelongerDO.setUserTel(personalResultDate.getEmployeeTel());
                    commonBelongerDO.setDeptCode(personalResultDate.getDeptCode());
                    commonBelongerDO.setDeptName(personalResultDate.getDeptName());
                }
                commonBelongerDO.setUserRole(StringConsts.BELONGER_DEAL);
                commonBelongerDO.setPactCode(param.getDissolutionCode());
                commonBelongerDO.setUserPin(param.getUserPin());
                commonBelongerDO.setIp(ip);
                commonBelongerDO.setCreateName(pin);
                commonBelongerDO.setModifiedName(pin);
                
                //收支(集合)**/
                List<FinanceReceiptPayDO> financeReceiptPayDOs = new ArrayList<>();
                BeanUtils.copyListProperties(param.getReceiptPayList(), financeReceiptPayDOs, FinanceReceiptPayDO.class);
                for (FinanceReceiptPayDO financeReceiptPayDO : financeReceiptPayDOs) {
                    financeReceiptPayDO.setPactCode(param.getDissolutionCode());
                    financeReceiptPayDO.setPactType(PactTypeConsts.RENT_TERMINATION);
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
                pactRentTerminationDO.setName(payeeFormRecPay.getName());
                pactRentTerminationDO.setAccountNum(payeeFormRecPay.getAccount());
                pactRentTerminationDO.setCaseBank(payeeFormRecPay.getBank());
                pactRentTerminationDO.setOpenBank(payeeFormRecPay.getOpenBank());
                pactRentTerminationDO.setTel(payeeFormRecPay.getTel());
                //合同照片(集合)**/
                List<CommonPicDO> commonPicDOs = new ArrayList<>();
                if(null != param.getPicList()){
                    BeanUtils.copyListProperties(param.getPicList(), commonPicDOs, CommonPicDO.class);
                }
                for (CommonPicDO commonPicDO : commonPicDOs) {
                    commonPicDO.setIp(ip);
                    commonPicDO.setCreateName(pin);
                    commonPicDO.setModifiedName(pin);
                    commonPicDO.setPactCode(param.getDissolutionCode());
                    commonPicDO.setPactType(PactTypeConsts.RENT_TERMINATION);
                }
                boolean flag = pactRentTerminationManager.updateTermination(pactRentTerminationDO, financeReceiptPayDOs, commonPicDOs, commonBelongerDO);
                if (flag) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put(NormalConstant.SERVICETYPE, SerializeTypeConts.RTERMINA);
                    map.put(NormalConstant.SERVICECODE, pactRentTerminationDO.getDissolutionCode());
                    map.put(NormalConstant.OPERATETYPE, StringConsts.UPDATE);
                    map.put(NormalConstant.OPERATEPIN, param.getCreateName());
                    map.put(NormalConstant.OPERATECONTENT, StringConsts.UPDATE_DATA);
                    operateLogRpc.saveOperateLog(map);
                	apiResult.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
        			apiResult.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
        			apiResult.setData(true);
				}
			}else{
				apiResult.setCode(PactCommonEnum.PACT_RENT_TRANSFER_STATE_ERROR.getCode());
				apiResult.setMessage(PactCommonEnum.PACT_RENT_TRANSFER_STATE_ERROR.getCode());
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
	public ApiResult<Boolean> applyCheck(PactRentTerminationParam param) {
		ApiResult<Boolean> apiResult  = new ApiResult<Boolean> ();
		try {
			if (param==null) {
				apiResult.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
	            apiResult.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
	            return apiResult;
			}
			String dissolutionCode = param.getDissolutionCode();
			String ip = param.getIp();
			PactRentTerminationQuery query = new PactRentTerminationQuery();
			query.setDissolutionCode(dissolutionCode);
			PactRentTerminationAllVO pactRentTerminationAllVO = pactRentTerminationManager.getPactRentTermination(query);
			Integer state = pactRentTerminationAllVO.getPactRentTerminationVO().getState();
			if (state==1||state==4) {
				PactRentTerminationDO pactRentTerminationDO = new PactRentTerminationDO();
				pactRentTerminationDO.setDissolutionCode(dissolutionCode);
				pactRentTerminationDO.setState(2);
				pactRentTerminationDO.setModifiedName(param.getModifiedName());
				pactRentTerminationDO.setIp(ip);
				boolean flag = pactRentTerminationManager.applyCheck(pactRentTerminationDO);
				if (flag) {
				    Map<String, Object> map = new HashMap<String, Object>();
                    map.put(NormalConstant.SERVICETYPE, SerializeTypeConts.RTERMINA);
                    map.put(NormalConstant.SERVICECODE, pactRentTerminationDO.getDissolutionCode());
                    map.put(NormalConstant.OPERATETYPE, StringConsts.OPERATETYPE_APPLICATION_AUDIT);
                    map.put(NormalConstant.OPERATEPIN, param.getModifiedName());
                    map.put(NormalConstant.OPERATECONTENT, StringConsts.AUDIT_DATA);
                    operateLogRpc.saveOperateLog(map);
                    /*************************首页待办数据统计  start*****************************/
                    CommonBelongerQuery commonBelongerQuery = new CommonBelongerQuery();
                    commonBelongerQuery.setPactCode(param.getDissolutionCode());
                    commonBelongerQuery.setUserRole(StringConsts.BELONGER_DEAL);
                    CommonBelongerVO commonBelongerVO = commonBelongerManager.getBelongersByParam(commonBelongerQuery);
                    AgendaDO agendaDO = new AgendaDO();
                    agendaDO.setServiceCode(param.getDissolutionCode());
                    agendaDO.setType(StringConsts.STATISTIC_PENDING_RENT_TERMINATION);
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
        			apiResult.setData(true);
				}
			}else{
				apiResult.setCode(PactCommonEnum.PACT_RENT_TRANSFER_APPLY_CHECK_STATE_ERROR.getCode());
				apiResult.setMessage(PactCommonEnum.PACT_RENT_TRANSFER_APPLY_CHECK_STATE_ERROR.getCode());
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
	public ApiResult<Boolean> reject(PactRentTerminationParam param) {
		ApiResult<Boolean> apiResult  = new ApiResult<Boolean> ();
		try {
			PactRentTerminationQuery query = new PactRentTerminationQuery();
			query.setDissolutionCode(param.getDissolutionCode());
			PactRentTerminationAllVO pactRentTerminationAllVO = pactRentTerminationManager.getPactRentTermination(query);
			Integer state = pactRentTerminationAllVO.getPactRentTerminationVO().getState();
			if (state==2) {
				PactRentTerminationDO pactRentTerminationDO = new PactRentTerminationDO();
				BeanUtils.copyProperties(param, pactRentTerminationDO);
				pactRentTerminationDO.setState(4);
				boolean flag = pactRentTerminationManager.reject(pactRentTerminationDO);
				if (flag) {
				    List<String> variableList = new ArrayList<>();
                    variableList.add(param.getDissolutionCode());
                    saveRemind(param,StringConsts.REVIEW_DISMISSAL_RENTTERMINATION,variableList);
				    Map<String, Object> map = new HashMap<String, Object>();
                    map.put(NormalConstant.SERVICETYPE, SerializeTypeConts.RTERMINA);
                    map.put(NormalConstant.SERVICECODE, pactRentTerminationDO.getDissolutionCode());
                    map.put(NormalConstant.OPERATETYPE, StringConsts.OPERATETYPE_REVIEW_DISMISSAL);
                    map.put(NormalConstant.OPERATEPIN, param.getModifiedName());
                    map.put(NormalConstant.OPERATECONTENT, param.getRejectReason());
                    operateLogRpc.saveOperateLog(map);
                    /******************** 修改待办事务统计表的状态  start *******************************/
                    AgendaDO agendaDO = new AgendaDO();
                    agendaDO.setServiceCode(param.getDissolutionCode());
                    agendaDO.setIsDo(1);
                    agendaDO.setType(StringConsts.STATISTIC_PENDING_RENT_TERMINATION);
                    agendaDO.setModifiedName(param.getModifiedName());
                    agendaDO.setIp(param.getIp());
                    statisticsManager.updatBacklogStatisticsIsDo(agendaDO);
                    /******************** 修改待办事务统计表的状态  end *******************************/
					apiResult.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
        			apiResult.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
        			apiResult.setData(true);
				}
			}else{
				apiResult.setCode(PactCommonEnum.PACT_RENT_TRANSFER_REJECT_STATE_ERROR.getCode());
				apiResult.setMessage(PactCommonEnum.PACT_RENT_TRANSFER_REJECT_STATE_ERROR.getMessage());
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
	public ApiResult<Boolean> delTermination(PactRentTerminationParam param) {
		ApiResult<Boolean> apiResult  = new ApiResult<Boolean> ();
		try {
			if (param==null) {
				apiResult.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
	            apiResult.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
	            return apiResult;
			}
			String dissolutionCode = param.getDissolutionCode();
			String pin = param.getUserPin();
			String ip = param.getIp();
			PactRentTerminationQuery query = new PactRentTerminationQuery();
			query.setDissolutionCode(dissolutionCode);
			PactRentTerminationAllVO pactRentTerminationAllVO = pactRentTerminationManager.getPactRentTermination(query);
			Integer state = pactRentTerminationAllVO.getPactRentTerminationVO().getState();
			if (state==1||state==4) {
				PactRentTerminationDO pactRentTerminationDO = new PactRentTerminationDO();
				pactRentTerminationDO.setDissolutionCode(dissolutionCode);
				pactRentTerminationDO.setIp(ip);
				pactRentTerminationDO.setModifiedName(pin);
				pactRentTerminationDO.setIsDelete(1);
				boolean flag = pactRentTerminationManager.delTermination(pactRentTerminationDO);//删除收支 TODO
				if (flag) {
				    Map<String, Object> map = new HashMap<String, Object>();
                    map.put(NormalConstant.SERVICETYPE, SerializeTypeConts.RTERMINA);
                    map.put(NormalConstant.SERVICECODE, pactRentTerminationDO.getDissolutionCode());
                    map.put(NormalConstant.OPERATETYPE, StringConsts.DELETE);
                    map.put(NormalConstant.OPERATEPIN, param.getModifiedName());
                    map.put(NormalConstant.OPERATECONTENT, StringConsts.DELETE_DATA);
                    operateLogRpc.saveOperateLog(map);
					apiResult.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
        			apiResult.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
        			apiResult.setData(true);
				}
			}else{
				apiResult.setCode(PactCommonEnum.PACT_RENT_TRANSFER_STATE_ERROR.getCode());
				apiResult.setMessage(PactCommonEnum.PACT_RENT_TRANSFER_STATE_ERROR.getCode());
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
	public ApiResult<Boolean> check(PactRentTerminationParam param) {
		ApiResult<Boolean> apiResult  = new ApiResult<Boolean> ();
		try {
			if (param==null) {
				apiResult.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
	            apiResult.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
	            return apiResult;
			}
			String dissolutionCode = param.getDissolutionCode();
			String pin = param.getModifiedName();
			String ip = param.getIp();
			PactRentTerminationQuery query = new PactRentTerminationQuery();
			query.setDissolutionCode(dissolutionCode);
			PactRentTerminationAllVO pactRentTerminationAllVO = pactRentTerminationManager.getPactRentTermination(query);
			Integer state = pactRentTerminationAllVO.getPactRentTerminationVO().getState();
			String oldPactCode = pactRentTerminationAllVO.getPactRentTerminationVO().getPirmaryPactCode();
			RentBaseVO rentBaseVO = rentBaseManager.getRentBaseByCode(oldPactCode);
			apiResult =  checkData(rentBaseVO);
            if(!apiResult.getData()){
                return apiResult;
            }
			if (state==2) {
				PactRentTerminationDO pactRentTerminationDO = new PactRentTerminationDO();
				pactRentTerminationDO.setDissolutionCode(dissolutionCode);
				pactRentTerminationDO.setState(3);
				pactRentTerminationDO.setModifiedName(pin);
				pactRentTerminationDO.setIp(ip);
				pactRentTerminationDO.setDissolutionTime(pactRentTerminationAllVO.getPactRentTerminationVO().getDissolutionTime());
				
				FinanceReceiptPayDO oldFinanceReceiptPayDO = new FinanceReceiptPayDO();
				oldFinanceReceiptPayDO.setPactCode(oldPactCode);
				oldFinanceReceiptPayDO.setCostTime(DateTools.weeHours(pactRentTerminationAllVO.getPactRentTerminationVO().getDissolutionTime(),1));
				oldFinanceReceiptPayDO.setIp(ip);
				oldFinanceReceiptPayDO.setModifiedName(pin);
				
				FinanceReceiptPayDO newFinanceReceiptPayDO = new FinanceReceiptPayDO();
				newFinanceReceiptPayDO.setPactCode(dissolutionCode);
				newFinanceReceiptPayDO.setIsValid(1);
				/********************************* 老合同处理   start ******************************************/
				PactRentTerminationVO pactRentTerminationVO = pactRentTerminationAllVO.getPactRentTerminationVO();
				RentBaseDO rentBaseDO = new RentBaseDO();
				rentBaseDO.setIp(ip);
				rentBaseDO.setModifiedName(param.getModifiedName());
                rentBaseDO.setRentPactCode(pactRentTerminationVO.getPirmaryPactCode());
				if(StringConsts.EXPIR_TERMINATION_VALUE.equals(pactRentTerminationVO.getDissolutionType())){
				    rentBaseDO.setDueState(2);
				    rentBaseDO.setCancelState(2);
				}else if(StringConsts.DUE_TERMINATION_VALUE.equals(pactRentTerminationVO.getDissolutionType())){
				    rentBaseDO.setCancelState(3);
				}
				/********************************* 老合同处理   end ******************************************/
				boolean flag = pactRentTerminationManager.check(pactRentTerminationDO, oldFinanceReceiptPayDO, newFinanceReceiptPayDO,rentBaseDO);
				if (flag) {
				    List<String> variableList = new ArrayList<>();
                    variableList.add(param.getDissolutionCode());
                    saveRemind(param,StringConsts.REVIEW_PASS_RENTTERMINATION,variableList);
				    Map<String, Object> map = new HashMap<String, Object>();
                    map.put(NormalConstant.SERVICETYPE, SerializeTypeConts.RTERMINA);
                    map.put(NormalConstant.SERVICECODE, pactRentTerminationDO.getDissolutionCode());
                    map.put(NormalConstant.OPERATETYPE, StringConsts.OPERATETYPE_REVIEW_PASS);
                    map.put(NormalConstant.OPERATEPIN, param.getModifiedName());
                    map.put(NormalConstant.OPERATECONTENT, StringConsts.REVIEWPASS_DATA);
                    operateLogRpc.saveOperateLog(map);
                    /****************************保存交易统计解约量   start**********************************/
                    CommonBelongerQuery commonBelongerQuery = new CommonBelongerQuery();
                    commonBelongerQuery.setPactCode(dissolutionCode);
                    commonBelongerQuery.setUserRole(StringConsts.BELONGER_DEAL);
                    CommonBelongerVO commonBelongerVO = commonBelongerManager.getBelongersByParam(commonBelongerQuery);
                    RentRoomVO rentRoomVO = rentRoomManager.getRommByRentCode(pactRentTerminationVO.getPirmaryPactCode());
                    com.young.product.api.domain.result.rpc.room.RoomResult roomRpcResult = roomRpc.getRoomByCode(rentRoomVO.getRoomCode());
                    if(StringConsts.WHOLE_RENT.equals(roomRpcResult.getRentWay())){
                        for(int i = 0;i < Integer.valueOf(roomRpcResult.getRoom()); i++){
                            StatisticDO statisticDO = new StatisticDO();
                            statisticDO.setServiceCode(dissolutionCode);
                            statisticDO.setPactType(StringConsts.RENT_DISSOLUTION_COUNT);
                            statisticDO.setPin(commonBelongerVO.getUserPin());
                            statisticDO.setName(commonBelongerVO.getUserName());
                            statisticDO.setDeptCode(commonBelongerVO.getDeptCode());
                            statisticDO.setDeptName(commonBelongerVO.getDeptName());
                            statisticsManager.savePactRoomStatistics(statisticDO);
                        }
                    }else if(StringConsts.JOINT_RENT.equals(roomRpcResult.getRentWay())){
                        StatisticDO statisticDO = new StatisticDO();
                        statisticDO.setServiceCode(dissolutionCode);
                        statisticDO.setPactType(StringConsts.RENT_DISSOLUTION_COUNT);
                        statisticDO.setPin(commonBelongerVO.getUserPin());
                        statisticDO.setName(commonBelongerVO.getUserName());
                        statisticDO.setDeptCode(commonBelongerVO.getDeptCode());
                        statisticDO.setDeptName(commonBelongerVO.getDeptName());
                        statisticsManager.savePactRoomStatistics(statisticDO);
                    }
                    /****************************保存交易统计解约量   end**********************************/
                    /******************** 修改待办事务统计表的状态  start *******************************/
                    AgendaDO agendaDO = new AgendaDO();
                    agendaDO.setServiceCode(param.getDissolutionCode());
                    agendaDO.setIsDo(1);
                    agendaDO.setType(StringConsts.STATISTIC_PENDING_RENT_TERMINATION);
                    agendaDO.setModifiedName(param.getModifiedName());
                    agendaDO.setIp(param.getIp());
                    statisticsManager.updatBacklogStatisticsIsDo(agendaDO);
                    /******************** 修改待办事务统计表的状态  end *******************************/
                    CommonBelongerQuery guardianBelongerQuery = new CommonBelongerQuery();
                    guardianBelongerQuery.setPactCode(param.getDissolutionCode());
                    guardianBelongerQuery.setUserRole(StringConsts.BELONGER_PERSON);
                    CommonBelongerVO guardianBelongerVO = commonBelongerManager.getBelongersByParam(guardianBelongerQuery);
                    saveReceivablesStatistics(param ,guardianBelongerVO);
                    saveObligationStatistics(param ,guardianBelongerVO);
					apiResult.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
        			apiResult.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
        			apiResult.setData(true);
				}else{
				    apiResult.setCode(CommonEnum.REQUEST_FAIL.getCode());
                    apiResult.setMessage(CommonEnum.REQUEST_FAIL.getMessage());
				}
			}else{
				apiResult.setCode(PactCommonEnum.PACT_RENT_TRANSFER_CHECK_ERROR.getCode());
				apiResult.setMessage(PactCommonEnum.PACT_RENT_TRANSFER_CHECK_ERROR.getMessage());
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
    * @Title: saveReceivablesStatistics
    * @Description: TODO( 添加待办事务统计待收款统计记录 )
    * @author GuoXiaoPeng
    * @param param  托出解约协议编码
    * @param afterRentBelongerVO 维护人
    * @throws 异常
     */
    private void saveReceivablesStatistics(PactRentTerminationParam param, CommonBelongerVO guardianBelongerVO) {
        FinanceReceiptPayQuery financeReceiptPayQuery = new FinanceReceiptPayQuery();
        financeReceiptPayQuery.setIsValid(1);
        financeReceiptPayQuery.setPactCode(param.getDissolutionCode());
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
                agendaDO.setPin(guardianBelongerVO.getUserPin());
                agendaDO.setName(guardianBelongerVO.getUserName());
                agendaDO.setDeptCode(guardianBelongerVO.getDeptCode());
                agendaDO.setDeptName(guardianBelongerVO.getDeptName());
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
    * @param param 托出解约协议编码
    * @param afterRentBelongerVO 维护人
    * @throws 异常
     */
    private void saveObligationStatistics(PactRentTerminationParam param, CommonBelongerVO guardianBelongerVO) {
        FinanceReceiptPayQuery financeReceiptPayQuery = new FinanceReceiptPayQuery();
        financeReceiptPayQuery.setIsValid(1);
        financeReceiptPayQuery.setPactCode(param.getDissolutionCode());
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
                agendaDO.setPin(guardianBelongerVO.getUserPin());
                agendaDO.setName(guardianBelongerVO.getUserName());
                agendaDO.setDeptCode(guardianBelongerVO.getDeptCode());
                agendaDO.setDeptName(guardianBelongerVO.getDeptName());
                agendaDO.setDate(new Date());
                statisticsManager.saveBacklogStatistics(agendaDO);
            }
        }
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
	private void saveRemind(PactRentTerminationParam param, String key, List<String> variableList) {
        String content = remindMap.get(key);
        for (int i = 0; i < variableList.size(); i++) {
            content = content.replace(StringConsts.VARIABLE + i, variableList.get(i));
        }
        CommonBelongerQuery commonBelongerQuery = new CommonBelongerQuery();
        commonBelongerQuery.setPactCode(param.getDissolutionCode());
        commonBelongerQuery.setUserRole(StringConsts.BELONGER_PERSON);
        CommonBelongerVO commonBelongerVO = commonBelongerManager.getBelongersByParam(commonBelongerQuery);
        Map<String, Object> map = new HashMap<>();
        map.put(NormalConstant.REMIND_TITLE, StringConsts.REMIND_AUDITING_TITLE);
        map.put(NormalConstant.REMIND_PIN, commonBelongerVO.getUserPin());
        map.put(NormalConstant.REMIND_SERVICETYPE, SerializeTypeConts.RTERMINA);
        map.put(NormalConstant.REMIND_SERVICECODE, param.getDissolutionCode());
        map.put(NormalConstant.REMIND_REMINDCONTENT,content);
        remindRpc.saveRemind(map);
    }
	/**
     * @Title: getPermissions
     * @Description: TODO( 查看登录人有没有这个托出解约协议的权限 )
     * @author GuoXiaoPeng
     * @param rentBaseParam托出解约协议编码和权限作用域
     * @return 有权限返回true，没有权限返回false
      */
    private boolean getPermissions(PactRentTerminationParam param) {
        boolean flag;
        ApiResult<PersonalResult> personalApiResult = personalRpc.getPersonalResultBypin(param.getCreateName());
        PersonalResult loginPersonal = personalApiResult.getData();
        Integer scope = param.getScope();
        PactRentTerminationQuery query = new PactRentTerminationQuery();
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
        query.setDissolutionCode(param.getDissolutionCode());
        flag = pactRentTerminationManager.getPermissions(query);
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
    public PactRentTerminationManager getPactRentTerminationManager() {
		return pactRentTerminationManager;
	}

	public void setPactRentTerminationManager(
			PactRentTerminationManager pactRentTerminationManager) {
		this.pactRentTerminationManager = pactRentTerminationManager;
	}

	public RentRoomManager getRentRoomManager() {
		return rentRoomManager;
	}

	public void setRentRoomManager(RentRoomManager rentRoomManager) {
		this.rentRoomManager = rentRoomManager;
	}


	public CommonBelongerManager getCommonBelongerManager() {
		return commonBelongerManager;
	}


	public void setCommonBelongerManager(CommonBelongerManager commonBelongerManager) {
		this.commonBelongerManager = commonBelongerManager;
	}


	public RentBaseManager getRentBaseManager() {
		return rentBaseManager;
	}


	public void setRentBaseManager(RentBaseManager rentBaseManager) {
		this.rentBaseManager = rentBaseManager;
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


    public SerializeRpc getSerializeRpc() {
        return serializeRpc;
    }


    public void setSerializeRpc(SerializeRpc serializeRpc) {
        this.serializeRpc = serializeRpc;
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


    public RentCustomerManager getRentCustomerManager() {
        return rentCustomerManager;
    }


    public void setRentCustomerManager(RentCustomerManager rentCustomerManager) {
        this.rentCustomerManager = rentCustomerManager;
    }
    
}
