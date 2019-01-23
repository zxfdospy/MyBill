package gui.model;

import service.DateService;
import util.DateUtil;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SelectMonthComBoxModel implements ComboBoxModel<String> {

//    public List<String> cs=new ArrayList<>();
    public List<String> cs=new DateService().listYYYYMM();
    String c=DateUtil.date2YYYYMM(new Date());
    public SelectMonthComBoxModel(){
        if(cs.size()==0) {
            cs.add(c);
            c=cs.get(0);
        }
    }



//    public List<String> cs=new ArrayList<>();
//    String c=null;

//    public List<String> cs=new ArrayList<>();
//    String c;
//
//    public SelectMonthComBoxModel(){
//        cs.add("2019年1月");
//        cs.add("2018年12月");
//        cs.add("2018年11月");
//        cs.add("2018年10月");
//        c=cs.get(0);
//    }

    @Override
    public void setSelectedItem(Object anItem) {
        c=(String)anItem;
    }

    @Override
    public Object getSelectedItem() {
        if(!cs.isEmpty())
            return c;
        else
            return null;
    }

    @Override
    public int getSize() {
        return cs.size();
    }

    @Override
    public String getElementAt(int index) {
        return cs.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {

    }

    @Override
    public void removeListDataListener(ListDataListener l) {

    }
}
