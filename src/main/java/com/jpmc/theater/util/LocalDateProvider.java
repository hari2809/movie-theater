package com.jpmc.theater.util;

import java.time.LocalDate;

public class LocalDateProvider {
    private static LocalDateProvider instance = null;

    /**
     * @return make sure to return singleton instance
     */
    public static LocalDateProvider singleton() {
        if (instance == null) {
            instance = new LocalDateProvider();
        }
            return instance;
        }

    /**
     * 
     * @return
     */
    public LocalDate currentDate() {
            return LocalDate.now();
    }
}
