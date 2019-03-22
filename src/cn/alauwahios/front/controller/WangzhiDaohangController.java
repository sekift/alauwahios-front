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
import cn.alauwahios.front.service.WangzhiDaohangService;
import cn.alauwahios.front.util.JsonReUtil;
import cn.alauwahios.front.validate.ControllerValidate;
import cn.alauwahios.front.vo.PageInfo;
import cn.alauwahios.front.vo.WangzhiDaohangVO;

@Controller("wangzhiDaohangController")
@RequestMapping("/daohang")
public class WangzhiDaohangController {
	@Autowired
	private WangzhiDaohangService wangzhiDaohangService;
	
	/**
	 * 刚打开
	 * @param request
	 * @param response
	 * @param pageInfo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String listWangzhiDaohang(HttpServletRequest request,HttpServletResponse response,
			PageInfo pageInfo, Model model) {
		model.addAttribute("keyword", "");
		List<WangzhiDaohangVO> result = wangzhiDaohangService.listWangzhiDaohang("", pageInfo, false);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("result", result);
		return "daohang";
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
			@RequestParam(value = "keyword", defaultValue = "", required = false) String keyword,
			PageInfo pageInfo,
			HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		model.addAttribute("keyword", keyword);
		List<WangzhiDaohangVO> result = wangzhiDaohangService.listWangzhiDaohang(keyword, pageInfo, false);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("result", result);
		return "daohang";
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
		boolean flag = wangzhiDaohangService.saveStar(id);
		if(flag) {
			 return sb.append(JsonReUtil.getSuccessResponse(CodeAndMessage.STAR_SUCCESS.getCode(),
					 CodeAndMessage.STAR_SUCCESS.getMessage())).toString();
		}
		return sb.append(JsonReUtil.getFailedResponse(CodeAndMessage.STAR_FAIL.getCode(),
				 CodeAndMessage.STAR_FAIL.getMessage())).toString();
	}
	
	@RequestMapping(value = "/saveSort", method = RequestMethod.GET)
	@ResponseBody
	public String saveSort(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="id",defaultValue="0",required = false) int id,
			@RequestParam(value="var",defaultValue="",required = false) String var,
			Model model) {
		StringBuffer sb = new StringBuffer();
		
		ControllerValidate.useVar(sb, var);
		
		if(id < 1) {
			return sb.append(JsonReUtil.getFailedResponse(CodeAndMessage.PARAMETER_ERROR.getCode(),
					CodeAndMessage.PARAMETER_ERROR.getMessage())).toString();
		}
		boolean flag = wangzhiDaohangService.saveSort(id);
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
		boolean flag = wangzhiDaohangService.cancelSort(id);
		if(flag) {
			return sb.append(JsonReUtil.getSuccessResponse(CodeAndMessage.SORT_CANCEL_SUCCESS.getCode(),
					CodeAndMessage.SORT_CANCEL_SUCCESS.getMessage())).toString();
		}
		return sb.append(JsonReUtil.getFailedResponse(CodeAndMessage.SORT_CANCEL_FAIL.getCode(),
				CodeAndMessage.SORT_CANCEL_FAIL.getMessage())).toString();
	}
}
