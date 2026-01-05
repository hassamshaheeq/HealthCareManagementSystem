package com.healthcare.model;

public class Clinician {
    private String clinicianID;
    private String registryID;
    private String firstName;
    private String lastName;
    private String role;
    private String specialty;
    private String contactInfo;

    public Clinician(String clinicianID, String registryID, String firstName, String lastName, 
                     String role, String specialty, String contactInfo) {
        this.clinicianID = clinicianID;
        this.registryID = registryID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.specialty = specialty;
        this.contactInfo = contactInfo;
    }

    // Getters and Setters
    public String getClinicianID() { return clinicianID; }
    public void setClinicianID(String clinicianID) { this.clinicianID = clinicianID; }
    public String getRegistryID() { return registryID; }
    public void setRegistryID(String registryID) { this.registryID = registryID; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getSpecialty() { return specialty; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }
    public String getContactInfo() { return contactInfo; }
    public void setContactInfo(String contactInfo) { this.contactInfo = contactInfo; }

    @Override
    public String toString() {
        return role + ": " + firstName + " " + lastName;
    }
}
