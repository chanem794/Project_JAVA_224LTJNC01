/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author MINH HUY
 */
public class TinhDTO {
    private String idTinh;
    private String quocGiaNo;
    private String tenTinh;

    public TinhDTO(String idTinh, String quocGiaNo, String tenTinh) {
        this.idTinh = idTinh;
        this.quocGiaNo = quocGiaNo;
        this.tenTinh = tenTinh;
    }

    public String getIdTinh() {
        return idTinh;
    }

    public void setIdTinh(String idTinh) {
        this.idTinh = idTinh;
    }

    public String getQuocGiaNo() {
        return quocGiaNo;
    }

    public void setQuocGiaNo(String quocGiaNo) {
        this.quocGiaNo = quocGiaNo;
    }

    public String getTenTinh() {
        return tenTinh;
    }

    public void setTenTinh(String tenTinh) {
        this.tenTinh = tenTinh;
    }
}
