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

    public void tambahTransaksi(
        Transaction trx
    ) {

        Wallet wallet =
                walletDAO.getWallet(
                        trx.getWalletId()
                );

        if (wallet == null) {

            System.out.println(
                    "Wallet tidak ditemukan"
            );

            return;

        }

        Category category =
                categoryDAO.getCategory(
                        trx.getCategoryId()
                );

        if (category == null) {

            System.out.println(
                    "Kategori tidak ditemukan"
            );

            return;

        }

        TransactionType jenis =
                category.getJenis();

        // ======================
        // CEK BUDGET
        // ======================

        Budget budget =
                budgetDAO.getByWalletId(
                        trx.getWalletId()
                );

        if (
                budget != null
                &&
                jenis
                ==
                TransactionType.PENGELUARAN
        ) {

            double totalHariIni = 0;

            for (
                    Transaction t
                    :
                    transactionDAO.getByDate(
                            trx.getTanggal()
                    )
            ) {

                Category c =
                        categoryDAO.getCategory(
                                t.getCategoryId()
                        );

                if (
                        t.getWalletId()
                        ==
                        trx.getWalletId()
                        &&
                        c.getJenis()
                        ==
                        TransactionType.PENGELUARAN
                ) {

                    totalHariIni +=
                            t.getJumlah();

                }

            }

            double totalBaru =
                    totalHariIni
                    +
                    trx.getJumlah();

            if (
                    totalBaru
                    >
                    budget.getLimitHarian()
            ) {

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
                jenis
                ==
                TransactionType.PEMASUKAN
        ) {

            wallet.tambahSaldo(
                    trx.getJumlah()
            );

        }

        else {

            boolean cukup =
                    wallet.kurangiSaldo(
                            trx.getJumlah()
                    );

            if (!cukup) {

                System.out.println(
                        "Saldo tidak cukup"
                );

                return;

            }

        }

        walletDAO.updateWallet(
                wallet
        );

        transactionDAO.createTransaction(
                trx
        );

        System.out.println(
                "Transaksi berhasil"
        );

    }

    public void deleteTransaction(
        int transactionId
    ) {

        Transaction trx =
                transactionDAO.getTransaction(
                        transactionId
                );

        if (trx == null) {

            System.out.println(
                    "Transaksi tidak ditemukan"
            );

            return;

        }

        Wallet wallet =
                walletDAO.getWallet(
                        trx.getWalletId()
                );

        Category category =
                categoryDAO.getCategory(
                        trx.getCategoryId()
                );

        if (
                wallet == null
                ||
                category == null
        ) {

            return;

        }

        if (
                category.getJenis()
                ==
                TransactionType.PENGELUARAN
        ) {

            wallet.tambahSaldo(
                    trx.getJumlah()
            );

        }

        else {

            wallet.kurangiSaldo(
                    trx.getJumlah()
            );

        }

        walletDAO.updateWallet(
                wallet
        );

        transactionDAO.deleteTransaction(
                transactionId
        );

        System.out.println(
                "Transaksi dihapus"
        );

    }

    public void updateTransaction(
        Transaction trxBaru
    ) {

        Transaction trxLama =
                transactionDAO.getTransaction(
                        trxBaru.getId()
                );

        if (trxLama == null) {

            System.out.println(
                    "Transaksi tidak ditemukan"
            );

            return;

        }

        Wallet wallet =
                walletDAO.getWallet(
                        trxLama.getWalletId()
                );

        Category kategoriLama =
                categoryDAO.getCategory(
                        trxLama.getCategoryId()
                );

        Category kategoriBaru =
                categoryDAO.getCategory(
                        trxBaru.getCategoryId()
                );

        if (
                wallet == null
                ||
                kategoriLama == null
                ||
                kategoriBaru == null
        ) {

            return;

        }

        // ======================
        // BALIKKAN EFEK LAMA
        // ======================

        if (
                kategoriLama.getJenis()
                ==
                TransactionType.PENGELUARAN
        ) {

            wallet.tambahSaldo(
                    trxLama.getJumlah()
            );

        }

        else {

            wallet.kurangiSaldo(
                    trxLama.getJumlah()
            );

        }

        // ======================
        // TERAPKAN EFEK BARU
        // ======================

        if (
                kategoriBaru.getJenis()
                ==
                TransactionType.PENGELUARAN
        ) {

            boolean cukup =
                    wallet.kurangiSaldo(
                            trxBaru.getJumlah()
                    );

            if (!cukup) {

                System.out.println(
                        "Saldo tidak cukup"
                );

                return;

            }

        }

        else {

            wallet.tambahSaldo(
                    trxBaru.getJumlah()
            );

        }

        walletDAO.updateWallet(
                wallet
        );

        transactionDAO.updateTransaction(
                trxBaru
        );

        System.out.println(
                "Transaksi berhasil diupdate"
        );

    }
    
}
