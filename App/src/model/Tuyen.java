/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author MINH HUY
 */

public class Tuyen {
    private String maTuyen;
    private String diemDi;
    private String diemDen;

    // Constructor
    public Tuyen(String maTuyen, String diemDi, String diemDen) {
        this.maTuyen = maTuyen;
        this.diemDi = diemDi;
        this.diemDen = diemDen;
    }


    public String getMaTuyen() {
        return maTuyen;
    }

    public void setMaTuyen(String maTuyen) {
        this.maTuyen = maTuyen;
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
}

