package com.bootdo.common.quartz.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author  dengzhangkun
 * cron与date互转工具类
 */
public class CronUtil {

    private static final String TRANS_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static final String TRANS_CRON_FORMAT_ONCE = "ss mm HH dd MM ? yyyy";

    private static final String TRANS_CRON_FORMAT_WEEK = "ss mm HH ? * -- *";

    public static void main(String[] args) throws Exception {

//         String result = getCronByOnce("2019-05-23 09:49:50");
//        String result = getCronToDate("50 49 09 23 05 ? 2019");
        String result = getCron("4,5", "2019-05-23 10:25:00");
        System.out.println(result);
    }

    /**
     * 指定具体年月日时分秒执行
     */
    public static String getCronByOnce(String dateStr) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(TRANS_DATE_FORMAT);
        Date parse = format.parse(dateStr);
        return fmtDateToStr(parse, TRANS_CRON_FORMAT_ONCE);
    }

    /**
     * 指定周执行<br/>
     * 注意：周的格式：1，2 或者 1-2<br/>
     * 前端实现时，请补上yyyy-mm-dd
     */
    public static String getCron(String period, String startDateStr) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(TRANS_DATE_FORMAT);
        Date startDate = format.parse(startDateStr);
        StringBuffer cronStringBuffer = new StringBuffer();
        StringBuffer sb = new StringBuffer(1024);
        //","分隔时,各数值加1
        if (period.indexOf(",") > 0) {
            String[] s = period.split(",");
            for (int i = 0; i < s.length; i++) {
                int v = Integer.valueOf(s[i]) + 1;
                sb.append(v);
                if (i < s.length - 1) {
                    sb.append(",");
                }
            }
            period = sb.toString();
        }

        //"-"分隔时,各数值加1
        if (period.indexOf("-") > 0) {
            String[] s = period.split("-");
            for (int i = 0; i < s.length; i++) {
                int v = Integer.valueOf(s[i]) + 1;
                sb.append(v);
                if (i < s.length - 1) {
                    sb.append("-");
                }
            }
            period = sb.toString();
        }


        String startDateCron = fmtDateToStr(startDate, TRANS_CRON_FORMAT_WEEK).trim();
        cronStringBuffer.append(startDateCron.replaceAll("--", period));

        return cronStringBuffer.toString();
    }


    /**
     * 转换cron为date
     * @param cron
     * @return
     */
    public static String getCronToDate(String cron) {
        String format = null;
        StringBuffer stringBuffer = new StringBuffer(cron);
//        int lastIndexOf = stringBuffer.lastIndexOf("/");
//        stringBuffer.deleteCharAt(lastIndexOf);
//        stringBuffer.deleteCharAt(lastIndexOf);
        String dateFormat = "ss mm HH dd MM ? yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        try {
            Date date = sdf.parse(stringBuffer.toString());
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            format = sdf.format(date);
        } catch (Exception e) {
            return null;
        }
        return format;
    }

    /**
     * 格式转换 日期-字符串
     */
    public static String fmtDateToStr(Date date, String dtFormat) {
        if (date == null)
            return "";
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(dtFormat);
            return dateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}