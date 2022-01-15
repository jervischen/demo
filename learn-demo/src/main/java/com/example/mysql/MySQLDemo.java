package com.example.mysql;

import java.sql.*;

/**
 * @author Chen Xiao
 * @since 2021-09-09 10:22
 */
public class MySQLDemo {
    // MySQL 8.0 以下版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://pplive-3306-mydb.lizhi.fm/lz_pp_lottery";

    // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
    //static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    //static final String DB_URL = "jdbc:mysql://localhost:3306/RUNOOB?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";


    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "db_admin#ops.fm";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");

            String sql = "INSERT INTO `lz_pp_lottery`.`lottery_winning_record`(`id`, `lottery_record_id`, `user_id`, `live_id`, `nj_id`, `activity_id`, `lottery_config_id`, `amount`, `single_cost`, `total_cost`, `single_reward`, `total_reward`, `treasure_id`, `is_lucky`, `is_window_gift`, `state`, `create_time`, `modify_time`, `type`) VALUES (?, 5065055908187353526, 2677455881410503212, 4892403144165777776, 2677417875244898348, 5065047966792837046, 5065048177246247862, 1, 2.0000, 2.0000, 270, 270, 5065049225218272694, 0, 0, 2, '2019-09-10 11:33:27', '2019-09-10 11:33:27', 1);";

            int i = 1;
            conn.setAutoCommit(false);
            PreparedStatement pst = conn.prepareStatement(sql);
            while (i< 55555){
                pst.setLong(1,i+999);
                pst.addBatch();
                i++;
                System.out.println(i);
            }

            pst.executeBatch();
            conn.commit();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }
}
