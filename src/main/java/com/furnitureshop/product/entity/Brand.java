package com.furnitureshop.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.furnitureshop.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Brand extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    private Long brandId;

    @Column(name = "brand_name",
            length = 50,
            nullable = false)
    private String brandName;

    @Column(name = "brand_desc",
            length = 1000,
            columnDefinition = "varchar(1000) default 'No description'")
    private String brandDesc = "No description";

    @Column(name = "is_deleted",
            columnDefinition = "boolean default false")
    private Boolean isDeleted = false;

    @JsonIgnore
    @OneToMany(mappedBy = "brand")
    private Set<Product> products = new HashSet<>();
}
