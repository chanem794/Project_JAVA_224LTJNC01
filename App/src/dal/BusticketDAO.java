/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.*;
import java.util.Vector;
import raven.application.form.other.BusTicketForm;
/**
 *
 * @author huynh
 */
public class BusticketDAO {
    public Connection conn=null;
    public Statement stm =null;
    public ResultSet rs= null;

    public BusticketDAO() throws SQLException {
    }
    public void connect() throws SQLException
    {
        String sql ="select * from xe";
        rs=stm.executeQuery(sql);
        while(rs.next())
        {
            BusTicketForm btf = new BusTicketForm();
            btf.setjLabel1(rs.getString(2));
            btf.setjLabel2(rs.getInt(10)+"");
            btf.setjLabel3(rs.getString(9));
            btf.setjLabel4(rs.getString(8));
            btf.setjLabel5(rs.getString(4));
            btf.setjLabel6(rs.getString(5));
        }
    }
}
