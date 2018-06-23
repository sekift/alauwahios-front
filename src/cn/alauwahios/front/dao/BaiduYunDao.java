package cn.alauwahios.front.dao;

import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import cn.alauwahios.front.Constants;
import cn.alauwahios.front.dao.impl.DBOperate;
import cn.alauwahios.front.util.Data2PageUtil;
import cn.alauwahios.front.vo.BaiduYunVO;
import cn.alauwahios.front.vo.PageInfo;

@Repository("baiduYunDao")
public class BaiduYunDao {
	private static final Logger logger = LoggerFactory.getLogger(BaiduYunDao.class);

	// 点赞
	public boolean saveStar(int id) {
		String sql = "UPDATE baidu_yun SET star=star+1 WHERE id=?";
		return DBOperate.updateQuietly(Constants.ALIAS_MASTER, sql, id) > 0;
	}

	// 阅读
	public boolean saveVisits(int id) {
		String sql = "UPDATE baidu_yun SET visits=visits+1 WHERE id=?";
		return DBOperate.updateQuietly(Constants.ALIAS_MASTER, sql, id) > 0;
	}
	
	// 置顶
	public boolean saveSort(int id) {
		String sql = "UPDATE baidu_yun SET sort=sort+1 WHERE id=?";
		return DBOperate.updateQuietly(Constants.ALIAS_MASTER, sql, id) > 0;
	}
	
	// 置顶
	public boolean cancelSort(int id) {
		String sql = "UPDATE baidu_yun SET sort=1 WHERE id=?";
		return DBOperate.updateQuietly(Constants.ALIAS_MASTER, sql, id) > 0;
	}

	// 前端展示查询
	/**
	 * 规则： 
	 * 1 postTime要在40分钟内，createTime与updateTime在20分钟内 
	 * 2 先按sort、再按postTime、再按updateTime排序、再按hot排序
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<BaiduYunVO> listBaiduYun(PageInfo pageInfo) {
		String sql = "SELECT * FROM baidu_yun "
				+ " WHERE updateTime > DATE_ADD(now(), INTERVAL -40 MINUTE) AND createTime > DATE_ADD(updateTime, INTERVAL -20 MINUTE)"
				+ " AND status= 1 ORDER BY sort DESC, updateTime DESC, hot ASC";
		List<BaiduYunVO> list = null;
		try {
			list = (List<BaiduYunVO>) Data2PageUtil.queryQuietly(Constants.ALIAS_SLAVE, pageInfo, sql,
					new BeanListHandler(BaiduYunVO.class));
		} catch (Exception e) {
			logger.debug("分页查找云失败!", e);
		}
		return list;
	}

}
