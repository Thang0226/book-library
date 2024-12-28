package com.controller;

import com.model.Book;
import com.model.BorrowCard;
import com.service.IBookService;
import com.service.ICardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.smartcardio.Card;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private IBookService bookService;
    @Autowired
    private ICardService cardService;

    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "book/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("book", new Book());
        return "book/create";
    }

    @PostMapping("/save")
    public String saveBook(Book book) {
        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/view/{id}")
    public String showViewForm(@PathVariable("id") Long id, Model model) {
        Optional<Book> book = bookService.findById(id);
        model.addAttribute("book", book.get());
        return "book/view";
    }

    @GetMapping("/borrow/{id}")
    public String showBorrowForm(@PathVariable("id") Long id, Model model) {
        Optional<Book> bookOptional = bookService.findById(id);
        Book book = bookOptional.get();
        int currentCount = book.getCount();
        book.setCount(currentCount - 1);
        bookService.save(book);

        String cardNumberString = getCardNumber();
        BorrowCard borrowCard = new BorrowCard(book, cardNumberString);
        cardService.save(borrowCard);
        return "redirect:/borrow-cards";
    }

    private String getCardNumber() {
        String cardNumberString;
        Optional<BorrowCard> card;
        do {
            int cardNumber = (int) (Math.random() * 100000);
            cardNumberString = String.format("%05d", cardNumber);
            card = cardService.findByCardNumber(cardNumberString);
        } while (card.isPresent());
        return cardNumberString;
    }
}
