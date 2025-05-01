/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bll;
import dal.TuyenDAO;
import java.sql.SQLException;
import java.util.List;

public class TuyenService {
    private TuyenDAO tuyenDAO;

    public TuyenService() {
        this.tuyenDAO = new TuyenDAO();
    }

    public List<String> getAllDiemDi() throws SQLException {
        return tuyenDAO.getAllDiemDi();
    }

    public List<String> getAllDiemDen() throws SQLException {
        return tuyenDAO.getAllDiemDen();
    }
}