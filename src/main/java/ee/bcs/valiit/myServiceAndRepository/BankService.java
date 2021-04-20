package ee.bcs.valiit.myServiceAndRepository;

import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class BankService {

    @Autowired
    private BankRepository bankRepository;

    public void createAccount(String accountNr, String name, double balance) {
        bankRepository.createAccount(accountNr, name, balance);
    }

    public Double getBalance(String accountNr) {
        Boolean response = bankRepository.accountStatus(accountNr);
        if (!response) {
            return bankRepository.getBalance(accountNr);
        } else {
            return -1.0;
        }
    }

    public Double updateBalance(String accountNr, Double deposit) {
        Boolean response = bankRepository.accountStatus(accountNr);
        if (response) {
            return -1.0;
        } else if (deposit > 0) {
            Double newBalance = bankRepository.getBalance(accountNr) + deposit;
            bankRepository.updateBalance(accountNr, newBalance);
            return newBalance;
        } else {
            return -2.0;
        }
    }

    public Double withdrawMoney(String accountNr,Double withdrawamount) {
        Boolean response = bankRepository.accountStatus(accountNr);
        if (response) {
            return -1.0;
        } else if (withdrawamount < 0) {
            return -2.0;
        } else if (bankRepository.getBalance(accountNr) < withdrawamount) {
            return -3.0;
        } else {
            Double balanceAfterWithdraw = bankRepository.getBalance(accountNr) - withdrawamount;
            bankRepository.updateBalance(accountNr, balanceAfterWithdraw);
            return balanceAfterWithdraw;
        }
    }
}
