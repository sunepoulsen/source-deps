package dk.sunepoulsen.sds.vcs.api

//-----------------------------------------------------------------------------
/**
 * Exception to report sub command related errors.
 */
public class VCSException extends Exception {
    /**
     * Constructs an exception with a single message.
     *
     * @param message The message.
     */
    public VCSException( String message ) {
        super( message );
    }

    /**
     * Constructs an exception with a message and a caused throwable.
     *
     * @param message Message.
     * @param cause   Cause instance.
     */
    public VCSException( String message, Throwable cause ) {
        super( message, cause );
    }
}
