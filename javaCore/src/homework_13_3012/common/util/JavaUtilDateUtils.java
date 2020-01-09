package homework_13_3012.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class JavaUtilDateUtils {

  private static final String PATTERN = "dd.MM.yyyy";

  private JavaUtilDateUtils(){

  }

  public static Date valueOf(String dateStr, String pattern) throws ParseException {
    DateFormat dateFormat = new SimpleDateFormat(pattern);
    return dateFormat.parse(dateStr);
  }

  public static Date valueOf(String dateStr) throws ParseException {
    return valueOf(dateStr, PATTERN);
  }

}
