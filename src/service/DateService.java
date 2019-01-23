package service;

import dao.CategoryDAO;
import dao.RecordDAO;
import entity.Record;
import util.ToolUtil;

import java.text.SimpleDateFormat;
import java.util.*;

public class DateService {

    CategoryDAO categoryDAO=new CategoryDAO();
    RecordDAO recordDAO=new RecordDAO();
     SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM" );
    //列出记录中出现的月份
    public List<String> listYYYYMM(){
        LinkedList<Record> rs=recordDAO.list();
        //降序排列
        Comparator<Record> c = new Comparator<Record>() {
            @Override
            public int compare(Record r1, Record r2) {
                if(r1.getDate().getTime()>=r2.getDate().getTime())
                    return -1;
                else
                    return 1;
            }
        };
        Collections.sort(rs,c);
        List<String> ds=new ArrayList<>();
        for(Record r:rs){
            ds.add(sdf.format(r.getDate()));
        }
        ToolUtil.removeDuplicate(ds);
        return ds;
    }



}
