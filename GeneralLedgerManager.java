/**
 * The GeneralLedgerManager class is the main method that takes the user input
 * @author Michael Hom
 * Stony brook id: 112536073
 * Recitation 09
 */

import java.util.Scanner;
public interface GeneralLedgerManager {
    public static void main(String[] args) throws TransactionAlreadyExistsException, InvalidTransactionException, FullGeneralLedgerException {
        GeneralLedger test= new GeneralLedger();
        GeneralLedger copy=new GeneralLedger();
        Scanner in=new Scanner(System.in);
        System.out.println("(A) Add Transaction  \n (G) Get Transaction \n (R) Remove Transaction \n (P) Print Transactions in General Ledger \n (F) Filter by Date \n (S) Size \n (B) Backup \n (PB) Print Transactions in Backup \n (RB) Revert to Backup \n (CB) Compare Backup with Current \n (PF) Print Financial Information \n (Q) Quit");
        while(true) {
            System.out.println("Please enter a command: ");
            String input = in.nextLine();

            if (input.equalsIgnoreCase("A")) {

                System.out.println("Enter Date: ");
                String date = in.nextLine();
                System.out.println("Enter Amount ($): ");
                double amount = in.nextDouble();
                System.out.println("Enter Description: ");
                in.nextLine();
                String description = in.nextLine();
                Transaction yer=new Transaction(date,amount,description);
                try{
                test.addTransaction(yer);
                System.out.println("Transaction Successfully added to the General Ledger");
            }
                catch(InvalidTransactionException|TransactionAlreadyExistsException|FullGeneralLedgerException ex){
                    System.out.println(ex.getMessage());
                }
            }



            if(input.equalsIgnoreCase("G")){
                    System.out.println("Enter Position: ");
                    int position = in.nextInt();
                    try {
                        double a=test.getTransaction(position).getAmount();
                        String s=test.getTransaction(position).getDate();
                        String bruh=test.getTransaction(position).getDescription();

                        String table="";
                        double tempCredit = 0;
                        double tempDebit = 0;
                                String header = String.format(("%-26s %19s %18s %16s %20s"), "No.", "Date", "Debit", "Credit", "Description");
                                String yuh = "-------------------------------------------------------------------------------------------------------------------------- ";
                                if (a > 0) {
                                    tempDebit = a;
                                }
                                if (a < 0) {
                                    tempCredit = a;
                                }
                                    table+= String.format(("%-26d %19s %18f %16f %20s"), position, s, tempDebit, tempCredit, bruh);


                                System.out.println(header);
                                System.out.println(yuh);
                                System.out.println(table);


                            }


                    catch(InvalidLedgerPositionException ex){
                        System.out.println(ex.getMessage());
                    }




            }
            if(input.equalsIgnoreCase("R")){
                try {
                    System.out.println("Enter Position: ");
                    int number = in.nextInt();
                    test.removeTransaction(number);
                    System.out.println("Transaction has been successfully removed from the general Ledger");
                }
                catch(InvalidLedgerPositionException ex ){
                    System.out.println(ex.getMessage());
                }
            }
            if(input.equalsIgnoreCase("P")){
                if(test.size()==0){
                    System.out.println("No transactions currently in the Ledger");
                }
                else {
                    test.printAllTransactions();
                }
            }
            if(input.equalsIgnoreCase("F")){
                try {
                    System.out.println("Enter Date: ");
                    String date = in.nextLine();
                    test.filter(test, date);
                }
                catch(InvalidLedgerPositionException ex){
                    System.out.println(ex.getMessage());
                }
            }
            if(input.equalsIgnoreCase("L")) {
                System.out.println("Enter Date: ");
                String date = in.nextLine();
                System.out.println("Enter Amount ($): ");
                double amount = in.nextDouble();
                System.out.println("Enter Description: ");
                in.nextLine();
                String description = in.nextLine();

                try {
                    for (int i = 1; i <= test.size(); i++) {
                        if (test.getLedger()[i - 1].getDate().equals(date) && test.getLedger()[i - 1].getAmount() == amount && test.getLedger()[i - 1].getDescription().equals(description)) {
                            String header = String.format(("%-26s %19s %18s %16s %20s"), "No.", "Date", "Debit", "Credit", "Description");
                            String yuh = "-------------------------------------------------------------------------------------------------------------------------- ";
                            double a = test.getTransaction(i).getAmount();
                            String s = test.getTransaction(i).getDate();
                            String bruh = test.getTransaction(i).getDescription();
                            String table = "";
                            double tempCredit = 0;
                            double tempDebit = 0;

                            if (a > 0) {
                                tempDebit = a;
                            }
                            if (a < 0) {
                                tempCredit = a;
                            }
                            table += String.format(("%-26d %19s %18f %16f %20s"), i, s, tempDebit, tempCredit, bruh);


                            System.out.println(header);
                            System.out.println(yuh);
                            System.out.println(table);

                        }


                    }
                }

            catch(InvalidLedgerPositionException ex) {
                System.out.println(ex.getMessage());
            }
            }
            if(input.equalsIgnoreCase("S")){
                System.out.println("There are currently "+ test.size()+ " in the General Ledger");
            }
            if(input.equalsIgnoreCase("B")){
                try {
                    copy=(GeneralLedger)test.clone();
                    System.out.println("Created a backup of the current general Ledger");
                }
                catch(CloneNotSupportedException ex){
                    System.out.println(ex.getMessage());
                }
            }
            if(input.equalsIgnoreCase("PB")){
                copy.printAllTransactions();
            }
            if(input.equalsIgnoreCase("RB")){
                test=copy;
                System.out.println("General ledger successfully reverted to the backup copy");

            }
            if(input.equalsIgnoreCase("CB")){
                double counter=0;
                for(int i=0; i<test.size();i++){
                    if(copy.getLedger()[i].equals(test.getLedger()[i])){
                        counter++;
                    }
                }
                if(counter==test.size()){
                    System.out.println("The current General Ledger is the same as the backup copy.");
                }
                else{
                    System.out.println("The current general ledger is NOT the same as the backup copy.");
                }
            }
            if(input.equalsIgnoreCase("PF")){
                System.out.println("Financial Data for Jack's Account");
                System.out.println("--------------------------------------------------");
                System.out.println("Assets: $"+test.getTotalDebitAmount());
                System.out.println("Liabilities: $"+test.getTotalCreditAmount());
                double total= test.getTotalDebitAmount()+test.getTotalCreditAmount();
                System.out.println("Net Worth: "+total );
            }
            if(input.equalsIgnoreCase("Q")){
                System.exit(0);

            }

        }

    }

}
