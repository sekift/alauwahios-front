package cn.alauwahios.front.asyn;

import java.util.concurrent.ExecutorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.alauwahios.front.service.ForwardService;

@Service("statisticsAsyn")
public class StatisticsAsyn {
	private static final int corePoolSize = 2;
	private static final int maximumPoolSize = 10;
	private static final int keepAliveTime = 5 * 60;
	private static final String poolName = "statistics_asyn";
	
	@Autowired
	private ForwardService forwardService;
	
	private static ExecutorService threadpool = ThreadPoolsService.newExecutorService(
			corePoolSize, maximumPoolSize, keepAliveTime, poolName);

	public void setAsynQueue(final int id, final String type, final String gogogo) {
		threadpool.execute(new Runnable() {
			public void run() {
				forwardService.saveVisits(id, type, gogogo);
			}
		});
	}

}
