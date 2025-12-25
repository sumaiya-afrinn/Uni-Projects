package bankManagement;

public class CurrentAccount extends Account {
    public CurrentAccount(int accNo, String name, double balance) {
        super(accNo, name, balance);
    }

    public String getType() {
        return "Current";
    }
}

