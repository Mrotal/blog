package com.jiem.blog.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {


    /**
     * 转换时间为字符
     * @param date
     * @return
     */
    public static String convertDate2String(Date date) {
        if (date == null) {
            return "";
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
}
