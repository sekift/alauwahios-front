/**
* COPYRIGHT. Tianya Inc. ALL RIGHTS RESERVED.
* Project: tianya_fw
* Author: wudg  <wudg@staff.tianya.cn>
* Create On: 2013-5-28 下午1:48:36 
*/

package cn.alauwahios.front.monitor.web;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.alauwahios.front.util.StringUtil;


/** 
 *请求信息记录类
 */

public class RequestLogger implements RequestLoggerMBean{
	
	private static Logger logger = LoggerFactory.getLogger(RequestLogger.class);
	
	/**
	 * 指定的url个数   超过该数，后续url会被忽略
	 */
	public static short MAX_URL_NUM = 10;
	
	/**
	 * 用于匹配的URL
	 */
	private String urlPattern;
	
	/**
	 * url匹配规则 由urlPattern形成
	 */
	private List<UrlRule> urlRuleList;
	
	
	/**
	 * 响应时间的阀值 单位:秒   默认2000
	 */
	private long respTimeThreshold=2000;
	
	
	/**
	 * 请求总数
	 */
	private AtomicLong totalRequestCnt=new AtomicLong();
	
	
	/**
	 * 符合匹配规则的url的请求数
	 */
	private AtomicLong matchedRequestCnt=new AtomicLong();
	
	/**
	 * 超过指定时间的请求总数
	 * */
	private AtomicLong overtimeRequestCnt=new AtomicLong();
	
	
	/**
	 * 符合匹配规则且响应时间超过响应指定时间的请求数
	 */
	private AtomicLong overtimeMatchedRequestCnt=new AtomicLong();
	
	/**
	 * 平均响应时间
	 */
	private double avgResqTime = 0.00;
	
	/**
	 * 检测 url需要记录
	 * @param url  待检测url字符串 
	 * @return  
	 */
	private boolean match(String url) {
		if (StringUtil.isNullOrBlank(url)|| urlRuleList == null || urlRuleList.isEmpty()) {
			return false;
		}

		for (UrlRule ub : urlRuleList) {
			if (ub.match(url)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 构造方法
	 * @param urlPattern  指定用于URL匹配的模式 如/jsp/*
	 * @param respTimeThreshold  请求响应时间阀值，单位毫秒  默认值为2000
	 */
	public RequestLogger(String urlPattern,Long respTimeThreshold){
		urlRuleList=new LinkedList<UrlRule>();
		if(!StringUtil.isNullOrBlank(urlPattern)){
			this.urlPattern=urlPattern.trim();
			String[] includeUrlArr = urlPattern.split(";");
			if (includeUrlArr != null && includeUrlArr.length > 0) {
				for (String e : includeUrlArr) {
					e = e.trim();
					if (!StringUtil.isBlank(e)&&urlRuleList.size()<=MAX_URL_NUM) {
						urlRuleList.add(new UrlRule(e));
					}
				}
			}
		}
		if(respTimeThreshold!=null){
			this.respTimeThreshold=respTimeThreshold;
		}
	}
	
	
	/**
	 * 记录总请求数,响应时间超过指定时间的请求数，及url符合指定规则(如/user/*; *.jsp)的请求数
	 * @param request 请求对象
	 * @param response 响应对象
	 * @param respTime 请求响应时间 
	 * @throws Exception
	 */
	public void log(HttpServletRequest request,HttpServletResponse response,long respTime){
		double sumResqTime = 0.00;
		try{
			String url = request.getServletPath()+ (request.getPathInfo() == null ? "" : request.getPathInfo());
			totalRequestCnt.incrementAndGet();
			if(totalRequestCnt.longValue() !=0 ){
				sumResqTime = sumResqTime + respTime;
				avgResqTime = sumResqTime/totalRequestCnt.longValue();
			}
			boolean overTimeFlag=respTime>respTimeThreshold;
			if(overTimeFlag){
				overtimeRequestCnt.incrementAndGet();
			}
			
			//记录URL符合指定规则的请求数
			if(urlRuleList!=null&&!urlRuleList.isEmpty()&&match(url)){
				matchedRequestCnt.incrementAndGet();
				if(overTimeFlag){
					overtimeMatchedRequestCnt.incrementAndGet();
				}
			} 
		}catch(Exception e){
			logger.error("记录请求数时出现异常",e);
		}
	}
	 
	@Override
	public String getUrlPattern() {
		return urlPattern;
	}

	@Override
	public long getRespTimeThreshold() {
		return respTimeThreshold;
	}

	@Override
	public long getTotalRequestCnt() {
		return totalRequestCnt.longValue();
	}
	
	@Override
	public long getMatchedRequestCnt() { 
		return matchedRequestCnt.longValue();
	}

	@Override
	public long getOvertimeRequestCnt() {
		return overtimeRequestCnt.longValue();
	}
	
	@Override
	public long getOvertimeMatchedRequestCnt() {
		return overtimeMatchedRequestCnt.longValue();
	}

	@Override
	public double getAvgResqTime() {
		return avgResqTime;
	}
}
