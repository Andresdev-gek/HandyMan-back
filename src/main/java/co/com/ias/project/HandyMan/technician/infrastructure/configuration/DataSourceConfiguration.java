package co.com.ias.project.HandyMan.technician.infrastructure.configuration;

import co.com.ias.project.HandyMan.technician.application.ports.in.CreateServiceReportUseCase;
import co.com.ias.project.HandyMan.technician.application.ports.out.ServiceReportsRepository;
import co.com.ias.project.HandyMan.technician.application.services.CreateServiceReportService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("database/schema.sql")
                .build();
    }

}

