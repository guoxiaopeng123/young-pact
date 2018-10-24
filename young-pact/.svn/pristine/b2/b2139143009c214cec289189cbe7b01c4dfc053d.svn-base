package com.young.pact.service;

import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.tools.common.util.json.JsonUtil;
import com.tools.common.util.security.SecurityUtil;
@SuppressWarnings("all")
public class HttpClientUtil {
	static DefaultHttpClient httpClient = new DefaultHttpClient();

	public static String get(String url, String encoding) throws Exception {
		HttpGet httpGet = new HttpGet(url);
		/* -------  自定义参数 -----start-- */
		httpGet.setHeader("token", "%7B%22pin%22%3A%221231231231%22%2C%22encryptStr%22%3A%225050E8A2C4754AB269C92EC165560C84%22%2C%22encryptMD5Str%22%3A%22ae74481cd0d9fbe612f5d80f963e7eb4%22%7D");
		httpGet.setHeader("cookie", "%7B%22pin%22%3A%224575%22%2C%22encryptStr%22%3A%22F6287B1F7CDAF6FDFC6B6B725081F3A8%22%2C%22encryptMD5Str%22%3A%22217d95c353edb4a7236d4a305415a69f%22%2C%22device%22%3A%2256bfd9acfb241e7a7a75bf3a48064ab7%22%7D");
        /* -------  自定义参数 -----end-- */
		HttpResponse res = httpClient.execute(httpGet);
		return getContent(res, encoding);
	}

	public static String get(String url, String encoding,
			DefaultHttpClient client) throws Exception {
		HttpGet httpGet = new HttpGet(url);
		HttpResponse res = client.execute(httpGet);
		return getContent(res, encoding);
	}

	public static String post(String url, StringEntity se, String host,
			String referer, String encoding) throws Exception {
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(se);
		httpPost.setHeader("Host", host);
		httpPost.setHeader("Referer", referer);
		httpPost.setHeader("Accept", "*/*");
		httpPost.setHeader("Accept-Language", "zh-cn");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
		httpPost.setHeader("UA-CPU", "x86");
		httpPost.setHeader(
				"User-Agent",
				"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1) ; .NET CLR 2.0.50727; InfoPath.2; CIBA)");
		httpPost.setHeader("Connection", "close");
		HttpResponse response = httpClient.execute(httpPost);

		return getContent(response, encoding);
	}

	public static String httpPost(String url, String queryString, String encoding) throws Exception {
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(new StringEntity(queryString,"UTF-8"));
		httpPost.setHeader("Content-Type", "application/json;charset=utf-8");
		httpPost.getParams().setParameter("http.socket.timeout", new Integer(60000));
		httpPost.setHeader("Connection", "close");
		
		/* -------  自定义参数 -----start-- */
		httpPost.setHeader("token", "%7B%22pin%22%3A%221231231231%22%2C%22encryptStr%22%3A%225050E8A2C4754AB269C92EC165560C84%22%2C%22encryptMD5Str%22%3A%22ae74481cd0d9fbe612f5d80f963e7eb4%22%7D");
		httpPost.setHeader("cookie", "%7B%22pin%22%3A%224575%22%2C%22encryptStr%22%3A%22F6287B1F7CDAF6FDFC6B6B725081F3A8%22%2C%22encryptMD5Str%22%3A%22217d95c353edb4a7236d4a305415a69f%22%2C%22device%22%3A%2256bfd9acfb241e7a7a75bf3a48064ab7%22%7D");
		/* -------  自定义参数 -----end-- */
		
		HttpResponse response = httpClient.execute(httpPost);

		return getContent(response, encoding);
	}

	public static String getContent(HttpResponse res, String encoding)
			throws Exception {
		HttpEntity ent = res.getEntity();
		String result = IOUtils.toString(ent.getContent(), encoding);
		ent.consumeContent();
		return result;
	}

	public static InputStream getStream(String url) throws Exception {
		HttpGet httpGet = new HttpGet(url);
		HttpResponse res = httpClient.execute(httpGet);
		return res.getEntity().getContent();
	}

	public static InputStream getStream(String url, DefaultHttpClient client)
			throws Exception {
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader(
				"User-Agent",
				"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1) ; .NET CLR 2.0.50727; InfoPath.2; CIBA)");
		httpGet.setHeader("Referer",
				"http://reg.126.com/regmail126/userRegist.do?action=fillinfo");
		// httpGet.setHeader("Accept", "*/*");
		// httpGet.setHeader("Accept-Language", "zh-cn");
		// httpGet.setHeader("Accept-Encoding", "gzip, deflate");
		httpGet.setHeader("Connection", "close");
		HttpResponse res = client.execute(httpGet);
		return res.getEntity().getContent();
	}

	public static void main(String args[]) throws Exception {
		//访问地址
//	    String url = "http://127.0.0.1:6101/token/getToken";
//		String url = "http://127.0.0.1:6101/login/getSms";
//		String url = "http://127.0.0.1:6101/login/saveSms";
//		String url = "http://127.0.0.1:6101/personal/getPersonal";
		String url = "http://127.0.0.1:6101/vueDemo/listVue";
		
		Map<String, String> map = new HashMap<String, String>();
//		map.put("appPin", "1231231231");
//		map.put("appId", "8528745b03a2418b9fea3a6319df4da5");
//		map.put("secret", "5123123123");
//		map.put("checkCode", "178350");
//		map.put("pin", "hezemin");
//		map.put("password", "c6d37caa262458f7cb80d16b4c056cd3");
//		map.put("deviceCode", "101d8559097168d9ca1");
//		map.put("employeePhoto", "12345.jpg");
//		map.put("oldPassword", SecurityUtil.md5("123456"));
//		map.put("newPassword", SecurityUtil.md5("1"));
//		map.put("employeeNo", "DP1827");
//		map.put("password", SecurityUtil.md5("1"));
//		map.put("employeeNo", "DP7284");
//		map.put("password", SecurityUtil.md5("123456"));
//		map.put("checkCode", "123456");
//		map.put("deviceCode", "abcdefghijklmnopqrstuvwxyz22");
		
//		map.put("id", "5");
//		map.put("name", "三");
//		map.put("age", "111");
//		map.put("birthday", "2018-07-03");
//		map.put("remark", "----111-");
		
		
		
		//发送
		String resultString = HttpClientUtil.httpPost(url, JsonUtil.toJson(map), "utf-8");
//		String resultString = HttpClientUtil.get(url, "utf-8");
		System.out.println(resultString);
		
	}
}

