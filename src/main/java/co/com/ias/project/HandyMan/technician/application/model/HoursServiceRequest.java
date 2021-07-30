package co.com.ias.project.HandyMan.technician.application.model;

import co.com.ias.project.HandyMan.commons.operation.ApplicationRequest;
import co.com.ias.project.HandyMan.technician.application.domain.TechnicianId;

public class HoursServiceRequest implements ApplicationRequest {
    private final TechnicianId technicianId;
    private final int NumOfWeek;

    public HoursServiceRequest(TechnicianId technicianId, int numOfWeek) {
        this.technicianId = technicianId;
        NumOfWeek = numOfWeek;
    }

    public TechnicianId getTechnicianId() {
        return technicianId;
    }

    public int getNumOfWeek() {
        return NumOfWeek;
    }
}
