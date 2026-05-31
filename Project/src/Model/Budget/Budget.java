package Model.Budget;

public class Budget {

    private int id;

    private int walletId;

    private double limitHarian;

    public Budget(
            int id,
            int walletId,
            double limitHarian
    ) {

        this.id=id;

        this.walletId=walletId;

        this.limitHarian=limitHarian;

    }

    public int getId() {
        return id;
    }

    public int getWalletId() {
        return walletId;
    }

    public double getLimitHarian() {
        return limitHarian;
    }

    public void setLimitHarian(
            double limitHarian
    ) {

        this.limitHarian=
                limitHarian;

    }

}