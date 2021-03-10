package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");
        short age = 29;
        byte fileSize = (byte) 25;
        int distance = 384400;
        char category = 'A';
        float capacity = 25.3f;
        double density = (double) distance / capacity;
        long time = System.nanoTime();
        boolean check = true;
        LOG.debug("My age : {}, file folder size : {}, distance to the moon : {}, \n"
                + " mail category : {}, capacity in cubic meters {}, density : {}, \n"
                + " time of running JVM : {}, check result : {}",
                age, fileSize, distance, category, capacity, density, time, check);
    }
}
