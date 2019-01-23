package gui.model;

import entity.Category;
import entity.Record;
import service.CategoryService;
import service.RecordService;
import test.ExCache;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DetailRecordTableModel extends AbstractTableModel {

    String[] columNames=new String[]{"分类","金额","备注","日期"};

    public LinkedList<Record> cs=new LinkedList<>();

    public DetailRecordTableModel(){
        cs=new RecordService().listToday();
    }

    @Override
    public String getColumnName(int column) {
        return columNames[column];
    }

    @Override
    public int getRowCount() {
        return cs.size();
    }

    @Override
    public int getColumnCount() {
        return columNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Record c=cs.get(rowIndex);
        if(0==columnIndex)
            return new RecordService().getCategoryName(c);
        if(1==columnIndex)
            return c.getSpend();
        if(2==columnIndex)
            return c.getComment();
        if(3==columnIndex)
            return c.getDate();
        return null;
    }

    private static void init(List<ExCache> c){
        c.add(new ExCache("买东西",395.87,"买蓝牙耳机","2019-01-01"));
        c.add(new ExCache("吃",27.78,"瓜子花生","2019-01-02"));
        c.add(new ExCache("买东西",876,"买锁","2018-12-30"));

    }
}
