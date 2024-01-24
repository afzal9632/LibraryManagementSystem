package com.root.service;

import com.root.dto.AuthorDTO;
import com.root.dto.BookDTO;
import com.root.exception.AuthorException;
import com.root.model.Author;
import com.root.model.Book;
import com.root.repository.AuthorRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService{

    @Autowired
    AuthorRepo authorRepo;

    @Autowired
    ModelMapper modelMapper;


    @Override
    public List<AuthorDTO> findAllAuthors() {

        List<Author> authorList = authorRepo.findAll();

        if (authorList==null) throw new AuthorException("No Author found. Please add some authors first.");

        return authorList.stream()
                .map(this::convertToAuthorDTO)
                .collect(Collectors.toList());
    }


    @Override
    public List<AuthorDTO> findAuthorBornAfter(LocalDate date) {

        List<Author> authors = authorRepo.findAuthorBornAfter(date);

        if (authors.isEmpty()) throw new AuthorException("No author found born after: "+date);

        return authors.stream()
                .map(this::convertToAuthorDTO)
                .collect(Collectors.toList());
    }

    public AuthorDTO convertToAuthorDTO(Author author)
    {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO = modelMapper.map(author,AuthorDTO.class);

        return authorDTO;
    }
}
