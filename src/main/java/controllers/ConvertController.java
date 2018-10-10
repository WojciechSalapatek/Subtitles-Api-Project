package controllers;

import models.IO.*;
import models.converters.ConvertManager;
import models.converters.FrameConverter;
import models.converters.MicroDVDToSubRipFrameConverter;
import models.converters.SubRipToMicroDVDFrameConverter;

import java.io.File;
import java.io.IOException;

public class ConvertController {

    public String convert(File inputFile, File outputFile, String inputFormat, String outputFormat, int fps){
        FrameReader reader;
        OutputWriter writer;
        FrameConverter converter;

        if(inputFormat.equals("SubRip")) {
            converter = new SubRipToMicroDVDFrameConverter();
            reader = new SubRipFrameReader(inputFile);
        }
        else if (inputFormat.equals("MicroDVD")) {
            converter = new MicroDVDToSubRipFrameConverter();
            reader = new MicroDVDFrameReader(inputFile);
        }
        else return "null";
        writer = new ToFileWriter(outputFile);

        ConvertManager manager = new ConvertManager(reader, converter, writer, fps);
        manager.convert();

        try {
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "shifted";

    }
}
