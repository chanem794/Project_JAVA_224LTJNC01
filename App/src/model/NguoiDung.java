package model;

import java.util.Date;

public class NguoiDung {
    private String maNguoiDung;
    private String tenNguoiDung;
    private String email;
    private Date ngaySinh;
    private String otpCode;
    private Date otpTaoThoiGian;

    // Constructor mặc định
    public NguoiDung() {
    }

    // Constructor đầy đủ
    public NguoiDung(String maNguoiDung, String tenNguoiDung, String email, Date ngaySinh, String otpCode, Date otpTaoThoiGian) {
        this.maNguoiDung = maNguoiDung;
        this.tenNguoiDung = tenNguoiDung;
        this.email = email;
        this.ngaySinh = ngaySinh;
        this.otpCode = otpCode;
        this.otpTaoThoiGian = otpTaoThoiGian;
    }

    // Getter và Setter
    public String getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(String maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    public String getTenNguoiDung() {
        return tenNguoiDung;
    }

    public void setTenNguoiDung(String tenNguoiDung) {
        this.tenNguoiDung = tenNguoiDung;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getOtpCode() {
        return otpCode;
    }

    public void setOtpCode(String otpCode) {
        this.otpCode = otpCode;
    }

    public Date getOtpTaoThoiGian() {
        return otpTaoThoiGian;
    }

    public void setOtpTaoThoiGian(Date otpTaoThoiGian) {
        this.otpTaoThoiGian = otpTaoThoiGian;
    }
}