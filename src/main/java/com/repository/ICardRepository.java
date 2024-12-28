package com.repository;

import com.model.BorrowCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICardRepository extends JpaRepository<BorrowCard, Long> {
    Optional<BorrowCard> findByCardNumber(String cardNumber);
}
