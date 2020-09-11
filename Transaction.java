/**
 * The Transaction class contains the date, amount, and the description of the Transaction.
 * @author Michael Hom
 * Stony brook id: 112536073
 * Recitation 07
 */
public class Transaction implements Cloneable {
    private String date;
    private double amount;
    private String description;

    public Transaction(){

    }

    /**
     * Constructor that creates a new Transaction with the given information
     * @param date1
     * @param amount1
     * @param description1
     */
    public Transaction(String date1, double amount1, String description1){
        date=date1;
        amount=amount1;
        description=description1;
    }

    /**
     * The clone method returns a copy of the Transaction
     * @return wham
     * @throws CloneNotSupportedException
     */
    public Object clone() throws CloneNotSupportedException {
            Transaction wham= (Transaction) super.clone();
            wham.setAmount(wham.getAmount());
            wham.setDate(wham.getDate());
            wham.setDescription(wham.getDescription());
            return wham;

    }

    /**
     * The equals method checks to see if the object that refers to a Transaction
     * object has the same attributes as the Transaction.
     * @param obj
     * @return (yer.date.equals(this.date) && yer.amount==this.amount && yer.description.equals(this.description));
     */
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(!(obj instanceof Transaction)){
            return false;
        }
        Transaction yer= (Transaction) obj;
        return (yer.date.equals(this.date) && yer.amount==this.amount && yer.description.equals(this.description));

    }

    /**
     * This is a getter method that returns the description of the transaction
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * This is a setter method that sets the description of the transaction
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This is a getter method that returns the amount of the transaction
     * @return amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * This is a setter method that sets the amount of the transaction
     * @param amount
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * This is a getter method that returns the date of the transaction
     * @return date
     */
    public String getDate() {
        return date;
    }

    /**
     * This is a setter method that sets the date of the transaction
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }
}
