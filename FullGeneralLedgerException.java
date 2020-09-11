/**
 *The FullGeneralLedgerException class or exception is thrown when there is no more space for adding
 * more transactions.
 * @author Michael Hom
 * Stony brook id:112536073
 * Recitation 09
 */
public class FullGeneralLedgerException extends Exception {
    public FullGeneralLedgerException(String message){
        super(message);
    }
}
