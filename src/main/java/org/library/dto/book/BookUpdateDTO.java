package org.library.dto.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

@Setter
@Getter
public class BookUpdateDTO {
    private JsonNullable<Long> authorId;
    private JsonNullable<String> title;
}
