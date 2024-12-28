package com.controller;

import com.service.ICardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/borrow-cards")
public class CardController {
    @Autowired
    private ICardService cardService;

    @GetMapping
    public String listCards(Model model) {
        model.addAttribute("borrowCards", cardService.findAll());
        return "borrow_card/list";
    }
}
