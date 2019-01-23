package gui.model;

import entity.Category;
import service.CategoryService;
import test.Cache;
import util.DateUtil;

import javax.swing.table.AbstractTableModel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class CategoryPreTableModel extends AbstractTableModel {

    String[] columnNames=new String[]{"消费分类","次数","总金额"};

    public LinkedList<Category> cs=new LinkedList<>();

//    public List<Cache> caches = new ArrayList<>();
//    public CategoryPreTableModel() {
//            init(caches);
//    }
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public int getRowCount() {
        return cs.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }


    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Category c=cs.get(rowIndex);
        if(0==columnIndex)
            return c.getName();
        if(1==columnIndex)
            return c.getRecordNumber();
        if(2==columnIndex)
            return c.getCategoryMoney();
        return null;
    }

    private static void init(List<Cache> a){
        a.add(new Cache("吃饭", 25, 6898.7));
        a.add(new Cache("买东西", 30, 19992.45));
    }
}
