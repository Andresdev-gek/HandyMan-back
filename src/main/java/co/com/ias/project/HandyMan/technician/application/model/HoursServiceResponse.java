package co.com.ias.project.HandyMan.technician.application.model;

import co.com.ias.project.HandyMan.commons.operation.ApplicationResponse;

public class HoursServiceResponse implements ApplicationResponse {
    private final Object[] data;

    public HoursServiceResponse(Object[] data) {
        this.data = data;
    }

    public Object[] getCalculo() {
        return data;
    }
}
