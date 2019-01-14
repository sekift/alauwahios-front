package cn.alauwahios.front;

import cn.alauwahios.front.util.CloseUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class Main {
	static cn.alauwahios.front.redis.RedisPoolService redisPool = cn.alauwahios.front.server.ServiceFactory
			.getService("FwRedisPoolService");

	public static void main(String[] args) throws Exception {
		String key1 = "key11";
		String key2 = "key21";
		try {
			JedisPool jp = redisPool.getPoolByKemataHash("w", key1);
			Jedis jd = jp.getResource();
			JedisPool jp1 = redisPool.getPoolByKemataHash("r", key1);
			Jedis jd1 = jp1.getResource();
			System.out.println(jd1.setex(key2, 600, "cba21"));
			System.out.println(jd.get(key2));

			CloseUtil.returnResourceSilently(jp, jd);
			CloseUtil.returnResourceSilently(jp1, jd1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
