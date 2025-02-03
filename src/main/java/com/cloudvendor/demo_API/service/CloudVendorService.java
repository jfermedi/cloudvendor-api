package com.cloudvendor.demo_API.service;


import com.cloudvendor.demo_API.model.CloudVendor;

import java.util.List;
import java.util.Map;

public interface CloudVendorService {

    /**
     * Abstract method to create a new cloud vendor
     * @param cloudVendor
     * @return a String with success
     */
    public String createCloudVendor(CloudVendor cloudVendor);

    /**
     * Abstract method to return all the cloud vendors
     * @return a List<CloudVendor>
     */
    public List<CloudVendor> getAllCloudVendors();

    /**
     * Abstract method to return a cloud vendor
     * based on vendorId
     * @param cloudVendorId
     * @return a CloudVendor obj
     */
    public CloudVendor getCloudVendorInfo(String cloudVendorId);

    /**
     * Abstract method to update the cloud vendor
     * based on vendorId
     * @param cloudVendor
     * @param vendorId
     * @return a String with success or fail
     */
    public String updateCloudVendorInfo(CloudVendor cloudVendor, String vendorId);

    /**
     * Abstract method to delete the cloud vendor
     * based on vendorId
     * @param cloudVendorId
     * @return a String with success or fail
     */
    public String deleteCloudVendor(Integer cloudVendorId);

    /**
     * Abstract method to get cloud vendor
     * based on vendorName
     * @param cloudVendorName
     * @return a List<CloudVendor>
     */
    public List<CloudVendor> getCloudVendorByName(String cloudVendorName);

    /**
     * Abstract method to update a specific info on the cloud vendor
     * based on vendorId
     * @param vendorId
     * @param updates
     * @return a String with success or fail
     */
    String updateSpecificCloudVendorInfo(String vendorId, Map<String, Object> updates);
}
