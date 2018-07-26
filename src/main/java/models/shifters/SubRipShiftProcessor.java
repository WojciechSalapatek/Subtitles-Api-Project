package models.shifters;
import models.exceptions.BadFormatException;
import models.exceptions.EndBeforeStartException;
import models.exceptions.NegativeFrameAfterShiftException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SubRipShiftProcessor implements ShiftProcessor {
    private Pattern pattern = Pattern.compile("(\\d+)\\n(\\d{2}:\\d{2}:\\d{2},\\d{3}) --> (\\d{2}:\\d{2}:\\d{2},\\d{3})\\n((?:.+\\n)+)\\n");

    public String shiftFrame(String frame, int offset, int startingLineNumber){
        Matcher matcher;
        String start, newStart, end, newEnd;
        matcher = pattern.matcher(frame);
        if(!matcher.matches()) throw new BadFormatException(startingLineNumber);
        start = matcher.group(2);
        end = matcher.group(3);

        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss,SSS");
        Calendar cal = Calendar.getInstance();
        Date d = null;

        try {
            d = df.parse(start);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal.setTime(d);
        long startInMillis = cal.getTimeInMillis()+3600000;
        if(offset<0 && startInMillis < Math.abs(offset)) throw new NegativeFrameAfterShiftException(startingLineNumber + 1);
        cal.add(Calendar.MILLISECOND, offset);
        newStart = df.format(cal.getTime());

        try {
            d = df.parse(end);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        cal.setTime(d);
        if((cal.getTimeInMillis() + 3600000) < startInMillis) throw new EndBeforeStartException(startingLineNumber + 1);
        cal.add(Calendar.MILLISECOND,offset);
        newEnd = df.format(cal.getTime());

        return matcher.group(1) + "\n" +
                newStart + " --> " + newEnd + "\n" +
                matcher.group(4) +
                "\n";
    }
}

