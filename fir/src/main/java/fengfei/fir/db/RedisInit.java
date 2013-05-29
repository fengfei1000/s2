package fengfei.fir.db;

import javax.annotation.PostConstruct;

import org.apache.commons.pool.impl.GenericObjectPool.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import fengfei.fir.rank.LastRank;
import fengfei.fir.rank.PopularRank;
import fengfei.fir.rank.TopRank;
import fengfei.forest.slice.plotter.HashPlotter;
import fengfei.shard.Ploy;
import fengfei.shard.Selector;
import fengfei.shard.redis.JedisShards;
import fengfei.shard.redis.ShardsRedis;

@Configuration
@PropertySource("classpath:redis.properties")
public class RedisInit {

    final static String HostsKey = "redis.hosts";
    final static String TimeoutKey = "redis.timeout";
    final static String PloyKey = "redis.ploy.class";
    static Logger logger = LoggerFactory.getLogger(RedisInit.class);
    @Autowired
    private Environment env;
    private String hosts;
    private int timeout;
    private Ploy ploy;

    public void onApplicationStart() {
        try {
            hosts = env.getProperty(HostsKey, "localhost:6379");
            String timeoutStr = env.getProperty(TimeoutKey, "localhost:6379");
            timeout = Integer.parseInt(timeoutStr);
            String ployClass = env.getProperty(PloyKey, HashPlotter.class.getCanonicalName());
            ploy = (Ploy) Class.forName(ployClass).newInstance();
            logger.info("Initialize redis shard/cluster....");
            ShardsRedis redis = new ShardsRedis(hosts, timeout, ploy, readConfig());
            // JedisShards shards = new JedisShards(hosts, timeout, ploy, readConfig());
            //
            logger.info("Initialized redis shard/cluster.");
            LastRank.read = redis.createRedisCommand();
            LastRank.write = redis.createRedisCommand(Selector.Write);
            //
            TopRank.read = redis.createRedisCommand();
            TopRank.write = redis.createRedisCommand(Selector.Write);
            //
            PopularRank.read = redis.createRedisCommand();
            PopularRank.write = redis.createRedisCommand(Selector.Write);
        } catch (Exception e) {
            logger.error("Init redis cluster.", e);
        }
    }

    public Config readConfig() {
        String prefix = "redis.pool.";
        Config cfg = new Config();

        cfg.lifo = Boolean.valueOf(env.getProperty(prefix + "lifo", "true"));
        cfg.maxActive = Integer.valueOf(env.getProperty(prefix + "maxActive", "18"));
        cfg.maxIdle = Integer.valueOf(env.getProperty(prefix + "maxIdle", "6"));
        cfg.maxWait = Integer.valueOf(env.getProperty(prefix + "maxWait", "150000"));
        cfg.minEvictableIdleTimeMillis = Integer.valueOf(env.getProperty(prefix
                + "minEvictableIdleTimeMillis", "100000"));
        cfg.minIdle = Integer.valueOf(env.getProperty(prefix + "minIdle", "0"));
        cfg.numTestsPerEvictionRun = Integer.valueOf(env.getProperty(prefix
                + "numTestsPerEvictionRun", "1"));
        cfg.testOnBorrow = Boolean.valueOf(env.getProperty(prefix + "testOnBorrow", "false"));
        cfg.testOnReturn = Boolean.valueOf(env.getProperty(prefix + "testOnReturn", "false"));
        cfg.testWhileIdle = Boolean.valueOf(env.getProperty(prefix + "testWhileIdle", "false"));
        cfg.timeBetweenEvictionRunsMillis = Integer.valueOf(env.getProperty(prefix
                + "timeBetweenEvictionRunsMillis", "120000"));
        cfg.whenExhaustedAction = Byte.valueOf(
            env.getProperty(prefix + "whenExhaustedAction", "1"),
            10);

        return cfg;

    }

    public String getHosts() {
        return hosts;
    }

    public void setHosts(String hosts) {
        this.hosts = hosts;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public Ploy getPloy() {
        return ploy;
    }

    public void setPloy(Ploy ploy) {
        this.ploy = ploy;
    }

}
