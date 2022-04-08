package com.furnitureshop.product.service;

import com.furnitureshop.product.entity.VariantValue;
import com.furnitureshop.product.repository.VariantValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VariantValueServiceImpl implements VariantValueService {
    private final VariantValueRepository repository;

    @Autowired
    public VariantValueServiceImpl(VariantValueRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<VariantValue> getVariantValues() {
        return repository.findAll();
    }
}
