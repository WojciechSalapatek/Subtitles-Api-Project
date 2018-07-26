package models.exceptions;

public class OutOfOrderFramesException extends SubtitleException {

    public OutOfOrderFramesException(int line) {
        super("Out of order frames", line);
    }
}
