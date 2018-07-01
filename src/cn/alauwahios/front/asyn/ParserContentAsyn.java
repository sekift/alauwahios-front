package cn.alauwahios.front.asyn;

import java.util.concurrent.ExecutorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.alauwahios.front.service.ParserContentService;

@Service("parserContentAsyn")
public class ParserContentAsyn {
	private static final int corePoolSize = 2;
	private static final int maximumPoolSize = 10;
	private static final int keepAliveTime = 5 * 60;
	private static final String poolName = "parser_content_asyn";
	
	@Autowired
	private ParserContentService parserContentService;
	
	private static ExecutorService threadpool = ThreadPoolsService.newExecutorService(
			corePoolSize, maximumPoolSize, keepAliveTime, poolName);

	/**
	 * type 1 为帖子
	 *  2 xxx
	 * @param tieziUrl
	 * @param type
	 */
	public void setAsynQueue(final String tieziUrl, final int type) {
		threadpool.execute(new Runnable() {
			public void run() {
				if(type == 1) {
					parserContentService.parserTiezi(tieziUrl);
				}else {
					
				}
			}
		});
	}

}
