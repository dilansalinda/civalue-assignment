package org.civalue.apiservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "shopper")
@Data
public class Shopper {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @Column(name = "shopper_id")
    private String shopperId;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "relevancy_score")
    private Double relevancyScore;

}
