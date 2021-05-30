package fr.utbm.school.core.tools;

import lombok.SneakyThrows;
import org.redisson.Redisson;
import org.redisson.api.RKeys;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.io.File;
import java.io.IOException;


/**
 *
 * @author Ruiqing ZHU/Neil FARMER
 */
public class RedisUtil {

    private boolean shutdownAtEnd = true;

    private RedissonClient client;

    /**
     * Constructor
     */
    public RedisUtil(){
        setRedisClient();
    }

    public RedisUtil(boolean shutdownAtEnd){
        setRedisClient();
        this.shutdownAtEnd = shutdownAtEnd;
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
    @SneakyThrows
    private void setRedisClient() {
        Config config = Config.fromYAML(new File("./src/main/resources/redissonConfig.yml"));

        this.client = Redisson.create(config);
    }

    /**
     * @deprecated
     * This method is no longer acceptable to create a client
     * <p> Use {@link RedisUtil#setRedisClient()} instead.
     *
     */
    @Deprecated
    private void setRedisClientSingle(){
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
    public void clearRedis(){
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
            if(this.shutdownAtEnd){
                this.client.shutdown();
            }
        } finally {
            super.finalize();
        }
    }

}
