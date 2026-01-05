package com.healthcare.model;

public class Appointment {
    private String appointmentID;
    private String patientID;
    private String clinicianID;
    private String facilityID;
    private String dateTime;
    private String reason;
    private String status;

    public Appointment(String appointmentID, String patientID, String clinicianID, 
                       String facilityID, String dateTime, String reason, String status) {
        this.appointmentID = appointmentID;
        this.patientID = patientID;
        this.clinicianID = clinicianID;
        this.facilityID = facilityID;
        this.dateTime = dateTime;
        this.reason = reason;
        this.status = status;
    }

    // Getters and Setters
    public String getAppointmentID() { return appointmentID; }
    public void setAppointmentID(String appointmentID) { this.appointmentID = appointmentID; }
    public String getPatientID() { return patientID; }
    public void setPatientID(String patientID) { this.patientID = patientID; }
    public String getClinicianID() { return clinicianID; }
    public void setClinicianID(String clinicianID) { this.clinicianID = clinicianID; }
    public String getFacilityID() { return facilityID; }
    public void setFacilityID(String facilityID) { this.facilityID = facilityID; }
    public String getDateTime() { return dateTime; }
    public void setDateTime(String dateTime) { this.dateTime = dateTime; }
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
