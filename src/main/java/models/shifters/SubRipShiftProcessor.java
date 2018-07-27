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
    private SubRipTimeParser parser;

    public SubRipShiftProcessor() {
        parser = new SubRipTimeParser();
    }

    public String shiftFrame(String frame, int offset, int startingLineNumber){
        Matcher matcher;
        long start, end;
        matcher = pattern.matcher(frame);
        if(!matcher.matches()) throw new BadFormatException(startingLineNumber);
        start = parser.parseStringToMillis(matcher.group(2));
        end = parser.parseStringToMillis(matcher.group(3));

        if(offset<0 && start < Math.abs(offset)) throw new NegativeFrameAfterShiftException(startingLineNumber + 1);
        if(end < start) throw new EndBeforeStartException(startingLineNumber + 1);

        return matcher.group(1) + "\n" +
                parser.parseMillisToString(start + offset) + " --> " + parser.parseMillisToString(end + offset) + "\n" +
                matcher.group(4) +
                "\n";
    }
}

