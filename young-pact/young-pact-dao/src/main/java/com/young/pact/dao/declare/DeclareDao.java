package com.young.pact.dao.declare;

import java.util.List;

import com.young.common.exception.DaoException;
import com.young.pact.domain.declare.DeclareDO;
import com.young.pact.domain.declare.DeclareQuery;
import com.young.pact.domain.declare.DeclareVO;

/**
 * @描述 : 申报
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月13日 下午1:40:02
 */
public interface DeclareDao {

    /**
    * @Title: saveDeclare
    * @Description: TODO( 保存申报)
    * @author GuoXiaoPeng
    * @param declareDO 申报信息
    * @return 记录标识
    * @throws DaoException
    * @throws 异常
     */
    Long saveDeclare(DeclareDO declareDO) throws DaoException;
    /**
    * @Title: listDeclare
    * @Description: TODO( 分页查询申报列表 )
    * @author GuoXiaoPeng
    * @param declareQuery 申报查询条件
    * @return 申报列表
    * @throws DaoException
    * @throws 异常
     */
    List<DeclareVO> listDeclare(DeclareQuery declareQuery)throws DaoException;
    /**
    * @Title: countDeclare
    * @Description: TODO( 查询申报总数 )
    * @author GuoXiaoPeng
    * @param declareQuery 申报查询条件
    * @return 申报总数
    * @throws DaoException
    * @throws 异常
     */
    Integer countDeclare(DeclareQuery declareQuery)throws DaoException;
    /**
    * @Title: getDeclareByDeclareCode
    * @Description: TODO( 根据申报编码查询申报详情 )
    * @author GuoXiaoPeng
    * @param declareCode 申报编码
    * @return 申报详情 
    * @throws DaoException;
    * @throws 异常
     */
    DeclareVO  getDeclareByDeclareCode(String declareCode)throws DaoException;
    /**
    * @Title: updateDeclareState
    * @Description: TODO( 修改申报审核状态 )
    * @author GuoXiaoPeng
    * @param declareDO
    * @return 记录标识
    * @throws DaoException;
    * @throws 异常
     */
    Integer updateDeclareState(DeclareDO declareDO)throws DaoException;
    /**
    * @Title: listDeclareByParam
    * @Description: TODO( 条件查询申报 )
    * @author GuoXiaoPeng
    * @param declareQuery 查询条件
    * @return 申报集合
    * @throws DaoException
    * @throws 异常
     */
    List<DeclareVO> listDeclareByParam(DeclareQuery declareQuery)throws DaoException;
    /**
     * @Title: countUnauditedDeclare
     * @Description: TODO(根据房源编码查询未审核状态的申报数量 )
     * @author GuoXiaoPeng
     * @param houseCode 房源编码
     * @return 未审核状态的申报数量 
     * @throws DaoException;
     * @throws 异常
      */
    Integer countUnauditedDeclare(String houseCode)throws DaoException;
}
