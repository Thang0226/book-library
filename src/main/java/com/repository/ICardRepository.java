package com.repository;

import com.model.BorrowCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICardRepository extends JpaRepository<BorrowCard, Long> {
}
