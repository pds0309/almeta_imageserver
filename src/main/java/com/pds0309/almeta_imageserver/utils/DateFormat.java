package com.pds0309.almeta_imageserver.utils;

import java.time.format.DateTimeFormatter;

public class DateFormat {

    private DateFormat() {

    }

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yy-MM-dd");
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH-mm-ss");

}
