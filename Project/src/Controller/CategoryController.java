package Controller;

import Model.Category.Category;
import Model.Category.CategoryDAO;
import java.util.ArrayList;

public class CategoryController {
    
    private CategoryDAO categoryDAO;

    public CategoryController() {
        categoryDAO = new CategoryDAO();
    }

    public boolean tambahCategory(Category category) {
        return categoryDAO.createCategory(category);
    }

    public boolean updateCategory(Category category) {
        return categoryDAO.updateCategory(category);
    }

    public boolean hapusCategory(int id) {
        return categoryDAO.deleteCategory(id);
    }

    public ArrayList<Category> getAllCategory() {
        return categoryDAO.getAllCategory();
    }
    
}
