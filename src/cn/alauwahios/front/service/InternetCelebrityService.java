package cn.alauwahios.front.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.alauwahios.front.Constants;
import cn.alauwahios.front.dao.InternetCelebrityDao;
import cn.alauwahios.front.redis.AlauwahiosRedis;
import cn.alauwahios.front.vo.InternetCelebrityVO;
import cn.alauwahios.front.vo.PageInfo;

@Service("internetCelebrityService")
public class InternetCelebrityService {

	@Autowired
	private InternetCelebrityDao internetCelebrityDao;

	public boolean saveStar(int id) {
		// TODO 添加防刷机制
		return internetCelebrityDao.saveStar(id);
	}

	public boolean saveVisits(int id) {
		// TODO 添加防刷机制
		return internetCelebrityDao.saveVisits(id);
	}

	public boolean saveSort(int id) {
		// TODO 添加防刷机制
		return internetCelebrityDao.saveSort(id);
	}
	
	public boolean cancelSort(int id) {
		// TODO 添加防刷机制
		return internetCelebrityDao.cancelSort(id);
	}
	
	public boolean saveInternetCelebrity(InternetCelebrityVO vo) {
		// TODO 添加防刷机制
		return internetCelebrityDao.saveInternetCelebrity(vo);
	}
	
	public List<InternetCelebrityVO> listInternetCelebrity(PageInfo pageInfo, boolean useCache) {
		// TODO 添加防刷机制
		// 添加缓存
		List<InternetCelebrityVO> list = null;
		if(useCache){
			list = AlauwahiosRedis.getInstance().getInternetCelebrity(Constants.CACHE_INTERNETCELEBRITY_KEY);
		}
		if(null == list) {
			list = internetCelebrityDao.listInternetCelebrity(pageInfo);
			if(null != list){
			    AlauwahiosRedis.getInstance().setInternetCelebrity(Constants.CACHE_INTERNETCELEBRITY_KEY, 
			    		list, Constants.CACHE_INTERNETCELEBRITY_TIME);
			}
		}
		return list;
	}
}
