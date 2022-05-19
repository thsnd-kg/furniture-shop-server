package com.furnitureshop.importer.service;

import com.furnitureshop.importer.dto.CreateImporterDetailDto;
import com.furnitureshop.importer.dto.CreateImporterDto;
import com.furnitureshop.importer.entity.Importer;
import com.furnitureshop.importer.entity.ImporterDetail;
import com.furnitureshop.importer.repository.ImporterRepository;
import com.furnitureshop.product.entity.Variant;
import com.furnitureshop.product.service.VariantService;
import com.furnitureshop.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImporterServiceImpl implements ImporterService {
    private final ImporterRepository repository;
    private final VariantService variantService;
    private final UserService userService;

    @Autowired
    public ImporterServiceImpl(ImporterRepository repository, VariantService variantService, UserService userService) {
        this.repository = repository;
        this.variantService = variantService;
        this.userService = userService;
    }

    @Override
    public List<Importer> getImports() {
        return repository.findAll();
    }

    @Override
    public Importer getImport(Long importId) {
        return repository.findById(importId).orElse(null);
    }

    @Override
    public Importer createImport(CreateImporterDto dto) {
        Importer importer = new Importer();

        if (dto.getImportDesc() != null) {
            importer.setImportDesc(dto.getImportDesc());
        }

        importer.setUser(userService.getProfile());

        for (CreateImporterDetailDto createImporterDetail : dto.getImportDetails()) {
            ImporterDetail importerDetail = new ImporterDetail();

            Variant variant = variantService.getVariantById(createImporterDetail.getVariantId());

            importerDetail.setQuantity(createImporterDetail.getQuantity());
            importerDetail.setPrice(createImporterDetail.getPrice());
            importerDetail.setImporter(importer);
            importerDetail.setVariant(variant);

            importer.getImportDetails().add(importerDetail);
        }

        return repository.save(importer);
    }
}
