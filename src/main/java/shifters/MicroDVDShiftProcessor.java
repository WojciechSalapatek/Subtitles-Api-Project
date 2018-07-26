package shifters;

import exceptions.BadFormatException;
import exceptions.EndBeforeStartException;
import exceptions.NegativeFrameAfterShiftException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MicroDVDShiftProcessor implements ShiftProcessor {

    private Pattern pattern = Pattern.compile("\\{(\\d+)\\}\\{(\\d+)\\}(.+)");

    public String shiftFrame(String frame, int offset, int startingLineNumber) {
        Matcher matcher;
        int start, newStart, end, newEnd;
        matcher = pattern.matcher(frame);
        if(!matcher.matches()) throw new BadFormatException(startingLineNumber);

        start = Integer.parseInt(matcher.group(1));
        end = Integer.parseInt(matcher.group(2));
        if(start > end) throw new EndBeforeStartException(startingLineNumber);

        newStart = start + offset;
        newEnd = end + offset;

        if(newStart < 0) throw new NegativeFrameAfterShiftException(startingLineNumber);

        return "{" + Integer.toString(newStart) + "}{" + Integer.toString(newEnd) + "}" + matcher.group(3);
    }
}
