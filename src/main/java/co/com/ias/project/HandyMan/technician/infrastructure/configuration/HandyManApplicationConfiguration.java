package co.com.ias.project.HandyMan.technician.infrastructure.configuration;


import co.com.ias.project.HandyMan.technician.application.ports.in.CreateServiceReportUseCase;
import co.com.ias.project.HandyMan.technician.application.ports.in.HoursServiceUseCase;
import co.com.ias.project.HandyMan.technician.application.ports.in.ListReportsUseCase;
import co.com.ias.project.HandyMan.technician.application.ports.out.ServiceReportsRepository;
import co.com.ias.project.HandyMan.technician.application.services.CreateServiceReportService;
import co.com.ias.project.HandyMan.technician.application.services.HoursService;
import co.com.ias.project.HandyMan.technician.application.services.ListReportsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HandyManApplicationConfiguration {

    @Bean
    public CreateServiceReportUseCase createServiceReportServiceBean(
            ServiceReportsRepository repository
    ) {
        return new CreateServiceReportService(repository);
    }

    @Bean
    public ListReportsUseCase listReportsServiceBean(
            ServiceReportsRepository repository
    ) {
        return new ListReportsService(repository);
    }

    @Bean
    public HoursServiceUseCase hoursServiceBean(
            ServiceReportsRepository repository
    ){
        return new HoursService(repository);
    }


}
