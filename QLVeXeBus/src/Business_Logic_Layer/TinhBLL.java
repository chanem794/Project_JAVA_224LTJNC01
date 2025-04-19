package Business_Logic_Layer;

import Data_Access_Layer.TinhDAO;
import java.util.ArrayList;

public class TinhBLL {
    private TinhDAO tinhDAO;

    public TinhBLL() {
        tinhDAO = new TinhDAO();
    }

    public ArrayList<String> getDanhSachTinh() {
        return tinhDAO.layDanhSachTinh();
    }
}