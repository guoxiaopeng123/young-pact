package com.young.pact.manager.propertytransfer;

import java.util.List;
import java.util.Map;

import com.young.pact.domain.commongoods.GoodsVO;
import com.young.pact.domain.commonmeterread.CommonMeterReadVO;

/**
 * 
* @ClassName: PropertyTransferManager
* @Description: TODO( 物业交接)
* @author HeZeMin
* @date 2018年8月2日 下午3:32:45
*
 */
public interface PropertyTransferManager {
    /**
     * 
    * @Title: savePropertyTransfer
    * @Description: TODO( 托进合同第四步 把物业交接保存到redis中)
    * @author HeZeMin
    * @param map    抄表，物品
    * @param redisKey   redis key
     */
    void savePropertyTransferRedis(Map<String, Object> map, String redisKey);
    /**
     * 
    * @Title: getPropertyTransfer
    * @Description: TODO( 根据redis key查询缓存中托进合同第四步物业交接)
    * @author HeZeMin
    * @param redisKey   redis key
    * @return   返回 抄表、物品
     */
    Map<String, Object> getPropertyTransferRedis(String redisKey);
    /**
    * @Title: listCommonMeterReadByPactCode
    * @Description: TODO( 根据合同编码查询抄表信息集合)
    * @author GuoXiaoPeng
    * @param pactCode 合同编码
    * @return 抄表信息集合
    * @throws 异常
     */
    List<CommonMeterReadVO>listCommonMeterReadByPactCode(String pactCode);
    /**
    * @Title: listCommonGoodsByPactCode
    * @Description: TODO(根据合同编码查询物品信息集合 )
    * @author GuoXiaoPeng
    * @param pactCode 合同编码
    * @return 物品信息集合 
    * @throws 异常
     */
    List<GoodsVO>listCommonGoodsByPactCode(String pactCode);
}
