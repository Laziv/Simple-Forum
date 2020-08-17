package ua.lviv.lgs.nl.simpleforum.service;

import ua.lviv.lgs.nl.simpleforum.dto.SectionDTO;
import ua.lviv.lgs.nl.simpleforum.dto.TopicDTO;
import ua.lviv.lgs.nl.simpleforum.entity.Topic;
import ua.lviv.lgs.nl.simpleforum.entity.User;

import java.util.List;

public interface TopicService {
    List<TopicDTO> findAllBySectionId(Long sectionId);

    void createTopic(TopicDTO topicDTO, User user);

    TopicDTO findById(Long id);
}
