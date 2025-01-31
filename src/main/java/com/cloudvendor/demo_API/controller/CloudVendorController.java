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

    @GetMapping("/allvendors")
    public List<CloudVendor> getAllCloudVendors(){

        return cloudVendorService.getAllCloudVendors();
    }

    @GetMapping("/{vendorId}")
    public CloudVendor getCloudVendorInfoById(@PathVariable String vendorId){

        return cloudVendorService.getCloudVendorInfo((vendorId));
    }

    @GetMapping("/vendorname/{vendorName}")
    public List<CloudVendor> getCloudVendorByName(@PathVariable String vendorName){
        return cloudVendorService.getCloudVendorByName(vendorName);
    }

    @PostMapping("/createvendor")
    public String createCloudVendorInfo(@RequestBody CloudVendor cloudVendor){
        return cloudVendorService.createCloudVendor(cloudVendor);
    }

    @DeleteMapping("/deletevendor/{vendorId}")
    public String deleteCloudVendor(@PathVariable String vendorId){
        return cloudVendorService.deleteCloudVendor(Integer.parseInt(vendorId));
    }

    @PutMapping("/updatevendor/{vendorId}")
    public String updateVendorInfo(@RequestBody CloudVendor cloudVendor, String vendorId){
       return cloudVendorService.updateCloudVendorInfo(cloudVendor, vendorId);
    }

    @PatchMapping("/updatepart/{vendorId}")
    public String updateVendorSpecificInfo(@PathVariable String vendorId, @RequestBody Map<String, Object> updates){
        return cloudVendorService.updateSpecificCloudVendorInfo(vendorId, updates);
    }
}
