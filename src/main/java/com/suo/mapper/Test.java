package com.suo.mapper;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.xml.bind.Element;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * XXX
 *
 * @author SixHwChen
 * @date 2022/7/29 13:48
 */
public class Test {
    public static void main(String[] args) throws IOException {
        //加载mybatis的配置文件
        InputStream input = User.class.getClassLoader().getResourceAsStream("mybatis.xml");
        //或者用这种方法加载配置文件，Resources是mybatis自己提供的类
//        InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");

        // 用建造者模式，创造 生产SqlSession的工厂（这个工厂的类型由配置文件决定）
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(input);

        // 工厂生产Sqlsession
        SqlSession sqlSession = factory.openSession();

        //新增
//        User user = new User();
//        user.setId(9);
//        user.setPwd("bea");
//        user.setName("zs");
//        user.setAge(30);
//        int affectedRows = sqlSession.insert("play.insertUser", user);
//        System.out.println("插入数量：" + affectedRows);

        //删除
//        int affectedRows1 = sqlSession.delete("play.deleteUserById", 2);
//        System.out.println("删除数量：" + affectedRows1);

        //修改
//        User user4 = new User();
//        user4.setId(4);
//        user4.setPwd("bea4");
//        user4.setName("zs4");
//        user4.setAge(305);
//        int affectedRows3 = sqlSession.update("play.selectOneLimit", user4);
//        System.out.println("修改数量：" + affectedRows3);


        //查询批量
        List<User> userList = sqlSession.selectList("play.selectList");
        for (User user1 : userList) {
            System.out.println("批量查询结果：" +user1);
        }

        //查询单个
        User user2 = sqlSession.selectOne("play.selectOne", 1);
        System.out.println("查询单个结果：" + user2.toString());
        sqlSession.commit();
        //关闭IO资源（工厂对象会自动回收）
        assert input != null;
        input.close();
        sqlSession.close();
    }
}
