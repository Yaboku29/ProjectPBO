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
    
    
    public TransactionController() {

        this.walletDAO = new WalletDAO();

        this.categoryDAO = new CategoryDAO();

        this.budgetDAO = new BudgetDAO();

        transactionDAO = new TransactionDAO();

    }

    public TransactionDAO getTransactionDAO() {

        return transactionDAO;

    }

    // =========================
    // TAMBAH TRANSAKSI
    // =========================

    public boolean tambahTransaksi(
            Transaction trx
    ) {

        // ======================
        // CEK WALLET
        // ======================

        Wallet wallet =
                walletDAO
                .getWallet(
                        trx.getWalletId()
                );

        if (
                wallet == null
        ) {

                System.out.println(
                        "Wallet tidak ditemukan"
                );

                return false;

        }

        // ======================
        // CEK CATEGORY
        // ======================

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

                return false;

        }

        // ======================
        // AMBIL JENIS
        // ======================

        TransactionType jenis =
                category.getJenis();

        // ======================
        // CEK BUDGET
        // ======================

        Budget budget =
                budgetDAO
                .getByWalletId(
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
                        transactionDAO
                        .getByDate(
                                trx.getTanggal()
                        )
                ) {

                Category c =
                        categoryDAO
                        .getCategory(
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

                return false;

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

                if (
                        !cukup
                ) {

                System.out.println(
                        "Saldo tidak cukup"
                );

                return false;

                }

        }

        // ======================
        // UPDATE WALLET
        // ======================

        boolean updateWallet =
                walletDAO.updateWallet(
                        wallet
                );

        if(!updateWallet){

                System.out.println(
                        "Gagal update wallet"
                );

                return false;

        }

        // ======================
        // SIMPAN TRANSAKSI
        // ======================

        transactionDAO
                .createTransaction(
                        trx
                );

        System.out.println(
                "Transaksi berhasil"
        );

        return true;
    }

                




    // =========================
    // DELETE TRANSACTION
    // =========================

    public void deleteTransaction(
            int transactionId
    ) {

        Transaction trx =
                transactionDAO
                .getTransaction(
                        transactionId
                );

        if (
                trx == null
        ) {

            System.out.println(
                    "Transaksi tidak ditemukan"
            );

            return;

        }

        Wallet wallet =
                walletDAO
                .getWallet(
                        trx.getWalletId()
                );

        Category category =
                categoryDAO
                .getCategory(
                        trx.getCategoryId()
                );

        // ======================
        // KEMBALIKAN SALDO
        // ======================

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

        transactionDAO
                .deleteTransaction(
                        transactionId
                );

        System.out.println(
                "Transaksi dihapus"
        );

    }

    // =========================
    // UPDATE TRANSACTION
    // =========================

    public void updateTransaction(
            Transaction trxBaru
    ) {

        Transaction trxLama =
                transactionDAO
                .getTransaction(
                        trxBaru.getId()
                );

        if (
                trxLama == null
        ) {

            System.out.println(
                    "Transaksi tidak ditemukan"
            );

            return;

        }

        Wallet wallet =
                walletDAO
                .getWallet(
                        trxLama.getWalletId()
                );

        Category oldCategory =
                categoryDAO
                .getCategory(
                        trxLama.getCategoryId()
                );

        Category newCategory =
                categoryDAO
                .getCategory(
                        trxBaru.getCategoryId()
                );

        // ======================
        // BALIK EFEK LAMA
        // ======================

        if (
                oldCategory.getJenis()
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
        // TERAPKAN BARU
        // ======================

        if (
                newCategory.getJenis()
                ==
                TransactionType.PENGELUARAN
        ) {

            boolean cukup =
                    wallet.kurangiSaldo(
                            trxBaru.getJumlah()
                    );

            if (
                    !cukup
            ) {

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

        transactionDAO
                .updateTransaction(
                        trxBaru
                );

        System.out.println(
                "Transaksi diupdate"
        );

    }

}