package com.root.controller;

import com.root.dto.AuthorDTO;
import com.root.model.Author;
import com.root.model.Book;
import com.root.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@Tag(
        name = "CRUD REST APIs for Author Resource",
        description = "CRUD REST APIs - Get All authors, Get all Authors born after certain date"
)
@RequestMapping("/api/authors")
@RestController
public class AuthorController {

    @Autowired
    AuthorService authorService;



    @Operation(
            summary = "Get all Authors REST API",
            description = "Get All Author REST API is used to get all authors from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping()
    public ResponseEntity<List<AuthorDTO>> getAllAuthors()
    {
        List<AuthorDTO> authorList = authorService.findAllAuthors();

        return new ResponseEntity<>(authorList, HttpStatus.OK);
    }



    @Operation(
            summary = "Get all Author born after REST API",
            description = "Get All authors born after REST API is used to get all authors from the database whose date of birth is after certain date"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping("/{date}")
    public ResponseEntity<List<AuthorDTO>> getAllAuthorsBornAfter(@PathVariable LocalDate date)
    {
        List<AuthorDTO> authorList = authorService.findAuthorBornAfter(date);

        return new ResponseEntity<>(authorList, HttpStatus.OK);
    }

}
