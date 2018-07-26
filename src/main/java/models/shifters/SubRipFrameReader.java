package models.shifters;

import java.io.*;

public class SubRipFrameReader implements FrameReader{

    private BufferedReader reader;
    private int newLineNumber = 1;
    private int lineNumber = 0;

    public String readFrame() throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        lineNumber = newLineNumber;
        while (!sb.toString().contains("\n\n")) {
            line = reader.readLine();
            ++newLineNumber;
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

    public void setInputFile(File file) throws IOException {
        reader = new BufferedReader(new FileReader(file));
    }
}
