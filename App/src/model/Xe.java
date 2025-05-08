package model;

import java.sql.Time;
import java.util.Date;

public class Xe {
    private int maXe;
    private String tenXe;
    private String loaiXe;
    private String diemDi;
    private String diemDen;
    private Date ngayKhoiHanh;
    private java.sql.Time gioDi;
    private java.sql.Time gioDen;
    private int soGhe;
    private int gheConTrong;
    private int giaVe;
    private int maTuyen;

    public Xe() { 
    }

     public Xe(int maXe, int maTuyen, Time gioDi, Time gioDen, int giaVe, String diemDi, String diemDen) {
        this.maXe = maXe;
        this.maTuyen = maTuyen;
        this.gioDi = gioDi;
        this.gioDen = gioDen;
        this.giaVe = giaVe;
        this.diemDi = diemDi;
        this.diemDen = diemDen;
    }
    
    public Xe(int maXe, String tenXe, String loaiXe, String diemDi, String diemDen,
              Date ngayKhoiHanh, java.sql.Time gioDi, java.sql.Time gioDen, int soGhe, int gheConTrong,
              int giaVe, int maTuyen) {
        this.maXe = maXe;
        this.tenXe = tenXe;
        this.loaiXe = loaiXe;
        this.diemDi = diemDi;
        this.diemDen = diemDen;
        this.ngayKhoiHanh = ngayKhoiHanh;
        this.gioDi = gioDi;
        this.gioDen = gioDen;
        this.soGhe = soGhe;
        this.gheConTrong = gheConTrong;
        this.giaVe = giaVe;
        this.maTuyen = maTuyen;
    }

    public int getMaXe() {
        return maXe;
    }

    public void setMaXe(int maXe) {
        this.maXe = maXe;
    }

    public String getTenXe() {
        return tenXe;
    }

    public void setTenXe(String tenXe) {
        this.tenXe = tenXe;
    }

    public String getLoaiXe() {
        return loaiXe;
    }

    public void setLoaiXe(String loaiXe) {
        this.loaiXe = loaiXe;
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

    public Date getNgayKhoiHanh() {
        return ngayKhoiHanh;
    }

    public void setNgayKhoiHanh(Date ngayKhoiHanh) {
        this.ngayKhoiHanh = ngayKhoiHanh;
    }

    public java.sql.Time getGioDi() {
        return gioDi;
    }

    public void setGioDi(java.sql.Time gioDi) {
        this.gioDi = gioDi;
    }

    public java.sql.Time getGioDen() {
        return gioDen;
    }

    public void setGioDen(java.sql.Time gioDen) {
        this.gioDen = gioDen;
    }

    public int getSoGhe() {
        return soGhe;
    }

    public void setSoGhe(int soGhe) {
        this.soGhe = soGhe;
    }

    public int getGheConTrong() {
        return gheConTrong;
    }

    public void setGheConTrong(int gheConTrong) {
        this.gheConTrong = gheConTrong;
    }

    public int getGiaVe() {
        return giaVe;
    }

    public void setGiaVe(int giaVe) {
        this.giaVe = giaVe;
    }

    public int getMaTuyen() {
        return maTuyen;
    }

    public void setMaTuyen(int maTuyen) {
        this.maTuyen = maTuyen;
    }
}