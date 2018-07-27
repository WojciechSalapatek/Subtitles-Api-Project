package models.shifters;

import models.IO.FrameReader;
import models.IO.OutputWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

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
        while ((frame=reader.readFrame())!= "null\n"){
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
