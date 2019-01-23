package gui.listener;

import entity.Category;
import entity.Record;
import gui.panel.ExpenseRecordPanel;
import service.RecordService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class ExpenseRecordJCheckListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        ExpenseRecordPanel p=ExpenseRecordPanel.instance;
        JCheckBox c=(JCheckBox)e.getSource();
        if(c==p.checkAllCategory) {
            if (p.checkAllCategory.isSelected()) {
                p.excb.setEnabled(false);
                p.updateData();
            } else {
                p.excb.setEnabled(true);
                p.updateData();
            }
        }

        if(c==p.searchAll){
            if(p.searchAll.isSelected()) {
                p.checkAllCategory.setEnabled(false);
                p.excb.setEnabled(false);
                p.exDateStart.setEnabled(false);
                p.exDateEnd.setEnabled(false);
                p.bExSearch.setEnabled(false);
                p.updateData();
            }else {
                p.checkAllCategory.setEnabled(true);
                p.exDateStart.setEnabled(true);
                p.exDateEnd.setEnabled(true);
                p.bExSearch.setEnabled(true);
                p.updateData();
            }
        }
    }
}
