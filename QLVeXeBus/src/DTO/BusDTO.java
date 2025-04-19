/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author MINH HUY
 */
public class BusDTO {
    private String idBus;
    private String gheNo;
    private String tenXeBus;
    private String mauXe;
    private String bienSo;
    private int soChoNgoi;

    public BusDTO(String idBus, String gheNo, String tenXeBus, String mauXe, String bienSo, int soChoNgoi) {
        this.idBus = idBus;
        this.gheNo = gheNo;
        this.tenXeBus = tenXeBus;
        this.mauXe = mauXe;
        this.bienSo = bienSo;
        this.soChoNgoi = soChoNgoi;
    }

    public String getIdBus() {
        return idBus;
    }

    public void setIdBus(String idBus) {
        this.idBus = idBus;
    }

    public String getGheNo() {
        return gheNo;
    }

    public void setGheNo(String gheNo) {
        this.gheNo = gheNo;
    }

    public String getTenXeBus() {
        return tenXeBus;
    }

    public void setTenXeBus(String tenXeBus) {
        this.tenXeBus = tenXeBus;
    }

    public String getMauXe() {
        return mauXe;
    }

    public void setMauXe(String mauXe) {
        this.mauXe = mauXe;
    }

    public String getBienSo() {
        return bienSo;
    }

    public void setBienSo(String bienSo) {
        this.bienSo = bienSo;
    }

    public int getSoChoNgoi() {
        return soChoNgoi;
    }

    public void setSoChoNgoi(int soChoNgoi) {
        this.soChoNgoi = soChoNgoi;
    }
}

