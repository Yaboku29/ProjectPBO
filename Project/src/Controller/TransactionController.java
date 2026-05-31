package Controller;

import Model.Transaction.*;
import Model.Budget.*;
import Model.Category.*;
import Model.Wallet.*;
import Model.TransactionType;

public class TransactionController {

    private TransactionDAO transactionDAO;
    private CategoryDAO categoryDAO;
    private WalletDAO walletDAO;
    private BudgetDAO budgetDAO;

    public TransactionController(WalletDAO walletDAO,
                                CategoryDAO categoryDAO,
                                BudgetDAO budgetDAO) {

        this.walletDAO =walletDAO;
        this.categoryDAO=categoryDAO;
        this.budgetDAO=budgetDAO;
        transactionDAO =new TransactionDAO();
    }
    
    public TransactionDAO getTransactionDAO(){
        return transactionDAO;
    }

    public void tambahTransaksi(Transaction trx) {
        Wallet wallet =walletDAO.getWallet(trx.getWalletId());
        // cek wallet
        if (wallet == null) {
            System.out.println("Wallet tidak ditemukan");
            return;
        }
        Category category =
                categoryDAO
                .getCategory(
                        trx.getCategoryId()
                );

        if (
                category == null
        ) {

            System.out.println(
                    "Kategori tidak ditemukan"
            );

            return;

        }

        // ======================
        // VALIDASI JENIS
        // ======================

        if (
                //category.getJenis().toString()
                //!=
                //trx.getJenis()
                !category.getJenis().toString().equals(trx.getJenis())
        ) {

            System.out.println(
                    "Jenis transaksi tidak cocok dengan kategori"
            );

            return;

        }
        // ======================
        // CEK BUDGET
        // ======================

        Budget budget =budgetDAO.getByWalletId(trx.getWalletId());
        if(
            budget != null
            &&
            trx.getJenis().equals(TransactionType.PENGELUARAN.toString())
            //trx.getJenis()
            //==
            //TransactionType
            //.Pengeluaran.toString()
        ){

            double totalHariIni = 0;

            for(
                    Transaction t
                    :
                    transactionDAO.getByDate(
                            trx.getTanggal()
                    )
            ){

                if(
                        t.getWalletId()
                        ==
                        trx.getWalletId()
                        &&
                        t.getJenis().equals(TransactionType.PENGELUARAN.toString())
                        //t.getJenis()
                        //==
                        //TransactionType
                        //.Pengeluaran.toString()
                ){

                    totalHariIni +=
                            t.getJumlah();

                }

            }

            double totalBaru =
                    totalHariIni
                    +
                    trx.getJumlah();

            if(
                    totalBaru
                    >
                    budget
                    .getLimitHarian()
            ){

                System.out.println(
                        "Melebihi budget harian"
                );

                return;

            }

        }
        // ======================
        // UPDATE SALDO
        // ======================

        if (
                trx.getJenis().equals(TransactionType.PEMASUKAN.toString())
                //trx.getJenis()
                //==
                //TransactionType.Pemasukan.toString()
        ) {

            wallet.tambahSaldo(
                    trx.getJumlah()
            );
            
            walletDAO.updateWallet(wallet); // update database

        }

        else {

            boolean cukup =
                    wallet.kurangiSaldo(
                            trx.getJumlah()
                    );

            if (
                    !cukup
            ) {

                System.out.println(
                        "Saldo tidak cukup"
                );

                return;

            }
            
            walletDAO.updateWallet(wallet); // update database

        }

        transactionDAO.createTransaction(trx);
        System.out.println("Transaksi berhasil");
    }

    public void deleteTransaction(int transactionId) {
        Transaction trx =transactionDAO.getTransaction(transactionId);
        if (trx == null) {
            System.out.println("Transaksi tidak ditemukan");
            return;
        }
        Wallet wallet = walletDAO.getWallet(trx.getWalletId());

        // Kembalikan saldo
        if (trx.getJenis().equals("Pengeluaran")) {

            wallet.tambahSaldo(trx.getJumlah());

        }

        else {

            wallet.kurangiSaldo(trx.getJumlah());

        }
        
        walletDAO.updateWallet(wallet);
        
        transactionDAO.deleteTransaction(transactionId);
        System.out.println("Transaksi dihapus");
    }

    public void updateTransaction(Transaction trxBaru) {
        Transaction trxLama =transactionDAO.getTransaction(trxBaru.getId());
        if (trxLama == null) {

            System.out.println("Transaksi tidak ditemukan");
            return;

        }

        Wallet wallet =walletDAO.getWallet(trxLama.getWalletId());

        // ======================
        // BALIK EFEK LAMA
        // ======================

        if (trxLama.getJenis().equals("Pengeluaran")) {

            wallet.tambahSaldo(trxLama.getJumlah());

        }

        else {

            wallet.kurangiSaldo(trxLama.getJumlah());

        }

        // ======================
        // TERAPKAN BARU
        // ======================

        if (
                trxBaru.getJenis().equals("Pengeluaran")
        ) {

            boolean cukup =wallet.kurangiSaldo(trxBaru.getJumlah());

            if (!cukup) {

                System.out.println("Saldo tidak cukup");

                return;

            }

        }

        else {

            wallet.tambahSaldo(
                    trxBaru.getJumlah()
            );

        }
        
        walletDAO.updateWallet(wallet);

        transactionDAO
                .updateTransaction(
                        trxBaru
                );

        System.out.println(
                "Transaksi diupdate"
        );

    }
    
}
