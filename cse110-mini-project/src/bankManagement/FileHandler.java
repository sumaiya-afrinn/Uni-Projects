package bankManagement;

import java.io.*;
import java.util.*;

public class FileHandler {
    private final String fileName = "accounts.txt";

    public ArrayList<Account> loadAccounts() {
        ArrayList<Account> list = new ArrayList<>();
        File file = new File(fileName);

        if (!file.exists())
            return list;

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String[] d = sc.nextLine().split(",");
                if (d.length != 4) continue;

                int accNo = Integer.parseInt(d[0]);
                String name = d[1];
                String type = d[2];
                double bal = Double.parseDouble(d[3]);

                if (type.equals("Savings"))
                    list.add(new SavingsAccount(accNo, name, bal));
                else
                    list.add(new CurrentAccount(accNo, name, bal));
            }
        } catch (Exception e) {}

        return list;
    }

    public void saveAccounts(ArrayList<Account> list) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Account a : list) {
                bw.write(a.toFileString());
                bw.newLine();
            }
        } catch (Exception e) {}
    }
}


