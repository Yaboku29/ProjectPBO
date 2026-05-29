/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Budget;

/**
 *
 * @author Admin
 */
import java.util.ArrayList;

public class BudgetDAO {

    private ArrayList<Budget>
            budgets;

    public BudgetDAO() {

        budgets =
                new ArrayList<>();

    }

    // CREATE
    public void createBudget(
            Budget budget
    ) {

        budgets.add(
                budget
        );

    }

    // GET BY WALLET
    public Budget getByWalletId(
            int walletId
    ) {

        for (
                Budget budget
                : budgets
        ) {

            if (
                    budget.getWalletId()
                    ==
                    walletId
            ) {

                return budget;

            }

        }

        return null;

    }

}