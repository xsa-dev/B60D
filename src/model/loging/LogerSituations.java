package model.loging;

import org.apache.log4j.Logger;

/**
 * Created by Administrator1 on 23.05.2017.
 */
public class LogerSituations {
    private Logger logger;

    public LogerSituations(Class clazz) {
        this.logger = Logger.getLogger( clazz);
    }

    public void logError(Exception e) {
        logger.error(e);
        e.printStackTrace();
    }

    public void logInfo( String e) {
        logger.info(e);
    }
}
