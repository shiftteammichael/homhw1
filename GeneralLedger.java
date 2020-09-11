/**
 * The General Ledger class stores an ordered list of transaction objects in an array
 * @author Michael Hom
 * Stony brook id: 112536073
 * Recitation 09
 */
public class GeneralLedger implements Cloneable {
    public static final int MAX_TRANSACTIONS=50;
    public double totalDebitAmount;
    public double totalCreditAmount;
    public Transaction [] ledger;
    public int size;


    /**
     * This is the GeneralLedger constructor that constructs an instance of the GeneralLedger
     * with no transactions in it
     */
    public GeneralLedger() {
        ledger= new Transaction[MAX_TRANSACTIONS];
    }

    /**
     * This method adds a new Transaction into GeneralLedger if it does not already exist
     * @param newTransaction
     * @throws FullGeneralLedgerException
     *
     * @throws InvalidTransactionException
     * @throws TransactionAlreadyExistsException
     */
    public void addTransaction (Transaction newTransaction) throws FullGeneralLedgerException, InvalidTransactionException, TransactionAlreadyExistsException{
        int a= Integer.parseInt(newTransaction.getDate().substring(0,4));
        int b= Integer.parseInt(newTransaction.getDate().substring(5,7));
        int c=Integer.parseInt(newTransaction.getDate().substring(8));

        if(this.size()==MAX_TRANSACTIONS){
            throw new FullGeneralLedgerException("Full general Ledger");
        }
        if(newTransaction.getAmount()==0 ){
            throw new InvalidTransactionException("Amount is 0");
        }
        if(a>=2050 || a<=1900){
            throw new InvalidTransactionException("Year is Wrong ");
        }
        if(b<1 || b>12){
            throw new InvalidTransactionException("Month is wrong ");
        }
        if(c<1 || c >30 ){
            throw new InvalidTransactionException("Day is wrong ");
        }
        for(int i=1; i<=this.size(); i++){
            if(newTransaction.equals(ledger[i-1])) {
                throw new TransactionAlreadyExistsException("Transaction not added: Transaction already exists in the general ledger");
            }
        }

                ledger[size]=newTransaction;




        size++;

        if(newTransaction.getAmount()<0){
            totalCreditAmount+=newTransaction.getAmount();
        }
        if(newTransaction.getAmount()>0){
            totalDebitAmount+=newTransaction.getAmount();
        }
    }

    /**
     * This method removes the transaction located at position
     * @param position
     * @throws InvalidLedgerPositionException
     */
    public void removeTransaction(int position) throws InvalidLedgerPositionException {

        if (position == 0 || position > this.size()) {
            throw new InvalidLedgerPositionException("Transaction not removed: No such transaction in the general ledger");
        }
        if(ledger[position-1].getAmount()<0){
            totalCreditAmount-=ledger[position-1].getAmount();
        }
        if(ledger[position-1].getAmount()>0){
            totalDebitAmount-=ledger[position-1].getAmount();
        }
        for (int i = position; i <= this.size(); i++) {
            if(i+1>position) {
                ledger[i - 1] = ledger[i];
            }
        }
        size--;

    }

    /**
     * This method returns a reference to the transaction object at the given position
     * @param position
     * @return
     * @throws InvalidLedgerPositionException
     */
    public Transaction getTransaction(int position) throws InvalidLedgerPositionException {
        if(position==0 || position>this.size()){
            throw new InvalidLedgerPositionException("No such transaction.");
        }
        return ledger[position-1];

    }

    /**
     * This method checks whether a certain transaction is contained in the ledger
     * @param transaction
     * @return
     * @throws IllegalArgumentException
     */
    public boolean exists(Transaction transaction) throws IllegalArgumentException{
        for(int i=0; i<this.size(); i++){
            if(ledger[i].getDate().equals(transaction.getDate()) && ledger[i].getAmount()==transaction.getAmount() && ledger[i].getDescription().equals(transaction.getDescription())){
                return true;
            }
        }
        return false;
    }

