package shifters;
import exceptions.BadFormatException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SubRipFormatProcessor implements FormatProcessor {
    private String frame;
    private Pattern pattern = Pattern.compile("(\\d+)\\n(\\d{2}:\\d{2}:\\d{2},\\d{3}) --> (\\d{2}:\\d{2}:\\d{2},\\d{3})\\n((?:.+\\n)+)\\n");
    private Matcher matcher;

    public SubRipFormatProcessor() {
    }

    public boolean matches(){
        matcher = pattern.matcher(frame);
        return matcher.matches();
    }

    public int getFrameNumber() {
        return Integer.parseInt(matcher.group(1));
    }

    public String getStart() {
        return matcher.group(2);
    }

    public String getEnd() {
        return matcher.group(3);
    }

    public String makeDelayedFrame(String newStart, String newEnd) {
        return matcher.group(1) + "\n" +
                newStart + " --> " + newEnd + "\n" +
                matcher.group(4) +
                "\n";
    }

    public void setFrame(String frame, int startingLinenumber) {
        this.frame = frame;
        matcher = pattern.matcher(frame);
        if(!matches()) throw new BadFormatException(startingLinenumber);
    }
}

