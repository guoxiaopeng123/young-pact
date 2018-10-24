package com.young.pact.manager.costrule.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.young.pact.common.constant.ErrorPactConsts;
import com.young.pact.common.exception.PactManagerExcepion;
import com.young.pact.dao.commoncostrule.CommonCostRuleDao;
import com.young.pact.domain.commoncostrule.CommonCostRuleVO;
import com.young.pact.manager.costrule.CommonCostRuleManager;

/**
 * @描述 : 
 * @创建者 : GuoXiaoPeng
 * @创建时间 : 2018年9月30日 下午3:03:34
 */
@Component("commonCostRuleManager")
public class CommonCostRuleManagerImpl implements CommonCostRuleManager{

    private static final Log LOG = LogFactory.getLog(CommonCostRuleManagerImpl.class);
    private CommonCostRuleDao commonCostRuleDao;
    @Override
    public List<CommonCostRuleVO> listCommonCostRule(String pactCode) {
        try {
            return commonCostRuleDao.listCommonCostRule(pactCode);
        } catch (Exception e) {
            LOG.error(ErrorPactConsts.LIST_COST_RULE_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.LIST_COST_RULE_ERROR,e);
        }
    }
    public CommonCostRuleDao getCommonCostRuleDao() {
        return commonCostRuleDao;
    }
    public void setCommonCostRuleDao(CommonCostRuleDao commonCostRuleDao) {
        this.commonCostRuleDao = commonCostRuleDao;
    }
    
}
