package co.com.ias.project.HandyMan.technician.application.errors;

import co.com.ias.project.HandyMan.commons.errors.ApplicationError;
import co.com.ias.project.HandyMan.commons.errors.HttpStatusCode;

import java.util.Map;

public class ExternalSystemError extends ApplicationError {
    private final String message;
    private final Throwable throwable;

    public ExternalSystemError(String message, Throwable throwable) {
        this.message = message;
        this.throwable = throwable;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String errorCode() {
        return "EXTERNAL_SYSTEM_ERROR";
    }

    @Override
    public HttpStatusCode httpStatusCode() {
        return HttpStatusCode.INTERNAL_SERVER_ERROR;
    }

    @Override
    public Map<String, Object> metadata() {
        return Map.of(
                "throwable", throwable
        );
    }
}
