package com.example.apollotabacco.services;

import com.example.apollotabacco.entities.Bucket;
import com.example.apollotabacco.repositories.BucketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BucketService {
    @Autowired
    private BucketRepo bucketRepo;
    public Bucket getSize(Long id) {
        return bucketRepo.getBucketById(id);
    }
    public Bucket save(Bucket bucket) {
        bucketRepo.save(bucket);
        return bucket;
    }
}
