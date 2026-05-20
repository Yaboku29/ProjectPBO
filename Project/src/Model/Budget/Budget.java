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

    private int categoryId;

    private double batas;

    private double terpakai;

    public Budget(){}

    public Budget(
            int id,
            int categoryId,
            double batas,
            double terpakai
    ){
        this.id=id;
        this.categoryId=categoryId;
        this.batas=batas;
        this.terpakai=terpakai;
    }

    public double sisaBudget(){
        return batas-terpakai;
    }

    public boolean melewatiLimit(){
        return terpakai>batas;
    }

}
