/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author MINH HUY
 */
public class ChuyenDiDTO {
    private String idChuyenDi;
    private String tramNo;
    private String tenChuyenDi;

    public ChuyenDiDTO(String idChuyenDi, String tramNo, String tenChuyenDi) {
        this.idChuyenDi = idChuyenDi;
        this.tramNo = tramNo;
        this.tenChuyenDi = tenChuyenDi;
    }

    public String getIdChuyenDi() {
        return idChuyenDi;
    }

    public void setIdChuyenDi(String idChuyenDi) {
        this.idChuyenDi = idChuyenDi;
    }

    public String getTramNo() {
        return tramNo;
    }

    public void setTramNo(String tramNo) {
        this.tramNo = tramNo;
    }

    public String getTenChuyenDi() {
        return tenChuyenDi;
    }

    public void setTenChuyenDi(String tenChuyenDi) {
        this.tenChuyenDi = tenChuyenDi;
    }
}

