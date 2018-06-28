package cn.alauwahios.front.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.alauwahios.front.CodeAndMessage;
import cn.alauwahios.front.Constants;
import cn.alauwahios.front.service.BaiduTieziService;
import cn.alauwahios.front.service.InternetCelebrityService;
import cn.alauwahios.front.util.JsonReUtil;
import cn.alauwahios.front.util.JsoupUtil;
import cn.alauwahios.front.validate.ControllerValidate;
import cn.alauwahios.front.vo.BaiduTieziVO;
import cn.alauwahios.front.vo.InternetCelebrityVO;
import cn.alauwahios.front.vo.PageInfo;

@Controller("baiduTieziController")
@RequestMapping("/tiezi")
public class BaiduTieziController {
	@Autowired
	private BaiduTieziService baiduTieziService;
	
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
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String encodePost(@RequestParam(value = "content", defaultValue = "", required = true) String content,
			HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		PageInfo pageInfo = new PageInfo();
		if ("".equals(content.trim()) ||
				!content.trim().contains(Constants.BAIDU_TIEBA_COM)) {
			model.addAttribute("error", "链接输入错误。");
			return listBaiduTiezi(request, response, pageInfo, model);
		}

		//String tieziUrl = "http://tieba.baidu.com/p/5604796832";
		String tieziLink = content.split("\\?")[0];
		Document doc = JsoupUtil.getDocByConnect(tieziLink);
		if(null == doc){
			model.addAttribute("error", "解释错误，请稍后再试。");
			return listBaiduTiezi(request, response, pageInfo, model);
		}
		//System.out.println(result.body());
		Element ele = doc.head();
		String title = ele.getElementsByTag("title").html();
		Elements meta = ele.getElementsByTag("meta");
		//帖子ID
		String[] tieziStr = tieziLink.split("\\/");
		String tieziId = tieziStr[tieziStr.length - 1];
		System.out.println(tieziId);
		
		//帖子文字
		String tieziName = title.split("【")[0];
		System.out.println(tieziName);
		//帖子链接
		System.out.println(tieziLink);
		
		String tiebaName = meta.attr("fname");
		String tiebaLink = "https://"+meta.attr("furl").replace("&ie=utf-8", "");
		//贴吧名称
		System.out.println(tiebaName);
		//贴吧链接
		System.out.println(tiebaLink);
		
		Element body = doc.body();
		Elements scriptEle = body.getElementsByClass("d_name");//.first().getElementsByTag("script").first()
		//作者名称
		String authorName = scriptEle.first().text();
		System.out.println(authorName);
		
		//作者链接
		String authorLink = "https://tieba.baidu.com"+scriptEle.first().select("a").attr("href");
		authorLink = authorLink.replace("&ie=utf-8", "").replace("&fr=pb", "");
		System.out.println(authorLink);
		
		BaiduTieziVO vo = new BaiduTieziVO();
		vo.setTieziId(Long.valueOf(tieziId));
		vo.setTieziName(tieziName);
		vo.setTieziLink(tieziLink);
		vo.setAuthorName(authorName);
		vo.setAuthorLink(authorLink);
		vo.setTiebaName(tiebaName);
		vo.setTiebaLink(tiebaLink);
		vo.setShortLink("");
		vo.setType(1);
		vo.setRemark("");
		boolean result = baiduTieziService.saveBaiduTiezi(vo);
		
		InternetCelebrityVO icVO = new InternetCelebrityVO();
		icVO.setAuthorId("");
		icVO.setAuthorName(authorName);
		icVO.setAuthorLink(authorLink);
		icVO.setShortLink("");
		icVO.setContent("");
		icVO.setType("tieba");
		icVO.setRemark("");
		boolean icResult = internetCelebrityService.saveInternetCelebrity(icVO);
		
		if (!result || !icResult) {
			model.addAttribute("error", "请输入正确的链接，并稍后再试。");
			return listBaiduTiezi(request, response, pageInfo, model);
		}
		model.addAttribute("error", "你的链接保存成功，可以去加链接啦！");
		return listBaiduTiezi(request, response, pageInfo, model);
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String listBaiduTiezi(HttpServletRequest request,HttpServletResponse response,
			PageInfo pageInfo, Model model) {
		List<BaiduTieziVO> result = baiduTieziService.listBaiduTiezi(pageInfo);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("result", result);
		return "tiezi";
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
		List<BaiduTieziVO> result = baiduTieziService.listBaiduTiezi(pageInfo);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("result", result);
		return "tiezi";
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
		boolean flag = baiduTieziService.saveStar(id);
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
		boolean flag = baiduTieziService.saveSort(id);
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
		boolean flag = baiduTieziService.cancelSort(id);
		if(flag) {
			return sb.append(JsonReUtil.getSuccessResponse(CodeAndMessage.SORT_CANCEL_SUCCESS.getCode(),
					CodeAndMessage.SORT_CANCEL_SUCCESS.getMessage())).toString();
		}
		return sb.append(JsonReUtil.getFailedResponse(CodeAndMessage.SORT_CANCEL_FAIL.getCode(),
				CodeAndMessage.SORT_CANCEL_FAIL.getMessage())).toString();
	}

}
