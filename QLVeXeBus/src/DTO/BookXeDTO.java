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

public class BookXeDTO {
    private String idBookXe;
    private String chuyenDiNo;
    private String nhaXeBusNo;
    private Date thoiGianXuatPhat;
    private String khachHangNo;
    private String tinhTrangBookNo;
    private String busNo;

    public BookXeDTO(String idBookXe, String chuyenDiNo, String nhaXeBusNo, Date thoiGianXuatPhat, String khachHangNo, String tinhTrangBookNo, String busNo) {
        this.idBookXe = idBookXe;
        this.chuyenDiNo = chuyenDiNo;
        this.nhaXeBusNo = nhaXeBusNo;
        this.thoiGianXuatPhat = thoiGianXuatPhat;
        this.khachHangNo = khachHangNo;
        this.tinhTrangBookNo = tinhTrangBookNo;
        this.busNo = busNo;
    }

    public String getIdBookXe() {
        return idBookXe;
    }

    public void setIdBookXe(String idBookXe) {
        this.idBookXe = idBookXe;
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

    public String getKhachHangNo() {
        return khachHangNo;
    }

    public void setKhachHangNo(String khachHangNo) {
        this.khachHangNo = khachHangNo;
    }

    public String getTinhTrangBookNo() {
        return tinhTrangBookNo;
    }

    public void setTinhTrangBookNo(String tinhTrangBookNo) {
        this.tinhTrangBookNo = tinhTrangBookNo;
    }

    public String getBusNo() {
        return busNo;
    }

    public void setBusNo(String busNo) {
        this.busNo = busNo;
    }
}

