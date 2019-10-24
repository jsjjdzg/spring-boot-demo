package com.dzg.project.utils;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 基于Java8的时间处理工具类
 *
 * @author DZG
 * @since: V1.0 2019/9/17
 */
public class Java8TimeUtils implements Comparable<Java8TimeUtils>, Serializable {

  private static final long serialVersionUID = 1L;
  private static final ZoneId zone = ZoneId.systemDefault();
  private static final ZoneOffset zoneOffset = ZoneOffset.UTC;
  private LocalDateTime dt;
  private static final Map<String, DateTimeFormatter> formatters = new ConcurrentHashMap<>();

  //第一部分：构造
  public static Java8TimeUtils now() {
    return fromLocalDateTime(LocalDateTime.now());
  }

  public static Java8TimeUtils yesterday() {
    return now().plusDays(-1);
  }

  public static Java8TimeUtils tomorrow() {
    return now().plusDays(1);
  }

  public static Java8TimeUtils fromLocalDateTime(LocalDateTime dt) {
    Java8TimeUtils d = new Java8TimeUtils();
    d.dt = dt;
    return d;
  }

  public static Java8TimeUtils of(int year, int month, int dayOfMonth, int hour, int minute, int second, int millisecond, int nanoOfSecond) {
    LocalDateTime dt = LocalDateTime.of(year, month, dayOfMonth, hour, minute, millisecond, nanoOfSecond);
    return fromLocalDateTime(dt);
  }

  public static Java8TimeUtils of(int year, int month, int dayOfMonth, int hour, int minute, int second, int millisecond) {
    return of(year, month, dayOfMonth, hour, minute, second, millisecond, 0);
  }

  public static Java8TimeUtils of(int year, int month, int dayOfMonth, int hour, int minute, int second) {
    return of(year, month, dayOfMonth, hour, minute, second, 0, 0);
  }

  public static Java8TimeUtils of(int year, int month, int dayOfMonth, int hour, int minute) {
    return of(year, month, dayOfMonth, hour, minute, 0, 0, 0);
  }

  public static Java8TimeUtils of(int year, int month, int dayOfMonth, int hour) {
    return of(year, month, dayOfMonth, hour, 0, 0, 0, 0);
  }

  public static Java8TimeUtils of(int year, int month, int dayOfMonth) {
    return of(year, month, dayOfMonth, 0, 0, 0, 0, 0);
  }

  public static Java8TimeUtils of(LocalDate localDate, LocalTime localTime) {
    return fromLocalDateTime(localTime.atDate(localDate));
  }

  public static Java8TimeUtils ofInstant(Instant instant) {
    return fromLocalDateTime(LocalDateTime.ofInstant(instant, zone));
  }

  public static Java8TimeUtils ofEpochSecond(long epochSecond) {
    return ofInstant(Instant.ofEpochSecond(epochSecond));
  }

  public static Java8TimeUtils ofEpochMilli(long epochMilli) {
    return ofInstant(Instant.ofEpochMilli(epochMilli));
  }

  public static Java8TimeUtils ofEpochSecond(long epochSecond, int nanoOfSecond) {
    return ofInstant(Instant.ofEpochSecond(epochSecond, nanoOfSecond));
  }

  //第二部分：解析
  public static Java8TimeUtils parseDateTime(String text) {
    return fromLocalDateTime(LocalDateTime.parse(text));
  }

  public static Java8TimeUtils parseDateTime(String text, String pattern) {
    DateTimeFormatter format = findFormatter(pattern);
    return fromLocalDateTime(LocalDateTime.parse(text, format));
  }

  public static Java8TimeUtils parseDate(String text) {
    return fromLocalDateTime(LocalDate.parse(text).atStartOfDay());
  }

  public static Java8TimeUtils parseDate(String text, String pattern) {
    DateTimeFormatter format = findFormatter(pattern);
    return fromLocalDateTime(LocalDate.parse(text, format).atStartOfDay());
  }

  public static Java8TimeUtils parseTime(String text) {
    return fromLocalDateTime(LocalDate.now().atTime(LocalTime.parse(text)));
  }

  public static Java8TimeUtils parseTime(String text, String pattern) {
    DateTimeFormatter format = findFormatter(pattern);
    return fromLocalDateTime(LocalDate.now().atTime(LocalTime.parse(text, format)));
  }

  //第三部分：格式化
  public String formatDate() {
    return dt.toLocalDate().toString();
  }

  public String formatDate(String pattern) {
    DateTimeFormatter format = findFormatter(pattern);
    return dt.toLocalDate().format(format);
  }

  public String formatDateTime() {
    return dt.toString();
  }

  public String formatDateTime(String pattern) {
    DateTimeFormatter format = findFormatter(pattern);
    return dt.format(format);
  }

  public String formatTime() {
    return dt.toLocalTime().toString();
  }

  public String formatTime(String pattern) {
    DateTimeFormatter format = findFormatter(pattern);
    return dt.toLocalTime().format(format);
  }

  //第四部分：转换成java日期
  public LocalDate toLocalDate() {
    return dt.toLocalDate();
  }

  public LocalTime toLocalTime() {
    return dt.toLocalTime();
  }

  public Instant toInstant() {
    return dt.toInstant(zoneOffset);
  }

