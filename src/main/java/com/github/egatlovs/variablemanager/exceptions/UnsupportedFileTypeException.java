package com.github.egatlovs.variablemanager.exceptions;

/**
 * <b>UnsupportedFileTypeException</b><br>
 * <br>
 * This is a RuntimeException which will be thrown if the processing of a FileValue resolve to an unsupported type.
 *
 * @author egatlovs
 */
public class UnsupportedFileTypeException extends RuntimeException {
    public UnsupportedFileTypeException(String message) {
        super(message);
    }
}
