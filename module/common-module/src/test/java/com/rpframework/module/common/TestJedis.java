package com.rpframework.module.common;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisServer;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestJedis {

  public static ApplicationContext ctx;

  public static JedisConnectionFactory jedisConnetionFactory;

  public JedisConnection jedisConnection;

  @BeforeClass
  public static void setBeforeClass() {
    ctx = new ClassPathXmlApplicationContext("spring-mvc.xml");
    jedisConnetionFactory = (JedisConnectionFactory) ctx
        .getBean("jedisConnectionFactory");
  }

  @Before
  public void setBefore() {
    jedisConnection = jedisConnetionFactory.getConnection();
  }

  @After
  public void setAfter() {
    jedisConnection.close();
  }

  private void print(Collection<RedisServer> c) {
    for (Iterator<RedisServer> iter = c.iterator(); iter.hasNext();) {
      RedisServer rs = (RedisServer) iter.next();
      System.out.println(rs.getHost() + ":" + rs.getPort());
    }
  }

  // 简单测试JedisConnection
  @Test
  public void test1() {
	  
		RedisTemplate<String, String> redisTemplate = ctx
				.getBean(RedisTemplate.class);
		BoundValueOperations<String, String> boundValueOps = redisTemplate
				.boundValueOps("test");
		boundValueOps.set("test1OK");
		BoundValueOperations<String,String> boundValueOps2 = redisTemplate.boundValueOps("test");
		System.out.println(boundValueOps2.get());
	
    if (!jedisConnection.exists(new String("zz").getBytes())) {
      jedisConnection.set(new String("zz").getBytes(),
          new String("zz").getBytes());
    }
  }

  @Test
  public void test2() {
    Set<byte[]> keys = jedisConnection.keys(new String("*").getBytes());
    for (Iterator<byte[]> iter = keys.iterator(); iter.hasNext();) {
      System.out.println(new String(iter.next()));
    }
  }

  // 测试Sentinel
//  @Ignore
  @Test
  public void test3() throws InterruptedException {
    if (jedisConnetionFactory.getSentinelConnection().isOpen()) {
      Collection<RedisServer> c = jedisConnetionFactory
          .getSentinelConnection().masters();
      print(c);
      RedisNode rn = new RedisNode("192.168.88.153", 6380);
      rn.setName("mymaster");
      c = jedisConnetionFactory.getSentinelConnection().slaves(rn);
      print(c);
    }

    for (int i = 0; i < 1000; i++) {
      jedisConnection.set(new String("k" + i).getBytes(), new String("v"
          + i).getBytes());
      Thread.sleep(1000);
    }
    Set<byte[]> keys = jedisConnection.keys(new String("k*").getBytes());
    Assert.assertEquals(1000, keys.size());
  }
}