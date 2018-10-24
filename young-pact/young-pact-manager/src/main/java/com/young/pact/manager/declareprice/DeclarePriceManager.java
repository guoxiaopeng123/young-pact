package com.young.pact.manager.declareprice;

import java.util.List;

import com.young.pact.domain.declareprice.DeclarePriceVO;

/**
 * @描述 : 申报价格
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月13日 下午4:42:10
 */
public interface DeclarePriceManager {

    /**
    * @Title: listDeclarePriceByDecCode
    * @Description: TODO(根据申报编码查询申报价格 )
    * @author GuoXiaoPeng
    * @param declareCode 申报编码
    * @return 申报价格结合
    * @throws 异常
     */
    List<DeclarePriceVO> listDeclarePriceByDecCode(String declareCode);

}
