package com.young.pact.service.rentcustomer.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.LogFactoryImpl;
import org.springframework.stereotype.Service;

import com.tools.common.util.convert.BeanUtils;
import com.tools.common.util.string.StringUtil;
import com.young.common.dict.CommonEnum;
import com.young.common.domain.ApiResult;
import com.young.pact.api.domain.param.rest.commonowner.CommonOwnerParam;
import com.young.pact.api.domain.param.rest.rentcustomer.RentCustomerParam;
import com.young.pact.api.domain.param.rest.rentcustomer.RenterParam;
import com.young.pact.api.domain.result.rest.commonowner.CommonOwnerResult;
import com.young.pact.api.domain.result.rest.rentcustomer.RentCustomerResult;
import com.young.pact.api.domain.result.rest.rentcustomer.RenterResult;
import com.young.pact.api.service.rest.rentcustomer.RentCustomerService;
import com.young.pact.common.constant.StringConsts;
import com.young.pact.common.dict.PactCommonEnum;
import com.young.pact.common.exception.PactManagerExcepion;
import com.young.pact.common.util.RegexUtil;
import com.young.pact.domain.rentcommonowner.RentCommonOwnerDO;
import com.young.pact.domain.rentcustomer.CustomerOwnerDO;
import com.young.pact.domain.rentcustomer.CustomerOwnerVO;
import com.young.pact.domain.rentcustomer.RentCustomerDO;
import com.young.pact.manager.rentcustomer.RentCustomerManager;

/**
 * @描述 : 托出租客
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月1日 下午5:04:16
 */
@Service("rentCustomerService")
public class RentCustomerServiceImpl implements RentCustomerService{

    private static Log LOG = LogFactoryImpl.getLog(RentCustomerServiceImpl.class);
    
