package cn.alauwahios.front.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.alauwahios.front.dao.BaiduYunDao;
import cn.alauwahios.front.vo.BaiduYunVO;
import cn.alauwahios.front.vo.PageInfo;

@Service("baiduYunService")
public class BaiduYunService {

	@Autowired
	private BaiduYunDao baiduYunDao;

	public boolean saveStar(int id) {
		// TODO 添加防刷机制
		return baiduYunDao.saveStar(id);
	}

	public boolean saveVisits(int id) {
		// TODO 添加防刷机制
		return baiduYunDao.saveVisits(id);
	}
	
	public boolean saveSort(int id) {
		// TODO 添加防刷机制
		return baiduYunDao.saveSort(id);
	}
	
	public boolean cancelSort(int id) {
		// TODO 添加防刷机制
		return baiduYunDao.cancelSort(id);
	}

	public List<BaiduYunVO> listBaiduYun(PageInfo pageInfo) {
		// TODO 添加防刷机制
		return baiduYunDao.listBaiduYun(pageInfo);
	}
	
	public boolean saveBaiduYunMake(BaiduYunVO vo){
		// TODO 添加防刷机制
		return baiduYunDao.saveBaiduYunMake(vo);
	}
	
	public List<BaiduYunVO> listBaiduYunMake(PageInfo pageInfo) {
		// TODO 添加防刷机制
		return baiduYunDao.listBaiduYunMake(pageInfo);
	}
}
