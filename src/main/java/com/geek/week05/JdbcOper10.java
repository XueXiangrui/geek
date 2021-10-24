package com.geek.week05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import sun.jvm.hotspot.debugger.cdbg.Sym;
import sun.tools.java.Environment;

import javax.annotation.Resource;
import java.beans.Transient;
import java.sql.*;

/**
 * Created by xiangrui.xue on 2021/10/24.
 */
@Configuration
@PropertySource("classpath:hikari.xml")
public class JdbcOper10 {

    @Autowired
    HikariConfig hikariConfig;

    public  void main(String[] args) throws ClassNotFoundException, SQLException {
//      10.1 使用 JDBC 原生接口，实现数据库的增删改查操作
        crudJDBC();
//      10.2 使用事务，PrepareStatement 方式，批处理方式，改进上述操作
        crudPreparedJDBC();
//      10.3配置 Hikari 连接池
        hikari();

    }

    public static void crudJDBC() throws ClassNotFoundException,SQLException {
//        Class.forName("com.mysql.jdbc.Driver"); 删除貌似也没问题。。
        String url = "jdbc:mysql://localhost:3306/school?useUnicode=true&character=utf8&useSSL=false&serverTimezone=UTC";
        String username = "root";
        String pwd = "123321";
        Connection connection = DriverManager.getConnection(url, username, pwd);
        Statement statement = connection.createStatement();
//        查询
        String sql = "select * from student";
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            System.out.println(resultSet.getInt("id"));
        }
//        增加
        String addSql = "insert into student values('1','Test','50')";
        System.out.println("插入执行结果：" + statement.executeUpdate(addSql));
//        更新
        String updateSql = "update student set score = '' where id = 5 ";
        System.out.println("插入执行结果：" + statement.executeUpdate(updateSql));
//        删除
        String delSql = "delete from student where id =5";
        System.out.println("插入执行结果：" + statement.executeUpdate(delSql));
        resultSet.close();
        statement.close();
        connection.close();
    }

//  这个Transient是假的不？
    @Transient
    public static void crudPreparedJDBC() {
        String url = "jdbc:mysql://localhost:3306/school?useUnicode=true&character=utf8&useSSL=false&serverTimezone=UTC";
        String username = "root";
        String pwd = "123321";
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement psQuery = null;
        PreparedStatement psAdd = null;
        PreparedStatement psDel = null;
        PreparedStatement psUpdate = null;
        try {
            connection = DriverManager.getConnection(url, username, pwd);
            connection.setAutoCommit(false);//开启事务
            //        查询
            String sql = "select * from student where id = ? and score = ?";
            psQuery = connection.prepareStatement(sql);
            psQuery.setInt(1, 4);
            psQuery.setInt(2, 60);
            resultSet = psQuery.executeQuery(sql);
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id"));
            }
            //        增加
            String addSql = "insert into student values(?,?,?)";
            psAdd = connection.prepareStatement(addSql);
            psAdd.setInt(1, 5);
            psAdd.setString(2, "name");
            psAdd.setInt(3, 70);
            System.out.println("插入执行结果：" + psAdd.executeUpdate(addSql));
            //        更新
            String updateSql = "update student set score = ? where id = ? ";
            psUpdate = connection.prepareStatement(updateSql);
            psUpdate.setInt(1, 60);
            psUpdate.setInt(2, 5);
            System.out.println("插入执行结果：" + psUpdate.executeUpdate(updateSql));
            //        删除
            String delSql = "delete from student where id = ?";
            psDel = connection.prepareStatement(delSql);
            psUpdate.setInt(1, 5);
            System.out.println("插入执行结果：" + psDel.executeUpdate(delSql));

            connection.commit();//提交事务
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                psQuery.close();
                psAdd.close();
                psUpdate.close();
                psDel.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
    public void hikari() {

        String url = hikariConfig.url;
        String name = hikariConfig.username;
        String pwd = hikariConfig.password;

    }



}
