package org.workpal.Services.ServicesInterfaces;

import org.workpal.Models.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryServiceInterface {
    Optional<Category> getCategoryById(int id);
    List<Category> getAllCategories();
    void createCategory(Category category);
    void updateCategory(Category category);
    void deleteCategory(int id);
}
