package com.cloudvendor.demo_API.model;


import jakarta.persistence.*;


@Entity
@Table(name = "cloud_vendor_info")
public class CloudVendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer vendorId;
    private String vendorName;
    private String vendorPhoneNumber;
    private String vendorAddress;

    public CloudVendor (){}

    public CloudVendor(String vendorAddress, String vendorPhoneNumber, String vendorName) {
        this.vendorAddress = vendorAddress;
        this.vendorPhoneNumber = vendorPhoneNumber;
        this.vendorName = vendorName;
    }

    public CloudVendor(String vendorId, String vendorAddress, String vendorPhoneNumber, String vendorName) {
        this.vendorId = Integer.valueOf(vendorId);
        this.vendorAddress = vendorAddress;
        this.vendorPhoneNumber = vendorPhoneNumber;
        this.vendorName = vendorName;
    }

    public String getVendorAddress() {
        return vendorAddress;
    }

    public void setVendorAddress(String vendorAddress) {
        this.vendorAddress = vendorAddress;
    }

    public String getVendorPhoneNumber() {
        return vendorPhoneNumber;
    }

    public void setVendorPhoneNumber(String vendorPhoneNumber) {
        this.vendorPhoneNumber = vendorPhoneNumber;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public Integer getVendorId() {
        return vendorId;
    }
}
