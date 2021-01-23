package com.project.service.impl;

import com.project.entity.Storage;
import com.project.repository.StorageRepository;
import com.project.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    private StorageRepository storageRepository;
    @Override
    public List<Storage> findAll() {
        return storageRepository.findAll();
    }

    @Override
    public Storage findById(Integer id) {
        return this.storageRepository.findById(id).orElse(null);
    }
}
