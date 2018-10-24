package com.young.pact.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;


/**
 * 
* @ClassName: DateUtil
* @Description: TODO(  日期工具类 )
* @author GuoXiaoPeng
* @date 2018年10月9日 上午9:41:05
*
 */
public class DateUtil extends com.tools.common.util.date.DateUtil{  
    private static final SimpleDateFormat DATE_MONTH = new SimpleDateFormat("yyyy-MM");
      
    public static void main(String[] args) {  
        Integer aa = daysBetweenByStr("2018-08-10");
        System.out.println(aa);
    	
    }  
      
    /***
     * 
     * @描述 : 获得当前日期后几天
     * @创建者：yuanwei
     * @创建时间： 2014-8-11下午2:29:53
     *
     * @param date
     * @param num
     * @return
     */
    public static Date getDayByDay(Date date,int num) {
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(date);  
        int day = calendar.get(Calendar.DATE);  
        calendar.set(Calendar.DATE, day + num);  
        return calendar.getTime();  
    }  
    /***
     * 
     * @描述 : 获得当前日期前几天
     * @创建者：liuyuchi
     * @创建时间： 2016年12月12日15:41:49
     *
     * @param date
     * @param num
     * @return
     */
    public static Date getBeforeYesterday(int num) {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    	Date date;
		try {
			date = sdf.parse(sdf.format(new Date()));
			 Calendar calendar = Calendar.getInstance();  
		        calendar.setTime(date);  
		        int day = calendar.get(Calendar.DATE);  
		        calendar.set(Calendar.DATE, day - num);  
		        return calendar.getTime();  
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}  
       
    }  
    /***
     * 
     * @描述 : 获得当前日期
     * @创建者：yuanwei
     * @创建时间： 2014-8-11下午2:29:53
     *
     * @param date
     * @param num
     * @return
     */
    public static Date getHourByDay(int num) {
    	 Calendar c = Calendar.getInstance();  
         c.setTime(new Date());   //设置当前日期  
         c.add(Calendar.HOUR, num); //日期分钟加1,Calendar.DATE(天),Calendar.HOUR(小时)  
         Date date = c.getTime(); //结果  
        return date;
    }  
  
    /***
     * 
     * @描述 : 获得当前日期后几月
     * @创建者：yuanwei
     * @创建时间： 2014-8-11下午2:30:21
     *
     * @param date
     * @param num
     * @return
     */
    public static Date getDayByMonth(Date date,int num) {
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(date);  
        int month = calendar.get(Calendar.MONTH);  
        calendar.set(Calendar.MONTH, month + num);  
        return calendar.getTime();  
    }
    
    /***
     * 
     * @描述 : 两个日期相减获得差(日期,月)
     * @创建者：yuanwei
     * @创建时间： 2014-8-12上午10:46:19
     * @param date1
     * @param date2
     * @return
     */
    public static String getDayForSub(Date date1,Date date2) {
    	int quot = (int) (getQuot(date1,date2)/30);
    	String returnString = "";
    	switch (quot) {
			case 0:returnString = "10";break;
			case 1:returnString = "1";break;
			case 3:returnString = "3";break;
			case 6:returnString = "6";break;
		}
    	return returnString;
    }
    
    /***
     * 
     * @描述 : 两个日期相差天数
     * @创建者：yuanwei
     * @创建时间： 2014-8-29上午11:04:44
     *
     * @param date1
     * @param date2
     * @return
     */
    public static long getQuot(Date date1,Date date2){
    	long quot = 0;
    	quot = date1.getTime() - date2.getTime();
    	quot = quot / 1000 / 60 / 60 / 24;
    	return quot;
	}
    
