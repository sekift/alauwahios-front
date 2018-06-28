package cn.alauwahios.front.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.alauwahios.front.dao.InternetCelebrityDao;
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
	
	public List<InternetCelebrityVO> listInternetCelebrity(PageInfo pageInfo) {
		// TODO 添加防刷机制
		return internetCelebrityDao.listInternetCelebrity(pageInfo);
	}

}
