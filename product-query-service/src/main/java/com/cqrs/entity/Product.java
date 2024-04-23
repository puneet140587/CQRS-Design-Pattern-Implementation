package com.cqrs.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("product_query")
public class Product {


    @Id
    private Long id ;
    private String name ;
    private String description ;
    private double price ;



}
