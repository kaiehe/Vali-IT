package ee.bcs.valiit.myServiceAndRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @Autowired
    private BankService bankService;

    //http://localhost:8080/repobank/createnewaccount?accountNr=1009&name=Kati&balance=2000
    @PostMapping("/repobank/createnewaccount")
    public void createAccount(@RequestParam("accountNr") String accountNr,
                              @RequestParam("name") String name,
                              @RequestParam("balance") double balance) {
        bankService.createAccount(accountNr, name, balance);
    }

    //http://localhost:8080/repobank/account/1002
    @GetMapping("/repobank/account/{accountNumber}")
    public Double getBalance(@PathVariable("accountNumber") String accountNr) {
        return bankService.getBalance(accountNr);
    }

    //http://localhost:8080/repobank/deposit/1001/200
    @PutMapping("/repobank/deposit/{account}/{deposit}")
    public Double updateBalance(@PathVariable("account") String accountNr, @PathVariable("deposit") Double deposit) {
    return bankService.updateBalance(accountNr, deposit);
    }

    //http://localhost:8080/bank/withdraw/1000/200
    @PutMapping("/repobank/withdraw/{account}/{withdraw}")
    public Double withdrawMoney(@PathVariable("account") String accountNr, @PathVariable("withdraw") Double withdrawamount) {
        return bankService.withdrawMoney(accountNr, withdrawamount);
    }
}
