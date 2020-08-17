package ua.lviv.lgs.nl.simpleforum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.lgs.nl.simpleforum.dto.PostDTO;
import ua.lviv.lgs.nl.simpleforum.dto.SectionDTO;
import ua.lviv.lgs.nl.simpleforum.entity.Post;
import ua.lviv.lgs.nl.simpleforum.entity.Section;
import ua.lviv.lgs.nl.simpleforum.exception.ResourceNotFoundException;
import ua.lviv.lgs.nl.simpleforum.repository.PostRepository;
import ua.lviv.lgs.nl.simpleforum.repository.SectionRepository;
import ua.lviv.lgs.nl.simpleforum.repository.TopicRepository;
import ua.lviv.lgs.nl.simpleforum.service.SectionService;
import ua.lviv.lgs.nl.simpleforum.service.mapper.PostMapper;
import ua.lviv.lgs.nl.simpleforum.service.mapper.SectionMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SectionServiceImpl implements SectionService {
    private final SectionRepository sectionRepository;
    private final SectionMapper sectionMapper;
    private final TopicRepository topicRepository;
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Autowired
    public SectionServiceImpl(SectionRepository sectionRepository, SectionMapper sectionMapper, TopicRepository topicRepository, PostRepository postRepository, PostMapper postMapper) {
        this.sectionRepository = sectionRepository;
        this.sectionMapper = sectionMapper;
        this.topicRepository = topicRepository;
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }


    @Override
    public List<SectionDTO> findAll() {
        return sectionRepository.findAll().stream().map(
                (e) -> {
                    int topics = topicRepository.countTopicsBySectionId(e.getId());
                    int posts = postRepository.countPostsBySectionId(e.getId());
                    SectionDTO sectionDTO = sectionMapper.toDto(e);
                    sectionDTO.setNumberOfTopics(topics);
                    sectionDTO.setNumberOfPosts(posts);

                    Optional<Post> lastPost =  postRepository.findFirstBySectionIdOrderByCreationDateDesc(e.getId());
                    if (lastPost.isPresent()){
                        PostDTO lastPostDTO = postMapper.toDto(lastPost.get());
                        sectionDTO.setLastPost(lastPostDTO);
                    }
                    return sectionDTO;
                }
        ).collect(Collectors.toList());
    }

    @Override
    public void createSection(SectionDTO sectionDTO) {
        Section section = sectionMapper.toEntity(sectionDTO);
        sectionRepository.save(section);
    }

    @Override
    public SectionDTO findById(Long id) {
        return sectionMapper.toDto(sectionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Section with id " + id + " not found")));
    }

}
