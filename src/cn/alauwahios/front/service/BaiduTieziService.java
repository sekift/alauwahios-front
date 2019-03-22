package cn.alauwahios.front.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.alauwahios.front.Constants;
import cn.alauwahios.front.dao.BaiduTieziDao;
import cn.alauwahios.front.redis.AlauwahiosRedis;
import cn.alauwahios.front.vo.BaiduTieziVO;
import cn.alauwahios.front.vo.PageInfo;

@Service("baiduTieziService")
public class BaiduTieziService {

	@Autowired
	private BaiduTieziDao baiduTieziDao;

	public boolean saveStar(int id) {
		// TODO 添加防刷机制
		return baiduTieziDao.saveStar(id);
	}

	public boolean saveVisits(int id) {
		// TODO 添加防刷机制
		return baiduTieziDao.saveVisits(id);
	}

	public boolean saveSort(int id) {
		// TODO 添加防刷机制
		return baiduTieziDao.saveSort(id);
	}
	
	public boolean cancelSort(int id) {
		// TODO 添加防刷机制
		return baiduTieziDao.cancelSort(id);
	}
	
	public boolean saveBaiduTiezi(BaiduTieziVO vo) {
		// TODO 添加防刷机制
		return baiduTieziDao.saveBaiduTiezi(vo);
	}
	
	public List<BaiduTieziVO> listBaiduTiezi(PageInfo pageInfo, boolean useCache) {
		// TODO 添加防刷机制
		// 添加缓存
		List<BaiduTieziVO> list = null;
		if(useCache){
			list = AlauwahiosRedis.getInstance().getBaiduTiezi(Constants.CACHE_BAIDUTIEZI_KEY);
		}
		if(null == list) {
			list = baiduTieziDao.listBaiduTiezi(pageInfo);
			if(null != list){
			    AlauwahiosRedis.getInstance().setBaiduTiezi(Constants.CACHE_BAIDUTIEZI_KEY, 
			    		list, Constants.CACHE_BAIDUTIEZI_TIME);
			}
		}
		return list;
	}

}
