package com.young.pact.api.service.rest.commonmeterread;

import java.util.List;

import com.young.common.domain.ApiResult;
import com.young.pact.api.domain.param.rest.commonmeterread.CommonMeterReadParam;
import com.young.pact.api.domain.param.rest.commonmeterread.MeterGoodsParam;
import com.young.pact.api.domain.result.rest.commonmeterread.CommonMeterReadResult;
import com.young.pact.api.domain.result.rest.commonmeterread.MeterGoodsResult;

/**
 * @描述 : 抄表
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月2日 下午5:25:28
 */
public interface MeterGoodsService {

    /**
    * @Title: saveMeterGoodsRedis
    * @Description: TODO( 保存抄表信息+物品信息)
    * @author GuoXiaoPeng
    * @param param 抄表信息+物品信息
    * @return 缓存的key
    */
    ApiResult<String> saveMeterGoodsRedis(MeterGoodsParam param);
    /**
    * @Title: getMeterGoodsRedis
    * @Description: TODO( 根据缓存key获取抄表信息+物品信息 )
    * @author GuoXiaoPeng
    * @param key 缓存的key
    * @return 返回结果
    */
    ApiResult<MeterGoodsResult> getMeterGoodsRedis(String key);
    
    /**
     * 
    * @Title: insertMeterRead
    * @Description: 新增抄表
    * @author GuoXiaoPeng
    * @param param 抄表信息
    * @return 返回结果
    * @throws 异常
     */
    ApiResult<Boolean> insertMeterRead(CommonMeterReadParam param);
    
    /**
     * 
    * @Title: getGoods
    * @Description: 抄表详情
    * @author GuoXiaoPeng
    * @param id
    * @return 抄表详情信息
    * @throws 异常
     */
    ApiResult<CommonMeterReadResult> getMeterRead(Long id);
    
    /**
     * 
    * @Title: listMeterRead
    * @Description: 抄表列表
    * @author GuoXiaoPeng
    * @param param 查询条件
    * @return 返回结果
    * @throws 异常
     */
    ApiResult <List<CommonMeterReadResult>> listMeterRead(CommonMeterReadParam param);
    
    /**
     * 
    * @Title: updateMeterRead
    * @Description: 修改抄表信息
    * @author GuoXiaoPeng
    * @param param 抄表信息
    * @return 返回结果
    * @throws 异常
     */
    ApiResult<Boolean> updateMeterRead(CommonMeterReadParam param);
    
    /**
     * 
    * @Title: removeMeterRead
    * @Description: 删除抄表信息
    * @author GuoXiaoPeng
    * @param commonMeterReadParam 记录标识
    * @return 返回结果
    * @throws 异常
     */
    ApiResult<Boolean> removeMeterRead(CommonMeterReadParam commonMeterReadParam);
}
