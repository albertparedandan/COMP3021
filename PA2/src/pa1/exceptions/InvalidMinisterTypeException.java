package exceptions;

import java.io.IOException;

public class InvalidMinisterTypeException extends IOException {
    public InvalidMinisterTypeException(String type) {
        super("Invalid Minister Type: " + type);
    }
}
