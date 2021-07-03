package com.evotek.nagakawa_be.utils;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {

    public static final String ISO_8601_PATTERN = "yyyy-MM-dd'T'HH:mm:ssZ";
    public static final String LONG_DATE_PATTERN = "dd/MM/yyyy HH:mm:ss";
    public static final String LONG_TIME_PATTERN = "HH:mm";
    public static final String SHORT_DATE_PATTERN = "dd/MM/yyyy";

    public static final String[] DAY_OF_WEEK = {"CN", "Hai", "Ba", "Tư", "Năm", "Sáu", "Bảy"};
    //	Thứ viết tắt
    public static final String[] DAY_OF_WEEK_ACRONYM = {"CN", "H", "B", "T", "N", "S", "B"};

    private static List<SimpleDateFormat> dateFormats = new ArrayList<SimpleDateFormat>() {
        {
            add(new SimpleDateFormat("MM/dd/yyyy"));
            add(new SimpleDateFormat("dd.MM.yyyy"));
            add(new SimpleDateFormat("dd/MM/yyyy"));
            add(new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a"));
            add(new SimpleDateFormat("dd.MM.yyyy hh:mm:ss a"));
            add(new SimpleDateFormat("dd.MMM.yyyy"));
            add(new SimpleDateFormat("dd-MMM-yyyy"));
        }
    };

    /**
     * So sánh 2 ngày bỏ qua Milliseconds.
     * date1 < dat2 return -1
     * date1 > dat2 return 1
     * date1 = dat2 return 0
     *
     * @param date1
     * @param date2
     * @return int
     */
    public static int compareTo(Date date1, Date date2) {
        return compareTo(date1, date2, false);
    }

    /**
     * @param date1
     * @param date2
     * @param ignoreMilliseconds
     * @return
     */
    public static int compareTo(Date date1, Date date2, boolean ignoreMilliseconds) {

        // Workaround for bug in JDK 1.5.x. This bug is fixed in JDK 1.5.07. See
        // http://bugs.sun.com/bugdatabase/view_bug.do;:YfiG?bug_id=6207898 for
        // more information.

        if ((date1 != null) && (date2 == null)) {
            return -1;
        } else if ((date1 == null) && (date2 != null)) {
            return 1;
        } else if ((date1 == null) && (date2 == null)) {
            return 0;
        }

        long time1 = date1.getTime();
        long time2 = date2.getTime();

        if (ignoreMilliseconds) {
            time1 = time1 / TimeUtil.SECOND;
            time2 = time2 / TimeUtil.SECOND;
        }

        if (time1 == time2) {
            return 0;
        } else if (time1 < time2) {
            return -1;
        } else {
            return 1;
        }
    }

    /**
     * So sánh 2 ngày bỏ qua Milisecond
     * date1 = date2 return true
     * date != date2 return false
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean equals(Date date1, Date date2) {
        if (compareTo(date1, date2) == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean equals(Date date1, Date date2, boolean ignoreMilliseconds) {

        if (!ignoreMilliseconds) {
            return equals(date1, date2);
        }

        long deltaTime = date1.getTime() - date2.getTime();

        if ((deltaTime > -1000) && (deltaTime < 1000)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * So sánh giá trị 2 ngày
     *
     * @param date1
     * @param date2
     * @param ignoreTime (== true xét cả giò, == false không xét giờ)
     * @return 1: date1 > date 2; 0: date1 == date2; -1: date1 < date2
     */
    public static int compare(Date date1, Date date2, boolean ignoreTime) {
        if ((date1 != null) && (date2 == null)) {
            return -1;
        } else if ((date1 == null) && (date2 != null)) {
            return 1;
        } else if ((date1 == null) && (date2 == null)) {
            return 0;
        }

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        if (ignoreTime) {
            cal1.set(Calendar.HOUR_OF_DAY, 0);
            cal1.set(Calendar.MINUTE, 0);
            cal1.set(Calendar.SECOND, 0);
            cal1.set(Calendar.MILLISECOND, 0);


            cal2.set(Calendar.HOUR_OF_DAY, 0);
            cal2.set(Calendar.MINUTE, 0);
            cal2.set(Calendar.SECOND, 0);
            cal2.set(Calendar.MILLISECOND, 0);
        }

        long time1 = cal1.getTime().getTime();
        long time2 = cal2.getTime().getTime();

        if (time1 == time2) {
            return 0;
        } else if (time1 < time2) {
            return -1;
        } else {
            return 1;
        }
    }

    /**
     * Só sánh giá trị 2 ngày bỏ qua thời gian
     *
     * @param date1
     * @param date2
     * @return boolean
     */
    public static boolean equalIgnoreTime(Date date1, Date date2) {
        if (compare(date1, date2, true) == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Tìm khoảng thời gian(số ngày) giữa 2 ngày
     *
     * @param date1
     * @param date2
     * @return int(date1 - date2)
     */
    public static int subDate(Date date1, Date date2) {
        int spaceYear;
        int spaceDay = 0;
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
//		cal1.get(Calendar.DAY_OF_YEAR);
        String date1Str[] = getDate(date1, SHORT_DATE_PATTERN).split("/");
        String date2Str[] = getDate(date2, SHORT_DATE_PATTERN).split("/");

        int yearOfDate1 = Integer.parseInt(date1Str[2]);
        int yearOfDate2 = Integer.parseInt(date2Str[2]);

        if (yearOfDate1 != yearOfDate2) {
            spaceYear = yearOfDate1 - yearOfDate2;
            System.out.println("spaceYear = " + spaceYear);
            int start = yearOfDate1;
            int end = yearOfDate2;
            boolean sign = true;
            if (spaceYear > 0) {
                start = yearOfDate2;
                end = yearOfDate1;
                sign = false;
            }
            cal1.set(Calendar.YEAR, yearOfDate2);
//			cal1.set(Calendar.YEAR, yearOfDate2);
            for (int i = start; i < end; i++) {
                if (isleap(i)) {
                    spaceDay += 366;
                } else {
                    spaceDay += 365;
                }
            }
            if (sign) {
                spaceDay = -spaceDay;
            }
            spaceDay += cal1.get(Calendar.DAY_OF_YEAR) - cal2.get(Calendar.DAY_OF_YEAR);
            return spaceDay;
        } else {
            return cal1.get(Calendar.DAY_OF_YEAR) - cal2.get(Calendar.DAY_OF_YEAR);
        }
//		return 1;
    }


    /**
     * Định dạng ngày kiểu dd/MM/yyyy
     *
     * @param date
     * @return Date
     */
    public static Date formatDateDate(Date date) {
        Date dt;
        try {
            if (date != null) {
                SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
                String strd = formater.format(date);
                dt = formater.parse(strd);
                return dt;
            }
        } catch (ParseException ex) {
            return null;
        }
        return null;

    }

    /**
     * Định dạng ngày kiểu dd/MM/yyyy
     *
     * @param date
     * @return String
     */
    public static String formatDateString(Date date) {
        if (date != null) {
            SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
            return formater.format(date);
        } else {
            return "";
        }

    }

    /**
     * Định dạng timesramp kiểu: dd/MM/yyyy HH:mm:ss
     *
     * @param date
     * @return Timestamp
     */
//    public static Timestamp formatDateToTimeStamp(Date date) {
//        Date dt;
//        try {
//            if (date != null) {
//                SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//                String timestampStr = formater.format(date.getTime());
//                dt = formater.parse(timestampStr);
//                return new Timestamp(dt.getTime());
//            }
//        } catch (Exception ex) {
//            Messagebox.show(Labels.getLabel("MesErrorParsingTimeStamp"), "error", 1, Messagebox.ERROR);
//        }
//        return null;
//    }

    /**
     * Định dạng date kiểu: dd/MM/yyyy HH:mm:ss
     *
     * @param date
     * @return String
     */
    public static String formatLongDate(Date date) {
        return getDate(date, LONG_DATE_PATTERN);
    }

    /**
     * Định dạng date kiểu: dd/MM/yyyy
     *
     * @param date
     * @return String
     */
    public static String formatShortDate(Date date) {
        return getDate(date, SHORT_DATE_PATTERN);
    }

    /**
     * Định dạng timesramp kiểu: dd/MM/yyyy HH:mm:ss
     *
     * @param date
     * @return String
     */
    public static String formatTimeStampString(Date date) {
        String timestampStr = null;
        try {
            if (date != null) {
                SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                timestampStr = formater.format(date.getTime());
            }
        } catch (Exception ex) {
            System.out.println(ex);
//            Messagebox.show(Labels.getLabel("MesErrorParsingTimeStamp"), Labels.getLabel("Error"), 1, Messagebox.ERROR);
        }
        return timestampStr;
    }

    /**
     * Lấy thời gian hiện tạo theo local
     *
     * @param pattern
     * @param locale
     * @return String
     */
    public static String getCurrentDate(String pattern, Locale locale) {
        return getDate(new Date(), pattern, locale);
    }

    public static String getCurrentDate(String pattern, Locale locale, TimeZone timeZone) {

        return getDate(new Date(), pattern, locale, timeZone);
    }

    public static String getDate(Date date, String pattern) {
        if (date == null) {
            return null;
        }

        Format dateFormat = FastDateFormatFactoryUtil.getSimpleDateFormat(pattern);

        return dateFormat.format(date);
    }

    public static Date getDate(Date date) {
        if (date == null) {
            return null;
        }

        Format dateFormat = FastDateFormatFactoryUtil.getSimpleDateFormat(DateUtil.SHORT_DATE_PATTERN);

        String dateStr[] = dateFormat.format(date).split("/");
        Calendar calendar = Calendar.getInstance();
//		System.out.println("getDate: " + dateStr[0] + "/" + (GetterUtil.getInteger(dateStr[1])-1) + "/" + dateStr[2]);
        calendar.set(Calendar.DAY_OF_MONTH, GetterUtil.getInteger(dateStr[0]));
        calendar.set(Calendar.MONTH, GetterUtil.getInteger(dateStr[1]) - 1);
        calendar.set(Calendar.YEAR, GetterUtil.getInteger(dateStr[2]));

        return calendar.getTime();
    }

    public static String getDate(Date date, String pattern, Locale locale) {
        if (date == null) {
            return null;
        }

        Format dateFormat = FastDateFormatFactoryUtil.getSimpleDateFormat(pattern, locale);

        return dateFormat.format(date);
    }

    public static String getDate(Date date, String pattern, Locale locale, TimeZone timeZone) {
        if (date == null) {
            return null;
        }

        Format dateFormat = FastDateFormatFactoryUtil.getSimpleDateFormat(pattern, locale, timeZone);

        return dateFormat.format(date);
    }

    /**
     * Trả về ngày sau ngày nhập 1 khoảng thời gian
     *
     * @param date:     Ngày nhập
     * @param afterDay: Khonagr thời gian lùi tiến
     * @return Date
     */
    public static Date getDateAfter(Date date, int afterDay) {
        Calendar cal = Calendar.getInstance();

        cal.setTime(date);

        cal.add(Calendar.DAY_OF_YEAR, afterDay);

        return cal.getTime();
    }

    /**
     * Trả về ngày sau khoảng thời gian so với ngày hiện tại
     *
     * @param afterDay: Khoảng thời gian tiến
     * @return Date
     */
    public static Date getDateAfter(int afterDay) {
        Calendar cal = Calendar.getInstance();

        cal.add(Calendar.DAY_OF_YEAR, afterDay);

        return cal.getTime();
    }

    /**
     * Trả về ngày trước 1 khoẳng thời gian
     *
     * @param date:      Ngày nhập
     * @param beforeDay: Số ngày lùi
     * @return Date
     */
    public static Date getDateBefore(Date date, int beforeDay) {
        Calendar cal = Calendar.getInstance();

        cal.setTime(date);

        cal.add(Calendar.DAY_OF_YEAR, -beforeDay);

        return cal.getTime();
    }

    /**
     * Trả về ngày trước 1 khoảng thời gian so bới ngày hiện tại
     *
     * @param beforeDay: Số ngày lùi
     * @return Date
     */
    public static Date getDateBefore(int beforeDay) {
        Calendar cal = Calendar.getInstance();

        cal.add(Calendar.DAY_OF_YEAR, -beforeDay);

        return cal.getTime();
    }
//
//    /**
//     * Số lượng ngày ở giữa 2 ngày
//     *
//     * @param startDate
//     * @param endDate
//     * @param timeZone
//     * @return int
//     */
//    public static int getDaysBetween(Date startDate, Date endDate, TimeZone timeZone) {
//
//        int offset = timeZone.getRawOffset();
//
//        Calendar startCal = Calendar.getInstance(timeZone);
//
//        startCal.setTime(startDate);
//        startCal.add(Calendar.MILLISECOND, offset);
//
//        Calendar endCal = Calendar.getInstance(timeZone);
//
//        endCal.setTime(endDate);
//        endCal.add(Calendar.MILLISECOND, offset);
//
//        int daysBetween = 0;
//
//        while (CalendarUtil.beforeByDay(startCal.getTime(), endCal.getTime())) {
//            startCal.add(Calendar.DAY_OF_MONTH, 1);
//
//            daysBetween++;
//        }
//
//        return daysBetween;
//    }

//    public static DateFormat getISO8601Format() {
//        return DateFormatFactoryUtil.getSimpleDateFormat(ISO_8601_PATTERN);
//    }

//    public static DateFormat getISOFormat() {
//        return getISOFormat(StringPool.BLANK);
//    }

//    public static DateFormat getISOFormat(String text) {
//        String pattern = StringPool.BLANK;
//
//        if (text.length() == 8) {
//            pattern = "yyyyMMdd";
//        } else if (text.length() == 12) {
//            pattern = "yyyyMMddHHmm";
//        } else if (text.length() == 13) {
//            pattern = "yyyyMMdd'T'HHmm";
//        } else if (text.length() == 14) {
//            pattern = "yyyyMMddHHmmss";
//        } else if (text.length() == 15) {
//            pattern = "yyyyMMdd'T'HHmmss";
//        } else if ((text.length() > 8) && (text.charAt(8) == 'T')) {
//            pattern = "yyyyMMdd'T'HHmmssz";
//        } else {
//            pattern = "yyyyMMddHHmmssz";
//        }
//
//        return DateFormatFactoryUtil.getSimpleDateFormat(pattern);
//    }

    public static Date getNextBirthDay(Date birthDay) {
        Calendar calBd = Calendar.getInstance();
        Calendar cal = Calendar.getInstance();

        calBd.setTime(birthDay);

        cal.set(Calendar.MONTH, calBd.get(Calendar.MONTH));
        cal.set(Calendar.DAY_OF_MONTH, calBd.get(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }

//    public static DateFormat getUTCFormat() {
//        return getUTCFormat(StringPool.BLANK);
//    }

//    public static DateFormat getUTCFormat(String text) {
//        String pattern = StringPool.BLANK;
//
//        if (text.length() == 8) {
//            pattern = "yyyyMMdd";
//        } else if (text.length() == 12) {
//            pattern = "yyyyMMddHHmm";
//        } else if (text.length() == 13) {
//            pattern = "yyyyMMdd'T'HHmm";
//        } else if (text.length() == 14) {
//            pattern = "yyyyMMddHHmmss";
//        } else if (text.length() == 15) {
//            pattern = "yyyyMMdd'T'HHmmss";
//        } else {
//            pattern = "yyyyMMdd'T'HHmmssz";
//        }
//
//        return DateFormatFactoryUtil.getSimpleDateFormat(pattern);
//    }

    public static Date newDate() {
        return new Date();
    }

    public static Date newDate(long date) {
        return new Date(date);
    }

    public static long newTime() {
        Date date = new Date();

        return date.getTime();
    }

    /**
     * Trả về ngày trong tuần
     * vd: CN: 1, T2: 2, ...
     *
     * @param date
     * @return số ngày trong tuần
     */
    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * Trả về thứ trong tuần
     *
     * @param date
     * @param isAcronym: true: dạng viết tắt; false: dạng đầy đủ
     * @return String
     */
    public static String getDayOfWeek(Date date, boolean isAcronym) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (isAcronym) {
            return DAY_OF_WEEK_ACRONYM[calendar.get(Calendar.DAY_OF_WEEK) - 1];
        }
        return DAY_OF_WEEK[calendar.get(Calendar.DAY_OF_WEEK) - 1];
    }


    /**
     * Trả về ngày trong tháng
     *
     * @param date
     * @return int
     */
    public static int getDayOfMonth(Date date) {
        String s = getDate(date, SHORT_DATE_PATTERN);
        String[] d = s.split("/");
        return Integer.parseInt(d[0]);
    }


    /**
     * Lấy tháng
     *
     * @param date
     * @return int
     */
    public static int getMonth(Date date) {
        String s = getDate(date, SHORT_DATE_PATTERN);
        String[] d = s.split("/");
        return Integer.parseInt(d[1]);
    }

    /**
     * Lấy năm
     *
     * @param date
     * @return int
     */
    public static int getYear(Date date) {
        String s = getDate(date, SHORT_DATE_PATTERN);
        String[] d = s.split("/");
        return Integer.parseInt(d[2]);
    }

    /**
     * Lấy số ngày của 1 tháng
     *
     * @param date
     * @return int
     */
    public static int getTotalDayOfMonth(Date date) {
        String dateStr[] = formatDateString(date).split("/");
        switch (Integer.parseInt(dateStr[1])) {
            case 2:
                if (isleap(Integer.parseInt(dateStr[2]))) {
                    return 29;
                } else {
                    return 28;
                }
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            default:
                return 31;
        }
    }


    /**
     * Kiểm tra năm nhuận
     *
     * @param year
     * @return boolean
     */
    public static boolean isleap(int year) {
        if (year % 100 != 0 && year % 4 == 0) {
            return true;
        } else if (year % 400 == 0) {
            return true;
        }
        return false;
    }

    /**
     * Kiểm tra năm nhuận
     *
     * @param date
     * @return boolean
     */
    public static boolean isleap(Date date) {
        String d[] = getDate(date, SHORT_DATE_PATTERN).split("/");
        int year = Integer.parseInt(d[2]);
        if (year % 100 != 0 && year % 4 == 0) {
            return true;
        } else if (year % 400 == 0) {
            return true;
        }
        return false;
    }

    /**
     * Trả về ngày cuối cùng của tháng
     *
     * @param month
     * @param year
     * @return Date
     */
    public static Date getDateEnd(int month, int year) {
        Calendar calendar = Calendar.getInstance();
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                calendar.set(year, month - 1, 31);

                break;
            case 2:
                if (isleap(year)) {
                    calendar.set(year, month - 1, 29);
                } else {
                    calendar.set(year, month - 1, 28);

                }
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                calendar.set(year, month - 1, 30);
                break;
            default:
                return null;

        }
        return calendar.getTime();
    }

    /**
     * Trả về ngày cuối cùng của tháng
     *
     * @param date (là 1 ngày bất kỳ của tháng)
     * @return date
     */
    public static Date getDateEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        int month = getMonth(date);
        int year = getYear(date);
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                calendar.set(year, month - 1, 31);

                break;
            case 2:
                if (isleap(year)) {
                    calendar.set(year, month - 1, 29);
                } else {
                    calendar.set(year, month - 1, 28);

                }
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                calendar.set(year, month - 1, 30);
                break;
            default:
                return null;

        }
        return calendar.getTime();
    }

    /**
     * Đổi sang ngày mới, giữ nguyên tháng và năm
     *
     * @param date:   Ngày cần dổi
     * @param newDay: ngày mới
     * @return Date
     */
    public static Date changeDay(Date date, int newDay) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DATE, newDay);
        return cal.getTime();
    }

    public static Date stringToDate(String dateStr) {
//        Date date = null;
//        if (null == dateStr || dateStr.equalsIgnoreCase("")) {
//            return null;
//        }
//        for(SimpleDateFormat format : dateFormats){
//            try {
//                format.setLenient(false);
//                date = format.parse(dateStr);
//            } catch (Exception ex) {
//                // Shhh.. try other formats
//            }
//            if (date != null) {
//                break;
//
//            }
//        }
//        return date;

//        String dates[] = dateStr.split("/");
////		if(dateStr.contains("/")){
////			dates = dateStr.split("/");
////		}
////		else if(dateStr.contains("-")){
////			dates = dateStr.split("-");
////		}
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.YEAR, Integer.parseInt(dates[2]));
//        calendar.set(Calendar.MONTH, Integer.parseInt(dates[1]) - 1);
//        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dates[0]));

        String dates[] = dateStr.split("-");
