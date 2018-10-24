package com.young.pact.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * 
* @ClassName: RegexUtil
* @Description: TODO( 正则表达式工具类 )
* @author GuoXiaoPeng
* @date 2018年10月9日 上午9:42:26
*
 */
public class RegexUtil {
    /**
     * 
     * @描述 : 字符串是否由数字，字母或汉字组成
     * @创建者 : guoxiaopeng
     * @创建时间 : 2017-5-12 上午11:11:23
     * @param content
     * @return  若匹配，返回true，否则返回false
     */
    public static boolean is_regName(String content){
        if(StringUtils.isBlank(content)){
            return false;
        }
        String regex="^[a-zA-Z0-9\u4E00-\u9FA5]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher match=pattern.matcher(content);
        boolean b=match.matches();
        return b;
    }
    /**
     * @描述 : 验证码格式是否是全数字
     * @创建者 : HeZeMin
     * @创建时间 : 2017-5-14 下午3:46:20
     */
    public static boolean is_authCode(String content){
        if(StringUtils.isBlank(content)){
            return false;
        }
        String regex="^[0-9]{6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher match = pattern.matcher(content);
        boolean b = match.matches();
        return b;
    }
    /**
     * @描述 : 手机号格式是否正确
     * @创建者 : HeZeMin
     * @创建时间 : 2017-5-14 下午4:08:04
     */
    public static boolean is_tel(String content){
        if(StringUtils.isBlank(content)){
            return false;
        }
        String regex="^0?(13|15|17|18|14|16|19)[0-9]{9}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher match = pattern.matcher(content);
        boolean b = match.matches();
        return b;
    }
    /**
     * 
     * @描述 : 字符串是否由字母组成
     * @创建者 : guoxiaopeng
     * @创建时间 : 2017-5-12 上午11:11:23
     * @param content
     * @return  若匹配，返回true，否则返回false
     */
    public static boolean is_alphabet(String content){
        if(StringUtils.isBlank(content)){
            return false;
        }
        String regex = "^[a-zA-Z]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher match=pattern.matcher(content);
        boolean b=match.matches();
        return b;
    }
    public static void main(String[] args) {
    }
}
