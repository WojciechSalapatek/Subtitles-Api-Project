package models.converters;

import models.IO.FrameReader;
import models.IO.OutputWriter;

public class ConvertManager {
    private FrameReader reader;
    private FrameConverter converter;
    private OutputWriter writer;
    private int fps;

    public ConvertManager(FrameReader reader, FrameConverter converter, OutputWriter writer, int fps) {
        this.reader = reader;
        this.converter = converter;
        this.writer = writer;
        this.fps = fps;
    }

    public void convert(){
        String frame;
        while(!(frame = reader.readFrame()).contains("null")) {
            writer.writeFrame(converter.convertFrame(frame, fps, reader.getLineNumber()));
        }
    }

}
