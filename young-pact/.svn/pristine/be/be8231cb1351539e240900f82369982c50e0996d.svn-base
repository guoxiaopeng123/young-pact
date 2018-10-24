package com.young.pact.service.schedule.percapitaefficiency.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.taobao.pamirs.schedule.IScheduleTaskDealSingle;
import com.taobao.pamirs.schedule.TaskItemDefine;
import com.young.pact.common.constant.StringConsts;
import com.young.pact.domain.purchasebase.PurchaseBaseQuery;
import com.young.pact.domain.purchasebase.PurchaseBaseVO;
import com.young.pact.domain.rentbase.RentBaseQuery;
import com.young.pact.domain.rentbase.RentBaseVO;
import com.young.pact.domain.statistics.SituationDO;
import com.young.pact.manager.purchasebase.PurchaseBaseManager;
import com.young.pact.manager.rentbase.RentBaseManager;
import com.young.pact.manager.statistics.StatisticsManager;
import com.young.pact.rpc.personal.PersonalRpc;
import com.young.sso.api.domain.result.rpc.personal.PersonalResult;

/**
 * @描述 : 本月人均效能定时任务
 * @创建者 : guoxiaopeng
 * @创建时间 : 2018年9月17日 下午2:08:13
 */
@Service("perCapitaEfficiencyTaskService")
public class PerCapitaEfficiencyTaskServiceImpl implements IScheduleTaskDealSingle<Map<Integer, BigDecimal>> {
    private static final Log LOG = LogFactory.getLog(PerCapitaEfficiencyTaskServiceImpl.class);
    private PersonalRpc personalRpc;
    private int count;
    private RentBaseManager rentBaseManager;
    private PurchaseBaseManager purchaseBaseManager;
    private StatisticsManager statisticsManager;

    @Override
    public Comparator<Map<Integer, BigDecimal>> getComparator() {
        return null;
    }

