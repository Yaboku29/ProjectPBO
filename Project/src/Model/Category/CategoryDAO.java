/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Category;

/**
 *
 * @author Admin
 */
import java.util.ArrayList;

public class CategoryDAO {
    private ArrayList<Category>categories;

    public CategoryDAO() {
        categories =new ArrayList<>();
    }

    // CREATE
    public void createCategory(Category category) {
        categories.add(category);
    }

    // READ
    public Category getCategory(int id) {

        for (Category c: categories) {
            if (c.getId()==id) {
                return c;
            }

        }

        return null;

    }

    // READ ALL
    public ArrayList<Category>getAllCategory() {
        return categories;
    }

}
