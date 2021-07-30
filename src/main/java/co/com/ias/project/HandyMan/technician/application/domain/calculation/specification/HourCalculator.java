package co.com.ias.project.HandyMan.technician.application.domain.calculation.specification;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;

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

    public static void dominicalsNormalAndExtra(LocalDateTime initDateTime, LocalDateTime endDateTime, String dayOfWeek, HoursCategory category, HoursCategory secondCategory, int totalHours) {

        int dominicalHours = category.hours;
        int dominicalMins = category.mins;

        int extraDominicalHours = secondCategory.hours;
        int extraDominicalMins = secondCategory.mins;

        if (dayOfWeek.equals("SUNDAY") && totalHours < 48) {

            dominicalHours += endDateTime.getHour() - initDateTime.getHour();
            if (endDateTime.getMinute() >= initDateTime.getMinute()) {
                if ((dominicalMins + (endDateTime.getMinute() - initDateTime.getMinute())) >= 60) {
                    dominicalHours++;
                    dominicalMins = (dominicalMins + (endDateTime.getMinute() - initDateTime.getMinute())) - 60;
                } else {
                    dominicalMins += endDateTime.getMinute() - initDateTime.getMinute();
                }
            } else {
                if ((dominicalMins + (initDateTime.getMinute() + 60 - endDateTime.getMinute())) >= 60) {
                    dominicalHours++;
                    dominicalMins = (dominicalMins + (initDateTime.getMinute() + 60 - endDateTime.getMinute())) - 60;
                } else {
                    dominicalMins += initDateTime.getMinute() + 60 - endDateTime.getMinute();
                }

            }
            dominicalHours += dominicalHours + (extraDominicalMins / 60);

        } else if (dayOfWeek.equals("SUNDAY") && totalHours > 48) {
            extraDominicalHours += endDateTime.getHour() - initDateTime.getHour();
            if (endDateTime.getMinute() >= initDateTime.getMinute()) {
                if ((extraDominicalMins + (endDateTime.getMinute() - initDateTime.getMinute())) >= 60) {
                    extraDominicalHours++;
                    extraDominicalMins = (extraDominicalMins + (endDateTime.getMinute() - initDateTime.getMinute())) - 60;
                } else {
                    extraDominicalMins += endDateTime.getMinute() - initDateTime.getMinute();
                }
            } else {
                if ((extraDominicalMins + (initDateTime.getMinute() + 60 - endDateTime.getMinute())) >= 60) {
                    extraDominicalHours++;
                    extraDominicalMins = (extraDominicalMins + (initDateTime.getMinute() + 60 - endDateTime.getMinute())) - 60;
                } else {
                    extraDominicalMins += initDateTime.getMinute() + 60 - endDateTime.getMinute();
                }

            }
            extraDominicalHours += extraDominicalHours + (extraDominicalMins / 60);
        }

        category.hours += dominicalHours;
        category.mins += dominicalMins;

        secondCategory.hours += extraDominicalHours;
        secondCategory.mins += extraDominicalMins;
    }

    //-----------------------------------------------------------------------------------------------------------------------------------------
    //caso cualquier diurno de horas normales y extra //

    public static void normalsAndExtraNormals(LocalDateTime initDateTime, LocalDateTime endDateTime, String dayOfWeek,HoursCategory category, HoursCategory secondCategory, int totalHours) {
        int normalHours = category.hours;
        int normalMins = category.mins;

        int extraNormalHours = secondCategory.hours;
        int extraNormalMins = secondCategory.mins;


        if (!dayOfWeek.equals("SUNDAY") && totalHours < 48) {
            if (initDateTime.getHour() >= 7 && endDateTime.getHour() < 20 && endDateTime.getHour() < 20) {
                if (endDateTime.getMinute() >= initDateTime.getMinute()) {
                    normalHours += endDateTime.getHour() - initDateTime.getHour();
                    if ((normalMins + (endDateTime.getMinute() - initDateTime.getMinute())) >= 60) {
                        normalHours++;
                        normalMins += (endDateTime.getMinute() - initDateTime.getMinute()) - 60;
                    } else
                        normalMins += (endDateTime.getMinute() - initDateTime.getMinute());
                } else {
                    normalHours += endDateTime.getHour() - initDateTime.getHour() - 1;
                    if ((normalMins + (60 - (initDateTime.getMinute() - endDateTime.getMinute()))) >= 60) {
                        normalHours++;
                        normalMins += (60 - (initDateTime.getMinute() - endDateTime.getMinute())) - 60;
                    } else {
                        normalMins += (60 - (initDateTime.getMinute() - endDateTime.getMinute()));
                    }

                }
                normalHours += normalHours + extraNormalHours + ((normalMins + extraNormalMins) / 60);


            } else if (!dayOfWeek.equals("SUNDAY") && totalHours > 48) {
                if (initDateTime.getHour() >= 7 && initDateTime.getHour() < 20 && endDateTime.getHour() < 20) {

                    if (endDateTime.getMinute() >= initDateTime.getMinute()) {
                        extraNormalHours += endDateTime.getHour() - initDateTime.getHour();
                        if ((extraNormalMins + (endDateTime.getMinute() - initDateTime.getMinute())) >= 60) {
                            extraNormalHours++;
                            extraNormalMins += (endDateTime.getMinute() - initDateTime.getMinute()) - 60;
                        } else
                            extraNormalMins += (endDateTime.getMinute() - initDateTime.getMinute());
                    } else {
                        extraNormalHours += endDateTime.getHour() - initDateTime.getHour() - 1;
                        if ((extraNormalMins + (60 - (initDateTime.getMinute() - endDateTime.getMinute()))) >= 60) {
                            extraNormalHours++;
                            extraNormalMins += (60 - (initDateTime.getMinute() - endDateTime.getMinute())) - 60;
                        } else
                            extraNormalMins += (60 - (initDateTime.getMinute() - endDateTime.getMinute()));
                    }
                    extraNormalHours += extraNormalHours + (extraNormalMins / 60);

                }
            }

        }

        category.hours += normalHours;
        category.mins += normalMins;
        secondCategory.hours += extraNormalHours;
        secondCategory.mins += extraNormalMins;
    }

    //------------------------------------------------------------------------------------------------------------------------------------

    //caso cualquier nocturno de horas normales y extra

    public static void nocturnalAndExtraNocturnal(LocalDateTime initDateTime, LocalDateTime endDateTime, String dayOfWeek,HoursCategory category, HoursCategory secondCategory, int totalHours) {
        int nocturnalHours = category.hours;
        int nocturnalMins = category.mins;

        int extraNocturnalHours = secondCategory.hours;
        int extraNocturnalMins = secondCategory.mins;

        if (!dayOfWeek.equals("SUNDAY") && totalHours < 48) {
            if ((initDateTime.getHour() >= 20 || initDateTime.getHour() < 7) && (endDateTime.getHour() >= 20 || endDateTime.getHour() < 7)) {
                if (initDateTime.getHour() >= 20 && initDateTime.getHour() < 7) {
                    if (endDateTime.getMinute() >= initDateTime.getMinute()) {
                        nocturnalHours += 24 - initDateTime.getHour() + endDateTime.getHour();
                        if ((nocturnalMins + (initDateTime.getMinute() - initDateTime.getMinute())) >= 60) {
                            nocturnalHours++;
                            nocturnalMins += (endDateTime.getMinute() - initDateTime.getMinute()) - 60;
                        } else
                            nocturnalMins += endDateTime.getMinute() - initDateTime.getMinute();
                    } else {
                        nocturnalHours += 24 - initDateTime.getHour() + endDateTime.getHour() - 1;
                        if ((nocturnalMins + (60 - (initDateTime.getMinute() - endDateTime.getMinute()))) >= 60) {
                            nocturnalHours++;
                            nocturnalMins += (60 - (initDateTime.getMinute() - endDateTime.getMinute())) - 60;
                        } else
                            nocturnalMins += 60 - (initDateTime.getMinute() - endDateTime.getMinute());
                    }
                } else if (endDateTime.getMinute() >= initDateTime.getMinute()) {
                    nocturnalHours += endDateTime.getHour() - initDateTime.getHour();
                    if ((nocturnalMins + (endDateTime.getMinute() - initDateTime.getMinute())) >= 60) {
                        nocturnalHours++;
                        nocturnalMins += (endDateTime.getMinute() - initDateTime.getMinute()) - 60;
                    } else
                        nocturnalMins += endDateTime.getMinute() - initDateTime.getMinute();
                } else {
                    nocturnalHours += endDateTime.getHour() - initDateTime.getHour() - 1;
                    if ((nocturnalMins + (60 - (initDateTime.getMinute() - endDateTime.getMinute()))) >= 60) {
                        nocturnalHours++;
                        nocturnalMins += (60 - (initDateTime.getMinute() - endDateTime.getMinute())) - 60;
                    } else
                        nocturnalMins += 60 - (initDateTime.getMinute() - endDateTime.getMinute());
                }

                nocturnalHours += nocturnalHours + (nocturnalMins / 60);

            } else if (!dayOfWeek.equals("SUNDAY") && totalHours > 48) {
                if (initDateTime.getHour() >= 20 && initDateTime.getHour() < 7) {
                    if (endDateTime.getMinute() >= initDateTime.getMinute()) {
                        extraNocturnalHours += 24 - initDateTime.getHour() + endDateTime.getHour();
                        if ((nocturnalMins + (initDateTime.getMinute() - initDateTime.getMinute())) >= 60) {
                            extraNocturnalHours++;
                            extraNocturnalMins += (endDateTime.getMinute() - initDateTime.getMinute()) - 60;
                        } else
                            extraNocturnalMins += endDateTime.getMinute() - initDateTime.getMinute();
                    } else {
                        extraNocturnalHours += 24 - initDateTime.getHour() + endDateTime.getHour() - 1;
                        if ((nocturnalMins + (60 - (initDateTime.getMinute() - endDateTime.getMinute()))) >= 60) {
                            extraNocturnalHours++;
                            extraNocturnalMins += (60 - (initDateTime.getMinute() - endDateTime.getMinute())) - 60;
                        } else
                            extraNocturnalMins += 60 - (initDateTime.getMinute() - endDateTime.getMinute());
                    }
                } else if (endDateTime.getMinute() >= initDateTime.getMinute()) {
                    extraNocturnalHours += endDateTime.getHour() - initDateTime.getHour();
                    if ((extraNocturnalMins + (endDateTime.getMinute() - initDateTime.getMinute())) >= 60) {
                        extraNocturnalHours++;
                        extraNocturnalMins += (endDateTime.getMinute() - initDateTime.getMinute()) - 60;
                    } else
                        extraNocturnalMins += endDateTime.getMinute() - initDateTime.getMinute();
                } else {
                    extraNocturnalHours += endDateTime.getHour() - initDateTime.getHour() - 1;
                    if ((extraNocturnalMins + (60 - (initDateTime.getMinute() - endDateTime.getMinute()))) >= 60) {
                        extraNocturnalHours++;
                        extraNocturnalMins += (60 - (initDateTime.getMinute() - endDateTime.getMinute())) - 60;
                    } else
                        extraNocturnalMins += 60 - (initDateTime.getMinute() - endDateTime.getMinute());
                }

                extraNocturnalHours += extraNocturnalHours + (extraNocturnalMins / 60);
            }


        }

        category.hours = nocturnalHours;
        category.mins = nocturnalMins;

        secondCategory.hours = extraNocturnalHours;
        secondCategory.mins = extraNocturnalMins;

    }

    //----------------------------------------------------------------------------------------------------------------------------------------
    // turnos medios / turno dia a noche

    public static void fromDayToNight(LocalDateTime initDateTime, LocalDateTime endDateTime, String dayOfWeek, HoursCategory category, HoursCategory secondCategory, int totalHours) {
        int normalHours = category.hours;
        int normalMins = category.mins;

        int nocturnalHours = secondCategory.hours;
        int nocturnalMins = secondCategory.mins;


        if (!dayOfWeek.equals("SUNDAY") && totalHours < 48) {
            if ((initDateTime.getHour() >= 7 && initDateTime.getHour() < 20) && (endDateTime.getHour() >= 20 || endDateTime.getHour() < 7)) {
                normalHours += 19 - initDateTime.getHour();
                if ((normalMins + (60 - initDateTime.getMinute())) >= 60) {
                    normalHours++;
                    normalMins = +(60 - initDateTime.getMinute()) - 60;
                } else {
                    normalMins += 60 - initDateTime.getMinute();
                }
                if (endDateTime.getHour() >= 20) {
                    nocturnalHours += (endDateTime.getHour() - 20);
                    if ((nocturnalMins + (endDateTime.getMinute())) >= 60) {
                        nocturnalHours++;
                        nocturnalMins += (endDateTime.getMinute()) - 60;
                    } else
                        nocturnalMins += endDateTime.getMinute();
                } else {
                    nocturnalHours += 4 + endDateTime.getHour();
                    if ((nocturnalMins + endDateTime.getMinute()) >= 60) {
                        nocturnalHours++;
                        nocturnalMins += endDateTime.getMinute() - 60;
                    } else
                        nocturnalMins += endDateTime.getMinute();
                }
                nocturnalHours += nocturnalHours + (nocturnalMins / 60);
                normalHours += normalHours + (normalMins / 60);
            }
        }

        category.hours += normalHours;
        category.mins += normalMins;

        secondCategory.hours += nocturnalHours;
        secondCategory.mins += nocturnalMins;

    }

    // aca para horas extra

    public static void fromDayToNightExtra(LocalDateTime initDateTime, LocalDateTime endDateTime, String dayOfWeek, HoursCategory category, HoursCategory secondCategory, int totalHours) {
        int extraNormalHours = category.hours;
        int extraNormalMins = category.mins;

        int extraNocturnalHours = secondCategory.hours;
        int extraNocturnalMins = secondCategory.mins;


        if (!dayOfWeek.equals("SUNDAY") && totalHours > 48) {
            if ((initDateTime.getHour() >= 7 && initDateTime.getHour() < 20) && (endDateTime.getHour() >= 20 || endDateTime.getHour() < 7)) {
                extraNormalHours += 19 - initDateTime.getHour();
                if ((extraNormalMins + (60 - initDateTime.getMinute())) >= 60) {
                    extraNormalHours++;
                    extraNormalMins = +(60 - initDateTime.getMinute()) - 60;
                } else {
                    extraNormalMins += 60 - initDateTime.getMinute();
                }
                if (endDateTime.getHour() >= 20) {
                    extraNocturnalHours += (endDateTime.getHour() - 20);
                    if ((extraNocturnalMins + (endDateTime.getMinute())) >= 60) {
                        extraNocturnalHours++;
                        extraNocturnalMins += (endDateTime.getMinute()) - 60;
                    } else
                        extraNocturnalMins += endDateTime.getMinute();
                } else {
                    extraNocturnalHours += 4 + endDateTime.getHour();
                    if ((extraNocturnalMins + endDateTime.getMinute()) >= 60) {
                        extraNocturnalHours++;
                        extraNocturnalMins += endDateTime.getMinute() - 60;
                    } else
                        extraNocturnalMins += endDateTime.getMinute();
                }
                extraNocturnalHours += extraNocturnalHours + (extraNocturnalMins / 60);
                extraNormalHours += extraNormalHours + (extraNormalMins / 60);
            }
        }

        category.hours += extraNormalHours;
        category.mins += extraNormalMins;

        secondCategory.hours += extraNocturnalHours;
        secondCategory.mins += extraNocturnalMins;

    }


    // turno medio / turno noche a dia


    public static void fromNightToDay(LocalDateTime initDateTime, LocalDateTime endDateTime, String dayOfWeek, HoursCategory category, HoursCategory secondCategory, int totalHours) {
        int normalHours = category.hours;
        int normalMins = category.mins;

        int nocturnalHours = secondCategory.hours;
        int nocturnalMins = secondCategory.mins;



        if (!dayOfWeek.equals("SUNDAY") && totalHours < 48) {
            if ((endDateTime.getHour() >= 20 || endDateTime.getHour() < 7) && (endDateTime.getHour() >= 7 && endDateTime.getHour() < 20)) {
                normalHours += endDateTime.getHour() - 7;
                if ((normalMins + (endDateTime.getMinute())) >= 60) {
                    normalHours++;
                    normalMins += endDateTime.getMinute() - 60;
                } else
                    normalMins += endDateTime.getMinute();
                if (initDateTime.getHour() >= 20)
                    if (endDateTime.getMinute() >= initDateTime.getMinute()) {
                        nocturnalHours += 24 - initDateTime.getHour() + 7;
                        if ((nocturnalMins + (initDateTime.getMinute())) >= 60) {
                            nocturnalHours++;
                            nocturnalMins += initDateTime.getMinute() - 60;
                        } else
                            nocturnalMins += initDateTime.getMinute();
                    } else {
                        nocturnalHours += 24 - initDateTime.getHour() + 6;
                        if ((nocturnalMins + (60 - initDateTime.getMinute())) >= 60) {
                            nocturnalHours++;
                            nocturnalMins += (60 - initDateTime.getMinute()) - 60;
                        } else
                            nocturnalMins += 60 - initDateTime.getMinute();
                    }
                else if (endDateTime.getMinute() >= initDateTime.getMinute()) {
                    nocturnalHours += 7 - initDateTime.getHour() - 1;
                    if ((nocturnalMins + (60 - initDateTime.getMinute())) >= 60) {
                        nocturnalHours++;
                        nocturnalMins += (60 - initDateTime.getMinute()) - 60;
                    } else
                        nocturnalMins += 60 - initDateTime.getMinute();
                } else {
                    nocturnalHours += 7 - initDateTime.getHour();
                    if ((nocturnalMins + (initDateTime.getMinute())) >= 60) {
                        nocturnalHours++;
                        nocturnalMins += initDateTime.getMinute() - 60;
                    } else
                        nocturnalMins += initDateTime.getMinute();
                }
                nocturnalHours += nocturnalHours + (nocturnalMins / 60);
                normalHours += normalHours + (normalMins / 60);
            }
        }

        category.hours += normalHours;
        category.mins += normalMins;

        secondCategory.hours += nocturnalHours;
        secondCategory.mins += nocturnalMins;


    }

    // horas extra del mismo tipo
    public static void fromNightToDayExtra(LocalDateTime initDateTime, LocalDateTime endDateTime, String dayOfWeek, HoursCategory category, HoursCategory secondCategory, int totalHours) {
        int extraNormalHours = category.hours;
        int extraNormalMins = category.mins;

        int extraNocturnalHours = secondCategory.hours;
        int extraNocturnalMins = secondCategory.mins;



        if (!dayOfWeek.equals("SUNDAY") && totalHours > 48) {
            if ((endDateTime.getHour() >= 20 || endDateTime.getHour() < 7) && (endDateTime.getHour() >= 7 && endDateTime.getHour() < 20)) {
                extraNormalHours += endDateTime.getHour() - 7;
                if ((extraNormalMins + (endDateTime.getMinute())) >= 60) {
                    extraNormalHours++;
                    extraNormalMins += endDateTime.getMinute() - 60;
                } else
                    extraNormalMins += endDateTime.getMinute();
                if (initDateTime.getHour() >= 20)
                    if (endDateTime.getMinute() >= initDateTime.getMinute()) {
                        extraNocturnalHours += 24 - initDateTime.getHour() + 7;
                        if ((extraNocturnalMins + (initDateTime.getMinute())) >= 60) {
                            extraNocturnalHours++;
                            extraNocturnalMins += initDateTime.getMinute() - 60;
                        } else
                            extraNocturnalMins += initDateTime.getMinute();
                    } else {
                        extraNocturnalHours += 24 - initDateTime.getHour() + 6;
                        if ((extraNocturnalMins + (60 - initDateTime.getMinute())) >= 60) {
                            extraNocturnalHours++;
                            extraNocturnalMins += (60 - initDateTime.getMinute()) - 60;
                        } else
                            extraNocturnalMins += 60 - initDateTime.getMinute();
                    }
                else if (endDateTime.getMinute() >= initDateTime.getMinute()) {
                    extraNocturnalHours += 7 - initDateTime.getHour() - 1;
                    if ((extraNocturnalMins + (60 - initDateTime.getMinute())) >= 60) {
                        extraNocturnalMins++;
                        extraNocturnalMins += (60 - initDateTime.getMinute()) - 60;
                    } else
                        extraNocturnalMins += 60 - initDateTime.getMinute();
                } else {
                    extraNocturnalHours += 7 - initDateTime.getHour();
                    if ((extraNocturnalMins + (initDateTime.getMinute())) >= 60) {
                        extraNocturnalHours++;
                        extraNocturnalMins += initDateTime.getMinute() - 60;
                    } else
                        extraNocturnalMins += initDateTime.getMinute();
                }
                extraNocturnalHours += extraNocturnalHours + (extraNocturnalMins / 60);
                extraNormalHours += extraNormalHours + (extraNormalMins / 60);
            }
        }

        category.hours += extraNormalHours;
        category.mins += extraNormalMins;

        secondCategory.hours += extraNocturnalHours;
        secondCategory.mins += extraNocturnalMins;


    }

}