    @Override
    public List<Map<Integer,BigDecimal>> selectTasks(String arg0, String arg1, int arg2, List<TaskItemDefine> arg3, int arg4) throws Exception {
        try {
            List<Map<Integer,BigDecimal>> list = new ArrayList<>();
            Map<Integer,BigDecimal> map = new HashMap<>();
            BigDecimal rentBefore = getRentBefore();
            BigDecimal rentAfter = getRentAfter();
            BigDecimal comprehensiveEfficiency = getComprehensiveEfficiency();
            map.put(1, rentBefore);
            map.put(2, rentAfter);
            map.put(3, comprehensiveEfficiency);
            list.add(map);
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
    public boolean execute(Map<Integer, BigDecimal> map, String arg1) throws Exception {
        BigDecimal rentBefore;
        BigDecimal rentAfter;
        BigDecimal comprehensiveEfficiency;
        //托出租前
        rentBefore = map.get(1);
        rentBefore.multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_DOWN);
        SituationDO rentBeforeSituationDO = new SituationDO();
        rentBeforeSituationDO.setModule(StringConsts.PER_CAPITA_EFFICIENCY_THIS_MONTH);
        rentBeforeSituationDO.setType(StringConsts.RENT_BEFORE);
        rentBeforeSituationDO.setValue(rentBefore.toString());
        rentBeforeSituationDO.setDate(new Date());
        statisticsManager.saveSituationDO(rentBeforeSituationDO);
        //托出租后
        rentAfter = map.get(2);
        rentAfter.multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_DOWN);
        SituationDO rentAfterSituationDO = new SituationDO();
        rentAfterSituationDO.setModule(StringConsts.PER_CAPITA_EFFICIENCY_THIS_MONTH);
        rentAfterSituationDO.setType(StringConsts.RENT_AFTER);
        rentAfterSituationDO.setValue(rentAfter.toString());
        rentAfterSituationDO.setDate(new Date());
        statisticsManager.saveSituationDO(rentAfterSituationDO);
        //综合效能
        comprehensiveEfficiency = map.get(3);
        comprehensiveEfficiency.multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_DOWN);
        SituationDO comprehensiveSituationDO = new SituationDO();
        comprehensiveSituationDO.setModule(StringConsts.PER_CAPITA_EFFICIENCY_THIS_MONTH);
        comprehensiveSituationDO.setType(StringConsts.COMPREHENSIVE_EFFICIENCY);
        comprehensiveSituationDO.setValue(comprehensiveEfficiency.toString());
        comprehensiveSituationDO.setDate(new Date());
        statisticsManager.saveSituationDO(comprehensiveSituationDO);
        return true;
    }
    /**
     * 
    * @Title: getComprehensiveEfficiency
    * @Description: TODO( 本月人均效能综合效能----（托进+托出）成交属性为新签的/售前管家 )
    * @author GuoXiaoPeng
    * @return 本月人均效能综合效能
    * @throws 异常
     */
    private BigDecimal getComprehensiveEfficiency() {
        BigDecimal bigDecimal = new BigDecimal(0.00);
        PurchaseBaseQuery purchaseBaseQuery = new PurchaseBaseQuery();
        int purCount = 0;
        purchaseBaseQuery.setDealAttr(1);
        purchaseBaseQuery.setState(3);
        purchaseBaseQuery.setDealStartDate(monthFirstDate());
        purchaseBaseQuery.setDealEndDate(monthLastDate());
        List<PurchaseBaseVO> purList = purchaseBaseManager.listPurchaseBaseByParam(purchaseBaseQuery);
        if(null != purList){
            purCount = purList.size();
        }
        //托出合同成交属性为新签的合同个数
        int  rentBaseCount = 0;
        RentBaseQuery rentBaseQuery = new RentBaseQuery();
        rentBaseQuery.setDealState(1);
        rentBaseQuery.setState(3);
        rentBaseQuery.setSigningStartTime(monthFirstDate());
        rentBaseQuery.setSigningEndTime(monthLastDate());
        List<RentBaseVO> rentBaseList = rentBaseManager.listRentBaseByParam(rentBaseQuery);
        if(null != rentBaseList){
            rentBaseCount = rentBaseList.size();
        }
        List<PersonalResult> operateList = personalRpc.listByPosition(StringConsts.BELONGER_OPERATE);
        //运管管家个数
        int operateCount = 0;
        if(operateList != null){
            operateCount = operateList.size();
        }
        if(operateCount == 0){
            return bigDecimal;
        }
        bigDecimal = new BigDecimal(purCount + rentBaseCount).divide(new BigDecimal(operateCount)).setScale(2, BigDecimal.ROUND_DOWN);
        return bigDecimal;
    }

    /**
     * 
    * @Title: getRentBefore
    * @Description: TODO( 本月人均效能托出租后----托出合同成交属性不是新签的/租后管家 )
    * @author GuoXiaoPeng
    * @return 本月人均效能托出租后
    * @throws 异常
     */
    private BigDecimal getRentAfter() {
        BigDecimal bigDecimal = new BigDecimal(0.00);
        //租后管家个数
        int afterRentCount = 0;
        List<PersonalResult> afterRentList = personalRpc.listByPosition(StringConsts.BELONGER_SERVICE);
        if(afterRentList != null){
            afterRentCount = afterRentList.size();
        }
        //托出合同成交属性为新签的合同个数
        int dealCount;
        RentBaseQuery rentBaseQuery = new RentBaseQuery();
        rentBaseQuery.setState(3);
        List<Integer> dealStateList = new ArrayList<>();
        dealStateList.add(2);
        dealStateList.add(3);
        dealStateList.add(4);
        rentBaseQuery.setDealStateList(dealStateList );
        rentBaseQuery.setSigningStartTime(monthFirstDate());
        rentBaseQuery.setSigningEndTime(monthLastDate());
        List<RentBaseVO> dealRentBaseList = rentBaseManager.listRentBaseByParam(rentBaseQuery);
        if(null != dealRentBaseList){
            dealCount = dealRentBaseList.size();
        }else{
            dealCount = 0;
        }
        if(afterRentCount == 0){
            return bigDecimal;
        }
        bigDecimal = new BigDecimal(dealCount).divide(new BigDecimal(afterRentCount)).setScale(2, BigDecimal.ROUND_DOWN);
        return bigDecimal;
    }

    /**
     * 
    * @Title: getRentBefore
    * @Description: TODO( 本月人均效能托出租前----托出合同成交属性为新签的/售前管家 )
    * @author GuoXiaoPeng
    * @return 本月人均效能托出租前
    * @throws 异常
     */
    private BigDecimal getRentBefore() {
        BigDecimal bigDecimal = new BigDecimal(0.00);
        List<PersonalResult> operateList = personalRpc.listByPosition(StringConsts.BELONGER_OPERATE);
        //运管管家个数
        int operateCount = 0;
        if(operateList != null){
            operateCount = operateList.size();
        }

        //托出合同成交属性为新签的合同个数
        int dealCount = 0;
        RentBaseQuery rentBaseQuery = new RentBaseQuery();
        rentBaseQuery.setDealState(1);
        rentBaseQuery.setState(3);
        rentBaseQuery.setSigningStartTime(monthFirstDate());
        rentBaseQuery.setSigningEndTime(monthLastDate());
        List<RentBaseVO> dealRentBaseList = rentBaseManager.listRentBaseByParam(rentBaseQuery);
        if(null != dealRentBaseList){
            dealCount = dealRentBaseList.size();
        }
        if(operateCount == 0){
            return bigDecimal;
        }
        bigDecimal = new BigDecimal(dealCount).divide(new BigDecimal(operateCount)).setScale(2, BigDecimal.ROUND_DOWN);
        return bigDecimal;
    }

    /**
     * 
    * @Title: monthFirstDate
    * @Description: TODO( 获取当月第一天  )
    * @author GuoXiaoPeng
    * @return 第一天  
    * @throws 异常
     */
    public  String monthFirstDate() {
        // 规定返回日期格式
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Date theDate = calendar.getTime();
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(theDate);
        // 设置为第一天
        gcLast.set(Calendar.DAY_OF_MONTH, 1);
        String day = sf.format(gcLast.getTime());
        // 打印本月第一天
        return day + " 00:00:00";
    }
    /**
     * 
    * @Title: monthLastDate
    * @Description: TODO( 获取当月最后一天 )
    * @author GuoXiaoPeng
    * @return  当月最后一天
    * @throws 异常
     */
    public  String monthLastDate() {
        // 获取Calendar
        Calendar calendar = Calendar.getInstance();
        // 设置日期为本月最大日期
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        // 设置日期格式
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String day = sf.format(calendar.getTime());
        return day + " 23:59:59";
    }

    public PersonalRpc getPersonalRpc() {
        return personalRpc;
    }

    public void setPersonalRpc(PersonalRpc personalRpc) {
        this.personalRpc = personalRpc;
    }

    public RentBaseManager getRentBaseManager() {
        return rentBaseManager;
    }

    public void setRentBaseManager(RentBaseManager rentBaseManager) {
        this.rentBaseManager = rentBaseManager;
    }

    public PurchaseBaseManager getPurchaseBaseManager() {
        return purchaseBaseManager;
    }

    public void setPurchaseBaseManager(PurchaseBaseManager purchaseBaseManager) {
        this.purchaseBaseManager = purchaseBaseManager;
    }

    public StatisticsManager getStatisticsManager() {
        return statisticsManager;
    }

    public void setStatisticsManager(StatisticsManager statisticsManager) {
        this.statisticsManager = statisticsManager;
    }

}
