package com.young.pact.dao.commonmeterread.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tools.common.ibatis.base.BaseDao;
import com.young.common.exception.DaoException;
import com.young.pact.dao.commonmeterread.CommonMeterReadDao;
import com.young.pact.domain.commonmeterread.CommonMeterReadDO;
import com.young.pact.domain.commonmeterread.CommonMeterReadVO;
/**
 * 
* @ClassName: CommonMeterReadDaoImpl
* @Description: TODO( 物业 抄表)
* @author HeZeMin
* @date 2018年8月2日 下午10:04:25
*
 */
@SuppressWarnings("all")
@Repository("commonMeterReadDao")
public class CommonMeterReadDaoImpl extends BaseDao implements CommonMeterReadDao {

    @Override
    public Long saveCommonMeterRead(CommonMeterReadDO commonMeterReadDO) throws DaoException {
        try {
            return (Long) this.insert("CommonMeterRead.saveCommonMeterRead", commonMeterReadDO);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int removeCommonMeterRead(String pactCode) throws DaoException {
        try {
            return this.delete("CommonMeterRead.removeCommonMeterRead", pactCode);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<CommonMeterReadVO> listCommonMeterReadByPactCode(String pactCode) throws DaoException {
        try {
            return this.queryForList("CommonMeterRead.listCommonMeterReadByPactCode", pactCode);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public CommonMeterReadVO getMeterReadById(Long id) throws DaoException {
        try {
            return (CommonMeterReadVO) this.queryForObject("CommonMeterRead.getMeterReadById", id);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int updateMeterReadById(CommonMeterReadDO commonMeterReadDO) throws DaoException {
        try {
            return this.update("CommonMeterRead.updateMeterReadById", commonMeterReadDO);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int removeCommonMeterReadById(CommonMeterReadDO commonMeterReadDO) throws DaoException {
        try {
            return this.delete("CommonMeterRead.removeCommonMeterReadById", commonMeterReadDO);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}
