/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author MINH HUY
 */
public class PhuongXaDTO {
    private String idPhuongXa;
    private String quanHuyenNo;
    private String tenPhuongXa;

    public PhuongXaDTO(String idPhuongXa, String quanHuyenNo, String tenPhuongXa) {
        this.idPhuongXa = idPhuongXa;
        this.quanHuyenNo = quanHuyenNo;
        this.tenPhuongXa = tenPhuongXa;
    }

    public String getIdPhuongXa() {
        return idPhuongXa;
    }

    public void setIdPhuongXa(String idPhuongXa) {
        this.idPhuongXa = idPhuongXa;
    }

    public String getQuanHuyenNo() {
        return quanHuyenNo;
    }

    public void setQuanHuyenNo(String quanHuyenNo) {
        this.quanHuyenNo = quanHuyenNo;
    }

    public String getTenPhuongXa() {
        return tenPhuongXa;
    }

    public void setTenPhuongXa(String tenPhuongXa) {
        this.tenPhuongXa = tenPhuongXa;
    }
}

