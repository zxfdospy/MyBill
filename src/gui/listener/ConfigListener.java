package gui.listener;

import gui.panel.ConfigPanel;
import service.ConfigService;
import util.GUIUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ConfigListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        ConfigPanel p=ConfigPanel.instance;
        if(!GUIUtil.checkNumber(p.tfBudget,"本月预算"))
            return;
        String mysql=p.tfMySql.getText().trim();
        if(0!=mysql.length()){
            File commandFile=new File(mysql,"bin/mysql.exe");
            System.out.println(commandFile.getAbsolutePath());
            if(!commandFile.exists()) {
                JOptionPane.showMessageDialog(p, "MySQL路径不正确");
                p.tfMySql.grabFocus();
                return;
            }
        }

        ConfigService cs=new ConfigService();
        cs.update(ConfigService.budget,p.tfBudget.getText());
        cs.update(ConfigService.mysqlPath,mysql);

        JOptionPane.showMessageDialog(p,"配置修改成功");
    }
}
