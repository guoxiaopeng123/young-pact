package com.young.pact.dao.commoncostrule.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tools.common.ibatis.base.BaseDao;
import com.young.common.exception.DaoException;
import com.young.pact.dao.commoncostrule.CommonCostRuleDao;
import com.young.pact.domain.commoncostrule.CommonCostRuleDO;
import com.young.pact.domain.commoncostrule.CommonCostRuleVO;
import com.young.pact.domain.purchasehouse.PurchaseHouseVO;
/**
 * 
* @ClassName: CommonCostRuleDaoImpl
* @Description: TODO( 合同收支规则)
* @author HeZeMin
* @date 2018年8月2日 下午10:06:17
*
 */
@SuppressWarnings("all")
@Repository("commonCostRuleDao")
public class CommonCostRuleDaoImpl extends BaseDao implements CommonCostRuleDao {

    @Override
    public int saveCommonCostRule(List<CommonCostRuleDO> commonCostRuleDOs) throws DaoException {
        try {
            return this.batchInsert("CommonCostRule.saveCommonCostRule", commonCostRuleDOs);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<CommonCostRuleVO> listCommonCostRule(String pactCode) throws DaoException {
        try {
            return this.queryForList("CommonCostRule.listCommonCostRule", pactCode);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int removeCommonCostRule(String pactCode) throws DaoException {
        try {
            return this.delete("CommonCostRule.removeCommonCostRule", pactCode);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int removeNotCommonCostRule(Map<String, Object> map) throws DaoException {
        try {
            return this.delete("CommonCostRule.removeNotCommonCostRule", map);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int updateCommonCostRule(List<CommonCostRuleDO> commonCostRuleDOs) throws DaoException {
        try {
            return this.batchUpdate("CommonCostRule.updateCommonCostRule", commonCostRuleDOs);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

}
