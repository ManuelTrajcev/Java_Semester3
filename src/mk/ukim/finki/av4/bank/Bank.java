package mk.ukim.finki.av4.bank;

import java.util.Arrays;
import java.util.Scanner;

public class Bank {

    private Account[] accounts;
    private int totalAccounts;
    private int max;

    public Bank(int max) {
        this.max = max;
        this.totalAccounts = 0;
        this.accounts = new Account[max];
    }

    public double totalAssets() {
        double sum = 0;
        for (Account account : accounts) {
            sum += account.getBalance();
        }
        return sum;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Account acc :
                accounts) {
            str.append(acc).append("\n");
        }
        return str.toString();
    }

    public void addInterest() {
        for (Account account : accounts) {
            if (account.isCanHavenInterest()) {
                InterestBearingAccount iba = (InterestBearingAccount) account;
                iba.addInterest();
            }
        }
    }

    public void addAccount(Account acc) {
        if (totalAccounts == max) {
            accounts = Arrays.copyOf(accounts, 2 * max);
            max *= 2;
        } else {
            accounts[totalAccounts++] = acc;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Bank bank = new Bank(n);
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            line = scanner.nextLine();
            String name = line.trim();
            line = scanner.nextLine();
            String type = line.trim();
            int b = scanner.nextInt();
            if (type.equals("i")) {
                Account acc = new InterestCheckingAccount(name, b);
                bank.addAccount(acc);
            } else if (type.equals("p")) {
                Account acc = new PlatinumCheckingAccount(name, b);
                bank.addAccount(acc);

            } else {
                Account acc = new NonInterestCheckingAccount(name, b);
                bank.addAccount(acc);
            }
        }
        System.out.println(bank);
        bank.addInterest();
        System.out.println(bank);
    }


}
