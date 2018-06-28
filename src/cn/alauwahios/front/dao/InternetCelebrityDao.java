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
import cn.alauwahios.front.vo.InternetCelebrityVO;
import cn.alauwahios.front.vo.PageInfo;

@Repository("internetCelebrityDao")
public class InternetCelebrityDao {
	private static final Logger logger = LoggerFactory.getLogger(InternetCelebrityDao.class);
	
	// 点赞
	public boolean saveStar(int id) {
		String sql = "UPDATE internet_celebrity SET star=star+1 WHERE id=?";
		return DBOperate.updateQuietly(Constants.ALIAS_MASTER, sql, id)>0;
	}
	
	// 阅读
	public boolean saveVisits(int id) {
		String sql = "UPDATE internet_celebrity SET visits=visits+1 WHERE id=?";
		return DBOperate.updateQuietly(Constants.ALIAS_MASTER, sql, id)>0;
	}
	
	// 置顶
	public boolean saveSort(int id) {
		String sql = "UPDATE internet_celebrity SET sort=sort+1 WHERE id=?";
		return DBOperate.updateQuietly(Constants.ALIAS_MASTER, sql, id) > 0;
	}
	
	// 置顶
	public boolean cancelSort(int id) {
		String sql = "UPDATE internet_celebrity SET sort=1 WHERE id=?";
		return DBOperate.updateQuietly(Constants.ALIAS_MASTER, sql, id) > 0;
	}
	
	// 前端展示查询
	/**
	 * 规则：先按sort、再按visits、再按updateTime排序
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<InternetCelebrityVO> listInternetCelebrity(PageInfo pageInfo) {
		String sql = "SELECT * FROM internet_celebrity WHERE status= 1 ORDER BY sort DESC, visits DESC, updateTime DESC";
		List<InternetCelebrityVO> list = null;
		try {
			list = (List<InternetCelebrityVO>) Data2PageUtil.queryQuietly(Constants.ALIAS_SLAVE, pageInfo, sql, new BeanListHandler(InternetCelebrityVO.class));
		} catch (Exception e) {
			logger.debug("分页查找网红失败!", e);
		}
		return list;
	}
	
	/**
	 * 人工插入的数据
	 * @param vo
	 * @return
	 */
	public boolean saveInternetCelebrity(InternetCelebrityVO vo) {
		boolean result = false;
		if (null == vo) {
			return result;
		}
		String sql = "INSERT INTO internet_celebrity(authorId,authorName,authorLink,"
				+ " shortLink,content,createTime,updateTime,type,status,star,sort,hot,visits,remark)"
				+ " VALUES(?,?,?,?,?,now(),now(),?,1,0,1,1,0,?) ON DUPLICATE KEY UPDATE updateTime=now(),hot=hot+1";
		List<Object> params = new ArrayList<Object>();
		params.add(vo.getAuthorId());
		params.add(vo.getAuthorName());
		params.add(vo.getAuthorLink());
		params.add(vo.getShortLink());
		params.add(vo.getContent());
		params.add(vo.getType());
		params.add(vo.getRemark());
		
		try {
			result = DBOperate.update(Constants.ALIAS_MASTER, sql, params.toArray()) > 0;
		} catch (Exception e) {
			result = false;
			logger.error("[网红链接]手工插入网红数据出错", e);
		}
		return result;
	}
	
}
