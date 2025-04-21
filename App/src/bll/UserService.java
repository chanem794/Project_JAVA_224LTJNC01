package bll;

import dal.UserDAO;
import model.User;
import util.OTPUtil;
import util.EmailUtil;
import java.sql.SQLException;
import java.util.Date;

public class UserService {
    private final UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    public boolean processLogin(String email) throws SQLException {
        if (!isValidEmail(email)) {
            return false;
        }
        if (userDAO.existsByEmail(email)) {
            String otp = OTPUtil.generateOTP();
            if (userDAO.updateOTP(email, otp)) {
                return EmailUtil.sendOTP(email, otp);
            }
        }
        return false;
    }

    public boolean processRegistration(String email) throws SQLException {
        if (!isValidEmail(email)) {
            return false;
        }
        if (!userDAO.existsByEmail(email)) {
            userDAO.insertUser(email);
        }
        String otp = OTPUtil.generateOTP();
        if (userDAO.updateOTP(email, otp)) {
            return EmailUtil.sendOTP(email, otp);
        }
        return false;
    }

    public boolean validateOTP(String email, String otp) throws SQLException {
        User user = userDAO.validateOTP(email, otp);
        if (user != null && OTPUtil.isOTPValid(user.getOtpCreatedAt())) {
            userDAO.clearOTP(email);
            return true;
        }
        return false;
    }

    public boolean needsUserInfo(String email) throws SQLException {
        User user = userDAO.getUserByEmail(email);
        return user == null || user.getFullName() == null || user.getBirthDate() == null;
    }

    public boolean updateUserInfo(String email, String fullName, Date birthDate) throws SQLException {
        return userDAO.updateUserInfo(email, fullName, birthDate);
    }

    private boolean isValidEmail(String email) {
        return email != null && email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }
}