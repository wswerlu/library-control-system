package org.library.mapper;

import org.library.dto.author.AuthorCreateDTO;
import org.library.dto.author.AuthorDTO;
import org.library.dto.author.AuthorUpdateDTO;
import org.library.model.Author;
import org.mapstruct.*;

@Mapper(
        uses = {JsonNullableMapper.class, ReferenceMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class AuthorMapper {
    public abstract Author map(AuthorCreateDTO dto);

    public abstract AuthorDTO map(Author model);

    public abstract void update(AuthorUpdateDTO dto, @MappingTarget Author model);
}
