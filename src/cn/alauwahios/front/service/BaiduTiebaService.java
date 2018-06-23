package cn.alauwahios.front.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.alauwahios.front.dao.BaiduTiebaDao;
import cn.alauwahios.front.util.UrlUtil;
import cn.alauwahios.front.vo.BaiduTiebaVO;
import cn.alauwahios.front.vo.PageInfo;

@Service("baiduTiebaService")
public class BaiduTiebaService {

	@Autowired
	private BaiduTiebaDao baiduTiebaDao;

	public boolean saveStar(int id) {
		// TODO 添加防刷机制
		return baiduTiebaDao.saveStar(id);
	}

	public boolean saveVisits(int id) {
		// TODO 添加防刷机制
		return baiduTiebaDao.saveVisits(id);
	}
	
	public boolean saveSort(int id) {
		// TODO 添加防刷机制
		return baiduTiebaDao.saveSort(id);
	}
	
	public boolean cancelSort(int id) {
		// TODO 添加防刷机制
		return baiduTiebaDao.cancelSort(id);
	}

	public List<BaiduTiebaVO> listBaiduTieba(PageInfo pageInfo) {
		// TODO 添加防刷机制
		return baiduTiebaDao.listBaiduTieba(pageInfo);
	}
	
	public int getIdByTiebaKw(String gogogo){
		String urlDecode = null;
		try {
			urlDecode = URLDecoder.decode(gogogo, "gbk");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String tiebaKw = UrlUtil.getUrlParamterValue(urlDecode, "kw");
		try {
			tiebaKw = URLEncoder.encode(tiebaKw, "gbk");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return baiduTiebaDao.getIdByTiebaKw(tiebaKw);
	}
}
