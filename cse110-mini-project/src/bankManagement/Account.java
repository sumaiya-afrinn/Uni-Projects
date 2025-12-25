package bankManagement;

public abstract class Account {
    protected int accountNumber;
    protected String name;
    protected double balance;

    public Account(int accountNumber, String name, double balance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public abstract String getType();

    public void deposit(double amount) throws BankException {
        if (amount <= 0)
            throw new BankException("Deposit amount must be positive");
        balance += amount;
    }

    public void withdraw(double amount) throws BankException {
        if (amount <= 0 || amount > balance)
            throw new BankException("Invalid withdrawal amount");
        balance -= amount;
    }

    public String toFileString() {
        return accountNumber + "," + name + "," + getType() + "," + balance;
    }
}
