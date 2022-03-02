package com.hhj.seckill.controller;

import com.github.pagehelper.PageInfo;
import com.hhj.seckill.common.Result;
import com.hhj.seckill.entry.Good;
import com.hhj.seckill.entry.Product;
import com.hhj.seckill.entry.ProductCategory;
import com.hhj.seckill.entry.ProductPicture;
import com.hhj.seckill.service.GoodService;
import com.hhj.seckill.service.ProductService;
import com.hhj.seckill.vo.ProductDetail;
import com.hhj.seckill.vo.ProductList;
import com.hhj.seckill.vo.ProductVo;
import com.hhj.seckill.vo.SearchVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author virtual
 * @Date 2021/5/29 17:23
 * @Version 1.0
 */
@RestController
@RequestMapping("/good")
@Api("操作商品表")
public class GoodController {

    @Autowired
    GoodService service;
    @Autowired
    ProductService productService;

    @GetMapping("list")
    @ApiOperation("分页查询")
    public Result page(@RequestParam("curpage") int curPage, @RequestParam("size") int size) {
        PageInfo<Good> goodPageInfo = service.selectPage(curPage, size);
        return Result.success(goodPageInfo);
    }

    @RequestMapping(value = "/getProduct", method = RequestMethod.POST)
    public Result getProduct(@RequestBody ProductVo productVo) {
        List<String> categoryIds = productVo.getCategoryIds();
        String currentPage = productVo.getCurrentPage();
        if (currentPage == null || currentPage.isEmpty()) {
            currentPage = "1";
        }
        String pageSize = productVo.getPageSize();
        if (pageSize == null || pageSize.isEmpty()) {
            pageSize = "7";
        }
        ProductList list = productService.getProductByCategoryId(categoryIds, Integer.parseInt(currentPage), Integer.parseInt(pageSize));
        return Result.success(list);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result getBySearch(@RequestBody SearchVo searchVo) {
        String search = searchVo.getSearch();
        String currentPage = searchVo.getCurrentPage();
        if (currentPage == null || currentPage.isEmpty()) {
            currentPage = "1";
        }
        String pageSize = searchVo.getPageSize();
        if (pageSize == null || pageSize.isEmpty()) {
            pageSize = "7";
        }
        ProductList list = productService.getProductBySearch(search, Integer.parseInt(currentPage), Integer.parseInt(pageSize));
        return Result.success(list);
    }

    @RequestMapping(value = "/getHotProduct", method = RequestMethod.POST)
    public Result getHotProduct(@RequestBody Map<String, List<String>> map) {
        List<String> categoryIds = map.get("category_ids");
        if (categoryIds == null) {
            return Result.error("no category ids");
        }
        List<Product> list = productService.getHotProductByCategoryIds(categoryIds);
        return Result.success(list);
    }

    @RequestMapping(value = "/getCategories", method = RequestMethod.POST)
    public Result getCategories(@RequestBody Map<String, List<String>> map) {
        List<String> categoryIds = map.get("category_ids");
        if (categoryIds == null) {
            categoryIds = new ArrayList<>();
        }
        List<ProductCategory> list = productService.getProductCategories(categoryIds);
        return Result.success(list, "success");

    }

    @RequestMapping(value = "/productDetail", method = RequestMethod.POST)
    public Result getProductDetail(@RequestBody ProductDetail productDetail) {
        int id = Integer.parseInt(productDetail.getProductId());
        Product product = productService.getProduct(id);
        return Result.success(product, "success");

    }

    @RequestMapping(value = "/productPicture", method = RequestMethod.POST)
    public Result getProductPicture(@RequestBody ProductDetail productDetail) {
        int id = Integer.parseInt(productDetail.getProductId());
        List<ProductPicture> pictures = productService.getProductPicture(id);
        return Result.success(pictures, "success");

    }

}
