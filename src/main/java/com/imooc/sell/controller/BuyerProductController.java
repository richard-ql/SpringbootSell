package com.imooc.sell.controller;

import com.imooc.sell.dataobject.ProductCategory;
import com.imooc.sell.dataobject.ProductInfo;
import com.imooc.sell.service.CategoryService;
import com.imooc.sell.service.ProductService;
import com.imooc.sell.utils.ResultVOUtil;
import com.imooc.sell.viewobject.ProductInfoVO;
import com.imooc.sell.viewobject.ProductVO;
import com.imooc.sell.viewobject.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public ResultVO list() {
        //查询所有上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();

//        //查询类目（一次性查询）
//        List<Integer> categoryTypeList = new ArrayList<>();
//
//        //传统方法for循环
//        for (ProductInfo productInfo: productInfoList) {
//            categoryTypeList.add(productInfo.getCategoryType());
//        }
        //Java8 lambda
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(ProductInfo::getCategoryType)
                .collect(Collectors.toList());

        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        List<ProductVO> productVOList = new ArrayList<>();
        for(ProductCategory productCategory: productCategoryList) {
           ProductVO productVO = new ProductVO();
           productVO.setCategoryName(productCategory.getCategoryName());
           productVO.setCategoryType(productCategory.getCategoryType());

           List<ProductInfoVO> productInfoVOList = new ArrayList<>();
           for(ProductInfo productInfo: productInfoList) {
               if(productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                   ProductInfoVO productInfoVO = new ProductInfoVO();
                   BeanUtils.copyProperties(productInfo, productInfoVO);
                   productInfoVOList.add(productInfoVO);
               }
           }
           productVO.setProductInfoVOList(productInfoVOList);
           productVOList.add(productVO);
        }

//        ResultVO resultVO = new ResultVO();
//
//        resultVO.setData(productVOList);
//        resultVO.setCode(0);
//        resultVO.setMessage("成功");

        return ResultVOUtil.success(productVOList);
    }
}
