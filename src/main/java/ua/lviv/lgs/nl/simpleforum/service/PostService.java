package ua.lviv.lgs.nl.simpleforum.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.lviv.lgs.nl.simpleforum.dto.PostDTO;
import ua.lviv.lgs.nl.simpleforum.dto.TopicDTO;
import ua.lviv.lgs.nl.simpleforum.entity.User;

import java.util.List;

public interface PostService {
    List<PostDTO> findAllByTopicId(Long topicId);

    Page<PostDTO> findAllByTopicId(Long topicId, Pageable pageable);

    void createPost(PostDTO postDTO, User user);
}
