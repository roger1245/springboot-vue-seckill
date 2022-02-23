package com.hhj.seckill.service;

import com.hhj.seckill.entry.Product;
import com.hhj.seckill.entry.ProductCategory;
import com.hhj.seckill.vo.ProductList;

import java.util.List;

public interface ProductService {
    public ProductList getProductByCategoryId(List<String> categoryIds, Integer currentPage, Integer pageSize);

    public List<Product> getHotProductByCategoryIds(List<String> ids);

    public List<ProductCategory> getProductCategories(List<String> ids);
}
