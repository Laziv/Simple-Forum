package ua.lviv.lgs.nl.simpleforum.service;

import ua.lviv.lgs.nl.simpleforum.dto.SectionDTO;
import ua.lviv.lgs.nl.simpleforum.entity.Section;

import java.util.List;


public interface SectionService {
    List<SectionDTO> findAll();

    void createSection(SectionDTO sectionDTO);

    SectionDTO findById(Long id);
}
