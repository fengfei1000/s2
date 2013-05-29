package fengfei.fir.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import fengfei.forest.slice.config.Config;
import fengfei.forest.slice.config.SliceConfigReader;
import fengfei.forest.slice.config.xml.XmlSliceConfigReader;
import fengfei.forest.slice.config.zk.ZKSliceConfigReader;
import fengfei.forest.slice.database.DatabaseRouterFactory;
import fengfei.ucm.dao.Transactions;

@Configuration
@PropertySource("classpath:DataSource.properties")
public class SliceDBInit {

    static Logger logger = LoggerFactory.getLogger(SliceDBInit.class);
    public static final String SliceConfig = "SliceConfig";
    @Autowired
    private Environment env;
    DatabaseRouterFactory databaseRouterFactory;
    SliceConfigReader configReader;

    public void onApplicationStart() {

        String sliceConfig = env.getProperty(SliceConfig);
        if (sliceConfig != null && !"".equals(sliceConfig)) {
            String[] scc = sliceConfig.split("/");
            String file = scc[1];

            try {
                if (scc[0].equals("file")) {
                    configReader = new XmlSliceConfigReader(file);
                } else {
                    configReader = new ZKSliceConfigReader(file);
                }
                logger.info("reading xml config..." + file);
                Config config = configReader.read("/root");
                logger.info("pasering config....");
                // System.out.println(config.toString());
                databaseRouterFactory = new DatabaseRouterFactory(config);
                logger.info("pasered config.");

                Transactions.setDatabaseSliceGroupFactory(databaseRouterFactory);

            } catch (Throwable e) {
                logger.error("Initialize slice config error for file " + sliceConfig, e);
            }
        }
    }

}
