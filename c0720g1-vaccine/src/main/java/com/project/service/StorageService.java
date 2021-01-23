package com.project.service;

import com.project.entity.Storage;
import com.project.repository.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface StorageService {

   List<Storage> findAll();

   Storage findById(Integer id);
}
