package com.service;

import com.model.Book;

public interface IBookService extends IService<Book> {
    Book minusBookCount(Long id);

    Book plusBookCount(Long id);
}
