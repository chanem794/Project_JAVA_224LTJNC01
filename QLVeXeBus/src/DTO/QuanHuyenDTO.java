/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author MINH HUY
 */
public class QuanHuyenDTO {
    private String idQuanHuyen;
    private String tinhNo;
    private String tenQuanHuyen;

    public QuanHuyenDTO(String idQuanHuyen, String tinhNo, String tenQuanHuyen) {
        this.idQuanHuyen = idQuanHuyen;
        this.tinhNo = tinhNo;
        this.tenQuanHuyen = tenQuanHuyen;
    }

    public String getIdQuanHuyen() {
        return idQuanHuyen;
    }

    public void setIdQuanHuyen(String idQuanHuyen) {
        this.idQuanHuyen = idQuanHuyen;
    }

    public String getTinhNo() {
        return tinhNo;
    }

    public void setTinhNo(String tinhNo) {
        this.tinhNo = tinhNo;
    }

    public String getTenQuanHuyen() {
        return tenQuanHuyen;
    }

    public void setTenQuanHuyen(String tenQuanHuyen) {
        this.tenQuanHuyen = tenQuanHuyen;
    }
}
