package com.dzg.project.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

/**
 * 比较老的时间处理方法
 */
public final class TimeUtils {

  private TimeUtils() {
  }

  private static final Logger logger = LoggerFactory.getLogger(TimeUtils.class);

  /**
   * 格式化日期和时间
   *
   * @param dateTime
   * @param formatString
   */
  public static String formatTime2String(Date dateTime, String formatString) {
    SimpleDateFormat sdf = new SimpleDateFormat(formatString);
    return sdf.format(dateTime);
  }

  /**
   * 获取前n月的第一天
   *
   * @param n
   */
  public static Date getFirstDayOfMonth(int n) {
    // 获取前月的第一天
    Calendar cal = Calendar.getInstance();// 获取当前日期
    cal.add(Calendar.MONTH, -n);
    cal.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    return cal.getTime();
  }

  /**
   * 将时间戳转换成日期和时间
   *
   * @param timestamp
   */
  public static String timeStamp2DateTime(long timestamp) {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String dateTime = format.format(timestamp);
    return dateTime;
  }

  /**
   * 将日期和时间转换成时间戳
   *
   * @param dateTime
   * @return
   * @throws ParseException
   */
  public static long dateTime2Timestamp(String dateTime) throws ParseException {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = format.parse(dateTime);
    return date.getTime();
  }

  public static long dateTime2TimestampForLog(String dateTime) throws ParseException {
    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = format.parse(dateTime);
    return date.getTime();
  }

  public static long dateTimeToLong(String dateTime) throws ParseException {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+08:00");
    Date date = format.parse(dateTime);
    return date.getTime();
  }

  public static long dateTimeToLongS(String dateTime) throws ParseException {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
    Date date = format.parse(dateTime);
    return date.getTime();
  }

  /**
   * 将日期和时间转换成时间戳
   *
   * @param timestamp
   * @return
   * @throws ParseException
   */
  public static Date timeStamp2Date(long timestamp) throws ParseException {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = format.parse(timeStamp2DateTime(timestamp));
    return date;
  }

  /**
   * 字符串转换成日期
   *
   * @param dateString
   * @return
   */
  public static Date stringToDate(String dateString) {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    Date date = null;
    try {
      if (dateString != null && dateString.length() != 0) {
        date = format.parse(dateString);
      }
    } catch (ParseException e) {
      logger.error(e.getMessage());
    }
    return date;
  }

  /**
   * 字符串转换成日期
   *
   * @param dateString
   * @return
   */
  public static Date stringToDateTime(String dateString) {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = null;
    try {
      if (dateString != null && dateString.length() != 0) {
        date = format.parse(dateString);
      }
    } catch (ParseException e) {
      logger.error(e.getMessage());
    }
    return date;
  }

  public static Date stringToDateTimeSimple(String dateString) {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    Date date = null;
    try {
      if (dateString != null && dateString.length() != 0) {
        date = format.parse(dateString);
      }
    } catch (ParseException e) {
      logger.error(e.getMessage());
    }
    return date;
  }

