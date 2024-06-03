package com.targetindia.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name="PRODUCTS")
@JacksonXmlRootElement(localName = "product")
public class Product {
    @Id
    @GeneratedValue(generator = "increment")
    @Column(name="product_id")
    private Integer id;
    @Column(name="product_name")
    private String name;
    @Column(name="unit_Price")
    private Double unitPrice;

}
