/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author Admin
 */
import java.util.ArrayList;
import Model.Wallet.*;

public class WalletController {
    private WalletDAO walletDAO;

    public WalletController() {
        walletDAO = new WalletDAO();
    }

    // CREATE
    public void tambahWallet(
            int id,int userId,
            String nama,double saldo
    ) {
        if (saldo < 0) {
            System.out.println("Saldo tidak boleh negatif");
            return;
        }
        Wallet wallet =new Wallet(id,userId,nama,saldo);
        walletDAO.createWallet(wallet);
    }

    // READ
    public Wallet cariWallet(int id) {
        return walletDAO.getWallet(id);
    }

    // READ ALL
    public ArrayList<Wallet>tampilSemuaWallet(){
        return walletDAO.getAllWallet();
    }

    // UPDATE
    public void updateWallet(Wallet wallet) {
        boolean berhasil =walletDAO.updateWallet(wallet);
        if (!berhasil) {
            System.out.println("Wallet tidak ditemukan");
        }
    }

    // DELETE
    public void hapusWallet(int id) {
        boolean berhasil =walletDAO.deleteWallet(id);
        if (!berhasil) {
            System.out.println("Wallet tidak ditemukan");
        }
    }
}