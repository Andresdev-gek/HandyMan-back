package co.com.ias.project.HandyMan.technician.application.errors;

import co.com.ias.project.HandyMan.commons.errors.ApplicationError;
import co.com.ias.project.HandyMan.commons.errors.HttpStatusCode;


public class InputDataError extends ApplicationError {
    private final String message;

    public InputDataError(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String errorCode() {
        return "INPUT_DATA_ERROR";
    }

    @Override
    public HttpStatusCode httpStatusCode() {
        return HttpStatusCode.BAD_REQUEST;
    }


}
