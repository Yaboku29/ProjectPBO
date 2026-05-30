/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Wallet;

/**
 *
 * @author Admin
 */
public class Wallet {

    private int id;
    private int userId;
    private String nama;
    private double saldo;

    public Wallet() {}

    public Wallet(
            int id,
            int userId,
            String nama,
            double saldo
    ) {
        this.id = id;
        this.userId = userId;
        this.nama = nama;
        this.saldo = saldo;
    }

    public void tambahSaldo(double jumlah) {
        saldo += jumlah;
    }

    public boolean kurangiSaldo(double nominal) {
        if (nominal > saldo) {
            return false;
        }
        saldo -= nominal;
        return true;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id=id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId=userId;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama=nama;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo=saldo;
    }
    @Override
    public String toString(){
        return nama;
    }
}
