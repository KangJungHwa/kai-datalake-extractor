package com.kai.smart.platform.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;

public class TimeUtil {

    public static Timestamp getNow() {
        Instant instant = Instant.now();
        Timestamp now = Timestamp.from(instant);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
        return Timestamp.valueOf(df.format(now));
    }

    public static String getNowString() {
        Instant instant = Instant.now();
        Timestamp now = Timestamp.from(instant);
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        return df.format(now);
    }

}
