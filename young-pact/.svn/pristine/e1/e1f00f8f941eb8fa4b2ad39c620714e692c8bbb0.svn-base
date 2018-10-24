package com.young.pact.service.commonbelonger.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.tools.common.util.convert.BeanUtils;
import com.young.common.dict.CommonEnum;
import com.young.common.domain.ApiResult;
import com.young.common.util.DateUtil;
import com.young.pact.api.domain.param.rest.commonbelonger.CommonBelongerParam;
import com.young.pact.api.service.rest.commonbelonger.CommonBelongerRestService;
import com.young.pact.common.constant.NormalConstant;
import com.young.pact.common.constant.PactTypeConsts;
import com.young.pact.common.constant.SerializeTypeConts;
import com.young.pact.common.constant.StringConsts;
import com.young.pact.common.dict.PactCommonEnum;
import com.young.pact.common.exception.PactManagerExcepion;
import com.young.pact.domain.commonbelonger.CommonBelongerDO;
import com.young.pact.domain.commonbelonger.CommonBelongerVO;
import com.young.pact.manager.commonbelonger.CommonBelongerManager;
import com.young.pact.rpc.operatelog.OperateLogRpc;
import com.young.pact.rpc.personal.PersonalRpc;
import com.young.pact.rpc.remind.RemindRpc;
import com.young.sso.api.domain.result.rpc.personal.PersonalResult;
/**
 * 
* @ClassName: CommonBelongerRestServiceImpl
* @Description: TODO( 合同责任人)
* @author HeZeMin
* @date 2018年8月5日 下午3:55:10
*
 */
