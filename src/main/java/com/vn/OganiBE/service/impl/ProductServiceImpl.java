package com.vn.OganiBE.service.impl;

import com.vn.OganiBE.entity.Category;
import com.vn.OganiBE.entity.Image;
import com.vn.OganiBE.entity.Product;
import com.vn.OganiBE.exception.NotFoundException;
import com.vn.OganiBE.model.request.CreateProductRequest;
import com.vn.OganiBE.repository.CategoryRepository;
import com.vn.OganiBE.repository.ImageRepository;
import com.vn.OganiBE.repository.ProductRepository;
import com.vn.OganiBE.service.ProductService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    private final ImageRepository imageRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, ImageRepository imageRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.imageRepository = imageRepository;
    }

    @Override
    public List<Product> getList() {
        // TODO Auto-generated method stub
        return productRepository.findAll(Sort.by("id").descending());
    }

    @Override
    public Product getProduct(long id) {
        // TODO Auto-generated method stub

        return productRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Product With Id: " + id));
    }
    

    @Override
    public Product createProduct(CreateProductRequest request) {
        // TODO Auto-generated method stub
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(()-> new NotFoundException("Not Found Category With Id: " + request.getCategoryId()));
        product.setCategory(category);

        Set<Image> images = new HashSet<>();
        for(long imageId: request.getImageIds()){
            Image image = imageRepository.findById(imageId).orElseThrow(() -> new NotFoundException("Not Found Image With Id: " + imageId));
            images.add(image);
        }
        product.setImages(images);
        productRepository.save(product);
        return product;
    }

    @Override
    public Product updateProduct(long id, CreateProductRequest request) {
        // TODO Auto-generated method stub
        Product product= productRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Product With Id: " + id));
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(()-> new NotFoundException("Not Found Category With Id: " + request.getCategoryId()));
        product.setCategory(category);

        Set<Image> images = new HashSet<>();
        for(long imageId: request.getImageIds()){
            Image image = imageRepository.findById(imageId).orElseThrow(() -> new NotFoundException("Not Found Image With Id: " + imageId));
            images.add(image);
        }
        product.setImages(images);
        productRepository.save(product);

        return product;
    }

    @Override
    public void deleteProduct(long id) {
        // TODO Auto-generated method stub
        Product product= productRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Product With Id: " + id));
        product.getImages().remove(this);
        productRepository.delete(product);
    }

    @Override
    public List<Product> getListNewst(int number) {
        // TODO Auto-generated method stub
        return productRepository.getListNewest(number);
    }

    @Override
    public List<Product> getListByPrice() {
        // TODO Auto-generated method stub
        return productRepository.getListByPrice();
    }

    @Override
    public List<Product> findRelatedProduct(long id){
        return productRepository.findRelatedProduct(id);

    }

    @Override
    public List<Product> getListProductByCategory(long id){
        return productRepository.getListProductByCategory(id);
    }

    @Override
    public List<Product> getListByPriceRange(long id,int min, int max){
        return productRepository.getListProductByPriceRange(id, min, max);
    }

    @Override
    public List<Product> searchProduct(String keyword) {
        // TODO Auto-generated method stub
        return productRepository.searchProduct(keyword);
    }


    
}
