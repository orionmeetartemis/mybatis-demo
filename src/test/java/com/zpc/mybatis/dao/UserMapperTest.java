package com.zpc.mybatis.dao;

import com.zpc.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class UserMapperTest {

    public UserMapper userMapper;

    @Before
    public void setUp() throws Exception {
        // 指定配置文件
        String resource = "mybatis-config.xml";
        // 读取配置文件
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // 构建sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        // 1. 映射文件的命名空间（namespace）必须是mapper接口的全路径
        // 2. 映射文件的statement的id必须和mapper接口的方法名保持一致
        // 3. Statement的resultType必须和mapper接口方法的返回类型一致
        // 4. statement的parameterType必须和mapper接口方法的参数类型一致（不一定）
        this.userMapper = sqlSession.getMapper(UserMapper.class);
    }

    @Test
    public void testQueryUserByTableName() {
        List<User> userList = this.userMapper.queryUserByTableName("tb_user");
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    public void testLogin() {
        System.out.println(this.userMapper.login("hj", "123456"));
    }

    @Test
    public void testQueryUserById() {
        System.out.println(this.userMapper.queryUserById("1"));
    }

    @Test
    public void testQueryUserAll() {
        List<User> userList = this.userMapper.queryUserAll();
        for (User user : userList) {
            System.out.println(user);
        }
    }

    /**
     * 插入之后返回主键
     */
    @Test
    public void testInsertUser() {
        User user = new User();
        user.setAge(20);
        user.setBirthday(new Date());
        user.setName("大神");
        user.setPassword("123456");
        user.setSex(2);
        user.setUserName("bigGod222");
        int res = this.userMapper.insertUser(user);
        System.out.println("res: "+res);//受影响行数
        System.out.println(user.getId());//主键
    }

    /**
     * 批量插入之后返回主键
     */
    @Test
    public void testBatchInsertUser() {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            User user = new User();
            user.setAge(20);
            user.setBirthday(new Date());
            user.setName("大神");
            user.setPassword("123456");
            user.setSex(2);
            user.setUserName("bigGod222");
            list.add(user);
        }

//        List<User> list = new ArrayList<>(Collections.nCopies(3, user));
        int res = this.userMapper.batchInsertUser(list);
        System.out.println("res: "+res);//受影响行数
//        System.out.println(user.getId());//主键
        list.stream().map(User::getId).forEach(System.out::println);
    }


    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setBirthday(new Date());
        user.setName("静静");
        user.setPassword("123456");
        user.setSex(0);
        user.setUserName("Jinjin");
        user.setId("1");
        this.userMapper.updateUser(user);
    }


    @Test
    public void testDeleteUserById() {
        this.userMapper.deleteUserById("1");
    }

    @Test
    public void queryUserList() {
        List<User> users = userMapper.queryUserList("程");
        users.forEach(System.out::println);
    }

    @Test
    public void queryUserListByNameOrAge() {
        List<User> users = userMapper.queryUserListByNameOrAge(null, 16);
        users.forEach(System.out::println);
    }

    @Test
    public void queryUserListByNameAndAge() {
        List<User> users = userMapper.queryUserListByNameAndAge("鹏程", 22);
        users.forEach(System.out::println);
    }

    @Test
    public void queryUserListByIds() throws Exception {
        List<User> users = this.userMapper.queryUserListByIds(new String[]{"1", "2"});
        for (User user : users) {
            System.out.println(user);
        }
    }
}