package service;

import dao.RecordDAO;
import entity.Record;
import gui.page.SpendPage;
import util.DateUtil;

import java.util.LinkedList;

public class SpendService {

    RecordDAO recordDAO=new RecordDAO();

    public SpendPage getSpendPage(){
        LinkedList<Record> thisMonthRecords=recordDAO.listThisMonth();
        LinkedList<Record> todayRecords=recordDAO.listToday();

        int thisMonthTotalDay= DateUtil.thisMonthTotalDay();

        double thisMonthSpend=0;
        double todaySpend=0;
        double avgSpendPerDay=0;
        double monthAvailable=0;
        double dayAvgAvailable=0;
        int monthLeftDay=0;
        int usagePercentage=0;

        double thisMonthBudget=new ConfigService().getDoubleBudget();

        for(Record r:thisMonthRecords){
            thisMonthSpend+=r.getSpend();
        }

        for(Record r:todayRecords){
            todaySpend+=r.getSpend();
        }

        avgSpendPerDay=thisMonthSpend/thisMonthTotalDay;

        monthAvailable=thisMonthBudget-thisMonthSpend;

        monthLeftDay=DateUtil.thisMonthLeftDay();

        dayAvgAvailable=monthAvailable/monthLeftDay;

        usagePercentage=(int)(thisMonthSpend*100/thisMonthBudget);

        return new SpendPage(thisMonthSpend,todaySpend,avgSpendPerDay,monthAvailable,dayAvgAvailable,monthLeftDay,usagePercentage);

    }
}
