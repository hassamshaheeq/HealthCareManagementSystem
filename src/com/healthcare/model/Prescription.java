package com.healthcare.model;

public class Prescription {
    private String prescriptionID;
    private String patientID;
    private String clinicianID;
    private String medication;
    private String dosage;
    private String frequency;
    private String duration;
    private String pharmacyStatus;
    private String dateIssued;

    public Prescription(String prescriptionID, String patientID, String clinicianID, 
                        String medication, String dosage, String frequency, String duration, 
                        String pharmacyStatus, String dateIssued) {
        this.prescriptionID = prescriptionID;
        this.patientID = patientID;
        this.clinicianID = clinicianID;
        this.medication = medication;
        this.dosage = dosage;
        this.frequency = frequency;
        this.duration = duration;
        this.pharmacyStatus = pharmacyStatus;
        this.dateIssued = dateIssued;
    }

    // Getters and Setters
    public String getPrescriptionID() { return prescriptionID; }
    public void setPrescriptionID(String prescriptionID) { this.prescriptionID = prescriptionID; }
    public String getPatientID() { return patientID; }
    public void setPatientID(String patientID) { this.patientID = patientID; }
    public String getClinicianID() { return clinicianID; }
    public void setClinicianID(String clinicianID) { this.clinicianID = clinicianID; }
    public String getMedication() { return medication; }
    public void setMedication(String medication) { this.medication = medication; }
    public String getDosage() { return dosage; }
    public void setDosage(String dosage) { this.dosage = dosage; }
    public String getFrequency() { return frequency; }
    public void setFrequency(String frequency) { this.frequency = frequency; }
    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }
    public String getPharmacyStatus() { return pharmacyStatus; }
    public void setPharmacyStatus(String pharmacyStatus) { this.pharmacyStatus = pharmacyStatus; }
    public String getDateIssued() { return dateIssued; }
    public void setDateIssued(String dateIssued) { this.dateIssued = dateIssued; }
}
