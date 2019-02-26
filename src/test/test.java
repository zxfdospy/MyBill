package test;

import dao.RecordDAO;
import entity.Category;
import entity.Record;
import gui.panel.CategoryPrePanel;
import service.CategoryService;
import service.RecordService;
import util.DBUtil;
import util.DateUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class test {
    public static void main(String[] args) {
//        GUIUtil.useLNF();
//        JFrame f=new JFrame();
//        f.setSize(500,500);
//        f.setTitle("一本糊涂账");
//        f.setContentPane(MainPanel.instance);
//        f.setLocationRelativeTo(null);
//        f.setResizable(false);
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        f.setVisible(true);
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//        Date cs=DateUtil.getSpecifiedMonthBegin(2018,05);
//
//        System.out.println(sdf.format(cs));
//        cs=DateUtil.getSpecifiedMonthEnd(2018,05);
//        System.out.println(sdf.format(cs));
//        System.out.println(sdf.format(DateUtil.thismonthBegin()));
//        System.out.println(sdf.format(DateUtil.thismonthEnd()));
//        System.out.println(DateUtil.thisMonthLeftDay());
//        ConfigDAO dao=new ConfigDAO();
//        Config a=dao.getByKey("budget");
//        System.out.println(a.getId());
//        System.out.println(a.getKey());
//        System.out.println(a.getValue());
//
//        a.setValue("1000");
//        dao.update(a);
//        a=dao.getByKey("budget");
//        System.out.println(a.getValue());
//
//        RecordDAO dao=new RecordDAO();
//        Record a=dao.get(1);
//        Date b=new Date();
//        System.out.println(a.getDate());
//        System.out.println(b);

//        CategoryDAO dao=new CategoryDAO();
//        Category a=dao.getByName("jixu");
//        System.out.println(a.getName());
//        System.out.println(a.getId());
//        int b=dao.getCountByID(2);
//        System.out.println(b);
//        System.out.println("select * from category where name='?'");
//        System.out.println(DateUtil.getSpecifiedMonthBegin("2018-12"));
//        System.out.println(DateUtil.getSpecifiedMonthEnd("2018-12"));
//        System.out.println(DateUtil.date2YYYYMM(new Date()));
//
//        LinkedList<Record> rs=new RecordDAO().listThisMonth();
//        System.out.println(rs);
//        CategoryPrePanel p=CategoryPrePanel.instance;
//        LinkedList<Category> a=new CategoryService().listAll();
//        System.out.println("1");
//        Category sum=new CategoryService().addListData(a);
//        System.out.println(sum);
//        System.out.println("2");
//        int a=new RecordService().getTotal();
//        System.out.println(a);
//        Record a=new RecordService().getRecordByID(7);
//        Category c=new CategoryService().getCategoryByRecord(a);
//        System.out.println("");
//        a.setComment("测试update");
//        new RecordService().update(a.getId(),a.getSpend(),new CategoryService().getCategoryByRecord(a),a.getComment(),a.getDate());
//            Connection a= DBUtil.getConnection();
//        System.out.println(DBUtil.connected);

//        System.out.println(DBUtil.hutubiliExist());
//        DBUtil.creatHutubill();
//        DBUtil.creatTable();
    }
}
