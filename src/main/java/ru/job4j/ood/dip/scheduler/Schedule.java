package ru.job4j.ood.dip.scheduler;

import java.time.LocalDateTime;

public class Schedule {

    public String getSchedule(LocalDateTime ldt, Biology subject) {
        return String.format("%s %s%n", ldt, subject);
    }
}
