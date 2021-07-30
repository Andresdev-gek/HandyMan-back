package co.com.ias.project.HandyMan.technician.infrastructure.commons;

import co.com.ias.project.HandyMan.commons.errors.ApplicationError;
import co.com.ias.project.HandyMan.commons.operation.ApplicationRequest;
import co.com.ias.project.HandyMan.commons.operation.ApplicationResponse;
import co.com.ias.project.HandyMan.commons.operation.ApplicationUseCase;
import co.com.ias.project.HandyMan.technician.application.domain.TechnicianId;
import co.com.ias.project.HandyMan.technician.application.ports.in.ListReportsUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UseCaseHttpExecutor {
    public <IN extends ApplicationRequest, OUT extends ApplicationResponse> ResponseEntity executeUseCase(
            ApplicationUseCase<IN, OUT> useCase,
            IN applicationRequest
    ) {
        try {
            OUT response = useCase.execute(applicationRequest);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(response);
        } catch (ApplicationError applicationError) {
            int httpStatusCode = applicationError.httpStatusCode().getCode();
            Map<String, Object> body = Map.of(
                    "message", applicationError.getMessage(),
                    "errorCode", applicationError.errorCode(),
                    "metadata", applicationError.metadata()
            );
            return ResponseEntity.status(httpStatusCode)
                    .body(body);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

}
