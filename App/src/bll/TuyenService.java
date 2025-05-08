package bll;

import dal.TuyenDAO;
import model.Tuyen;
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

    public boolean createTuyen(Tuyen tuyen) throws SQLException {
        return tuyenDAO.createTuyen(tuyen);
    }

    public List<Tuyen> getAllTuyen() throws SQLException {
        return tuyenDAO.getAllTuyen();
    }

    public Tuyen getTuyenByMaTuyen(int maTuyen) throws SQLException {
        return tuyenDAO.getTuyenByMaTuyen(maTuyen);
    }

    public boolean updateTuyen(Tuyen tuyen) throws SQLException {
        return tuyenDAO.updateTuyen(tuyen);
    }

    public boolean deleteTuyen(int maTuyen) throws SQLException {
        return tuyenDAO.deleteTuyen(maTuyen);
    }
}