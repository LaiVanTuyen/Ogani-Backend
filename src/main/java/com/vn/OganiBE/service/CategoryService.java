package com.vn.OganiBE.service;



import com.vn.OganiBE.entity.Category;
import com.vn.OganiBE.model.request.CreateCategoryRequest;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    List<Category> getListEnabled();

    Category createCategory(CreateCategoryRequest request);

    Category updateCategory(long id,CreateCategoryRequest request);

    void enableCategory(long id);

    void deleteCategory(long id);
}
