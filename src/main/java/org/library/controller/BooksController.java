package org.library.controller;

import jakarta.validation.Valid;
import org.library.dto.book.BookCreateDTO;
import org.library.dto.book.BookDTO;
import org.library.dto.book.BookUpdateDTO;
import org.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BooksController {

    @Autowired
    private BookService bookService;

    @GetMapping(path = "")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDTO> index(){
        return bookService.getAllBooks();
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO create(@Valid @RequestBody BookCreateDTO bookData) {
        return bookService.create(bookData);
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookDTO show(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookDTO update(@Valid @RequestBody BookUpdateDTO bookData, @PathVariable Long id) {
        return bookService.update(bookData, id);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }
}
