package pl.coderslab.charity.service;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.model.Category;
import pl.coderslab.charity.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;
@Service
public class CategoryService{

    private final CategoryRepository categoryRepository;
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }
    public void add(Category category) { categoryRepository.save(category);    }
    public Optional<Category> get(Long id) {
        return categoryRepository.findById(id);
    }
    public void delete(Long id) {
        categoryRepository.deleteCategoryById(id);
    }
}
