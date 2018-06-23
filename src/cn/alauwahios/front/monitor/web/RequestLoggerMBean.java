package cn.alauwahios.front.monitor.web;

/** 
 *web请求计数器mbean接口
 */

public interface RequestLoggerMBean {
	/**
	 * URL匹配模式
	 * @return
	 */
	public String getUrlPattern();
	
	/**
	 * 响应时间阀值
	 * @return
	 */
	public long getRespTimeThreshold();
	
	/**
	 * 总请求个数
	 * @return
	 */
	public long getTotalRequestCnt();
	
	/**
	 * 符合指定匹配规则的url的请求数
	 * @return
	 */
	public long getMatchedRequestCnt(); 
	
	/**
	 * 超时请求个数
	 * @return
	 */
	public long getOvertimeRequestCnt();
	
	/**
	 * 符合匹配规则且响应时间超过响应指定时间的请求数
	 * @return
	 */
	public long getOvertimeMatchedRequestCnt();

	/**
	 * 平均响应时间
	 * @return
	 */
	public double getAvgResqTime();

}
