package co.com.ias.project.HandyMan.technician.application.model;

import co.com.ias.project.HandyMan.commons.operation.ApplicationResponse;
import co.com.ias.project.HandyMan.technician.application.domain.ServiceReport;

public class CreateServiceReportResponse implements ApplicationResponse {
    private final ServiceReport serviceReport;

    public CreateServiceReportResponse(ServiceReport serviceReport) {
        this.serviceReport = serviceReport;
    }

    public ServiceReport getServiceReport() {
        return serviceReport;
    }

    @Override
    public String toString() {
        return "CreateServiceReportResponse{" +
                "serviceReport=" + serviceReport +
                '}';
    }
}