    /**
     * This method creates a copy of the GeneralLedger
     * @return yer
     * @throws CloneNotSupportedException
     */
    public Object clone() throws CloneNotSupportedException {
        GeneralLedger yer = (GeneralLedger)super.clone();
        yer.setLedger(yer.getLedger().clone());
        yer.setTotalCreditAmount(yer.getTotalCreditAmount());
        yer.setTotalDebitAmount(yer.getTotalDebitAmount());
        for(int i=1; i< this.size();i++){
            try {
                yer.getTransaction(i).clone();

            } catch (InvalidLedgerPositionException e) {
                e.printStackTrace();
            }

        }
        return yer;

    }

    /**
     * This method returns a string representation of the GeneralLedger object in a formatted table
     * @return header, yuh, table
     */
    public String toString(){
        String header= String.format(("%-26s %19s %18s %16s %20s"), "No." , "Date" , "Debit", "Credit", "Description" );
        String yuh= "\n --------------------------------------------------------------------------------------------------------------------- ";
        String table="";

        for(int i=1; i<=this.size();i++) {
            double tempCredit=0;
            double tempDebit=0;
            if (ledger[i-1].getAmount() < 0) {
                tempCredit = ledger[i-1].getAmount();
            }
            if (ledger[i-1].getAmount() > 0) {
                tempDebit = ledger[i-1].getAmount();
            }
            table+=String.format("\n %-26d %19s %19f %16f %20s",i, ledger[i-1].getDate(),tempDebit,tempCredit,ledger[i-1].getDescription());

        }

        return header+ yuh+ table;
    }

    /**
     * This method prints a neatly formatted table of each item in the list
     */
    public void printAllTransactions(){

           System.out.println(this.toString());


    }

    /**
     * This method prints all the transactions posted on the specific date
     * @param generalLedger
     * @param date
     * @throws InvalidLedgerPositionException
     */
    public static void filter(GeneralLedger generalLedger, String date) throws InvalidLedgerPositionException {
        System.out.printf("%-26s %19s %18s %16s %20s", "No. ", "Date" , "Debit", "Credit ", "Description");
        System.out.println("\n -------------------------------------------------------------------------------------------------------------------- ");
        for(int i=1; i<=generalLedger.size(); i++ ){
            double tempCredit=0;
            double tempDebit=0;
            if(date.equals(generalLedger.getTransaction(i).getDate())){
                if (generalLedger.getTransaction(i).getAmount() < 0) {
                    tempCredit = generalLedger.getTransaction(i).getAmount();
                }
                if (generalLedger.getTransaction(i).getAmount() > 0) {
                    tempDebit = generalLedger.getTransaction(i).getAmount();
                }
                System.out.printf(" %-26s %19s %18s %16s %20s",i,generalLedger.getTransaction(i).getDate(),tempDebit,tempCredit, generalLedger.getTransaction(i).getDescription() +"\n");
            }
        }
    }

    /**
     *This method returns the number of transactions currently in the ledger
     * @return size
     */
    public int size() {

        return size;

    }

    /**
     * This is a getter method that returns the ledger
     * @return ledger
     */
    public Transaction[] getLedger() {
        return ledger;
    }

    /**
     * This is a setter method that sets the ledger
     * @param ledger
     */
    public void setLedger(Transaction[] ledger) {
        this.ledger = ledger;
    }

    /**
     * This a getter method that returns the total credit amount
     * @return totalCreditAmount
     */
    public double getTotalCreditAmount() {
        return totalCreditAmount;
    }

    /**
     * This is a setter method that sets the total credit amount
     * @param totalCreditAmount
     */
    public void setTotalCreditAmount(double totalCreditAmount) {
        this.totalCreditAmount = totalCreditAmount;
    }

    /**
     * This is a getter method that returns the total debit amount
     * @return totalDebitAmount
     */
    public double getTotalDebitAmount() {
        return totalDebitAmount;
    }

    /**
     * This is a setter method that sets the total debit amount
     * @param totalDebitAmount
     */
    public void setTotalDebitAmount(double totalDebitAmount) {
        this.totalDebitAmount = totalDebitAmount;
    }

}
