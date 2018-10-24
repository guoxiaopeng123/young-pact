package com.young.pact.dao.purchaserentfree;

import java.util.List;
import java.util.Map;

import com.young.common.exception.DaoException;
import com.young.pact.domain.purchaserentfree.PurchaseRentfreeDO;
import com.young.pact.domain.purchaserentfree.PurchaseRentfreeVO;

/**
 * 
* @ClassName: PurchaseRentfreeDao
* @Description: TODO( 托进免租期)
* @author HeZeMin
* @date 2018年8月2日 下午10:02:50
*
 */
public interface PurchaseRentfreeDao {
    /**
     * 
    * @Title: savePurchaseRentfree
    * @Description: TODO( 保存托进合同免租期信息)
    * @author HeZeMin
    * @param purchaseRentfreeDOs    托进合同免租期信息集合
    * @return   返回插入个数
    * @throws   异常
     */
    int savePurchaseRentfree(List<PurchaseRentfreeDO> purchaseRentfreeDOs) throws DaoException;
    /**
     * 
    * @Title: listPurchaseRentfree
    * @Description: TODO( 根据托进合同编码查询托进合同免租期信息集合)
    * @author HeZeMin
    * @param purchaseCode   托进合同编码
    * @return   返回托进合同免租期信息集合
    * @throws   异常
     */
    List<PurchaseRentfreeVO> listPurchaseRentfree(String purchaseCode) throws DaoException;
    /**
     * 
    * @Title: removePurchaseRentfree
    * @Description: TODO( 根据托进合同编码删除免租期信息)
    * @author HeZeMin
    * @param purchaseCode   托进合同编码
    * @return   影响行数
    * @throws   异常
     */
    int removePurchaseRentfree(String purchaseCode) throws DaoException;
    /**
     * 
    * @Title: removeNotPurchaseRentfree
    * @Description: TODO( 删除 不包含 免租期集合数据 的免租期！！)
    * @author HeZeMin
    * @param purchaseRentfreeList    免租期集合
    * @param purchaseCode      托进合同编码
    * @return   返回删除结果
    * @throws   异常
     */
    int removeNotPurchaseRentfree(Map<String, Object> map) throws DaoException;
    /**
     * 
    * @Title: updatePurchaseRentfree
    * @Description: TODO( 批量修改免租期)
    * @author HeZeMin
    * @param purchaseRentfreeDOs    免租期集合
    * @return   返回修改结果 
    * @throws   异常
     */
    int updatePurchaseRentfree(List<PurchaseRentfreeDO> purchaseRentfreeDOs) throws DaoException;
}
