package gui.dialog;

import entity.Category;
import entity.Record;
import gui.frame.MainFrame;
import gui.model.CategoryComBoxModel;
import gui.panel.BackupPanel;
import gui.panel.ExpenseRecordPanel;
import gui.panel.RecordPanel;
import org.jdesktop.swingx.JXDatePicker;
import service.CategoryService;
import service.RecordService;
import util.ColorUtil;
import util.GUIUtil;
import util.ToolUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExpenseRecordEdit extends JDialog {

    public JLabel lSpend=new JLabel("花费¥");
    public JLabel lCategory=new JLabel("分类");
    public JLabel lComment=new JLabel("备注");
    public JLabel lDate=new JLabel("日期");

    public JTextField tfSpend=new JTextField("");

    public CategoryComBoxModel ccbmRecord=new CategoryComBoxModel();
    public JComboBox<Category> cbCategory=new JComboBox<>(ccbmRecord);
    public JTextField tfComment=new JTextField();
    public JXDatePicker datepick=new JXDatePicker(new Date());


    public JButton bEREdit=new JButton("提交");

    ExpenseRecordPanel expenseRecordPanel=ExpenseRecordPanel.instance;

    public ExpenseRecordEdit(Record record){
        this.setLayout(new BorderLayout());
        this.setModal(true);
        this.setLocationRelativeTo(expenseRecordPanel);
        tfSpend.setText(ToolUtil.formatDouble(record.getSpend()));
        //设置页面分类固定在此记录分类上
        ccbmRecord.c=(new CategoryService().getCategoryByRecord(record));
        cbCategory.updateUI();
        tfComment.setText(record.getComment());
        datepick.setDate(record.getDate());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd EEE");
        datepick.setFormats(formatter);
        this.add(center(),BorderLayout.CENTER);
        JPanel south=new JPanel();
        south.add(bEREdit);
        GUIUtil.setColor(ColorUtil.blueColor,bEREdit);
        this.add(south,BorderLayout.SOUTH);
        bEREdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tfSpend.getText().length()==0) {
                    JOptionPane.showMessageDialog(expenseRecordPanel, "输入为空");
                    return;
                }
                if(GUIUtil.checkZero(tfSpend,"花费金额")){
                    return;
                }
                int index=expenseRecordPanel.tExRecord.getSelectedRow();
                double spend=Double.parseDouble(tfSpend.getText().trim());
                Category c=(Category)cbCategory.getSelectedItem();
                String comment=tfComment.getText().trim();
                Date date=datepick.getDate();
                int id=record.getId();
                Record newRecord=new RecordService().newRecord(id,spend,c,comment,date);
                new RecordService().update(newRecord);
                JOptionPane.showMessageDialog(expenseRecordPanel,"修改成功");
                setVisible(false);
                expenseRecordPanel.drtm.cs.set(index,newRecord);
                expenseRecordPanel.updateData();
            }
        });
//        this.setPreferredSize(new Dimension(400,300));
        this.pack();
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
        GUIUtil.setColor(ColorUtil.grayColor,lSpend,lCategory,lComment,lDate);
        GUIUtil.setLableCenter(lCategory,lComment,lDate,lSpend);

        return p;
    }




    public static void main(String[] args) {
        ExpenseRecordEdit e=new ExpenseRecordEdit(new RecordService().getRecordByID(2));
        e.setVisible(true);
    }

}
