package com.healthcare.logic;

import com.healthcare.model.Referral;
import java.io.*;
import java.util.*;

public class ReferralManager {
    private static ReferralManager instance;
    private Queue<Referral> referralQueue;
    private List<String> auditLog;

    private ReferralManager() {
        referralQueue = new LinkedList<>();
        auditLog = new ArrayList<>();
        // Load existing referrals into queue if they are pending
        for (Referral r : DataManager.getInstance().getReferrals()) {
            if ("Pending".equalsIgnoreCase(r.getStatus())) {
                referralQueue.add(r);
            }
        }
    }

    public static synchronized ReferralManager getInstance() {
        if (instance == null) {
            instance = new ReferralManager();
        }
        return instance;
    }

    public void processReferral(Referral referral) {
        referralQueue.add(referral);
        DataManager.getInstance().addReferral(referral);
        logAction("Created referral " + referral.getReferralID() + " for Patient " + referral.getPatientID());
        generateReferralFile(referral);
    }

    private void logAction(String action) {
        String entry = new java.util.Date() + ": " + action;
        auditLog.add(entry);
        System.out.println(entry); // Console log as well
    }

    private void generateReferralFile(Referral r) {
        String fileName = "referral_" + r.getReferralID() + ".txt";
        try (PrintWriter out = new PrintWriter(new FileWriter(fileName))) {
            out.println("REFERRAL DETAILS");
            out.println("================");
            out.println("Referral ID: " + r.getReferralID());
            out.println("Patient ID: " + r.getPatientID());
            out.println("Source Clinician: " + r.getSourceClinicianID());
            out.println("Target Facility: " + r.getTargetFacilityID());
            out.println("Reason: " + r.getReason());
            out.println("Urgency: " + r.getUrgencyLevel());
            out.println("Summary: " + r.getClinicalSummary());
            out.println("Status: " + r.getStatus());
            out.println("Date: " + r.getDateReferred());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Queue<Referral> getReferralQueue() {
        return referralQueue;
    }

    public List<String> getAuditLog() {
        return auditLog;
    }
}
