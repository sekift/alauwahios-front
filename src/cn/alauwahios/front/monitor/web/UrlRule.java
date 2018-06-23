package cn.alauwahios.front.monitor.web;

import cn.alauwahios.front.util.StringUtil;

/**
 * URL对象 
 * oriUrl=/test/*;matchType=MATCH_PREFIXX,即匹配前缀; treatedUrl=/test/
 * oriUrl=*.jsp; matchType=MATCH_DISFIXX,即匹配后缀; treatedUrl=.jsp 
 * 其他情况为精确匹配,oriUrl=treatedUrl
 * 
 */

public class UrlRule {

	/**
	 * 精确匹配
	 */
	public static final int MATCH_PRECISE = 1;

	/**
	 * 匹配前缀
	 */
	public static final int MATCH_PREFIX = 2;

	/**
	 * 匹配后缀
	 */
	public static final int MATCH_DISFIX = 3;

	/**
	 * 原始URL 用户指定的url 如 /test/*
	 */
	private String oriUrl;

	/**
	 * 处理过的url  如/test/*,处理后就是/test/
	 */
	private String treatedUrl;

	/**
	 * 匹配类型
	 */
	private int matchType;
	
	/**
	 * url匹配模式字符串
	 * @param urlPatternMatched
	 */
	public UrlRule(String urlPatternMatched){
		if (!StringUtil.isNullOrBlank(urlPatternMatched)) {
			urlPatternMatched = urlPatternMatched.trim(); 
			this.oriUrl=urlPatternMatched;
			if (urlPatternMatched.endsWith("*")) {
				// 匹配前缀 
				this.treatedUrl=urlPatternMatched.substring(0, urlPatternMatched.length() - 1);
				this.matchType=UrlRule.MATCH_PREFIX; 
			} else if (urlPatternMatched.startsWith("*")) {
				// 匹配后缀
				this.treatedUrl = urlPatternMatched.substring(1, urlPatternMatched.length());
				this.matchType=UrlRule.MATCH_DISFIX; 
			} else {
				// 精确匹配 
				this.treatedUrl =urlPatternMatched;
				this.matchType= UrlRule.MATCH_PRECISE;
			}  
		}
	}
	 
	public String getOriUrl() {
		return oriUrl;
	}

	public void setOriUrl(String oriUrl) {
		this.oriUrl = oriUrl;
	}

	public String getTreatedUrl() {
		return treatedUrl;
	}

	public void setTreatedUrl(String treatedUrl) {
		this.treatedUrl = treatedUrl;
	}

	public int getMatchType() {
		return matchType;
	}

	public void setMatchType(int matchType) {
		this.matchType = matchType;
	}
	
	
	/**
	 * 检测当前URL是否在
	 * @param url 待匹配URL
	 * @return
	 */
	public boolean match(String url){
		if (url == null || url.equals("")) {
			return false;
		} 
		//前缀匹配
		if (this.getMatchType() ==MATCH_PREFIX
				&& url.startsWith(this.getTreatedUrl())) {
			return true;
		}

		//后缀匹配
		if (this.getMatchType() == MATCH_DISFIX
				&& url.endsWith(this.getTreatedUrl())) {
			return true;
		}
		
		//精确匹配
		if (this.getMatchType() == MATCH_PRECISE
				&& url.equals(this.getTreatedUrl())) {
			return true;
		}

		return false; 
	}
}