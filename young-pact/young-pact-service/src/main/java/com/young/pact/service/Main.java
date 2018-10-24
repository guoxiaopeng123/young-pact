/**  
* @Title:  main.java
* @Package com.young.customer.service.impl
* @Description: TODO(用一句话描述该文件做什么)
* @author Weisong
* @date  2018年1月10日 下午12:32:29
* Update Logs:
* ****************************************************
* Name:
* Date:
* Description:
******************************************************
*/
package com.young.pact.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * @ClassName: main
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Weisong
 * @date 2018年1月10日 下午12:32:29
 *
 */
public class Main {
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * @author Weisong
 */
	private static final Log LOG = LogFactory.getLog(Main.class);

	@SuppressWarnings("resource")
    public static void main(String[] args) {
		 try {
	            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-config.xml");
	            context.start();
	        } catch (Exception e) {
	        	LOG.error("== DubboProvider context start error:",e);
	        }
	        synchronized (Main.class) {
	            while (true) {
	                try {
	                	Main.class.wait();
	                } catch (InterruptedException e) {
	                	LOG.error("== synchronized error:",e);
	                }
	            }
	        }
	    }
	}

