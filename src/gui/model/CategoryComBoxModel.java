package gui.model;

import entity.Category;
import service.CategoryService;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CategoryComBoxModel implements ComboBoxModel<Category> {
    public LinkedList<Category> cs= new CategoryService().listAll();
    public Category c;
    public CategoryComBoxModel(){
        if(!cs.isEmpty())
            c=cs.get(0);
    }

//    public CategoryComBoxModel(){
//        cs.add("餐饮");
//        cs.add("交通");
//        cs.add("住宿");
//        cs.add("话费");
//        c=cs.get(0);
//    }

    @Override
    public void setSelectedItem(Object anItem) {
        c=(Category) anItem;
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
    public Category getElementAt(int index) {
        return cs.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {

    }

    @Override
    public void removeListDataListener(ListDataListener l) {

    }
}
