package com.hhj.seckill.service;

import com.hhj.seckill.entry.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getProductByCategoryId(Integer categoryId);

    public List<Product> getHotProductByCategoryIds(List<String> ids);
}
