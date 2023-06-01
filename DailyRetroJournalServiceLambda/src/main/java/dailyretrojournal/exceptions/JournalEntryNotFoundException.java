package dailyretrojournal.exceptions;

public class JournalEntryNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public JournalEntryNotFoundException() {
        super();
    }

    public JournalEntryNotFoundException(String message) {
        super(message);
    }

    public JournalEntryNotFoundException(Throwable cause) {
        super(cause);
    }

    public JournalEntryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
