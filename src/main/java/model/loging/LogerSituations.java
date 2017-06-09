package model.loging;


import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
/**
 * Created by Administrator1 on 23.05.2017.
 */
public class LogerSituations {
    private Logger logger;

    public LogerSituations(Class clazz) {
        logger = LogManager.getLogger(clazz.getName());
    }

    public void logError(Exception e) {
        logger.error(e);
        e.printStackTrace();
    }

    public void logInfo( String e) {
        logger.info(e);
    }
}
