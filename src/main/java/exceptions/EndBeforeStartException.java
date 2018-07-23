package exceptions;

public class EndBeforeStartException extends SubtitleException {

    public EndBeforeStartException(int line) {
        super("Frame has end before start", line);
    }
}
