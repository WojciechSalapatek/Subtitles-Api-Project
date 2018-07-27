package models.IO;


import java.io.File;
import java.io.IOException;

public interface OutputWriter {

    void writeFrame(String frame) throws IOException;

    void setOutputPath(String path) throws IOException;

    void setOutputFile(File file) throws IOException;

    void close() throws IOException;
}
