package com.cloudvendor.demo_API.controller;

import com.cloudvendor.demo_API.model.CloudVendor;
import com.cloudvendor.demo_API.service.CloudVendorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cloudvendor")
public class CloudVendorController {

    CloudVendorService cloudVendorService;

    public CloudVendorController(CloudVendorService cloudVendorService) {
        this.cloudVendorService = cloudVendorService;
    }

    /**
     * Get endpoint to return all existing cloud vendors
     * @return a List<CloudVendor>
     */
    @GetMapping("/allvendors")
    public List<CloudVendor> getAllCloudVendors(){

        return cloudVendorService.getAllCloudVendors();
    }

    /**
     * Get endpoint to return the cloud vendor
     * based on vendorId
     * @param vendorId
     * @return a CloudVendor obj
     */
    @GetMapping("/{vendorId}")
    public CloudVendor getCloudVendorInfoById(@PathVariable String vendorId){

        return cloudVendorService.getCloudVendorInfo((vendorId));
    }

    /**
     * Get endpoint a list of  cloud vendor
     * based on vendorName
     * @param vendorName
     * @return a List<CloudVendor>
     */
    @GetMapping("/vendorname/{vendorName}")
    public List<CloudVendor> getCloudVendorByName(@PathVariable String vendorName){
        return cloudVendorService.getCloudVendorByName(vendorName);
    }

    /**
     * Post endpoint to create a new cloud vendor
     * @param cloudVendor
     * @return a String with the success
     */
    @PostMapping("/createvendor")
    public String createCloudVendorInfo(@RequestBody CloudVendor cloudVendor){
        return cloudVendorService.createCloudVendor(cloudVendor);
    }

    /**
     * Delete endpoint for cloud vendor
     * based on vendorId
     * @param vendorId
     * @return a String with success or fail
     */
    @DeleteMapping("/deletevendor/{vendorId}")
    public String deleteCloudVendor(@PathVariable String vendorId){
        return cloudVendorService.deleteCloudVendor(Integer.parseInt(vendorId));
    }

    /**
     * Put endpoint for updating the cloud vendor
     * based on vendorId
     * @param cloudVendor
     * @param vendorId
     * @return a String with success or fail
     */
    @PutMapping("/updatevendor/{vendorId}")
    public String updateVendorInfo(@RequestBody CloudVendor cloudVendor, String vendorId){
       return cloudVendorService.updateCloudVendorInfo(cloudVendor, vendorId);
    }

    /**
     * Patch endpoint for updating a specific info on the
     * cloud vendor based on vendorId
     * @param vendorId
     * @param updates
     * @return a String with success or fail
     */
    @PatchMapping("/updatepart/{vendorId}")
    public String updateVendorSpecificInfo(@PathVariable String vendorId, @RequestBody Map<String, Object> updates){
        return cloudVendorService.updateSpecificCloudVendorInfo(vendorId, updates);
    }
}
