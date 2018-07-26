package models.shifters;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SubRipFrameReader implements FrameReader{

    private BufferedReader reader;
    private int lineNumber = 0;

    public String readFrame() throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;

        while (!sb.toString().contains("\n\n")) {
            line = reader.readLine();
            ++lineNumber;
            sb.append(line);
            sb.append("\n");
        }
        return sb.toString();
    }

    public void setInputPath(String path) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(path));
    }

    public void close() throws IOException {
        lineNumber = 0;
        reader.close();
    }

    public int getLineNumber() {
        return lineNumber;
    }
}
