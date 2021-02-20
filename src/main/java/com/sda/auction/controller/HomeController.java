package com.sda.auction.controller;

import com.sda.auction.dto.ProductDto;
import com.sda.auction.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.dialect.ProgressDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@Controller
public class HomeController {

    // == fields
    private final ProductService productService;

    // == constructor
    @Autowired
    public HomeController(ProductService productService){
        this.productService = productService;
    }

    // == mapping methods==
    @GetMapping("/home")
    public String getHomePage(Model model){
        List<ProductDto> productDtoList = productService.getProductDtoList();
        model.addAttribute("productDtoList", productDtoList);
        return "home";
    }

    @GetMapping("/viewProduct")
    public String getViewProduct(Model model){
        ProductDto productDto = new ProductDto();
        productDto.setName("Flamingo");
        productDto.setDescription("aceasta este un produs");
        productDto.setStartingPrice("23");
        productDto.setCategory("Pisici");
        model.addAttribute("product", productDto);
        return "viewProduct";
    }
}
