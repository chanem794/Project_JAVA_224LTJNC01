package model;

import java.sql.Date;
import java.sql.Time;

public class BusTicket {
    private int maXe;           // Mã xe
    private String tenXe;       // Tên xe (nhà xe)
    private String loaiXe;      // Loại xe
    private String diemDi;      // Điểm đi
    private String diemDen;     // Điểm đến
    private Date ngayKhoiHanh;  // Ngày khởi hành
    private Time gioDi;         // Giờ đi
    private Time gioDen;        // Giờ đến
    private int soGhe;          // Số ghế tổng
    private int gheConTrong;    // Số ghế còn trống
    private int giaVe;          // Giá vé
    private int maTuyen;        // Mã tuyến

    // Constructor đầy đủ
    public BusTicket(int maXe, String tenXe, String loaiXe, String diemDi, String diemDen, 
                     Date ngayKhoiHanh, Time gioDi, Time gioDen, int soGhe, int gheConTrong, 
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

    // Getters và Setters
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

    public Time getGioDi() {
        return gioDi;
    }

    public void setGioDi(Time gioDi) {
        this.gioDi = gioDi;
    }

    public Time getGioDen() {
        return gioDen;
    }

    public void setGioDen(Time gioDen) {
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