package co.com.ias.project.HandyMan.technician.application.model;

import co.com.ias.project.HandyMan.commons.operation.ApplicationRequest;

import java.time.LocalDateTime;
import java.util.Objects;

public class CreateServiceReportRequest implements ApplicationRequest {
    private String technicianId;
    private String serviceId;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public CreateServiceReportRequest() {

    }

    public CreateServiceReportRequest(String technicianId, String serviceId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.technicianId = technicianId;
        this.serviceId = serviceId;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public String getTechnicianId() {
        return technicianId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateServiceReportRequest that = (CreateServiceReportRequest) o;
        return technicianId.equals(that.technicianId) && serviceId.equals(that.serviceId) && startDateTime.equals(that.startDateTime) && endDateTime.equals(that.endDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(technicianId, serviceId, startDateTime, endDateTime);
    }

    @Override
    public String toString() {
        return "CreateServiceReportRequest{" +
                "technicianId='" + technicianId + '\'' +
                ", serviceId='" + serviceId + '\'' +
                ", startDateTime=" + startDateTime +
                ", endDateTime=" + endDateTime +
                '}';
    }
}
