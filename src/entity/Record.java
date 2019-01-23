package entity;
 
import java.util.Date;
 
public class Record {

    private int id;
    private double spend;
    private int cid;
    private String comment;
    private Date date;
     
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getCid() {
        return cid;
    }
    public void setCid(int cid) {
        this.cid = cid;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public double getSpend() {
        return spend;
    }
    public void setSpend(double spend) {
        this.spend = spend;
    }
 
}