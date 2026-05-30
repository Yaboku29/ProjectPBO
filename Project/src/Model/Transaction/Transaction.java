/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Transaction;

import java.time.LocalDate;
import Model.FinancialRecord;
public class Transaction extends FinancialRecord {

    private int id;
    private int walletId;
    private int categoryId;

    private double jumlah;

    private String deskripsi;

    private LocalDate tanggal;

    public Transaction(){}

    public Transaction(
            int id,
            int walletId,
            int categoryId,
            double jumlah,
            String deskripsi,
            LocalDate tanggal
    ){

        super(id,walletId);

        this.categoryId=categoryId;

        this.jumlah=jumlah;

        this.deskripsi=deskripsi;

        this.tanggal=tanggal;

    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(
            int categoryId
    ){
        this.categoryId=categoryId;
    }

    public double getJumlah() {
        return jumlah;
    }

    public void setJumlah(
            double jumlah
    ){
        this.jumlah=jumlah;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(
            String deskripsi
    ){
        this.deskripsi=deskripsi;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public void setTanggal(
            LocalDate tanggal
    ){
        this.tanggal=tanggal;
    }

}