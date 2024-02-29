package org.library.service;

import org.library.dto.author.AuthorCreateDTO;
import org.library.dto.author.AuthorDTO;
import org.library.dto.author.AuthorUpdateDTO;
import org.library.exception.ResourceNotFoundException;
import org.library.mapper.AuthorMapper;
import org.library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorMapper authorMapper;

    public List<AuthorDTO> getAllAuthors() {
        var authors = authorRepository.findAll();

        return authors.stream()
                .map(authorMapper::map)
                .toList();
    }

    public AuthorDTO create(AuthorCreateDTO authorData) {
        var author = authorMapper.map(authorData);
        authorRepository.save(author);

        return authorMapper.map(author);
    }

    public AuthorDTO getAuthorById(Long id) {
        var author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author with id " + id + " not found"));

        return authorMapper.map(author);
    }

    public AuthorDTO update(AuthorUpdateDTO authorData, Long id) {
        var author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author with id " + id + " not found"));

        authorMapper.update(authorData, author);
        authorRepository.save(author);

        return authorMapper.map(author);
    }

    public void delete(Long id) {
        authorRepository.deleteById(id);
    }
}
