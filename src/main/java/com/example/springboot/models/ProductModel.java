package com.example.springboot.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "tb_produtos")

public class ProductModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID product_id;
    private String product_name;
    private BigDecimal product_value;

    public UUID getProduct_id() {
        return product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public BigDecimal getProduct_value() {
        return product_value;
    }

    public void setProduct_id(UUID product_id) {
        this.product_id = product_id;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setProduct_value(BigDecimal product_value) {
        this.product_value = product_value;
    }
}
