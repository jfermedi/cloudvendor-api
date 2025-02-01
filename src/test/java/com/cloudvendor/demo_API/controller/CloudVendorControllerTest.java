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

import java.util.List;


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
    AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
       autoCloseable = MockitoAnnotations.openMocks(this);
       cloudVendor = new CloudVendor("1", "Address 1", "2222-3333", "Amazon");
       cloudVendor2 = new CloudVendor("2","Address 2", "2222-4444", "Google");
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void getAllCloudVendors() {

        List<CloudVendor> cloudVendorList = java.util.List.of(cloudVendor, cloudVendor2);

        when(cloudVendorService.getAllCloudVendors()).thenReturn(cloudVendorList);

        List<CloudVendor> result = cloudVendorController.getAllCloudVendors();

        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getVendorId()).isEqualTo(1);
        assertThat(result.get(1).getVendorId()).isEqualTo(2);
    }

    @Test
    void getCloudVendorInfoById() {
    }

    @Test
    void getCloudVendorByName() {
    }

    @Test
    void createCloudVendorInfo() {
    }

    @Test
    void deleteCloudVendor() {
    }

    @Test
    void updateVendorInfo() {
    }

    @Test
    void updateVendorSpecificInfo() {
    }
}