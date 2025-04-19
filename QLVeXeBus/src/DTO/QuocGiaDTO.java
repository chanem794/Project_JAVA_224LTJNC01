/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author MINH HUY
 */
public class QuocGiaDTO {
    private String idQuocGia;
    private String tenQuocGia;

    public QuocGiaDTO(String idQuocGia, String tenQuocGia) {
        this.idQuocGia = idQuocGia;
        this.tenQuocGia = tenQuocGia;
    }

    public String getIdQuocGia() {
        return idQuocGia;
    }

    public void setIdQuocGia(String idQuocGia) {
        this.idQuocGia = idQuocGia;
    }

    public String getTenQuocGia() {
        return tenQuocGia;
    }

    public void setTenQuocGia(String tenQuocGia) {
        this.tenQuocGia = tenQuocGia;
    }
}

