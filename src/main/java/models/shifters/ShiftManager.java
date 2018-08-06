package models.shifters;

import models.IO.FrameReader;
import models.IO.OutputWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Menages shifting subtitles
 */
public class ShiftManager {
    private FrameReader reader;
    private OutputWriter writer;
    private ShiftProcessor shiftProcessor;

    /**
     * Constructs ShiftManager with no input, output, shift processors. These values must be set before usage.
     */
    public ShiftManager(){

    }

    /**
     * Constructs ready to use ShiftManager
     * @param reader FrameReader that is able to read single frame.
     * @param writer OutputWriter managing writing delayed frames to destination
     * @param shiftProcessor processor that delays frames in given format
     */
    public ShiftManager(FrameReader reader, OutputWriter writer, ShiftProcessor shiftProcessor) {
        this.reader = reader;
        this.writer = writer;
        this.shiftProcessor = shiftProcessor;
    }

    /**
     * Shifts all frames, that can be read by FrameReader
     * @param offset an offset to shift frames
     * @throws IOException
     */
    public void shiftSubtitlesBy(int offset) throws IOException {
        String frame;
        String newFrame;
        while ((frame=reader.readFrame())!= "null\n"){
           newFrame = shiftProcessor.shiftFrame(frame, offset, reader.getLineNumber());
           writer.writeFrame(frame);
            MicroDVDShiftProcessor processor = new MicroDVDShiftProcessor();
        }
    }


    public void setInputFile(File file) throws FileNotFoundException {
        reader.setInputFile(file);
    }


    public void setOutputFile(File file) throws IOException {
        writer.setOutputFile(file);
    }
}
