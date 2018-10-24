package com.young.pact.dao.commonmeterread.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tools.common.ibatis.base.BaseDao;
import com.young.common.exception.DaoException;
import com.young.pact.dao.commonmeterread.CommonMeterReadPicDao;
import com.young.pact.domain.commonmeterread.CommonMeterReadPicDO;
import com.young.pact.domain.commonmeterread.CommonMeterReadPicVO;
/**
 * 
* @ClassName: CommonMeterReadPicDaoImpl
* @Description: TODO(抄表图片 )
* @author HeZeMin
* @date 2018年8月2日 下午10:04:43
*
 */
@SuppressWarnings("all")
@Repository("commonMeterReadPicDao")
public class CommonMeterReadPicDaoImpl extends BaseDao implements CommonMeterReadPicDao {

    @Override
    public int saveCommonMeterReadPic(List<CommonMeterReadPicDO> commonMeterReadPicDOs) throws DaoException {
        try {
            return this.batchInsert("CommonMeterReadPic.saveCommonMeterReadPic", commonMeterReadPicDOs);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<CommonMeterReadPicVO> listMeterReadByMeterId(Long meterReadId) throws DaoException {
        try {
            return this.queryForList("CommonMeterReadPic.listMeterReadByMeterId", meterReadId);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int removeMeterReadByMeterId(Long meterReadId) throws DaoException {
        try {
            return this.delete("CommonMeterReadPic.removeMeterReadByMeterId", meterReadId);
        } catch (Exception e) {
            // TODO: handle exception
            throw new DaoException(e);
        }
    }

}
