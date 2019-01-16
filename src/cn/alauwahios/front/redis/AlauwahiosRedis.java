package cn.alauwahios.front.redis;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.alauwahios.front.Constants;
import cn.alauwahios.front.util.JsonUtil;
import cn.alauwahios.front.vo.BaiduWangpanVO;
import cn.alauwahios.front.vo.BaiduYunVO;

/**
 * redis业务操作类
 * @author luyz
 * @date 2019-01-14
 *
 */
public class AlauwahiosRedis {

	private static final Logger logger = LoggerFactory.getLogger(AlauwahiosRedis.class);

	private static AlauwahiosRedis instance = new AlauwahiosRedis();

	/**
	 * 构造方法
	 * 
	 */
	private AlauwahiosRedis() {
	}

	/**
	 * 静态工厂方法
	 * 
	 * @return
	 */
	public static AlauwahiosRedis getInstance() {
		return instance;
	}
	
	/**
	 * baidu_yun
	 */
	public List<BaiduYunVO> getBaiduYun(String key){
		List<BaiduYunVO> list  = null;
		try {
			String value = RedisOperate.getValueByKey(Constants.CACHE_PRE + key);
			if(null != value) {
				list = (List<BaiduYunVO>) JsonUtil.toBeanList(value, BaiduYunVO.class);
			}
		} catch (Exception e) {
			logger.error("[缓存错误]获取缓存错误：", e);
		}
		return list;
	}
	
	public void setBaiduYun(String key, List<BaiduYunVO> list, int time){
		try {
			String value = JsonUtil.toJson(list);
			RedisOperate.set(Constants.CACHE_PRE + key, value, time);
		} catch (Exception e) {
			logger.error("[缓存错误]设置缓存错误：", e);
		}
	}
	
	/**
	 * baidu_wangpan
	 */
	public List<BaiduWangpanVO> getBaiduWangpan(String key){
		List<BaiduYunVO> list  = null;
		try {
			String value = RedisOperate.getValueByKey(Constants.CACHE_PRE + key);
			if(null != value) {
				list = (List<BaiduYunVO>) JsonUtil.toBeanList(value, BaiduYunVO.class);
			}
		} catch (Exception e) {
			logger.error("[缓存错误]获取缓存错误：", e);
		}
		return list;
	}
	
	public void setBaiduYun(String key, List<BaiduYunVO> list, int time){
		try {
			String value = JsonUtil.toJson(list);
			RedisOperate.set(Constants.CACHE_PRE + key, value, time);
		} catch (Exception e) {
			logger.error("[缓存错误]设置缓存错误：", e);
		}
	}
	
	public void delPan(String key){
		try {
			RedisOperate.deleteByKey(Constants.CACHE_PRE + key);
		} catch (Exception e) {
			logger.error("[缓存错误]删除缓存错误：", e);
		}
	}
	

}
