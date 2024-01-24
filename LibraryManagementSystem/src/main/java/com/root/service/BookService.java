package com.root.service;

import com.root.dto.BookDTO;
import com.root.model.Book;

import java.util.List;

public interface BookService {

    public List<BookDTO> getAllBooks();

    public  BookDTO getBookById(Integer bookId);

    public Book addBook(Book book);

    public Book updateBook(Integer id,Book book);

    public BookDTO deleteBook(Integer bookId);

    public List<BookDTO> findBookByTitle(String title);

    public List<BookDTO> findBookByAuthor(String author);
}
