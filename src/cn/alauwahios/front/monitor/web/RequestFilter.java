package cn.alauwahios.front.monitor.web;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.alauwahios.front.dao.RequestDao;
import cn.alauwahios.front.util.IPUtil;
import cn.alauwahios.front.util.StringUtil;

/** 
 *WEB 请求计数过滤器   
 */
public class RequestFilter implements Filter{
	private static Logger logger = LoggerFactory.getLogger(RequestFilter.class);
	
	private RequestLogger requestLogger;

	/**
	 * 初始化
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		try{
			//指定的url匹配模式
			String urlPattern=null;
			String urlPatternStr=filterConfig.getInitParameter("urlPattern");
			if(!StringUtil.isNullOrBlank(urlPatternStr)){
				urlPattern=urlPatternStr.trim();
			}
			
			//响应时间阀值参数
			Long respTimeThreshold=null;
			String respTimeThresholdStr=filterConfig.getInitParameter("respTimeThreshold");
			if(!StringUtil.isNullOrBlank(respTimeThresholdStr)){
				respTimeThreshold=Long.valueOf(respTimeThresholdStr.trim());
			}
			
			requestLogger = new RequestLogger(urlPattern, respTimeThreshold);
			
			logger.info("Web请求计数器初始化完成");
			
			saveRequest();
			//注册MBean
			//JMXUtil.registerMBean(requestLogger,"cn.alauwahios.front.monitor.web:type=WebRequestLogger");
		}catch(Exception e){
			logger.error("Web请求计数服务启动失败",e);
		} 
	}

	/**
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain chain) throws IOException, ServletException {
		
		long startTime=System.currentTimeMillis();
		
		chain.doFilter(servletRequest, servletResponse); 
		 
		//响应时间
		long respTime=System.currentTimeMillis()-startTime;
		
		//记录请求信息
		try{
			HttpServletRequest httpRequest = (HttpServletRequest)servletRequest; 
			HttpServletResponse httpResponse=(HttpServletResponse)servletResponse;
			if(requestLogger!=null){
				requestLogger.log(httpRequest,httpResponse,respTime);
			}
		}catch(Exception e){
			logger.error("Web请求计数过程出现异常",e);
		} 
	}
	
	// 统计入库，5分钟一次
	String serverIp = IPUtil.getLocalIP();
	RequestDao requestDao = new RequestDao();
	
	private class SaveRequestImpl implements Runnable{
		@Override
		public void run() {
			requestDao.saveRequestCnt(serverIp, requestLogger);
			requestLogger =  new RequestLogger(requestLogger.getUrlPattern(), requestLogger.getRespTimeThreshold());
		}
	}
	
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	
	public void saveRequest() {
		SaveRequestImpl sr = new SaveRequestImpl();
		scheduler.scheduleAtFixedRate(sr, 0, 5*60, SECONDS);
	}

	/**
	 * 销毁过滤器
	 */
	@Override
	public void destroy() { 
		requestLogger = null;
		logger.info("Web请求计数服务过滤器已被销毁");
	}

}
