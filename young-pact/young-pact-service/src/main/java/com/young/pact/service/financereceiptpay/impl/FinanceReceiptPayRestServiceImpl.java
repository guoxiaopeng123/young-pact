package com.young.pact.service.financereceiptpay.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.tools.common.util.convert.BeanUtils;
import com.young.common.dict.CommonEnum;
import com.young.common.domain.ApiResult;
import com.young.common.page.PageBean;
import com.young.common.page.PageTableBean;
import com.young.follow.api.domain.param.rpc.OperateLogParam;
import com.young.follow.api.domain.result.rpc.OperateLogResult;
import com.young.pact.api.domain.param.rest.financereceiptpay.FinanceReceiptPayParam;
import com.young.pact.api.domain.param.rest.financereceiptpay.FinanceReceiptPayQueryParam;
import com.young.pact.api.domain.result.rest.financereceiptpay.FinanceReceiptPayListResult;
import com.young.pact.api.domain.result.rest.financereceiptpay.FinanceReceiptPayResult;
import com.young.pact.api.domain.result.rest.financereceiptpay.FinanceReceiptPayVoucherResult;
import com.young.pact.api.domain.result.rest.financereceiptpay.RealReceiptPayResult;
import com.young.pact.api.service.rest.financereceiptpay.FinanceReceiptPayRestService;
import com.young.pact.common.constant.NormalConstant;
import com.young.pact.common.constant.PactTypeConsts;
import com.young.pact.common.constant.SerializeTypeConts;
import com.young.pact.common.constant.StringConsts;
import com.young.pact.common.dict.PactCommonEnum;
import com.young.pact.common.exception.PactManagerExcepion;
import com.young.pact.common.util.DateUtil;
import com.young.pact.domain.commonbelonger.CommonBelongerQuery;
import com.young.pact.domain.commonbelonger.CommonBelongerVO;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayDO;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayQuery;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayVO;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayVoucherDO;
import com.young.pact.domain.pactrenttermination.PactRentTerminationAllVO;
import com.young.pact.domain.pactrenttermination.PactRentTerminationQuery;
import com.young.pact.domain.pactrenttermination.PactRentTerminationVO;
import com.young.pact.domain.pactrenttransfer.PactRentTransferVO;
import com.young.pact.domain.pactrenttransfer.PactTransferAllVO;
import com.young.pact.domain.rentturn.RentTurnVO;
import com.young.pact.domain.statistics.AgendaDO;
import com.young.pact.manager.commonbelonger.CommonBelongerManager;
import com.young.pact.manager.financereceiptpay.FinanceReceiptPayManager;
import com.young.pact.manager.financereceiptpay.FinanceReceiptPayVoucherManager;
import com.young.pact.manager.pactrenttermination.PactRentTerminationManager;
import com.young.pact.manager.pactrenttransfer.PactRentTransferManager;
import com.young.pact.manager.rentbase.RentBaseManager;
import com.young.pact.manager.rentturn.RentTurnManager;
import com.young.pact.manager.statistics.StatisticsManager;
import com.young.pact.rpc.dept.DeptRpc;
import com.young.pact.rpc.operatelog.OperateLogRpc;
import com.young.pact.rpc.personal.PersonalRpc;
import com.young.pact.rpc.remind.RemindRpc;
import com.young.sso.api.domain.result.rpc.personal.PersonalResult;
/**
 * 
* @ClassName: FinanceReceiptPayRestServiceImpl
* @Description: TODO(收支)
* @author HeZeMin
* @date 2018年8月8日 上午10:54:40
*
 */
@Service("financeReceiptPayRestService")
public class FinanceReceiptPayRestServiceImpl implements FinanceReceiptPayRestService {
    /*******************声明区**************************/
    private static final Log LOG = LogFactory.getLog(FinanceReceiptPayRestServiceImpl.class);
    private FinanceReceiptPayManager financeReceiptPayManager;
    private FinanceReceiptPayVoucherManager financeReceiptPayVoucherManager;
    private RentTurnManager rentTurnManager;
    private PactRentTransferManager pactRentTransferManager;
    private PactRentTerminationManager pactRentTerminationManager;
    private PersonalRpc personalRpc;
    private OperateLogRpc operateLogRpc;
    private DeptRpc deptRpc;
    private Map<String,String> remindMap;
    private RemindRpc remindRpc;
    private CommonBelongerManager commonBelongerManager;
    private RentBaseManager rentBaseManager;
    private StatisticsManager statisticsManager;
    /*******************方法区**************************/
    