  public Date toDate() {
    return Date.from(dt.toInstant(zoneOffset));
  }

  public LocalDateTime toLocalDateTime() {
    return dt;
  }

  //第五部分：计算
  public Java8TimeUtils plusDays(int days) {
    return fromLocalDateTime(dt.plusDays(days));
  }

  public Java8TimeUtils plusYears(int years) {
    return fromLocalDateTime(dt.plusYears(years));
  }

  public Java8TimeUtils plusWeeks(int weeks) {
    return fromLocalDateTime(dt.plusWeeks(weeks));
  }

  public Java8TimeUtils plusMonths(int months) {
    return fromLocalDateTime(dt.plusMonths(months));
  }

  public Java8TimeUtils plusHours(int hours) {
    return fromLocalDateTime(dt.plusHours(hours));
  }

  public Java8TimeUtils plusMinutes(int minutes) {
    return fromLocalDateTime(dt.plusMinutes(minutes));
  }

  public Java8TimeUtils plusSeconds(int seconds) {
    return fromLocalDateTime(dt.plusSeconds(seconds));
  }

  public Java8TimeUtils plusNanos(int nanos) {
    return fromLocalDateTime(dt.plusNanos(nanos));
  }

  //第六部分：调整值
  public Java8TimeUtils previousOrSameMonday() {
    return fromLocalDateTime(dt.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)));
  }

  public Java8TimeUtils previousOrSameTuesday() {
    return fromLocalDateTime(dt.with(TemporalAdjusters.previousOrSame(DayOfWeek.TUESDAY)));
  }

  public Java8TimeUtils previousOrSameWendesday() {
    return fromLocalDateTime(dt.with(TemporalAdjusters.previousOrSame(DayOfWeek.WEDNESDAY)));
  }

  public Java8TimeUtils previousOrSameThursday() {
    return fromLocalDateTime(dt.with(TemporalAdjusters.previousOrSame(DayOfWeek.THURSDAY)));
  }

  public Java8TimeUtils previousOrSameFriday() {
    return fromLocalDateTime(dt.with(TemporalAdjusters.previousOrSame(DayOfWeek.FRIDAY)));
  }

  public Java8TimeUtils previousOrSameSaturday() {
    return fromLocalDateTime(dt.with(TemporalAdjusters.previousOrSame(DayOfWeek.SATURDAY)));
  }

  public Java8TimeUtils previousOrSameSunday() {
    return fromLocalDateTime(dt.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY)));
  }

  public Java8TimeUtils previousOrSameWeekDay(DayOfWeek dayOfWeek) {
    return fromLocalDateTime(dt.with(TemporalAdjusters.previousOrSame(dayOfWeek)));
  }

  public Java8TimeUtils withYear(int year) {
    return fromLocalDateTime(dt.withYear(year));
  }

  public Java8TimeUtils withMonth(int month) {
    return fromLocalDateTime(dt.withMonth(month));
  }

  public Java8TimeUtils withDayOfMonth(int dayOfMonth) {
    return fromLocalDateTime(dt.withDayOfMonth(dayOfMonth));
  }

  public Java8TimeUtils withDayOfYear(int dayOfYear) {
    return fromLocalDateTime(dt.withDayOfYear(dayOfYear));
  }

  public Java8TimeUtils withHour(int hour) {
    return fromLocalDateTime(dt.withHour(hour));
  }

  public Java8TimeUtils withMinute(int minute) {
    return fromLocalDateTime(dt.withMinute(minute));
  }

  public Java8TimeUtils withSecond(int second) {
    return fromLocalDateTime(dt.withSecond(second));
  }

  public Java8TimeUtils withNanoOfSecond(int nanoOfSecond) {
    return fromLocalDateTime(dt.withNano(nanoOfSecond));
  }

  //第七部分：时间段
  public long daysBetween(Java8TimeUtils date) {
    return dt.toLocalDate().toEpochDay() - date.dt.toLocalDate().toEpochDay();
  }

  public long secondsBetween(Java8TimeUtils date) {
    return dt.toEpochSecond(zoneOffset) - date.dt.toEpochSecond(zoneOffset);
  }

  //第八部分：比较
  public boolean isBefore(Java8TimeUtils date) {
    return dt.isBefore(date.dt);
  }

  public boolean isAfter(Java8TimeUtils date) {
    return dt.isAfter(date.dt);
  }

  public boolean isEqual(Java8TimeUtils date) {
    return dt.isEqual(date.dt);
  }

  @Override
  public int compareTo(Java8TimeUtils date) {
    return dt.compareTo(date.dt);
  }

  //第九部分：提取值
  public long toEpochSecond() {
    return dt.toEpochSecond(zoneOffset);
  }

  public long toEpochMilli() {
    return dt.toInstant(zoneOffset).toEpochMilli();
  }

  public long getNano() {
    return dt.toInstant(zoneOffset).getNano();
  }
  //第十部分：

  private static DateTimeFormatter findFormatter(String pattern) {
    DateTimeFormatter format = formatters.get(pattern);
    if (format == null) {
      format = DateTimeFormatter.ofPattern(pattern);
      formatters.put(pattern, format);
    }
    return format;
  }
}
