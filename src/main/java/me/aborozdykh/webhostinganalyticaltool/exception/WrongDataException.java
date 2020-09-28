package me.aborozdykh.webhostinganalyticaltool.exception;

/**
 * @author Andrii Borozdykh
 */
public class WrongDataException extends RuntimeException {
    public WrongDataException(String message) {
        super(message);
    }

    public WrongDataException(String message, Throwable cause) {
        super(message, cause);
    }

}
