package com.young.pact.manager.statistics.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.tools.common.rocketmq.client.RocketmqClient;
import com.tools.common.util.json.JsonUtil;
import com.young.pact.common.constant.ErrorPactConsts;
import com.young.pact.common.constant.NormalConstant;
import com.young.pact.common.dict.PactCommonEnum;
import com.young.pact.common.exception.PactManagerExcepion;
import com.young.pact.domain.statistics.AgendaDO;
import com.young.pact.domain.statistics.SituationDO;
import com.young.pact.domain.statistics.StatisticDO;
import com.young.pact.manager.statistics.StatisticsManager;

/**
 * @描述 :  统计记录
 * @创建者 : guoxiaopeng
 * @创建时间 : 2018年9月13日 下午8:11:18
 */
@Component("statisticsManager")
public class StatisticsManagerImpl implements StatisticsManager{
    private static final Log LOG = LogFactory.getLog(StatisticsManagerImpl.class);
    private RocketmqClient rocketmqClient;
    @Override
    public Boolean savePactRoomStatistics(StatisticDO statisticDO) {
        try {
            statisticDO.setDate(new Date());
            statisticDO.setYear(getYearByCalendar(statisticDO.getDate()));
            statisticDO.setMonth(getMonthByCalendar(statisticDO.getDate()));
            statisticDO.setDay(getDayByCalendar(statisticDO.getDate()));
            String json = JsonUtil.toJson(statisticDO);
            rocketmqClient.sendMessage(NormalConstant.TOPIC_PACTROOMSTATISTICS, "", "", json);
        } catch (Exception e) {
            LOG.error("保存合同新出量失败,操作对象：" + JsonUtil.toJson(statisticDO));
            LOG.error(ErrorPactConsts.SAVE_PACTROOM_STATISTICS_ERROR);
            throw new PactManagerExcepion(ErrorPactConsts.SAVE_PACTROOM_STATISTICS_ERROR);
        }
        return true;
    }

    @Override
    public Boolean saveBacklogStatistics(AgendaDO agendaDO) {
        try {
            String json = JsonUtil.toJson(agendaDO);
            rocketmqClient.sendMessage(NormalConstant.TOPIC_BACKLOGSTATISTICS, NormalConstant.TAG_DATA_AGENDA_SAVE, "", json);
        } catch (Exception e) {
            LOG.error("保存待办事务失败,操作对象：" + JsonUtil.toJson(agendaDO));
            LOG.error(ErrorPactConsts.SAVE_BACKLOG_STATISTICS_ERROR);
            throw new PactManagerExcepion(ErrorPactConsts.SAVE_BACKLOG_STATISTICS_ERROR);
        }
        return true;
    }
    
    @Override
    public Boolean updatBacklogStatisticsIsDo(AgendaDO agendaDO) {
        try {
            String json = JsonUtil.toJson(agendaDO);
            rocketmqClient.sendMessage(NormalConstant.TOPIC_BACKLOGSTATISTICS, NormalConstant.TAG_DATA_AGENDA_UPDATE, "", json);
        } catch (Exception e) {
            // TODO: handle exception
            LOG.error(PactCommonEnum.UPDATE_BACKLOGSTATISTICS_ERROR.getMessage(),e);
            LOG.error("修改待办事务失败,操作对象：" + JsonUtil.toJson(agendaDO));
            throw new PactManagerExcepion(PactCommonEnum.UPDATE_BACKLOGSTATISTICS_ERROR.getMessage());
        }
        return true;
    }
    
    @Override
    public Boolean removeBacklogStatistics(List<AgendaDO> deleteList) {
        try {
            String json = JsonUtil.toJson(deleteList);
            rocketmqClient.sendMessage(NormalConstant.TOPIC_BACKLOGSTATISTICS, "", NormalConstant.TAG_DATA_AGENDA_REMOVE, json);
        } catch (Exception e) {
            // TODO: handle exception
            LOG.error(PactCommonEnum.DELETE_BACKLOGSTATISTICS_ERROR.getMessage(),e);
            LOG.error("删除待办事务失败,操作集合：" + JsonUtil.toJson(deleteList));
            throw new PactManagerExcepion(PactCommonEnum.DELETE_BACKLOGSTATISTICS_ERROR.getMessage());
        }
        return true;
    }
    
    @Override
    public Boolean saveSituationDO(SituationDO situationDO) {
        try {
            String json = JsonUtil.toJson(situationDO);
            rocketmqClient.sendMessage(NormalConstant.TOPIC_DATA_SITUATION_SAVE, "", "", json);
        } catch (Exception e) {
            LOG.error(PactCommonEnum.SAVE_SITUATIONDO_ERROR.getMessage(),e);
            LOG.error("保存本月人均效能/本月营业收入/本月现金流量统计记录失败,操作记录：" + JsonUtil.toJson(situationDO));
            throw new PactManagerExcepion(PactCommonEnum.SAVE_SITUATIONDO_ERROR.getMessage());
        }
        return true;
    }
    /**
    * @Title: getYearByCalendar
    * @Description: TODO( 获取指定日期的年份 )
    * @author GuoXiaoPeng
    * @param date 指定日期
    * @return 年份 
    * @throws 异常
     */
    public String getYearByCalendar(Date date){
        try {
            if(date == null){
                throw new PactManagerExcepion(PactCommonEnum.PARAM_IS_NULL.getMessage());
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            Integer year = calendar.get(Calendar.YEAR);//获取年份
            return String.valueOf(year); 
        } catch (Exception e) {
            LOG.error(PactCommonEnum.GETYEAR_BYCALENDAR_ERROR.getMessage(),e);
            throw new PactManagerExcepion(PactCommonEnum.GETYEAR_BYCALENDAR_ERROR.getMessage());
        }
    }
    /***
    * @Title: getMonthByCalendar
    * @Description: TODO( 获取指定日期的月份 )
    * @author GuoXiaoPeng
    * @param date 指定日期
    * @return 月份 
    * @throws
     */
    public String getMonthByCalendar(Date date){
        try {
            if(date == null){
                throw new PactManagerExcepion(PactCommonEnum.PARAM_IS_NULL.getMessage());
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int month=calendar.get(Calendar.MONTH) + 1;//获取月份
            return String.valueOf(month);
        } catch (Exception e) {
            LOG.error(PactCommonEnum.GETMONTH_BYCALENDAR_ERROR.getMessage(),e);
            throw new PactManagerExcepion(PactCommonEnum.GETMONTH_BYCALENDAR_ERROR.getMessage());
        }
       
    }
    /**
     * 
    * @Title: getDayByCalendar
    * @Description: TODO( 获取指定日期的天数，就是这个月的几号 )
    * @author GuoXiaoPeng
    * @param date 指定日期
    * @return 天数
    * @throws
     */
    public String getDayByCalendar(Date date){
        try {
            if(date == null){
                throw new PactManagerExcepion(PactCommonEnum.PARAM_IS_NULL.getMessage());
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int day=calendar.get(Calendar.DATE);//获取日
            return String.valueOf(day);
        } catch (Exception e) {
            // TODO: handle exception
            LOG.error(PactCommonEnum.GETDAY_BYCALENDAR_ERROR.getMessage(),e);
            throw new PactManagerExcepion(PactCommonEnum.GETDAY_BYCALENDAR_ERROR.getMessage());
        }
    }
    
    
    public RocketmqClient getRocketmqClient() {
        return rocketmqClient;
    }

    public void setRocketmqClient(RocketmqClient rocketmqClient) {
        this.rocketmqClient = rocketmqClient;
    }
}
