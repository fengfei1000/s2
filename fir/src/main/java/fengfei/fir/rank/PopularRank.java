package fengfei.fir.rank;

import java.util.Random;
import java.util.Set;

import fengfei.shard.redis.RedisComand;

public class PopularRank {
	final static String RankPopularKey = "RankPopular";
	final static double MaxScore = 100D;
	public static RedisComand read;
	public static RedisComand write;

	public static void main(String[] args) {
		// float s1 = 0.425f;
		// float s2 = 0.27f;
		// float s3 = 0.1f;
		// double last = 0;
		// int time = 0;
		// Random random = new Random();
		// for (int i = 1; i < 1000; i++) {
		// // System.out.println(Math.log(i));
		// // last = last + ((100 - last) * s2);
		// time += random.nextInt(10);
		// double score = (i - 1) / Math.pow(time + 2, 1.8);
		// // last = last + ((100 - last) * score);
		// // last = - (1 / score);
		// System.out.println(i + "--  " + score);
		// }
		test();
	}

	public static void test() {
		// double vists = 2743;
		// double likes = 906;
		// double likesIn30Days = 906;
		// double favorites = 519;
		//27,15.5
		double vists = 100;
		double likes = 27;
		double likesIn30Days = 24;
		double favorites = 17;

		double s1 = (vists / 100) * 100;
		double s2 = (likes / 100) * 100;
		double s3 = (favorites / 100) * 100;

		int t = 1;
		double s4 = (likesIn30Days - 1) / Math.pow((t + 2), 1.8);
		double score2 = (s1 * 0.1) + (s2 * 0.2) + (s3 * 0.1) + (s4 * 0.6);
		System.out.println(score2);
		;
		System.out.println(10*Math.log10(score2));
		System.out.printf("%f %f %f %f %f \n", s1, s2, s3, s4, s3);
	}

	public double incr(String id, int percent) {
		percent = percent > 50 ? 50 : percent;
		Double d = write.zscore(RankPopularKey, id);
		double incr = (MaxScore - (d == null ? 0d : d)) * percent / 100;
		Double score = write
				.zincrby(RankPopularKey, (incr < 0 ? 0d : incr), id);
		return score;
	}

	public double incrPercent50(String id) {
		return incr(id, 50);
	}

	public double incrPercent25(String id) {
		return incr(id, 25);
	}

	public double incrPercent10(String id) {
		return incr(id, 10);
	}

	public Set<String> find(int offset, int count) {
		return read.zrevrangeByScore(RankPopularKey, 100D, 0D, offset, count);
	}

	public long removeExpired() {
		return 0;
	}

}
