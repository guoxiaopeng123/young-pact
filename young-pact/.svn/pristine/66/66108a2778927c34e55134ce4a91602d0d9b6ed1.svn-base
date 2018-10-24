import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tools.common.util.json.JsonUtil;
import com.young.common.exception.DaoException;
import com.young.common.page.PageBean;
import com.young.pact.dao.commongoods.CommonGoodsDao;
import com.young.pact.domain.commongoods.CommonGoodsQuery;
import com.young.pact.domain.commongoods.GoodsVO;
import com.young.pact.manager.commongoods.CommonGoodsManager;

/**
 * @描述 : 
 * @创建者 : guoxiaopeng
 * @创建时间 : 2018年8月28日 下午6:40:38
 */
public class Test2 {

    @SuppressWarnings({ "resource", "unused" })
    public static void main(String[] args) throws DaoException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-config.xml");
        CommonGoodsDao commonGoodsDao = (CommonGoodsDao) context.getBean("commonGoodsDao");
        CommonGoodsQuery query = new CommonGoodsQuery();
        query.setPageIndex(1);
        query.setPageSize(10);
        query.setPactCode("RT310572");
//        List<GoodsVO> listGoods = commonGoodsDao.listGoods(query);
//        System.out.println(JsonUtil.toJson(listGoods));
        
        
        CommonGoodsManager commonGoodsManager = (CommonGoodsManager) context.getBean("commonGoodsManager");
        PageBean<GoodsVO> listGoods = commonGoodsManager.listGoods(query);
        System.out.println(JsonUtil.toJson(listGoods));
        
        
//        CommonGoodsPicDao commonGoodsPicDao =  (CommonGoodsPicDao) context.getBean("commonGoodsPicDao");
//        
//        List<Long> goodsIds = new ArrayList<>();
//        goodsIds.add(46L);
//        List<CommonGoodsPicVO> listGoodsPicByGdIds = commonGoodsPicDao.listGoodsPicByGdIds(goodsIds );
//        System.out.println(JsonUtil.toJson(listGoodsPicByGdIds));
    }

}
