package models.IO;

import java.io.*;

public class SubRipFrameReader implements FrameReader{

    private BufferedReader reader;
    private int newLineNumber = 1;
    private int lineNumber = 0;

    public SubRipFrameReader(File input){
        try {
            reader = new BufferedReader(new FileReader(input));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String readFrame() {
        StringBuilder sb = new StringBuilder();
        String line = "";
        lineNumber = newLineNumber;
        while (!sb.toString().contains("\n\n") && !sb.toString().contains("null")) {
            try {
                line = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ++newLineNumber;
            sb.append(line);
            sb.append("\n");
        }
        return sb.toString();
    }


    public void close() throws IOException {
        lineNumber = 0;
        reader.close();
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
}
