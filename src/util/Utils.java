package util;

import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;

/**
 * This is a utility interface which list the utility methods.
 */
public interface Utils {

  /**
   * This method formats the string date into the LocalDate.
   *
   * @param date the string date
   * @return the LocalDate
   * @throws IllegalArgumentException invalid date or date format
   */
  static LocalDate string2Date(String date) throws IllegalArgumentException {
    if (date == null || !date.matches("([0-9]{4})-([0-9]{2})-([0-9]{2})")) {
      throw new IllegalArgumentException("The date is invalid!");
    }

    LocalDate localDate;
    try {
      localDate = LocalDate.parse(date);
    } catch (DateTimeException e) {
      throw new IllegalArgumentException("The date is invalid!");
    }

    //    if (localDate.isAfter(LocalDate.now())) {
    //      throw new IllegalArgumentException("The date cannot be a future date!");
    //    }

    return localDate;
  }

  /**
   * It checks whether the date is weekend or not.
   *
   * @param date the string date
   * @throws IllegalArgumentException invalid date or date format
   */
  static void checkWeekends(LocalDate date) throws IllegalArgumentException {
    DayOfWeek dayOfWeek = date.getDayOfWeek();

    if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
      throw new IllegalArgumentException("The date cannot be weekends!");
    }
  }

  /**
   * Checks the string.
   *
   * @param str the string passed
   * @throws IllegalArgumentException invalid string
   */
  static void checkString(String str) throws IllegalArgumentException {
    if (str == null || str.isEmpty()) {
      throw new IllegalArgumentException("The portfolio name shouldn't be empty or null");
    }

    if (str.contains(" ")) {
      throw new IllegalArgumentException("The portfolio name cannot contain space!");
    }
  }


  /**
   * Converts date to string.
   *
   * @param date takes the date parameter
   * @return the string format of the date
   */
  static String date2String(Date date) {
    if (date == null) {
      return "";
    }

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    return format.format(date);
  }
}
