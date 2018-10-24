package com.young.pact.controller.financeamortize;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.tools.common.springmvc.base.BaseController;
import com.young.common.annotation.PermissionsCode;
import com.young.common.domain.ApiResult;
import com.young.common.page.PageBean;
import com.young.pact.api.domain.param.rest.financeamortize.FinanceAmortizeQueryParam;
import com.young.pact.api.domain.result.rest.financeamortize.FinanceAmortizeListResult;
import com.young.pact.api.service.rest.financeamortize.FinanceAmortizeRestService;
/**
 * 
* @ClassName: FinanceAmortizeController
* @Description: TODO( 摊销)
* @author HeZeMin
* @date 2018年10月10日 下午4:26:27
*
 */
@RestController
@RequestMapping(value = "/financeamortize", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class FinanceAmortizeController extends BaseController {
    /*********************声明区**********************/
    private static final Log LOG = LogFactory.getLog(FinanceAmortizeController.class);
    private FinanceAmortizeRestService financeAmortizeRestService;
    /*********************方法区**********************/
    /**
     * 
    * @Title: listFinanceAmortize
    * @Description: TODO( 摊销列表)
    * @author HeZeMin
     */
    @PermissionsCode(code = "rest_financeamortize_page")
    @RequestMapping(value = "/listFinanceAmortize", method = RequestMethod.POST)
    public ApiResult<PageBean<FinanceAmortizeListResult>> listFinanceAmortize(HttpServletRequest request, @RequestBody FinanceAmortizeQueryParam param) {
        return financeAmortizeRestService.listFinanceAmortize(param);
    }
    /*********************get/set区**********************/
    public FinanceAmortizeRestService getFinanceAmortizeRestService() {
        return financeAmortizeRestService;
    }
    public void setFinanceAmortizeRestService(FinanceAmortizeRestService financeAmortizeRestService) {
        this.financeAmortizeRestService = financeAmortizeRestService;
    }
}
