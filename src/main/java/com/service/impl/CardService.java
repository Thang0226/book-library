package com.service.impl;

import com.model.BorrowCard;
import com.repository.ICardRepository;
import com.service.ICardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardService implements ICardService {
    @Autowired
    private ICardRepository cardRepository;

    @Override
    public Iterable<BorrowCard> findAll() {
        return cardRepository.findAll();
    }

    @Override
    public void save(BorrowCard borrowCard) {
        cardRepository.save(borrowCard);
    }

    @Override
    public Optional<BorrowCard> findById(Long id) {
        return cardRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        cardRepository.deleteById(id);
    }

    @Override
    public Optional<BorrowCard> findByCardNumber(String cardNumber) {
        return cardRepository.findByCardNumber(cardNumber);
    }
}
