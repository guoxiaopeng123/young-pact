package com.young.pact.dao.rentcommonowner.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tools.common.ibatis.base.BaseDao;
import com.young.common.exception.DaoException;
import com.young.pact.dao.rentcommonowner.CommonOwnerDao;
import com.young.pact.domain.rentcommonowner.RentCommonOwnerDO;
import com.young.pact.domain.rentcommonowner.RentCommonOwnerVO;

/**
 * @描述 : 托出租客共同居住人 
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月3日 下午6:39:25
 */
@Repository("commonOwnerDao")
public class CommonOwnerDaoImpl extends BaseDao implements CommonOwnerDao {

    @Override
    public int savaCommonOwner(List<RentCommonOwnerDO> commonOwnerDOList)throws DaoException {
        try {
            return this.batchInsert("Rentcommonowner.savaCommonOwner", commonOwnerDOList);
        } catch (Exception e) {
            // TODO: handle exception
            throw new DaoException(e);
        }
    }

    @SuppressWarnings({ "deprecation", "unchecked" })
    @Override
    public List<RentCommonOwnerVO> listCommonOwnerByRenterCode(String renterCode)throws DaoException {
        try {
            return this.queryForList("Rentcommonowner.listCommonOwnerByRenterCode",renterCode);
        } catch (Exception e) {
            // TODO: handle exception
            throw new DaoException(e);
        }
    }

    @Override
    public int updateCommonOwner(List<RentCommonOwnerDO> cohabitantList) throws DaoException {
        try {
           int batchUpdate = this.batchUpdate("Rentcommonowner.updateCommonOwner", cohabitantList);
           return batchUpdate;
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void removeCommonOwner(String renterCode) throws DaoException {
        try {
            this.delete("Rentcommonowner.removeCommonOwner", renterCode);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}
