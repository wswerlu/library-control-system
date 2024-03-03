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
import org.library.dto.author.AuthorCreateDTO;
import org.library.dto.author.AuthorDTO;
import org.library.dto.author.AuthorUpdateDTO;
import org.library.domain.constants.StatusCode;
import org.library.model.response.EmptyResponse;
import org.library.model.response.ErrorResponse;
import org.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/authors")
@Tag(name = "author", description = "Operations about authors")
public class AuthorsController {

    @Autowired
    private AuthorService authorService;

    @GetMapping(path = "")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Find all authors")
    @ApiResponses(value = {
            @ApiResponse(responseCode = StatusCode.OK, content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON,
                            array = @ArraySchema(schema = @Schema(implementation = AuthorDTO.class)))
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
    public List<AuthorDTO> index() {
        return authorService.getAllAuthors();
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create author")
    @ApiResponses(value = {
            @ApiResponse(responseCode = StatusCode.CREATED, content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON, 
                            schema = @Schema(implementation = AuthorDTO.class))
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
    public AuthorDTO create(@Valid @RequestBody AuthorCreateDTO authorData) {
        return authorService.create(authorData);
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Find author by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = StatusCode.OK, content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON, 
                            schema = @Schema(implementation = AuthorDTO.class))
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
    public AuthorDTO show(@PathVariable @Parameter(description = "Author id") Long id) {
        return authorService.getAuthorById(id);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update author with id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = StatusCode.OK, content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON, 
                            schema = @Schema(implementation = AuthorDTO.class))
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
    public AuthorDTO update(@Valid @RequestBody AuthorUpdateDTO authorData,
                            @PathVariable @Parameter(description = "Author id") Long id) {
        return authorService.update(authorData, id);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete author by id")
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
    public Map<String, Object> delete(@PathVariable @Parameter(description = "Author id") Long id) {
        authorService.delete(id);

        return Collections.emptyMap();
    }
}
