package Model;
public class FinancialRecord {

    protected int id;

    protected int walletId;

    public FinancialRecord(){}

    public FinancialRecord(
            int id,
            int walletId
    ){

        this.id = id;

        this.walletId = walletId;

    }

    public int getId() {
        return id;
    }

    public int getWalletId() {
        return walletId;
    }

}