package com.geek.week07.src;

import java.sql.*;

/**
 * Created by xiangrui.xue on 2021/11/7.
 */
//按自己设计的表结构，插入 100 万订单模拟数据，测试不同方式的插入效率
public class w7_2 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//       经测试，1w100次无索引无事务单线程的情况下，2条数据0.02s左右，10万数据1.734s，基于10万，id重复9.077s
//        删除数据，9.945s
//        10w执行10次，8.692s；batch放1w-10w，不要放100w
//        todo 单线程，要试一下多线程；其他同学单线程十几秒，多线程5秒

        inserts();
    }


    public static void inserts() {
        String url = "jdbc:mysql://10.58.11.208:3306/test_test?useUnicode=true&character=utf8&useSSL=false" +
                "&serverTimezone=UTC&rewriteBatchedStatements=true";
        String username = "root";
        String pwd = "TD@123";
        Connection connection = null;
        PreparedStatement psAdd = null;
        long start = 0;
        try {
            connection = DriverManager.getConnection(url, username, pwd);
            //        增加
            System.out.println("开始拼接sql" + System.currentTimeMillis());
            String addSql = "insert into USER values(?,?,?)";
            psAdd = connection.prepareStatement(addSql);
            for (int i = 0; i < 10; i ++){
                for (int j = 0; j < 100000; j++){
                    psAdd.setString(1, "1" + String.valueOf(i)+ String.valueOf(j));
                    psAdd.setString(2, "name");
                    psAdd.setString(3, "13575475333");
                    psAdd.addBatch();

                }
            }
            start = System.currentTimeMillis();
            System.out.println("开始执行sql" + start);
            psAdd.executeBatch();

            System.out.println("结束执行sql" + System.currentTimeMillis());
            System.out.println(System.currentTimeMillis() - start);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                psAdd.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}
