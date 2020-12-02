package com.zpc.mybatis.dao;

import com.zpc.mybatis.pojo.Order;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

public class OrderMapperTest {

    private OrderMapper orderMapper;
    @Before
    public void setUp() throws Exception {
        // mybatis-config.xml
        String resource = "mybatis-config.xml";
        // 读取配置文件
        InputStream is = Resources.getResourceAsStream(resource);
        // 构建SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        // 获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
//        this.userDao = new UserDaoImpl(sqlSession);
        orderMapper = sqlSession.getMapper(OrderMapper.class);
    }

    //一对一
    @Test
    public void queryOrderWithUserByOrderNumber() {
        Order order = orderMapper.queryOrderWithUserByOrderNumber("201807010001");
        System.out.println(order.getUser());
    }

    //一对多
    @Test
    public void queryOrderWithUserAndDetailByOrderNumber() {
        Order order = orderMapper.queryOrderWithUserAndDetailByOrderNumber("201807010001");
        System.out.println(order);
        System.out.println(order.getUser());
        System.out.println(order.getDetailList());
    }

    //多对多
    @Test
    public void queryOrderWithUserAndDetailItemByOrderNumber() {
        Order order = orderMapper.queryOrderWithUserAndDetailItemByOrderNumber("201807010001");
        System.out.println(order);
        System.out.println("----------------------------");
        System.out.println(order.getUser());
        System.out.println("----------------------------");
        System.out.println(order.getDetailList());
    }
}