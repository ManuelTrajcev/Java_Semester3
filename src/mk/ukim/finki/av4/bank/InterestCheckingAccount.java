package mk.ukim.finki.av4.bank;

public class InterestCheckingAccount extends Account implements InterestBearingAccount {

    public static double INTEREST = 0.03;

    public InterestCheckingAccount(String accountHolder, double balance) {
        super(accountHolder, balance);
        setCanHavenInterest(true);
    }

    public void addInterest() {
        setBalance(getBalance() * (1 + INTEREST));
    }
}
