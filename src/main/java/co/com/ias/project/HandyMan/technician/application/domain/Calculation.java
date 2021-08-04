package co.com.ias.project.HandyMan.technician.application.domain;


import co.com.ias.project.HandyMan.technician.application.domain.calculation.specification.HourCalculator;
import co.com.ias.project.HandyMan.technician.application.domain.calculation.specification.HoursCategory;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.IsoFields;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Calculation {

    public static Object[] calculation(Collection<ServiceReport> reports, int numberOfWeek) {
        List<LocalDateTime> dates = new ArrayList<>();
        List<LocalDateTime> datesWithWeek = new ArrayList<>();
        Object[] workedHours = new Object[7];
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

        Integer totalHours = 0;

        // estos objetos guardan la cantidad de horas por categorias

        HoursCategory NORMAL = new HoursCategory(0, 0);
        HoursCategory NORMAL_EXTRA = new HoursCategory(0, 0);

        HoursCategory NOCTURNAL = new HoursCategory(0, 0);
        HoursCategory NOCTURNAL_EXTRA = new HoursCategory(0, 0);

        HoursCategory DOMINICAL = new HoursCategory(0, 0);
        HoursCategory DOMINICAL_EXTRA = new HoursCategory(0, 0);


        // sacamos solo la fecha de inicio y final de cada reporte y las almacenamos en una lista

        for (ServiceReport report : reports) {
            dates.add(report.getStartDateTime());
            dates.add(report.getEndDateTime());
        }

        //nos quedamos solo con las fechas cuyo numero de semana coincide con el solicitado
        for (LocalDateTime date : dates) {
            LocalDate onlyDate = date.toLocalDate();

            if (onlyDate.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR) == numberOfWeek) {
                datesWithWeek.add(date);
            }

        }


        // iteramos sobre cada una de estas fechas que ya sabemos que coinciden con el numero de semana solicitado
        if (numberOfWeek >= 1 && numberOfWeek <= 53) {
            for (int i = 0; i < datesWithWeek.size()-1; i = i + 2) {
                LocalDateTime initDateTime = LocalDateTime.parse(String.valueOf(datesWithWeek.get(i)), format);
                LocalDateTime endDateTime = LocalDateTime.parse(String.valueOf(datesWithWeek.get(i+1)), format);

                String dayOfWeek = String.valueOf(initDateTime.getDayOfWeek());

                if (totalHours < 48) {
                    if (dayOfWeek.equals("SUNDAY")) {
                        HourCalculator.dominicals(initDateTime, endDateTime, DOMINICAL);
                    } else {
                        HourCalculator.normals(initDateTime, endDateTime, NORMAL);
                        HourCalculator.nocturnals(initDateTime, endDateTime, NOCTURNAL);

                        List<HoursCategory> categories = HourCalculator.nightToDay(initDateTime, endDateTime, NORMAL, NOCTURNAL);
                        NORMAL = categories.get(0);
                        NOCTURNAL = categories.get(1);

                        List<HoursCategory> categoriesTwo = HourCalculator.dayToNight(initDateTime, endDateTime, NORMAL, NOCTURNAL);
                        NORMAL = categoriesTwo.get(0);
                        NOCTURNAL = categoriesTwo.get(1);

                    }
                } else {
                    if (dayOfWeek.equals("SUNDAY")) {
                        HourCalculator.dominicals(initDateTime, endDateTime, DOMINICAL_EXTRA);
                    } else {
                        HourCalculator.normals(initDateTime, endDateTime, NORMAL_EXTRA);
                        HourCalculator.nocturnals(initDateTime, endDateTime, NOCTURNAL_EXTRA);

                        List<HoursCategory> categories = HourCalculator.nightToDay(initDateTime, endDateTime, NORMAL_EXTRA, NOCTURNAL_EXTRA);
                        NORMAL_EXTRA = categories.get(0);
                        NOCTURNAL_EXTRA = categories.get(1);

                        List<HoursCategory> categoriesTwo = HourCalculator.dayToNight(initDateTime, endDateTime, NORMAL_EXTRA, NOCTURNAL_EXTRA);
                        NORMAL_EXTRA = categoriesTwo.get(0);
                        NOCTURNAL_EXTRA = categoriesTwo.get(1);
                    }
                }

                totalHours = NORMAL.getHours() + NORMAL_EXTRA.getHours() + NOCTURNAL.getHours() + NOCTURNAL_EXTRA.getHours() + DOMINICAL.getHours() + DOMINICAL_EXTRA.getHours() + ((NORMAL.getMins() + NORMAL_EXTRA.getMins() + NOCTURNAL.getMins() + NOCTURNAL_EXTRA.getMins() + DOMINICAL.getMins() + DOMINICAL_EXTRA.getMins()) / 60);


            } //fin del bucle

        }


        workedHours[0] = NORMAL.getMins() < 10 ? NORMAL.getHours() + ":0" + NORMAL.getMins() : NORMAL.getHours() + ":" + NORMAL.getMins();
        workedHours[1] = NOCTURNAL.getMins() < 10 ? NOCTURNAL.getHours() + ":0" + NOCTURNAL.getMins() : NOCTURNAL.getHours() + ":" + NOCTURNAL.getMins();
        workedHours[2] = DOMINICAL.getMins() < 10 ? DOMINICAL.getHours() + ":0" + DOMINICAL.getMins() : DOMINICAL.getHours() + ":" + DOMINICAL.getMins();
        workedHours[3] = NORMAL_EXTRA.getMins() < 10 ? NORMAL_EXTRA.getHours() + ":0" + NORMAL_EXTRA.getMins() : NORMAL_EXTRA.getHours() + ":" + NORMAL_EXTRA.getMins();
        workedHours[4] = NOCTURNAL_EXTRA.getMins() < 10 ? NOCTURNAL_EXTRA.getHours() + ":0" + NOCTURNAL_EXTRA.getMins() : NOCTURNAL_EXTRA.getHours() + ":" + NOCTURNAL_EXTRA.getMins();
        workedHours[5] = DOMINICAL_EXTRA.getMins() < 10 ? DOMINICAL_EXTRA.getHours() + ":0" + DOMINICAL_EXTRA.getMins() : DOMINICAL_EXTRA.getHours() + ":" + DOMINICAL_EXTRA.getMins();
        workedHours[6] = "" + totalHours;


        return workedHours;
    }

}