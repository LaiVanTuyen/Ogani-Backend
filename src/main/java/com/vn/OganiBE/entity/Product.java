package com.vn.OganiBE.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;


    @Column(name = "description",columnDefinition = "TEXT")
    private String description;

    private long price;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany
    @JoinTable(name = "product_image",joinColumns = @JoinColumn(name="product_id"),inverseJoinColumns = @JoinColumn(name="image_id"))
    private Set<Image> images = new HashSet<>();

}
