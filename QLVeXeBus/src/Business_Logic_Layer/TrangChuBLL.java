package Business_Logic_Layer;

import Data_Access_Layer.TrangChuDAO;
import java.util.ArrayList;

public class TrangChuBLL {
    private TrangChuDAO trangChuDAO;

    public TrangChuBLL() {
        trangChuDAO = new TrangChuDAO();
    }

    public ArrayList<String> timKiemChuyenDi(String tu, String den, String ngay) {
        // Kiểm tra đầu vào
        if (tu == null || den == null || ngay == null || tu.isEmpty() || den.isEmpty() || ngay.isEmpty()) {
            return new ArrayList<>();
        }
        return trangChuDAO.timKiemChuyenDi(tu, den, ngay);
    }

    public ArrayList<String> getChuyenDiNoiBat() {
        return trangChuDAO.layDanhSachChuyenDiNoiBat();
    }
}