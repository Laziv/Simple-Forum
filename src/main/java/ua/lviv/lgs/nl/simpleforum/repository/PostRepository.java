package ua.lviv.lgs.nl.simpleforum.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.lviv.lgs.nl.simpleforum.entity.Post;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Integer countPostsByTopicId(Long topicId);

    Integer countPostsBySectionId(Long sectionId);

    Integer countPostsByUserId(Long userId);

    List<Post> findAllByTopicId(Long topicId);

    @Query
    Page<Post> findAllByTopicId(Long topicId, Pageable pageable);


    Optional<Post> findFirstByTopicIdOrderByCreationDateDesc(Long topicId);

    Optional<Post> findFirstBySectionIdOrderByCreationDateDesc(Long sectionId);

}