    @Override
    public ApiResult<PageBean<FinanceReceiptPayListResult>> listAllFinanceReceiptPay(FinanceReceiptPayQueryParam param) {
        ApiResult<PageBean<FinanceReceiptPayListResult>> result = new ApiResult<>();
        if(null == param){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        try {
            FinanceReceiptPayQuery query = new FinanceReceiptPayQuery();
            BeanUtils.copyProperties(param, query);
            initPermissionsAllRecPay(param,query);
            PageBean<FinanceReceiptPayVO> financeReceiptPayList = financeReceiptPayManager.listAllFinanceReceiptPay(query);
            List<FinanceReceiptPayVO> financeReceiptPayVOs = financeReceiptPayList.getData();
            if(null != financeReceiptPayVOs && financeReceiptPayVOs.size()>0){
                for(FinanceReceiptPayVO financeReceiptPayVO:financeReceiptPayVOs){
                    if(PactTypeConsts.RENT.equals(financeReceiptPayVO.getPactType())){
                        CommonBelongerVO rentAfterBelonger = rentBaseManager.getRentAfterBelonger(financeReceiptPayVO.getPactCode());
                        if(null!=rentAfterBelonger){
                            financeReceiptPayVO.setServiceUserName(rentAfterBelonger.getUserName());
                            financeReceiptPayVO.setServiceDeptName(rentAfterBelonger.getDeptName());
                        }
                    }else if(PactTypeConsts.PURCHASE_TERMINATION.equals(financeReceiptPayVO.getPactType())
                            ||PactTypeConsts.RENT_SUBLET.equals(financeReceiptPayVO.getPactType())
                            ||PactTypeConsts.RENT_RENEW.equals(financeReceiptPayVO.getPactType())
                            ||PactTypeConsts.RENT_ROOM.equals(financeReceiptPayVO.getPactType())
                            ||PactTypeConsts.RENT_TERMINATION.equals(financeReceiptPayVO.getPactType())){
                        CommonBelongerQuery commonBelongerQuery = new CommonBelongerQuery();
                        commonBelongerQuery.setPactCode(financeReceiptPayVO.getPactCode());
                        commonBelongerQuery.setUserRole(StringConsts.BELONGER_PERSON);
                        CommonBelongerVO commonBelongerVO = commonBelongerManager.getBelongersByParam(commonBelongerQuery);
                        if(null != commonBelongerVO){
                            financeReceiptPayVO.setServiceUserName(commonBelongerVO.getUserName());
                            financeReceiptPayVO.setServiceDeptName(commonBelongerVO.getDeptName());
                        }
                    }
                }
            }
            if(financeReceiptPayList.getTotalItem() > 0){
                PageBean<FinanceReceiptPayListResult> resultList = new PageTableBean<FinanceReceiptPayListResult>(query.getPageIndex(),query.getPageSize());
                resultList.setTotalItem(financeReceiptPayList.getTotalItem());
                // copy分页数据
                BeanUtils.copyListProperties(financeReceiptPayList.getData(), resultList.getData(), FinanceReceiptPayListResult.class);
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
    public ApiResult<PageBean<FinanceReceiptPayListResult>> listReceivFinanceReceiptPay(FinanceReceiptPayQueryParam param) {
        ApiResult<PageBean<FinanceReceiptPayListResult>> result = new ApiResult<>();
        if(null == param){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        try {
            FinanceReceiptPayQuery query = new FinanceReceiptPayQuery();
            BeanUtils.copyProperties(param, query);
            initPermissionsQuery(param,query);
            PageBean<FinanceReceiptPayVO> financeReceiptPayList = financeReceiptPayManager.listReceivFinanceReceiptPay(query);
            List<FinanceReceiptPayVO> financeReceiptPayVOs = financeReceiptPayList.getData();
            if(null != financeReceiptPayVOs && financeReceiptPayVOs.size()>0){
                for(FinanceReceiptPayVO financeReceiptPayVO:financeReceiptPayVOs){
                    if(PactTypeConsts.RENT.equals(financeReceiptPayVO.getPactType())){
                        CommonBelongerVO rentAfterBelonger = rentBaseManager.getRentAfterBelonger(financeReceiptPayVO.getPactCode());
                        if(null!=rentAfterBelonger){
                            financeReceiptPayVO.setServiceUserName(rentAfterBelonger.getUserName());
                            financeReceiptPayVO.setServiceDeptName(rentAfterBelonger.getDeptName());
                        }
                    }else if(PactTypeConsts.PURCHASE_TERMINATION.equals(financeReceiptPayVO.getPactType())
                            ||PactTypeConsts.RENT_SUBLET.equals(financeReceiptPayVO.getPactType())
                            ||PactTypeConsts.RENT_RENEW.equals(financeReceiptPayVO.getPactType())
                            ||PactTypeConsts.RENT_ROOM.equals(financeReceiptPayVO.getPactType())
                            ||PactTypeConsts.RENT_TERMINATION.equals(financeReceiptPayVO.getPactType())){
                        CommonBelongerQuery commonBelongerQuery = new CommonBelongerQuery();
                        commonBelongerQuery.setPactCode(financeReceiptPayVO.getPactCode());
                        commonBelongerQuery.setUserRole(StringConsts.BELONGER_PERSON);
                        CommonBelongerVO commonBelongerVO = commonBelongerManager.getBelongersByParam(commonBelongerQuery);
                        if(null != commonBelongerVO){
                            financeReceiptPayVO.setServiceUserName(commonBelongerVO.getUserName());
                            financeReceiptPayVO.setServiceDeptName(commonBelongerVO.getDeptName());
                        }
                    }
                }
            }
            if(financeReceiptPayList.getTotalItem() > 0){
                PageBean<FinanceReceiptPayListResult> resultList = new PageTableBean<FinanceReceiptPayListResult>(query.getPageIndex(),query.getPageSize());
                resultList.setTotalItem(financeReceiptPayList.getTotalItem());
                // copy分页数据
                BeanUtils.copyListProperties(financeReceiptPayList.getData(), resultList.getData(), FinanceReceiptPayListResult.class);
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
    public ApiResult<PageBean<FinanceReceiptPayListResult>> listNetFinanceReceiptPay(FinanceReceiptPayQueryParam param) {
        ApiResult<PageBean<FinanceReceiptPayListResult>> result = new ApiResult<>();
        if(null == param){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        try {
            FinanceReceiptPayQuery query = new FinanceReceiptPayQuery();
            BeanUtils.copyProperties(param, query);
            initPermissionsQuery(param,query);
            PageBean<FinanceReceiptPayVO> financeReceiptPayList = financeReceiptPayManager.listNetFinanceReceiptPay(query);
            List<FinanceReceiptPayVO> financeReceiptPayVOs = financeReceiptPayList.getData();
            if(null != financeReceiptPayVOs && financeReceiptPayVOs.size()>0){
                for(FinanceReceiptPayVO financeReceiptPayVO:financeReceiptPayVOs){
                    if(PactTypeConsts.RENT.equals(financeReceiptPayVO.getPactType())){
                        CommonBelongerVO rentAfterBelonger = rentBaseManager.getRentAfterBelonger(financeReceiptPayVO.getPactCode());
                        if(null!=rentAfterBelonger){
                            financeReceiptPayVO.setServiceUserName(rentAfterBelonger.getUserName());
                            financeReceiptPayVO.setServiceDeptName(rentAfterBelonger.getDeptName());
                        }
                    }else if(PactTypeConsts.PURCHASE_TERMINATION.equals(financeReceiptPayVO.getPactType())
                            ||PactTypeConsts.RENT_SUBLET.equals(financeReceiptPayVO.getPactType())
                            ||PactTypeConsts.RENT_RENEW.equals(financeReceiptPayVO.getPactType())
                            ||PactTypeConsts.RENT_ROOM.equals(financeReceiptPayVO.getPactType())
                            ||PactTypeConsts.RENT_TERMINATION.equals(financeReceiptPayVO.getPactType())){
                        CommonBelongerQuery commonBelongerQuery = new CommonBelongerQuery();
                        commonBelongerQuery.setPactCode(financeReceiptPayVO.getPactCode());
                        commonBelongerQuery.setUserRole(StringConsts.BELONGER_PERSON);
                        CommonBelongerVO commonBelongerVO = commonBelongerManager.getBelongersByParam(commonBelongerQuery);
                        if(null != commonBelongerVO){
                            financeReceiptPayVO.setServiceUserName(commonBelongerVO.getUserName());
                            financeReceiptPayVO.setServiceDeptName(commonBelongerVO.getDeptName());
                        }
                    }
                }
            }
            if(financeReceiptPayList.getTotalItem() > 0){
                PageBean<FinanceReceiptPayListResult> resultList = new PageTableBean<FinanceReceiptPayListResult>(query.getPageIndex(),query.getPageSize());
                resultList.setTotalItem(financeReceiptPayList.getTotalItem());
                // copy分页数据
                BeanUtils.copyListProperties(financeReceiptPayList.getData(), resultList.getData(), FinanceReceiptPayListResult.class);
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
    @SuppressWarnings("unused")
    @Override
    public ApiResult<FinanceReceiptPayResult> getAnswerCollectDetail(FinanceReceiptPayQueryParam param) {
        ApiResult<FinanceReceiptPayResult> result = new ApiResult<>();
        Long id = param.getId();
        if(null == id  || id <= 0){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        try {
            FinanceReceiptPayVO financeReceiptPayVO = financeReceiptPayManager.getAnswerCollectDetail(id);
            if(PactTypeConsts.RENT.equals(financeReceiptPayVO.getPactType())){
                CommonBelongerVO rentAfterBelonger = rentBaseManager.getRentAfterBelonger(financeReceiptPayVO.getPactCode());
                if(null != rentAfterBelonger){
                    financeReceiptPayVO.setBelongerUserName(rentAfterBelonger.getUserName());
                    financeReceiptPayVO.setBelongerDeptName(rentAfterBelonger.getDeptName());
                }
            }else if(PactTypeConsts.PURCHASE.equals(financeReceiptPayVO.getPactType())){
                CommonBelongerQuery commonBelongerQuery = new CommonBelongerQuery();
                commonBelongerQuery.setPactCode(financeReceiptPayVO.getPactCode());
                commonBelongerQuery.setUserRole(StringConsts.BELONGER_SERVICE);
                CommonBelongerVO rentAfterBelonger = commonBelongerManager.getBelongersByParam(commonBelongerQuery);
                if(null !=rentAfterBelonger){
                    financeReceiptPayVO.setBelongerUserName(rentAfterBelonger.getUserName());
                    financeReceiptPayVO.setBelongerDeptName(rentAfterBelonger.getDeptName());
                }
            }
            if(null != financeReceiptPayVO){
                FinanceReceiptPayResult financeReceiptPayResult = new FinanceReceiptPayResult();
                BeanUtils.copyProperties(financeReceiptPayVO, financeReceiptPayResult);
                boolean permissionsFlag = getPermissions(param);
                if(!permissionsFlag){
                    result.setCode(PactCommonEnum.FINANCERECEIPTPAY_NO_PERMISSIONS.getCode());
                    result.setMessage(PactCommonEnum.FINANCERECEIPTPAY_NO_PERMISSIONS.getMessage());
                    return result;
                }
                // 根据创建人pin 查询创建人信息及部门*-*-*-*-*-
                String createPin = financeReceiptPayResult.getCreateName();
                ApiResult<PersonalResult> personalResult = personalRpc.getPersonalResultBypin(createPin);
                if(null!=personalResult&&StringConsts.REQUEST_SUCCESS_CODE.equals(personalResult.getCode())){
                    PersonalResult personalResultData = personalResult.getData();
                    financeReceiptPayResult.setCreateName(personalResultData.getEmployeeName());
                    financeReceiptPayResult.setCreateDeptName(personalResultData.getDeptName());
                }
                // 查询实收集合
                List<FinanceReceiptPayVO> financeReceiptPayList = financeReceiptPayManager.listRealCollectByPid(id);
                if(null != financeReceiptPayList && financeReceiptPayList.size() > 0){
                    List<RealReceiptPayResult> realReceiptPayResults = new ArrayList<>();
                    BeanUtils.copyListProperties(financeReceiptPayList, realReceiptPayResults, RealReceiptPayResult.class);
                    List<Long> pids = new ArrayList<>();
                    for (RealReceiptPayResult realReceiptPayResult : realReceiptPayResults) {
                        // 根据创建人pin 查询收款人信息及部门*-*-*-*-*-
                        String paymentPin = realReceiptPayResult.getCreateName();
                        ApiResult<PersonalResult> realPersonalResult = personalRpc.getPersonalResultBypin(paymentPin);
                        if(null!=realPersonalResult&&StringConsts.REQUEST_SUCCESS_CODE.equals(realPersonalResult.getCode())){
                            PersonalResult personalResultData = personalResult.getData();
                            realReceiptPayResult.setCreateName(personalResultData.getEmployeeName());
                            realReceiptPayResult.setCreateDeptName(personalResultData.getDeptName());
                        }
                        pids.add(realReceiptPayResult.getId());
                    }
                    
                    
                    // 实收凭证
                    List<FinanceReceiptPayVoucherDO> voucherList = financeReceiptPayVoucherManager.listVoucherByPids(pids);
                    if(null != voucherList){
                        for (RealReceiptPayResult realReceiptPayResult : realReceiptPayResults) {
                            List<FinanceReceiptPayVoucherDO> financeReceiptPayVoucherList = new ArrayList<>();
                            for (FinanceReceiptPayVoucherDO financeReceiptPayVoucherDO : voucherList) {
                                if(realReceiptPayResult.getId().equals(financeReceiptPayVoucherDO.getReceiptPayId())){
                                    financeReceiptPayVoucherList.add(financeReceiptPayVoucherDO);
                                }
                            }
                            List<FinanceReceiptPayVoucherResult> financeReceiptPayVoucherResults = new ArrayList<>();
                            BeanUtils.copyListProperties(financeReceiptPayVoucherList, financeReceiptPayVoucherResults, FinanceReceiptPayVoucherResult.class);
                            realReceiptPayResult.setFinanceReceiptPayVoucherList(financeReceiptPayVoucherResults);
                        }
                    }
                    // 实收确认信息，根据实收id查询操作日志*-*-*-*-*-*
                    for (RealReceiptPayResult realReceiptPayResult : realReceiptPayResults) {
                        Long realReceiptPayId = realReceiptPayResult.getId();
                        OperateLogParam operateLogParam = new OperateLogParam();
                        operateLogParam.setServiceCode(String.valueOf(realReceiptPayId));
//                        operateLogParam.setServiceType(StringConsts.REALCOLLECTCONFIRM);
                        List<OperateLogResult> operateRecordList = operateLogRpc.listOperateRecord(operateLogParam);
                        OperateLogResult operateLogResult = new OperateLogResult();
                        if(null!=operateRecordList&&operateRecordList.size()>0){
                             operateLogResult = operateRecordList.get(0);
                        }
                        realReceiptPayResult.setConfirmUserName(operateLogResult.getOperateName());
                        realReceiptPayResult.setConfirmDeptName(operateLogResult.getOperateDept());
                        realReceiptPayResult.setConfirmTime(operateLogResult.getOperateTime());
                    }
                    financeReceiptPayResult.setRealReceiptPayList(realReceiptPayResults);
                    
                }
                result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                result.setData(financeReceiptPayResult);
            }else{
                // 收支id无效
                result.setCode(PactCommonEnum.FINANCE_RECEIPT_PAY_ID_INVALID.getCode());
                result.setMessage(PactCommonEnum.FINANCE_RECEIPT_PAY_ID_INVALID.getMessage());
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
    * @Title: getPermissions
    * @Description: TODO(  查看登录人有没有某个收支 的权限 )
    * @author GuoXiaoPeng
    * @param param 收支id和权限作用域
    * @return 有权限返回true否则false
     */
    private boolean getPermissions(FinanceReceiptPayQueryParam param) {
        boolean flag;
        ApiResult<PersonalResult> personalApiResult = personalRpc.getPersonalResultBypin(param.getCreateName());
        PersonalResult loginPersonal = personalApiResult.getData();
        String position = loginPersonal.getPosition();
        Integer scope = param.getScope();
        FinanceReceiptPayQuery query = new FinanceReceiptPayQuery();
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
        query.setId(param.getId());
        flag = financeReceiptPayManager.getPermissions(query);
        return flag;
    }

    @SuppressWarnings("unused")
    @Override
    public ApiResult<RealReceiptPayResult> getRealCollectDetail(FinanceReceiptPayQueryParam param) {
        ApiResult<RealReceiptPayResult> result = new ApiResult<>();
        Long id = param.getId();
        if(null == id  || id <= 0){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        try {
            boolean permissionsFlag = getPermissions(param);
            if(!permissionsFlag){
                result.setCode(PactCommonEnum.FINANCERECEIPTPAY_NO_PERMISSIONS.getCode());
                result.setMessage(PactCommonEnum.FINANCERECEIPTPAY_NO_PERMISSIONS.getMessage());
                return result;
            }
            FinanceReceiptPayVO financeReceiptPayVO = financeReceiptPayManager.getRealCollectDetail(id);
            if(PactTypeConsts.RENT.equals(financeReceiptPayVO.getPactType())){
                CommonBelongerVO rentAfterBelonger = rentBaseManager.getRentAfterBelonger(financeReceiptPayVO.getPactCode());
                if(null !=rentAfterBelonger){
                    financeReceiptPayVO.setBelongerUserName(rentAfterBelonger.getUserName());
                    financeReceiptPayVO.setBelongerDeptName(rentAfterBelonger.getDeptName());
                }
            }else if(PactTypeConsts.PURCHASE.equals(financeReceiptPayVO.getPactType())){
                CommonBelongerQuery commonBelongerQuery = new CommonBelongerQuery();
                commonBelongerQuery.setPactCode(financeReceiptPayVO.getPactCode());
                commonBelongerQuery.setUserRole(StringConsts.BELONGER_SERVICE);
                CommonBelongerVO rentAfterBelonger = commonBelongerManager.getBelongersByParam(commonBelongerQuery);
                if(null !=rentAfterBelonger){
                    financeReceiptPayVO.setBelongerUserName(rentAfterBelonger.getUserName());
                    financeReceiptPayVO.setBelongerDeptName(rentAfterBelonger.getDeptName());
                }
            }
            
            if(null != financeReceiptPayVO){
                RealReceiptPayResult realReceiptPayResult = new RealReceiptPayResult();
                BeanUtils.copyProperties(financeReceiptPayVO, realReceiptPayResult);
                
                // 根据创建人pin 查询创建人部门*-*-*-*-*-
                String paymentPin = realReceiptPayResult.getCreateName();
                ApiResult<PersonalResult> paymentPinPersonalResult = personalRpc.getPersonalResultBypin(paymentPin);
                PersonalResult realReceiptPayData = paymentPinPersonalResult.getData();
                realReceiptPayResult.setCreateName(realReceiptPayData.getEmployeeName());
                realReceiptPayResult.setCreateDeptName(realReceiptPayData.getDeptName());
                
                
                // 实收凭证
                List<Long> pids = new ArrayList<>();
                pids.add(id);
                List<FinanceReceiptPayVoucherDO> voucherList = financeReceiptPayVoucherManager.listVoucherByPids(pids);
                if(null != voucherList){
                    List<FinanceReceiptPayVoucherResult> financeReceiptPayVoucherResults = new ArrayList<>();
                    BeanUtils.copyListProperties(voucherList, financeReceiptPayVoucherResults, FinanceReceiptPayVoucherResult.class);
                    realReceiptPayResult.setFinanceReceiptPayVoucherList(financeReceiptPayVoucherResults);
                }
                
                // 实收确认信息，根据实收id查询操作日志*-*-*-*-*-*
                OperateLogParam operateLogParam = new OperateLogParam();
                operateLogParam.setServiceCode(String.valueOf(id));
                List<OperateLogResult> operateRecordList = operateLogRpc.listOperateRecord(operateLogParam);
                OperateLogResult operateLogResult = new OperateLogResult();
                if(null!=operateRecordList&&operateRecordList.size()>0){
                     operateLogResult = operateRecordList.get(0);
                }
                realReceiptPayResult.setConfirmUserName(operateLogResult.getOperateName());
                realReceiptPayResult.setConfirmDeptName(operateLogResult.getOperateDept());
                realReceiptPayResult.setConfirmTime(operateLogResult.getOperateTime());
                
                result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                result.setData(realReceiptPayResult);
            }else{
                // 收支id无效
                result.setCode(PactCommonEnum.FINANCE_RECEIPT_PAY_ID_INVALID.getCode());
                result.setMessage(PactCommonEnum.FINANCE_RECEIPT_PAY_ID_INVALID.getMessage());
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

    @SuppressWarnings("unused")
    @Override
    public ApiResult<FinanceReceiptPayResult> getAnswerExpendDetail(FinanceReceiptPayQueryParam param) {
        ApiResult<FinanceReceiptPayResult> result = new ApiResult<>();
        Long id = param.getId();
        if(null == id || id <= 0){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        try {
            boolean permissionsFlag = getPermissions(param);
            if(!permissionsFlag){
                result.setCode(PactCommonEnum.FINANCERECEIPTPAY_NO_PERMISSIONS.getCode());
                result.setMessage(PactCommonEnum.FINANCERECEIPTPAY_NO_PERMISSIONS.getMessage());
                return result;
            }
            FinanceReceiptPayVO financeReceiptPayVO = financeReceiptPayManager.getAnswerExpendDetail(id);
            if(PactTypeConsts.RENT.equals(financeReceiptPayVO.getPactType())){
                CommonBelongerVO rentAfterBelonger = rentBaseManager.getRentAfterBelonger(financeReceiptPayVO.getPactCode());
                if(null !=rentAfterBelonger){
                    financeReceiptPayVO.setBelongerUserName(rentAfterBelonger.getUserName());
                    financeReceiptPayVO.setBelongerDeptName(rentAfterBelonger.getDeptName());
                }
            }else if(PactTypeConsts.PURCHASE.equals(financeReceiptPayVO.getPactType())){
                CommonBelongerQuery commonBelongerQuery = new CommonBelongerQuery();
                commonBelongerQuery.setPactCode(financeReceiptPayVO.getPactCode());
                commonBelongerQuery.setUserRole(StringConsts.BELONGER_SERVICE);
                CommonBelongerVO rentAfterBelonger = commonBelongerManager.getBelongersByParam(commonBelongerQuery);
                if(null !=rentAfterBelonger){
                    financeReceiptPayVO.setBelongerUserName(rentAfterBelonger.getUserName());
                    financeReceiptPayVO.setBelongerDeptName(rentAfterBelonger.getDeptName());
                }
            }
            if(null != financeReceiptPayVO){
                FinanceReceiptPayResult financeReceiptPayResult = new FinanceReceiptPayResult();
                BeanUtils.copyProperties(financeReceiptPayVO, financeReceiptPayResult);
                
                // 根据创建人pin 查询创建人信息及部门*-*-*-*-*-
                String createPin = financeReceiptPayResult.getCreateName();
                ApiResult<PersonalResult> personalResult = personalRpc.getPersonalResultBypin(createPin);
                PersonalResult personalResultData = personalResult.getData();
                financeReceiptPayResult.setCreateName(personalResultData.getEmployeeName());
                financeReceiptPayResult.setCreateDeptName(personalResultData.getDeptName());
                
                // 查询实支集合
                List<FinanceReceiptPayVO> financeReceiptPayList = financeReceiptPayManager.listRealExpendByPid(id);
                if(null != financeReceiptPayList && financeReceiptPayList.size() > 0){
                    List<RealReceiptPayResult> realReceiptPayResults = new ArrayList<>();
                    if(null != financeReceiptPayList){
                        BeanUtils.copyListProperties(financeReceiptPayList, realReceiptPayResults, RealReceiptPayResult.class);
                    }
                    List<Long> pids = new ArrayList<>();
                    for (RealReceiptPayResult realReceiptPayResult : realReceiptPayResults) {
                        // 根据创建人pin 查询申请人信息及部门*-*-*-*-*-
                        String applyPin = realReceiptPayResult.getCreateName();
                        if(StringUtils.isNotBlank(applyPin)){
                            ApiResult<PersonalResult> applyPinPersonalResult = personalRpc.getPersonalResultBypin(applyPin);
                            PersonalResult applyPersonalResult = applyPinPersonalResult.getData();
                            realReceiptPayResult.setCreateName(applyPersonalResult.getEmployeeName());
                            realReceiptPayResult.setCreateDeptName(applyPersonalResult.getDeptName());
                        }
                        pids.add(realReceiptPayResult.getId());
                    }
                    
                    
                    // 实支凭证
                    List<FinanceReceiptPayVoucherDO> voucherList = financeReceiptPayVoucherManager.listVoucherByPids(pids);
                    if(null != voucherList){
                        for (RealReceiptPayResult realReceiptPayResult : realReceiptPayResults) {
                            List<FinanceReceiptPayVoucherDO> financeReceiptPayVoucherList = new ArrayList<>();
                            for (FinanceReceiptPayVoucherDO financeReceiptPayVoucherDO : voucherList) {
                                if(realReceiptPayResult.getId().equals(financeReceiptPayVoucherDO.getReceiptPayId())){
                                    financeReceiptPayVoucherList.add(financeReceiptPayVoucherDO);
                                    realReceiptPayResult.setPaymentUserName(financeReceiptPayVoucherDO.getCreateName());
                                    realReceiptPayResult.setPaymentTime(financeReceiptPayVoucherDO.getCreate());
                                }
                            }
                            // 根据付款人pin 查询付款人信息及部门*-*-*-*-*-
                            String paymentUserPin = realReceiptPayResult.getPaymentUserName();
                            if(StringUtils.isNotBlank(paymentUserPin)){
                                ApiResult<PersonalResult> paymentPersonalResult = personalRpc.getPersonalResultBypin(paymentUserPin);
                                PersonalResult paymentUserPersonalResult = paymentPersonalResult.getData();
                                realReceiptPayResult.setPaymentUserName(paymentUserPersonalResult.getEmployeeName());
                                realReceiptPayResult.setPaymentDeptName(paymentUserPersonalResult.getDeptName());
                            }
                            List<FinanceReceiptPayVoucherResult> financeReceiptPayVoucherResults = new ArrayList<>();
                            BeanUtils.copyListProperties(financeReceiptPayVoucherList, financeReceiptPayVoucherResults, FinanceReceiptPayVoucherResult.class);
                            realReceiptPayResult.setFinanceReceiptPayVoucherList(financeReceiptPayVoucherResults);
                        }
                    }
                    // 实支审核信息，根据实支id查询操作日志*-*-*-*-*-*
                    for (RealReceiptPayResult realReceiptPayResult : realReceiptPayResults) {
                        Long realReceiptPayId = realReceiptPayResult.getId();
                        OperateLogParam operateLogParam = new OperateLogParam();
                        operateLogParam.setServiceCode(String.valueOf(realReceiptPayId));
                        operateLogParam.setServiceType(StringConsts.REAL_SUPPORT_AUDIT);
                        List<OperateLogResult> operateRecordList = operateLogRpc.listOperateRecord(operateLogParam);
                        OperateLogResult operateLogResult = new OperateLogResult();
                        if(null!=operateRecordList&&operateRecordList.size()>0){
                             operateLogResult = operateRecordList.get(0);
                        }
                        realReceiptPayResult.setReviewUserName(operateLogResult.getOperateName());
                        realReceiptPayResult.setReviewDeptName(operateLogResult.getOperateDept());
                        realReceiptPayResult.setReviewTime(operateLogResult.getOperateTime());
                    }
                    financeReceiptPayResult.setRealReceiptPayList(realReceiptPayResults);
                }
                result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                result.setData(financeReceiptPayResult);
            }else{// 收支id无效
                result.setCode(PactCommonEnum.FINANCE_RECEIPT_PAY_ID_INVALID.getCode());
                result.setMessage(PactCommonEnum.FINANCE_RECEIPT_PAY_ID_INVALID.getMessage());
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

    @SuppressWarnings("unused")
    @Override
    public ApiResult<RealReceiptPayResult> getRealExpendDetail(FinanceReceiptPayQueryParam param) {
        ApiResult<RealReceiptPayResult> result = new ApiResult<>();
        Long id = param.getId();
        if(null == id || id <= 0){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        try {
            boolean permissionsFlag = getPermissions(param);
            if(!permissionsFlag){
                result.setCode(PactCommonEnum.FINANCERECEIPTPAY_NO_PERMISSIONS.getCode());
                result.setMessage(PactCommonEnum.FINANCERECEIPTPAY_NO_PERMISSIONS.getMessage());
                return result;
            }
            FinanceReceiptPayVO financeReceiptPayVO = financeReceiptPayManager.getRealExpendDetail(id);
            if(PactTypeConsts.RENT.equals(financeReceiptPayVO.getPactType())){
                CommonBelongerVO rentAfterBelonger = rentBaseManager.getRentAfterBelonger(financeReceiptPayVO.getPactCode());
                if(null !=rentAfterBelonger){
                    financeReceiptPayVO.setBelongerUserName(rentAfterBelonger.getUserName());
                    financeReceiptPayVO.setBelongerDeptName(rentAfterBelonger.getDeptName());
                }
            }else if(PactTypeConsts.PURCHASE.equals(financeReceiptPayVO.getPactType())){
                CommonBelongerQuery commonBelongerQuery = new CommonBelongerQuery();
                commonBelongerQuery.setPactCode(financeReceiptPayVO.getPactCode());
                commonBelongerQuery.setUserRole(StringConsts.BELONGER_SERVICE);
                CommonBelongerVO rentAfterBelonger = commonBelongerManager.getBelongersByParam(commonBelongerQuery);
                if(null !=rentAfterBelonger){
                    financeReceiptPayVO.setBelongerUserName(rentAfterBelonger.getUserName());
                    financeReceiptPayVO.setBelongerDeptName(rentAfterBelonger.getDeptName());
                }
            }
            if(null != financeReceiptPayVO){
                RealReceiptPayResult realReceiptPayResult = new RealReceiptPayResult();
                BeanUtils.copyProperties(financeReceiptPayVO, realReceiptPayResult);
                
                
                // 根据创建人pin 查询创建人部门*-*-*-*-*-
                String paymentPin = realReceiptPayResult.getCreateName();
                ApiResult<PersonalResult> paymentPersonalResult = personalRpc.getPersonalResultBypin(paymentPin);
                PersonalResult personalResult = paymentPersonalResult.getData();
                realReceiptPayResult.setCreateName(personalResult.getEmployeeName());
                realReceiptPayResult.setCreateDeptName(personalResult.getDeptName());
                
                
                financeReceiptPayVO = financeReceiptPayManager.getAnswerExpendDetail(financeReceiptPayVO.getPid());
                if(null != financeReceiptPayVO){
                    realReceiptPayResult.setAnswerCostType(financeReceiptPayVO.getCostType());
                    realReceiptPayResult.setAnswerCostAmount(financeReceiptPayVO.getCostAmount());
                    realReceiptPayResult.setAnswerStartTime(financeReceiptPayVO.getStartTime());
                    realReceiptPayResult.setAnswerEndTime(financeReceiptPayVO.getEndTime());
                    realReceiptPayResult.setAnswerDescribe(financeReceiptPayVO.getDescribe());
                    realReceiptPayResult.setAnswerCostTime(financeReceiptPayVO.getCostTime());
                    realReceiptPayResult.setAnswerCreate(financeReceiptPayVO.getCreate());
                    // 根据应支创建人pin 查询创建人部门*-*-*-*-*-
                    String createPin = financeReceiptPayVO.getCreateName();
                    ApiResult<PersonalResult> realReceiptPersonalResult = personalRpc.getPersonalResultBypin(createPin);
                    PersonalResult personalResultData = realReceiptPersonalResult.getData();
                    realReceiptPayResult.setAnswerCreateName(personalResultData.getEmployeeName());
                    realReceiptPayResult.setAnswerCreateDeptName(personalResultData.getDeptName());
                    realReceiptPayResult.setAnswerUnsupportedAmount(financeReceiptPayVO.getUnsupportedAmount());
                }
                
                // 实支凭证
                List<Long> pids = new ArrayList<>();
                pids.add(id);
                List<FinanceReceiptPayVoucherDO> voucherList = financeReceiptPayVoucherManager.listVoucherByPids(pids);
                if(null != voucherList){
                    List<FinanceReceiptPayVoucherResult> financeReceiptPayVoucherResults = new ArrayList<>();
                    BeanUtils.copyListProperties(voucherList, financeReceiptPayVoucherResults, FinanceReceiptPayVoucherResult.class);
                    realReceiptPayResult.setFinanceReceiptPayVoucherList(financeReceiptPayVoucherResults);
                }
                
                // 实支审核信息，根据实支id查询操作日志*-*-*-*-*-*
                OperateLogParam operateLogParam = new OperateLogParam();
                operateLogParam.setServiceCode(String.valueOf(id));
                operateLogParam.setServiceType(StringConsts.REAL_SUPPORT_AUDIT);
                List<OperateLogResult> operateRecordList = operateLogRpc.listOperateRecord(operateLogParam);
                OperateLogResult operateLogResult = new OperateLogResult();
                if(null!=operateRecordList&&operateRecordList.size()>0){
                     operateLogResult = operateRecordList.get(0);
                }
                realReceiptPayResult.setReviewUserName(operateLogResult.getOperateName());
                realReceiptPayResult.setReviewDeptName(operateLogResult.getOperateDept());
                realReceiptPayResult.setReviewTime(operateLogResult.getOperateTime());
                
                // 根据应支付款人pin 查询付款人部门*-*-*-*-*-
                operateLogParam.setServiceCode(String.valueOf(id));
                operateLogParam.setServiceType(StringConsts.ANSWEREXPENDRECEIPT);
                List<OperateLogResult> operateRecordPayList = operateLogRpc.listOperateRecord(operateLogParam);
                OperateLogResult operateLogPayResult = new OperateLogResult();
                if(null!=operateRecordPayList&&operateRecordPayList.size()>0){
                    operateLogPayResult = operateRecordPayList.get(0);
                }
                if(null != voucherList && voucherList.size()>0){
                    realReceiptPayResult.setPaymentTime(voucherList.get(0).getCreate());
                    realReceiptPayResult.setPaymentUserName(operateLogPayResult.getOperateName());
                    realReceiptPayResult.setPaymentDeptName(operateLogPayResult.getOperateDept());
                }
                result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                result.setData(realReceiptPayResult);
            }else{// 收支id无效
                result.setCode(PactCommonEnum.FINANCE_RECEIPT_PAY_ID_INVALID.getCode());
                result.setMessage(PactCommonEnum.FINANCE_RECEIPT_PAY_ID_INVALID.getMessage());
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
    public ApiResult<PageBean<FinanceReceiptPayListResult>> listReceivReceiptPay(FinanceReceiptPayQueryParam param) {
        ApiResult<PageBean<FinanceReceiptPayListResult>> result = new ApiResult<>();
        if(null == param){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        try {
            FinanceReceiptPayQuery query = new FinanceReceiptPayQuery();
            BeanUtils.copyProperties(param, query);
            initPermissionsQuery(param,query);
            PageBean<FinanceReceiptPayVO> financeReceiptPayList = financeReceiptPayManager.listReceivReceiptPay(query);
            if(financeReceiptPayList.getTotalItem() > 0){
                PageBean<FinanceReceiptPayListResult> resultList = new PageTableBean<FinanceReceiptPayListResult>(query.getPageIndex(),query.getPageSize());
                resultList.setTotalItem(financeReceiptPayList.getTotalItem());
                // copy分页数据
                BeanUtils.copyListProperties(financeReceiptPayList.getData(), resultList.getData(), FinanceReceiptPayListResult.class);
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
    public ApiResult<PageBean<FinanceReceiptPayListResult>> listNetReceiptPay(FinanceReceiptPayQueryParam param) {
        ApiResult<PageBean<FinanceReceiptPayListResult>> result = new ApiResult<>();
        if(null == param){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        try {
            FinanceReceiptPayQuery query = new FinanceReceiptPayQuery();
            BeanUtils.copyProperties(param, query);
            initPermissionsQuery(param, query);
            PageBean<FinanceReceiptPayVO> financeReceiptPayList = financeReceiptPayManager.listNetReceiptPay(query);
            if(financeReceiptPayList.getTotalItem() > 0){
                PageBean<FinanceReceiptPayListResult> resultList = new PageTableBean<FinanceReceiptPayListResult>(query.getPageIndex(),query.getPageSize());
                resultList.setTotalItem(financeReceiptPayList.getTotalItem());
                // copy分页数据
                BeanUtils.copyListProperties(financeReceiptPayList.getData(), resultList.getData(), FinanceReceiptPayListResult.class);
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
    public ApiResult<FinanceReceiptPayResult> getPactAnswerCollectDetail(FinanceReceiptPayQueryParam param) {
        ApiResult<FinanceReceiptPayResult> result = new ApiResult<>();
        Long id = param.getId();
        if(null == id || id <= 0){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        try {
            boolean permissionsFlag = getPermissions(param);
            if(!permissionsFlag){
                result.setCode(PactCommonEnum.FINANCERECEIPTPAY_NO_PERMISSIONS.getCode());
                result.setMessage(PactCommonEnum.FINANCERECEIPTPAY_NO_PERMISSIONS.getMessage());
                return result;
            }
            FinanceReceiptPayVO financeReceiptPayVO = financeReceiptPayManager.getPactAnswerCollectDetail(id);
            if(null != financeReceiptPayVO){
                FinanceReceiptPayResult financeReceiptPayResult = new FinanceReceiptPayResult();
                BeanUtils.copyProperties(financeReceiptPayVO, financeReceiptPayResult);
                
                // 根据创建人pin 查询创建人信息及部门*-*-*-*-*-
                String createPin = financeReceiptPayResult.getCreateName();
                ApiResult<PersonalResult> personalResult = personalRpc.getPersonalResultBypin(createPin);
                PersonalResult personalResultData = personalResult.getData();
                financeReceiptPayResult.setCreateName(personalResultData.getEmployeeName());
                financeReceiptPayResult.setCreateDeptName(personalResultData.getDeptName());
                
                // 查询实收集合
                List<FinanceReceiptPayVO> financeReceiptPayList = financeReceiptPayManager.listRealCollectByPid(id);
                if(null != financeReceiptPayList && financeReceiptPayList.size() > 0){
                    List<RealReceiptPayResult> realReceiptPayResults = new ArrayList<>();
                    BeanUtils.copyListProperties(financeReceiptPayList, realReceiptPayResults, RealReceiptPayResult.class);
                    List<Long> pids = new ArrayList<>();
                    for (RealReceiptPayResult realReceiptPayResult : realReceiptPayResults) {
                        // 根据创建人pin 查询收款人信息及部门*-*-*-*-*-
                        String paymentPin = realReceiptPayResult.getCreateName();
                        ApiResult<PersonalResult> payPersonalResult = personalRpc.getPersonalResultBypin(paymentPin);
                        PersonalResult paypPersonalResult = payPersonalResult.getData();
                        realReceiptPayResult.setCreateName(paypPersonalResult.getEmployeeName());
                        realReceiptPayResult.setCreateDeptName(paypPersonalResult.getDeptName());
                        pids.add(realReceiptPayResult.getId());
                    }
                    
                    
                    // 实收凭证
                    List<FinanceReceiptPayVoucherDO> voucherList = financeReceiptPayVoucherManager.listVoucherByPids(pids);
                    if(null != voucherList){
                        for (RealReceiptPayResult realReceiptPayResult : realReceiptPayResults) {
                            List<FinanceReceiptPayVoucherDO> financeReceiptPayVoucherList = new ArrayList<>();
                            for (FinanceReceiptPayVoucherDO financeReceiptPayVoucherDO : voucherList) {
                                if(realReceiptPayResult.getId().equals(financeReceiptPayVoucherDO.getReceiptPayId())){
                                    financeReceiptPayVoucherList.add(financeReceiptPayVoucherDO);
                                }
                            }
                            List<FinanceReceiptPayVoucherResult> financeReceiptPayVoucherResults = new ArrayList<>();
                            BeanUtils.copyListProperties(financeReceiptPayVoucherList, financeReceiptPayVoucherResults, FinanceReceiptPayVoucherResult.class);
                            realReceiptPayResult.setFinanceReceiptPayVoucherList(financeReceiptPayVoucherResults);
                        }
                    }
                    // 实收确认信息，根据实收id查询操作日志*-*-*-*-*-*
                    for (RealReceiptPayResult realReceiptPayResult : realReceiptPayResults) {
                        Long realReceiptPayId = realReceiptPayResult.getId();
                        OperateLogParam operateLogParam = new OperateLogParam();
                        operateLogParam.setServiceCode(String.valueOf(realReceiptPayId));
//                        operateLogParam.setServiceType(StringConsts.REALCOLLECTCONFIRM);
                        List<OperateLogResult> operateRecordList = operateLogRpc.listOperateRecord(operateLogParam);
                        OperateLogResult operateLogResult = new OperateLogResult();
                        if(null!=operateRecordList&&operateRecordList.size()>0){
                             operateLogResult = operateRecordList.get(0);
                        }
                        realReceiptPayResult.setConfirmUserName(operateLogResult.getOperateName());
                        realReceiptPayResult.setConfirmDeptName(operateLogResult.getOperateDept());
                        realReceiptPayResult.setConfirmTime(operateLogResult.getOperateTime());
                    }
                    financeReceiptPayResult.setRealReceiptPayList(realReceiptPayResults);
                    
                }
                result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                result.setData(financeReceiptPayResult);
            }else{// 收支id无效
                result.setCode(PactCommonEnum.FINANCE_RECEIPT_PAY_ID_INVALID.getCode());
                result.setMessage(PactCommonEnum.FINANCE_RECEIPT_PAY_ID_INVALID.getMessage());
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
    public ApiResult<RealReceiptPayResult> getPactRealCollectDetail(FinanceReceiptPayQueryParam param) {
        ApiResult<RealReceiptPayResult> result = new ApiResult<>();
        Long id = param.getId();
        if(null == id  || id <= 0){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        try {
            boolean permissionsFlag = getPermissions(param);
            if(!permissionsFlag){
                result.setCode(PactCommonEnum.FINANCERECEIPTPAY_NO_PERMISSIONS.getCode());
                result.setMessage(PactCommonEnum.FINANCERECEIPTPAY_NO_PERMISSIONS.getMessage());
                return result;
            }
            FinanceReceiptPayVO financeReceiptPayVO = financeReceiptPayManager.getPactRealCollectDetail(id);
            if(null != financeReceiptPayVO){
                RealReceiptPayResult realReceiptPayResult = new RealReceiptPayResult();
                BeanUtils.copyProperties(financeReceiptPayVO, realReceiptPayResult);
                
                // 根据创建人pin 查询创建人部门*-*-*-*-*-
                String paymentPin = realReceiptPayResult.getCreateName();
                ApiResult<PersonalResult> personalResult = personalRpc.getPersonalResultBypin(paymentPin);
                PersonalResult personalResultData = personalResult.getData();
                realReceiptPayResult.setCreateName(personalResultData.getEmployeeName());
                realReceiptPayResult.setCreateDeptName(personalResultData.getDeptName());
               
                
                // 实收凭证
                List<Long> pids = new ArrayList<>();
                pids.add(id);
                List<FinanceReceiptPayVoucherDO> voucherList = financeReceiptPayVoucherManager.listVoucherByPids(pids);
                if(null != voucherList){
                    List<FinanceReceiptPayVoucherResult> financeReceiptPayVoucherResults = new ArrayList<>();
                    BeanUtils.copyListProperties(voucherList, financeReceiptPayVoucherResults, FinanceReceiptPayVoucherResult.class);
                    realReceiptPayResult.setFinanceReceiptPayVoucherList(financeReceiptPayVoucherResults);
                }
                // 实收确认信息，根据实收id查询操作日志*-*-*-*-*-*
                OperateLogParam operateLogParam = new OperateLogParam();
                operateLogParam.setServiceCode(String.valueOf(id));
                List<OperateLogResult> operateRecordList = operateLogRpc.listOperateRecord(operateLogParam);
                OperateLogResult operateLogResult = new OperateLogResult();
                if(null!=operateRecordList&&operateRecordList.size()>0){
                     operateLogResult = operateRecordList.get(0);
                }
                realReceiptPayResult.setConfirmUserName(operateLogResult.getOperateName());
                realReceiptPayResult.setConfirmDeptName(operateLogResult.getOperateDept());
                realReceiptPayResult.setConfirmTime(operateLogResult.getOperateTime());
                
                result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                result.setData(realReceiptPayResult);
            }else{// 收支id无效
                result.setCode(PactCommonEnum.FINANCE_RECEIPT_PAY_ID_INVALID.getCode());
                result.setMessage(PactCommonEnum.FINANCE_RECEIPT_PAY_ID_INVALID.getMessage());
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
    public ApiResult<FinanceReceiptPayResult> getPactAnswerExpendDetail(FinanceReceiptPayQueryParam param) {
        ApiResult<FinanceReceiptPayResult> result = new ApiResult<>();
        Long id = param.getId();
        if(null == id || id <= 0){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        try {
            boolean permissionsFlag = getPermissions(param);
            if(!permissionsFlag){
                result.setCode(PactCommonEnum.FINANCERECEIPTPAY_NO_PERMISSIONS.getCode());
                result.setMessage(PactCommonEnum.FINANCERECEIPTPAY_NO_PERMISSIONS.getMessage());
                return result;
            }
            FinanceReceiptPayVO financeReceiptPayVO = financeReceiptPayManager.getPactAnswerExpendDetail(id);
            if(null != financeReceiptPayVO){
                FinanceReceiptPayResult financeReceiptPayResult = new FinanceReceiptPayResult();
                BeanUtils.copyProperties(financeReceiptPayVO, financeReceiptPayResult);
                
                // 根据创建人pin 查询创建人信息及部门*-*-*-*-*-
                String createPin = financeReceiptPayResult.getCreateName();
                ApiResult<PersonalResult> personalResult = personalRpc.getPersonalResultBypin(createPin);
                PersonalResult personalResultData = personalResult.getData();
                financeReceiptPayResult.setCreateName(personalResultData.getEmployeeName());
                financeReceiptPayResult.setCreateDeptName(personalResultData.getDeptName());
                
                // 查询实支集合
                List<FinanceReceiptPayVO> financeReceiptPayList = financeReceiptPayManager.listRealExpendByPid(id);
                if(null != financeReceiptPayList && financeReceiptPayList.size() > 0){
                    List<RealReceiptPayResult> realReceiptPayResults = new ArrayList<>();
                    BeanUtils.copyListProperties(financeReceiptPayList, realReceiptPayResults, RealReceiptPayResult.class);
                    List<Long> pids = new ArrayList<>();
                    for (RealReceiptPayResult realReceiptPayResult : realReceiptPayResults) {
                        // 根据创建人pin 查询申请人信息及部门*-*-*-*-*-
                        String applyPin = realReceiptPayResult.getCreateName();
                        ApiResult<PersonalResult> applyPersonalResult = personalRpc.getPersonalResultBypin(applyPin);
                        PersonalResult applyPersonalResultData = applyPersonalResult.getData();
                        realReceiptPayResult.setCreateName(applyPersonalResultData.getEmployeeName());
                        realReceiptPayResult.setCreateDeptName(applyPersonalResultData.getDeptName());
                        pids.add(realReceiptPayResult.getId());
                    }
                    
                    
                    // 实支凭证
                    List<FinanceReceiptPayVoucherDO> voucherList = financeReceiptPayVoucherManager.listVoucherByPids(pids);
                    if(null != voucherList){
                        for (RealReceiptPayResult realReceiptPayResult : realReceiptPayResults) {
                            List<FinanceReceiptPayVoucherDO> financeReceiptPayVoucherList = new ArrayList<>();
                            for (FinanceReceiptPayVoucherDO financeReceiptPayVoucherDO : voucherList) {
                                if(realReceiptPayResult.getId().equals(financeReceiptPayVoucherDO.getReceiptPayId())){
                                    financeReceiptPayVoucherList.add(financeReceiptPayVoucherDO);
                                    realReceiptPayResult.setPaymentUserName(financeReceiptPayVoucherDO.getCreateName());
                                    realReceiptPayResult.setPaymentTime(financeReceiptPayVoucherDO.getCreate());
                                }
                            }
                            // 根据付款人pin 查询付款人信息及部门*-*-*-*-*-
                            String paymentUserPin = realReceiptPayResult.getPaymentUserName();
                            if(StringUtils.isNotBlank(paymentUserPin)){
                                ApiResult<PersonalResult> paymentPersonalResult = personalRpc.getPersonalResultBypin(paymentUserPin);
                                PersonalResult paymentPersonalResultData = paymentPersonalResult.getData();
                                realReceiptPayResult.setPaymentUserName(paymentPersonalResultData.getEmployeeName());
                                realReceiptPayResult.setPaymentDeptName(paymentPersonalResultData.getDeptName());
                            }
                            List<FinanceReceiptPayVoucherResult> financeReceiptPayVoucherResults = new ArrayList<>();
                            BeanUtils.copyListProperties(financeReceiptPayVoucherList, financeReceiptPayVoucherResults, FinanceReceiptPayVoucherResult.class);
                            realReceiptPayResult.setFinanceReceiptPayVoucherList(financeReceiptPayVoucherResults);
                        }
                    }
                    // 实支审核信息，根据实支id查询操作日志*-*-*-*-*-*
                    for (RealReceiptPayResult realReceiptPayResult : realReceiptPayResults) {
                        Long realReceiptPayId = realReceiptPayResult.getId();
                        OperateLogParam operateLogParam = new OperateLogParam();
                        operateLogParam.setServiceCode(String.valueOf(realReceiptPayId));
                        operateLogParam.setServiceType(StringConsts.REAL_SUPPORT_AUDIT);
                        List<OperateLogResult> operateRecordList = operateLogRpc.listOperateRecord(operateLogParam);
                        OperateLogResult operateLogResult = new OperateLogResult();
                        if(null!=operateRecordList&&operateRecordList.size()>0){
                             operateLogResult = operateRecordList.get(0);
                        }
                        realReceiptPayResult.setReviewUserName(operateLogResult.getOperateName());
                        realReceiptPayResult.setReviewDeptName(operateLogResult.getOperateDept());
                        realReceiptPayResult.setReviewTime(operateLogResult.getOperateTime());
                    }
                    financeReceiptPayResult.setRealReceiptPayList(realReceiptPayResults);
                }
                result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                result.setData(financeReceiptPayResult);
            }else{// 收支id无效
                result.setCode(PactCommonEnum.FINANCE_RECEIPT_PAY_ID_INVALID.getCode());
                result.setMessage(PactCommonEnum.FINANCE_RECEIPT_PAY_ID_INVALID.getMessage());
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
    public ApiResult<RealReceiptPayResult> getPactRealExpendDetail(FinanceReceiptPayQueryParam param) {
        ApiResult<RealReceiptPayResult> result = new ApiResult<>();
        Long id = param.getId();
        if(null == id || id <= 0){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        try {
            boolean permissionsFlag = getPermissions(param);
            if(!permissionsFlag){
                result.setCode(PactCommonEnum.FINANCERECEIPTPAY_NO_PERMISSIONS.getCode());
                result.setMessage(PactCommonEnum.FINANCERECEIPTPAY_NO_PERMISSIONS.getMessage());
                return result;
            }
            FinanceReceiptPayVO financeReceiptPayVO = financeReceiptPayManager.getPactRealExpendDetail(id);
            if(null != financeReceiptPayVO){
                RealReceiptPayResult realReceiptPayResult = new RealReceiptPayResult();
                BeanUtils.copyProperties(financeReceiptPayVO, realReceiptPayResult);
                
                
                // 根据创建人pin 查询创建人部门*-*-*-*-*-
                String paymentPin = realReceiptPayResult.getCreateName();
                ApiResult<PersonalResult> paymentPersonalResult = personalRpc.getPersonalResultBypin(paymentPin);
                PersonalResult paymentpersonalResultData = paymentPersonalResult.getData();
                realReceiptPayResult.setCreateName(paymentpersonalResultData.getEmployeeName());
                realReceiptPayResult.setCreateDeptName(paymentpersonalResultData.getDeptName());
                
                
                financeReceiptPayVO = financeReceiptPayManager.getAnswerExpendDetail(financeReceiptPayVO.getPid());
                if(null != financeReceiptPayVO){
                    realReceiptPayResult.setAnswerCostType(financeReceiptPayVO.getCostType());
                    realReceiptPayResult.setAnswerCostAmount(financeReceiptPayVO.getCostAmount());
                    realReceiptPayResult.setAnswerStartTime(financeReceiptPayVO.getStartTime());
                    realReceiptPayResult.setAnswerEndTime(financeReceiptPayVO.getEndTime());
                    realReceiptPayResult.setAnswerDescribe(financeReceiptPayVO.getDescribe());
                    realReceiptPayResult.setAnswerCostTime(financeReceiptPayVO.getCostTime());
                    realReceiptPayResult.setAnswerCreate(financeReceiptPayVO.getCreate());
                    // 根据应支创建人pin 查询创建人部门*-*-*-*-*-
                    String createPin = financeReceiptPayVO.getCreateName();
                    ApiResult<PersonalResult> personalResult = personalRpc.getPersonalResultBypin(createPin);
                    PersonalResult personalResultData = personalResult.getData();
                    realReceiptPayResult.setAnswerCreateName(personalResultData.getEmployeeName());
                    realReceiptPayResult.setAnswerCreateDeptName(personalResultData.getDeptName());
                    realReceiptPayResult.setAnswerUnsupportedAmount(financeReceiptPayVO.getUnsupportedAmount());
                }
                
                // 实支凭证
                List<Long> pids = new ArrayList<>();
                pids.add(id);
                List<FinanceReceiptPayVoucherDO> voucherList = financeReceiptPayVoucherManager.listVoucherByPids(pids);
                if(null != voucherList){
                    List<FinanceReceiptPayVoucherResult> financeReceiptPayVoucherResults = new ArrayList<>();
                    BeanUtils.copyListProperties(voucherList, financeReceiptPayVoucherResults, FinanceReceiptPayVoucherResult.class);
                    realReceiptPayResult.setFinanceReceiptPayVoucherList(financeReceiptPayVoucherResults);
                }
                
                // 实支审核信息，根据实支id查询操作日志*-*-*-*-*-*
                OperateLogParam operateLogParam = new OperateLogParam();
                operateLogParam.setServiceCode(String.valueOf(id));
                operateLogParam.setServiceType(StringConsts.REAL_SUPPORT_AUDIT);
                List<OperateLogResult> operateRecordList = operateLogRpc.listOperateRecord(operateLogParam);
                OperateLogResult operateLogResult = new OperateLogResult();
                if(null!=operateRecordList&&operateRecordList.size()>0){
                     operateLogResult = operateRecordList.get(0);
                }
                realReceiptPayResult.setReviewUserName(operateLogResult.getOperateName());
                realReceiptPayResult.setReviewDeptName(operateLogResult.getOperateDept());
                realReceiptPayResult.setReviewTime(operateLogResult.getOperateTime());
                
                // 付款人信息  查询付款人部门*-*-*-*-*-s
                if(null != voucherList &&voucherList.size() > 0){
                    realReceiptPayResult.setPaymentTime(voucherList.get(0).getCreate());
                }
                OperateLogParam payOperateLogParam = new OperateLogParam();
                payOperateLogParam.setServiceCode(String.valueOf(id));
                payOperateLogParam.setServiceType(StringConsts.ANSWEREXPENDRECEIPT);
                List<OperateLogResult> payOperateRecordList = operateLogRpc.listOperateRecord(payOperateLogParam);
                OperateLogResult payPperateLogResult = new OperateLogResult();
                if(null!=payOperateRecordList&&payOperateRecordList.size()>0){
                    payPperateLogResult = operateRecordList.get(0);
                }
                realReceiptPayResult.setPaymentUserName(payPperateLogResult.getOperateName());
                realReceiptPayResult.setPaymentDeptName(payPperateLogResult.getOperateDept());
                /*************************** 实支逾期天数   start *********************************/
                if(StringUtils.isBlank(realReceiptPayResult.getOverdueDay())){
                    realReceiptPayResult.setOverdueDay( "" + DateUtil.daysBetweenTwo(new Date(),financeReceiptPayVO.getCostTime() ));
                }
                /*************************** 实支逾期天数   end  *********************************/
                result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                result.setData(realReceiptPayResult);
            }else{// 收支id无效
                result.setCode(PactCommonEnum.FINANCE_RECEIPT_PAY_ID_INVALID.getCode());
                result.setMessage(PactCommonEnum.FINANCE_RECEIPT_PAY_ID_INVALID.getMessage());
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
    public ApiResult<Boolean> answerCollectReceipt(FinanceReceiptPayParam param) {
        ApiResult<Boolean> result = new ApiResult<>();
        if(null == param){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        try {
            FinanceReceiptPayDO financeReceiptPayDO = new FinanceReceiptPayDO();
            BeanUtils.copyProperties(param, financeReceiptPayDO);
            List<FinanceReceiptPayVoucherDO> financeReceiptPayVoucherList = new ArrayList<>();
            if(null != param.getFinanceReceiptPayVoucherList()){
                BeanUtils.copyListProperties(param.getFinanceReceiptPayVoucherList(), financeReceiptPayVoucherList, FinanceReceiptPayVoucherDO.class);
                financeReceiptPayDO.setFinanceReceiptPayVoucherList(financeReceiptPayVoucherList);
            }
            FinanceReceiptPayVO financeReceiptPayVO = financeReceiptPayManager.getAnswerCollectDetail(financeReceiptPayDO.getPid());
            if(null != financeReceiptPayVO){
                // 未收 金额
                BigDecimal totalAmount = BigDecimal.valueOf(financeReceiptPayVO.getCostAmount());
                // 锁代码块
                synchronized (this) {
                    // 实收总和
                    double sumAmount = financeReceiptPayManager.getRealAmountByPid(financeReceiptPayVO.getId());
                    // 减掉 实收 金额
                    totalAmount = totalAmount.subtract(BigDecimal.valueOf(financeReceiptPayDO.getCostAmount())).subtract(BigDecimal.valueOf(sumAmount));
                }
                // 判断未收大小
                if(totalAmount.compareTo(new BigDecimal(0)) == 0 || totalAmount.compareTo(new BigDecimal(0)) == 1){// 等于 或者大于
                    financeReceiptPayDO.setType(1);// 实收
                    financeReceiptPayDO.setPactCode(financeReceiptPayVO.getPactCode());
                    financeReceiptPayDO.setPactType(financeReceiptPayVO.getPactType());
                    financeReceiptPayDO.setCostType(financeReceiptPayVO.getCostType());
                    financeReceiptPayDO.setIncomeExpendType(financeReceiptPayVO.getIncomeExpendType());
                    financeReceiptPayDO.setCostSubject(financeReceiptPayVO.getCostSubject());
                    financeReceiptPayDO.setStartTime(financeReceiptPayVO.getStartTime());
                    financeReceiptPayDO.setEndTime(financeReceiptPayVO.getEndTime());
                    financeReceiptPayDO.setDescribe(financeReceiptPayVO.getDescribe());
                    financeReceiptPayDO.setHouseCode(financeReceiptPayVO.getHouseCode());
                    financeReceiptPayDO.setRoomCode(financeReceiptPayVO.getRoomCode());
                    financeReceiptPayDO.setAddress(financeReceiptPayVO.getAddress());
                    financeReceiptPayDO.setPersonnelCode(financeReceiptPayVO.getPersonnelCode());
                    financeReceiptPayDO.setCustomerRole(financeReceiptPayVO.getCustomerRole());
                    financeReceiptPayDO.setPayeeObject(financeReceiptPayVO.getPayeeObject());
                    financeReceiptPayDO.setName(financeReceiptPayVO.getName());
                    financeReceiptPayDO.setTel(financeReceiptPayVO.getTel());
                    financeReceiptPayDO.setAccount(financeReceiptPayVO.getAccount());
                    financeReceiptPayDO.setBank(financeReceiptPayVO.getBank());
                    financeReceiptPayDO.setOpenBank(financeReceiptPayVO.getOpenBank());
                    financeReceiptPayDO.setCostState(7);// 待审核
                    financeReceiptPayDO.setIsValid(1);// 有效
                    // 逾期天数 计算
                    Integer daysBetween = DateUtil.daysBetweenTwo(financeReceiptPayVO.getCostTime(), financeReceiptPayDO.getCostTime());
                    financeReceiptPayDO.setOverdueDay("" + (daysBetween > 0 ? daysBetween : 0));
                    boolean flag = financeReceiptPayManager.answerCollectReceipt(financeReceiptPayDO);
                    if(flag){
                        /*---------收款操作日志----start------*/
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put(NormalConstant.SERVICETYPE, StringConsts.ANSWERCOLLECTRECEIPT);
                        map.put(NormalConstant.SERVICECODE, param.getPid());
                        map.put(NormalConstant.OPERATETYPE, StringConsts.ADD);
                        map.put(NormalConstant.OPERATECONTENT, StringConsts.ANSWER_COLLECT_RECEIPT_DATA);
                        map.put(NormalConstant.OPERATEPIN, financeReceiptPayDO.getModifiedName());
                        operateLogRpc.saveOperateLog(map);
                        /*---------收款操作日志----end------*/
                        /*-------------应收款项审批提醒 start------*/
                        String pin ="";
                        //托进和托出合同的提醒对象：租后管家的上级
                        if(PactTypeConsts.PURCHASE.equals(financeReceiptPayVO.getPactType())){
                            CommonBelongerQuery commonBelongerQuery = new CommonBelongerQuery();
                            commonBelongerQuery.setPactCode(financeReceiptPayVO.getPactCode());
                            commonBelongerQuery.setUserRole(StringConsts.BELONGER_SERVICE);
                            CommonBelongerVO commonBelongerVO = commonBelongerManager.getBelongersByParam(commonBelongerQuery);
                            if(null != commonBelongerVO){
                                PersonalResult superior = personalRpc.getSuperiorByPin(commonBelongerVO.getUserPin());
                                if(null != superior){
                                    pin = superior.getPin();
                                }
                            }
                        }else if(PactTypeConsts.RENT.equals(financeReceiptPayVO.getPactType())){
                            CommonBelongerVO commonBelongerVO = rentBaseManager.getRentAfterBelonger(financeReceiptPayVO.getPactCode());
                            if(null != commonBelongerVO){
                                PersonalResult superior = personalRpc.getSuperiorByPin(commonBelongerVO.getUserPin());
                                if(null != superior){
                                    pin = superior.getPin();
                                }
                            }
                        }
                        else{
                            CommonBelongerQuery commonBelongerQuery = new CommonBelongerQuery();
                            commonBelongerQuery.setPactCode(financeReceiptPayVO.getPactCode());
                            commonBelongerQuery.setUserRole(StringConsts.GUARDIAN);
                            CommonBelongerVO commonBelongerVO = commonBelongerManager.getBelongersByParam(commonBelongerQuery);
                            PersonalResult superior = personalRpc.getSuperiorByPin(commonBelongerVO.getUserPin());
                            if(null != superior){
                                pin = superior.getPin();
                            }
                        }
                        List<String> variableList = new ArrayList<>();
                        variableList.add(StringConsts.REMIND_RECEIVABLE);
                        saveRemind(param,StringConsts.TEMPLATE_RECPAYAUDITING,variableList,pin,StringConsts.REMIND_RECEIVABLE_TITLE);
                        /*---------应收款项审批提醒   end------*/
                        result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                        result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                        result.setData(flag);
                    }else{
                        result.setCode(CommonEnum.REQUEST_FAIL.getCode());
                        result.setMessage(CommonEnum.REQUEST_FAIL.getMessage());
                    }
                }else if(totalAmount.compareTo(new BigDecimal(0)) == -1){// 小于
                    // 实收金额不能大于未收
                    result.setCode(PactCommonEnum.FINANCE_RECEIPT_BIG_AMOUNT_INVALID.getCode());
                    result.setMessage(PactCommonEnum.FINANCE_RECEIPT_BIG_AMOUNT_INVALID.getMessage());
                }
            }else{// 应收id无效
                result.setCode(PactCommonEnum.FINANCE_RECEIPT_PAY_ID_INVALID.getCode());
                result.setMessage(PactCommonEnum.FINANCE_RECEIPT_PAY_ID_INVALID.getMessage());
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
     * @param pin 所属人上级
     * @throws 异常
     */
    private void saveRemind(FinanceReceiptPayParam param, String key, List<String> variableList,String pin,String title) {
        String content = remindMap.get(key);
        for (int i = 0; i < variableList.size(); i++) {
            content = content.replace(StringConsts.VARIABLE + i, variableList.get(i));
        }
        Map<String, Object> map = new HashMap<>();
        map.put(NormalConstant.REMIND_TITLE, title);
        map.put(NormalConstant.REMIND_PIN, pin);
        map.put(NormalConstant.REMIND_SERVICETYPE, SerializeTypeConts.RECEIVAPAY);
        map.put(NormalConstant.REMIND_SERVICECODE, param.getPactCode());
        map.put(NormalConstant.REMIND_REMINDCONTENT,content);
        remindRpc.saveRemind(map);
    }
    @Override
    public ApiResult<Boolean> realCollectConfirm(FinanceReceiptPayParam param) {
        ApiResult<Boolean> result = new ApiResult<>();
        if(null == param){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        try {
            FinanceReceiptPayDO financeReceiptPayDO = new FinanceReceiptPayDO();
            BeanUtils.copyProperties(param, financeReceiptPayDO);
            financeReceiptPayDO.setCostState(8);
            boolean flag = financeReceiptPayManager.realCollectConfirm(financeReceiptPayDO);
            if(flag){
                /*---------确认操作日志----start------*/
                Map<String, Object> map = new HashMap<String, Object>();
                map.put(NormalConstant.SERVICETYPE, StringConsts.REALCOLLECTCONFIRM);
                map.put(NormalConstant.SERVICECODE, param.getId());
                map.put(NormalConstant.OPERATETYPE, StringConsts.UPDATE);
                map.put(NormalConstant.OPERATECONTENT, StringConsts.REAL_COLLECT_CONFIRM_DATA);
                map.put(NormalConstant.OPERATEPIN, financeReceiptPayDO.getModifiedName());
                operateLogRpc.saveOperateLog(map);
                /*---------确认操作日志----end------*/
                /*-------------实收确认提醒 start------*/
                FinanceReceiptPayVO financeReceiptPayVO = financeReceiptPayManager.getRealCollectDetail(param.getId());
                String pin ="";
                //托进和托出合同的提醒对象：租后管家
                if(PactTypeConsts.PURCHASE.equals(financeReceiptPayVO.getPactType())){
                    CommonBelongerQuery commonBelongerQuery = new CommonBelongerQuery();
                    commonBelongerQuery.setPactCode(financeReceiptPayVO.getPactCode());
                    commonBelongerQuery.setUserRole(StringConsts.BELONGER_SERVICE);
                    CommonBelongerVO commonBelongerVO = commonBelongerManager.getBelongersByParam(commonBelongerQuery);
                    pin = commonBelongerVO.getUserPin();
                }else if(PactTypeConsts.RENT.equals(financeReceiptPayVO.getPactType())){
                    CommonBelongerVO commonBelongerVO = rentBaseManager.getRentAfterBelonger(financeReceiptPayVO.getPactCode());
                    pin = commonBelongerVO.getUserPin();
                }
                else{
                    CommonBelongerQuery commonBelongerQuery = new CommonBelongerQuery();
                    commonBelongerQuery.setPactCode(financeReceiptPayVO.getPactCode());
                    commonBelongerQuery.setUserRole(StringConsts.GUARDIAN);
                    CommonBelongerVO commonBelongerVO = commonBelongerManager.getBelongersByParam(commonBelongerQuery);
                    pin = commonBelongerVO.getUserPin();
                }
                List<String> variableList = new ArrayList<>();
                variableList.add(StringConsts.REMIND_REAL_RECEIVA);
                variableList.add(StringConsts.REMIND_AUDITING_PASS);
                saveRemind(param,StringConsts.TEMPLATE_REAL_RECPAYAUDITING,variableList,pin,StringConsts.REMIND_REAL_RECEIVE_TITLE);
                /*---------实收确认提醒   end------*/
                /******************** 修改待办事务统计收款待确认的状态  start *******************************/
                AgendaDO agendaDO = new AgendaDO();
                agendaDO.setServiceCode(String.valueOf(financeReceiptPayDO.getId()));
                agendaDO.setIsDo(1);
                agendaDO.setType(StringConsts.STATISTIC_PENDING_RECEIPT_CONFIRMED);
                statisticsManager.updatBacklogStatisticsIsDo(agendaDO);
                agendaDO.setModifiedName(param.getCreateName());
                agendaDO.setIp(param.getIp());
                /******************** 修改待办事务统计收款待确认的状态  end *******************************/
                result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                result.setData(flag);
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
    public ApiResult<Boolean> realCollectReject(FinanceReceiptPayParam param) {
        ApiResult<Boolean> result = new ApiResult<>();
        if(null == param){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        try {
            FinanceReceiptPayDO financeReceiptPayDO = new FinanceReceiptPayDO();
            BeanUtils.copyProperties(param, financeReceiptPayDO);
            financeReceiptPayDO.setCostState(9);
            boolean flag = financeReceiptPayManager.realCollectReject(financeReceiptPayDO);
            if(flag){
                /*---------驳回操作日志----start------*/
                Map<String, Object> map = new HashMap<String, Object>();
                map.put(NormalConstant.SERVICETYPE, StringConsts.REALCOLLECTREJECT);
                map.put(NormalConstant.SERVICECODE, param.getId());
                map.put(NormalConstant.OPERATETYPE, StringConsts.UPDATE);
                map.put(NormalConstant.OPERATECONTENT, StringConsts.REAL_COLLECT_REJECT_DATA);
                map.put(NormalConstant.OPERATEPIN, financeReceiptPayDO.getModifiedName());
                operateLogRpc.saveOperateLog(map);
                /*---------驳回操作日志----end------*/
                /*-------------实收驳回提醒 start------*/
                FinanceReceiptPayVO financeReceiptPayVO = financeReceiptPayManager.getRealCollectDetail(param.getId());
                String pin ="";
                //托进和托出合同的提醒对象：租后管家
                if(PactTypeConsts.PURCHASE.equals(financeReceiptPayVO.getPactType())){
                    CommonBelongerQuery commonBelongerQuery = new CommonBelongerQuery();
                    commonBelongerQuery.setPactCode(financeReceiptPayVO.getPactCode());
                    commonBelongerQuery.setUserRole(StringConsts.BELONGER_SERVICE);
                    CommonBelongerVO commonBelongerVO = commonBelongerManager.getBelongersByParam(commonBelongerQuery);
                    pin = commonBelongerVO.getUserPin();
                }else if(PactTypeConsts.RENT.equals(financeReceiptPayVO.getPactType())){
                    CommonBelongerVO commonBelongerVO = rentBaseManager.getRentAfterBelonger(financeReceiptPayVO.getPactCode());
                    pin = commonBelongerVO.getUserPin();
                }
                else{
                    CommonBelongerQuery commonBelongerQuery = new CommonBelongerQuery();
                    commonBelongerQuery.setPactCode(financeReceiptPayVO.getPactCode());
                    commonBelongerQuery.setUserRole(StringConsts.GUARDIAN);
                    CommonBelongerVO commonBelongerVO = commonBelongerManager.getBelongersByParam(commonBelongerQuery);
                    pin = commonBelongerVO.getUserPin();
                }
                List<String> variableList = new ArrayList<>();
                variableList.add(StringConsts.REMIND_REAL_RECEIVA);
                variableList.add(StringConsts.REMIND_AUDITING_REJECT);
                saveRemind(param,StringConsts.TEMPLATE_REAL_RECPAYAUDITING,variableList,pin,StringConsts.REMIND_REAL_RECEIVE_TITLE);
                /*---------实收驳回提醒   end------*/
                /******************** 修改待办事务统计收款待确认的状态  start *******************************/
                AgendaDO agendaDO = new AgendaDO();
                agendaDO.setServiceCode(String.valueOf(financeReceiptPayDO.getId()));
                agendaDO.setIsDo(1);
                agendaDO.setType(StringConsts.STATISTIC_PENDING_RECEIPT_CONFIRMED);
                statisticsManager.updatBacklogStatisticsIsDo(agendaDO);
                agendaDO.setModifiedName(param.getCreateName());
                agendaDO.setIp(param.getIp());
                /******************** 修改待办事务统计收款待确认的状态  end *******************************/
                result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                result.setData(flag);
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
    public ApiResult<Boolean> answerExpendApply(FinanceReceiptPayParam param) {
        ApiResult<Boolean> result = new ApiResult<>();
        if(null == param){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        try {
            FinanceReceiptPayDO financeReceiptPayDO = new FinanceReceiptPayDO();
            BeanUtils.copyProperties(param, financeReceiptPayDO);
            
            FinanceReceiptPayVO financeReceiptPayVO = financeReceiptPayManager.getAnswerExpendDetail(financeReceiptPayDO.getPid());
            if(null != financeReceiptPayVO){
                // 未支 金额
                BigDecimal totalAmount = BigDecimal.valueOf(financeReceiptPayVO.getCostAmount());
                // 锁代码块
                synchronized (this) {
                    // 实支总和
                    double sumAmount = financeReceiptPayManager.getRealAmountByPid(financeReceiptPayVO.getId());
                    // 减掉 实支 金额
                    totalAmount = totalAmount.subtract(BigDecimal.valueOf(financeReceiptPayDO.getCostAmount())).subtract(BigDecimal.valueOf(sumAmount));
                }
                // 判断未支大小
                if(totalAmount.compareTo(new BigDecimal(0)) == 0 || totalAmount.compareTo(new BigDecimal(0)) == 1){// 等于 或者大于
                    financeReceiptPayDO.setType(3);// 实支
                    financeReceiptPayDO.setPactCode(financeReceiptPayVO.getPactCode());
                    financeReceiptPayDO.setPactType(financeReceiptPayVO.getPactType());
                    financeReceiptPayDO.setCostType(financeReceiptPayVO.getCostType());
                    financeReceiptPayDO.setIncomeExpendType(financeReceiptPayVO.getIncomeExpendType());
                    financeReceiptPayDO.setCostSubject(financeReceiptPayVO.getCostSubject());
                    financeReceiptPayDO.setStartTime(financeReceiptPayVO.getStartTime());
                    financeReceiptPayDO.setEndTime(financeReceiptPayVO.getEndTime());
                    financeReceiptPayDO.setDescribe(financeReceiptPayVO.getDescribe());
                    financeReceiptPayDO.setHouseCode(financeReceiptPayVO.getHouseCode());
                    financeReceiptPayDO.setRoomCode(financeReceiptPayVO.getRoomCode());
                    financeReceiptPayDO.setAddress(financeReceiptPayVO.getAddress());
                    financeReceiptPayDO.setPersonnelCode(financeReceiptPayVO.getPersonnelCode());
                    financeReceiptPayDO.setCustomerRole(financeReceiptPayVO.getCustomerRole());
                    financeReceiptPayDO.setPayeeObject(financeReceiptPayVO.getPayeeObject());
                    financeReceiptPayDO.setName(financeReceiptPayVO.getName());
                    financeReceiptPayDO.setTel(financeReceiptPayVO.getTel());
                    financeReceiptPayDO.setAccount(financeReceiptPayVO.getAccount());
                    financeReceiptPayDO.setBank(financeReceiptPayVO.getBank());
                    financeReceiptPayDO.setOpenBank(financeReceiptPayVO.getOpenBank());
                    financeReceiptPayDO.setCostState(7);// 待审核
                    financeReceiptPayDO.setIsValid(1);// 有效
                    if(totalAmount.compareTo(new BigDecimal(0)) == 0 ){
                        /******************* 修改待处理事务待付款状态  start************************************/
                        AgendaDO agendaDO = new AgendaDO();
                        agendaDO.setServiceCode(String.valueOf(param.getPid()));
                        agendaDO.setIsDo(1);
                        agendaDO.setType(StringConsts.STATISTIC_PENDING_PENDING_PAYMENT);
                        statisticsManager.updatBacklogStatisticsIsDo(agendaDO);
                        agendaDO.setModifiedName(param.getCreateName());
                        agendaDO.setIp(param.getIp());
                        /******************* 修改待处理事务待付款状态  end************************************/
                    }
                    boolean flag = financeReceiptPayManager.answerExpendApply(financeReceiptPayDO);
                    if(flag){
                        /*---------应支申请付款操作日志----start------*/
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put(NormalConstant.SERVICETYPE, StringConsts.ANSWEREXPENDAPPLY);
                        map.put(NormalConstant.SERVICECODE, financeReceiptPayDO.getId());
                        map.put(NormalConstant.OPERATETYPE, StringConsts.ADD);
                        map.put(NormalConstant.OPERATECONTENT, StringConsts.ANSWER_EXPEND_APPLY_DATA);
                        map.put(NormalConstant.OPERATEPIN, financeReceiptPayDO.getModifiedName());
                        operateLogRpc.saveOperateLog(map);
                        /*---------应支申请付款操作日志----end------*/
                        /*-------------应收款项审批提醒 start------*/
                        String pin ="";
                        //托进和托出合同的提醒对象：租后管家的上级
                       if(PactTypeConsts.PURCHASE.equals(financeReceiptPayVO.getPactType())){
                            CommonBelongerQuery commonBelongerQuery = new CommonBelongerQuery();
                            commonBelongerQuery.setPactCode(financeReceiptPayVO.getPactCode());
                            commonBelongerQuery.setUserRole(StringConsts.BELONGER_SERVICE);
                            CommonBelongerVO commonBelongerVO = commonBelongerManager.getBelongersByParam(commonBelongerQuery);
                            if(null!=commonBelongerVO && StringUtils.isNotBlank(commonBelongerVO.getUserPin())){
                                PersonalResult superior = personalRpc.getSuperiorByPin(commonBelongerVO.getUserPin());
                                pin = superior.getPin();
                            }
                        }else if(PactTypeConsts.RENT.equals(financeReceiptPayVO.getPactType())){
                            CommonBelongerVO commonBelongerVO = rentBaseManager.getRentAfterBelonger(financeReceiptPayVO.getPactCode());
                            if(null!=commonBelongerVO && StringUtils.isNotBlank(commonBelongerVO.getUserPin())){
                                PersonalResult superior = personalRpc.getSuperiorByPin(commonBelongerVO.getUserPin());
                                pin = superior.getPin();
                            }
                        }
                        else{
                            CommonBelongerQuery commonBelongerQuery = new CommonBelongerQuery();
                            commonBelongerQuery.setPactCode(financeReceiptPayVO.getPactCode());
                            commonBelongerQuery.setUserRole(StringConsts.GUARDIAN);
                            CommonBelongerVO commonBelongerVO = commonBelongerManager.getBelongersByParam(commonBelongerQuery);
                            if(null!=commonBelongerVO && StringUtils.isNotBlank(commonBelongerVO.getUserPin())){
                                PersonalResult superior = personalRpc.getSuperiorByPin(commonBelongerVO.getUserPin());
                                pin = superior.getPin();
                            }
                        }
                        List<String> variableList = new ArrayList<>();
                        variableList.add(StringConsts.REMIND_PAY);
                        saveRemind(param,StringConsts.TEMPLATE_RECPAYAUDITING,variableList,pin,StringConsts.REMIND_RECEIVABLE_TITLE);
                        /*---------应收款项审批提醒   end------*/
                        result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                        result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                        result.setData(flag);
                    }else{
                        result.setCode(CommonEnum.REQUEST_FAIL.getCode());
                        result.setMessage(CommonEnum.REQUEST_FAIL.getMessage());
                    }
                }else if(totalAmount.compareTo(new BigDecimal(0)) == -1){// 小于
                    // 付款金额不能大于未支金额
                    result.setCode(PactCommonEnum.FINANCE_PAY_BIG_AMOUNT_INVALID.getCode());
                    result.setMessage(PactCommonEnum.FINANCE_PAY_BIG_AMOUNT_INVALID.getMessage());
                }
            }else{// 应支id无效
                result.setCode(PactCommonEnum.FINANCE_RECEIPT_PAY_ID_INVALID.getCode());
                result.setMessage(PactCommonEnum.FINANCE_RECEIPT_PAY_ID_INVALID.getMessage());
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
    public ApiResult<Boolean> answerExpendReview(FinanceReceiptPayParam param) {
        ApiResult<Boolean> result = new ApiResult<>();
        if(null == param){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        try {
            FinanceReceiptPayDO financeReceiptPayDO = new FinanceReceiptPayDO();
            BeanUtils.copyProperties(param, financeReceiptPayDO);
            financeReceiptPayDO.setCostState(8);
            boolean flag = financeReceiptPayManager.answerExpendReview(financeReceiptPayDO);
            if(flag){
                /*---------实支审核通过操作日志----start------*/
                Map<String, Object> map = new HashMap<String, Object>();
                map.put(NormalConstant.SERVICETYPE, StringConsts.REAL_SUPPORT_AUDIT);
                map.put(NormalConstant.SERVICECODE, financeReceiptPayDO.getId());
                map.put(NormalConstant.OPERATETYPE, StringConsts.UPDATE);
                map.put(NormalConstant.OPERATECONTENT, StringConsts.ANSWER_EXPEND_REVIEW_DATA);
                map.put(NormalConstant.OPERATEPIN, financeReceiptPayDO.getModifiedName());
                operateLogRpc.saveOperateLog(map);
                /*---------实支审核通过操作日志----end------*/
                /*-------------实支审核通过提醒 start------*/
                FinanceReceiptPayVO financeReceiptPayVO = financeReceiptPayManager.getPactRealExpendDetail(financeReceiptPayDO.getId());
                String pin ="";
                //托进和托出合同的提醒对象：租后管家
                if(PactTypeConsts.PURCHASE.equals(financeReceiptPayVO.getPactType())){
                    CommonBelongerQuery commonBelongerQuery = new CommonBelongerQuery();
                    commonBelongerQuery.setPactCode(financeReceiptPayVO.getPactCode());
                    commonBelongerQuery.setUserRole(StringConsts.BELONGER_SERVICE);
                    CommonBelongerVO commonBelongerVO = commonBelongerManager.getBelongersByParam(commonBelongerQuery);
                    pin = commonBelongerVO.getUserPin();
                }else if(PactTypeConsts.RENT.equals(financeReceiptPayVO.getPactType())){
                    CommonBelongerVO commonBelongerVO = rentBaseManager.getRentAfterBelonger(financeReceiptPayVO.getPactCode());
                    pin = commonBelongerVO.getUserPin();
                }
                else{
                    CommonBelongerQuery commonBelongerQuery = new CommonBelongerQuery();
                    commonBelongerQuery.setPactCode(financeReceiptPayVO.getPactCode());
                    commonBelongerQuery.setUserRole(StringConsts.GUARDIAN);
                    CommonBelongerVO commonBelongerVO = commonBelongerManager.getBelongersByParam(commonBelongerQuery);
                    pin = commonBelongerVO.getUserPin();
                }
                List<String> variableList = new ArrayList<>();
                variableList.add(StringConsts.REMIND_REAL_PAY);
                variableList.add(StringConsts.REMIND_AUDITING_PASS);
                saveRemind(param,StringConsts.TEMPLATE_REAL_RECPAYAUDITING,variableList,pin,StringConsts.REMIND_REAL_RECEIVE_TITLE);
                /*--------实支审核通过提醒   end------*/
                /******************** 修改待办事务统计付款待审核的状态  start *******************************/
                AgendaDO agendaDO = new AgendaDO();
                agendaDO.setServiceCode(String.valueOf(param.getId()));
                agendaDO.setIsDo(1);
                agendaDO.setType(StringConsts.STATISTIC_PENDING_PAYMENT_AUDITED);
                agendaDO.setModifiedName(param.getModifiedName());
                agendaDO.setIp(param.getIp());
                statisticsManager.updatBacklogStatisticsIsDo(agendaDO);
                /******************** 修改待办事务统计付款待审核的状态  end *******************************/
                /******************** 添加待办事务统计付款已审核待支付  start *******************************/
                AgendaDO agendaDOUnPay = new AgendaDO();
                agendaDOUnPay.setServiceCode(String.valueOf(param.getId()));
                agendaDOUnPay.setIsDo(0);
                agendaDOUnPay.setType(StringConsts.STATISTIC_PENDING_PAYMENT_AUDITED_PAID);
                agendaDO.setCreateName(param.getModifiedName());
                agendaDO.setIp(param.getIp());
                statisticsManager.saveBacklogStatistics(agendaDOUnPay);
                /******************** 添加待办事务统计付款已审核待支付  end *******************************/
                result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                result.setData(flag);
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
    public ApiResult<Boolean> answerExpendReject(FinanceReceiptPayParam param) {
        ApiResult<Boolean> result = new ApiResult<>();
        if(null == param){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        try {
            FinanceReceiptPayDO financeReceiptPayDO = new FinanceReceiptPayDO();
            BeanUtils.copyProperties(param, financeReceiptPayDO);
            financeReceiptPayDO.setCostState(9);
            boolean flag = financeReceiptPayManager.answerExpendReject(financeReceiptPayDO);
            if(flag){
                /*---------驳回操作日志----start------*/
                Map<String, Object> map = new HashMap<String, Object>();
                map.put(NormalConstant.SERVICETYPE, StringConsts.REAL_SUPPORT_AUDIT);
                map.put(NormalConstant.SERVICECODE, financeReceiptPayDO.getId());
                map.put(NormalConstant.OPERATETYPE, StringConsts.UPDATE);
                map.put(NormalConstant.OPERATECONTENT, StringConsts.ANSWER_EXPEND_REJECT_DATA);
                map.put(NormalConstant.OPERATEPIN, financeReceiptPayDO.getModifiedName());
                operateLogRpc.saveOperateLog(map);
          
                /*---------驳回操作日志----end------*/
                
                /*-------------实支审核驳回提醒 start------*/
                FinanceReceiptPayVO financeReceiptPayVO = financeReceiptPayManager.getPactRealExpendDetail(param.getId());
                String pin ="";
                //托进和托出合同的提醒对象：租后管家
                if(PactTypeConsts.PURCHASE.equals(financeReceiptPayVO.getPactType())){
                    CommonBelongerQuery commonBelongerQuery = new CommonBelongerQuery();
                    commonBelongerQuery.setPactCode(financeReceiptPayVO.getPactCode());
                    commonBelongerQuery.setUserRole(StringConsts.BELONGER_SERVICE);
                    CommonBelongerVO commonBelongerVO = commonBelongerManager.getBelongersByParam(commonBelongerQuery);
                    if(null != commonBelongerVO){
                        pin = commonBelongerVO.getUserPin();
                    }
                }else if(PactTypeConsts.RENT.equals(financeReceiptPayVO.getPactType())){
                    CommonBelongerVO commonBelongerVO = rentBaseManager.getRentAfterBelonger(financeReceiptPayVO.getPactCode());
                    if(null != commonBelongerVO){
                        pin = commonBelongerVO.getUserPin();
                    }
                }
                else{
                    CommonBelongerQuery commonBelongerQuery = new CommonBelongerQuery();
                    commonBelongerQuery.setPactCode(financeReceiptPayVO.getPactCode());
                    commonBelongerQuery.setUserRole(StringConsts.GUARDIAN);
                    CommonBelongerVO commonBelongerVO = commonBelongerManager.getBelongersByParam(commonBelongerQuery);
                    if(null != commonBelongerVO){
                        pin = commonBelongerVO.getUserPin();
                    }
                }
                List<String> variableList = new ArrayList<>();
                variableList.add(StringConsts.REMIND_REAL_PAY);
                variableList.add(StringConsts.REMIND_AUDITING_REJECT);
                saveRemind(param,StringConsts.TEMPLATE_REAL_RECPAYAUDITING,variableList,pin,StringConsts.REMIND_REAL_RECEIVE_TITLE);
                /*--------实支审核驳回提醒   end------*/
                /******************** 修改待办事务统计付款待审核的状态  start *******************************/
                AgendaDO agendaDO = new AgendaDO();
                agendaDO.setServiceCode(String.valueOf(param.getId()));
                agendaDO.setIsDo(1);
                agendaDO.setType(StringConsts.STATISTIC_PENDING_PAYMENT_AUDITED);
                statisticsManager.updatBacklogStatisticsIsDo(agendaDO);
                agendaDO.setModifiedName(param.getCreateName());
                agendaDO.setIp(param.getIp());
                /******************** 修改待办事务统计付款待审核的状态  end *******************************/
                result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                result.setData(flag);
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
    public ApiResult<Boolean> answerExpendReceipt(FinanceReceiptPayParam param) {
        ApiResult<Boolean> result = new ApiResult<>();
        if(null == param){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        try {
            FinanceReceiptPayVO realExpendDetail = financeReceiptPayManager.getRealExpendDetail(param.getId());
            if(null != realExpendDetail){
                if(8 == realExpendDetail.getCostState()){// 必须为审核通过
                    FinanceReceiptPayDO financeReceiptPayDO = new FinanceReceiptPayDO();
                    BeanUtils.copyProperties(param, financeReceiptPayDO);
                    List<FinanceReceiptPayVoucherDO> financeReceiptPayVoucherList = new ArrayList<>();
                    if(null != param.getFinanceReceiptPayVoucherList()){
                        BeanUtils.copyListProperties(param.getFinanceReceiptPayVoucherList(), financeReceiptPayVoucherList, FinanceReceiptPayVoucherDO.class);
                        for (FinanceReceiptPayVoucherDO financeReceiptPayVoucherDO : financeReceiptPayVoucherList) {
                            financeReceiptPayVoucherDO.setIp(financeReceiptPayDO.getIp());
                            financeReceiptPayVoucherDO.setCreateName(financeReceiptPayDO.getCreateName());
                            financeReceiptPayVoucherDO.setModifiedName(financeReceiptPayDO.getModifiedName());
                            financeReceiptPayVoucherDO.setReceiptPayId(financeReceiptPayDO.getId());
                        }
                        financeReceiptPayDO.setFinanceReceiptPayVoucherList(financeReceiptPayVoucherList);
                    }
                    financeReceiptPayDO.setCostState(10);
                    FinanceReceiptPayVO branchRecPay = financeReceiptPayManager.getPactAnswerExpendDetail(realExpendDetail.getPid());
                    int overdueDay = DateUtil.daysBetweenTwo(param.getCostTime(), branchRecPay.getCostTime());
                    financeReceiptPayDO.setOverdueDay("" + (overdueDay > 0 ? overdueDay : 0));
                    boolean flag = financeReceiptPayManager.answerExpendReceipt(financeReceiptPayDO);
                    if(flag){
                        /*--------实支付款操作日志----start------*/
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put(NormalConstant.SERVICETYPE, StringConsts.ANSWEREXPENDRECEIPT);
                        map.put(NormalConstant.SERVICECODE, param.getId());
                        map.put(NormalConstant.OPERATETYPE, StringConsts.UPDATE);
                        map.put(NormalConstant.OPERATECONTENT, StringConsts.ANSWER_EXPEND_RECEIPT_DATA);
                        map.put(NormalConstant.OPERATEPIN, financeReceiptPayDO.getModifiedName());
                        operateLogRpc.saveOperateLog(map);
                        /*---------实支付款操作日志----end------*/
                        /******************** 修改待办事务统计付款已审核待支付的状态  start *******************************/
                        AgendaDO agendaDO = new AgendaDO();
                        agendaDO.setServiceCode(String.valueOf(param.getId()));
                        agendaDO.setIsDo(1);
                        agendaDO.setType(StringConsts.STATISTIC_PENDING_PAYMENT_AUDITED_PAID);
                        agendaDO.setModifiedName(param.getModifiedName());
                        agendaDO.setIp(param.getIp());
                        statisticsManager.updatBacklogStatisticsIsDo(agendaDO);
                        /******************** 修改待办事务统计付款已审核待支付的状态end *******************************/
                        result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                        result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                        result.setData(flag);
                    }else{
                        result.setCode(CommonEnum.REQUEST_FAIL.getCode());
                        result.setMessage(CommonEnum.REQUEST_FAIL.getMessage());
                    }
                }else{// 应支费用状态不是审核通过
                    result.setCode(PactCommonEnum.FINANCE_PAY_STATE_NO_REVIEW_INVALID.getCode());
                    result.setMessage(PactCommonEnum.FINANCE_PAY_STATE_NO_REVIEW_INVALID.getMessage());
                }
            }else{// 应支id无效
                result.setCode(PactCommonEnum.FINANCE_RECEIPT_PAY_ID_INVALID.getCode());
                result.setMessage(PactCommonEnum.FINANCE_RECEIPT_PAY_ID_INVALID.getMessage());
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
    public ApiResult<Boolean> saveReceiptPay(FinanceReceiptPayParam param) {
        ApiResult<Boolean> result = new ApiResult<>();
        if(null == param){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        try {
            FinanceReceiptPayDO financeReceiptPayDO = new FinanceReceiptPayDO();
            BeanUtils.copyProperties(param, financeReceiptPayDO);
            FinanceReceiptPayQuery query = new FinanceReceiptPayQuery();
            query.setPactCode(financeReceiptPayDO.getPactCode());
            FinanceReceiptPayVO financeReceiptPayVO = financeReceiptPayManager.getPayeeByRentPactCode(query);
            if(null != financeReceiptPayVO){
                financeReceiptPayDO.setHouseCode(financeReceiptPayVO.getHouseCode());
                financeReceiptPayDO.setRoomCode(financeReceiptPayVO.getRoomCode());
                financeReceiptPayDO.setAddress(financeReceiptPayVO.getAddress());
            }
            if(0 == financeReceiptPayDO.getType()){// 应收
                financeReceiptPayDO.setCostState(1);
            }else if(2 == financeReceiptPayDO.getType()){// 应支
                financeReceiptPayDO.setCostState(4);
                if(PactTypeConsts.RENT.equals(financeReceiptPayDO.getPactType()) && StringConsts.PAYEE_OBJECT_CUSTOMER.equals(financeReceiptPayDO.getPayeeObject())){
                    query.setPayeeObject(financeReceiptPayDO.getPayeeObject());
                    financeReceiptPayVO = financeReceiptPayManager.getPayeeByRentPactCode(query);
                    if(null != financeReceiptPayVO){
                        financeReceiptPayDO.setName(financeReceiptPayVO.getName());
                        financeReceiptPayDO.setTel(financeReceiptPayVO.getTel());
                        financeReceiptPayDO.setBank(financeReceiptPayVO.getBank());
                        financeReceiptPayDO.setOpenBank(financeReceiptPayVO.getOpenBank());
                        financeReceiptPayDO.setAccount(financeReceiptPayVO.getAccount());
                    }
                }else if(PactTypeConsts.RENT_TERMINATION.equals(financeReceiptPayDO.getPactType()) && StringConsts.PAYEE_OBJECT_CUSTOMER.equals(financeReceiptPayDO.getPayeeObject())){// 托出解约
                    PactRentTerminationQuery pactRentTerminationQuery = new PactRentTerminationQuery();
                    pactRentTerminationQuery.setDissolutionCode(financeReceiptPayDO.getPactCode());
                    PactRentTerminationAllVO pactRentTerminationAllVO = pactRentTerminationManager.getPactRentTermination(pactRentTerminationQuery);
                    if(null != pactRentTerminationAllVO){
                        PactRentTerminationVO pactRentTerminationVO = pactRentTerminationAllVO.getPactRentTerminationVO();
                        if(null != pactRentTerminationVO){
                            financeReceiptPayDO.setName(pactRentTerminationVO.getName());
                            financeReceiptPayDO.setTel(pactRentTerminationVO.getTel());
                            financeReceiptPayDO.setBank(pactRentTerminationVO.getCaseBank());
                            financeReceiptPayDO.setOpenBank(pactRentTerminationVO.getOpenBank());
                            financeReceiptPayDO.setAccount(pactRentTerminationVO.getAccountNum());
                        }
                    }
                }else if(PactTypeConsts.RENT_ROOM.equals(financeReceiptPayDO.getPactType()) && StringConsts.PAYEE_OBJECT_CUSTOMER.equals(financeReceiptPayDO.getPayeeObject())){
                    PactTransferAllVO pactTransferAllVO = pactRentTransferManager.getTransfer(financeReceiptPayDO.getPactCode());
                    if(null != pactTransferAllVO){
                        PactRentTransferVO pactRentTransferVO = pactTransferAllVO.getPactRentTransferVO();
                        if(null != pactRentTransferVO){
                            financeReceiptPayDO.setName(pactRentTransferVO.getName());
                            financeReceiptPayDO.setTel(pactRentTransferVO.getTel());
                            financeReceiptPayDO.setBank(pactRentTransferVO.getCaseBank());
                            financeReceiptPayDO.setOpenBank(pactRentTransferVO.getOpenBank());
                            financeReceiptPayDO.setAccount(pactRentTransferVO.getAccountNum());
                        }
                    }
                }else if(PactTypeConsts.RENT_SUBLET.equals(financeReceiptPayDO.getPactType()) && StringConsts.PAYEE_OBJECT_CUSTOMER.equals(financeReceiptPayDO.getPayeeObject())){
                    RentTurnVO rentTurnVO = rentTurnManager.getRentTurnByReletCode(financeReceiptPayDO.getPactCode());
                    if(null != rentTurnVO){
                        financeReceiptPayDO.setName(rentTurnVO.getPayeeName());
                        financeReceiptPayDO.setTel(rentTurnVO.getTel());
                        financeReceiptPayDO.setBank(rentTurnVO.getCaseBank());
                        financeReceiptPayDO.setOpenBank(rentTurnVO.getOpenBank());
                        financeReceiptPayDO.setAccount(rentTurnVO.getAccountNum());
                    }
                }else if(PactTypeConsts.PURCHASE.equals(financeReceiptPayDO.getPactType()) && StringConsts.PAYEE_OBJECT_OWNER.equals(financeReceiptPayDO.getPayeeObject())){
                    query.setPayeeObject(financeReceiptPayDO.getPayeeObject());
                    financeReceiptPayVO = financeReceiptPayManager.getPayeeByRentPactCode(query);
                    if(null != financeReceiptPayVO){
                        financeReceiptPayDO.setName(financeReceiptPayVO.getName());
                        financeReceiptPayDO.setTel(financeReceiptPayVO.getTel());
                        financeReceiptPayDO.setBank(financeReceiptPayVO.getBank());
                        financeReceiptPayDO.setOpenBank(financeReceiptPayVO.getOpenBank());
                        financeReceiptPayDO.setAccount(financeReceiptPayVO.getAccount());
                    }
                }else if(PactTypeConsts.PURCHASE_TERMINATION.equals(financeReceiptPayDO.getPactType()) && StringConsts.PAYEE_OBJECT_OWNER.equals(financeReceiptPayDO.getPayeeObject())){
                    query.setPayeeObject(financeReceiptPayDO.getPayeeObject());
                    financeReceiptPayVO = financeReceiptPayManager.getPayeeByRentPactCode(query);
                    if(null != financeReceiptPayVO){
                        financeReceiptPayDO.setName(financeReceiptPayVO.getName());
                        financeReceiptPayDO.setTel(financeReceiptPayVO.getTel());
                        financeReceiptPayDO.setBank(financeReceiptPayVO.getBank());
                        financeReceiptPayDO.setOpenBank(financeReceiptPayVO.getOpenBank());
                        financeReceiptPayDO.setAccount(financeReceiptPayVO.getAccount());
                    }
                }
            }
            financeReceiptPayDO.setIsValid(1);// 有效
            boolean flag = financeReceiptPayManager.saveReceiptPay(financeReceiptPayDO);
            if(flag){
                result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                result.setData(flag);
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
    /**
     * @Title: initPermissionsAllRecPay
     * @Description: TODO( 初始化列表权限查询对象 )
     * @author GuoXiaoPeng
     * @param param 权限作用域
     * @param query 数据查询对象
      */
    private void initPermissionsAllRecPay(FinanceReceiptPayQueryParam param, FinanceReceiptPayQuery query) {
        ApiResult<PersonalResult> personalApiResult = personalRpc.getPersonalResultBypin(param.getCreateName());
        PersonalResult personalResult = personalApiResult.getData();
        String position = personalResult.getPosition();
        Integer scope = param.getScope();
        if(StringConsts.BELONGER_SERVICE.equals(position)){
            query.setPosition(1);
        }else {
            query.setPosition(2);
        }    
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
    }
    /**
     * @Title: initPermissionsQuery
     * @Description: TODO( 初始化权限查询对象 )
     * @author GuoXiaoPeng
     * @param param 权限作用域
     * @param query 数据查询对象
      */
     private void initPermissionsQuery(FinanceReceiptPayQueryParam param, FinanceReceiptPayQuery query) {
         ApiResult<PersonalResult> personalApiResult = personalRpc.getPersonalResultBypin(param.getCreateName());
         PersonalResult personalResult = personalApiResult.getData();
         String position = personalResult.getPosition();
         Integer scope = param.getScope();
         position = StringConsts.BELONGER_SERVICE;
         if(StringConsts.BELONGER_SERVICE.equals(position)){
             query.setPosition(1);
         }else {
             query.setPosition(2);
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
     }
     
     @Override
     public ApiResult<Boolean> updateAnswerExpendReceipt(FinanceReceiptPayParam param) {
         ApiResult<Boolean> result = new ApiResult<>();
             if(null == param){
                 result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
                 result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
                 return result;
             }
             try {
                 FinanceReceiptPayVO realExpendDetail = financeReceiptPayManager.getRealExpendDetail(param.getId());
                 if(null != realExpendDetail){
                     if(10 == realExpendDetail.getCostState()){// 必须为已付款
                         FinanceReceiptPayDO financeReceiptPayDO = new FinanceReceiptPayDO();
                         BeanUtils.copyProperties(param, financeReceiptPayDO);
                         List<FinanceReceiptPayVoucherDO> financeReceiptPayVoucherList = new ArrayList<>();
                         if(null != param.getFinanceReceiptPayVoucherList()){
                             BeanUtils.copyListProperties(param.getFinanceReceiptPayVoucherList(), financeReceiptPayVoucherList, FinanceReceiptPayVoucherDO.class);
                             for (FinanceReceiptPayVoucherDO financeReceiptPayVoucherDO : financeReceiptPayVoucherList) {
                                 financeReceiptPayVoucherDO.setIp(financeReceiptPayDO.getIp());
                                 financeReceiptPayVoucherDO.setCreateName(financeReceiptPayDO.getCreateName());
                                 financeReceiptPayVoucherDO.setModifiedName(financeReceiptPayDO.getModifiedName());
                                 financeReceiptPayVoucherDO.setReceiptPayId(financeReceiptPayDO.getId());
                             }
                             financeReceiptPayDO.setFinanceReceiptPayVoucherList(financeReceiptPayVoucherList);
                         }
                         FinanceReceiptPayVO branchRecPay = financeReceiptPayManager.getPactAnswerExpendDetail(realExpendDetail.getPid());
                         int overdueDay = DateUtil.daysBetweenTwo(param.getCostTime(), branchRecPay.getCostTime());
                         financeReceiptPayDO.setOverdueDay("" + (overdueDay > 0 ? overdueDay : 0));
                         boolean flag = financeReceiptPayManager.answerExpendReceipt(financeReceiptPayDO);
                         if(flag){
                             result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                             result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                             result.setData(flag);
                         }else{
                             result.setCode(CommonEnum.REQUEST_FAIL.getCode());
                             result.setMessage(CommonEnum.REQUEST_FAIL.getMessage());
                         }
                     }else{
                         // 实支费用状态不是已付款
                         result.setCode(PactCommonEnum.FINANCE_PAY_STATE_NO_PAY_INVALID.getCode());
                         result.setMessage(PactCommonEnum.FINANCE_PAY_STATE_NO_PAY_INVALID.getMessage());
                     }
                 }else{
                     // 实支id无效
                     result.setCode(PactCommonEnum.FINANCE_RECEIPT_PAY_ID_INVALID.getCode());
                     result.setMessage(PactCommonEnum.FINANCE_RECEIPT_PAY_ID_INVALID.getMessage());
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
    /*******************get/set**************************/

    public FinanceReceiptPayManager getFinanceReceiptPayManager() {
        return financeReceiptPayManager;
    }

    public void setFinanceReceiptPayManager(FinanceReceiptPayManager financeReceiptPayManager) {
        this.financeReceiptPayManager = financeReceiptPayManager;
    }

    public FinanceReceiptPayVoucherManager getFinanceReceiptPayVoucherManager() {
        return financeReceiptPayVoucherManager;
    }

    public void setFinanceReceiptPayVoucherManager(FinanceReceiptPayVoucherManager financeReceiptPayVoucherManager) {
        this.financeReceiptPayVoucherManager = financeReceiptPayVoucherManager;
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

    public PactRentTerminationManager getPactRentTerminationManager() {
        return pactRentTerminationManager;
    }

    public void setPactRentTerminationManager(PactRentTerminationManager pactRentTerminationManager) {
        this.pactRentTerminationManager = pactRentTerminationManager;
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
    public StatisticsManager getStatisticsManager() {
        return statisticsManager;
    }
    public void setStatisticsManager(StatisticsManager statisticsManager) {
        this.statisticsManager = statisticsManager;
    }
}
