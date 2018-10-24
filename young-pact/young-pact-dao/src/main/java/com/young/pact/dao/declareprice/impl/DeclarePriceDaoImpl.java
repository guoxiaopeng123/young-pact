package com.young.pact.dao.declareprice.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tools.common.ibatis.base.BaseDao;
import com.young.common.exception.DaoException;
import com.young.pact.dao.declareprice.DeclarePriceDao;
import com.young.pact.domain.declareprice.DeclarePriceDO;
import com.young.pact.domain.declareprice.DeclarePriceVO;

/**
 * @描述 : 申报价格
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月13日 下午9:00:57
 */
@Repository("declarePriceDao")
public class DeclarePriceDaoImpl extends BaseDao implements DeclarePriceDao {

    @Override
    public int saveDeclarePrice(List<DeclarePriceDO> declarePriceDOs) throws DaoException {
        try {
            return this.batchInsert("DeclarePrice.saveDeclarePrice", declarePriceDOs);
        } catch (Exception e) {
            // TODO: handle exception
            throw new DaoException(e);
        }
    }

    @SuppressWarnings({ "deprecation", "unchecked" })
    @Override
    public List<DeclarePriceVO> listDeclarePriceByDecCode(String declareCode) throws DaoException {
        try {
            return this.queryForList("DeclarePrice.listDeclarePriceByDecCode", declareCode);
        } catch (Exception e) {
            // TODO: handle exception
            throw new DaoException(e);
        }
    }
}
