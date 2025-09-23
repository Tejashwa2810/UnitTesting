package com.example.Project1.Repository;

import com.example.Project1.Models.Books;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Books, Integer> {
    Page<Books> findByTitle(String title, Pageable pageable);
}
