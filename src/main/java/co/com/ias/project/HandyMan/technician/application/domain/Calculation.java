package co.com.ias.project.HandyMan.technician.application.domain;


import co.com.ias.project.HandyMan.technician.application.domain.calculation.specification.HourCalculator;
import co.com.ias.project.HandyMan.technician.application.domain.calculation.specification.HoursCategory;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.IsoFields;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Calculation {

    public static Object[] calculation(Collection<ServiceReport> reports, int numberOfWeek) {
        List<LocalDateTime> dates = new java.util.ArrayList<>(Collections.singletonList(LocalDateTime.of(0, 0, 0, 0, 0)));
        List<LocalDateTime> datesWithWeek = new java.util.ArrayList<>(Collections.singletonList(LocalDateTime.of(0, 0, 0, 0, 0)));
        Object[] workedHours = new Object[7];

        // estos objetos guardan la cantidad de horas por categorias

        HoursCategory NORMAL = new HoursCategory(0, 0);
        HoursCategory NORMAL_EXTRA = new HoursCategory(0, 0);

        HoursCategory NOCTURNAL = new HoursCategory(0, 0);
        HoursCategory NOCTURNAL_EXTRA = new HoursCategory(0, 0);

        HoursCategory DOMINICAL = new HoursCategory(0, 0);
        HoursCategory DOMINICAL_EXTRA = new HoursCategory(0, 0);

        int totalHours = NORMAL.getHours() + NOCTURNAL.getHours() + DOMINICAL.getHours() + NORMAL_EXTRA.getHours() + NOCTURNAL_EXTRA.getHours() + DOMINICAL_EXTRA.getHours() + ((NORMAL.getMins() + NOCTURNAL.getMins() + DOMINICAL.getMins() + NORMAL_EXTRA.getMins() + NOCTURNAL_EXTRA.getMins() + DOMINICAL_EXTRA.getMins()) / 60);

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
            for (int i = 1; i < datesWithWeek.size(); i = i + 2) {
                LocalDateTime initDateTime = LocalDateTime.parse(datesWithWeek.get(i).toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                LocalDateTime endDateTime = LocalDateTime.parse(datesWithWeek.get(i + 1).toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

                String dayOfWeek = String.valueOf(initDateTime.getDayOfWeek());

                HourCalculator.dominicalsNormalAndExtra(initDateTime, endDateTime, dayOfWeek, DOMINICAL, DOMINICAL_EXTRA, totalHours);
                HourCalculator.normalsAndExtraNormals(initDateTime, endDateTime, dayOfWeek, NORMAL, NORMAL_EXTRA, totalHours);
                HourCalculator.nocturnalAndExtraNocturnal(initDateTime, endDateTime, dayOfWeek, NOCTURNAL, NOCTURNAL_EXTRA, totalHours);
                HourCalculator.fromDayToNight(initDateTime, endDateTime, dayOfWeek, NORMAL, NOCTURNAL, totalHours);
                HourCalculator.fromDayToNightExtra(initDateTime, endDateTime, dayOfWeek, NORMAL_EXTRA, NOCTURNAL_EXTRA, totalHours);
                HourCalculator.fromNightToDay(initDateTime, endDateTime, dayOfWeek, NORMAL, NOCTURNAL, totalHours);
                HourCalculator.fromNightToDayExtra(initDateTime, endDateTime, dayOfWeek, NORMAL_EXTRA, NOCTURNAL_EXTRA, totalHours);


            }
        }

        workedHours[0] = NORMAL.getMins() < 10 ? NORMAL.getHours() + ":0" + NORMAL.getMins() : NORMAL.getHours() + ":" + NORMAL.getMins();
        workedHours[1] = NOCTURNAL.getMins() < 10 ? NOCTURNAL.getHours() + ":0" + NOCTURNAL.getMins() : NOCTURNAL.getHours() + ":" + NOCTURNAL.getMins();
        workedHours[2] = DOMINICAL.getMins() < 10 ? DOMINICAL.getHours() + ":0" + DOMINICAL.getMins() : DOMINICAL.getHours() + ":" + DOMINICAL.getMins();
        workedHours[3] = NOCTURNAL_EXTRA.getMins() < 10 ? NORMAL_EXTRA.getHours() + ":0" + NORMAL_EXTRA.getMins() : NORMAL_EXTRA.getHours() + ":" + NORMAL_EXTRA.getMins();
        workedHours[4] = NOCTURNAL_EXTRA.getMins() < 10 ? NOCTURNAL_EXTRA.getHours() + ":0" + NOCTURNAL_EXTRA.getMins() : NOCTURNAL_EXTRA.getHours() + ":" + NOCTURNAL_EXTRA.getMins();
        workedHours[5] = DOMINICAL_EXTRA.getMins() < 10 ? DOMINICAL_EXTRA.getHours() + ":0" + DOMINICAL_EXTRA.getMins() : DOMINICAL_EXTRA.getHours() + ":" + DOMINICAL_EXTRA.getMins();
        workedHours[6] = " total horas trabajadas: " + totalHours;


        return workedHours;
    }


}

