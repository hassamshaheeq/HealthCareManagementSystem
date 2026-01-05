package com.healthcare.logic;

import com.healthcare.model.Clinician;

public class ClinicianManager {

    public void registerClinician(Clinician c) {
        DataManager.getInstance().addClinician(c);
    }

    public void removeClinician(String id) {
        DataManager.getInstance().deleteClinician(id);
    }
}
