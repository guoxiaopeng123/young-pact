package com.young.pact.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
* @ClassName: DateTools
* @Description: 日期工具类
* @author LiuYuChi
* @date 2018年8月9日 下午2:24:10
*
 */
public class DateTools {
	
	/**
	 * 
	* @Title: getLastDay
	* @Description: 获取当前日期的后几天
	* @author LiuYuChi
	* @param date 当前日期
	* @param num 后几天
	* @return
	* @throws
	 */
	public static Date getLastDay(Date date,int num){
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(date);  
        int day = calendar.get(Calendar.DATE);  
        calendar.set(Calendar.DATE, day + num);  
        Date nDate = calendar.getTime();
        return nDate;
	}
	
	public static Date stringToDate(String s){
		DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = fmt.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static Date stringToDate2(String s){
		DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = fmt.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
     * 得到当前天的零点，或者24点时间
     * 
     * @param date 当前时间
     * @param flag	 0 返回yyyy-MM-dd 00:00:00日期，当天零点 <br>
     *       	     1 返回yyyy-MM-dd 23:59:59日期，当天24点
     *       
     * @return	返回当前天的零点，或者24点时间
     */
    public static Date weeHours(Date date, int flag) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);
        //时分秒（毫秒数）
        long millisecond = hour*60*60*1000 + minute*60*1000 + second*1000;
        //凌晨00:00:00
        cal.setTimeInMillis(cal.getTimeInMillis()-millisecond);
         
        if (flag == 0) {
            return cal.getTime();
        } else if (flag == 1) {
            //凌晨23:59:59
            cal.setTimeInMillis(cal.getTimeInMillis()+23*60*60*1000 + 59*60*1000 + 59*1000);
        }
        return cal.getTime();
    }
    
    public static String parseDay(Date date){
    	if (null==date) {
			return null;
		}
    	SimpleDateFormat DATE_TIME = new SimpleDateFormat(
    			"yyyy-MM-dd HH:mm:ss");
    	return DATE_TIME.format(date);
    }
}
