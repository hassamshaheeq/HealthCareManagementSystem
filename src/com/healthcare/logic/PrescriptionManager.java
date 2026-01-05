package com.healthcare.logic;

import com.healthcare.model.Prescription;
import java.io.*;

public class PrescriptionManager {

    public void createPrescription(Prescription p) {
        DataManager.getInstance().addPrescription(p);
        generatePrescriptionFile(p);
    }

    private void generatePrescriptionFile(Prescription p) {
        String fileName = "prescription_" + p.getPrescriptionID() + ".txt";
        try (PrintWriter out = new PrintWriter(new FileWriter(fileName))) {
            out.println("PRESCRIPTION DETAILS");
            out.println("====================");
            out.println("Prescription ID: " + p.getPrescriptionID());
            out.println("Patient ID: " + p.getPatientID());
            out.println("Clinician ID: " + p.getClinicianID());
            out.println("Medication: " + p.getMedication());
            out.println("Dosage: " + p.getDosage());
            out.println("Frequency: " + p.getFrequency());
            out.println("Duration: " + p.getDuration());
            out.println("Pharmacy Status: " + p.getPharmacyStatus());
            out.println("Date Issued: " + p.getDateIssued());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
