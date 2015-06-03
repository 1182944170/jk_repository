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
//import org.springframework.data.redis.connection.RedisNode;
//import org.springframework.data.redis.connection.RedisServer;
//import org.springframework.data.redis.connection.jedis.JedisConnection;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.BoundValueOperations;
//import org.springframework.data.redis.core.RedisTemplate;
//import redis.clients.jedis.Jedis;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestJedis {/*

  public static ApplicationContext ctx;

  public static JedisConnectionFactory jedisConnetionFactory;

  public JedisConnection jedisConnection;

  @BeforeClass
  public static void setBeforeClass() {
    ctx = new ClassPathXmlApplicationContext("spring-mvc.xml");
  }

  @Before
  public void setBefore() {
  }

  @After
  public void setAfter() {
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
	  	Jedis jedis = new Jedis("localhost");
		jedis.set("foo", "bar");
		String value = jedis.get("foo");
		System.out.println(value);
		
		RedisTemplate<String, String> redisTemplate = ctx
				.getBean(RedisTemplate.class);
		BoundValueOperations<String, String> boundValueOps = redisTemplate
				.boundValueOps("test");
		System.out.println(boundValueOps.get());
		boundValueOps.set("test1OK");
		BoundValueOperations<String,String> boundValueOps2 = redisTemplate.boundValueOps("test");
		System.out.println(boundValueOps2.get());
  }

  @Test
  public void test2() {
  }

  // 测试Sentinel
//  @Ignore
  @Test
  public void test3() throws InterruptedException {
  }
*/}