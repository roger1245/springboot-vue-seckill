package com.hhj.seckill.service;

import com.hhj.seckill.entry.Product;
import com.hhj.seckill.entry.ProductCategory;
import com.hhj.seckill.entry.ProductPicture;
import com.hhj.seckill.vo.ProductList;

import java.util.List;

public interface ProductService {
    public ProductList getProductByCategoryId(List<String> categoryIds, Integer currentPage, Integer pageSize);

    public List<Product> getHotProductByCategoryIds(List<String> ids);

    public List<ProductCategory> getProductCategories(List<String> ids);

    public ProductList getProductBySearch(String search, Integer currentPage, Integer pageSize);

    public Product getProduct(int id);

    public List<ProductPicture> getProductPicture(int id);
}
