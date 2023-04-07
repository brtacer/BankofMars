package com.berat.service;

import static com.berat.converter.CardConverter.*;
import com.berat.dto.request.CreateCardRequest;
import com.berat.dto.request.PaymentRequest;
import com.berat.dto.request.UpdateLimitRequest;
import com.berat.dto.response.CardResponse;
import com.berat.exception.CardManagerException;
import com.berat.exception.ErrorType;
import com.berat.manager.IAccountManager;
import com.berat.model.Card;
import com.berat.model.Status;
import com.berat.rabbitmq.model.PaymentModel;
import com.berat.repository.ICardRepository;
import com.berat.util.ServiceManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CardService extends ServiceManager<Card,String> {
    private final ICardRepository cardRepository;
    private final IAccountManager accountManager;

    public CardService(ICardRepository cardRepository, IAccountManager accountManager) {
        super(cardRepository);
        this.cardRepository = cardRepository;
        this.accountManager = accountManager;
    }

    public Boolean createCard(CreateCardRequest dto) {
        if (!accountManager.existByAccountId(dto.getAccountId()).getBody())
            throw new CardManagerException(ErrorType.ACCOUNT_NOT_FOUND);
        save(toCard(dto));
        return true;
    }
    public List<CardResponse> getAllByAccountId(Long accountId) {
        if (!accountManager.existByAccountId(accountId).getBody())
            throw new CardManagerException(ErrorType.ACCOUNT_NOT_FOUND);
        return cardRepository.findAllByAccountId(accountId).stream()
                .map(card -> toCardResponse(card)).toList();
    }

    public void debtPayment(PaymentModel model){
        Optional<Card> card = findById(model.getCardId());
        if (card.isEmpty())
            throw new CardManagerException(ErrorType.CARD_NOT_FOUND);
        card.get().setDebt(card.get().getDebt().subtract(model.getQuantity()));
        try {
            update(card.get());
        }catch (Exception exception){
            throw new CardManagerException(ErrorType.PAYMENT_FAILED);
        }
    }

    public Boolean paymentByCard(PaymentRequest dto) {
        Optional<Card> card = findById(dto.getCardId());
        if (card.isEmpty())
            throw new CardManagerException(ErrorType.CARD_NOT_FOUND);
        Card toPayment = card.get();
        if (!toPayment.getStatus().equals(Status.ACTIVE))
            throw new CardManagerException(ErrorType.CARD_NOT_ACTIVE);
        if (!toPayment.getCVV().equals(dto.getCVV()))
            throw new CardManagerException(ErrorType.INVALID_PARAMETER);
        if (toPayment.getBoundary().subtract(toPayment.getDebt()).compareTo(dto.getQuantity())==-1)
            throw new CardManagerException(ErrorType.INSUFFICIENT_LIMIT);
        toPayment.setDebt(toPayment.getDebt().add(dto.getQuantity()));
        update(toPayment);
        return true;

    }
    public Boolean blockById(String cardId) {
        Optional<Card> card = findById(cardId);
        if (card.isEmpty())
            throw new CardManagerException(ErrorType.CARD_NOT_FOUND);
        card.get().setStatus(Status.BLOCKED);
        update(card.get());
        return true;
    }

    public Boolean deleteByCardId(String cardId) {
        Optional<Card> card = findById(cardId);
        if (card.isEmpty())
            throw new CardManagerException(ErrorType.CARD_NOT_FOUND);
        deleteById(cardId);
        return true;
    }


    public void updateCardLimit(UpdateLimitRequest dto) {
        Optional<Card> card = findById(dto.getCardId());
        if (card.isEmpty())
            throw new CardManagerException(ErrorType.CARD_NOT_FOUND);
        card.get().setBoundary(dto.getNewLimit());
        update(card.get());
    }

    public CardResponse getOneByCardId(String cardId) {
        return toCardResponse(findById(cardId).orElseThrow(()-> new CardManagerException(ErrorType.CARD_NOT_FOUND)));
    }
}
