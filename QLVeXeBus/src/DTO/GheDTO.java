/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author MINH HUY
 */
public class GheDTO {
    private String idGhe;
    private int loaiGhe;

    public GheDTO(String idGhe, int loaiGhe) {
        this.idGhe = idGhe;
        this.loaiGhe = loaiGhe;
    }

    public String getIdGhe() {
        return idGhe;
    }

    public void setIdGhe(String idGhe) {
        this.idGhe = idGhe;
    }

    public int getLoaiGhe() {
        return loaiGhe;
    }

    public void setLoaiGhe(int loaiGhe) {
        this.loaiGhe = loaiGhe;
    }
}
