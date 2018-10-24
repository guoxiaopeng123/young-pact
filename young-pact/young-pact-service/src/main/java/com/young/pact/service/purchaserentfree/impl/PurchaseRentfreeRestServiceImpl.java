package com.young.pact.service.purchaserentfree.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.tools.common.util.convert.BeanUtils;
import com.young.common.dict.CommonEnum;
import com.young.common.domain.ApiResult;
import com.young.pact.api.domain.result.rest.purchaserentfree.PurchaseRentfreeResult;
import com.young.pact.api.service.rest.purchaserentfree.PurchaseRentfreeRestService;
import com.young.pact.common.dict.PactCommonEnum;
import com.young.pact.common.exception.PactManagerExcepion;
import com.young.pact.domain.purchaserentfree.PurchaseRentfreeVO;
import com.young.pact.manager.purchaserentfree.PurchaseRentfreeManager;
/**
 * 
* @ClassName: PurchaseRentfreeRestServiceImpl
* @Description: TODO( 免租期)
* @author HeZeMin
* @date 2018年8月6日 下午4:07:38
*
 */
@Service("purchaseRentfreeRestService")
public class PurchaseRentfreeRestServiceImpl implements PurchaseRentfreeRestService {
    /**********************声明区***************************/
    private static final Log LOG = LogFactory.getLog(PurchaseRentfreeRestServiceImpl.class);
    private PurchaseRentfreeManager purchaseRentfreeManager;
    /**********************方法区***************************/
    @Override
    public ApiResult<List<PurchaseRentfreeResult>> listPurchaseRentfree(String purchaseCode) {
        ApiResult<List<PurchaseRentfreeResult>> result = new ApiResult<>();
        if(StringUtils.isBlank(purchaseCode)){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        try {
            List<PurchaseRentfreeVO> purchaseRentfreeList = purchaseRentfreeManager.listPurchaseRentfree(purchaseCode);
            if(null != purchaseRentfreeList){
                List<PurchaseRentfreeResult> purchaseRentfreeResults = new ArrayList<>();
                BeanUtils.copyListProperties(purchaseRentfreeList, purchaseRentfreeResults, PurchaseRentfreeResult.class);
                
                result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                result.setData(purchaseRentfreeResults);
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
    
    
    
    
    /**********************get/set***************************/
    public PurchaseRentfreeManager getPurchaseRentfreeManager() {
        return purchaseRentfreeManager;
    }
    public void setPurchaseRentfreeManager(PurchaseRentfreeManager purchaseRentfreeManager) {
        this.purchaseRentfreeManager = purchaseRentfreeManager;
    }
    

}
