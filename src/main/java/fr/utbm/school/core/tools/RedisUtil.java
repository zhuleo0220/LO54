package fr.utbm.school.core.tools;

import fr.utbm.school.core.Dao.impl.EntityCourseDaoImpl;
import fr.utbm.school.core.controller.ClientController;
import fr.utbm.school.core.entity.Course;
import org.apache.log4j.Logger;
import org.redisson.Redisson;
import org.redisson.api.RKeys;
import org.redisson.api.RLiveObjectService;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Ruiqing ZHU/Neil FARMER
 */
public class RedisUtil {

    // Logger of the controller
    private static final Logger logger = Logger.getLogger(RedisUtil.class.getName());

    private RedissonClient client;

    /**
     * Constructor
     */
    public RedisUtil(){
        setRedisClient();
    }

    /**
     * Method to client the connection to redis
     * @return
     */
    public RedissonClient getClient() {
        if(!isRedisAvailable()){
            setRedisClient();
        }

        return client;
    }

    /**
     * Private method to establish connection to redis server
     */
    private void setRedisClient(){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        this.client = Redisson.create(config);
    }

    /**
     * Method to know if we can use redis
     * @return True if redis server is available, else false
     */
    public boolean isRedisAvailable(){
        return !(client.isShuttingDown() || client.isShutdown());
    }

    /**
     * Method to empty redis server
     */
    private void clearRedis(){
        RKeys keys = client.getKeys();
        keys.flushall();
        client.shutdown();
    }

    /**
     * method to  shutdown the connection to redis server
     */
    public void shutdown(){
        this.client.shutdown();
    }

    /**
     * Overide finalize method to shutdown properly teh connection to redis db
     * @throws Throwable
     */
    @Override
    protected void finalize() throws Throwable{
        try {
            this.client.shutdown();
        } finally {
            super.finalize();
        }
    }

}
