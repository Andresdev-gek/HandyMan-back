package co.com.ias.project.HandyMan.technician.application.domain.calculation.specification;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;

public class HourCalculator {


    //se saca la diferencia de horas que hay entre la fecha de inicio y de fin de un reporte
    public static double hoursDifference(LocalDateTime init, LocalDateTime end) {
        if (init == null || end == null) {
            return 0.0;
        } else {
            int diff = (int) (end.getLong(ChronoField.INSTANT_SECONDS) - init.getLong(ChronoField.INSTANT_SECONDS));

            return diff / 3600;
        }
    }


    //-------------------------------- calculo de tipos de horas  -----------------------------------------------------//

    // Horas dominicales, normales y extra //


    // dominicales normales
    public static HoursCategory dominicals(LocalDateTime initDateTime, LocalDateTime endDateTime, HoursCategory category) {
        category.hours += endDateTime.getHour() - initDateTime.getHour();
        if( endDateTime.getMinute() >= initDateTime.getMinute() ){
            if( (category.mins + (endDateTime.getMinute()-initDateTime.getMinute())) >= 60 ){
                category.hours++;
                category.mins = (category.mins + (endDateTime.getMinute()-initDateTime.getMinute())) - 60;
            }else
                category.mins += endDateTime.getMinute()-initDateTime.getMinute();
        }else {
            if((category.mins+ (initDateTime.getMinute() +60 -endDateTime.getMinute())) >= 60 ){
                category.hours++;
                category.mins = (category.mins + (initDateTime.getMinute() +60 -endDateTime.getMinute())) - 60;
            }else
                category.mins += initDateTime.getMinute() +60 -endDateTime.getMinute();
        }
        category.hours = category.hours + (category.mins / 60);


        return category;
    }


    //-----------------------------------------------------------------------------------------------------------------------------------------
    //caso cualquier diurno de horas normales y extra //

    public static HoursCategory normals(LocalDateTime initDateTime, LocalDateTime endDateTime, HoursCategory category) {
        category.hours += endDateTime.getHour() - initDateTime.getHour();
        if (initDateTime.getHour() >= 7 && initDateTime.getHour() < 20 && endDateTime.getHour() < 20) {
            category.hours += endDateTime.getHour() - initDateTime.getHour();
            if (endDateTime.getMinute() >= initDateTime.getMinute()) {
                if ((category.mins + (endDateTime.getMinute() - initDateTime.getMinute())) >= 60) {
                    category.hours++;
                    category.mins = (category.mins + (endDateTime.getMinute() - initDateTime.getMinute())) - 60;
                } else
                    category.mins += endDateTime.getMinute() - initDateTime.getMinute();
            } else {
                if ((category.mins + (initDateTime.getMinute() + 60 - endDateTime.getMinute())) >= 60) {
                    category.hours++;
                    category.mins = (category.mins + (initDateTime.getMinute() + 60 - endDateTime.getMinute())) - 60;
                } else {
                    category.mins += initDateTime.getMinute() + 60 - endDateTime.getMinute();
                }
            }
        }
        category.hours = category.hours + (category.mins / 60);


        return category;
    }


    //------------------------------------------------------------------------------------------------------------------------------------

    //caso cualquier nocturno de horas normales y extra
    public static HoursCategory nocturnals(LocalDateTime initDateTime, LocalDateTime endDateTime, HoursCategory category) {
        category.hours += endDateTime.getHour() - initDateTime.getHour();
        if ((initDateTime.getHour() >= 20 || initDateTime.getHour() < 7) && (endDateTime.getHour() >= 20 || endDateTime.getHour() < 7)) {
            if (endDateTime.getMinute() >= initDateTime.getMinute()) {
                category.hours += 24 - initDateTime.getHour() + endDateTime.getHour();
                if ((category.mins + (endDateTime.getMinute() - initDateTime.getMinute())) >= 60) {
                    category.hours++;
                    category.mins += (endDateTime.getMinute() - initDateTime.getMinute()) - 60;
                } else
                    category.mins += endDateTime.getMinute() - initDateTime.getMinute();
            } else {
                category.hours += 24 - initDateTime.getHour() + endDateTime.getHour() - 1;
                if ((category.mins + (60 - (initDateTime.getMinute() - endDateTime.getMinute()))) >= 60) {
                    category.hours++;
                    category.mins += (60 - (initDateTime.getMinute() - endDateTime.getMinute())) - 60;
                } else
                    category.mins += 60 - (initDateTime.getMinute() - endDateTime.getMinute());
            }
        if (endDateTime.getMinute() >= initDateTime.getMinute()) {
                category.hours += endDateTime.getHour() - initDateTime.getHour();
                if ((category.mins + (endDateTime.getMinute() - initDateTime.getMinute())) >= 60) {
                    category.hours++;
                    category.mins += (endDateTime.getMinute() - initDateTime.getMinute()) - 60;
                } else
                    category.mins += endDateTime.getMinute() - initDateTime.getMinute();
            } else {
                category.hours += endDateTime.getHour() - initDateTime.getHour() - 1;
                if ((category.mins + (60 - (initDateTime.getMinute() - endDateTime.getMinute()))) >= 60) {
                    category.hours++;
                    category.mins += (60 - (initDateTime.getMinute() - endDateTime.getMinute())) - 60;
                } else {
                    category.mins += 60 - (initDateTime.getMinute() - endDateTime.getMinute());
                }
            }
        }
        category.hours = category.hours + (category.mins / 60);

        return category;
    }


