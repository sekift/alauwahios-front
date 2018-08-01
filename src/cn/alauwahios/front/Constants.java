package cn.alauwahios.front;

/**
 * 提供常数的类
 * 
 * @author:sekift
 * @time:2018-06-08 下午03:38:14
 */
public class Constants {
	//是否测试环境
	//private static final boolean test = false; 
	// 贴吧写文件
	public static String TIEBA_SINGLE = "/www/client/monitor_crawler/link.txt";
	public static String TIEBA_ALL = "/www/client/monitor_crawler/tieba_link.txt";
	
	// 主库
	public static final String ALIAS_MASTER = "alauwahios_master";
	
	// 从库
	public static final String ALIAS_SLAVE = "alauwahios_slave";
	
	// 类别默认为0
	public static final int DEFAULT_TYPE = 0;
	
	// 备用字段默认值为""
	public static final String DEFAULT_REMARK = "";
	
	// 短链接字段默认值为""
	public static final String DEFAULT_SHORT_LINK = "";
	
	//功能常量
	public static final String TIEBA = "tieba";
	public static final String TIEZI = "tiezi";
	public static final String WANGPAN = "wangpan";
	public static final String YUN = "yun";
	public static final String WANGPAN_TIEBA = "wangpan_tieba";
	public static final String TIEZI_TIEBA = "tiezi_tieba";
	public static final String INTERNET_CELEBRITY = "internet_celebrity";
	public static final String ZIYUAN = "ziyuan";
	
	// 主域名
	public static final String BASE_DOMAIN = "http://www.pan00.com";
	
	// 网盘网址
	public static final String HTTP= "http";
	public static final String HTTPS= "https";
	public static final String HTTP_MARK= HTTP + "://";
	public static final String HTTPS_MARK= HTTPS + "://";
	public static final String HOME_PAGE_URL = "https://pan.baidu.com/mbox/homepage";
	public static final String QUESTION_MARK= "?";
	public static final String SHORT_IDX_UNMARK = "short=";
	public static final String SHORT_IDX = QUESTION_MARK + SHORT_IDX_UNMARK;
	public static final String BAIDU_TIEBA_COM = "tieba.baidu.com";
	public static final String BAIDU_TIEZI_P = "/p/";
	public static final String BAIDU_TIEZI_BASE = HTTPS_MARK + BAIDU_TIEBA_COM + BAIDU_TIEZI_P; 
	public static final String BAIDU_TIEBA_F = "/f?kw=";
	public static final String BAIDU_TIEBA_PATH = HTTPS_MARK + BAIDU_TIEBA_COM + BAIDU_TIEBA_F;
	
	
	/*static{
		if(test){
			System.out.println("进入了");
			TIEBA_SINGLE = "D:/www/client/monitor_crawler/link.txt";
			TIEBA_ALL = "D:/www/client/monitor_crawler/tieba_link.txt";
		}
	}*/
}
