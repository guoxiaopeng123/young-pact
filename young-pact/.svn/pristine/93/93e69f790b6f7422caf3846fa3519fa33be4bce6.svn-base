package com.young.pact.manager.declare;

import java.util.List;

import com.young.common.page.PageBean;
import com.young.pact.domain.declare.DeclareDO;
import com.young.pact.domain.declare.DeclareQuery;
import com.young.pact.domain.declare.DeclareVO;
import com.young.pact.domain.declareprice.DeclarePriceDO;

/**
 * @描述 : 申报
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月13日 下午1:43:48
 */
public interface DeclareManager {

    /**
    * @Title: listDeclareByParam
    * @Description: TODO( 根据条件查询申报集合 )
    * @author GuoXiaoPeng
    * @param declareQuery 申报查询条件
    * @return 申报集合
    * @throws 异常
     */
    List<DeclareVO> listDeclareByParam(DeclareQuery declareQuery);
    /**
    * @Title: saveDeclare
    * @Description: TODO( 保存申报 )
    * @author GuoXiaoPeng
    * @param declareDO 申报
    * @param declarePriceDOs 申报价格
    * @return 成功返回true 失败返回false
    * @throws 异常
     */
    Boolean saveDeclare(DeclareDO declareDO, List<DeclarePriceDO> declarePriceDOs);
    /**
    * @Title: listDeclare
    * @Description: TODO( 分页查询申报列表 )
    * @author GuoXiaoPeng
    * @param declareQuery 申报条件查询
    * @return 申报列表
    * @throws 异常
     */
    PageBean<DeclareVO> listDeclare(DeclareQuery declareQuery);
    /**
    * @Title: getDeclareByDeclareCode
    * @Description: TODO( 根据申报编码查询申报详情 )
    * @author GuoXiaoPeng
    * @param declareCode 申报编码
    * @return 申报详情
    * @throws 异常
     */
    DeclareVO getDeclareByDeclareCode(String declareCode);
    /**
    * @Title: updateDeclareStateByCode
    * @Description: TODO( 修改申报审核状态 )
    * @author GuoXiaoPeng
    * @param declareDO 申报信息
    * @return 成功返回true 失败返回false 
    * @throws 异常
     */
    boolean updateDeclareStateByCode(DeclareDO declareDO);
    /**
     * @Title: countUnauditedDeclare
     * @Description: TODO(根据房源编码查询未审核状态的申报数量 )
     * @author GuoXiaoPeng
     * @param houseCode 房源编码
     * @return 未审核状态的申报数量 
     * @throws 异常
      */
    Integer countUnauditedDeclare(String houseCode);

}
