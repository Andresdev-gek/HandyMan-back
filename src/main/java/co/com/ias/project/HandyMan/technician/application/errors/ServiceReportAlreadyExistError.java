package co.com.ias.project.HandyMan.technician.application.errors;

import co.com.ias.project.HandyMan.commons.errors.ApplicationError;
import co.com.ias.project.HandyMan.commons.errors.HttpStatusCode;
import co.com.ias.project.HandyMan.technician.application.domain.ServiceReport;

import java.util.Map;

public class ServiceReportAlreadyExistError extends ApplicationError {
    private final ServiceReport serviceReport;

    public ServiceReportAlreadyExistError(ServiceReport serviceReport) {
        this.serviceReport = serviceReport;
    }

    public ServiceReport getServiceReport() {
        return serviceReport;
    }

    @Override
    public String getMessage() {
        return "The service report with the service id: " + serviceReport.getServiceId().getValue() +
                " It is registered with the start date: " + serviceReport.getStartDateTime().toString() +
                " and the end date: " + serviceReport.getEndDateTime().toString() +
                " with the technician id: " + serviceReport.getTechnicianId().getValue() + " already exists.";
    }

    @Override
    public String errorCode() {
        return "SERVICE_REPORT_EXISTS_ERROR";
    }

    @Override
    public HttpStatusCode httpStatusCode() {
        return HttpStatusCode.BAD_REQUEST;
    }

    @Override
    public Map<String, Object> metadata() {
        return Map.of("startDateTime", serviceReport.getStartDateTime(),
                "endDateTime", serviceReport.getEndDateTime(),
                "technicianId", serviceReport.getTechnicianId()
        );
    }

}
