package ua.lviv.lgs.nl.simpleforum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.lviv.lgs.nl.simpleforum.dto.TopicDTO;
import ua.lviv.lgs.nl.simpleforum.entity.User;
import ua.lviv.lgs.nl.simpleforum.service.SectionService;
import ua.lviv.lgs.nl.simpleforum.service.TopicService;
import ua.lviv.lgs.nl.simpleforum.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/section/{sectionId}")
public class TopicController {
    private final SectionService sectionService;
    private final TopicService topicService;
    private final UserService userService;

    @Autowired
    public TopicController(SectionService sectionService, TopicService topicService, UserService userService) {
        this.sectionService = sectionService;
        this.topicService = topicService;
        this.userService = userService;
    }


    @GetMapping("/new-topic")
    public String getCreateTopicPage(Model model,
                                     @PathVariable Long sectionId) {

        TopicDTO topicDTO = new TopicDTO();
        topicDTO.setSectionId(sectionId);

        model.addAttribute("topic", topicDTO);
        return "add-topic";
    }

    @PostMapping("/new-topic")
    public String createSection(@ModelAttribute("topic") @Valid TopicDTO topicDTO,
                                BindingResult bindingResult,
                                Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        if (bindingResult.hasErrors()) {
            return "add-topic";
        }
        topicService.createTopic(topicDTO, user);
        return "redirect:/section/" + topicDTO.getSectionId();
    }

}