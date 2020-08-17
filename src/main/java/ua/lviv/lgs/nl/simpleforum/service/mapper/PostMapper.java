package ua.lviv.lgs.nl.simpleforum.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.lviv.lgs.nl.simpleforum.dto.PostDTO;
import ua.lviv.lgs.nl.simpleforum.entity.Post;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(source = "sectionId", target = "section.id")
    @Mapping(source = "topicId", target = "topic.id")
    Post toEntity(PostDTO postDTO);

    @Mapping(source = "creationDate", target = "creationDate", dateFormat = "yyyy-MM-dd, HH:mm")
    @Mapping(source = "section.id" , target = "sectionId")
    @Mapping(source = "topic.id" , target = "topicId")
    PostDTO toDto(Post post);
}
