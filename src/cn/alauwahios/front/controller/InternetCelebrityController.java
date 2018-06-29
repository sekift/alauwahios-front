package cn.alauwahios.front.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.alauwahios.front.CodeAndMessage;
import cn.alauwahios.front.Constants;
import cn.alauwahios.front.service.InternetCelebrityService;
import cn.alauwahios.front.util.JsonReUtil;
import cn.alauwahios.front.util.UrlUtil;
import cn.alauwahios.front.validate.ControllerValidate;
import cn.alauwahios.front.vo.InternetCelebrityVO;
import cn.alauwahios.front.vo.PageInfo;

@Controller("internetCelebrityController")
@RequestMapping("/star")
public class InternetCelebrityController {
	@Autowired
	private InternetCelebrityService internetCelebrityService;
	
	/**
	 * 保存内容
	 * @param content
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String encodePost(@RequestParam(value = "content", defaultValue = "", required = true) String content,
			HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		PageInfo pageInfo = new PageInfo();
		if ("".equals(content.trim())) {
			model.addAttribute("error", "输入链接不能为空。");
			return listInternetCelebrity(request, response, pageInfo, model);
		}

		String panShortLink = "";
		if(content.startsWith(Constants.HTTP)){
			panShortLink = UrlUtil.getUrlParamterValue(content, "short");
		} else if(content.startsWith(Constants.SHORT_IDX)){
			panShortLink = content.replace(Constants.SHORT_IDX, "");
		} else if(content.startsWith(Constants.SHORT_IDX_UNMARK)){
			panShortLink = content.replace(Constants.SHORT_IDX_UNMARK, "");
		} else {
			panShortLink = content;
		}
		if(!panShortLink.matches("^[a-zA-Z0-9]{6,8}+$")){
			model.addAttribute("error", "请输入short=后面的6-8个字母数字。");
			return listInternetCelebrity(request, response, pageInfo, model);
		}
		InternetCelebrityVO vo = new InternetCelebrityVO();
		vo.setShortLink("");
		vo.setType(1);
		vo.setRemark("");
		boolean result = internetCelebrityService.saveInternetCelebrity(vo);

		if (!result) {
			model.addAttribute("error", "请输入short=后面的6-8个字母数字，并稍后再试。");
			return listInternetCelebrity(request, response, pageInfo, model);
		}
		model.addAttribute("error", "你的链接保存成功，可以去加链接啦！");
		return listInternetCelebrity(request, response, pageInfo, model);
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String listInternetCelebrity(HttpServletRequest request,HttpServletResponse response,
			PageInfo pageInfo, Model model) {
		List<InternetCelebrityVO> result = internetCelebrityService.listInternetCelebrity(pageInfo);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("result", result);
		return "star";
	}
	
	/**
	 * 搜索内容
	 * @param pageInfo
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String searchPost(
			PageInfo pageInfo,
			HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		List<InternetCelebrityVO> result = internetCelebrityService.listInternetCelebrity(pageInfo);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("result", result);
		return "star";
	}
	
	@RequestMapping(value = "/saveStar", method = RequestMethod.GET)
	@ResponseBody
	public String saveStar(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="id",defaultValue="0",required = false) int id,
			@RequestParam(value="var",defaultValue="",required = false) String var,
			Model model) {
		StringBuffer sb = new StringBuffer();
		
		ControllerValidate.useVar(sb, var);
		
		if(id < 1) {
			return sb.append(JsonReUtil.getFailedResponse(CodeAndMessage.PARAMETER_ERROR.getCode(),
					CodeAndMessage.PARAMETER_ERROR.getMessage())).toString();
		}
		// 点赞
		boolean flag = internetCelebrityService.saveStar(id);
		if(flag) {
			 return sb.append(JsonReUtil.getSuccessResponse(CodeAndMessage.STAR_SUCCESS.getCode(),
					 CodeAndMessage.STAR_SUCCESS.getMessage())).toString();
		}
		return sb.append(JsonReUtil.getFailedResponse(CodeAndMessage.STAR_FAIL.getCode(),
				 CodeAndMessage.STAR_FAIL.getMessage())).toString();
	}
	
	@RequestMapping(value = "/saveSort", method = RequestMethod.GET)
	@ResponseBody
	public String saveVisits(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="id",defaultValue="0",required = false) int id,
			@RequestParam(value="var",defaultValue="",required = false) String var,
			Model model) {
		StringBuffer sb = new StringBuffer();
		
		ControllerValidate.useVar(sb, var);
		
		if(id < 1) {
			return sb.append(JsonReUtil.getFailedResponse(CodeAndMessage.PARAMETER_ERROR.getCode(),
					CodeAndMessage.PARAMETER_ERROR.getMessage())).toString();
		}
		boolean flag = internetCelebrityService.saveSort(id);
		if(flag) {
			return sb.append(JsonReUtil.getSuccessResponse(CodeAndMessage.SORT_SUCCESS.getCode(),
					CodeAndMessage.SORT_SUCCESS.getMessage())).toString();
		}
		return sb.append(JsonReUtil.getFailedResponse(CodeAndMessage.SORT_FAIL.getCode(),
				CodeAndMessage.SORT_FAIL.getMessage())).toString();
	}
	
	@RequestMapping(value = "/cancelSort", method = RequestMethod.GET)
	@ResponseBody
	public String cancelSort(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="id",defaultValue="0",required = false) int id,
			@RequestParam(value="var",defaultValue="",required = false) String var,
			Model model) {
		StringBuffer sb = new StringBuffer();
		
		ControllerValidate.useVar(sb, var);
		
		if(id < 1) {
			return sb.append(JsonReUtil.getFailedResponse(CodeAndMessage.PARAMETER_ERROR.getCode(),
					CodeAndMessage.PARAMETER_ERROR.getMessage())).toString();
		}
		boolean flag = internetCelebrityService.cancelSort(id);
		if(flag) {
			return sb.append(JsonReUtil.getSuccessResponse(CodeAndMessage.SORT_CANCEL_SUCCESS.getCode(),
					CodeAndMessage.SORT_CANCEL_SUCCESS.getMessage())).toString();
		}
		return sb.append(JsonReUtil.getFailedResponse(CodeAndMessage.SORT_CANCEL_FAIL.getCode(),
				CodeAndMessage.SORT_CANCEL_FAIL.getMessage())).toString();
	}
}
