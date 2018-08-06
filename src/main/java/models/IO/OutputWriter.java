package models.IO;


import java.io.File;
import java.io.IOException;

public interface OutputWriter {

    void writeFrame(String frame);

    void setOutputFile(File file);

    void close() throws IOException;
}
