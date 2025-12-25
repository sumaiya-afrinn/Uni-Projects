package bankManagement;

import java.util.*;

public class Bank {
    private ArrayList<Account> accounts;
    private FileHandler fileHandler;

    public Bank() {
        fileHandler = new FileHandler();
        accounts = fileHandler.loadAccounts();
    }

    private int generateAccountNumber() {
        int max = 10000;
        for (Account a : accounts) {
            if (a.getAccountNumber() > max)
                max = a.getAccountNumber();
        }
        return max + 1;
    }

    public int createAccount(String name, String type, double balance) throws BankException {
        if (balance < 0)
            throw new BankException("Balance cannot be negative");

        int accNo = generateAccountNumber();
        Account acc;

        if (type.equals("Savings"))
            acc = new SavingsAccount(accNo, name, balance);
        else
            acc = new CurrentAccount(accNo, name, balance);

        accounts.add(acc);
        fileHandler.saveAccounts(accounts);
        return accNo;
    }

    private Account findAccount(int accNo) throws BankException {
        for (Account a : accounts) {
            if (a.getAccountNumber() == accNo)
                return a;
        }
        throw new BankException("Account not found");
    }

    public void deposit(int accNo, double amount) throws BankException {
        Account acc = findAccount(accNo);
        acc.deposit(amount);
        fileHandler.saveAccounts(accounts);
    }

    public void withdraw(int accNo, double amount) throws BankException {
        Account acc = findAccount(accNo);
        acc.withdraw(amount);
        fileHandler.saveAccounts(accounts);
    }

    public void viewAccount(int accNo) throws BankException {
        Account acc = findAccount(accNo);
        System.out.println("Account Number : " + acc.accountNumber);
        System.out.println("Name           : " + acc.name);
        System.out.println("Account Type   : " + acc.getType());
        System.out.println("Balance        : " + acc.balance);
    }

    public void viewAllAccounts() throws BankException {
        if (accounts.isEmpty())
            throw new BankException("No accounts available");

        for (Account acc : accounts) {
            System.out.println("----------------------------");
            System.out.println("Account Number : " + acc.accountNumber);
            System.out.println("Name           : " + acc.name);
            System.out.println("Account Type   : " + acc.getType());
            System.out.println("Balance        : " + acc.balance);
        }
        System.out.println("----------------------------");
    }

}
