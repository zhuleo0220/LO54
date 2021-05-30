package fr.utbm.school.core.tools;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;

/**
 * @author Neil Farmer/Ruiqing Zhu
 */
public class LoggingUtil {
    public static void setLoggingToTrace(){
        LogManager.getRootLogger().setLevel(Level.TRACE);
    }

    public static void setLoggingToDebug(){
        LogManager.getRootLogger().setLevel(Level.DEBUG);
    }

    public static void setLoggingToInfo(){
        LogManager.getRootLogger().setLevel(Level.INFO);
    }

    public static void setLoggingToWarning(){
        LogManager.getRootLogger().setLevel(Level.WARN);
    }

    public static void setLoggingToError(){
        LogManager.getRootLogger().setLevel(Level.ERROR);
    }

    public static void setLoggingToFatal(){
        LogManager.getRootLogger().setLevel(Level.FATAL);
    }
}
