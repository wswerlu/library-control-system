package org.library.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "book", description = "Operations about books")
public class BooksController {

    @Autowired
    private BookService bookService;

    @GetMapping(path = "")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Find all books")
    public List<BookDTO> index(){
        return bookService.getAllBooks();
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create book")
    public BookDTO create(@Valid @RequestBody BookCreateDTO bookData) {
        return bookService.create(bookData);
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Find book by id")
    public BookDTO show(@PathVariable @Parameter(description = "Book id") Long id) {
        return bookService.getBookById(id);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update book with id")
    public BookDTO update(@Valid @RequestBody BookUpdateDTO bookData,
                          @PathVariable @Parameter(description = "Book id") Long id) {
        return bookService.update(bookData, id);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete book by id")
    public void delete(@PathVariable @Parameter(description = "Book id") Long id) {
        bookService.delete(id);
    }
}
