package bll;

import dal.DatChoDAO;
import model.DatCho;
import java.sql.SQLException;
import java.util.List;

public class DatChoService {
    private DatChoDAO datChoDAO;

    public DatChoService() {
        this.datChoDAO = new DatChoDAO();
    }

    public boolean createDatCho(DatCho datCho) throws SQLException {
        return datChoDAO.createDatCho(datCho);
    }

    public List<DatCho> getAllDatCho() throws SQLException {
        return datChoDAO.getAllDatCho();
    }

    public DatCho getDatChoByMaDatCho(int maDatCho) throws SQLException {
        return datChoDAO.getDatChoByMaDatCho(maDatCho);
    }

    public boolean updateDatCho(DatCho datCho) throws SQLException {
        return datChoDAO.updateDatCho(datCho);
    }

    public boolean deleteDatCho(int maDatCho) throws SQLException {
        return datChoDAO.deleteDatCho(maDatCho);
    }

    public List<DatCho> getDatChoByMaXe(int maXe) throws SQLException {
        return datChoDAO.getDatChoByMaXe(maXe);
    }
}