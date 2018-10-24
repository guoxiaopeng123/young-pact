import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tools.common.util.json.JsonUtil;
import com.young.common.dict.CommonEnum;
import com.young.common.domain.ApiResult;
import com.young.common.page.PageBean;
import com.young.pact.api.domain.param.rest.purchasebase.PurchaseBaseQueryParam;
import com.young.pact.api.domain.param.rest.purchasetermination.PurchaseTerminationQueryParam;
import com.young.pact.api.domain.result.rest.purchasebase.PurchaseBaseListResult;
import com.young.pact.api.domain.result.rest.purchasetermination.PurchaseTerminationListResult;
import com.young.pact.api.service.rest.purchasebase.PurchaseBaseRestService;
import com.young.pact.api.service.rest.purchasetermination.PurchaseTerminationRestService;

/**
 * @描述 : 
 * @创建者 : guoxiaopeng
 * @创建时间 : 2018年8月28日 下午6:40:38
 */
public class Test {

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-config.xml");
        PurchaseBaseRestService purchaseBaseRestService = (PurchaseBaseRestService) context.getBean("purchaseBaseRestService");
        purBaseTest(purchaseBaseRestService);
        PurchaseTerminationRestService purchaseTerminationRestService = (PurchaseTerminationRestService) context.getBean("purchaseTerminationRestService");
        PurchaseTerminationQueryParam purchaseTerminationQueryParam = new PurchaseTerminationQueryParam();
        purchaseTerminationQueryParam.setScope(1);
        purchaseTerminationQueryParam.setCreateName("admin");
        purchaseTerminationQueryParam.setPageIndex(1);
        purchaseTerminationQueryParam.setPageSize(10);
        ApiResult<PageBean<PurchaseTerminationListResult>> result = purchaseTerminationRestService.listPurchaseTermination(purchaseTerminationQueryParam);
        if(CommonEnum.REQUEST_SUCCESS.equals(result.getCode())){
            PageBean<PurchaseTerminationListResult> data = result.getData();
            System.out.println("==============================================");
            System.out.println(JsonUtil.toJson(data.getData()));
            System.out.println("==============================================");
        }
    }

    private static void purBaseTest(PurchaseBaseRestService purchaseBaseRestService) {
        PurchaseBaseQueryParam param = new PurchaseBaseQueryParam();
        param.setScope(0);
        param.setCreateName("admin");
        param.setPageIndex(1);
        param.setPageSize(10);
        ApiResult<PageBean<PurchaseBaseListResult>> result = purchaseBaseRestService.listPurchaseBase(param);
        
        if(CommonEnum.REQUEST_SUCCESS.equals(result.getCode())){
            PageBean<PurchaseBaseListResult> data = result.getData();
            System.out.println("==============================================");
            System.out.println(JsonUtil.toJson(data.getData()));
            System.out.println("==============================================");
        }
    }
}
