package models.IO;

import models.shifters.MicroDVDShiftProcessor;

import java.io.*;

public class MicroDVDFrameReader implements FrameReader{
    private BufferedReader reader;
    private int lineNumber = 0;

    public MicroDVDFrameReader(File input){
        try {
            reader = new BufferedReader(new FileReader(input));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String readFrame() {
        lineNumber++;
        String frame = "";
        try {
            frame = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        frame += "\n";
        return frame;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setInputFile(File file) {
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void close() throws IOException {
        reader.close();
    }
}
