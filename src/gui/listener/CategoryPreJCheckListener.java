package gui.listener;

import gui.panel.CategoryPrePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CategoryPreJCheckListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        CategoryPrePanel p=CategoryPrePanel.instance;
        if(p.checkAll.isSelected()){
            p.bCPreSearch.setEnabled(false);
            p.cbPreMonth.setEnabled(false);
            p.updateData();
        }else {
            p.bCPreSearch.setEnabled(true);
            p.cbPreMonth.setEnabled(true);
            p.updateData();
        }
    }
}
