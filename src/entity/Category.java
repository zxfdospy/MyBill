package entity;

public class Category {
    private int id;
    private String name;
    private int recordNumber;
    private String categoryMoney;

    public void setId(int id){
        this.id=id;
    }

    public void setName(String name){
        this.name=name;
    }

    public void setRecordNumber(int recordNumber){
        this.recordNumber=recordNumber;
    }

    public void setCategoryMoney(String categoryMoney){
        this.categoryMoney=categoryMoney;
    }

    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public int getRecordNumber(){
        return this.recordNumber;
    }

    public String getCategoryMoney(){
        return this.categoryMoney;
    }

    public String toString(){
        return this.name;
    }
}