//		if(dateStr.contains("/")){
//			dates = dateStr.split("/");
//		}
//		else if(dateStr.contains("-")){
//			dates = dateStr.split("-");
//		}
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.parseInt(dates[0]));
        calendar.set(Calendar.MONTH, Integer.parseInt(dates[1]) - 1);
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dates[2]));

        return calendar.getTime();
    }


//	public static int getBeforeMonth(int beforeDay){
//		Date date = g
//	}

    public static void main(String[] args) {
//		Date date = getNextBirthDay(new Date("05/12/2021"));
//		Date date = changeDay(new Date("05/12/2021"), 22);
//		System.out.println(getDate(date, SHORT_DATE_PATTERN));

//		Date date1 = new Date();
//		Date date2 = new Date("05/23/2021");
////		date1.setTime(0);
//		System.out.println(date1);
//		System.out.println(date2);
//		System.out.println("equal: " + compareTo(date1, date2, false));
//		Calendar cal1 = Calendar.getInstance();
//		cal1.setTime(date1);
////		cal1.set(Calendar.HOUR_OF_DAY, 0);
////		cal1.set(Calendar.MINUTE, 0);
////		cal1.set(Calendar.SECOND, 0);
////		cal1.set(Calendar.MILLISECOND, 0);
//		System.out.println(cal1.getTime());
//		System.out.println("equal: " + equalIgnoreTime(date1, date2));
//

//		Date date = new Date("01/4/2021");
//		Calendar cal1 = Calendar.getInstance();
//		cal1.setTime(date);
//		System.out.println(cal1.get(Calendar.DAY_OF_YEAR));
//		cal1.set(Calendar.YEAR, 2023);
//		System.out.println(cal1.getTime());

//		Date date1 = new Date();
//		Date date2 = new Date("05/23/2019");
////		Calendar cal1 = Calendar.getInstance();
////		cal1.setTime(date1);
////		System.out.println(cal1.get(Calendar.DAY_OF_YEAR));
////		Calendar cal2 = Calendar.getInstance();
////		cal2.setTime(date2);
////		System.out.println(cal2.get(Calendar.DAY_OF_YEAR));
////		System.out.println(cal1.get(Calendar.DAY_OF_YEAR) - cal2.get(Calendar.DAY_OF_YEAR));
//		System.out.println(subDate(date1, date2));

//		String s = "24/02/2021";
//		System.out.println(getDate(stringToDate(s), SHORT_DATE_PATTERN));

        Date date1 = new Date("06/06/2021");
        Date date2 = new Date("06/06/2021");
        int holiday = 1;
        while (DateUtil.getDay(date1) != holiday) {
            date1 = DateUtil.getDateAfter(date1, 1);
        }


//        Calendar calendar = Calendar.getInstance();
//        if (DateUtil.getDaysBetween(date1, date2, calendar.getTimeZone()) % 7 == 0){
//            System.out.println("Yes");
//        }
//        else{
//            System.out.println("Noooooo");
//        }
//
//        System.out.println(getDaysBetween(date2, date1, calendar.getTimeZone()));

    }


}
