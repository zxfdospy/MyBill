package gui.panel;

import gui.listener.ConfigListener;
import service.ConfigService;
import util.ColorUtil;
import util.GUIUtil;

import javax.swing.*;
import java.awt.*;

public class ConfigPanel extends WorkingPanel{

    public static ConfigPanel instance=new ConfigPanel();

    public JButton bConfig=new JButton("设置");

    JLabel lBudget=new JLabel("本月预算");
    JLabel lMySqlPath=new JLabel("MySql安装目录");

    public JTextField tfBudget=new JTextField("");
    public JTextField tfMySql=new JTextField("");
    private ConfigPanel(){
        setLayout(new BorderLayout());
        add(north(),BorderLayout.NORTH);
        add(center(),BorderLayout.CENTER);
        addListener();
    }

    private JPanel north(){
        JPanel p=new JPanel();
        int gap=50;
        p.setLayout(new GridLayout(4,1,gap,gap));
        p.add(lBudget);
        p.add(tfBudget);
        p.add(lMySqlPath);
        p.add(tfMySql);
        GUIUtil.setColor(ColorUtil.grayColor,lBudget,lMySqlPath);
        GUIUtil.setFontSize(1,14,lBudget,lMySqlPath);
        return p;
    }

    private JPanel center(){
        JPanel p=new JPanel();
        p.add(bConfig);
        GUIUtil.setColor(ColorUtil.blueColor,bConfig);
        return p;
    }

    @Override
    public void updateData() {
        tfBudget.setText(new ConfigService().get(ConfigService.budget));
        tfMySql.setText(new ConfigService().get(ConfigService.mysqlPath));
        tfBudget.grabFocus();
    }

    public void addListener(){
        ConfigListener listener=new ConfigListener();
        bConfig.addActionListener(listener);
    }
}
