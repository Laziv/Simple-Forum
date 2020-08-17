package ua.lviv.lgs.nl.simpleforum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.lviv.lgs.nl.simpleforum.dto.PostDTO;
import ua.lviv.lgs.nl.simpleforum.entity.User;
import ua.lviv.lgs.nl.simpleforum.service.PostService;
import ua.lviv.lgs.nl.simpleforum.service.SectionService;
import ua.lviv.lgs.nl.simpleforum.service.TopicService;
import ua.lviv.lgs.nl.simpleforum.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/section/{sectionId}/topic/{topicId}")
public class PostController {
    private final SectionService sectionService;
    private final TopicService topicService;
    private final UserService userService;
    private final PostService postService;

    @Autowired
    public PostController(SectionService sectionService, TopicService topicService, UserService userService, PostService postService) {
        this.sectionService = sectionService;
        this.topicService = topicService;
        this.userService = userService;
        this.postService = postService;
    }


    @GetMapping()
    public String showListPosts(Model model,
                                @PathVariable Long sectionId,
                                @PathVariable Long topicId,
                                Pageable pageable) {

        PostDTO postDTO = new PostDTO();
        postDTO.setSectionId(sectionId);
        postDTO.setTopicId(topicId);

        model.addAttribute("post", postDTO);
        model.addAttribute("posts", postService.findAllByTopicId(topicId, pageable));
        model.addAttribute("topic", topicService.findById(topicId));
        return "topic";
    }

    @PostMapping("/new-post")
    public String createPost(@ModelAttribute("post") @Valid PostDTO postDTO,
                             BindingResult bindingResult,
                             Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        if (!bindingResult.hasErrors()) {
            postService.createPost(postDTO, user);
        }
        return "redirect:/section/" + postDTO.getSectionId() + "/topic/" + postDTO.getTopicId();
    }

}