package co.com.ias.project.HandyMan.technician.infrastructure.adapters.in;


import co.com.ias.project.HandyMan.technician.application.domain.TechnicianId;
import co.com.ias.project.HandyMan.technician.application.model.CreateServiceReportRequest;
import co.com.ias.project.HandyMan.technician.application.model.HoursServiceRequest;
import co.com.ias.project.HandyMan.technician.application.model.ListReportsRequest;
import co.com.ias.project.HandyMan.technician.application.ports.in.CreateServiceReportUseCase;
import co.com.ias.project.HandyMan.technician.application.ports.in.HoursServiceUseCase;
import co.com.ias.project.HandyMan.technician.application.ports.in.ListReportsUseCase;
import co.com.ias.project.HandyMan.technician.infrastructure.commons.UseCaseHttpExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(path = "/reports")
public class ServiceReportsController {
    private final UseCaseHttpExecutor useCaseHttpExecutor;
    private final CreateServiceReportUseCase createServiceReportUseCase;
    private final ListReportsUseCase listReportsUseCase;
    private final HoursServiceUseCase hoursServiceUseCase;

    @Autowired
    public ServiceReportsController(UseCaseHttpExecutor useCaseHttpExecutor, CreateServiceReportUseCase createServiceReportUseCase, ListReportsUseCase listReportsUseCase, HoursServiceUseCase hoursServiceUseCase) {
        this.useCaseHttpExecutor = useCaseHttpExecutor;
        this.createServiceReportUseCase = createServiceReportUseCase;
        this.listReportsUseCase = listReportsUseCase;
        this.hoursServiceUseCase = hoursServiceUseCase;
    }

    @GetMapping(value = "/{technicianId}")
    public ResponseEntity listServiceReportsHandler(
            @PathVariable("technicianId") String technicianId,

            @RequestParam(name = "limit", defaultValue = "60") Integer limit,
            @RequestParam(name = "skip", defaultValue = "0") Integer skip
    ) {
        TechnicianId valor = TechnicianId.of(technicianId);

        return useCaseHttpExecutor.executeUseCase(
                listReportsUseCase,
                new ListReportsRequest(valor, limit, skip)
        );
    }

    @GetMapping(value = "/calculateHours")
    public ResponseEntity hoursServiceHandler(
            @RequestParam  String technicianId,
            @RequestParam int numberOfWeek
    ) {
        TechnicianId valor = TechnicianId.of(technicianId);

        return useCaseHttpExecutor.executeUseCase(
                hoursServiceUseCase,
                new HoursServiceRequest(valor, numberOfWeek)
        );

    }

    @PostMapping
    public ResponseEntity createServiceReportHandler(
            @RequestBody CreateServiceReportRequest request
    ) {
        return useCaseHttpExecutor.executeUseCase(
                createServiceReportUseCase,
                request
        );
    }


}
