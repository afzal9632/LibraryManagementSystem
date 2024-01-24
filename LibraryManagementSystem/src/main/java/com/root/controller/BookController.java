package com.root.controller;

import com.root.dto.BookDTO;
import com.root.model.Book;
import com.root.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(
        name = "CRUD REST APIs for Book Resource",
        description = "CRUD REST APIs - Add book, Update book, Get book by id, Get All books, Delete book by id, Get book by title, Get book by Author name"
)
@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    BookService bookService;


    @Operation(
            summary = "Add book REST API",
            description = "Add REST API is used to save book in a database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PostMapping()
    public ResponseEntity<Book> addBook(@Valid @RequestBody Book book)
    {
        Book addedbook = bookService.addBook(book);

        return new ResponseEntity<>(addedbook, HttpStatus.OK);

    }



    @Operation(
            summary = "Get all Book REST API",
            description = "Get All Book REST API is used to get all Books from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping()
    public ResponseEntity<List<BookDTO>> getAllBooks()
    {
        List<BookDTO> bookList = bookService.getAllBooks();

        return new ResponseEntity<>(bookList,HttpStatus.FOUND);
    }


    @Operation(
            summary = "Get Book REST API",
            description = "Get Book by id REST API is used to get Books from the database using book id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Integer id)
    {
        BookDTO bookDTO = bookService.getBookById(id);

        return new ResponseEntity<>(bookDTO,HttpStatus.FOUND);
    }


    @Operation(
            summary = "Delete Book by Id REST API",
            description = "Delete Bookr by Id REST API is used to delete specific Bookr from the database using book_Id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<BookDTO> deleteBook(@PathVariable Integer id)
    {
        BookDTO deletedBook = bookService.deleteBook(id);

        return new ResponseEntity<>(deletedBook,HttpStatus.OK);
    }



    @Operation(
            summary = "Update Bookr by Id REST API",
            description = "Update book by Id REST API is used to Update specific book in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Integer id,
                                           @RequestBody Book book)
    {

        Book updatedBook = bookService.updateBook(id,book);

        return new ResponseEntity<>(updatedBook,HttpStatus.OK);
    }



    @Operation(
            summary = "Get Book by title REST API",
            description = "Get book by title REST API is used to get book from the database using book title"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping("/title/{title}")
    public ResponseEntity<List<BookDTO>> getBooksByTitle(@PathVariable String title)
    {
        List<BookDTO> bookList = bookService.findBookByTitle(title);

        return new ResponseEntity<>(bookList,HttpStatus.FOUND);
    }



    @Operation(
            summary = "Get Books by Author name REST API",
            description = "Get book by author name REST API is used to get books from the database using author name"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping("/author/{author}")
    public ResponseEntity<List<BookDTO>> getBooksByAuthor(@PathVariable String author)
    {
        List<BookDTO> bookList = bookService.findBookByAuthor(author);

        return new ResponseEntity<>(bookList,HttpStatus.FOUND);
    }

}
