package mk.ukim.finki.av4.bank;

public class PlatinumCheckingAccount extends Account implements InterestBearingAccount {
    public PlatinumCheckingAccount(String holderName, double balance) {
        super(holderName, balance);
        setCanHavenInterest(true);
    }

    public void addInterest() {
        setBalance(getBalance() * (1 + InterestCheckingAccount.INTEREST * 2));
    }

}
