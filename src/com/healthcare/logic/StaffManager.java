package com.healthcare.logic;

import com.healthcare.model.Staff;

public class StaffManager {

    public void registerStaff(Staff s) {
        DataManager.getInstance().addStaff(s);
    }

    public void removeStaff(String id) {
        DataManager.getInstance().deleteStaff(id);
    }
}
