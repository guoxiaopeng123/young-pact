package com.young.pact.service.financeamortize.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.tools.common.util.convert.BeanUtils;
import com.young.common.dict.CommonEnum;
import com.young.common.domain.ApiResult;
import com.young.common.page.PageBean;
import com.young.common.page.PageTableBean;
import com.young.pact.api.domain.param.rest.financeamortize.FinanceAmortizeQueryParam;
import com.young.pact.api.domain.result.rest.financeamortize.FinanceAmortizeListResult;
import com.young.pact.api.service.rest.financeamortize.FinanceAmortizeRestService;
import com.young.pact.common.dict.PactCommonEnum;
import com.young.pact.common.exception.PactManagerExcepion;
import com.young.pact.domain.financeamortize.FinanceAmortizeQuery;
import com.young.pact.domain.financeamortize.FinanceAmortizeVO;
import com.young.pact.manager.financeamortize.FinanceAmortizeManager;
/**
 * 
* @ClassName: FinanceAmortizeServiceImpl
* @Description: TODO( 摊销)
* @author HeZeMin
* @date 2018年10月10日 下午4:24:21
*
 */
@Service("financeAmortizeRestService")
public class FinanceAmortizeServiceImpl implements FinanceAmortizeRestService {
    /*******************声明区**************************/
    private static final Log LOG = LogFactory.getLog(FinanceAmortizeServiceImpl.class);
    private FinanceAmortizeManager financeAmortizeManager;
    /*******************方法区**************************/
    
    @Override
    public ApiResult<PageBean<FinanceAmortizeListResult>> listFinanceAmortize(FinanceAmortizeQueryParam param) {
        ApiResult<PageBean<FinanceAmortizeListResult>> result = new ApiResult<>();
        if(null == param || null == param.getCostType()){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        try {
            FinanceAmortizeQuery query = new FinanceAmortizeQuery();
            BeanUtils.copyProperties(param, query);
            PageBean<FinanceAmortizeVO> financeAmortizeList = financeAmortizeManager.listFinanceAmortize(query);
            if(financeAmortizeList.getTotalItem() > 0){
                PageBean<FinanceAmortizeListResult> resultList = new PageTableBean<FinanceAmortizeListResult>(query.getPageIndex(),query.getPageSize());
                resultList.setTotalItem(financeAmortizeList.getTotalItem());
                // copy分页数据
                BeanUtils.copyListProperties(financeAmortizeList.getData(), resultList.getData(), FinanceAmortizeListResult.class);
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
    /*******************get/set区**************************/
    public FinanceAmortizeManager getFinanceAmortizeManager() {
        return financeAmortizeManager;
    }
    public void setFinanceAmortizeManager(FinanceAmortizeManager financeAmortizeManager) {
        this.financeAmortizeManager = financeAmortizeManager;
    }
    
}
