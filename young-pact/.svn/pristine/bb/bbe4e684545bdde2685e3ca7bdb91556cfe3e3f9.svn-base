package com.young.pact.service.schedule.rentbase.impl;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.taobao.pamirs.schedule.IScheduleTaskDealSingle;
import com.taobao.pamirs.schedule.TaskItemDefine;
import com.young.pact.common.constant.StringConsts;
import com.young.pact.domain.rentbase.RentBaseVO;
import com.young.pact.domain.statistics.AgendaDO;
import com.young.pact.manager.rentbase.RentBaseManager;
import com.young.pact.manager.statistics.StatisticsManager;

/**
 * @描述 : 查询托出合同到期时间距离今日还有60天的的托出合同定时任务
 * @创建者 : guoxiaopeng
 * @创建时间 : 2018年9月15日 下午3:50:02
 */
@Service("rentExpirTaskService")
public class RentExpirTaskServiceImpl implements IScheduleTaskDealSingle<RentBaseVO>{
    private static final Log LOG = LogFactory.getLog(RentExpirTaskServiceImpl.class);
    private RentBaseManager rentBaseManager;
    private StatisticsManager statisticsManager;
    private int count;
    @Override
    public Comparator<RentBaseVO> getComparator() {
        return null;
    }

    @Override
    public List<RentBaseVO> selectTasks(String arg0, String arg1, int arg2, List<TaskItemDefine> arg3, int arg4) throws Exception {
        try {
            List<RentBaseVO> list = rentBaseManager.listRentExpir();
            if(list != null && list.size()>0){
                if(count == list.size()){
                    count = 0;
                    return null;
                }else{
                    count = list.size();
                    return list;
                }
            }else{
                return null;
            }
        } catch (Exception e) {
            LOG.error(e);
        }
        return null;
    }

    @Override
    public boolean execute(RentBaseVO rentBaseVO, String arg1) throws Exception {
        LOG.info("-----------查询托出到期时间距离今日还有60天的的托出合同定时任务执行结果   开始--------");
        AgendaDO agendaDO = new AgendaDO();
        agendaDO.setServiceCode(rentBaseVO.getRentPactCode());
        agendaDO.setType(StringConsts.STATISTIC_PENDING_RENT_EXPIR);
        agendaDO.setIsDo(0);
        agendaDO.setPin(rentBaseVO.getAfterRentingPin());
        agendaDO.setName(rentBaseVO.getAfterRentingName());
        agendaDO.setDeptCode(rentBaseVO.getAfterRentingDeptCode());
        agendaDO.setDeptName(rentBaseVO.getAfterRentingDeptName());
        agendaDO.setDate(new Date());
        statisticsManager.saveBacklogStatistics(agendaDO);
        LOG.info("-----------查询托出到期时间距离今日还有60天的的托出合同定时任务执行结果   结束--------");
        return true;
    }

    public RentBaseManager getRentBaseManager() {
        return rentBaseManager;
    }

    public void setRentBaseManager(RentBaseManager rentBaseManager) {
        this.rentBaseManager = rentBaseManager;
    }

    public StatisticsManager getStatisticsManager() {
        return statisticsManager;
    }

    public void setStatisticsManager(StatisticsManager statisticsManager) {
        this.statisticsManager = statisticsManager;
    }

}
