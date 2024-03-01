package org.library.dto.author;

import lombok.Getter;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

@Setter
@Getter
public class AuthorUpdateDTO {
    private JsonNullable<String> firstName;
    private JsonNullable<String> lastName;
}
