package model;

import java.util.Date;

public class User {
    private String email;
    private String fullName;
    private Date birthDate;
    private String otpCode;
    private Date otpCreatedAt;

    public User() {}

    public User(String email, String fullName, Date birthDate, String otpCode, Date otpCreatedAt) {
        this.email = email;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.otpCode = otpCode;
        this.otpCreatedAt = otpCreatedAt;
    }

    // Getters and Setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public Date getBirthDate() { return birthDate; }
    public void setBirthDate(Date birthDate) { this.birthDate = birthDate; }
    public String getOtpCode() { return otpCode; }
    public void setOtpCode(String otpCode) { this.otpCode = otpCode; }
    public Date getOtpCreatedAt() { return otpCreatedAt; }
    public void setOtpCreatedAt(Date otpCreatedAt) { this.otpCreatedAt = otpCreatedAt; }
}