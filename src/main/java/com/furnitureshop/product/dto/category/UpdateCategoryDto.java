package com.furnitureshop.product.dto.category;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
public class UpdateCategoryDto {
    @NotNull(message = "Category id must not be null")
    private Long categoryId;

    @NotNull(message = "Category name must not be null")
    @NotBlank(message = "Category name must not be blank")
    private String categoryName;

    @Size(max = 100, message = "Category description must be less than 100 characters")
    private String categoryDesc;

    @NotNull(message = "Options must not be null")
    @NotEmpty(message = "Options must not be empty")
    private List<UpdateOptionDto> options;

    private Long parentId;
}
