package cn.alauwahios.front.dao;

import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import cn.alauwahios.front.Constants;
import cn.alauwahios.front.dao.impl.DBOperate;
import cn.alauwahios.front.util.Data2PageUtil;
import cn.alauwahios.front.vo.BaiduTieziVO;
import cn.alauwahios.front.vo.PageInfo;

@Repository("baiduTieziDao")
public class BaiduTieziDao {
	private static final Logger logger = LoggerFactory.getLogger(BaiduTieziDao.class);
	
	// 点赞
	public boolean saveStar(int id) {
		String sql = "UPDATE baidu_tiezi SET star=star+1 WHERE id=?";
		return DBOperate.updateQuietly(Constants.ALIAS_MASTER, sql, id)>0;
	}
	
	// 阅读
	public boolean saveVisits(int id) {
		String sql = "UPDATE baidu_tiezi SET visits=visits+1 WHERE id=?";
		return DBOperate.updateQuietly(Constants.ALIAS_MASTER, sql, id)>0;
	}
	
	// 置顶
	public boolean saveSort(int id) {
		String sql = "UPDATE baidu_tiezi SET sort=sort+1 WHERE id=?";
		return DBOperate.updateQuietly(Constants.ALIAS_MASTER, sql, id) > 0;
	}
	
	// 置顶
	public boolean cancelSort(int id) {
		String sql = "UPDATE baidu_tiezi SET sort=1 WHERE id=?";
		return DBOperate.updateQuietly(Constants.ALIAS_MASTER, sql, id) > 0;
	}
	
	// 前端展示查询
	/**
	 * 规则：先按sort、再按visits、再按updateTime排序
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<BaiduTieziVO> listBaiduTiezi(PageInfo pageInfo) {
		String sql = "SELECT * FROM baidu_tiezi WHERE status= 1 ORDER BY sort DESC, visits DESC, updateTime DESC";
		List<BaiduTieziVO> list = null;
		try {
			list = (List<BaiduTieziVO>) Data2PageUtil.queryQuietly(Constants.ALIAS_SLAVE, pageInfo, sql, new BeanListHandler(BaiduTieziVO.class));
		} catch (Exception e) {
			logger.debug("分页查找贴子失败!", e);
		}
		return list;
	}
	
}
