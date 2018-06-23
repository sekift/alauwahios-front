package cn.alauwahios.front.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.alauwahios.front.dao.BaiduWangpanDao;
import cn.alauwahios.front.vo.BaiduWangpanVO;
import cn.alauwahios.front.vo.PageInfo;

@Service("baiduWangpanService")
public class BaiduWangpanService {

	@Autowired
	private BaiduWangpanDao baiduWangpanDao;

	public boolean saveStar(int id) {
		// TODO 添加防刷机制
		return baiduWangpanDao.saveStar(id);
	}

	public boolean saveVisits(int id) {
		// TODO 添加防刷机制
		return baiduWangpanDao.saveVisits(id);
	}
	
	public boolean saveSort(int id) {
		// TODO 添加防刷机制
		return baiduWangpanDao.saveSort(id);
	}
	
	public boolean cancelSort(int id) {
		// TODO 添加防刷机制
		return baiduWangpanDao.cancelSort(id);
	}

	public List<BaiduWangpanVO> listBaiduWangpan(PageInfo pageInfo) {
		// TODO 添加防刷机制
		return baiduWangpanDao.listBaiduWangpan(pageInfo);
	}

}
