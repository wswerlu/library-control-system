package org.library.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.library.domain.constants.MediaType;
import org.library.domain.constants.StatusCode;
import org.library.dto.book.BookCreateDTO;
import org.library.dto.book.BookDTO;
import org.library.dto.book.BookUpdateDTO;
import org.library.model.response.EmptyResponse;
import org.library.model.response.ErrorResponse;
import org.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
@Tag(name = "book", description = "Operations about books")
public class BooksController {

    @Autowired
    private BookService bookService;

    @GetMapping(path = "")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Find all books")
    @ApiResponses(value = {
            @ApiResponse(responseCode = StatusCode.OK, content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON,
                            array = @ArraySchema(schema = @Schema(implementation = BookDTO.class)))
            }),
            @ApiResponse(responseCode = StatusCode.BAD_REQUEST, content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = ErrorResponse.class))
            }),
            @ApiResponse(responseCode = StatusCode.NOT_FOUND, content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = ErrorResponse.class))
            }),
            @ApiResponse(responseCode = StatusCode.INTERNAL_ERROR, content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = ErrorResponse.class))
            })
    })
    public List<BookDTO> index() {
        return bookService.getAllBooks();
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = StatusCode.CREATED, content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = BookDTO.class))
            }),
            @ApiResponse(responseCode = StatusCode.BAD_REQUEST, content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = ErrorResponse.class))
            }),
            @ApiResponse(responseCode = StatusCode.NOT_FOUND, content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = ErrorResponse.class))
            }),
            @ApiResponse(responseCode = StatusCode.INTERNAL_ERROR, content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = ErrorResponse.class))
            })
    })
    public BookDTO create(@Valid @RequestBody BookCreateDTO bookData) {
        return bookService.create(bookData);
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Find book by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = StatusCode.OK, content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = BookDTO.class))
            }),
            @ApiResponse(responseCode = StatusCode.BAD_REQUEST, content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = ErrorResponse.class))
            }),
            @ApiResponse(responseCode = StatusCode.NOT_FOUND, content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = ErrorResponse.class))
            }),
            @ApiResponse(responseCode = StatusCode.INTERNAL_ERROR, content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = ErrorResponse.class))
            })
    })
    public BookDTO show(@PathVariable @Parameter(description = "Book id") Long id) {
        return bookService.getBookById(id);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update book with id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = StatusCode.OK, content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = BookDTO.class))
            }),
            @ApiResponse(responseCode = StatusCode.BAD_REQUEST, content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = ErrorResponse.class))
            }),
            @ApiResponse(responseCode = StatusCode.NOT_FOUND, content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = ErrorResponse.class))
            }),
            @ApiResponse(responseCode = StatusCode.INTERNAL_ERROR, content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = ErrorResponse.class))
            })
    })
    public BookDTO update(@Valid @RequestBody BookUpdateDTO bookData,
                          @PathVariable @Parameter(description = "Book id") Long id) {
        return bookService.update(bookData, id);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete book by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = StatusCode.OK, content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = EmptyResponse.class))
            }),
            @ApiResponse(responseCode = StatusCode.BAD_REQUEST, content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = ErrorResponse.class))
            }),
            @ApiResponse(responseCode = StatusCode.NOT_FOUND, content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = ErrorResponse.class))
            }),
            @ApiResponse(responseCode = StatusCode.INTERNAL_ERROR, content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = ErrorResponse.class))
            })
    })
    public Map<String, Object> delete(@PathVariable @Parameter(description = "Book id") Long id) {
        bookService.delete(id);

        return Collections.emptyMap();
    }
}
