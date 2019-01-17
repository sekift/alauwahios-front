package cn.alauwahios.front.redis;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.alauwahios.front.Constants;
import cn.alauwahios.front.util.JsonUtil;
import cn.alauwahios.front.vo.BaiduTiebaVO;
import cn.alauwahios.front.vo.BaiduWangpanVO;
import cn.alauwahios.front.vo.BaiduYunVO;
import cn.alauwahios.front.vo.FxZiyuanVO;

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
		List<BaiduWangpanVO> list  = null;
		try {
			String value = RedisOperate.getValueByKey(Constants.CACHE_PRE + key);
			if(null != value) {
				list = (List<BaiduWangpanVO>) JsonUtil.toBeanList(value, BaiduWangpanVO.class);
			}
		} catch (Exception e) {
			logger.error("[缓存错误]获取缓存错误：", e);
		}
		return list;
	}
	
	public void setBaiduWangpan(String key, List<BaiduWangpanVO> list, int time){
		try {
			String value = JsonUtil.toJson(list);
			RedisOperate.set(Constants.CACHE_PRE + key, value, time);
		} catch (Exception e) {
			logger.error("[缓存错误]设置缓存错误：", e);
		}
	}
	
	/**
	 * FxZiyuan
	 */
	public List<FxZiyuanVO> getFxZiyuan(String key){
		List<FxZiyuanVO> list  = null;
		try {
			String value = RedisOperate.getValueByKey(Constants.CACHE_PRE + key);
			if(null != value) {
				list = (List<FxZiyuanVO>) JsonUtil.toBeanList(value, FxZiyuanVO.class);
			}
		} catch (Exception e) {
			logger.error("[缓存错误]获取缓存错误：", e);
		}
		return list;
	}
	
	public void setFxZiyuan(String key, List<FxZiyuanVO> list, int time){
		try {
			String value = JsonUtil.toJson(list);
			RedisOperate.set(Constants.CACHE_PRE + key, value, time);
		} catch (Exception e) {
			logger.error("[缓存错误]设置缓存错误：", e);
		}
	}
	
	/**
	 * baidu_tieba
	 */
	public List<BaiduTiebaVO> getBaiduTieba(String key){
		List<BaiduTiebaVO> list  = null;
		try {
			String value = RedisOperate.getValueByKey(Constants.CACHE_PRE + key);
			if(null != value) {
				list = (List<BaiduTiebaVO>) JsonUtil.toBeanList(value, BaiduTiebaVO.class);
			}
		} catch (Exception e) {
			logger.error("[缓存错误]获取缓存错误：", e);
		}
		return list;
	}
	
	public void setBaiduTieba(String key, List<BaiduTiebaVO> list, int time){
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
