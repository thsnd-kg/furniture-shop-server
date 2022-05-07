package com.furnitureshop.product.dto.variant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateValueDto {
    @NotNull(message = "{value.option.not-null}")
    private Long optionId;

    @NotNull(message = "{value.optionValue.not-null}")
    @Size(max = 50, message = "{value.optionValue.size}")
    private String optionValue;

    @Size(max = 300, message = "{value.optionImage.size}")
    private String optionImage;
}
