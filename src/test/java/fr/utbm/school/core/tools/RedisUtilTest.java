package fr.utbm.school.core.tools;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.redisson.api.RTopic;

import static org.junit.jupiter.api.Assertions.*;

class RedisUtilTest {

    @Test
    void getClient() {
        RedisUtil rutil = new RedisUtil();
        Assertions.assertTrue(!rutil.getClient().isShutdown() && !rutil.getClient().isShuttingDown());
    }

    @Test
    void shutdown() {
        RedisUtil rutil = new RedisUtil();
        rutil.shutdown();
        Assertions.assertTrue(!rutil.isRedisAvailable());
    }

    @Test
    void pubSub(){
        RedisUtil rutil = new RedisUtil();
        RTopic subscribeTopic = rutil.getClient().getTopic("test");

        // The onMessage function
        subscribeTopic.addListener(String.class,
                (channel, message) -> System.out.print("Test OK"));

        // Publish the message
        long clientsReceivedMessage = subscribeTopic.publish(new String("Test"));

        // Check that one client received the test message
        Assertions.assertEquals(1L, clientsReceivedMessage);
    }
}