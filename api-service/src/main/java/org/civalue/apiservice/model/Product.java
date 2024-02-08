package org.civalue.apiservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "product")
@Data
public class Product {

    @Id
    private String productId;

    private String category;
    private String brand;

}
