package com.furnitureshop.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.furnitureshop.common.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name",
            length = 50,
            nullable = false)
    private String productName;

    @Column(name = "product_desc",
            length = 1000,
            columnDefinition = "varchar(1000) default 'No description'")
    private String productDesc = "No description";

    @Column(name = "image",
            length = 300)
    private String image;

    @Column(name = "is_deleted",
            columnDefinition = "boolean default false")
    private Boolean isDeleted = false;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "product")
    private Set<Variant> variants = new HashSet<>();
}
