package com.keMoleseng.bookapiv2.controller;

import com.keMoleseng.bookapiv2.exceptions.BookNotFoundException;
import com.keMoleseng.bookapiv2.model.Book;
import com.keMoleseng.bookapiv2.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/v2/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    //create book
    @PostMapping("/createBook")
    public Book createBook(@RequestBody Book book) {
        return (Book) bookRepository.save(book);
    }

    //read all
    @GetMapping("/allBooks")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    //read one
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Book>> getBook(@PathVariable int id) {
        Optional<Book> book = null;
        try {
            book = bookRepository.findById(id);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(book);
    }

    //update book
    @PutMapping("/updatebook/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable int id, @RequestBody Book book) {

        Optional<Book> editBook = bookRepository.findById(id);
        Book editThisBook = null;

        if(editBook.isPresent()){
            editThisBook = editBook.get();
            editThisBook.setTitle(book.getTitle());
            editThisBook.setAuthor(book.getAuthor());
            editThisBook.setPublisher(book.getPublisher());
            editThisBook.setGenre(book.getPublisher());
            editThisBook.setYearReleased(book.getYearReleased());
        }
        else {
            throw new BookNotFoundException("Book with ID: "+id+ " not found.");
        }

        return ResponseEntity.ok(editThisBook);

    }

    //delete book
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteBook(@PathVariable int id) {

        Optional<Book> removeBook = bookRepository.findById(id);
        Map<String, Boolean> response = null;

        if(removeBook.isPresent()) {
            Book removeThisBook = removeBook.get();
            bookRepository.delete(removeThisBook);
            response = new HashMap<>();
            response.put("deleted", Boolean.TRUE);
        }
        else {
            throw new BookNotFoundException("Book with ID: "+id+ " not found.");
        }

        return ResponseEntity.ok(response);
    }


}


