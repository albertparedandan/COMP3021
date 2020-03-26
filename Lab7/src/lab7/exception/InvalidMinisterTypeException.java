package lab7.exception;

import java.io.IOException;

public class InvalidMinisterTypeException extends IOException
{
	public InvalidMinisterTypeException(String type)
	{
		super("Invalid Minister Type: " + type);
	}
}
