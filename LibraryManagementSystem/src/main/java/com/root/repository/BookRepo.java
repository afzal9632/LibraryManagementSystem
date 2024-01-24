package com.root.repository;

import com.root.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book,Integer> {

    public List<Book> findByTitle(String title);


    @Query(value = "select * from Book b inner join Author a on b.book_id=a.id AND a.name=?1",
    nativeQuery = true)
    public List<Book> findByAuthorName(String name);
}
