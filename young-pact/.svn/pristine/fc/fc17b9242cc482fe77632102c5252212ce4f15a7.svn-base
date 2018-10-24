package com.young.pact.dao.commonpic.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tools.common.ibatis.base.BaseDao;
import com.young.common.exception.DaoException;
import com.young.pact.dao.commonpic.CommonPicDao;
import com.young.pact.domain.commonpic.CommonPicDO;
import com.young.pact.domain.commonpic.CommonPicVO;
/**
 * 
* @ClassName: CommonPicDaoImpl
* @Description: TODO( 合同公共图片)
* @author HeZeMin
* @date 2018年8月2日 下午10:04:01
*
 */
@SuppressWarnings("all")
@Repository("commonPicDao")
public class CommonPicDaoImpl extends BaseDao implements CommonPicDao {

    @Override
    public int saveCommonPic(List<CommonPicDO> commonPicDOs) throws DaoException {
        try {
            return this.batchInsert("CommonPic.saveCommonPic", commonPicDOs);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<CommonPicVO> listCommonPic(String pactCode) throws DaoException {
        try {
            return this.queryForList("CommonPic.listCommonPic", pactCode);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int removeCommonPic(String pactCode) throws DaoException {
        try {
            return this.delete("CommonPic.removeCommonPic", pactCode);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int removeNotCommonPic(Map<String, Object> map) throws DaoException {
        try {
            return this.delete("CommonPic.removeNotCommonPic", map);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int updateCommonPic(List<CommonPicDO> commonPicDOs) throws DaoException {
        try {
            return this.batchUpdate("CommonPic.updateCommonPic", commonPicDOs);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

}
