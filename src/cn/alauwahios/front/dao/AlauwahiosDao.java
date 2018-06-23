package cn.alauwahios.front.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import cn.alauwahios.front.Constants;
import cn.alauwahios.front.dao.impl.DBOperate;
import cn.alauwahios.front.vo.BaiduTiebaVO;
import cn.alauwahios.front.vo.BaiduTieziVO;
import cn.alauwahios.front.vo.BaiduWangpanVO;
import cn.alauwahios.front.vo.BaiduYunVO;

/**
 * 操作数据库DAO层
 * 
 * @author:sekift
 * @time:2016-7-26 下午04:15:30
 */
@Repository("alauwahiosDao")
public class AlauwahiosDao {
	private static Logger logger = LoggerFactory.getLogger(AlauwahiosDao.class);

	/**
	 * 网盘群
	 * @param vo
	 * @return
	 */
	public static boolean saveBaiduWangpan(BaiduWangpanVO vo) {
		boolean result = false;
		if (null == vo) {
			return result;
		}
		String sql = "INSERT INTO baidu_wangpan(panShortLink,panLink,replyName,replyLink,tiebaName,"
				+ " tiebaLink,shortLink,postTime,createTime,updateTime,type,status,star,sort,hot,visits,remark)"
				+ " VALUES(?,?,?,?,?,?,?,?,now(),now(),?,1,0,0,1,0,?) ON DUPLICATE KEY UPDATE updateTime=now(),hot=hot+1";
		List<Object> params = new ArrayList<Object>();
		params.add(vo.getPanShortLink());
		params.add(vo.getPanLink());
		params.add(vo.getReplyName());
		params.add(vo.getReplyLink());
		params.add(vo.getTiebaName());
		params.add(vo.getTiebaLink());
		params.add(vo.getShortLink());
		params.add(vo.getPostTime());
		params.add(vo.getType());
		params.add(vo.getRemark());
		
		try {
			result = DBOperate.update(Constants.ALIAS_MASTER, sql, params.toArray()) > 0;
		} catch (Exception e) {
			result = false;
			logger.error("[贴吧链接]插入数据出错", e);
		}
		return result;
	}
	
	/**
	 * 贴吧信息
	 * @param vo
	 * @return
	 */
	public static boolean saveBaiduTieba(BaiduTiebaVO vo) {
		boolean result = false;
		if (null == vo) {
			return result;
		}
		String sql = "INSERT INTO baidu_tieba(tiebaKw,tiebaName,"
				+ " tiebaLink,shortLink,createTime,updateTime,type,status,star,sort,hot,visits,remark)"
				+ " VALUES(?,?,?,?,now(),now(),?,1,0,0,1,0,?) ON DUPLICATE KEY UPDATE updateTime=now(),hot=hot+1";
		List<Object> params = new ArrayList<Object>();
		params.add(vo.getTiebaKw());
		params.add(vo.getTiebaName());
		params.add(vo.getTiebaLink());
		params.add(vo.getShortLink());
		params.add(vo.getType());
		params.add(vo.getRemark());
		
		try {
			result = DBOperate.update(Constants.ALIAS_MASTER, sql, params.toArray()) > 0;
		} catch (Exception e) {
			result = false;
			logger.error("[贴吧链接]插入数据出错", e);
		}
		return result;
	}
	
	/**
	 * 帖子
	 * @param vo
	 * @return
	 */
	public static boolean saveBaiduTiezi(BaiduTieziVO vo) {
		boolean result = false;
		if (null == vo) {
			return result;
		}
		String sql = "INSERT INTO baidu_tiezi(tieziId,tieziName,tieziLink,tiebaName,"
				+ " tiebaLink,shortLink,postTime,createTime,updateTime,type,status,star,sort,hot,visits,remark)"
				+ " VALUES(?,?,?,?,?,?,?,now(),now(),?,1,0,0,1,0,?) ON DUPLICATE KEY UPDATE updateTime=now(),hot=hot+1";
		List<Object> params = new ArrayList<Object>();
		params.add(vo.getTieziId());
		params.add(vo.getTieziName());
		params.add(vo.getTieziLink());
		params.add(vo.getTiebaName());
		params.add(vo.getTiebaLink());
		params.add(vo.getShortLink());
		params.add(vo.getPostTime());
		params.add(vo.getType());
		params.add(vo.getRemark());
		
		try {
			result = DBOperate.update(Constants.ALIAS_MASTER, sql, params.toArray()) > 0;
		} catch (Exception e) {
			result = false;
			logger.error("[贴吧链接]插入数据出错", e);
		}
		return result;
	}
	
	/**
	 * baiduyun.xyz 过来的数据
	 * @param vo
	 * @return
	 */
	public static boolean saveBaiduYun(BaiduYunVO vo) {
		boolean result = false;
		if (null == vo) {
			return result;
		}
		String sql = "INSERT INTO baidu_yun(panShortLink,panLink,"
				+ " shortLink,createTime,updateTime,type,status,star,sort,hot,visits,remark)"
				+ " VALUES(?,?,?,now(),now(),?,1,0,0,1,0,?) ON DUPLICATE KEY UPDATE updateTime=now(),hot=hot+1";
		List<Object> params = new ArrayList<Object>();
		params.add(vo.getPanShortLink());
		params.add(vo.getPanLink());
		params.add(vo.getShortLink());
		params.add(vo.getType());
		params.add(vo.getRemark());
		
		try {
			result = DBOperate.update(Constants.ALIAS_MASTER, sql, params.toArray()) > 0;
		} catch (Exception e) {
			result = false;
			logger.error("[贴吧链接]插入数据出错", e);
		}
		return result;
	}
}
