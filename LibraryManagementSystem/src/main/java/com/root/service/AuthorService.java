package com.root.service;

import com.root.dto.AuthorDTO;
import com.root.model.Author;

import java.time.LocalDate;
import java.util.List;

public interface AuthorService {

    public List<AuthorDTO> findAllAuthors();

    public  List<AuthorDTO> findAuthorBornAfter(LocalDate date);
}
