package com.berat.service;

import static com.berat.converter.AccountConverter.*;
import com.berat.dto.request.AccountCreateRequest;
import com.berat.dto.request.AccountDepositRequest;
import com.berat.dto.request.AccountWithdrawalRequest;
import com.berat.dto.response.AccountResponse;
import com.berat.exception.AccountManagerException;
import com.berat.exception.ErrorType;
import com.berat.model.Account;
import com.berat.model.Status;
import com.berat.rabbitmq.model.TransferModel;
import com.berat.rabbitmq.producer.PaymentProducer;
import com.berat.repository.IAccountRepository;
import com.berat.util.ServiceManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AccountService extends ServiceManager<Account,Long> {
    private final IAccountRepository accountRepository;
    private final PaymentProducer paymentProducer;

    public AccountService(IAccountRepository accountRepository, PaymentProducer paymentProducer) {
        super(accountRepository);
        this.accountRepository = accountRepository;
        this.paymentProducer = paymentProducer;
    }

    public void createAccount(AccountCreateRequest dto) {
        save(toAccount(dto));
    }

    public void deActivateById(Long accountId) {
        Optional<Account> account = findById(accountId);
        if (account.isEmpty())
            throw new AccountManagerException(ErrorType.ACCOUNT_NOT_FOUND);
        account.get().setStatus(Status.NON_ACTIVE);
        update(account.get());
    }

    public void deleteByAccountId(Long accountId) {
        Optional<Account> account = findById(accountId);
        if (account.isEmpty())
            throw new AccountManagerException(ErrorType.ACCOUNT_NOT_FOUND);
        deleteById(accountId);
    }

    public List<AccountResponse> getAllByCustomerId(Long customerId) {
        return accountRepository.findAllByCustomerId(customerId).stream()
                .map(a-> fromAccount(a)).toList();
    }

    public Boolean existByAccountId(Long accountId) {
        return accountRepository.existsById(accountId);
    }

    public AccountResponse getOneAccount(Long accountId) {
        return fromAccount(findById(accountId)
                .orElseThrow(()-> new AccountManagerException(ErrorType.ACCOUNT_NOT_FOUND)));
    }

    @Transactional
    public void transferMoney(TransferModel model){
        Optional<Account> account = findById(model.getAccountId());
        if (account.isEmpty())
            throw new AccountManagerException(ErrorType.ACCOUNT_NOT_FOUND);
        Account giveAccount = account.get();
        if (giveAccount.getBalance().compareTo(model.getQuantity()) == -1)
            throw new AccountManagerException(ErrorType.INSUFFICIENT_BALANCE);
        if (Objects.isNull(model.getCardId())){
            Optional<Account> account1 = accountRepository.findByIban(model.getDestinationIbanNumber());
            if (account1.isEmpty())
                throw new AccountManagerException(ErrorType.ACCOUNT_NOT_FOUND);
            Account takeAccount = account1.get();
            if (!giveAccount.getCurrency().equals(takeAccount.getCurrency()))
                throw new AccountManagerException(ErrorType.CURRENCIES_NOT_MATCH);
            giveAccount.setBalance(giveAccount.getBalance().subtract(model.getQuantity()));
            takeAccount.setBalance(takeAccount.getBalance().add(model.getQuantity()));
            try {
                update(giveAccount);
                update(takeAccount);
            }catch (Exception exception){
                throw new AccountManagerException(ErrorType.TRANSFER_FAILED);
            }
        }else {
            giveAccount.setBalance(giveAccount.getBalance().subtract(model.getQuantity()));
            try{
                update(giveAccount);
                paymentProducer.payment(toPaymentModel(model));
            }catch (Exception exception){
                throw new AccountManagerException(ErrorType.TRANSFER_FAILED);
            }
        }

    }

    public void depositByAccountId(AccountDepositRequest dto) {
        Optional<Account> account = findById(dto.getAccountId());
        if (account.isEmpty())
            throw new AccountManagerException(ErrorType.ACCOUNT_NOT_FOUND);
        Account toUpdate = account.get();
        toUpdate.setBalance(toUpdate.getBalance().add(dto.getQuantity()));
        update(toUpdate);
    }

    public void withdrawalByAccountId(AccountWithdrawalRequest dto) {
        Optional<Account> account = findById(dto.getAccountId());
        if (account.isEmpty())
            throw new AccountManagerException(ErrorType.ACCOUNT_NOT_FOUND);
        Account toUpdate = account.get();
        toUpdate.setBalance(toUpdate.getBalance().subtract(dto.getQuantity()));
        update(toUpdate);
    }
}
