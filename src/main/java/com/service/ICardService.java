package com.service;

import com.model.BorrowCard;

import java.util.Optional;

public interface ICardService extends IService<BorrowCard> {
    Optional<BorrowCard> findByCardNumber(String cardNumber);
}
