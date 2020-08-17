package ua.lviv.lgs.nl.simpleforum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.lviv.lgs.nl.simpleforum.dto.SectionDTO;
import ua.lviv.lgs.nl.simpleforum.dto.TopicDTO;
import ua.lviv.lgs.nl.simpleforum.entity.Topic;
import ua.lviv.lgs.nl.simpleforum.service.SectionService;
import ua.lviv.lgs.nl.simpleforum.service.TopicService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/section")
public class SectionController {
    private final SectionService sectionService;
     private final TopicService topicService;
    @Autowired
    public SectionController(SectionService sectionService, TopicService topicService) {
        this.sectionService = sectionService;
        this.topicService = topicService;
    }


    @Secured("ROLE_ADMIN")
    @GetMapping("/new")
    public String getCreateSectionPage(Model model){
        model.addAttribute("section", new SectionDTO());
        return "add-section";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/new")
    public String createSection(@ModelAttribute("section") @Valid  SectionDTO sectionDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "add-section";
        }else {
            sectionService.createSection(sectionDTO);
            return "redirect:/";
        }
    }

    @GetMapping("/{id}")
    public String getTopicsByCategoryId(Model model, @PathVariable Long id) {

        List<TopicDTO> topics = topicService.findAllBySectionId(id);
        model.addAttribute("topics", topics);
        model.addAttribute("section", sectionService.findById(id));
        return "section";
    }
}