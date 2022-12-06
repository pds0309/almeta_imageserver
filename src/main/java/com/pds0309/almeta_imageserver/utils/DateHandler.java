package com.pds0309.almeta_imageserver.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

import static com.pds0309.almeta_imageserver.utils.DateFormat.*;


@Component
public class DateHandler {

    public String getCurrentTime() {
        return LocalTime.now().format(TIME_FORMATTER);
    }

    public String getCurrentDate() {
        return LocalDate.now().format(DATE_FORMATTER);
    }

}
