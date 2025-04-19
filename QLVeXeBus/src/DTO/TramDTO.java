/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author MINH HUY
 */
public class TramDTO {
    private String idTram;
    private String phuongXaNo;
    private String tenTram;

    public TramDTO(String idTram, String phuongXaNo, String tenTram) {
        this.idTram = idTram;
        this.phuongXaNo = phuongXaNo;
        this.tenTram = tenTram;
    }

    public String getIdTram() {
        return idTram;
    }

    public void setIdTram(String idTram) {
        this.idTram = idTram;
    }

    public String getPhuongXaNo() {
        return phuongXaNo;
    }

    public void setPhuongXaNo(String phuongXaNo) {
        this.phuongXaNo = phuongXaNo;
    }

    public String getTenTram() {
        return tenTram;
    }

    public void setTenTram(String tenTram) {
        this.tenTram = tenTram;
    }
}

