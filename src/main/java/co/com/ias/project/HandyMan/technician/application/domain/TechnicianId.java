package co.com.ias.project.HandyMan.technician.application.domain;

import co.com.ias.project.HandyMan.commons.operation.StringUtils;
import co.com.ias.project.HandyMan.commons.operation.Validate;

import java.util.Objects;
import java.util.regex.Pattern;


public class TechnicianId {
    private final String technicianId;

    public static TechnicianId of(String technicianId) {
        return new TechnicianId(technicianId);
    }

    private TechnicianId(String technicianId) {
        Validate.notNull(technicianId, "The technician id can not be null");
        Validate.isTrue(StringUtils.nonBlank(technicianId), "The technician id can not be empty");
        Pattern pattern = Pattern.compile("^((\\d{8})|(\\d{10})|(\\d{11})|(\\d{6}-\\d{5}))?$"); //Regex para cedula de ciudadania
        Validate.isTrue(pattern.matcher(technicianId).matches(), "Invalid technician id: " + technicianId);

        this.technicianId = technicianId;
    }

    public String getValue() {
        return technicianId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TechnicianId that = (TechnicianId) o;
        return technicianId.equals(that.technicianId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(technicianId);
    }

    @Override
    public String toString() {
        return "TechnicianId{" +
                "technicianId='" + technicianId + '\'' +
                '}';
    }
}
