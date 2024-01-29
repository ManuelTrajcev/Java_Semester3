package mk.ukim.finki.av4.bank;

public abstract class Account {
    private static long ID_COUNTER = 1000;
    private String accountHolder;
    private long id;
    private double balance;
    private boolean canHavenInterest;

    public Account(String accountHolder, double balance) {
        this.accountHolder = accountHolder;
        this.balance = balance;
        this.id = ID_COUNTER++;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public long getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setCanHavenInterest(boolean canHavenInterest) {
        this.canHavenInterest = canHavenInterest;
    }

    public boolean isCanHavenInterest() {
        return canHavenInterest;
    }

    public boolean withdraw(double amount) {
        if (amount > balance) {
            return false;
        } else {
            balance -= amount;
            return true;
        }
    }
    public void deposit(double amount) {
        balance+=amount;
    }

    @Override
    public String toString() {
        return String.format("%s %d %.2f", accountHolder, id, balance);
    }
}
