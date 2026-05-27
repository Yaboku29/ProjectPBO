/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Wallet;

/**
 *
 * @author Admin
 */
import java.util.ArrayList;
public class WalletDAO {
    private ArrayList<Wallet> wallets;

    public WalletDAO() {
        wallets = new ArrayList<>();
    }

    // CREATE
    public void createWallet(Wallet wallet) {
        wallets.add(wallet);
    }

    // READ SATU
    public Wallet getWallet(int id) {
        for (Wallet wallet: wallets) {
            if (wallet.getId()==id) {
                return wallet;
            }
        }
        return null;
    }

    // READ SEMUA
    public ArrayList<Wallet>getAllWallet() {
        return wallets;
    }

    // UPDATE
    public boolean updateWallet(Wallet walletBaru) {
        for (int i = 0;i < wallets.size();i++) {
            if (wallets.get(i).getId()==walletBaru.getId()) {
                wallets.set(i,walletBaru);
                return true;
            }
        }
        return false;
    }

    // DELETE
    public boolean deleteWallet(int id) {
        for (int i = 0;i < wallets.size();i++) {
            if (wallets.get(i).getId()==id) {
                wallets.remove(i);
                return true;
            }
        }
        return false;
    }
}
