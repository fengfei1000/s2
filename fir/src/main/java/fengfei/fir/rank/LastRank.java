package fengfei.fir.rank;

import java.util.Calendar;
import java.util.Set;

import fengfei.shard.redis.RedisComand;

public class LastRank implements Rank {
	final static String RankLastKey = "RankLast";
	final static int T2012Minute;
	final static int WeekMinute = 7 * 24 * 60;
	public static RedisComand read;
	public static RedisComand write;
	static {
		Calendar c = Calendar.getInstance();
		c.set(2012, 0, 1, 0, 0, 0);
		T2012Minute = (int) (c.getTimeInMillis() / 1000 / 60);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fengfei.fir.service.Rank#add(java.lang.String)
	 */
	@Override
	public void add(String id) {

		long time = (System.currentTimeMillis() / 1000 / 60) - T2012Minute;
		write.zadd(RankLastKey, time, id);
 
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fengfei.fir.service.Rank#find(int, int)
	 */
	@Override
	public Set<String> find(int offset, int count) {
		long last = ((System.currentTimeMillis() / 1000 / 60)) - T2012Minute
				- WeekMinute;
		return read.zrevrangeByScore(RankLastKey, Long.MAX_VALUE, last, offset,
				count);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fengfei.fir.service.Rank#removeExpired()
	 */
	@Override
	public long removeExpired() {
		long last = ((System.currentTimeMillis() / 1000 / 60)) - T2012Minute
				- WeekMinute;
		long size = write.zremrangeByScore(RankLastKey, 0, last);
		return size;
	}
}
