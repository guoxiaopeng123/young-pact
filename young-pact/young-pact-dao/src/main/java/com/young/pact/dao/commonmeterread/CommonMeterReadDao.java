package com.young.pact.dao.commonmeterread;

import java.util.List;

import com.young.common.exception.DaoException;
import com.young.pact.domain.commonmeterread.CommonMeterReadDO;
import com.young.pact.domain.commonmeterread.CommonMeterReadVO;

/**
 * 
* @ClassName: CommonMeterReadDao
* @Description: TODO( 物业 抄表)
* @author HeZeMin
* @date 2018年8月2日 下午10:04:19
*
 */
public interface CommonMeterReadDao {
    /**
     * 
    * @Title: saveCommonMeterRead
    * @Description: TODO( 保存物品信息)
    * @author HeZeMin
    * @param commonMeterReadDO  物品信息
    * @return   返回id
    * @throws DaoException;
    * @throws   异常
     */
    Long saveCommonMeterRead(CommonMeterReadDO commonMeterReadDO) throws DaoException;
    /**
     * 
    * @Title: removeCommonMeterRead
    * @Description: TODO( 根据合同编码删除抄表信息及抄表图片)
    * @author HeZeMin
    * @param pactCode   合同编码
    * @return   返回影响行数
    * @throws DaoException;
    * @throws   异常
     */
    int removeCommonMeterRead(String pactCode) throws DaoException;
    /**
     * @Title: listCommonMeterReadByPactCode
     * @Description: TODO( 根据合同编码查询抄表信息集合)
     * @author GuoXiaoPeng
     * @param pactCode 合同编码
     * @return 抄表信息集合
     * @throws DaoException;
     * @throws 异常
     */
    List<CommonMeterReadVO> listCommonMeterReadByPactCode(String pactCode)throws DaoException;
    /**
    * @Title: getMeterReadById
    * @Description: TODO( 根据抄表id获取抄表详情 )
    * @author GuoXiaoPeng
    * @param id 抄表id
    * @return 抄表详情
    * @throws DaoException;
    * @throws 异常
     */
    CommonMeterReadVO getMeterReadById(Long id)throws DaoException;
    /***
    * @Title: updateMeterReadById
    * @Description: TODO( 根据抄表id修改抄表 )
    * @author GuoXiaoPeng
    * @param commonMeterReadDO 抄表信息
    * @return 影响的记录数
    * @throws DaoException
    * @throws 异常
     */
    int updateMeterReadById(CommonMeterReadDO commonMeterReadDO)throws DaoException;
    /**
    * @Title: removeCommonMeterRead
    * @Description: TODO( 根据抄表id删除抄表 )
    * @author GuoXiaoPeng
    * @param commonMeterReadDO 抄表id
    * @return  影响的记录数
    * @throws DaoException
    * @throws 异常
     */
    int removeCommonMeterReadById(CommonMeterReadDO commonMeterReadDO)throws DaoException;
}
