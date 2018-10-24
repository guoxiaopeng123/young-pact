package com.young.pact.dao.declare.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tools.common.ibatis.base.BaseDao;
import com.young.common.exception.DaoException;
import com.young.pact.dao.declare.DeclareDao;
import com.young.pact.domain.declare.DeclareDO;
import com.young.pact.domain.declare.DeclareQuery;
import com.young.pact.domain.declare.DeclareVO;

/**
 * @描述 : 申报
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月13日 下午1:40:28
 */
@Repository("declareDao")
public class DeclareDaoImpl extends BaseDao implements DeclareDao {

    @SuppressWarnings("deprecation")
    @Override
    public Long saveDeclare(DeclareDO declareDO) throws DaoException {
        try {
            return (Long) this.insert("Declare.saveDeclare", declareDO);
        } catch (Exception e) {
            // TODO: handle exception
            throw new DaoException(e);
        }
    }

    @SuppressWarnings({ "deprecation", "unchecked" })
    @Override
    public List<DeclareVO> listDeclare(DeclareQuery declareQuery) throws DaoException {
        try {
            return  this.queryForList("Declare.listDeclare", declareQuery);
        } catch (Exception e) {
            // TODO: handle exception
            throw new DaoException(e);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public Integer countDeclare(DeclareQuery declareQuery) throws DaoException {
        try {
            return  (Integer) this.queryForObject("Declare.countDeclare", declareQuery);
        } catch (Exception e) {
            // TODO: handle exception
            throw new DaoException(e);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public DeclareVO getDeclareByDeclareCode(String declareCode) throws DaoException {
        try {
            return (DeclareVO) this.queryForObject("Declare.getDeclareByDeclareCode", declareCode);
        } catch (Exception e) {
            // TODO: handle exception
            throw new DaoException(e);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public Integer updateDeclareState(DeclareDO declareDO) throws DaoException {
        try {
            return this.update("Declare.updateDeclareState", declareDO);
        } catch (Exception e) {
            // TODO: handle exception
            throw new DaoException(e);
        }
    }

    @SuppressWarnings({ "deprecation", "unchecked" })
    @Override
    public List<DeclareVO> listDeclareByParam(DeclareQuery declareQuery) throws DaoException {
        try {
            return this.queryForList("Declare.listDeclareByParam", declareQuery);
        } catch (Exception e) {
            // TODO: handle exception
            throw new DaoException(e);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public Integer countUnauditedDeclare(String houseCode) throws DaoException {
        try {
            return (Integer) this.queryForObject("Declare.countUnauditedDeclare", houseCode);
        } catch (Exception e) {
            // TODO: handle exception
            throw new DaoException(e);
        }
    }

}
