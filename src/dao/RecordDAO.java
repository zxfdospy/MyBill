package dao;

import entity.Record;
import util.DBUtil;
import util.DateUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Date;

public class RecordDAO {

    public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "select count(*) from record";

            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return total;
    }

    public void add(Record record) {

        String sql = "insert into record values(null,?,?,?,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
            ps.setDouble(1, record.getSpend());
            ps.setInt(2, record.getCid());
            ps.setString(3, record.getComment());
            ps.setDate(4, DateUtil.util2sql(record.getDate()));

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                record.setId(id);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public void update(Record record) {

        String sql = "update record set spend= ?, cid= ?, comment =?, date = ? where id = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setDouble(1, record.getSpend());
            ps.setInt(2, record.getCid());
            ps.setString(3, record.getComment());
            ps.setDate(4, DateUtil.util2sql(record.getDate()));
            ps.setInt(5, record.getId());

            ps.execute();

        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    public void delete(int id) {

        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "delete from record where id = " + id;

            s.execute(sql);

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public Record get(int id) {
        Record record = null;

        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "select * from record where id = " + id;

            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {
                record = new Record();
                double spend = rs.getDouble("spend");
                int cid = rs.getInt("cid");
                String comment = rs.getString("comment");
                Date date = rs.getDate("date");

                record.setSpend(spend);
                record.setCid(cid);
                record.setComment(comment);
                record.setDate(date);
                record.setId(id);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return record;
    }

    public LinkedList<Record> list() {
        return list(0, Short.MAX_VALUE);
    }

    public LinkedList<Record> list(int start, int count) {
        LinkedList<Record> records = new LinkedList<Record>();

        String sql = "select * from record order by date desc limit ?,? ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, start);
            ps.setInt(2, count);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Record record = new Record();
                int id = rs.getInt("id");
                double spend = rs.getDouble("spend");
                int cid = rs.getInt("cid");

                String comment = rs.getString("comment");
                Date date = rs.getDate("date");

                record.setSpend(spend);
                record.setCid(cid);
                record.setComment(comment);
                record.setDate(date);
                record.setId(id);
                records.add(record);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return records;
    }

    public LinkedList<Record> list(int cid) {
        LinkedList<Record> records = new LinkedList<Record>();

        String sql = "select * from record where cid = ?";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, cid);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Record record = new Record();
                int id = rs.getInt("id");
                double spend = rs.getDouble("spend");


                String comment = rs.getString("comment");
                Date date = rs.getDate("date");

                record.setSpend(spend);
                record.setCid(cid);
                record.setComment(comment);
                record.setDate(date);
                record.setId(id);
                records.add(record);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return records;
    }

    public LinkedList<Record> listToday(){
        return list(DateUtil.today());
    }


    public LinkedList<Record> list(Date day) {
        LinkedList<Record> records = new LinkedList<Record>();
        String sql = "select * from record where date =? order by date desc";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setDate(1, DateUtil.util2sql(day));

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Record record = new Record();
                int id = rs.getInt("id");
                double spend = rs.getDouble("spend");
                int cid = rs.getInt("cid");

                String comment = rs.getString("comment");
                Date date = rs.getDate("date");

                record.setSpend(spend);
                record.setCid(cid);
                record.setComment(comment);
                record.setDate(date);
                record.setId(id);
                records.add(record);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return records;
    }


    public LinkedList<Record> list(Date start, Date end) {
        LinkedList<Record> records = new LinkedList<Record>();
        String sql = "select * from record where date >=? and date <= ? order by date desc";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setDate(1, DateUtil.util2sql(start));
            ps.setDate(2, DateUtil.util2sql(end));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Record record = new Record();
                int id = rs.getInt("id");
                int cid = rs.getInt("cid");
                double spend = rs.getDouble("spend");

                String comment = rs.getString("comment");
                Date date = rs.getDate("date");

                record.setSpend(spend);
                record.setCid(cid);
                record.setComment(comment);
                record.setDate(date);
                record.setId(id);
                records.add(record);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return records;
    }

    public LinkedList<Record> listThisMonth(){
        return list(DateUtil.thismonthBegin(),DateUtil.thismonthEnd());
    }


}