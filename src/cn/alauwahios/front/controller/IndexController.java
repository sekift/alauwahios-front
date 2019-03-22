package cn.alauwahios.front.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.alauwahios.front.redis.RateLimitService;
import cn.alauwahios.front.service.BaiduTiebaService;
import cn.alauwahios.front.service.BaiduTieziService;
import cn.alauwahios.front.service.BaiduYunService;
import cn.alauwahios.front.service.FxZiyuanService;
import cn.alauwahios.front.service.InternetCelebrityService;
import cn.alauwahios.front.service.WangzhiDaohangService;
import cn.alauwahios.front.util.IPUtil;
import cn.alauwahios.front.vo.BaiduTiebaVO;
import cn.alauwahios.front.vo.BaiduTieziVO;
import cn.alauwahios.front.vo.BaiduYunVO;
import cn.alauwahios.front.vo.FxZiyuanVO;
import cn.alauwahios.front.vo.InternetCelebrityVO;
import cn.alauwahios.front.vo.PageInfo;
import cn.alauwahios.front.vo.WangzhiDaohangVO;

@Controller("indexController")
@RequestMapping("")
public class IndexController {
	@Autowired
	private BaiduTiebaService baiduTiebaService;

	@Autowired
	private BaiduTieziService baiduTieziService;

	//@Autowired
	//private BaiduWangpanService baiduWangpanService;
	
	@Autowired
	private WangzhiDaohangService wangzhiDaohangService;
	
	@Autowired
	private BaiduYunService baiduYunService;
	
	@Autowired
	private InternetCelebrityService internetCelebrityService;

	@Autowired
	private FxZiyuanService fxZiyuanService;
	
	//访问IP请求次数限制3秒1次
	private static final RateLimitService RLS_VISIT_IP = RateLimitService.getInstance("USER_VISIT_INDEX_IP").addLimit(3, 1).addLimit(60, 30);

	/**
	 * 刚打开
	 * 
	 * @param request
	 * @param response
	 * @param pageInfo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"/","/index"}, method = RequestMethod.GET)
	public String listBaiduTieba(HttpServletRequest request, HttpServletResponse response, PageInfo pageInfo,
			Model model) {
		
		//访问IP请求次数限制统计
		if(!RLS_VISIT_IP.check(IPUtil.getUserIP(request))){
			return "index";
		}

		List<BaiduYunVO> make= baiduYunService.listBaiduYunMake(pageInfo, true);
		model.addAttribute("make", make);
		List<BaiduYunVO> yun = baiduYunService.listBaiduYun(pageInfo, true);
		model.addAttribute("yun", yun);
		//List<BaiduWangpanVO> wangpan = baiduWangpanService.listBaiduWangpan(pageInfo, true);
		//model.addAttribute("wangpan", wangpan);
		
		List<WangzhiDaohangVO> wangzhiDaohang = wangzhiDaohangService.listWangzhiDaohang("", pageInfo, true);
		model.addAttribute("wangzhiDaohang", wangzhiDaohang);
		List<FxZiyuanVO> ziyuan = fxZiyuanService.listFxZiyuan("", pageInfo, true);
		model.addAttribute("ziyuan", ziyuan);
		List<BaiduTiebaVO> tieba = baiduTiebaService.listBaiduTieba(pageInfo, true);
		model.addAttribute("tieba", tieba);
		List<BaiduTieziVO> tiezi = baiduTieziService.listBaiduTiezi(pageInfo, true);
		model.addAttribute("tiezi", tiezi);
		List<InternetCelebrityVO> star = internetCelebrityService.listInternetCelebrity(pageInfo, true);
		model.addAttribute("star", star);

		return "index";
	}
}
