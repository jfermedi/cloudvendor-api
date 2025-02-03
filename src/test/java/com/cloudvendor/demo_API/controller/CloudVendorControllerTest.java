package com.cloudvendor.demo_API.controller;

import com.cloudvendor.demo_API.model.CloudVendor;
import com.cloudvendor.demo_API.service.CloudVendorService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class CloudVendorControllerTest {

    @Mock
    CloudVendorService cloudVendorService;
    @InjectMocks
    CloudVendorController cloudVendorController;
    CloudVendor cloudVendor;
    CloudVendor cloudVendor2;
    CloudVendor cloudVendor3;
    AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
       autoCloseable = MockitoAnnotations.openMocks(this);
       cloudVendor = new CloudVendor("1", "Address 1", "2222-3333", "Amazon");
       cloudVendor2 = new CloudVendor("2","Address 2", "2222-4444", "Google");
       cloudVendor3 = new CloudVendor("3","Address 3", "2222-5555", "Google");
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void getAllCloudVendors() {

        List<CloudVendor> cloudVendorList = List.of(cloudVendor, cloudVendor2);

        when(cloudVendorService.getAllCloudVendors()).thenReturn(cloudVendorList);

        List<CloudVendor> result = cloudVendorController.getAllCloudVendors();

        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(3);
        assertThat(result.get(0).getVendorId()).isEqualTo(1);
        assertThat(result.get(1).getVendorId()).isEqualTo(2);
        assertThat(result.get(2).getVendorId()).isEqualTo(3);
    }

    @Test
    void getCloudVendorInfoById() {

        when(cloudVendorService.getCloudVendorInfo("1")).thenReturn(cloudVendor);

        CloudVendor result = cloudVendorController.getCloudVendorInfoById("1");

        assertThat(result).isNotNull();
        assertThat(result.getVendorId()).isEqualTo(1);
        assertThat(result.getVendorName()).isEqualTo("Amazon");
        assertThat(result.getVendorAddress()).isEqualTo("Address 1");
        assertThat(result.getVendorPhoneNumber()).isEqualTo("2222-3333");

    }

    @Test
    void getCloudVendorByName() {

        List<CloudVendor> cloudVendorList = List.of(cloudVendor2, cloudVendor3);

        when(cloudVendorService.getCloudVendorByName("Google")).thenReturn(cloudVendorList);

        List<CloudVendor> result = cloudVendorController.getCloudVendorByName("Google");

        assertThat(result).isNotNull();
        assertThat(result.get(0).getVendorId()).isEqualTo(2);
        assertThat(result.get(1).getVendorId()).isEqualTo(3);
        assertThat(result.get(0).getVendorName()).isEqualTo("Google");
        assertThat(result.get(1).getVendorName()).isEqualTo("Google");
    }

    @Test
    void createCloudVendorInfo() {
        CloudVendor cloudVendor = new CloudVendor("Address 4", "1111-4444", "Azure");

        when(cloudVendorService.createCloudVendor(cloudVendor)).thenReturn("Cloud Vendor created");

        String result = cloudVendorController.createCloudVendorInfo(cloudVendor);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo("Cloud Vendor created");
    }

    @Test
    void deleteCloudVendor() {

        when(cloudVendorService.deleteCloudVendor(1)).thenReturn("Cloud Vendor deleted");

        String result = cloudVendorController.deleteCloudVendor("1");

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo("Cloud Vendor deleted");
    }

    @Test
    void updateVendorInfo() {
        CloudVendor cloudVendorToUpdate = new CloudVendor("2","Address 2", "2222-4444", "Azure Cloud");

        when(cloudVendorService.updateCloudVendorInfo(cloudVendorToUpdate, "2")).thenReturn("Cloud Vendor updated");

        String result = cloudVendorController.updateVendorInfo(cloudVendorToUpdate, "2");

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo("Cloud Vendor updated");
    }

    @Test
    void updateVendorSpecificInfo() {

        Map<String, Object> dataToUpdate = new HashMap<>();
        String newAddres = "Address to be updated";
        dataToUpdate.put("vendorAdress", newAddres);

        when(cloudVendorService.updateSpecificCloudVendorInfo("1", dataToUpdate)).thenReturn("Cloud Vendor partial updated with sucess");

        String result = cloudVendorController.updateVendorSpecificInfo("1", dataToUpdate);


        assertThat(result).isNotNull();
        assertThat(result).isEqualTo("Cloud Vendor partial updated with sucess");


    }
}