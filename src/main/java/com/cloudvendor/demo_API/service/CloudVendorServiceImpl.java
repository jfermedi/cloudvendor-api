package com.cloudvendor.demo_API.service;

import com.cloudvendor.demo_API.exception.CloudVendorNotFoundException;
import com.cloudvendor.demo_API.model.CloudVendor;
import com.cloudvendor.demo_API.repository.CloudVendorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class CloudVendorServiceImpl implements CloudVendorService{
    CloudVendorRepository cloudVendorRepository;

    public CloudVendorServiceImpl(CloudVendorRepository cloudVendorRepository) {
        this.cloudVendorRepository = cloudVendorRepository;
    }

    /**
     * Method implementation to create a new cloud vendor obj
     * and persist on the database
     * @param cloudVendor
     * @return a String with success of operation
     */
    @Override
    public String createCloudVendor(CloudVendor cloudVendor) {

        cloudVendorRepository.save(cloudVendor);
        return "Cloud Vendor created";
    }

    /**
     * Method implementation to return all the cloud vendors
     * existing on the database, if none exists return empty
     * @return a List<CloudVendor>
     */
    @Override
    public List<CloudVendor> getAllCloudVendors() {
        return cloudVendorRepository.findAll();
    }

    /**
     * Method implementation to retrieve a cloud vendor from the
     * database based on vendorId. If vendorId doesn't exist on database
     * a CloudVendorNotFoundException is launched.
     * @param cloudVendorId
     * @return a CloudVendor obj
     */
    @Override
    public CloudVendor getCloudVendorInfo(String cloudVendorId) {
        if(cloudVendorRepository.findById(Integer.parseInt(cloudVendorId)).isEmpty())
            throw new CloudVendorNotFoundException("Cloud Vendor doesn't exist");
        return cloudVendorRepository.findByVendorId(Integer.parseInt(cloudVendorId));
    }

    /**
     * Method implementation to update the cloud vendor information on the database
     * based on vendorId. If the vendorId doesn't exist returns a message
     * @param cloudVendor
     * @param vendorId
     * @return a String with success or fail
     */
    @Override
    public String updateCloudVendorInfo(CloudVendor cloudVendor, String vendorId) {

        if (cloudVendorRepository.existsById(Integer.parseInt(vendorId))){
            cloudVendorRepository.save(cloudVendor);
            return "Cloud Vendor updated";
        }else{

            return "Cloud Vendor doesn't exist";
        }

    }

    /**
     * Method implementation to delete the cloud vendor from the
     * database, based on vendorId. If the vendorId doesn't exist returns a message
     * @param cloudVendorId
     * @return a String with success or fail
     */
    @Override
    public String deleteCloudVendor(Integer cloudVendorId) {

        if(cloudVendorRepository.findById(cloudVendorId).isEmpty())
            return "Cloud Vendor doesn't exist";
        else {
            CloudVendor cloudVendor = cloudVendorRepository.findById(cloudVendorId).get();
            cloudVendorRepository.delete(cloudVendor);
            return "Cloud Vendor deleted";
            }

    }

    /**
     * Method implementation to return all the cloud vendors from the
     * database with the same vendorName, based on vendorId.
     * @param cloudVendorName
     * @return a List<CloudVendor>
     */
    @Override
    public List<CloudVendor> getCloudVendorByName(String cloudVendorName) {

        return cloudVendorRepository.findByVendorName(cloudVendorName);
    }

    /**
     * Method implementation to update one or more specific fields of the cloud vendor
     * in the database, based on vendorId. If the vendorId doesn't exist on the database
     * launches a EntityNotFoundException
     * @param vendorId
     * @param updates
     * @return a String with success or fail
     */
    @Override
    public String updateSpecificCloudVendorInfo(String vendorId, Map<String, Object> updates) {
        //check if the entity exists
       CloudVendor cloudVendorToFind = cloudVendorRepository.findById(Integer.parseInt(vendorId)).
               orElseThrow(() -> new EntityNotFoundException("Cloud Vendor not found for the id" + vendorId));
        //loops through the parameters to be updated
       updates.forEach((key, value) -> {
           Field field = ReflectionUtils.findField(CloudVendor.class, key);
           if(field != null){
               field.setAccessible(true);
               ReflectionUtils.setField(field,  cloudVendorToFind, value);
           }
       });

       cloudVendorRepository.save(cloudVendorToFind);
       return "Cloud Vendor partial updated with sucess";
    }

}
