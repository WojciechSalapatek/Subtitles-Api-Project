package shifters;

import exceptions.BadFormatException;
import exceptions.EndBeforeStartException;
import exceptions.NegativeFrameAfterShiftException;
import exceptions.OutOfOrderFramesException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ShiftManager {
    private int currFrame = 0;
    private FrameReader reader;
    private OutputWriter writer;
    private FormatProcessor formatProcessor;

    public ShiftManager(FrameReader reader, OutputWriter writer, FormatProcessor formatProcessor) {
        this.currFrame = 0;
        this.reader = reader;
        this.writer = writer;
        this.formatProcessor = formatProcessor;
    }

    public void shiftSubtitlesBy(int offsetInMillis) throws IOException {
        String frame;
        while ((frame=reader.readFrame())!= ""){
            shiftFrame(frame, offsetInMillis, reader.getLineNumber());
            currFrame++;
        }
    }

    public String shiftFrame(String frame, int offset, int startingLineNumber){
        String start, newStart, end, newEnd;
        formatProcessor.setFrame(frame, startingLineNumber);
        if(formatProcessor.getFrameNumber() != currFrame) throw new OutOfOrderFramesException(startingLineNumber);

        start = formatProcessor.getStart();
        end = formatProcessor.getEnd();

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

        return formatProcessor.makeDelayedFrame(newStart, newEnd);
    }

    public void setInputPath(String path) throws FileNotFoundException {
        reader.setInputPath(path);
    }


    public void setOutputPath(String path) throws IOException {
        writer.setOutputPath(path);
    }
}
