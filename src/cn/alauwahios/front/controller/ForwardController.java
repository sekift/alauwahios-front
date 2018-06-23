package cn.alauwahios.front.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.alauwahios.front.Constants;
import cn.alauwahios.front.asyn.ThreadPoolsService;
import cn.alauwahios.front.util.StringUtil;

/**
 * 跳转专用页面+统计
 * 
 */
@Controller("forwardController")
public class ForwardController {
	
	@Autowired
	private ThreadPoolsService threadPoolsService;

	@RequestMapping(value = "/forward", method = RequestMethod.GET)
	public String showForward(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "id", defaultValue = "", required = false) int id,
			@RequestParam(value = "type", defaultValue = "", required = false) String type,
			@RequestParam(value = "name", defaultValue = "", required = false) String name,
			@RequestParam(value = "gogogo", defaultValue = "", required = false) String gogogo,
			Model model) {
		if (StringUtil.isNullOrBlank(gogogo)) {
			gogogo = Constants.BASE_DOMAIN;
		}
		
		// 异步统计
		threadPoolsService.setAsynQueue(id, type, gogogo);
		
		model.addAttribute("forwardURL", gogogo);
		model.addAttribute("name", name);
		return "gogogo";
	}
}
