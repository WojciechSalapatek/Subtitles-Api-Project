package models.shifters;

import java.io.*;

public interface FrameReader {
    String readFrame() throws IOException;

    void setInputPath(String path) throws FileNotFoundException;

    int getLineNumber();

    void setInputFile(File file) throws IOException;

    void close() throws IOException;
}
