package com.young.pact.service.purchasetermination.impl;

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
import com.young.pact.api.domain.param.rest.commongoods.CommonGoodsParam;
import com.young.pact.api.domain.param.rest.commonmeterread.CommonMeterReadParam;
import com.young.pact.api.domain.param.rest.purchasetermination.PurchaseTerminationParam;
import com.young.pact.api.domain.param.rest.purchasetermination.PurchaseTerminationQueryParam;
import com.young.pact.api.domain.result.rest.commonpic.CommonPicResult;
import com.young.pact.api.domain.result.rest.financereceiptpay.FinanceReceiptPayResult;
import com.young.pact.api.domain.result.rest.purchasetermination.PurchaseTerminationBaseResult;
import com.young.pact.api.domain.result.rest.purchasetermination.PurchaseTerminationEditResult;
import com.young.pact.api.domain.result.rest.purchasetermination.PurchaseTerminationListResult;
import com.young.pact.api.service.rest.purchasetermination.PurchaseTerminationRestService;
import com.young.pact.common.constant.NormalConstant;
import com.young.pact.common.constant.PactTypeConsts;
import com.young.pact.common.constant.SerializeTypeConts;
import com.young.pact.common.constant.StringConsts;
import com.young.pact.common.dict.PactCommonEnum;
import com.young.pact.common.exception.PactManagerExcepion;
import com.young.pact.domain.commonbelonger.CommonBelongerDO;
import com.young.pact.domain.commonbelonger.CommonBelongerQuery;
import com.young.pact.domain.commonbelonger.CommonBelongerVO;
import com.young.pact.domain.commongoods.CommonGoodsDO;
import com.young.pact.domain.commongoods.CommonGoodsPicDO;
import com.young.pact.domain.commonmeterread.CommonMeterReadDO;
import com.young.pact.domain.commonmeterread.CommonMeterReadPicDO;
import com.young.pact.domain.commonpic.CommonPicDO;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayDO;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayQuery;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayVO;
import com.young.pact.domain.purchasebase.PurchaseBaseVO;
import com.young.pact.domain.purchasehouse.PurchaseHouseOwnerVO;
import com.young.pact.domain.purchasehouse.PurchaseHouseVO;
import com.young.pact.domain.purchasetermination.PurchaseTerminationBaseVO;
import com.young.pact.domain.purchasetermination.PurchaseTerminationDO;
import com.young.pact.domain.purchasetermination.PurchaseTerminationEditVO;
import com.young.pact.domain.purchasetermination.PurchaseTerminationQuery;
import com.young.pact.domain.purchasetermination.PurchaseTerminationVO;
import com.young.pact.domain.statistics.AgendaDO;
import com.young.pact.manager.commonbelonger.CommonBelongerManager;
import com.young.pact.manager.financereceiptpay.FinanceReceiptPayManager;
import com.young.pact.manager.purchasebase.PurchaseBaseManager;
import com.young.pact.manager.purchasehouse.PurchaseHouseManager;
import com.young.pact.manager.purchasehouse.PurchaseHouseOwnerManager;
import com.young.pact.manager.purchasetermination.PurchaseTerminationManager;
import com.young.pact.manager.statistics.StatisticsManager;
import com.young.pact.rpc.dept.DeptRpc;
import com.young.pact.rpc.operatelog.OperateLogRpc;
import com.young.pact.rpc.personal.PersonalRpc;
import com.young.pact.rpc.remind.RemindRpc;
import com.young.pact.rpc.room.RoomRpc;
import com.young.pact.rpc.serialize.SerializeRpc;
import com.young.product.api.domain.param.rpc.room.RoomParam;
import com.young.sso.api.domain.result.rpc.personal.PersonalResult;

/**
 * 
* @ClassName: PurchaseTerminationRestServiceImpl
* @Description: 托进解约协议
* @author SunYiXuan
* @date 2018年8月9日 下午2:57:39
*
 */
@Service("purchaseTerminationRestService")
public class PurchaseTerminationRestServiceImpl implements PurchaseTerminationRestService{

