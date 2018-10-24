package com.young.pact.service.common.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.young.common.dict.CommonEnum;
import com.young.common.domain.ApiResult;
import com.young.common.util.DateUtil;
import com.young.pact.api.domain.param.rest.resourcetransfer.ResourceTransferParam;
import com.young.pact.api.service.rest.common.ResourceTransferService;
import com.young.pact.common.constant.NormalConstant;
import com.young.pact.common.constant.PactTypeConsts;
import com.young.pact.common.constant.SerializeTypeConts;
import com.young.pact.common.constant.StringConsts;
import com.young.pact.common.dict.PactCommonEnum;
import com.young.pact.domain.commonbelonger.CommonBelongerDO;
import com.young.pact.domain.commonbelonger.CommonBelongerQuery;
import com.young.pact.domain.commonbelonger.CommonBelongerVO;
import com.young.pact.manager.commonbelonger.CommonBelongerManager;
import com.young.pact.manager.pactrenttermination.PactRentTerminationManager;
import com.young.pact.manager.pactrenttransfer.PactRentTransferManager;
import com.young.pact.manager.purchasebase.PurchaseBaseManager;
import com.young.pact.manager.purchasetermination.PurchaseTerminationManager;
import com.young.pact.manager.rentbase.RentBaseManager;
import com.young.pact.manager.rentcontinued.RentContinuedManager;
import com.young.pact.manager.rentturn.RentTurnManager;
import com.young.pact.rpc.operatelog.OperateLogRpc;
import com.young.pact.rpc.personal.PersonalRpc;
import com.young.pact.rpc.remind.RemindRpc;
import com.young.product.api.domain.param.rpc.house.HouseParam;
import com.young.sso.api.domain.result.rpc.personal.PersonalResult;

/**
 * @描述 : 资源转移
 * @创建者 : GuoXiaoPeng
 * @创建时间 : 2018年9月4日 下午4:04:51
 */
