package gui.listener;

import dao.CategoryDAO;
import entity.Category;
import gui.panel.CategoryPrePanel;
import service.CategoryService;
import util.DateUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CategoryPreJButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        CategoryPrePanel p=CategoryPrePanel.instance;
        JButton b=(JButton)e.getSource();
//        if(b==p.bCPreSearch){
//
//        }

        if(b==p.bCPreAdd){
            String name = JOptionPane.showInputDialog("分类名称");
            if(name==null)
                return;
            if (0 == name.length()) {
                JOptionPane.showMessageDialog(p, "分类名称不能为空");
                return;
            }
            if(new CategoryService().categoryExisted(name)) {
                JOptionPane.showInputDialog("已有存在的分类");
                return;
            }
            new CategoryService().add(name);
        }

        if(b==p.bCPreDelete){
            Category c=p.getSeclectedCategory();
            if(new CategoryService().categoryExistRecord(c)){
                JOptionPane.showMessageDialog(p,"本分类下有消费记录存在，不能删除");
                return;
            }
            if(JOptionPane.OK_OPTION!=JOptionPane.showConfirmDialog(p,"确认删除?"))
                return;
            new CategoryService().delete(c.getId());
        }

        if(b==p.bCPreEdit){
            Category c=p.getSeclectedCategory();
            String name=JOptionPane.showInputDialog("请输入新的分类名称",c.getName());
            if(name==null)
                return;
            if(name.trim().length()==0){
                JOptionPane.showMessageDialog(p,"名称不能为空");
                return;
            }
            if(new CategoryService().categoryExisted(name)) {
                JOptionPane.showMessageDialog(p,"已有存在的分类");
                return;
            }
            c.setName(name);
            new CategoryService().update(c);
        }
        p.updateData();


    }
}
