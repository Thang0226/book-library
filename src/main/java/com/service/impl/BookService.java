package com.service.impl;

import com.model.Book;
import com.repository.IBookRepository;
import com.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService implements IBookService {
    @Autowired
    private IBookRepository bookRepository;

    @Override
    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Book minusBookCount(Long id) {
        Optional<Book> bookOptional = findById(id);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            int currentCount = book.getCount();
            if (currentCount == 0) {
                return null;
            }
            book.setCount(currentCount - 1);
            save(book);
            return book;
        }
        return null;
    }

    @Override
    public Book plusBookCount(Long id) {
        Optional<Book> bookOptional = findById(id);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            int currentCount = book.getCount();
            book.setCount(currentCount + 1);
            save(book);
            return book;
        }
        return null;
    }
}
