package com.healthcare.logic;

import com.healthcare.model.Appointment;

public class AppointmentManager {

    public void scheduleAppointment(Appointment a) {
        // Here we could add logic to check for clinician availability or patient
        // collisions
        DataManager.getInstance().addAppointment(a);
    }

    public void cancelAppointment(String appointmentID) {
        for (Appointment a : DataManager.getInstance().getAppointments()) {
            if (a.getAppointmentID().equals(appointmentID)) {
                a.setStatus("Cancelled");
                DataManager.getInstance().saveAll();
                return;
            }
        }
    }
}
