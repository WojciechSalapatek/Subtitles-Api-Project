package models.exceptions;

public class BadFormatException extends SubtitleException {
    public BadFormatException(int line) {
        super("Bad formatting", line);
    }
}
