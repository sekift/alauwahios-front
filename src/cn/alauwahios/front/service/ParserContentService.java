package cn.alauwahios.front.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.alauwahios.front.Constants;
import cn.alauwahios.front.util.JsoupUtil;
import cn.alauwahios.front.util.UrlUtil;
import cn.alauwahios.front.vo.BaiduTiebaVO;
import cn.alauwahios.front.vo.BaiduTieziVO;
import cn.alauwahios.front.vo.InternetCelebrityVO;
/**
 * 解析帖子等内容
 * @author luyz
 *
 */
@Service("parserContentService")
public class ParserContentService {
	private static final Logger logger = LoggerFactory.getLogger(ParserContentService.class);

	@Autowired
	private BaiduTieziService baiduTieziService;

	@Autowired
	private BaiduTiebaService baiduTiebaService;
	
	@Autowired	
	private InternetCelebrityService internetCelebrityService;
	
	public void parserTiezi(String tieziUrl) {
		try {
			parserTieziQuietly(tieziUrl);
		}catch(Exception e) {
			logger.error("[解析帖子]解析帖子出错了，tiezi=" + tieziUrl, e);
		}
	}
	
	public void parserTieziQuietly(String tieziUrl) {
		//String tieziUrl = "http://tieba.baidu.com/p/5604796832";
		String tieziLink = tieziUrl.split("\\?")[0];
		Document doc = JsoupUtil.getDocByConnect(tieziLink);
		
		//System.out.println(result.body());
		Element ele = doc.head();
		String title = ele.getElementsByTag("title").html();
		Elements meta = ele.getElementsByTag("meta");
		//帖子ID
		String[] tieziStr = tieziLink.split("\\/");
		String tieziId = tieziStr[tieziStr.length - 1];
		//System.out.println(tieziId);
		
		//帖子文字
		String tieziName = title;//.substring(0,title.length() - 5)
		//System.out.println(tieziName);
		//帖子链接
		//System.out.println(tieziLink);
		
		String tiebaName = meta.attr("fname");
		String tiebaLink = "https://"+meta.attr("furl").replace("&ie=utf-8", "");
		//贴吧名称
		//System.out.println(tiebaName);
		//贴吧链接
		//System.out.println(tiebaLink);
		
		Element body = doc.body();
		Elements scriptEle = body.getElementsByClass("d_name");//.first().getElementsByTag("script").first()
		//作者名称
		String authorName = scriptEle.first().text();
		//System.out.println(authorName);
		
		//作者链接
		String authorLink = "https://tieba.baidu.com"+scriptEle.first().select("a").attr("href");
		authorLink = authorLink.replace("&ie=utf-8", "").replace("&fr=pb", "");
		//System.out.println(authorLink);
		
		saveBaiduTiezi(tieziId, tieziName, tieziLink, authorName, authorLink, tiebaName, tiebaLink);
		saveBaiduTieba(tiebaName, tiebaLink);
		saveTiebaInternetCelebrity(authorName, authorLink);
	}
	
	//帖子表
	public void saveBaiduTiezi(String tieziId, String tieziName, String tieziLink, String authorName
			, String authorLink, String tiebaName, String tiebaLink) {
		BaiduTieziVO vo = new BaiduTieziVO();
		vo.setTieziId(Long.valueOf(tieziId));
		vo.setTieziName(tieziName);
		vo.setTieziLink(tieziLink);
		vo.setAuthorName(authorName);
		vo.setAuthorLink(authorLink);
		vo.setTiebaName(tiebaName);
		try {
			String tiebaKw = UrlUtil.getUrlParamterValue(tiebaLink, "kw");
			String dec = URLDecoder.decode(tiebaKw, "utf-8");
			tiebaKw = URLEncoder.encode(dec, "gbk");
			tiebaLink = Constants.BAIDU_TIEBA_PATH + tiebaKw;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		vo.setTiebaLink(tiebaLink);
		vo.setShortLink(Constants.DEFAULT_SHORT_LINK);
		vo.setType(1);
		vo.setRemark(Constants.DEFAULT_REMARK);
		
		baiduTieziService.saveBaiduTiezi(vo);
	}
	
	//贴吧表
	public void saveBaiduTieba(String tiebaName, String tiebaLink) {
		String tiebaKw = UrlUtil.getUrlParamterValue(tiebaLink, "kw");
		BaiduTiebaVO btvo = new BaiduTiebaVO();
		try {
			String dec = URLDecoder.decode(tiebaKw, "utf-8");
			tiebaKw = URLEncoder.encode(dec, "gbk");
			tiebaLink = Constants.BAIDU_TIEBA_PATH + tiebaKw;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		btvo.setTiebaKw(tiebaKw);
		btvo.setTiebaName(tiebaName);
		btvo.setTiebaLink(tiebaLink);
		btvo.setShortLink(Constants.DEFAULT_SHORT_LINK);
		btvo.setType(Constants.DEFAULT_TYPE);
		btvo.setRemark(Constants.DEFAULT_REMARK);
		baiduTiebaService.saveBaiduTieba(btvo);
	}
	
	// 网红表
	public void saveTiebaInternetCelebrity(String authorName, String authorLink) {
		InternetCelebrityVO icVO = new InternetCelebrityVO();
		icVO.setAuthorId("");
		icVO.setAuthorName(authorName);
		icVO.setAuthorLink(authorLink);
		icVO.setShortLink(Constants.DEFAULT_SHORT_LINK);
		icVO.setContent("");
		icVO.setType(100101);
		icVO.setRemark(Constants.DEFAULT_REMARK);
		
		internetCelebrityService.saveInternetCelebrity(icVO);
	}

}
