package mk.ukim.finki.av4.bank;

public class NonInterestCheckingAccount extends Account {
    public NonInterestCheckingAccount(String accountHolder, double balance) {
        super(accountHolder, balance);
        setCanHavenInterest(false);
    }

}
