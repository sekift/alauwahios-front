package cn.alauwahios.front.locate;

/**
 * hash算法接口
 * @author:sekift
 * @time:2014-7-14 下午05:10:40
 */
public interface HashAlgorithm {

	/**
	 * 对key进行hash运行,获取hash值
	 * @param key -- 被进行hash的key
	 * @return -- hash值
	 */
	long hash(final String key);
}
