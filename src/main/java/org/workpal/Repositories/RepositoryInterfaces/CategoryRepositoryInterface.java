package org.workpal.Repositories.RepositoryInterfaces;

import org.workpal.Models.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepositoryInterface {
    Optional<Category> findById(int id);
    List<Category> findAll();
    void save(Category category);
    void update(Category category);
    void deleteById(int id);
}
