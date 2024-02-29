package org.library.controller;

import jakarta.validation.Valid;
import org.library.dto.author.AuthorCreateDTO;
import org.library.dto.author.AuthorDTO;
import org.library.dto.author.AuthorUpdateDTO;
import org.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorsController {

    @Autowired
    private AuthorService authorService;

    @GetMapping(path = "")
    @ResponseStatus(HttpStatus.OK)
    public List<AuthorDTO> index(){
        return authorService.getAllAuthors();
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorDTO create(@Valid @RequestBody AuthorCreateDTO authorData) {
        return authorService.create(authorData);
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AuthorDTO show(@PathVariable Long id) {
        return authorService.getAuthorById(id);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AuthorDTO update(@Valid @RequestBody AuthorUpdateDTO authorData, @PathVariable Long id) {
        return authorService.update(authorData, id);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        authorService.delete(id);
    }
}
