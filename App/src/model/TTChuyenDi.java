package model;

import java.util.Date;
import java.sql.Time;

public class TTChuyenDi {
    private String tenXe;
    private String loaiXe;
    private String diemDi;
    private String diemDen;
    private Date ngayKhoiHanh;
    private Time gioDi;
    private Time gioDen;
    private int soGhe;
    private int gheConTrong;
    private int giaVe;

    public String getTenXe() { return tenXe; }
    public void setTenXe(String tenXe) { this.tenXe = tenXe; }
    public String getLoaiXe() { return loaiXe; }
    public void setLoaiXe(String loaiXe) { this.loaiXe = loaiXe; }
    public String getDiemDi() { return diemDi; }
    public void setDiemDi(String diemDi) { this.diemDi = diemDi; }
    public String getDiemDen() { return diemDen; }
    public void setDiemDen(String diemDen) { this.diemDen = diemDen; }
    public Date getNgayKhoiHanh() { return ngayKhoiHanh; }
    public void setNgayKhoiHanh(Date ngayKhoiHanh) { this.ngayKhoiHanh = ngayKhoiHanh; }
    public Time getGioDi() { return gioDi; }
    public void setGioDi(Time gioDi) { this.gioDi = gioDi; }
    public Time getGioDen() { return gioDen; }
    public void setGioDen(Time gioDen) { this.gioDen = gioDen; }
    public int getSoGhe() { return soGhe; }
    public void setSoGhe(int soGhe) { this.soGhe = soGhe; }
    public int getGheConTrong() { return gheConTrong; }
    public void setGheConTrong(int gheConTrong) { this.gheConTrong = gheConTrong; }
    public int getGiaVe() { return giaVe; }
    public void setGiaVe(int giaVe) { this.giaVe = giaVe; }
    
}