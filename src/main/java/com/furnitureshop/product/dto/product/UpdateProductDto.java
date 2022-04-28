package com.furnitureshop.product.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductDto {
    @NotNull(message = "{product.product.not-null}")
    private Long productId;

    @NotNull(message = "{product.category.not-null}")
    private Long categoryId;

    @NotNull(message = "{product.brand.not-null}")
    private Long brandId;

    @NotNull(message = "{product.name.not-null}")
    @Size(max = 50, message = "{product.name.size}")
    private String productName;

    @Size(max = 1000, message = "{product.desc.size}")
    private String productDesc;

    @NotNull(message = "{product.name.not-null}")
    @Size(max = 300, message = "{product.image.size}")
    private String image;
}
