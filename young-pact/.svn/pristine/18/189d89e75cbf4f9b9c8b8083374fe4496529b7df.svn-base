package com.young.pact.dao.financeamortize;

import java.util.List;

import com.young.common.exception.DaoException;
import com.young.pact.domain.financeamortize.FinanceAmortizeDO;
import com.young.pact.domain.financeamortize.FinanceAmortizeQuery;
import com.young.pact.domain.financeamortize.FinanceAmortizeVO;

/**
 * 
* @ClassName: FinanceAmortizeDao
* @Description: TODO( 摊销)
* @author HeZeMin
* @date 2018年8月6日 上午10:47:48
*
 */
public interface FinanceAmortizeDao {
    /**
     * 
    * @Title: saveFinanceAmortizes
    * @Description: TODO( 批量添加摊销)
    * @author HeZeMin
    * @param amortizeDOs    摊销信息 集合
    * @return   返回影响个数
    * @throws   自定义异常
     */
    int saveFinanceAmortizes(List<FinanceAmortizeDO> amortizeDOs) throws DaoException;
    /**
     * 
    * @Title: countFinanceAmortize
    * @Description: TODO( 查询列表个数)
    * @author HeZeMin
    * @param financeAmortizeQuery 查询条件
    * @return   返回摊销列表个数
    * @throws   自定义异常
     */
    int countFinanceAmortize(FinanceAmortizeQuery financeAmortizeQuery) throws DaoException;
    /**
     * 
    * @Title: listFinanceAmortize
    * @Description: TODO( 查询列表数据)
    * @author HeZeMin
    * @param financeAmortizeQuery   查询条件
    * @return   返回摊销列表数据
    * @throws   自定义异常
     */
    List<FinanceAmortizeVO> listFinanceAmortize(FinanceAmortizeQuery financeAmortizeQuery) throws DaoException;
}
