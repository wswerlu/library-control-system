package org.library.service;

import org.library.dto.book.BookCreateDTO;
import org.library.dto.book.BookDTO;
import org.library.dto.book.BookUpdateDTO;
import org.library.exception.ResourceNotFoundException;
import org.library.mapper.BookMapper;
import org.library.repository.AuthorRepository;
import org.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookMapper bookMapper;

    public List<BookDTO> getAllBooks() {
        var books = bookRepository.findAll();

        return books.stream()
                .map(bookMapper::map)
                .toList();
    }

    public BookDTO create(BookCreateDTO bookData) {
        var book = bookMapper.map(bookData);
        bookRepository.save(book);

        return bookMapper.map(book);
    }

    public BookDTO getBookById(Long id) {
        var book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found"));

        return bookMapper.map(book);
    }

    public BookDTO update(BookUpdateDTO bookData, Long id) {
        var book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found"));
        var author = authorRepository.findById(bookData.getAuthorId().get())
                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));

        bookMapper.update(bookData, book);
        book.setAuthor(author);
        bookRepository.save(book);

        return bookMapper.map(book);
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}
