package fengfei.fir.db;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.pool.impl.GenericObjectPool.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import fengfei.forest.database.pool.BonePoolableDataSourceFactory;
import fengfei.forest.database.pool.DbcpPoolableDataSourceFactory;
import fengfei.forest.database.pool.PoolableDataSourceFactory;
import fengfei.forest.database.pool.PoolableException;
import fengfei.forest.database.pool.TomcatPoolableDataSourceFactory;
//@Configuration
//@PropertySource("classpath:DataSource.properties")

public class PooledDBPlugin {

    static Logger logger = LoggerFactory.getLogger(PooledDBPlugin.class);
    public static final String POOL_NAME = "database.pool.name";
    public static final String POOL_BONECP = "BoneCP";
    public static final String POOL_DBCP = "DBCP";
    public static final String POOL_TOMCAT_JDBC = "TomcatJDBC";
    // @Autowired
    private Environment env;
    PoolableDataSourceFactory poolableDataSourceFactory = null;
    DataSource dataSource;

    public void onApplicationStart() {
        try {
            DbParameters dp = readDbParameters();

            String poolName = dp.poolName;
            if (null == poolName || "".equals(poolName)) {
                poolName = "BoneCP";
            }

            if (POOL_BONECP.equalsIgnoreCase(poolName)) {
                poolableDataSourceFactory = new BonePoolableDataSourceFactory();
            } else if (POOL_DBCP.equalsIgnoreCase(poolName)) {
                poolableDataSourceFactory = new DbcpPoolableDataSourceFactory();
            } else if (POOL_TOMCAT_JDBC.equalsIgnoreCase(poolName)) {
                poolableDataSourceFactory = new TomcatPoolableDataSourceFactory();
            } else {
                logger.warn("Can't supported pool, default using TomcatJDBC.");
                poolableDataSourceFactory = new TomcatPoolableDataSourceFactory();
            }

            dataSource = poolableDataSourceFactory.createDataSource(
                dp.driver,
                dp.url,
                dp.user,
                dp.password,
                dp.params);
            logger.info(String.format("Initialized %s pool configuration.", poolName));
        } catch (Exception e) {
            logger.error("Initialize datasource error", e);
        }

    }

    public void onApplicationStop() {
        try {
            poolableDataSourceFactory.destory(dataSource);
            logger.info(String.format("destory %s.", dataSource.getClass().getName()));
        } catch (PoolableException e) {
            logger.error("destory datasource error", e);
        }
    }

    private DbParameters readDbParameters()
        throws IllegalAccessException,
        InvocationTargetException,
        NoSuchMethodException {

        String user = env.getProperty("database.user");
        String url = env.getProperty("database.url");
        String password = env.getProperty("database.password");
        String driver = env.getProperty("database.driver");
        String poolName = env.getProperty(POOL_NAME);
        Config config = readConfig(poolName);
        Map<String, String> params = BeanUtils.describe(config);

        DbParameters dbParameters = new DbParameters(url, driver, user, password, poolName, params);
        return dbParameters;
    }

    @Bean
    private Config readConfig(String prefix) {

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

    public static class DbParameters {

        public String url;
        public String driver;
        public String user;
        public String password;
        public String poolName;
        public Map<String, String> params;

        public DbParameters(
            String url,
            String driver,
            String user,
            String password,
            String poolName,
            Map<String, String> params) {
            super();
            this.url = url;
            this.driver = driver;
            this.user = user;
            this.password = password;
            this.poolName = poolName;
            this.params = params;
        }

    }
}
