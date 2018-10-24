package com.young.pact.manager.purchaserentfree;

import java.util.List;

import com.young.pact.domain.purchaserentfree.PurchaseRentfreeVO;

/**
 * 
* @ClassName: PurchaseRentfreeManager
* @Description: TODO( 免租期)
* @author HeZeMin
* @date 2018年8月6日 下午4:10:20
*
 */
public interface PurchaseRentfreeManager {
    /**
     * 
    * @Title: listPurchaseRentfree
    * @Description: TODO( 根据托进合同编码查询托进合同免租期信息集合)
    * @author HeZeMin
    * @param purchaseCode   托进合同编码
    * @return   返回托进合同免租期信息集合
     */
    List<PurchaseRentfreeVO> listPurchaseRentfree(String purchaseCode);
}
