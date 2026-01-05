package com.healthcare.logic;

import com.healthcare.data.CSVHandler;
import com.healthcare.model.*;
import java.util.*;

public class DataManager {
    private static DataManager instance;
    private List<Patient> patients;
    private List<Clinician> clinicians;
    private List<Facility> facilities;
    private List<Appointment> appointments;
    private List<Prescription> prescriptions;
    private List<Referral> referrals;
    private List<Staff> staff;

    private DataManager() {
        loadData();
    }

    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    private void loadData() {
        patients = new ArrayList<>();
        for (String[] row : CSVHandler.readCSV("data/patients.csv")) {
            if (row.length >= 9)
                patients.add(new Patient(row[0], row[1], row[2], row[3], row[4], row[5], row[6], row[7], row[8]));
        }

        clinicians = new ArrayList<>();
        for (String[] row : CSVHandler.readCSV("data/clinicians.csv")) {
            if (row.length >= 7)
                clinicians.add(new Clinician(row[0], row[1], row[2], row[3], row[4], row[5], row[6]));
        }

        facilities = new ArrayList<>();
        for (String[] row : CSVHandler.readCSV("data/facilities.csv")) {
            if (row.length >= 5)
                facilities.add(new Facility(row[0], row[1], row[2], row[3], row[4]));
        }

        appointments = new ArrayList<>();
        for (String[] row : CSVHandler.readCSV("data/appointments.csv")) {
            if (row.length >= 7)
                appointments.add(new Appointment(row[0], row[1], row[2], row[3], row[4], row[5], row[6]));
        }

        prescriptions = new ArrayList<>();
        for (String[] row : CSVHandler.readCSV("data/prescriptions.csv")) {
            if (row.length >= 9)
                prescriptions
                        .add(new Prescription(row[0], row[1], row[2], row[3], row[4], row[5], row[6], row[7], row[8]));
        }

        referrals = new ArrayList<>();
        for (String[] row : CSVHandler.readCSV("data/referrals.csv")) {
            if (row.length >= 9)
                referrals.add(new Referral(row[0], row[1], row[2], row[3], row[4], row[5], row[6], row[7], row[8]));
        }

        staff = new ArrayList<>();
        for (String[] row : CSVHandler.readCSV("data/staff.csv")) {
            if (row.length >= 6)
                staff.add(new Staff(row[0], row[1], row[2], row[3], row[4], row[5]));
        }
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public List<Clinician> getClinicians() {
        return clinicians;
    }

    public List<Facility> getFacilities() {
        return facilities;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public List<Referral> getReferrals() {
        return referrals;
    }

    public List<Staff> getStaff() {
        return staff;
    }

    public void addPatient(Patient p) {
        patients.add(p);
        CSVHandler.appendCSV("data/patients.csv", new String[] { p.getPatientID(), p.getNhsNumber(), p.getFirstName(),
                p.getLastName(), p.getDateOfBirth(), p.getGender(), p.getAddress(), p.getPhoneNumber(), p.getEmail() });
    }

    public void addAppointment(Appointment a) {
        appointments.add(a);
        CSVHandler.appendCSV("data/appointments.csv", new String[] { a.getAppointmentID(), a.getPatientID(),
                a.getClinicianID(), a.getFacilityID(), a.getDateTime(), a.getReason(), a.getStatus() });
    }

    public void addPrescription(Prescription p) {
        prescriptions.add(p);
        CSVHandler.appendCSV("data/prescriptions.csv",
                new String[] { p.getPrescriptionID(), p.getPatientID(), p.getClinicianID(), p.getMedication(),
                        p.getDosage(), p.getFrequency(), p.getDuration(), p.getPharmacyStatus(), p.getDateIssued() });
    }

    public void addReferral(Referral r) {
        referrals.add(r);
        CSVHandler.appendCSV("data/referrals.csv",
                new String[] { r.getReferralID(), r.getPatientID(), r.getSourceClinicianID(), r.getTargetFacilityID(),
                        r.getReason(), r.getUrgencyLevel(), r.getClinicalSummary(), r.getStatus(),
                        r.getDateReferred() });
    }

    public void saveAll() {
        CSVHandler.writeCSV("data/patients.csv",
                new String[] { "PatientID", "NHSNumber", "FirstName", "LastName", "DateOfBirth", "Gender", "Address",
                        "PhoneNumber", "Email" },
                patients.stream()
                        .map(p -> new String[] { p.getPatientID(), p.getNhsNumber(), p.getFirstName(), p.getLastName(),
                                p.getDateOfBirth(), p.getGender(), p.getAddress(), p.getPhoneNumber(), p.getEmail() })
                        .toList());

        CSVHandler.writeCSV("data/appointments.csv",
                new String[] { "AppointmentID", "PatientID", "ClinicianID", "FacilityID", "DateTime", "Reason",
                        "Status" },
                appointments.stream().map(a -> new String[] { a.getAppointmentID(), a.getPatientID(),
                        a.getClinicianID(), a.getFacilityID(), a.getDateTime(), a.getReason(), a.getStatus() })
                        .toList());

        CSVHandler.writeCSV("data/prescriptions.csv",
                new String[] { "PrescriptionID", "PatientID", "ClinicianID", "Medication", "Dosage", "Frequency",
                        "Duration", "PharmacyStatus", "DateIssued" },
                prescriptions.stream()
                        .map(p -> new String[] { p.getPrescriptionID(), p.getPatientID(), p.getClinicianID(),
                                p.getMedication(), p.getDosage(), p.getFrequency(), p.getDuration(),
                                p.getPharmacyStatus(), p.getDateIssued() })
                        .toList());

        CSVHandler.writeCSV("data/referrals.csv",
                new String[] { "ReferralID", "PatientID", "SourceClinicianID", "TargetFacilityID", "Reason",
                        "UrgencyLevel", "ClinicalSummary", "Status", "DateReferred" },
                referrals.stream()
                        .map(r -> new String[] { r.getReferralID(), r.getPatientID(), r.getSourceClinicianID(),
                                r.getTargetFacilityID(), r.getReason(), r.getUrgencyLevel(), r.getClinicalSummary(),
                                r.getStatus(), r.getDateReferred() })
                        .toList());

        CSVHandler.writeCSV("data/facilities.csv",
                new String[] { "FacilityID", "Name", "Type", "Address", "ContactNumber" },
                facilities.stream().map(f -> new String[] { f.getFacilityID(), f.getName(), f.getType(), f.getAddress(),
                        f.getContactNumber() }).toList());

        CSVHandler.writeCSV("data/clinicians.csv",
                new String[] { "ClinicianID", "RegistryID", "FirstName", "LastName", "Role", "Specialty",
                        "ContactInfo" },
                clinicians.stream().map(c -> new String[] { c.getClinicianID(), c.getRegistryID(), c.getFirstName(),
                        c.getLastName(), c.getRole(), c.getSpecialty(), c.getContactInfo() }).toList());

        CSVHandler.writeCSV("data/staff.csv",
                new String[] { "StaffID", "FirstName", "LastName", "Role", "Department", "ContactInfo" },
                staff.stream().map(s -> new String[] { s.getStaffID(), s.getFirstName(), s.getLastName(), s.getRole(),
                        s.getDepartment(), s.getContactInfo() }).toList());
    }

    public void deletePatient(String id) {
        patients.removeIf(p -> p.getPatientID().equals(id));
        saveAll();
    }

    public void deleteAppointment(String id) {
        appointments.removeIf(a -> a.getAppointmentID().equals(id));
        saveAll();
    }

    public void deletePrescription(String id) {
        prescriptions.removeIf(p -> p.getPrescriptionID().equals(id));
        saveAll();
    }

    public void deleteReferral(String id) {
        referrals.removeIf(r -> r.getReferralID().equals(id));
        saveAll();
    }

    public void deleteFacility(String id) {
        facilities.removeIf(f -> f.getFacilityID().equals(id));
        saveAll();
    }

    public void deleteStaff(String id) {
        staff.removeIf(s -> s.getStaffID().equals(id));
        saveAll();
    }

    public void deleteClinician(String id) {
        clinicians.removeIf(c -> c.getClinicianID().equals(id));
        saveAll();
    }

    public void addFacility(Facility f) {
        facilities.add(f);
        CSVHandler.appendCSV("data/facilities.csv",
                new String[] { f.getFacilityID(), f.getName(), f.getType(), f.getAddress(), f.getContactNumber() });
    }

    public void addStaff(Staff s) {
        staff.add(s);
        CSVHandler.appendCSV("data/staff.csv", new String[] { s.getStaffID(), s.getFirstName(), s.getLastName(),
                s.getRole(), s.getDepartment(), s.getContactInfo() });
    }


}