    //----------------------------------------------------------------------------------------------------------------------------------------
    // turnos medios / turno dia a noche

    public static List<HoursCategory> dayToNight(LocalDateTime initDateTime, LocalDateTime endDateTime, HoursCategory category, HoursCategory secondCategory) {
        if((initDateTime.getHour() >= 7 && initDateTime.getHour() < 20) && (endDateTime.getHour() >= 20 || endDateTime.getHour() < 7)) {
            category.hours += 19 - initDateTime.getHour();
            if ((category.mins + (60 - initDateTime.getMinute())) >= 60) {
                category.hours++;
                category.mins = +(60 - initDateTime.getMinute()) - 60;
            } else
                category.mins += 60 - initDateTime.getMinute();
            if (endDateTime.getHour() >= 20) {
                secondCategory.hours += (endDateTime.getHour() - 20);
                if ((secondCategory.mins + (endDateTime.getMinute())) >= 60) {
                    secondCategory.hours++;
                    secondCategory.mins += (endDateTime.getMinute()) - 60;
                } else
                    secondCategory.mins += endDateTime.getMinute();
            } else {
                secondCategory.hours += 4 + endDateTime.getHour();
                if ((secondCategory.mins + endDateTime.getMinute()) >= 60) {
                    secondCategory.hours++;
                    secondCategory.mins += endDateTime.getMinute() - 60;
                } else {
                    secondCategory.mins += endDateTime.getMinute();
                }
            }
        }
        category.hours = category.hours + (category.mins / 60);
        secondCategory.hours = secondCategory.hours + (secondCategory.mins / 60);


        secondCategory.hours = secondCategory.hours + (secondCategory.mins / 60);
        category.hours = category.hours + (category.mins / 60);

        List<HoursCategory> categories = new ArrayList<>();

        categories.add(category);
        categories.add(secondCategory);

        return categories;
    }


    // turno medio / turno noche a dia


    public static List<HoursCategory> nightToDay(LocalDateTime initDateTime, LocalDateTime endDateTime, HoursCategory category, HoursCategory secondCategory) {
        if((initDateTime.getHour() >= 20 || initDateTime.getHour() < 7) && (endDateTime.getHour() >= 7 && endDateTime.getHour() < 20)) {
            category.hours += endDateTime.getHour() - 7;
            if ((category.mins + (endDateTime.getMinute())) >= 60) {
                category.hours++;
                category.mins += endDateTime.getMinute() - 60;
            } else
                category.mins += endDateTime.getMinute();
            if (initDateTime.getHour() >= 20)
                if (endDateTime.getMinute() >= initDateTime.getMinute()) {
                    secondCategory.hours += 24 - initDateTime.getHour() + 7;
                    if ((secondCategory.mins + (initDateTime.getMinute())) >= 60) {
                        secondCategory.hours++;
                        secondCategory.mins += initDateTime.getMinute() - 60;
                    } else {
                        secondCategory.mins += initDateTime.getMinute();
                    }
                } else {
                    secondCategory.hours += 24 - initDateTime.getHour() + 6;
                    if ((secondCategory.mins + (60 - initDateTime.getMinute())) >= 60) {
                        secondCategory.hours++;
                        secondCategory.mins += (60 - initDateTime.getMinute()) - 60;
                    } else {
                        secondCategory.mins += 60 - initDateTime.getMinute();
                    }
                }
            else if (endDateTime.getMinute() >= initDateTime.getMinute()) {
                secondCategory.hours += 7 - initDateTime.getHour() - 1;
                if ((secondCategory.mins + (60 - initDateTime.getMinute())) >= 60) {
                    secondCategory.hours++;
                    secondCategory.mins += (60 - initDateTime.getMinute()) - 60;
                } else {
                    secondCategory.mins += 60 - initDateTime.getMinute();
                }
            } else {
                secondCategory.hours += 7 - initDateTime.getHour();
                if ((secondCategory.mins + (initDateTime.getMinute())) >= 60) {
                    secondCategory.hours++;
                    secondCategory.mins += initDateTime.getMinute() - 60;
                } else {
                    secondCategory.mins += initDateTime.getMinute();
                }
            }
        }
        secondCategory.hours = secondCategory.hours + (secondCategory.mins / 60);
        category.hours = category.hours + (category.mins / 60);

        List<HoursCategory> categories = new ArrayList<>();

        categories.add(category);
        categories.add(secondCategory);

        return categories;
    }

}
