package cn.alauwahios.front.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import cn.alauwahios.front.Constants;
import cn.alauwahios.front.dao.impl.DBOperate;
import cn.alauwahios.front.util.Data2PageUtil;
import cn.alauwahios.front.vo.BaiduWangpanVO;
import cn.alauwahios.front.vo.PageInfo;

@Repository("baiduWangpanDao")
public class BaiduWangpanDao {
	private static final Logger logger = LoggerFactory.getLogger(BaiduWangpanDao.class);

	// 点赞
	public boolean saveStar(int id) {
		String sql = "UPDATE baidu_wangpan SET star=star+1 WHERE id=?";
		return DBOperate.updateQuietly(Constants.ALIAS_MASTER, sql, id) > 0;
	}

	// 阅读
	public boolean saveVisits(int id) {
		String sql = "UPDATE baidu_wangpan SET visits=visits+1 WHERE id=?";
		return DBOperate.updateQuietly(Constants.ALIAS_MASTER, sql, id) > 0;
	}
	
	// 置顶
	public boolean saveSort(int id) {
		String sql = "UPDATE baidu_wangpan SET sort=sort+1 WHERE id=?";
		return DBOperate.updateQuietly(Constants.ALIAS_MASTER, sql, id) > 0;
	}
	
	// 置顶
	public boolean cancelSort(int id) {
		String sql = "UPDATE baidu_wangpan SET sort=1 WHERE id=?";
		return DBOperate.updateQuietly(Constants.ALIAS_MASTER, sql, id) > 0;
	}

	// 前端展示查询
	/**
	 * 规则： 
	 * 1 postTime要在6小时内，createTime与updateTime在4小时内 
	 * 2 先按sort、再按postTime、再按updateTime排序、再按hot排序
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<BaiduWangpanVO> listBaiduWangpan(PageInfo pageInfo) {
		String sql = "SELECT * FROM baidu_wangpan "
				+ " WHERE postTime > DATE_ADD(now(), INTERVAL -6 HOUR) AND createTime > DATE_ADD(updateTime, INTERVAL -4 HOUR)"
				+ " AND status= 1 ORDER BY sort DESC, postTime DESC, updateTime DESC, hot ASC";
		List<BaiduWangpanVO> list = new ArrayList<BaiduWangpanVO>();
		try {
			list = (List<BaiduWangpanVO>) Data2PageUtil.queryQuietly(Constants.ALIAS_SLAVE, pageInfo, sql,
					new BeanListHandler(BaiduWangpanVO.class));
		} catch (Exception e) {
			logger.debug("分页查找网盘失败!", e);
		}
		return list;
	}

}
