package co.com.ias.project.HandyMan.technician.application.domain;

import co.com.ias.project.HandyMan.commons.operation.Validate;


import java.time.LocalDateTime;
import java.util.Objects;

public class ServiceReport {
    private final TechnicianId technicianId;
    private final ServiceId serviceId;
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;


    public static ServiceReport from(TechnicianId technicianId, ServiceId serviceId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return new ServiceReport(technicianId, serviceId, startDateTime, endDateTime);
    }

    private ServiceReport(TechnicianId technicianId, ServiceId serviceId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        Validate.notNull(technicianId, "The technician id cant not be null");
        Validate.notNull(serviceId, "The service id cant not be null");
        Validate.notNull(startDateTime, "The start date and time cant not be null");
        Validate.notNull(endDateTime, "The end date and time cant not be null");
        Validate.isTrue(endDateTime.isAfter(startDateTime), "the end date must be after the start date");

        this.technicianId = technicianId;
        this.serviceId = serviceId;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public TechnicianId getTechnicianId() {
        return technicianId;
    }

    public ServiceId getServiceId() {
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
        ServiceReport that = (ServiceReport) o;
        return technicianId.equals(that.technicianId) && serviceId.equals(that.serviceId) && startDateTime.equals(that.startDateTime) && endDateTime.equals(that.endDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(technicianId, serviceId, startDateTime, endDateTime);
    }

    @Override
    public String toString() {
        return "ServiceReport{" +
                "technicianId=" + technicianId +
                ", serviceId=" + serviceId +
                ", startDateTime=" + startDateTime +
                ", endDateTime=" + endDateTime +
                '}';
    }
}
