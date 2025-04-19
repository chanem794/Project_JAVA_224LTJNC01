/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author MINH HUY
 */
public class TinhTrangBookDTO {
    private String idTinhTrangBook;
    private String tinhTrang;

    public TinhTrangBookDTO(String idTinhTrangBook, String tinhTrang) {
        this.idTinhTrangBook = idTinhTrangBook;
        this.tinhTrang = tinhTrang;
    }

    public String getIdTinhTrangBook() {
        return idTinhTrangBook;
    }

    public void setIdTinhTrangBook(String idTinhTrangBook) {
        this.idTinhTrangBook = idTinhTrangBook;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }
}
