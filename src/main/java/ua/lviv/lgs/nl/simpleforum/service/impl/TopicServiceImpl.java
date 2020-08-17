package ua.lviv.lgs.nl.simpleforum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.lgs.nl.simpleforum.dto.PostDTO;
import ua.lviv.lgs.nl.simpleforum.dto.TopicDTO;
import ua.lviv.lgs.nl.simpleforum.entity.Post;
import ua.lviv.lgs.nl.simpleforum.entity.Topic;
import ua.lviv.lgs.nl.simpleforum.entity.User;
import ua.lviv.lgs.nl.simpleforum.exception.ResourceNotFoundException;
import ua.lviv.lgs.nl.simpleforum.repository.PostRepository;
import ua.lviv.lgs.nl.simpleforum.repository.TopicRepository;
import ua.lviv.lgs.nl.simpleforum.service.TopicService;
import ua.lviv.lgs.nl.simpleforum.service.mapper.PostMapper;
import ua.lviv.lgs.nl.simpleforum.service.mapper.TopicMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TopicServiceImpl implements TopicService {
    private final TopicRepository topicRepository;
    private final TopicMapper topicMapper;
    private final PostRepository postRepository;
    private final PostMapper postMapper;


    @Autowired
    public TopicServiceImpl(TopicRepository topicRepository, TopicMapper topicMapper, PostRepository postRepository, PostMapper postMapper) {
        this.topicRepository = topicRepository;
        this.topicMapper = topicMapper;
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    @Override
    public List<TopicDTO> findAllBySectionId(Long sectionId) {
        return topicRepository.findAllBySectionIdOrderByCreationDateDesc(sectionId).stream().map(
                (e) -> {
                    int replies = postRepository.countPostsByTopicId(e.getId());
                    TopicDTO topicDTO = topicMapper.toDto(e);
                    topicDTO.setNumberOfReplies(replies - 1);

                    Optional<Post> lastPost =  postRepository.findFirstByTopicIdOrderByCreationDateDesc(e.getId());
                    if (lastPost.isPresent()){
                        PostDTO lastPostDTO = postMapper.toDto(lastPost.get());
                        topicDTO.setLastPost(lastPostDTO);
                    }
                    return topicDTO;
                }
        ).collect(Collectors.toList());
    }

    @Override
    public TopicDTO findById(Long id) {
        return topicMapper.toDto(topicRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Topic with id " + id + " not found")));
    }

    @Override
    public void createTopic(TopicDTO topicDTO, User user) {
        Topic topic = topicMapper.toEntity(topicDTO);
        topic.setNumberOfViews(0L);
        topic.setUser(user);
        topicRepository.save(topic);

        Post post = new Post();
        post.setContent(topic.getContent());
        post.setCreationDate(topic.getCreationDate());
        post.setSection(topic.getSection());
        post.setUser(topic.getUser());
        post.setTopic(topic);
        postRepository.save(post);
    }
}
