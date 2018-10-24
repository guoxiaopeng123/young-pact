package com.young.pact.service.propertytransfer.impl;

import java.util.ArrayList;
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
import com.young.pact.api.domain.param.rest.propertytransfer.PropertyTransferParam;
import com.young.pact.api.domain.result.rest.commongoods.CommonGoodsResult;
import com.young.pact.api.domain.result.rest.commonmeterread.CommonMeterReadResult;
import com.young.pact.api.domain.result.rest.propertytransfer.PropertyTransferResult;
import com.young.pact.api.service.rest.propertytransfer.PropertyTransferRestService;
import com.young.pact.common.constant.StringConsts;
import com.young.pact.common.dict.PactCommonEnum;
import com.young.pact.common.exception.PactManagerExcepion;
import com.young.pact.domain.commongoods.CommonGoodsDO;
import com.young.pact.domain.commonmeterread.CommonMeterReadDO;
import com.young.pact.manager.propertytransfer.PropertyTransferManager;
/**
 * 
* @ClassName: PropertyTransferRestServiceImpl
* @Description: TODO( 物业交接)
* @author HeZeMin
* @date 2018年8月2日 下午3:29:21
*
 */
@Service("propertyTransferRestService")
public class PropertyTransferRestServiceImpl implements PropertyTransferRestService {
    /*******************声明区**************************/
    private static final Log LOG = LogFactory.getLog(PropertyTransferRestServiceImpl.class);
    private PropertyTransferManager propertyTransferManager;
    /*******************方法区**************************/
    @Override
    public ApiResult<String> savePropertyTransferRedis(PropertyTransferParam param) {
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
            // redis key
            StringBuilder sb = new StringBuilder();
            // 抄表
            List<CommonMeterReadDO> commonMeterReadList = new ArrayList<>();
            if(null != param.getCommonMeterReadList()){
                BeanUtils.copyListProperties(param.getCommonMeterReadList(), commonMeterReadList, CommonMeterReadDO.class);
                map.put("commonMeterReadList", commonMeterReadList);
            }
            // 物品
            List<CommonGoodsDO> commonGoodsList = new ArrayList<>();
            if(null != param.getCommonGoodsList()){
                BeanUtils.copyListProperties(param.getCommonGoodsList(), commonGoodsList, CommonGoodsDO.class);
                map.put("commonGoodsList", commonGoodsList);
            }
            if(StringUtils.isNotBlank(param.getDeclareCode())){
                map.put("declareCode", param.getDeclareCode());
                sb.append(param.getDeclareCode()).append(StringConsts.UNDERLINE)
                .append(StringConsts.PROPERTY_TRANSFER).append(StringConsts.UNDERLINE).append(param.getCreateName());
            }else if(StringUtils.isNotBlank(param.getHouseCode())){
                map.put("houseCode", param.getHouseCode());
                sb.append(param.getHouseCode()).append(StringConsts.UNDERLINE)
                .append(StringConsts.PROPERTY_TRANSFER).append(StringConsts.UNDERLINE).append(param.getCreateName());
            }
            propertyTransferManager.savePropertyTransferRedis(map, sb.toString());
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
    public ApiResult<PropertyTransferResult> getPropertyTransferRedis(String redisKey) {
        ApiResult<PropertyTransferResult> result = new ApiResult<>();
        if(StringUtils.isBlank(redisKey)){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        try {
            Map<String, Object> map = propertyTransferManager.getPropertyTransferRedis(redisKey);
            if(null != map && map.size() > 0){
                PropertyTransferResult propertyTransferResult = new PropertyTransferResult();
                if(map.containsKey("declareCode")){
                    propertyTransferResult.setDeclareCode((String) map.get("declareCode"));
                }else if(map.containsKey("houseCode")){
                    propertyTransferResult.setHouseCode((String) map.get("houseCode"));
                }
                // 抄表
                List<CommonMeterReadDO> commonMeterReadList = (List<CommonMeterReadDO>) map.get("commonMeterReadList");
                if(null != commonMeterReadList){
                    List<CommonMeterReadResult> commonMeterReadResults = new ArrayList<>();
                    BeanUtils.copyListProperties(commonMeterReadList, commonMeterReadResults, CommonMeterReadResult.class);
                    propertyTransferResult.setCommonMeterReadList(commonMeterReadResults);
                }
                // 物品
                List<CommonGoodsDO> commonGoodsList = (List<CommonGoodsDO>) map.get("commonGoodsList");
                if(null != commonGoodsList){
                    List<CommonGoodsResult> commonGoodsResults = new ArrayList<>();
                    BeanUtils.copyListProperties(commonGoodsList, commonGoodsResults, CommonGoodsResult.class);
                    propertyTransferResult.setCommonGoodsList(commonGoodsResults);
                }
                result.setData(propertyTransferResult);
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
    /*******************get/set**************************/

    public PropertyTransferManager getPropertyTransferManager() {
        return propertyTransferManager;
    }

    public void setPropertyTransferManager(PropertyTransferManager propertyTransferManager) {
        this.propertyTransferManager = propertyTransferManager;
    }
}
