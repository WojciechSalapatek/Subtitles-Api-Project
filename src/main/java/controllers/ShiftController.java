package controllers;

import models.IO.*;
import models.shifters.MicroDVDShiftProcessor;
import models.shifters.ShiftManager;
import models.shifters.ShiftProcessor;
import models.shifters.SubRipShiftProcessor;

import java.io.File;
import java.io.IOException;

public class ShiftController {

    public String shift(File inputFile, File outputFile, String inputFormat, int offset){
        FrameReader reader = null;
        ShiftProcessor pr = null;
        OutputWriter writer = new ToFileWriter(outputFile);

        if(inputFormat.equals("SubRip")) {
            reader = new SubRipFrameReader(inputFile);
            pr = new SubRipShiftProcessor();
        }
        else if(inputFormat.equals("MicroDVD")) {
            reader = new MicroDVDFrameReader(inputFile);
            pr = new MicroDVDShiftProcessor();
        }

        ShiftManager manager = new ShiftManager(reader, writer, pr);

        try {
            manager.shiftSubtitlesBy(offset);;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return "";
    }
}
