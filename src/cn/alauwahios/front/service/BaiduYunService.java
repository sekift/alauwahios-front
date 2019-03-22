package cn.alauwahios.front.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.alauwahios.front.Constants;
import cn.alauwahios.front.dao.BaiduYunDao;
import cn.alauwahios.front.redis.AlauwahiosRedis;
import cn.alauwahios.front.util.BaiduLinkUtil;
import cn.alauwahios.front.util.StringUtil;
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
		return true;
		//return baiduYunDao.saveSort(id);
	}
	
	public boolean cancelSort(int id) {
		// TODO 添加防刷机制
		return true;
		//return baiduYunDao.cancelSort(id);
	}

	public List<BaiduYunVO> listBaiduYun(PageInfo pageInfo, boolean useCache) {
		// TODO 添加防刷机制
		// 添加缓存
		List<BaiduYunVO> list = null;
		if(useCache){
			list = AlauwahiosRedis.getInstance().getBaiduYun(Constants.CACHE_BAIDUYUN_KEY);
		}
		if(null == list) {
			list = baiduYunDao.listBaiduYun(pageInfo);
			if(null != list){
			    AlauwahiosRedis.getInstance().setBaiduYun(Constants.CACHE_BAIDUYUN_KEY, 
			    		list, Constants.CACHE_BAIDUYUN_TIME);
			}
		}
		return list;
	}
	
	public boolean saveBaiduYunMake(BaiduYunVO vo){
		// 防刷机制:同一个IP 1分钟一个
		if (!StringUtil.isNullOrBlank(vo.getAddIp())) {
			if (baiduYunDao.limitIpAndPort(vo.getAddIp())) {
				return true;
			}
		}
		
		// 链接校验
		boolean flag = BaiduLinkUtil.panLink(vo.getPanShortLink());
		if(!flag){
			return true;
		}
		
		boolean result = baiduYunDao.saveBaiduYunMake(vo);
		if(result) {
		    AlauwahiosRedis.getInstance().delPan(Constants.CACHE_BAIDUYUNMAKE_KEY);
		}
		return result;
	}
	
	public List<BaiduYunVO> listBaiduYunMake(PageInfo pageInfo, boolean useCache) {
		// TODO 添加防刷机制
		// 添加缓存
		List<BaiduYunVO> list = null;
		if(useCache){
			list = AlauwahiosRedis.getInstance().getBaiduYun(Constants.CACHE_BAIDUYUNMAKE_KEY);
		}
		if(null == list) {
			list = baiduYunDao.listBaiduYunMake(pageInfo);
			if(null != list){
			    AlauwahiosRedis.getInstance().setBaiduYun(Constants.CACHE_BAIDUYUNMAKE_KEY, 
			    		list, Constants.CACHE_BAIDUYUNMAKE_TIME);
			}
		}
		return list;
	}
}
