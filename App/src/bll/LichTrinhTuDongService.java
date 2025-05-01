package bll;

import dal.LichTrinhTuDongDAO;
import model.LichTrinhTuDong;
import java.sql.SQLException;
import java.util.List;

public class LichTrinhTuDongService {
    private LichTrinhTuDongDAO lichTrinhDAO;

    public LichTrinhTuDongService() {
        this.lichTrinhDAO = new LichTrinhTuDongDAO();
    }

    public boolean createLichTrinhTuDong(LichTrinhTuDong lichTrinh) throws SQLException {
        return lichTrinhDAO.createLichTrinhTuDong(lichTrinh);
    }

    public List<LichTrinhTuDong> getAllLichTrinhTuDong() throws SQLException {
        return lichTrinhDAO.getAllLichTrinhTuDong();
    }

    public LichTrinhTuDong getLichTrinhTuDongById(int maLichTrinhTuDong) throws SQLException {
        return lichTrinhDAO.getLichTrinhTuDongById(maLichTrinhTuDong);
    }

    public boolean updateLichTrinhTuDong(LichTrinhTuDong lichTrinh) throws SQLException {
        return lichTrinhDAO.updateLichTrinhTuDong(lichTrinh);
    }

    public boolean deleteLichTrinhTuDong(int maLichTrinhTuDong) throws SQLException {
        return lichTrinhDAO.deleteLichTrinhTuDong(maLichTrinhTuDong);
    }

    public List<LichTrinhTuDong> getLichTrinhTuDongByMaXe(int maXe) throws SQLException {
        return lichTrinhDAO.getLichTrinhTuDongByMaXe(maXe);
    }
}