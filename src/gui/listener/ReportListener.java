package gui.listener;

import entity.Record;
import gui.panel.ReportPanel;
import service.RecordService;
import service.ReportService;
import util.ChartUtil;
import util.GUIUtil;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReportListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        ReportPanel p=ReportPanel.instance;
        if(e.getActionCommand().equals("comboBoxChanged")){
            LinkedList<Record> rs=new ReportService().listYYYYMMMonthRecords((String)p.cbReport.getSelectedItem());
            Image i= ChartUtil.getImage(rs,(int)(p.getWidth()*0.9),(int)(p.getHeight()*0.85));
            ImageIcon icon=new ImageIcon(i);
            p.lReport.setIcon(icon);
            GUIUtil.setLableCenter(p.lReport);
        }
    }
}
