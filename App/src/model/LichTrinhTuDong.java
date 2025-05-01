/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author dinhk
 */
public class LichTrinhTuDong {
    private int maLichTrinhTuDong;
    private int maXe;
    private int thuTu;
    private String diaDiem;
    private int thoiGianDuKien;

    public LichTrinhTuDong(int maLichTrinhTuDong, int maXe, int thuTu, String diaDiem, int thoiGianDuKien) {
        this.maLichTrinhTuDong = maLichTrinhTuDong;
        this.maXe = maXe;
        this.thuTu = thuTu;
        this.diaDiem = diaDiem;
        this.thoiGianDuKien = thoiGianDuKien;
    }

    public int getMaLichTrinhTuDong() {
        return maLichTrinhTuDong;
    }

    public void setMaLichTrinhTuDong(int maLichTrinhTuDong) {
        this.maLichTrinhTuDong = maLichTrinhTuDong;
    }

    public int getMaXe() {
        return maXe;
    }

    public void setMaXe(int maXe) {
        this.maXe = maXe;
    }

    public int getThuTu() {
        return thuTu;
    }

    public void setThuTu(int thuTu) {
        this.thuTu = thuTu;
    }

    public String getDiaDiem() {
        return diaDiem;
    }

    public void setDiaDiem(String diaDiem) {
        this.diaDiem = diaDiem;
    }

    public int getThoiGianDuKien() {
        return thoiGianDuKien;
    }

    public void setThoiGianDuKien(int thoiGianDuKien) {
        this.thoiGianDuKien = thoiGianDuKien;
    }
    
    
}
