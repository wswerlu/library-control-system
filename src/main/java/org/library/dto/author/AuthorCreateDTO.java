package org.library.dto.author;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthorCreateDTO {

    @NotBlank
    @Pattern(regexp = "[a-zA-Z-]*")
    private String firstName;

    @NotBlank
    @Pattern(regexp = "[a-zA-Z-]*")
    private String lastName;
}
