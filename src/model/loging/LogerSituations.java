package model.loging;

import model.record_worker.ConectorToBd;
import org.apache.log4j.Logger;

/**
 * Created by Administrator1 on 23.05.2017.
 */
public class LogerSituations {
    private static Logger log = Logger.getLogger( ConectorToBd.class);

    public static enum LevelMessage {
        ERROR,
        FATAL,
        INFO,
        WARN
    }

    public static void log(String message, LevelMessage errorType) {
//        log.error("");
        switch (errorType){
            case INFO: log.info(message); break;
            case ERROR: log.error(message); break;
            case FATAL: log.fatal(message); break;
            case WARN: log.warn(message); break;
        }
    }


}
