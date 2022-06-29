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
@Table(name = "test_user")
@Entity
@Data
public class UserPojo {

    @Id
    @Column(name = "id")
    private Long id;


    @Column(name = "product_name")
    private String productName;


    @Column(name = "numbers")
    private Double numbers;


    @Column(name = "password")
    private String password;


    @Column(name = "degree")
    private Integer degree;


}