  public static String dateToStringTimeSimple(Date dateTime) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    return sdf.format(dateTime);
  }

  /**
   * 格式化日期和时间（特定日期格式）
   *
   * @param dateTime
   * @return
   */
  public static String formatTime(Date dateTime) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return sdf.format(dateTime);
  }

  public static String formatTimeShort(Date dateTime) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
    return sdf.format(dateTime);
  }

  /**
   * 月份转换
   *
   * @param month
   * @return
   */
  public static Date string2Month(String month) {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
    Date date = null;
    try {
      date = format.parse(month);
    } catch (ParseException e) {
      logger.error(e.getMessage());
    }
    return date;
  }

  /**
   * 保留小数点后两位的小数
   *
   * @param value
   * @return
   */
  public static double round2(double value) {
    return (double) (Math.round(value * 100) / 100.0);
  }

  /**
   * 增加几分钟
   *
   * @param date
   * @param minute
   * @return
   */
  public static Date addMinuteForDate(Date date, int minute) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.MINUTE, minute);
    return calendar.getTime();
  }

  /**
   * 增加几个小时
   *
   * @author DZG
   * @since 2017年3月20日
   */
  public static Date addHourForDate(Date date, int hour) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.HOUR, hour);
    return calendar.getTime();
  }

  /**
   * 增加几个月
   *
   * @param date
   * @return
   */
  public static Date addMonthForDate(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.MONTH, 1);
    return calendar.getTime();
  }

  /**
   * 增加月份
   *
   * @since 2017年7月19日
   */
  public static Date addMonthForDate(Date date, int month) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.MONTH, 1);
    return calendar.getTime();
  }

  /**
   * md5加密算法
   *
   * @param pwd
   * @return
   */
  public final static String md5(String pwd) {
    // 用于加密的字符
    char md5String[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    try {
      // 使用平台的默认字符集将此 String 编码为 byte序列，并将结果存储到一个新的 byte数组中
      byte[] btInput = pwd.getBytes();

      // 获得指定摘要算法的 MessageDigest对象，此处为MD5
      // MessageDigest类为应用程序提供信息摘要算法的功能，如 MD5 或 SHA 算法。
      // 信息摘要是安全的单向哈希函数，它接收任意大小的数据，并输出固定长度的哈希值。
      MessageDigest mdInst = MessageDigest.getInstance("MD5");

      // MessageDigest对象通过使用 update方法处理数据， 使用指定的byte数组更新摘要
      mdInst.update(btInput);

      // 摘要更新之后，通过调用digest（）执行哈希计算，获得密文
      byte[] md = mdInst.digest();

      // 把密文转换成十六进制的字符串形式
      int j = md.length;
      char str[] = new char[j * 2];
      int k = 0;
      for (int i = 0; i < j; i++) { // i = 0
        byte byte0 = md[i]; // 95
        str[k++] = md5String[byte0 >>> 4 & 0xf]; // 5
        str[k++] = md5String[byte0 & 0xf]; // F
      }

      // 返回经过加密后的字符串
      return new String(str);
    } catch (Exception e) {
      logger.error(e.getMessage());
      return null;
    }
  }

  /**
   * 计算使用时间
   *
   * @param createDate
   * @return
   */
  public static String dateToUsed(Date createDate) {
    Date current = new Date();
    long elapse = current.getTime() - createDate.getTime();
    long day = elapse / (24 * 60 * 60 * 1000);
    long hour = (elapse / (60 * 60 * 1000) - day * 24);
    long min = ((elapse / (60 * 1000)) - day * 24 * 60 - hour * 60);
    if (day > 0) {
      return day + " 天";
    } else if (hour > 0) {
      return hour + " 小时";
    } else if (min > 0) {
      return min + " 分钟";
    } else {
      return "<１分钟";
    }
  }

  /**
   * 计算使用时间
   *
   * @param createDate
   * @return
   */
  public static String timeToUsed(Date createDate) {
    Date currnt = new Date();
    double elapse = currnt.getTime() - createDate.getTime();
    return (long) Math.ceil(elapse / (60 * 1000 * 60 * 24)) + "天";
  }

  /**
   * 计算使用时间
   *
   * @param createDate
   * @param endDate
   * @return
   */
  public static String timeToUsed(Date createDate, Date endDate) {
    double elapse = endDate.getTime() - createDate.getTime();
    return (long) Math.ceil(elapse / (60 * 1000 * 60 * 24)) + "天";
  }

  /**
   * 计算时间间隔
   *
   * @param startTime
   * @param endTime
   * @return
   */
  public static int timeElapse(Date startTime, Date endTime) {
    long elapse = endTime.getTime() - startTime.getTime();
    int elapseInt = 0;
    if (elapse > 0) {
      elapseInt = (int) elapse / 1000;
    }
    return elapseInt;
  }

  /**
   * 使用多长时间（分钟）
   *
   * @param createDate
   * @param endDate
   * @return
   */
  public static long timeToUsedMin(Date createDate, Date endDate) {
    long elapse = endDate.getTime() - createDate.getTime();
    return elapse / (60 * 1000);
  }

  /**
   * 使用多少天
   *
   * @param createDate
   * @param endDate
   * @return
   */
  public static long timeToUsedDay(Date createDate, Date endDate) {
    double elapse = endDate.getTime() - createDate.getTime();
    return (long) Math.ceil(elapse / (60 * 1000 * 60 * 24));
  }

  public static int timeDays(Date createDate, Date endDate) {
    double elapse = endDate.getTime() - createDate.getTime();
    return (int) Math.ceil(elapse / (60 * 1000 * 60 * 24));
  }

  /**
   * 日期加和
   *
   * @param currentDay
   * @param day
   * @return
   */
  public static Date daySub(int currentDay, int day) {
    Calendar calendar = Calendar.getInstance();
    calendar.add(currentDay, day);
    Date startDate = calendar.getTime();
    return startDate;
  }

  /**
   * 日期差
   *
   * @param date1 <Date>
   * @param date2 <Date>
   * @param type  <int> 0月 1年
   * @return
   */
  public static int getDateSpace(Date date1, Date date2, int type) {
    int result = 0;
    Calendar c1 = Calendar.getInstance();
    Calendar c2 = Calendar.getInstance();
    c1.setTime(date1);
    c2.setTime(date2);
    if (type == 0) {
      result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH)
              + (c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR)) * 12;
    } else if (type == 1) {
      result = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
    }
    return result == 0 ? 1 : Math.abs(result);
  }

  /**
   * 日期加天
   *
   * @param date <Date>
   * @param day  <int>
   * @return
   */
  public static Date addDayForDate(Date date, int day) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.DAY_OF_MONTH, day);
    return calendar.getTime();
  }

  /**
   * 获取当前日期
   *
   * @since 2017年10月10日
   */
  public static Date getCurrentDate() {
    Clock clock = Clock.systemDefaultZone();
    Instant instant = clock.instant();
    Date legacyDate = Date.from(instant); // legacy java.util.Date
    return legacyDate;
  }

  public static Date getNowDate() throws ParseException {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    return df.parse(df.format(new Date()));
  }

  public static String getISO8601Timestamp(Date date) {
    //TimeZone tz = TimeZone.getTimeZone("UTC");
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    //df.setTimeZone(tz);
    String nowAsISO = df.format(date);
    return nowAsISO;
  }

  public static Date getISO8601TimestampStr(String date) {
    //TimeZone tz = TimeZone.getTimeZone("UTC");
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    //df.setTimeZone(tz);
    Date nowAsISO = null;
    try {
      nowAsISO = df.parse(date);
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return nowAsISO;
  }

  public static Date getJiraTimestampStr(String date) {
    //TimeZone tz = TimeZone.getTimeZone("UTC");
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'+08:00'");
    //df.setTimeZone(tz);
    Date nowAsISO = null;
    try {
      nowAsISO = df.parse(date);
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return nowAsISO;
  }

  public static Date getJiraTimestampStr2(String date) {
    //TimeZone tz = TimeZone.getTimeZone("UTC");
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'+0800'");
    //df.setTimeZone(tz);
    Date nowAsISO = null;
    try {
      nowAsISO = df.parse(date);
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return nowAsISO;
  }

  public static double getValueRoundFour(String value) {
    return Math.round((Double.valueOf(value) * 100)) * 0.01;
  }

  public static double getValueRoundTwo(String value) {
    return Math.round((Double.valueOf(value) * 100)) * 0.01;
  }

  public static double getValueRoundFourDouble(double value) {
    return Math.round((value * 100)) * 0.01;
  }

  public static double getValueRoundTwoDouble(double value) {
    return Math.round((value * 100)) * 0.01;
  }

  public static String getValueRoundZero(double value) {
    return Math.round((value * 100)) + "";
  }

  public static String getValueRoundTwo(double value) {
    return String.format("%.2f", value);
  }

  public static String getValueRoundFour(double value) {
    return String.format("%.2f", value);
  }
}
