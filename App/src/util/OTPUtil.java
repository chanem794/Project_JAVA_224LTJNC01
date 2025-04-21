package util;

import java.util.Random;

public class OTPUtil {
    private static final int OTP_LENGTH = 6;
    private static final int OTP_VALIDITY_SECONDS = 300;

    public static String generateOTP() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    public static boolean isOTPValid(java.util.Date otpCreatedAt) {
        if (otpCreatedAt == null) return false;
        long currentTime = System.currentTimeMillis();
        long otpTime = otpCreatedAt.getTime();
        return (currentTime - otpTime) / 1000 <= OTP_VALIDITY_SECONDS;
    }
}