package co.com.ias.project.HandyMan.technician.application.services;

import co.com.ias.project.HandyMan.technician.application.domain.ServiceReport;
import co.com.ias.project.HandyMan.technician.application.domain.TechnicianId;
import co.com.ias.project.HandyMan.technician.application.model.ListReportsRequest;
import co.com.ias.project.HandyMan.technician.application.model.ListReportsResponse;
import co.com.ias.project.HandyMan.technician.application.ports.in.ListReportsUseCase;
import co.com.ias.project.HandyMan.technician.application.ports.out.ServiceReportsRepository;

import java.util.Collection;

public class ListReportsService implements ListReportsUseCase {
    private final ServiceReportsRepository repository;

    public ListReportsService(ServiceReportsRepository repository) {
        this.repository = repository;
    }

    @Override
    public ListReportsResponse execute(ListReportsRequest request) {
        Collection<ServiceReport> data = repository.listServiceReports(TechnicianId.of(request.getTechnicianId().getValue()), request.getLimit(), request.getSkip());

        return new ListReportsResponse(data);
    }
}
