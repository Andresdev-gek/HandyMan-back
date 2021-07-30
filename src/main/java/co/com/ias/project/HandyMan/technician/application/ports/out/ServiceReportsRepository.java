package co.com.ias.project.HandyMan.technician.application.ports.out;

import co.com.ias.project.HandyMan.technician.application.domain.ServiceId;
import co.com.ias.project.HandyMan.technician.application.domain.ServiceReport;
import co.com.ias.project.HandyMan.technician.application.domain.TechnicianId;

import java.util.Collection;
import java.util.Optional;

public interface ServiceReportsRepository {
    Optional<ServiceReport> getServiceReportByServiceId(ServiceId serviceId);

    void storeServiceReport(ServiceReport serviceReport);

    Collection<ServiceReport> listServiceReports(TechnicianId technicianId, int limit, int skip);

    Object[] calculateHours(TechnicianId technicianId, int numberOfWeek);


}
