package com.furnitureshop.product.service;

import com.furnitureshop.product.dto.product.CreateProductDto;
import com.furnitureshop.product.dto.product.UpdateProductDto;
import com.furnitureshop.product.entity.Product;
import com.furnitureshop.product.entity.ProductPage;
import com.furnitureshop.product.entity.ProductSearchCriteria;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    Product getProductById(Long productId);

    Page<Product> findByProductName(String name, int page, int size);

    Page<Product> getProducts(ProductPage productPage, ProductSearchCriteria productSearchCriteria);

    List<Product> getProducts();

    Page<Product> getProductsActive(int page, int size);

    Product createProduct(CreateProductDto dto);

    Product updateProduct(UpdateProductDto dto);

    Boolean deleteProduct(Long productId);
}
