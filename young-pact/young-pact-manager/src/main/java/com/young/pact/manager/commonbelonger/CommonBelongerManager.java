package com.young.pact.manager.commonbelonger;

import java.util.List;

import com.young.pact.domain.commonbelonger.CommonBelongerDO;
import com.young.pact.domain.commonbelonger.CommonBelongerQuery;
import com.young.pact.domain.commonbelonger.CommonBelongerVO;
import com.young.product.api.domain.param.rpc.house.HouseParam;

/**
 * 
* @ClassName: CommonBelongerManager
* @Description: TODO( 合同责任人)
* @author HeZeMin
* @date 2018年8月5日 下午3:59:13
*
 */
public interface CommonBelongerManager {
    /**
     * 
    * @Title: listCommonBelonger
    * @Description: TODO( 根据合同编码查询合同责任人信息集合)
    * @author HeZeMin
    * @param pactCode   合同编码
    * @return   返回合同责任人信息集合
     */
    List<CommonBelongerVO> listCommonBelonger(String pactCode);
    /**
     * 
    * @Title: updateCommonBelonger
    * @Description: TODO( 修改合同责任人)
    * @author HeZeMin
    * @param commonBelongerDO   合同责任人信息
    * @return   返回修改结果
     */
    boolean updateCommonBelonger(CommonBelongerDO commonBelongerDO);
    /**
     * 
    * @Title: saveCommonBelonger
    * @Description: TODO( 保存合同责任人信息)
    * @author HeZeMin
    * @param commonBelongerDOs  合同责任人信息
    * @return   返回插入条数
     */
    boolean saveCommonBelonger(List<CommonBelongerDO> commonBelongerDOs);
    /**
     * 
    * @Title: updateCommonBelongers
    * @Description: TODO( 修改合同责任人)
    * @author HeZeMin
    * @param commonBelongerDO   合同责任人信息
    * @return   返回修改结果
     */
    boolean updateCommonBelongers(CommonBelongerDO commonBelongerDO);
    /**
    * @Title: getBelongersByParam
    * @Description: TODO( 根据合同编码负责人角色查询负责人 )
    * @author GuoXiaoPeng
    * @param commonBelongerQuery 合同编码负责人角色
    * @return 负责人
    * @throws 异常
     */
    CommonBelongerVO getBelongersByParam(CommonBelongerQuery commonBelongerQuery);
    /**
    * @Title: updateCommonBelonger
    * @Description: TODO( 根据合同编码批量添加，修改负责人 )
    * @author GuoXiaoPeng
    * @param addBelongerDOs 要添加的负责人
    * @param updateBelongerDOs 要修改的负责人
    * @return 成功，返回 true，失败false
    * @throws 异常
     */
    boolean updateCommonBelonger(List<CommonBelongerDO> addBelongerDOs, List<CommonBelongerDO> updateBelongerDOs);
    /**
     * 
    * @Title: updateSyncOperateBelonger
    * @Description: TODO( 根据合同编码修改运管管家并且同步合同运管管家信息到生产系统)
    * @author GuoXiaoPeng
    * @param commonBelongerDO 运管管家
    * @return
    * @throws
     */
    boolean updateSyncOperateBelonger(CommonBelongerDO commonBelongerDO);
    /**
     * 
    * @Title: transferPurchase
    * @Description: TODO( 资源转移托进合同，修改合同运营管家和租后管家，并且同步合同运营管家到生产系统 )
    * @author GuoXiaoPeng
    * @param addBelongerDOs 新添加的租后管家集合
    * @param updateBelongerDOs 修改的运营管家和租后管家集合
    * @param houseParam 托进合同编码集合和运营管家pin
    * @return 成功，返回 true，失败false
    * @throws  异常
     */
    boolean transferPurchase(List<CommonBelongerDO> addBelongerDOs, List<CommonBelongerDO> updateBelongerDOs, HouseParam houseParam);
}
