import java.util.Scanner;
class ATM {
    private BankAccount account;
    public ATM(BankAccount account) {
        this.account = account;
    }
    public void withdraw(double amount) {
        account.withdraw(amount);
    }
    public void deposit(double amount) {
        account.deposit(amount);
    }
    public void checkBalance() {
        account.checkBalance();
    }
    public void run() {
        Scanner scanner = new Scanner(System.in);
        int choice =0;

        do {
            try {
                System.out.println("\nSelect an option:");
                System.out.println("1. Withdraw");
                System.out.println("2. Deposit");
                System.out.println("3. Check Balance");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                double amount;

                switch (choice) {
                    case 1:
                        System.out.print("Enter withdrawal amount: Rs ");
                        amount = scanner.nextDouble();
                        withdraw(amount);
                        break;
                    case 2:
                        System.out.print("Enter deposit amount: Rs ");
                        amount = scanner.nextDouble();
                        deposit(amount);
                        break;
                    case 3:
                        checkBalance();
                        break;
                    case 4:
                        System.out.println("Thank you for using the ATM.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Consume the invalid input
            }
        } while (choice != 4);

        scanner.close();
    }
}