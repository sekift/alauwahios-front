package cn.alauwahios.front.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.alauwahios.front.Constants;

@Service("forwardService")
public class ForwardService {
	@Autowired
	private BaiduTiebaService baiduTiebaService;

	@Autowired
	private BaiduTieziService baiduTieziService;

	@Autowired
	private BaiduWangpanService baiduWangpanService;

	@Autowired
	private BaiduYunService baiduYunService;

	@Autowired
	private InternetCelebrityService internetCelebrityService;
	
	@Autowired
	private FxZiyuanService fxZiyuanService;
	
	public boolean saveVisits(int id, String type, String gogogo) {
		boolean flag = false;
		if (Constants.TIEBA.equals(type)) {
			flag = baiduTiebaService.saveVisits(id);
		}else if (Constants.WANGPAN.equals(type)) {
			flag = baiduWangpanService.saveVisits(id);
		} else if (Constants.WANGPAN_TIEBA.equals(type)
	         || Constants.TIEZI_TIEBA.equals(type)) {
			int tiebaId = baiduTiebaService.getIdByTiebaKw(gogogo);
			flag = baiduTiebaService.saveVisits(tiebaId);
		} else if (Constants.TIEZI.equals(type)) {
			flag = baiduTieziService.saveVisits(id);
		} else if (Constants.YUN.equals(type)) {
			flag = baiduYunService.saveVisits(id);
		} else if (Constants.INTERNET_CELEBRITY.equals(type)) {
			flag = internetCelebrityService.saveVisits(id);
		} else if(Constants.ZIYUAN.equals(type)){
			flag = fxZiyuanService.saveVisits(id);
		}
		return flag;
	}
}