@Service("resourceTransferService")
public class ResourceTransferServiceImpl implements ResourceTransferService{
    private static final Log LOG = LogFactory.getLog(ResourceTransferServiceImpl.class); 
    private PurchaseBaseManager purchaseBaseManager;
    private PurchaseTerminationManager purchaseTerminationManager;
    private RentBaseManager rentBaseManager;
    private PactRentTerminationManager pactRentTerminationManager;
    private RentContinuedManager rentContinuedManager;
    private RentTurnManager rentTurnManager;
    private PactRentTransferManager pactRentTransferManager;
    private CommonBelongerManager commonBelongerManager;
    private PersonalRpc personalRpc;
    private Map<String,String> remindMap;
    private RemindRpc remindRpc;
    private OperateLogRpc operateLogRpc;
    @Override
    public ApiResult<Boolean> resourceTransfer(ResourceTransferParam param) {
        ApiResult<Boolean> result =new ApiResult<>();
        try {
            if(null == param){
                result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
                result.setCode(PactCommonEnum.PARAM_IS_NULL.getMessage());
                return result;
            }
            Integer type = param.getType();
            if(1 == type){
                //托进合同
                result = transferPurchase(param);
            }else if(2 == type){
                //托进解约
                result = transferPurTermination(param);
            }else if(3 == type){
                //托出合同
                result = transferRentBase(param);
            }else if(4 == type){
                //托出解约
                result = transferRentTermination(param);
            }else if(5 == type){
                //转租
                result = transferRentTurn(param);
            }else if(6 == type){
                //续签
                result = transferRentContinued(param);
            }else if(7 == type){
                //调房
                result = transferRoom(param);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            result.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            result.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        }
        return result;
    }

    /**
    * @Title: transferPurchase
    * @Description: TODO( 资源转移 ---托进合同  )
    * @author GuoXiaoPeng
    * @param param 资源编码集合+资源接收人PIN
    * @return 转移成功true否则false
    * @throws 异常
     */
    @SuppressWarnings("unused")
    private ApiResult<Boolean> transferPurchase(ResourceTransferParam param) {
        ApiResult<Boolean> result = new ApiResult<>(); 
        List<String> resourceCodeList = param.getResourceCodeList();
        List<CommonBelongerDO> addBelongerDOs = new ArrayList<>();
        List<CommonBelongerDO> updateBelongerDOs = new ArrayList<>();
        /*****************资源接收人    start***************************/
        ApiResult<PersonalResult> personalResult = personalRpc.getPersonalResultBypin(param.getPin());
        PersonalResult personal = personalResult.getData();
        /*****************资源接收人    end***************************/
        if(null!=resourceCodeList&&resourceCodeList.size()>0){
            for (String pactCode:resourceCodeList) {
                /*****************运营管家    start***************************/
                CommonBelongerQuery operateBelongerQuery = new CommonBelongerQuery();
                operateBelongerQuery.setUserRole(StringConsts.BELONGER_OPERATE);
                operateBelongerQuery.setPactCode(pactCode);
                CommonBelongerVO operateBelongerVO = commonBelongerManager.getBelongersByParam(operateBelongerQuery);                if(null!=operateBelongerVO){
                    CommonBelongerDO operateBelongerDO = new CommonBelongerDO();
                    operateBelongerDO.setId(operateBelongerVO.getId());
                    operateBelongerDO.setPactCode(pactCode);
                    operateBelongerDO.setUserPin(personal.getPin());
                    operateBelongerDO.setUserName(personal.getEmployeeName());
                    operateBelongerDO.setUserTel(personal.getEmployeeTel());
                    operateBelongerDO.setDeptCode(personal.getDeptCode());
                    operateBelongerDO.setDeptName(personal.getDeptName());
                    operateBelongerDO.setModifiedName(param.getModifiedName());
                    operateBelongerDO.setIp(param.getIp());
                    updateBelongerDOs.add(operateBelongerDO);
                }else{
                    CommonBelongerDO commonBelongerDO = new CommonBelongerDO();
                    commonBelongerDO.setPactCode(pactCode);
                    commonBelongerDO.setPactType(PactTypeConsts.PURCHASE);
                    commonBelongerDO.setUserPin(personal.getPin());
                    commonBelongerDO.setUserName(personal.getEmployeeName());
                    commonBelongerDO.setUserTel(personal.getEmployeeTel());
                    commonBelongerDO.setUserRole(StringConsts.BELONGER_OPERATE);
                    commonBelongerDO.setDeptCode(personal.getDeptCode());
                    commonBelongerDO.setDeptName(personal.getDeptName());
                    commonBelongerDO.setIp(param.getIp());
                    commonBelongerDO.setCreateName(param.getModifiedName());
                    addBelongerDOs.add(commonBelongerDO);
                }
                
                /*****************运营管家    end***************************/
                /*****************租后管家    start***************************/
                CommonBelongerQuery rentAfterBelongerQuery = new CommonBelongerQuery();
                rentAfterBelongerQuery.setUserRole(StringConsts.BELONGER_SERVICE);
                rentAfterBelongerQuery.setPactCode(pactCode);
                CommonBelongerVO rentAfterBelongerVO = commonBelongerManager.getBelongersByParam(rentAfterBelongerQuery);
                if(null == rentAfterBelongerQuery){
                    CommonBelongerDO commonBelongerDO = new CommonBelongerDO();
                    commonBelongerDO.setPactCode(pactCode);
                    commonBelongerDO.setPactType(PactTypeConsts.PURCHASE);
                    commonBelongerDO.setUserPin(personal.getPin());
                    commonBelongerDO.setUserName(personal.getEmployeeName());
                    commonBelongerDO.setUserTel(personal.getEmployeeTel());
                    commonBelongerDO.setUserRole(StringConsts.BELONGER_SERVICE);
                    commonBelongerDO.setDeptCode(personal.getDeptCode());
                    commonBelongerDO.setDeptName(personal.getDeptName());
                    commonBelongerDO.setIp(param.getIp());
                    commonBelongerDO.setCreateName(param.getModifiedName());
                    addBelongerDOs.add(commonBelongerDO);
                }else{
                    CommonBelongerDO commonBelongerDO = new CommonBelongerDO();
                    commonBelongerDO.setId(rentAfterBelongerVO.getId());
                    commonBelongerDO.setPactCode(pactCode);
                    commonBelongerDO.setUserPin(personal.getPin());
                    commonBelongerDO.setUserName(personal.getEmployeeName());
                    commonBelongerDO.setUserTel(personal.getEmployeeTel());
                    commonBelongerDO.setUserRole(StringConsts.BELONGER_SERVICE);
                    commonBelongerDO.setDeptCode(personal.getDeptCode());
                    commonBelongerDO.setDeptName(personal.getDeptName());
                    commonBelongerDO.setModifiedName(param.getModifiedName());
                    commonBelongerDO.setIp(param.getIp());
                    updateBelongerDOs.add(commonBelongerDO);
                }
                /*****************租后管家    end***************************/
            }
        }
        HouseParam houseParam = new HouseParam();
        houseParam.setPactCodeList(resourceCodeList);
        houseParam.setStewardPin(param.getPin());
        houseParam.setStewardDeptCode(personal.getDeptCode());
        houseParam.setIp(param.getIp());
        houseParam.setModifiedName(param.getModifiedName());
        boolean flag = commonBelongerManager.transferPurchase(addBelongerDOs,updateBelongerDOs,houseParam);
        if(flag){
            /*****************登录人    start***************************/
            ApiResult<PersonalResult> loginPersonal = personalRpc.getPersonalResultBypin(param.getModifiedName());
            PersonalResult login = loginPersonal.getData();
            /*****************登录人    end***************************/
            for (String pactCode:resourceCodeList) {
                List<String> variableList = new ArrayList<>();
                variableList.add(login.getEmployeeName());
                variableList.add(DateUtil.formatDateTime(new Date()));
                variableList.add(PactTypeConsts.PURCHASE);
                variableList.add(pactCode);
                saveRemind(pactCode,StringConsts.TEMPLATE_PACT_DISTRIBUTION,variableList,SerializeTypeConts.PURCHASE,param.getPin());
               /************************保存日志  start**************************************/
                Map<String, Object> map = new HashMap<String, Object>();
                map.put(NormalConstant.SERVICETYPE, SerializeTypeConts.PURCHASE);
                map.put(NormalConstant.SERVICECODE, pactCode);
                map.put(NormalConstant.OPERATETYPE, StringConsts.UPDATE);
                StringBuilder content = new StringBuilder();
                content.append(login.getEmployeeName()).append(StringConsts.CONSTANT_RESOURCE_TRANSFER).append(param.getPin())
                .append(StringConsts.CONSTANT_RESOURCE_TRANSFER_PACTCODE).append(pactCode);
                map.put(NormalConstant.OPERATECONTENT, content);
                map.put(NormalConstant.OPERATEPIN, param.getModifiedName());
                operateLogRpc.saveOperateLog(map);
                /************************保存日志  end**************************************/
            }
            result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
            result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
            result.setData(true);
        }else{
            result.setCode(CommonEnum.REQUEST_FAIL.getCode());
            result.setMessage(CommonEnum.REQUEST_FAIL.getMessage());
            result.setData(false);
        }
        return result;
    }
    /**
     * 
    * @Title: transferPurTermination
    * @Description: TODO( 资源转移 ---托进解约 )
    * @author GuoXiaoPeng
    * @param param 资源编码集合+资源接收人PIN
    * @return 转移成功true否则false
    * @throws 异常
     */
    private ApiResult<Boolean> transferPurTermination(ResourceTransferParam param) {
        ApiResult<Boolean> result = new ApiResult<>();
        List<String> resourceCodeList = param.getResourceCodeList();
        List<CommonBelongerDO> updateBelongerDOs = new ArrayList<>();
        /*****************资源接收人    start***************************/
        ApiResult<PersonalResult> personalResult = personalRpc.getPersonalResultBypin(param.getPin());
        PersonalResult personal = personalResult.getData();
        /*****************资源接收人    end***************************/
        if(null!=resourceCodeList&&resourceCodeList.size()>0){
            for (String pactCode:resourceCodeList) {
                /*****************维护人    start***************************/
                CommonBelongerQuery commonBelongerQuery = new CommonBelongerQuery();
                commonBelongerQuery.setUserRole(StringConsts.GUARDIAN);
                commonBelongerQuery.setPactCode(pactCode);
                CommonBelongerVO commonBelongerVO = commonBelongerManager.getBelongersByParam(commonBelongerQuery);
                if(null!=commonBelongerVO){
                    CommonBelongerDO commonBelongerDO = new CommonBelongerDO();
                    commonBelongerDO.setId(commonBelongerVO.getId());
                    commonBelongerDO.setPactCode(pactCode);
                    commonBelongerDO.setUserPin(personal.getPin());
                    commonBelongerDO.setUserName(personal.getEmployeeName());
                    commonBelongerDO.setUserTel(personal.getEmployeeTel());
                    commonBelongerDO.setDeptCode(personal.getDeptCode());
                    commonBelongerDO.setDeptName(personal.getDeptName());
                    commonBelongerDO.setModifiedName(param.getModifiedName());
                    commonBelongerDO.setIp(param.getIp());
                    updateBelongerDOs.add(commonBelongerDO);
                }else{
                    result.setCode(PactCommonEnum.TRANSFER_PURTERMINATION_ERROR.getCode());
                    result.setMessage(PactCommonEnum.TRANSFER_PURTERMINATION_ERROR.getMessage() + pactCode);
                    result.setData(false);
                    LOG.error("托进解约协议没有维护人,托进解约协议编码："+ pactCode);
                    return result;
                }
                /*****************维护人    end***************************/
            }
        }
        boolean flag = commonBelongerManager.updateCommonBelonger(null, updateBelongerDOs);
        if(flag){
            /*****************登录人    start***************************/
            ApiResult<PersonalResult> loginPersonal = personalRpc.getPersonalResultBypin(param.getModifiedName());
            PersonalResult login = loginPersonal.getData();
            /*****************登录人    end***************************/
            for (String pactCode:resourceCodeList) {
                List<String> variableList = new ArrayList<>();
                variableList.add(login.getEmployeeName());
                variableList.add(DateUtil.formatDateTime(new Date()));
                variableList.add(PactTypeConsts.PURCHASE_TERMINATION);
                variableList.add(pactCode);
                saveRemind(pactCode,StringConsts.TEMPLATE_PACT_DISTRIBUTION,variableList,SerializeTypeConts.PTERMINA,param.getPin());
                /************************保存日志  start**************************************/
                Map<String, Object> map = new HashMap<String, Object>();
                map.put(NormalConstant.SERVICETYPE, SerializeTypeConts.PTERMINA);
                map.put(NormalConstant.SERVICECODE, pactCode);
                map.put(NormalConstant.OPERATETYPE, StringConsts.UPDATE);
                StringBuilder content = new StringBuilder();
                content.append(login.getEmployeeName()).append(StringConsts.CONSTANT_RESOURCE_TRANSFER).append(param.getPin())
                .append(StringConsts.CONSTANT_RESOURCE_TRANSFER_PACTCODE).append(pactCode);
                map.put(NormalConstant.OPERATECONTENT, content);
                map.put(NormalConstant.OPERATEPIN, param.getModifiedName());
                operateLogRpc.saveOperateLog(map);
                /************************保存日志  end**************************************/
            }
            result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
            result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
            result.setData(true);
        }else{
            result.setCode(CommonEnum.REQUEST_FAIL.getCode());
            result.setMessage(CommonEnum.REQUEST_FAIL.getMessage());
            result.setData(false);
        }
        return result;
    }
    /**
     * 
    * @Title: transferRentBase
    * @Description: TODO( 资源转移 ---托出合同 )
    * @author GuoXiaoPeng
    * @param param 资源编码集合+资源接收人PIN
    * @return 转移成功true否则false
    * @throws 异常
     */
    private ApiResult<Boolean> transferRentBase(ResourceTransferParam param) {
        ApiResult<Boolean> result = new ApiResult<>();
        List<String> resourceCodeList = param.getResourceCodeList();
        List<CommonBelongerDO> addBelongerDOs = new ArrayList<>();
        List<CommonBelongerDO> updateBelongerDOs = new ArrayList<>();
        /*****************资源接收人    start***************************/
        ApiResult<PersonalResult> personalResult = personalRpc.getPersonalResultBypin(param.getPin());
        PersonalResult personal = personalResult.getData();
        /*****************资源接收人    end***************************/
        if(null!=resourceCodeList&&resourceCodeList.size()>0){
            for (String pactCode:resourceCodeList) {
                /*****************租后管家    start***************************/
                CommonBelongerVO commonBelongerVO = rentBaseManager.getRentAfterBelonger(pactCode);
                if(null != commonBelongerVO){
                    CommonBelongerDO commonBelongerDO = new CommonBelongerDO();
                    commonBelongerDO.setPactCode(commonBelongerVO.getPactCode());
                    commonBelongerDO.setPactType(PactTypeConsts.PURCHASE);
                    commonBelongerDO.setUserPin(personal.getPin());
                    commonBelongerDO.setUserName(personal.getEmployeeName());
                    commonBelongerDO.setUserTel(personal.getEmployeeTel());
                    commonBelongerDO.setUserRole(StringConsts.BELONGER_SERVICE);
                    commonBelongerDO.setDeptCode(personal.getDeptCode());
                    commonBelongerDO.setDeptName(personal.getDeptName());
                    commonBelongerDO.setIp(param.getIp());
                    commonBelongerDO.setCreateName(param.getModifiedName());
                    commonBelongerDO.setId(commonBelongerVO.getId());
                    updateBelongerDOs.add(commonBelongerDO);
                }else{
                    result.setCode(PactCommonEnum.TRANSFER_RENTBASE_ERROR.getCode());
                    result.setMessage(PactCommonEnum.TRANSFER_RENTBASE_ERROR.getMessage() + pactCode);
                    result.setData(false);
                    LOG.warn("托出合同资源转移，找不到租后管家，托出合同编码："+pactCode);
                    return result;
                }
                /*****************租后管家    end***************************/
            }
        }
        boolean flag = commonBelongerManager.updateCommonBelonger(addBelongerDOs, updateBelongerDOs);
        if(flag){
            /*****************登录人    start***************************/
            ApiResult<PersonalResult> loginPersonal = personalRpc.getPersonalResultBypin(param.getModifiedName());
            PersonalResult login = loginPersonal.getData();
            /*****************登录人    end***************************/
            for (String pactCode:resourceCodeList) {
                List<String> variableList = new ArrayList<>();
                variableList.add(login.getEmployeeName());
                variableList.add(DateUtil.formatDateTime(new Date()));
                variableList.add(PactTypeConsts.RENT);
                variableList.add(pactCode);
                saveRemind(pactCode,StringConsts.TEMPLATE_PACT_DISTRIBUTION,variableList,SerializeTypeConts.RENT,param.getPin());
                /************************保存日志  start**************************************/
                Map<String, Object> map = new HashMap<String, Object>();
                map.put(NormalConstant.SERVICETYPE, SerializeTypeConts.RENT);
                map.put(NormalConstant.SERVICECODE, pactCode);
                map.put(NormalConstant.OPERATETYPE, StringConsts.UPDATE);
                StringBuilder content = new StringBuilder();
                content.append(login.getEmployeeName()).append(StringConsts.CONSTANT_RESOURCE_TRANSFER).append(param.getPin())
                .append(StringConsts.CONSTANT_RESOURCE_TRANSFER_PACTCODE).append(pactCode);
                map.put(NormalConstant.OPERATECONTENT, content);
                map.put(NormalConstant.OPERATEPIN, param.getModifiedName());
                operateLogRpc.saveOperateLog(map);
                /************************保存日志  end**************************************/
            }
            result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
            result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
            result.setData(true);
        }else{
            result.setCode(CommonEnum.REQUEST_FAIL.getCode());
            result.setMessage(CommonEnum.REQUEST_FAIL.getMessage());
            result.setData(false);
        }
        return result;
    }
    /**
     * 
    * @Title: transferRentTermination
    * @Description: TODO( 资源转移 ---托出解约 )
    * @author GuoXiaoPeng
    * @param param 资源编码集合+资源接收人PIN
    * @return 转移成功true否则false
    * @throws 异常
     */
    private ApiResult<Boolean> transferRentTermination(ResourceTransferParam param) {
        ApiResult<Boolean> result = new ApiResult<>();
        List<String> resourceCodeList = param.getResourceCodeList();
        List<CommonBelongerDO> updateBelongerDOs = new ArrayList<>();
        /*****************资源接收人    start***************************/
        ApiResult<PersonalResult> personalResult = personalRpc.getPersonalResultBypin(param.getPin());
        PersonalResult personal = personalResult.getData();
        /*****************资源接收人    end***************************/
        if(null!=resourceCodeList&&resourceCodeList.size()>0){
            for (String pactCode:resourceCodeList) {
                /*****************维护人    start***************************/
                CommonBelongerQuery commonBelongerQuery = new CommonBelongerQuery();
                commonBelongerQuery.setUserRole(StringConsts.GUARDIAN);
                commonBelongerQuery.setPactCode(pactCode);
                CommonBelongerVO commonBelongerVO = commonBelongerManager.getBelongersByParam(commonBelongerQuery);
                if(null!=commonBelongerVO){
                    CommonBelongerDO commonBelongerDO = new CommonBelongerDO();
                    commonBelongerDO.setId(commonBelongerVO.getId());
                    commonBelongerDO.setPactCode(pactCode);
                    commonBelongerDO.setUserPin(personal.getPin());
                    commonBelongerDO.setUserName(personal.getEmployeeName());
                    commonBelongerDO.setUserTel(personal.getEmployeeTel());
                    commonBelongerDO.setDeptCode(personal.getDeptCode());
                    commonBelongerDO.setDeptName(personal.getDeptName());
                    commonBelongerDO.setModifiedName(param.getModifiedName());
                    commonBelongerDO.setIp(param.getIp());
                    updateBelongerDOs.add(commonBelongerDO);
                }else{
                    LOG.error("托出解约协议没有维护人,托出解约协议编码："+ pactCode);
                    result.setCode(PactCommonEnum.TRANSFER_RENTTERMINATION_ERROR.getCode());
                    result.setMessage(PactCommonEnum.TRANSFER_RENTTERMINATION_ERROR.getMessage() + pactCode);
                    result.setData(false);
                    return result;
                }
                /*****************维护人    end***************************/
            }
        }
        boolean flag = commonBelongerManager.updateCommonBelonger(null, updateBelongerDOs);
        if(flag){
            /*****************登录人    start***************************/
            ApiResult<PersonalResult> loginPersonal = personalRpc.getPersonalResultBypin(param.getModifiedName());
            PersonalResult login = loginPersonal.getData();
            /*****************登录人    end***************************/
            for (String pactCode:resourceCodeList) {
                List<String> variableList = new ArrayList<>();
                variableList.add(login.getEmployeeName());
                variableList.add(DateUtil.formatDateTime(new Date()));
                variableList.add(PactTypeConsts.RENT_TERMINATION);
                variableList.add(pactCode);
                saveRemind(pactCode,StringConsts.TEMPLATE_PACT_DISTRIBUTION,variableList,SerializeTypeConts.RTERMINA,param.getPin());
                /************************保存日志  start**************************************/
                Map<String, Object> map = new HashMap<String, Object>();
                map.put(NormalConstant.SERVICETYPE, SerializeTypeConts.RTERMINA);
                map.put(NormalConstant.SERVICECODE, pactCode);
                map.put(NormalConstant.OPERATETYPE, StringConsts.UPDATE);
                StringBuilder content = new StringBuilder();
                content.append(login.getEmployeeName()).append(StringConsts.CONSTANT_RESOURCE_TRANSFER).append(param.getPin())
                .append(StringConsts.CONSTANT_RESOURCE_TRANSFER_PACTCODE).append(pactCode);
                map.put(NormalConstant.OPERATECONTENT, content);
                map.put(NormalConstant.OPERATEPIN, param.getModifiedName());
                operateLogRpc.saveOperateLog(map);
                /************************保存日志  end**************************************/
            }
            result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
            result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
            result.setData(true);
        }else{
            result.setCode(CommonEnum.REQUEST_FAIL.getCode());
            result.setMessage(CommonEnum.REQUEST_FAIL.getMessage());
            result.setData(false);
        }
        return result;
    }
    /**
     * 
    * @Title: transferRentTurn
    * @Description: TODO( 资源转移 ---转租 )
    * @author GuoXiaoPeng
    * @param param 资源编码集合+资源接收人PIN
    * @return 转移成功true否则false
    * @throws 异常
     */
    private ApiResult<Boolean> transferRentTurn(ResourceTransferParam param) {
        ApiResult<Boolean> result = new ApiResult<>();
        List<String> resourceCodeList = param.getResourceCodeList();
        List<CommonBelongerDO> updateBelongerDOs = new ArrayList<>();
        /*****************资源接收人    start***************************/
        ApiResult<PersonalResult> personalResult = personalRpc.getPersonalResultBypin(param.getPin());
        PersonalResult personal = personalResult.getData();
        /*****************资源接收人    end***************************/
        if(null!=resourceCodeList&&resourceCodeList.size()>0){
            for (String pactCode:resourceCodeList) {
                /*****************维护人    start***************************/
                CommonBelongerQuery commonBelongerQuery = new CommonBelongerQuery();
                commonBelongerQuery.setUserRole(StringConsts.GUARDIAN);
                commonBelongerQuery.setPactCode(pactCode);
                CommonBelongerVO commonBelongerVO = commonBelongerManager.getBelongersByParam(commonBelongerQuery);
                if(null!=commonBelongerVO){
                    CommonBelongerDO commonBelongerDO = new CommonBelongerDO();
                    commonBelongerDO.setId(commonBelongerVO.getId());
                    commonBelongerDO.setPactCode(pactCode);
                    commonBelongerDO.setUserPin(personal.getPin());
                    commonBelongerDO.setUserName(personal.getEmployeeName());
                    commonBelongerDO.setUserTel(personal.getEmployeeTel());
                    commonBelongerDO.setDeptCode(personal.getDeptCode());
                    commonBelongerDO.setDeptName(personal.getDeptName());
                    commonBelongerDO.setModifiedName(param.getModifiedName());
                    commonBelongerDO.setIp(param.getIp());
                    updateBelongerDOs.add(commonBelongerDO);
                }else{
                    LOG.error("转租协议没有维护人,转租协议编码："+ pactCode);
                    result.setCode(PactCommonEnum.TRANSFER_RENTTURN_ERROR.getCode());
                    result.setMessage(PactCommonEnum.TRANSFER_RENTTURN_ERROR.getMessage());
                    result.setData(false);
                    return result;
                }
                /*****************维护人    end***************************/
            }
        }
        boolean flag = commonBelongerManager.updateCommonBelonger(null, updateBelongerDOs);
        if(flag){
            /*****************登录人    start***************************/
            ApiResult<PersonalResult> loginPersonal = personalRpc.getPersonalResultBypin(param.getModifiedName());
            PersonalResult login = loginPersonal.getData();
            /*****************登录人    end***************************/
            for (String pactCode:resourceCodeList) {
                List<String> variableList = new ArrayList<>();
                variableList.add(login.getEmployeeName());
                variableList.add(DateUtil.formatDateTime(new Date()));
                variableList.add(PactTypeConsts.RENT_SUBLET);
                variableList.add(pactCode);
                saveRemind(pactCode,StringConsts.TEMPLATE_PACT_DISTRIBUTION,variableList,SerializeTypeConts.RENTTURN,param.getPin());
                /************************保存日志  start**************************************/
                Map<String, Object> map = new HashMap<String, Object>();
                map.put(NormalConstant.SERVICETYPE, SerializeTypeConts.RENTTURN);
                map.put(NormalConstant.SERVICECODE, pactCode);
                map.put(NormalConstant.OPERATETYPE, StringConsts.UPDATE);
                StringBuilder content = new StringBuilder();
                content.append(login.getEmployeeName()).append(StringConsts.CONSTANT_RESOURCE_TRANSFER).append(param.getPin())
                .append(StringConsts.CONSTANT_RESOURCE_TRANSFER_PACTCODE).append(pactCode);
                map.put(NormalConstant.OPERATECONTENT, content);
                map.put(NormalConstant.OPERATEPIN, param.getModifiedName());
                operateLogRpc.saveOperateLog(map);
                /************************保存日志  end**************************************/
            }
            result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
            result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
            result.setData(true);
        }else{
            result.setCode(CommonEnum.REQUEST_FAIL.getCode());
            result.setMessage(CommonEnum.REQUEST_FAIL.getMessage());
            result.setData(false);
        }
        return result;
    }
    /**
    * @Title: transferRentContinued
    * @Description: TODO( 资源转移 ---续签 )
    * @author GuoXiaoPeng
    * @param param 资源编码集合+资源接收人PIN
    * @return 转移成功true否则false
    * @throws 异常
     */
    private ApiResult<Boolean> transferRentContinued(ResourceTransferParam param) {
        ApiResult<Boolean> result = new ApiResult<>();
        List<String> resourceCodeList = param.getResourceCodeList();
        List<CommonBelongerDO> updateBelongerDOs = new ArrayList<>();
        /*****************资源接收人    start***************************/
        ApiResult<PersonalResult> personalResult = personalRpc.getPersonalResultBypin(param.getPin());
        PersonalResult personal = personalResult.getData();
        /*****************资源接收人    end***************************/
        if(null!=resourceCodeList&&resourceCodeList.size()>0){
            for (String pactCode:resourceCodeList) {
                /*****************维护人    start***************************/
                CommonBelongerQuery commonBelongerQuery = new CommonBelongerQuery();
                commonBelongerQuery.setUserRole(StringConsts.GUARDIAN);
                commonBelongerQuery.setPactCode(pactCode);
                CommonBelongerVO commonBelongerVO = commonBelongerManager.getBelongersByParam(commonBelongerQuery);
                if(null!=commonBelongerVO){
                    CommonBelongerDO commonBelongerDO = new CommonBelongerDO();
                    commonBelongerDO.setId(commonBelongerVO.getId());
                    commonBelongerDO.setPactCode(pactCode);
                    commonBelongerDO.setUserPin(personal.getPin());
                    commonBelongerDO.setUserName(personal.getEmployeeName());
                    commonBelongerDO.setUserTel(personal.getEmployeeTel());
                    commonBelongerDO.setDeptCode(personal.getDeptCode());
                    commonBelongerDO.setDeptName(personal.getDeptName());
                    commonBelongerDO.setModifiedName(param.getModifiedName());
                    commonBelongerDO.setIp(param.getIp());
                    updateBelongerDOs.add(commonBelongerDO);
                }else{
                    LOG.error("续签协议没有维护人,续签协议编码："+ pactCode);
                    result.setCode(PactCommonEnum.TRANSFER_RENTCONTINUED_ERROR.getCode());
                    result.setMessage(PactCommonEnum.TRANSFER_RENTCONTINUED_ERROR.getMessage() + pactCode);
                    result.setData(false);
                    return result;
                }
                /*****************维护人    end***************************/
            }
        }
        boolean flag = commonBelongerManager.updateCommonBelonger(null, updateBelongerDOs);
        if(flag){
            /*****************登录人    start***************************/
            ApiResult<PersonalResult> loginPersonal = personalRpc.getPersonalResultBypin(param.getModifiedName());
            PersonalResult login = loginPersonal.getData();
            /*****************登录人    end***************************/
            for (String pactCode:resourceCodeList) {
                List<String> variableList = new ArrayList<>();
                variableList.add(login.getEmployeeName());
                variableList.add(DateUtil.formatDateTime(new Date()));
                variableList.add(PactTypeConsts.RENT_RENEW);
                variableList.add(pactCode);
                saveRemind(pactCode,StringConsts.TEMPLATE_PACT_DISTRIBUTION,variableList,SerializeTypeConts.RENEW,param.getPin());
                /************************保存日志  start**************************************/
                Map<String, Object> map = new HashMap<String, Object>();
                map.put(NormalConstant.SERVICETYPE, SerializeTypeConts.RENEW);
                map.put(NormalConstant.SERVICECODE, pactCode);
                map.put(NormalConstant.OPERATETYPE, StringConsts.UPDATE);
                StringBuilder content = new StringBuilder();
                content.append(login.getEmployeeName()).append(StringConsts.CONSTANT_RESOURCE_TRANSFER).append(param.getPin())
                .append(StringConsts.CONSTANT_RESOURCE_TRANSFER_PACTCODE).append(pactCode);
                map.put(NormalConstant.OPERATECONTENT, content);
                map.put(NormalConstant.OPERATEPIN, param.getModifiedName());
                operateLogRpc.saveOperateLog(map);
                /************************保存日志  end**************************************/
            }
            result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
            result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
            result.setData(true);
        }else{
            result.setCode(CommonEnum.REQUEST_FAIL.getCode());
            result.setMessage(CommonEnum.REQUEST_FAIL.getMessage());
            result.setData(false);
        }
        return result;
    }
    /**
     * 
    * @Title: transferRoom
    * @Description: TODO( 资源转移 ---调房 )
    * @author GuoXiaoPeng
    * @param param  资源编码集合+资源接收人PIN
    * @return 转移成功true否则false
    * @throws 异常
     */
    private ApiResult<Boolean> transferRoom(ResourceTransferParam param) {
        ApiResult<Boolean> result = new ApiResult<>();
        List<String> resourceCodeList = param.getResourceCodeList();
        List<CommonBelongerDO> updateBelongerDOs = new ArrayList<>();
        /*****************资源接收人    start***************************/
        ApiResult<PersonalResult> personalResult = personalRpc.getPersonalResultBypin(param.getPin());
        PersonalResult personal = personalResult.getData();
        /*****************资源接收人    end***************************/
        if(null!=resourceCodeList&&resourceCodeList.size()>0){
            for (String pactCode:resourceCodeList) {
                /*****************维护人    start***************************/
                CommonBelongerQuery commonBelongerQuery = new CommonBelongerQuery();
                commonBelongerQuery.setUserRole(StringConsts.GUARDIAN);
                commonBelongerQuery.setPactCode(pactCode);
                CommonBelongerVO commonBelongerVO = commonBelongerManager.getBelongersByParam(commonBelongerQuery);
                if(null!=commonBelongerVO){
                    CommonBelongerDO commonBelongerDO = new CommonBelongerDO();
                    commonBelongerDO.setId(commonBelongerVO.getId());
                    commonBelongerDO.setPactCode(pactCode);
                    commonBelongerDO.setUserPin(personal.getPin());
                    commonBelongerDO.setUserName(personal.getEmployeeName());
                    commonBelongerDO.setUserTel(personal.getEmployeeTel());
                    commonBelongerDO.setDeptCode(personal.getDeptCode());
                    commonBelongerDO.setDeptName(personal.getDeptName());
                    commonBelongerDO.setModifiedName(param.getModifiedName());
                    commonBelongerDO.setIp(param.getIp());
                    updateBelongerDOs.add(commonBelongerDO);
                }else{
                    LOG.error("调房协议没有维护人,调房协议编码："+ pactCode);
                    result.setCode(PactCommonEnum.TRANSFER_ROOM_ERROR.getCode());
                    result.setMessage(PactCommonEnum.TRANSFER_ROOM_ERROR.getMessage());
                    result.setData(false);
                    return result;
                }
                /*****************维护人    end***************************/
            }
        }
        boolean flag = commonBelongerManager.updateCommonBelonger(null, updateBelongerDOs);
        if(flag){
            /*****************登录人    start***************************/
            ApiResult<PersonalResult> loginPersonal = personalRpc.getPersonalResultBypin(param.getModifiedName());
            PersonalResult login = loginPersonal.getData();
            /*****************登录人    end***************************/
            for (String pactCode:resourceCodeList) {
                List<String> variableList = new ArrayList<>();
                variableList.add(login.getEmployeeName());
                variableList.add(DateUtil.formatDateTime(new Date()));
                variableList.add(PactTypeConsts.RENT_ROOM);
                variableList.add(pactCode);
                saveRemind(pactCode,StringConsts.TEMPLATE_PACT_DISTRIBUTION,variableList,SerializeTypeConts.TRANSFER,param.getPin());
                /************************保存日志  start**************************************/
                Map<String, Object> map = new HashMap<String, Object>();
                map.put(NormalConstant.SERVICETYPE, SerializeTypeConts.TRANSFER);
                map.put(NormalConstant.SERVICECODE, pactCode);
                map.put(NormalConstant.OPERATETYPE, StringConsts.UPDATE);
                StringBuilder content = new StringBuilder();
                content.append(login.getEmployeeName()).append(StringConsts.CONSTANT_RESOURCE_TRANSFER).append(param.getPin())
                .append(StringConsts.CONSTANT_RESOURCE_TRANSFER_PACTCODE).append(pactCode);
                map.put(NormalConstant.OPERATECONTENT, content);
                map.put(NormalConstant.OPERATEPIN, param.getModifiedName());
                operateLogRpc.saveOperateLog(map);
                /************************保存日志  end**************************************/
            }
            result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
            result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
            result.setData(true);
        }else{
            result.setCode(CommonEnum.REQUEST_FAIL.getCode());
            result.setMessage(CommonEnum.REQUEST_FAIL.getMessage());
            result.setData(false);
        }
        return result;
    }
    /**
     * @Title: saveRemind
     * @Description: TODO( 合同资源转移发送提醒消息 )
     * @author GuoXiaoPeng
     * @param pactCode 合同编码
     * @param key  模板key
     * @param variableList 替换的变量集合
     * @param serviceType 资源类型
     * @param pin 资源接收人
     * @throws
      */
     private void saveRemind(String pactCode, String key, List<String> variableList,String serviceType,String pin) {
         String content = remindMap.get(key);
         for (int i = 0; i < variableList.size(); i++) {
             content = content.replace(StringConsts.VARIABLE + i, variableList.get(i));
         }
         Map<String, Object> map = new HashMap<>();
         map.put(NormalConstant.REMIND_TITLE, StringConsts.PACT_DISTRIBUTION);
         map.put(NormalConstant.REMIND_PIN, pin);
         map.put(NormalConstant.REMIND_SERVICETYPE, serviceType);
         map.put(NormalConstant.REMIND_SERVICECODE, pactCode);
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

    public RentBaseManager getRentBaseManager() {
        return rentBaseManager;
    }

    public void setRentBaseManager(RentBaseManager rentBaseManager) {
        this.rentBaseManager = rentBaseManager;
    }

    public PactRentTerminationManager getPactRentTerminationManager() {
        return pactRentTerminationManager;
    }

    public void setPactRentTerminationManager(PactRentTerminationManager pactRentTerminationManager) {
        this.pactRentTerminationManager = pactRentTerminationManager;
    }

    public RentContinuedManager getRentContinuedManager() {
        return rentContinuedManager;
    }

    public void setRentContinuedManager(RentContinuedManager rentContinuedManager) {
        this.rentContinuedManager = rentContinuedManager;
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

    public CommonBelongerManager getCommonBelongerManager() {
        return commonBelongerManager;
    }

    public void setCommonBelongerManager(CommonBelongerManager commonBelongerManager) {
        this.commonBelongerManager = commonBelongerManager;
    }

    public PersonalRpc getPersonalRpc() {
        return personalRpc;
    }

    public void setPersonalRpc(PersonalRpc personalRpc) {
        this.personalRpc = personalRpc;
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

    public OperateLogRpc getOperateLogRpc() {
        return operateLogRpc;
    }

    public void setOperateLogRpc(OperateLogRpc operateLogRpc) {
        this.operateLogRpc = operateLogRpc;
    }
    
}
