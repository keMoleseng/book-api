package com.keMoleseng.bookapiv2.repository;

import com.keMoleseng.bookapiv2.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}
