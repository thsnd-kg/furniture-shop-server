package com.furnitureshop.product.controller;

import com.furnitureshop.common.ResponseHandler;
import com.furnitureshop.product.dto.BrandDto;
import com.furnitureshop.product.entity.Brand;
import com.furnitureshop.product.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class BrandController {
    private final BrandService service;

    @Autowired
    public BrandController(BrandService service) {
        this.service = service;
    }

    @GetMapping("/website/brands")
    public Object getBrands(@RequestParam(value = "onlyActive") Boolean isActive) {
        if(isActive) {
            List<Brand> brands = service.getBrandsActive();
            brands.sort(Comparator.comparing(Brand::getBrandId));
            return ResponseHandler.getResponse(brands, HttpStatus.OK);
        }

        List<Brand> brands = service.getBrands();
        brands.sort(Comparator.comparing(Brand::getBrandId));
        return ResponseHandler.getResponse(brands, HttpStatus.OK);
    }


    @GetMapping(path = "/website/brands/{brand-id}")
    public Object getBrandById(@PathVariable("brand-id") Long brandId) {
        try {
            if (brandId == null)
                throw new IllegalStateException("Brand Id must not be null");

            return ResponseHandler.getResponse(service.getBrandById(brandId), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/brands")
    public Object createBrand(@Valid @RequestBody BrandDto newBrand, BindingResult errors){
        try{
            if(errors.hasErrors())
                return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

            return ResponseHandler.getResponse(service.createBrand(newBrand), HttpStatus.OK);
        }catch (Exception e){
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/brands")
    public Object updateBrand(@RequestBody BrandDto updatedBrand, BindingResult errors) {
        try{
            if(errors.hasErrors())
                return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

            return ResponseHandler.getResponse(service.updateBrand(updatedBrand), HttpStatus.OK);
        }catch (Exception e){
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/brands/{brand-id}")
    public Object deleteBrand(@PathVariable("brand-id") Long brandId){
        try{
            if(brandId == null)
                throw new IllegalStateException("Brand Id must not be null");

            return ResponseHandler.getResponse(service.deleteBrand(brandId), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }

    }
}
