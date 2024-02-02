package com.vn.OganiBE.controller;

import com.vn.OganiBE.entity.Category;
import com.vn.OganiBE.model.request.CreateCategoryRequest;
import com.vn.OganiBE.model.response.MessageResponse;
import com.vn.OganiBE.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/category")
@CrossOrigin(origins = "*",maxAge = 3600)
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    @Operation(summary="Lấy danh sách danh mục")
    public ResponseEntity<?> getListCategory(){
        List<Category> categories = categoryService.findAll();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/enabled")
    @Operation(summary="Lấy ra danh sách danh mục đã kích hoạt")
    public ResponseEntity<List<Category>> getListEnabled(){
        List<Category> categories = categoryService.getListEnabled();
        return ResponseEntity.ok(categories);
    }


    @PostMapping("/create")
    @Operation(summary="Tạo mới danh mục")
    public ResponseEntity<?> createCategory(@Valid @RequestBody CreateCategoryRequest request){
        Category category = categoryService.createCategory(request);

        return ResponseEntity.ok(category);
    }

    @PutMapping("/update/{id}")
    @Operation(summary="Tìm danh mục bằng id và cập nhật danh mục đó")
    public ResponseEntity<?> updateCategory(@PathVariable long id, @Valid @RequestBody CreateCategoryRequest request){
        Category category = categoryService.updateCategory(id, request);
        return ResponseEntity.ok(category);
    }

    @PutMapping("/enable/{id}")
    @Operation(summary="Kích hoạt danh mục bằng id")
    public ResponseEntity<?> enabled(@PathVariable long id){
        categoryService.enableCategory(id);
        return ResponseEntity.ok(new MessageResponse("Cập nhật thành công"));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary="Xóa danh mục bằng id")
    public ResponseEntity<?> delete(@PathVariable long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(new MessageResponse("Xóa thành công"));
    }


}
