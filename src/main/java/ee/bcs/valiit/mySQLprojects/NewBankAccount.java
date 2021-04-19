package ee.bcs.valiit.mySQLprojects;

public class NewBankAccount {
    private String owner;
    private String accountNr;
    private Double balance;
    private boolean accountStatus;

    public NewBankAccount(String owner, String accountNr, Double balance, boolean isLocked) {
        this.owner = owner;
        this.accountNr = accountNr;
        this.balance = balance;
        this.accountStatus = isLocked;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAccountNr() {
        return accountNr;
    }

    public void setAccountNr(String accountNr) {
        this.accountNr = accountNr;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public boolean isAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(boolean accountStatus) {
        this.accountStatus = accountStatus;
    }
}
