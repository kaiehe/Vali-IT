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

    public Double withdrawMoney(String accountNr, Double withdrawamount) {
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

    public String transferMoney(String fromAccount, Double transferAmount, String toAccount) {
        Boolean fromStatus = bankRepository.accountStatus(fromAccount);
        Boolean toStatus = bankRepository.accountStatus(toAccount);
        if (fromStatus) {
            return "Konto, millelt proovite ülekannet teha, on blokeeritud.Tehingute tegemine keelatud.";
        } else if (toStatus) {
            return "Konto, kuhu proovite raha kanda, on blokeeritud.Tehingute tegemine keelatud.";
        } else if (bankRepository.getBalance(fromAccount) < transferAmount) {
            return "Kontol puudub piisavalt vahendeid";
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
