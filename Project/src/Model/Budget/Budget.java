/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Budget;

/**
 *
 * @author Admin
 */
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