package com.cloudvendor.demo_API.repository;

import com.cloudvendor.demo_API.model.CloudVendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CloudVendorRepository extends JpaRepository<CloudVendor, Integer> {
    public List<CloudVendor> findByVendorName(String vendorName);
    public CloudVendor findByVendorId(Integer vendorId);
}
