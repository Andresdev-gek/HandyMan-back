package co.com.ias.project.HandyMan.technician.application.domain.calculation.specification;

import java.util.Objects;

public class HoursCategory {
    int hours, mins;


    public HoursCategory(int hours, int mins) {
        this.hours = hours;
        this.mins = mins;
    }

    public int getHours() {
        return hours;
    }

    public int getMins() {
        return mins;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HoursCategory that = (HoursCategory) o;
        return hours == that.hours && mins == that.mins;
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
