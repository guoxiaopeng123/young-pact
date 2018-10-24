package com.young.pact.manager.commonmeterread;

import java.util.List;

import com.young.pact.domain.commonmeterread.CommonMeterReadDO;
import com.young.pact.domain.commonmeterread.CommonMeterReadQuery;
import com.young.pact.domain.commonmeterread.CommonMeterReadVO;
import com.young.pact.domain.commonmeterread.MeterReadGoodsDO;
import com.young.pact.domain.commonmeterread.MeterReadGoodsVO;

/**
 * @描述 : 合同物业交接抄表信息
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月2日 下午5:58:17
 */
public interface CommonMeterReadManager {

    /**
    * @Title: saveMeterGoodsRedis
    * @Description: TODO(保存抄表信息+物品信息 )
    * @author GuoXiaoPeng
    * @param meterReadGoodsDO 抄表信息+物品信息
    */
    void saveMeterGoodsRedis(String key,MeterReadGoodsDO meterReadGoodsDO);
    /**
    * @Title: getMeterGoodsRedis
    * @Description: TODO( 从缓存中获取抄表信息+物品信息)
    * @author GuoXiaoPeng
    * @param key 缓存的key
    * @return 抄表信息+物品信息
    */
    MeterReadGoodsVO getMeterGoodsRedis(String key);
    /**
    * @Title: insertMeterRead
    * @Description: TODO( 添加抄表  )
    * @author GuoXiaoPeng
    * @param commonMeterReadDO  抄表
    * @return 成功返回true 失败返回false
    * @throws 异常
     */
    boolean insertMeterRead(CommonMeterReadDO commonMeterReadDO);
    /**
    * @Title: getMeterRead
    * @Description: TODO( 抄表详情 )
    * @author GuoXiaoPeng
    * @param id 记录标识
    * @return 抄表详情
    * @throws 异常
     */
    CommonMeterReadVO getMeterRead(Long id);
    /**
    * @Title: listMeterReadByParam
    * @Description: TODO( 条件查询抄表 )
    * @author GuoXiaoPeng
    * @param query 查询条件
    * @return 抄表集合
    * @throws 异常
     */
    List<CommonMeterReadVO> listMeterReadByParam(CommonMeterReadQuery query);
    /**
    * @Title: updateMeterRead
    * @Description: TODO( 修改抄表 )
    * @author GuoXiaoPeng
    * @param commonMeterReadDO 抄表信息
    * @return 成功返回true 失败返回false
    * @throws 异常
     */
    boolean updateMeterRead(CommonMeterReadDO commonMeterReadDO);
    /**
    * @Title: removeMeterRead
    * @Description: TODO( 删除抄表)
    * @author GuoXiaoPeng
    * @param commonMeterReadDO 记录标识
    * @return 成功返回true 失败返回false
    * @throws 异常
     */
    boolean removeMeterRead(CommonMeterReadDO commonMeterReadDO);
}
