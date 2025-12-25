package bankManagement;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bank bank = new Bank();

        while (true) {
            System.out.println("====== BANK MANAGEMENT SYSTEM ======");
            System.out.println("1. Create New Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. View Account Details");
            System.out.println("5. View All Accounts");
            System.out.println("6. Exit");
            System.out.println("===================================");
            System.out.print("Enter your choice: ");


            try {
                int choice = sc.nextInt();

                if (choice == 1) {
                    sc.nextLine();
                    System.out.print("Enter customer name: ");
                    String name = sc.nextLine();

                    System.out.println("Select account type:");
                    System.out.println("1. Savings Account");
                    System.out.println("2. Current Account");
                    System.out.print("Enter choice (1 or 2): ");
                    int t = sc.nextInt();

                    String type;
                    if (t == 1)
                        type = "Savings";
                    else if (t == 2)
                        type = "Current";
                    else
                        throw new BankException("Invalid account type");

                    System.out.print("Enter initial balance: ");
                    double bal = sc.nextDouble();

                    int accNo = bank.createAccount(name, type, bal);
                    System.out.println("Account created successfully");
                    System.out.println("Your Account Number is: " + accNo);
                }

                else if (choice == 2) {
                    System.out.print("Enter account number: ");
                    int accNo = sc.nextInt();
                    System.out.print("Enter amount to deposit: ");
                    double amt = sc.nextDouble();
                    bank.deposit(accNo, amt);
                    System.out.println("Deposit successful");
                }

                else if (choice == 3) {
                    System.out.print("Enter account number: ");
                    int accNo = sc.nextInt();
                    System.out.print("Enter amount to withdraw: ");
                    double amt = sc.nextDouble();
                    bank.withdraw(accNo, amt);
                    System.out.println("Withdrawal successful");
                }

                else if (choice == 4) {
                    System.out.print("Enter account number: ");
                    int accNo = sc.nextInt();
                    bank.viewAccount(accNo);
                }

                else if (choice == 5) {
                    bank.viewAllAccounts();
                }

                else if (choice == 6) {
                    System.out.println("Thank you for using the system");
                    break;
                }

                else {
                    System.out.println("Invalid choice");
                }

            } catch (BankException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Invalid input");
                sc.nextLine();
            }
        }
    }
}
