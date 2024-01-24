package com.root.repository;

import com.root.model.Author;
import com.root.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AuthorRepo extends JpaRepository<Author,Integer> {


    @Query(value = "select * from Author a where a.birth_date>?1",
            nativeQuery = true)
    public List<Author> findAuthorBornAfter(LocalDate date);
}
