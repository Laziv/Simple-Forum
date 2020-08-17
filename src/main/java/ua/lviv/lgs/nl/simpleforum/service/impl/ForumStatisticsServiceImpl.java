package ua.lviv.lgs.nl.simpleforum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.lgs.nl.simpleforum.dto.ForumStatisticsDTO;
import ua.lviv.lgs.nl.simpleforum.entity.User;
import ua.lviv.lgs.nl.simpleforum.repository.PostRepository;
import ua.lviv.lgs.nl.simpleforum.repository.SectionRepository;
import ua.lviv.lgs.nl.simpleforum.repository.TopicRepository;
import ua.lviv.lgs.nl.simpleforum.repository.UserRepository;
import ua.lviv.lgs.nl.simpleforum.service.ForumStatisticsService;
import ua.lviv.lgs.nl.simpleforum.service.mapper.UserMapper;

import java.util.Optional;

@Service
public class ForumStatisticsServiceImpl implements ForumStatisticsService {
    private final UserRepository userRepository;
    private final TopicRepository topicRepository;
    private final SectionRepository sectionRepository;
    private final PostRepository postRepository;

    @Autowired
    public ForumStatisticsServiceImpl(UserRepository userRepository, TopicRepository topicRepository, SectionRepository sectionRepository, PostRepository postRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.topicRepository = topicRepository;
        this.sectionRepository = sectionRepository;
        this.postRepository = postRepository;
    }

    @Override
    public ForumStatisticsDTO getForumStatistics() {
        ForumStatisticsDTO forumStatisticsDTO = new ForumStatisticsDTO();
        forumStatisticsDTO.setNumberOfUsers(userRepository.count());
        forumStatisticsDTO.setNumberOfPosts(postRepository.count());
        forumStatisticsDTO.setNumberOfSections(sectionRepository.count());
        forumStatisticsDTO.setNumberOfTopics(topicRepository.count());
        Optional<User> newestUser = userRepository.findTopByOrderByIdDesc();
        newestUser.ifPresent(user -> forumStatisticsDTO.setNewestUser(newestUser.get().getUsername()));
        return forumStatisticsDTO;
    }
}
