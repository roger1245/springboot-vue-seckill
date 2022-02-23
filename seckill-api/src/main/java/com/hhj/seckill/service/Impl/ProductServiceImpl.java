package com.hhj.seckill.service.Impl;

import com.github.pagehelper.PageHelper;
import com.hhj.seckill.entry.Product;
import com.hhj.seckill.entry.ProductCategory;
import com.hhj.seckill.entry.ProductCategoryExample;
import com.hhj.seckill.entry.ProductExample;
import com.hhj.seckill.mapper.ProductCategoryMapper;
import com.hhj.seckill.mapper.ProductMapper;
import com.hhj.seckill.service.ProductService;
import com.hhj.seckill.vo.ProductList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductCategoryMapper productCategoryMapper;


    public ProductList getProductByCategoryId(List<String> categoryIds, Integer currentPage, Integer pageSize) {
        List<Integer> ids_local = new ArrayList<>();
        if (categoryIds != null && !categoryIds.isEmpty()) {
            categoryIds.forEach(item -> {
                Integer i = Integer.parseInt(item);
                ids_local.add(i);
            });
        }
        List<Product> list = null;
        ProductExample example = new ProductExample();
        if (!ids_local.isEmpty()) { //如果说明为空则是全部商品，只有不为空，才加判断
            example.createCriteria().andCategoryIdIn(ids_local);
        }
        PageHelper.startPage(currentPage, pageSize);
        list = productMapper.selectByExample(example);
        int total = productMapper.countByExample(example);
        return new ProductList(list, total);
    }

    @Override
    public List<Product> getHotProductByCategoryIds(List<String> ids) {
        List<Integer> ids_local = new ArrayList<>();
        ids.forEach(item -> {
            Integer i = Integer.parseInt(item);
            ids_local.add(i);
        });
        List<Product> list = null;
        ProductExample example = new ProductExample();
        example.setOrderByClause("`product_sales` desc");
        example.createCriteria().andCategoryIdIn(ids_local);
        PageHelper.startPage(1, 7);
        list = productMapper.selectByExample(example);
        return list;
    }

    @Override
    public List<ProductCategory> getProductCategories(List<String> ids) {
        List<Integer> ids_local = new ArrayList<>();
        ids.forEach(item -> {
            Integer i = Integer.parseInt(item);
            ids_local.add(i);
        });
        List<ProductCategory> productCategories = new ArrayList<>();
        ProductCategoryExample example = new ProductCategoryExample();
        if (ids.size() != 0) {
            example.createCriteria().andCategoryIdIn(ids_local);
        }
        productCategories = productCategoryMapper.selectByExample(example);
        return productCategories;
    }
}