@Service("commonBelongerRestService")
public class CommonBelongerRestServiceImpl implements CommonBelongerRestService {
    /*******************声明区**************************/
    private static final Log LOG = LogFactory.getLog(CommonBelongerRestServiceImpl.class);
    private CommonBelongerManager commonBelongerManager;
    private PersonalRpc personalRpc;
    private OperateLogRpc operateLogRpc;
    private Map<String,String> remindMap;
    private RemindRpc remindRpc;
    /*******************方法区**************************/
    @Override
    public ApiResult<Boolean> updateOperateBelonger(CommonBelongerParam param) {
        ApiResult<Boolean> result = new ApiResult<>();
        if(null == param){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        try {
            CommonBelongerDO commonBelongerDO = new CommonBelongerDO();
            BeanUtils.copyProperties(param, commonBelongerDO);
            
            List<CommonBelongerVO> commonBelongerList = commonBelongerManager.listCommonBelonger(commonBelongerDO.getPactCode());
            if(null != commonBelongerList){
                for (CommonBelongerVO commonBelongerVO : commonBelongerList) {
                    if(StringConsts.BELONGER_OPERATE.equals(commonBelongerVO.getUserRole())){
                        commonBelongerDO.setId(commonBelongerVO.getId());
                    }
                }
            }
            // 根据运营管家Pin查询管家信息
            ApiResult<PersonalResult> personalResult = personalRpc.getPersonalResultBypin(commonBelongerDO.getUserPin());
            PersonalResult personalResultData = null;
            if(StringConsts.REQUEST_SUCCESS_CODE.equals(personalResult.getCode())){
                personalResultData = personalResult.getData();
                commonBelongerDO.setUserName(personalResultData.getEmployeeName());
                commonBelongerDO.setUserTel(personalResultData.getEmployeeTel());
                commonBelongerDO.setDeptName(personalResultData.getDeptName());
                commonBelongerDO.setDeptCode(personalResultData.getDeptCode());
                commonBelongerDO.setUserPin(personalResultData.getPin());
            }
            boolean flag = commonBelongerManager.updateSyncOperateBelonger(commonBelongerDO);
            if(flag){
                result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                result.setData(flag);
                /*-------分配运营管家操作日志--------start-----------*/
                Map<String, Object> map = new HashMap<String, Object>();
                map.put(NormalConstant.SERVICETYPE, SerializeTypeConts.PURCHASE);
                map.put(NormalConstant.SERVICECODE, commonBelongerDO.getPactCode());
                map.put(NormalConstant.OPERATEPIN, commonBelongerDO.getCreateName());
                map.put(NormalConstant.OPERATETYPE, StringConsts.UPDATE);
                map.put(NormalConstant.OPERATECONTENT  , StringConsts.UPDATE_BELONGER_OPERATE);
                operateLogRpc.saveOperateLog(map);
                /*-------分配运营管家操作日志--------end-----------*/
                /*********************托进合同分配租后管家给租后管家发送提醒消息  start***************************************/
                ApiResult<PersonalResult> loginPersonal = personalRpc.getPersonalResultBypin(param.getModifiedName());
                PersonalResult login = loginPersonal.getData();
                List<String> variableList = new ArrayList<>();
                variableList.add(login.getEmployeeName());
                variableList.add(DateUtil.formatDateTime(new Date()));
                variableList.add(PactTypeConsts.PURCHASE);
                variableList.add(commonBelongerDO.getPactCode());
                saveRemind(param,StringConsts.TEMPLATE_PACT_DISTRIBUTION,variableList);
                /*********************托进合同分配租后管家给租后管家 发送提醒消息  end***************************************/
            }else{
                result.setCode(CommonEnum.REQUEST_FAIL.getCode());
                result.setMessage(CommonEnum.REQUEST_FAIL.getMessage());
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
    public ApiResult<Boolean> updateServiceBelonger(CommonBelongerParam param) {
        ApiResult<Boolean> result = new ApiResult<>();
        if(null == param){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        try {
            CommonBelongerDO commonBelongerDO = new CommonBelongerDO();
            BeanUtils.copyProperties(param, commonBelongerDO);
            
            List<CommonBelongerVO> commonBelongerList = commonBelongerManager.listCommonBelonger(commonBelongerDO.getPactCode());
            if(null != commonBelongerList){
                for (CommonBelongerVO commonBelongerVO : commonBelongerList) {
                    if(StringConsts.BELONGER_SERVICE.equals(commonBelongerVO.getUserRole())){
                        commonBelongerDO.setId(commonBelongerVO.getId());
                    }
                }
            }
            // 根据租后管家Pin查询管家信息
            ApiResult<PersonalResult> personalResult = personalRpc.getPersonalResultBypin(param.getUserPin());
            if(null!=personalResult&&StringConsts.REQUEST_SUCCESS_CODE.equals(personalResult.getCode())){
                PersonalResult perResultData = personalResult.getData();
                commonBelongerDO.setPactType(PactTypeConsts.PURCHASE);
                commonBelongerDO.setUserName(perResultData.getEmployeeName());
                commonBelongerDO.setUserTel(perResultData.getEmployeeTel());
                commonBelongerDO.setDeptName(perResultData.getDeptName());
                commonBelongerDO.setDeptCode(perResultData.getDeptCode());
            }
            commonBelongerDO.setUserRole(StringConsts.BELONGER_SERVICE);
            if(null != commonBelongerDO.getId() && commonBelongerDO.getId() > 0){
                // 有租后，去修改
                boolean flag = commonBelongerManager.updateCommonBelonger(commonBelongerDO);
                result.setData(flag);
            }else{
                // 没有租后，去添加
                List<CommonBelongerDO> commonBelongerDOList = new ArrayList<>();
                commonBelongerDOList.add(commonBelongerDO);
                boolean flag = commonBelongerManager.saveCommonBelonger(commonBelongerDOList);
                result.setData(flag);
            }
            /*-------分配租后管家操作日志--------start-----------*/
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(NormalConstant.SERVICETYPE, SerializeTypeConts.PURCHASE);
            map.put(NormalConstant.SERVICECODE, commonBelongerDO.getPactCode());
            map.put(NormalConstant.OPERATEPIN, commonBelongerDO.getCreateName());
            map.put(NormalConstant.OPERATETYPE, StringConsts.UPDATE);
            map.put(NormalConstant.OPERATECONTENT , StringConsts.UPDATE_BELONGER_SERVICE);
            operateLogRpc.saveOperateLog(map);
            /*-------分配租后管家操作日志--------end-----------*/
            /*********************托进合同分配租后管家给租后管家发送提醒消息  start***************************************/
            ApiResult<PersonalResult> loginPersonal = personalRpc.getPersonalResultBypin(commonBelongerDO.getUserPin());
            PersonalResult login = loginPersonal.getData();
            List<String> variableList = new ArrayList<>();
            variableList.add(login.getEmployeeName());
            variableList.add(DateUtil.formatDateTime(new Date()));
            variableList.add(PactTypeConsts.PURCHASE);
            variableList.add(commonBelongerDO.getPactCode());
            saveRemind(param,StringConsts.TEMPLATE_PACT_DISTRIBUTION,variableList);
            /*********************托进合同分配租后管家给租后管家 发送提醒消息  end***************************************/
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
    
    /**
     * @Title: saveRemind
     * @Description: TODO( 发送指定内容的提醒消息 )
     * @author GuoXiaoPeng
     * @param param 资源编码
     * @param key 模板key
     * @param variableList 替换的变量集合
     * @throws 异常
     */
     private void saveRemind(CommonBelongerParam param, String key, List<String> variableList) {
         String content = remindMap.get(key);
         for (int i = 0; i < variableList.size(); i++) {
             content = content.replace(StringConsts.VARIABLE + i, variableList.get(i));
         }
         Map<String, Object> map = new HashMap<>();
         map.put(NormalConstant.REMIND_TITLE, StringConsts.PACT_DISTRIBUTION);
         map.put(NormalConstant.REMIND_PIN, param.getUserPin());
         map.put(NormalConstant.REMIND_SERVICETYPE, SerializeTypeConts.PURCHASE);
         map.put(NormalConstant.REMIND_SERVICECODE, param.getPactCode());
         map.put(NormalConstant.REMIND_REMINDCONTENT,content);
         remindRpc.saveRemind(map);
     }
    /*******************get/set**************************/

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

    public OperateLogRpc getOperateLogRpc() {
        return operateLogRpc;
    }

    public void setOperateLogRpc(OperateLogRpc operateLogRpc) {
        this.operateLogRpc = operateLogRpc;
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
    
}
