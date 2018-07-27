package models.IO;

import java.io.*;

public class MicroDVDFrameReader implements FrameReader{
    private BufferedReader reader;
    private int lineNumber = 0;

    public String readFrame() throws IOException {
        lineNumber++;
        String frame;
        frame = reader.readLine();
        frame += "\n";
        return frame;
    }

    public void setInputPath(String path) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(path));
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setInputFile(File file) throws IOException {
        reader = new BufferedReader(new FileReader(file));
    }

    public void close() throws IOException {
        reader.close();
    }
}
