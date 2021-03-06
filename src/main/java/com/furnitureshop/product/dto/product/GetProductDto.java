package com.furnitureshop.product.dto.product;

import com.furnitureshop.product.dto.variant.GetVariantDto;
import com.furnitureshop.product.entity.Product;
import com.furnitureshop.product.entity.Variant;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class GetProductDto {
    private final Long productId;
    private final String productName;
    private final Long categoryId;
    private final String categoryName;
    private final Long brandId;
    private final String brandName;
    private final String productDesc;
    private final String image;
    private final Boolean isDeleted;
    private final List<GetVariantDto> variants;

    public GetProductDto(Product product) {
        this.productId = product.getProductId();
        this.productName = product.getProductName();
        this.categoryId = product.getCategory().getCategoryId();
        this.categoryName = product.getCategory().getCategoryName();
        this.brandId = product.getBrand().getBrandId();
        this.brandName = product.getBrand().getBrandName();
        this.productDesc = product.getProductDesc();
        this.image = product.getImage();
        this.isDeleted = product.getIsDeleted();
        List<Variant> variantsEntity = new ArrayList<>() ;
        for(Variant v  : product.getVariants()) {
            if(!v.getIsDeleted()) variantsEntity.add(v);
        }
        this.variants = product.getVariants().isEmpty() ? new ArrayList<>() : variantsEntity.stream().map(GetVariantDto::new).collect(Collectors.toList());
        this.variants.sort(Comparator.comparing(GetVariantDto::getVariantId));
    }
}
