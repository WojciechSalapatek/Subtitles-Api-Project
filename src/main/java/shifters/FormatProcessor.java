package shifters;

public interface FormatProcessor {

    boolean matches();

    int getFrameNumber();

    String getStart();

    String getEnd();

    String makeDelayedFrame(String start, String end);

    void setFrame(String frame, int startingLineNumber);
}
