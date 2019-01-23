package gui.panel;

import entity.Category;
import gui.listener.RecordListener;
import gui.model.CategoryComBoxModel;
import org.jdesktop.swingx.JXDatePicker;
import service.CategoryService;
import util.ColorUtil;
import util.GUIUtil;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RecordPanel extends WorkingPanel{
    static {GUIUtil.useLNF();}

    public static RecordPanel instance=new RecordPanel();

    public JLabel lSpend=new JLabel("花费¥");
    public JLabel lCategory=new JLabel("分类");
    public JLabel lComment=new JLabel("备注");
    public JLabel lDate=new JLabel("日期");

    public JTextField tfSpend=new JTextField("");

    public CategoryComBoxModel ccbmRecord=new CategoryComBoxModel();
    public JComboBox<Category> cbCategory=new JComboBox<>(ccbmRecord);
    public JTextField tfComment=new JTextField();
    public JXDatePicker datepick=new JXDatePicker(new Date());

    public JButton bSubmit=new JButton("记一笔");

    private RecordPanel(){
        setLayout(new BorderLayout());
        GUIUtil.setColor(ColorUtil.grayColor,lSpend,lCategory,lComment,lDate);
        GUIUtil.setLableCenter(lCategory,lComment,lDate,lSpend);
        GUIUtil.setColor(ColorUtil.blueColor,bSubmit);
        GUIUtil.setFontSize(1,16,lCategory,lComment,lDate,lSpend,cbCategory);
        GUIUtil.setFontSize(0,14,cbCategory);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd EEE");
        datepick.setFormats(formatter);

        add(center(),BorderLayout.CENTER);
        add(south(),BorderLayout.SOUTH);

        addListener();
    }


    @Override
    public void updateData() {
        if(new CategoryService().getCategoryTotal()==0){
//            JOptionPane.showMessageDialog(instance,"暂无分类，请先新建分类信息");
            MainPanel.instance.workingPanel.show(CategoryPrePanel.instance);
            return;
        }
        ccbmRecord.cs=new CategoryService().listAll();
//        ccbmRecord.c=ccbmRecord.cs.get(0);
//        cbCategory.updateUI();
        tfSpend.setText("");
        tfComment.setText("");
        tfSpend.grabFocus();
    }

    @Override
    public void addListener() {
        RecordListener listener=new RecordListener();
        bSubmit.addActionListener(listener);
    }



    public JPanel center(){
        JPanel p=new JPanel();
        int gap=40;
        p.setLayout(new GridLayout(4,2,gap,gap));
        p.add(lSpend);
        p.add(tfSpend);
        p.add(lCategory);
        p.add(cbCategory);
        p.add(lComment);
        p.add(tfComment);
        p.add(lDate);
        p.add(datepick);


        return p;
    }

    private JPanel south(){
        JPanel p=new JPanel();
        p.setLayout(new FlowLayout());
        p.add(bSubmit);
        return p;
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(RecordPanel.instance);
    }



}
