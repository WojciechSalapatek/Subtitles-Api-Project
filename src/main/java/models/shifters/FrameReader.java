package models.shifters;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface FrameReader {
    String readFrame() throws IOException;

    void setInputPath(String path) throws FileNotFoundException;

    int getLineNumber();

    void close() throws IOException;
}
