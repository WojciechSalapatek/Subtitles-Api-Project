package models.shifters;

import models.exceptions.BadFormatException;
import models.exceptions.EndBeforeStartException;
import models.exceptions.NegativeFrameAfterShiftException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MicroDVDShiftProcessor implements ShiftProcessor {

    private Pattern pattern = Pattern.compile("\\{(\\d+)\\}\\{(\\d+)\\}(.+)\n");

    /**
     * Shifts frame in MicroDVD  format  by the offset of frames
     * @param frame frame in MicroDVD format
     * @param offset offset that will be used to shift frame
     * @param startingLineNumber number of line in which frame begins in source
     * @return shifted frame
     */
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

        return "{" + Integer.toString(newStart) + "}{" + Integer.toString(newEnd) + "}" + matcher.group(3) + "\n";
    }
}
