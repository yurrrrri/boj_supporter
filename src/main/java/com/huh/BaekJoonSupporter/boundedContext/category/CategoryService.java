package com.huh.BaekJoonSupporter.boundedContext.category;

import com.huh.BaekJoonSupporter.customException.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category getCategory(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("존재하지 않는 카테고리입니다."));
    }

    public Category getCategory(String name) {
        return categoryRepository.findByName(name)
                .orElseThrow(() -> new DataNotFoundException("존재하지 않는 카테고리입니다."));
    }

    public List<Category> getCategoryAll() {
        return categoryRepository.findAll();
    }

    @Transactional
    public Category create(String name, String about) {
        Category category = Category.create(name, about);
        categoryRepository.save(category);
        return category;
    }

    @Transactional
    public Category modify(Category category, String name, String about) {
        Category modifyCategory = category.modify(name, about);
        categoryRepository.save(modifyCategory);
        return modifyCategory;
    }

    @Transactional
    public void delete(Category category) {
        categoryRepository.delete(category);
    }
}
