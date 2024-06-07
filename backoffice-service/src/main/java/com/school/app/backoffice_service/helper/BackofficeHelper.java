package com.school.app.backoffice_service.helper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BackofficeHelper {

    public static LocalDateTime convertDob(String dob){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dob, dateTimeFormatter);
        return localDate.atStartOfDay();
    }

    public static String convertDob(LocalDateTime dob){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return dob.format(dateTimeFormatter);
    }
}
