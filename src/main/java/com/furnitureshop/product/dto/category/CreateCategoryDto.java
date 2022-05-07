package com.furnitureshop.product.dto.category;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class CreateCategoryDto {
    @NotNull(message = "Category name must not be null")
    @NotBlank(message = "Category name must not be blank")
    private String categoryName;

    @Size(max = 100, message = "Category description must be less than 100 characters")
    private String categoryDesc;

    @NotNull(message = "List option must not be null")
    @NotEmpty(message = "List option must not be empty")
    private List<CreateOptionDto> options;

    private Long parentId;
}
