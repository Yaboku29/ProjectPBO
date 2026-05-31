package Model.Report;

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
    
    public double getTotalPemasukan(){
        return totalPemasukan;
    }
    
    public double getTotalPengeluaran(){
        return totalPengeluaran;
    }

    public double getSaldoAkhir() {
        return saldoAkhir;
    }

}
