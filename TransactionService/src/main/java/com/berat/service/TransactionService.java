package com.berat.service;

import static com.berat.converter.TransactionConverter.*;

import com.berat.converter.TransactionConverter;
import com.berat.dto.request.PaymentTransactionRequest;
import com.berat.dto.request.TransferTransactionRequest;
import com.berat.dto.response.TransactionResponse;
import com.berat.exception.ErrorType;
import com.berat.exception.TransactionManagerException;
import com.berat.model.Transaction;
import com.berat.rabbitmq.producer.TransferProducer;
import com.berat.repository.ITransactionRepository;
import com.berat.util.ServiceManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class TransactionService extends ServiceManager<Transaction,String> {
    private final ITransactionRepository transactionRepository;
    private final TransferProducer transferProducer;

    public TransactionService(ITransactionRepository transactionRepository, TransferProducer transferProducer) {
        super(transactionRepository);
        this.transactionRepository = transactionRepository;
        this.transferProducer = transferProducer;
    }

    @Transactional
    public void createTransferTransaction(TransferTransactionRequest dto) {
        try {
            transferProducer.transfer(toTransferModel(dto));
            save(toTransaction(dto));
        }catch (Exception exception){
            throw new TransactionManagerException(ErrorType.TRANSACTION_NOT_CREATED);
        }
    }

    @Transactional
    public void createPaymentTransaction(PaymentTransactionRequest dto) {
        try {
            transferProducer.transfer(toTransferModel(dto));
            save(toTransaction(dto));
        }catch (Exception exception){
            throw new TransactionManagerException(ErrorType.TRANSACTION_NOT_CREATED);
        }
    }

    public Slice<TransactionResponse> getAllByAccountId(Long accountId) {
        Sort sort = Sort.by(Sort.Direction.fromString("DESC"),"createDate");
        Pageable pageable = PageRequest.of(0,10,sort);
        return transactionRepository.findByAccountId(accountId, pageable).map(TransactionConverter::toTransactionResponse);
    }

    public TransactionResponse getOneByTransactionId(String transactionId) {
        return toTransactionResponse(findById(transactionId)
                .orElseThrow(()-> new TransactionManagerException(ErrorType.TRANSACTION_NOT_FOUND)));
    }
}
