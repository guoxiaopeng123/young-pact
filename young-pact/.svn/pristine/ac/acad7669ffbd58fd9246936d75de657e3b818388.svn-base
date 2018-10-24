package com.young.pact.dao.commongoods.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tools.common.ibatis.base.BaseDao;
import com.young.common.exception.DaoException;
import com.young.pact.dao.commongoods.CommonGoodsPicDao;
import com.young.pact.domain.commongoods.CommonGoodsPicDO;
import com.young.pact.domain.commongoods.CommonGoodsPicVO;
/**
 * 
* @ClassName: CommonGoodsPicDaoImpl
* @Description: TODO( 物品图片)
* @author HeZeMin
* @date 2018年8月2日 下午10:05:59
*
 */
@SuppressWarnings("all")
@Repository("commonGoodsPicDao")
public class CommonGoodsPicDaoImpl extends BaseDao implements CommonGoodsPicDao {

    @Override
    public int saveCommonGoodsPic(List<CommonGoodsPicDO> commonGoodsPicDOs) throws DaoException {
        try {
            return this.batchInsert("CommonGoodsPic.saveCommonGoodsPic", commonGoodsPicDOs);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

	@Override
	public int updateCommonGoodsPic(List<CommonGoodsPicDO> commonGoodsPicDO)throws DaoException {
		try {
			return this.batchUpdate("CommonGoodsPic.updatePic", commonGoodsPicDO);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

    @Override
    public List<CommonGoodsPicVO> listGoodsPicByGdIds(List<Long> goodsIds) throws DaoException {
        try {
            return this.queryForList("CommonGoodsPic.listGoodsPicByGdIds", goodsIds);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int removeGoodsByGoodId(Long goodsId) throws DaoException {
        try {
            return this.delete("CommonGoodsPic.removeGoodsByGoodId", goodsId);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

}
