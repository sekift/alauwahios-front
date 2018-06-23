package cn.alauwahios.front;

/**
 * 返回值的枚举类
 * @time:2018-06-20 下午02:39:32
 * @version:
 */
public enum CodeAndMessage {
	//通用
	PARAMETER_ERROR	("-1", "参数错误"),
	TYPE_ERROR		("0", "类型错误"),
	REQUEST_FAIL	("0", "请求失败"),
	OPERATE_FAIL	("0", "操作失败"),
	
	SUCCESS			("1", "成功"),
	OPERATE_SUCCESS	("1", "操作成功"),
	
	STAR_SUCCESS	("1", "点赞成功"),
	STAR_FAIL		("0", "点赞失败"),
	SORT_SUCCESS	("1", "置顶成功"),
	SORT_FAIL		("0", "置顶失败"),
	SORT_CANCEL_SUCCESS	("1", "取消置顶成功"),
	SORT_CANCEL_FAIL	("0", "取消置顶失败"),
    
	//结束语
	END_WORD		("0", "end");
	
	private String code = null;
	private String message = null;

	CodeAndMessage(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
