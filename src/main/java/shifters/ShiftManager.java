package shifters;

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
    private FrameReader reader;
    private OutputWriter writer;
    private ShiftProcessor shiftProcessor;

    public ShiftManager(FrameReader reader, OutputWriter writer, ShiftProcessor shiftProcessor) {
        this.reader = reader;
        this.writer = writer;
        this.shiftProcessor = shiftProcessor;
    }

    public void shiftSubtitlesBy(int offsetInMillis) throws IOException {
        String frame;
        String newFrame;
        while ((frame=reader.readFrame())!= ""){
           newFrame = shiftProcessor.shiftFrame(frame, offsetInMillis, reader.getLineNumber());
           writer.writeFrame(frame);
        }
    }



    public void setInputPath(String path) throws FileNotFoundException {
        reader.setInputPath(path);
    }


    public void setOutputPath(String path) throws IOException {
        writer.setOutputPath(path);
    }
}
