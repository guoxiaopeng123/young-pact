package com.young.pact.manager.statistics;

import java.util.List;

import com.young.pact.domain.statistics.AgendaDO;
import com.young.pact.domain.statistics.SituationDO;
import com.young.pact.domain.statistics.StatisticDO;

/**
 * @描述 : 统计结果
 * @创建者 : guoxiaopeng
 * @创建时间 : 2018年9月13日 下午8:07:59
 */
public interface StatisticsManager {
    /**
     * 
    * @Title: savePactRoomStatistics
    * @Description: TODO( 审核通过托出合同，托进合同，托出续租协议，托出解约协议，保存信息到统计表 )
    * @author GuoXiaoPeng
    * @param statisticDO 合同统计实体
    * @return 成功返回true，失败返回false
    * @throws 异常
     */
    Boolean savePactRoomStatistics(StatisticDO statisticDO);
    /**
     * 
    * @Title: saveBacklogStatistics
    * @Description: TODO( 把待审核交易记录保存到统计表  )
    * @author GuoXiaoPeng
    * @param agendaDO 首页待办数据统计实体
    * @return 成功返回true，失败返回false
    * @throws 异常
     */
    Boolean saveBacklogStatistics(AgendaDO agendaDO);
    /**
    * @Title: updatBacklogStatisticsIsDo
    * @Description: TODO( 修改待审核交易记录是否办完状态 )
    * @author GuoXiaoPeng
    * @param agendaDO 是否办完状态
    * @return  成功返回true，失败返回false
    * @throws 异常
     */
    Boolean updatBacklogStatisticsIsDo(AgendaDO agendaDO);
    /**
     * 
    * @Title: removeBacklogStatistics
    * @Description: TODO( 根据资源编码集合删除待办 统计记录)
    * @author GuoXiaoPeng
    * @param deleteList 删除的待办事务待收款和待付款集合
    * @return 成功返回true，失败返回false
    * @throws 异常
     */
    Boolean removeBacklogStatistics(List<AgendaDO> deleteList);
    /**
     * 
    * @Title: saveSituationDO
    * @Description: TODO(保存本月人均效能，本月营业收入 ，本月现金流量)
    * @author GuoXiaoPeng
    * @param situationDO 本月人均效能，本月营业收入 ，本月现金流量信息
    * @return 成功返回true，失败返回false
    * @throws 异常
     */
    Boolean saveSituationDO(SituationDO situationDO);
}
