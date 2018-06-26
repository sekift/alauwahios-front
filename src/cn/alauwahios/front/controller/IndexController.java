package cn.alauwahios.front.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.alauwahios.front.service.BaiduTiebaService;
import cn.alauwahios.front.service.BaiduTieziService;
import cn.alauwahios.front.service.BaiduWangpanService;
import cn.alauwahios.front.service.BaiduYunService;
import cn.alauwahios.front.vo.BaiduTiebaVO;
import cn.alauwahios.front.vo.BaiduTieziVO;
import cn.alauwahios.front.vo.BaiduWangpanVO;
import cn.alauwahios.front.vo.BaiduYunVO;
import cn.alauwahios.front.vo.PageInfo;

@Controller("indexController")
@RequestMapping("")
public class IndexController {
	@Autowired
	private BaiduTiebaService baiduTiebaService;

	@Autowired
	private BaiduTieziService baiduTieziService;

	@Autowired
	private BaiduWangpanService baiduWangpanService;

	@Autowired
	private BaiduYunService baiduYunService;

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
		List<BaiduTiebaVO> tieba = baiduTiebaService.listBaiduTieba(pageInfo);
		model.addAttribute("tieba", tieba);
		List<BaiduTieziVO> tiezi = baiduTieziService.listBaiduTiezi(pageInfo);
		model.addAttribute("tiezi", tiezi);
		List<BaiduWangpanVO> wangpan = baiduWangpanService.listBaiduWangpan(pageInfo);
		model.addAttribute("wangpan", wangpan);
		List<BaiduYunVO> yun = baiduYunService.listBaiduYun(pageInfo);
		model.addAttribute("yun", yun);
		List<BaiduYunVO> make= baiduYunService.listBaiduYunMake(pageInfo);
		model.addAttribute("make", make);

		return "index";
	}
}
