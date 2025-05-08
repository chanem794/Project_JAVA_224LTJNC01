/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.sql.Time;
/**
 *
 * @author MINH HUY
 */
public class Xe {
    private String maXe;
    private String maTuyen;
    private Time gioDi;
    private Time gioDen;
    private int giaVe;
    private String diemDi; 
    private String diemDen; 

    public Xe(String maXe, String maTuyen, Time gioDi, Time gioDen, int giaVe, String diemDi, String diemDen) {
        this.maXe = maXe;
        this.maTuyen = maTuyen;
        this.gioDi = gioDi;
        this.gioDen = gioDen;
        this.giaVe = giaVe;
        this.diemDi = diemDi;
        this.diemDen = diemDen;
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

    public String getMaXe() {
        return maXe;
    }

    public void setMaXe(String maXe) {
        this.maXe = maXe;
    }

    public String getMaTuyen() {
        return maTuyen;
    }

    public void setMaTuyen(String maTuyen) {
        this.maTuyen = maTuyen;
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

    public int getGiaVe() {
        return giaVe;
    }

    public void setGiaVe(int giaVe) {
        this.giaVe = giaVe;
    }
    
}
