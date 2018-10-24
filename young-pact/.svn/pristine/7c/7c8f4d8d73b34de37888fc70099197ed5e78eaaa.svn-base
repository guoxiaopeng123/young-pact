package com.young.pact.controller.commontradingrecord;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tools.common.springmvc.base.BaseController;
import com.young.common.annotation.PermissionsCode;
import com.young.common.domain.ApiResult;
import com.young.common.page.PageBean;
import com.young.pact.api.domain.param.rest.commontradingrecord.CommonTradingRecordQueryParam;
import com.young.pact.api.domain.result.rest.commontradingrecord.CommonTradingRecordResult;
import com.young.pact.api.service.rest.commontradingrecord.CommonTradingRecordService;
/**
 * 
* @ClassName: TradingRecordController
* @Description: TODO( 交易记录 )
* @author GuoXiaoPeng
* @date 2018年10月9日 上午9:47:00
*
 */
@RestController()
@RequestMapping("/tradingrecord")
public class TradingRecordController extends BaseController{

    private static Log LOG = LogFactory.getLog(TradingRecordController.class);
    private CommonTradingRecordService commonTradingRecordService;
    /**
    * @Title: listTradingRecord
    * @Description: TODO( 分页查询交易记录列表  )
    * @author GuoXiaoPeng
    * @param param 房间编码
    * @return 交易记录集合
    * @throws 异常
     */
    @PermissionsCode(code = "rest_tradingrecord_page")
    @RequestMapping(value = "/listTradingRecord", method = RequestMethod.POST)
    ApiResult<PageBean<CommonTradingRecordResult>> listTradingRecord(@RequestBody CommonTradingRecordQueryParam param){
        ApiResult<PageBean<CommonTradingRecordResult>> result = new ApiResult<>();
        try {
            result = commonTradingRecordService.listTradingRecord(param);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
        
    }
    public CommonTradingRecordService getCommonTradingRecordService() {
        return commonTradingRecordService;
    }
    public void setCommonTradingRecordService(CommonTradingRecordService commonTradingRecordService) {
        this.commonTradingRecordService = commonTradingRecordService;
    }
    
}
