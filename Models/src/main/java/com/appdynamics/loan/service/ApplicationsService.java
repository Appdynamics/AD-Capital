package com.appdynamics.loan.service;

import com.appdynamics.loan.model.Applications;
import com.appdynamics.loan.persistence.ApplicationsPersistence;
import com.appdynamics.loan.persistence.CustomerPersistence;

import java.util.UUID;

/**
 * Created by swetha.ravichandran on 8/17/15.
 */

public class ApplicationsService {

    /**
     * Ref to ApplicationsPersistence class
     */
    private ApplicationsPersistence applicationsPersistence;

    public void setApplicationsPersistence(ApplicationsPersistence applicationsPersistence) {
        this.applicationsPersistence = applicationsPersistence;
    }

    public ApplicationsPersistence getApplicationsPersistence() {
        return applicationsPersistence;
    }

    public void saveNewApplications(Applications applications) {
        applicationsPersistence.save(applications);
    }

    public Applications getApplicationsWithCCStatus() {
        return applicationsPersistence.getApplicationsWithCCStatus();
    }

    public Applications getApplicationsWithUWStatus() {
        return applicationsPersistence.getApplicationsWithUWStatus();
    }

    public Integer updateApplicationsById(String id){
        return applicationsPersistence.updateApplicationsById(id);
    }

    public Integer deleteApplicationsByID(String id){
        return applicationsPersistence.deleteApplicationsByID(id);
    }
}
