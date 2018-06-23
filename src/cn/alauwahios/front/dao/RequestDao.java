package cn.alauwahios.front.dao;

import org.springframework.stereotype.Repository;

import cn.alauwahios.front.Constants;
import cn.alauwahios.front.dao.impl.DBOperate;
import cn.alauwahios.front.monitor.web.RequestLogger;

/**
 * web请求入库DAO
 * @author Administrator
 *
 */
@Repository("requestDao")
public class RequestDao {
	
	// web请求入库
	public boolean saveRequestCnt(String serverIp, RequestLogger vo) {
		String sql = "INSERT INTO request_cnt(serverIp,urlPattern,totalRequestCnt,"
				+ "overtimeRequestCnt,matchedRequestCnt,overtimeMatchedRequestCnt,respTime,createTime)"
				+ " VALUES(?,?,?,?,?,?,?,now())";
		return DBOperate.updateQuietly(Constants.ALIAS_MASTER, sql, 
				serverIp,
				vo.getUrlPattern(),
				vo.getTotalRequestCnt(),
				vo.getOvertimeRequestCnt(),
				vo.getMatchedRequestCnt(),
				vo.getOvertimeMatchedRequestCnt(),
				vo.getAvgResqTime()
				) > 0;
	}

}
