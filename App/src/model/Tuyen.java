package model;

public class Tuyen {
    private int maTuyen;
    private String diemDi;
    private String diemDen;
    private int quangDuong;

    public Tuyen() {
    }

    public Tuyen(int maTuyen, String diemDi, String diemDen, int quangDuong) {
        this.maTuyen = maTuyen;
        this.diemDi = diemDi;
        this.diemDen = diemDen;
        this.quangDuong = quangDuong;
    }

    public int getMaTuyen() {
        return maTuyen;
    }

    public void setMaTuyen(int maTuyen) {
        this.maTuyen = maTuyen;
    }

    public String getDiemDi() {
        return diemDi;
    }

    public void setDiemDi(String diemDi) {
        this.diemDi = diemDi;
    }

    public String getDiemDen() {
        return diemDen;
    }

    public void setDiemDen(String diemDen) {
        this.diemDen = diemDen;
    }

    public int getQuangDuong() {
        return quangDuong;
    }

    public void setQuangDuong(int quangDuong) {
        this.quangDuong = quangDuong;
    }
}