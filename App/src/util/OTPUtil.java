package util;

import java.util.Date;
import java.util.Random;

public class OTPUtil {
    private static final int OTP_LENGTH = 6;
    private static final long OTP_VALIDITY_DURATION = 5 * 60 * 1000; // 5 phút

    public static String generateOTP() {
        Random random = new Random();
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < OTP_LENGTH; i++) {
            otp.append(random.nextInt(10));
        }
        return otp.toString();
    }

    public static boolean isOTPValid(Date otpTaoThoiGian) {
        if (otpTaoThoiGian == null) {
            return false;
        }
        long currentTime = System.currentTimeMillis();
        long otpTime = otpTaoThoiGian.getTime();
        return (currentTime - otpTime) <= OTP_VALIDITY_DURATION;
    }
}