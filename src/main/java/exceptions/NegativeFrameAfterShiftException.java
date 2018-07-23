package exceptions;

public class NegativeFrameAfterShiftException extends SubtitleException {
    public NegativeFrameAfterShiftException(int line) {
        super("Shift is too big, it causes frame show before film start",line);
    }
}
