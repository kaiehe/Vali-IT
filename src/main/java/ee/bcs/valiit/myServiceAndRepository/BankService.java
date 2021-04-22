package ee.bcs.valiit.myServiceAndRepository;

import ee.bcs.valiit.MyHibernate.Account;
import ee.bcs.valiit.MyHibernate.AccountRepository;
import ee.bcs.valiit.myExceptions.MyApplicationException;
import ee.bcs.valiit.solution.exception.SampleApplicationException;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@org.springframework.stereotype.Service
public class BankService {

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private AccountRepository hibernateAccountRepository;

    public void createAccount(String accountNr, String name, double balance) {
        bankRepository.createAccount(accountNr, name, balance);
        Double updatedBalance = balance;
        bankRepository.transactionHistory(LocalDateTime.now(), accountNr, balance, updatedBalance);
    }

    public Double getBalance(String accountNr) {
        Boolean response = bankRepository.accountStatus(accountNr);
        if (!response) {
            Account accountNumber  = hibernateAccountRepository.getOne(accountNr);
            return bankRepository.getBalance(accountNr);
        } else {
            return -1.0;
        }
    }

    public Double updateBalance(String accountNr, Double deposit) {
        Boolean response = bankRepository.accountStatus(accountNr);
        if (response) {
            throw new MyApplicationException("Konto on blokeeritud, tehingute tegemine keelatud.");
        } else if (deposit > 0) {
            Double newBalance = bankRepository.getBalance(accountNr) + deposit;
            bankRepository.updateBalance(accountNr, newBalance);
            bankRepository.transactionHistory(LocalDateTime.now(), accountNr, deposit, newBalance);
            return newBalance;
        } else {
            return -2.0;
        }
    }

    public Double withdrawMoney(String accountNr, Double withdrawamount) {
        Boolean response = bankRepository.accountStatus(accountNr);
        if (response) {
            throw new MyApplicationException("Konto on blokeeritud, tehingute tegemine keelatud.");
        } else if (withdrawamount < 0) {
            return -2.0;
        } else if (bankRepository.getBalance(accountNr) < withdrawamount) {
            return -3.0;
        } else {
            Double balanceAfterWithdraw = bankRepository.getBalance(accountNr) - withdrawamount;
            bankRepository.updateBalance(accountNr, balanceAfterWithdraw);
            bankRepository.transactionHistoryWithdraw(LocalDateTime.now(), accountNr, withdrawamount, balanceAfterWithdraw);
            return balanceAfterWithdraw;
        }
    }

    public String transferMoney(String fromAccount, Double transferAmount, String toAccount) {
        Boolean fromStatus = bankRepository.accountStatus(fromAccount);
        Boolean toStatus = bankRepository.accountStatus(toAccount);
        if (fromStatus) {
            throw new MyApplicationException("Konto on blokeeritud, tehingute tegemine keelatud.");
        } else if (toStatus) {
            throw new MyApplicationException("Konto, kuhu soovite raha kanda, on blokeeritud.");
        } else if (bankRepository.getBalance(fromAccount) < transferAmount) {
            throw new MyApplicationException("Kontol puudub piisavalt vabu vahendeid");
        } else if (transferAmount > 0) {
            Double balanceAfterForFrom = bankRepository.getBalance(fromAccount) - transferAmount;
            Double balanceAfterForTo = bankRepository.getBalance(toAccount) + transferAmount;
            bankRepository.updateBalance(fromAccount, balanceAfterForFrom);
            bankRepository.updateBalance(toAccount, balanceAfterForTo);
            return "Ülekanne teostatud. Kontolt " + fromAccount + " kanti " + transferAmount + " kontole nr " + toAccount +
                    ". Konto jääk peale ülekannet: " + balanceAfterForFrom + ". Konto " + toAccount + " kontoseis peale ülekannet " +
                    balanceAfterForTo;
        } else {
            return "Ülekande summa ei saa olla väiksem kui 0 EUR. Kontrolli andmeid!";
        }
    }

    public String lock(String accountNr) {
        bankRepository.lock(accountNr);
        return "Konto on blokeeritud";
    }

    public String unlock(String accountNr) {
        bankRepository.unlock(accountNr);
        return "Konto on lahti tehtud";
    }


}
