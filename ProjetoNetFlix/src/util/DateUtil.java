package util;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

public class DateUtil {
    public static int calculateAge(Date birthDate) {
		LocalDate birthDateLocalDate =  new java.sql.Date(birthDate.getTime()).toLocalDate();
        return Period.between(birthDateLocalDate, LocalDate.now()).getYears();
    }
}
