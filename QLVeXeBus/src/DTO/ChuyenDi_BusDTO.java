/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author MINH HUY
 */
import java.util.Date;

public class ChuyenDi_BusDTO {
    private String chuyenDiNo;
    private String nhaXeBusNo;
    private Date thoiGianXuatPhat;
    private Date thoiGianDen;
    private double tienXe;

    public ChuyenDi_BusDTO(String chuyenDiNo, String nhaXeBusNo, Date thoiGianXuatPhat, Date thoiGianDen, double tienXe) {
        this.chuyenDiNo = chuyenDiNo;
        this.nhaXeBusNo = nhaXeBusNo;
        this.thoiGianXuatPhat = thoiGianXuatPhat;
        this.thoiGianDen = thoiGianDen;
        this.tienXe = tienXe;
    }

    public String getChuyenDiNo() {
        return chuyenDiNo;
    }

    public void setChuyenDiNo(String chuyenDiNo) {
        this.chuyenDiNo = chuyenDiNo;
    }

    public String getNhaXeBusNo() {
        return nhaXeBusNo;
    }

    public void setNhaXeBusNo(String nhaXeBusNo) {
        this.nhaXeBusNo = nhaXeBusNo;
    }

    public Date getThoiGianXuatPhat() {
        return thoiGianXuatPhat;
    }

    public void setThoiGianXuatPhat(Date thoiGianXuatPhat) {
        this.thoiGianXuatPhat = thoiGianXuatPhat;
    }

    public Date getThoiGianDen() {
        return thoiGianDen;
    }

    public void setThoiGianDen(Date thoiGianDen) {
        this.thoiGianDen = thoiGianDen;
    }

    public double getTienXe() {
        return tienXe;
    }

    public void setTienXe(double tienXe) {
        this.tienXe = tienXe;
    }
}
