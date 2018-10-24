package com.young.pact.dao.commonmeterread;

import java.util.List;

import com.young.common.exception.DaoException;
import com.young.pact.domain.commonmeterread.CommonMeterReadPicDO;
import com.young.pact.domain.commonmeterread.CommonMeterReadPicVO;

/**
 * 
* @ClassName: CommonMeterReadPicDao
* @Description: TODO( 抄表图片)
* @author HeZeMin
* @date 2018年8月2日 下午10:04:37
*
 */
public interface CommonMeterReadPicDao {
    /**
     * 
    * @Title: saveCommonMeterReadPic
    * @Description: TODO( 保存物品图片集合)
    * @author HeZeMin
    * @param commonMeterReadPicDOs  物品图片集合
    * @return   返回插入条数
    * @throws DaoException;
    * @throws   异常
     */
    int saveCommonMeterReadPic(List<CommonMeterReadPicDO> commonMeterReadPicDOs) throws DaoException;
    /**
    * @Title: listMeterReadByMeterId
    * @Description: TODO( 根据抄表id查询抄表图片集合 )
    * @author GuoXiaoPeng
    * @param id 抄表id
    * @return 抄表图片集合 
    * @throws DaoException
    * @throws 异常
     */
    List<CommonMeterReadPicVO> listMeterReadByMeterId(Long id)throws DaoException;
    /**
    * @Title: removeMeterReadByMeterId
    * @Description: TODO( 根据抄表id删除抄表图片 )
    * @author GuoXiaoPeng
    * @param id
    * @return
    * @throws DaoException
    * @throws
     */
    int removeMeterReadByMeterId(Long id)throws DaoException;
}
