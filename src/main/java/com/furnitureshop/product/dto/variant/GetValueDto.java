package com.furnitureshop.product.dto.variant;

import com.furnitureshop.product.entity.Value;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetValueDto {
    private final Long optionId;
    private final String optionName;
    private final String optionValue;
    private final String optionImage;

    public GetValueDto(Value value) {
        this.optionId = value.getOption().getOptionId();
        this.optionName = value.getOption().getOptionName();
        this.optionValue = value.getOptionValue();
        this.optionImage = value.getOptionImage();
    }
}
