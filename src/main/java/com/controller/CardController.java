package com.controller;

import com.model.BorrowCard;
import com.service.IBookService;
import com.service.ICardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/borrow-cards")
public class CardController {
    @Autowired
    private ICardService cardService;
    @Autowired
    private IBookService bookService;

    @GetMapping
    public String listCards(Model model) {
        model.addAttribute("borrowCards", cardService.findAll());
        return "borrow_card/list";
    }

    @PostMapping("/remove")
    public String removeCard(@RequestParam("cardNumber") String cardNumber, Model model) {
        Optional<BorrowCard> card = cardService.findByCardNumber(cardNumber);
        if (!card.isPresent()) {
            return "error_card";
        }
        cardService.remove(card.get().getId());
        bookService.plusBookCount(card.get().getBook().getId());
        return "redirect:/borrow-cards";
    }
}
