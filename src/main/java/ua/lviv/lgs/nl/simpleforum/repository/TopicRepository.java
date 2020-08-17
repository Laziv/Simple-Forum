package ua.lviv.lgs.nl.simpleforum.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.lgs.nl.simpleforum.entity.Topic;

import java.util.List;
import java.util.Optional;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    List<Topic> findAllBySectionIdOrderByCreationDateDesc(Long sectionId);

    Integer countTopicsBySectionId(Long sectionId);

    Optional<Topic> findById(Long id);

    Page<Topic> findAll(Pageable pageable);
}
