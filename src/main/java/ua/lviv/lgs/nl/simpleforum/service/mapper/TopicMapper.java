package ua.lviv.lgs.nl.simpleforum.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.lviv.lgs.nl.simpleforum.dto.TopicDTO;
import ua.lviv.lgs.nl.simpleforum.entity.Topic;

@Mapper(componentModel = "spring")
public interface TopicMapper {

    @Mapping(source = "section.id" , target = "sectionId")
    @Mapping(source = "creationDate", target = "creationDate", dateFormat = "yyyy-MM-dd, HH:mm")
    TopicDTO toDto(Topic topic);

    @Mapping(source = "sectionId", target = "section.id")
    Topic toEntity (TopicDTO topicDTO);


}
