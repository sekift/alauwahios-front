package cn.alauwahios.front.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.alauwahios.front.Constants;
import cn.alauwahios.front.dao.FxZiyuanDao;
import cn.alauwahios.front.redis.AlauwahiosRedis;
import cn.alauwahios.front.util.UrlUtil;
import cn.alauwahios.front.vo.FxZiyuanVO;
import cn.alauwahios.front.vo.PageInfo;

@Service("fxZiyuanService")
public class FxZiyuanService {

	@Autowired
	private FxZiyuanDao fxZiyuanDao;

	public boolean saveStar(int id) {
		// TODO 添加防刷机制
		return fxZiyuanDao.saveStar(id);
	}

	public boolean saveVisits(int id) {
		// TODO 添加防刷机制
		return fxZiyuanDao.saveVisits(id);
	}
	
	public boolean saveSort(int id) {
		// TODO 添加防刷机制
		return fxZiyuanDao.saveSort(id);
	}
	
	public boolean cancelSort(int id) {
		// TODO 添加防刷机制
		return fxZiyuanDao.cancelSort(id);
	}

	public List<FxZiyuanVO> listFxZiyuan(String keyword, PageInfo pageInfo, boolean useCache) {
		// TODO 添加防刷机制
		// 添加缓存
		List<FxZiyuanVO> list = null;
		if(useCache){
			list = AlauwahiosRedis.getInstance().getFxZiyuan(Constants.CACHE_FXZIYUAN_KEY);
		}
		if(null == list) {
			list = fxZiyuanDao.listFxZiyuan(keyword, pageInfo);
			if(null != list){
			    AlauwahiosRedis.getInstance().setFxZiyuan(Constants.CACHE_FXZIYUAN_KEY, 
			    		list, Constants.CACHE_FXZIYUAN_TIME);
			}
		}
		return list;
	}
	
	public int getIdByFxKw(String gogogo){
		String urlDecode = null;
		try {
			urlDecode = URLDecoder.decode(gogogo, "gbk");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String fxKw = UrlUtil.getUrlParamterValue(urlDecode, "kw");
		try {
			fxKw = URLEncoder.encode(fxKw, "gbk");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return fxZiyuanDao.getIdByFxKw(fxKw);
	}
	
	public boolean saveBaiduTieba(FxZiyuanVO vo) {
		return fxZiyuanDao.saveFxZiyuan(vo);
	}
}