    public static Date parseDate(String date,String format,Locale local){
    	SimpleDateFormat DATE_TIME = new SimpleDateFormat(
    			format, local);
    	try {
		return 	DATE_TIME.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return null;
    	
    }
    public static Date parseDate(String date){
    	SimpleDateFormat DATE_TIME = new SimpleDateFormat(
    			"yyyy-MM-dd HH:mm:ss");
    	try {
		return 	DATE_TIME.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return null;
    	
    }
    
    public static Date parseDateDay(String date){
    	SimpleDateFormat DATE_TIME = new SimpleDateFormat(
    			"yyyy-MM-dd");
    	try {
		return 	DATE_TIME.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return null;
    	
    }
    public static String parseStrDay(String date){
    	if (StringUtils.isBlank(date)) {
			return null;
		}
    	SimpleDateFormat DATE_TIME = new SimpleDateFormat(
    			"yyyy-MM-dd");
    	try {
    		return 	DATE_TIME.format(DATE_TIME.parse(date));
    	} catch (ParseException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	return null;
    	
    }
    /**
     * @描述 : 返回当前日期[yyyy-MM-dd]Date类型
     * @创建者 : HeZeMin
     * @创建时间 : 2016-6-28 上午10:52:28
     */
    public static Date todate(){
        try {
        	
        	SimpleDateFormat matter1=new SimpleDateFormat("yyyy-MM-dd");
			return matter1.parse(matter1.format(new Date()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
        return null;
    }
    public static String hourDiff(Date endTime) {
			//按照传入的格式生成一个simpledateformate对象
			long nh = 1000*60*60;//一小时的毫秒数
			long nm = 1000*60;//一分钟的毫秒数
			//获得两个时间的毫秒时间差异
			long diff = endTime.getTime() - new Date().getTime();
			long hour = diff/nh;//计算差多少小时
			if(hour>0){
				return "剩余："+hour+"小时";
			}else if(hour==0){
				long min = diff/nm;//计算差多少分钟
				if(min>0){
					return "剩余："+min+"分钟";
				}else{
					return "已过期";
				}
			}else {
				return "已过期";
			}
    }
    
	public static String formatDateMonth(Date date)
    {
        if(date == null)
            return null;
        else
            return DATE_MONTH.format(date);
    }
    
    public static String dayByDiff(Date endTime) {
		//按照传入的格式生成一个simpledateformate对象
    	long nd = 1000*24*60*60;//一天的毫秒数
		long nh = 1000*60*60;//一小时的毫秒数
		long nm = 1000*60;//一分钟的毫秒数
		//获得两个时间的毫秒时间差异
		long diff = endTime.getTime() - new Date().getTime();
		long day = diff/nd;//计算差多少小时
		if(day>0){
			return "剩余："+day+"天";
		}else if(day==0){
			long hour = diff/nh;//计算差多少小时
			if(hour>0){
				return "剩余："+hour+"小时";
			}else if(hour==0){
				long min = diff/nm;//计算差多少分钟
				if(min>0){
					return "剩余："+min+"分钟";
				}else{
					return "已过期";
				}
			}else {
				return "已过期";
			}
		}else{
			return "已过期";
		}
	}
    
    /**
     * @描述 : 返回当前日期[yyyy-MM-dd HH:mm:ss]String类型
     * @创建者 : 艾勇胜
     * @创建时间 : 2016-10-7 下午13:02:28
     */
    public static String dateToString(Date date){
    	try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
    }
	/**  
     * 计算一个日子与当前日期差多少天
     * @param
     * @return 相差天数 
     * @throws ParseException  
     */    
    public static Integer daysBetween(Date smdate)
    {    
    	if (null==smdate) {
			return null;
		}
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        try {
			smdate=sdf.parse(sdf.format(smdate));  
			Date bdate=sdf.parse(sdf.format(new Date()));
			Calendar cal = Calendar.getInstance();    
			cal.setTime(smdate);    
			long time1 = cal.getTimeInMillis();                 
			cal.setTime(bdate);    
			long time2 = cal.getTimeInMillis();         
			long between_days=(time2-time1)/(1000*3600*24);  
			return Integer.parseInt(String.valueOf(between_days));           
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}  
            
    }
    /**  
     * 计算一个日子与当前日期差多少天(string)
     * @param
     * @return 相差天数 
     * @throws ParseException  
     */  
    public static Integer daysBetweenByStr(String mindate)
    {    
    	if (StringUtils.isBlank(mindate)) {
    		return null;
    	}
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
    	try {
    		
    		Date smdate=sdf.parse(mindate);  
    		Date bdate=sdf.parse(sdf.format(new Date()));
    		Calendar cal = Calendar.getInstance();    
    		cal.setTime(smdate);    
    		long time1 = cal.getTimeInMillis();                 
    		cal.setTime(bdate);    
    		long time2 = cal.getTimeInMillis();         
    		long between_days=(time2-time1)/(1000*3600*24);  
    		return Integer.parseInt(String.valueOf(between_days));           
    	} catch (ParseException e) {
    		e.printStackTrace();
    		return null;
    	}  
    	
    }
    
    
    
    /**  
     * 计算两个日期之间相差的天数  
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @return 相差天数 
     * @throws ParseException  
     */    
    public static int daysBetweenTwo(Date smdate,Date bdate) throws ParseException    
    {    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        smdate=sdf.parse(sdf.format(smdate));  
        bdate=sdf.parse(sdf.format(bdate));  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));           
    }    
    
    
    /**  
     * 比较两个日期大小
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @return boolean
     * @throws ParseException  
     */    
    public static boolean betweenTwo(Date smdate,Date bdate) throws ParseException    
    {    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        smdate=sdf.parse(sdf.format(smdate));  
        bdate=sdf.parse(sdf.format(bdate));  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        if (time1<time2) {
			return true;
		}else{
			return false;
		}      
    }       
}  