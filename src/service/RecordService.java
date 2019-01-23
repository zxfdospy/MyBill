package service;

        import dao.CategoryDAO;
        import dao.RecordDAO;
        import entity.Category;
        import entity.Record;
        import util.DateUtil;

        import java.util.Date;
        import java.util.LinkedList;
        import java.util.List;

public class RecordService {

    CategoryDAO categoryDAO=new CategoryDAO();
    RecordDAO recordDao=new RecordDAO();

    public void add(double spend, Category c, String comment, Date date){
        Record r=new Record();
        r.setSpend(spend);
        r.setCid(c.getId());
        r.setComment(comment);
        r.setDate(date);
        recordDao.add(r);
    }

    public LinkedList<Record> listSelectedYYYYMM(String YYYYMM){
        Date d=DateUtil.getDateByStr(YYYYMM);
        return recordDao.list(DateUtil.getSpecifiedYMBegin(d),DateUtil.getSpecifiedYMEnd(d));
    }

    public LinkedList<Record> listAll(){
        return recordDao.list();
    }

    public LinkedList<Record> listDate2Date(Date start,Date end){
        return recordDao.list(start,end);
    }

    public int getTotal(){
        return recordDao.getTotal();
    }

    public LinkedList<Record> listThisMonth(){
        return recordDao.listThisMonth();
    }

    public LinkedList<Record> listToday(){
        return recordDao.listToday();
    }


    public String getCategoryName(Record cid){
        Category c=categoryDAO.get(cid.getCid());
        return c.getName();
    }

    public LinkedList<Record> getRecordsByCategory(LinkedList<Record> records,Category category){
        LinkedList<Record> rs=new LinkedList<>();
        for(Record r:records){
            if(r.getCid()==category.getId()){
                rs.add(r);
            }
        }
        return rs;
    }

    public Record getRecordByID(int id){
        return recordDao.get(id);
    }

    public void update(Record record){
        recordDao.update(record);
    }

    public Record newRecord(int id,double spend, Category c, String comment, Date date){
        Record r=new Record();
        r.setId(id);
        r.setSpend(spend);
        r.setCid(c.getId());
        r.setComment(comment);
        r.setDate(date);
        return r;
    }

    public void delete(Record record){
        recordDao.delete(record.getId());
    }

}
