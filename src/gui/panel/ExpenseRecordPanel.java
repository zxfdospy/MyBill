package gui.panel;

import com.sun.deploy.panel.JHighDPITable;
import entity.Category;
import entity.Record;
import gui.listener.ExpenseRecordJButtonListener;
import gui.listener.ExpenseRecordJCheckListener;
import gui.model.CategoryComBoxModel;
import gui.model.DetailRecordTableModel;
import org.jdesktop.swingx.JXBusyLabel;
import org.jdesktop.swingx.JXDatePicker;
import service.RecordService;
import sun.swing.table.DefaultTableCellHeaderRenderer;
import test.Cache;
import util.ColorUtil;
import util.GUIUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class ExpenseRecordPanel extends WorkingPanel{
    static {
        GUIUtil.useLNF();
    }

    public static ExpenseRecordPanel instance=new ExpenseRecordPanel();

//    private JLabel lERSearchWay=new JLabel("请选择查询方式");
//    private JLabel lERCategory=new JLabel("分类");
    private JLabel lERDate=new JLabel("日期");
    private JLabel lEPSpe=new JLabel("-");

//    public JRadioButton selectCategory=new JRadioButton("分类");
//    public JRadioButton selectDate=new JRadioButton("日期");

    public CategoryComBoxModel exccbm=new CategoryComBoxModel();
    public JComboBox<Category> excb=new JComboBox<>(exccbm);

    public JXDatePicker exDateStart=new JXDatePicker(new Date());
    public JXDatePicker exDateEnd=new JXDatePicker(new Date());

    public JButton bExSearch=new JButton("查询");
    public JCheckBox checkAllCategory=new JCheckBox("全部分类");


    public DetailRecordTableModel drtm=new DetailRecordTableModel();
    public JTable tExRecord=new JTable(drtm);

    public JCheckBox searchAll=new JCheckBox("查询全部");
    public JButton bExEdit=new JButton("修改");
    public JButton bExDelete=new JButton("删除");

//    public boolean searchAll=false;

    private ExpenseRecordPanel(){
        setLayout(new BorderLayout());
        add(north(),BorderLayout.NORTH);
        add(center(),BorderLayout.CENTER);
        add(south(),BorderLayout.SOUTH);
        addListener();
    }


    @Override
    public void updateData() {
        LinkedList<Record> selectDateRecords = new RecordService().listDate2Date(exDateStart.getDate(), exDateEnd.getDate());
        if(checkAllCategory.isSelected())
            drtm.cs=selectDateRecords;
        else
            drtm.cs=new RecordService().getRecordsByCategory(selectDateRecords,(Category)excb.getSelectedItem());
        if(searchAll.isSelected()){
            drtm.cs=new RecordService().listAll();
        }
        tExRecord.updateUI();
    }

    @Override
    public void addListener() {
        ExpenseRecordJCheckListener jCheckListener=new ExpenseRecordJCheckListener();
        ExpenseRecordJButtonListener jButtonListener=new ExpenseRecordJButtonListener();
        checkAllCategory.addActionListener(jCheckListener);
        bExSearch.addActionListener(jButtonListener);
        searchAll.addActionListener(jCheckListener);
        bExEdit.addActionListener(jButtonListener);
        bExDelete.addActionListener(jButtonListener);

    }

    public Record getSeclectedRecord(){
        int index=tExRecord.getSelectedRow();
        return drtm.cs.get(index);
    }



    private JPanel north(){
        JPanel p=new JPanel();
        p.setLayout(new FlowLayout());
//        p.add(lERCategory);
        p.add(excb);
        excb.setEnabled(false);
        p.add(checkAllCategory);
        checkAllCategory.setSelected(true);
        p.add(lERDate);
        p.add(exDateStart);
        p.add(lEPSpe);
        p.add(exDateEnd);
        p.add(bExSearch);
        GUIUtil.setColor(ColorUtil.grayColor,lERDate,lEPSpe);
        GUIUtil.setColor(ColorUtil.blueColor,bExSearch);
//        GUIUtil.setFontSize(1,14,lERSearchWay,lERCategory,lERDate);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
        exDateStart.setFormats(formatter);
        exDateEnd.setFormats(formatter);
        return p;
    }

    private JScrollPane center(){
        JScrollPane p=new JScrollPane(tExRecord);

        tExRecord.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        tExRecord.setDefaultRenderer(Object.class, r);

        DefaultTableCellHeaderRenderer hr = new DefaultTableCellHeaderRenderer();
        hr.setHorizontalAlignment(JLabel.CENTER);
        tExRecord.getTableHeader().setDefaultRenderer(hr);

        return p;
    }

    private JPanel south(){
        JPanel p=new JPanel();
        p.setLayout(new FlowLayout());
        p.add(searchAll);
        p.add(bExEdit);
        p.add(bExDelete);
        GUIUtil.setColor(ColorUtil.blueColor,bExDelete,bExEdit,searchAll);
        return p;
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(ExpenseRecordPanel.instance);
    }


}

