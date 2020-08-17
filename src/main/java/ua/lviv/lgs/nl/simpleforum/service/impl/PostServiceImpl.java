package ua.lviv.lgs.nl.simpleforum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.lviv.lgs.nl.simpleforum.dto.PostDTO;
import ua.lviv.lgs.nl.simpleforum.entity.Post;
import ua.lviv.lgs.nl.simpleforum.entity.Topic;
import ua.lviv.lgs.nl.simpleforum.entity.User;
import ua.lviv.lgs.nl.simpleforum.repository.PostRepository;
import ua.lviv.lgs.nl.simpleforum.repository.TopicRepository;
import ua.lviv.lgs.nl.simpleforum.service.PostService;
import ua.lviv.lgs.nl.simpleforum.service.mapper.PostMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private final PostMapper postMapper;
    private final PostRepository postRepository;
    private final TopicRepository topicRepository;

    @Autowired
    public PostServiceImpl(PostMapper postMapper, PostRepository postRepository, TopicRepository topicRepository) {
        this.postMapper = postMapper;
        this.postRepository = postRepository;
        this.topicRepository = topicRepository;
    }


    @Override
    public List<PostDTO> findAllByTopicId(Long topicId) {
        Optional<Topic> topicOptional =  topicRepository.findById(topicId);
        if (topicOptional.isPresent()){
            Topic topic = topicOptional.get();
            topic.setNumberOfViews(topic.getNumberOfViews() + 1 );
            topicRepository.save(topic);
        }

        return postRepository.findAllByTopicId(topicId).stream().map(
                (e) -> {
                    PostDTO postDTO = postMapper.toDto(e);
                    Long userId = postDTO.getUser().getId();
                    Integer numberOfUserPosts = postRepository.countPostsByUserId(userId);
                    postDTO.getUser().setNumberOfPosts(numberOfUserPosts);
                    return postDTO;
                }
        ).collect(Collectors.toList());
    }

    @Override
    public Page<PostDTO> findAllByTopicId(Long topicId, Pageable pageable) {
        Optional<Topic> topicOptional =  topicRepository.findById(topicId);
        if (topicOptional.isPresent()){
            Topic topic = topicOptional.get();
            topic.setNumberOfViews(topic.getNumberOfViews() + 1 );
            topicRepository.save(topic);
        }

        return postRepository.findAllByTopicId(topicId, pageable).map(
                (e) -> {
                    PostDTO postDTO = postMapper.toDto(e);
                    Long userId = postDTO.getUser().getId();
                    Integer numberOfUserPosts = postRepository.countPostsByUserId(userId);
                    postDTO.getUser().setNumberOfPosts(numberOfUserPosts);
                    return postDTO;
                }
        );
    }


    @Override
    public void createPost(PostDTO postDTO, User user) {
        Post post = postMapper.toEntity(postDTO);
        post.setUser(user);
        postRepository.save(post);
    }
}
