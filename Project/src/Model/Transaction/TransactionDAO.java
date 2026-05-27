/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Transaction;

/**
 *
 * @author Admin
 */
import java.util.ArrayList;

public class TransactionDAO {
    private ArrayList<Transaction> transactions;

    public TransactionDAO() {
        transactions =new ArrayList<>();
    }

    // CREATE
    public void createTransaction(Transaction trx) {
        transactions.add(trx);
    }

    // READ BY ID
    public Transaction getTransaction(int id) {
        for (Transaction trx: transactions) {
            if (trx.getId()==id) {
                return trx;
            }
        }
        return null;
    }

    // READ ALL
    public ArrayList<Transaction>getAllTransaction() {
        return transactions;
    }

    // UPDATE
    public boolean updateTransaction(Transaction trxBaru) {
        for (int i = 0;i < transactions.size();i++){
            if (transactions.get(i).getId()==trxBaru.getId()) {
                transactions.set(i,trxBaru);
                return true;
            }
        }
        return false;
    }

    // DELETE
    public boolean deleteTransaction(int id){
        for (int i = 0;i < transactions.size();i++) {
            if (transactions.get(i).getId()==id) {
                transactions.remove(i);
                return true;
            }
        }
        return false;
    }
}
