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
import cn.alauwahios.front.util.StringUtil;
import cn.alauwahios.front.vo.FxZiyuanVO;
import cn.alauwahios.front.vo.PageInfo;


/**
 * 百度云资源操作DAO
 * @author sekift
 *
 */
@Repository("fxZiyuanDao")
public class FxZiyuanDao {
	private static final Logger logger = LoggerFactory.getLogger(FxZiyuanDao.class);
	
	// 点赞
	public boolean saveStar(int id) {
		String sql = "UPDATE fx_ziyuan SET star=star+1 WHERE id=?";
		return DBOperate.updateQuietly(Constants.ALIAS_MASTER, sql, id) > 0;
	}

	// 阅读
	public boolean saveVisits(int id) {
		String sql = "UPDATE fx_ziyuan SET visits=visits+1 WHERE id=?";
		return DBOperate.updateQuietly(Constants.ALIAS_MASTER, sql, id) > 0;
	}
	
	// 置顶
	public boolean saveSort(int id) {
		String sql = "UPDATE fx_ziyuan SET sort=sort+1 WHERE id=?";
		return DBOperate.updateQuietly(Constants.ALIAS_MASTER, sql, id) > 0;
	}
	
	// 取消置顶
	public boolean cancelSort(int id) {
		String sql = "UPDATE fx_ziyuan SET sort=1 WHERE id=?";
		return DBOperate.updateQuietly(Constants.ALIAS_MASTER, sql, id) > 0;
	}
	
	// 前端展示查询
	/**
	 * 规则：先按sort、再按postTime、再按visits排序
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<FxZiyuanVO> listFxZiyuan(String keyword, PageInfo pageInfo) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append("SELECT * FROM fx_ziyuan WHERE status=1");
		if(!StringUtil.isNullOrBlank(keyword)){
			sql.append(" AND fxName like ?");
			params.add("%"+keyword+"%");
		}
		sql.append(" ORDER BY sort DESC,postTime DESC, visits DESC");
		List<FxZiyuanVO> list = new ArrayList<FxZiyuanVO>();
		try {
			list = (List<FxZiyuanVO>) Data2PageUtil.queryQuietly(Constants.ALIAS_SLAVE, pageInfo, sql.toString(),
					new BeanListHandler(FxZiyuanVO.class), params.toArray());
		} catch (Exception e) {
			logger.debug("分页查找百度云资源失败!", e);
		}
		return list;
	}
	
	//更加fxKw获取id
	public int getIdByFxKw(String fxKw){
		String sql = "SELECT id FROM fx_ziyuan WHERE fxKw=? LIMIT 1";
		Integer id = (Integer) DBOperate.query4ObjectQuietly(Constants.ALIAS_SLAVE, sql, fxKw);
		if(null == id){
			id = 0;
		}
		return id;
	}
	
	/**
	 * 保存资源信息
	 * @param vo
	 * @return
	 */
	public boolean saveFxZiyuan(FxZiyuanVO vo) {
		boolean result = false;
		if (null == vo) {
			return result;
		}
		String sql = "INSERT INTO fx_ziyuan(fxKW,fxName,"
				+ " fxLink,shortLink,postTime,createTime,updateTime,type,status,star,sort,hot,visits,remark)"
				+ " VALUES(?,?,?,?,?,now(),now(),?,1,0,1,1,0,?) ON DUPLICATE KEY UPDATE updateTime=now(),hot=hot+1";
		List<Object> params = new ArrayList<Object>();
		params.add(vo.getFxKW());
		params.add(vo.getFxName());
		params.add(vo.getFxLink());
		params.add(vo.getShortLink());
		params.add(vo.getPostTime());
		params.add(vo.getType());
		params.add(vo.getRemark());
		
		try {
			result = DBOperate.update(Constants.ALIAS_MASTER, sql, params.toArray()) > 0;
		} catch (Exception e) {
			result = false;
			logger.error("[百度云资源]插入数据出错", e);
		}
		return result;
	}
	
}
