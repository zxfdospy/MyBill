package service;

import java.util.*;

import dao.RecordDAO;
import entity.Record;
import util.DateUtil;

public class ReportService {

    /**
     * 获取某一天的消费金额
     * @param d
     * @param monthRawData
     * @return
     */
    public double getDaySpend(Date d, LinkedList<Record> monthRawData){
        double daySpend = 0;
        for (Record record : monthRawData) {
            if(record.getDate().equals(d))
                daySpend+=record.getSpend();
        }
        return daySpend;
    }

    /**
     * 获取一个月的消费记录集合
     * @return
     */
    public LinkedList<Record> listThisMonthRecords() {
        RecordDAO dao= new RecordDAO();
        LinkedList<Record> monthRawData= dao.list(DateUtil.thismonthBegin(),DateUtil.thismonthEnd());
        LinkedList<Record> result= new LinkedList<>();
        Date monthBegin = DateUtil.thismonthBegin();
        int monthTotalDay = DateUtil.thisMonthTotalDay();
        Calendar c = Calendar.getInstance();
        for (int i = 0; i < monthTotalDay; i++) {
            Record r = new Record();
            c.setTime(monthBegin);
            c.add(Calendar.DATE, i);
            Date eachDayOfThisMonth=c.getTime() ;
            double daySpend = getDaySpend(eachDayOfThisMonth,monthRawData);
            r.setSpend(daySpend);
            result.add(r);
        }
        return result;
    }

    public LinkedList<Record> listYYYYMMMonthRecords(String YYYYMM) {
        RecordDAO dao= new RecordDAO();
        LinkedList<Record> monthRawData= dao.list(DateUtil.getSpecifiedYMBegin(YYYYMM),DateUtil.getSpecifiedYMEnd(YYYYMM));
        LinkedList<Record> result= new LinkedList<>();
        Date monthBegin = DateUtil.getSpecifiedYMBegin(YYYYMM);
        int monthTotalDay = DateUtil.selectedMonthTotalDay(YYYYMM);
        Calendar c = Calendar.getInstance();
        for (int i = 0; i < monthTotalDay; i++) {
            Record r = new Record();
            c.setTime(monthBegin);
            c.add(Calendar.DATE, i);
            Date eachDayOfThisMonth=c.getTime() ;
            double daySpend = getDaySpend(eachDayOfThisMonth,monthRawData);
            r.setSpend(daySpend);
            result.add(r);
        }
        return result;
    }

}