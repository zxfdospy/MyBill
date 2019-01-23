package gui.panel;

import gui.listener.RecoverListener;
import util.ColorUtil;
import util.GUIUtil;

import javax.swing.*;
import java.awt.*;

public class RecoverPanel extends WorkingPanel {

    public static RecoverPanel instance=new RecoverPanel();

    public JButton bRecover=new JButton("恢复");


    private RecoverPanel(){
        setLayout(new FlowLayout());
        GUIUtil.setColor(ColorUtil.blueColor,bRecover);
        add(bRecover);
        addListener();
    }

    @Override
    public void updateData() {

    }

    @Override
    public void addListener() {
        RecoverListener listener = new RecoverListener();
        bRecover.addActionListener(listener);
    }
}
