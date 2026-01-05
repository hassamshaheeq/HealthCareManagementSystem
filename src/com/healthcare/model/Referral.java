package com.healthcare.model;

public class Referral {
    private String referralID;
    private String patientID;
    private String sourceClinicianID;
    private String targetFacilityID;
    private String reason;
    private String urgencyLevel;
    private String clinicalSummary;
    private String status;
    private String dateReferred;

    public Referral(String referralID, String patientID, String sourceClinicianID, 
                    String targetFacilityID, String reason, String urgencyLevel, 
                    String clinicalSummary, String status, String dateReferred) {
        this.referralID = referralID;
        this.patientID = patientID;
        this.sourceClinicianID = sourceClinicianID;
        this.targetFacilityID = targetFacilityID;
        this.reason = reason;
        this.urgencyLevel = urgencyLevel;
        this.clinicalSummary = clinicalSummary;
        this.status = status;
        this.dateReferred = dateReferred;
    }

    // Getters and Setters
    public String getReferralID() { return referralID; }
    public void setReferralID(String referralID) { this.referralID = referralID; }
    public String getPatientID() { return patientID; }
    public void setPatientID(String patientID) { this.patientID = patientID; }
    public String getSourceClinicianID() { return sourceClinicianID; }
    public void setSourceClinicianID(String sourceClinicianID) { this.sourceClinicianID = sourceClinicianID; }
    public String getTargetFacilityID() { return targetFacilityID; }
    public void setTargetFacilityID(String targetFacilityID) { this.targetFacilityID = targetFacilityID; }
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
    public String getUrgencyLevel() { return urgencyLevel; }
    public void setUrgencyLevel(String urgencyLevel) { this.urgencyLevel = urgencyLevel; }
    public String getClinicalSummary() { return clinicalSummary; }
    public void setClinicalSummary(String clinicalSummary) { this.clinicalSummary = clinicalSummary; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getDateReferred() { return dateReferred; }
    public void setDateReferred(String dateReferred) { this.dateReferred = dateReferred; }
}
