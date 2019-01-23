package gui.listener;

import entity.Category;
import entity.Record;
import gui.panel.CategoryPrePanel;
import gui.panel.MainPanel;
import gui.panel.RecordPanel;
import service.RecordService;
import util.GUIUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class RecordListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        RecordPanel p= RecordPanel.instance;
        if(0==p.ccbmRecord.getSize()){
            JOptionPane.showMessageDialog(p, "暂无消费分类，无法添加，请先增加消费分类");
            MainPanel.instance.workingPanel.show(CategoryPrePanel.instance);
            return;
        }

        if(p.tfSpend.getText().length()==0) {
            JOptionPane.showMessageDialog(p, "输入为空");
            return;
        }
        if(GUIUtil.checkZero(p.tfSpend,"花费金额")){
           return;
        }
        double spend=Double.parseDouble(p.tfSpend.getText().trim());
        Category c=(Category)p.cbCategory.getSelectedItem();
        String comment=p.tfComment.getText().trim();
        Date date=p.datepick.getDate();
        new RecordService().add(spend,c,comment,date);
        JOptionPane.showMessageDialog(p,"添加成功");
        p.updateData();

    }
}