    private RentCustomerManager rentCustomerManager;
    @Override
    public ApiResult<String> saveRentCustomerRedis(RentCustomerParam rentCustomerParam) {
        ApiResult<String> result = new ApiResult<>();
        StringBuilder key = new StringBuilder();
        String pin = ""; 
        try {
            result = validNull(rentCustomerParam);
            if(StringUtil.isNotEmpty(result.getCode())){
                return result;
            }
            List<CommonOwnerParam> cohabitantList = rentCustomerParam.getCohabitantList();
            if(null!= cohabitantList && cohabitantList.size() > 0){
                for(CommonOwnerParam commonOwnerParam :cohabitantList){
                    if(!RegexUtil.is_tel(commonOwnerParam.getTel())){
                        result.setCode(PactCommonEnum.TEL_NOT_MATCH.getCode());
                        result.setMessage(PactCommonEnum.TEL_NOT_MATCH.getMessage());
                        return result;
                    }
                }
            }
            pin = rentCustomerParam.getCreateName() == null?"":rentCustomerParam.getCreateName();
            key.append(rentCustomerParam.getServiceCode()).append(StringConsts.UNDERLINE)
            .append(StringConsts.REDIS_RENTER_KEY).append(StringConsts.UNDERLINE).append(pin);
            CustomerOwnerDO  customerOwnerDO= new CustomerOwnerDO();
            customerOwnerDO.setKey(key.toString());
            BeanUtils.copyProperties(rentCustomerParam, customerOwnerDO);
            RentCustomerDO renter = new RentCustomerDO();
            BeanUtils.copyProperties(rentCustomerParam.getRenter(), renter);
            List<RentCommonOwnerDO> rentCommonOwnerDOs=new ArrayList<>();
            BeanUtils.copyListProperties(cohabitantList, rentCommonOwnerDOs, RentCommonOwnerDO.class);
            customerOwnerDO.setCohabitantList(rentCommonOwnerDOs);
            customerOwnerDO.setRenter(renter);
            rentCustomerManager.saveRentCustomerRedis(customerOwnerDO);
            result.setData(customerOwnerDO.getKey());
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
    * @Title: validNull
    * @Description: TODO( 校验必填项 )
    * @author GuoXiaoPeng
    * @param rentCustomerParam 租客 + 共同居住人
    * @return 返回结果
     */
    private ApiResult<String> validNull(RentCustomerParam param) {
        ApiResult<String> result = new ApiResult<>();
        RenterParam renter = null;
        List<CommonOwnerParam> cohabitantList = null;
        if(param == null){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        if(null == param.getType()){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        if(StringUtil.isBlank(param.getServiceCode())){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        renter = param.getRenter();
        cohabitantList = param.getCohabitantList();
        if(StringUtil.isBlank(renter.getName())){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
        }
        if(null == renter.getSex()){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
        }
        if(StringUtil.isBlank(renter.getTel())){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
        }
        if(!RegexUtil.is_tel(renter.getTel())){
            result.setCode(PactCommonEnum.TEL_NOT_MATCH.getCode());
            result.setMessage(PactCommonEnum.TEL_NOT_MATCH.getMessage());
            return result;
        }
        if(StringUtil.isBlank(renter.getIdType())){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        if(StringUtil.isBlank(renter.getIdCode())){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        if(null == renter.getBirthday()){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        if(StringUtil.isBlank(renter.getCardPositiveUrl())){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        if(StringUtil.isBlank(renter.getCardBackUrl())){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        if(StringUtil.isBlank(renter.getProfession())){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        if(StringUtil.isBlank(renter.getMaritalStatus())){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        if(StringUtil.isBlank(renter.getNativePlance())){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        if(null == renter.getMonthlyIncome()){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        if(StringUtil.isBlank(renter.getLabel())){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        if(StringUtil.isBlank(renter.getEmergencyContact())){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        
        if(!RegexUtil.is_tel(renter.getEmergencyTel())){
            result.setCode(PactCommonEnum.TEL_NOT_MATCH.getCode());
            result.setMessage(PactCommonEnum.TEL_NOT_MATCH.getMessage());
            return result;
        }
        if(StringUtil.isBlank(renter.getEmergencyRelation())){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        if(null!=cohabitantList&&cohabitantList.size()>0){
            for(CommonOwnerParam commonOwnerParam:cohabitantList){
                if(StringUtil.isBlank(commonOwnerParam.getName())){
                    result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
                    result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
                    return result;
                }
                if(null == commonOwnerParam.getSex()){
                    result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
                    result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
                    return result;
                }
                if(!RegexUtil.is_tel(commonOwnerParam.getTel())){
                    result.setCode(PactCommonEnum.TEL_NOT_MATCH.getCode());
                    result.setMessage(PactCommonEnum.TEL_NOT_MATCH.getMessage());
                    return result;
                }
                if(StringUtil.isBlank(commonOwnerParam.getIdType())){
                    result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
                    result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
                    return result;
                }
                if(StringUtil.isBlank(commonOwnerParam.getIdCode())){
                    result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
                    result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
                    return result;
                }
                if(null == commonOwnerParam.getBirthday()){
                    result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
                    result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
                    return result;
                }
                if(StringUtil.isBlank(commonOwnerParam.getCardPositiveUrl())){
                    result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
                    result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
                    return result;
                }
                if(StringUtil.isBlank(commonOwnerParam.getCardBackUrl())){
                    result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
                    result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
                    return result;
                }
            }
        }
        return result;
    }
    @Override
    public ApiResult<RentCustomerResult> getRentCustomerRedis(String key) {
        ApiResult<RentCustomerResult> result = new ApiResult<>();
        try {
            CustomerOwnerVO customerOwnerVO = rentCustomerManager.getRentCustomerRedis(key);
            if(null != customerOwnerVO){
                RentCustomerResult rentCustomerResult = new RentCustomerResult();
                BeanUtils.copyProperties(customerOwnerVO, rentCustomerResult);
                List<CommonOwnerResult> commonOwnerResults = new ArrayList<>();
                BeanUtils.copyListProperties(customerOwnerVO.getCohabitantList(), commonOwnerResults, CommonOwnerResult.class);
                rentCustomerResult.setCohabitantList(commonOwnerResults);
                RenterResult renter = new RenterResult();
                BeanUtils.copyProperties(customerOwnerVO.getRenter(), renter);
                rentCustomerResult.setRenter(renter);
                result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                result.setData(rentCustomerResult);
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

    public RentCustomerManager getRentCustomerManager() {
        return rentCustomerManager;
    }

    public void setRentCustomerManager(RentCustomerManager rentCustomerManager) {
        this.rentCustomerManager = rentCustomerManager;
    }
}
