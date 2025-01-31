package com.cloudvendor.demo_API.service;


import com.cloudvendor.demo_API.model.CloudVendor;

import java.util.List;
import java.util.Map;

public interface CloudVendorService {

    public String createCloudVendor(CloudVendor cloudVendor);
    public List<CloudVendor> getAllCloudVendors();
    public CloudVendor getCloudVendorInfo(String cloudVendorId);
    public String updateCloudVendorInfo(CloudVendor cloudVendor, String vendorId);
    public String deleteCloudVendor(Integer cloudVendorId);
    public List<CloudVendor> getCloudVendorByName(String cloudVendorName);
    String updateSpecificCloudVendorInfo(String vendorId, Map<String, Object> updates);
}
