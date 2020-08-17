package ua.lviv.lgs.nl.simpleforum.service.mapper;

import org.mapstruct.Mapper;
import ua.lviv.lgs.nl.simpleforum.dto.SectionDTO;
import ua.lviv.lgs.nl.simpleforum.entity.Section;

@Mapper(componentModel = "spring")
public interface SectionMapper {
    SectionDTO toDto(Section section);

    Section toEntity (SectionDTO sectionDTO);
}
