package com.furnitureshop.product.service;

import com.furnitureshop.product.entity.Option;

import java.util.List;

public interface OptionService {
    List<Option> getOptions();

    Option getOptionById(Long optionId);
}
