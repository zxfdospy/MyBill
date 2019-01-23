package gui.listener;

import entity.Category;
import entity.Record;
import gui.dialog.ExpenseRecordEdit;
import gui.frame.MainFrame;
import gui.panel.ExpenseRecordPanel;
import service.RecordService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.LinkedList;

public class ExpenseRecordJButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        ExpenseRecordPanel p=ExpenseRecordPanel.instance;
        JButton b=(JButton)e.getSource();
        if(b==p.bExSearch){
            if(p.exDateEnd.getDate().getTime()<p.exDateStart.getDate().getTime()){
                JOptionPane.showMessageDialog(p,"起止日期不合法");
                p.exDateStart.setDate(new Date());
                return;
            }

        }

        if(b==p.bExEdit){
            Record r=p.getSeclectedRecord();
            ExpenseRecordEdit edit=new ExpenseRecordEdit(r);
            edit.setVisible(true);
        }
        if(b==p.bExDelete){
            if(JOptionPane.OK_OPTION!=JOptionPane.showConfirmDialog(p,"确认删除?"))
                return;
            new RecordService().delete(p.getSeclectedRecord());
        }

        p.updateData();
    }
}
