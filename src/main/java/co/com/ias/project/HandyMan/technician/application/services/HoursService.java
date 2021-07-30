package co.com.ias.project.HandyMan.technician.application.services;


import co.com.ias.project.HandyMan.technician.application.domain.TechnicianId;
import co.com.ias.project.HandyMan.technician.application.model.HoursServiceRequest;
import co.com.ias.project.HandyMan.technician.application.model.HoursServiceResponse;

import co.com.ias.project.HandyMan.technician.application.ports.in.HoursServiceUseCase;
import co.com.ias.project.HandyMan.technician.application.ports.out.ServiceReportsRepository;

import java.util.Collection;

public class HoursService implements HoursServiceUseCase {
    private final ServiceReportsRepository repository;

    public HoursService(ServiceReportsRepository repository) {
        this.repository = repository;
    }

    @Override
    public HoursServiceResponse execute(HoursServiceRequest request) {

        Object[] data = repository.calculateHours(TechnicianId.of(request.getTechnicianId().getValue()), request.getNumOfWeek());

        return new HoursServiceResponse(data);
    }
}
