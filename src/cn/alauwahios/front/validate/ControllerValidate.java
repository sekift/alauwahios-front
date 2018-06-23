package cn.alauwahios.front.validate;

import cn.alauwahios.front.util.StringUtil;

public class ControllerValidate {
	
	/**
	 * 是否为前端输出var
	 * @param sb
	 * @param var
	 */
	public static void useVar(StringBuffer sb, String var) {
		if(!StringUtil.isNullOrBlank(var)){
			sb.append("var ").append(var).append("=");
		}
	}

}
