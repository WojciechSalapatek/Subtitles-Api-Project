package models.IO;

import java.io.*;

public class ToFileWriter implements OutputWriter{

    private BufferedWriter writer;

    public ToFileWriter(File file){
        try {
            writer = new BufferedWriter(new FileWriter(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeFrame(String frame) {
        try {
            writer.write(frame);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setOutputFile(File file) {
        try {
            writer = new BufferedWriter(new FileWriter(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() throws IOException {
        writer.flush();
        writer.close();
    }
}
