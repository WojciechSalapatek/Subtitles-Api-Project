package models.shifters;

import models.exceptions.BadFormatException;
import models.exceptions.EndBeforeStartException;
import models.exceptions.NegativeFrameAfterShiftException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;

public class SubRipTimeParser {
    private SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss,SSS");
    private Calendar cal = Calendar.getInstance();
    private Date d = null;
    public long parseStringToMillis(String time){
        try {
            d = df.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal.setTime(d);
        return cal.getTimeInMillis()+3600000;

    }

    public String parseMillisToString(long millis){
        return df.format(millis - 3600000);
    }
}
