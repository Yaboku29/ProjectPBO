/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Budget;
import Model.FinancialRecord;
import Model.Wallet.*;
/**
 *
 * @author Admin
 */
public class Budget extends FinancialRecord{

    private int id;

    private int walletId;

    private double limitHarian;

    public Budget(
            int id,
            int walletId,
            double limitHarian
    ) {

        super(id,walletId);

        this.limitHarian=limitHarian;

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