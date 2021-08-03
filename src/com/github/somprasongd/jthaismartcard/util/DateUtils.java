/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.somprasongd.jthaismartcard.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sompr
 */
public class DateUtils {

    private static final Logger LOG = Logger.getLogger(DateUtils.class.getName());

    public static Locale TH = new Locale("th", "TH");
    public static Locale EN = new Locale("en", "US");

    public static String yyyymmddToFormattedFullThaiDate(String yyyyMMdd) {
        SimpleDateFormat from = new SimpleDateFormat("yyyyMMdd", TH);
        SimpleDateFormat to = new SimpleDateFormat("d MMMM yyyy", TH);
        Date date;
        try {
            date = from.parse(yyyyMMdd.trim());
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
        return to.format(date);
    }

    public static String yyyymmddToFormattedMediumThaiDate(String yyyyMMdd) {
        SimpleDateFormat from = new SimpleDateFormat("yyyyMMdd", TH);
        SimpleDateFormat to = new SimpleDateFormat("d MMM yyyy", TH);
        Date date;
        try {
            date = from.parse(yyyyMMdd.trim());
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
        return to.format(date);
    }

    public static String yyyymmddToFormattedMediumThaiDateOrEmpty(String yyyyMMdd) {
        try {
            return yyyymmddToFormattedMediumThaiDate(yyyyMMdd);
        } catch (Exception e) {
        }
        return "";
    }

    public static String yyyymmddCristToFormattedFullThaiDate(String yyyyMMdd) {
        SimpleDateFormat from = new SimpleDateFormat("yyyyMMdd", EN);
        SimpleDateFormat to = new SimpleDateFormat("d MMMM yyyy", TH);
        Date date;
        try {
            date = from.parse(yyyyMMdd.trim());
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
        return to.format(date);
    }

    public static String yyyymmddToFormattedFullThaiDateOrEmpty(String yyyyMMdd) {
        try {
            yyyyMMdd = yyyyMMdd.trim();
            if (yyyyMMdd.endsWith("0000")) {
                return yyyyMMdd.substring(0, 4);
            }
            if (yyyyMMdd.endsWith("00")) {
                SimpleDateFormat from = new SimpleDateFormat("yyyyMM", EN);
                SimpleDateFormat to = new SimpleDateFormat("MMMM yyyy", TH);
                Date date = null;
                try {
                    date = from.parse(yyyyMMdd.substring(0, 6));
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
                return to.format(date);
            }
            return yyyymmddToFormattedFullThaiDate(yyyyMMdd);
        } catch (Exception ex) {
        }
        return "";
    }

    public static String yyyymmddToFormattedFullThaiDateOrNull(String yyyyMMdd) {
        try {
            yyyyMMdd = yyyyMMdd.trim();
            if (yyyyMMdd.endsWith("0000")) {
                return yyyyMMdd.substring(0, 4);
            }
            if (yyyyMMdd.endsWith("00")) {
                SimpleDateFormat from = new SimpleDateFormat("yyyyMM", EN);
                SimpleDateFormat to = new SimpleDateFormat("MMMM yyyy", TH);
                Date date = null;
                try {
                    date = from.parse(yyyyMMdd.substring(0, 6));
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
                return to.format(date);
            }
            return yyyymmddToFormattedFullThaiDate(yyyyMMdd);
        } catch (Exception ex) {
        }
        return null;
    }

    public static String formatShortThaiDateTime(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy  เวลา HH.mm น.", TH);
        return df.format(date);
    }

    public static String formatThaiDateTime(Date date, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format, TH);
        return df.format(date);
    }

    public static String formatFullThaiDateTime(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("dd MMMM yyyy  เวลา HH.mm", TH);
        return df.format(date);
    }

    public static Date todate(String yyyyMMdd) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd", TH);
        try {
            return df.parse(yyyyMMdd);
        } catch (ParseException ex) {
        }
        return null;
    }

    public static int getAgeYear(String yyyyMMdd) {
        int age = 0;
        try {
            if (yyyyMMdd == null || yyyyMMdd.isEmpty()) {
                return 0;
            }
            Date birthDate = new SimpleDateFormat("yyyyMMdd", TH).parse(yyyyMMdd);
            Calendar now = Calendar.getInstance();
            Calendar dob = Calendar.getInstance();
            dob.setTime(birthDate);
            if (dob.after(now)) {
                throw new IllegalArgumentException("Can't be born in the future");
            }
            int year1 = now.get(Calendar.YEAR);
            int year2 = dob.get(Calendar.YEAR);
            age = year1 - year2;
            int month1 = now.get(Calendar.MONTH) + 1;
            int month2 = dob.get(Calendar.MONTH) + 1;
            if (month2 > month1) {
                age--;
            } else if (month1 == month2) {
                int day1 = now.get(Calendar.DAY_OF_MONTH);
                int day2 = dob.get(Calendar.DAY_OF_MONTH);
                if (day2 > day1) {
                    age--;
                }
            }
        } catch (ParseException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return age;

    }

    public static int getAgeMonth(String yyyyMMdd) {
        int month = 0;
        try {
            if (yyyyMMdd == null || yyyyMMdd.isEmpty()) {
                return 0;
            }
            Date birthDate = new SimpleDateFormat("yyyyMMdd", TH).parse(yyyyMMdd);
            Calendar now = Calendar.getInstance();
            Calendar dob = Calendar.getInstance();
            dob.setTime(birthDate);
            if (dob.after(now)) {
                throw new IllegalArgumentException("Can't be born in the future");
            }
            int year1 = now.get(Calendar.YEAR);
            int year2 = dob.get(Calendar.YEAR);
            int year = year1 - year2;
            int month1 = now.get(Calendar.MONTH) + 1;
            int month2 = dob.get(Calendar.MONTH) + 1;
            int day1 = now.get(Calendar.DAY_OF_MONTH);
            int day2 = dob.get(Calendar.DAY_OF_MONTH);
            int day = day1 - day2;
            month = month1 - month2;
            if (month < 0) {
                month = month1 - month2 + 12;
                year = year - 1;
            }
            if (month == 0) {
                if (day < 0) {
                    month = month1 - 1;
                }
            }
        } catch (ParseException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return month;
    }
}
