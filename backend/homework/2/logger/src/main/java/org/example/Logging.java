package org.example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Logging {
    private static final Logger logger = LoggerFactory.getLogger(Logging.class);

    public void logInfo(String msg)
    {
        logger.info(msg);
    }

    public void logWarn(String msg){
        logger.warn(msg);
    }
}
