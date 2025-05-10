package bll;

import dal.XeDAO;
import model.Xe;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class XeService {
    private XeDAO xeDAO;

    public XeService() {
        this.xeDAO = new XeDAO();
    }

    public boolean createXe(Xe xe) throws SQLException {
        return xeDAO.createXe(xe);
    }

    public List<Xe> getAllXe() throws SQLException {
        return xeDAO.getAllXe();
    }

    public Xe getXeByMaXe(int maXe) throws SQLException {
        return xeDAO.getXeByMaXe(maXe);
    }

    public boolean updateXe(Xe xe) throws SQLException {
        return xeDAO.updateXe(xe);
    }

    public boolean deleteXe(int maXe) throws SQLException {
        return xeDAO.deleteXe(maXe);
    }

    public List<Xe> getXeByMaTuyen(int maTuyen) throws SQLException {
        return xeDAO.getXeByMaTuyen(maTuyen);
    }

    public List<Xe> findDiemDi(String searchText) throws SQLException {
        return xeDAO.findDiemDi(searchText);
    }

    public List<Xe> findDiemDen(String searchText) throws SQLException {
        return xeDAO.findDiemDen(searchText);
    }

    public String getPrice(String diemDi, String diemDen) throws SQLException {
        return xeDAO.getprice(diemDi, diemDen);
    }

    public List<Xe> ShowXe() throws SQLException {
        return xeDAO.showxe();
    }
    
}
