package com.cqrs.controller;


import com.cqrs.dto.ProductEvent;
import com.cqrs.entity.Product;
import com.cqrs.service.ProductCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductCommandController {

    @Autowired
    private ProductCommandService productCommandService ;

    @PostMapping
    public Product createProduct(@RequestBody ProductEvent productEvent) {
        return productCommandService.createProduct(productEvent) ;}

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody ProductEvent productEvent) {
        return productCommandService.updateProduct(id,productEvent) ;
    }


}
