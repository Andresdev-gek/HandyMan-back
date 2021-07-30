package co.com.ias.project.HandyMan.technician.application.domain;

import co.com.ias.project.HandyMan.commons.operation.StringUtils;
import co.com.ias.project.HandyMan.commons.operation.Validate;

import java.util.Objects;

public class ServiceId {
    private final String serviceId;

    public static ServiceId of(String serviceId) {
        return new ServiceId(serviceId);
    }

    private ServiceId(String serviceId) {
        Validate.notNull(serviceId, "The service id can not be null");
        Validate.isTrue(StringUtils.nonBlank(serviceId), "The service id can not be empty");
        this.serviceId = serviceId;
    }

    public String getValue() {
        return serviceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceId serviceId1 = (ServiceId) o;
        return serviceId.equals(serviceId1.serviceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceId);
    }

    @Override
    public String toString() {
        return "ServiceId{" +
                "serviceId='" + serviceId + '\'' +
                '}';
    }
}
