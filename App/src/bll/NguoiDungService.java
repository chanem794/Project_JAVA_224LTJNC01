package bll;

import dal.NguoiDungDAO;
import model.NguoiDung;
import util.OTPUtil;
import util.EmailUtil;
import java.sql.SQLException;
import java.util.Date;

public class NguoiDungService {
    private final NguoiDungDAO nguoiDungDAO;

    public NguoiDungService() {
        this.nguoiDungDAO = new NguoiDungDAO();
    }

    public boolean processLogin(String email) throws SQLException {
        if (!isValidEmail(email)) {
            return false;
        }
        if (nguoiDungDAO.existsByEmail(email)) {
            String otp = OTPUtil.generateOTP();
            if (nguoiDungDAO.updateOTP(email, otp)) {
                return EmailUtil.sendOTP(email, otp);
            }
        }
        return false;
    }

    public boolean processRegistration(String email) throws SQLException {
        if (!isValidEmail(email)) {
            return false;
        }
        if (!nguoiDungDAO.existsByEmail(email)) {
            nguoiDungDAO.insertUser(email);
        }
        String otp = OTPUtil.generateOTP();
        if (nguoiDungDAO.updateOTP(email, otp)) {
            return EmailUtil.sendOTP(email, otp);
        }
        return false;
    }

    public boolean validateOTP(String email, String otp) throws SQLException {
        NguoiDung nguoiDung = nguoiDungDAO.validateOTP(email, otp);
        if (nguoiDung != null && OTPUtil.isOTPValid(nguoiDung.getOtpTaoThoiGian())) {
            nguoiDungDAO.clearOTP(email);
            return true;
        }
        return false;
    }

    public boolean needsUserInfo(String email) throws SQLException {
        NguoiDung nguoiDung = nguoiDungDAO.getUserByEmail(email);
        return nguoiDung == null || nguoiDung.getTenNguoiDung() == null || nguoiDung.getTenNguoiDung().isEmpty() || nguoiDung.getNgaySinh() == null;
    }

    public boolean updateUserInfo(String email, String tenNguoiDung, Date ngaySinh) throws SQLException {
        if (tenNguoiDung == null || tenNguoiDung.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên người dùng không được để trống");
        }
        return nguoiDungDAO.updateUserInfo(email, tenNguoiDung, ngaySinh);
    }

    public NguoiDung getUserByEmail(String email) throws SQLException {
        return nguoiDungDAO.getUserByEmail(email);
    }

    private boolean isValidEmail(String email) {
        return email != null && email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }
    public NguoiDung getUserByMaNguoiDung(String maNguoiDung) throws SQLException {
        return nguoiDungDAO.getUserByMaNguoiDung(maNguoiDung);
}
}