package service;

import dao.CategoryDAO;
import dao.RecordDAO;
import entity.Category;
import entity.Record;
import util.DateUtil;
import util.GUIUtil;
import util.ToolUtil;

import javax.swing.*;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class CategoryService {

    CategoryDAO categoryDAO=new CategoryDAO();
    RecordDAO recordDAO=new RecordDAO();

    //显示全部分类的次数
    public LinkedList<Category> listAll(){
        LinkedList<Category> cs=categoryDAO.list();
        for(Category c:cs){
            LinkedList<Record> rs=recordDAO.list(c.getId());
            double categoryMoney=0;
            for(Record r:rs){
                categoryMoney+=r.getSpend();
            }
            c.setRecordNumber(rs.size());
            c.setCategoryMoney(ToolUtil.formatDouble(categoryMoney));
        }
        //根据次数降序排列
        Collections.sort(cs,(c1,c2)->c2.getRecordNumber()-c1.getRecordNumber());
        return cs;
    }

    public LinkedList<Category> listSeclectedYM(Date date){
        LinkedList<Record> rs=recordDAO.list(DateUtil.getSpecifiedYMBegin(date),DateUtil.getSpecifiedYMEnd(date));
        LinkedList<Category> cs=categoryDAO.list();

        for(Category c:cs){
            int categoryNumber=0;
            double categoryMoney=0;
            for(Record r:rs) {
                if(c.getId()==r.getCid()){
                    categoryNumber++;
                    categoryMoney+=r.getSpend();
                }
            }
            c.setRecordNumber(categoryNumber);
            c.setCategoryMoney(ToolUtil.formatDouble(categoryMoney));
        }
        //根据次数降序排列
        Collections.sort(cs,(c1,c2)->c2.getRecordNumber()-c1.getRecordNumber());
        return cs;
    }

    public void add(String str){
        Category c=new Category();
        c.setName(str);
        categoryDAO.add(c);
    }


//    public void add(String str){
//        Category repet=categoryDAO.getByName(str);
//        if(repet.getName().equals(str)){
//            JOptionPane.showInputDialog("已有存在的分类");
//        }else {
//            Category c=new Category();
//            c.setName(str);
//            categoryDAO.add(c);
//        }
//    }

    public void delete(int id){
        categoryDAO.delete(id);
    }

    public void update(Category category){
        categoryDAO.update(category);
    }

    public boolean categoryExistRecord(Category category){
        int count=categoryDAO.getCountByID(category.getId());
        if(count==0)
            return false;
        else
            return true;
    }

    public boolean categoryExisted(String str){
        if(null!=categoryDAO.getByName(str.trim()))
            return true;
        else
            return false;
    }

    public int getCategoryTotal(){
        return categoryDAO.getTotal();
    }

    public Category addListData(LinkedList<Category> categories){
        Category category=null;
        int count=0;
        double totalMoney=0;
        for(Category c:categories){
            count+=c.getRecordNumber();
            totalMoney+=Double.valueOf(c.getCategoryMoney());
        }
        category=new Category();
        category.setName("总计");
        category.setRecordNumber(count);
        category.setCategoryMoney(ToolUtil.formatDouble(totalMoney));
        return category;
    }

    public Category getCategoryByRecord(Record record){
        return categoryDAO.get(record.getCid());
    }

}
