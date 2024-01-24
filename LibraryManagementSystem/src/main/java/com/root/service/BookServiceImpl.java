package com.root.service;

import com.root.dto.BookDTO;
import com.root.exception.BookException;
import com.root.model.Book;
import com.root.repository.BookRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService{


    @Autowired
    BookRepo bookRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<BookDTO> getAllBooks() {

     List<Book> bookList = bookRepo.findAll();

     if (bookList==null) throw new BookException("No book found. Please add some book first.");

      return bookList.stream()
              .map(this::convertToBookDTO)
              .collect(Collectors.toList());

    }

    @Override
    public BookDTO getBookById(Integer bookId) {

       Book book = bookRepo.findById(bookId).orElseThrow(()->new BookException("Book not found by given id: "+bookId));

        return convertToBookDTO(book);
    }

    @Override
    public Book addBook(Book book) {

        Boolean flag = isnbValidator(book.getIsbn());

        if (!flag) throw new BookException("Please enter valid isnb.");

      return  bookRepo.save(book);

    }

    public Boolean isnbValidator(String isnb)
    {
        String replaceString =isnb.trim().replaceAll("-","");

        Integer len = replaceString.length();

       if(len !=10 && len!=13)
           return false;

       int[] arr = Arrays.stream(replaceString.split("")).mapToInt(Integer::parseInt).toArray();


       int sum = 0;
       int pointer = 10;

        if(len==10){

            for(int i=0;i<len;i++)
            {
                sum += pointer*arr[i];
                pointer--;
            }

            if (sum%11==0) return true;
            else return false;
        }
        else {

            for (int i=0;i<len;i++)
            {
                if(i%2==0)
                sum += arr[i];
                else sum += arr[i]*3;
            }

            if(sum%10==0) return true;
            else return false;
        }


    }

    @Override
    public Book updateBook(Integer id,Book book) {

        Book oldbook = bookRepo.findById(id).orElseThrow(()->new BookException("Book not found by given id: "+id));

        Boolean flag = isnbValidator(book.getIsbn());

        if (!flag) throw new BookException("Please enter valid isnb.");

        book.setBookId(id);

        Book updatedBook = bookRepo.save(book);

        return updatedBook;
    }

    @Override
    public BookDTO deleteBook(Integer bookId) {

        Book book = bookRepo.findById(bookId).orElseThrow(()->new BookException("Book not found by given id: "+bookId));

        bookRepo.deleteById(bookId);

        return convertToBookDTO(book);
    }

    @Override
    public List<BookDTO> findBookByTitle(String title) {

        List<Book> bookList = bookRepo.findByTitle(title);

        if (bookList.isEmpty()) throw new BookException("No book found by title: "+title);

        return bookList.stream()
                .map(this::convertToBookDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDTO> findBookByAuthor(String author) {

       List<Book> bookList = bookRepo.findByAuthorName(author);

       if (bookList.isEmpty()) throw new BookException("No author found by name: "+author);

       return bookList.stream()
               .map(this::convertToBookDTO)
               .collect(Collectors.toList());


    }

    public BookDTO convertToBookDTO(Book book)
    {
        BookDTO bookDTO = new BookDTO();
        bookDTO = modelMapper.map(book,BookDTO.class);

        return bookDTO;
    }
}
