/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author MINH HUY
 */
public class NhaXeBusDTO {
    private String idNhaXeBus;
    private String busNo;
    private String diaChiNhaXe;
    private String tenNhaXe;

    public NhaXeBusDTO(String idNhaXeBus, String busNo, String diaChiNhaXe, String tenNhaXe) {
        this.idNhaXeBus = idNhaXeBus;
        this.busNo = busNo;
        this.diaChiNhaXe = diaChiNhaXe;
        this.tenNhaXe = tenNhaXe;
    }

    public String getIdNhaXeBus() {
        return idNhaXeBus;
    }

    public void setIdNhaXeBus(String idNhaXeBus) {
        this.idNhaXeBus = idNhaXeBus;
    }

    public String getBusNo() {
        return busNo;
    }

    public void setBusNo(String busNo) {
        this.busNo = busNo;
    }

    public String getDiaChiNhaXe() {
        return diaChiNhaXe;
    }

    public void setDiaChiNhaXe(String diaChiNhaXe) {
        this.diaChiNhaXe = diaChiNhaXe;
    }

    public String getTenNhaXe() {
        return tenNhaXe;
    }

    public void setTenNhaXe(String tenNhaXe) {
        this.tenNhaXe = tenNhaXe;
    }
}

