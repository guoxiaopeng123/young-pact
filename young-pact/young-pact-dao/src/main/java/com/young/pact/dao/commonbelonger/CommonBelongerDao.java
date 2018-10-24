package com.young.pact.dao.commonbelonger;

import java.util.List;

import com.young.common.exception.DaoException;
import com.young.pact.domain.commonbelonger.CommonBelongerDO;
import com.young.pact.domain.commonbelonger.CommonBelongerQuery;
import com.young.pact.domain.commonbelonger.CommonBelongerVO;

/**
 * 
* @ClassName: CommonBelongerDao
* @Description: TODO(合同责任人 )
* @author HeZeMin
* @date 2018年8月2日 下午10:06:29
*
 */
public interface CommonBelongerDao {
    /**
     * 
    * @Title: saveCommonBelonger
    * @Description: TODO( 保存合同责任人信息)
    * @author HeZeMin
    * @param commonBelongerDOs  合同责任人信息
    * @return   返回插入条数
    * @throws DaoException;
    * @throws   异常
     */
    int saveCommonBelonger(List<CommonBelongerDO> commonBelongerDOs) throws DaoException;
    /**
     * 
    * @Title: listCommonBelonger
    * @Description: TODO( 根据合同编码查询合同责任人信息集合)
    * @author HeZeMin
    * @param pactCode   合同编码
    * @return   返回合同责任人信息集合
    * @throws DaoException;
    * @throws   异常
     */
    List<CommonBelongerVO> listCommonBelonger(String pactCode) throws DaoException;
    /**
     * 
    * @Title: removeCommonBelonger
    * @Description: TODO( 根据合同编码删除合同责任人信息)
    * @author HeZeMin
    * @param pactCode   合同编码
    * @return   返回影响行数
    * @throws DaoException;
    * @throws   异常
     */
    int removeCommonBelonger(String pactCode) throws DaoException;
    /**
     * 
    * @Title: updateCommonBelonger
    * @Description: TODO( 修改合同责任人)
    * @author HeZeMin
    * @param commonBelongerDO   合同责任人信息
    * @return   返回影响行数
    * @throws DaoException;
    * @throws   异常
     */
    int updateCommonBelonger(CommonBelongerDO commonBelongerDO) throws DaoException;

    /**
     * 
    * @Title: updateCommonBelongers
    * @Description: 根据合同编码修改责任人
    * @author SunYiXuan
    * @param commonBelongerDO
    * @return
    * @throws DaoException
    * @throws
     */
    int updateCommonBelongers(CommonBelongerDO commonBelongerDO) throws DaoException;

    /**
     * 
    * @Title: updateCommonBelonger
    * @Description: TODO( 修改合同责任人)
    * @author HeZeMin
    * @param commonBelongerDO   合同责任人信息
    * @return   返回影响行数
    * @throws DaoException 
    * @throws   异常
     */
    int batchUpdateCommonBelonger(List<CommonBelongerDO> commonBelongerDOs) throws DaoException;
    /**
    * @Title: getBelongerByParam
    * @Description: TODO( 根据合同编码和负责人角色查询负责人 )
    * @author GuoXiaoPeng
    * @param belongerQuery 合同编码和负责人角色
    * @return 负责人
    * @throws DaoException;
    * @throws 异常
     */
    CommonBelongerVO getBelongerByParam(CommonBelongerQuery belongerQuery)throws DaoException;
    /**
    * @Title: batchSaveCommonBelonger
    * @Description: TODO( 批量添加负责人 )
    * @author GuoXiaoPeng
    * @param addBelongerDOs 负责人集合
    * @return 影响的记录数
    * @throws DaoException
    * @throws 异常
     */
    int batchSaveCommonBelonger(List<CommonBelongerDO> addBelongerDOs)throws DaoException;
}
