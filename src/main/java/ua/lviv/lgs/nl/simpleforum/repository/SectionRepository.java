package ua.lviv.lgs.nl.simpleforum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.lgs.nl.simpleforum.entity.Section;
import ua.lviv.lgs.nl.simpleforum.entity.Topic;

import java.util.List;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {
}
