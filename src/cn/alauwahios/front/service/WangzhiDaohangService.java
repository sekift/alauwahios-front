package cn.alauwahios.front.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.alauwahios.front.Constants;
import cn.alauwahios.front.dao.WangzhiDaohangDao;
import cn.alauwahios.front.redis.AlauwahiosRedis;
import cn.alauwahios.front.vo.WangzhiDaohangVO;
import cn.alauwahios.front.vo.PageInfo;

@Service("wangzhiDaohangService")
public class WangzhiDaohangService {

	@Autowired
	private WangzhiDaohangDao wangzhiDaohangDao;

	public boolean saveStar(int id) {
		// TODO 添加防刷机制
		return wangzhiDaohangDao.saveStar(id);
	}

	public boolean saveVisits(int id) {
		// TODO 添加防刷机制
		return wangzhiDaohangDao.saveVisits(id);
	}
	
	public boolean saveSort(int id) {
		// TODO 添加防刷机制
		return wangzhiDaohangDao.saveSort(id);
	}
	
	public boolean cancelSort(int id) {
		// TODO 添加防刷机制
		return wangzhiDaohangDao.cancelSort(id);
	}

	public List<WangzhiDaohangVO> listWangzhiDaohang(String keyword, PageInfo pageInfo, boolean useCache) {
		// TODO 添加防刷机制
		// 添加缓存
		List<WangzhiDaohangVO> list = null;
		if(useCache){
			list = AlauwahiosRedis.getInstance().getWangzhiDaohang(Constants.CACHE_WANGZHIDAOHANG_KEY);
		}
		if(null == list) {
			list = wangzhiDaohangDao.listWangzhiDaohang(keyword, pageInfo);
			if(null != list){
			    AlauwahiosRedis.getInstance().setWangzhiDaohang(Constants.CACHE_WANGZHIDAOHANG_KEY, 
			    		list, Constants.CACHE_WANGZHIDAOHANG_TIME);
			}
		}
		return list;
	}
	
	public boolean saveWangzhiDaohang(WangzhiDaohangVO vo) {
		return wangzhiDaohangDao.saveWangzhiDaohang(vo);
	}
}
