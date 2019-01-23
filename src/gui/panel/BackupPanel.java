package gui.panel;

import gui.listener.BackupListener;
import util.ColorUtil;
import util.GUIUtil;

import javax.swing.*;
import java.awt.*;

public class BackupPanel extends WorkingPanel {

    public static BackupPanel instance=new BackupPanel();

    public JButton bBackup=new JButton("备份");


    private BackupPanel(){
        setLayout(new FlowLayout());
        GUIUtil.setColor(ColorUtil.blueColor,bBackup);
        add(bBackup);
        addListener();
    }


    @Override
    public void updateData() {

    }

    @Override
    public void addListener() {
        BackupListener listener = new BackupListener();
        bBackup.addActionListener(listener);
    }
}
