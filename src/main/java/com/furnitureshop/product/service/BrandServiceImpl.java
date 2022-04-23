package com.furnitureshop.product.service;
import com.furnitureshop.product.dto.BrandDto;
import com.furnitureshop.product.entity.Brand;
import com.furnitureshop.product.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {
    private final BrandRepository repo;

    @Autowired
    public BrandServiceImpl(BrandRepository repository) {
        this.repo = repository;
    }

    @Override
    public List<Brand> getBrands() {
        return repo.findAll();
    }

    @Override
    public List<Brand> getBrandsActive() {
        return repo.findByIsDeletedFalse();
    }

    @Override
    public Brand getBrandById(Long brandId) {
        Optional<Brand> brand = repo.findById(brandId);
        if(brand.isPresent())
            return brand.get();

        throw new IllegalStateException("Brand does not exist");
    }

    @Override
    public Brand createBrand(BrandDto dto) {
        Brand brand = handleData(dto, false);
        return repo.save(brand);
    }

    @Override
    public Brand updateBrand(BrandDto dto) {
        Brand brand = handleData(dto, true);
        return repo.save(brand);
    }

    @Override
    public Boolean deleteBrand(Long brandId) {
        if(!isExisted(brandId))
            throw new IllegalStateException("Brand does not exist");

        Brand brand = repo.getById(brandId);
        brand.setIsDeleted(true);
        repo.save(brand);
        return true;
    }

    public boolean isExisted(Long brandId){
        Optional<Brand> brand = repo.findById(brandId);
        if(brand.isPresent())
            return true;

        return false;
    }

    public Brand handleData(BrandDto dto, boolean hasId){
        Brand brand = new Brand();

        if(hasId) {
            if (dto.getBrandId() == null)
                throw new IllegalStateException("Brand Id must not be null");
            if (isExisted(dto.getBrandId()))
                brand = repo.getById(dto.getBrandId());
        }

        if(dto.getBrandName() != null)
            brand.setBrandName(dto.getBrandName());

        if(dto.getDescription() !=null) {
            brand.setBrandDesc(dto.getDescription());
        }

        return  brand;
    }

}
