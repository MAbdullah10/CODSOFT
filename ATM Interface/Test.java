import java.util.Scanner;
public class Test {
    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        String account_no,account_name;
        int total_balance;
        System.out.println("Enter your Account Number: ");
        account_no = s.nextLine();
        System.out.println("Enter your Account Name: ");
        account_name = s.nextLine();
        System.out.println("Enter your total balance: ");
        total_balance = s.nextInt();
                BankAccount account = new BankAccount(account_no,account_name,total_balance);
                ATM atm = new ATM(account);
                atm.run();

                System.out.println();
                account.printTransactionHistory();
                account.saveTransactionHistoryToFile("TRANSACTION_HISTORY");
    }
}