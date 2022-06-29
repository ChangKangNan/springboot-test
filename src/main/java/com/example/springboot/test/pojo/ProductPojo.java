package com.example.springboot.test.pojo;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;
import lombok.experimental.Accessors;

/**
* @author kangnan.chang
*/

@Accessors(chain=true)
@Table(name = "product")
@Entity
@Data
public class ProductPojo {

    @Id
    @Column(name = "id")
    private Long id;


    @Column(name = "product_name")
    private String productName;


    @Column(name = "relation_stock")
    private String relationStock;


    @Column(name = "org")
    private String org;


}
