package cn.alauwahios.front.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import cn.alauwahios.front.util.Data2PageUtil;

import cn.alauwahios.front.Constants;
import cn.alauwahios.front.dao.impl.DBOperate;
import cn.alauwahios.front.vo.BaiduTiebaVO;
import cn.alauwahios.front.vo.PageInfo;


/**
 * 百度贴吧操作
 * @author Administrator
 *
 */
@Repository("baiduTiebaDao")
public class BaiduTiebaDao {
	private static final Logger logger = LoggerFactory.getLogger(BaiduTiebaDao.class);
	
	// 点赞
	public boolean saveStar(int id) {
		String sql = "UPDATE baidu_tieba SET star=star+1 WHERE id=?";
		return DBOperate.updateQuietly(Constants.ALIAS_MASTER, sql, id) > 0;
	}

	// 阅读
	public boolean saveVisits(int id) {
		String sql = "UPDATE baidu_tieba SET visits=visits+1 WHERE id=?";
		return DBOperate.updateQuietly(Constants.ALIAS_MASTER, sql, id) > 0;
	}
	
	// 置顶
	public boolean saveSort(int id) {
		String sql = "UPDATE baidu_tieba SET sort=sort+1 WHERE id=?";
		return DBOperate.updateQuietly(Constants.ALIAS_MASTER, sql, id) > 0;
	}
	
	// 取消置顶
	public boolean cancelSort(int id) {
		String sql = "UPDATE baidu_tieba SET sort=1 WHERE id=?";
		return DBOperate.updateQuietly(Constants.ALIAS_MASTER, sql, id) > 0;
	}
	
	// 前端展示查询
	/**
	 * 规则：先按sort、再按hot、再按visits、再按updateTime排序
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<BaiduTiebaVO> listBaiduTieba(PageInfo pageInfo) {
		String sql = "SELECT * FROM baidu_tieba WHERE status= 1 ORDER BY sort DESC, hot DESC, visits DESC, updateTime DESC";
		List<BaiduTiebaVO> list = new ArrayList<BaiduTiebaVO>();
		try {
			list = (List<BaiduTiebaVO>) Data2PageUtil.queryQuietly(Constants.ALIAS_SLAVE, pageInfo, sql,
					new BeanListHandler(BaiduTiebaVO.class));
		} catch (Exception e) {
			logger.debug("分页查找贴吧失败!", e);
		}
		return list;
	}
	
	//更加tiebeKw获取id
	public int getIdByTiebaKw(String tiebaKw){
		String sql = "SELECT id FROM baidu_tieba WHERE tiebaKw=? LIMIT 1";
		Integer id = (Integer) DBOperate.query4ObjectQuietly(Constants.ALIAS_SLAVE, sql, tiebaKw);
		if(null == id){
			id = 0;
		}
		return id;
	}
	
	/**
	 * 贴吧信息
	 * @param vo
	 * @return
	 */
	public boolean saveBaiduTieba(BaiduTiebaVO vo) {
		boolean result = false;
		if (null == vo) {
			return result;
		}
		String sql = "INSERT INTO baidu_tieba(tiebaKw,tiebaName,"
				+ " tiebaLink,shortLink,createTime,updateTime,type,status,star,sort,hot,visits,remark)"
				+ " VALUES(?,?,?,?,now(),now(),?,1,0,1,1,0,?) ON DUPLICATE KEY UPDATE updateTime=now(),hot=hot+1";
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
	
}
