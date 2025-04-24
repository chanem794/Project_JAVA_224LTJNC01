package util;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailUtil {
    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "587";
    private static final String EMAIL_USERNAME = "ht4784073@gmail.com";
    private static final String EMAIL_PASSWORD = "ukiq gqix yvjl djbh";

    public static boolean sendOTP(String recipientEmail, String otp) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", SMTP_PORT);
        props.put("mail.debug", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_USERNAME, EMAIL_PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL_USERNAME, "Quan Ly Ve Xe Buyt"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setReplyTo(InternetAddress.parse(EMAIL_USERNAME));
            message.setSubject("Your OTP Code for Quan Ly Ve Xe Buyt");
            message.setContent(
                "<html>" +
                "<body style='font-family: Arial, sans-serif;'>" +
                "<h2>Your OTP Code</h2>" +
                "<p>Dear User,</p>" +
                "<p>Your one-time password (OTP) for Quan Ly Ve Xe Buyt is:</p>" +
                "<p style='font-size: 24px; font-weight: bold; color: #2F4F4F;'>" + otp + "</p>" +
                "<p>This code is valid for 5 minutes.</p>" +
                "<p>If you did not request this OTP, please ignore this email or contact support.</p>" +
                "<p>Best regards,<br>Quan Ly Ve Xe Buyt Team</p>" +
                "</body>" +
                "</html>",
                "text/html; charset=utf-8"
            );

            Transport.send(message);
            System.out.println("OTP sent to " + recipientEmail + ": " + otp);
            return true;
        } catch (MessagingException e) {
            System.err.println("Failed to send OTP: " + e.getMessage());
            e.printStackTrace();
            return false;
        } catch (java.io.UnsupportedEncodingException e) {
            System.err.println("Failed to set sender name: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}