/**
 *The InvalidTransactionException class or exception is thrown when the amount is 0 or if the
 * date is invalid.
 * @author Michael Hom
 * Stony brook id: 112536073
 * Recitation 09
 */

public class InvalidTransactionException extends Exception {
    public InvalidTransactionException(String message){
        super(message);
    }
}
