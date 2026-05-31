package Model.Report;

import Helper.Session;
import Model.Transaction.*;
import Model.Category.*;
import Model.TransactionType;

import java.time.LocalDate;

import java.util.ArrayList;

public class DailyReport extends ReportGenerator {

    @Override
    public String generateReport(){

        TransactionDAO transactionDAO =
                new TransactionDAO();

        CategoryDAO categoryDAO =
                new CategoryDAO();

        ArrayList<Transaction>
                transactions =
                transactionDAO
                .getByDateAndUser(LocalDate.now(), Session.currentUser.getId());

        double totalPemasukan = 0;

        double totalPengeluaran = 0;

        StringBuilder laporan =
                new StringBuilder();

        laporan.append(
                "===== DAILY REPORT =====\n\n"
        );

        laporan.append(
                "Tanggal : "
                +
                LocalDate.now()
                +
                "\n\n"
        );

        laporan.append(
                "===== TRANSAKSI =====\n\n"
        );

        for(
                Transaction trx
                :
                transactions
        ){

            Category category =
                    categoryDAO
                    .getCategory(
                            trx.getCategoryId()
                    );

            laporan.append(

                    category.getNama()
                    +
                    " - Rp"
                    +
                    trx.getJumlah()
                    +
                    "\n"

            );

            // ======================
            // HITUNG TOTAL
            // ======================

            if(

                    category.getJenis()
                    ==
                    TransactionType.PEMASUKAN

            ){

                totalPemasukan +=
                        trx.getJumlah();

            }

            else{

                totalPengeluaran +=
                        trx.getJumlah();

            }

        }

        laporan.append("\n");

        laporan.append(
                "Total Pemasukan : Rp"
                +
                totalPemasukan
                +
                "\n"
        );

        laporan.append(
                "Total Pengeluaran : Rp"
                +
                totalPengeluaran
                +
                "\n"
        );

        laporan.append(
                "Selisih : Rp"
                +
                (
                        totalPemasukan
                        -
                        totalPengeluaran
                )
        );

        return laporan.toString();

    }

}