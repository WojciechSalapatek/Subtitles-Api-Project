package models.exceptions;

public class SubtitleException extends RuntimeException{
    public SubtitleException(String msg, int line){
        super(msg + " in line: " + Integer.toString(line));
    }
}
