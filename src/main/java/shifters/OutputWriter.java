package shifters;


import java.io.IOException;

public interface OutputWriter {

    void writeFrame(String frame) throws IOException;

    void setOutputPath(String path) throws IOException;

    void close() throws IOException;
}
