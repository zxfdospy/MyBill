package gui.panel;

import entity.Record;
import gui.listener.ReportListener;
import gui.model.SelectMonthComBoxModel;
import service.RecordService;
import service.ReportService;
import util.ChartUtil;
import util.ColorUtil;
import util.GUIUtil;


import java.util.LinkedList;
import java.util.List;
import javax.swing.*;
import java.awt.*;

public class ReportPanel extends WorkingPanel{

    public static ReportPanel instance=new ReportPanel();


    JLabel lreportselectmonth=new JLabel("选择月份");

    public SelectMonthComBoxModel smcbmreport=new SelectMonthComBoxModel();
    public JComboBox<String> cbReport=new JComboBox<>(smcbmreport);

    public JLabel lReport=new JLabel();



    private ReportPanel(){
        setLayout(new BorderLayout());
        add(north(),BorderLayout.NORTH);
        LinkedList<Record> rs=new ReportService().listThisMonthRecords();
        Image i= ChartUtil.getImage(rs,(int)(SpendPanel.instance.getWidth()*0.9),(int)(SpendPanel.instance.getHeight()*0.85));
        ImageIcon icon=new ImageIcon(i);
        lReport.setIcon(icon);
        GUIUtil.setLableCenter(lReport);
        add(lReport,BorderLayout.CENTER);
        addListener();

    }

    private JPanel north(){
        JPanel p=new JPanel();
        p.setLayout(new FlowLayout());
        p.add(lreportselectmonth);
        GUIUtil.setColor(ColorUtil.grayColor,lreportselectmonth);
        p.add(cbReport);
        return p;
    }


    @Override
    public void updateData() {

    }

    @Override
    public void addListener() {
        ReportListener listener=new ReportListener();
        cbReport.addActionListener(listener);
    }
}
