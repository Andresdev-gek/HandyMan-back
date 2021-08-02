package co.com.ias.project.HandyMan.technician.application.domain.calculation.specification;

import java.util.Objects;

public class HoursCategory {
    Integer hours, mins;


    public HoursCategory(Integer hours, Integer mins) {
        this.hours = hours;
        this.mins = mins;
    }

    public Integer getHours() {
        return hours;
    }

    public Integer getMins() {
        return mins;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HoursCategory that = (HoursCategory) o;
        return Objects.equals(hours, that.hours) && Objects.equals(mins, that.mins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hours, mins);
    }

    @Override
    public String toString() {
        return "HoursCategory{" +
                "hours=" + hours +
                ", mins=" + mins +
                '}';
    }
}
