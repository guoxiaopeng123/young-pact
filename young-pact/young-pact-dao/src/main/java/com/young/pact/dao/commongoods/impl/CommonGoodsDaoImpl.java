package com.young.pact.dao.commongoods.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tools.common.ibatis.base.BaseDao;
import com.young.common.exception.DaoException;
import com.young.pact.dao.commongoods.CommonGoodsDao;
import com.young.pact.domain.commongoods.CommonGoodsDO;
import com.young.pact.domain.commongoods.CommonGoodsQuery;
import com.young.pact.domain.commongoods.GoodsVO;
/**
 * 
* @ClassName: CommonGoodsDaoImpl
* @Description: TODO(  物业物品)
* @author HeZeMin
* @date 2018年8月2日 下午10:05:33
*
 */
@SuppressWarnings("all")
@Repository("commonGoodsDao")
public class CommonGoodsDaoImpl extends BaseDao implements CommonGoodsDao {

    @Override
    public Long saveCommonGoods(CommonGoodsDO commonGoodsDO) throws DaoException {
        try {
            return (Long) this.insert("CommonGoods.saveCommonGoods", commonGoodsDO);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int removeCommonGoods(String pactCode) throws DaoException {
        try {
            return this.delete("CommonGoods.removeCommonGoods", pactCode);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<GoodsVO> listCommonGoodsByPactCode(String pactCode) throws DaoException {
        try {
            return this.queryForList("CommonGoods.listCommonGoodsByPactCode", pactCode);
        } catch (Exception e) {
            // TODO: handle exception
            throw new DaoException(e);
        }
    }

	@Override
	public GoodsVO getGoods(Long id) throws DaoException {
		try {
			return (GoodsVO) this.queryForObject("CommonGoods.getGoods", id);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int updateGoods(CommonGoodsDO commonGoodsDO) throws DaoException {
		try {
			return this.update("CommonGoods.updateGoods", commonGoodsDO);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int delGoods(CommonGoodsDO commonGoodsDO) throws DaoException {
		try {
			return this.delete("CommonGoods.delCommonGoods", commonGoodsDO);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int countGoods(String pactCode) throws DaoException {
		try {
			return (int) this.queryForObject("CommonGoods.countCommonGoodsByPactCode", pactCode);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<GoodsVO> listGoods(CommonGoodsQuery query) throws DaoException {
		try {
			return this.queryForList("CommonGoods.listGoods", query);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

}
