package com.healthcare.model;

public class Staff {
    private String staffID;
    private String firstName;
    private String lastName;
    private String role;
    private String department;
    private String contactInfo;

    public Staff(String staffID, String firstName, String lastName, String role, 
                 String department, String contactInfo) {
        this.staffID = staffID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.department = department;
        this.contactInfo = contactInfo;
    }

    // Getters and Setters
    public String getStaffID() { return staffID; }
    public void setStaffID(String staffID) { this.staffID = staffID; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public String getContactInfo() { return contactInfo; }
    public void setContactInfo(String contactInfo) { this.contactInfo = contactInfo; }

    @Override
    public String toString() {
        return role + ": " + firstName + " " + lastName;
    }
}
