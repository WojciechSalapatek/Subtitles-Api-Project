package models.converters;

import models.exceptions.BadFormatException;
import models.shifters.SubRipTimeParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MicroDVDToSubRipFrameConverter implements FrameConverter {

    private Pattern pattern = Pattern.compile("\\{(\\d+)\\}\\{(\\d+)\\}(.+\n)");
    private SubRipTimeParser parser;
    private int subNumber;

    public MicroDVDToSubRipFrameConverter(){
            parser = new SubRipTimeParser();
            subNumber = 0;
        }

        public String convertFrame(String frame, int fps, int startingLineNumber) {
            subNumber++;
            String start, end;
            String text;
            Matcher matcher = pattern.matcher(frame);
            if(!matcher.matches()) throw new BadFormatException(startingLineNumber);
            start = parser.parseMillisToString(Integer.parseInt(matcher.group(1))*1000/fps);
            end = parser.parseMillisToString(Integer.parseInt(matcher.group(2))*1000/fps);
            text = matcher.group(3);

            return  subNumber + "\n" +
                    start + " --> " + end + "\n" +
                    text.replaceAll("\\|","\n") + "\n";


        }

}
