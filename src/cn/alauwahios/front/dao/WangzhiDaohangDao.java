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
import cn.alauwahios.front.vo.WangzhiDaohangVO;
import cn.alauwahios.front.vo.PageInfo;

@Repository("wangzhiDaohangDao")
public class WangzhiDaohangDao {
	private static final Logger logger = LoggerFactory.getLogger(WangzhiDaohangDao.class);

	// 点赞
	public boolean saveStar(int id) {
		String sql = "UPDATE wangzhi_daohang SET star=star+1 WHERE id=?";
		return DBOperate.updateQuietly(Constants.ALIAS_MASTER, sql, id) > 0;
	}

	// 阅读
	public boolean saveVisits(int id) {
		String sql = "UPDATE wangzhi_daohang SET visits=visits+1 WHERE id=?";
		return DBOperate.updateQuietly(Constants.ALIAS_MASTER, sql, id) > 0;
	}
	
	// 置顶
	public boolean saveSort(int id) {
		String sql = "UPDATE wangzhi_daohang SET sort=sort+1 WHERE id=?";
		return DBOperate.updateQuietly(Constants.ALIAS_MASTER, sql, id) > 0;
	}
	
	// 置顶
	public boolean cancelSort(int id) {
		String sql = "UPDATE wangzhi_daohang SET sort=1 WHERE id=?";
		return DBOperate.updateQuietly(Constants.ALIAS_MASTER, sql, id) > 0;
	}
	
	/**
	 * 人工插入的数据
	 * @param vo
	 * @return
	 */
	public boolean saveWangzhiDaohang(WangzhiDaohangVO vo) {
		boolean result = false;
		if (null == vo) {
			return result;
		}
		String sql = "INSERT INTO wangzhi_daohang(fxName,fxLink,"
				+ " shortLink,createTime,updateTime,type,status,star,sort,hot,visits,remark)"
				+ " VALUES(?,?,?,now(),now(),100301,1,0,1,1,0,?) "
				+ " ON DUPLICATE KEY UPDATE updateTime=now()";
		List<Object> params = new ArrayList<Object>();
		params.add(vo.getFxName());
		params.add(vo.getFxLink());
		params.add(vo.getShortLink());
		params.add(vo.getRemark());
		
		try {
			result = DBOperate.update(Constants.ALIAS_MASTER, sql, params.toArray()) > 0;
		} catch (Exception e) {
			result = false;
			logger.error("[导航链接]手工插入数据出错", e);
		}
		return result;
	}

	/**
	 * 前端展示查询
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<WangzhiDaohangVO> listWangzhiDaohang(String keyword, PageInfo pageInfo) {
		String sql = "SELECT * FROM wangzhi_daohang "
				+ " WHERE status = 1 ORDER BY sort DESC, updateTime DESC, hot ASC";
		List<WangzhiDaohangVO> list = new ArrayList<WangzhiDaohangVO>();
		try {
			list = (List<WangzhiDaohangVO>) Data2PageUtil.queryQuietly(Constants.ALIAS_SLAVE, pageInfo, sql,
					new BeanListHandler(WangzhiDaohangVO.class));
		} catch (Exception e) {
			logger.debug("分页查找网址导航失败!", e);
		}
		return list;
	}

}
