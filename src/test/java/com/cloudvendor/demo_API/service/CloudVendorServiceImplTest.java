package com.cloudvendor.demo_API.service;

import com.cloudvendor.demo_API.model.CloudVendor;
import com.cloudvendor.demo_API.repository.CloudVendorRepository;
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
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class CloudVendorServiceImplTest {

    @Mock
    CloudVendorRepository cloudVendorRepository;
    @InjectMocks
    CloudVendorServiceImpl cloudVendorService;
    AutoCloseable autoCloseable;
    CloudVendor cloudVendor;
    CloudVendor cloudVendor2;
    CloudVendor cloudVendor3;

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
    void createCloudVendor() {
        when(cloudVendorRepository.save(cloudVendor)).thenReturn(cloudVendor);

        String result = cloudVendorService.createCloudVendor(cloudVendor);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo("Cloud Vendor created");
    }

    @Test
    void getAllCloudVendors() {
        List<CloudVendor> cloudVendorList = List.of(cloudVendor,cloudVendor2, cloudVendor3);

        when(cloudVendorRepository.findAll()).thenReturn(cloudVendorList);

        List<CloudVendor> cloudVendorListResult = cloudVendorService.getAllCloudVendors();

        assertThat(cloudVendorListResult).isNotNull();
        assertThat(cloudVendorListResult.get(0).getVendorId()).isEqualTo(1);
        assertThat(cloudVendorListResult.get(0).getVendorName()).isEqualTo("Amazon");
        assertThat(cloudVendorListResult.get(1).getVendorId()).isEqualTo(2);
        assertThat(cloudVendorListResult.get(1).getVendorName()).isEqualTo("Google");
        assertThat(cloudVendorListResult.get(2).getVendorId()).isEqualTo(3);
        assertThat(cloudVendorListResult.get(2).getVendorName()).isEqualTo("Google");
    }

    @Test
    void getCloudVendorInfo() {

        when(cloudVendorRepository.findById(1)).thenReturn(Optional.of(cloudVendor));
        when(cloudVendorRepository.findByVendorId(1)).thenReturn(cloudVendor);

        CloudVendor cloudVendorFound = cloudVendorService.getCloudVendorInfo("1");

        assertThat(cloudVendorFound).isNotNull();
        assertThat(cloudVendorFound.getVendorId()).isEqualTo(1);
        assertThat(cloudVendorFound.getVendorAddress()).isEqualTo("Address 1");
        assertThat(cloudVendorFound.getVendorPhoneNumber()).isEqualTo("2222-3333");
        assertThat(cloudVendorFound.getVendorName()).isEqualTo("Amazon");
    }

    @Test
    void updateCloudVendorInfo() {
        CloudVendor cloudVendorToUpdate =  new CloudVendor( "Address 4", "2222-4444", "Azure");

        when(cloudVendorRepository.existsById(1)).thenReturn(true);
        when(cloudVendorRepository.save(cloudVendorToUpdate)).thenReturn(cloudVendorToUpdate);

        String result = cloudVendorService.updateCloudVendorInfo(cloudVendorToUpdate, "1");

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo("Cloud Vendor updated");
    }

    @Test
    void deleteCloudVendor() {

        when(cloudVendorRepository.findById(2)).thenReturn(Optional.of(cloudVendor2));

        String result = cloudVendorService.deleteCloudVendor(2);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo("Cloud Vendor deleted");
    }

    @Test
    void getCloudVendorByName() {
        List<CloudVendor> cloudVendorList = List.of(cloudVendor2, cloudVendor3);

        when(cloudVendorRepository.findByVendorName("Google")).thenReturn(cloudVendorList);

        List<CloudVendor> cloudVendorListFound = cloudVendorService.getCloudVendorByName("Google");

        assertThat(cloudVendorListFound).isNotNull();
        assertThat(cloudVendorListFound.size()).isEqualTo(2);
        assertThat(cloudVendorListFound.get(0).getVendorName()).isEqualTo("Google");
        assertThat(cloudVendorListFound.get(1).getVendorName()).isEqualTo("Google");
    }

    @Test
    void updateSpecificCloudVendorInfo() {
        Map<String, Object> dataToUpdate = new HashMap<>();
        dataToUpdate.put("vendorName", "Azure");
        String vendorId = "2";

        when(cloudVendorRepository.findById(2)).thenReturn(Optional.of(cloudVendor2));

        String result = cloudVendorService.updateSpecificCloudVendorInfo(vendorId, dataToUpdate);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo("Cloud Vendor partial updated with sucess");
    }
}