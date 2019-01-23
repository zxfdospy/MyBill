package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

    static String ip="127.0.0.1";
    static int port=3306;
    static String database="hutubill";
    static String encoding="UTF-8";
    static String loginName="root";
    static String password="admin";



    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static boolean mySQLExist(){
        Connection con=null;
        String url=String.format("jdbc:mysql://%s:%d",ip,port,database);
        try {
            con=DriverManager.getConnection(url,loginName,password);
            con.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean hutubiliExist(){
        try (Connection c=DBUtil.getConnection()){
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static void creatHutubill(){
        String url=String.format("jdbc:mysql://%s:%d",ip,port,database);
        try(Connection c=DriverManager.getConnection(url,loginName,password); Statement s=c.createStatement()) {
            String sql= "create database hutubill;";
            s.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void creatTable(){
        String[] sql=new String[]{"CREATE TABLE config (id int AUTO_INCREMENT,key_ varchar(255) ,value varchar(255),PRIMARY KEY (id))  DEFAULT CHARSET=utf8;","CREATE TABLE category (id int AUTO_INCREMENT,name varchar(255) ,PRIMARY KEY (id))  DEFAULT CHARSET=utf8;","CREATE TABLE record (id int AUTO_INCREMENT,spend double ,cid int,comment varchar(255) ,date Date,PRIMARY KEY (id),CONSTRAINT `fk_record_category` FOREIGN KEY (`cid`) REFERENCES `category` (`id`))  DEFAULT CHARSET=utf8;"};
        try(Connection c=DBUtil.getConnection();Statement s=c.createStatement()) {
            for (int i = 0; i < 3; i++) {
//                System.out.println(sql[i]);
                s.execute(sql[i]);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException {
        Connection con=null;
        String url=String.format("jdbc:mysql://%s:%d/%s?characterEncoding=%s",ip,port,database,encoding);
        con=DriverManager.getConnection(url,loginName,password);
        return con;
    }


}
