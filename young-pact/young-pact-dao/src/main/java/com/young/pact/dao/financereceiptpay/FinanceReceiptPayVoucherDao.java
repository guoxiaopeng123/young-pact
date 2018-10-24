package com.young.pact.dao.financereceiptpay;

import java.util.List;
import java.util.Map;

import com.young.common.exception.DaoException;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayVoucherDO;

/**
 * 
* @ClassName: FinanceReceiptPayVoucherDao
* @Description: TODO( 收支- 凭证)
* @author HeZeMin
* @date 2018年8月8日 下午9:37:13
*
 */
public interface FinanceReceiptPayVoucherDao {
    /**
     * 
    * @Title: listVoucherByPids
    * @Description: TODO( 根据实收、实支 id集合查询凭证集合)
    * @author HeZeMin
    * @param pids   实收、实支 id集合
    * @return   返回凭证集合
    * @throws   异常
     */
    List<FinanceReceiptPayVoucherDO> listVoucherByPids(List<Long> pids) throws DaoException;
    /**
     * 
    * @Title: saveFinanceReceiptPayVouchers
    * @Description: TODO( 批量插入收支凭证)
    * @author HeZeMin
    * @param financeReceiptPayVoucherDOs    收支凭证
    * @return   返回添加结果
    * @throws   异常
     */
    int saveFinanceReceiptPayVouchers(List<FinanceReceiptPayVoucherDO> financeReceiptPayVoucherDOs) throws DaoException;
    /**
     * 
    * @Title: updateFinanceReceiptPayVouchers
    * @Description: TODO( 批量修改收支凭证)
    * @author HeZeMin
    * @param financeReceiptPayVoucherDOs    实支凭证
    * @return   返回修改结果
    * @throws   异常
     */
    int updateFinanceReceiptPayVouchers(List<FinanceReceiptPayVoucherDO> financeReceiptPayVoucherDOs) throws DaoException;
    /**
     * 
    * @Title: removeVouchersByPid
    * @Description: TODO( 删除 不包含凭证集合数据 的凭证！！)
    * @author HeZeMin
    * @param map    收支id、凭证
    * @return   返回删除结果
    * @throws   异常
     */
    int removeVouchersByPid(Map<String, Object> map) throws DaoException;
    
}
