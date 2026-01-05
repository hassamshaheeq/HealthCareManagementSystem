package com.healthcare.model;

public class Facility {
    private String facilityID;
    private String name;
    private String type;
    private String address;
    private String contactNumber;

    public Facility(String facilityID, String name, String type, String address, String contactNumber) {
        this.facilityID = facilityID;
        this.name = name;
        this.type = type;
        this.address = address;
        this.contactNumber = contactNumber;
    }

    // Getters and Setters
    public String getFacilityID() { return facilityID; }
    public void setFacilityID(String facilityID) { this.facilityID = facilityID; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }

    @Override
    public String toString() {
        return name + " (" + type + ")";
    }
}
