package project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.models.Category;
import project.repositories.ICategoryRepository;

import java.util.List;

@Service
public class CategoryService implements ICategoryService{

    @Autowired
    ICategoryRepository iCategoryRepository;

    @Override
    public List<Category> findAll() {
        return iCategoryRepository.findAll();
    }
}
