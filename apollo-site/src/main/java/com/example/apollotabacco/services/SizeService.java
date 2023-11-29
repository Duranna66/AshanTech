package com.example.apollotabacco.services;

import com.example.apollotabacco.entities.Size;
import com.example.apollotabacco.repositories.SizeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SizeService {
    @Autowired
    private SizeRepo sizeRepo;
    public Size getSize(Long id) {
        return sizeRepo.getSizeById(id);
    }
    public Size save(Size size) {
        sizeRepo.save(size);
        return size;
    }
}
