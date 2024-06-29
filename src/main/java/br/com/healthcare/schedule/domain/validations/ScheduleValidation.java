package br.com.healthcare.schedule.domain.validations;

import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ScheduleValidation {


    public boolean isScheduleValid(LocalDateTime inicioConsulta, LocalDateTime fimConsulta){
        DayOfWeek weekDay = inicioConsulta.getDayOfWeek();
        int hourDay = inicioConsulta.getHour();

        if (weekDay == DayOfWeek.SATURDAY || weekDay == DayOfWeek.SUNDAY || hourDay < 7 || hourDay > 22){
            return false;
        }

        Duration duration = Duration.between(inicioConsulta, fimConsulta);
        return duration.equals(Duration.ofHours(1));

    }
}
