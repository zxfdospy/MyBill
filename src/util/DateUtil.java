package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    static long millisecondsOfOneDay=1000*60*60*24;

    public static java.sql.Date util2sql(java.util.Date d){
        return new java.sql.Date(d.getTime());
    }

    public static Date today(){
        Calendar c=Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.HOUR,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        c.set(Calendar.MILLISECOND,0);
        return c.getTime();
    }

    public static Date thismonthBegin(){
        Calendar c=Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.DATE,1);
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        c.set(Calendar.MILLISECOND,0);
        return c.getTime();
    }

    public static Date thismonthEnd(){
        Calendar c=Calendar.getInstance();
        c.setTime(new Date());

        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        c.set(Calendar.MILLISECOND,0);

        c.set(Calendar.DATE,1);
        c.add(Calendar.MONTH,1);
        c.add(Calendar.SECOND,-1);

        return c.getTime();
    }

    public static Date getSpecifiedMonthBegin(String YYYYMM){
        Calendar c=Calendar.getInstance();
        Date date=DateUtil.getDateByStr(YYYYMM);
        c.setTime(date);

        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        c.set(Calendar.MILLISECOND,0);

        return c.getTime();
    }

    public static Date getSpecifiedMonthEnd(String YYYYMM){
        Calendar c=Calendar.getInstance();
        Date date=DateUtil.getDateByStr(YYYYMM);
        c.setTime(date);

        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        c.set(Calendar.MILLISECOND,0);

        c.add(Calendar.MONTH,1);
        c.add(Calendar.SECOND,-1);

        return c.getTime();
    }

    public static Date getSpecifiedYMBegin(String YYYYMM){
        Calendar c=Calendar.getInstance();
        Date date=DateUtil.getDateByStr(YYYYMM);
        c.setTime(date);
        c.set(Calendar.DATE,1);
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        c.set(Calendar.MILLISECOND,0);
        return c.getTime();
    }

    public static Date getSpecifiedYMBegin(Date date){
        Calendar c=Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DATE,1);
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        c.set(Calendar.MILLISECOND,0);
        return c.getTime();
    }


    public static Date getSpecifiedYMEnd(String YYYYMM){
        Calendar c=Calendar.getInstance();
        Date date=DateUtil.getDateByStr(YYYYMM);
        c.setTime(date);

        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        c.set(Calendar.MILLISECOND,0);

        c.set(Calendar.DATE,1);
        c.add(Calendar.MONTH,1);
        c.add(Calendar.SECOND,-1);
        return c.getTime();
    }

    public static Date getSpecifiedYMEnd(Date date){
        Calendar c=Calendar.getInstance();
        c.setTime(date);

        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        c.set(Calendar.MILLISECOND,0);

        c.set(Calendar.DATE,1);
        c.add(Calendar.MONTH,1);
        c.add(Calendar.SECOND,-1);
        return c.getTime();
    }


    public static int thisMonthTotalDay(){
        Calendar c=Calendar.getInstance();
        int day=c.getActualMaximum(Calendar.DATE);
        return day;
    }

    public static int selectedMonthTotalDay(String YYYYMM){
        Calendar c=Calendar.getInstance();
        Date date=DateUtil.getDateByStr(YYYYMM);
        c.setTime(date);
        int day=c.getActualMaximum(Calendar.DATE);
        return day;
    }


    public static int thisMonthLeftDay(){
        long lastDayMilliSeconds = thismonthEnd().getTime();
        long toDayMilliSeconds = today().getTime();
        return (int) ((lastDayMilliSeconds-toDayMilliSeconds)/millisecondsOfOneDay);
    }

    public static Date getDateByStr(String YYYYMM){
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM" );
        Date selectMonth= null;
        try {
            selectMonth = sdf.parse(YYYYMM);
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        return selectMonth;
    }

    public static String date2YYYYMM(Date date){
        SimpleDateFormat sdf1 =new SimpleDateFormat("yyyy-MM" );
        Date d1= new Date();
        return sdf1.format(d1);
    }

}
