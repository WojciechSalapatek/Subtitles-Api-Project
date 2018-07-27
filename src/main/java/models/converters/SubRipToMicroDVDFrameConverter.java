package models.converters;

import models.exceptions.BadFormatException;
import models.shifters.SubRipTimeParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SubRipToMicroDVDFrameConverter implements FrameConverter {
    private Pattern pattern = Pattern.compile("(\\d+)\\n(\\d{2}:\\d{2}:\\d{2},\\d{3}) --> (\\d{2}:\\d{2}:\\d{2},\\d{3})\\n((?:.+\\n)+)\\n");
    private SubRipTimeParser parser;

    public SubRipToMicroDVDFrameConverter(){
        parser = new SubRipTimeParser();
    }

    public String convertFrame(String frame, int fps, int startingLineNumber) {
        long start, end;
        String text;
        Matcher matcher = pattern.matcher(frame);
        if(!matcher.matches()) throw new BadFormatException(startingLineNumber);
        start = parser.parseStringToMillis(matcher.group(2))*fps/1000;
        end = parser.parseStringToMillis(matcher.group(3))*fps/1000;
        text = matcher.group(4);

        return "{" + start + "}{" + end + "}" + text.substring(0,text.length()-1).replaceAll("\\n","|") + "\n";


    }
}
