package org.library.dto.author;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

@Setter
@Getter
public class AuthorUpdateDTO {

    @Pattern(regexp = "[a-zA-Z-]*")
    private JsonNullable<String> firstName;


    @Pattern(regexp = "[a-zA-Z-]*")
    private JsonNullable<String> lastName;
}
