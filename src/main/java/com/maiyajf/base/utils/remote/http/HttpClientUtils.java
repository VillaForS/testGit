package com.maiyajf.base.utils.remote.http;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.maiyajf.base.utils.log.ExceptionLogger;

/**
 * @ClassName: HttpClientUtils
 * @Description: HttpClient请求工具类
 * @author: yunlei.hua
 * @date: 2015年10月24日 下午3:06:53
 */
public class HttpClientUtils {
	
	private static PoolingHttpClientConnectionManager connMgr;
	private static CloseableHttpClient httpClient;
	private static RequestConfig requestConfig;
	
	static {
		// 设置连接池
		connMgr = new PoolingHttpClientConnectionManager();
		connMgr.setMaxTotal(50);
		connMgr.setDefaultMaxPerRoute(20);
		
		// 设置超时时间2s
		Builder builder = RequestConfig.custom();
		builder.setSocketTimeout(1000);
		builder.setConnectTimeout(1000);
		builder.setConnectionRequestTimeout(1000);
		requestConfig = builder.build();
	}
	
	/**
	 * @Title: doPOST
	 * @Description: post请求
	 * @param: params 请求参数（参数类型为List<NameValuePair>）
	 * @param: url
	 * @param: clazz 返回类型
	 * @return: T
	 */
	public static <T> T doPOST (List<NameValuePair> params, String url, Class<T> clazz) {
		httpClient = HttpClients.custom().setConnectionManager(connMgr)
				.setDefaultRequestConfig(requestConfig).build();
		HttpPost httpPost = new HttpPost(url);
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		
		try {
			httpPost.setConfig(requestConfig);
			httpPost.setEntity(new UrlEncodedFormEntity(params));
			response = httpClient.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				return null;
			}
			
			entity = response.getEntity();
			if (null == entity) {
				return null;
			}
			String result = EntityUtils.toString(entity, "utf-8");
			return JSON.parseObject(result, clazz);
		} catch (Exception e) {
			ExceptionLogger.error(e);
			e.printStackTrace();
		} finally {
			try {
				if (null != entity) {
					entity.getContent().close();
				}
				if (null != response) {
					response.close();
				}
				if (null != httpPost) {
					httpPost.releaseConnection();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	/**
	 * @Title: doPostStringEntity
	 * @Description: post请求（HttpEntity请求数据为StringEntity）
	 * @param: params 请求参数（参数类型为Map<String, Object>）
	 * @param: url
	 * @param: clazz 返回类型
	 * @return: T
	 */
	public static <T> T doPostStringEntity (Map<String, Object> params, String url, Class<T> clazz) {
		httpClient = HttpClients.custom().setConnectionManager(connMgr)
				.setDefaultRequestConfig(requestConfig).build();
		HttpPost httpPost = new HttpPost(url);
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		StringEntity reqEntity = null;
		
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, Object> map : params.entrySet()) {
			sb.append(map.getKey());
			sb.append("=");
			sb.append(map.getValue());
			sb.append("&");
		}
		String reqParams = sb.toString();
		reqParams = reqParams.substring(0, reqParams.length()-1);
		
		try {
			httpPost.setConfig(requestConfig);
			reqEntity = new StringEntity(reqParams);
			reqEntity.setContentType("application/x-www-form-urlencoded");    
			// 设置请求的数据    
			httpPost.setEntity(reqEntity);    
			response = httpClient.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				return null;
			}
			
			entity = response.getEntity();
			if (null == entity) {
				return null;
			}
			String result = EntityUtils.toString(entity, "utf-8");
			return JSON.parseObject(result, clazz);
		} catch (Exception e) {
			ExceptionLogger.error(e);
			e.printStackTrace();
		} finally {
			try {
				if (null != reqEntity) {
					reqEntity.getContent().close();
				}
				if (null != entity) {
					entity.getContent().close();
				}
				if (null != response) {
					response.close();
				}
				if (null != httpPost) {
					httpPost.releaseConnection();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	/**
	 * @Title: doPostNameVals
	 * @Description: post请求
	 * @param: params 请求参数（参数类型为Map<String, String>）
	 * @param: url
	 * @param: clazz 返回类型
	 * @return: T
	 */
	public static <T> T doPostNameVals (Map<String, String> params, String url, Class<T> clazz) {
		if (null == params || params.size() ==0)
			return null;
		
		List<NameValuePair> paramList = new ArrayList<NameValuePair>();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			NameValuePair nameValue = new BasicNameValuePair(entry.getKey(), entry.getValue());
			paramList.add(nameValue);
		}
		
		return HttpClientUtils.doPOST(paramList, url, clazz);
	}
	
	/**
	 * @Title: doGet
	 * @Description: get请求
	 * @param: url
	 * @param: clazz 返回类型
	 * @return: T
	 */
	public static <T> T doGet (String url, Class<T> clazz) {
		httpClient = HttpClients.custom().setConnectionManager(connMgr)
				.setDefaultRequestConfig(requestConfig).build();
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		
		try {
			httpGet.setConfig(requestConfig);
			response = httpClient.execute(httpGet);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				return null;
			}
			
			entity = response.getEntity();
			if (null == entity) {
				return null;
			}
			String result = EntityUtils.toString(entity, "utf-8");
			return JSON.parseObject(result, clazz);
		} catch (Exception e) {
			ExceptionLogger.error(e);
			e.printStackTrace();
		} finally {
			try {
				if (null != entity) {
					entity.getContent().close();
				}
				if (null != response) {
					response.close();
				}
				if (null != httpGet) {
					httpGet.releaseConnection();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	
}
