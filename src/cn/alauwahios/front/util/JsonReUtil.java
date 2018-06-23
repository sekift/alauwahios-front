package cn.alauwahios.front.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Json操作工具
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class JsonReUtil {
	/**
	 * 构造执行失败响应；符合前端格式
	 * 
	 * @param error
	 * @return
	 */
	public static String getFailedResponse(String error) {
		String response = null;
		try {
			Map map = new HashMap();
			Map responseMap = new HashMap();
			responseMap.put("result", 0);
			responseMap.put("error", error);
			responseMap.put("data", map);
			response = JsonUtil.toJson(responseMap);
		} catch (Exception e) {
		}

		return response;
	}
	/**
	 * 构造执行失败响应
	 * 
	 * @param code
	 * @param message
	 * @return
	 */
	public static String getFailedResponse(String code, String message) {
		String response = null;
		try {
			Map<Object, Object> map = new HashMap<Object, Object>();
			Map<Object, Object> responseMap = new HashMap<Object, Object>();
			responseMap.put("success", 0);
			responseMap.put("code", code);
			responseMap.put("message", message);
			responseMap.put("data", map);
			response = toJson(responseMap);
		} catch (Exception e) {
		}
		return response;
	}

	/**
	 * 构造执行失败响应(临时)
	 * 
	 * @param code
	 * @param message
	 * @return
	 */
	public static String getFailedResponse(String code, String message, Map map) {
		String response = null;
		try {
			Map<Object, Object> responseMap = new HashMap<Object, Object>();
			responseMap.put("success", 0);
			responseMap.put("code", code);
			responseMap.put("message", message);
			responseMap.put("data", map);
			response = toJson(responseMap);
		} catch (Exception e) {
		}
		return response;
	}
	
	/**
	 * 构造JSON请求
	 * 
	 * @param map
	 * @return
	 */
	public static String getJsonRequest(Map map) {
		String request = null;
		try {
			if (map == null) {
				map = new HashMap<Object, Object>();
			}
			request = toJson(map);
		} catch (Exception e) {
		}
		return request;
	}

	/**
	 * 构造JSON响应
	 * 
	 * @param map
	 * @return
	 */
	public static String getJsonResponse(Map<Object, Object> map) {
		String request = null;
		try {
			if (map == null) {
				map = new HashMap<Object, Object>();
			}
			request = toJson(map);
		} catch (Exception e) {
		}
		return request;
	}

	/**
	 * 把json字符串转换为Map
	 * @param request
	 * @return
	 */
	public static Map<Object, Object> getRequestMap(String request) {
		Map<Object, Object> requestMap = toBean(request,
				HashMap.class);
		return requestMap;
	}

	/**
	 * 构造执行成功响应
	 * 
	 * @param code
	 * @param message
	 * @return
	 */
	public static String getSuccessResponse(String code, String message) {
		Map map = new HashMap();
		return getSuccessResponse(code, message, map);
	}

	/**
	 * 构造执行成功响应
	 * 
	 * @param code
	 * @param message
	 * @param list
	 * @return
	 */
	public static String getSuccessResponse(String code, String message,
			List list) {
		Map map = new HashMap();
		map.put("list", list);
		return getSuccessResponse(code, message, map);
	}
	
	/**
	 * 构造执行成功响应
	 * 
	 * @param code
	 * @param message
	 * @param map
	 * @return
	 */
	public static String getSuccessResponse(String code, String message, Map map) {
		String response = null;
		try {
			Map responseMap = new HashMap();
			responseMap.put("success", 1);
			responseMap.put("code", code);
			responseMap.put("message", message);
			responseMap.put("data", map);
			response = toJson(responseMap);
		} catch (Exception e) {
		}
		return response;
	}

	/**
	 * 构造执行成功响应
	 * 
	 * @param code
	 * @param message
	 * @param map
	 * @param list
	 * @return
	 */
	public static String getSuccessResponse(String code, String message,
			Map map, List list) {
		map.put("list", list);
		return getSuccessResponse(code, message, map);
	}
	/**
	 * 构造执行成功响应
	 * 
	 * @param error
	 * @return
	 */
	public static String getSuccessResponse() {
		Map map = new HashMap();
		return getSuccessResponse(map);
	}
	/**
	 * 构造执行成功响应
	 * 
	 * @param error
	 * @param map
	 * @return
	 */
	public static String getSuccessResponse(Map map) {
		String response = null;
		try {
			if (map == null) {
				map = new HashMap();
			}
			Map responseMap = new HashMap();
			responseMap.put("result", 1);
			responseMap.put("error", "");
			responseMap.put("data", map);
			response = JsonUtil.toJson(responseMap);
		} catch (Exception e) {
		}

		return response;
	}
	/**
	 * JSON反序列化
	 * 
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T toBean(String json, Class<T> clazz) {
		return JsonUtil.toBean(json, clazz);
	}

	/**
	 * JSON序列化
	 * 
	 * @param bean
	 * @return
	 */
	public static String toJson(Object bean) {
		return JsonUtil.toJson(bean);
	}

	/**
	 * 构造响应
	 * 
	 * @param result
	 *            响应错误码，成功为1，其它为失败。
	 * @param error
	 *            错语提示，错误响应时必填
	 * @param map
	 *            返回数据
	 * @return
	 */
	public static String getResponse(String result, String error,
			Map<Object, Object> map) {
		String response = null;
		try {
			if (map == null) {
				map = new HashMap<Object, Object>();
			}
			Map<Object, Object> responseMap = new HashMap<Object, Object>();
			responseMap.put("result", result);
			responseMap.put("error", error);
			responseMap.put("data", map);
			response = toJson(responseMap);
		} catch (Exception e) {
		}
		return response;
	}

	/**
	 * 构造响应
	 * 
	 * @param result
	 *            响应错误码，成功为1，其它为失败。
	 * @param error
	 *            错语提示，错误响应时必填
	 * @return
	 */
	public static String getResponse(String result, String error) {
		String response = null;
		try {
			Map<Object, Object> responseMap = new HashMap<Object, Object>();
			responseMap.put("result", result);
			responseMap.put("error", error);
			responseMap.put("data", new HashMap<Object, Object>());
			response = toJson(responseMap);
		} catch (Exception e) {
		}
		return response;
	}
	
	/**
	 * 转换缓存为success格式
	 * @param result
	 * @return
	 */
	public static String parse(String result){
		String response = result;
		try {
			// 将Json字符串转成Map
			Map resultMap = JsonUtil.toBean(result,
					HashMap.class);

			if(!resultMap.containsKey("code")){
				resultMap.put("code", "1");
			}
			
			// 获取success
			if (resultMap.containsKey("result")) {
				resultMap.put("success", Integer.parseInt(String.valueOf(resultMap
						.get("result"))));
				resultMap.remove("result");
				response = getJsonRequest(resultMap);
			}
			
			// 获取success
			if (resultMap.containsKey("error")) {
				resultMap.put("message", String.valueOf(resultMap
						.get("error")));
				resultMap.remove("error");
				response = getJsonRequest(resultMap);
			}
			
		} catch (Exception e) {
		}
	
		return response;
	}
	
}
