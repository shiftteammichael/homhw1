/**
 *The TransactionAlreadyExistsException class or exception is thrown when there is already the same transaction
 * in the GeneralLedger
 * @author Michael Hom
 * Stony brook id: 112536073
 * Recitation 09
 */

public class TransactionAlreadyExistsException extends Exception {
    public TransactionAlreadyExistsException(String message){
        super(message);
    }

}
