package models.shifters;

import java.io.*;

public class SubRipOutputWriter implements OutputWriter{

    private BufferedWriter writer;

    public void writeFrame(String frame) throws IOException {
        writer.write(frame);
    }

    public void setOutputPath(String path) throws IOException {
        writer = new BufferedWriter(new FileWriter(path));
    }

    public void close() throws IOException {
        writer.close();
    }
}
