package bankManagement;

public class SavingsAccount extends Account {
    public SavingsAccount(int accNo, String name, double balance) {
        super(accNo, name, balance);
    }

    public String getType() {
        return "Savings";
    }
}

