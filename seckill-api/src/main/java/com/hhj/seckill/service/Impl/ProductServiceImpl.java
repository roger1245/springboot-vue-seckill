package com.hhj.seckill.service.Impl;

import com.github.pagehelper.PageHelper;
import com.hhj.seckill.entry.Product;
import com.hhj.seckill.entry.ProductExample;
import com.hhj.seckill.mapper.ProductMapper;
import com.hhj.seckill.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    public List<Product> getProductByCategoryId(Integer categoryId) {
        List<Product> list = null;
        ProductExample example = new ProductExample();
//        example.createCriteria().andCategoryIdEqualTo(categoryId);
        PageHelper.startPage(1, 8);
        list = productMapper.selectByExample(example);
        return list;
    }
}