package com.cqrs.service;

import com.cqrs.dto.ProductEvent;
import com.cqrs.entity.Product;
import com.cqrs.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Service
public class ProductQueryService {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @KafkaListener(topics = "product-event-topic", groupId = "product-event-group")
    public void processProductEvents(ProductEvent productEvent) {
        Product product = productEvent.getProduct();
        if (productEvent.getEventType().equals("CreateProduct")) {
            productRepository.save(product);
        }
        if (productEvent.getEventType().equals("UpdateProduct")) {
            Product existingProduct = productRepository.findById(product.getId()).get();
            existingProduct.setName(product.getName());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setPrice(product.getPrice());
            productRepository.save(product);
        }

    }
}
