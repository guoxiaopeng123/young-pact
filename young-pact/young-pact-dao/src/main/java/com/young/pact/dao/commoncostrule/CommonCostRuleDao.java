package com.young.pact.dao.commoncostrule;

import java.util.List;
import java.util.Map;

import com.young.common.exception.DaoException;
import com.young.pact.domain.commoncostrule.CommonCostRuleDO;
import com.young.pact.domain.commoncostrule.CommonCostRuleVO;

/**
 * 
* @ClassName: CommonCostRuleDao
* @Description: TODO( 合同收支规则)
* @author HeZeMin
* @date 2018年8月2日 下午10:06:12
*
 */
public interface CommonCostRuleDao {
    /**
     * 
    * @Title: saveCommonCostRule
    * @Description: TODO( 保存托进合同收支规则)
    * @author HeZeMin
    * @param commonCostRuleDOs   合同收支规则集合
    * @return   返回插入条数
    * @throws DaoException;
    * @throws   异常
     */
    int saveCommonCostRule(List<CommonCostRuleDO> commonCostRuleDOs) throws DaoException;
    /**
     * 
    * @Title: getCommonCostRule
    * @Description: TODO( 根据合同编码查询合同收支规则信息集合)
    * @author HeZeMin
    * @param pactCode   合同编码
    * @return   返回合同收支规则信息集合
    * @throws DaoException;
    * @throws   异常
     */
    List<CommonCostRuleVO> listCommonCostRule(String pactCode) throws DaoException;
    /**
     * 
    * @Title: removeCommonCostRule
    * @Description: TODO( 根据合同编码查询合同收支规则)
    * @author HeZeMin
    * @param pactCode   合同编码
    * @return   返回影响行数
    * @throws DaoException;
    * @throws   异常
     */
    int removeCommonCostRule(String pactCode) throws DaoException;
    /**
     * 
    * @Title: removeNotCommonCostRule
    * @Description: TODO( 删除 不包含 收支规则集合数据 的收支规则！！)
    * @author HeZeMin
    * @param commonCostRuleList  收支规则集合
    * @param pactCode   合同编码
    * @return   返回删除结果
    * @throws DaoException;
    * @throws   异常
     */
    int removeNotCommonCostRule(Map<String, Object> map) throws DaoException;
    /**
     * 
    * @Title: updateCommonCostRule
    * @Description: TODO( 批量修改收支规则集合)
    * @author HeZeMin
    * @param commonCostRuleDOs  收支规则集合
    * @return   返回修改结果
    * @throws DaoException;
    * @throws   异常
     */
    int updateCommonCostRule(List<CommonCostRuleDO> commonCostRuleDOs) throws DaoException;
}
