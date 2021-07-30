package co.com.ias.project.HandyMan.technician.application.model;

import co.com.ias.project.HandyMan.commons.operation.ApplicationRequest;
import co.com.ias.project.HandyMan.technician.application.domain.TechnicianId;

public class ListReportsRequest implements ApplicationRequest {
    private final TechnicianId technicianId;
    private final Integer limit;
    private final Integer skip;

    public ListReportsRequest(TechnicianId technicianId, Integer limit, Integer skip) {
        this.technicianId = technicianId;
        this.limit = limit;
        this.skip = skip;
    }

    public TechnicianId getTechnicianId() {
        return technicianId;
    }

    public Integer getLimit() {
        return limit;
    }

    public Integer getSkip() {
        return skip;
    }
}
