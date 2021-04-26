package ee.bcs.valiit.myServiceAndRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @Autowired
    private BankService bankService;


    //http://localhost:8080/repobank/createnewaccount?accountNr=1000&name=Kaie&balance=2000
    @CrossOrigin
    @PostMapping("/repobank/createnewaccount/{accountNr}/{name}/{balance}")
    public void createAccount(@PathVariable("accountNr") String accountNr,
                              @PathVariable("name") String name,
                              @PathVariable("balance") double balance) {
        bankService.createAccount(accountNr, name, balance);
    }

    //http://localhost:8080/repobank/account/1002
    @GetMapping("/repobank/account/{accountNr}")
    public Double getBalance(@PathVariable("accountNr") String accountNr) {
        return bankService.getBalance(accountNr);
    }

    //http://localhost:8080/repobank/deposit/1001/200
    @PutMapping("/repobank/deposit/{account}/{deposit}")
    public Double updateBalance(@PathVariable("account") String accountNr,
                                @PathVariable("deposit") Double deposit) {
        return bankService.updateBalance(accountNr, deposit);
    }

    //http://localhost:8080/repobank/withdraw/1000/200
    @PutMapping("/repobank/withdraw/{account}/{withdraw}")
    public Double withdrawMoney(@PathVariable("account") String accountNr,
                                @PathVariable("withdraw") Double withdrawamount) {
        return bankService.withdrawMoney(accountNr, withdrawamount);
    }

    //http://localhost:8080/repobank/transfer/1001/100/1002
    @PutMapping("/repobank/transfer/{fromAcc}/{amount}/{toAcc}")
    public String transferMoney(@PathVariable("fromAcc") String fromAccount,
                                @PathVariable("amount") Double transferAmount,
                                @PathVariable("toAcc") String toAccount) {
        return bankService.transferMoney(fromAccount, transferAmount, toAccount);
    }

    //http://localhost:8080/repobank/account/1001/lock
    @PutMapping("/repobank/account/{accountNumber}/lock")
    public String lock(@PathVariable("accountNumber") String accountNr) {
        return bankService.lock(accountNr);
    }

    //http://localhost:8080/repobank/account/1001/unlock
    @PutMapping("/repobank/account/{accountNumber}/unlock")
    public String unlock(@PathVariable("accountNumber") String accountNr) {
        return bankService.unlock(accountNr);
    }

//    @GetMapping("repobank/history/{accountNumber}")
//    public String transactionHistory(@PathVariable("accountNumber") String accountNr) {
//        return bankService.transactionHistory(accountNr);
//    }

}
