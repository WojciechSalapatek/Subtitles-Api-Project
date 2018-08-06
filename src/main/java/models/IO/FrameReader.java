package models.IO;

import java.io.*;

public interface FrameReader {
    String readFrame();

    int getLineNumber();

    void setInputFile(File file);

    void close() throws IOException;
}