    private static final Log LOG = LogFactory.getLog(PurchaseTerminationRestServiceImpl.class);
    private PurchaseBaseManager purchaseBaseManager;
    private PurchaseTerminationManager purchaseTerminationManager;
    private PurchaseHouseOwnerManager purchaseHouseOwnerManager;
    private PurchaseHouseManager purchaseHouseManager;
    private CommonBelongerManager commonBelongerManager;
    private RoomRpc roomRpc;
    private SerializeRpc serializeRpc;
    private PersonalRpc personalRpc;
    private OperateLogRpc operateLogRpc;
    private DeptRpc deptRpc;
    private RemindRpc remindRpc;
    private Map<String,String> remindMap;
    private StatisticsManager statisticsManager;
    private FinanceReceiptPayManager financeReceiptPayManager;
    @Override
    public ApiResult<Boolean> savePurchaseTermination(PurchaseTerminationParam param) {
        ApiResult<Boolean> apiResult = new ApiResult<Boolean>();
        int count = 0;
        String terminationCode = "";
        if(null == param || StringUtils.isBlank(param.getPurchaseCode())){
            apiResult.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            apiResult.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return apiResult;
        }
        try{
            //根据合同编码判断合同的状态是不是已审核状态
            PurchaseBaseVO purchaseBase = purchaseBaseManager.getPurchaseBase(param.getPurchaseCode());
            if(purchaseBase.getState() == 3){//合同已审核]
                RoomParam roomParam = new RoomParam();
                roomParam.setPactCode(param.getPurchaseCode());
                roomParam.setStockState(1);
                count = roomRpc.countRoomStateByCode(roomParam);
                //判断房间是否是未租
                if(count <= 0){//房屋是未租状态
                    //判断该合同下是否已经存在解约协议
                    int purchaseTerminatiorCount = purchaseTerminationManager.countByPurchaseCode(param.getPurchaseCode());
                    if(purchaseTerminatiorCount <= 0){//该合同下没有解约协议
                        //托进解约协议编码
                        terminationCode = serializeRpc.queryCodeBySerializeType(SerializeTypeConts.RTERMINA);
                        String pin = param.getCreateName();
                        String ip = param.getIp();
                        if(StringUtils.isNotBlank(terminationCode)){
                            
                            
                            //解约协议信息
                            PurchaseTerminationDO purchaseTerminationDO = new PurchaseTerminationDO();
                            BeanUtils.copyProperties(param, purchaseTerminationDO);
                            purchaseTerminationDO.setTerminationCode(terminationCode);
                            
                            //收支
                            List<FinanceReceiptPayDO> financeReceiptPayList = new ArrayList<FinanceReceiptPayDO>();
                            BeanUtils.copyListProperties(param.getFinanceReceiptPayList(), financeReceiptPayList, FinanceReceiptPayDO.class);
                            for(FinanceReceiptPayDO financeReceiptPayDO:financeReceiptPayList){
                                financeReceiptPayDO.setPactCode(terminationCode);
                                financeReceiptPayDO.setPactType(PactTypeConsts.PURCHASE_TERMINATION);
                                financeReceiptPayDO.setCreateName(pin);
                                financeReceiptPayDO.setIp(ip);
                                if(null != financeReceiptPayDO.getType() && 0 == financeReceiptPayDO.getType()){// 应收
                                    financeReceiptPayDO.setCostState(1);// 待收款
                                }else if(null != financeReceiptPayDO.getType() && 2 == financeReceiptPayDO.getType()){// 应支
                                    financeReceiptPayDO.setCostState(4);// 待付款
                                    // 收款人是业主
                                    if(StringConsts.PAYEE_OBJECT_OWNER.equals(financeReceiptPayDO.getPayeeObject())){
                                        PurchaseHouseOwnerVO purchaseHouseOwnerVO = purchaseHouseOwnerManager.getPurchaseHouseOwner(param.getPurchaseCode());
                                        if(null != purchaseHouseOwnerVO){
                                            financeReceiptPayDO.setName(purchaseHouseOwnerVO.getPayeeName());
                                            financeReceiptPayDO.setTel(purchaseHouseOwnerVO.getPayeeTel());
                                            financeReceiptPayDO.setAccount(purchaseHouseOwnerVO.getPayeeAccount());
                                            financeReceiptPayDO.setBank(purchaseHouseOwnerVO.getPayeeBank());
                                            financeReceiptPayDO.setOpenBank(purchaseHouseOwnerVO.getPayeeOpeningBank());
                                        }
                                        
                                    }
                                }
                                PurchaseHouseVO purchaseHouseVO = purchaseHouseManager.getPurchaseHouse(param.getPurchaseCode());
                                if(null != purchaseHouseVO){
                                    financeReceiptPayDO.setHouseCode(purchaseHouseVO.getHouseCode());
                                    financeReceiptPayDO.setAddress(purchaseHouseVO.getAddress());
                                }
                             }
                            
                            //合同图片
                            List<CommonPicDO> commonPicList = new ArrayList<CommonPicDO>();
                            BeanUtils.copyListProperties(param.getCommonPicList(), commonPicList, CommonPicDO.class);
                            if(null != commonPicList){
                                for (CommonPicDO commonPicDO : commonPicList) {
                                    commonPicDO.setIp(ip);
                                    commonPicDO.setCreateName(pin);
                                    commonPicDO.setPactCode(terminationCode);
                                    commonPicDO.setPactType(PactTypeConsts.PURCHASE_TERMINATION);
                                }
                            }
                            
                            //合同抄表
                            List<CommonMeterReadDO> commonMeterReadList = new ArrayList<CommonMeterReadDO>();
                            if(null!=param.getCommonMeterReadList()){
                                List<CommonMeterReadParam> meterParamList= param.getCommonMeterReadList();
                                BeanUtils.copyListProperties(meterParamList, commonMeterReadList, CommonMeterReadDO.class);
                                for(int i=0;i<meterParamList.size();i++){
                                    commonMeterReadList.get(i).setIp(ip);
                                    commonMeterReadList.get(i).setCreateName(pin);
                                    commonMeterReadList.get(i).setPactCode(terminationCode);
                                    commonMeterReadList.get(i).setPactType(PactTypeConsts.PURCHASE_TERMINATION);
                                    if(null!=meterParamList.get(i).getCommonMeterReadPicList()){
                                        List<CommonMeterReadPicDO> commonMeterReadPicList = new ArrayList<CommonMeterReadPicDO>();
                                        BeanUtils.copyListProperties(meterParamList.get(i).getCommonMeterReadPicList(), commonMeterReadPicList, CommonMeterReadPicDO.class);
                                        for(CommonMeterReadPicDO commonMeterReadPicDO:commonMeterReadPicList){
                                            commonMeterReadPicDO.setIp(ip);
                                            commonMeterReadPicDO.setCreateName(pin);
                                        }
                                        commonMeterReadList.get(i).setCommonMeterReadPicList(commonMeterReadPicList);
                                    }
                                }
                            }
                            
                            //物品
                            List<CommonGoodsDO> commonGoodsList = new ArrayList<CommonGoodsDO>();
                            if(null!=param.getCommonGoodsList()){
                                List<CommonGoodsParam> goodsParamList = param.getCommonGoodsList();
                                BeanUtils.copyListProperties(goodsParamList, commonGoodsList, CommonGoodsDO.class);
                                for(int i=0;i<goodsParamList.size();i++){
                                    commonGoodsList.get(i).setIp(ip);
                                    commonGoodsList.get(i).setCreateName(pin);
                                    commonGoodsList.get(i).setPactCode(terminationCode);
                                    commonGoodsList.get(i).setPactType(PactTypeConsts.PURCHASE_TERMINATION);
                                    if(null!=goodsParamList.get(i).getCommonGoodsPicList()){
                                        List<CommonGoodsPicDO> commonGoodsPicList = new ArrayList<CommonGoodsPicDO>();
                                        BeanUtils.copyListProperties(goodsParamList.get(i).getCommonGoodsPicList(), commonGoodsPicList, CommonGoodsPicDO.class);
                                        for(CommonGoodsPicDO commonGoodsPicDO:commonGoodsPicList){
                                            commonGoodsPicDO.setIp(ip);
                                            commonGoodsPicDO.setCreateName(pin);
                                        }
                                        commonGoodsList.get(i).setCommonGoodsPicList(commonGoodsPicList);
                                    }
                                }
                            }
                            
                            //负责人
                            List<CommonBelongerDO> commonBelongerDOList = new ArrayList<>();
                            //通过pin获取管家信息
                            ApiResult<PersonalResult> personalResult = personalRpc.getPersonalResultBypin(param.getUserPin());
                            PersonalResult personalData = personalResult.getData();
                            CommonBelongerDO commonBelongerDO = new CommonBelongerDO();
                            commonBelongerDO.setPactCode(terminationCode);
                            commonBelongerDO.setPactType(PactTypeConsts.PURCHASE_TERMINATION);
                            commonBelongerDO.setUserPin(param.getUserPin());
                            commonBelongerDO.setUserName(personalData.getEmployeeName());
                            commonBelongerDO.setUserTel(personalData.getEmployeeTel());
                            commonBelongerDO.setUserRole(StringConsts.BELONGER_DEAL);
                            commonBelongerDO.setDeptCode(personalData.getDeptCode());
                            commonBelongerDO.setDeptName(personalData.getDeptName());
                            commonBelongerDO.setIp(ip);
                            commonBelongerDO.setCreateName(pin);
                            commonBelongerDOList.add(commonBelongerDO);
                            //维护人
                            commonBelongerDO = new CommonBelongerDO();
                            commonBelongerDO.setPactCode(terminationCode);
                            commonBelongerDO.setPactType(PactTypeConsts.PURCHASE_TERMINATION);
                            commonBelongerDO.setUserPin(param.getUserPin());
                            commonBelongerDO.setUserName(personalData.getEmployeeName());
                            commonBelongerDO.setUserTel(personalData.getEmployeeTel());
                            commonBelongerDO.setUserRole(StringConsts.BELONGER_PERSON);
                            commonBelongerDO.setDeptCode(personalData.getDeptCode());
                            commonBelongerDO.setDeptName(personalData.getDeptName());
                            commonBelongerDO.setIp(ip);
                            commonBelongerDO.setCreateName(pin);
                            commonBelongerDOList.add(commonBelongerDO);
                            
                            Boolean flag = purchaseTerminationManager.savePurchaseTermination(purchaseTerminationDO,financeReceiptPayList,commonPicList,commonMeterReadList,commonGoodsList,commonBelongerDOList);
                            if(flag){
                                Map<String, Object> map = new HashMap<String, Object>();
                                map.put(NormalConstant.SERVICETYPE, SerializeTypeConts.PTERMINA);
                                map.put(NormalConstant.SERVICECODE, purchaseTerminationDO.getTerminationCode());
                                map.put(NormalConstant.OPERATETYPE, StringConsts.ADD);
                                map.put(NormalConstant.OPERATEPIN, purchaseTerminationDO.getCreateName());
                                map.put(NormalConstant.OPERATECONTENT, StringConsts.ADD_DATA);
                                operateLogRpc.saveOperateLog(map);
                                apiResult.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                                apiResult.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                                apiResult.setData(flag);
                            }else{
                                apiResult.setCode(CommonEnum.REQUEST_FAIL.getCode());
                                apiResult.setMessage(CommonEnum.REQUEST_FAIL.getMessage());
                            }
                        }else{// 序列化失败
                            apiResult.setCode(PactCommonEnum.SYSTEM_SERIALIZE_FAIL.getCode());
                            apiResult.setMessage(PactCommonEnum.SYSTEM_SERIALIZE_FAIL.getMessage());
                        }
                    }else{//该合同下已存在解约协议
                        apiResult.setCode(PactCommonEnum.PURCHASE_TERMINATION_IS_EXIST.getCode());
                        apiResult.setMessage(PactCommonEnum.PURCHASE_TERMINATION_IS_EXIST.getMessage());
                    }
                }else{//房屋不是未租状态
                    apiResult.setCode(PactCommonEnum.PURCHASE_TERMINATION_RENTED_ERROR.getCode());
                    apiResult.setMessage(PactCommonEnum.PURCHASE_TERMINATION_RENTED_ERROR.getMessage());
                }
            }else{//合同未审核
                apiResult.setCode(PactCommonEnum.PURCHASE_STATE_NOTIS_3.getCode());
                apiResult.setMessage(PactCommonEnum.PURCHASE_STATE_NOTIS_3.getMessage());
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
    public ApiResult<PageBean<PurchaseTerminationListResult>> listPurchaseTermination(PurchaseTerminationQueryParam param) {
        ApiResult<PageBean<PurchaseTerminationListResult>> apiResult = new ApiResult<PageBean<PurchaseTerminationListResult>>();
        if(null == param){
            apiResult.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            apiResult.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return apiResult;
        }
        try {
            ApiResult<PersonalResult> personalApiResult = personalRpc.getPersonalResultBypin(param.getCreateName());
            PersonalResult personalResult = personalApiResult.getData();
            PurchaseTerminationQuery query = new PurchaseTerminationQuery();
            BeanUtils.copyProperties(param, query);
            Integer scope = param.getScope();
            if(1 == scope){// 1=本人
                query.setUserPin(param.getCreateName());
            }else if(2 == scope){// 2=本部门
                List<String> deptList = new ArrayList<>();
                deptList.add(personalResult.getDeptCode());
                query.setDeptList(deptList);
            }else if(3 == scope){//3=本人及下属
                List<String> pinList = personalRpc.listLowerByPin(param.getCreateName());
                query.setPinList(pinList);
            }else if(4 == scope){//4=本部门及下属部门
                List<String> deptList = deptRpc.listCodeByDeptCode(personalResult.getDeptCode());
                query.setDeptList(deptList);
            }
            PageBean<PurchaseTerminationVO> terminationList = purchaseTerminationManager.listPurchaseTermination(query);
            if(terminationList.getTotalItem() > 0){
                PageBean<PurchaseTerminationListResult> resultList = new PageTableBean<PurchaseTerminationListResult>(query.getPageIndex(),query.getPageSize());
                resultList.setTotalItem(terminationList.getTotalItem());
                BeanUtils.copyListProperties(terminationList.getData(), resultList.getData(), PurchaseTerminationListResult.class);
                apiResult.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                apiResult.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                apiResult.setData(resultList);
            }else{
                apiResult.setCode(CommonEnum.NO_CONTENT.getCode());
                apiResult.setMessage(CommonEnum.NO_CONTENT.getMessage());
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
    public ApiResult<PurchaseTerminationBaseResult> getPurchaseTerminationBase(PurchaseTerminationQueryParam param) {
        ApiResult<PurchaseTerminationBaseResult> apiResult = new ApiResult<PurchaseTerminationBaseResult>();
        String terminationCode = param.getTerminationCode();
        if(null == terminationCode){
            apiResult.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            apiResult.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return apiResult;
        }
        try {
            boolean permissionFlag = getPermission(param);
            if(!permissionFlag){
                apiResult.setCode(PactCommonEnum.PURCHASETERMINATION_NO_PERMISSIONS.getCode());
                apiResult.setMessage(PactCommonEnum.PURCHASETERMINATION_NO_PERMISSIONS.getMessage());
                return apiResult;
            }
            PurchaseTerminationBaseVO terminationBaseVO = purchaseTerminationManager.getPurchaseTerminationBase(terminationCode);
            if(null != terminationBaseVO){
                PurchaseTerminationBaseResult terminationBaseResult = new PurchaseTerminationBaseResult();
                BeanUtils.copyProperties(terminationBaseVO, terminationBaseResult);
                List<CommonPicResult> picResultList = new ArrayList<CommonPicResult>(); 
                BeanUtils.copyListProperties(terminationBaseVO.getCommonPicList(), picResultList, CommonPicResult.class);
                terminationBaseResult.setCommonPicList(picResultList);
                apiResult.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                apiResult.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                apiResult.setData(terminationBaseResult);
            }else{// 解约协议编码无效
                apiResult.setCode(PactCommonEnum.PURCHASE_TERMINATION_CODE_INVALID.getCode());
                apiResult.setMessage(PactCommonEnum.PURCHASE_TERMINATION_CODE_INVALID.getMessage());
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
    * @Title: getPermission
    * @Description: TODO( 查看登录人有没有这个托进合同解约协议的权限 )
    * @author GuoXiaoPeng
    * @param param 数据权限作用域和托进合同解约协议编码
    * @return 有权限返回true，否则返回false
     */
    private boolean getPermission(PurchaseTerminationQueryParam param) {
        boolean flag;
        PurchaseTerminationQuery query = new PurchaseTerminationQuery();
        query.setTerminationCode(param.getTerminationCode());
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
        flag = purchaseTerminationManager.getPermissions(query);
        return flag;
    }


    @Override
    public ApiResult<PurchaseTerminationEditResult> getPurchaseTerminationEdit(String terminationCode) {
        ApiResult<PurchaseTerminationEditResult> apiResult = new ApiResult<PurchaseTerminationEditResult>();
        if(null == terminationCode){
            apiResult.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            apiResult.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return apiResult;
        }
        try {
            PurchaseTerminationEditVO terminationEditVO = purchaseTerminationManager.getPurchaseTerminationEdit(terminationCode);
            if(null != terminationEditVO){
                PurchaseTerminationEditResult terminationEditResult = new PurchaseTerminationEditResult();
                BeanUtils.copyProperties(terminationEditVO, terminationEditResult);
                List<FinanceReceiptPayResult> financeReceiptPayResultList = new ArrayList<FinanceReceiptPayResult>();
                BeanUtils.copyListProperties(terminationEditVO.getFinanceReceiptPayList(), financeReceiptPayResultList, FinanceReceiptPayResult.class);
                terminationEditResult.setFinanceReceiptPayList(financeReceiptPayResultList);
                List<CommonPicResult> commonPicResultList = new ArrayList<CommonPicResult>();
                BeanUtils.copyListProperties(terminationEditVO.getCommonPicList(),commonPicResultList,CommonPicResult.class);
                terminationEditResult.setCommonPicList(commonPicResultList);
                apiResult.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                apiResult.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                apiResult.setData(terminationEditResult);
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
    public ApiResult<Boolean> updatePurchaseTermination(PurchaseTerminationParam param) {
        ApiResult<Boolean> apiResult = new ApiResult<Boolean>();
        if(null == param || StringUtils.isBlank(param.getTerminationCode())){
            apiResult.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            apiResult.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return apiResult;
        }
        try {
            PurchaseTerminationVO terminationVO = purchaseTerminationManager.getPurchaseTerminarion(param.getTerminationCode());
            if(null != terminationVO){
                if(1 == terminationVO.getState() || 4 == terminationVO.getState()){//草稿和驳回状态才能修改
                    String ip = param.getIp();
                    String pin = param.getModifiedName();
                    String terminationCode = param.getTerminationCode();
                    
                    
                    //协议信息
                    PurchaseTerminationDO terminationDO = new PurchaseTerminationDO();
                    BeanUtils.copyProperties(param, terminationDO);
                    
                    //收支
                    List<FinanceReceiptPayDO> financeReceiptPayDOList = new ArrayList<FinanceReceiptPayDO>();
                    BeanUtils.copyListProperties(param.getFinanceReceiptPayList(), financeReceiptPayDOList, FinanceReceiptPayDO.class);
                    for(FinanceReceiptPayDO financeReceiptPayDO : financeReceiptPayDOList){
                        financeReceiptPayDO.setPactCode(terminationCode);
                        financeReceiptPayDO.setPactType(PactTypeConsts.PURCHASE_TERMINATION);
                        financeReceiptPayDO.setCreateName(pin);
                        financeReceiptPayDO.setModifiedName(pin);
                        financeReceiptPayDO.setIp(ip);
                        if(null != financeReceiptPayDO.getType() && 0 == financeReceiptPayDO.getType()){// 应收
                            financeReceiptPayDO.setCostState(1);// 待收款
                        }else if(null != financeReceiptPayDO.getType() && 2 == financeReceiptPayDO.getType()){//应支
                            financeReceiptPayDO.setCostState(4);// 待付款
                            // 收款人是业主
                            if(StringConsts.PAYEE_OBJECT_OWNER.equals(financeReceiptPayDO.getPayeeObject())){
                                PurchaseHouseOwnerVO purchaseHouseOwner = purchaseHouseOwnerManager.getPurchaseHouseOwner(terminationVO.getPurchaseCode());
                                if(null != purchaseHouseOwner){
                                    financeReceiptPayDO.setName(purchaseHouseOwner.getPayeeName());
                                    financeReceiptPayDO.setTel(purchaseHouseOwner.getPayeeTel());
                                    financeReceiptPayDO.setAccount(purchaseHouseOwner.getPayeeAccount());
                                    financeReceiptPayDO.setBank(purchaseHouseOwner.getPayeeBank());
                                    financeReceiptPayDO.setOpenBank(purchaseHouseOwner.getPayeeOpeningBank());
                                }
                            }
                        }
                        PurchaseHouseVO purchaseHouse = purchaseHouseManager.getPurchaseHouse(terminationVO.getPurchaseCode());
                        if(null != purchaseHouse){
                            financeReceiptPayDO.setHouseCode(purchaseHouse.getHouseCode());
                            financeReceiptPayDO.setAddress(purchaseHouse.getAddress());
                        }
                    }
                    
                    //协议照片
                    List<CommonPicDO> commonPicDOList = new ArrayList<>();
                    BeanUtils.copyListProperties(param.getCommonPicList(), commonPicDOList, CommonPicDO.class);
                    for (CommonPicDO commonPicDO : commonPicDOList) {
                        commonPicDO.setIp(ip);
                        commonPicDO.setCreateName(pin);
                        commonPicDO.setModifiedName(pin);
                        commonPicDO.setPactCode(terminationCode);
                        commonPicDO.setPactType(PactTypeConsts.PURCHASE_TERMINATION);
                    }
                    
                    //责任人
                    //管家信息
                    ApiResult<PersonalResult> personalResult = personalRpc.getPersonalResultBypin(param.getUserPin());
                    PersonalResult personalResultData = personalResult.getData();
                    CommonBelongerDO commonBelongerDO = new CommonBelongerDO();
                    commonBelongerDO.setPactCode(terminationCode);
                    commonBelongerDO.setUserPin(param.getUserPin());
                    commonBelongerDO.setUserName(personalResultData.getEmployeeName());
                    commonBelongerDO.setUserTel(personalResultData.getEmployeeTel());
                    commonBelongerDO.setDeptCode(personalResultData.getDeptCode());
                    commonBelongerDO.setDeptName(personalResultData.getDeptName());
                    commonBelongerDO.setIp(ip);
                    commonBelongerDO.setModifiedName(pin);
                    
                    Boolean flag = purchaseTerminationManager.updateTermination(terminationDO,financeReceiptPayDOList,commonPicDOList,commonBelongerDO);
                    if(flag){
                        //日志
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put(NormalConstant.SERVICETYPE, SerializeTypeConts.PTERMINA);
                        map.put(NormalConstant.SERVICECODE, terminationDO.getTerminationCode());
                        map.put(NormalConstant.OPERATETYPE, StringConsts.UPDATE);
                        map.put(NormalConstant.OPERATEPIN, terminationDO.getModifiedName());
                        map.put(NormalConstant.OPERATECONTENT, StringConsts.UPDATE_DATA);
                        operateLogRpc.saveOperateLog(map);
                        apiResult.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                        apiResult.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                        apiResult.setData(flag);
                    }else{
                        apiResult.setCode(PactCommonEnum.PURCHASE_SAVE_FAIL.getCode());
                        apiResult.setMessage(PactCommonEnum.PURCHASE_SAVE_FAIL.getMessage());
                    }
                }else{//不是草稿或驳回状态
                    apiResult.setCode(PactCommonEnum.PURCHASE_TERMINATION_STATE_UPDATE_ERROR.getCode());
                    apiResult.setMessage(PactCommonEnum.PURCHASE_TERMINATION_STATE_UPDATE_ERROR.getMessage());
                }
            }else{//协议编码无效
                apiResult.setCode(PactCommonEnum.PURCHASE_TERMINATION_CODE_INVALID.getCode());
                apiResult.setMessage(PactCommonEnum.PURCHASE_TERMINATION_CODE_INVALID.getMessage());
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
    public ApiResult<Boolean> removePurchaseTermination(PurchaseTerminationParam param) {
        ApiResult<Boolean> apiResult = new ApiResult<Boolean>();
        if(null == param || StringUtils.isBlank(param.getTerminationCode())){
            apiResult.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            apiResult.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return apiResult;
        }
        try {
            PurchaseTerminationDO terminationDO = new PurchaseTerminationDO();
            BeanUtils.copyProperties(param, terminationDO);
            terminationDO.setIsDelete(1);
            PurchaseTerminationVO terminationVO = purchaseTerminationManager.getPurchaseTerminarion(param.getTerminationCode());
            if(null != terminationVO){
                if(1==terminationVO.getState() || 4==terminationVO.getState()){//草稿或驳回状态
                    Boolean flag = purchaseTerminationManager.removePurchaseTermination(terminationDO);
                    if(flag){
                        apiResult.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                        apiResult.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                        apiResult.setData(flag);
                        //日志.....................rpc
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put(NormalConstant.SERVICETYPE, SerializeTypeConts.PTERMINA);
                        map.put(NormalConstant.SERVICECODE, terminationDO.getTerminationCode());
                        map.put(NormalConstant.OPERATETYPE, StringConsts.DELETE);
                        map.put(NormalConstant.OPERATEPIN, terminationDO.getModifiedName());
                        map.put(NormalConstant.OPERATECONTENT, StringConsts.DELETE_DATA);
                        operateLogRpc.saveOperateLog(map);
                    }else{
                        apiResult.setCode(PactCommonEnum.PURCHASE_SAVE_FAIL.getCode());
                        apiResult.setMessage(PactCommonEnum.PURCHASE_SAVE_FAIL.getMessage());
                    }
                }else{
                    apiResult.setCode(PactCommonEnum.PURCHASE_TERMINATION_STATE_REMOVE_ERROR.getCode());
                    apiResult.setMessage(PactCommonEnum.PURCHASE_TERMINATION_STATE_REMOVE_ERROR.getMessage());
                }
            }else{//编码无效
                apiResult.setCode(PactCommonEnum.PURCHASE_TERMINATION_CODE_INVALID.getCode());
                apiResult.setMessage(PactCommonEnum.PURCHASE_TERMINATION_CODE_INVALID.getMessage());
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
    public ApiResult<Boolean> purchaseTerminationApply(PurchaseTerminationParam param) {
        ApiResult<Boolean> apiResult = new ApiResult<Boolean>();
        if(null == param || StringUtils.isBlank(param.getTerminationCode())){
            apiResult.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            apiResult.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return apiResult;
        }
        try {
            PurchaseTerminationDO terminationDO = new PurchaseTerminationDO();
            BeanUtils.copyProperties(param, terminationDO);
            terminationDO.setState(2);
            PurchaseTerminationVO terminationVO = purchaseTerminationManager.getPurchaseTerminarion(param.getTerminationCode());
            if(null != terminationVO){
                if(1==terminationVO.getState() || 4==terminationVO.getState()){//草稿或驳回状态
                    Boolean flag = purchaseTerminationManager.updateState(terminationDO);
                    if(flag){
                        apiResult.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                        apiResult.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                        apiResult.setData(flag);
                        //日志
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put(NormalConstant.SERVICETYPE, SerializeTypeConts.PTERMINA);
                        map.put(NormalConstant.SERVICECODE, terminationDO.getTerminationCode());
                        map.put(NormalConstant.OPERATETYPE, StringConsts.OPERATETYPE_APPLICATION_AUDIT);
                        map.put(NormalConstant.OPERATEPIN, terminationDO.getModifiedName());
                        map.put(NormalConstant.OPERATECONTENT, StringConsts.AUDIT_DATA);
                        operateLogRpc.saveOperateLog(map);
                        /*************************首页待办数据统计  start*****************************/
                        CommonBelongerQuery commonBelongerQuery = new CommonBelongerQuery();
                        commonBelongerQuery.setPactCode(param.getTerminationCode());
                        commonBelongerQuery.setUserRole(StringConsts.BELONGER_DEAL);
                        CommonBelongerVO commonBelongerVO = commonBelongerManager.getBelongersByParam(commonBelongerQuery);
                        AgendaDO agendaDO = new AgendaDO();
                        agendaDO.setServiceCode(param.getTerminationCode());
                        agendaDO.setType(StringConsts.STATISTIC_PENDING_PURCHBASE_TERMINATION);
                        agendaDO.setIsDo(0);
                        agendaDO.setPin(commonBelongerVO.getUserPin());
                        agendaDO.setName(commonBelongerVO.getUserName());
                        agendaDO.setDeptCode(commonBelongerVO.getDeptCode());
                        agendaDO.setDeptName(commonBelongerVO.getDeptName());
                        agendaDO.setDate(new Date());
                        statisticsManager.saveBacklogStatistics(agendaDO);
                        /*************************首页待办数据统计  end*****************************/
                    }else{
                        apiResult.setCode(PactCommonEnum.PURCHASE_SAVE_FAIL.getCode());
                        apiResult.setMessage(PactCommonEnum.PURCHASE_SAVE_FAIL.getMessage());
                    }
                }else{
                    apiResult.setCode(PactCommonEnum.PURCHASE_TERMINATION_STATE_UPDATE_ERROR.getCode());
                    apiResult.setMessage(PactCommonEnum.PURCHASE_TERMINATION_STATE_UPDATE_ERROR.getMessage());
                }
            }else{//编码无效
                apiResult.setCode(PactCommonEnum.PURCHASE_TERMINATION_CODE_INVALID.getCode());
                apiResult.setMessage(PactCommonEnum.PURCHASE_TERMINATION_CODE_INVALID.getMessage());
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
    public ApiResult<Boolean> purchaseTerminationReject(PurchaseTerminationParam param) {
        ApiResult<Boolean> apiResult = new ApiResult<Boolean>();
        if(null == param || StringUtils.isBlank(param.getTerminationCode())){
            apiResult.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            apiResult.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return apiResult;
        }
        try {
            PurchaseTerminationDO terminationDO = new PurchaseTerminationDO();
            BeanUtils.copyProperties(param, terminationDO);
            terminationDO.setState(4);
            PurchaseTerminationVO terminationVO = purchaseTerminationManager.getPurchaseTerminarion(param.getTerminationCode());
            if(null != terminationVO){
                if(2 == terminationVO.getState()){//待审核状态
                    Boolean flag = purchaseTerminationManager.updateState(terminationDO);
                    if(flag){
                        List<String> variableList = new ArrayList<>();
                        variableList.add(param.getTerminationCode());
                        saveRemind(param,StringConsts.PURCHASETERMINATION_REJECT,variableList);
                        //日志
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put(NormalConstant.SERVICETYPE, SerializeTypeConts.PTERMINA);
                        map.put(NormalConstant.SERVICECODE, terminationDO.getTerminationCode());
                        map.put(NormalConstant.OPERATETYPE, StringConsts.OPERATETYPE_REVIEW_DISMISSAL);
                        map.put(NormalConstant.OPERATEPIN, terminationDO.getModifiedName());
                        map.put(NormalConstant.OPERATECONTENT, param.getReason());
                        operateLogRpc.saveOperateLog(map);
                        /******************** 修改待办事务统计表的状态  start *******************************/
                        AgendaDO agendaDO = new AgendaDO();
                        agendaDO.setServiceCode(param.getTerminationCode());
                        agendaDO.setIsDo(1);
                        agendaDO.setType(StringConsts.STATISTIC_PENDING_PURCHBASE_TERMINATION);
                        agendaDO.setModifiedName(param.getModifiedName());
                        agendaDO.setIp(param.getIp());
                        statisticsManager.updatBacklogStatisticsIsDo(agendaDO);
                        /******************** 修改待办事务统计表的状态  end *******************************/
                        apiResult.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                        apiResult.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                        apiResult.setData(flag);
                    }else{
                        apiResult.setCode(PactCommonEnum.PURCHASE_SAVE_FAIL.getCode());
                        apiResult.setMessage(PactCommonEnum.PURCHASE_SAVE_FAIL.getMessage());
                    }
                    
                }else{//不是待审核状态
                    apiResult.setCode(PactCommonEnum.PURCHASE_TERMINATION_AUDIT_ERROR.getCode());
                    apiResult.setMessage(PactCommonEnum.PURCHASE_TERMINATION_AUDIT_ERROR.getMessage());
                }
            }else{//编码无效
                apiResult.setCode(PactCommonEnum.PURCHASE_TERMINATION_CODE_INVALID.getCode());
                apiResult.setMessage(PactCommonEnum.PURCHASE_TERMINATION_CODE_INVALID.getMessage());
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
    public ApiResult<Boolean> purchaseTerminationReview(PurchaseTerminationParam param) {
        ApiResult<Boolean> apiResult = new ApiResult<Boolean>();
        if(null == param || StringUtils.isBlank(param.getTerminationCode())){
            apiResult.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            apiResult.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return apiResult;
        }
        try {
            PurchaseTerminationDO terminationDO = new PurchaseTerminationDO();
            BeanUtils.copyProperties(param, terminationDO);
            terminationDO.setState(3);
            PurchaseTerminationVO terminationVO = purchaseTerminationManager.getPurchaseTerminarion(param.getTerminationCode());
            if(null != terminationVO){
                if(2 == terminationVO.getState()){//待审核状态
                    Boolean flag = purchaseTerminationManager.updateState(terminationDO);
                    if(flag){
                        /*---------托进解约审核通过操作日志----start------*/
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put(NormalConstant.SERVICETYPE, SerializeTypeConts.PTERMINA);
                        map.put(NormalConstant.SERVICECODE, terminationDO.getTerminationCode());
                        map.put(NormalConstant.OPERATETYPE, StringConsts.OPERATETYPE_REVIEW_PASS);
                        map.put(NormalConstant.OPERATEPIN, terminationDO.getModifiedName());
                        map.put(NormalConstant.OPERATECONTENT, StringConsts.REVIEWPASS_DATA);
                        operateLogRpc.saveOperateLog(map);
                        /*---------托进解约审核通过操作日志----end------*/
                        List<String> variableList = new ArrayList<>();
                        variableList.add(param.getTerminationCode());
                        saveRemind(param,StringConsts.PURCHASETERMINATION_REVIEW,variableList);
                        /******************** 修改待办事务统计表的状态  start *******************************/
                        AgendaDO agendaDO = new AgendaDO();
                        agendaDO.setServiceCode(param.getTerminationCode());
                        agendaDO.setIsDo(1);
                        agendaDO.setType(StringConsts.STATISTIC_PENDING_PURCHBASE_TERMINATION);
                        agendaDO.setModifiedName(param.getModifiedName());
                        agendaDO.setIp(param.getIp());
                        statisticsManager.updatBacklogStatisticsIsDo(agendaDO);
                        /******************** 修改待办事务统计表的状态  end *******************************/
                        CommonBelongerQuery guardianBelongerQuery = new CommonBelongerQuery();
                        guardianBelongerQuery.setPactCode(param.getPurchaseCode());
                        guardianBelongerQuery.setUserRole(StringConsts.BELONGER_PERSON);
                        CommonBelongerVO guardianBelongerVO = commonBelongerManager.getBelongersByParam(guardianBelongerQuery);
                        saveReceivablesStatistics(param ,guardianBelongerVO);
                        saveObligationStatistics(param ,guardianBelongerVO);
                        apiResult.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                        apiResult.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                        apiResult.setData(flag);
                    }else{
                        apiResult.setCode(CommonEnum.REQUEST_FAIL.getCode());
                        apiResult.setMessage(CommonEnum.REQUEST_FAIL.getMessage());
                    }
                }else{//不是待审核状态
                    apiResult.setCode(PactCommonEnum.PURCHASE_TERMINATION_AUDIT_ERROR.getCode());
                    apiResult.setMessage(PactCommonEnum.PURCHASE_TERMINATION_AUDIT_ERROR.getMessage());
                }
            }else{//编码无效
                apiResult.setCode(PactCommonEnum.PURCHASE_TERMINATION_CODE_INVALID.getCode());
                apiResult.setMessage(PactCommonEnum.PURCHASE_TERMINATION_CODE_INVALID.getMessage());
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
    * @param param  托进解约协议编码
     * @param afterRentBelongerVO 维护人
    * @throws 异常
     */
    private void saveObligationStatistics(PurchaseTerminationParam param, CommonBelongerVO guardianBelongerVO) {
        FinanceReceiptPayQuery financeReceiptPayQuery = new FinanceReceiptPayQuery();
        financeReceiptPayQuery.setIsValid(1);
        financeReceiptPayQuery.setPactCode(param.getTerminationCode());
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
    * @param param 托进解约协议编码
     * @param afterRentBelongerVO 维护人
    * @throws 异常
     */
    private void saveReceivablesStatistics(PurchaseTerminationParam param, CommonBelongerVO guardianBelongerVO) {
        FinanceReceiptPayQuery financeReceiptPayQuery = new FinanceReceiptPayQuery();
        financeReceiptPayQuery.setIsValid(1);
        financeReceiptPayQuery.setPactCode(param.getTerminationCode());
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
     private void saveRemind(PurchaseTerminationParam param, String key, List<String> variableList) {
         String content = remindMap.get(key);
         for (int i = 0; i < variableList.size(); i++) {
             content = content.replace(StringConsts.VARIABLE + i, variableList.get(i));
         }
         CommonBelongerQuery commonBelongerQuery = new CommonBelongerQuery();
         commonBelongerQuery.setPactCode(param.getTerminationCode());
         commonBelongerQuery.setUserRole(StringConsts.BELONGER_PERSON);
         CommonBelongerVO commonBelongerVO = commonBelongerManager.getBelongersByParam(commonBelongerQuery);
         Map<String, Object> map = new HashMap<>();
         map.put(NormalConstant.REMIND_TITLE, StringConsts.REMIND_AUDITING_TITLE);
         map.put(NormalConstant.REMIND_PIN, commonBelongerVO.getUserPin());
         map.put(NormalConstant.REMIND_SERVICETYPE, SerializeTypeConts.PTERMINA);
         map.put(NormalConstant.REMIND_SERVICECODE, param.getTerminationCode());
         map.put(NormalConstant.REMIND_REMINDCONTENT,content);
         remindRpc.saveRemind(map);
     }
    public PurchaseBaseManager getPurchaseBaseManager() {
        return purchaseBaseManager;
    }


    public void setPurchaseBaseManager(PurchaseBaseManager purchaseBaseManager) {
        this.purchaseBaseManager = purchaseBaseManager;
    }



    public PurchaseTerminationManager getPurchaseTerminationManager() {
        return purchaseTerminationManager;
    }


    public void setPurchaseTerminationManager(PurchaseTerminationManager purchaseTerminationManager) {
        this.purchaseTerminationManager = purchaseTerminationManager;
    }


    public PurchaseHouseOwnerManager getPurchaseHouseOwnerManager() {
        return purchaseHouseOwnerManager;
    }

    public void setPurchaseHouseOwnerManager(PurchaseHouseOwnerManager purchaseHouseOwnerManager) {
        this.purchaseHouseOwnerManager = purchaseHouseOwnerManager;
    }

    public PurchaseHouseManager getPurchaseHouseManager() {
        return purchaseHouseManager;
    }

    public void setPurchaseHouseManager(PurchaseHouseManager purchaseHouseManager) {
        this.purchaseHouseManager = purchaseHouseManager;
    }


    public CommonBelongerManager getCommonBelongerManager() {
        return commonBelongerManager;
    }


    public void setCommonBelongerManager(CommonBelongerManager commonBelongerManager) {
        this.commonBelongerManager = commonBelongerManager;
    }


    public RoomRpc getRoomRpc() {
        return roomRpc;
    }


    public void setRoomRpc(RoomRpc roomRpc) {
        this.roomRpc = roomRpc;
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


    public FinanceReceiptPayManager getFinanceReceiptPayManager() {
        return financeReceiptPayManager;
    }


    public void setFinanceReceiptPayManager(FinanceReceiptPayManager financeReceiptPayManager) {
        this.financeReceiptPayManager = financeReceiptPayManager;
    }
    
}
