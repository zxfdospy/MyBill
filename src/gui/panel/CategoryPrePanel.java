package gui.panel;

import entity.Category;
import gui.listener.CategoryPreJButtonListener;
import gui.listener.CategoryPreJCheckListener;
import gui.model.CategoryComBoxModel;
import gui.model.CategoryPreTableModel;
import gui.model.SelectMonthComBoxModel;
import org.jdesktop.swingx.JXDatePicker;
import service.CategoryService;
import service.DateService;
import service.RecordService;
import sun.swing.table.DefaultTableCellHeaderRenderer;
import util.ColorUtil;
import util.DateUtil;
import util.GUIUtil;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class CategoryPrePanel extends WorkingPanel {
    static {
        GUIUtil.useLNF();
    }

    public static CategoryPrePanel instance=new CategoryPrePanel();

    public SelectMonthComBoxModel categoryPresmcbm=new SelectMonthComBoxModel();
    public JComboBox<String> cbPreMonth=new JComboBox<>(categoryPresmcbm);
    public CategoryPreTableModel cptm=new CategoryPreTableModel();
    public JTable tCategoryPre=new JTable(cptm);


    public JCheckBox checkAll=new JCheckBox("全部");
    public JButton bCPreAdd=new JButton("增加");
    public JButton bCPreEdit=new JButton("编辑");
    public JButton bCPreDelete=new JButton("删除");

    public JButton bCPreSearch=new JButton("搜索");


    private CategoryPrePanel(){
        setLayout(new BorderLayout());
        add(north(),BorderLayout.NORTH);
        add(center(),BorderLayout.CENTER);
        add(south(),BorderLayout.SOUTH);
        addListener();
    }

    @Override
    public void updateData(){
        if(new CategoryService().getCategoryTotal()==0)
        {
            String name=JOptionPane.showInputDialog(instance,"暂无分类，请新建");
            if(name==null) {
                cptm.cs=new CategoryService().listAll();
                tCategoryPre.updateUI();
                bCPreEdit.setEnabled(false);
                bCPreDelete.setEnabled(false);
                return;
            }
            if (0 == name.length()) {
                JOptionPane.showMessageDialog(instance, "分类名称不能为空");
                return;
            }
            new CategoryService().add(name);
        }
        bCPreEdit.setEnabled(true);
        bCPreDelete.setEnabled(true);
        if (checkAll.isSelected()) {
            cptm.cs = new CategoryService().listAll();
        } else {
            String yearAndMonth = (String) cbPreMonth.getSelectedItem();
            cptm.cs = new CategoryService().listSeclectedYM(DateUtil.getDateByStr(yearAndMonth));
        }
        Category sum=new CategoryService().addListData(cptm.cs);
        cptm.cs.add(0,sum);
        tCategoryPre.updateUI();
//        tCategoryPre.getSelectionModel().setSelectionInterval(0,0);
        if(new RecordService().getTotal()!=0) {
            List<String> a=new DateService().listYYYYMM();
            if(categoryPresmcbm.cs.size() != a.size()) {
                categoryPresmcbm.cs=a;
                cbPreMonth.updateUI();
            }
        }
    }

    public void addListener(){
        CategoryPreJButtonListener blistener=new CategoryPreJButtonListener();
        CategoryPreJCheckListener checkListener=new CategoryPreJCheckListener();
        bCPreSearch.addActionListener(blistener);
        checkAll.addActionListener(checkListener);
        bCPreAdd.addActionListener(blistener);
        bCPreEdit.addActionListener(blistener);
        bCPreDelete.addActionListener(blistener);
    }



    private JPanel north(){
        JPanel p1=new JPanel(new BorderLayout());
        JPanel p2=new JPanel(new FlowLayout());
        JLabel lMonth=new JLabel("月份");
        GUIUtil.setColor(ColorUtil.grayColor,lMonth);
        lMonth.setFont(new FontUIResource("微软雅黑",1,16));
        p2.add(lMonth);
        p2.add(cbPreMonth);
        cbPreMonth.setEnabled(false);
        p2.add(bCPreSearch);
        bCPreSearch.setEnabled(false);
        JPanel p3=new JPanel(new FlowLayout());
        p3.add(checkAll);
        checkAll.setSelected(true);

        p1.add(p2,BorderLayout.CENTER);
        p1.add(p3,BorderLayout.EAST);



        return p1;
    }

    private JScrollPane center(){
        JScrollPane p=new JScrollPane(tCategoryPre);

        tCategoryPre.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);


        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        tCategoryPre.setDefaultRenderer(Object.class, r);

        DefaultTableCellHeaderRenderer hr = new DefaultTableCellHeaderRenderer();
        hr.setHorizontalAlignment(JLabel.CENTER);
        tCategoryPre.getTableHeader().setDefaultRenderer(hr);


        return p;
    }

    private JPanel south(){
        JPanel p=new JPanel();
        GUIUtil.setColor(ColorUtil.blueColor,bCPreAdd,bCPreDelete,bCPreEdit);
        p.add(bCPreAdd);
        p.add(bCPreEdit);
        p.add(bCPreDelete);
        return p;
    }
    
    public Category getSeclectedCategory(){
        int index=tCategoryPre.getSelectedRow();
        return cptm.cs.get(index);
    }







    public static void main(String[] args) {
        GUIUtil.showPanel(CategoryPrePanel.instance);
    }



}
