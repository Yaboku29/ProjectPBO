/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Report;

/**
 *
 * @author Admin
 */
public class Report {

    private double totalPemasukan;
    private double totalPengeluaran;
    private double saldoAkhir;

    public Report(){}

    public Report(
            double totalPemasukan,
            double totalPengeluaran
    ){

        this.totalPemasukan=
                totalPemasukan;

        this.totalPengeluaran=
                totalPengeluaran;

        saldoAkhir =
                totalPemasukan
                - totalPengeluaran;
    }

    public double getSaldoAkhir() {
        return saldoAkhir;
    }

}
