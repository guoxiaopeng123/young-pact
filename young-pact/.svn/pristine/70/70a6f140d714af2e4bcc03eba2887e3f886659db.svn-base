package com.young.pact.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * 
* @ClassName: StrTools
* @Description: TODO( 字符串工具类 )
* @author GuoXiaoPeng
* @date 2018年10月9日 上午9:42:52
*
 */
public class StrTools {
	/**
	 * @描述 : 隐藏手机号
	 * @创建者 : HeZeMin
	 * @创建时间 : 2016-3-8 下午7:50:36
	 * @param tel	手机号
	 * @return	返回处理过的手机号,123***789
	 */
	public static String hideTel(String tel){
		if(null != tel && tel.length() == 11){
			String str1 = tel.substring(0, 3);
			String str2 = tel.substring(7, 11);
			StringBuffer sb = new StringBuffer();
			sb.append(str1).append("****").append(str2);
			return sb.toString();
		}
		return tel;
	}
	/**
	 * @描述 : 隐藏身份证号码
	 * @创建者 : HeZeMin
	 * @创建时间 : 2016-3-8 下午7:53:28
	 * @param idNumber	身份证号码
	 * @return	返回处理过的身份证号码
	 */
	public static String hideIdNumber(String idNumber){
		if(null != idNumber && idNumber.length() == 18){
			String str1 = idNumber.substring(0, 3);
			String str2 = idNumber.substring(14, 18);
			StringBuffer sb = new StringBuffer();
			sb.append(str1).append("***********").append(str2);
			return sb.toString();                                                                                                            
		}
		return idNumber;
	}
	/**
	 * 
	 * @描述 : 前台页面中的某一个字符串是否包含另一个字符串，用,隔开
	 * @创建者：qiwanzeng
	 * @创建时间： 2016-10-6下午4:14:26
	 *
	 * @param parent
	 * @param child
	 * @return
	 */
	public static boolean isContainStr(String parent,String child){
		if (StringUtils.isBlank(parent)||StringUtils.isBlank(child)) {
			return false;
		}else {
			List<String> list = Arrays.asList(StringUtils.split(parent,","));
			if (list.contains(child)) {
				return true;
			}else {
				return false;
			}
		}
	}
	/**
	 * 处理内容
	 * @param content
	 * @param key
	 * @return
	 */
	public static String replaceContent(String content,String... key){
		for (int i = 0; i < key.length; i++) {
			content=content.replace("varValue"+i, key[i]);
		}
		return content;
	}
	
	public static List<String> stringToList(String ids) {
		String[] arrids = ids.split(",");
		List<String> idslist = new ArrayList<String>();
		for (int i = 0; i < arrids.length; i++) {
			idslist.add(arrids[i]);
		}
		return idslist;
	}
	/**
	 * 
	* @Title: subString
	* @Description: 截取字符串特殊字符之前的字符串
	* @author LiuYuChi
	* @param string 字符串(例如:aaa_bbb_ccc)
	* @param str 特殊字符(例如:_)
	* @return s 截取之后的(例如:aaa)
	* @throws
	 */
	public static String subString(String string,String str){
		if (string.contains(str)) {
			if (string.indexOf(str)>0) {
				String s = string.substring(0, string.indexOf(str));
				return s;
			}else {
				return null;
			}
		}else {
			return null;
		}
	}
}