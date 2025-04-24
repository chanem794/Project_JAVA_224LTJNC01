package model;

import java.util.Date;

public class DatCho {
    private int maDatCho;
    private String trangThai;
    private Date ngayDat;
    private java.sql.Time gioDat;
    private String diemDi;
    private String diemDen;
    private Date ngayGioKhoiHanh;
    private int soGheDat;
    private int giaVe;
    private String maNguoiDung;
    private int maXe;

    public DatCho() {
    }

    public DatCho(int maDatCho, String trangThai, Date ngayDat, java.sql.Time gioDat, String diemDi, String diemDen,
                  Date ngayGioKhoiHanh, int soGheDat, int giaVe, String maNguoiDung, int maXe) {
        this.maDatCho = maDatCho;
        this.trangThai = trangThai;
        this.ngayDat = ngayDat;
        this.gioDat = gioDat;
        this.diemDi = diemDi;
        this.diemDen = diemDen;
        this.ngayGioKhoiHanh = ngayGioKhoiHanh;
        this.soGheDat = soGheDat;
        this.giaVe = giaVe;
        this.maNguoiDung = maNguoiDung;
        this.maXe = maXe;
    }

    public int getMaDatCho() {
        return maDatCho;
    }

    public void setMaDatCho(int maDatCho) {
        this.maDatCho = maDatCho;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public Date getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(Date ngayDat) {
        this.ngayDat = ngayDat;
    }

    public java.sql.Time getGioDat() {
        return gioDat;
    }

    public void setGioDat(java.sql.Time gioDat) {
        this.gioDat = gioDat;
    }

    public String getDiemDi() {
        return diemDi;
    }

    public void setDiemDi(String diemDi) {
        this.diemDi = diemDi;
    }

    public String getDiemDen() {
        return diemDen;
    }

    public void setDiemDen(String diemDen) {
        this.diemDen = diemDen;
    }

    public Date getNgayGioKhoiHanh() {
        return ngayGioKhoiHanh;
    }

    public void setNgayGioKhoiHanh(Date ngayGioKhoiHanh) {
        this.ngayGioKhoiHanh = ngayGioKhoiHanh;
    }

    public int getSoGheDat() {
        return soGheDat;
    }

    public void setSoGheDat(int soGheDat) {
        this.soGheDat = soGheDat;
    }

    public int getGiaVe() {
        return giaVe;
    }

    public void setGiaVe(int giaVe) {
        this.giaVe = giaVe;
    }

    public String getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(String maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    public int getMaXe() {
        return maXe;
    }

    public void setMaXe(int maXe) {
        this.maXe = maXe;
    }
}