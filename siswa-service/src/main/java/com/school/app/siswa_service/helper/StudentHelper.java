package com.school.app.siswa_service.helper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StudentHelper {

    public static LocalDateTime convertDob(String dob){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dob, dateTimeFormatter);
        return localDate.atStartOfDay();
    }
}
