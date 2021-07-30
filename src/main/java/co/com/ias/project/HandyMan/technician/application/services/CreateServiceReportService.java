package co.com.ias.project.HandyMan.technician.application.services;

import co.com.ias.project.HandyMan.technician.application.domain.*;
import co.com.ias.project.HandyMan.technician.application.errors.InputDataError;
import co.com.ias.project.HandyMan.technician.application.errors.ServiceReportAlreadyExistError;
import co.com.ias.project.HandyMan.technician.application.model.CreateServiceReportRequest;
import co.com.ias.project.HandyMan.technician.application.model.CreateServiceReportResponse;
import co.com.ias.project.HandyMan.technician.application.ports.in.CreateServiceReportUseCase;
import co.com.ias.project.HandyMan.technician.application.ports.out.ServiceReportsRepository;

import java.time.LocalDateTime;

import java.util.Optional;



public class CreateServiceReportService implements CreateServiceReportUseCase {
    private final ServiceReportsRepository repository;

    public CreateServiceReportService(ServiceReportsRepository repository) {
        this.repository = repository;
    }

    private ServiceReport validateInput(CreateServiceReportRequest request) {
        try {
            TechnicianId technicianId = TechnicianId.of(request.getTechnicianId());
            ServiceId serviceId = ServiceId.of(request.getServiceId());
            LocalDateTime startDateTime = LocalDateTime.from(request.getStartDateTime());
            LocalDateTime endDateTime = LocalDateTime.from(request.getEndDateTime());

            return ServiceReport.from(
                    technicianId,
                    serviceId,
                    startDateTime,
                    endDateTime
            );
        } catch (RuntimeException e) {
            throw new InputDataError(e.getMessage());
        }
    }

    @Override
    public CreateServiceReportResponse execute(CreateServiceReportRequest request) {
        ServiceReport serviceReport = validateInput(request);

        ServiceId serviceId = serviceReport.getServiceId();
        Optional<ServiceReport> serviceReportByServiceId = repository.getServiceReportByServiceId(serviceId);

        if (serviceReportByServiceId.isPresent()) {
            throw new ServiceReportAlreadyExistError(serviceReport);
        }

        repository.storeServiceReport(serviceReport);

        return new CreateServiceReportResponse(serviceReport);
    }
}
