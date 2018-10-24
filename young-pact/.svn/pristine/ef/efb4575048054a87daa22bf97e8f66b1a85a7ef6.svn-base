package com.young.pact.dao.commonbelonger.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tools.common.ibatis.base.BaseDao;
import com.young.common.exception.DaoException;
import com.young.pact.dao.commonbelonger.CommonBelongerDao;
import com.young.pact.domain.commonbelonger.CommonBelongerDO;
import com.young.pact.domain.commonbelonger.CommonBelongerQuery;
import com.young.pact.domain.commonbelonger.CommonBelongerVO;
/**
 * 
* @ClassName: CommonBelongerDaoImpl
* @Description: TODO( 合同责任人)
* @author HeZeMin
* @date 2018年8月2日 下午10:06:36
*
 */
@SuppressWarnings("all")
@Repository("commonBelongerDao")
public class CommonBelongerDaoImpl extends BaseDao implements CommonBelongerDao {

    @Override
    public int saveCommonBelonger(List<CommonBelongerDO> commonBelongerDOs) throws DaoException {
        try {
            return this.batchInsert("CommonBelonger.saveCommonBelonger", commonBelongerDOs);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<CommonBelongerVO> listCommonBelonger(String pactCode) throws DaoException {
        try {
            return this.queryForList("CommonBelonger.listCommonBelonger", pactCode);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int removeCommonBelonger(String pactCode) throws DaoException {
        try {
            return this.delete("CommonBelonger.removeCommonBelonger", pactCode);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int updateCommonBelonger(CommonBelongerDO commonBelongerDO) throws DaoException {
        try {
            return this.update("CommonBelonger.updateCommonBelonger", commonBelongerDO);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }


    @Override
    public int updateCommonBelongers(CommonBelongerDO commonBelongerDO) throws DaoException {
        try {
            return this.update("CommonBelonger.updateCommonBelongers", commonBelongerDO);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }


    @Override
    public int batchUpdateCommonBelonger(List<CommonBelongerDO> commonBelongerDOs) throws DaoException {
        try {
            return this.batchUpdate("CommonBelonger.batchUpdateCommonBelonger", commonBelongerDOs);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public CommonBelongerVO getBelongerByParam(CommonBelongerQuery belongerQuery) throws DaoException {
        try {
            return (CommonBelongerVO) this.queryForObject("CommonBelonger.getBelongerByParam", belongerQuery);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int batchSaveCommonBelonger(List<CommonBelongerDO> addBelongerDOs) throws DaoException {
        try {
            return this.batchInsert("CommonBelonger.batchSaveCommonBelonger", addBelongerDOs);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}
