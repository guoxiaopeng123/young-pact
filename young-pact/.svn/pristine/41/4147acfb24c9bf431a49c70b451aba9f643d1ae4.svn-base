package com.young.pact.manager.financeamortize.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.young.common.exception.DaoException;
import com.young.common.page.PageBean;
import com.young.common.page.PageTableBean;
import com.young.pact.common.constant.ErrorPactConsts;
import com.young.pact.common.exception.PactManagerExcepion;
import com.young.pact.dao.financeamortize.FinanceAmortizeDao;
import com.young.pact.domain.financeamortize.FinanceAmortizeQuery;
import com.young.pact.domain.financeamortize.FinanceAmortizeVO;
import com.young.pact.manager.financeamortize.FinanceAmortizeManager;
/**
 * 
* @ClassName: FinanceAmortizeManagerImpl
* @Description: TODO( 摊销)
* @author HeZeMin
* @date 2018年10月10日 下午3:49:36
*
 */
@Component("financeAmortizeManager")
public class FinanceAmortizeManagerImpl implements FinanceAmortizeManager {
    /********************声明区*************************/
    private static final Log LOG = LogFactory.getLog(FinanceAmortizeManagerImpl.class);
    private FinanceAmortizeDao financeAmortizeDao;
    /********************方法区*************************/
    @Override
    public PageBean<FinanceAmortizeVO> listFinanceAmortize(FinanceAmortizeQuery query) {
        try {
            int count = financeAmortizeDao.countFinanceAmortize(query);
            PageBean<FinanceAmortizeVO> page = new PageTableBean<FinanceAmortizeVO>(query.getPageIndex(), query.getPageSize());
            if (count != 0){
                page.setTotalItem(count);
                int startRow = page.getStartRow();
                if (startRow > 0) {
                    startRow -= 1;
                }
                query.setStartRow(startRow);
                page.addAll(financeAmortizeDao.listFinanceAmortize(query));
            }
            return page;
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.LIST_FINANCEAMORTIZE, e);
            throw new PactManagerExcepion(ErrorPactConsts.LIST_FINANCEAMORTIZE, e);
        }
    }
    /********************get/set*************************/
    public FinanceAmortizeDao getFinanceAmortizeDao() {
        return financeAmortizeDao;
    }
    public void setFinanceAmortizeDao(FinanceAmortizeDao financeAmortizeDao) {
        this.financeAmortizeDao = financeAmortizeDao;
    }

}
