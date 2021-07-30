package co.com.ias.project.HandyMan.technician.application.model;

import co.com.ias.project.HandyMan.commons.operation.ApplicationResponse;
import co.com.ias.project.HandyMan.technician.application.domain.ServiceReport;

import java.util.Collection;

public class ListReportsResponse implements ApplicationResponse {
    private final Collection<ServiceReport> items;

    public ListReportsResponse(Collection<ServiceReport> items) {
        this.items = items;
    }

    public Collection<ServiceReport> getItems() {
        return items;
    }
}
