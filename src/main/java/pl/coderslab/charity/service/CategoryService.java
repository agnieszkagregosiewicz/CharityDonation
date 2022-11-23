package pl.coderslab.charity.service;

import pl.coderslab.charity.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getCategories();
    void add(Category category);
    Optional<Category> get(Long id);
    void delete(Long id);

}
